// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.users

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.UserService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see UserService.list */
class UserListPage
private constructor(
    private val service: UserService,
    private val params: UserListParams,
    private val response: UserListPageResponse,
) : Page<BetaOrganizationUser> {

    /**
     * Delegates to [UserListPageResponse], but gracefully handles missing data.
     *
     * @see UserListPageResponse.data
     */
    fun data(): List<BetaOrganizationUser> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [UserListPageResponse], but gracefully handles missing data.
     *
     * @see UserListPageResponse.hasMore
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [UserListPageResponse], but gracefully handles missing data.
     *
     * @see UserListPageResponse.firstId
     */
    fun firstId(): Optional<String> = response._firstId().getOptional("first_id")

    /**
     * Delegates to [UserListPageResponse], but gracefully handles missing data.
     *
     * @see UserListPageResponse.lastId
     */
    fun lastId(): Optional<String> = response._lastId().getOptional("last_id")

    override fun items(): List<BetaOrganizationUser> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && lastId().isPresent

    fun nextPageParams(): UserListParams {
        val nextCursor =
            lastId().getOrNull() ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().afterId(nextCursor).build()
    }

    override fun nextPage(): UserListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaOrganizationUser> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): UserListParams = params

    /** The response that this page was parsed from. */
    fun response(): UserListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UserListPage].
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

    /** A builder for [UserListPage]. */
    class Builder internal constructor() {

        private var service: UserService? = null
        private var params: UserListParams? = null
        private var response: UserListPageResponse? = null

        @JvmSynthetic
        internal fun from(userListPage: UserListPage) = apply {
            service = userListPage.service
            params = userListPage.params
            response = userListPage.response
        }

        fun service(service: UserService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: UserListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: UserListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [UserListPage].
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
        fun build(): UserListPage =
            UserListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "UserListPage{service=$service, params=$params, response=$response}"
}
