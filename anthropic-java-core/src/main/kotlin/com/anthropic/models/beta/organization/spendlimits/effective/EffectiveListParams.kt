package com.anthropic.models.beta.organization.spendlimits.effective

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * List each member's effective spend limit and period-to-date spend.
 *
 * Returns one row per (member, period) the member resolves a spend limit for, with the `source`
 * scope the spend limit was inherited from. Paginates by member, so a member's periods never split
 * across pages.
 */
class EffectiveListParams
private constructor(
    private val limit: Long?,
    private val page: String?,
    private val period: List<Period>?,
    private val userIds: List<String>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * Maximum number of members per page. A member's period rows never split across pages, so a
     * page may carry more rows than this. Defaults to `20`.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /** Opaque cursor from a previous response's `next_page` field. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /**
     * Restrict the report to these limit periods. Omit to return one row per period each member
     * resolves a spend limit for.
     */
    fun period(): Optional<List<Period>> = Optional.ofNullable(period)

    /**
     * Restrict the report to these members, by tagged user ID (`user_...`). At most 100 entries.
     */
    fun userIds(): Optional<List<String>> = Optional.ofNullable(userIds)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): EffectiveListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [EffectiveListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [EffectiveListParams]. */
    class Builder internal constructor() {

        private var limit: Long? = null
        private var page: String? = null
        private var period: MutableList<Period>? = null
        private var userIds: MutableList<String>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(effectiveListParams: EffectiveListParams) = apply {
            limit = effectiveListParams.limit
            page = effectiveListParams.page
            period = effectiveListParams.period?.toMutableList()
            userIds = effectiveListParams.userIds?.toMutableList()
            additionalHeaders = effectiveListParams.additionalHeaders.toBuilder()
            additionalQueryParams = effectiveListParams.additionalQueryParams.toBuilder()
        }

        /**
         * Maximum number of members per page. A member's period rows never split across pages, so a
         * page may carry more rows than this. Defaults to `20`.
         */
        fun limit(limit: Long?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Long) = limit(limit as Long?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Long>) = limit(limit.getOrNull())

        /** Opaque cursor from a previous response's `next_page` field. */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /**
         * Restrict the report to these limit periods. Omit to return one row per period each member
         * resolves a spend limit for.
         */
        fun period(period: List<Period>?) = apply { this.period = period?.toMutableList() }

        /** Alias for calling [Builder.period] with `period.orElse(null)`. */
        fun period(period: Optional<List<Period>>) = period(period.getOrNull())

        /**
         * Adds a single [Period] to [Builder.period].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPeriod(period: Period) = apply {
            this.period = (this.period ?: mutableListOf()).apply { add(period) }
        }

        /**
         * Restrict the report to these members, by tagged user ID (`user_...`). At most 100
         * entries.
         */
        fun userIds(userIds: List<String>?) = apply { this.userIds = userIds?.toMutableList() }

        /** Alias for calling [Builder.userIds] with `userIds.orElse(null)`. */
        fun userIds(userIds: Optional<List<String>>) = userIds(userIds.getOrNull())

        /**
         * Adds a single [String] to [userIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addUserId(userId: String) = apply {
            userIds = (userIds ?: mutableListOf()).apply { add(userId) }
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
         * Returns an immutable instance of [EffectiveListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): EffectiveListParams =
            EffectiveListParams(
                limit,
                page,
                period?.toImmutable(),
                userIds?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                limit?.let { put("limit", it.toString()) }
                page?.let { put("page", it) }
                period?.forEach { put("period[]", it.toString()) }
                userIds?.forEach { put("user_ids[]", it) }
                putAll(additionalQueryParams)
            }
            .build()

    class Period private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val DAILY = Period(JsonField.of("daily"))

            @JvmField val MONTHLY = Period(JsonField.of("monthly"))

            @JvmField val WEEKLY = Period(JsonField.of("weekly"))

            @JvmStatic
            fun of(value: String): Period =
                // Intern known values so `==` works
                when (value) {
                    "daily" -> DAILY
                    "monthly" -> MONTHLY
                    "weekly" -> WEEKLY
                    else -> Period(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Period =
                value.asString().getOrNull()?.let { of(it) } ?: Period(value)
        }

        /** An enum containing [Period]'s known values. */
        enum class Known {
            DAILY,
            MONTHLY,
            WEEKLY,
        }

        /**
         * An enum containing [Period]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Period] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            DAILY,
            MONTHLY,
            WEEKLY,
            /** An enum member indicating that [Period] was instantiated with an unknown value. */
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
                DAILY -> Value.DAILY
                MONTHLY -> Value.MONTHLY
                WEEKLY -> Value.WEEKLY
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
                DAILY -> Known.DAILY
                MONTHLY -> Known.MONTHLY
                WEEKLY -> Known.WEEKLY
                else -> throw AnthropicInvalidDataException("Unknown Period: $value")
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
        fun validate(): Period = apply {
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

            return other is Period && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is EffectiveListParams &&
            limit == other.limit &&
            page == other.page &&
            period == other.period &&
            userIds == other.userIds &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(limit, page, period, userIds, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "EffectiveListParams{limit=$limit, page=$page, period=$period, userIds=$userIds, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
