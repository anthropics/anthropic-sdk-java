package com.anthropic.models.userprofiles

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** List User Profiles */
class UserProfileListParams
private constructor(
    private val enforcementStatus: List<UserProfileEnforcementStatus>?,
    private val limit: Int?,
    private val order: UserProfileListOrder?,
    private val orderBy: UserProfileListOrderBy?,
    private val page: String?,
    private val workspaceId: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * Return only the user profiles whose `enforcement_status` is one of these values:
     * `unrestricted`, `pending`, `restricted` or `banned`. Repeat the parameter to match any of
     * several. With it set, a page may hold fewer than `limit` profiles, or none, and still carry a
     * `next_page`: the service examines a bounded number of profiles per request, and only a null
     * `next_page` means the listing is complete. Rejected with `invalid_request_error` under the
     * `user-profiles-2026-09-04` and earlier beta headers. Fails with `overloaded_error` when a
     * profile's enforcement actions cannot be read for the moment.
     */
    fun enforcementStatus(): Optional<List<UserProfileEnforcementStatus>> =
        Optional.ofNullable(enforcementStatus)

    /** The maximum number of user profiles to return, from 1 to 100. Defaults to 20. */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /** The sort direction, applied to the field that `order_by` selects. Defaults to `desc`. */
    fun order(): Optional<UserProfileListOrder> = Optional.ofNullable(order)

    /**
     * The field to sort user profiles by, in the direction that `order` sets. Defaults to
     * `created_at`.
     */
    fun orderBy(): Optional<UserProfileListOrderBy> = Optional.ofNullable(orderBy)

    /**
     * The cursor for the page to return, taken from `next_page` in a previous response.
     *
     * Leave it out to get the first page.
     */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /**
     * Optional header to select the Workspace for this request. The value is a Workspace ID (for
     * example, `wrkspc_011CZkZaBF1tNoB5wlCeusgy`).
     *
     * Only needed for credentials that can act on more than one Workspace. A credential that
     * belongs to a specific Workspace may omit it; if sent, it must match that Workspace.
     */
    fun workspaceId(): Optional<String> = Optional.ofNullable(workspaceId)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): UserProfileListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [UserProfileListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserProfileListParams]. */
    class Builder internal constructor() {

        private var enforcementStatus: MutableList<UserProfileEnforcementStatus>? = null
        private var limit: Int? = null
        private var order: UserProfileListOrder? = null
        private var orderBy: UserProfileListOrderBy? = null
        private var page: String? = null
        private var workspaceId: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(userProfileListParams: UserProfileListParams) = apply {
            enforcementStatus = userProfileListParams.enforcementStatus?.toMutableList()
            limit = userProfileListParams.limit
            order = userProfileListParams.order
            orderBy = userProfileListParams.orderBy
            page = userProfileListParams.page
            workspaceId = userProfileListParams.workspaceId
            additionalHeaders = userProfileListParams.additionalHeaders.toBuilder()
            additionalQueryParams = userProfileListParams.additionalQueryParams.toBuilder()
        }

        /**
         * Return only the user profiles whose `enforcement_status` is one of these values:
         * `unrestricted`, `pending`, `restricted` or `banned`. Repeat the parameter to match any of
         * several. With it set, a page may hold fewer than `limit` profiles, or none, and still
         * carry a `next_page`: the service examines a bounded number of profiles per request, and
         * only a null `next_page` means the listing is complete. Rejected with
         * `invalid_request_error` under the `user-profiles-2026-09-04` and earlier beta headers.
         * Fails with `overloaded_error` when a profile's enforcement actions cannot be read for the
         * moment.
         */
        fun enforcementStatus(enforcementStatus: List<UserProfileEnforcementStatus>?) = apply {
            this.enforcementStatus = enforcementStatus?.toMutableList()
        }

        /** Alias for calling [Builder.enforcementStatus] with `enforcementStatus.orElse(null)`. */
        fun enforcementStatus(enforcementStatus: Optional<List<UserProfileEnforcementStatus>>) =
            enforcementStatus(enforcementStatus.getOrNull())

        /**
         * Adds a single [UserProfileEnforcementStatus] to [Builder.enforcementStatus].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addEnforcementStatus(enforcementStatus: UserProfileEnforcementStatus) = apply {
            this.enforcementStatus =
                (this.enforcementStatus ?: mutableListOf()).apply { add(enforcementStatus) }
        }

        /** The maximum number of user profiles to return, from 1 to 100. Defaults to 20. */
        fun limit(limit: Int?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Int) = limit(limit as Int?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Int>) = limit(limit.getOrNull())

        /** The sort direction, applied to the field that `order_by` selects. Defaults to `desc`. */
        fun order(order: UserProfileListOrder?) = apply { this.order = order }

        /** Alias for calling [Builder.order] with `order.orElse(null)`. */
        fun order(order: Optional<UserProfileListOrder>) = order(order.getOrNull())

        /**
         * The field to sort user profiles by, in the direction that `order` sets. Defaults to
         * `created_at`.
         */
        fun orderBy(orderBy: UserProfileListOrderBy?) = apply { this.orderBy = orderBy }

        /** Alias for calling [Builder.orderBy] with `orderBy.orElse(null)`. */
        fun orderBy(orderBy: Optional<UserProfileListOrderBy>) = orderBy(orderBy.getOrNull())

        /**
         * The cursor for the page to return, taken from `next_page` in a previous response.
         *
         * Leave it out to get the first page.
         */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /**
         * Optional header to select the Workspace for this request. The value is a Workspace ID
         * (for example, `wrkspc_011CZkZaBF1tNoB5wlCeusgy`).
         *
         * Only needed for credentials that can act on more than one Workspace. A credential that
         * belongs to a specific Workspace may omit it; if sent, it must match that Workspace.
         */
        fun workspaceId(workspaceId: String?) = apply { this.workspaceId = workspaceId }

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

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
         * Returns an immutable instance of [UserProfileListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): UserProfileListParams =
            UserProfileListParams(
                enforcementStatus?.toImmutable(),
                limit,
                order,
                orderBy,
                page,
                workspaceId,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                workspaceId?.let { put("anthropic-workspace-id", it) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                enforcementStatus?.forEach { put("enforcement_status[]", it.toString()) }
                limit?.let { put("limit", it.toString()) }
                order?.let { put("order", it.toString()) }
                orderBy?.let { put("order_by", it.toString()) }
                page?.let { put("page", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.user_profiles.list",
            route = "/v1/user_profiles",
            queryParams = listOf("enforcement_status", "limit", "order", "order_by", "page"),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfileListParams &&
            enforcementStatus == other.enforcementStatus &&
            limit == other.limit &&
            order == other.order &&
            orderBy == other.orderBy &&
            page == other.page &&
            workspaceId == other.workspaceId &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            enforcementStatus,
            limit,
            order,
            orderBy,
            page,
            workspaceId,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "UserProfileListParams{enforcementStatus=$enforcementStatus, limit=$limit, order=$order, orderBy=$orderBy, page=$page, workspaceId=$workspaceId, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
