// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.invites

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.InviteService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see InviteService.list */
class InviteListPage
private constructor(
    private val service: InviteService,
    private val params: InviteListParams,
    private val response: InviteListPageResponse,
) : Page<BetaOrganizationInvite> {

    /**
     * Delegates to [InviteListPageResponse], but gracefully handles missing data.
     *
     * @see InviteListPageResponse.data
     */
    fun data(): List<BetaOrganizationInvite> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [InviteListPageResponse], but gracefully handles missing data.
     *
     * @see InviteListPageResponse.hasMore
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [InviteListPageResponse], but gracefully handles missing data.
     *
     * @see InviteListPageResponse.firstId
     */
    fun firstId(): Optional<String> = response._firstId().getOptional("first_id")

    /**
     * Delegates to [InviteListPageResponse], but gracefully handles missing data.
     *
     * @see InviteListPageResponse.lastId
     */
    fun lastId(): Optional<String> = response._lastId().getOptional("last_id")

    override fun items(): List<BetaOrganizationInvite> = data()

    override fun hasNextPage(): Boolean {
        if (items().isEmpty()) {
            return false
        }
        if (hasMore().getOrNull() == false) {
            return false
        }

        return if (params.beforeId().isPresent) firstId().isPresent else lastId().isPresent
    }

    fun nextPageParams(): InviteListParams {
        if (params.beforeId().isPresent) {
            val previousCursor =
                firstId().getOrNull()
                    ?: throw IllegalStateException("Cannot construct next page params")
            return params.toBuilder().beforeId(previousCursor).build()
        }
        val nextCursor =
            lastId().getOrNull() ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().afterId(nextCursor).build()
    }

    override fun nextPage(): InviteListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaOrganizationInvite> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): InviteListParams = params

    /** The response that this page was parsed from. */
    fun response(): InviteListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [InviteListPage].
         *
         * The following fields are required:
         * ```java
         * .service()
         * .params()
         * .response()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [InviteListPage]. */
    class Builder internal constructor() {

        private var service: InviteService? = null
        private var params: InviteListParams? = null
        private var response: InviteListPageResponse? = null

        @JvmSynthetic
        internal fun from(inviteListPage: InviteListPage) = apply {
            service = inviteListPage.service
            params = inviteListPage.params
            response = inviteListPage.response
        }

        fun service(service: InviteService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: InviteListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: InviteListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [InviteListPage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .service()
         * .params()
         * .response()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): InviteListPage =
            InviteListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is InviteListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "InviteListPage{service=$service, params=$params, response=$response}"
}
