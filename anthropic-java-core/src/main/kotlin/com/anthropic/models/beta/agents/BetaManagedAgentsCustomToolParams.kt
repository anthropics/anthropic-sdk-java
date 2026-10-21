package com.anthropic.models.beta.agents

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
import kotlin.jvm.optionals.getOrNull

/**
 * A custom tool that is executed by the API client rather than the agent. When the agent calls this
 * tool, an `agent.custom_tool_use` event is emitted and the session goes idle, waiting for the
 * client to provide the result via a `user.custom_tool_result` event.
 */
class BetaManagedAgentsCustomToolParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val description: JsonField<String>,
    private val inputSchema: JsonField<BetaManagedAgentsCustomToolInputSchema>,
    private val name: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("input_schema")
        @ExcludeMissing
        inputSchema: JsonField<BetaManagedAgentsCustomToolInputSchema> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(description, inputSchema, name, type, mutableMapOf())

    /**
     * Description of what the tool does, shown to the agent to help it decide when to use the tool.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun description(): String = description.getRequired("description")

    /**
     * JSON Schema defining the expected input parameters for the tool.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun inputSchema(): BetaManagedAgentsCustomToolInputSchema =
        inputSchema.getRequired("input_schema")

    /**
     * Unique name for the tool. 1-128 characters; letters, digits, underscores, and hyphens.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("custom")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    /**
     * Returns the raw JSON value of [inputSchema].
     *
     * Unlike [inputSchema], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input_schema")
    @ExcludeMissing
    fun _inputSchema(): JsonField<BetaManagedAgentsCustomToolInputSchema> = inputSchema

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

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
         * Returns a mutable builder for constructing an instance of
         * [BetaManagedAgentsCustomToolParams].
         *
         * The following fields are required:
         * ```java
         * .description()
         * .inputSchema()
         * .name()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsCustomToolParams]. */
    class Builder internal constructor() {

        private var description: JsonField<String>? = null
        private var inputSchema: JsonField<BetaManagedAgentsCustomToolInputSchema>? = null
        private var name: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("custom")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsCustomToolParams: BetaManagedAgentsCustomToolParams) =
            apply {
                description = betaManagedAgentsCustomToolParams.description
                inputSchema = betaManagedAgentsCustomToolParams.inputSchema
                name = betaManagedAgentsCustomToolParams.name
                type = betaManagedAgentsCustomToolParams.type
                additionalProperties =
                    betaManagedAgentsCustomToolParams.additionalProperties.toMutableMap()
            }

        /**
         * Description of what the tool does, shown to the agent to help it decide when to use the
         * tool.
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

        /** JSON Schema defining the expected input parameters for the tool. */
        fun inputSchema(inputSchema: BetaManagedAgentsCustomToolInputSchema) =
            inputSchema(JsonField.of(inputSchema))

        /**
         * Sets [Builder.inputSchema] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputSchema] with a well-typed
         * [BetaManagedAgentsCustomToolInputSchema] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun inputSchema(inputSchema: JsonField<BetaManagedAgentsCustomToolInputSchema>) = apply {
            this.inputSchema = inputSchema
        }

        /**
         * Unique name for the tool. 1-128 characters; letters, digits, underscores, and hyphens.
         */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("custom")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaManagedAgentsCustomToolParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .description()
         * .inputSchema()
         * .name()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsCustomToolParams =
            BetaManagedAgentsCustomToolParams(
                checkRequired("description", description),
                checkRequired("inputSchema", inputSchema),
                checkRequired("name", name),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaManagedAgentsCustomToolParams = apply {
        if (validated) {
            return@apply
        }

        description()
        inputSchema().validate()
        name()
        _type().let {
            if (it != JsonValue.from("custom")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (if (description.asKnown().isPresent) 1 else 0) +
            (inputSchema.asKnown().getOrNull()?.validity() ?: 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("custom")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsCustomToolParams &&
            description == other.description &&
            inputSchema == other.inputSchema &&
            name == other.name &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(description, inputSchema, name, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsCustomToolParams{description=$description, inputSchema=$inputSchema, name=$name, type=$type, additionalProperties=$additionalProperties}"
}
