package kmp.apigen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File

/**
 * Generates Compose HTML (Kotlin/JS web) UI from OpenAPI schemas.
 *
 * Each object schema → Form, List, Detail, MasterDetail for web.
 * Uses org.jetbrains.compose.web.dom (Compose for Web).
 * $ref properties → FK links (<a> navigation).
 */
class HtmlEmitter : ProtocolEmitter {
    override val protocol = "html"

    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val modelsPackage = "$pkg.models"
        val htmlPackage = "$pkg.html"
        val htmlDir = File(outputDir, htmlPackage.replace(".", "/"))
        htmlDir.mkdirs()

        spec.schemas.forEach { (name, schema) ->
            if (schema !is ParsedSchema.Object) return@forEach
            if (schema.properties.size < 2) return@forEach

            val lowerName = name.lowercase()
            val html = buildString {
                appendLine("""
                    package $htmlPackage

                    import $modelsPackage.$name
                    import org.jetbrains.compose.web.dom.*
                    import org.jetbrains.compose.web.attributes.*
                    import org.jetbrains.compose.web.css.*
                    import androidx.compose.runtime.*

                """.trimIndent())

                // Form
                appendLine("""
                    @Composable
                    fun ${name}Form(
                        initial: $name? = null,
                        onSubmit: ($name) -> Unit,
                    ) {
                        ${schema.properties.filter { it.value.type in listOf("String", "Int", "Long", "Double") }.map { (propName, _) ->
                            val kn = propName.toCamelCase()
                            "var $kn by remember { mutableStateOf(initial?.$kn?.toString() ?: \"\") }"
                        }.joinToString("\n    ")}
                        Form(attrs = { onSubmit { it.preventDefault() } }) {
                """.trimIndent())

                schema.properties.forEach { (propName, prop) ->
                    val kn = propName.toCamelCase()
                    val label = propName.replace("_", " ").replaceFirstChar { it.uppercase() }
                    when {
                        prop.ref != null -> {
                            appendLine("""
                                            // FK: $label → ${prop.ref!!.substringAfterLast("/")}
                                            Label { Text("$label: "); A(href = "#/${prop.ref!!.substringAfterLast("/").lowercase()}") { Text(initial?.$kn?.toString() ?: "Select...") } }
                                            Br()
                            """.trimIndent())
                        }
                        prop.type == "Boolean" -> {
                            appendLine("""
                                            Label { CheckboxInput(checked = initial?.$kn ?: false); Text(" $label") }
                                            Br()
                            """.trimIndent())
                        }
                        prop.type in listOf("String", "Int", "Long", "Double") -> {
                            appendLine("""
                                            Label { Text("$label: "); TextInput(value = $kn) { onInput { $kn = it.value } } }
                                            Br()
                            """.trimIndent())
                        }
                        else -> appendLine("            // $label: ${prop.type}")
                    }
                }

                appendLine("""
                            Button(attrs = { onClick { /* build entity, call onSubmit */ } }) { Text("Save") }
                        }
                    }
                """.trimIndent())

                // List
                val displayProp = schema.properties.entries.firstOrNull { it.key == "name" }
                    ?: schema.properties.entries.firstOrNull { it.key == "title" }
                    ?: schema.properties.entries.firstOrNull { it.key == "id" }
                val displayField = displayProp?.key?.toCamelCase() ?: "toString()"

                appendLine("""

                    @Composable
                    fun ${name}List(items: List<$name>, onClick: ($name) -> Unit) {
                        Table {
                            Thead { Tr { ${schema.properties.keys.joinToString("; ") { "Th { Text(\"${it.replaceFirstChar { c -> c.uppercase() }}\") }" }} } }
                            Tbody {
                                items.forEach { item ->
                                    Tr(attrs = { onClick { onClick(item) }; style { cursor("pointer") } }) {
                                        ${schema.properties.map { (propName, prop) ->
                                            val kn = propName.toCamelCase()
                                            if (prop.ref != null) {
                                                "Td { A(href = \"#/${prop.ref!!.substringAfterLast("/").lowercase()}\") { Text(item.$kn?.toString() ?: \"\") } }"
                                            } else {
                                                "Td { Text(item.$kn?.toString() ?: \"\") }"
                                            }
                                        }.joinToString("\n                        ")}
                                    }
                                }
                            }
                        }
                    }
                """.trimIndent())

                // Detail
                appendLine("""

                    @Composable
                    fun ${name}Detail(entity: $name) {
                        Div {
                            H2 { Text("$name") }
                            Dl {
                                ${schema.properties.map { (propName, prop) ->
                                    val kn = propName.toCamelCase()
                                    val label = propName.replaceFirstChar { it.uppercase() }
                                    if (prop.ref != null) {
                                        "Dt { Text(\"$label\") }; Dd { A(href = \"#/${prop.ref!!.substringAfterLast("/").lowercase()}\") { Text(entity.$kn?.toString() ?: \"—\") } }"
                                    } else if (prop.nullable) {
                                        "Dt { Text(\"$label\") }; Dd { Text(entity.$kn?.toString() ?: \"—\") }"
                                    } else {
                                        "Dt { Text(\"$label\") }; Dd { Text(entity.$kn.toString()) }"
                                    }
                                }.joinToString("\n                ")}
                            }
                        }
                    }
                """.trimIndent())

                // MasterDetail
                appendLine("""

                    @Composable
                    fun ${name}MasterDetail(items: List<$name>) {
                        var selected by remember { mutableStateOf<$name?>(null) }
                        Div(attrs = { style { display(DisplayStyle.Flex) } }) {
                            Div(attrs = { style { flex(1); overflow("auto"); maxHeight(80.vh) } }) {
                                H3 { Text("${name}s") }
                                ${name}List(items = items, onClick = { selected = it })
                            }
                            Div(attrs = { style { flex(1); padding(16.px) } }) {
                                selected?.let { ${name}Detail(entity = it) }
                                    ?: P { Text("Select a $name") }
                            }
                        }
                    }
                """.trimIndent())
            }

            File(htmlDir, "${name}Html.kt").writeText(html)
        }
    }
}

private fun String.toCamelCase(): String =
    split("_", "-").mapIndexed { i, part ->
        if (i == 0) part.lowercase() else part.replaceFirstChar { it.uppercase() }
    }.joinToString("")
