package com.anthropic.models.beta.organization.analytics.users

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUserActivity
import com.anthropic.services.blocking.beta.organization.analytics.UserService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see UserService.list */
class UserListPage
private constructor(
    private val service: UserService,
    private val params: UserListParams,
    private val response: UserListPageResponse,
) : Page<BetaAnalyticsUserActivity> {

    /**
     * Delegates to [UserListPageResponse], but gracefully handles missing data.
     *
     * @see UserListPageResponse.data
     */
    fun data(): List<BetaAnalyticsUserActivity> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [UserListPageResponse], but gracefully handles missing data.
     *
     * @see UserListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsUserActivity> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): UserListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): UserListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsUserActivity> = AutoPager.from(this)

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
