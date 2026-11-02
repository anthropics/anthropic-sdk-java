package com.anthropic.models.organization.spendlimits.effective

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.models.organization.spendlimits.SpendLimitPeriod
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * List each member's effective spend limit and period-to-date spend.
 *
 * Returns one row per (member, period) the member resolves a spend limit for, with the `source`
 * scope the spend limit was inherited from. Paginates by member, so a member's periods never split
 * across pages. Supported for Claude Enterprise organizations only, not for Claude Console
 * organizations.
 */
class EffectiveListParams
private constructor(
    private val limit: Long?,
    private val page: String?,
    private val period: List<SpendLimitPeriod>?,
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
    fun period(): Optional<List<SpendLimitPeriod>> = Optional.ofNullable(period)

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
        private var period: MutableList<SpendLimitPeriod>? = null
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
        fun period(period: List<SpendLimitPeriod>?) = apply {
            this.period = period?.toMutableList()
        }

        /** Alias for calling [Builder.period] with `period.orElse(null)`. */
        fun period(period: Optional<List<SpendLimitPeriod>>) = period(period.getOrNull())

        /**
         * Adds a single [SpendLimitPeriod] to [Builder.period].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPeriod(period: SpendLimitPeriod) = apply {
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

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.organization.spend_limits.effective.list",
            route = "/v1/organizations/spend_limits/effective",
            queryParams = listOf("limit", "page", "period", "user_ids"),
        )

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
