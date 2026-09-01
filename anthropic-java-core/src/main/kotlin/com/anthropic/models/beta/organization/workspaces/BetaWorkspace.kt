// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaWorkspace
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val compartmentId: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val dataResidency: JsonField<BetaDataResidency>,
    private val displayColor: JsonField<String>,
    private val externalKeyId: JsonField<String>,
    private val name: JsonField<String>,
    private val tags: JsonField<Tags>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("archived_at")
        @ExcludeMissing
        archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("compartment_id")
        @ExcludeMissing
        compartmentId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("data_residency")
        @ExcludeMissing
        dataResidency: JsonField<BetaDataResidency> = JsonMissing.of(),
        @JsonProperty("display_color")
        @ExcludeMissing
        displayColor: JsonField<String> = JsonMissing.of(),
        @JsonProperty("external_key_id")
        @ExcludeMissing
        externalKeyId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tags") @ExcludeMissing tags: JsonField<Tags> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        id,
        archivedAt,
        compartmentId,
        createdAt,
        dataResidency,
        displayColor,
        externalKeyId,
        name,
        tags,
        type,
        mutableMapOf(),
    )

    /**
     * ID of the Workspace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * RFC 3339 datetime string indicating when the Workspace was archived, or `null` if the
     * Workspace is not archived.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedAt(): Optional<OffsetDateTime> = archivedAt.getOptional("archived_at")

    /**
     * Identifier for this Workspace's encryption compartment. When you configure a customer-managed
     * encryption key (CMEK) on AWS, reference this value in your KMS key-policy condition so the
     * key is scoped to this compartment. On GCP and Azure, Anthropic enforces the compartment
     * binding automatically; you do not need to reference this value in your key configuration. See
     * the CMEK integration guide for the required key configuration; unless your organization is on
     * Claude Platform on AWS, it includes a separate value used during key validation. On Claude
     * Platform on AWS there is no separate validation value: the key is validated against this
     * Workspace's own value when it is attached, so if your key policy uses the compartment
     * condition, add this value to it before attaching the key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun compartmentId(): String = compartmentId.getRequired("compartment_id")

    /**
     * RFC 3339 datetime string indicating when the Workspace was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Data residency configuration.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun dataResidency(): BetaDataResidency = dataResidency.getRequired("data_residency")

    /**
     * Hex color code representing the Workspace in the Anthropic Console.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun displayColor(): String = displayColor.getRequired("display_color")

    /**
     * ID of the customer-managed encryption key (CMEK) configuration to use for this Workspace.
     * Setting this field requires CMEK to be enabled for your organization. When set, data stored
     * for this Workspace is encrypted with the referenced key. Create key configurations with the
     * External Keys API. On Claude Platform on AWS the value is the AWS KMS key ARN, and the key
     * must be a single-Region key in the same AWS account and Region as the Workspace. On that
     * platform the key is validated against this Workspace when it is attached, so a key-policy
     * problem is reported as an error on this request. This field is write-once: once a key is
     * attached to a Workspace it cannot be detached or replaced. To rotate key material, rotate the
     * underlying key on your cloud KMS; the `external_key_id` stays the same.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun externalKeyId(): Optional<String> = externalKeyId.getOptional("external_key_id")

    /**
     * Name of the Workspace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * User-defined tags as string key-value pairs. Keys may not begin with `anthropic`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tags(): Tags = tags.getRequired("tags")

    /**
     * Object type.
     *
     * For Workspaces, this is always `"workspace"`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("workspace")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [archivedAt].
     *
     * Unlike [archivedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("archived_at")
    @ExcludeMissing
    fun _archivedAt(): JsonField<OffsetDateTime> = archivedAt

    /**
     * Returns the raw JSON value of [compartmentId].
     *
     * Unlike [compartmentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("compartment_id")
    @ExcludeMissing
    fun _compartmentId(): JsonField<String> = compartmentId

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [dataResidency].
     *
     * Unlike [dataResidency], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data_residency")
    @ExcludeMissing
    fun _dataResidency(): JsonField<BetaDataResidency> = dataResidency

    /**
     * Returns the raw JSON value of [displayColor].
     *
     * Unlike [displayColor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display_color")
    @ExcludeMissing
    fun _displayColor(): JsonField<String> = displayColor

    /**
     * Returns the raw JSON value of [externalKeyId].
     *
     * Unlike [externalKeyId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("external_key_id")
    @ExcludeMissing
    fun _externalKeyId(): JsonField<String> = externalKeyId

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [tags].
     *
     * Unlike [tags], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tags") @ExcludeMissing fun _tags(): JsonField<Tags> = tags

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
         * Returns a mutable builder for constructing an instance of [BetaWorkspace].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .compartmentId()
         * .createdAt()
         * .dataResidency()
         * .displayColor()
         * .externalKeyId()
         * .name()
         * .tags()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaWorkspace]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var compartmentId: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var dataResidency: JsonField<BetaDataResidency>? = null
        private var displayColor: JsonField<String>? = null
        private var externalKeyId: JsonField<String>? = null
        private var name: JsonField<String>? = null
        private var tags: JsonField<Tags>? = null
        private var type: JsonValue = JsonValue.from("workspace")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaWorkspace: BetaWorkspace) = apply {
            id = betaWorkspace.id
            archivedAt = betaWorkspace.archivedAt
            compartmentId = betaWorkspace.compartmentId
            createdAt = betaWorkspace.createdAt
            dataResidency = betaWorkspace.dataResidency
            displayColor = betaWorkspace.displayColor
            externalKeyId = betaWorkspace.externalKeyId
            name = betaWorkspace.name
            tags = betaWorkspace.tags
            type = betaWorkspace.type
            additionalProperties = betaWorkspace.additionalProperties.toMutableMap()
        }

        /** ID of the Workspace. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * RFC 3339 datetime string indicating when the Workspace was archived, or `null` if the
         * Workspace is not archived.
         */
        fun archivedAt(archivedAt: OffsetDateTime?) = archivedAt(JsonField.ofNullable(archivedAt))

        /** Alias for calling [Builder.archivedAt] with `archivedAt.orElse(null)`. */
        fun archivedAt(archivedAt: Optional<OffsetDateTime>) = archivedAt(archivedAt.getOrNull())

        /**
         * Sets [Builder.archivedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.archivedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun archivedAt(archivedAt: JsonField<OffsetDateTime>) = apply {
            this.archivedAt = archivedAt
        }

        /**
         * Identifier for this Workspace's encryption compartment. When you configure a
         * customer-managed encryption key (CMEK) on AWS, reference this value in your KMS
         * key-policy condition so the key is scoped to this compartment. On GCP and Azure,
         * Anthropic enforces the compartment binding automatically; you do not need to reference
         * this value in your key configuration. See the CMEK integration guide for the required key
         * configuration; unless your organization is on Claude Platform on AWS, it includes a
         * separate value used during key validation. On Claude Platform on AWS there is no separate
         * validation value: the key is validated against this Workspace's own value when it is
         * attached, so if your key policy uses the compartment condition, add this value to it
         * before attaching the key.
         */
        fun compartmentId(compartmentId: String) = compartmentId(JsonField.of(compartmentId))

        /**
         * Sets [Builder.compartmentId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.compartmentId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun compartmentId(compartmentId: JsonField<String>) = apply {
            this.compartmentId = compartmentId
        }

        /** RFC 3339 datetime string indicating when the Workspace was created. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** Data residency configuration. */
        fun dataResidency(dataResidency: BetaDataResidency) =
            dataResidency(JsonField.of(dataResidency))

        /**
         * Sets [Builder.dataResidency] to an arbitrary JSON value.
         *
         * You should usually call [Builder.dataResidency] with a well-typed [BetaDataResidency]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun dataResidency(dataResidency: JsonField<BetaDataResidency>) = apply {
            this.dataResidency = dataResidency
        }

        /** Hex color code representing the Workspace in the Anthropic Console. */
        fun displayColor(displayColor: String) = displayColor(JsonField.of(displayColor))

        /**
         * Sets [Builder.displayColor] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayColor] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayColor(displayColor: JsonField<String>) = apply {
            this.displayColor = displayColor
        }

        /**
         * ID of the customer-managed encryption key (CMEK) configuration to use for this Workspace.
         * Setting this field requires CMEK to be enabled for your organization. When set, data
         * stored for this Workspace is encrypted with the referenced key. Create key configurations
         * with the External Keys API. On Claude Platform on AWS the value is the AWS KMS key ARN,
         * and the key must be a single-Region key in the same AWS account and Region as the
         * Workspace. On that platform the key is validated against this Workspace when it is
         * attached, so a key-policy problem is reported as an error on this request. This field is
         * write-once: once a key is attached to a Workspace it cannot be detached or replaced. To
         * rotate key material, rotate the underlying key on your cloud KMS; the `external_key_id`
         * stays the same.
         */
        fun externalKeyId(externalKeyId: String?) =
            externalKeyId(JsonField.ofNullable(externalKeyId))

        /** Alias for calling [Builder.externalKeyId] with `externalKeyId.orElse(null)`. */
        fun externalKeyId(externalKeyId: Optional<String>) =
            externalKeyId(externalKeyId.getOrNull())

        /**
         * Sets [Builder.externalKeyId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.externalKeyId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun externalKeyId(externalKeyId: JsonField<String>) = apply {
            this.externalKeyId = externalKeyId
        }

        /** Name of the Workspace. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** User-defined tags as string key-value pairs. Keys may not begin with `anthropic`. */
        fun tags(tags: Tags) = tags(JsonField.of(tags))

        /**
         * Sets [Builder.tags] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tags] with a well-typed [Tags] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun tags(tags: JsonField<Tags>) = apply { this.tags = tags }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("workspace")
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
         * Returns an immutable instance of [BetaWorkspace].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .compartmentId()
         * .createdAt()
         * .dataResidency()
         * .displayColor()
         * .externalKeyId()
         * .name()
         * .tags()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaWorkspace =
            BetaWorkspace(
                checkRequired("id", id),
                checkRequired("archivedAt", archivedAt),
                checkRequired("compartmentId", compartmentId),
                checkRequired("createdAt", createdAt),
                checkRequired("dataResidency", dataResidency),
                checkRequired("displayColor", displayColor),
                checkRequired("externalKeyId", externalKeyId),
                checkRequired("name", name),
                checkRequired("tags", tags),
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
    fun validate(): BetaWorkspace = apply {
        if (validated) {
            return@apply
        }

        id()
        archivedAt()
        compartmentId()
        createdAt()
        dataResidency().validate()
        displayColor()
        externalKeyId()
        name()
        tags().validate()
        _type().let {
            if (it != JsonValue.from("workspace")) {
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (archivedAt.asKnown().isPresent) 1 else 0) +
            (if (compartmentId.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (dataResidency.asKnown().getOrNull()?.validity() ?: 0) +
            (if (displayColor.asKnown().isPresent) 1 else 0) +
            (if (externalKeyId.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (tags.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("workspace")) 1 else 0 }

    /** User-defined tags as string key-value pairs. Keys may not begin with `anthropic`. */
    class Tags
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

            /** Returns a mutable builder for constructing an instance of [Tags]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Tags]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(tags: Tags) = apply {
                additionalProperties = tags.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Tags].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Tags = Tags(additionalProperties.toImmutable())
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
        fun validate(): Tags = apply {
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

            return other is Tags && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Tags{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWorkspace &&
            id == other.id &&
            archivedAt == other.archivedAt &&
            compartmentId == other.compartmentId &&
            createdAt == other.createdAt &&
            dataResidency == other.dataResidency &&
            displayColor == other.displayColor &&
            externalKeyId == other.externalKeyId &&
            name == other.name &&
            tags == other.tags &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            archivedAt,
            compartmentId,
            createdAt,
            dataResidency,
            displayColor,
            externalKeyId,
            name,
            tags,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaWorkspace{id=$id, archivedAt=$archivedAt, compartmentId=$compartmentId, createdAt=$createdAt, dataResidency=$dataResidency, displayColor=$displayColor, externalKeyId=$externalKeyId, name=$name, tags=$tags, type=$type, additionalProperties=$additionalProperties}"
}
