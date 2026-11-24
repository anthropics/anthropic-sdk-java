package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The region is verifying the configured bucket. */
class BetaGoogleManagedProjectStorageRegionPending
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val effectiveBucket: JsonField<BetaGoogleManagedProjectEffectiveBucket>,
    private val failedChecks: JsonField<List<BetaGoogleManagedProjectStorageFailedCheck>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("effective_bucket")
        @ExcludeMissing
        effectiveBucket: JsonField<BetaGoogleManagedProjectEffectiveBucket> = JsonMissing.of(),
        @JsonProperty("failed_checks")
        @ExcludeMissing
        failedChecks: JsonField<List<BetaGoogleManagedProjectStorageFailedCheck>> =
            JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(effectiveBucket, failedChecks, type, mutableMapOf())

    /**
     * The bucket that the region applies now. While a replacement bucket is `pending` or `failed`,
     * this is the previous bucket. `null` when the region applies no bucket.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun effectiveBucket(): Optional<BetaGoogleManagedProjectEffectiveBucket> =
        effectiveBucket.getOptional("effective_bucket")

    /**
     * The checks that the latest reported verification attempt failed. Empty when none has failed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun failedChecks(): List<BetaGoogleManagedProjectStorageFailedCheck> =
        failedChecks.getRequired("failed_checks")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("pending")
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
    fun _effectiveBucket(): JsonField<BetaGoogleManagedProjectEffectiveBucket> = effectiveBucket

    /**
     * Returns the raw JSON value of [failedChecks].
     *
     * Unlike [failedChecks], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("failed_checks")
    @ExcludeMissing
    fun _failedChecks(): JsonField<List<BetaGoogleManagedProjectStorageFailedCheck>> = failedChecks

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
         * [BetaGoogleManagedProjectStorageRegionPending].
         *
         * The following fields are required:
         * ```java
         * .effectiveBucket()
         * .failedChecks()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedProjectStorageRegionPending]. */
    class Builder internal constructor() {

        private var effectiveBucket: JsonField<BetaGoogleManagedProjectEffectiveBucket>? = null
        private var failedChecks:
            JsonField<MutableList<BetaGoogleManagedProjectStorageFailedCheck>>? =
            null
        private var type: JsonValue = JsonValue.from("pending")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaGoogleManagedProjectStorageRegionPending:
                BetaGoogleManagedProjectStorageRegionPending
        ) = apply {
            effectiveBucket = betaGoogleManagedProjectStorageRegionPending.effectiveBucket
            failedChecks =
                betaGoogleManagedProjectStorageRegionPending.failedChecks
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaGoogleManagedProjectStorageRegionPending.type
            additionalProperties =
                betaGoogleManagedProjectStorageRegionPending.additionalProperties.toMutableMap()
        }

        /**
         * The bucket that the region applies now. While a replacement bucket is `pending` or
         * `failed`, this is the previous bucket. `null` when the region applies no bucket.
         */
        fun effectiveBucket(effectiveBucket: BetaGoogleManagedProjectEffectiveBucket?) =
            effectiveBucket(JsonField.ofNullable(effectiveBucket))

        /** Alias for calling [Builder.effectiveBucket] with `effectiveBucket.orElse(null)`. */
        fun effectiveBucket(effectiveBucket: Optional<BetaGoogleManagedProjectEffectiveBucket>) =
            effectiveBucket(effectiveBucket.getOrNull())

        /**
         * Sets [Builder.effectiveBucket] to an arbitrary JSON value.
         *
         * You should usually call [Builder.effectiveBucket] with a well-typed
         * [BetaGoogleManagedProjectEffectiveBucket] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun effectiveBucket(effectiveBucket: JsonField<BetaGoogleManagedProjectEffectiveBucket>) =
            apply {
                this.effectiveBucket = effectiveBucket
            }

        /**
         * The checks that the latest reported verification attempt failed. Empty when none has
         * failed.
         */
        fun failedChecks(failedChecks: List<BetaGoogleManagedProjectStorageFailedCheck>) =
            failedChecks(JsonField.of(failedChecks))

        /**
         * Sets [Builder.failedChecks] to an arbitrary JSON value.
         *
         * You should usually call [Builder.failedChecks] with a well-typed
         * `List<BetaGoogleManagedProjectStorageFailedCheck>` value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun failedChecks(
            failedChecks: JsonField<List<BetaGoogleManagedProjectStorageFailedCheck>>
        ) = apply { this.failedChecks = failedChecks.map { it.toMutableList() } }

        /**
         * Adds a single [BetaGoogleManagedProjectStorageFailedCheck] to [failedChecks].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addFailedCheck(failedCheck: BetaGoogleManagedProjectStorageFailedCheck) = apply {
            failedChecks =
                (failedChecks ?: JsonField.of(mutableListOf())).also {
                    checkKnown("failedChecks", it).add(failedCheck)
                }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("pending")
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
         * Returns an immutable instance of [BetaGoogleManagedProjectStorageRegionPending].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .effectiveBucket()
         * .failedChecks()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedProjectStorageRegionPending =
            BetaGoogleManagedProjectStorageRegionPending(
                checkRequired("effectiveBucket", effectiveBucket),
                checkRequired("failedChecks", failedChecks).map { it.toImmutable() },
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
    fun validate(): BetaGoogleManagedProjectStorageRegionPending = apply {
        if (validated) {
            return@apply
        }

        effectiveBucket().ifPresent { it.validate() }
        failedChecks().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("pending")) {
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
            (failedChecks.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            type.let { if (it == JsonValue.from("pending")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedProjectStorageRegionPending &&
            effectiveBucket == other.effectiveBucket &&
            failedChecks == other.failedChecks &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(effectiveBucket, failedChecks, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedProjectStorageRegionPending{effectiveBucket=$effectiveBucket, failedChecks=$failedChecks, type=$type, additionalProperties=$additionalProperties}"
}
