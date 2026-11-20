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

/** An Amazon S3 bucket. */
class BetaAwsExternalStorageConfigParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val accountId: JsonField<String>,
    private val bucketName: JsonField<String>,
    private val region: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("account_id") @ExcludeMissing accountId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("bucket_name")
        @ExcludeMissing
        bucketName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("region") @ExcludeMissing region: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(accountId, bucketName, region, type, mutableMapOf())

    /**
     * 12-digit ID of the AWS account that owns the bucket.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun accountId(): String = accountId.getRequired("account_id")

    /**
     * The bucket's name, without `s3://`: 3 to 63 lower-case letters, digits and hyphens, starting
     * and ending with a letter or digit.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun bucketName(): String = bucketName.getRequired("bucket_name")

    /**
     * The bucket's AWS region. Activating the storage fails if Anthropic can't use buckets in that
     * region.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun region(): String = region.getRequired("region")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("aws")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [accountId].
     *
     * Unlike [accountId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("account_id") @ExcludeMissing fun _accountId(): JsonField<String> = accountId

    /**
     * Returns the raw JSON value of [bucketName].
     *
     * Unlike [bucketName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("bucket_name") @ExcludeMissing fun _bucketName(): JsonField<String> = bucketName

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
         * [BetaAwsExternalStorageConfigParam].
         *
         * The following fields are required:
         * ```java
         * .accountId()
         * .bucketName()
         * .region()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAwsExternalStorageConfigParam]. */
    class Builder internal constructor() {

        private var accountId: JsonField<String>? = null
        private var bucketName: JsonField<String>? = null
        private var region: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("aws")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAwsExternalStorageConfigParam: BetaAwsExternalStorageConfigParam) =
            apply {
                accountId = betaAwsExternalStorageConfigParam.accountId
                bucketName = betaAwsExternalStorageConfigParam.bucketName
                region = betaAwsExternalStorageConfigParam.region
                type = betaAwsExternalStorageConfigParam.type
                additionalProperties =
                    betaAwsExternalStorageConfigParam.additionalProperties.toMutableMap()
            }

        /** 12-digit ID of the AWS account that owns the bucket. */
        fun accountId(accountId: String) = accountId(JsonField.of(accountId))

        /**
         * Sets [Builder.accountId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.accountId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun accountId(accountId: JsonField<String>) = apply { this.accountId = accountId }

        /**
         * The bucket's name, without `s3://`: 3 to 63 lower-case letters, digits and hyphens,
         * starting and ending with a letter or digit.
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

        /**
         * The bucket's AWS region. Activating the storage fails if Anthropic can't use buckets in
         * that region.
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
         * JsonValue.from("aws")
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
         * Returns an immutable instance of [BetaAwsExternalStorageConfigParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .accountId()
         * .bucketName()
         * .region()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAwsExternalStorageConfigParam =
            BetaAwsExternalStorageConfigParam(
                checkRequired("accountId", accountId),
                checkRequired("bucketName", bucketName),
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
    fun validate(): BetaAwsExternalStorageConfigParam = apply {
        if (validated) {
            return@apply
        }

        accountId()
        bucketName()
        region()
        _type().let {
            if (it != JsonValue.from("aws")) {
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
        (if (accountId.asKnown().isPresent) 1 else 0) +
            (if (bucketName.asKnown().isPresent) 1 else 0) +
            (if (region.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("aws")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAwsExternalStorageConfigParam &&
            accountId == other.accountId &&
            bucketName == other.bucketName &&
            region == other.region &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(accountId, bucketName, region, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAwsExternalStorageConfigParam{accountId=$accountId, bucketName=$bucketName, region=$region, type=$type, additionalProperties=$additionalProperties}"
}
