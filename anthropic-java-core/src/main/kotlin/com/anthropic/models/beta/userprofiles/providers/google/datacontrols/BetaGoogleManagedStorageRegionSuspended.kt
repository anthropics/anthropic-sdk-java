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

/**
 * Anthropic has withdrawn or paused its approval of customer-owned storage for this profile, so the
 * region does not apply the control.
 */
class BetaGoogleManagedStorageRegionSuspended
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val effectiveBucket: JsonField<BetaGoogleManagedBucketReference>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("effective_bucket")
        @ExcludeMissing
        effectiveBucket: JsonField<BetaGoogleManagedBucketReference> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(effectiveBucket, type, mutableMapOf())

    /**
     * The bucket that the region applies now. While a replacement bucket is `pending` or `failed`,
     * this is the previous bucket. `null` when the region applies no bucket.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun effectiveBucket(): Optional<BetaGoogleManagedBucketReference> =
        effectiveBucket.getOptional("effective_bucket")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("suspended")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [effectiveBucket].
     *
     * Unlike [effectiveBucket], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("effective_bucket")
    @ExcludeMissing
    fun _effectiveBucket(): JsonField<BetaGoogleManagedBucketReference> = effectiveBucket

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
         * [BetaGoogleManagedStorageRegionSuspended].
         *
         * The following fields are required:
         * ```java
         * .effectiveBucket()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaGoogleManagedStorageRegionSuspended] with the
         * required [effectiveBucket] set to the given value.
         */
        @JvmStatic
        fun of(effectiveBucket: BetaGoogleManagedBucketReference?) =
            builder().effectiveBucket(effectiveBucket).build()

        /** Alias for calling [of] with `effectiveBucket.orElse(null)`. */
        @JvmStatic
        fun of(effectiveBucket: Optional<BetaGoogleManagedBucketReference>) =
            of(effectiveBucket.getOrNull())
    }

    /** A builder for [BetaGoogleManagedStorageRegionSuspended]. */
    class Builder internal constructor() {

        private var effectiveBucket: JsonField<BetaGoogleManagedBucketReference>? = null
        private var type: JsonValue = JsonValue.from("suspended")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaGoogleManagedStorageRegionSuspended: BetaGoogleManagedStorageRegionSuspended
        ) = apply {
            effectiveBucket = betaGoogleManagedStorageRegionSuspended.effectiveBucket
            type = betaGoogleManagedStorageRegionSuspended.type
            additionalProperties =
                betaGoogleManagedStorageRegionSuspended.additionalProperties.toMutableMap()
        }

        /**
         * The bucket that the region applies now. While a replacement bucket is `pending` or
         * `failed`, this is the previous bucket. `null` when the region applies no bucket.
         */
        fun effectiveBucket(effectiveBucket: BetaGoogleManagedBucketReference?) =
            effectiveBucket(JsonField.ofNullable(effectiveBucket))

        /** Alias for calling [Builder.effectiveBucket] with `effectiveBucket.orElse(null)`. */
        fun effectiveBucket(effectiveBucket: Optional<BetaGoogleManagedBucketReference>) =
            effectiveBucket(effectiveBucket.getOrNull())

        /**
         * Sets [Builder.effectiveBucket] to an arbitrary JSON value.
         *
         * You should usually call [Builder.effectiveBucket] with a well-typed
         * [BetaGoogleManagedBucketReference] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun effectiveBucket(effectiveBucket: JsonField<BetaGoogleManagedBucketReference>) = apply {
            this.effectiveBucket = effectiveBucket
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("suspended")
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
         * Returns an immutable instance of [BetaGoogleManagedStorageRegionSuspended].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .effectiveBucket()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedStorageRegionSuspended =
            BetaGoogleManagedStorageRegionSuspended(
                checkRequired("effectiveBucket", effectiveBucket),
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
    fun validate(): BetaGoogleManagedStorageRegionSuspended = apply {
        if (validated) {
            return@apply
        }

        effectiveBucket().ifPresent { it.validate() }
        _type().let {
            if (it != JsonValue.from("suspended")) {
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
        (effectiveBucket.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("suspended")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedStorageRegionSuspended &&
            effectiveBucket == other.effectiveBucket &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(effectiveBucket, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedStorageRegionSuspended{effectiveBucket=$effectiveBucket, type=$type, additionalProperties=$additionalProperties}"
}
