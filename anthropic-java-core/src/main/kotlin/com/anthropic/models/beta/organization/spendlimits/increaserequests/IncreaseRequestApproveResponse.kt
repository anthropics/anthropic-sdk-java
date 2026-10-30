package com.anthropic.models.beta.organization.spendlimits.increaserequests

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class IncreaseRequestApproveResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val actor: JsonField<BetaSpendLimitIncreaseRequestActor>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val period: JsonField<BetaSpendLimitPeriod>,
    private val resolvedAt: JsonField<OffsetDateTime>,
    private val resolvedBy: JsonField<BetaSpendLimitIncreaseRequestResolvedBy>,
    private val spendLimit: JsonField<BetaSpendLimit>,
    private val spendSummary: JsonField<BetaSpendSummary>,
    private val status: JsonField<BetaSpendLimitIncreaseRequestStatus>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("actor")
        @ExcludeMissing
        actor: JsonField<BetaSpendLimitIncreaseRequestActor> = JsonMissing.of(),
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
        resolvedBy: JsonField<BetaSpendLimitIncreaseRequestResolvedBy> = JsonMissing.of(),
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
     * Who submitted the increase request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun actor(): BetaSpendLimitIncreaseRequestActor = actor.getRequired("actor")

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
     * Who approved or denied the increase request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun resolvedBy(): Optional<BetaSpendLimitIncreaseRequestResolvedBy> =
        resolvedBy.getOptional("resolved_by")

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
    @JsonProperty("actor")
    @ExcludeMissing
    fun _actor(): JsonField<BetaSpendLimitIncreaseRequestActor> = actor

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
    fun _resolvedBy(): JsonField<BetaSpendLimitIncreaseRequestResolvedBy> = resolvedBy

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
        private var actor: JsonField<BetaSpendLimitIncreaseRequestActor>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var period: JsonField<BetaSpendLimitPeriod>? = null
        private var resolvedAt: JsonField<OffsetDateTime>? = null
        private var resolvedBy: JsonField<BetaSpendLimitIncreaseRequestResolvedBy>? = null
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

        /** Who submitted the increase request. */
        fun actor(actor: BetaSpendLimitIncreaseRequestActor) = actor(JsonField.of(actor))

        /**
         * Sets [Builder.actor] to an arbitrary JSON value.
         *
         * You should usually call [Builder.actor] with a well-typed
         * [BetaSpendLimitIncreaseRequestActor] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun actor(actor: JsonField<BetaSpendLimitIncreaseRequestActor>) = apply {
            this.actor = actor
        }

        /** Alias for calling [actor] with `BetaSpendLimitIncreaseRequestActor.ofUser(user)`. */
        fun actor(user: BetaSpendLimitUserActor) =
            actor(BetaSpendLimitIncreaseRequestActor.ofUser(user))

        /**
         * Alias for calling [actor] with
         * `BetaSpendLimitIncreaseRequestActor.ofScopedApiKey(scopedApiKey)`.
         */
        fun actor(scopedApiKey: BetaSpendLimitScopedApiKeyActor) =
            actor(BetaSpendLimitIncreaseRequestActor.ofScopedApiKey(scopedApiKey))

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

        /** Who approved or denied the increase request. */
        fun resolvedBy(resolvedBy: BetaSpendLimitIncreaseRequestResolvedBy?) =
            resolvedBy(JsonField.ofNullable(resolvedBy))

        /** Alias for calling [Builder.resolvedBy] with `resolvedBy.orElse(null)`. */
        fun resolvedBy(resolvedBy: Optional<BetaSpendLimitIncreaseRequestResolvedBy>) =
            resolvedBy(resolvedBy.getOrNull())

        /**
         * Sets [Builder.resolvedBy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.resolvedBy] with a well-typed
         * [BetaSpendLimitIncreaseRequestResolvedBy] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun resolvedBy(resolvedBy: JsonField<BetaSpendLimitIncreaseRequestResolvedBy>) = apply {
            this.resolvedBy = resolvedBy
        }

        /**
         * Alias for calling [resolvedBy] with
         * `BetaSpendLimitIncreaseRequestResolvedBy.ofUserActor(userActor)`.
         */
        fun resolvedBy(userActor: BetaSpendLimitUserActor) =
            resolvedBy(BetaSpendLimitIncreaseRequestResolvedBy.ofUserActor(userActor))

        /**
         * Alias for calling [resolvedBy] with
         * `BetaSpendLimitIncreaseRequestResolvedBy.ofScopedApiKeyActor(scopedApiKeyActor)`.
         */
        fun resolvedBy(scopedApiKeyActor: BetaSpendLimitScopedApiKeyActor) =
            resolvedBy(
                BetaSpendLimitIncreaseRequestResolvedBy.ofScopedApiKeyActor(scopedApiKeyActor)
            )

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
