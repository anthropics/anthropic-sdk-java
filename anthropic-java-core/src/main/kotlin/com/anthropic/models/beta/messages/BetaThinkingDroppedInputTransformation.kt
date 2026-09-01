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
import kotlin.jvm.optionals.getOrNull

class BetaThinkingDroppedInputTransformation
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val path: JsonField<String>,
    private val reason: JsonField<Reason>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("path") @ExcludeMissing path: JsonField<String> = JsonMissing.of(),
        @JsonProperty("reason") @ExcludeMissing reason: JsonField<Reason> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(path, reason, type, mutableMapOf())

    /**
     * Where the removed block was in your request, as `messages.{i}.content.{j}`: `i` indexes the
     * `messages` array you sent and `j` that message's `content` array — the same form error
     * messages use.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun path(): String = path.getRequired("path")

    /**
     * Which binding check removed the block: `model_binding_mismatch` — it was created by a model
     * whose reasoning the requested model may not read; `prefix_binding_mismatch` — the
     * conversation before it differs from the conversation it was created in (the rest of that
     * turn's consecutive thinking blocks are removed with it, each with this reason);
     * `organization_binding_mismatch` — it was created under a different organization (an Anthropic
     * organization, AWS account or Google Cloud project) and this organization is not one of its
     * additional organizations; `end_user_binding_mismatch` — it was created for a different end
     * user, or was removed by the consumer-organization binding. A block that would fail several
     * checks reports one reason, in this order of precedence: `organization_binding_mismatch`,
     * `end_user_binding_mismatch`, `model_binding_mismatch`, `prefix_binding_mismatch`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun reason(): Reason = reason.getRequired("reason")

    /**
     * Always `thinking_dropped` for this entry type.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("thinking_dropped")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [path].
     *
     * Unlike [path], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("path") @ExcludeMissing fun _path(): JsonField<String> = path

    /**
     * Returns the raw JSON value of [reason].
     *
     * Unlike [reason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("reason") @ExcludeMissing fun _reason(): JsonField<Reason> = reason

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
         * [BetaThinkingDroppedInputTransformation].
         *
         * The following fields are required:
         * ```java
         * .path()
         * .reason()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaThinkingDroppedInputTransformation]. */
    class Builder internal constructor() {

        private var path: JsonField<String>? = null
        private var reason: JsonField<Reason>? = null
        private var type: JsonValue = JsonValue.from("thinking_dropped")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaThinkingDroppedInputTransformation: BetaThinkingDroppedInputTransformation
        ) = apply {
            path = betaThinkingDroppedInputTransformation.path
            reason = betaThinkingDroppedInputTransformation.reason
            type = betaThinkingDroppedInputTransformation.type
            additionalProperties =
                betaThinkingDroppedInputTransformation.additionalProperties.toMutableMap()
        }

        /**
         * Where the removed block was in your request, as `messages.{i}.content.{j}`: `i` indexes
         * the `messages` array you sent and `j` that message's `content` array — the same form
         * error messages use.
         */
        fun path(path: String) = path(JsonField.of(path))

        /**
         * Sets [Builder.path] to an arbitrary JSON value.
         *
         * You should usually call [Builder.path] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun path(path: JsonField<String>) = apply { this.path = path }

        /**
         * Which binding check removed the block: `model_binding_mismatch` — it was created by a
         * model whose reasoning the requested model may not read; `prefix_binding_mismatch` — the
         * conversation before it differs from the conversation it was created in (the rest of that
         * turn's consecutive thinking blocks are removed with it, each with this reason);
         * `organization_binding_mismatch` — it was created under a different organization (an
         * Anthropic organization, AWS account or Google Cloud project) and this organization is not
         * one of its additional organizations; `end_user_binding_mismatch` — it was created for a
         * different end user, or was removed by the consumer-organization binding. A block that
         * would fail several checks reports one reason, in this order of precedence:
         * `organization_binding_mismatch`, `end_user_binding_mismatch`, `model_binding_mismatch`,
         * `prefix_binding_mismatch`.
         */
        fun reason(reason: Reason) = reason(JsonField.of(reason))

        /**
         * Sets [Builder.reason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.reason] with a well-typed [Reason] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun reason(reason: JsonField<Reason>) = apply { this.reason = reason }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("thinking_dropped")
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
         * Returns an immutable instance of [BetaThinkingDroppedInputTransformation].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .path()
         * .reason()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaThinkingDroppedInputTransformation =
            BetaThinkingDroppedInputTransformation(
                checkRequired("path", path),
                checkRequired("reason", reason),
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
    fun validate(): BetaThinkingDroppedInputTransformation = apply {
        if (validated) {
            return@apply
        }

        path()
        reason().validate()
        _type().let {
            if (it != JsonValue.from("thinking_dropped")) {
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
        (if (path.asKnown().isPresent) 1 else 0) +
            (reason.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("thinking_dropped")) 1 else 0 }

    /**
     * Which binding check removed the block: `model_binding_mismatch` — it was created by a model
     * whose reasoning the requested model may not read; `prefix_binding_mismatch` — the
     * conversation before it differs from the conversation it was created in (the rest of that
     * turn's consecutive thinking blocks are removed with it, each with this reason);
     * `organization_binding_mismatch` — it was created under a different organization (an Anthropic
     * organization, AWS account or Google Cloud project) and this organization is not one of its
     * additional organizations; `end_user_binding_mismatch` — it was created for a different end
     * user, or was removed by the consumer-organization binding. A block that would fail several
     * checks reports one reason, in this order of precedence: `organization_binding_mismatch`,
     * `end_user_binding_mismatch`, `model_binding_mismatch`, `prefix_binding_mismatch`.
     */
    class Reason @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val MODEL_BINDING_MISMATCH = of("model_binding_mismatch")

            @JvmField val PREFIX_BINDING_MISMATCH = of("prefix_binding_mismatch")

            @JvmField val ORGANIZATION_BINDING_MISMATCH = of("organization_binding_mismatch")

            @JvmField val END_USER_BINDING_MISMATCH = of("end_user_binding_mismatch")

            @JvmStatic fun of(value: String) = Reason(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Reason =
                value.asString().getOrNull()?.let { of(it) } ?: Reason(value)
        }

        /** An enum containing [Reason]'s known values. */
        enum class Known {
            MODEL_BINDING_MISMATCH,
            PREFIX_BINDING_MISMATCH,
            ORGANIZATION_BINDING_MISMATCH,
            END_USER_BINDING_MISMATCH,
        }

        /**
         * An enum containing [Reason]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Reason] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            MODEL_BINDING_MISMATCH,
            PREFIX_BINDING_MISMATCH,
            ORGANIZATION_BINDING_MISMATCH,
            END_USER_BINDING_MISMATCH,
            /** An enum member indicating that [Reason] was instantiated with an unknown value. */
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
                MODEL_BINDING_MISMATCH -> Value.MODEL_BINDING_MISMATCH
                PREFIX_BINDING_MISMATCH -> Value.PREFIX_BINDING_MISMATCH
                ORGANIZATION_BINDING_MISMATCH -> Value.ORGANIZATION_BINDING_MISMATCH
                END_USER_BINDING_MISMATCH -> Value.END_USER_BINDING_MISMATCH
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
                MODEL_BINDING_MISMATCH -> Known.MODEL_BINDING_MISMATCH
                PREFIX_BINDING_MISMATCH -> Known.PREFIX_BINDING_MISMATCH
                ORGANIZATION_BINDING_MISMATCH -> Known.ORGANIZATION_BINDING_MISMATCH
                END_USER_BINDING_MISMATCH -> Known.END_USER_BINDING_MISMATCH
                else -> throw AnthropicInvalidDataException("Unknown Reason: $value")
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

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Reason = apply {
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

            return other is Reason && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaThinkingDroppedInputTransformation &&
            path == other.path &&
            reason == other.reason &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(path, reason, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaThinkingDroppedInputTransformation{path=$path, reason=$reason, type=$type, additionalProperties=$additionalProperties}"
}
