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
                val cols = schema.properties.map { (propName, prop) ->
                    val colName = propName.lowercase()
                    val isJson = prop.type in jsonTypes ||
                        prop.type.startsWith("List<") ||
                        (prop.ref != null && prop.ref!!.substringAfterLast("/") in jsonTypes)
                    // SQLite: JSON stored as TEXT with AS kotlinx.serialization.json.JsonElement adapter
                    val sqlType = when {
                        isJson -> "TEXT AS kotlinx.serialization.json.JsonElement"
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
        }
    }
}
