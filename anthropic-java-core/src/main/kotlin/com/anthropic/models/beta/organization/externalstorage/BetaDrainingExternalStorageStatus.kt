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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/**
 * No Workspace has the storage attached. A Workspace can attach it again, unless `removal_started`
 * is true.
 */
class BetaDrainingExternalStorageStatus
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val healthy: JsonField<Boolean>,
    private val lastCheckedAt: JsonField<OffsetDateTime>,
    private val removalStarted: JsonField<Boolean>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("healthy") @ExcludeMissing healthy: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("last_checked_at")
        @ExcludeMissing
        lastCheckedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("removal_started")
        @ExcludeMissing
        removalStarted: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(healthy, lastCheckedAt, removalStarted, type, mutableMapOf())

    /**
     * Whether the bucket passed its latest recorded check. It can stay `true` after the bucket
     * stops working, so don't use it for monitoring.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun healthy(): Boolean = healthy.getRequired("healthy")

    /**
     * When the bucket's latest recorded check ran, in RFC 3339.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun lastCheckedAt(): OffsetDateTime = lastCheckedAt.getRequired("last_checked_at")

    /**
     * True once a delete has started removing this storage, which can't be undone. It then can't be
     * attached, and another delete finishes the removal.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun removalStarted(): Boolean = removalStarted.getRequired("removal_started")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("draining")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [healthy].
     *
     * Unlike [healthy], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("healthy") @ExcludeMissing fun _healthy(): JsonField<Boolean> = healthy

    /**
     * Returns the raw JSON value of [lastCheckedAt].
     *
     * Unlike [lastCheckedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("last_checked_at")
    @ExcludeMissing
    fun _lastCheckedAt(): JsonField<OffsetDateTime> = lastCheckedAt

    /**
     * Returns the raw JSON value of [removalStarted].
     *
     * Unlike [removalStarted], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("removal_started")
    @ExcludeMissing
    fun _removalStarted(): JsonField<Boolean> = removalStarted

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
         * [BetaDrainingExternalStorageStatus].
         *
         * The following fields are required:
         * ```java
         * .healthy()
         * .lastCheckedAt()
         * .removalStarted()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaDrainingExternalStorageStatus]. */
    class Builder internal constructor() {

        private var healthy: JsonField<Boolean>? = null
        private var lastCheckedAt: JsonField<OffsetDateTime>? = null
        private var removalStarted: JsonField<Boolean>? = null
        private var type: JsonValue = JsonValue.from("draining")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaDrainingExternalStorageStatus: BetaDrainingExternalStorageStatus) =
            apply {
                healthy = betaDrainingExternalStorageStatus.healthy
                lastCheckedAt = betaDrainingExternalStorageStatus.lastCheckedAt
                removalStarted = betaDrainingExternalStorageStatus.removalStarted
                type = betaDrainingExternalStorageStatus.type
                additionalProperties =
                    betaDrainingExternalStorageStatus.additionalProperties.toMutableMap()
            }

        /**
         * Whether the bucket passed its latest recorded check. It can stay `true` after the bucket
         * stops working, so don't use it for monitoring.
         */
        fun healthy(healthy: Boolean) = healthy(JsonField.of(healthy))

        /**
         * Sets [Builder.healthy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.healthy] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun healthy(healthy: JsonField<Boolean>) = apply { this.healthy = healthy }

        /** When the bucket's latest recorded check ran, in RFC 3339. */
        fun lastCheckedAt(lastCheckedAt: OffsetDateTime) =
            lastCheckedAt(JsonField.of(lastCheckedAt))

        /**
         * Sets [Builder.lastCheckedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastCheckedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun lastCheckedAt(lastCheckedAt: JsonField<OffsetDateTime>) = apply {
            this.lastCheckedAt = lastCheckedAt
        }

        /**
         * True once a delete has started removing this storage, which can't be undone. It then
         * can't be attached, and another delete finishes the removal.
         */
        fun removalStarted(removalStarted: Boolean) = removalStarted(JsonField.of(removalStarted))

        /**
         * Sets [Builder.removalStarted] to an arbitrary JSON value.
         *
         * You should usually call [Builder.removalStarted] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun removalStarted(removalStarted: JsonField<Boolean>) = apply {
            this.removalStarted = removalStarted
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("draining")
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
         * Returns an immutable instance of [BetaDrainingExternalStorageStatus].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .healthy()
         * .lastCheckedAt()
         * .removalStarted()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaDrainingExternalStorageStatus =
            BetaDrainingExternalStorageStatus(
                checkRequired("healthy", healthy),
                checkRequired("lastCheckedAt", lastCheckedAt),
                checkRequired("removalStarted", removalStarted),
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
    fun validate(): BetaDrainingExternalStorageStatus = apply {
        if (validated) {
            return@apply
        }

        healthy()
        lastCheckedAt()
        removalStarted()
        _type().let {
            if (it != JsonValue.from("draining")) {
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
        (if (healthy.asKnown().isPresent) 1 else 0) +
            (if (lastCheckedAt.asKnown().isPresent) 1 else 0) +
            (if (removalStarted.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("draining")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaDrainingExternalStorageStatus &&
            healthy == other.healthy &&
            lastCheckedAt == other.lastCheckedAt &&
            removalStarted == other.removalStarted &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(healthy, lastCheckedAt, removalStarted, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaDrainingExternalStorageStatus{healthy=$healthy, lastCheckedAt=$lastCheckedAt, removalStarted=$removalStarted, type=$type, additionalProperties=$additionalProperties}"
}
