package com.anthropic.models.beta.organization.spendlimits.increaserequests

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
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimit
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitPeriod
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitScopedApiKeyActor
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitUserActor
import com.anthropic.models.beta.organization.spendlimits.BetaSpendSummary
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

class IncreaseRequestApproveResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val actor: JsonField<Actor>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val period: JsonField<BetaSpendLimitPeriod>,
    private val resolvedAt: JsonField<OffsetDateTime>,
    private val resolvedBy: JsonField<ResolvedBy>,
    private val spendLimit: JsonField<BetaSpendLimit>,
    private val spendSummary: JsonField<BetaSpendSummary>,
    private val status: JsonField<BetaSpendLimitIncreaseRequestStatus>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("actor") @ExcludeMissing actor: JsonField<Actor> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("period")
        @ExcludeMissing
        period: JsonField<BetaSpendLimitPeriod> = JsonMissing.of(),
        @JsonProperty("resolved_at")
        @ExcludeMissing
        resolvedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("resolved_by")
        @ExcludeMissing
        resolvedBy: JsonField<ResolvedBy> = JsonMissing.of(),
        @JsonProperty("spend_limit")
        @ExcludeMissing
        spendLimit: JsonField<BetaSpendLimit> = JsonMissing.of(),
        @JsonProperty("spend_summary")
        @ExcludeMissing
        spendSummary: JsonField<BetaSpendSummary> = JsonMissing.of(),
        @JsonProperty("status")
        @ExcludeMissing
        status: JsonField<BetaSpendLimitIncreaseRequestStatus> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        id,
        actor,
        createdAt,
        period,
        resolvedAt,
        resolvedBy,
        spendLimit,
        spendSummary,
        status,
        type,
        mutableMapOf(),
    )

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun actor(): Actor = actor.getRequired("actor")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun period(): BetaSpendLimitPeriod = period.getRequired("period")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun resolvedAt(): Optional<OffsetDateTime> = resolvedAt.getOptional("resolved_at")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun resolvedBy(): Optional<ResolvedBy> = resolvedBy.getOptional("resolved_by")

    /**
     * A configured spend limit: a cap on metered spend for one scope and period.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun spendLimit(): BetaSpendLimit = spendLimit.getRequired("spend_limit")

    /**
     * Per-member effective-limit report row (`GET /spend_limits/effective`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun spendSummary(): Optional<BetaSpendSummary> = spendSummary.getOptional("spend_summary")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): BetaSpendLimitIncreaseRequestStatus = status.getRequired("status")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("spend_limit_increase_request")
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
     * Returns the raw JSON value of [actor].
     *
     * Unlike [actor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("actor") @ExcludeMissing fun _actor(): JsonField<Actor> = actor

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [period].
     *
     * Unlike [period], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("period") @ExcludeMissing fun _period(): JsonField<BetaSpendLimitPeriod> = period

    /**
     * Returns the raw JSON value of [resolvedAt].
     *
     * Unlike [resolvedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("resolved_at")
    @ExcludeMissing
    fun _resolvedAt(): JsonField<OffsetDateTime> = resolvedAt

    /**
     * Returns the raw JSON value of [resolvedBy].
     *
     * Unlike [resolvedBy], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("resolved_by")
    @ExcludeMissing
    fun _resolvedBy(): JsonField<ResolvedBy> = resolvedBy

    /**
     * Returns the raw JSON value of [spendLimit].
     *
     * Unlike [spendLimit], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("spend_limit")
    @ExcludeMissing
    fun _spendLimit(): JsonField<BetaSpendLimit> = spendLimit

    /**
     * Returns the raw JSON value of [spendSummary].
     *
     * Unlike [spendSummary], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("spend_summary")
    @ExcludeMissing
    fun _spendSummary(): JsonField<BetaSpendSummary> = spendSummary

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status")
    @ExcludeMissing
    fun _status(): JsonField<BetaSpendLimitIncreaseRequestStatus> = status

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
         * [IncreaseRequestApproveResponse].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .actor()
         * .createdAt()
         * .period()
         * .resolvedAt()
         * .resolvedBy()
         * .spendLimit()
         * .spendSummary()
         * .status()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [IncreaseRequestApproveResponse]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var actor: JsonField<Actor>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var period: JsonField<BetaSpendLimitPeriod>? = null
        private var resolvedAt: JsonField<OffsetDateTime>? = null
        private var resolvedBy: JsonField<ResolvedBy>? = null
        private var spendLimit: JsonField<BetaSpendLimit>? = null
        private var spendSummary: JsonField<BetaSpendSummary>? = null
        private var status: JsonField<BetaSpendLimitIncreaseRequestStatus>? = null
        private var type: JsonValue = JsonValue.from("spend_limit_increase_request")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(increaseRequestApproveResponse: IncreaseRequestApproveResponse) = apply {
            id = increaseRequestApproveResponse.id
            actor = increaseRequestApproveResponse.actor
            createdAt = increaseRequestApproveResponse.createdAt
            period = increaseRequestApproveResponse.period
            resolvedAt = increaseRequestApproveResponse.resolvedAt
            resolvedBy = increaseRequestApproveResponse.resolvedBy
            spendLimit = increaseRequestApproveResponse.spendLimit
            spendSummary = increaseRequestApproveResponse.spendSummary
            status = increaseRequestApproveResponse.status
            type = increaseRequestApproveResponse.type
            additionalProperties =
                increaseRequestApproveResponse.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

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

        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        fun period(period: BetaSpendLimitPeriod) = period(JsonField.of(period))

        /**
         * Sets [Builder.period] to an arbitrary JSON value.
         *
         * You should usually call [Builder.period] with a well-typed [BetaSpendLimitPeriod] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun period(period: JsonField<BetaSpendLimitPeriod>) = apply { this.period = period }

        fun resolvedAt(resolvedAt: OffsetDateTime?) = resolvedAt(JsonField.ofNullable(resolvedAt))

        /** Alias for calling [Builder.resolvedAt] with `resolvedAt.orElse(null)`. */
        fun resolvedAt(resolvedAt: Optional<OffsetDateTime>) = resolvedAt(resolvedAt.getOrNull())

        /**
         * Sets [Builder.resolvedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.resolvedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun resolvedAt(resolvedAt: JsonField<OffsetDateTime>) = apply {
            this.resolvedAt = resolvedAt
        }

        fun resolvedBy(resolvedBy: ResolvedBy?) = resolvedBy(JsonField.ofNullable(resolvedBy))

        /** Alias for calling [Builder.resolvedBy] with `resolvedBy.orElse(null)`. */
        fun resolvedBy(resolvedBy: Optional<ResolvedBy>) = resolvedBy(resolvedBy.getOrNull())

        /**
         * Sets [Builder.resolvedBy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.resolvedBy] with a well-typed [ResolvedBy] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun resolvedBy(resolvedBy: JsonField<ResolvedBy>) = apply { this.resolvedBy = resolvedBy }

        /** Alias for calling [resolvedBy] with `ResolvedBy.ofUserActor(userActor)`. */
        fun resolvedBy(userActor: BetaSpendLimitUserActor) =
            resolvedBy(ResolvedBy.ofUserActor(userActor))

        /**
         * Alias for calling [resolvedBy] with `ResolvedBy.ofScopedApiKeyActor(scopedApiKeyActor)`.
         */
        fun resolvedBy(scopedApiKeyActor: BetaSpendLimitScopedApiKeyActor) =
            resolvedBy(ResolvedBy.ofScopedApiKeyActor(scopedApiKeyActor))

        /**
         * Alias for calling [resolvedBy] with the following:
         * ```java
         * BetaSpendLimitScopedApiKeyActor.builder()
         *     .scopedApiKeyId(scopedApiKeyId)
         *     .build()
         * ```
         */
        fun scopedApiKeyActorResolvedBy(scopedApiKeyId: String) =
            resolvedBy(
                BetaSpendLimitScopedApiKeyActor.builder().scopedApiKeyId(scopedApiKeyId).build()
            )

        /** A configured spend limit: a cap on metered spend for one scope and period. */
        fun spendLimit(spendLimit: BetaSpendLimit) = spendLimit(JsonField.of(spendLimit))

        /**
         * Sets [Builder.spendLimit] to an arbitrary JSON value.
         *
         * You should usually call [Builder.spendLimit] with a well-typed [BetaSpendLimit] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun spendLimit(spendLimit: JsonField<BetaSpendLimit>) = apply {
            this.spendLimit = spendLimit
        }

        /** Per-member effective-limit report row (`GET /spend_limits/effective`). */
        fun spendSummary(spendSummary: BetaSpendSummary?) =
            spendSummary(JsonField.ofNullable(spendSummary))

        /** Alias for calling [Builder.spendSummary] with `spendSummary.orElse(null)`. */
        fun spendSummary(spendSummary: Optional<BetaSpendSummary>) =
            spendSummary(spendSummary.getOrNull())

        /**
         * Sets [Builder.spendSummary] to an arbitrary JSON value.
         *
         * You should usually call [Builder.spendSummary] with a well-typed [BetaSpendSummary] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun spendSummary(spendSummary: JsonField<BetaSpendSummary>) = apply {
            this.spendSummary = spendSummary
        }

        fun status(status: BetaSpendLimitIncreaseRequestStatus) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed
         * [BetaSpendLimitIncreaseRequestStatus] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<BetaSpendLimitIncreaseRequestStatus>) = apply {
            this.status = status
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("spend_limit_increase_request")
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
         * Returns an immutable instance of [IncreaseRequestApproveResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .actor()
         * .createdAt()
         * .period()
         * .resolvedAt()
         * .resolvedBy()
         * .spendLimit()
         * .spendSummary()
         * .status()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): IncreaseRequestApproveResponse =
            IncreaseRequestApproveResponse(
                checkRequired("id", id),
                checkRequired("actor", actor),
                checkRequired("createdAt", createdAt),
                checkRequired("period", period),
                checkRequired("resolvedAt", resolvedAt),
                checkRequired("resolvedBy", resolvedBy),
                checkRequired("spendLimit", spendLimit),
                checkRequired("spendSummary", spendSummary),
                checkRequired("status", status),
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
    fun validate(): IncreaseRequestApproveResponse = apply {
        if (validated) {
            return@apply
        }

        id()
        actor().validate()
        createdAt()
        period().validate()
        resolvedAt()
        resolvedBy().ifPresent { it.validate() }
        spendLimit().validate()
        spendSummary().ifPresent { it.validate() }
        status().validate()
        _type().let {
            if (it != JsonValue.from("spend_limit_increase_request")) {
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
            (actor.asKnown().getOrNull()?.validity() ?: 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (period.asKnown().getOrNull()?.validity() ?: 0) +
            (if (resolvedAt.asKnown().isPresent) 1 else 0) +
            (resolvedBy.asKnown().getOrNull()?.validity() ?: 0) +
            (spendLimit.asKnown().getOrNull()?.validity() ?: 0) +
            (spendSummary.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("spend_limit_increase_request")) 1 else 0 }

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

    @JsonDeserialize(using = ResolvedBy.Deserializer::class)
    @JsonSerialize(using = ResolvedBy.Serializer::class)
    class ResolvedBy
    private constructor(
        private val userActor: BetaSpendLimitUserActor? = null,
        private val scopedApiKeyActor: BetaSpendLimitScopedApiKeyActor? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            when {
                userActor != null -> Type.USER_ACTOR
                scopedApiKeyActor != null -> Type.SCOPED_API_KEY_ACTOR
                else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }

        /**
         * A user within the organization. `name` and `email_address` are null when the underlying
         * account is unavailable or has been deleted; `deleted` is true only for deleted accounts.
         */
        fun userActor(): Optional<BetaSpendLimitUserActor> = Optional.ofNullable(userActor)

        /** A scoped Admin API key acting on behalf of the organization. */
        fun scopedApiKeyActor(): Optional<BetaSpendLimitScopedApiKeyActor> =
            Optional.ofNullable(scopedApiKeyActor)

        fun isUserActor(): Boolean = userActor != null

        fun isScopedApiKeyActor(): Boolean = scopedApiKeyActor != null

        /**
         * A user within the organization. `name` and `email_address` are null when the underlying
         * account is unavailable or has been deleted; `deleted` is true only for deleted accounts.
         */
        fun asUserActor(): BetaSpendLimitUserActor = userActor.getOrThrow("userActor")

        /** A scoped Admin API key acting on behalf of the organization. */
        fun asScopedApiKeyActor(): BetaSpendLimitScopedApiKeyActor =
            scopedApiKeyActor.getOrThrow("scopedApiKeyActor")

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
         * Optional<String> result = resolvedBy.accept(new ResolvedBy.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitUserActor(BetaSpendLimitUserActor userActor) {
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
                scopedApiKeyActor != null -> visitor.visitScopedApiKeyActor(scopedApiKeyActor)
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
        fun validate(): ResolvedBy = apply {
            if (validated) {
                return@apply
            }

            when {
                userActor != null -> userActor.validate()
                scopedApiKeyActor != null -> scopedApiKeyActor.validate()
                else -> throw AnthropicInvalidDataException("Unknown ResolvedBy: $_json")
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
                userActor != null -> userActor.validity()
                scopedApiKeyActor != null -> scopedApiKeyActor.validity()
                else -> 0
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ResolvedBy &&
                userActor == other.userActor &&
                scopedApiKeyActor == other.scopedApiKeyActor
        }

        override fun hashCode(): Int = Objects.hash(userActor, scopedApiKeyActor)

        override fun toString(): String =
            when {
                userActor != null -> "ResolvedBy{userActor=$userActor}"
                scopedApiKeyActor != null -> "ResolvedBy{scopedApiKeyActor=$scopedApiKeyActor}"
                _json != null -> "ResolvedBy{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ResolvedBy")
            }

        companion object {

            /**
             * A user within the organization. `name` and `email_address` are null when the
             * underlying account is unavailable or has been deleted; `deleted` is true only for
             * deleted accounts.
             */
            @JvmStatic
            fun ofUserActor(userActor: BetaSpendLimitUserActor) = ResolvedBy(userActor = userActor)

            /** A scoped Admin API key acting on behalf of the organization. */
            @JvmStatic
            fun ofScopedApiKeyActor(scopedApiKeyActor: BetaSpendLimitScopedApiKeyActor) =
                ResolvedBy(scopedApiKeyActor = scopedApiKeyActor)

            /**
             * Returns an immutable instance of [ResolvedBy] whose [ofScopedApiKeyActor] variant is
             * built from the given required [scopedApiKeyId].
             */
            @JvmStatic
            fun ofScopedApiKeyActor(scopedApiKeyId: String) =
                ofScopedApiKeyActor(BetaSpendLimitScopedApiKeyActor.of(scopedApiKeyId))
        }

        /**
         * An interface that defines how to map each variant of [ResolvedBy] to a value of type [T].
         */
        interface Visitor<out T> {

            /**
             * A user within the organization. `name` and `email_address` are null when the
             * underlying account is unavailable or has been deleted; `deleted` is true only for
             * deleted accounts.
             */
            fun visitUserActor(userActor: BetaSpendLimitUserActor): T

            /** A scoped Admin API key acting on behalf of the organization. */
            fun visitScopedApiKeyActor(scopedApiKeyActor: BetaSpendLimitScopedApiKeyActor): T

            /**
             * Maps an unknown variant of [ResolvedBy] to a value of type [T].
             *
             * An instance of [ResolvedBy] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown ResolvedBy: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<ResolvedBy>(ResolvedBy::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): ResolvedBy {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "user_actor" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaSpendLimitUserActor>())
                            ?.let { ResolvedBy(userActor = it, _json = json) }
                            ?: ResolvedBy(_json = json)
                    }
                    "scoped_api_key_actor" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaSpendLimitScopedApiKeyActor>(),
                            )
                            ?.let { ResolvedBy(scopedApiKeyActor = it, _json = json) }
                            ?: ResolvedBy(_json = json)
                    }
                }

                return ResolvedBy(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<ResolvedBy>(ResolvedBy::class) {

            override fun serialize(
                value: ResolvedBy,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.userActor != null -> generator.writeObject(value.userActor)
                    value.scopedApiKeyActor != null ->
                        generator.writeObject(value.scopedApiKeyActor)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid ResolvedBy")
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is IncreaseRequestApproveResponse &&
            id == other.id &&
            actor == other.actor &&
            createdAt == other.createdAt &&
            period == other.period &&
            resolvedAt == other.resolvedAt &&
            resolvedBy == other.resolvedBy &&
            spendLimit == other.spendLimit &&
            spendSummary == other.spendSummary &&
            status == other.status &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            actor,
            createdAt,
            period,
            resolvedAt,
            resolvedBy,
            spendLimit,
            spendSummary,
            status,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "IncreaseRequestApproveResponse{id=$id, actor=$actor, createdAt=$createdAt, period=$period, resolvedAt=$resolvedAt, resolvedBy=$resolvedBy, spendLimit=$spendLimit, spendSummary=$spendSummary, status=$status, type=$type, additionalProperties=$additionalProperties}"
}
