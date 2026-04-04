package kmp.apigen

import java.io.File

/**
 * Generates database schema from OpenAPI schemas.
 *
 * Each object schema → Exposed table (JVM) + SQLDelight .sq (KMP).
 * Properties → columns. $ref → foreign keys.
 */
class DatabaseEmitter : ProtocolEmitter {
    override val protocol = "database"

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
            appendLine()

            spec.schemas.forEach { (name, schema) ->
                if (schema !is ParsedSchema.Object) return@forEach
                if (schema.properties.size < 2) return@forEach

                val tableName = name.lowercase() + "s"
                appendLine("object ${name}s : Table(\"$tableName\") {")

                schema.properties.forEach { (propName, prop) ->
                    val colName = propName.lowercase()
                    val colDef = when (prop.type) {
                        "String" -> "varchar(\"$colName\", 255)"
                        "Int" -> "integer(\"$colName\")"
                        "Long" -> "long(\"$colName\")"
                        "Double" -> "double(\"$colName\")"
                        "Boolean" -> "bool(\"$colName\")"
                        else -> {
                            if (prop.ref != null) {
                                val refTable = prop.ref!!.substringAfterLast("/") + "s"
                                "reference(\"${colName}_id\", $refTable)"
                            } else {
                                "text(\"$colName\")"
                            }
                        }
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
                    val sqlType = when (prop.type) {
                        "String" -> "TEXT"
                        "Int", "Long" -> "INTEGER"
                        "Double" -> "REAL"
                        "Boolean" -> "INTEGER"
                        "Url" -> "TEXT"
                        else -> if (prop.ref != null) "INTEGER" else "TEXT"
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
