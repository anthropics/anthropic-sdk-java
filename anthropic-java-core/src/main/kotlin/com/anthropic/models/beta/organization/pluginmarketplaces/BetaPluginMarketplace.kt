package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.organization.plugins.BetaPluginOwnerOrganization
import com.anthropic.models.beta.organization.plugins.BetaPluginOwnerUser
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaPluginMarketplace
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val defaultInstallationPreference: JsonField<DefaultInstallationPreference>,
    private val lastSyncEndedAt: JsonField<OffsetDateTime>,
    private val lastSyncReadSha: JsonField<String>,
    private val name: JsonField<String>,
    private val owner: JsonField<Owner>,
    private val source: JsonField<Source>,
    private val syncStatus: JsonField<SyncStatus>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("default_installation_preference")
        @ExcludeMissing
        defaultInstallationPreference: JsonField<DefaultInstallationPreference> = JsonMissing.of(),
        @JsonProperty("last_sync_ended_at")
        @ExcludeMissing
        lastSyncEndedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("last_sync_read_sha")
        @ExcludeMissing
        lastSyncReadSha: JsonField<String> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("owner") @ExcludeMissing owner: JsonField<Owner> = JsonMissing.of(),
        @JsonProperty("source") @ExcludeMissing source: JsonField<Source> = JsonMissing.of(),
        @JsonProperty("sync_status")
        @ExcludeMissing
        syncStatus: JsonField<SyncStatus> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        id,
        createdAt,
        defaultInstallationPreference,
        lastSyncEndedAt,
        lastSyncReadSha,
        name,
        owner,
        source,
        syncStatus,
        type,
        mutableMapOf(),
    )

    /**
     * The plugin marketplace's ID, prefixed `marketplace_`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * RFC 3339.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Organization plugin marketplace: the organization-wide setting every Plugin in it with no
     * setting of its own gets. Null for a member's personal plugin marketplace. One of `required`,
     * `auto_install`, `available`, `not_available`; a value this API does not yet name is returned
     * as stored.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun defaultInstallationPreference(): Optional<DefaultInstallationPreference> =
        defaultInstallationPreference.getOptional("default_installation_preference")

    /**
     * RFC 3339. When the most recent synchronization attempt to finish did so, whatever its
     * outcome; for a repository plugin marketplace no synchronization has run on yet, when it was
     * created. Null for a plugin marketplace that is not synchronized from a repository.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun lastSyncEndedAt(): Optional<OffsetDateTime> =
        lastSyncEndedAt.getOptional("last_sync_ended_at")

    /**
     * The commit the last synchronization attempt that reached the repository read, whether or not
     * its content was then accepted (see `sync_status`); an attempt that ends `failed_auth` or
     * `failed_transient` leaves it unchanged. Null until an attempt has first read the repository,
     * and for a plugin marketplace that is not synchronized from a repository.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun lastSyncReadSha(): Optional<String> = lastSyncReadSha.getOptional("last_sync_read_sha")

    /**
     * Fixed for the plugin marketplace's lifetime.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * The organization, or the member whose personal plugin marketplace it is.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun owner(): Owner = owner.getRequired("owner")

    /**
     * Where the plugin marketplace's Plugins come from: `manual` when they are uploaded; `github`,
     * `gitlab` or `public_git` when they are synchronized from the Git repository the owner
     * connected, into which nothing can be uploaded; `directory` is Anthropic's own catalog, which
     * this API does not list. A value this API does not yet name is returned as stored.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): Source = source.getRequired("source")

    /**
     * Outcome of the plugin marketplace's most recent synchronization: one of `success`,
     * `in_progress`, `failed_content`, `failed_transient`, `failed_auth`, `failed_limits`; a value
     * this API does not yet name is returned as stored. Null until a synchronization is first
     * attempted — so always for a `manual` plugin marketplace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun syncStatus(): Optional<SyncStatus> = syncStatus.getOptional("sync_status")

    /**
     * Always `plugin_marketplace`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("plugin_marketplace")
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
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [defaultInstallationPreference].
     *
     * Unlike [defaultInstallationPreference], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("default_installation_preference")
    @ExcludeMissing
    fun _defaultInstallationPreference(): JsonField<DefaultInstallationPreference> =
        defaultInstallationPreference

    /**
     * Returns the raw JSON value of [lastSyncEndedAt].
     *
     * Unlike [lastSyncEndedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("last_sync_ended_at")
    @ExcludeMissing
    fun _lastSyncEndedAt(): JsonField<OffsetDateTime> = lastSyncEndedAt

    /**
     * Returns the raw JSON value of [lastSyncReadSha].
     *
     * Unlike [lastSyncReadSha], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("last_sync_read_sha")
    @ExcludeMissing
    fun _lastSyncReadSha(): JsonField<String> = lastSyncReadSha

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [owner].
     *
     * Unlike [owner], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("owner") @ExcludeMissing fun _owner(): JsonField<Owner> = owner

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

    /**
     * Returns the raw JSON value of [syncStatus].
     *
     * Unlike [syncStatus], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("sync_status")
    @ExcludeMissing
    fun _syncStatus(): JsonField<SyncStatus> = syncStatus

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
         * Returns a mutable builder for constructing an instance of [BetaPluginMarketplace].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .defaultInstallationPreference()
         * .lastSyncEndedAt()
         * .lastSyncReadSha()
         * .name()
         * .owner()
         * .source()
         * .syncStatus()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaPluginMarketplace]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var defaultInstallationPreference: JsonField<DefaultInstallationPreference>? = null
        private var lastSyncEndedAt: JsonField<OffsetDateTime>? = null
        private var lastSyncReadSha: JsonField<String>? = null
        private var name: JsonField<String>? = null
        private var owner: JsonField<Owner>? = null
        private var source: JsonField<Source>? = null
        private var syncStatus: JsonField<SyncStatus>? = null
        private var type: JsonValue = JsonValue.from("plugin_marketplace")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaPluginMarketplace: BetaPluginMarketplace) = apply {
            id = betaPluginMarketplace.id
            createdAt = betaPluginMarketplace.createdAt
            defaultInstallationPreference = betaPluginMarketplace.defaultInstallationPreference
            lastSyncEndedAt = betaPluginMarketplace.lastSyncEndedAt
            lastSyncReadSha = betaPluginMarketplace.lastSyncReadSha
            name = betaPluginMarketplace.name
            owner = betaPluginMarketplace.owner
            source = betaPluginMarketplace.source
            syncStatus = betaPluginMarketplace.syncStatus
            type = betaPluginMarketplace.type
            additionalProperties = betaPluginMarketplace.additionalProperties.toMutableMap()
        }

        /** The plugin marketplace's ID, prefixed `marketplace_`. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** RFC 3339. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /**
         * Organization plugin marketplace: the organization-wide setting every Plugin in it with no
         * setting of its own gets. Null for a member's personal plugin marketplace. One of
         * `required`, `auto_install`, `available`, `not_available`; a value this API does not yet
         * name is returned as stored.
         */
        fun defaultInstallationPreference(
            defaultInstallationPreference: DefaultInstallationPreference?
        ) = defaultInstallationPreference(JsonField.ofNullable(defaultInstallationPreference))

        /**
         * Alias for calling [Builder.defaultInstallationPreference] with
         * `defaultInstallationPreference.orElse(null)`.
         */
        fun defaultInstallationPreference(
            defaultInstallationPreference: Optional<DefaultInstallationPreference>
        ) = defaultInstallationPreference(defaultInstallationPreference.getOrNull())

        /**
         * Sets [Builder.defaultInstallationPreference] to an arbitrary JSON value.
         *
         * You should usually call [Builder.defaultInstallationPreference] with a well-typed
         * [DefaultInstallationPreference] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun defaultInstallationPreference(
            defaultInstallationPreference: JsonField<DefaultInstallationPreference>
        ) = apply { this.defaultInstallationPreference = defaultInstallationPreference }

        /**
         * RFC 3339. When the most recent synchronization attempt to finish did so, whatever its
         * outcome; for a repository plugin marketplace no synchronization has run on yet, when it
         * was created. Null for a plugin marketplace that is not synchronized from a repository.
         */
        fun lastSyncEndedAt(lastSyncEndedAt: OffsetDateTime?) =
            lastSyncEndedAt(JsonField.ofNullable(lastSyncEndedAt))

        /** Alias for calling [Builder.lastSyncEndedAt] with `lastSyncEndedAt.orElse(null)`. */
        fun lastSyncEndedAt(lastSyncEndedAt: Optional<OffsetDateTime>) =
            lastSyncEndedAt(lastSyncEndedAt.getOrNull())

        /**
         * Sets [Builder.lastSyncEndedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastSyncEndedAt] with a well-typed [OffsetDateTime]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun lastSyncEndedAt(lastSyncEndedAt: JsonField<OffsetDateTime>) = apply {
            this.lastSyncEndedAt = lastSyncEndedAt
        }

        /**
         * The commit the last synchronization attempt that reached the repository read, whether or
         * not its content was then accepted (see `sync_status`); an attempt that ends `failed_auth`
         * or `failed_transient` leaves it unchanged. Null until an attempt has first read the
         * repository, and for a plugin marketplace that is not synchronized from a repository.
         */
        fun lastSyncReadSha(lastSyncReadSha: String?) =
            lastSyncReadSha(JsonField.ofNullable(lastSyncReadSha))

        /** Alias for calling [Builder.lastSyncReadSha] with `lastSyncReadSha.orElse(null)`. */
        fun lastSyncReadSha(lastSyncReadSha: Optional<String>) =
            lastSyncReadSha(lastSyncReadSha.getOrNull())

        /**
         * Sets [Builder.lastSyncReadSha] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastSyncReadSha] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun lastSyncReadSha(lastSyncReadSha: JsonField<String>) = apply {
            this.lastSyncReadSha = lastSyncReadSha
        }

        /** Fixed for the plugin marketplace's lifetime. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** The organization, or the member whose personal plugin marketplace it is. */
        fun owner(owner: Owner) = owner(JsonField.of(owner))

        /**
         * Sets [Builder.owner] to an arbitrary JSON value.
         *
         * You should usually call [Builder.owner] with a well-typed [Owner] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun owner(owner: JsonField<Owner>) = apply { this.owner = owner }

        /** Alias for calling [owner] with `Owner.ofOrganization(organization)`. */
        fun owner(organization: BetaPluginOwnerOrganization) =
            owner(Owner.ofOrganization(organization))

        /** Alias for calling [owner] with `Owner.ofUser(user)`. */
        fun owner(user: BetaPluginOwnerUser) = owner(Owner.ofUser(user))

        /**
         * Alias for calling [owner] with the following:
         * ```java
         * BetaPluginOwnerUser.builder()
         *     .userId(userId)
         *     .build()
         * ```
         */
        fun userOwner(userId: String) = owner(BetaPluginOwnerUser.builder().userId(userId).build())

        /**
         * Where the plugin marketplace's Plugins come from: `manual` when they are uploaded;
         * `github`, `gitlab` or `public_git` when they are synchronized from the Git repository the
         * owner connected, into which nothing can be uploaded; `directory` is Anthropic's own
         * catalog, which this API does not list. A value this API does not yet name is returned as
         * stored.
         */
        fun source(source: Source) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [Source] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<Source>) = apply { this.source = source }

        /**
         * Outcome of the plugin marketplace's most recent synchronization: one of `success`,
         * `in_progress`, `failed_content`, `failed_transient`, `failed_auth`, `failed_limits`; a
         * value this API does not yet name is returned as stored. Null until a synchronization is
         * first attempted — so always for a `manual` plugin marketplace.
         */
        fun syncStatus(syncStatus: SyncStatus?) = syncStatus(JsonField.ofNullable(syncStatus))

        /** Alias for calling [Builder.syncStatus] with `syncStatus.orElse(null)`. */
        fun syncStatus(syncStatus: Optional<SyncStatus>) = syncStatus(syncStatus.getOrNull())

        /**
         * Sets [Builder.syncStatus] to an arbitrary JSON value.
         *
         * You should usually call [Builder.syncStatus] with a well-typed [SyncStatus] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun syncStatus(syncStatus: JsonField<SyncStatus>) = apply { this.syncStatus = syncStatus }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("plugin_marketplace")
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
         * Returns an immutable instance of [BetaPluginMarketplace].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .defaultInstallationPreference()
         * .lastSyncEndedAt()
         * .lastSyncReadSha()
         * .name()
         * .owner()
         * .source()
         * .syncStatus()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaPluginMarketplace =
            BetaPluginMarketplace(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("defaultInstallationPreference", defaultInstallationPreference),
                checkRequired("lastSyncEndedAt", lastSyncEndedAt),
                checkRequired("lastSyncReadSha", lastSyncReadSha),
                checkRequired("name", name),
                checkRequired("owner", owner),
                checkRequired("source", source),
                checkRequired("syncStatus", syncStatus),
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
    fun validate(): BetaPluginMarketplace = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        defaultInstallationPreference().ifPresent { it.validate() }
        lastSyncEndedAt()
        lastSyncReadSha()
        name()
        owner().validate()
        source().validate()
        syncStatus().ifPresent { it.validate() }
        _type().let {
            if (it != JsonValue.from("plugin_marketplace")) {
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
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (defaultInstallationPreference.asKnown().getOrNull()?.validity() ?: 0) +
            (if (lastSyncEndedAt.asKnown().isPresent) 1 else 0) +
            (if (lastSyncReadSha.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (owner.asKnown().getOrNull()?.validity() ?: 0) +
            (source.asKnown().getOrNull()?.validity() ?: 0) +
            (syncStatus.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("plugin_marketplace")) 1 else 0 }

    /**
     * Organization plugin marketplace: the organization-wide setting every Plugin in it with no
     * setting of its own gets. Null for a member's personal plugin marketplace. One of `required`,
     * `auto_install`, `available`, `not_available`; a value this API does not yet name is returned
     * as stored.
     */
    class DefaultInstallationPreference private constructor(private val value: JsonField<String>) :
        Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val AUTO_INSTALL = DefaultInstallationPreference(JsonField.of("auto_install"))

            @JvmField val AVAILABLE = DefaultInstallationPreference(JsonField.of("available"))

            @JvmField
            val NOT_AVAILABLE = DefaultInstallationPreference(JsonField.of("not_available"))

            @JvmField val REQUIRED = DefaultInstallationPreference(JsonField.of("required"))

            @JvmStatic
            fun of(value: String): DefaultInstallationPreference =
                // Intern known values so `==` works
                when (value) {
                    "auto_install" -> AUTO_INSTALL
                    "available" -> AVAILABLE
                    "not_available" -> NOT_AVAILABLE
                    "required" -> REQUIRED
                    else -> DefaultInstallationPreference(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): DefaultInstallationPreference =
                value.asString().getOrNull()?.let { of(it) } ?: DefaultInstallationPreference(value)
        }

        /** An enum containing [DefaultInstallationPreference]'s known values. */
        enum class Known {
            AUTO_INSTALL,
            AVAILABLE,
            NOT_AVAILABLE,
            REQUIRED,
        }

        /**
         * An enum containing [DefaultInstallationPreference]'s known values, as well as an
         * [_UNKNOWN] member.
         *
         * An instance of [DefaultInstallationPreference] can contain an unknown value in a couple
         * of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            AUTO_INSTALL,
            AVAILABLE,
            NOT_AVAILABLE,
            REQUIRED,
            /**
             * An enum member indicating that [DefaultInstallationPreference] was instantiated with
             * an unknown value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                AUTO_INSTALL -> Value.AUTO_INSTALL
                AVAILABLE -> Value.AVAILABLE
                NOT_AVAILABLE -> Value.NOT_AVAILABLE
                REQUIRED -> Value.REQUIRED
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                AUTO_INSTALL -> Known.AUTO_INSTALL
                AVAILABLE -> Known.AVAILABLE
                NOT_AVAILABLE -> Known.NOT_AVAILABLE
                REQUIRED -> Known.REQUIRED
                else ->
                    throw AnthropicInvalidDataException(
                        "Unknown DefaultInstallationPreference: $value"
                    )
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
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
        fun validate(): DefaultInstallationPreference = apply {
            if (validated) {
                return@apply
            }

            known()
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
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is DefaultInstallationPreference && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** The organization, or the member whose personal plugin marketplace it is. */
    @JsonDeserialize(using = Owner.Deserializer::class)
    @JsonSerialize(using = Owner.Serializer::class)
    class Owner
    private constructor(
        private val organization: BetaPluginOwnerOrganization? = null,
        private val user: BetaPluginOwnerUser? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitOrganization(
                        organization: BetaPluginOwnerOrganization
                    ): Type = Type.ORGANIZATION

                    override fun visitUser(user: BetaPluginOwnerUser): Type = Type.USER

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun organization(): Optional<BetaPluginOwnerOrganization> =
            Optional.ofNullable(organization)

        fun user(): Optional<BetaPluginOwnerUser> = Optional.ofNullable(user)

        fun isOrganization(): Boolean = organization != null

        fun isUser(): Boolean = user != null

        fun asOrganization(): BetaPluginOwnerOrganization = organization.getOrThrow("organization")

        fun asUser(): BetaPluginOwnerUser = user.getOrThrow("user")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = owner.accept(new Owner.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitOrganization(BetaPluginOwnerOrganization organization) {
         *         return Optional.of(organization.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                organization != null -> visitor.visitOrganization(organization)
                user != null -> visitor.visitUser(user)
                else -> visitor.unknown(_json)
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
        fun validate(): Owner = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitOrganization(organization: BetaPluginOwnerOrganization) {
                        organization.validate()
                    }

                    override fun visitUser(user: BetaPluginOwnerUser) {
                        user.validate()
                    }
                }
            )
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
            accept(
                object : Visitor<Int> {
                    override fun visitOrganization(organization: BetaPluginOwnerOrganization) =
                        organization.validity()

                    override fun visitUser(user: BetaPluginOwnerUser) = user.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Owner && organization == other.organization && user == other.user
        }

        override fun hashCode(): Int = Objects.hash(organization, user)

        override fun toString(): String =
            when {
                organization != null -> "Owner{organization=$organization}"
                user != null -> "Owner{user=$user}"
                _json != null -> "Owner{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Owner")
            }

        companion object {

            @JvmStatic
            fun ofOrganization(organization: BetaPluginOwnerOrganization) =
                Owner(organization = organization)

            @JvmStatic fun ofUser(user: BetaPluginOwnerUser) = Owner(user = user)

            /**
             * Returns an immutable instance of [Owner] whose [ofUser] variant is built from the
             * given required [userId].
             */
            @JvmStatic fun ofUser(userId: String) = ofUser(BetaPluginOwnerUser.of(userId))
        }

        /** An interface that defines how to map each variant of [Owner] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitOrganization(organization: BetaPluginOwnerOrganization): T

            fun visitUser(user: BetaPluginOwnerUser): T

            /**
             * Maps an unknown variant of [Owner] to a value of type [T].
             *
             * An instance of [Owner] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Owner: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Owner>(Owner::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Owner {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "organization" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaPluginOwnerOrganization>())
                            ?.let { Owner(organization = it, _json = json) } ?: Owner(_json = json)
                    }
                    "user" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaPluginOwnerUser>())?.let {
                            Owner(user = it, _json = json)
                        } ?: Owner(_json = json)
                    }
                }

                return Owner(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Owner>(Owner::class) {

            override fun serialize(
                value: Owner,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.organization != null -> generator.writeObject(value.organization)
                    value.user != null -> generator.writeObject(value.user)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Owner")
                }
            }
        }

        class Type private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val ORGANIZATION = Type(JsonField.of("organization"))

                @JvmField val USER = Type(JsonField.of("user"))

                @JvmStatic
                fun of(value: String): Type =
                    // Intern known values so `==` works
                    when (value) {
                        "organization" -> ORGANIZATION
                        "user" -> USER
                        else -> Type(JsonField.of(value))
                    }

                @JsonCreator
                @JvmStatic
                fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                ORGANIZATION,
                USER,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                ORGANIZATION,
                USER,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    ORGANIZATION -> Value.ORGANIZATION
                    USER -> Value.USER
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    ORGANIZATION -> Known.ORGANIZATION
                    USER -> Known.USER
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
                if (validated) {
                    return@apply
                }

                known()
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
            @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    /**
     * Where the plugin marketplace's Plugins come from: `manual` when they are uploaded; `github`,
     * `gitlab` or `public_git` when they are synchronized from the Git repository the owner
     * connected, into which nothing can be uploaded; `directory` is Anthropic's own catalog, which
     * this API does not list. A value this API does not yet name is returned as stored.
     */
    class Source private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val DIRECTORY = Source(JsonField.of("directory"))

            @JvmField val GITHUB = Source(JsonField.of("github"))

            @JvmField val GITLAB = Source(JsonField.of("gitlab"))

            @JvmField val MANUAL = Source(JsonField.of("manual"))

            @JvmField val PUBLIC_GIT = Source(JsonField.of("public_git"))

            @JvmStatic
            fun of(value: String): Source =
                // Intern known values so `==` works
                when (value) {
                    "directory" -> DIRECTORY
                    "github" -> GITHUB
                    "gitlab" -> GITLAB
                    "manual" -> MANUAL
                    "public_git" -> PUBLIC_GIT
                    else -> Source(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Source =
                value.asString().getOrNull()?.let { of(it) } ?: Source(value)
        }

        /** An enum containing [Source]'s known values. */
        enum class Known {
            DIRECTORY,
            GITHUB,
            GITLAB,
            MANUAL,
            PUBLIC_GIT,
        }

        /**
         * An enum containing [Source]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Source] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            DIRECTORY,
            GITHUB,
            GITLAB,
            MANUAL,
            PUBLIC_GIT,
            /** An enum member indicating that [Source] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                DIRECTORY -> Value.DIRECTORY
                GITHUB -> Value.GITHUB
                GITLAB -> Value.GITLAB
                MANUAL -> Value.MANUAL
                PUBLIC_GIT -> Value.PUBLIC_GIT
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                DIRECTORY -> Known.DIRECTORY
                GITHUB -> Known.GITHUB
                GITLAB -> Known.GITLAB
                MANUAL -> Known.MANUAL
                PUBLIC_GIT -> Known.PUBLIC_GIT
                else -> throw AnthropicInvalidDataException("Unknown Source: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
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
        fun validate(): Source = apply {
            if (validated) {
                return@apply
            }

            known()
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
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Source && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /**
     * Outcome of the plugin marketplace's most recent synchronization: one of `success`,
     * `in_progress`, `failed_content`, `failed_transient`, `failed_auth`, `failed_limits`; a value
     * this API does not yet name is returned as stored. Null until a synchronization is first
     * attempted — so always for a `manual` plugin marketplace.
     */
    class SyncStatus private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val FAILED_AUTH = SyncStatus(JsonField.of("failed_auth"))

            @JvmField val FAILED_CONTENT = SyncStatus(JsonField.of("failed_content"))

            @JvmField val FAILED_LIMITS = SyncStatus(JsonField.of("failed_limits"))

            @JvmField val FAILED_TRANSIENT = SyncStatus(JsonField.of("failed_transient"))

            @JvmField val IN_PROGRESS = SyncStatus(JsonField.of("in_progress"))

            @JvmField val SUCCESS = SyncStatus(JsonField.of("success"))

            @JvmStatic
            fun of(value: String): SyncStatus =
                // Intern known values so `==` works
                when (value) {
                    "failed_auth" -> FAILED_AUTH
                    "failed_content" -> FAILED_CONTENT
                    "failed_limits" -> FAILED_LIMITS
                    "failed_transient" -> FAILED_TRANSIENT
                    "in_progress" -> IN_PROGRESS
                    "success" -> SUCCESS
                    else -> SyncStatus(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): SyncStatus =
                value.asString().getOrNull()?.let { of(it) } ?: SyncStatus(value)
        }

        /** An enum containing [SyncStatus]'s known values. */
        enum class Known {
            FAILED_AUTH,
            FAILED_CONTENT,
            FAILED_LIMITS,
            FAILED_TRANSIENT,
            IN_PROGRESS,
            SUCCESS,
        }

        /**
         * An enum containing [SyncStatus]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [SyncStatus] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            FAILED_AUTH,
            FAILED_CONTENT,
            FAILED_LIMITS,
            FAILED_TRANSIENT,
            IN_PROGRESS,
            SUCCESS,
            /**
             * An enum member indicating that [SyncStatus] was instantiated with an unknown value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                FAILED_AUTH -> Value.FAILED_AUTH
                FAILED_CONTENT -> Value.FAILED_CONTENT
                FAILED_LIMITS -> Value.FAILED_LIMITS
                FAILED_TRANSIENT -> Value.FAILED_TRANSIENT
                IN_PROGRESS -> Value.IN_PROGRESS
                SUCCESS -> Value.SUCCESS
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                FAILED_AUTH -> Known.FAILED_AUTH
                FAILED_CONTENT -> Known.FAILED_CONTENT
                FAILED_LIMITS -> Known.FAILED_LIMITS
                FAILED_TRANSIENT -> Known.FAILED_TRANSIENT
                IN_PROGRESS -> Known.IN_PROGRESS
                SUCCESS -> Known.SUCCESS
                else -> throw AnthropicInvalidDataException("Unknown SyncStatus: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
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
        fun validate(): SyncStatus = apply {
            if (validated) {
                return@apply
            }

            known()
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
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is SyncStatus && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaPluginMarketplace &&
            id == other.id &&
            createdAt == other.createdAt &&
            defaultInstallationPreference == other.defaultInstallationPreference &&
            lastSyncEndedAt == other.lastSyncEndedAt &&
            lastSyncReadSha == other.lastSyncReadSha &&
            name == other.name &&
            owner == other.owner &&
            source == other.source &&
            syncStatus == other.syncStatus &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            defaultInstallationPreference,
            lastSyncEndedAt,
            lastSyncReadSha,
            name,
            owner,
            source,
            syncStatus,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaPluginMarketplace{id=$id, createdAt=$createdAt, defaultInstallationPreference=$defaultInstallationPreference, lastSyncEndedAt=$lastSyncEndedAt, lastSyncReadSha=$lastSyncReadSha, name=$name, owner=$owner, source=$source, syncStatus=$syncStatus, type=$type, additionalProperties=$additionalProperties}"
}
