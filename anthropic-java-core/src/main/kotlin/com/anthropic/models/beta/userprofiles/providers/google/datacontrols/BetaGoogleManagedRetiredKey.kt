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

/** A key that a region has retired. Keep the key usable until `expires_at`. */
class BetaGoogleManagedRetiredKey
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val expiresAt: JsonField<OffsetDateTime>,
    private val externalKeyId: JsonField<String>,
    private val region: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("expires_at")
        @ExcludeMissing
        expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("external_key_id")
        @ExcludeMissing
        externalKeyId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("region") @ExcludeMissing region: JsonField<String> = JsonMissing.of(),
    ) : this(expiresAt, externalKeyId, region, mutableMapOf())

    /**
     * When this entry is removed, in RFC 3339 format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun expiresAt(): OffsetDateTime = expiresAt.getRequired("expires_at")

    /**
     * The ID of the retired External Key (`ekey_...`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun externalKeyId(): String = externalKeyId.getRequired("external_key_id")

    /**
     * The code of the region that retired the key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun region(): String = region.getRequired("region")

    /**
     * Returns the raw JSON value of [expiresAt].
     *
     * Unlike [expiresAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expires_at")
    @ExcludeMissing
    fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

    /**
     * Returns the raw JSON value of [externalKeyId].
     *
     * Unlike [externalKeyId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("external_key_id")
    @ExcludeMissing
    fun _externalKeyId(): JsonField<String> = externalKeyId

    /**
     * Returns the raw JSON value of [region].
     *
     * Unlike [region], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("region") @ExcludeMissing fun _region(): JsonField<String> = region

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
         * Returns a mutable builder for constructing an instance of [BetaGoogleManagedRetiredKey].
         *
         * The following fields are required:
         * ```java
         * .expiresAt()
         * .externalKeyId()
         * .region()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedRetiredKey]. */
    class Builder internal constructor() {

        private var expiresAt: JsonField<OffsetDateTime>? = null
        private var externalKeyId: JsonField<String>? = null
        private var region: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaGoogleManagedRetiredKey: BetaGoogleManagedRetiredKey) = apply {
            expiresAt = betaGoogleManagedRetiredKey.expiresAt
            externalKeyId = betaGoogleManagedRetiredKey.externalKeyId
            region = betaGoogleManagedRetiredKey.region
            additionalProperties = betaGoogleManagedRetiredKey.additionalProperties.toMutableMap()
        }

        /** When this entry is removed, in RFC 3339 format. */
        fun expiresAt(expiresAt: OffsetDateTime) = expiresAt(JsonField.of(expiresAt))

        /**
         * Sets [Builder.expiresAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.expiresAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply { this.expiresAt = expiresAt }

        /** The ID of the retired External Key (`ekey_...`). */
        fun externalKeyId(externalKeyId: String) = externalKeyId(JsonField.of(externalKeyId))

        /**
         * Sets [Builder.externalKeyId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.externalKeyId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun externalKeyId(externalKeyId: JsonField<String>) = apply {
            this.externalKeyId = externalKeyId
        }

        /** The code of the region that retired the key. */
        fun region(region: String) = region(JsonField.of(region))

        /**
         * Sets [Builder.region] to an arbitrary JSON value.
         *
         * You should usually call [Builder.region] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun region(region: JsonField<String>) = apply { this.region = region }

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
         * Returns an immutable instance of [BetaGoogleManagedRetiredKey].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .expiresAt()
         * .externalKeyId()
         * .region()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedRetiredKey =
            BetaGoogleManagedRetiredKey(
                checkRequired("expiresAt", expiresAt),
                checkRequired("externalKeyId", externalKeyId),
                checkRequired("region", region),
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
    fun validate(): BetaGoogleManagedRetiredKey = apply {
        if (validated) {
            return@apply
        }

        expiresAt()
        externalKeyId()
        region()
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
        (if (expiresAt.asKnown().isPresent) 1 else 0) +
            (if (externalKeyId.asKnown().isPresent) 1 else 0) +
            (if (region.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedRetiredKey &&
            expiresAt == other.expiresAt &&
            externalKeyId == other.externalKeyId &&
            region == other.region &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(expiresAt, externalKeyId, region, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedRetiredKey{expiresAt=$expiresAt, externalKeyId=$externalKeyId, region=$region, additionalProperties=$additionalProperties}"
}
