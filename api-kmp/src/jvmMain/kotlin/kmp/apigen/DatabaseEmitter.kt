package kmp.apigen

import java.io.File

/**
 * Generates database schema from OpenAPI schemas.
 *
 * Each object schema → Exposed table (JVM, PostgreSQL 18 jsonb) +
 * SQLDelight .sq (KMP, SQLite TEXT as JSON).
 *
 * **JSON storage strategy (PG 18 + SQLDelight):**
 * - Complex RFC types (VCardContact/jCard RFC 7095, ICalEvent/jCal RFC 7265)
 *   stored as jsonb (PG) / TEXT (SQLite) — Wire type → kotlinx.serialization.json
 * - Scalar fields (String/Int/Bool/Date/UUID) stored as native columns
 * - Nested objects (oneOf/anyOf) → jsonb column
 * - Arrays of scalars → jsonb array (PG) / TEXT JSON (SQLite)
 * - $ref to another schema → INTEGER foreign key + optional embedded jsonb for denorm
 *
 * The generated code serializes Wire types via jCard/jCal helpers:
 *   Validators.toJCard(vcard)  → jsonb string for INSERT
 *   Validators.parseJCard(row) → VCardContact for SELECT
 *   Validators.toJCalRfc7265(events) → jsonb for events array
 */
class DatabaseEmitter : ProtocolEmitter {
    override val protocol = "database"

    /** Types stored as JSON (jsonb on PG, TEXT on SQLite). */
    private val jsonTypes = setOf(
        "VCardContact", "ICalEvent", "ICalAttendee", "ICalAlarm",
        "PersonName", "PostalAddress", "PhoneNumber", "GeoPoint", "GeoIp",
        "Measure", "PatchEvent", "PatchOperation",
    )

    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val dbPackage = "$pkg.db"
        val dbDir = File(outputDir, dbPackage.replace(".", "/"))
        dbDir.mkdirs()

        // Generate Exposed tables (Kotlin)
        val exposedCode = buildString {
            appendLine("package $dbPackage")
            appendLine()
            appendLine("import org.jetbrains.exposed.sql.*")
            appendLine("import org.jetbrains.exposed.sql.Table")
            appendLine("import org.jetbrains.exposed.sql.json.jsonb")
            appendLine("import kotlinx.serialization.json.Json")
            appendLine("import kotlinx.serialization.json.JsonElement")
            appendLine()
            appendLine("// PostgreSQL 18 jsonb columns for RFC 7095 jCard / RFC 7265 jCal storage")
            appendLine("// via Exposed's jsonb() column type + kotlinx.serialization.json.JsonElement")
            appendLine()

            spec.schemas.forEach { (name, schema) ->
                if (schema !is ParsedSchema.Object) return@forEach
                if (schema.properties.size < 2) return@forEach

                val tableName = name.lowercase() + "s"
                appendLine("object ${name}s : Table(\"$tableName\") {")

                schema.properties.forEach { (propName, prop) ->
                    val colName = propName.lowercase()
                    val isJson = prop.type in jsonTypes ||
                        prop.type.startsWith("List<") ||
                        (prop.ref != null && prop.ref!!.substringAfterLast("/") in jsonTypes)
                    val colDef = when {
                        isJson -> "jsonb<JsonElement>(\"$colName\", Json) { it }"
                        prop.type == "String" -> "varchar(\"$colName\", 255)"
                        prop.type == "Int" -> "integer(\"$colName\")"
                        prop.type == "Long" -> "long(\"$colName\")"
                        prop.type == "Double" -> "double(\"$colName\")"
                        prop.type == "Boolean" -> "bool(\"$colName\")"
                        prop.ref != null -> {
                            val refTable = prop.ref!!.substringAfterLast("/") + "s"
                            "reference(\"${colName}_id\", $refTable)"
                        }
                        else -> "text(\"$colName\")"
                    }
                    val nullable = if (prop.nullable) ".nullable()" else ""
                    val autoInc = if (propName == "id" && prop.type in listOf("Int", "Long")) ".autoIncrement()" else ""
                    appendLine("    val $colName = $colDef$autoInc$nullable")
                }

                // Primary key
                if (schema.properties.containsKey("id")) {
                    appendLine("    override val primaryKey = PrimaryKey(id)")
                }

                appendLine("}")
                appendLine()
            }
        }
        File(dbDir, "Tables.kt").writeText(exposedCode)

        // Generate SQLDelight .sq files
        val sqDir = File(outputDir, "../sqldelight/$dbPackage".replace(".", "/"))
        sqDir.mkdirs()

        spec.schemas.forEach { (name, schema) ->
            if (schema !is ParsedSchema.Object) return@forEach
            if (schema.properties.size < 2) return@forEach

            val tableName = name.lowercase()
            val sql = buildString {
                // CREATE TABLE
                appendLine("CREATE TABLE $tableName (")
                // SQLite (SQLDelight) — TEXT AS JsonElement + CHECK(json_valid) so
                // the DB enforces JSON validity AND json_extract / json1 works.
                // SQLite 3.45+ stores JSON efficiently via internal jsonb encoding.
                val cols = schema.properties.map { (propName, prop) ->
                    val colName = propName.lowercase()
                    val isJson = prop.type in jsonTypes ||
                        prop.type.startsWith("List<") ||
                        (prop.ref != null && prop.ref!!.substringAfterLast("/") in jsonTypes)
                    val sqlType = when {
                        // SQLDelight TEXT AS kotlinx.serialization.json.JsonElement —
                        // SQLite json1 extension functions (json_extract, json_each,
                        // json_valid) work directly against this column.
                        isJson -> "TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid($colName))"
                        prop.type == "String" -> "TEXT"
                        prop.type in listOf("Int", "Long") -> "INTEGER"
                        prop.type == "Double" -> "REAL"
                        prop.type == "Boolean" -> "INTEGER"
                        prop.type in listOf("Url", "Uri") -> "TEXT"
                        prop.type in listOf("Instant", "LocalDate", "LocalTime", "Duration") -> "TEXT"
                        prop.type in listOf("Uuid", "Email", "IpAddress", "Hostname", "Password") -> "TEXT"
                        prop.type in listOf("Base64", "Binary") -> "BLOB"
                        prop.type == "Phone" -> "TEXT"
                        prop.type == "Float" -> "REAL"
                        prop.ref != null -> "INTEGER"
                        else -> "TEXT"
                    }
                    val notNull = if (!prop.nullable && propName in schema.required) " NOT NULL" else ""
                    val pk = if (propName == "id") " PRIMARY KEY" else ""
                    val fk = if (prop.ref != null) {
                        val refTable = prop.ref!!.substringAfterLast("/").lowercase()
                        " REFERENCES $refTable(id)"
                    } else ""
                    "    $colName $sqlType$pk$notNull$fk"
                }
                appendLine(cols.joinToString(",\n"))
                appendLine(");")
                appendLine()

                // Queries
                appendLine("selectAll:")
                appendLine("SELECT * FROM $tableName;")
                appendLine()
                appendLine("findById:")
                appendLine("SELECT * FROM $tableName WHERE id = :id;")
                appendLine()
                appendLine("insert:")
                val insertCols = schema.properties.keys.joinToString(", ") { it.lowercase() }
                val insertParams = schema.properties.keys.joinToString(", ") { ":" + it.lowercase() }
                appendLine("INSERT INTO $tableName ($insertCols) VALUES ($insertParams);")
                appendLine()
                appendLine("deleteById:")
                appendLine("DELETE FROM $tableName WHERE id = :id;")
                appendLine()

                // Search by each property
                schema.properties.filter { it.value.type in listOf("String", "Int", "Long") }.forEach { (propName, _) ->
                    val colName = propName.lowercase()
                    appendLine("findBy${propName.replaceFirstChar { it.uppercase() }}:")
                    appendLine("SELECT * FROM $tableName WHERE $colName = :$colName;")
                    appendLine()
                }

                // Paged query
                appendLine("selectPaged:")
                appendLine("SELECT * FROM $tableName LIMIT :limit OFFSET :offset;")
                appendLine()
                appendLine("count:")
                appendLine("SELECT COUNT(*) FROM $tableName;")

                // JSON queries — for columns stored as JSON, expose SQLite json_extract
                // and json_each helpers (SQLite 3.38+ / SQLDelight 2.x).
                // PG equivalent uses jsonb ->> operator in the generated PG flavor.
                val jsonCols = schema.properties.filter { (_, p) ->
                    p.type in jsonTypes ||
                        p.type.startsWith("List<") ||
                        (p.ref != null && p.ref!!.substringAfterLast("/") in jsonTypes)
                }
                jsonCols.forEach { (propName, _) ->
                    val colName = propName.lowercase()
                    appendLine()
                    appendLine("-- JSON path query (SQLite json_extract / PG jsonb ->>)")
                    appendLine("${colName}AtPath:")
                    appendLine("SELECT * FROM $tableName")
                    appendLine("WHERE json_extract($colName, :path) = :value;")
                    appendLine()
                    appendLine("${colName}Contains:")
                    appendLine("SELECT * FROM $tableName")
                    appendLine("WHERE json_extract($colName, :path) LIKE '%' || :value || '%';")
                    appendLine()
                    appendLine("${colName}ArrayContains:")
                    appendLine("SELECT * FROM $tableName t, json_each(t.$colName, :path) j")
                    appendLine("WHERE j.value = :value;")
                }
                if (jsonCols.isNotEmpty()) {
                    appendLine()
                    appendLine("-- Indexes on JSON paths (SQLite expression indexes, PG jsonb_path_ops)")
                    jsonCols.forEach { (propName, _) ->
                        val colName = propName.lowercase()
                        appendLine("CREATE INDEX IF NOT EXISTS idx_${tableName}_${colName}_uid")
                        appendLine("  ON $tableName(json_extract($colName, '\$.uid'));")
                    }
                }

                // Custom SQL from x-sql extensions in paths
                spec.paths.values
                    .filter { it.customSql != null }
                    .forEach { path ->
                        appendLine()
                        appendLine("${path.operationId}:")
                        appendLine("${path.customSql};")
                    }
            }
            File(sqDir, "$tableName.sq").writeText(sql)

            // Companion PG18 schema — native jsonb + GIN indexes per column
            File(sqDir, "$tableName.pg.sql").writeText(emitPgSchema(name, schema, tableName))
        }

        // Always emit proto-defined vcard_contacts + ical_events tables —
        // these are composable/exposable first-class types, not optional.
        File(sqDir, "vcard_contact.sq").writeText(vCardContactSqliteSchema())
        File(sqDir, "vcard_contact.pg.sql").writeText(vCardContactPgSchema())
        File(sqDir, "ical_event.sq").writeText(iCalEventSqliteSchema())
        File(sqDir, "ical_event.pg.sql").writeText(iCalEventPgSchema())
    }

    /** Emit PostgreSQL 18 schema with native jsonb + GIN indexes. */
    private fun emitPgSchema(
        name: String,
        schema: ParsedSchema.Object,
        tableName: String,
    ): String = buildString {
        appendLine("-- PostgreSQL 18 schema for $name")
        appendLine("-- Uses native jsonb for composable types + GIN indexes for json query.")
        appendLine()
        appendLine("CREATE TABLE IF NOT EXISTS $tableName (")
        val cols = schema.properties.map { (propName, prop) ->
            val colName = propName.lowercase()
            val isJson = prop.type in jsonTypes ||
                prop.type.startsWith("List<") ||
                (prop.ref != null && prop.ref!!.substringAfterLast("/") in jsonTypes)
            val sqlType = when {
                isJson -> "jsonb"
                prop.type == "String" -> "text"
                prop.type == "Int" -> "integer"
                prop.type == "Long" -> "bigint"
                prop.type == "Double" -> "double precision"
                prop.type == "Boolean" -> "boolean"
                prop.type == "Instant" -> "timestamptz"
                prop.type == "LocalDate" -> "date"
                prop.type == "LocalTime" -> "time"
                prop.type == "Duration" -> "interval"
                prop.type == "Uuid" -> "uuid"
                prop.type in listOf("Base64", "Binary") -> "bytea"
                prop.type == "Float" -> "real"
                prop.ref != null -> "bigint"
                else -> "text"
            }
            val notNull = if (!prop.nullable && propName in schema.required) " NOT NULL" else ""
            val pk = if (propName == "id") " PRIMARY KEY" else ""
            val fk = if (prop.ref != null) {
                val refTable = prop.ref!!.substringAfterLast("/").lowercase()
                " REFERENCES $refTable(id)"
            } else ""
            "    $colName $sqlType$pk$notNull$fk"
        }
        appendLine(cols.joinToString(",\n"))
        appendLine(");")
        appendLine()
        // GIN indexes on jsonb columns (jsonb_path_ops for @> containment queries)
        schema.properties.forEach { (propName, prop) ->
            val colName = propName.lowercase()
            val isJson = prop.type in jsonTypes ||
                prop.type.startsWith("List<") ||
                (prop.ref != null && prop.ref!!.substringAfterLast("/") in jsonTypes)
            if (isJson) {
                appendLine("CREATE INDEX IF NOT EXISTS idx_${tableName}_${colName}_gin")
                appendLine("    ON $tableName USING gin ($colName jsonb_path_ops);")
            }
        }
    }

    /**
     * SQLite/SQLDelight schema for VCardContact (RFC 6350).
     * All repeated + nested fields stored as jsonb-validated TEXT.
     */
    private fun vCardContactSqliteSchema(): String = """
        -- RFC 6350 vCard Contact — proto-defined, always emitted.
        -- All repeated fields stored as JSON (validated via json_valid).
        
        CREATE TABLE IF NOT EXISTS vcard_contact (
            uid TEXT PRIMARY KEY NOT NULL,
            kind TEXT NOT NULL DEFAULT 'individual',
            source TEXT,
            name TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(name)),
            nicknames TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(nicknames)),
            photo TEXT,
            birthday TEXT,
            anniversary TEXT,
            gender TEXT,
            addresses TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(addresses)),
            phones TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(phones)),
            emails TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(emails)),
            impp TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(impp)),
            languages TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(languages)),
            jabber_id TEXT,
            timezone TEXT,
            geo TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(geo)),
            title TEXT,
            role TEXT,
            logo TEXT,
            organization TEXT,
            urls TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(urls)),
            categories TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(categories)),
            note TEXT,
            rev TEXT,
            key TEXT,
            fburl TEXT,
            cal_uri TEXT,
            full_json TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(full_json))  -- jCard RFC 7095 backup
        );
        
        -- Expression indexes on common lookup paths
        CREATE INDEX IF NOT EXISTS idx_vcard_contact_email
            ON vcard_contact(json_extract(emails, '${'$'}[0]'));
        CREATE INDEX IF NOT EXISTS idx_vcard_contact_phone
            ON vcard_contact(json_extract(phones, '${'$'}[0].e164_number'));
        CREATE INDEX IF NOT EXISTS idx_vcard_contact_jabber ON vcard_contact(jabber_id);
        CREATE INDEX IF NOT EXISTS idx_vcard_contact_kind ON vcard_contact(kind);
        
        -- Queries
        selectAll:
        SELECT * FROM vcard_contact;
        
        findByUid:
        SELECT * FROM vcard_contact WHERE uid = :uid;
        
        findByEmail:
        SELECT * FROM vcard_contact, json_each(emails) j
        WHERE j.value = :email;
        
        findByCategory:
        SELECT * FROM vcard_contact, json_each(categories) j
        WHERE j.value = :category;
        
        findByJabberId:
        SELECT * FROM vcard_contact WHERE jabber_id = :jid;
        
        findByKind:
        SELECT * FROM vcard_contact WHERE kind = :kind;
        
        insert:
        INSERT INTO vcard_contact VALUES (
            :uid, :kind, :source, :name, :nicknames, :photo, :birthday, :anniversary,
            :gender, :addresses, :phones, :emails, :impp, :languages, :jabber_id,
            :timezone, :geo, :title, :role, :logo, :organization, :urls,
            :categories, :note, :rev, :key, :fburl, :cal_uri, :full_json
        );
        
        deleteByUid:
        DELETE FROM vcard_contact WHERE uid = :uid;
    """.trimIndent()

    /** PostgreSQL 18 schema for VCardContact with native jsonb + GIN. */
    private fun vCardContactPgSchema(): String = """
        -- RFC 6350 vCard Contact — PostgreSQL 18 native jsonb schema.
        
        CREATE TABLE IF NOT EXISTS vcard_contact (
            uid           text PRIMARY KEY NOT NULL,
            kind          text NOT NULL DEFAULT 'individual',
            source        text,
            name          jsonb,
            nicknames     jsonb DEFAULT '[]'::jsonb,
            photo         text,
            birthday      date,
            anniversary   date,
            gender        text,
            addresses     jsonb DEFAULT '[]'::jsonb,
            phones        jsonb DEFAULT '[]'::jsonb,
            emails        jsonb DEFAULT '[]'::jsonb,
            impp          jsonb DEFAULT '[]'::jsonb,
            languages     jsonb DEFAULT '[]'::jsonb,
            jabber_id     text,
            timezone      text,
            geo           jsonb,
            title         text,
            role          text,
            logo          text,
            organization  text,
            urls          jsonb DEFAULT '[]'::jsonb,
            categories    jsonb DEFAULT '[]'::jsonb,
            note          text,
            rev           timestamptz,
            key           text,
            fburl         text,
            cal_uri       text,
            full_json     jsonb,  -- jCard RFC 7095 backup
            created_at    timestamptz DEFAULT now(),
            updated_at    timestamptz DEFAULT now()
        );
        
        -- GIN indexes on jsonb columns for @>, ?, ?&, ?| operators
        CREATE INDEX IF NOT EXISTS idx_vcard_emails_gin ON vcard_contact USING gin (emails jsonb_path_ops);
        CREATE INDEX IF NOT EXISTS idx_vcard_phones_gin ON vcard_contact USING gin (phones jsonb_path_ops);
        CREATE INDEX IF NOT EXISTS idx_vcard_categories_gin ON vcard_contact USING gin (categories jsonb_path_ops);
        CREATE INDEX IF NOT EXISTS idx_vcard_languages_gin ON vcard_contact USING gin (languages jsonb_path_ops);
        CREATE INDEX IF NOT EXISTS idx_vcard_addresses_gin ON vcard_contact USING gin (addresses jsonb_path_ops);
        CREATE INDEX IF NOT EXISTS idx_vcard_impp_gin ON vcard_contact USING gin (impp jsonb_path_ops);
        CREATE INDEX IF NOT EXISTS idx_vcard_full_json_gin ON vcard_contact USING gin (full_json jsonb_path_ops);
        
        -- Scalar B-tree indexes
        CREATE INDEX IF NOT EXISTS idx_vcard_jabber_id ON vcard_contact(jabber_id);
        CREATE INDEX IF NOT EXISTS idx_vcard_kind ON vcard_contact(kind);
        CREATE INDEX IF NOT EXISTS idx_vcard_timezone ON vcard_contact(timezone);
    """.trimIndent()

    /** SQLite/SQLDelight schema for ICalEvent (RFC 5545). */
    private fun iCalEventSqliteSchema(): String = """
        -- RFC 5545 iCalendar Event — proto-defined, always emitted.
        
        CREATE TABLE IF NOT EXISTS ical_event (
            uid TEXT PRIMARY KEY NOT NULL,
            summary TEXT,
            description TEXT,
            location TEXT,
            dt_start TEXT,
            dt_end TEXT,
            duration TEXT,
            rrule TEXT,
            status TEXT,
            priority INTEGER DEFAULT 0,
            url TEXT,
            geo TEXT AS kotlinx.serialization.json.JsonElement CHECK (geo IS NULL OR json_valid(geo)),
            categories TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(categories)),
            organizer TEXT,
            attendees TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(attendees)),
            alarms TEXT AS kotlinx.serialization.json.JsonElement CHECK (json_valid(alarms)),
            created TEXT,
            last_modified TEXT,
            full_jcal TEXT AS kotlinx.serialization.json.JsonElement CHECK (full_jcal IS NULL OR json_valid(full_jcal))  -- jCal RFC 7265 backup
        );
        
        -- Indexes for common date-range + status filters
        CREATE INDEX IF NOT EXISTS idx_ical_dt_start ON ical_event(dt_start);
        CREATE INDEX IF NOT EXISTS idx_ical_dt_end ON ical_event(dt_end);
        CREATE INDEX IF NOT EXISTS idx_ical_status ON ical_event(status);
        CREATE INDEX IF NOT EXISTS idx_ical_organizer ON ical_event(organizer);
        
        -- Queries
        selectAll:
        SELECT * FROM ical_event;
        
        findByUid:
        SELECT * FROM ical_event WHERE uid = :uid;
        
        findInRange:
        SELECT * FROM ical_event
        WHERE dt_start >= :start AND dt_start < :end
        ORDER BY dt_start;
        
        findByStatus:
        SELECT * FROM ical_event WHERE status = :status;
        
        findByCategory:
        SELECT * FROM ical_event, json_each(categories) j
        WHERE j.value = :category;
        
        findByAttendee:
        SELECT * FROM ical_event, json_each(attendees) j
        WHERE json_extract(j.value, '${'$'}.email') = :email;
        
        insert:
        INSERT INTO ical_event VALUES (
            :uid, :summary, :description, :location, :dt_start, :dt_end, :duration,
            :rrule, :status, :priority, :url, :geo, :categories, :organizer,
            :attendees, :alarms, :created, :last_modified, :full_jcal
        );
        
        deleteByUid:
        DELETE FROM ical_event WHERE uid = :uid;
    """.trimIndent()

    /** PostgreSQL 18 schema for ICalEvent with native jsonb + GIN. */
    private fun iCalEventPgSchema(): String = """
        -- RFC 5545 iCalendar Event — PostgreSQL 18 native jsonb schema.
        
        CREATE TABLE IF NOT EXISTS ical_event (
            uid           text PRIMARY KEY NOT NULL,
            summary       text,
            description   text,
            location      text,
            dt_start      timestamptz,
            dt_end        timestamptz,
            duration      interval,
            rrule         text,
            status        text,
            priority      integer DEFAULT 0,
            url           text,
            geo           jsonb,
            categories    jsonb DEFAULT '[]'::jsonb,
            organizer     text,
            attendees     jsonb DEFAULT '[]'::jsonb,
            alarms        jsonb DEFAULT '[]'::jsonb,
            created       timestamptz,
            last_modified timestamptz,
            full_jcal     jsonb,
            created_at    timestamptz DEFAULT now(),
            updated_at    timestamptz DEFAULT now()
        );
        
        -- Range index for calendar-query time-range REPORTs
        CREATE INDEX IF NOT EXISTS idx_ical_range ON ical_event USING btree (dt_start, dt_end);
        CREATE INDEX IF NOT EXISTS idx_ical_status ON ical_event(status);
        CREATE INDEX IF NOT EXISTS idx_ical_organizer ON ical_event(organizer);
        
        -- GIN indexes for jsonb containment + path queries
        CREATE INDEX IF NOT EXISTS idx_ical_categories_gin ON ical_event USING gin (categories jsonb_path_ops);
        CREATE INDEX IF NOT EXISTS idx_ical_attendees_gin ON ical_event USING gin (attendees jsonb_path_ops);
        CREATE INDEX IF NOT EXISTS idx_ical_alarms_gin ON ical_event USING gin (alarms jsonb_path_ops);
        CREATE INDEX IF NOT EXISTS idx_ical_full_jcal_gin ON ical_event USING gin (full_jcal jsonb_path_ops);
    """.trimIndent()
}
