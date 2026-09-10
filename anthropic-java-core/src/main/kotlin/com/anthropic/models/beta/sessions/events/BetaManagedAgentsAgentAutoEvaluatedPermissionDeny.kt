package com.anthropic.models.beta.sessions.events

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

/**
 * The server judged the invocation high-risk; it does not execute and a synthetic error tool result
 * is appended.
 */
class BetaManagedAgentsAgentAutoEvaluatedPermissionDeny
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val reasonCode: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("reason_code")
        @ExcludeMissing
        reasonCode: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(reasonCode, type, mutableMapOf())

    /**
     * The judgement's grounds in registry-bound terms. Open registry; currently "high_risk" (judged
     * high-risk; the call does not run). Clients must tolerate values outside this set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun reasonCode(): String = reasonCode.getRequired("reason_code")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("deny")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [reasonCode].
     *
     * Unlike [reasonCode], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("reason_code") @ExcludeMissing fun _reasonCode(): JsonField<String> = reasonCode

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
         * [BetaManagedAgentsAgentAutoEvaluatedPermissionDeny].
         *
         * The following fields are required:
         * ```java
         * .reasonCode()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsAgentAutoEvaluatedPermissionDeny] with
         * the required [reasonCode] set to the given value.
         */
        @JvmStatic fun of(reasonCode: String) = builder().reasonCode(reasonCode).build()
    }

    /** A builder for [BetaManagedAgentsAgentAutoEvaluatedPermissionDeny]. */
    class Builder internal constructor() {

        private var reasonCode: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("deny")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentAutoEvaluatedPermissionDeny:
                BetaManagedAgentsAgentAutoEvaluatedPermissionDeny
        ) = apply {
            reasonCode = betaManagedAgentsAgentAutoEvaluatedPermissionDeny.reasonCode
            type = betaManagedAgentsAgentAutoEvaluatedPermissionDeny.type
            additionalProperties =
                betaManagedAgentsAgentAutoEvaluatedPermissionDeny.additionalProperties
                    .toMutableMap()
        }

        /**
         * The judgement's grounds in registry-bound terms. Open registry; currently "high_risk"
         * (judged high-risk; the call does not run). Clients must tolerate values outside this set.
         */
        fun reasonCode(reasonCode: String) = reasonCode(JsonField.of(reasonCode))

        /**
         * Sets [Builder.reasonCode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.reasonCode] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun reasonCode(reasonCode: JsonField<String>) = apply { this.reasonCode = reasonCode }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("deny")
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
         * Returns an immutable instance of [BetaManagedAgentsAgentAutoEvaluatedPermissionDeny].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .reasonCode()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentAutoEvaluatedPermissionDeny =
            BetaManagedAgentsAgentAutoEvaluatedPermissionDeny(
                checkRequired("reasonCode", reasonCode),
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
    fun validate(): BetaManagedAgentsAgentAutoEvaluatedPermissionDeny = apply {
        if (validated) {
            return@apply
        }

        reasonCode()
        _type().let {
            if (it != JsonValue.from("deny")) {
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
        (if (reasonCode.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("deny")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentAutoEvaluatedPermissionDeny &&
            reasonCode == other.reasonCode &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(reasonCode, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentAutoEvaluatedPermissionDeny{reasonCode=$reasonCode, type=$type, additionalProperties=$additionalProperties}"
}
