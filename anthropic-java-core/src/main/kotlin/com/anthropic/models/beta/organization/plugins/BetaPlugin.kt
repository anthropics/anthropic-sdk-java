package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
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

class BetaPlugin
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val components: JsonField<List<BetaPluginComponent>>,
    private val contentScan: JsonField<BetaPluginContentScan>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val createdBy: JsonField<CreatedBy>,
    private val description: JsonField<String>,
    private val displayName: JsonField<String>,
    private val latestVersionId: JsonField<String>,
    private val manifestVersion: JsonField<String>,
    private val marketplaceId: JsonField<String>,
    private val name: JsonField<String>,
    private val organizationInstallationPreference: JsonField<OrganizationInstallationPreference>,
    private val organizationInstallationPreferenceInherited: JsonField<Boolean>,
    private val owner: JsonField<Owner>,
    private val reach: JsonField<Reach>,
    private val servedVersionId: JsonField<String>,
    private val servedVersionPinned: JsonField<Boolean>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("components")
        @ExcludeMissing
        components: JsonField<List<BetaPluginComponent>> = JsonMissing.of(),
        @JsonProperty("content_scan")
        @ExcludeMissing
        contentScan: JsonField<BetaPluginContentScan> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_by")
        @ExcludeMissing
        createdBy: JsonField<CreatedBy> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("display_name")
        @ExcludeMissing
        displayName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("latest_version_id")
        @ExcludeMissing
        latestVersionId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("manifest_version")
        @ExcludeMissing
        manifestVersion: JsonField<String> = JsonMissing.of(),
        @JsonProperty("marketplace_id")
        @ExcludeMissing
        marketplaceId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("organization_installation_preference")
        @ExcludeMissing
        organizationInstallationPreference: JsonField<OrganizationInstallationPreference> =
            JsonMissing.of(),
        @JsonProperty("organization_installation_preference_inherited")
        @ExcludeMissing
        organizationInstallationPreferenceInherited: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("owner") @ExcludeMissing owner: JsonField<Owner> = JsonMissing.of(),
        @JsonProperty("reach") @ExcludeMissing reach: JsonField<Reach> = JsonMissing.of(),
        @JsonProperty("served_version_id")
        @ExcludeMissing
        servedVersionId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("served_version_pinned")
        @ExcludeMissing
        servedVersionPinned: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(
        id,
        components,
        contentScan,
        createdAt,
        createdBy,
        description,
        displayName,
        latestVersionId,
        manifestVersion,
        marketplaceId,
        name,
        organizationInstallationPreference,
        organizationInstallationPreferenceInherited,
        owner,
        reach,
        servedVersionId,
        servedVersionPinned,
        type,
        updatedAt,
        mutableMapOf(),
    )

    /**
     * The Plugin's ID.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * What the served version contains; null when not enumerated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun components(): Optional<List<BetaPluginComponent>> = components.getOptional("components")

    /**
     * The served version's content scan; null when it has not been scanned.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun contentScan(): Optional<BetaPluginContentScan> = contentScan.getOptional("content_scan")

    /**
     * RFC 3339.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Who created the Plugin; null when no creator is recorded.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun createdBy(): Optional<CreatedBy> = createdBy.getOptional("created_by")

    /**
     * The served version's description.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun description(): Optional<String> = description.getOptional("description")

    /**
     * The served version's display name.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayName(): Optional<String> = displayName.getOptional("display_name")

    /**
     * The newest version.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun latestVersionId(): String = latestVersionId.getRequired("latest_version_id")

    /**
     * The version string the served version's manifest declares.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun manifestVersion(): Optional<String> = manifestVersion.getOptional("manifest_version")

    /**
     * The ID of the plugin marketplace the Plugin lives in.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun marketplaceId(): String = marketplaceId.getRequired("marketplace_id")

    /**
     * Lowercase identifier, unique within its plugin marketplace. Fixed for an organization-owned
     * Plugin's lifetime; a member-owned Plugin's changes when its owner renames it in claude.ai,
     * while its `id` stays the same.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * Organization-owned Plugin: the organization-wide installation setting every member gets
     * unless an RBAC Group they belong to holds its own — the Plugin's own setting, or its plugin
     * marketplace's default. Null for a member-owned Plugin, which has shares instead. One of
     * `required`, `auto_install`, `available`, `not_available`; a value this API does not yet name
     * is returned as stored.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun organizationInstallationPreference(): Optional<OrganizationInstallationPreference> =
        organizationInstallationPreference.getOptional("organization_installation_preference")

    /**
     * Organization-owned Plugin: true while it has no organization-wide setting of its own and
     * `organization_installation_preference` is its plugin marketplace's default. Null for a
     * member-owned Plugin.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun organizationInstallationPreferenceInherited(): Optional<Boolean> =
        organizationInstallationPreferenceInherited.getOptional(
            "organization_installation_preference_inherited"
        )

    /**
     * Who owns the Plugin: the organization, or the member whose personal plugin marketplace it
     * lives in.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun owner(): Owner = owner.getRequired("owner")

    /**
     * How far the served version reaches: `remote` when it declares an MCP server or a CLI,
     * `privileged` when it declares a hook, monitor, language server or settings but nothing
     * remote, `contained` otherwise; null when not classifiable.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun reach(): Optional<Reach> = reach.getOptional("reach")

    /**
     * The version claude.ai serves to members.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun servedVersionId(): String = servedVersionId.getRequired("served_version_id")

    /**
     * False while the served version follows each new version; true once it has been pinned to one.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun servedVersionPinned(): Boolean = servedVersionPinned.getRequired("served_version_pinned")

    /**
     * Always `plugin`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("plugin")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * RFC 3339. Moves on a new version and on a served-version change; a change to the Plugin's
     * installation settings or shares does not move it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [components].
     *
     * Unlike [components], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("components")
    @ExcludeMissing
    fun _components(): JsonField<List<BetaPluginComponent>> = components

    /**
     * Returns the raw JSON value of [contentScan].
     *
     * Unlike [contentScan], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content_scan")
    @ExcludeMissing
    fun _contentScan(): JsonField<BetaPluginContentScan> = contentScan

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [createdBy].
     *
     * Unlike [createdBy], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_by") @ExcludeMissing fun _createdBy(): JsonField<CreatedBy> = createdBy

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    /**
     * Returns the raw JSON value of [displayName].
     *
     * Unlike [displayName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display_name")
    @ExcludeMissing
    fun _displayName(): JsonField<String> = displayName

    /**
     * Returns the raw JSON value of [latestVersionId].
     *
     * Unlike [latestVersionId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("latest_version_id")
    @ExcludeMissing
    fun _latestVersionId(): JsonField<String> = latestVersionId

    /**
     * Returns the raw JSON value of [manifestVersion].
     *
     * Unlike [manifestVersion], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("manifest_version")
    @ExcludeMissing
    fun _manifestVersion(): JsonField<String> = manifestVersion

    /**
     * Returns the raw JSON value of [marketplaceId].
     *
     * Unlike [marketplaceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("marketplace_id")
    @ExcludeMissing
    fun _marketplaceId(): JsonField<String> = marketplaceId

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [organizationInstallationPreference].
     *
     * Unlike [organizationInstallationPreference], this method doesn't throw if the JSON field has
     * an unexpected type.
     */
    @JsonProperty("organization_installation_preference")
    @ExcludeMissing
    fun _organizationInstallationPreference(): JsonField<OrganizationInstallationPreference> =
        organizationInstallationPreference

    /**
     * Returns the raw JSON value of [organizationInstallationPreferenceInherited].
     *
     * Unlike [organizationInstallationPreferenceInherited], this method doesn't throw if the JSON
     * field has an unexpected type.
     */
    @JsonProperty("organization_installation_preference_inherited")
    @ExcludeMissing
    fun _organizationInstallationPreferenceInherited(): JsonField<Boolean> =
        organizationInstallationPreferenceInherited

    /**
     * Returns the raw JSON value of [owner].
     *
     * Unlike [owner], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("owner") @ExcludeMissing fun _owner(): JsonField<Owner> = owner

    /**
     * Returns the raw JSON value of [reach].
     *
     * Unlike [reach], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("reach") @ExcludeMissing fun _reach(): JsonField<Reach> = reach

    /**
     * Returns the raw JSON value of [servedVersionId].
     *
     * Unlike [servedVersionId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("served_version_id")
    @ExcludeMissing
    fun _servedVersionId(): JsonField<String> = servedVersionId

    /**
     * Returns the raw JSON value of [servedVersionPinned].
     *
     * Unlike [servedVersionPinned], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("served_version_pinned")
    @ExcludeMissing
    fun _servedVersionPinned(): JsonField<Boolean> = servedVersionPinned

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
         * Returns a mutable builder for constructing an instance of [BetaPlugin].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .components()
         * .contentScan()
         * .createdAt()
         * .createdBy()
         * .description()
         * .displayName()
         * .latestVersionId()
         * .manifestVersion()
         * .marketplaceId()
         * .name()
         * .organizationInstallationPreference()
         * .organizationInstallationPreferenceInherited()
         * .owner()
         * .reach()
         * .servedVersionId()
         * .servedVersionPinned()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaPlugin]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var components: JsonField<MutableList<BetaPluginComponent>>? = null
        private var contentScan: JsonField<BetaPluginContentScan>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var createdBy: JsonField<CreatedBy>? = null
        private var description: JsonField<String>? = null
        private var displayName: JsonField<String>? = null
        private var latestVersionId: JsonField<String>? = null
        private var manifestVersion: JsonField<String>? = null
        private var marketplaceId: JsonField<String>? = null
        private var name: JsonField<String>? = null
        private var organizationInstallationPreference:
            JsonField<OrganizationInstallationPreference>? =
            null
        private var organizationInstallationPreferenceInherited: JsonField<Boolean>? = null
        private var owner: JsonField<Owner>? = null
        private var reach: JsonField<Reach>? = null
        private var servedVersionId: JsonField<String>? = null
        private var servedVersionPinned: JsonField<Boolean>? = null
        private var type: JsonValue = JsonValue.from("plugin")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaPlugin: BetaPlugin) = apply {
            id = betaPlugin.id
            components =
                betaPlugin.components.map { it.toMutableList() }.takeUnless { it.isMissing() }
            contentScan = betaPlugin.contentScan
            createdAt = betaPlugin.createdAt
            createdBy = betaPlugin.createdBy
            description = betaPlugin.description
            displayName = betaPlugin.displayName
            latestVersionId = betaPlugin.latestVersionId
            manifestVersion = betaPlugin.manifestVersion
            marketplaceId = betaPlugin.marketplaceId
            name = betaPlugin.name
            organizationInstallationPreference = betaPlugin.organizationInstallationPreference
            organizationInstallationPreferenceInherited =
                betaPlugin.organizationInstallationPreferenceInherited
            owner = betaPlugin.owner
            reach = betaPlugin.reach
            servedVersionId = betaPlugin.servedVersionId
            servedVersionPinned = betaPlugin.servedVersionPinned
            type = betaPlugin.type
            updatedAt = betaPlugin.updatedAt
            additionalProperties = betaPlugin.additionalProperties.toMutableMap()
        }

        /** The Plugin's ID. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** What the served version contains; null when not enumerated. */
        fun components(components: List<BetaPluginComponent>?) =
            components(JsonField.ofNullable(components))

        /** Alias for calling [Builder.components] with `components.orElse(null)`. */
        fun components(components: Optional<List<BetaPluginComponent>>) =
            components(components.getOrNull())

        /**
         * Sets [Builder.components] to an arbitrary JSON value.
         *
         * You should usually call [Builder.components] with a well-typed
         * `List<BetaPluginComponent>` value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun components(components: JsonField<List<BetaPluginComponent>>) = apply {
            this.components = components.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaPluginComponent] to [components].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addComponent(component: BetaPluginComponent) = apply {
            components =
                (components ?: JsonField.of(mutableListOf())).also {
                    checkKnown("components", it).add(component)
                }
        }

        /** The served version's content scan; null when it has not been scanned. */
        fun contentScan(contentScan: BetaPluginContentScan?) =
            contentScan(JsonField.ofNullable(contentScan))

        /** Alias for calling [Builder.contentScan] with `contentScan.orElse(null)`. */
        fun contentScan(contentScan: Optional<BetaPluginContentScan>) =
            contentScan(contentScan.getOrNull())

        /**
         * Sets [Builder.contentScan] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contentScan] with a well-typed [BetaPluginContentScan]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun contentScan(contentScan: JsonField<BetaPluginContentScan>) = apply {
            this.contentScan = contentScan
        }

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

        /** Who created the Plugin; null when no creator is recorded. */
        fun createdBy(createdBy: CreatedBy?) = createdBy(JsonField.ofNullable(createdBy))

        /** Alias for calling [Builder.createdBy] with `createdBy.orElse(null)`. */
        fun createdBy(createdBy: Optional<CreatedBy>) = createdBy(createdBy.getOrNull())

        /**
         * Sets [Builder.createdBy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdBy] with a well-typed [CreatedBy] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun createdBy(createdBy: JsonField<CreatedBy>) = apply { this.createdBy = createdBy }

        /** Alias for calling [createdBy] with `CreatedBy.ofUserActor(userActor)`. */
        fun createdBy(userActor: BetaPluginUserActor) = createdBy(CreatedBy.ofUserActor(userActor))

        /** Alias for calling [createdBy] with `CreatedBy.ofApiActor(apiActor)`. */
        fun createdBy(apiActor: BetaPluginApiActor) = createdBy(CreatedBy.ofApiActor(apiActor))

        /**
         * Alias for calling [createdBy] with the following:
         * ```java
         * BetaPluginApiActor.builder()
         *     .apiKeyId(apiKeyId)
         *     .build()
         * ```
         */
        fun apiActorCreatedBy(apiKeyId: String) =
            createdBy(BetaPluginApiActor.builder().apiKeyId(apiKeyId).build())

        /** The served version's description. */
        fun description(description: String?) = description(JsonField.ofNullable(description))

        /** Alias for calling [Builder.description] with `description.orElse(null)`. */
        fun description(description: Optional<String>) = description(description.getOrNull())

        /**
         * Sets [Builder.description] to an arbitrary JSON value.
         *
         * You should usually call [Builder.description] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun description(description: JsonField<String>) = apply { this.description = description }

        /** The served version's display name. */
        fun displayName(displayName: String?) = displayName(JsonField.ofNullable(displayName))

        /** Alias for calling [Builder.displayName] with `displayName.orElse(null)`. */
        fun displayName(displayName: Optional<String>) = displayName(displayName.getOrNull())

        /**
         * Sets [Builder.displayName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayName(displayName: JsonField<String>) = apply { this.displayName = displayName }

        /** The newest version. */
        fun latestVersionId(latestVersionId: String) =
            latestVersionId(JsonField.of(latestVersionId))

        /**
         * Sets [Builder.latestVersionId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.latestVersionId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun latestVersionId(latestVersionId: JsonField<String>) = apply {
            this.latestVersionId = latestVersionId
        }

        /** The version string the served version's manifest declares. */
        fun manifestVersion(manifestVersion: String?) =
            manifestVersion(JsonField.ofNullable(manifestVersion))

        /** Alias for calling [Builder.manifestVersion] with `manifestVersion.orElse(null)`. */
        fun manifestVersion(manifestVersion: Optional<String>) =
            manifestVersion(manifestVersion.getOrNull())

        /**
         * Sets [Builder.manifestVersion] to an arbitrary JSON value.
         *
         * You should usually call [Builder.manifestVersion] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun manifestVersion(manifestVersion: JsonField<String>) = apply {
            this.manifestVersion = manifestVersion
        }

        /** The ID of the plugin marketplace the Plugin lives in. */
        fun marketplaceId(marketplaceId: String) = marketplaceId(JsonField.of(marketplaceId))

        /**
         * Sets [Builder.marketplaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.marketplaceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun marketplaceId(marketplaceId: JsonField<String>) = apply {
            this.marketplaceId = marketplaceId
        }

        /**
         * Lowercase identifier, unique within its plugin marketplace. Fixed for an
         * organization-owned Plugin's lifetime; a member-owned Plugin's changes when its owner
         * renames it in claude.ai, while its `id` stays the same.
         */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /**
         * Organization-owned Plugin: the organization-wide installation setting every member gets
         * unless an RBAC Group they belong to holds its own — the Plugin's own setting, or its
         * plugin marketplace's default. Null for a member-owned Plugin, which has shares instead.
         * One of `required`, `auto_install`, `available`, `not_available`; a value this API does
         * not yet name is returned as stored.
         */
        fun organizationInstallationPreference(
            organizationInstallationPreference: OrganizationInstallationPreference?
        ) =
            organizationInstallationPreference(
                JsonField.ofNullable(organizationInstallationPreference)
            )

        /**
         * Alias for calling [Builder.organizationInstallationPreference] with
         * `organizationInstallationPreference.orElse(null)`.
         */
        fun organizationInstallationPreference(
            organizationInstallationPreference: Optional<OrganizationInstallationPreference>
        ) = organizationInstallationPreference(organizationInstallationPreference.getOrNull())

        /**
         * Sets [Builder.organizationInstallationPreference] to an arbitrary JSON value.
         *
         * You should usually call [Builder.organizationInstallationPreference] with a well-typed
         * [OrganizationInstallationPreference] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun organizationInstallationPreference(
            organizationInstallationPreference: JsonField<OrganizationInstallationPreference>
        ) = apply { this.organizationInstallationPreference = organizationInstallationPreference }

        /**
         * Organization-owned Plugin: true while it has no organization-wide setting of its own and
         * `organization_installation_preference` is its plugin marketplace's default. Null for a
         * member-owned Plugin.
         */
        fun organizationInstallationPreferenceInherited(
            organizationInstallationPreferenceInherited: Boolean?
        ) =
            organizationInstallationPreferenceInherited(
                JsonField.ofNullable(organizationInstallationPreferenceInherited)
            )

        /**
         * Alias for [Builder.organizationInstallationPreferenceInherited].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun organizationInstallationPreferenceInherited(
            organizationInstallationPreferenceInherited: Boolean
        ) =
            organizationInstallationPreferenceInherited(
                organizationInstallationPreferenceInherited as Boolean?
            )

        /**
         * Alias for calling [Builder.organizationInstallationPreferenceInherited] with
         * `organizationInstallationPreferenceInherited.orElse(null)`.
         */
        fun organizationInstallationPreferenceInherited(
            organizationInstallationPreferenceInherited: Optional<Boolean>
        ) =
            organizationInstallationPreferenceInherited(
                organizationInstallationPreferenceInherited.getOrNull()
            )

        /**
         * Sets [Builder.organizationInstallationPreferenceInherited] to an arbitrary JSON value.
         *
         * You should usually call [Builder.organizationInstallationPreferenceInherited] with a
         * well-typed [Boolean] value instead. This method is primarily for setting the field to an
         * undocumented or not yet supported value.
         */
        fun organizationInstallationPreferenceInherited(
            organizationInstallationPreferenceInherited: JsonField<Boolean>
        ) = apply {
            this.organizationInstallationPreferenceInherited =
                organizationInstallationPreferenceInherited
        }

        /**
         * Who owns the Plugin: the organization, or the member whose personal plugin marketplace it
         * lives in.
         */
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
         * How far the served version reaches: `remote` when it declares an MCP server or a CLI,
         * `privileged` when it declares a hook, monitor, language server or settings but nothing
         * remote, `contained` otherwise; null when not classifiable.
         */
        fun reach(reach: Reach?) = reach(JsonField.ofNullable(reach))

        /** Alias for calling [Builder.reach] with `reach.orElse(null)`. */
        fun reach(reach: Optional<Reach>) = reach(reach.getOrNull())

        /**
         * Sets [Builder.reach] to an arbitrary JSON value.
         *
         * You should usually call [Builder.reach] with a well-typed [Reach] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun reach(reach: JsonField<Reach>) = apply { this.reach = reach }

        /** The version claude.ai serves to members. */
        fun servedVersionId(servedVersionId: String) =
            servedVersionId(JsonField.of(servedVersionId))

        /**
         * Sets [Builder.servedVersionId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.servedVersionId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun servedVersionId(servedVersionId: JsonField<String>) = apply {
            this.servedVersionId = servedVersionId
        }

        /**
         * False while the served version follows each new version; true once it has been pinned to
         * one.
         */
        fun servedVersionPinned(servedVersionPinned: Boolean) =
            servedVersionPinned(JsonField.of(servedVersionPinned))

        /**
         * Sets [Builder.servedVersionPinned] to an arbitrary JSON value.
         *
         * You should usually call [Builder.servedVersionPinned] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun servedVersionPinned(servedVersionPinned: JsonField<Boolean>) = apply {
            this.servedVersionPinned = servedVersionPinned
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("plugin")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * RFC 3339. Moves on a new version and on a served-version change; a change to the Plugin's
         * installation settings or shares does not move it.
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
         * Returns an immutable instance of [BetaPlugin].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .components()
         * .contentScan()
         * .createdAt()
         * .createdBy()
         * .description()
         * .displayName()
         * .latestVersionId()
         * .manifestVersion()
         * .marketplaceId()
         * .name()
         * .organizationInstallationPreference()
         * .organizationInstallationPreferenceInherited()
         * .owner()
         * .reach()
         * .servedVersionId()
         * .servedVersionPinned()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaPlugin =
            BetaPlugin(
                checkRequired("id", id),
                checkRequired("components", components).map { it.toImmutable() },
                checkRequired("contentScan", contentScan),
                checkRequired("createdAt", createdAt),
                checkRequired("createdBy", createdBy),
                checkRequired("description", description),
                checkRequired("displayName", displayName),
                checkRequired("latestVersionId", latestVersionId),
                checkRequired("manifestVersion", manifestVersion),
                checkRequired("marketplaceId", marketplaceId),
                checkRequired("name", name),
                checkRequired(
                    "organizationInstallationPreference",
                    organizationInstallationPreference,
                ),
                checkRequired(
                    "organizationInstallationPreferenceInherited",
                    organizationInstallationPreferenceInherited,
                ),
                checkRequired("owner", owner),
                checkRequired("reach", reach),
                checkRequired("servedVersionId", servedVersionId),
                checkRequired("servedVersionPinned", servedVersionPinned),
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
    fun validate(): BetaPlugin = apply {
        if (validated) {
            return@apply
        }

        id()
        components().ifPresent { it.forEach { it.validate() } }
        contentScan().ifPresent { it.validate() }
        createdAt()
        createdBy().ifPresent { it.validate() }
        description()
        displayName()
        latestVersionId()
        manifestVersion()
        marketplaceId()
        name()
        organizationInstallationPreference().ifPresent { it.validate() }
        organizationInstallationPreferenceInherited()
        owner().validate()
        reach().ifPresent { it.validate() }
        servedVersionId()
        servedVersionPinned()
        _type().let {
            if (it != JsonValue.from("plugin")) {
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (components.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (contentScan.asKnown().getOrNull()?.validity() ?: 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (createdBy.asKnown().getOrNull()?.validity() ?: 0) +
            (if (description.asKnown().isPresent) 1 else 0) +
            (if (displayName.asKnown().isPresent) 1 else 0) +
            (if (latestVersionId.asKnown().isPresent) 1 else 0) +
            (if (manifestVersion.asKnown().isPresent) 1 else 0) +
            (if (marketplaceId.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (organizationInstallationPreference.asKnown().getOrNull()?.validity() ?: 0) +
            (if (organizationInstallationPreferenceInherited.asKnown().isPresent) 1 else 0) +
            (owner.asKnown().getOrNull()?.validity() ?: 0) +
            (reach.asKnown().getOrNull()?.validity() ?: 0) +
            (if (servedVersionId.asKnown().isPresent) 1 else 0) +
            (if (servedVersionPinned.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("plugin")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0)

    /** Who created the Plugin; null when no creator is recorded. */
    @JsonDeserialize(using = CreatedBy.Deserializer::class)
    @JsonSerialize(using = CreatedBy.Serializer::class)
    class CreatedBy
    private constructor(
        private val userActor: BetaPluginUserActor? = null,
        private val apiActor: BetaPluginApiActor? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitUserActor(userActor: BetaPluginUserActor): Type =
                        Type.USER_ACTOR

                    override fun visitApiActor(apiActor: BetaPluginApiActor): Type = Type.API_ACTOR

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun userActor(): Optional<BetaPluginUserActor> = Optional.ofNullable(userActor)

        fun apiActor(): Optional<BetaPluginApiActor> = Optional.ofNullable(apiActor)

        fun isUserActor(): Boolean = userActor != null

        fun isApiActor(): Boolean = apiActor != null

        fun asUserActor(): BetaPluginUserActor = userActor.getOrThrow("userActor")

        fun asApiActor(): BetaPluginApiActor = apiActor.getOrThrow("apiActor")

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
         * Optional<String> result = createdBy.accept(new CreatedBy.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitUserActor(BetaPluginUserActor userActor) {
         *         return Optional.of(userActor.toString());
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
                userActor != null -> visitor.visitUserActor(userActor)
                apiActor != null -> visitor.visitApiActor(apiActor)
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
        fun validate(): CreatedBy = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitUserActor(userActor: BetaPluginUserActor) {
                        userActor.validate()
                    }

                    override fun visitApiActor(apiActor: BetaPluginApiActor) {
                        apiActor.validate()
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
                    override fun visitUserActor(userActor: BetaPluginUserActor) =
                        userActor.validity()

                    override fun visitApiActor(apiActor: BetaPluginApiActor) = apiActor.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is CreatedBy && userActor == other.userActor && apiActor == other.apiActor
        }

        override fun hashCode(): Int = Objects.hash(userActor, apiActor)

        override fun toString(): String =
            when {
                userActor != null -> "CreatedBy{userActor=$userActor}"
                apiActor != null -> "CreatedBy{apiActor=$apiActor}"
                _json != null -> "CreatedBy{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid CreatedBy")
            }

        companion object {

            @JvmStatic
            fun ofUserActor(userActor: BetaPluginUserActor) = CreatedBy(userActor = userActor)

            @JvmStatic fun ofApiActor(apiActor: BetaPluginApiActor) = CreatedBy(apiActor = apiActor)

            /**
             * Returns an immutable instance of [CreatedBy] whose [ofApiActor] variant is built from
             * the given required [apiKeyId].
             */
            @JvmStatic
            fun ofApiActor(apiKeyId: String) = ofApiActor(BetaPluginApiActor.of(apiKeyId))
        }

        /**
         * An interface that defines how to map each variant of [CreatedBy] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitUserActor(userActor: BetaPluginUserActor): T

            fun visitApiActor(apiActor: BetaPluginApiActor): T

            /**
             * Maps an unknown variant of [CreatedBy] to a value of type [T].
             *
             * An instance of [CreatedBy] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown CreatedBy: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<CreatedBy>(CreatedBy::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): CreatedBy {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "user_actor" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaPluginUserActor>())?.let {
                            CreatedBy(userActor = it, _json = json)
                        } ?: CreatedBy(_json = json)
                    }
                    "api_actor" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaPluginApiActor>())?.let {
                            CreatedBy(apiActor = it, _json = json)
                        } ?: CreatedBy(_json = json)
                    }
                }

                return CreatedBy(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<CreatedBy>(CreatedBy::class) {

            override fun serialize(
                value: CreatedBy,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.userActor != null -> generator.writeObject(value.userActor)
                    value.apiActor != null -> generator.writeObject(value.apiActor)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid CreatedBy")
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

                @JvmField val USER_ACTOR = Type(JsonField.of("user_actor"))

                @JvmField val API_ACTOR = Type(JsonField.of("api_actor"))

                @JvmStatic
                fun of(value: String): Type =
                    // Intern known values so `==` works
                    when (value) {
                        "user_actor" -> USER_ACTOR
                        "api_actor" -> API_ACTOR
                        else -> Type(JsonField.of(value))
                    }

                @JsonCreator
                @JvmStatic
                fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                USER_ACTOR,
                API_ACTOR,
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
                USER_ACTOR,
                API_ACTOR,
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
                    USER_ACTOR -> Value.USER_ACTOR
                    API_ACTOR -> Value.API_ACTOR
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
                    USER_ACTOR -> Known.USER_ACTOR
                    API_ACTOR -> Known.API_ACTOR
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
     * Organization-owned Plugin: the organization-wide installation setting every member gets
     * unless an RBAC Group they belong to holds its own — the Plugin's own setting, or its plugin
     * marketplace's default. Null for a member-owned Plugin, which has shares instead. One of
     * `required`, `auto_install`, `available`, `not_available`; a value this API does not yet name
     * is returned as stored.
     */
    class OrganizationInstallationPreference
    private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField
            val AUTO_INSTALL = OrganizationInstallationPreference(JsonField.of("auto_install"))

            @JvmField val AVAILABLE = OrganizationInstallationPreference(JsonField.of("available"))

            @JvmField
            val NOT_AVAILABLE = OrganizationInstallationPreference(JsonField.of("not_available"))

            @JvmField val REQUIRED = OrganizationInstallationPreference(JsonField.of("required"))

            @JvmStatic
            fun of(value: String): OrganizationInstallationPreference =
                // Intern known values so `==` works
                when (value) {
                    "auto_install" -> AUTO_INSTALL
                    "available" -> AVAILABLE
                    "not_available" -> NOT_AVAILABLE
                    "required" -> REQUIRED
                    else -> OrganizationInstallationPreference(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): OrganizationInstallationPreference =
                value.asString().getOrNull()?.let { of(it) }
                    ?: OrganizationInstallationPreference(value)
        }

        /** An enum containing [OrganizationInstallationPreference]'s known values. */
        enum class Known {
            AUTO_INSTALL,
            AVAILABLE,
            NOT_AVAILABLE,
            REQUIRED,
        }

        /**
         * An enum containing [OrganizationInstallationPreference]'s known values, as well as an
         * [_UNKNOWN] member.
         *
         * An instance of [OrganizationInstallationPreference] can contain an unknown value in a
         * couple of cases:
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
             * An enum member indicating that [OrganizationInstallationPreference] was instantiated
             * with an unknown value.
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
                        "Unknown OrganizationInstallationPreference: $value"
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
        fun validate(): OrganizationInstallationPreference = apply {
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

            return other is OrganizationInstallationPreference && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /**
     * Who owns the Plugin: the organization, or the member whose personal plugin marketplace it
     * lives in.
     */
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
     * How far the served version reaches: `remote` when it declares an MCP server or a CLI,
     * `privileged` when it declares a hook, monitor, language server or settings but nothing
     * remote, `contained` otherwise; null when not classifiable.
     */
    class Reach private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val CONTAINED = Reach(JsonField.of("contained"))

            @JvmField val PRIVILEGED = Reach(JsonField.of("privileged"))

            @JvmField val REMOTE = Reach(JsonField.of("remote"))

            @JvmStatic
            fun of(value: String): Reach =
                // Intern known values so `==` works
                when (value) {
                    "contained" -> CONTAINED
                    "privileged" -> PRIVILEGED
                    "remote" -> REMOTE
                    else -> Reach(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Reach =
                value.asString().getOrNull()?.let { of(it) } ?: Reach(value)
        }

        /** An enum containing [Reach]'s known values. */
        enum class Known {
            CONTAINED,
            PRIVILEGED,
            REMOTE,
        }

        /**
         * An enum containing [Reach]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Reach] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            CONTAINED,
            PRIVILEGED,
            REMOTE,
            /** An enum member indicating that [Reach] was instantiated with an unknown value. */
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
                CONTAINED -> Value.CONTAINED
                PRIVILEGED -> Value.PRIVILEGED
                REMOTE -> Value.REMOTE
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
                CONTAINED -> Known.CONTAINED
                PRIVILEGED -> Known.PRIVILEGED
                REMOTE -> Known.REMOTE
                else -> throw AnthropicInvalidDataException("Unknown Reach: $value")
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
        fun validate(): Reach = apply {
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

            return other is Reach && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaPlugin &&
            id == other.id &&
            components == other.components &&
            contentScan == other.contentScan &&
            createdAt == other.createdAt &&
            createdBy == other.createdBy &&
            description == other.description &&
            displayName == other.displayName &&
            latestVersionId == other.latestVersionId &&
            manifestVersion == other.manifestVersion &&
            marketplaceId == other.marketplaceId &&
            name == other.name &&
            organizationInstallationPreference == other.organizationInstallationPreference &&
            organizationInstallationPreferenceInherited ==
                other.organizationInstallationPreferenceInherited &&
            owner == other.owner &&
            reach == other.reach &&
            servedVersionId == other.servedVersionId &&
            servedVersionPinned == other.servedVersionPinned &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            components,
            contentScan,
            createdAt,
            createdBy,
            description,
            displayName,
            latestVersionId,
            manifestVersion,
            marketplaceId,
            name,
            organizationInstallationPreference,
            organizationInstallationPreferenceInherited,
            owner,
            reach,
            servedVersionId,
            servedVersionPinned,
            type,
            updatedAt,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaPlugin{id=$id, components=$components, contentScan=$contentScan, createdAt=$createdAt, createdBy=$createdBy, description=$description, displayName=$displayName, latestVersionId=$latestVersionId, manifestVersion=$manifestVersion, marketplaceId=$marketplaceId, name=$name, organizationInstallationPreference=$organizationInstallationPreference, organizationInstallationPreferenceInherited=$organizationInstallationPreferenceInherited, owner=$owner, reach=$reach, servedVersionId=$servedVersionId, servedVersionPinned=$servedVersionPinned, type=$type, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
