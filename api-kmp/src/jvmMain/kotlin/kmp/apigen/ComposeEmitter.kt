package kmp.apigen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File

/**
 * Generates Compose Multiplatform UI from OpenAPI schemas.
 *
 * Each object schema → Form, List, Detail, MasterDetail @Composable.
 * Properties → form fields. $ref properties → FK navigation (clickable links).
 * Master-Detail: list on left, detail on right (or navigation on mobile).
 */
class ComposeEmitter : ProtocolEmitter {
    override val protocol = "compose"

    private val composable = ClassName("androidx.compose.runtime", "Composable")
    private val mutableStateOf = MemberName("androidx.compose.runtime", "mutableStateOf")
    private val remember = MemberName("androidx.compose.runtime", "remember")

    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val modelsPackage = "$pkg.models"
        val uiPackage = "$pkg.ui"

        spec.schemas.forEach { (name, schema) ->
            if (schema !is ParsedSchema.Object) return@forEach
            if (schema.properties.size < 2) return@forEach

            val entityType = ClassName(modelsPackage, name)
            val fileSpec = FileSpec.builder(uiPackage, "${name}UI")

            fileSpec.addFunction(generateForm(name, schema, entityType, modelsPackage))
            fileSpec.addFunction(generateList(name, schema, entityType, modelsPackage))
            fileSpec.addFunction(generateDetail(name, schema, entityType, modelsPackage))
            fileSpec.addFunction(generateMasterDetail(name, schema, entityType, modelsPackage))

            fileSpec.build().writeTo(outputDir)
        }
    }

    private fun generateForm(
        name: String,
        schema: ParsedSchema.Object,
        entityType: ClassName,
        modelsPackage: String,
    ): FunSpec {
        val funBuilder = FunSpec.builder("${name}Form")
            .addAnnotation(composable)
            .addParameter(ParameterSpec.builder("initial", entityType.copy(nullable = true)).defaultValue("null").build())
            .addParameter("onSubmit", LambdaTypeName.get(parameters = listOf(ParameterSpec.unnamed(entityType)), returnType = UNIT))

        // FK properties get a dropdown/selector callback
        schema.properties.filter { it.value.ref != null }.forEach { (propName, prop) ->
            val refType = ClassName(modelsPackage, prop.ref!!.substringAfterLast("/"))
            funBuilder.addParameter(
                ParameterSpec.builder("on${propName.replaceFirstChar { it.uppercase() }}Select",
                    LambdaTypeName.get(returnType = UNIT)).defaultValue("{}").build()
            )
        }

        val editableProps = schema.properties.filter { (_, p) -> p.type in listOf("String", "Int", "Long", "Double", "Boolean") }

        editableProps.forEach { (propName, prop) ->
            val kotlinName = propName.toCamelCase()
            val default = when (prop.type) {
                "Boolean" -> "initial?.$kotlinName ?: false"
                else -> "initial?.$kotlinName?.toString() ?: \"\""
            }
            funBuilder.addStatement("var $kotlinName by %M { %M($default) }", remember, mutableStateOf)
        }

        funBuilder.addCode("androidx.compose.foundation.layout.Column {\n")

        schema.properties.forEach { (propName, prop) ->
            val kotlinName = propName.toCamelCase()
            val label = propName.replace("_", " ").replaceFirstChar { it.uppercase() }
            when {
                prop.ref != null -> {
                    // FK field — clickable to navigate to related entity
                    funBuilder.addCode("    // FK: $label → ${prop.ref!!.substringAfterLast("/")}\n")
                    funBuilder.addCode("    androidx.compose.foundation.layout.Row {\n")
                    funBuilder.addCode("        androidx.compose.material3.Text(\"$label: \")\n")
                    funBuilder.addCode("        androidx.compose.material3.TextButton(onClick = { on${propName.replaceFirstChar { it.uppercase() }}Select() }) {\n")
                    funBuilder.addCode("            androidx.compose.material3.Text(initial?.$kotlinName?.toString() ?: \"Select...\")\n")
                    funBuilder.addCode("        }\n")
                    funBuilder.addCode("    }\n")
                }
                prop.type == "Boolean" -> {
                    funBuilder.addCode("    androidx.compose.foundation.layout.Row { ")
                    funBuilder.addCode("androidx.compose.material3.Checkbox(checked = $kotlinName, onCheckedChange = { $kotlinName = it }); ")
                    funBuilder.addCode("androidx.compose.material3.Text(%S) }\n", label)
                }
                prop.type in listOf("String", "Int", "Long", "Double") -> {
                    funBuilder.addCode("    androidx.compose.material3.TextField(value = $kotlinName, onValueChange = { $kotlinName = it }, ")
                    funBuilder.addCode("label = { androidx.compose.material3.Text(%S) })\n", label)
                }
                else -> {
                    funBuilder.addCode("    // $label: ${prop.type} (complex — custom editor needed)\n")
                }
            }
        }

        funBuilder.addCode("    androidx.compose.material3.Button(onClick = { /* build entity and call onSubmit */ }) { ")
        funBuilder.addCode("androidx.compose.material3.Text(\"Save\") }\n")
        funBuilder.addCode("}\n")

        return funBuilder.build()
    }

    private fun generateList(
        name: String,
        schema: ParsedSchema.Object,
        entityType: ClassName,
        modelsPackage: String,
    ): FunSpec {
        // Find display field: name > title > id > first string property
        val displayProp = schema.properties.entries.firstOrNull { it.key == "name" }
            ?: schema.properties.entries.firstOrNull { it.key == "title" }
            ?: schema.properties.entries.firstOrNull { it.key == "id" }
            ?: schema.properties.entries.firstOrNull { it.value.type == "String" }

        val displayField = displayProp?.key?.toCamelCase() ?: "toString()"
        val displayExpr = if (displayProp != null) "item.${displayField}?.toString() ?: \"\"" else "item.toString()"

        // Find subtitle field: status > description > type > second string property
        val subtitleProp = schema.properties.entries.firstOrNull { it.key == "status" }
            ?: schema.properties.entries.firstOrNull { it.key == "description" }
            ?: schema.properties.entries.firstOrNull { it.key == "type" && it.key != displayProp?.key }

        val subtitleExpr = subtitleProp?.let { "item.${it.key.toCamelCase()}?.toString() ?: \"\"" }

        val builder = FunSpec.builder("${name}List")
            .addAnnotation(composable)
            .addParameter("items", LIST.parameterizedBy(entityType))
            .addParameter("onClick", LambdaTypeName.get(parameters = listOf(ParameterSpec.unnamed(entityType)), returnType = UNIT))

        builder.addCode("androidx.compose.foundation.lazy.LazyColumn {\n")
        builder.addCode("    items(items.size) { index ->\n")
        builder.addCode("        val item = items[index]\n")
        builder.addCode("        androidx.compose.material3.ListItem(\n")
        builder.addCode("            headlineContent = { androidx.compose.material3.Text($displayExpr) },\n")
        if (subtitleExpr != null) {
            builder.addCode("            supportingContent = { androidx.compose.material3.Text($subtitleExpr) },\n")
        }
        builder.addCode("            modifier = androidx.compose.ui.Modifier.clickable { onClick(item) }\n")
        builder.addCode("        )\n")
        builder.addCode("    }\n")
        builder.addCode("}\n")

        return builder.build()
    }

    private fun generateDetail(
        name: String,
        schema: ParsedSchema.Object,
        entityType: ClassName,
        modelsPackage: String,
    ): FunSpec {
        val funBuilder = FunSpec.builder("${name}Detail")
            .addAnnotation(composable)
            .addParameter("entity", entityType)

        // FK navigation callbacks
        schema.properties.filter { it.value.ref != null }.forEach { (propName, prop) ->
            funBuilder.addParameter(
                ParameterSpec.builder("on${propName.replaceFirstChar { it.uppercase() }}Click",
                    LambdaTypeName.get(returnType = UNIT)).defaultValue("{}").build()
            )
        }

        funBuilder.addCode("androidx.compose.foundation.layout.Column {\n")

        schema.properties.forEach { (propName, prop) ->
            val kotlinName = propName.toCamelCase()
            val label = propName.replace("_", " ").replaceFirstChar { it.uppercase() }
            when {
                prop.ref != null -> {
                    // FK — clickable link to related entity detail
                    val refName = prop.ref!!.substringAfterLast("/")
                    funBuilder.addCode("    // FK: $label → $refName\n")
                    if (prop.nullable) {
                        funBuilder.addCode("    entity.$kotlinName?.let {\n")
                        funBuilder.addCode("        androidx.compose.material3.TextButton(onClick = { on${propName.replaceFirstChar { it.uppercase() }}Click() }) {\n")
                        funBuilder.addCode("            androidx.compose.material3.Text(\"$label: \$it →\")\n")
                        funBuilder.addCode("        }\n")
                        funBuilder.addCode("    }\n")
                    } else {
                        funBuilder.addCode("    androidx.compose.material3.TextButton(onClick = { on${propName.replaceFirstChar { it.uppercase() }}Click() }) {\n")
                        funBuilder.addCode("        androidx.compose.material3.Text(\"$label: \${entity.$kotlinName} →\")\n")
                        funBuilder.addCode("    }\n")
                    }
                }
                prop.nullable -> funBuilder.addCode("    entity.$kotlinName?.let { androidx.compose.material3.Text(\"$label: \$it\") }\n")
                else -> funBuilder.addCode("    androidx.compose.material3.Text(\"$label: \${entity.$kotlinName}\")\n")
            }
        }

        funBuilder.addCode("}\n")
        return funBuilder.build()
    }

    private fun generateMasterDetail(
        name: String,
        schema: ParsedSchema.Object,
        entityType: ClassName,
        modelsPackage: String,
    ): FunSpec {
        return FunSpec.builder("${name}MasterDetail")
            .addAnnotation(composable)
            .addParameter("items", LIST.parameterizedBy(entityType))
            .addParameter(ParameterSpec.builder("onAdd", LambdaTypeName.get(returnType = UNIT)).defaultValue("{}").build())
            .addCode("""
                |var selected by %M { %M<$name?>(null) }
                |androidx.compose.foundation.layout.Row {
                |    // Master: list (left side / top on mobile)
                |    androidx.compose.foundation.layout.Box(modifier = androidx.compose.ui.Modifier.weight(1f)) {
                |        androidx.compose.foundation.layout.Column {
                |            androidx.compose.material3.TextButton(onClick = onAdd) {
                |                androidx.compose.material3.Text("+ Add $name")
                |            }
                |            ${name}List(items = items, onClick = { selected = it })
                |        }
                |    }
                |    // Detail: selected entity (right side / bottom on mobile)
                |    androidx.compose.foundation.layout.Box(modifier = androidx.compose.ui.Modifier.weight(1f)) {
                |        selected?.let { ${name}Detail(entity = it) }
                |            ?: androidx.compose.material3.Text("Select a $name")
                |    }
                |}
            """.trimMargin(), remember, mutableStateOf)
            .build()
    }
}

private fun String.toCamelCase(): String =
    split("_", "-").mapIndexed { i, part ->
        if (i == 0) part.lowercase() else part.replaceFirstChar { it.uppercase() }
    }.joinToString("")
