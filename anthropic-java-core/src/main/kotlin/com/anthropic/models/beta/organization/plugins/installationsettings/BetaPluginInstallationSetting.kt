package com.anthropic.models.beta.organization.plugins.installationsettings

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
import com.anthropic.models.beta.organization.plugins.BetaPluginTargetOrganization
import com.anthropic.models.beta.organization.plugins.BetaPluginTargetOrganizationMember
import com.anthropic.models.beta.organization.plugins.BetaPluginTargetRbacGroup
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

/**
 * The installation setting an organization-owned Plugin holds for one target. It has no ID of its
 * own: it is addressed by the Plugin's ID and the target.
 */
class BetaPluginInstallationSetting
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val createdAt: JsonField<OffsetDateTime>,
    private val installationPreference: JsonField<InstallationPreference>,
    private val pluginId: JsonField<String>,
    private val target: JsonField<Target>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("installation_preference")
        @ExcludeMissing
        installationPreference: JsonField<InstallationPreference> = JsonMissing.of(),
        @JsonProperty("plugin_id") @ExcludeMissing pluginId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("target") @ExcludeMissing target: JsonField<Target> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(createdAt, installationPreference, pluginId, target, type, updatedAt, mutableMapOf())

    /**
     * When the target was first given a setting for this Plugin.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * The setting the target holds for this Plugin. One of `required`, `auto_install`, `available`,
     * `not_available`; a value this API does not yet name is returned as stored.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun installationPreference(): InstallationPreference =
        installationPreference.getRequired("installation_preference")

    /**
     * The Plugin's ID.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pluginId(): String = pluginId.getRequired("plugin_id")

    /**
     * Whose setting this is: `organization` (the Plugin's own organization-wide setting) or
     * `rbac_group` (one RBAC Group's own setting); `organization_member` does not occur here.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun target(): Target = target.getRequired("target")

    /**
     * Always `plugin_installation_setting`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("plugin_installation_setting")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * When its setting last changed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [installationPreference].
     *
     * Unlike [installationPreference], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("installation_preference")
    @ExcludeMissing
    fun _installationPreference(): JsonField<InstallationPreference> = installationPreference

    /**
     * Returns the raw JSON value of [pluginId].
     *
     * Unlike [pluginId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("plugin_id") @ExcludeMissing fun _pluginId(): JsonField<String> = pluginId

    /**
     * Returns the raw JSON value of [target].
     *
     * Unlike [target], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<Target> = target

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
         * [BetaPluginInstallationSetting].
         *
         * The following fields are required:
         * ```java
         * .createdAt()
         * .installationPreference()
         * .pluginId()
         * .target()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaPluginInstallationSetting]. */
    class Builder internal constructor() {

        private var createdAt: JsonField<OffsetDateTime>? = null
        private var installationPreference: JsonField<InstallationPreference>? = null
        private var pluginId: JsonField<String>? = null
        private var target: JsonField<Target>? = null
        private var type: JsonValue = JsonValue.from("plugin_installation_setting")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaPluginInstallationSetting: BetaPluginInstallationSetting) = apply {
            createdAt = betaPluginInstallationSetting.createdAt
            installationPreference = betaPluginInstallationSetting.installationPreference
            pluginId = betaPluginInstallationSetting.pluginId
            target = betaPluginInstallationSetting.target
            type = betaPluginInstallationSetting.type
            updatedAt = betaPluginInstallationSetting.updatedAt
            additionalProperties = betaPluginInstallationSetting.additionalProperties.toMutableMap()
        }

        /** When the target was first given a setting for this Plugin. */
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
         * The setting the target holds for this Plugin. One of `required`, `auto_install`,
         * `available`, `not_available`; a value this API does not yet name is returned as stored.
         */
        fun installationPreference(installationPreference: InstallationPreference) =
            installationPreference(JsonField.of(installationPreference))

        /**
         * Sets [Builder.installationPreference] to an arbitrary JSON value.
         *
         * You should usually call [Builder.installationPreference] with a well-typed
         * [InstallationPreference] value instead. This method is primarily for setting the field to
         * an undocumented or not yet supported value.
         */
        fun installationPreference(installationPreference: JsonField<InstallationPreference>) =
            apply {
                this.installationPreference = installationPreference
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
         * Whose setting this is: `organization` (the Plugin's own organization-wide setting) or
         * `rbac_group` (one RBAC Group's own setting); `organization_member` does not occur here.
         */
        fun target(target: Target) = target(JsonField.of(target))

        /**
         * Sets [Builder.target] to an arbitrary JSON value.
         *
         * You should usually call [Builder.target] with a well-typed [Target] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun target(target: JsonField<Target>) = apply { this.target = target }

        /** Alias for calling [target] with `Target.ofOrganization(organization)`. */
        fun target(organization: BetaPluginTargetOrganization) =
            target(Target.ofOrganization(organization))

        /** Alias for calling [target] with `Target.ofRbacGroup(rbacGroup)`. */
        fun target(rbacGroup: BetaPluginTargetRbacGroup) = target(Target.ofRbacGroup(rbacGroup))

        /**
         * Alias for calling [target] with the following:
         * ```java
         * BetaPluginTargetRbacGroup.builder()
         *     .rbacGroupId(rbacGroupId)
         *     .build()
         * ```
         */
        fun rbacGroupTarget(rbacGroupId: String) =
            target(BetaPluginTargetRbacGroup.builder().rbacGroupId(rbacGroupId).build())

        /** Alias for calling [target] with `Target.ofOrganizationMember(organizationMember)`. */
        fun target(organizationMember: BetaPluginTargetOrganizationMember) =
            target(Target.ofOrganizationMember(organizationMember))

        /**
         * Alias for calling [target] with the following:
         * ```java
         * BetaPluginTargetOrganizationMember.builder()
         *     .userId(userId)
         *     .build()
         * ```
         */
        fun organizationMemberTarget(userId: String) =
            target(BetaPluginTargetOrganizationMember.builder().userId(userId).build())

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("plugin_installation_setting")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** When its setting last changed. */
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
         * Returns an immutable instance of [BetaPluginInstallationSetting].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .createdAt()
         * .installationPreference()
         * .pluginId()
         * .target()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaPluginInstallationSetting =
            BetaPluginInstallationSetting(
                checkRequired("createdAt", createdAt),
                checkRequired("installationPreference", installationPreference),
                checkRequired("pluginId", pluginId),
                checkRequired("target", target),
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
    fun validate(): BetaPluginInstallationSetting = apply {
        if (validated) {
            return@apply
        }

        createdAt()
        installationPreference().validate()
        pluginId()
        target().validate()
        _type().let {
            if (it != JsonValue.from("plugin_installation_setting")) {
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
        (if (createdAt.asKnown().isPresent) 1 else 0) +
            (installationPreference.asKnown().getOrNull()?.validity() ?: 0) +
            (if (pluginId.asKnown().isPresent) 1 else 0) +
            (target.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("plugin_installation_setting")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0)

    /**
     * The setting the target holds for this Plugin. One of `required`, `auto_install`, `available`,
     * `not_available`; a value this API does not yet name is returned as stored.
     */
    class InstallationPreference private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val AUTO_INSTALL = InstallationPreference(JsonField.of("auto_install"))

            @JvmField val AVAILABLE = InstallationPreference(JsonField.of("available"))

            @JvmField val NOT_AVAILABLE = InstallationPreference(JsonField.of("not_available"))

            @JvmField val REQUIRED = InstallationPreference(JsonField.of("required"))

            @JvmStatic
            fun of(value: String): InstallationPreference =
                // Intern known values so `==` works
                when (value) {
                    "auto_install" -> AUTO_INSTALL
                    "available" -> AVAILABLE
                    "not_available" -> NOT_AVAILABLE
                    "required" -> REQUIRED
                    else -> InstallationPreference(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): InstallationPreference =
                value.asString().getOrNull()?.let { of(it) } ?: InstallationPreference(value)
        }

        /** An enum containing [InstallationPreference]'s known values. */
        enum class Known {
            AUTO_INSTALL,
            AVAILABLE,
            NOT_AVAILABLE,
            REQUIRED,
        }

        /**
         * An enum containing [InstallationPreference]'s known values, as well as an [_UNKNOWN]
         * member.
         *
         * An instance of [InstallationPreference] can contain an unknown value in a couple of
         * cases:
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
             * An enum member indicating that [InstallationPreference] was instantiated with an
             * unknown value.
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
                    throw AnthropicInvalidDataException("Unknown InstallationPreference: $value")
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
        fun validate(): InstallationPreference = apply {
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

            return other is InstallationPreference && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /**
     * Whose setting this is: `organization` (the Plugin's own organization-wide setting) or
     * `rbac_group` (one RBAC Group's own setting); `organization_member` does not occur here.
     */
    @JsonDeserialize(using = Target.Deserializer::class)
    @JsonSerialize(using = Target.Serializer::class)
    class Target
    private constructor(
        private val organization: BetaPluginTargetOrganization? = null,
        private val rbacGroup: BetaPluginTargetRbacGroup? = null,
        private val organizationMember: BetaPluginTargetOrganizationMember? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            when {
                organization != null -> Type.ORGANIZATION
                rbacGroup != null -> Type.RBAC_GROUP
                organizationMember != null -> Type.ORGANIZATION_MEMBER
                else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }

        fun organization(): Optional<BetaPluginTargetOrganization> =
            Optional.ofNullable(organization)

        fun rbacGroup(): Optional<BetaPluginTargetRbacGroup> = Optional.ofNullable(rbacGroup)

        fun organizationMember(): Optional<BetaPluginTargetOrganizationMember> =
            Optional.ofNullable(organizationMember)

        fun isOrganization(): Boolean = organization != null

        fun isRbacGroup(): Boolean = rbacGroup != null

        fun isOrganizationMember(): Boolean = organizationMember != null

        fun asOrganization(): BetaPluginTargetOrganization = organization.getOrThrow("organization")

        fun asRbacGroup(): BetaPluginTargetRbacGroup = rbacGroup.getOrThrow("rbacGroup")

        fun asOrganizationMember(): BetaPluginTargetOrganizationMember =
            organizationMember.getOrThrow("organizationMember")

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
         * Optional<String> result = target.accept(new Target.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitOrganization(BetaPluginTargetOrganization organization) {
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
                rbacGroup != null -> visitor.visitRbacGroup(rbacGroup)
                organizationMember != null -> visitor.visitOrganizationMember(organizationMember)
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
        fun validate(): Target = apply {
            if (validated) {
                return@apply
            }

            when {
                organization != null -> organization.validate()
                rbacGroup != null -> rbacGroup.validate()
                organizationMember != null -> organizationMember.validate()
                else -> throw AnthropicInvalidDataException("Unknown Target: $_json")
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
            when {
                organization != null -> organization.validity()
                rbacGroup != null -> rbacGroup.validity()
                organizationMember != null -> organizationMember.validity()
                else -> 0
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Target &&
                organization == other.organization &&
                rbacGroup == other.rbacGroup &&
                organizationMember == other.organizationMember
        }

        override fun hashCode(): Int = Objects.hash(organization, rbacGroup, organizationMember)

        override fun toString(): String =
            when {
                organization != null -> "Target{organization=$organization}"
                rbacGroup != null -> "Target{rbacGroup=$rbacGroup}"
                organizationMember != null -> "Target{organizationMember=$organizationMember}"
                _json != null -> "Target{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Target")
            }

        companion object {

            @JvmStatic
            fun ofOrganization(organization: BetaPluginTargetOrganization) =
                Target(organization = organization)

            @JvmStatic
            fun ofRbacGroup(rbacGroup: BetaPluginTargetRbacGroup) = Target(rbacGroup = rbacGroup)

            /**
             * Returns an immutable instance of [Target] whose [ofRbacGroup] variant is built from
             * the given required [rbacGroupId].
             */
            @JvmStatic
            fun ofRbacGroup(rbacGroupId: String) =
                ofRbacGroup(BetaPluginTargetRbacGroup.of(rbacGroupId))

            @JvmStatic
            fun ofOrganizationMember(organizationMember: BetaPluginTargetOrganizationMember) =
                Target(organizationMember = organizationMember)

            /**
             * Returns an immutable instance of [Target] whose [ofOrganizationMember] variant is
             * built from the given required [userId].
             */
            @JvmStatic
            fun ofOrganizationMember(userId: String) =
                ofOrganizationMember(BetaPluginTargetOrganizationMember.of(userId))
        }

        /** An interface that defines how to map each variant of [Target] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitOrganization(organization: BetaPluginTargetOrganization): T

            fun visitRbacGroup(rbacGroup: BetaPluginTargetRbacGroup): T

            fun visitOrganizationMember(organizationMember: BetaPluginTargetOrganizationMember): T

            /**
             * Maps an unknown variant of [Target] to a value of type [T].
             *
             * An instance of [Target] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Target: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Target>(Target::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Target {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "organization" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaPluginTargetOrganization>())
                            ?.let { Target(organization = it, _json = json) }
                            ?: Target(_json = json)
                    }
                    "rbac_group" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaPluginTargetRbacGroup>())
                            ?.let { Target(rbacGroup = it, _json = json) } ?: Target(_json = json)
                    }
                    "organization_member" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaPluginTargetOrganizationMember>(),
                            )
                            ?.let { Target(organizationMember = it, _json = json) }
                            ?: Target(_json = json)
                    }
                }

                return Target(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Target>(Target::class) {

            override fun serialize(
                value: Target,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.organization != null -> generator.writeObject(value.organization)
                    value.rbacGroup != null -> generator.writeObject(value.rbacGroup)
                    value.organizationMember != null ->
                        generator.writeObject(value.organizationMember)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Target")
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

                @JvmField val RBAC_GROUP = Type(JsonField.of("rbac_group"))

                @JvmField val ORGANIZATION_MEMBER = Type(JsonField.of("organization_member"))

                @JvmStatic
                fun of(value: String): Type =
                    // Intern known values so `==` works
                    when (value) {
                        "organization" -> ORGANIZATION
                        "rbac_group" -> RBAC_GROUP
                        "organization_member" -> ORGANIZATION_MEMBER
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
                RBAC_GROUP,
                ORGANIZATION_MEMBER,
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
                RBAC_GROUP,
                ORGANIZATION_MEMBER,
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
                    RBAC_GROUP -> Value.RBAC_GROUP
                    ORGANIZATION_MEMBER -> Value.ORGANIZATION_MEMBER
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
                    RBAC_GROUP -> Known.RBAC_GROUP
                    ORGANIZATION_MEMBER -> Known.ORGANIZATION_MEMBER
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaPluginInstallationSetting &&
            createdAt == other.createdAt &&
            installationPreference == other.installationPreference &&
            pluginId == other.pluginId &&
            target == other.target &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            createdAt,
            installationPreference,
            pluginId,
            target,
            type,
            updatedAt,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaPluginInstallationSetting{createdAt=$createdAt, installationPreference=$installationPreference, pluginId=$pluginId, target=$target, type=$type, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
