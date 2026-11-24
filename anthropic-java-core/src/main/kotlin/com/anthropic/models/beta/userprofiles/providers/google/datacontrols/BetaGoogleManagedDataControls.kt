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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * A user profile's encryption key and storage bucket settings for Claude on Google Cloud's Agent
 * Platform. Every user profile has one of these objects. You can read and update it, but not create
 * or delete it. Keys and buckets are set for each region. A request's region depends on the Google
 * endpoint it is sent to: `us` is the US multi-region endpoint and the regional endpoint of every
 * location whose name starts with `us-`, `eu` is the EU multi-region endpoint and the regional
 * endpoint of every location whose name starts with `europe-`, `sg` is the `asia-southeast1`
 * regional endpoint, and `global` is the global endpoint, wherever Google serves the request.
 * Anthropic may add regions.
 */
class BetaGoogleManagedDataControls
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val bucketLabelValue: JsonField<String>,
    private val cmek: JsonField<BetaGoogleManagedCmekControl>,
    private val storage: JsonField<BetaGoogleManagedStorageControl>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val userProfileId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("bucket_label_value")
        @ExcludeMissing
        bucketLabelValue: JsonField<String> = JsonMissing.of(),
        @JsonProperty("cmek")
        @ExcludeMissing
        cmek: JsonField<BetaGoogleManagedCmekControl> = JsonMissing.of(),
        @JsonProperty("storage")
        @ExcludeMissing
        storage: JsonField<BetaGoogleManagedStorageControl> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("user_profile_id")
        @ExcludeMissing
        userProfileId: JsonField<String> = JsonMissing.of(),
    ) : this(bucketLabelValue, cmek, storage, type, updatedAt, userProfileId, mutableMapOf())

    /**
     * The label value that every bucket set up for this profile must have. It never changes.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun bucketLabelValue(): String = bucketLabelValue.getRequired("bucket_label_value")

    /**
     * The customer-managed encryption key control: the key in the end customer's Google Cloud KMS
     * that is set for each region.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun cmek(): BetaGoogleManagedCmekControl = cmek.getRequired("cmek")

    /**
     * The customer-owned storage control: the end customer's Cloud Storage bucket that is set for
     * each region.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun storage(): BetaGoogleManagedStorageControl = storage.getRequired("storage")

    /**
     * Object type. Always `google_managed_data_controls`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("google_managed_data_controls")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * When an update last changed a control, or a region's status last changed, in RFC 3339 format.
     * A region that starts or stops reading `suspended`, a change to `last_checked_at` alone, and
     * the removal of a retired entry do not count.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * The ID of the user profile that these controls belong to (`uprof_...`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun userProfileId(): String = userProfileId.getRequired("user_profile_id")

    /**
     * Returns the raw JSON value of [bucketLabelValue].
     *
     * Unlike [bucketLabelValue], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("bucket_label_value")
    @ExcludeMissing
    fun _bucketLabelValue(): JsonField<String> = bucketLabelValue

    /**
     * Returns the raw JSON value of [cmek].
     *
     * Unlike [cmek], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cmek")
    @ExcludeMissing
    fun _cmek(): JsonField<BetaGoogleManagedCmekControl> = cmek

    /**
     * Returns the raw JSON value of [storage].
     *
     * Unlike [storage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("storage")
    @ExcludeMissing
    fun _storage(): JsonField<BetaGoogleManagedStorageControl> = storage

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [userProfileId].
     *
     * Unlike [userProfileId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_profile_id")
    @ExcludeMissing
    fun _userProfileId(): JsonField<String> = userProfileId

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
         * [BetaGoogleManagedDataControls].
         *
         * The following fields are required:
         * ```java
         * .bucketLabelValue()
         * .cmek()
         * .storage()
         * .updatedAt()
         * .userProfileId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedDataControls]. */
    class Builder internal constructor() {

        private var bucketLabelValue: JsonField<String>? = null
        private var cmek: JsonField<BetaGoogleManagedCmekControl>? = null
        private var storage: JsonField<BetaGoogleManagedStorageControl>? = null
        private var type: JsonValue = JsonValue.from("google_managed_data_controls")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var userProfileId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaGoogleManagedDataControls: BetaGoogleManagedDataControls) = apply {
            bucketLabelValue = betaGoogleManagedDataControls.bucketLabelValue
            cmek = betaGoogleManagedDataControls.cmek
            storage = betaGoogleManagedDataControls.storage
            type = betaGoogleManagedDataControls.type
            updatedAt = betaGoogleManagedDataControls.updatedAt
            userProfileId = betaGoogleManagedDataControls.userProfileId
            additionalProperties = betaGoogleManagedDataControls.additionalProperties.toMutableMap()
        }

        /**
         * The label value that every bucket set up for this profile must have. It never changes.
         */
        fun bucketLabelValue(bucketLabelValue: String) =
            bucketLabelValue(JsonField.of(bucketLabelValue))

        /**
         * Sets [Builder.bucketLabelValue] to an arbitrary JSON value.
         *
         * You should usually call [Builder.bucketLabelValue] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun bucketLabelValue(bucketLabelValue: JsonField<String>) = apply {
            this.bucketLabelValue = bucketLabelValue
        }

        /**
         * The customer-managed encryption key control: the key in the end customer's Google Cloud
         * KMS that is set for each region.
         */
        fun cmek(cmek: BetaGoogleManagedCmekControl) = cmek(JsonField.of(cmek))

        /**
         * Sets [Builder.cmek] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cmek] with a well-typed [BetaGoogleManagedCmekControl]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cmek(cmek: JsonField<BetaGoogleManagedCmekControl>) = apply { this.cmek = cmek }

        /** Alias for calling [cmek] with `BetaGoogleManagedCmekControl.ofDisabled(disabled)`. */
        fun cmek(disabled: BetaGoogleManagedCmekDisabled) =
            cmek(BetaGoogleManagedCmekControl.ofDisabled(disabled))

        /**
         * Alias for calling [cmek] with the following:
         * ```java
         * BetaGoogleManagedCmekDisabled.builder()
         *     .retiredKeys(retiredKeys)
         *     .build()
         * ```
         */
        fun disabledCmek(retiredKeys: List<BetaGoogleManagedRetiredKey>) =
            cmek(BetaGoogleManagedCmekDisabled.builder().retiredKeys(retiredKeys).build())

        /** Alias for calling [cmek] with `BetaGoogleManagedCmekControl.ofEnabled(enabled)`. */
        fun cmek(enabled: BetaGoogleManagedCmekEnabled) =
            cmek(BetaGoogleManagedCmekControl.ofEnabled(enabled))

        /**
         * The customer-owned storage control: the end customer's Cloud Storage bucket that is set
         * for each region.
         */
        fun storage(storage: BetaGoogleManagedStorageControl) = storage(JsonField.of(storage))

        /**
         * Sets [Builder.storage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.storage] with a well-typed
         * [BetaGoogleManagedStorageControl] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun storage(storage: JsonField<BetaGoogleManagedStorageControl>) = apply {
            this.storage = storage
        }

        /**
         * Alias for calling [storage] with `BetaGoogleManagedStorageControl.ofDisabled(disabled)`.
         */
        fun storage(disabled: BetaGoogleManagedStorageDisabled) =
            storage(BetaGoogleManagedStorageControl.ofDisabled(disabled))

        /**
         * Alias for calling [storage] with the following:
         * ```java
         * BetaGoogleManagedStorageDisabled.builder()
         *     .retiredBuckets(retiredBuckets)
         *     .build()
         * ```
         */
        fun disabledStorage(retiredBuckets: List<BetaGoogleManagedRetiredBucket>) =
            storage(
                BetaGoogleManagedStorageDisabled.builder().retiredBuckets(retiredBuckets).build()
            )

        /**
         * Alias for calling [storage] with `BetaGoogleManagedStorageControl.ofEnabled(enabled)`.
         */
        fun storage(enabled: BetaGoogleManagedStorageEnabled) =
            storage(BetaGoogleManagedStorageControl.ofEnabled(enabled))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("google_managed_data_controls")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * When an update last changed a control, or a region's status last changed, in RFC 3339
         * format. A region that starts or stops reading `suspended`, a change to `last_checked_at`
         * alone, and the removal of a retired entry do not count.
         */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /** The ID of the user profile that these controls belong to (`uprof_...`). */
        fun userProfileId(userProfileId: String) = userProfileId(JsonField.of(userProfileId))

        /**
         * Sets [Builder.userProfileId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userProfileId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun userProfileId(userProfileId: JsonField<String>) = apply {
            this.userProfileId = userProfileId
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
         * Returns an immutable instance of [BetaGoogleManagedDataControls].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .bucketLabelValue()
         * .cmek()
         * .storage()
         * .updatedAt()
         * .userProfileId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedDataControls =
            BetaGoogleManagedDataControls(
                checkRequired("bucketLabelValue", bucketLabelValue),
                checkRequired("cmek", cmek),
                checkRequired("storage", storage),
                type,
                checkRequired("updatedAt", updatedAt),
                checkRequired("userProfileId", userProfileId),
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
    fun validate(): BetaGoogleManagedDataControls = apply {
        if (validated) {
            return@apply
        }

        bucketLabelValue()
        cmek().validate()
        storage().validate()
        _type().let {
            if (it != JsonValue.from("google_managed_data_controls")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
        userProfileId()
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
        (if (bucketLabelValue.asKnown().isPresent) 1 else 0) +
            (cmek.asKnown().getOrNull()?.validity() ?: 0) +
            (storage.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("google_managed_data_controls")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (userProfileId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedDataControls &&
            bucketLabelValue == other.bucketLabelValue &&
            cmek == other.cmek &&
            storage == other.storage &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            userProfileId == other.userProfileId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            bucketLabelValue,
            cmek,
            storage,
            type,
            updatedAt,
            userProfileId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedDataControls{bucketLabelValue=$bucketLabelValue, cmek=$cmek, storage=$storage, type=$type, updatedAt=$updatedAt, userProfileId=$userProfileId, additionalProperties=$additionalProperties}"
}
