package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
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

/** Turns the control on, or changes its buckets while it is on. */
class BetaGoogleManagedProjectStorageEnabledParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val buckets: JsonField<Buckets>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("buckets") @ExcludeMissing buckets: JsonField<Buckets> = JsonMissing.of(),
    ) : this(type, buckets, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("enabled")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The bucket to use in each region, keyed by region code, such as `us` or `eu`. An object with
     * `project_number` and `bucket_name` sets that region's bucket, `null` removes it, and a region
     * you leave out keeps its bucket. A bucket can serve only one region. While the control is on,
     * at least one region must have a bucket.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun buckets(): Optional<Buckets> = buckets.getOptional("buckets")

    /**
     * Returns the raw JSON value of [buckets].
     *
     * Unlike [buckets], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("buckets") @ExcludeMissing fun _buckets(): JsonField<Buckets> = buckets

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
         * [BetaGoogleManagedProjectStorageEnabledParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedProjectStorageEnabledParams]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("enabled")
        private var buckets: JsonField<Buckets> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaGoogleManagedProjectStorageEnabledParams:
                BetaGoogleManagedProjectStorageEnabledParams
        ) = apply {
            type = betaGoogleManagedProjectStorageEnabledParams.type
            buckets = betaGoogleManagedProjectStorageEnabledParams.buckets
            additionalProperties =
                betaGoogleManagedProjectStorageEnabledParams.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("enabled")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * The bucket to use in each region, keyed by region code, such as `us` or `eu`. An object
         * with `project_number` and `bucket_name` sets that region's bucket, `null` removes it, and
         * a region you leave out keeps its bucket. A bucket can serve only one region. While the
         * control is on, at least one region must have a bucket.
         */
        fun buckets(buckets: Buckets) = buckets(JsonField.of(buckets))

        /**
         * Sets [Builder.buckets] to an arbitrary JSON value.
         *
         * You should usually call [Builder.buckets] with a well-typed [Buckets] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun buckets(buckets: JsonField<Buckets>) = apply { this.buckets = buckets }

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
         * Returns an immutable instance of [BetaGoogleManagedProjectStorageEnabledParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaGoogleManagedProjectStorageEnabledParams =
            BetaGoogleManagedProjectStorageEnabledParams(
                type,
                buckets,
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
    fun validate(): BetaGoogleManagedProjectStorageEnabledParams = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("enabled")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        buckets().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("enabled")) 1 else 0 } +
            (buckets.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * The bucket to use in each region, keyed by region code, such as `us` or `eu`. An object with
     * `project_number` and `bucket_name` sets that region's bucket, `null` removes it, and a region
     * you leave out keeps its bucket. A bucket can serve only one region. While the control is on,
     * at least one region must have a bucket.
     */
    class Buckets
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Buckets]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Buckets]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(buckets: Buckets) = apply {
                additionalProperties = buckets.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Buckets].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Buckets = Buckets(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Buckets = apply {
            if (validated) {
                return@apply
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Buckets && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Buckets{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedProjectStorageEnabledParams &&
            type == other.type &&
            buckets == other.buckets &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, buckets, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedProjectStorageEnabledParams{type=$type, buckets=$buckets, additionalProperties=$additionalProperties}"
}
