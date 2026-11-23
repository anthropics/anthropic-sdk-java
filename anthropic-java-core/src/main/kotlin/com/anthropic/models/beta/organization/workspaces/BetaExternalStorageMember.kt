package com.anthropic.models.beta.organization.workspaces

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

/** The organization's External Storage that `external_storage_id` names. */
class BetaExternalStorageMember
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val externalStorageId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("external_storage_id")
        @ExcludeMissing
        externalStorageId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(externalStorageId, type, mutableMapOf())

    /**
     * The External Storage ID, which starts with `estrg_`. Through Claude Platform on AWS, storage
     * whose `provider_config.type` is `aws` has its bucket ARN as its ID instead:
     * `arn:<partition>:s3:::<bucket_name>`, where `<partition>` is the partition of the bucket's
     * region.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun externalStorageId(): String = externalStorageId.getRequired("external_storage_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("external")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [externalStorageId].
     *
     * Unlike [externalStorageId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("external_storage_id")
    @ExcludeMissing
    fun _externalStorageId(): JsonField<String> = externalStorageId

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
         * Returns a mutable builder for constructing an instance of [BetaExternalStorageMember].
         *
         * The following fields are required:
         * ```java
         * .externalStorageId()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaExternalStorageMember] with the required
         * [externalStorageId] set to the given value.
         */
        @JvmStatic
        fun of(externalStorageId: String) = builder().externalStorageId(externalStorageId).build()
    }

    /** A builder for [BetaExternalStorageMember]. */
    class Builder internal constructor() {

        private var externalStorageId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("external")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaExternalStorageMember: BetaExternalStorageMember) = apply {
            externalStorageId = betaExternalStorageMember.externalStorageId
            type = betaExternalStorageMember.type
            additionalProperties = betaExternalStorageMember.additionalProperties.toMutableMap()
        }

        /**
         * The External Storage ID, which starts with `estrg_`. Through Claude Platform on AWS,
         * storage whose `provider_config.type` is `aws` has its bucket ARN as its ID instead:
         * `arn:<partition>:s3:::<bucket_name>`, where `<partition>` is the partition of the
         * bucket's region.
         */
        fun externalStorageId(externalStorageId: String) =
            externalStorageId(JsonField.of(externalStorageId))

        /**
         * Sets [Builder.externalStorageId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.externalStorageId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun externalStorageId(externalStorageId: JsonField<String>) = apply {
            this.externalStorageId = externalStorageId
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("external")
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
         * Returns an immutable instance of [BetaExternalStorageMember].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .externalStorageId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaExternalStorageMember =
            BetaExternalStorageMember(
                checkRequired("externalStorageId", externalStorageId),
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
    fun validate(): BetaExternalStorageMember = apply {
        if (validated) {
            return@apply
        }

        externalStorageId()
        _type().let {
            if (it != JsonValue.from("external")) {
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
        (if (externalStorageId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("external")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaExternalStorageMember &&
            externalStorageId == other.externalStorageId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(externalStorageId, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaExternalStorageMember{externalStorageId=$externalStorageId, type=$type, additionalProperties=$additionalProperties}"
}
