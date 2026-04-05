package kmp.apigen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File

/**
 * Generates GraphQL schema (.graphql) + Kotlin resolver classes from OpenAPI.
 *
 * Schemas → GraphQL types/unions/enums
 * Paths → Query (GET) / Mutation (POST/PUT/DELETE/PATCH)
 * Resolvers are open classes — add logic by overriding.
 */
class GraphqlFullEmitter : ProtocolEmitter {
    override val protocol = "graphql"

    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val graphqlDir = File(outputDir, "graphql")
        graphqlDir.mkdirs()

        // 1. Generate .graphql schema
        val schema = buildString {
            spec.schemas.forEach { (name, schema) ->
                when (schema) {
                    is ParsedSchema.Object -> {
                        appendLine("type $name {")
                        schema.properties.forEach { (propName, prop) ->
                            appendLine("  ${propName}: ${graphqlType(prop.type, !prop.nullable)}")
                        }
                        appendLine("}")
                        appendLine()
                    }
                    is ParsedSchema.Sealed -> {
                        appendLine("union $name = ${schema.variants.joinToString(" | ") { it.name }}")
                        appendLine()
                    }
                    is ParsedSchema.Enum -> {
                        appendLine("enum $name {")
                        schema.values.forEach { appendLine("  ${it.uppercase().replace("-", "_")}") }
                        appendLine("}")
                        appendLine()
                    }
                    is ParsedSchema.Alias -> {}
                }
            }

            // Query from GET paths
            val queries = spec.paths.filter { it.value.method == "GET" }
            val mutations = spec.paths.filter { it.value.method != "GET" }

            if (queries.isNotEmpty()) {
                appendLine("type Query {")
                queries.forEach { (_, path) ->
                    val returnType = path.responseRef ?: "String"
                    appendLine("  ${path.operationId}(id: ID): $returnType")
                }
                appendLine("}")
                appendLine()
            }

            if (mutations.isNotEmpty()) {
                appendLine("type Mutation {")
                mutations.forEach { (_, path) ->
                    val inputType = path.requestBodyRef ?: ""
                    val returnType = path.responseRef ?: "Boolean"
                    val args = if (inputType.isNotEmpty()) "(input: ${inputType}Input!)" else ""
                    appendLine("  ${path.operationId}$args: $returnType")
                }
                appendLine("}")
                appendLine()
            }

            // Subscription from streaming paths
            val streaming = spec.paths.filter { it.value.streaming }
            if (streaming.isNotEmpty()) {
                appendLine("type Subscription {")
                streaming.forEach { (_, path) ->
                    val returnType = path.responseRef ?: "String"
                    appendLine("  ${path.operationId}: $returnType")
                }
                appendLine("}")
            }
        }
        File(graphqlDir, "schema.graphql").writeText(schema)

        // 2. Generate Kotlin resolver classes (open — add logic by overriding)
        val modelsPackage = "$pkg.models"
        val resolverPackage = "$pkg.graphql"
        val patchEvent = ClassName("kotlinx.kmp.util.core.component", "PatchEvent")

        val queries = spec.paths.filter { it.value.method == "GET" }
        val mutations = spec.paths.filter { it.value.method != "GET" }

        // QueryResolver
        if (queries.isNotEmpty()) {
            val queryBuilder = TypeSpec.classBuilder("QueryResolver")
                .addModifiers(KModifier.OPEN)

            queries.forEach { (_, path) ->
                val returnType = path.responseRef?.let { ClassName(modelsPackage, it) } ?: ANY
                queryBuilder.addFunction(
                    FunSpec.builder(path.operationId)
                        .addModifiers(KModifier.OPEN, KModifier.SUSPEND)
                        .addParameter("id", String::class.asTypeName().copy(nullable = true))
                        .returns(returnType.copy(nullable = true))
                        .addStatement("TODO(%S)", "Implement ${path.operationId}")
                        .build()
                )
            }

            FileSpec.builder(resolverPackage, "QueryResolver")
                .addType(queryBuilder.build())
                .build()
                .writeTo(outputDir)
        }

        // MutationResolver
        if (mutations.isNotEmpty()) {
            val mutationBuilder = TypeSpec.classBuilder("MutationResolver")
                .addModifiers(KModifier.OPEN)

            mutations.forEach { (_, path) ->
                val returnType = path.responseRef?.let {
                    patchEvent.parameterizedBy(ClassName(modelsPackage, it))
                } ?: ANY
                val inputType = path.requestBodyRef?.let { ClassName(modelsPackage, it) }

                val funBuilder = FunSpec.builder(path.operationId)
                    .addModifiers(KModifier.OPEN, KModifier.SUSPEND)
                    .returns(returnType)

                inputType?.let { funBuilder.addParameter("input", it) }

                funBuilder.addStatement("TODO(%S)", "Implement ${path.operationId}")
                mutationBuilder.addFunction(funBuilder.build())
            }

            FileSpec.builder(resolverPackage, "MutationResolver")
                .addType(mutationBuilder.build())
                .build()
                .writeTo(outputDir)
        }
    }

    private fun graphqlType(type: String, required: Boolean): String {
        val base = when (type) {
            "String" -> "String"
            "Int" -> "Int"
            "Long" -> "Int"
            "Double" -> "Float"
            "Boolean" -> "Boolean"
            else -> if (type.startsWith("List<")) "[${graphqlType(type.removePrefix("List<").removeSuffix(">"), false)}]"
                    else type
        }
        return if (required) "$base!" else base
    }
}
