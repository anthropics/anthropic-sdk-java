package com.anthropic.models.beta.organization.externalstorage

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

/** A Google Cloud Storage bucket. */
class BetaGcpExternalStorageConfigParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val bucketName: JsonField<String>,
    private val projectNumber: JsonField<String>,
    private val region: JsonField<String>,
    private val type: JsonValue,
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
        @JsonProperty("region") @ExcludeMissing region: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(bucketName, projectNumber, region, type, mutableMapOf())

    /**
     * The bucket's name, without `gs://`: 3 to 63 lower-case letters, digits and hyphens, starting
     * and ending with a letter or digit. It can't start with `goog` or contain `google`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun bucketName(): String = bucketName.getRequired("bucket_name")

    /**
     * Project number, not project ID, of the Google Cloud project that holds the bucket.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun projectNumber(): String = projectNumber.getRequired("project_number")

    /**
     * The bucket's Google Cloud region, such as `us-east5`: one of the single regions that the
     * setup guide lists. Dual-regions and multi-regions aren't supported.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun region(): String = region.getRequired("region")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("gcp")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

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
         * [BetaGcpExternalStorageConfigParam].
         *
         * The following fields are required:
         * ```java
         * .bucketName()
         * .projectNumber()
         * .region()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGcpExternalStorageConfigParam]. */
    class Builder internal constructor() {

        private var bucketName: JsonField<String>? = null
        private var projectNumber: JsonField<String>? = null
        private var region: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("gcp")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaGcpExternalStorageConfigParam: BetaGcpExternalStorageConfigParam) =
            apply {
                bucketName = betaGcpExternalStorageConfigParam.bucketName
                projectNumber = betaGcpExternalStorageConfigParam.projectNumber
                region = betaGcpExternalStorageConfigParam.region
                type = betaGcpExternalStorageConfigParam.type
                additionalProperties =
                    betaGcpExternalStorageConfigParam.additionalProperties.toMutableMap()
            }

        /**
         * The bucket's name, without `gs://`: 3 to 63 lower-case letters, digits and hyphens,
         * starting and ending with a letter or digit. It can't start with `goog` or contain
         * `google`.
         */
        fun bucketName(bucketName: String) = bucketName(JsonField.of(bucketName))

        /**
         * Sets [Builder.bucketName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.bucketName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun bucketName(bucketName: JsonField<String>) = apply { this.bucketName = bucketName }

        /** Project number, not project ID, of the Google Cloud project that holds the bucket. */
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

        /**
         * The bucket's Google Cloud region, such as `us-east5`: one of the single regions that the
         * setup guide lists. Dual-regions and multi-regions aren't supported.
         */
        fun region(region: String) = region(JsonField.of(region))

        /**
         * Sets [Builder.region] to an arbitrary JSON value.
         *
         * You should usually call [Builder.region] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun region(region: JsonField<String>) = apply { this.region = region }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("gcp")
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
         * Returns an immutable instance of [BetaGcpExternalStorageConfigParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .bucketName()
         * .projectNumber()
         * .region()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGcpExternalStorageConfigParam =
            BetaGcpExternalStorageConfigParam(
                checkRequired("bucketName", bucketName),
                checkRequired("projectNumber", projectNumber),
                checkRequired("region", region),
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
    fun validate(): BetaGcpExternalStorageConfigParam = apply {
        if (validated) {
            return@apply
        }

        bucketName()
        projectNumber()
        region()
        _type().let {
            if (it != JsonValue.from("gcp")) {
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
        (if (bucketName.asKnown().isPresent) 1 else 0) +
            (if (projectNumber.asKnown().isPresent) 1 else 0) +
            (if (region.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("gcp")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGcpExternalStorageConfigParam &&
            bucketName == other.bucketName &&
            projectNumber == other.projectNumber &&
            region == other.region &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(bucketName, projectNumber, region, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGcpExternalStorageConfigParam{bucketName=$bucketName, projectNumber=$projectNumber, region=$region, type=$type, additionalProperties=$additionalProperties}"
}
