// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class Tool
private constructor(
    private val inputSchema: JsonField<InputSchema>,
    private val name: JsonField<String>,
    private val cacheControl: JsonField<CacheControlEphemeral>,
    private val description: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("input_schema")
        @ExcludeMissing
        inputSchema: JsonField<InputSchema> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
    ) : this(inputSchema, name, cacheControl, description, mutableMapOf())

    /**
     * [JSON schema](https://json-schema.org/draft/2020-12) for this tool's input.
     *
     * This defines the shape of the `input` that your tool accepts and that the model will produce.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun inputSchema(): InputSchema = inputSchema.getRequired("input_schema")

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<CacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    /**
     * Description of what this tool does.
     *
     * Tool descriptions should be as detailed as possible. The more information that the model has
     * about what the tool is and how to use it, the better it will perform. You can use natural
     * language descriptions to reinforce important aspects of the tool input JSON schema.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun description(): Optional<String> =
        Optional.ofNullable(description.getNullable("description"))

    /**
     * Returns the raw JSON value of [inputSchema].
     *
     * Unlike [inputSchema], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input_schema")
    @ExcludeMissing
    fun _inputSchema(): JsonField<InputSchema> = inputSchema

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [Tool].
         *
         * The following fields are required:
         * ```java
         * .inputSchema()
         * .name()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [Tool]. */
    class Builder internal constructor() {

        private var inputSchema: JsonField<InputSchema>? = null
        private var name: JsonField<String>? = null
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var description: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(tool: Tool) = apply {
            inputSchema = tool.inputSchema
            name = tool.name
            cacheControl = tool.cacheControl
            description = tool.description
            additionalProperties = tool.additionalProperties.toMutableMap()
        }

        /**
         * [JSON schema](https://json-schema.org/draft/2020-12) for this tool's input.
         *
         * This defines the shape of the `input` that your tool accepts and that the model will
         * produce.
         */
        fun inputSchema(inputSchema: InputSchema) = inputSchema(JsonField.of(inputSchema))

        /**
         * Sets [Builder.inputSchema] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputSchema] with a well-typed [InputSchema] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
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
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        fun cacheControl(cacheControl: CacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<CacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed [CacheControlEphemeral]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
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
         * Sets [Builder.description] to an arbitrary JSON value.
         *
         * You should usually call [Builder.description] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun description(description: JsonField<String>) = apply { this.description = description }

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

        /**
         * Returns an immutable instance of [Tool].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .inputSchema()
         * .name()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): Tool =
            Tool(
                checkRequired("inputSchema", inputSchema),
                checkRequired("name", name),
                cacheControl,
                description,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): Tool = apply {
        if (validated) {
            return@apply
        }

        inputSchema().validate()
        name()
        cacheControl().ifPresent { it.validate() }
        description()
        validated = true
    }

    /**
     * [JSON schema](https://json-schema.org/draft/2020-12) for this tool's input.
     *
     * This defines the shape of the `input` that your tool accepts and that the model will produce.
     */
    class InputSchema
    private constructor(
        private val type: JsonValue,
        private val properties: JsonValue,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("properties") @ExcludeMissing properties: JsonValue = JsonMissing.of(),
        ) : this(type, properties, mutableMapOf())

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("object")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        @JsonProperty("properties") @ExcludeMissing fun _properties(): JsonValue = properties

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [InputSchema]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [InputSchema]. */
        class Builder internal constructor() {

            private var type: JsonValue = JsonValue.from("object")
            private var properties: JsonValue = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(inputSchema: InputSchema) = apply {
                type = inputSchema.type
                properties = inputSchema.properties
                additionalProperties = inputSchema.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("object")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun type(type: JsonValue) = apply { this.type = type }

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

            /**
             * Returns an immutable instance of [InputSchema].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): InputSchema =
                InputSchema(type, properties, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        fun validate(): InputSchema = apply {
            if (validated) {
                return@apply
            }

            _type().let {
                if (it != JsonValue.from("object")) {
                    throw AnthropicInvalidDataException("'type' is invalid, received $it")
                }
            }
            validated = true
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

        return /* spotless:off */ other is Tool && inputSchema == other.inputSchema && name == other.name && cacheControl == other.cacheControl && description == other.description && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(inputSchema, name, cacheControl, description, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Tool{inputSchema=$inputSchema, name=$name, cacheControl=$cacheControl, description=$description, additionalProperties=$additionalProperties}"
}
