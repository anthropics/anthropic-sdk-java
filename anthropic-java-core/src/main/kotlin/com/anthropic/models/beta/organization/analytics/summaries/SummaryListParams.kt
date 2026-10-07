package com.anthropic.models.beta.organization.analytics.summaries

import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import java.time.LocalDate
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Get organization-wide activity summaries for a date range.
 *
 * Returns one entry per day from `starting_date` (inclusive) to `ending_date` (exclusive) in
 * `data`, the same `data` / `next_page` envelope as the other analytics list endpoints; the series
 * is currently returned in full, so `next_page` is always null. Data is typically available with a
 * 1-day lag and may be revised by a few percent over the following days: when `ending_date` is
 * omitted it defaults to the most recent available day + 1, so the last entry covers the most
 * recent available day. The series can be scoped to an RBAC group via
 * `filter[]=rbac_group_id:{id}`. Available to organizations on a Claude Enterprise plan. Requires
 * an API key with the `read:analytics` scope.
 */
class SummaryListParams
private constructor(
    private val startingDate: LocalDate,
    private val endingDate: LocalDate?,
    private val filter: List<String>?,
    private val limit: Long?,
    private val page: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * UTC date in YYYY-MM-DD format. Start of the date range (inclusive). Data is typically
     * available with a 1-day lag (varies by query; the error for a too-recent date names the latest
     * available day) and may be revised by a few percent over the following days. No earlier than
     * 2026-01-01.
     */
    fun startingDate(): LocalDate = startingDate

    /**
     * UTC date in YYYY-MM-DD format. End of the date range (exclusive). Data is typically available
     * with a 1-day lag, so this can be at most today — which is also the default when omitted,
     * making the last entry cover the most recent available day. Data may be revised by a few
     * percent over the following days. The range may span at most 366 days.
     */
    fun endingDate(): Optional<LocalDate> = Optional.ofNullable(endingDate)

    /**
     * Filters as `dimension:value`. Only `rbac_group_id` is supported (e.g.
     * `filter[]=rbac_group_id:{id}`); repeat the param to OR across groups. Scopes the whole day
     * series to members of the matching group(s), re-aggregated from member-level activity —
     * org-wide seat/invite fields and the adoption rates derived from them are null on scoped rows.
     * `rbac_group_id` accepts the tagged id (`rbac_group_...`, as emitted in responses and by the
     * spend-limits API) or a bare group UUID, and matches users who held the group at any point
     * during each UTC day (time-of-usage attribution). At most 100 entries.
     */
    fun filter(): Optional<List<String>> = Optional.ofNullable(filter)

    /**
     * Number of results per page (1-1000, default 100). The day series (at most 366 entries) is
     * currently returned in full in a single page, so `limit` does not yet shorten it.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /**
     * Opaque cursor from a previous response's `next_page` field. `next_page` is currently always
     * null, so there is never a cursor to send.
     */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SummaryListParams].
         *
         * The following fields are required:
         * ```java
         * .startingDate()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SummaryListParams]. */
    class Builder internal constructor() {

        private var startingDate: LocalDate? = null
        private var endingDate: LocalDate? = null
        private var filter: MutableList<String>? = null
        private var limit: Long? = null
        private var page: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(summaryListParams: SummaryListParams) = apply {
            startingDate = summaryListParams.startingDate
            endingDate = summaryListParams.endingDate
            filter = summaryListParams.filter?.toMutableList()
            limit = summaryListParams.limit
            page = summaryListParams.page
            additionalHeaders = summaryListParams.additionalHeaders.toBuilder()
            additionalQueryParams = summaryListParams.additionalQueryParams.toBuilder()
        }

        /**
         * UTC date in YYYY-MM-DD format. Start of the date range (inclusive). Data is typically
         * available with a 1-day lag (varies by query; the error for a too-recent date names the
         * latest available day) and may be revised by a few percent over the following days. No
         * earlier than 2026-01-01.
         */
        fun startingDate(startingDate: LocalDate) = apply { this.startingDate = startingDate }

        /**
         * UTC date in YYYY-MM-DD format. End of the date range (exclusive). Data is typically
         * available with a 1-day lag, so this can be at most today — which is also the default when
         * omitted, making the last entry cover the most recent available day. Data may be revised
         * by a few percent over the following days. The range may span at most 366 days.
         */
        fun endingDate(endingDate: LocalDate?) = apply { this.endingDate = endingDate }

        /** Alias for calling [Builder.endingDate] with `endingDate.orElse(null)`. */
        fun endingDate(endingDate: Optional<LocalDate>) = endingDate(endingDate.getOrNull())

        /**
         * Filters as `dimension:value`. Only `rbac_group_id` is supported (e.g.
         * `filter[]=rbac_group_id:{id}`); repeat the param to OR across groups. Scopes the whole
         * day series to members of the matching group(s), re-aggregated from member-level activity
         * — org-wide seat/invite fields and the adoption rates derived from them are null on scoped
         * rows. `rbac_group_id` accepts the tagged id (`rbac_group_...`, as emitted in responses
         * and by the spend-limits API) or a bare group UUID, and matches users who held the group
         * at any point during each UTC day (time-of-usage attribution). At most 100 entries.
         */
        fun filter(filter: List<String>?) = apply { this.filter = filter?.toMutableList() }

        /** Alias for calling [Builder.filter] with `filter.orElse(null)`. */
        fun filter(filter: Optional<List<String>>) = filter(filter.getOrNull())

        /**
         * Adds a single [String] to [Builder.filter].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addFilter(filter: String) = apply {
            this.filter = (this.filter ?: mutableListOf()).apply { add(filter) }
        }

        /**
         * Number of results per page (1-1000, default 100). The day series (at most 366 entries) is
         * currently returned in full in a single page, so `limit` does not yet shorten it.
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

        /**
         * Opaque cursor from a previous response's `next_page` field. `next_page` is currently
         * always null, so there is never a cursor to send.
         */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

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
         * Returns an immutable instance of [SummaryListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .startingDate()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SummaryListParams =
            SummaryListParams(
                checkRequired("startingDate", startingDate),
                endingDate,
                filter?.toImmutable(),
                limit,
                page,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                put("starting_date", startingDate.toString())
                endingDate?.let { put("ending_date", it.toString()) }
                filter?.forEach { put("filter[]", it) }
                limit?.let { put("limit", it.toString()) }
                page?.let { put("page", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SummaryListParams &&
            startingDate == other.startingDate &&
            endingDate == other.endingDate &&
            filter == other.filter &&
            limit == other.limit &&
            page == other.page &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            startingDate,
            endingDate,
            filter,
            limit,
            page,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "SummaryListParams{startingDate=$startingDate, endingDate=$endingDate, filter=$filter, limit=$limit, page=$page, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
