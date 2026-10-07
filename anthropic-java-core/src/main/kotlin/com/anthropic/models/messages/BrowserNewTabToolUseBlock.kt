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
import kotlin.jvm.optionals.getOrNull

class BrowserNewTabToolUseBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val caller: JsonField<ToolUseCaller>,
    private val input: JsonField<BrowserNewTabInput>,
    private val name: JsonValue,
    private val toolsetName: JsonValue,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("caller") @ExcludeMissing caller: JsonField<ToolUseCaller> = JsonMissing.of(),
        @JsonProperty("input")
        @ExcludeMissing
        input: JsonField<BrowserNewTabInput> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
        @JsonProperty("toolset_name") @ExcludeMissing toolsetName: JsonValue = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, caller, input, name, toolsetName, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Which party invoked the tool call: the model directly, or a server tool on its behalf.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun caller(): ToolUseCaller = caller.getRequired("caller")

    /**
     * Open a new empty tab and return its tab_id.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun input(): BrowserNewTabInput = input.getRequired("input")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("new_tab")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("browser")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("toolset_name") @ExcludeMissing fun _toolsetName(): JsonValue = toolsetName

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("tool_use")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [caller].
     *
     * Unlike [caller], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("caller") @ExcludeMissing fun _caller(): JsonField<ToolUseCaller> = caller

    /**
     * Returns the raw JSON value of [input].
     *
     * Unlike [input], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input") @ExcludeMissing fun _input(): JsonField<BrowserNewTabInput> = input

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
         * Returns a mutable builder for constructing an instance of [BrowserNewTabToolUseBlock].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .caller()
         * .input()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BrowserNewTabToolUseBlock]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var caller: JsonField<ToolUseCaller>? = null
        private var input: JsonField<BrowserNewTabInput>? = null
        private var name: JsonValue = JsonValue.from("new_tab")
        private var toolsetName: JsonValue = JsonValue.from("browser")
        private var type: JsonValue = JsonValue.from("tool_use")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(browserNewTabToolUseBlock: BrowserNewTabToolUseBlock) = apply {
            id = browserNewTabToolUseBlock.id
            caller = browserNewTabToolUseBlock.caller
            input = browserNewTabToolUseBlock.input
            name = browserNewTabToolUseBlock.name
            toolsetName = browserNewTabToolUseBlock.toolsetName
            type = browserNewTabToolUseBlock.type
            additionalProperties = browserNewTabToolUseBlock.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * Which party invoked the tool call: the model directly, or a server tool on its behalf.
         */
        fun caller(caller: ToolUseCaller) = caller(JsonField.of(caller))

        /**
         * Sets [Builder.caller] to an arbitrary JSON value.
         *
         * You should usually call [Builder.caller] with a well-typed [ToolUseCaller] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun caller(caller: JsonField<ToolUseCaller>) = apply { this.caller = caller }

        /** Alias for calling [caller] with `ToolUseCaller.ofDirect(direct)`. */
        fun caller(direct: DirectCaller) = caller(ToolUseCaller.ofDirect(direct))

        /**
         * Alias for calling [caller] with
         * `ToolUseCaller.ofCodeExecution20250825(codeExecution20250825)`.
         */
        fun caller(codeExecution20250825: ServerToolCaller) =
            caller(ToolUseCaller.ofCodeExecution20250825(codeExecution20250825))

        /**
         * Alias for calling [caller] with the following:
         * ```java
         * ServerToolCaller.builder()
         *     .toolId(toolId)
         *     .build()
         * ```
         */
        fun codeExecution20250825Caller(toolId: String) =
            caller(ServerToolCaller.builder().toolId(toolId).build())

        /**
         * Alias for calling [caller] with
         * `ToolUseCaller.ofCodeExecution20260120(codeExecution20260120)`.
         */
        fun caller(codeExecution20260120: ServerToolCaller20260120) =
            caller(ToolUseCaller.ofCodeExecution20260120(codeExecution20260120))

        /**
         * Alias for calling [caller] with the following:
         * ```java
         * ServerToolCaller20260120.builder()
         *     .toolId(toolId)
         *     .build()
         * ```
         */
        fun codeExecution20260120Caller(toolId: String) =
            caller(ServerToolCaller20260120.builder().toolId(toolId).build())

        /** Open a new empty tab and return its tab_id. */
        fun input(input: BrowserNewTabInput) = input(JsonField.of(input))

        /**
         * Sets [Builder.input] to an arbitrary JSON value.
         *
         * You should usually call [Builder.input] with a well-typed [BrowserNewTabInput] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun input(input: JsonField<BrowserNewTabInput>) = apply { this.input = input }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("new_tab")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun name(name: JsonValue) = apply { this.name = name }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("browser")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun toolsetName(toolsetName: JsonValue) = apply { this.toolsetName = toolsetName }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("tool_use")
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
         * Returns an immutable instance of [BrowserNewTabToolUseBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .caller()
         * .input()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BrowserNewTabToolUseBlock =
            BrowserNewTabToolUseBlock(
                checkRequired("id", id),
                checkRequired("caller", caller),
                checkRequired("input", input),
                name,
                toolsetName,
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
    fun validate(): BrowserNewTabToolUseBlock = apply {
        if (validated) {
            return@apply
        }

        id()
        caller().validate()
        input().validate()
        _name().let {
            if (it != JsonValue.from("new_tab")) {
                throw AnthropicInvalidDataException("'name' is invalid, received $it")
            }
        }
        _toolsetName().let {
            if (it != JsonValue.from("browser")) {
                throw AnthropicInvalidDataException("'toolsetName' is invalid, received $it")
            }
        }
        _type().let {
            if (it != JsonValue.from("tool_use")) {
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (caller.asKnown().getOrNull()?.validity() ?: 0) +
            (input.asKnown().getOrNull()?.validity() ?: 0) +
            name.let { if (it == JsonValue.from("new_tab")) 1 else 0 } +
            toolsetName.let { if (it == JsonValue.from("browser")) 1 else 0 } +
            type.let { if (it == JsonValue.from("tool_use")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BrowserNewTabToolUseBlock &&
            id == other.id &&
            caller == other.caller &&
            input == other.input &&
            name == other.name &&
            toolsetName == other.toolsetName &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, caller, input, name, toolsetName, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BrowserNewTabToolUseBlock{id=$id, caller=$caller, input=$input, name=$name, toolsetName=$toolsetName, type=$type, additionalProperties=$additionalProperties}"
}
