package com.anthropic.models.beta.organization.spendlimits

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

/** A configured spend limit: a cap on metered spend for one scope and period. */
class BetaSpendLimit
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val amount: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val currency: JsonField<String>,
    private val isEnabled: JsonField<Boolean>,
    private val period: JsonField<BetaSpendLimitPeriod>,
    private val scope: JsonField<Scope>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("amount") @ExcludeMissing amount: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("currency") @ExcludeMissing currency: JsonField<String> = JsonMissing.of(),
        @JsonProperty("is_enabled")
        @ExcludeMissing
        isEnabled: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("period")
        @ExcludeMissing
        period: JsonField<BetaSpendLimitPeriod> = JsonMissing.of(),
        @JsonProperty("scope") @ExcludeMissing scope: JsonField<Scope> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(
        id,
        amount,
        createdAt,
        currency,
        isEnabled,
        period,
        scope,
        type,
        updatedAt,
        mutableMapOf(),
    )

    /**
     * Unique tagged ID of the spend limit (`spl_...`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Limit amount as a non-negative integer decimal string in the minor unit of `currency` (cents
     * for USD): "50000" is $500.00. `null` means no numeric cap is configured at this scope — see
     * the effective report for whether a limit applies.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun amount(): Optional<String> = amount.getOptional("amount")

    /**
     * RFC 3339 datetime at which the spend limit was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * ISO 4217 code of the organization's billing currency; the unit for `amount`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun currency(): String = currency.getRequired("currency")

    /**
     * Read-only. `false` when extra usage is switched off for this organization (`organization`
     * limit) or for this member (`user` limit); `amount` is kept and applies again when it's
     * switched back on. Always `true` for other limits.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun isEnabled(): Boolean = isEnabled.getRequired("is_enabled")

    /**
     * Length of the window the limit resets over. `amount` caps spend within each period.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun period(): BetaSpendLimitPeriod = period.getRequired("period")

    /**
     * What the limit applies to. A tagged union on `type`; each variant carries the identifier for
     * its scope.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun scope(): Scope = scope.getRequired("scope")

    /**
     * Object type. Always `spend_limit`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("spend_limit")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * RFC 3339 datetime at which the spend limit was last modified.
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
     * Returns the raw JSON value of [amount].
     *
     * Unlike [amount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("amount") @ExcludeMissing fun _amount(): JsonField<String> = amount

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [currency].
     *
     * Unlike [currency], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("currency") @ExcludeMissing fun _currency(): JsonField<String> = currency

    /**
     * Returns the raw JSON value of [isEnabled].
     *
     * Unlike [isEnabled], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("is_enabled") @ExcludeMissing fun _isEnabled(): JsonField<Boolean> = isEnabled

    /**
     * Returns the raw JSON value of [period].
     *
     * Unlike [period], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("period") @ExcludeMissing fun _period(): JsonField<BetaSpendLimitPeriod> = period

    /**
     * Returns the raw JSON value of [scope].
     *
     * Unlike [scope], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scope") @ExcludeMissing fun _scope(): JsonField<Scope> = scope

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
         * Returns a mutable builder for constructing an instance of [BetaSpendLimit].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .amount()
         * .createdAt()
         * .currency()
         * .isEnabled()
         * .period()
         * .scope()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaSpendLimit]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var amount: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var currency: JsonField<String>? = null
        private var isEnabled: JsonField<Boolean>? = null
        private var period: JsonField<BetaSpendLimitPeriod>? = null
        private var scope: JsonField<Scope>? = null
        private var type: JsonValue = JsonValue.from("spend_limit")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaSpendLimit: BetaSpendLimit) = apply {
            id = betaSpendLimit.id
            amount = betaSpendLimit.amount
            createdAt = betaSpendLimit.createdAt
            currency = betaSpendLimit.currency
            isEnabled = betaSpendLimit.isEnabled
            period = betaSpendLimit.period
            scope = betaSpendLimit.scope
            type = betaSpendLimit.type
            updatedAt = betaSpendLimit.updatedAt
            additionalProperties = betaSpendLimit.additionalProperties.toMutableMap()
        }

        /** Unique tagged ID of the spend limit (`spl_...`). */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * Limit amount as a non-negative integer decimal string in the minor unit of `currency`
         * (cents for USD): "50000" is $500.00. `null` means no numeric cap is configured at this
         * scope — see the effective report for whether a limit applies.
         */
        fun amount(amount: String?) = amount(JsonField.ofNullable(amount))

        /** Alias for calling [Builder.amount] with `amount.orElse(null)`. */
        fun amount(amount: Optional<String>) = amount(amount.getOrNull())

        /**
         * Sets [Builder.amount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.amount] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun amount(amount: JsonField<String>) = apply { this.amount = amount }

        /** RFC 3339 datetime at which the spend limit was created. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** ISO 4217 code of the organization's billing currency; the unit for `amount`. */
        fun currency(currency: String) = currency(JsonField.of(currency))

        /**
         * Sets [Builder.currency] to an arbitrary JSON value.
         *
         * You should usually call [Builder.currency] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun currency(currency: JsonField<String>) = apply { this.currency = currency }

        /**
         * Read-only. `false` when extra usage is switched off for this organization (`organization`
         * limit) or for this member (`user` limit); `amount` is kept and applies again when it's
         * switched back on. Always `true` for other limits.
         */
        fun isEnabled(isEnabled: Boolean) = isEnabled(JsonField.of(isEnabled))

        /**
         * Sets [Builder.isEnabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.isEnabled] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun isEnabled(isEnabled: JsonField<Boolean>) = apply { this.isEnabled = isEnabled }

        /** Length of the window the limit resets over. `amount` caps spend within each period. */
        fun period(period: BetaSpendLimitPeriod) = period(JsonField.of(period))

        /**
         * Sets [Builder.period] to an arbitrary JSON value.
         *
         * You should usually call [Builder.period] with a well-typed [BetaSpendLimitPeriod] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun period(period: JsonField<BetaSpendLimitPeriod>) = apply { this.period = period }

        /**
         * What the limit applies to. A tagged union on `type`; each variant carries the identifier
         * for its scope.
         */
        fun scope(scope: Scope) = scope(JsonField.of(scope))

        /**
         * Sets [Builder.scope] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scope] with a well-typed [Scope] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun scope(scope: JsonField<Scope>) = apply { this.scope = scope }

        /** Alias for calling [scope] with `Scope.ofUser(user)`. */
        fun scope(user: BetaSpendLimitUserScope) = scope(Scope.ofUser(user))

        /**
         * Alias for calling [scope] with the following:
         * ```java
         * BetaSpendLimitUserScope.builder()
         *     .userId(userId)
         *     .build()
         * ```
         */
        fun userScope(userId: String) =
            scope(BetaSpendLimitUserScope.builder().userId(userId).build())

        /** Alias for calling [scope] with `Scope.ofSeatTier(seatTier)`. */
        fun scope(seatTier: BetaSpendLimitSeatTierScope) = scope(Scope.ofSeatTier(seatTier))

        /**
         * Alias for calling [scope] with the following:
         * ```java
         * BetaSpendLimitSeatTierScope.builder()
         *     .seatTier(seatTier)
         *     .build()
         * ```
         */
        fun seatTierScope(seatTier: String) =
            scope(BetaSpendLimitSeatTierScope.builder().seatTier(seatTier).build())

        /** Alias for calling [scope] with `Scope.ofRbacGroup(rbacGroup)`. */
        fun scope(rbacGroup: BetaSpendLimitRbacGroupScope) = scope(Scope.ofRbacGroup(rbacGroup))

        /**
         * Alias for calling [scope] with the following:
         * ```java
         * BetaSpendLimitRbacGroupScope.builder()
         *     .rbacGroupId(rbacGroupId)
         *     .build()
         * ```
         */
        fun rbacGroupScope(rbacGroupId: String) =
            scope(BetaSpendLimitRbacGroupScope.builder().rbacGroupId(rbacGroupId).build())

        /** Alias for calling [scope] with `Scope.ofOrganizationService(organizationService)`. */
        fun scope(organizationService: BetaSpendLimitOrganizationServiceScope) =
            scope(Scope.ofOrganizationService(organizationService))

        /**
         * Alias for calling [scope] with the following:
         * ```java
         * BetaSpendLimitOrganizationServiceScope.builder()
         *     .service(service)
         *     .build()
         * ```
         */
        fun organizationServiceScope(service: String) =
            scope(BetaSpendLimitOrganizationServiceScope.builder().service(service).build())

        /** Alias for calling [scope] with `Scope.ofOrganization(organization)`. */
        fun scope(organization: BetaSpendLimitOrganizationScope) =
            scope(Scope.ofOrganization(organization))

        /** Alias for calling [scope] with `Scope.ofWorkspace(workspace)`. */
        fun scope(workspace: BetaSpendLimitWorkspaceScope) = scope(Scope.ofWorkspace(workspace))

        /**
         * Alias for calling [scope] with the following:
         * ```java
         * BetaSpendLimitWorkspaceScope.builder()
         *     .workspaceId(workspaceId)
         *     .build()
         * ```
         */
        fun workspaceScope(workspaceId: String) =
            scope(BetaSpendLimitWorkspaceScope.builder().workspaceId(workspaceId).build())

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("spend_limit")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** RFC 3339 datetime at which the spend limit was last modified. */
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
         * Returns an immutable instance of [BetaSpendLimit].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .amount()
         * .createdAt()
         * .currency()
         * .isEnabled()
         * .period()
         * .scope()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaSpendLimit =
            BetaSpendLimit(
                checkRequired("id", id),
                checkRequired("amount", amount),
                checkRequired("createdAt", createdAt),
                checkRequired("currency", currency),
                checkRequired("isEnabled", isEnabled),
                checkRequired("period", period),
                checkRequired("scope", scope),
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
    fun validate(): BetaSpendLimit = apply {
        if (validated) {
            return@apply
        }

        id()
        amount()
        createdAt()
        currency()
        isEnabled()
        period().validate()
        scope().validate()
        _type().let {
            if (it != JsonValue.from("spend_limit")) {
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
            (if (amount.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (currency.asKnown().isPresent) 1 else 0) +
            (if (isEnabled.asKnown().isPresent) 1 else 0) +
            (period.asKnown().getOrNull()?.validity() ?: 0) +
            (scope.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("spend_limit")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0)

    /**
     * What the limit applies to. A tagged union on `type`; each variant carries the identifier for
     * its scope.
     */
    @JsonDeserialize(using = Scope.Deserializer::class)
    @JsonSerialize(using = Scope.Serializer::class)
    class Scope
    private constructor(
        private val user: BetaSpendLimitUserScope? = null,
        private val seatTier: BetaSpendLimitSeatTierScope? = null,
        private val rbacGroup: BetaSpendLimitRbacGroupScope? = null,
        private val organizationService: BetaSpendLimitOrganizationServiceScope? = null,
        private val organization: BetaSpendLimitOrganizationScope? = null,
        private val workspace: BetaSpendLimitWorkspaceScope? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            when {
                user != null -> Type.USER
                seatTier != null -> Type.SEAT_TIER
                rbacGroup != null -> Type.RBAC_GROUP
                organizationService != null -> Type.ORGANIZATION_SERVICE
                organization != null -> Type.ORGANIZATION
                workspace != null -> Type.WORKSPACE
                else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }

        /** Scope selecting a single member of the organization. */
        fun user(): Optional<BetaSpendLimitUserScope> = Optional.ofNullable(user)

        fun seatTier(): Optional<BetaSpendLimitSeatTierScope> = Optional.ofNullable(seatTier)

        fun rbacGroup(): Optional<BetaSpendLimitRbacGroupScope> = Optional.ofNullable(rbacGroup)

        fun organizationService(): Optional<BetaSpendLimitOrganizationServiceScope> =
            Optional.ofNullable(organizationService)

        fun organization(): Optional<BetaSpendLimitOrganizationScope> =
            Optional.ofNullable(organization)

        /** Scope selecting one workspace of a Claude Console organization. */
        fun workspace(): Optional<BetaSpendLimitWorkspaceScope> = Optional.ofNullable(workspace)

        fun isUser(): Boolean = user != null

        fun isSeatTier(): Boolean = seatTier != null

        fun isRbacGroup(): Boolean = rbacGroup != null

        fun isOrganizationService(): Boolean = organizationService != null

        fun isOrganization(): Boolean = organization != null

        fun isWorkspace(): Boolean = workspace != null

        /** Scope selecting a single member of the organization. */
        fun asUser(): BetaSpendLimitUserScope = user.getOrThrow("user")

        fun asSeatTier(): BetaSpendLimitSeatTierScope = seatTier.getOrThrow("seatTier")

        fun asRbacGroup(): BetaSpendLimitRbacGroupScope = rbacGroup.getOrThrow("rbacGroup")

        fun asOrganizationService(): BetaSpendLimitOrganizationServiceScope =
            organizationService.getOrThrow("organizationService")

        fun asOrganization(): BetaSpendLimitOrganizationScope =
            organization.getOrThrow("organization")

        /** Scope selecting one workspace of a Claude Console organization. */
        fun asWorkspace(): BetaSpendLimitWorkspaceScope = workspace.getOrThrow("workspace")

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
         * Optional<String> result = scope.accept(new Scope.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitUser(BetaSpendLimitUserScope user) {
         *         return Optional.of(user.toString());
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
                user != null -> visitor.visitUser(user)
                seatTier != null -> visitor.visitSeatTier(seatTier)
                rbacGroup != null -> visitor.visitRbacGroup(rbacGroup)
                organizationService != null -> visitor.visitOrganizationService(organizationService)
                organization != null -> visitor.visitOrganization(organization)
                workspace != null -> visitor.visitWorkspace(workspace)
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
        fun validate(): Scope = apply {
            if (validated) {
                return@apply
            }

            when {
                user != null -> user.validate()
                seatTier != null -> seatTier.validate()
                rbacGroup != null -> rbacGroup.validate()
                organizationService != null -> organizationService.validate()
                organization != null -> organization.validate()
                workspace != null -> workspace.validate()
                else -> throw AnthropicInvalidDataException("Unknown Scope: $_json")
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
                user != null -> user.validity()
                seatTier != null -> seatTier.validity()
                rbacGroup != null -> rbacGroup.validity()
                organizationService != null -> organizationService.validity()
                organization != null -> organization.validity()
                workspace != null -> workspace.validity()
                else -> 0
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Scope &&
                user == other.user &&
                seatTier == other.seatTier &&
                rbacGroup == other.rbacGroup &&
                organizationService == other.organizationService &&
                organization == other.organization &&
                workspace == other.workspace
        }

        override fun hashCode(): Int =
            Objects.hash(user, seatTier, rbacGroup, organizationService, organization, workspace)

        override fun toString(): String =
            when {
                user != null -> "Scope{user=$user}"
                seatTier != null -> "Scope{seatTier=$seatTier}"
                rbacGroup != null -> "Scope{rbacGroup=$rbacGroup}"
                organizationService != null -> "Scope{organizationService=$organizationService}"
                organization != null -> "Scope{organization=$organization}"
                workspace != null -> "Scope{workspace=$workspace}"
                _json != null -> "Scope{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Scope")
            }

        companion object {

            /** Scope selecting a single member of the organization. */
            @JvmStatic fun ofUser(user: BetaSpendLimitUserScope) = Scope(user = user)

            /**
             * Returns an immutable instance of [Scope] whose [ofUser] variant is built from the
             * given required [userId].
             */
            @JvmStatic fun ofUser(userId: String) = ofUser(BetaSpendLimitUserScope.of(userId))

            @JvmStatic
            fun ofSeatTier(seatTier: BetaSpendLimitSeatTierScope) = Scope(seatTier = seatTier)

            /**
             * Returns an immutable instance of [Scope] whose [ofSeatTier] variant is built from the
             * given required [seatTier].
             */
            @JvmStatic
            fun ofSeatTier(seatTier: String) = ofSeatTier(BetaSpendLimitSeatTierScope.of(seatTier))

            @JvmStatic
            fun ofRbacGroup(rbacGroup: BetaSpendLimitRbacGroupScope) = Scope(rbacGroup = rbacGroup)

            /**
             * Returns an immutable instance of [Scope] whose [ofRbacGroup] variant is built from
             * the given required [rbacGroupId].
             */
            @JvmStatic
            fun ofRbacGroup(rbacGroupId: String) =
                ofRbacGroup(BetaSpendLimitRbacGroupScope.of(rbacGroupId))

            @JvmStatic
            fun ofOrganizationService(organizationService: BetaSpendLimitOrganizationServiceScope) =
                Scope(organizationService = organizationService)

            /**
             * Returns an immutable instance of [Scope] whose [ofOrganizationService] variant is
             * built from the given required [service].
             */
            @JvmStatic
            fun ofOrganizationService(service: String) =
                ofOrganizationService(BetaSpendLimitOrganizationServiceScope.of(service))

            @JvmStatic
            fun ofOrganization(organization: BetaSpendLimitOrganizationScope) =
                Scope(organization = organization)

            /** Scope selecting one workspace of a Claude Console organization. */
            @JvmStatic
            fun ofWorkspace(workspace: BetaSpendLimitWorkspaceScope) = Scope(workspace = workspace)

            /**
             * Returns an immutable instance of [Scope] whose [ofWorkspace] variant is built from
             * the given required [workspaceId].
             */
            @JvmStatic
            fun ofWorkspace(workspaceId: String) =
                ofWorkspace(BetaSpendLimitWorkspaceScope.of(workspaceId))
        }

        /** An interface that defines how to map each variant of [Scope] to a value of type [T]. */
        interface Visitor<out T> {

            /** Scope selecting a single member of the organization. */
            fun visitUser(user: BetaSpendLimitUserScope): T

            fun visitSeatTier(seatTier: BetaSpendLimitSeatTierScope): T

            fun visitRbacGroup(rbacGroup: BetaSpendLimitRbacGroupScope): T

            fun visitOrganizationService(
                organizationService: BetaSpendLimitOrganizationServiceScope
            ): T

            fun visitOrganization(organization: BetaSpendLimitOrganizationScope): T

            /** Scope selecting one workspace of a Claude Console organization. */
            fun visitWorkspace(workspace: BetaSpendLimitWorkspaceScope): T

            /**
             * Maps an unknown variant of [Scope] to a value of type [T].
             *
             * An instance of [Scope] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Scope: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Scope>(Scope::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Scope {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "user" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaSpendLimitUserScope>())
                            ?.let { Scope(user = it, _json = json) } ?: Scope(_json = json)
                    }
                    "seat_tier" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaSpendLimitSeatTierScope>())
                            ?.let { Scope(seatTier = it, _json = json) } ?: Scope(_json = json)
                    }
                    "rbac_group" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaSpendLimitRbacGroupScope>())
                            ?.let { Scope(rbacGroup = it, _json = json) } ?: Scope(_json = json)
                    }
                    "organization_service" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaSpendLimitOrganizationServiceScope>(),
                            )
                            ?.let { Scope(organizationService = it, _json = json) }
                            ?: Scope(_json = json)
                    }
                    "organization" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaSpendLimitOrganizationScope>(),
                            )
                            ?.let { Scope(organization = it, _json = json) } ?: Scope(_json = json)
                    }
                    "workspace" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaSpendLimitWorkspaceScope>())
                            ?.let { Scope(workspace = it, _json = json) } ?: Scope(_json = json)
                    }
                }

                return Scope(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Scope>(Scope::class) {

            override fun serialize(
                value: Scope,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.user != null -> generator.writeObject(value.user)
                    value.seatTier != null -> generator.writeObject(value.seatTier)
                    value.rbacGroup != null -> generator.writeObject(value.rbacGroup)
                    value.organizationService != null ->
                        generator.writeObject(value.organizationService)
                    value.organization != null -> generator.writeObject(value.organization)
                    value.workspace != null -> generator.writeObject(value.workspace)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Scope")
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

                @JvmField val USER = Type(JsonField.of("user"))

                @JvmField val SEAT_TIER = Type(JsonField.of("seat_tier"))

                @JvmField val RBAC_GROUP = Type(JsonField.of("rbac_group"))

                @JvmField val ORGANIZATION_SERVICE = Type(JsonField.of("organization_service"))

                @JvmField val ORGANIZATION = Type(JsonField.of("organization"))

                @JvmField val WORKSPACE = Type(JsonField.of("workspace"))

                @JvmStatic
                fun of(value: String): Type =
                    // Intern known values so `==` works
                    when (value) {
                        "user" -> USER
                        "seat_tier" -> SEAT_TIER
                        "rbac_group" -> RBAC_GROUP
                        "organization_service" -> ORGANIZATION_SERVICE
                        "organization" -> ORGANIZATION
                        "workspace" -> WORKSPACE
                        else -> Type(JsonField.of(value))
                    }

                @JsonCreator
                @JvmStatic
                fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                USER,
                SEAT_TIER,
                RBAC_GROUP,
                ORGANIZATION_SERVICE,
                ORGANIZATION,
                WORKSPACE,
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
                USER,
                SEAT_TIER,
                RBAC_GROUP,
                ORGANIZATION_SERVICE,
                ORGANIZATION,
                WORKSPACE,
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
                    USER -> Value.USER
                    SEAT_TIER -> Value.SEAT_TIER
                    RBAC_GROUP -> Value.RBAC_GROUP
                    ORGANIZATION_SERVICE -> Value.ORGANIZATION_SERVICE
                    ORGANIZATION -> Value.ORGANIZATION
                    WORKSPACE -> Value.WORKSPACE
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
                    USER -> Known.USER
                    SEAT_TIER -> Known.SEAT_TIER
                    RBAC_GROUP -> Known.RBAC_GROUP
                    ORGANIZATION_SERVICE -> Known.ORGANIZATION_SERVICE
                    ORGANIZATION -> Known.ORGANIZATION
                    WORKSPACE -> Known.WORKSPACE
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

        return other is BetaSpendLimit &&
            id == other.id &&
            amount == other.amount &&
            createdAt == other.createdAt &&
            currency == other.currency &&
            isEnabled == other.isEnabled &&
            period == other.period &&
            scope == other.scope &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            amount,
            createdAt,
            currency,
            isEnabled,
            period,
            scope,
            type,
            updatedAt,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSpendLimit{id=$id, amount=$amount, createdAt=$createdAt, currency=$currency, isEnabled=$isEnabled, period=$period, scope=$scope, type=$type, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
