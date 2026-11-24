package com.anthropic.models.beta.compliance.review.notices

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.compliance.review.BetaComplianceReviewOrder
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * List Anthropic's notices to the organizations that the API key can reach, newest first by
 * default. A notice is returned only if the API key can read every case in its `case_ids`.
 *
 * To poll, store the largest `created_at` you have read, and send it as the at-or-after filter on
 * `created_at`. Read every page, match results by `id`, and store the new largest value after the
 * last page.
 *
 * An Admin API key with the `read:compliance_review_cases` scope can call this endpoint.
 */
class NoticeListParams
private constructor(
    private val caseId: String?,
    private val createdAtGt: OffsetDateTime?,
    private val createdAtGte: OffsetDateTime?,
    private val createdAtLt: OffsetDateTime?,
    private val createdAtLte: OffsetDateTime?,
    private val limit: Int?,
    private val order: BetaComplianceReviewOrder?,
    private val page: String?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * Return only notices whose `case_ids` include this case (`rvc_...`). A case that the API key
     * can't read returns an empty list.
     */
    fun caseId(): Optional<String> = Optional.ofNullable(caseId)

    /** Return only notices whose `created_at` is after this time. */
    fun createdAtGt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGt)

    /**
     * Return notices whose `created_at` is at or after this time. Notices slightly older than this
     * time are also returned, so that a poll misses no notice.
     */
    fun createdAtGte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGte)

    /** Return only notices whose `created_at` is before this time. */
    fun createdAtLt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLt)

    /** Return only notices whose `created_at` is at or before this time. */
    fun createdAtLte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLte)

    /** Number of notices to return per page, between 1 and 100. Defaults to 20. */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /** Sort direction, by `created_at` and then by `id`. Defaults to `desc`. */
    fun order(): Optional<BetaComplianceReviewOrder> = Optional.ofNullable(order)

    /**
     * Opaque cursor from a previous response's `next_page`. Leave it out to get the first page. A
     * cursor works only with the filters and sort order of the request that returned it.
     */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): NoticeListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [NoticeListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [NoticeListParams]. */
    class Builder internal constructor() {

        private var caseId: String? = null
        private var createdAtGt: OffsetDateTime? = null
        private var createdAtGte: OffsetDateTime? = null
        private var createdAtLt: OffsetDateTime? = null
        private var createdAtLte: OffsetDateTime? = null
        private var limit: Int? = null
        private var order: BetaComplianceReviewOrder? = null
        private var page: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(noticeListParams: NoticeListParams) = apply {
            caseId = noticeListParams.caseId
            createdAtGt = noticeListParams.createdAtGt
            createdAtGte = noticeListParams.createdAtGte
            createdAtLt = noticeListParams.createdAtLt
            createdAtLte = noticeListParams.createdAtLte
            limit = noticeListParams.limit
            order = noticeListParams.order
            page = noticeListParams.page
            betas = noticeListParams.betas?.toMutableList()
            additionalHeaders = noticeListParams.additionalHeaders.toBuilder()
            additionalQueryParams = noticeListParams.additionalQueryParams.toBuilder()
        }

        /**
         * Return only notices whose `case_ids` include this case (`rvc_...`). A case that the API
         * key can't read returns an empty list.
         */
        fun caseId(caseId: String?) = apply { this.caseId = caseId }

        /** Alias for calling [Builder.caseId] with `caseId.orElse(null)`. */
        fun caseId(caseId: Optional<String>) = caseId(caseId.getOrNull())

        /** Return only notices whose `created_at` is after this time. */
        fun createdAtGt(createdAtGt: OffsetDateTime?) = apply { this.createdAtGt = createdAtGt }

        /** Alias for calling [Builder.createdAtGt] with `createdAtGt.orElse(null)`. */
        fun createdAtGt(createdAtGt: Optional<OffsetDateTime>) =
            createdAtGt(createdAtGt.getOrNull())

        /**
         * Return notices whose `created_at` is at or after this time. Notices slightly older than
         * this time are also returned, so that a poll misses no notice.
         */
        fun createdAtGte(createdAtGte: OffsetDateTime?) = apply { this.createdAtGte = createdAtGte }

        /** Alias for calling [Builder.createdAtGte] with `createdAtGte.orElse(null)`. */
        fun createdAtGte(createdAtGte: Optional<OffsetDateTime>) =
            createdAtGte(createdAtGte.getOrNull())

        /** Return only notices whose `created_at` is before this time. */
        fun createdAtLt(createdAtLt: OffsetDateTime?) = apply { this.createdAtLt = createdAtLt }

        /** Alias for calling [Builder.createdAtLt] with `createdAtLt.orElse(null)`. */
        fun createdAtLt(createdAtLt: Optional<OffsetDateTime>) =
            createdAtLt(createdAtLt.getOrNull())

        /** Return only notices whose `created_at` is at or before this time. */
        fun createdAtLte(createdAtLte: OffsetDateTime?) = apply { this.createdAtLte = createdAtLte }

        /** Alias for calling [Builder.createdAtLte] with `createdAtLte.orElse(null)`. */
        fun createdAtLte(createdAtLte: Optional<OffsetDateTime>) =
            createdAtLte(createdAtLte.getOrNull())

        /** Number of notices to return per page, between 1 and 100. Defaults to 20. */
        fun limit(limit: Int?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Int) = limit(limit as Int?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Int>) = limit(limit.getOrNull())

        /** Sort direction, by `created_at` and then by `id`. Defaults to `desc`. */
        fun order(order: BetaComplianceReviewOrder?) = apply { this.order = order }

        /** Alias for calling [Builder.order] with `order.orElse(null)`. */
        fun order(order: Optional<BetaComplianceReviewOrder>) = order(order.getOrNull())

        /**
         * Opaque cursor from a previous response's `next_page`. Leave it out to get the first page.
         * A cursor works only with the filters and sort order of the request that returned it.
         */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

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
         * Returns an immutable instance of [NoticeListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): NoticeListParams =
            NoticeListParams(
                caseId,
                createdAtGt,
                createdAtGte,
                createdAtLt,
                createdAtLte,
                limit,
                order,
                page,
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

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

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                caseId?.let { put("case_id", it) }
                createdAtGt?.let {
                    put("created_at[gt]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdAtGte?.let {
                    put("created_at[gte]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdAtLt?.let {
                    put("created_at[lt]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdAtLte?.let {
                    put("created_at[lte]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                limit?.let { put("limit", it.toString()) }
                order?.let { put("order", it.toString()) }
                page?.let { put("page", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.compliance.review.notices.list",
            route = "/v1/compliance/review/notices",
            queryParams =
                listOf(
                    "case_id",
                    "created_at[gt]",
                    "created_at[gte]",
                    "created_at[lt]",
                    "created_at[lte]",
                    "limit",
                    "order",
                    "page",
                ),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is NoticeListParams &&
            caseId == other.caseId &&
            createdAtGt == other.createdAtGt &&
            createdAtGte == other.createdAtGte &&
            createdAtLt == other.createdAtLt &&
            createdAtLte == other.createdAtLte &&
            limit == other.limit &&
            order == other.order &&
            page == other.page &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            caseId,
            createdAtGt,
            createdAtGte,
            createdAtLt,
            createdAtLte,
            limit,
            order,
            page,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "NoticeListParams{caseId=$caseId, createdAtGt=$createdAtGt, createdAtGte=$createdAtGte, createdAtLt=$createdAtLt, createdAtLte=$createdAtLte, limit=$limit, order=$order, page=$page, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
