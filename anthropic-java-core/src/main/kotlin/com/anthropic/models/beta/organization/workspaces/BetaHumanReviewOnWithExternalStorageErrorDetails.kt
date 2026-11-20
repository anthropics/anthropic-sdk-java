package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects

/**
 * The request would leave the Workspace with `inference_data_privacy.safeguards_storage` set to
 * External Storage and `inference_data_privacy.human_review` enabled. Anthropic doesn't offer human
 * review of data in a customer's own storage. Set `human_review` to `disabled`, or
 * `safeguards_storage` to `internal`.
 */
class BetaHumanReviewOnWithExternalStorageErrorDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val errorCode: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code") @ExcludeMissing errorCode: JsonValue = JsonMissing.of()
    ) : this(errorCode, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("human_review_on_with_external_storage")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("error_code") @ExcludeMissing fun _errorCode(): JsonValue = errorCode

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
         * [BetaHumanReviewOnWithExternalStorageErrorDetails].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaHumanReviewOnWithExternalStorageErrorDetails]. */
    class Builder internal constructor() {

        private var errorCode: JsonValue = JsonValue.from("human_review_on_with_external_storage")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaHumanReviewOnWithExternalStorageErrorDetails:
                BetaHumanReviewOnWithExternalStorageErrorDetails
        ) = apply {
            errorCode = betaHumanReviewOnWithExternalStorageErrorDetails.errorCode
            additionalProperties =
                betaHumanReviewOnWithExternalStorageErrorDetails.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("human_review_on_with_external_storage")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun errorCode(errorCode: JsonValue) = apply { this.errorCode = errorCode }

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
         * Returns an immutable instance of [BetaHumanReviewOnWithExternalStorageErrorDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaHumanReviewOnWithExternalStorageErrorDetails =
            BetaHumanReviewOnWithExternalStorageErrorDetails(
                errorCode,
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
    fun validate(): BetaHumanReviewOnWithExternalStorageErrorDetails = apply {
        if (validated) {
            return@apply
        }

        _errorCode().let {
            if (it != JsonValue.from("human_review_on_with_external_storage")) {
                throw AnthropicInvalidDataException("'errorCode' is invalid, received $it")
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
        errorCode.let {
            if (it == JsonValue.from("human_review_on_with_external_storage")) 1 else 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaHumanReviewOnWithExternalStorageErrorDetails &&
            errorCode == other.errorCode &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(errorCode, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaHumanReviewOnWithExternalStorageErrorDetails{errorCode=$errorCode, additionalProperties=$additionalProperties}"
}
