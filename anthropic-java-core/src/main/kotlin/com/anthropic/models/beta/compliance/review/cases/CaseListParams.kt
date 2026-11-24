package com.anthropic.models.beta.compliance.review.cases

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
 * List the review cases of every organization that the API key can reach, newest first by default.
 * Both open and closed cases are returned unless `status` is set.
 *
 * To sync, store the largest `updated_at` you have read, and send it as the at-or-after filter on
 * `updated_at`, sorted by `updated_at` ascending. That finds new cases, newly closed cases, and
 * cases with a new notice. Read every page, match results by `id`, and store the new largest value
 * after the last page.
 *
 * A request with the `anthropic-user-profile-id` header and no `user_profile_id` parameter returns
 * a 400 error.
 *
 * An Admin API key with the `read:compliance_review_cases` scope can call this endpoint.
 */
class CaseListParams
private constructor(
    private val createdAtGt: OffsetDateTime?,
    private val createdAtGte: OffsetDateTime?,
    private val createdAtLt: OffsetDateTime?,
    private val createdAtLte: OffsetDateTime?,
    private val limit: Int?,
    private val order: BetaComplianceReviewOrder?,
    private val orderBy: BetaComplianceReviewCaseOrderBy?,
    private val page: String?,
    private val status: BetaComplianceReviewCaseStatusFilter?,
    private val updatedAtGt: OffsetDateTime?,
    private val updatedAtGte: OffsetDateTime?,
    private val updatedAtLt: OffsetDateTime?,
    private val updatedAtLte: OffsetDateTime?,
    private val userProfileId: String?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Return only cases whose `created_at` is after this time. */
    fun createdAtGt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGt)

    /** Return only cases whose `created_at` is at or after this time. */
    fun createdAtGte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGte)

    /** Return only cases whose `created_at` is before this time. */
    fun createdAtLt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLt)

    /** Return only cases whose `created_at` is at or before this time. */
    fun createdAtLte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLte)

    /** Number of cases to return per page, between 1 and 100. Defaults to 20. */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /** Sort direction, by `order_by` and then by `id`. Defaults to `desc`. */
    fun order(): Optional<BetaComplianceReviewOrder> = Optional.ofNullable(order)

    /** The field to sort by. Defaults to `created_at`. */
    fun orderBy(): Optional<BetaComplianceReviewCaseOrderBy> = Optional.ofNullable(orderBy)

    /**
     * Opaque cursor from a previous response's `next_page`. Leave it out to get the first page. A
     * cursor works only with the filters and sort order of the request that returned it.
     */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** Return only cases with this status. Leave it out to get both open and closed cases. */
    fun status(): Optional<BetaComplianceReviewCaseStatusFilter> = Optional.ofNullable(status)

    /** Return only cases whose `updated_at` is after this time. */
    fun updatedAtGt(): Optional<OffsetDateTime> = Optional.ofNullable(updatedAtGt)

    /**
     * Return cases whose `updated_at` is at or after this time. Cases slightly older than this time
     * are also returned, so that a sync misses no change.
     */
    fun updatedAtGte(): Optional<OffsetDateTime> = Optional.ofNullable(updatedAtGte)

    /** Return only cases whose `updated_at` is before this time. */
    fun updatedAtLt(): Optional<OffsetDateTime> = Optional.ofNullable(updatedAtLt)

    /** Return only cases whose `updated_at` is at or before this time. */
    fun updatedAtLte(): Optional<OffsetDateTime> = Optional.ofNullable(updatedAtLte)

    /**
     * Return only cases where a message's `user_profile_id` is this ID, including cases that also
     * have messages of other user profiles. A case stops matching once its messages'
     * `user_profile_id` is null. An ID that matches no reachable case returns no cases. A request
     * that sends the `anthropic-user-profile-id` header without this parameter returns a 400 error.
     */
    fun userProfileId(): Optional<String> = Optional.ofNullable(userProfileId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): CaseListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [CaseListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CaseListParams]. */
    class Builder internal constructor() {

        private var createdAtGt: OffsetDateTime? = null
        private var createdAtGte: OffsetDateTime? = null
        private var createdAtLt: OffsetDateTime? = null
        private var createdAtLte: OffsetDateTime? = null
        private var limit: Int? = null
        private var order: BetaComplianceReviewOrder? = null
        private var orderBy: BetaComplianceReviewCaseOrderBy? = null
        private var page: String? = null
        private var status: BetaComplianceReviewCaseStatusFilter? = null
        private var updatedAtGt: OffsetDateTime? = null
        private var updatedAtGte: OffsetDateTime? = null
        private var updatedAtLt: OffsetDateTime? = null
        private var updatedAtLte: OffsetDateTime? = null
        private var userProfileId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(caseListParams: CaseListParams) = apply {
            createdAtGt = caseListParams.createdAtGt
            createdAtGte = caseListParams.createdAtGte
            createdAtLt = caseListParams.createdAtLt
            createdAtLte = caseListParams.createdAtLte
            limit = caseListParams.limit
            order = caseListParams.order
            orderBy = caseListParams.orderBy
            page = caseListParams.page
            status = caseListParams.status
            updatedAtGt = caseListParams.updatedAtGt
            updatedAtGte = caseListParams.updatedAtGte
            updatedAtLt = caseListParams.updatedAtLt
            updatedAtLte = caseListParams.updatedAtLte
            userProfileId = caseListParams.userProfileId
            betas = caseListParams.betas?.toMutableList()
            additionalHeaders = caseListParams.additionalHeaders.toBuilder()
            additionalQueryParams = caseListParams.additionalQueryParams.toBuilder()
        }

        /** Return only cases whose `created_at` is after this time. */
        fun createdAtGt(createdAtGt: OffsetDateTime?) = apply { this.createdAtGt = createdAtGt }

        /** Alias for calling [Builder.createdAtGt] with `createdAtGt.orElse(null)`. */
        fun createdAtGt(createdAtGt: Optional<OffsetDateTime>) =
            createdAtGt(createdAtGt.getOrNull())

        /** Return only cases whose `created_at` is at or after this time. */
        fun createdAtGte(createdAtGte: OffsetDateTime?) = apply { this.createdAtGte = createdAtGte }

        /** Alias for calling [Builder.createdAtGte] with `createdAtGte.orElse(null)`. */
        fun createdAtGte(createdAtGte: Optional<OffsetDateTime>) =
            createdAtGte(createdAtGte.getOrNull())

        /** Return only cases whose `created_at` is before this time. */
        fun createdAtLt(createdAtLt: OffsetDateTime?) = apply { this.createdAtLt = createdAtLt }

        /** Alias for calling [Builder.createdAtLt] with `createdAtLt.orElse(null)`. */
        fun createdAtLt(createdAtLt: Optional<OffsetDateTime>) =
            createdAtLt(createdAtLt.getOrNull())

        /** Return only cases whose `created_at` is at or before this time. */
        fun createdAtLte(createdAtLte: OffsetDateTime?) = apply { this.createdAtLte = createdAtLte }

        /** Alias for calling [Builder.createdAtLte] with `createdAtLte.orElse(null)`. */
        fun createdAtLte(createdAtLte: Optional<OffsetDateTime>) =
            createdAtLte(createdAtLte.getOrNull())

        /** Number of cases to return per page, between 1 and 100. Defaults to 20. */
        fun limit(limit: Int?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Int) = limit(limit as Int?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Int>) = limit(limit.getOrNull())

        /** Sort direction, by `order_by` and then by `id`. Defaults to `desc`. */
        fun order(order: BetaComplianceReviewOrder?) = apply { this.order = order }

        /** Alias for calling [Builder.order] with `order.orElse(null)`. */
        fun order(order: Optional<BetaComplianceReviewOrder>) = order(order.getOrNull())

        /** The field to sort by. Defaults to `created_at`. */
        fun orderBy(orderBy: BetaComplianceReviewCaseOrderBy?) = apply { this.orderBy = orderBy }

        /** Alias for calling [Builder.orderBy] with `orderBy.orElse(null)`. */
        fun orderBy(orderBy: Optional<BetaComplianceReviewCaseOrderBy>) =
            orderBy(orderBy.getOrNull())

        /**
         * Opaque cursor from a previous response's `next_page`. Leave it out to get the first page.
         * A cursor works only with the filters and sort order of the request that returned it.
         */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /** Return only cases with this status. Leave it out to get both open and closed cases. */
        fun status(status: BetaComplianceReviewCaseStatusFilter?) = apply { this.status = status }

        /** Alias for calling [Builder.status] with `status.orElse(null)`. */
        fun status(status: Optional<BetaComplianceReviewCaseStatusFilter>) =
            status(status.getOrNull())

        /** Return only cases whose `updated_at` is after this time. */
        fun updatedAtGt(updatedAtGt: OffsetDateTime?) = apply { this.updatedAtGt = updatedAtGt }

        /** Alias for calling [Builder.updatedAtGt] with `updatedAtGt.orElse(null)`. */
        fun updatedAtGt(updatedAtGt: Optional<OffsetDateTime>) =
            updatedAtGt(updatedAtGt.getOrNull())

        /**
         * Return cases whose `updated_at` is at or after this time. Cases slightly older than this
         * time are also returned, so that a sync misses no change.
         */
        fun updatedAtGte(updatedAtGte: OffsetDateTime?) = apply { this.updatedAtGte = updatedAtGte }

        /** Alias for calling [Builder.updatedAtGte] with `updatedAtGte.orElse(null)`. */
        fun updatedAtGte(updatedAtGte: Optional<OffsetDateTime>) =
            updatedAtGte(updatedAtGte.getOrNull())

        /** Return only cases whose `updated_at` is before this time. */
        fun updatedAtLt(updatedAtLt: OffsetDateTime?) = apply { this.updatedAtLt = updatedAtLt }

        /** Alias for calling [Builder.updatedAtLt] with `updatedAtLt.orElse(null)`. */
        fun updatedAtLt(updatedAtLt: Optional<OffsetDateTime>) =
            updatedAtLt(updatedAtLt.getOrNull())

        /** Return only cases whose `updated_at` is at or before this time. */
        fun updatedAtLte(updatedAtLte: OffsetDateTime?) = apply { this.updatedAtLte = updatedAtLte }

        /** Alias for calling [Builder.updatedAtLte] with `updatedAtLte.orElse(null)`. */
        fun updatedAtLte(updatedAtLte: Optional<OffsetDateTime>) =
            updatedAtLte(updatedAtLte.getOrNull())

        /**
         * Return only cases where a message's `user_profile_id` is this ID, including cases that
         * also have messages of other user profiles. A case stops matching once its messages'
         * `user_profile_id` is null. An ID that matches no reachable case returns no cases. A
         * request that sends the `anthropic-user-profile-id` header without this parameter returns
         * a 400 error.
         */
        fun userProfileId(userProfileId: String?) = apply { this.userProfileId = userProfileId }

        /** Alias for calling [Builder.userProfileId] with `userProfileId.orElse(null)`. */
        fun userProfileId(userProfileId: Optional<String>) =
            userProfileId(userProfileId.getOrNull())

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
         * Returns an immutable instance of [CaseListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): CaseListParams =
            CaseListParams(
                createdAtGt,
                createdAtGte,
                createdAtLt,
                createdAtLte,
                limit,
                order,
                orderBy,
                page,
                status,
                updatedAtGt,
                updatedAtGte,
                updatedAtLt,
                updatedAtLte,
                userProfileId,
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
                orderBy?.let { put("order_by", it.toString()) }
                page?.let { put("page", it) }
                status?.let { put("status", it.toString()) }
                updatedAtGt?.let {
                    put("updated_at[gt]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                updatedAtGte?.let {
                    put("updated_at[gte]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                updatedAtLt?.let {
                    put("updated_at[lt]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                updatedAtLte?.let {
                    put("updated_at[lte]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                userProfileId?.let { put("user_profile_id", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.compliance.review.cases.list",
            route = "/v1/compliance/review/cases",
            queryParams =
                listOf(
                    "created_at[gt]",
                    "created_at[gte]",
                    "created_at[lt]",
                    "created_at[lte]",
                    "limit",
                    "order",
                    "order_by",
                    "page",
                    "status",
                    "updated_at[gt]",
                    "updated_at[gte]",
                    "updated_at[lt]",
                    "updated_at[lte]",
                    "user_profile_id",
                ),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CaseListParams &&
            createdAtGt == other.createdAtGt &&
            createdAtGte == other.createdAtGte &&
            createdAtLt == other.createdAtLt &&
            createdAtLte == other.createdAtLte &&
            limit == other.limit &&
            order == other.order &&
            orderBy == other.orderBy &&
            page == other.page &&
            status == other.status &&
            updatedAtGt == other.updatedAtGt &&
            updatedAtGte == other.updatedAtGte &&
            updatedAtLt == other.updatedAtLt &&
            updatedAtLte == other.updatedAtLte &&
            userProfileId == other.userProfileId &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            createdAtGt,
            createdAtGte,
            createdAtLt,
            createdAtLte,
            limit,
            order,
            orderBy,
            page,
            status,
            updatedAtGt,
            updatedAtGte,
            updatedAtLt,
            updatedAtLte,
            userProfileId,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "CaseListParams{createdAtGt=$createdAtGt, createdAtGte=$createdAtGte, createdAtLt=$createdAtLt, createdAtLte=$createdAtLte, limit=$limit, order=$order, orderBy=$orderBy, page=$page, status=$status, updatedAtGt=$updatedAtGt, updatedAtGte=$updatedAtGte, updatedAtLt=$updatedAtLt, updatedAtLte=$updatedAtLte, userProfileId=$userProfileId, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
