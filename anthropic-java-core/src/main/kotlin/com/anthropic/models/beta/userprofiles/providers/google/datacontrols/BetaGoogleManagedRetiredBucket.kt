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

/**
 * A bucket that a region has retired. Until `expires_at`, keep the bucket and Anthropic's access to
 * it as they were when the bucket passed verification.
 */
class BetaGoogleManagedRetiredBucket
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val bucketName: JsonField<String>,
    private val expiresAt: JsonField<OffsetDateTime>,
    private val projectNumber: JsonField<String>,
    private val region: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("bucket_name")
        @ExcludeMissing
        bucketName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("expires_at")
        @ExcludeMissing
        expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("project_number")
        @ExcludeMissing
        projectNumber: JsonField<String> = JsonMissing.of(),
        @JsonProperty("region") @ExcludeMissing region: JsonField<String> = JsonMissing.of(),
    ) : this(bucketName, expiresAt, projectNumber, region, mutableMapOf())

    /**
     * The bucket's Cloud Storage name.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun bucketName(): String = bucketName.getRequired("bucket_name")

    /**
     * When this entry is removed, in RFC 3339 format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun expiresAt(): OffsetDateTime = expiresAt.getRequired("expires_at")

    /**
     * The number of the Google Cloud project that holds the bucket, in decimal.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun projectNumber(): String = projectNumber.getRequired("project_number")

    /**
     * The code of the region that retired the bucket.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun region(): String = region.getRequired("region")

    /**
     * Returns the raw JSON value of [bucketName].
     *
     * Unlike [bucketName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("bucket_name") @ExcludeMissing fun _bucketName(): JsonField<String> = bucketName

    /**
     * Returns the raw JSON value of [expiresAt].
     *
     * Unlike [expiresAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expires_at")
    @ExcludeMissing
    fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

    /**
     * Returns the raw JSON value of [projectNumber].
     *
     * Unlike [projectNumber], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("project_number")
    @ExcludeMissing
    fun _projectNumber(): JsonField<String> = projectNumber

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
         * Returns a mutable builder for constructing an instance of
         * [BetaGoogleManagedRetiredBucket].
         *
         * The following fields are required:
         * ```java
         * .bucketName()
         * .expiresAt()
         * .projectNumber()
         * .region()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedRetiredBucket]. */
    class Builder internal constructor() {

        private var bucketName: JsonField<String>? = null
        private var expiresAt: JsonField<OffsetDateTime>? = null
        private var projectNumber: JsonField<String>? = null
        private var region: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaGoogleManagedRetiredBucket: BetaGoogleManagedRetiredBucket) = apply {
            bucketName = betaGoogleManagedRetiredBucket.bucketName
            expiresAt = betaGoogleManagedRetiredBucket.expiresAt
            projectNumber = betaGoogleManagedRetiredBucket.projectNumber
            region = betaGoogleManagedRetiredBucket.region
            additionalProperties =
                betaGoogleManagedRetiredBucket.additionalProperties.toMutableMap()
        }

        /** The bucket's Cloud Storage name. */
        fun bucketName(bucketName: String) = bucketName(JsonField.of(bucketName))

        /**
         * Sets [Builder.bucketName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.bucketName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun bucketName(bucketName: JsonField<String>) = apply { this.bucketName = bucketName }

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

        /** The number of the Google Cloud project that holds the bucket, in decimal. */
        fun projectNumber(projectNumber: String) = projectNumber(JsonField.of(projectNumber))

        /**
         * Sets [Builder.projectNumber] to an arbitrary JSON value.
         *
         * You should usually call [Builder.projectNumber] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun projectNumber(projectNumber: JsonField<String>) = apply {
            this.projectNumber = projectNumber
        }

        /** The code of the region that retired the bucket. */
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
         * Returns an immutable instance of [BetaGoogleManagedRetiredBucket].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .bucketName()
         * .expiresAt()
         * .projectNumber()
         * .region()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedRetiredBucket =
            BetaGoogleManagedRetiredBucket(
                checkRequired("bucketName", bucketName),
                checkRequired("expiresAt", expiresAt),
                checkRequired("projectNumber", projectNumber),
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
    fun validate(): BetaGoogleManagedRetiredBucket = apply {
        if (validated) {
            return@apply
        }

        bucketName()
        expiresAt()
        projectNumber()
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
        (if (bucketName.asKnown().isPresent) 1 else 0) +
            (if (expiresAt.asKnown().isPresent) 1 else 0) +
            (if (projectNumber.asKnown().isPresent) 1 else 0) +
            (if (region.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedRetiredBucket &&
            bucketName == other.bucketName &&
            expiresAt == other.expiresAt &&
            projectNumber == other.projectNumber &&
            region == other.region &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(bucketName, expiresAt, projectNumber, region, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedRetiredBucket{bucketName=$bucketName, expiresAt=$expiresAt, projectNumber=$projectNumber, region=$region, additionalProperties=$additionalProperties}"
}
