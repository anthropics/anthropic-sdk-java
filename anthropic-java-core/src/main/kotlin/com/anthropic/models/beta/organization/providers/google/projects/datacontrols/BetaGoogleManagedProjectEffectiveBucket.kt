package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

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

/** A Cloud Storage bucket, named by the project that holds it and by its own name. */
class BetaGoogleManagedProjectEffectiveBucket
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val bucketName: JsonField<String>,
    private val projectNumber: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("bucket_name")
        @ExcludeMissing
        bucketName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("project_number")
        @ExcludeMissing
        projectNumber: JsonField<String> = JsonMissing.of(),
    ) : this(bucketName, projectNumber, mutableMapOf())

    /**
     * The bucket's Cloud Storage name.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun bucketName(): String = bucketName.getRequired("bucket_name")

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
         * [BetaGoogleManagedProjectEffectiveBucket].
         *
         * The following fields are required:
         * ```java
         * .bucketName()
         * .projectNumber()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedProjectEffectiveBucket]. */
    class Builder internal constructor() {

        private var bucketName: JsonField<String>? = null
        private var projectNumber: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaGoogleManagedProjectEffectiveBucket: BetaGoogleManagedProjectEffectiveBucket
        ) = apply {
            bucketName = betaGoogleManagedProjectEffectiveBucket.bucketName
            projectNumber = betaGoogleManagedProjectEffectiveBucket.projectNumber
            additionalProperties =
                betaGoogleManagedProjectEffectiveBucket.additionalProperties.toMutableMap()
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
         * Returns an immutable instance of [BetaGoogleManagedProjectEffectiveBucket].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .bucketName()
         * .projectNumber()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedProjectEffectiveBucket =
            BetaGoogleManagedProjectEffectiveBucket(
                checkRequired("bucketName", bucketName),
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
    fun validate(): BetaGoogleManagedProjectEffectiveBucket = apply {
        if (validated) {
            return@apply
        }

        bucketName()
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
            (if (projectNumber.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedProjectEffectiveBucket &&
            bucketName == other.bucketName &&
            projectNumber == other.projectNumber &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(bucketName, projectNumber, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedProjectEffectiveBucket{bucketName=$bucketName, projectNumber=$projectNumber, additionalProperties=$additionalProperties}"
}
