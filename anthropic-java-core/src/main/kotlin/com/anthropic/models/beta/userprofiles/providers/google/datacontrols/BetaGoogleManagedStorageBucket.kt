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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The bucket configured for a region. */
class BetaGoogleManagedStorageBucket
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val bucketName: JsonField<String>,
    private val kmsKeyName: JsonField<String>,
    private val location: JsonField<String>,
    private val projectId: JsonField<String>,
    private val projectNumber: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("bucket_name")
        @ExcludeMissing
        bucketName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("kms_key_name")
        @ExcludeMissing
        kmsKeyName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("location") @ExcludeMissing location: JsonField<String> = JsonMissing.of(),
        @JsonProperty("project_id") @ExcludeMissing projectId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("project_number")
        @ExcludeMissing
        projectNumber: JsonField<String> = JsonMissing.of(),
    ) : this(bucketName, kmsKeyName, location, projectId, projectNumber, mutableMapOf())

    /**
     * The bucket's Cloud Storage name.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun bucketName(): String = bucketName.getRequired("bucket_name")

    /**
     * The resource name of the Cloud KMS key that the bucket encrypts objects under by default.
     * `null` until the region has confirmed the bucket, and when the bucket has no default key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun kmsKeyName(): Optional<String> = kmsKeyName.getOptional("kms_key_name")

    /**
     * The bucket's location, as Cloud Storage reports it. `null` until the region has confirmed the
     * bucket.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun location(): Optional<String> = location.getOptional("location")

    /**
     * The ID of the project that holds the bucket. `null` until the region has confirmed the
     * bucket.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun projectId(): Optional<String> = projectId.getOptional("project_id")

    /**
     * The number of the Google Cloud project that holds the bucket, in decimal.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun projectNumber(): String = projectNumber.getRequired("project_number")

    /**
     * Returns the raw JSON value of [bucketName].
     *
     * Unlike [bucketName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("bucket_name") @ExcludeMissing fun _bucketName(): JsonField<String> = bucketName

    /**
     * Returns the raw JSON value of [kmsKeyName].
     *
     * Unlike [kmsKeyName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("kms_key_name") @ExcludeMissing fun _kmsKeyName(): JsonField<String> = kmsKeyName

    /**
     * Returns the raw JSON value of [location].
     *
     * Unlike [location], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("location") @ExcludeMissing fun _location(): JsonField<String> = location

    /**
     * Returns the raw JSON value of [projectId].
     *
     * Unlike [projectId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("project_id") @ExcludeMissing fun _projectId(): JsonField<String> = projectId

    /**
     * Returns the raw JSON value of [projectNumber].
     *
     * Unlike [projectNumber], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("project_number")
    @ExcludeMissing
    fun _projectNumber(): JsonField<String> = projectNumber

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
         * [BetaGoogleManagedStorageBucket].
         *
         * The following fields are required:
         * ```java
         * .bucketName()
         * .kmsKeyName()
         * .location()
         * .projectId()
         * .projectNumber()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedStorageBucket]. */
    class Builder internal constructor() {

        private var bucketName: JsonField<String>? = null
        private var kmsKeyName: JsonField<String>? = null
        private var location: JsonField<String>? = null
        private var projectId: JsonField<String>? = null
        private var projectNumber: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaGoogleManagedStorageBucket: BetaGoogleManagedStorageBucket) = apply {
            bucketName = betaGoogleManagedStorageBucket.bucketName
            kmsKeyName = betaGoogleManagedStorageBucket.kmsKeyName
            location = betaGoogleManagedStorageBucket.location
            projectId = betaGoogleManagedStorageBucket.projectId
            projectNumber = betaGoogleManagedStorageBucket.projectNumber
            additionalProperties =
                betaGoogleManagedStorageBucket.additionalProperties.toMutableMap()
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

        /**
         * The resource name of the Cloud KMS key that the bucket encrypts objects under by default.
         * `null` until the region has confirmed the bucket, and when the bucket has no default key.
         */
        fun kmsKeyName(kmsKeyName: String?) = kmsKeyName(JsonField.ofNullable(kmsKeyName))

        /** Alias for calling [Builder.kmsKeyName] with `kmsKeyName.orElse(null)`. */
        fun kmsKeyName(kmsKeyName: Optional<String>) = kmsKeyName(kmsKeyName.getOrNull())

        /**
         * Sets [Builder.kmsKeyName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.kmsKeyName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun kmsKeyName(kmsKeyName: JsonField<String>) = apply { this.kmsKeyName = kmsKeyName }

        /**
         * The bucket's location, as Cloud Storage reports it. `null` until the region has confirmed
         * the bucket.
         */
        fun location(location: String?) = location(JsonField.ofNullable(location))

        /** Alias for calling [Builder.location] with `location.orElse(null)`. */
        fun location(location: Optional<String>) = location(location.getOrNull())

        /**
         * Sets [Builder.location] to an arbitrary JSON value.
         *
         * You should usually call [Builder.location] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun location(location: JsonField<String>) = apply { this.location = location }

        /**
         * The ID of the project that holds the bucket. `null` until the region has confirmed the
         * bucket.
         */
        fun projectId(projectId: String?) = projectId(JsonField.ofNullable(projectId))

        /** Alias for calling [Builder.projectId] with `projectId.orElse(null)`. */
        fun projectId(projectId: Optional<String>) = projectId(projectId.getOrNull())

        /**
         * Sets [Builder.projectId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.projectId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun projectId(projectId: JsonField<String>) = apply { this.projectId = projectId }

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
         * Returns an immutable instance of [BetaGoogleManagedStorageBucket].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .bucketName()
         * .kmsKeyName()
         * .location()
         * .projectId()
         * .projectNumber()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedStorageBucket =
            BetaGoogleManagedStorageBucket(
                checkRequired("bucketName", bucketName),
                checkRequired("kmsKeyName", kmsKeyName),
                checkRequired("location", location),
                checkRequired("projectId", projectId),
                checkRequired("projectNumber", projectNumber),
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
    fun validate(): BetaGoogleManagedStorageBucket = apply {
        if (validated) {
            return@apply
        }

        bucketName()
        kmsKeyName()
        location()
        projectId()
        projectNumber()
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
            (if (kmsKeyName.asKnown().isPresent) 1 else 0) +
            (if (location.asKnown().isPresent) 1 else 0) +
            (if (projectId.asKnown().isPresent) 1 else 0) +
            (if (projectNumber.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedStorageBucket &&
            bucketName == other.bucketName &&
            kmsKeyName == other.kmsKeyName &&
            location == other.location &&
            projectId == other.projectId &&
            projectNumber == other.projectNumber &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            bucketName,
            kmsKeyName,
            location,
            projectId,
            projectNumber,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedStorageBucket{bucketName=$bucketName, kmsKeyName=$kmsKeyName, location=$location, projectId=$projectId, projectNumber=$projectNumber, additionalProperties=$additionalProperties}"
}
