package com.anthropic.models.beta.organization.claudecode.managedsettings.configuration

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaClaudeCodeManagedSettingsConfiguration
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val mode: JsonField<BetaClaudeCodeManagedSettingsMode>,
    private val modeUpdatedAt: JsonField<OffsetDateTime>,
    private val rbacGroupOrder: JsonField<List<String>>,
    private val rbacGroupOrderVersion: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("mode")
        @ExcludeMissing
        mode: JsonField<BetaClaudeCodeManagedSettingsMode> = JsonMissing.of(),
        @JsonProperty("mode_updated_at")
        @ExcludeMissing
        modeUpdatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("rbac_group_order")
        @ExcludeMissing
        rbacGroupOrder: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("rbac_group_order_version")
        @ExcludeMissing
        rbacGroupOrderVersion: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(mode, modeUpdatedAt, rbacGroupOrder, rbacGroupOrderVersion, type, mutableMapOf())

    /**
     * How the organization serves managed settings. In `organization` mode every member receives
     * the organization settings, which are set in claude.ai. In `rbac_groups` mode each member
     * receives the settings of the first RBAC Group in `rbac_group_order` that they belong to. A
     * member in none of them receives the default settings, or no settings if no default is set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun mode(): BetaClaudeCodeManagedSettingsMode = mode.getRequired("mode")

    /**
     * When the mode last changed, in RFC 3339 format, or `null` if it never has.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun modeUpdatedAt(): Optional<OffsetDateTime> = modeUpdatedAt.getOptional("mode_updated_at")

    /**
     * The ID of every RBAC Group with settings, deleted groups included, highest precedence first.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun rbacGroupOrder(): List<String> = rbacGroupOrder.getRequired("rbac_group_order")

    /**
     * An opaque version of `rbac_group_order`, which changes whenever the order does. Send it back
     * with a new order.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun rbacGroupOrderVersion(): String =
        rbacGroupOrderVersion.getRequired("rbac_group_order_version")

    /**
     * Always `claude_code_managed_settings_configuration`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("claude_code_managed_settings_configuration")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [mode].
     *
     * Unlike [mode], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mode")
    @ExcludeMissing
    fun _mode(): JsonField<BetaClaudeCodeManagedSettingsMode> = mode

    /**
     * Returns the raw JSON value of [modeUpdatedAt].
     *
     * Unlike [modeUpdatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mode_updated_at")
    @ExcludeMissing
    fun _modeUpdatedAt(): JsonField<OffsetDateTime> = modeUpdatedAt

    /**
     * Returns the raw JSON value of [rbacGroupOrder].
     *
     * Unlike [rbacGroupOrder], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rbac_group_order")
    @ExcludeMissing
    fun _rbacGroupOrder(): JsonField<List<String>> = rbacGroupOrder

    /**
     * Returns the raw JSON value of [rbacGroupOrderVersion].
     *
     * Unlike [rbacGroupOrderVersion], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("rbac_group_order_version")
    @ExcludeMissing
    fun _rbacGroupOrderVersion(): JsonField<String> = rbacGroupOrderVersion

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
         * [BetaClaudeCodeManagedSettingsConfiguration].
         *
         * The following fields are required:
         * ```java
         * .mode()
         * .modeUpdatedAt()
         * .rbacGroupOrder()
         * .rbacGroupOrderVersion()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaClaudeCodeManagedSettingsConfiguration]. */
    class Builder internal constructor() {

        private var mode: JsonField<BetaClaudeCodeManagedSettingsMode>? = null
        private var modeUpdatedAt: JsonField<OffsetDateTime>? = null
        private var rbacGroupOrder: JsonField<MutableList<String>>? = null
        private var rbacGroupOrderVersion: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("claude_code_managed_settings_configuration")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaClaudeCodeManagedSettingsConfiguration: BetaClaudeCodeManagedSettingsConfiguration
        ) = apply {
            mode = betaClaudeCodeManagedSettingsConfiguration.mode
            modeUpdatedAt = betaClaudeCodeManagedSettingsConfiguration.modeUpdatedAt
            rbacGroupOrder =
                betaClaudeCodeManagedSettingsConfiguration.rbacGroupOrder
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            rbacGroupOrderVersion = betaClaudeCodeManagedSettingsConfiguration.rbacGroupOrderVersion
            type = betaClaudeCodeManagedSettingsConfiguration.type
            additionalProperties =
                betaClaudeCodeManagedSettingsConfiguration.additionalProperties.toMutableMap()
        }

        /**
         * How the organization serves managed settings. In `organization` mode every member
         * receives the organization settings, which are set in claude.ai. In `rbac_groups` mode
         * each member receives the settings of the first RBAC Group in `rbac_group_order` that they
         * belong to. A member in none of them receives the default settings, or no settings if no
         * default is set.
         */
        fun mode(mode: BetaClaudeCodeManagedSettingsMode) = mode(JsonField.of(mode))

        /**
         * Sets [Builder.mode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mode] with a well-typed
         * [BetaClaudeCodeManagedSettingsMode] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun mode(mode: JsonField<BetaClaudeCodeManagedSettingsMode>) = apply { this.mode = mode }

        /**
         * Alias for calling [mode] with
         * `BetaClaudeCodeManagedSettingsMode.ofOrganization(organization)`.
         */
        fun mode(organization: BetaClaudeCodeManagedSettingsOrganizationMode) =
            mode(BetaClaudeCodeManagedSettingsMode.ofOrganization(organization))

        /**
         * Alias for calling [mode] with
         * `BetaClaudeCodeManagedSettingsMode.ofRbacGroups(rbacGroups)`.
         */
        fun mode(rbacGroups: BetaClaudeCodeManagedSettingsRbacGroupsMode) =
            mode(BetaClaudeCodeManagedSettingsMode.ofRbacGroups(rbacGroups))

        /** When the mode last changed, in RFC 3339 format, or `null` if it never has. */
        fun modeUpdatedAt(modeUpdatedAt: OffsetDateTime?) =
            modeUpdatedAt(JsonField.ofNullable(modeUpdatedAt))

        /** Alias for calling [Builder.modeUpdatedAt] with `modeUpdatedAt.orElse(null)`. */
        fun modeUpdatedAt(modeUpdatedAt: Optional<OffsetDateTime>) =
            modeUpdatedAt(modeUpdatedAt.getOrNull())

        /**
         * Sets [Builder.modeUpdatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.modeUpdatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun modeUpdatedAt(modeUpdatedAt: JsonField<OffsetDateTime>) = apply {
            this.modeUpdatedAt = modeUpdatedAt
        }

        /**
         * The ID of every RBAC Group with settings, deleted groups included, highest precedence
         * first.
         */
        fun rbacGroupOrder(rbacGroupOrder: List<String>) =
            rbacGroupOrder(JsonField.of(rbacGroupOrder))

        /**
         * Sets [Builder.rbacGroupOrder] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupOrder] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun rbacGroupOrder(rbacGroupOrder: JsonField<List<String>>) = apply {
            this.rbacGroupOrder = rbacGroupOrder.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [Builder.rbacGroupOrder].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRbacGroupOrder(rbacGroupOrder: String) = apply {
            this.rbacGroupOrder =
                (this.rbacGroupOrder ?: JsonField.of(mutableListOf())).also {
                    checkKnown("rbacGroupOrder", it).add(rbacGroupOrder)
                }
        }

        /**
         * An opaque version of `rbac_group_order`, which changes whenever the order does. Send it
         * back with a new order.
         */
        fun rbacGroupOrderVersion(rbacGroupOrderVersion: String) =
            rbacGroupOrderVersion(JsonField.of(rbacGroupOrderVersion))

        /**
         * Sets [Builder.rbacGroupOrderVersion] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupOrderVersion] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun rbacGroupOrderVersion(rbacGroupOrderVersion: JsonField<String>) = apply {
            this.rbacGroupOrderVersion = rbacGroupOrderVersion
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("claude_code_managed_settings_configuration")
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
         * Returns an immutable instance of [BetaClaudeCodeManagedSettingsConfiguration].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .mode()
         * .modeUpdatedAt()
         * .rbacGroupOrder()
         * .rbacGroupOrderVersion()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaClaudeCodeManagedSettingsConfiguration =
            BetaClaudeCodeManagedSettingsConfiguration(
                checkRequired("mode", mode),
                checkRequired("modeUpdatedAt", modeUpdatedAt),
                checkRequired("rbacGroupOrder", rbacGroupOrder).map { it.toImmutable() },
                checkRequired("rbacGroupOrderVersion", rbacGroupOrderVersion),
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
    fun validate(): BetaClaudeCodeManagedSettingsConfiguration = apply {
        if (validated) {
            return@apply
        }

        mode().validate()
        modeUpdatedAt()
        rbacGroupOrder()
        rbacGroupOrderVersion()
        _type().let {
            if (it != JsonValue.from("claude_code_managed_settings_configuration")) {
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
        (mode.asKnown().getOrNull()?.validity() ?: 0) +
            (if (modeUpdatedAt.asKnown().isPresent) 1 else 0) +
            (rbacGroupOrder.asKnown().getOrNull()?.size ?: 0) +
            (if (rbacGroupOrderVersion.asKnown().isPresent) 1 else 0) +
            type.let {
                if (it == JsonValue.from("claude_code_managed_settings_configuration")) 1 else 0
            }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeCodeManagedSettingsConfiguration &&
            mode == other.mode &&
            modeUpdatedAt == other.modeUpdatedAt &&
            rbacGroupOrder == other.rbacGroupOrder &&
            rbacGroupOrderVersion == other.rbacGroupOrderVersion &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            mode,
            modeUpdatedAt,
            rbacGroupOrder,
            rbacGroupOrderVersion,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaClaudeCodeManagedSettingsConfiguration{mode=$mode, modeUpdatedAt=$modeUpdatedAt, rbacGroupOrder=$rbacGroupOrder, rbacGroupOrderVersion=$rbacGroupOrderVersion, type=$type, additionalProperties=$additionalProperties}"
}
