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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Per-member effective-limit report row (`GET /spend_limits/effective`). */
class BetaSpendSummary
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val actor: JsonField<Actor>,
    private val amount: JsonField<String>,
    private val currency: JsonField<String>,
    private val period: JsonField<BetaSpendLimitPeriod>,
    private val periodToDateSpend: JsonField<String>,
    private val scope: JsonField<Scope>,
    private val source: JsonField<Source>,
    private val spendLimitId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("actor") @ExcludeMissing actor: JsonField<Actor> = JsonMissing.of(),
        @JsonProperty("amount") @ExcludeMissing amount: JsonField<String> = JsonMissing.of(),
        @JsonProperty("currency") @ExcludeMissing currency: JsonField<String> = JsonMissing.of(),
        @JsonProperty("period")
        @ExcludeMissing
        period: JsonField<BetaSpendLimitPeriod> = JsonMissing.of(),
        @JsonProperty("period_to_date_spend")
        @ExcludeMissing
        periodToDateSpend: JsonField<String> = JsonMissing.of(),
        @JsonProperty("scope") @ExcludeMissing scope: JsonField<Scope> = JsonMissing.of(),
        @JsonProperty("source") @ExcludeMissing source: JsonField<Source> = JsonMissing.of(),
        @JsonProperty("spend_limit_id")
        @ExcludeMissing
        spendLimitId: JsonField<String> = JsonMissing.of(),
    ) : this(
        actor,
        amount,
        currency,
        period,
        periodToDateSpend,
        scope,
        source,
        spendLimitId,
        mutableMapOf(),
    )

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun actor(): Actor = actor.getRequired("actor")

    /**
     * Effective limit amount as a non-negative integer decimal string in the minor unit of
     * `currency` (cents for USD). `null` means no limit applies for this row's `period` — each
     * period resolves independently, so another period may still cap this member.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun amount(): Optional<String> = amount.getOptional("amount")

    /**
     * ISO 4217 code of the organization's billing currency; the unit for `amount` and
     * `period_to_date_spend`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun currency(): String = currency.getRequired("currency")

    /**
     * Period this row's effective limit and spend are reported for.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun period(): BetaSpendLimitPeriod = period.getRequired("period")

    /**
     * The member's spend so far in the current period, as a non-negative decimal string in the
     * minor unit of `currency` (cents for USD). May carry fractional minor units up to three
     * decimal places (e.g. `"12050.5"`) — metered usage is not rounded to whole cents. Reads as
     * `"0"` when the spend reading is temporarily unavailable.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun periodToDateSpend(): String = periodToDateSpend.getRequired("period_to_date_spend")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun scope(): Scope = scope.getRequired("scope")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): Source = source.getRequired("source")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun spendLimitId(): String = spendLimitId.getRequired("spend_limit_id")

    /**
     * Returns the raw JSON value of [actor].
     *
     * Unlike [actor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("actor") @ExcludeMissing fun _actor(): JsonField<Actor> = actor

    /**
     * Returns the raw JSON value of [amount].
     *
     * Unlike [amount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("amount") @ExcludeMissing fun _amount(): JsonField<String> = amount

    /**
     * Returns the raw JSON value of [currency].
     *
     * Unlike [currency], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("currency") @ExcludeMissing fun _currency(): JsonField<String> = currency

    /**
     * Returns the raw JSON value of [period].
     *
     * Unlike [period], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("period") @ExcludeMissing fun _period(): JsonField<BetaSpendLimitPeriod> = period

    /**
     * Returns the raw JSON value of [periodToDateSpend].
     *
     * Unlike [periodToDateSpend], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("period_to_date_spend")
    @ExcludeMissing
    fun _periodToDateSpend(): JsonField<String> = periodToDateSpend

    /**
     * Returns the raw JSON value of [scope].
     *
     * Unlike [scope], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scope") @ExcludeMissing fun _scope(): JsonField<Scope> = scope

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

    /**
     * Returns the raw JSON value of [spendLimitId].
     *
     * Unlike [spendLimitId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("spend_limit_id")
    @ExcludeMissing
    fun _spendLimitId(): JsonField<String> = spendLimitId

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
         * Returns a mutable builder for constructing an instance of [BetaSpendSummary].
         *
         * The following fields are required:
         * ```java
         * .actor()
         * .amount()
         * .currency()
         * .period()
         * .periodToDateSpend()
         * .scope()
         * .source()
         * .spendLimitId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaSpendSummary]. */
    class Builder internal constructor() {

        private var actor: JsonField<Actor>? = null
        private var amount: JsonField<String>? = null
        private var currency: JsonField<String>? = null
        private var period: JsonField<BetaSpendLimitPeriod>? = null
        private var periodToDateSpend: JsonField<String>? = null
        private var scope: JsonField<Scope>? = null
        private var source: JsonField<Source>? = null
        private var spendLimitId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaSpendSummary: BetaSpendSummary) = apply {
            actor = betaSpendSummary.actor
            amount = betaSpendSummary.amount
            currency = betaSpendSummary.currency
            period = betaSpendSummary.period
            periodToDateSpend = betaSpendSummary.periodToDateSpend
            scope = betaSpendSummary.scope
            source = betaSpendSummary.source
            spendLimitId = betaSpendSummary.spendLimitId
            additionalProperties = betaSpendSummary.additionalProperties.toMutableMap()
        }

        fun actor(actor: Actor) = actor(JsonField.of(actor))

        /**
         * Sets [Builder.actor] to an arbitrary JSON value.
         *
         * You should usually call [Builder.actor] with a well-typed [Actor] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun actor(actor: JsonField<Actor>) = apply { this.actor = actor }

        /** Alias for calling [actor] with `Actor.ofUser(user)`. */
        fun actor(user: BetaSpendLimitUserActor) = actor(Actor.ofUser(user))

        /** Alias for calling [actor] with `Actor.ofScopedApiKey(scopedApiKey)`. */
        fun actor(scopedApiKey: BetaSpendLimitScopedApiKeyActor) =
            actor(Actor.ofScopedApiKey(scopedApiKey))

        /**
         * Alias for calling [actor] with the following:
         * ```java
         * BetaSpendLimitScopedApiKeyActor.builder()
         *     .scopedApiKeyId(scopedApiKeyId)
         *     .build()
         * ```
         */
        fun scopedApiKeyActor(scopedApiKeyId: String) =
            actor(BetaSpendLimitScopedApiKeyActor.builder().scopedApiKeyId(scopedApiKeyId).build())

        /**
         * Effective limit amount as a non-negative integer decimal string in the minor unit of
         * `currency` (cents for USD). `null` means no limit applies for this row's `period` — each
         * period resolves independently, so another period may still cap this member.
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

        /**
         * ISO 4217 code of the organization's billing currency; the unit for `amount` and
         * `period_to_date_spend`.
         */
        fun currency(currency: String) = currency(JsonField.of(currency))

        /**
         * Sets [Builder.currency] to an arbitrary JSON value.
         *
         * You should usually call [Builder.currency] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun currency(currency: JsonField<String>) = apply { this.currency = currency }

        /** Period this row's effective limit and spend are reported for. */
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
         * The member's spend so far in the current period, as a non-negative decimal string in the
         * minor unit of `currency` (cents for USD). May carry fractional minor units up to three
         * decimal places (e.g. `"12050.5"`) — metered usage is not rounded to whole cents. Reads as
         * `"0"` when the spend reading is temporarily unavailable.
         */
        fun periodToDateSpend(periodToDateSpend: String) =
            periodToDateSpend(JsonField.of(periodToDateSpend))

        /**
         * Sets [Builder.periodToDateSpend] to an arbitrary JSON value.
         *
         * You should usually call [Builder.periodToDateSpend] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun periodToDateSpend(periodToDateSpend: JsonField<String>) = apply {
            this.periodToDateSpend = periodToDateSpend
        }

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

        fun source(source: Source) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [Source] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<Source>) = apply { this.source = source }

        /** Alias for calling [source] with `Source.ofUser(user)`. */
        fun source(user: BetaSpendLimitUserScope) = source(Source.ofUser(user))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaSpendLimitUserScope.builder()
         *     .userId(userId)
         *     .build()
         * ```
         */
        fun userSource(userId: String) =
            source(BetaSpendLimitUserScope.builder().userId(userId).build())

        /** Alias for calling [source] with `Source.ofSeatTier(seatTier)`. */
        fun source(seatTier: BetaSpendLimitSeatTierScope) = source(Source.ofSeatTier(seatTier))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaSpendLimitSeatTierScope.builder()
         *     .seatTier(seatTier)
         *     .build()
         * ```
         */
        fun seatTierSource(seatTier: String) =
            source(BetaSpendLimitSeatTierScope.builder().seatTier(seatTier).build())

        /** Alias for calling [source] with `Source.ofRbacGroup(rbacGroup)`. */
        fun source(rbacGroup: BetaSpendLimitRbacGroupScope) = source(Source.ofRbacGroup(rbacGroup))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaSpendLimitRbacGroupScope.builder()
         *     .rbacGroupId(rbacGroupId)
         *     .build()
         * ```
         */
        fun rbacGroupSource(rbacGroupId: String) =
            source(BetaSpendLimitRbacGroupScope.builder().rbacGroupId(rbacGroupId).build())

        /** Alias for calling [source] with `Source.ofOrganizationService(organizationService)`. */
        fun source(organizationService: BetaSpendLimitOrganizationServiceScope) =
            source(Source.ofOrganizationService(organizationService))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaSpendLimitOrganizationServiceScope.builder()
         *     .service(service)
         *     .build()
         * ```
         */
        fun organizationServiceSource(service: String) =
            source(BetaSpendLimitOrganizationServiceScope.builder().service(service).build())

        /** Alias for calling [source] with `Source.ofOrganization(organization)`. */
        fun source(organization: BetaSpendLimitOrganizationScope) =
            source(Source.ofOrganization(organization))

        /** Alias for calling [source] with `Source.ofWorkspace(workspace)`. */
        fun source(workspace: BetaSpendLimitWorkspaceScope) = source(Source.ofWorkspace(workspace))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaSpendLimitWorkspaceScope.builder()
         *     .workspaceId(workspaceId)
         *     .build()
         * ```
         */
        fun workspaceSource(workspaceId: String) =
            source(BetaSpendLimitWorkspaceScope.builder().workspaceId(workspaceId).build())

        fun spendLimitId(spendLimitId: String) = spendLimitId(JsonField.of(spendLimitId))

        /**
         * Sets [Builder.spendLimitId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.spendLimitId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun spendLimitId(spendLimitId: JsonField<String>) = apply {
            this.spendLimitId = spendLimitId
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
         * Returns an immutable instance of [BetaSpendSummary].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .actor()
         * .amount()
         * .currency()
         * .period()
         * .periodToDateSpend()
         * .scope()
         * .source()
         * .spendLimitId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaSpendSummary =
            BetaSpendSummary(
                checkRequired("actor", actor),
                checkRequired("amount", amount),
                checkRequired("currency", currency),
                checkRequired("period", period),
                checkRequired("periodToDateSpend", periodToDateSpend),
                checkRequired("scope", scope),
                checkRequired("source", source),
                checkRequired("spendLimitId", spendLimitId),
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
    fun validate(): BetaSpendSummary = apply {
        if (validated) {
            return@apply
        }

        actor().validate()
        amount()
        currency()
        period().validate()
        periodToDateSpend()
        scope().validate()
        source().validate()
        spendLimitId()
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
        (actor.asKnown().getOrNull()?.validity() ?: 0) +
            (if (amount.asKnown().isPresent) 1 else 0) +
            (if (currency.asKnown().isPresent) 1 else 0) +
            (period.asKnown().getOrNull()?.validity() ?: 0) +
            (if (periodToDateSpend.asKnown().isPresent) 1 else 0) +
            (scope.asKnown().getOrNull()?.validity() ?: 0) +
            (source.asKnown().getOrNull()?.validity() ?: 0) +
            (if (spendLimitId.asKnown().isPresent) 1 else 0)

    @JsonDeserialize(using = Actor.Deserializer::class)
    @JsonSerialize(using = Actor.Serializer::class)
    class Actor
    private constructor(
        private val user: BetaSpendLimitUserActor? = null,
        private val scopedApiKey: BetaSpendLimitScopedApiKeyActor? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            when {
                user != null -> Type.USER_ACTOR
                scopedApiKey != null -> Type.SCOPED_API_KEY_ACTOR
                else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }

        /**
         * A user within the organization. `name` and `email_address` are null when the underlying
         * account is unavailable or has been deleted; `deleted` is true only for deleted accounts.
         */
        fun user(): Optional<BetaSpendLimitUserActor> = Optional.ofNullable(user)

        /** A scoped Admin API key acting on behalf of the organization. */
        fun scopedApiKey(): Optional<BetaSpendLimitScopedApiKeyActor> =
            Optional.ofNullable(scopedApiKey)

        fun isUser(): Boolean = user != null

        fun isScopedApiKey(): Boolean = scopedApiKey != null

        /**
         * A user within the organization. `name` and `email_address` are null when the underlying
         * account is unavailable or has been deleted; `deleted` is true only for deleted accounts.
         */
        fun asUser(): BetaSpendLimitUserActor = user.getOrThrow("user")

        /** A scoped Admin API key acting on behalf of the organization. */
        fun asScopedApiKey(): BetaSpendLimitScopedApiKeyActor =
            scopedApiKey.getOrThrow("scopedApiKey")

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
         * Optional<String> result = actor.accept(new Actor.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitUser(BetaSpendLimitUserActor user) {
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
                scopedApiKey != null -> visitor.visitScopedApiKey(scopedApiKey)
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
        fun validate(): Actor = apply {
            if (validated) {
                return@apply
            }

            when {
                user != null -> user.validate()
                scopedApiKey != null -> scopedApiKey.validate()
                else -> throw AnthropicInvalidDataException("Unknown Actor: $_json")
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
                scopedApiKey != null -> scopedApiKey.validity()
                else -> 0
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Actor && user == other.user && scopedApiKey == other.scopedApiKey
        }

        override fun hashCode(): Int = Objects.hash(user, scopedApiKey)

        override fun toString(): String =
            when {
                user != null -> "Actor{user=$user}"
                scopedApiKey != null -> "Actor{scopedApiKey=$scopedApiKey}"
                _json != null -> "Actor{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Actor")
            }

        companion object {

            /**
             * A user within the organization. `name` and `email_address` are null when the
             * underlying account is unavailable or has been deleted; `deleted` is true only for
             * deleted accounts.
             */
            @JvmStatic fun ofUser(user: BetaSpendLimitUserActor) = Actor(user = user)

            /** A scoped Admin API key acting on behalf of the organization. */
            @JvmStatic
            fun ofScopedApiKey(scopedApiKey: BetaSpendLimitScopedApiKeyActor) =
                Actor(scopedApiKey = scopedApiKey)

            /**
             * Returns an immutable instance of [Actor] whose [ofScopedApiKey] variant is built from
             * the given required [scopedApiKeyId].
             */
            @JvmStatic
            fun ofScopedApiKey(scopedApiKeyId: String) =
                ofScopedApiKey(BetaSpendLimitScopedApiKeyActor.of(scopedApiKeyId))
        }

        /** An interface that defines how to map each variant of [Actor] to a value of type [T]. */
        interface Visitor<out T> {

            /**
             * A user within the organization. `name` and `email_address` are null when the
             * underlying account is unavailable or has been deleted; `deleted` is true only for
             * deleted accounts.
             */
            fun visitUser(user: BetaSpendLimitUserActor): T

            /** A scoped Admin API key acting on behalf of the organization. */
            fun visitScopedApiKey(scopedApiKey: BetaSpendLimitScopedApiKeyActor): T

            /**
             * Maps an unknown variant of [Actor] to a value of type [T].
             *
             * An instance of [Actor] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Actor: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Actor>(Actor::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Actor {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "user_actor" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaSpendLimitUserActor>())
                            ?.let { Actor(user = it, _json = json) } ?: Actor(_json = json)
                    }
                    "scoped_api_key_actor" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaSpendLimitScopedApiKeyActor>(),
                            )
                            ?.let { Actor(scopedApiKey = it, _json = json) } ?: Actor(_json = json)
                    }
                }

                return Actor(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Actor>(Actor::class) {

            override fun serialize(
                value: Actor,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.user != null -> generator.writeObject(value.user)
                    value.scopedApiKey != null -> generator.writeObject(value.scopedApiKey)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Actor")
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

                @JvmField val SCOPED_API_KEY_ACTOR = Type(JsonField.of("scoped_api_key_actor"))

                @JvmStatic
                fun of(value: String): Type =
                    // Intern known values so `==` works
                    when (value) {
                        "user_actor" -> USER_ACTOR
                        "scoped_api_key_actor" -> SCOPED_API_KEY_ACTOR
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
                SCOPED_API_KEY_ACTOR,
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
                SCOPED_API_KEY_ACTOR,
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
                    SCOPED_API_KEY_ACTOR -> Value.SCOPED_API_KEY_ACTOR
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
                    SCOPED_API_KEY_ACTOR -> Known.SCOPED_API_KEY_ACTOR
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

    @JsonDeserialize(using = Source.Deserializer::class)
    @JsonSerialize(using = Source.Serializer::class)
    class Source
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
         * Optional<String> result = source.accept(new Source.Visitor<Optional<String>>() {
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
        fun validate(): Source = apply {
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
                else -> throw AnthropicInvalidDataException("Unknown Source: $_json")
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

            return other is Source &&
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
                user != null -> "Source{user=$user}"
                seatTier != null -> "Source{seatTier=$seatTier}"
                rbacGroup != null -> "Source{rbacGroup=$rbacGroup}"
                organizationService != null -> "Source{organizationService=$organizationService}"
                organization != null -> "Source{organization=$organization}"
                workspace != null -> "Source{workspace=$workspace}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            /** Scope selecting a single member of the organization. */
            @JvmStatic fun ofUser(user: BetaSpendLimitUserScope) = Source(user = user)

            /**
             * Returns an immutable instance of [Source] whose [ofUser] variant is built from the
             * given required [userId].
             */
            @JvmStatic fun ofUser(userId: String) = ofUser(BetaSpendLimitUserScope.of(userId))

            @JvmStatic
            fun ofSeatTier(seatTier: BetaSpendLimitSeatTierScope) = Source(seatTier = seatTier)

            /**
             * Returns an immutable instance of [Source] whose [ofSeatTier] variant is built from
             * the given required [seatTier].
             */
            @JvmStatic
            fun ofSeatTier(seatTier: String) = ofSeatTier(BetaSpendLimitSeatTierScope.of(seatTier))

            @JvmStatic
            fun ofRbacGroup(rbacGroup: BetaSpendLimitRbacGroupScope) = Source(rbacGroup = rbacGroup)

            /**
             * Returns an immutable instance of [Source] whose [ofRbacGroup] variant is built from
             * the given required [rbacGroupId].
             */
            @JvmStatic
            fun ofRbacGroup(rbacGroupId: String) =
                ofRbacGroup(BetaSpendLimitRbacGroupScope.of(rbacGroupId))

            @JvmStatic
            fun ofOrganizationService(organizationService: BetaSpendLimitOrganizationServiceScope) =
                Source(organizationService = organizationService)

            /**
             * Returns an immutable instance of [Source] whose [ofOrganizationService] variant is
             * built from the given required [service].
             */
            @JvmStatic
            fun ofOrganizationService(service: String) =
                ofOrganizationService(BetaSpendLimitOrganizationServiceScope.of(service))

            @JvmStatic
            fun ofOrganization(organization: BetaSpendLimitOrganizationScope) =
                Source(organization = organization)

            /** Scope selecting one workspace of a Claude Console organization. */
            @JvmStatic
            fun ofWorkspace(workspace: BetaSpendLimitWorkspaceScope) = Source(workspace = workspace)

            /**
             * Returns an immutable instance of [Source] whose [ofWorkspace] variant is built from
             * the given required [workspaceId].
             */
            @JvmStatic
            fun ofWorkspace(workspaceId: String) =
                ofWorkspace(BetaSpendLimitWorkspaceScope.of(workspaceId))
        }

        /** An interface that defines how to map each variant of [Source] to a value of type [T]. */
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
             * Maps an unknown variant of [Source] to a value of type [T].
             *
             * An instance of [Source] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Source: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Source>(Source::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Source {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "user" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaSpendLimitUserScope>())
                            ?.let { Source(user = it, _json = json) } ?: Source(_json = json)
                    }
                    "seat_tier" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaSpendLimitSeatTierScope>())
                            ?.let { Source(seatTier = it, _json = json) } ?: Source(_json = json)
                    }
                    "rbac_group" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaSpendLimitRbacGroupScope>())
                            ?.let { Source(rbacGroup = it, _json = json) } ?: Source(_json = json)
                    }
                    "organization_service" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaSpendLimitOrganizationServiceScope>(),
                            )
                            ?.let { Source(organizationService = it, _json = json) }
                            ?: Source(_json = json)
                    }
                    "organization" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaSpendLimitOrganizationScope>(),
                            )
                            ?.let { Source(organization = it, _json = json) }
                            ?: Source(_json = json)
                    }
                    "workspace" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaSpendLimitWorkspaceScope>())
                            ?.let { Source(workspace = it, _json = json) } ?: Source(_json = json)
                    }
                }

                return Source(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Source>(Source::class) {

            override fun serialize(
                value: Source,
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
                    else -> throw IllegalStateException("Invalid Source")
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

        return other is BetaSpendSummary &&
            actor == other.actor &&
            amount == other.amount &&
            currency == other.currency &&
            period == other.period &&
            periodToDateSpend == other.periodToDateSpend &&
            scope == other.scope &&
            source == other.source &&
            spendLimitId == other.spendLimitId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            actor,
            amount,
            currency,
            period,
            periodToDateSpend,
            scope,
            source,
            spendLimitId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSpendSummary{actor=$actor, amount=$amount, currency=$currency, period=$period, periodToDateSpend=$periodToDateSpend, scope=$scope, source=$source, spendLimitId=$spendLimitId, additionalProperties=$additionalProperties}"
}
