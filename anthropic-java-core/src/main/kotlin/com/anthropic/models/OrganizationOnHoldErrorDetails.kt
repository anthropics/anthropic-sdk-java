package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Anthropic has disabled the organization for trust and safety reasons. An organization disabled
 * for any other reason, such as billing, does not get this code. `appeal_url`, when present, is
 * where the organization can appeal.
 */
class OrganizationOnHoldErrorDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val errorCode: JsonValue,
    private val appealUrl: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code") @ExcludeMissing errorCode: JsonValue = JsonMissing.of(),
        @JsonProperty("appeal_url") @ExcludeMissing appealUrl: JsonField<String> = JsonMissing.of(),
    ) : this(errorCode, appealUrl, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("organization_on_hold")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("error_code") @ExcludeMissing fun _errorCode(): JsonValue = errorCode

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun appealUrl(): Optional<String> = appealUrl.getOptional("appeal_url")

    /**
     * Returns the raw JSON value of [appealUrl].
     *
     * Unlike [appealUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("appeal_url") @ExcludeMissing fun _appealUrl(): JsonField<String> = appealUrl

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
         * [OrganizationOnHoldErrorDetails].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [OrganizationOnHoldErrorDetails]. */
    class Builder internal constructor() {

        private var errorCode: JsonValue = JsonValue.from("organization_on_hold")
        private var appealUrl: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(organizationOnHoldErrorDetails: OrganizationOnHoldErrorDetails) = apply {
            errorCode = organizationOnHoldErrorDetails.errorCode
            appealUrl = organizationOnHoldErrorDetails.appealUrl
            additionalProperties =
                organizationOnHoldErrorDetails.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("organization_on_hold")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun errorCode(errorCode: JsonValue) = apply { this.errorCode = errorCode }

        fun appealUrl(appealUrl: String?) = appealUrl(JsonField.ofNullable(appealUrl))

        /** Alias for calling [Builder.appealUrl] with `appealUrl.orElse(null)`. */
        fun appealUrl(appealUrl: Optional<String>) = appealUrl(appealUrl.getOrNull())

        /**
         * Sets [Builder.appealUrl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.appealUrl] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun appealUrl(appealUrl: JsonField<String>) = apply { this.appealUrl = appealUrl }

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
         * Returns an immutable instance of [OrganizationOnHoldErrorDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): OrganizationOnHoldErrorDetails =
            OrganizationOnHoldErrorDetails(
                errorCode,
                appealUrl,
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
    fun validate(): OrganizationOnHoldErrorDetails = apply {
        if (validated) {
            return@apply
        }

        _errorCode().let {
            if (it != JsonValue.from("organization_on_hold")) {
                throw AnthropicInvalidDataException("'errorCode' is invalid, received $it")
            }
        }
        appealUrl()
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
        errorCode.let { if (it == JsonValue.from("organization_on_hold")) 1 else 0 } +
            (if (appealUrl.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is OrganizationOnHoldErrorDetails &&
            errorCode == other.errorCode &&
            appealUrl == other.appealUrl &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(errorCode, appealUrl, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "OrganizationOnHoldErrorDetails{errorCode=$errorCode, appealUrl=$appealUrl, additionalProperties=$additionalProperties}"
}
