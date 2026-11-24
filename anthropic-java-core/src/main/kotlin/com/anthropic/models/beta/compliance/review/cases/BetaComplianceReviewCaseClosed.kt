package com.anthropic.models.beta.compliance.review.cases

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

/** Anthropic has stopped waiting for a response. A closed case stays closed. */
class BetaComplianceReviewCaseClosed
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val reason: JsonField<BetaComplianceReviewCaseCloseReason>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("reason")
        @ExcludeMissing
        reason: JsonField<BetaComplianceReviewCaseCloseReason> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(reason, type, mutableMapOf())

    /**
     * Why the case closed. It doesn't change afterwards.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun reason(): BetaComplianceReviewCaseCloseReason = reason.getRequired("reason")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("closed")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [reason].
     *
     * Unlike [reason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("reason")
    @ExcludeMissing
    fun _reason(): JsonField<BetaComplianceReviewCaseCloseReason> = reason

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
         * [BetaComplianceReviewCaseClosed].
         *
         * The following fields are required:
         * ```java
         * .reason()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaComplianceReviewCaseClosed] with the required
         * [reason] set to the given value.
         */
        @JvmStatic
        fun of(reason: BetaComplianceReviewCaseCloseReason) = builder().reason(reason).build()
    }

    /** A builder for [BetaComplianceReviewCaseClosed]. */
    class Builder internal constructor() {

        private var reason: JsonField<BetaComplianceReviewCaseCloseReason>? = null
        private var type: JsonValue = JsonValue.from("closed")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComplianceReviewCaseClosed: BetaComplianceReviewCaseClosed) = apply {
            reason = betaComplianceReviewCaseClosed.reason
            type = betaComplianceReviewCaseClosed.type
            additionalProperties =
                betaComplianceReviewCaseClosed.additionalProperties.toMutableMap()
        }

        /** Why the case closed. It doesn't change afterwards. */
        fun reason(reason: BetaComplianceReviewCaseCloseReason) = reason(JsonField.of(reason))

        /**
         * Sets [Builder.reason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.reason] with a well-typed
         * [BetaComplianceReviewCaseCloseReason] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun reason(reason: JsonField<BetaComplianceReviewCaseCloseReason>) = apply {
            this.reason = reason
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("closed")
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
         * Returns an immutable instance of [BetaComplianceReviewCaseClosed].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .reason()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaComplianceReviewCaseClosed =
            BetaComplianceReviewCaseClosed(
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
    fun validate(): BetaComplianceReviewCaseClosed = apply {
        if (validated) {
            return@apply
        }

        reason().validate()
        _type().let {
            if (it != JsonValue.from("closed")) {
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
        (reason.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("closed")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewCaseClosed &&
            reason == other.reason &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(reason, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaComplianceReviewCaseClosed{reason=$reason, type=$type, additionalProperties=$additionalProperties}"
}
