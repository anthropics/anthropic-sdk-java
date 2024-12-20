// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.Objects
import java.util.Optional

@JsonDeserialize(builder = Tool.Builder::class)
@NoAutoDetect
class Tool
private constructor(
    private val description: JsonField<String>,
    private val name: JsonField<String>,
    private val inputSchema: JsonField<InputSchema>,
    private val cacheControl: JsonField<CacheControlEphemeral>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    /**
     * Description of what this tool does.
     *
     * Tool descriptions should be as detailed as possible. The more information that the model has
     * about what the tool is and how to use it, the better it will perform. You can use natural
     * language descriptions to reinforce important aspects of the tool input JSON schema.
     */
    fun description(): Optional<String> =
        Optional.ofNullable(description.getNullable("description"))

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     */
    fun name(): String = name.getRequired("name")

    /**
     * [JSON schema](https://json-schema.org/) for this tool's input.
     *
     * This defines the shape of the `input` that your tool accepts and that the model will produce.
     */
    fun inputSchema(): InputSchema = inputSchema.getRequired("input_schema")

    fun cacheControl(): Optional<CacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    /**
     * Description of what this tool does.
     *
     * Tool descriptions should be as detailed as possible. The more information that the model has
     * about what the tool is and how to use it, the better it will perform. You can use natural
     * language descriptions to reinforce important aspects of the tool input JSON schema.
     */
    @JsonProperty("description") @ExcludeMissing fun _description() = description

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     */
    @JsonProperty("name") @ExcludeMissing fun _name() = name

    /**
     * [JSON schema](https://json-schema.org/) for this tool's input.
     *
     * This defines the shape of the `input` that your tool accepts and that the model will produce.
     */
    @JsonProperty("input_schema") @ExcludeMissing fun _inputSchema() = inputSchema

    @JsonProperty("cache_control") @ExcludeMissing fun _cacheControl() = cacheControl

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): Tool = apply {
        if (!validated) {
            description()
            name()
            inputSchema().validate()
            cacheControl().map { it.validate() }
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var description: JsonField<String> = JsonMissing.of()
        private var name: JsonField<String> = JsonMissing.of()
        private var inputSchema: JsonField<InputSchema> = JsonMissing.of()
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(tool: Tool) = apply {
            description = tool.description
            name = tool.name
            inputSchema = tool.inputSchema
            cacheControl = tool.cacheControl
            additionalProperties = tool.additionalProperties.toMutableMap()
        }

        /**
         * Description of what this tool does.
         *
         * Tool descriptions should be as detailed as possible. The more information that the model
         * has about what the tool is and how to use it, the better it will perform. You can use
         * natural language descriptions to reinforce important aspects of the tool input JSON
         * schema.
         */
        fun description(description: String) = description(JsonField.of(description))

        /**
         * Description of what this tool does.
         *
         * Tool descriptions should be as detailed as possible. The more information that the model
         * has about what the tool is and how to use it, the better it will perform. You can use
         * natural language descriptions to reinforce important aspects of the tool input JSON
         * schema.
         */
        @JsonProperty("description")
        @ExcludeMissing
        fun description(description: JsonField<String>) = apply { this.description = description }

        /**
         * Name of the tool.
         *
         * This is how the tool will be called by the model and in tool_use blocks.
         */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Name of the tool.
         *
         * This is how the tool will be called by the model and in tool_use blocks.
         */
        @JsonProperty("name")
        @ExcludeMissing
        fun name(name: JsonField<String>) = apply { this.name = name }

        /**
         * [JSON schema](https://json-schema.org/) for this tool's input.
         *
         * This defines the shape of the `input` that your tool accepts and that the model will
         * produce.
         */
        fun inputSchema(inputSchema: InputSchema) = inputSchema(JsonField.of(inputSchema))

        /**
         * [JSON schema](https://json-schema.org/) for this tool's input.
         *
         * This defines the shape of the `input` that your tool accepts and that the model will
         * produce.
         */
        @JsonProperty("input_schema")
        @ExcludeMissing
        fun inputSchema(inputSchema: JsonField<InputSchema>) = apply {
            this.inputSchema = inputSchema
        }

        fun cacheControl(cacheControl: CacheControlEphemeral) =
            cacheControl(JsonField.of(cacheControl))

        @JsonProperty("cache_control")
        @ExcludeMissing
        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        @JsonAnySetter
        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        fun build(): Tool =
            Tool(
                description,
                name,
                inputSchema,
                cacheControl,
                additionalProperties.toImmutable(),
            )
    }

    /**
     * [JSON schema](https://json-schema.org/) for this tool's input.
     *
     * This defines the shape of the `input` that your tool accepts and that the model will produce.
     */
    @JsonDeserialize(builder = InputSchema.Builder::class)
    @NoAutoDetect
    class InputSchema
    private constructor(
        private val type: JsonField<Type>,
        private val properties: JsonValue,
        private val additionalProperties: Map<String, JsonValue>,
    ) {

        fun type(): Type = type.getRequired("type")

        @JsonProperty("type") @ExcludeMissing fun _type() = type

        @JsonProperty("properties") @ExcludeMissing fun _properties() = properties

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        private var validated: Boolean = false

        fun validate(): InputSchema = apply {
            if (!validated) {
                type()
                validated = true
            }
        }

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder {

            private var type: JsonField<Type> = JsonMissing.of()
            private var properties: JsonValue = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(inputSchema: InputSchema) = apply {
                type = inputSchema.type
                properties = inputSchema.properties
                additionalProperties = inputSchema.additionalProperties.toMutableMap()
            }

            fun type(type: Type) = type(JsonField.of(type))

            @JsonProperty("type")
            @ExcludeMissing
            fun type(type: JsonField<Type>) = apply { this.type = type }

            @JsonProperty("properties")
            @ExcludeMissing
            fun properties(properties: JsonValue) = apply { this.properties = properties }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            @JsonAnySetter
            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            fun build(): InputSchema =
                InputSchema(
                    type,
                    properties,
                    additionalProperties.toImmutable(),
                )
        }

        class Type
        @JsonCreator
        private constructor(
            private val value: JsonField<String>,
        ) : Enum {

            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val OBJECT = of("object")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))
            }

            enum class Known {
                OBJECT,
            }

            enum class Value {
                OBJECT,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    OBJECT -> Value.OBJECT
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    OBJECT -> Known.OBJECT
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            fun asString(): String = _value().asStringOrThrow()

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return /* spotless:off */ other is Type && value == other.value /* spotless:on */
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is InputSchema && type == other.type && properties == other.properties && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(type, properties, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "InputSchema{type=$type, properties=$properties, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is Tool && description == other.description && name == other.name && inputSchema == other.inputSchema && cacheControl == other.cacheControl && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(description, name, inputSchema, cacheControl, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Tool{description=$description, name=$name, inputSchema=$inputSchema, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
}
