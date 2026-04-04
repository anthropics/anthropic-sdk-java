package kmp.apigen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File

/**
 * Generates Compose Multiplatform UI from OpenAPI schemas.
 *
 * Each object schema → Form, List, Detail @Composable functions.
 * Properties → form fields (TextField, Checkbox, Dropdown).
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
            if (schema.properties.size < 2) return@forEach // skip trivial schemas

            val entityType = ClassName(modelsPackage, name)
            val fileSpec = FileSpec.builder(uiPackage, "${name}UI")

            // Generate Form
            fileSpec.addFunction(generateForm(name, schema, entityType))

            // Generate List
            fileSpec.addFunction(generateList(name, entityType))

            // Generate Detail
            fileSpec.addFunction(generateDetail(name, schema, entityType))

            fileSpec.build().writeTo(outputDir)
        }
    }

    private fun generateForm(
        name: String,
        schema: ParsedSchema.Object,
        entityType: ClassName,
    ): FunSpec {
        val funBuilder = FunSpec.builder("${name}Form")
            .addAnnotation(composable)
            .addParameter(
                ParameterSpec.builder("initial", entityType.copy(nullable = true))
                    .defaultValue("null")
                    .build()
            )
            .addParameter(
                ParameterSpec.builder("onSubmit", LambdaTypeName.get(parameters = listOf(ParameterSpec.unnamed(entityType)), returnType = UNIT))
                    .build()
            )

        // State variables for each editable property
        val editableProps = schema.properties.filter { (_, prop) ->
            prop.type in listOf("String", "Int", "Long", "Double", "Boolean")
        }

        editableProps.forEach { (propName, prop) ->
            val kotlinName = propName.toCamelCase()
            val default = when (prop.type) {
                "String" -> "initial?.$kotlinName ?: \"\""
                "Int", "Long" -> "initial?.$kotlinName?.toString() ?: \"\""
                "Double" -> "initial?.$kotlinName?.toString() ?: \"\""
                "Boolean" -> "initial?.$kotlinName ?: false"
                else -> "\"\""
            }
            if (prop.type == "Boolean") {
                funBuilder.addStatement("var $kotlinName by %M { %M($default) }", remember, mutableStateOf)
            } else {
                funBuilder.addStatement("var $kotlinName by %M { %M($default) }", remember, mutableStateOf)
            }
        }

        // Column with fields
        funBuilder.addCode("androidx.compose.foundation.layout.Column {\n")

        editableProps.forEach { (propName, prop) ->
            val kotlinName = propName.toCamelCase()
            val label = propName.replace("_", " ").replaceFirstChar { it.uppercase() }
            when (prop.type) {
                "Boolean" -> {
                    funBuilder.addCode("    androidx.compose.foundation.layout.Row { ")
                    funBuilder.addCode("androidx.compose.material3.Checkbox(checked = $kotlinName, onCheckedChange = { $kotlinName = it }); ")
                    funBuilder.addCode("androidx.compose.material3.Text(%S) }\n", label)
                }
                else -> {
                    funBuilder.addCode("    androidx.compose.material3.TextField(value = $kotlinName, onValueChange = { $kotlinName = it }, ")
                    funBuilder.addCode("label = { androidx.compose.material3.Text(%S) })\n", label)
                }
            }
        }

        // Submit button
        funBuilder.addCode("    androidx.compose.material3.Button(onClick = { /* build entity from state and call onSubmit */ }) { ")
        funBuilder.addCode("androidx.compose.material3.Text(\"Save\") }\n")
        funBuilder.addCode("}\n")

        return funBuilder.build()
    }

    private fun generateList(name: String, entityType: ClassName): FunSpec {
        return FunSpec.builder("${name}List")
            .addAnnotation(composable)
            .addParameter("items", LIST.parameterizedBy(entityType))
            .addParameter(
                ParameterSpec.builder("onClick", LambdaTypeName.get(parameters = listOf(ParameterSpec.unnamed(entityType)), returnType = UNIT))
                    .build()
            )
            .addCode("""
                |androidx.compose.foundation.lazy.LazyColumn {
                |    items(items.size) { index ->
                |        val item = items[index]
                |        androidx.compose.material3.ListItem(
                |            headlineContent = { androidx.compose.material3.Text(item.toString()) },
                |            modifier = androidx.compose.ui.Modifier.clickable { onClick(item) }
                |        )
                |    }
                |}
            """.trimMargin())
            .build()
    }

    private fun generateDetail(
        name: String,
        schema: ParsedSchema.Object,
        entityType: ClassName,
    ): FunSpec {
        val funBuilder = FunSpec.builder("${name}Detail")
            .addAnnotation(composable)
            .addParameter("entity", entityType)

        funBuilder.addCode("androidx.compose.foundation.layout.Column {\n")

        schema.properties.forEach { (propName, prop) ->
            val kotlinName = propName.toCamelCase()
            val label = propName.replace("_", " ").replaceFirstChar { it.uppercase() }
            when {
                prop.nullable -> funBuilder.addCode("    entity.$kotlinName?.let { androidx.compose.material3.Text(\"$label: \$it\") }\n")
                else -> funBuilder.addCode("    androidx.compose.material3.Text(\"$label: \${entity.$kotlinName}\")\n")
            }
        }

        funBuilder.addCode("}\n")
        return funBuilder.build()
    }
}

private fun String.toCamelCase(): String =
    split("_", "-").mapIndexed { i, part ->
        if (i == 0) part.lowercase() else part.replaceFirstChar { it.uppercase() }
    }.joinToString("")
