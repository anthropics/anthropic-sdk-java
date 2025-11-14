package com.anthropic.core

import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.messages.BetaJsonOutputFormat
import com.anthropic.models.beta.messages.BetaTool
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.github.victools.jsonschema.generator.Option
import com.github.victools.jsonschema.generator.OptionPreset
import com.github.victools.jsonschema.generator.SchemaGenerator
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder
import com.github.victools.jsonschema.module.jackson.JacksonModule
import com.github.victools.jsonschema.module.swagger2.Swagger2Module

// The SDK `ObjectMappers.jsonMapper()` requires that all fields of classes be marked with
// `@JsonProperty`, which is not desirable in this context, as it impedes usability. Therefore, a
// custom JSON mapper configuration is required.
private val MAPPER =
    JsonMapper.builder()
        .addModule(kotlinModule())
        .addModule(Jdk8Module())
        .addModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .build()

/**
 * Builds an output format using a JSON schema derived from the structure of an arbitrary Java
 * class.
 */
@JvmSynthetic
internal fun outputFormatFromClass(
    outputType: Class<*>,
    localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
): BetaJsonOutputFormat =
    BetaJsonOutputFormat.builder()
        .schema(
            JsonValue.fromJsonNode(
                validateSchema(extractSchema(outputType), outputType, localValidation)
            )
        )
        .build()

/**
 * Validates the given JSON schema with respect to Anthropic's JSON schema restrictions.
 *
 * @param schema The JSON schema to be validated.
 * @param sourceType The class from which the JSON schema was derived. This is only used in error
 *   messages.
 * @param localValidation Set to [JsonSchemaLocalValidation.YES] to perform the validation. Other
 *   values will cause validation to be skipped.
 */
@JvmSynthetic
internal fun validateSchema(
    schema: ObjectNode,
    sourceType: Class<*>,
    localValidation: JsonSchemaLocalValidation,
): ObjectNode {
    if (localValidation == JsonSchemaLocalValidation.YES) {
        val validator = JsonSchemaValidator.create().validate(schema)

        require(validator.isValid()) {
            "Local validation failed for JSON schema derived from $sourceType:\n" +
                validator.errors().joinToString("\n") { " - $it" }
        }
    }
    return schema
}

// "internal" instead of "private" for testing purposes.
internal data class FunctionInfo(
    val name: String,
    val description: String?,
    val schema: ObjectNode,
)

@JvmSynthetic
// "internal" instead of "private" for testing purposes.
internal fun extractFunctionInfo(
    parametersType: Class<*>,
    localValidation: JsonSchemaLocalValidation,
): FunctionInfo {
    val schema = extractSchema(parametersType)

    validateSchema(schema, parametersType, localValidation)

    // The JSON schema generator ignores the `@JsonTypeName` annotation, so it never sets the "name"
    // field at the root of the schema. Respect that annotation here and use it to set the name
    // (outside the schema). Fall back to using the simple name of the class, but converted from
    // "camel case" to "snake case".
    val name =
        parametersType.getAnnotation(JsonTypeName::class.java)?.value
            ?: parametersType.simpleName.toSnakeCase()

    // The JSON schema generator will copy the `@JsonClassDescription` into the schema. If present,
    // remove it from the schema so it can be set on the function definition/tool.
    val descriptionNode: JsonNode? = schema.remove("description")
    val description: String? = descriptionNode?.textValue()

    return FunctionInfo(name, description, schema)
}

/**
 * Converts a camel case string to snake case. All characters are converted to lower-case and
 * underscores are inserted at word boundaries.
 *
 * Word boundaries are identified where the current character is not the first character, is
 * upper-case, and either of these two conditions is true:
 * 1. The previous character was lower-case (e.g., "helloWorld" -> "hello_world").
 * 1. The next character is lower-case, and the previous character was upper-case (e.g., "XMLParser"
 *    -> "xml_parser")
 *
 * If the input is not a camel case string, the output is not defined.
 */
@JvmSynthetic
internal fun String.toSnakeCase(): String {
    val snakeCaseBuilder = StringBuilder()

    this.forEachIndexed { index, char ->
        if (char.isUpperCase() && index > 0) {
            val prevChar = this[index - 1]
            if (
                prevChar.isLowerCase() ||
                    (index < this.length - 1 &&
                        this[index + 1].isLowerCase() &&
                        prevChar.isUpperCase())
            ) {
                snakeCaseBuilder.append('_')
            }
        }
        snakeCaseBuilder.append(char.lowercaseChar())
    }

    return snakeCaseBuilder.toString()
}

/**
 * Creates a Messages API tool defining a function whose input schema (defining its parameters) is
 * derived from the fields of a class.
 */
@JvmSynthetic
internal fun toolFromClass(
    parametersType: Class<*>,
    localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
): BetaTool {
    val functionInfo = extractFunctionInfo(parametersType, localValidation)

    return BetaTool.builder()
        .name(functionInfo.name)
        .apply { functionInfo.description?.let(::description) }
        .inputSchema(JsonValue.fromJsonNode(functionInfo.schema))
        // Setting strict to true ensures tool use responses reliably adhere to the JSON schema.
        .strict(true)
        .build()
}

/**
 * Derives a JSON schema from the structure of an arbitrary Java class.
 *
 * Validation is not performed by this function, as it allows extraction of the schema and
 * validation of the schema to be controlled more easily when unit testing, as no exceptions will be
 * thrown and any recorded validation errors can be inspected at leisure by the tests.
 */
@JvmSynthetic
internal fun extractSchema(type: Class<*>): ObjectNode {
    val configBuilder =
        SchemaGeneratorConfigBuilder(
                com.github.victools.jsonschema.generator.SchemaVersion.DRAFT_2020_12,
                OptionPreset.PLAIN_JSON,
            )
            // Add `"additionalProperties" : false` to all object schemas (see Anthropic).
            .with(Option.FORBIDDEN_ADDITIONAL_PROPERTIES_BY_DEFAULT)
            // Use `JacksonModule` to support the use of Jackson annotations to set property and
            // class names and descriptions and to mark fields with `@JsonIgnore`.
            .with(JacksonModule())
            // Use `Swagger2Module` to support OpenAPI Swagger 2 `@Schema` annotations to set
            // property constraints (e.g., a `"pattern"` constraint for a string property).
            .with(Swagger2Module())

    configBuilder
        .forFields()
        // For Anthropic schemas, _all_ properties _must_ be required. Override the interpretation
        // of the Jackson `required` parameter to the `@JsonProperty` annotation: it will always be
        // assumed to be `true`, even if explicitly `false` and even if there is no `@JsonProperty`
        // annotation present.
        .withRequiredCheck { true }

    return SchemaGenerator(configBuilder.build()).generateSchema(type)
}

/**
 * Creates an instance of a Java class using data from a JSON string. The JSON data should conform
 * to the JSON schema previously extracted from the Java class.
 *
 * @throws AnthropicInvalidDataException If the JSON data cannot be parsed to an instance of the
 *   [outputType] class.
 */
@JvmSynthetic
internal fun <T> outputTypeFromJson(json: String, outputType: Class<T>): T =
    try {
        MAPPER.readValue(json, outputType)
    } catch (e: Exception) {
        // The JSON document is included in the exception message to aid diagnosis of the problem.
        // It is the responsibility of the SDK user to ensure that exceptions that may contain
        // sensitive data are not exposed in logs.
        throw AnthropicInvalidDataException("Error parsing JSON: $json", e)
    }

/**
 * Converts any object into a JSON-formatted string. For `Object` types (other than strings and
 * boxed primitives) a JSON object is created with its fields and values set from the fields of the
 * object.
 */
@JvmSynthetic internal fun toJsonString(obj: Any): String = MAPPER.writeValueAsString(obj)
