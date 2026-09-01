// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

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

class BetaAwsExternalKeyConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val kmsArn: JsonField<String>,
    private val type: JsonValue,
    private val region: JsonField<String>,
    private val roleArn: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("kms_arn") @ExcludeMissing kmsArn: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("region") @ExcludeMissing region: JsonField<String> = JsonMissing.of(),
        @JsonProperty("role_arn") @ExcludeMissing roleArn: JsonField<String> = JsonMissing.of(),
    ) : this(kmsArn, type, region, roleArn, mutableMapOf())

    /**
     * Full ARN of the AWS KMS key. On Claude Platform on AWS the key must be a single-Region key in
     * your organization's own AWS account; cross-account keys, multi-Region keys, and alias ARNs
     * are rejected.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun kmsArn(): String = kmsArn.getRequired("kms_arn")

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
     * AWS region. Derived from `kms_arn` if omitted.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun region(): Optional<String> = region.getOptional("region")

    /**
     * IAM role ARN. Deprecated — Anthropic reaches the KMS key through its own intermediate role
     * (or, on Claude Platform on AWS, with credentials AWS issues for the Workspace); this field is
     * ignored.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    @Deprecated("deprecated") fun roleArn(): Optional<String> = roleArn.getOptional("role_arn")

    /**
     * Returns the raw JSON value of [kmsArn].
     *
     * Unlike [kmsArn], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("kms_arn") @ExcludeMissing fun _kmsArn(): JsonField<String> = kmsArn

    /**
     * Returns the raw JSON value of [region].
     *
     * Unlike [region], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("region") @ExcludeMissing fun _region(): JsonField<String> = region

    /**
     * Returns the raw JSON value of [roleArn].
     *
     * Unlike [roleArn], this method doesn't throw if the JSON field has an unexpected type.
     */
    @Deprecated("deprecated")
    @JsonProperty("role_arn")
    @ExcludeMissing
    fun _roleArn(): JsonField<String> = roleArn

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
         * Returns a mutable builder for constructing an instance of [BetaAwsExternalKeyConfig].
         *
         * The following fields are required:
         * ```java
         * .kmsArn()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaAwsExternalKeyConfig] with the required [kmsArn]
         * set to the given value.
         */
        @JvmStatic fun of(kmsArn: String) = builder().kmsArn(kmsArn).build()
    }

    /** A builder for [BetaAwsExternalKeyConfig]. */
    class Builder internal constructor() {

        private var kmsArn: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("aws")
        private var region: JsonField<String> = JsonMissing.of()
        private var roleArn: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAwsExternalKeyConfig: BetaAwsExternalKeyConfig) = apply {
            kmsArn = betaAwsExternalKeyConfig.kmsArn
            type = betaAwsExternalKeyConfig.type
            region = betaAwsExternalKeyConfig.region
            roleArn = betaAwsExternalKeyConfig.roleArn
            additionalProperties = betaAwsExternalKeyConfig.additionalProperties.toMutableMap()
        }

        /**
         * Full ARN of the AWS KMS key. On Claude Platform on AWS the key must be a single-Region
         * key in your organization's own AWS account; cross-account keys, multi-Region keys, and
         * alias ARNs are rejected.
         */
        fun kmsArn(kmsArn: String) = kmsArn(JsonField.of(kmsArn))

        /**
         * Sets [Builder.kmsArn] to an arbitrary JSON value.
         *
         * You should usually call [Builder.kmsArn] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun kmsArn(kmsArn: JsonField<String>) = apply { this.kmsArn = kmsArn }

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

        /** AWS region. Derived from `kms_arn` if omitted. */
        fun region(region: String?) = region(JsonField.ofNullable(region))

        /** Alias for calling [Builder.region] with `region.orElse(null)`. */
        fun region(region: Optional<String>) = region(region.getOrNull())

        /**
         * Sets [Builder.region] to an arbitrary JSON value.
         *
         * You should usually call [Builder.region] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun region(region: JsonField<String>) = apply { this.region = region }

        /**
         * IAM role ARN. Deprecated — Anthropic reaches the KMS key through its own intermediate
         * role (or, on Claude Platform on AWS, with credentials AWS issues for the Workspace); this
         * field is ignored.
         */
        @Deprecated("deprecated")
        fun roleArn(roleArn: String?) = roleArn(JsonField.ofNullable(roleArn))

        /** Alias for calling [Builder.roleArn] with `roleArn.orElse(null)`. */
        @Deprecated("deprecated")
        fun roleArn(roleArn: Optional<String>) = roleArn(roleArn.getOrNull())

        /**
         * Sets [Builder.roleArn] to an arbitrary JSON value.
         *
         * You should usually call [Builder.roleArn] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        @Deprecated("deprecated")
        fun roleArn(roleArn: JsonField<String>) = apply { this.roleArn = roleArn }

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
         * Returns an immutable instance of [BetaAwsExternalKeyConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .kmsArn()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAwsExternalKeyConfig =
            BetaAwsExternalKeyConfig(
                checkRequired("kmsArn", kmsArn),
                type,
                region,
                roleArn,
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
    fun validate(): BetaAwsExternalKeyConfig = apply {
        if (validated) {
            return@apply
        }

        kmsArn()
        _type().let {
            if (it != JsonValue.from("aws")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        region()
        roleArn()
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
        (if (kmsArn.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("aws")) 1 else 0 } +
            (if (region.asKnown().isPresent) 1 else 0) +
            (if (roleArn.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAwsExternalKeyConfig &&
            kmsArn == other.kmsArn &&
            type == other.type &&
            region == other.region &&
            roleArn == other.roleArn &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(kmsArn, type, region, roleArn, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAwsExternalKeyConfig{kmsArn=$kmsArn, type=$type, region=$region, roleArn=$roleArn, additionalProperties=$additionalProperties}"
}
