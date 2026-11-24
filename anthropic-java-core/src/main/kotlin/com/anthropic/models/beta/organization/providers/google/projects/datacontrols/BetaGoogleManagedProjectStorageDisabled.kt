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
import kotlin.jvm.optionals.getOrNull

/** The control is off and has no buckets. */
class BetaGoogleManagedProjectStorageDisabled
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val retiredBuckets: JsonField<List<BetaGoogleManagedProjectRetiredBucket>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("retired_buckets")
        @ExcludeMissing
        retiredBuckets: JsonField<List<BetaGoogleManagedProjectRetiredBucket>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(retiredBuckets, type, mutableMapOf())

    /**
     * Buckets that a region has retired, earliest `expires_at` first.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun retiredBuckets(): List<BetaGoogleManagedProjectRetiredBucket> =
        retiredBuckets.getRequired("retired_buckets")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("disabled")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [retiredBuckets].
     *
     * Unlike [retiredBuckets], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("retired_buckets")
    @ExcludeMissing
    fun _retiredBuckets(): JsonField<List<BetaGoogleManagedProjectRetiredBucket>> = retiredBuckets

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
         * [BetaGoogleManagedProjectStorageDisabled].
         *
         * The following fields are required:
         * ```java
         * .retiredBuckets()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaGoogleManagedProjectStorageDisabled] with the
         * required [retiredBuckets] set to the given value.
         */
        @JvmStatic
        fun of(retiredBuckets: List<BetaGoogleManagedProjectRetiredBucket>) =
            builder().retiredBuckets(retiredBuckets).build()
    }

    /** A builder for [BetaGoogleManagedProjectStorageDisabled]. */
    class Builder internal constructor() {

        private var retiredBuckets: JsonField<MutableList<BetaGoogleManagedProjectRetiredBucket>>? =
            null
        private var type: JsonValue = JsonValue.from("disabled")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaGoogleManagedProjectStorageDisabled: BetaGoogleManagedProjectStorageDisabled
        ) = apply {
            retiredBuckets =
                betaGoogleManagedProjectStorageDisabled.retiredBuckets
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaGoogleManagedProjectStorageDisabled.type
            additionalProperties =
                betaGoogleManagedProjectStorageDisabled.additionalProperties.toMutableMap()
        }

        /** Buckets that a region has retired, earliest `expires_at` first. */
        fun retiredBuckets(retiredBuckets: List<BetaGoogleManagedProjectRetiredBucket>) =
            retiredBuckets(JsonField.of(retiredBuckets))

        /**
         * Sets [Builder.retiredBuckets] to an arbitrary JSON value.
         *
         * You should usually call [Builder.retiredBuckets] with a well-typed
         * `List<BetaGoogleManagedProjectRetiredBucket>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun retiredBuckets(retiredBuckets: JsonField<List<BetaGoogleManagedProjectRetiredBucket>>) =
            apply {
                this.retiredBuckets = retiredBuckets.map { it.toMutableList() }
            }

        /**
         * Adds a single [BetaGoogleManagedProjectRetiredBucket] to [retiredBuckets].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRetiredBucket(retiredBucket: BetaGoogleManagedProjectRetiredBucket) = apply {
            retiredBuckets =
                (retiredBuckets ?: JsonField.of(mutableListOf())).also {
                    checkKnown("retiredBuckets", it).add(retiredBucket)
                }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("disabled")
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
         * Returns an immutable instance of [BetaGoogleManagedProjectStorageDisabled].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .retiredBuckets()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedProjectStorageDisabled =
            BetaGoogleManagedProjectStorageDisabled(
                checkRequired("retiredBuckets", retiredBuckets).map { it.toImmutable() },
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
    fun validate(): BetaGoogleManagedProjectStorageDisabled = apply {
        if (validated) {
            return@apply
        }

        retiredBuckets().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("disabled")) {
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
        (retiredBuckets.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            type.let { if (it == JsonValue.from("disabled")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedProjectStorageDisabled &&
            retiredBuckets == other.retiredBuckets &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(retiredBuckets, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedProjectStorageDisabled{retiredBuckets=$retiredBuckets, type=$type, additionalProperties=$additionalProperties}"
}
