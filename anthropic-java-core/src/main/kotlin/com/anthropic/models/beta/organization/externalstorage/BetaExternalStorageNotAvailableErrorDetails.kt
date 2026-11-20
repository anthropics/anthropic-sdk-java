package com.anthropic.models.beta.organization.externalstorage

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
 * External Storage isn't available for this Workspace, so only `internal` storage is accepted. The
 * error's `message` says why.
 */
class BetaExternalStorageNotAvailableErrorDetails
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
     * JsonValue.from("external_storage_not_available")
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
         * [BetaExternalStorageNotAvailableErrorDetails].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaExternalStorageNotAvailableErrorDetails]. */
    class Builder internal constructor() {

        private var errorCode: JsonValue = JsonValue.from("external_storage_not_available")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaExternalStorageNotAvailableErrorDetails: BetaExternalStorageNotAvailableErrorDetails
        ) = apply {
            errorCode = betaExternalStorageNotAvailableErrorDetails.errorCode
            additionalProperties =
                betaExternalStorageNotAvailableErrorDetails.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("external_storage_not_available")
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
         * Returns an immutable instance of [BetaExternalStorageNotAvailableErrorDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaExternalStorageNotAvailableErrorDetails =
            BetaExternalStorageNotAvailableErrorDetails(
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
    fun validate(): BetaExternalStorageNotAvailableErrorDetails = apply {
        if (validated) {
            return@apply
        }

        _errorCode().let {
            if (it != JsonValue.from("external_storage_not_available")) {
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
        errorCode.let { if (it == JsonValue.from("external_storage_not_available")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaExternalStorageNotAvailableErrorDetails &&
            errorCode == other.errorCode &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(errorCode, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaExternalStorageNotAvailableErrorDetails{errorCode=$errorCode, additionalProperties=$additionalProperties}"
}
