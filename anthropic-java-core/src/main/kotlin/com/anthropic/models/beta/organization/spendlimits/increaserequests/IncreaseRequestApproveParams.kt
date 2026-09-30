package com.anthropic.models.beta.organization.spendlimits.increaserequests

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitPeriod
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Approve a pending spend limit increase request.
 *
 * Writes a per-user spend limit at `amount` for the requester and transitions the request to
 * `approved`. `period` defaults to the period the member was blocked on. Anthropic emails the
 * requester unless `suppress_notification` is set.
 */
class IncreaseRequestApproveParams
private constructor(
    private val spendLimitIncreaseRequestId: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** ID of the spend limit increase request. */
    fun spendLimitIncreaseRequestId(): Optional<String> =
        Optional.ofNullable(spendLimitIncreaseRequestId)

    /**
     * New per-user spend limit as a non-negative integer decimal string (minor units).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun amount(): String = body.amount()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun period(): Optional<BetaSpendLimitPeriod> = body.period()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun suppressNotification(): Optional<Boolean> = body.suppressNotification()

    /**
     * Returns the raw JSON value of [amount].
     *
     * Unlike [amount], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _amount(): JsonField<String> = body._amount()

    /**
     * Returns the raw JSON value of [period].
     *
     * Unlike [period], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _period(): JsonField<BetaSpendLimitPeriod> = body._period()

    /**
     * Returns the raw JSON value of [suppressNotification].
     *
     * Unlike [suppressNotification], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _suppressNotification(): JsonField<Boolean> = body._suppressNotification()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [IncreaseRequestApproveParams].
         *
         * The following fields are required:
         * ```java
         * .amount()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [IncreaseRequestApproveParams]. */
    class Builder internal constructor() {

        private var spendLimitIncreaseRequestId: String? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(increaseRequestApproveParams: IncreaseRequestApproveParams) = apply {
            spendLimitIncreaseRequestId = increaseRequestApproveParams.spendLimitIncreaseRequestId
            body = increaseRequestApproveParams.body.toBuilder()
            additionalHeaders = increaseRequestApproveParams.additionalHeaders.toBuilder()
            additionalQueryParams = increaseRequestApproveParams.additionalQueryParams.toBuilder()
        }

        /** ID of the spend limit increase request. */
        fun spendLimitIncreaseRequestId(spendLimitIncreaseRequestId: String?) = apply {
            this.spendLimitIncreaseRequestId = spendLimitIncreaseRequestId
        }

        /**
         * Alias for calling [Builder.spendLimitIncreaseRequestId] with
         * `spendLimitIncreaseRequestId.orElse(null)`.
         */
        fun spendLimitIncreaseRequestId(spendLimitIncreaseRequestId: Optional<String>) =
            spendLimitIncreaseRequestId(spendLimitIncreaseRequestId.getOrNull())

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [amount]
         * - [period]
         * - [suppressNotification]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** New per-user spend limit as a non-negative integer decimal string (minor units). */
        fun amount(amount: String) = apply { body.amount(amount) }

        /**
         * Sets [Builder.amount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.amount] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun amount(amount: JsonField<String>) = apply { body.amount(amount) }

        fun period(period: BetaSpendLimitPeriod?) = apply { body.period(period) }

        /** Alias for calling [Builder.period] with `period.orElse(null)`. */
        fun period(period: Optional<BetaSpendLimitPeriod>) = period(period.getOrNull())

        /**
         * Sets [Builder.period] to an arbitrary JSON value.
         *
         * You should usually call [Builder.period] with a well-typed [BetaSpendLimitPeriod] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun period(period: JsonField<BetaSpendLimitPeriod>) = apply { body.period(period) }

        fun suppressNotification(suppressNotification: Boolean) = apply {
            body.suppressNotification(suppressNotification)
        }

        /**
         * Sets [Builder.suppressNotification] to an arbitrary JSON value.
         *
         * You should usually call [Builder.suppressNotification] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun suppressNotification(suppressNotification: JsonField<Boolean>) = apply {
            body.suppressNotification(suppressNotification)
        }

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
         * Returns an immutable instance of [IncreaseRequestApproveParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .amount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): IncreaseRequestApproveParams =
            IncreaseRequestApproveParams(
                spendLimitIncreaseRequestId,
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> spendLimitIncreaseRequestId ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val amount: JsonField<String>,
        private val period: JsonField<BetaSpendLimitPeriod>,
        private val suppressNotification: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("amount") @ExcludeMissing amount: JsonField<String> = JsonMissing.of(),
            @JsonProperty("period")
            @ExcludeMissing
            period: JsonField<BetaSpendLimitPeriod> = JsonMissing.of(),
            @JsonProperty("suppress_notification")
            @ExcludeMissing
            suppressNotification: JsonField<Boolean> = JsonMissing.of(),
        ) : this(amount, period, suppressNotification, mutableMapOf())

        /**
         * New per-user spend limit as a non-negative integer decimal string (minor units).
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun amount(): String = amount.getRequired("amount")

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun period(): Optional<BetaSpendLimitPeriod> = period.getOptional("period")

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun suppressNotification(): Optional<Boolean> =
            suppressNotification.getOptional("suppress_notification")

        /**
         * Returns the raw JSON value of [amount].
         *
         * Unlike [amount], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("amount") @ExcludeMissing fun _amount(): JsonField<String> = amount

        /**
         * Returns the raw JSON value of [period].
         *
         * Unlike [period], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("period")
        @ExcludeMissing
        fun _period(): JsonField<BetaSpendLimitPeriod> = period

        /**
         * Returns the raw JSON value of [suppressNotification].
         *
         * Unlike [suppressNotification], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("suppress_notification")
        @ExcludeMissing
        fun _suppressNotification(): JsonField<Boolean> = suppressNotification

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
             * ```
             */
            @JvmStatic fun builder() = Builder()

            /**
             * Returns an immutable instance of [Body] with the required [amount] set to the given
             * value.
             */
            @JvmStatic fun of(amount: String) = builder().amount(amount).build()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var amount: JsonField<String>? = null
            private var period: JsonField<BetaSpendLimitPeriod> = JsonMissing.of()
            private var suppressNotification: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                amount = body.amount
                period = body.period
                suppressNotification = body.suppressNotification
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /** New per-user spend limit as a non-negative integer decimal string (minor units). */
            fun amount(amount: String) = amount(JsonField.of(amount))

            /**
             * Sets [Builder.amount] to an arbitrary JSON value.
             *
             * You should usually call [Builder.amount] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun amount(amount: JsonField<String>) = apply { this.amount = amount }

            fun period(period: BetaSpendLimitPeriod?) = period(JsonField.ofNullable(period))

            /** Alias for calling [Builder.period] with `period.orElse(null)`. */
            fun period(period: Optional<BetaSpendLimitPeriod>) = period(period.getOrNull())

            /**
             * Sets [Builder.period] to an arbitrary JSON value.
             *
             * You should usually call [Builder.period] with a well-typed [BetaSpendLimitPeriod]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun period(period: JsonField<BetaSpendLimitPeriod>) = apply { this.period = period }

            fun suppressNotification(suppressNotification: Boolean) =
                suppressNotification(JsonField.of(suppressNotification))

            /**
             * Sets [Builder.suppressNotification] to an arbitrary JSON value.
             *
             * You should usually call [Builder.suppressNotification] with a well-typed [Boolean]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun suppressNotification(suppressNotification: JsonField<Boolean>) = apply {
                this.suppressNotification = suppressNotification
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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .amount()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("amount", amount),
                    period,
                    suppressNotification,
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
            period().ifPresent { it.validate() }
            suppressNotification()
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
                (period.asKnown().getOrNull()?.validity() ?: 0) +
                (if (suppressNotification.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                amount == other.amount &&
                period == other.period &&
                suppressNotification == other.suppressNotification &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(amount, period, suppressNotification, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{amount=$amount, period=$period, suppressNotification=$suppressNotification, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is IncreaseRequestApproveParams &&
            spendLimitIncreaseRequestId == other.spendLimitIncreaseRequestId &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(spendLimitIncreaseRequestId, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "IncreaseRequestApproveParams{spendLimitIncreaseRequestId=$spendLimitIncreaseRequestId, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
