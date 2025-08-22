// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.Enum
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

class BetaServerToolUseBlockParam
private constructor(
    private val id: JsonField<String>,
    private val input: JsonValue,
    private val name: JsonField<Name>,
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("input") @ExcludeMissing input: JsonValue = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<Name> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
    ) : this(id, input, name, type, cacheControl, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    @JsonProperty("input") @ExcludeMissing fun _input(): JsonValue = input

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): Name = name.getRequired("name")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("server_tool_use")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        cacheControl.getOptional("cache_control")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<Name> = name

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

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
         * Returns a mutable builder for constructing an instance of [BetaServerToolUseBlockParam].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .input()
         * .name()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaServerToolUseBlockParam]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var input: JsonValue? = null
        private var name: JsonField<Name>? = null
        private var type: JsonValue = JsonValue.from("server_tool_use")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaServerToolUseBlockParam: BetaServerToolUseBlockParam) = apply {
            id = betaServerToolUseBlockParam.id
            input = betaServerToolUseBlockParam.input
            name = betaServerToolUseBlockParam.name
            type = betaServerToolUseBlockParam.type
            cacheControl = betaServerToolUseBlockParam.cacheControl
            additionalProperties = betaServerToolUseBlockParam.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        fun input(input: JsonValue) = apply { this.input = input }

        fun name(name: Name) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [Name] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<Name>) = apply { this.name = name }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("server_tool_use")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed
         * [BetaCacheControlEphemeral] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAlladditional_properties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAlladditional_properties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAlladditional_properties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaServerToolUseBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .input()
         * .name()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaServerToolUseBlockParam =
            BetaServerToolUseBlockParam(
                checkRequired("id", id),
                checkRequired("input", input),
                checkRequired("name", name),
                type,
                cacheControl,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaServerToolUseBlockParam = apply {
        if (validated) {
            return@apply
        }

        id()
        name().validate()
        _type().let {
            if (it != JsonValue.from("server_tool_use")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
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
            (name.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("server_tool_use")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0)

    class Name @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val WEB_SEARCH = of("web_search")

            @JvmField val CODE_EXECUTION = of("code_execution")

            @JvmStatic fun of(value: String) = Name(JsonField.of(value))
        }

        /** An enum containing [Name]'s known values. */
        enum class Known {
            WEB_SEARCH,
            CODE_EXECUTION,
        }

        /**
         * An enum containing [Name]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Name] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            WEB_SEARCH,
            CODE_EXECUTION,
            /** An enum member indicating that [Name] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                WEB_SEARCH -> Value.WEB_SEARCH
                CODE_EXECUTION -> Value.CODE_EXECUTION
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                WEB_SEARCH -> Known.WEB_SEARCH
                CODE_EXECUTION -> Known.CODE_EXECUTION
                else -> throw AnthropicInvalidDataException("Unknown Name: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): Name = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Name && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaServerToolUseBlockParam &&
            id == other.id &&
            input == other.input &&
            name == other.name &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, input, name, type, cacheControl, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaServerToolUseBlockParam{id=$id, input=$input, name=$name, type=$type, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
}
