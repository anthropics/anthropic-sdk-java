package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
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

/**
 * Set a spend limit.
 *
 * Upsert keyed on (scope, period): setting a limit that already exists overwrites it in place. A
 * Claude Enterprise organization sets `user` limits. Its seat-tier, group, and organization-level
 * defaults are configured in claude.ai. A Claude Console organization sets `organization` and
 * `workspace` limits, which are monthly and always carry an amount. Setting those limits is in an
 * early access preview. To request access, contact your Anthropic account team.
 */
class SpendLimitSetParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Limit amount as a non-negative integer decimal string in the minor unit of the organization's
     * billing currency (cents for USD): "50000" is $500.00. `null` sets an explicit no-limit
     * override for this scope and `period` only — each period resolves independently, so caps for
     * other periods still apply.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun amount(): Optional<String> = body.amount()

    /**
     * What the limit applies to. Claude Enterprise organizations set `user` limits. Claude Console
     * organizations set `organization` and `workspace` limits. Any other combination returns 400.
     * Setting `organization` and `workspace` limits through the API is in an early access preview.
     * To request access, contact your Anthropic account team.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun scope(): Scope = body.scope()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun period(): Optional<BetaSpendLimitPeriod> = body.period()

    /**
     * Returns the raw JSON value of [amount].
     *
     * Unlike [amount], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _amount(): JsonField<String> = body._amount()

    /**
     * Returns the raw JSON value of [scope].
     *
     * Unlike [scope], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _scope(): JsonField<Scope> = body._scope()

    /**
     * Returns the raw JSON value of [period].
     *
     * Unlike [period], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _period(): JsonField<BetaSpendLimitPeriod> = body._period()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SpendLimitSetParams].
         *
         * The following fields are required:
         * ```java
         * .amount()
         * .scope()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SpendLimitSetParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(spendLimitSetParams: SpendLimitSetParams) = apply {
            betas = spendLimitSetParams.betas?.toMutableList()
            body = spendLimitSetParams.body.toBuilder()
            additionalHeaders = spendLimitSetParams.additionalHeaders.toBuilder()
            additionalQueryParams = spendLimitSetParams.additionalQueryParams.toBuilder()
        }

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [amount]
         * - [scope]
         * - [period]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * Limit amount as a non-negative integer decimal string in the minor unit of the
         * organization's billing currency (cents for USD): "50000" is $500.00. `null` sets an
         * explicit no-limit override for this scope and `period` only — each period resolves
         * independently, so caps for other periods still apply.
         */
        fun amount(amount: String?) = apply { body.amount(amount) }

        /** Alias for calling [Builder.amount] with `amount.orElse(null)`. */
        fun amount(amount: Optional<String>) = amount(amount.getOrNull())

        /**
         * Sets [Builder.amount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.amount] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun amount(amount: JsonField<String>) = apply { body.amount(amount) }

        /**
         * What the limit applies to. Claude Enterprise organizations set `user` limits. Claude
         * Console organizations set `organization` and `workspace` limits. Any other combination
         * returns 400. Setting `organization` and `workspace` limits through the API is in an early
         * access preview. To request access, contact your Anthropic account team.
         */
        fun scope(scope: Scope) = apply { body.scope(scope) }

        /**
         * Sets [Builder.scope] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scope] with a well-typed [Scope] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun scope(scope: JsonField<Scope>) = apply { body.scope(scope) }

        /** Alias for calling [scope] with `Scope.ofUser(user)`. */
        fun scope(user: BetaSpendLimitUserScope) = apply { body.scope(user) }

        /**
         * Alias for calling [scope] with the following:
         * ```java
         * BetaSpendLimitUserScope.builder()
         *     .userId(userId)
         *     .build()
         * ```
         */
        fun userScope(userId: String) = apply { body.userScope(userId) }

        /** Alias for calling [scope] with `Scope.ofOrganization(organization)`. */
        fun scope(organization: BetaSpendLimitOrganizationScope) = apply {
            body.scope(organization)
        }

        /** Alias for calling [scope] with `Scope.ofWorkspace(workspace)`. */
        fun scope(workspace: BetaSpendLimitWorkspaceScope) = apply { body.scope(workspace) }

        /**
         * Alias for calling [scope] with the following:
         * ```java
         * BetaSpendLimitWorkspaceScope.builder()
         *     .workspaceId(workspaceId)
         *     .build()
         * ```
         */
        fun workspaceScope(workspaceId: String) = apply { body.workspaceScope(workspaceId) }

        fun period(period: BetaSpendLimitPeriod) = apply { body.period(period) }

        /**
         * Sets [Builder.period] to an arbitrary JSON value.
         *
         * You should usually call [Builder.period] with a well-typed [BetaSpendLimitPeriod] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun period(period: JsonField<BetaSpendLimitPeriod>) = apply { body.period(period) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [SpendLimitSetParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .amount()
         * .scope()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SpendLimitSetParams =
            SpendLimitSetParams(
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.let {
                    if (it.isNotEmpty()) {
                        put("anthropic-beta", it.joinToString(","))
                    }
                }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val amount: JsonField<String>,
        private val scope: JsonField<Scope>,
        private val period: JsonField<BetaSpendLimitPeriod>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("amount") @ExcludeMissing amount: JsonField<String> = JsonMissing.of(),
            @JsonProperty("scope") @ExcludeMissing scope: JsonField<Scope> = JsonMissing.of(),
            @JsonProperty("period")
            @ExcludeMissing
            period: JsonField<BetaSpendLimitPeriod> = JsonMissing.of(),
        ) : this(amount, scope, period, mutableMapOf())

        /**
         * Limit amount as a non-negative integer decimal string in the minor unit of the
         * organization's billing currency (cents for USD): "50000" is $500.00. `null` sets an
         * explicit no-limit override for this scope and `period` only — each period resolves
         * independently, so caps for other periods still apply.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun amount(): Optional<String> = amount.getOptional("amount")

        /**
         * What the limit applies to. Claude Enterprise organizations set `user` limits. Claude
         * Console organizations set `organization` and `workspace` limits. Any other combination
         * returns 400. Setting `organization` and `workspace` limits through the API is in an early
         * access preview. To request access, contact your Anthropic account team.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun scope(): Scope = scope.getRequired("scope")

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun period(): Optional<BetaSpendLimitPeriod> = period.getOptional("period")

        /**
         * Returns the raw JSON value of [amount].
         *
         * Unlike [amount], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("amount") @ExcludeMissing fun _amount(): JsonField<String> = amount

        /**
         * Returns the raw JSON value of [scope].
         *
         * Unlike [scope], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("scope") @ExcludeMissing fun _scope(): JsonField<Scope> = scope

        /**
         * Returns the raw JSON value of [period].
         *
         * Unlike [period], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("period")
        @ExcludeMissing
        fun _period(): JsonField<BetaSpendLimitPeriod> = period

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
             * Returns a mutable builder for constructing an instance of [Body].
             *
             * The following fields are required:
             * ```java
             * .amount()
             * .scope()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var amount: JsonField<String>? = null
            private var scope: JsonField<Scope>? = null
            private var period: JsonField<BetaSpendLimitPeriod> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                amount = body.amount
                scope = body.scope
                period = body.period
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * Limit amount as a non-negative integer decimal string in the minor unit of the
             * organization's billing currency (cents for USD): "50000" is $500.00. `null` sets an
             * explicit no-limit override for this scope and `period` only — each period resolves
             * independently, so caps for other periods still apply.
             */
            fun amount(amount: String?) = amount(JsonField.ofNullable(amount))

            /** Alias for calling [Builder.amount] with `amount.orElse(null)`. */
            fun amount(amount: Optional<String>) = amount(amount.getOrNull())

            /**
             * Sets [Builder.amount] to an arbitrary JSON value.
             *
             * You should usually call [Builder.amount] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun amount(amount: JsonField<String>) = apply { this.amount = amount }

            /**
             * What the limit applies to. Claude Enterprise organizations set `user` limits. Claude
             * Console organizations set `organization` and `workspace` limits. Any other
             * combination returns 400. Setting `organization` and `workspace` limits through the
             * API is in an early access preview. To request access, contact your Anthropic account
             * team.
             */
            fun scope(scope: Scope) = scope(JsonField.of(scope))

            /**
             * Sets [Builder.scope] to an arbitrary JSON value.
             *
             * You should usually call [Builder.scope] with a well-typed [Scope] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
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

            fun period(period: BetaSpendLimitPeriod) = period(JsonField.of(period))

            /**
             * Sets [Builder.period] to an arbitrary JSON value.
             *
             * You should usually call [Builder.period] with a well-typed [BetaSpendLimitPeriod]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun period(period: JsonField<BetaSpendLimitPeriod>) = apply { this.period = period }

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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .amount()
             * .scope()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("amount", amount),
                    checkRequired("scope", scope),
                    period,
                    additionalProperties.toMutableMap(),
                )
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
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            amount()
            scope().validate()
            period().ifPresent { it.validate() }
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
            (if (amount.asKnown().isPresent) 1 else 0) +
                (scope.asKnown().getOrNull()?.validity() ?: 0) +
                (period.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                amount == other.amount &&
                scope == other.scope &&
                period == other.period &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(amount, scope, period, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{amount=$amount, scope=$scope, period=$period, additionalProperties=$additionalProperties}"
    }

    /**
     * What the limit applies to. Claude Enterprise organizations set `user` limits. Claude Console
     * organizations set `organization` and `workspace` limits. Any other combination returns 400.
     * Setting `organization` and `workspace` limits through the API is in an early access preview.
     * To request access, contact your Anthropic account team.
     */
    @JsonDeserialize(using = Scope.Deserializer::class)
    @JsonSerialize(using = Scope.Serializer::class)
    class Scope
    private constructor(
        private val user: BetaSpendLimitUserScope? = null,
        private val organization: BetaSpendLimitOrganizationScope? = null,
        private val workspace: BetaSpendLimitWorkspaceScope? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            when {
                user != null -> Type.USER
                organization != null -> Type.ORGANIZATION
                workspace != null -> Type.WORKSPACE
                else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }

        /** Scope selecting a single member of the organization. */
        fun user(): Optional<BetaSpendLimitUserScope> = Optional.ofNullable(user)

        fun organization(): Optional<BetaSpendLimitOrganizationScope> =
            Optional.ofNullable(organization)

        /** Scope selecting one workspace of a Claude Console organization. */
        fun workspace(): Optional<BetaSpendLimitWorkspaceScope> = Optional.ofNullable(workspace)

        fun isUser(): Boolean = user != null

        fun isOrganization(): Boolean = organization != null

        fun isWorkspace(): Boolean = workspace != null

        /** Scope selecting a single member of the organization. */
        fun asUser(): BetaSpendLimitUserScope = user.getOrThrow("user")

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
                organization == other.organization &&
                workspace == other.workspace
        }

        override fun hashCode(): Int = Objects.hash(user, organization, workspace)

        override fun toString(): String =
            when {
                user != null -> "Scope{user=$user}"
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

                @JvmField val ORGANIZATION = Type(JsonField.of("organization"))

                @JvmField val WORKSPACE = Type(JsonField.of("workspace"))

                @JvmStatic
                fun of(value: String): Type =
                    // Intern known values so `==` works
                    when (value) {
                        "user" -> USER
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

        return other is SpendLimitSetParams &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "SpendLimitSetParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
