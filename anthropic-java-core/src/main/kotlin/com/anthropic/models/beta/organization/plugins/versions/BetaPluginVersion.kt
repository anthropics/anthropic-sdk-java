package com.anthropic.models.beta.organization.plugins.versions

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
import com.anthropic.models.beta.organization.plugins.BetaPluginApiActor
import com.anthropic.models.beta.organization.plugins.BetaPluginComponent
import com.anthropic.models.beta.organization.plugins.BetaPluginContentScan
import com.anthropic.models.beta.organization.plugins.BetaPluginUserActor
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

class BetaPluginVersion
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val components: JsonField<List<BetaPluginComponent>>,
    private val contentScan: JsonField<BetaPluginContentScan>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val createdBy: JsonField<CreatedBy>,
    private val description: JsonField<String>,
    private val displayName: JsonField<String>,
    private val manifestVersion: JsonField<String>,
    private val pluginId: JsonField<String>,
    private val reach: JsonField<Reach>,
    private val releaseNotes: JsonField<String>,
    private val type: JsonValue,
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
        @JsonProperty("manifest_version")
        @ExcludeMissing
        manifestVersion: JsonField<String> = JsonMissing.of(),
        @JsonProperty("plugin_id") @ExcludeMissing pluginId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("reach") @ExcludeMissing reach: JsonField<Reach> = JsonMissing.of(),
        @JsonProperty("release_notes")
        @ExcludeMissing
        releaseNotes: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        id,
        components,
        contentScan,
        createdAt,
        createdBy,
        description,
        displayName,
        manifestVersion,
        pluginId,
        reach,
        releaseNotes,
        type,
        mutableMapOf(),
    )

    /**
     * The version's ID.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * What the version contains; null when not enumerated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun components(): Optional<List<BetaPluginComponent>> = components.getOptional("components")

    /**
     * This version's content scan; null when it has not been scanned.
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
     * Who uploaded this version; null when not recorded.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun createdBy(): Optional<CreatedBy> = createdBy.getOptional("created_by")

    /**
     * The manifest's description; null when it declares none.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun description(): Optional<String> = description.getOptional("description")

    /**
     * The manifest's display name; null when it declares none.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayName(): Optional<String> = displayName.getOptional("display_name")

    /**
     * The version string the manifest declares; null when it declares none.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun manifestVersion(): Optional<String> = manifestVersion.getOptional("manifest_version")

    /**
     * The Plugin's ID.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pluginId(): String = pluginId.getRequired("plugin_id")

    /**
     * How far the version reaches: `remote`, `privileged` or `contained`, as on the Plugin; null
     * when not classifiable.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun reach(): Optional<Reach> = reach.getOptional("reach")

    /**
     * As supplied with the upload; null when none were supplied.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun releaseNotes(): Optional<String> = releaseNotes.getOptional("release_notes")

    /**
     * Always `plugin_version`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("plugin_version")
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
     * Returns the raw JSON value of [manifestVersion].
     *
     * Unlike [manifestVersion], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("manifest_version")
    @ExcludeMissing
    fun _manifestVersion(): JsonField<String> = manifestVersion

    /**
     * Returns the raw JSON value of [pluginId].
     *
     * Unlike [pluginId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("plugin_id") @ExcludeMissing fun _pluginId(): JsonField<String> = pluginId

    /**
     * Returns the raw JSON value of [reach].
     *
     * Unlike [reach], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("reach") @ExcludeMissing fun _reach(): JsonField<Reach> = reach

    /**
     * Returns the raw JSON value of [releaseNotes].
     *
     * Unlike [releaseNotes], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("release_notes")
    @ExcludeMissing
    fun _releaseNotes(): JsonField<String> = releaseNotes

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
         * Returns a mutable builder for constructing an instance of [BetaPluginVersion].
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
         * .manifestVersion()
         * .pluginId()
         * .reach()
         * .releaseNotes()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaPluginVersion]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var components: JsonField<MutableList<BetaPluginComponent>>? = null
        private var contentScan: JsonField<BetaPluginContentScan>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var createdBy: JsonField<CreatedBy>? = null
        private var description: JsonField<String>? = null
        private var displayName: JsonField<String>? = null
        private var manifestVersion: JsonField<String>? = null
        private var pluginId: JsonField<String>? = null
        private var reach: JsonField<Reach>? = null
        private var releaseNotes: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("plugin_version")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaPluginVersion: BetaPluginVersion) = apply {
            id = betaPluginVersion.id
            components =
                betaPluginVersion.components
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            contentScan = betaPluginVersion.contentScan
            createdAt = betaPluginVersion.createdAt
            createdBy = betaPluginVersion.createdBy
            description = betaPluginVersion.description
            displayName = betaPluginVersion.displayName
            manifestVersion = betaPluginVersion.manifestVersion
            pluginId = betaPluginVersion.pluginId
            reach = betaPluginVersion.reach
            releaseNotes = betaPluginVersion.releaseNotes
            type = betaPluginVersion.type
            additionalProperties = betaPluginVersion.additionalProperties.toMutableMap()
        }

        /** The version's ID. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** What the version contains; null when not enumerated. */
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

        /** This version's content scan; null when it has not been scanned. */
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

        /** Who uploaded this version; null when not recorded. */
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

        /** The manifest's description; null when it declares none. */
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

        /** The manifest's display name; null when it declares none. */
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

        /** The version string the manifest declares; null when it declares none. */
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

        /** The Plugin's ID. */
        fun pluginId(pluginId: String) = pluginId(JsonField.of(pluginId))

        /**
         * Sets [Builder.pluginId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pluginId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun pluginId(pluginId: JsonField<String>) = apply { this.pluginId = pluginId }

        /**
         * How far the version reaches: `remote`, `privileged` or `contained`, as on the Plugin;
         * null when not classifiable.
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

        /** As supplied with the upload; null when none were supplied. */
        fun releaseNotes(releaseNotes: String?) = releaseNotes(JsonField.ofNullable(releaseNotes))

        /** Alias for calling [Builder.releaseNotes] with `releaseNotes.orElse(null)`. */
        fun releaseNotes(releaseNotes: Optional<String>) = releaseNotes(releaseNotes.getOrNull())

        /**
         * Sets [Builder.releaseNotes] to an arbitrary JSON value.
         *
         * You should usually call [Builder.releaseNotes] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun releaseNotes(releaseNotes: JsonField<String>) = apply {
            this.releaseNotes = releaseNotes
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("plugin_version")
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
         * Returns an immutable instance of [BetaPluginVersion].
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
         * .manifestVersion()
         * .pluginId()
         * .reach()
         * .releaseNotes()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaPluginVersion =
            BetaPluginVersion(
                checkRequired("id", id),
                checkRequired("components", components).map { it.toImmutable() },
                checkRequired("contentScan", contentScan),
                checkRequired("createdAt", createdAt),
                checkRequired("createdBy", createdBy),
                checkRequired("description", description),
                checkRequired("displayName", displayName),
                checkRequired("manifestVersion", manifestVersion),
                checkRequired("pluginId", pluginId),
                checkRequired("reach", reach),
                checkRequired("releaseNotes", releaseNotes),
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
    fun validate(): BetaPluginVersion = apply {
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
        manifestVersion()
        pluginId()
        reach().ifPresent { it.validate() }
        releaseNotes()
        _type().let {
            if (it != JsonValue.from("plugin_version")) {
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
            (components.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (contentScan.asKnown().getOrNull()?.validity() ?: 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (createdBy.asKnown().getOrNull()?.validity() ?: 0) +
            (if (description.asKnown().isPresent) 1 else 0) +
            (if (displayName.asKnown().isPresent) 1 else 0) +
            (if (manifestVersion.asKnown().isPresent) 1 else 0) +
            (if (pluginId.asKnown().isPresent) 1 else 0) +
            (reach.asKnown().getOrNull()?.validity() ?: 0) +
            (if (releaseNotes.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("plugin_version")) 1 else 0 }

    /** Who uploaded this version; null when not recorded. */
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
     * How far the version reaches: `remote`, `privileged` or `contained`, as on the Plugin; null
     * when not classifiable.
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

        return other is BetaPluginVersion &&
            id == other.id &&
            components == other.components &&
            contentScan == other.contentScan &&
            createdAt == other.createdAt &&
            createdBy == other.createdBy &&
            description == other.description &&
            displayName == other.displayName &&
            manifestVersion == other.manifestVersion &&
            pluginId == other.pluginId &&
            reach == other.reach &&
            releaseNotes == other.releaseNotes &&
            type == other.type &&
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
            manifestVersion,
            pluginId,
            reach,
            releaseNotes,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaPluginVersion{id=$id, components=$components, contentScan=$contentScan, createdAt=$createdAt, createdBy=$createdBy, description=$description, displayName=$displayName, manifestVersion=$manifestVersion, pluginId=$pluginId, reach=$reach, releaseNotes=$releaseNotes, type=$type, additionalProperties=$additionalProperties}"
}
