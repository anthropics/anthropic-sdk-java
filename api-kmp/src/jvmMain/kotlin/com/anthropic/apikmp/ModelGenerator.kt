package com.anthropic.apikmp

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File

/**
 * Generates @Serializable model classes from parsed OpenAPI schemas.
 *
 * oneOf + discriminator → sealed class with @SerialName variants
 * object → @Serializable data class
 * enum → @Serializable enum class
 */
class ModelGenerator(
    private val basePackage: String,
    private val outputDir: File,
) {
    private val modelsPackage = "$basePackage.models"
    private val serializable = ClassName("kotlinx.serialization", "Serializable")
    private val serialName = ClassName("kotlinx.serialization", "SerialName")

    fun generate(name: String, schema: ParsedSchema) {
        val fileSpec = when (schema) {
            is ParsedSchema.Sealed -> generateSealedClass(name, schema)
            is ParsedSchema.Object -> generateDataClass(name, schema)
            is ParsedSchema.Enum -> generateEnumClass(name, schema)
            is ParsedSchema.Alias -> return // skip aliases
        }
        fileSpec.writeTo(outputDir)
    }

    private fun generateSealedClass(name: String, schema: ParsedSchema.Sealed): FileSpec {
        val sealedClass = TypeSpec.classBuilder(name)
            .addModifiers(KModifier.SEALED)
            .addAnnotation(serializable)

        schema.description?.let {
            sealedClass.addKdoc("%L", it)
        }

        // Add discriminator property if present
        schema.discriminator?.let { disc ->
            sealedClass.addProperty(
                PropertySpec.builder("type", String::class)
                    .addModifiers(KModifier.ABSTRACT)
                    .build()
            )
        }

        // Generate variant subclasses
        schema.variants.forEach { variant ->
            val variantName = variant.name
            val serialNameValue = schema.discriminatorMapping
                .entries.find { it.value.endsWith(variantName) }
                ?.key ?: variantName.toSnakeCase()

            val subclass = TypeSpec.classBuilder(variantName)
                .addModifiers(KModifier.DATA)
                .superclass(ClassName(modelsPackage, name))
                .addAnnotation(serializable)
                .addAnnotation(
                    AnnotationSpec.builder(serialName)
                        .addMember("%S", serialNameValue)
                        .build()
                )
                .primaryConstructor(
                    FunSpec.constructorBuilder()
                        .addParameter("value", ClassName(modelsPackage, variantName))
                        .build()
                )
                .addProperty(
                    PropertySpec.builder("value", ClassName(modelsPackage, variantName))
                        .initializer("value")
                        .build()
                )

            // Override discriminator
            schema.discriminator?.let {
                subclass.addProperty(
                    PropertySpec.builder("type", String::class)
                        .addModifiers(KModifier.OVERRIDE)
                        .getter(FunSpec.getterBuilder().addStatement("return %S", serialNameValue).build())
                        .build()
                )
            }

            sealedClass.addType(subclass.build())
        }

        return FileSpec.builder(modelsPackage, name)
            .addType(sealedClass.build())
            .build()
    }

    private fun generateDataClass(name: String, schema: ParsedSchema.Object): FileSpec {
        val constructor = FunSpec.constructorBuilder()
        val properties = mutableListOf<PropertySpec>()

        schema.properties.forEach { (propName, prop) ->
            val typeName = resolveTypeName(prop.type, prop.nullable)
            val kotlinName = propName.toCamelCase()

            constructor.addParameter(
                ParameterSpec.builder(kotlinName, typeName)
                    .apply {
                        if (prop.nullable && propName !in schema.required) {
                            defaultValue("null")
                        }
                    }
                    .build()
            )

            properties.add(
                PropertySpec.builder(kotlinName, typeName)
                    .initializer(kotlinName)
                    .apply {
                        // Add @SerialName if Kotlin name differs from JSON name
                        if (kotlinName != propName) {
                            addAnnotation(
                                AnnotationSpec.builder(serialName)
                                    .addMember("%S", propName)
                                    .build()
                            )
                        }
                        prop.description?.let { addKdoc("%L", it) }
                    }
                    .build()
            )
        }

        val dataClass = TypeSpec.classBuilder(name)
            .addModifiers(KModifier.DATA)
            .addAnnotation(serializable)
            .primaryConstructor(constructor.build())
            .addProperties(properties)

        schema.description?.let { dataClass.addKdoc("%L", it) }

        return FileSpec.builder(modelsPackage, name)
            .addType(dataClass.build())
            .build()
    }

    private fun generateEnumClass(name: String, schema: ParsedSchema.Enum): FileSpec {
        val enumClass = TypeSpec.enumBuilder(name)
            .addAnnotation(serializable)

        schema.values.forEach { value ->
            val entryName = value.uppercase().replace("-", "_").replace(".", "_")
            enumClass.addEnumConstant(
                entryName,
                TypeSpec.anonymousClassBuilder()
                    .addAnnotation(
                        AnnotationSpec.builder(serialName)
                            .addMember("%S", value)
                            .build()
                    )
                    .build()
            )
        }

        schema.description?.let { enumClass.addKdoc("%L", it) }

        return FileSpec.builder(modelsPackage, name)
            .addType(enumClass.build())
            .build()
    }

    private fun resolveTypeName(type: String, nullable: Boolean): TypeName {
        val base = when (type) {
            "String" -> STRING
            "Int" -> INT
            "Long" -> LONG
            "Double" -> DOUBLE
            "Boolean" -> BOOLEAN
            "Any" -> ANY
            "JsonObject" -> ClassName("kotlinx.serialization.json", "JsonObject")
            else -> {
                if (type.startsWith("List<")) {
                    val inner = type.removePrefix("List<").removeSuffix(">")
                    LIST.parameterizedBy(resolveTypeName(inner, false))
                } else if (type.startsWith("Map<")) {
                    val parts = type.removePrefix("Map<").removeSuffix(">").split(", ", limit = 2)
                    MAP.parameterizedBy(resolveTypeName(parts[0], false), resolveTypeName(parts[1], false))
                } else {
                    ClassName(modelsPackage, type)
                }
            }
        }
        return if (nullable) base.copy(nullable = true) else base
    }
}

// Utility extensions
private fun String.toCamelCase(): String {
    return split("_", "-").mapIndexed { i, part ->
        if (i == 0) part.lowercase() else part.replaceFirstChar { it.uppercase() }
    }.joinToString("")
}

private fun String.toSnakeCase(): String {
    return replace(Regex("([a-z])([A-Z])"), "$1_$2").lowercase()
}
