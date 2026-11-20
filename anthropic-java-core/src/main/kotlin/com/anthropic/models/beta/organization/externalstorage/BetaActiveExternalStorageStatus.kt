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

/** At least one Workspace has the storage attached. */
class BetaActiveExternalStorageStatus
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val healthy: JsonField<Boolean>,
    private val lastCheckedAt: JsonField<OffsetDateTime>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("healthy") @ExcludeMissing healthy: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("last_checked_at")
        @ExcludeMissing
        lastCheckedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(healthy, lastCheckedAt, type, mutableMapOf())

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
     * Expected to always return the following:
     * ```java
     * JsonValue.from("active")
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
         * [BetaActiveExternalStorageStatus].
         *
         * The following fields are required:
         * ```java
         * .healthy()
         * .lastCheckedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaActiveExternalStorageStatus]. */
    class Builder internal constructor() {

        private var healthy: JsonField<Boolean>? = null
        private var lastCheckedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonValue = JsonValue.from("active")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaActiveExternalStorageStatus: BetaActiveExternalStorageStatus) =
            apply {
                healthy = betaActiveExternalStorageStatus.healthy
                lastCheckedAt = betaActiveExternalStorageStatus.lastCheckedAt
                type = betaActiveExternalStorageStatus.type
                additionalProperties =
                    betaActiveExternalStorageStatus.additionalProperties.toMutableMap()
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
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("active")
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
         * Returns an immutable instance of [BetaActiveExternalStorageStatus].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .healthy()
         * .lastCheckedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaActiveExternalStorageStatus =
            BetaActiveExternalStorageStatus(
                checkRequired("healthy", healthy),
                checkRequired("lastCheckedAt", lastCheckedAt),
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
    fun validate(): BetaActiveExternalStorageStatus = apply {
        if (validated) {
            return@apply
        }

        healthy()
        lastCheckedAt()
        _type().let {
            if (it != JsonValue.from("active")) {
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
            type.let { if (it == JsonValue.from("active")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaActiveExternalStorageStatus &&
            healthy == other.healthy &&
            lastCheckedAt == other.lastCheckedAt &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(healthy, lastCheckedAt, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaActiveExternalStorageStatus{healthy=$healthy, lastCheckedAt=$lastCheckedAt, type=$type, additionalProperties=$additionalProperties}"
}
