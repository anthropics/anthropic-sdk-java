package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The configured key passed validation, and the region applies it. */
class BetaGoogleManagedCmekRegionActive
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val effectiveExternalKeyId: JsonField<String>,
    private val error: JsonField<String>,
    private val lastCheckedAt: JsonField<OffsetDateTime>,
    private val type: JsonValue,
    private val validatedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("effective_external_key_id")
        @ExcludeMissing
        effectiveExternalKeyId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("error") @ExcludeMissing error: JsonField<String> = JsonMissing.of(),
        @JsonProperty("last_checked_at")
        @ExcludeMissing
        lastCheckedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("validated_at")
        @ExcludeMissing
        validatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(effectiveExternalKeyId, error, lastCheckedAt, type, validatedAt, mutableMapOf())

    /**
     * The ID of the External Key that the region applies now (`ekey_...`). While a replacement key
     * is `pending` or `failed`, this is the previous key. `null` when the region applies no key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun effectiveExternalKeyId(): Optional<String> =
        effectiveExternalKeyId.getOptional("effective_external_key_id")

    /**
     * Why Google Cloud KMS refused the region's latest use of the key. `null` when the region has
     * no refusal to report. A `null` does not mean that the key works.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun error(): Optional<String> = error.getOptional("error")

    /**
     * When the region last got a successful answer from Google Cloud KMS for the key, in RFC 3339
     * format. `null` when it has none to report.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun lastCheckedAt(): Optional<OffsetDateTime> = lastCheckedAt.getOptional("last_checked_at")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("active")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * When the status last became `active`, in RFC 3339 format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun validatedAt(): OffsetDateTime = validatedAt.getRequired("validated_at")

    /**
     * Returns the raw JSON value of [effectiveExternalKeyId].
     *
     * Unlike [effectiveExternalKeyId], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("effective_external_key_id")
    @ExcludeMissing
    fun _effectiveExternalKeyId(): JsonField<String> = effectiveExternalKeyId

    /**
     * Returns the raw JSON value of [error].
     *
     * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<String> = error

    /**
     * Returns the raw JSON value of [lastCheckedAt].
     *
     * Unlike [lastCheckedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("last_checked_at")
    @ExcludeMissing
    fun _lastCheckedAt(): JsonField<OffsetDateTime> = lastCheckedAt

    /**
     * Returns the raw JSON value of [validatedAt].
     *
     * Unlike [validatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("validated_at")
    @ExcludeMissing
    fun _validatedAt(): JsonField<OffsetDateTime> = validatedAt

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
         * [BetaGoogleManagedCmekRegionActive].
         *
         * The following fields are required:
         * ```java
         * .effectiveExternalKeyId()
         * .error()
         * .lastCheckedAt()
         * .validatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedCmekRegionActive]. */
    class Builder internal constructor() {

        private var effectiveExternalKeyId: JsonField<String>? = null
        private var error: JsonField<String>? = null
        private var lastCheckedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonValue = JsonValue.from("active")
        private var validatedAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaGoogleManagedCmekRegionActive: BetaGoogleManagedCmekRegionActive) =
            apply {
                effectiveExternalKeyId = betaGoogleManagedCmekRegionActive.effectiveExternalKeyId
                error = betaGoogleManagedCmekRegionActive.error
                lastCheckedAt = betaGoogleManagedCmekRegionActive.lastCheckedAt
                type = betaGoogleManagedCmekRegionActive.type
                validatedAt = betaGoogleManagedCmekRegionActive.validatedAt
                additionalProperties =
                    betaGoogleManagedCmekRegionActive.additionalProperties.toMutableMap()
            }

        /**
         * The ID of the External Key that the region applies now (`ekey_...`). While a replacement
         * key is `pending` or `failed`, this is the previous key. `null` when the region applies no
         * key.
         */
        fun effectiveExternalKeyId(effectiveExternalKeyId: String?) =
            effectiveExternalKeyId(JsonField.ofNullable(effectiveExternalKeyId))

        /**
         * Alias for calling [Builder.effectiveExternalKeyId] with
         * `effectiveExternalKeyId.orElse(null)`.
         */
        fun effectiveExternalKeyId(effectiveExternalKeyId: Optional<String>) =
            effectiveExternalKeyId(effectiveExternalKeyId.getOrNull())

        /**
         * Sets [Builder.effectiveExternalKeyId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.effectiveExternalKeyId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun effectiveExternalKeyId(effectiveExternalKeyId: JsonField<String>) = apply {
            this.effectiveExternalKeyId = effectiveExternalKeyId
        }

        /**
         * Why Google Cloud KMS refused the region's latest use of the key. `null` when the region
         * has no refusal to report. A `null` does not mean that the key works.
         */
        fun error(error: String?) = error(JsonField.ofNullable(error))

        /** Alias for calling [Builder.error] with `error.orElse(null)`. */
        fun error(error: Optional<String>) = error(error.getOrNull())

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun error(error: JsonField<String>) = apply { this.error = error }

        /**
         * When the region last got a successful answer from Google Cloud KMS for the key, in RFC
         * 3339 format. `null` when it has none to report.
         */
        fun lastCheckedAt(lastCheckedAt: OffsetDateTime?) =
            lastCheckedAt(JsonField.ofNullable(lastCheckedAt))

        /** Alias for calling [Builder.lastCheckedAt] with `lastCheckedAt.orElse(null)`. */
        fun lastCheckedAt(lastCheckedAt: Optional<OffsetDateTime>) =
            lastCheckedAt(lastCheckedAt.getOrNull())

        /**
         * Sets [Builder.lastCheckedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastCheckedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun lastCheckedAt(lastCheckedAt: JsonField<OffsetDateTime>) = apply {
            this.lastCheckedAt = lastCheckedAt
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("active")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** When the status last became `active`, in RFC 3339 format. */
        fun validatedAt(validatedAt: OffsetDateTime) = validatedAt(JsonField.of(validatedAt))

        /**
         * Sets [Builder.validatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.validatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun validatedAt(validatedAt: JsonField<OffsetDateTime>) = apply {
            this.validatedAt = validatedAt
        }

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
         * Returns an immutable instance of [BetaGoogleManagedCmekRegionActive].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .effectiveExternalKeyId()
         * .error()
         * .lastCheckedAt()
         * .validatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedCmekRegionActive =
            BetaGoogleManagedCmekRegionActive(
                checkRequired("effectiveExternalKeyId", effectiveExternalKeyId),
                checkRequired("error", error),
                checkRequired("lastCheckedAt", lastCheckedAt),
                type,
                checkRequired("validatedAt", validatedAt),
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
    fun validate(): BetaGoogleManagedCmekRegionActive = apply {
        if (validated) {
            return@apply
        }

        effectiveExternalKeyId()
        error()
        lastCheckedAt()
        _type().let {
            if (it != JsonValue.from("active")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validatedAt()
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
        (if (effectiveExternalKeyId.asKnown().isPresent) 1 else 0) +
            (if (error.asKnown().isPresent) 1 else 0) +
            (if (lastCheckedAt.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("active")) 1 else 0 } +
            (if (validatedAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedCmekRegionActive &&
            effectiveExternalKeyId == other.effectiveExternalKeyId &&
            error == other.error &&
            lastCheckedAt == other.lastCheckedAt &&
            type == other.type &&
            validatedAt == other.validatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            effectiveExternalKeyId,
            error,
            lastCheckedAt,
            type,
            validatedAt,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedCmekRegionActive{effectiveExternalKeyId=$effectiveExternalKeyId, error=$error, lastCheckedAt=$lastCheckedAt, type=$type, validatedAt=$validatedAt, additionalProperties=$additionalProperties}"
}
