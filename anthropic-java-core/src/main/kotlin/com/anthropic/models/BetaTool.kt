// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects
import java.util.Optional

@NoAutoDetect
class BetaTool
@JsonCreator
private constructor(
    @JsonProperty("input_schema")
    @ExcludeMissing
    private val inputSchema: JsonField<InputSchema> = JsonMissing.of(),
    @JsonProperty("name") @ExcludeMissing private val name: JsonField<String> = JsonMissing.of(),
    @JsonProperty("cache_control")
    @ExcludeMissing
    private val cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
    @JsonProperty("description")
    @ExcludeMissing
    private val description: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /**
     * [JSON schema](https://json-schema.org/) for this tool's input.
     *
     * This defines the shape of the `input` that your tool accepts and that the model will produce.
     */
    fun inputSchema(): InputSchema = inputSchema.getRequired("input_schema")

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     */
    fun name(): String = name.getRequired("name")

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    /**
     * Description of what this tool does.
     *
     * Tool descriptions should be as detailed as possible. The more information that the model has
     * about what the tool is and how to use it, the better it will perform. You can use natural
     * language descriptions to reinforce important aspects of the tool input JSON schema.
     */
    fun description(): Optional<String> =
        Optional.ofNullable(description.getNullable("description"))

    fun type(): Optional<Type> = Optional.ofNullable(type.getNullable("type"))

    /**
     * [JSON schema](https://json-schema.org/) for this tool's input.
     *
     * This defines the shape of the `input` that your tool accepts and that the model will produce.
     */
    @JsonProperty("input_schema")
    @ExcludeMissing
    fun _inputSchema(): JsonField<InputSchema> = inputSchema

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

    /**
     * Description of what this tool does.
     *
     * Tool descriptions should be as detailed as possible. The more information that the model has
     * about what the tool is and how to use it, the better it will perform. You can use natural
     * language descriptions to reinforce important aspects of the tool input JSON schema.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaTool = apply {
        if (!validated) {
            inputSchema().validate()
            name()
            cacheControl().map { it.validate() }
            description()
            type()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var inputSchema: JsonField<InputSchema>? = null
        private var name: JsonField<String>? = null
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var description: JsonField<String> = JsonMissing.of()
        private var type: JsonField<Type> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaTool: BetaTool) = apply {
            inputSchema = betaTool.inputSchema
            name = betaTool.name
            cacheControl = betaTool.cacheControl
            description = betaTool.description
            type = betaTool.type
            additionalProperties = betaTool.additionalProperties.toMutableMap()
        }

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
        fun inputSchema(inputSchema: JsonField<InputSchema>) = apply {
            this.inputSchema = inputSchema
        }

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
        fun name(name: JsonField<String>) = apply { this.name = name }

        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.orElse(null))

        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
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
        fun description(description: JsonField<String>) = apply { this.description = description }

        fun type(type: Type?) = type(JsonField.ofNullable(type))

        fun type(type: Optional<Type>) = type(type.orElse(null))

        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

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

        fun build(): BetaTool =
            BetaTool(
                checkNotNull(inputSchema) { "`inputSchema` is required but was not set" },
                checkNotNull(name) { "`name` is required but was not set" },
                cacheControl,
                description,
                type,
                additionalProperties.toImmutable(),
            )
    }

    /**
     * [JSON schema](https://json-schema.org/) for this tool's input.
     *
     * This defines the shape of the `input` that your tool accepts and that the model will produce.
     */
    @NoAutoDetect
    class InputSchema
    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("properties")
        @ExcludeMissing
        private val properties: JsonValue = JsonMissing.of(),
        @JsonAnySetter
        private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
    ) {

        fun type(): Type = type.getRequired("type")

        @JsonProperty("properties") @ExcludeMissing fun _properties(): JsonValue = properties

        @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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

            private var type: JsonField<Type>? = null
            private var properties: JsonValue = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(inputSchema: InputSchema) = apply {
                type = inputSchema.type
                properties = inputSchema.properties
                additionalProperties = inputSchema.additionalProperties.toMutableMap()
            }

            fun type(type: Type) = type(JsonField.of(type))

            fun type(type: JsonField<Type>) = apply { this.type = type }

            fun properties(properties: JsonValue) = apply { this.properties = properties }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

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
                    checkNotNull(type) { "`type` is required but was not set" },
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

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val CUSTOM = of("custom")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            CUSTOM,
        }

        enum class Value {
            CUSTOM,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                CUSTOM -> Value.CUSTOM
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                CUSTOM -> Known.CUSTOM
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

        return /* spotless:off */ other is BetaTool && inputSchema == other.inputSchema && name == other.name && cacheControl == other.cacheControl && description == other.description && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(inputSchema, name, cacheControl, description, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTool{inputSchema=$inputSchema, name=$name, cacheControl=$cacheControl, description=$description, type=$type, additionalProperties=$additionalProperties}"
}
