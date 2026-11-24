package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

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
 * A Google Cloud project's encryption key and storage bucket settings for Claude on Google Cloud's
 * Agent Platform. Your organization has one of these objects for each Google Cloud project that it
 * has proved it owns. You can read and update it, but not create or delete it. Keys and buckets are
 * set for each region. A request's region depends on the Google endpoint it is sent to: `us` is the
 * US multi-region endpoint and the regional endpoint of every location whose name starts with
 * `us-`, `eu` is the EU multi-region endpoint and the regional endpoint of every location whose
 * name starts with `europe-`, `sg` is the `asia-southeast1` regional endpoint, and `global` is the
 * global endpoint, wherever Google serves the request. Anthropic may add regions.
 */
class BetaGoogleManagedProjectDataControls
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cmek: JsonField<BetaGoogleManagedProjectCmekControl>,
    private val projectNumber: JsonField<String>,
    private val storage: JsonField<BetaGoogleManagedProjectStorageControl>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cmek")
        @ExcludeMissing
        cmek: JsonField<BetaGoogleManagedProjectCmekControl> = JsonMissing.of(),
        @JsonProperty("project_number")
        @ExcludeMissing
        projectNumber: JsonField<String> = JsonMissing.of(),
        @JsonProperty("storage")
        @ExcludeMissing
        storage: JsonField<BetaGoogleManagedProjectStorageControl> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(cmek, projectNumber, storage, type, updatedAt, mutableMapOf())

    /**
     * The customer-managed encryption key control: the key in your Google Cloud KMS that is set for
     * each region. It covers the project's requests that name no user profile, or a user profile
     * that has no key of its own for the project.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun cmek(): BetaGoogleManagedProjectCmekControl = cmek.getRequired("cmek")

    /**
     * The number of the Google Cloud project that these controls belong to, in decimal.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun projectNumber(): String = projectNumber.getRequired("project_number")

    /**
     * The customer-owned storage control: your Cloud Storage bucket that is set for each region. It
     * covers the project's requests that name no user profile, or a user profile that has no bucket
     * of its own for the project.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun storage(): BetaGoogleManagedProjectStorageControl = storage.getRequired("storage")

    /**
     * Object type. Always `google_managed_project_data_controls`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("google_managed_project_data_controls")
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
     * Returns the raw JSON value of [cmek].
     *
     * Unlike [cmek], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cmek")
    @ExcludeMissing
    fun _cmek(): JsonField<BetaGoogleManagedProjectCmekControl> = cmek

    /**
     * Returns the raw JSON value of [projectNumber].
     *
     * Unlike [projectNumber], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("project_number")
    @ExcludeMissing
    fun _projectNumber(): JsonField<String> = projectNumber

    /**
     * Returns the raw JSON value of [storage].
     *
     * Unlike [storage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("storage")
    @ExcludeMissing
    fun _storage(): JsonField<BetaGoogleManagedProjectStorageControl> = storage

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

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
         * [BetaGoogleManagedProjectDataControls].
         *
         * The following fields are required:
         * ```java
         * .cmek()
         * .projectNumber()
         * .storage()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedProjectDataControls]. */
    class Builder internal constructor() {

        private var cmek: JsonField<BetaGoogleManagedProjectCmekControl>? = null
        private var projectNumber: JsonField<String>? = null
        private var storage: JsonField<BetaGoogleManagedProjectStorageControl>? = null
        private var type: JsonValue = JsonValue.from("google_managed_project_data_controls")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaGoogleManagedProjectDataControls: BetaGoogleManagedProjectDataControls
        ) = apply {
            cmek = betaGoogleManagedProjectDataControls.cmek
            projectNumber = betaGoogleManagedProjectDataControls.projectNumber
            storage = betaGoogleManagedProjectDataControls.storage
            type = betaGoogleManagedProjectDataControls.type
            updatedAt = betaGoogleManagedProjectDataControls.updatedAt
            additionalProperties =
                betaGoogleManagedProjectDataControls.additionalProperties.toMutableMap()
        }

        /**
         * The customer-managed encryption key control: the key in your Google Cloud KMS that is set
         * for each region. It covers the project's requests that name no user profile, or a user
         * profile that has no key of its own for the project.
         */
        fun cmek(cmek: BetaGoogleManagedProjectCmekControl) = cmek(JsonField.of(cmek))

        /**
         * Sets [Builder.cmek] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cmek] with a well-typed
         * [BetaGoogleManagedProjectCmekControl] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun cmek(cmek: JsonField<BetaGoogleManagedProjectCmekControl>) = apply { this.cmek = cmek }

        /**
         * Alias for calling [cmek] with `BetaGoogleManagedProjectCmekControl.ofDisabled(disabled)`.
         */
        fun cmek(disabled: BetaGoogleManagedProjectCmekDisabled) =
            cmek(BetaGoogleManagedProjectCmekControl.ofDisabled(disabled))

        /**
         * Alias for calling [cmek] with the following:
         * ```java
         * BetaGoogleManagedProjectCmekDisabled.builder()
         *     .retiredKeys(retiredKeys)
         *     .build()
         * ```
         */
        fun disabledCmek(retiredKeys: List<BetaGoogleManagedProjectRetiredKey>) =
            cmek(BetaGoogleManagedProjectCmekDisabled.builder().retiredKeys(retiredKeys).build())

        /**
         * Alias for calling [cmek] with `BetaGoogleManagedProjectCmekControl.ofEnabled(enabled)`.
         */
        fun cmek(enabled: BetaGoogleManagedProjectCmekEnabled) =
            cmek(BetaGoogleManagedProjectCmekControl.ofEnabled(enabled))

        /** The number of the Google Cloud project that these controls belong to, in decimal. */
        fun projectNumber(projectNumber: String) = projectNumber(JsonField.of(projectNumber))

        /**
         * Sets [Builder.projectNumber] to an arbitrary JSON value.
         *
         * You should usually call [Builder.projectNumber] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun projectNumber(projectNumber: JsonField<String>) = apply {
            this.projectNumber = projectNumber
        }

        /**
         * The customer-owned storage control: your Cloud Storage bucket that is set for each
         * region. It covers the project's requests that name no user profile, or a user profile
         * that has no bucket of its own for the project.
         */
        fun storage(storage: BetaGoogleManagedProjectStorageControl) =
            storage(JsonField.of(storage))

        /**
         * Sets [Builder.storage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.storage] with a well-typed
         * [BetaGoogleManagedProjectStorageControl] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun storage(storage: JsonField<BetaGoogleManagedProjectStorageControl>) = apply {
            this.storage = storage
        }

        /**
         * Alias for calling [storage] with
         * `BetaGoogleManagedProjectStorageControl.ofDisabled(disabled)`.
         */
        fun storage(disabled: BetaGoogleManagedProjectStorageDisabled) =
            storage(BetaGoogleManagedProjectStorageControl.ofDisabled(disabled))

        /**
         * Alias for calling [storage] with the following:
         * ```java
         * BetaGoogleManagedProjectStorageDisabled.builder()
         *     .retiredBuckets(retiredBuckets)
         *     .build()
         * ```
         */
        fun disabledStorage(retiredBuckets: List<BetaGoogleManagedProjectRetiredBucket>) =
            storage(
                BetaGoogleManagedProjectStorageDisabled.builder()
                    .retiredBuckets(retiredBuckets)
                    .build()
            )

        /**
         * Alias for calling [storage] with
         * `BetaGoogleManagedProjectStorageControl.ofEnabled(enabled)`.
         */
        fun storage(enabled: BetaGoogleManagedProjectStorageEnabled) =
            storage(BetaGoogleManagedProjectStorageControl.ofEnabled(enabled))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("google_managed_project_data_controls")
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
         * Returns an immutable instance of [BetaGoogleManagedProjectDataControls].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .cmek()
         * .projectNumber()
         * .storage()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedProjectDataControls =
            BetaGoogleManagedProjectDataControls(
                checkRequired("cmek", cmek),
                checkRequired("projectNumber", projectNumber),
                checkRequired("storage", storage),
                type,
                checkRequired("updatedAt", updatedAt),
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
    fun validate(): BetaGoogleManagedProjectDataControls = apply {
        if (validated) {
            return@apply
        }

        cmek().validate()
        projectNumber()
        storage().validate()
        _type().let {
            if (it != JsonValue.from("google_managed_project_data_controls")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
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
        (cmek.asKnown().getOrNull()?.validity() ?: 0) +
            (if (projectNumber.asKnown().isPresent) 1 else 0) +
            (storage.asKnown().getOrNull()?.validity() ?: 0) +
            type.let {
                if (it == JsonValue.from("google_managed_project_data_controls")) 1 else 0
            } +
            (if (updatedAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedProjectDataControls &&
            cmek == other.cmek &&
            projectNumber == other.projectNumber &&
            storage == other.storage &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(cmek, projectNumber, storage, type, updatedAt, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedProjectDataControls{cmek=$cmek, projectNumber=$projectNumber, storage=$storage, type=$type, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
