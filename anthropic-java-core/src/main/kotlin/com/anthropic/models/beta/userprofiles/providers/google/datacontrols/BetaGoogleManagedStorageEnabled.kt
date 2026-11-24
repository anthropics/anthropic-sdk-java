package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

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

/** The control is on and has a bucket for at least one region. */
class BetaGoogleManagedStorageEnabled
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val buckets: JsonField<Buckets>,
    private val retiredBuckets: JsonField<List<BetaGoogleManagedRetiredBucket>>,
    private val status: JsonField<Status>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("buckets") @ExcludeMissing buckets: JsonField<Buckets> = JsonMissing.of(),
        @JsonProperty("retired_buckets")
        @ExcludeMissing
        retiredBuckets: JsonField<List<BetaGoogleManagedRetiredBucket>> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(buckets, retiredBuckets, status, type, mutableMapOf())

    /**
     * The bucket configured for each region, keyed by region code. A region with no bucket is left
     * out.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun buckets(): Buckets = buckets.getRequired("buckets")

    /**
     * Buckets that a region has retired, earliest `expires_at` first.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun retiredBuckets(): List<BetaGoogleManagedRetiredBucket> =
        retiredBuckets.getRequired("retired_buckets")

    /**
     * Each region's status for the control, keyed by region code. A region that your organization
     * is not offered may have no entry, and Anthropic may add regions.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): Status = status.getRequired("status")

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
     * Returns the raw JSON value of [buckets].
     *
     * Unlike [buckets], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("buckets") @ExcludeMissing fun _buckets(): JsonField<Buckets> = buckets

    /**
     * Returns the raw JSON value of [retiredBuckets].
     *
     * Unlike [retiredBuckets], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("retired_buckets")
    @ExcludeMissing
    fun _retiredBuckets(): JsonField<List<BetaGoogleManagedRetiredBucket>> = retiredBuckets

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

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
         * [BetaGoogleManagedStorageEnabled].
         *
         * The following fields are required:
         * ```java
         * .buckets()
         * .retiredBuckets()
         * .status()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedStorageEnabled]. */
    class Builder internal constructor() {

        private var buckets: JsonField<Buckets>? = null
        private var retiredBuckets: JsonField<MutableList<BetaGoogleManagedRetiredBucket>>? = null
        private var status: JsonField<Status>? = null
        private var type: JsonValue = JsonValue.from("enabled")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaGoogleManagedStorageEnabled: BetaGoogleManagedStorageEnabled) =
            apply {
                buckets = betaGoogleManagedStorageEnabled.buckets
                retiredBuckets =
                    betaGoogleManagedStorageEnabled.retiredBuckets
                        .map { it.toMutableList() }
                        .takeUnless { it.isMissing() }
                status = betaGoogleManagedStorageEnabled.status
                type = betaGoogleManagedStorageEnabled.type
                additionalProperties =
                    betaGoogleManagedStorageEnabled.additionalProperties.toMutableMap()
            }

        /**
         * The bucket configured for each region, keyed by region code. A region with no bucket is
         * left out.
         */
        fun buckets(buckets: Buckets) = buckets(JsonField.of(buckets))

        /**
         * Sets [Builder.buckets] to an arbitrary JSON value.
         *
         * You should usually call [Builder.buckets] with a well-typed [Buckets] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun buckets(buckets: JsonField<Buckets>) = apply { this.buckets = buckets }

        /** Buckets that a region has retired, earliest `expires_at` first. */
        fun retiredBuckets(retiredBuckets: List<BetaGoogleManagedRetiredBucket>) =
            retiredBuckets(JsonField.of(retiredBuckets))

        /**
         * Sets [Builder.retiredBuckets] to an arbitrary JSON value.
         *
         * You should usually call [Builder.retiredBuckets] with a well-typed
         * `List<BetaGoogleManagedRetiredBucket>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun retiredBuckets(retiredBuckets: JsonField<List<BetaGoogleManagedRetiredBucket>>) =
            apply {
                this.retiredBuckets = retiredBuckets.map { it.toMutableList() }
            }

        /**
         * Adds a single [BetaGoogleManagedRetiredBucket] to [retiredBuckets].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRetiredBucket(retiredBucket: BetaGoogleManagedRetiredBucket) = apply {
            retiredBuckets =
                (retiredBuckets ?: JsonField.of(mutableListOf())).also {
                    checkKnown("retiredBuckets", it).add(retiredBucket)
                }
        }

        /**
         * Each region's status for the control, keyed by region code. A region that your
         * organization is not offered may have no entry, and Anthropic may add regions.
         */
        fun status(status: Status) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [Status] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<Status>) = apply { this.status = status }

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
         * Returns an immutable instance of [BetaGoogleManagedStorageEnabled].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .buckets()
         * .retiredBuckets()
         * .status()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedStorageEnabled =
            BetaGoogleManagedStorageEnabled(
                checkRequired("buckets", buckets),
                checkRequired("retiredBuckets", retiredBuckets).map { it.toImmutable() },
                checkRequired("status", status),
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
    fun validate(): BetaGoogleManagedStorageEnabled = apply {
        if (validated) {
            return@apply
        }

        buckets().validate()
        retiredBuckets().forEach { it.validate() }
        status().validate()
        _type().let {
            if (it != JsonValue.from("enabled")) {
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
        (buckets.asKnown().getOrNull()?.validity() ?: 0) +
            (retiredBuckets.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("enabled")) 1 else 0 }

    /**
     * The bucket configured for each region, keyed by region code. A region with no bucket is left
     * out.
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

    /**
     * Each region's status for the control, keyed by region code. A region that your organization
     * is not offered may have no entry, and Anthropic may add regions.
     */
    class Status
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

            /** Returns a mutable builder for constructing an instance of [Status]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Status]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(status: Status) = apply {
                additionalProperties = status.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Status].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Status = Status(additionalProperties.toImmutable())
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
        fun validate(): Status = apply {
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

            return other is Status && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Status{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedStorageEnabled &&
            buckets == other.buckets &&
            retiredBuckets == other.retiredBuckets &&
            status == other.status &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(buckets, retiredBuckets, status, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedStorageEnabled{buckets=$buckets, retiredBuckets=$retiredBuckets, status=$status, type=$type, additionalProperties=$additionalProperties}"
}
