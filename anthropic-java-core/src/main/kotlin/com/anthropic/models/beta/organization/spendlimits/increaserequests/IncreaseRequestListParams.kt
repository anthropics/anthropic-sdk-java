package com.anthropic.models.beta.organization.spendlimits.increaserequests

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * List spend limit increase requests, most recent first.
 *
 * Pending requests include a live `spend_summary` for the requester. Requests whose requester is no
 * longer a member are excluded.
 */
class IncreaseRequestListParams
private constructor(
    private val actorIds: List<String>?,
    private val limit: Long?,
    private val page: String?,
    private val status: List<BetaSpendLimitIncreaseRequestStatus>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Filter by requester, as `user_...` tagged IDs. */
    fun actorIds(): Optional<List<String>> = Optional.ofNullable(actorIds)

    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /** Opaque cursor from a previous response's `next_page`. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** Filter by status. Omit to return all. */
    fun status(): Optional<List<BetaSpendLimitIncreaseRequestStatus>> = Optional.ofNullable(status)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): IncreaseRequestListParams = builder().build()

        /**
         * Returns a mutable builder for constructing an instance of [IncreaseRequestListParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [IncreaseRequestListParams]. */
    class Builder internal constructor() {

        private var actorIds: MutableList<String>? = null
        private var limit: Long? = null
        private var page: String? = null
        private var status: MutableList<BetaSpendLimitIncreaseRequestStatus>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(increaseRequestListParams: IncreaseRequestListParams) = apply {
            actorIds = increaseRequestListParams.actorIds?.toMutableList()
            limit = increaseRequestListParams.limit
            page = increaseRequestListParams.page
            status = increaseRequestListParams.status?.toMutableList()
            additionalHeaders = increaseRequestListParams.additionalHeaders.toBuilder()
            additionalQueryParams = increaseRequestListParams.additionalQueryParams.toBuilder()
        }

        /** Filter by requester, as `user_...` tagged IDs. */
        fun actorIds(actorIds: List<String>?) = apply { this.actorIds = actorIds?.toMutableList() }

        /** Alias for calling [Builder.actorIds] with `actorIds.orElse(null)`. */
        fun actorIds(actorIds: Optional<List<String>>) = actorIds(actorIds.getOrNull())

        /**
         * Adds a single [String] to [actorIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addActorId(actorId: String) = apply {
            actorIds = (actorIds ?: mutableListOf()).apply { add(actorId) }
        }

        fun limit(limit: Long?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Long) = limit(limit as Long?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Long>) = limit(limit.getOrNull())

        /** Opaque cursor from a previous response's `next_page`. */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /** Filter by status. Omit to return all. */
        fun status(status: List<BetaSpendLimitIncreaseRequestStatus>?) = apply {
            this.status = status?.toMutableList()
        }

        /** Alias for calling [Builder.status] with `status.orElse(null)`. */
        fun status(status: Optional<List<BetaSpendLimitIncreaseRequestStatus>>) =
            status(status.getOrNull())

        /**
         * Adds a single [BetaSpendLimitIncreaseRequestStatus] to [Builder.status].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addStatus(status: BetaSpendLimitIncreaseRequestStatus) = apply {
            this.status = (this.status ?: mutableListOf()).apply { add(status) }
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
         * Returns an immutable instance of [IncreaseRequestListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): IncreaseRequestListParams =
            IncreaseRequestListParams(
                actorIds?.toImmutable(),
                limit,
                page,
                status?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                actorIds?.forEach { put("actor_ids[]", it) }
                limit?.let { put("limit", it.toString()) }
                page?.let { put("page", it) }
                status?.forEach { put("status[]", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is IncreaseRequestListParams &&
            actorIds == other.actorIds &&
            limit == other.limit &&
            page == other.page &&
            status == other.status &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(actorIds, limit, page, status, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "IncreaseRequestListParams{actorIds=$actorIds, limit=$limit, page=$page, status=$status, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
