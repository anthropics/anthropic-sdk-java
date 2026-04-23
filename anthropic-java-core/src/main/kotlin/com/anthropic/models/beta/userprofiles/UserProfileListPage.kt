// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.userprofiles

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.UserProfileService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see UserProfileService.list */
class UserProfileListPage
private constructor(
    private val service: UserProfileService,
    private val params: UserProfileListParams,
    private val response: UserProfileListPageResponse,
) : Page<BetaUserProfile> {

    /**
     * Delegates to [UserProfileListPageResponse], but gracefully handles missing data.
     *
     * @see UserProfileListPageResponse.data
     */
    fun data(): List<BetaUserProfile> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [UserProfileListPageResponse], but gracefully handles missing data.
     *
     * @see UserProfileListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaUserProfile> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): UserProfileListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): UserProfileListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaUserProfile> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): UserProfileListParams = params

    /** The response that this page was parsed from. */
    fun response(): UserProfileListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UserProfileListPage].
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

    /** A builder for [UserProfileListPage]. */
    class Builder internal constructor() {

        private var service: UserProfileService? = null
        private var params: UserProfileListParams? = null
        private var response: UserProfileListPageResponse? = null

        @JvmSynthetic
        internal fun from(userProfileListPage: UserProfileListPage) = apply {
            service = userProfileListPage.service
            params = userProfileListPage.params
            response = userProfileListPage.response
        }

        fun service(service: UserProfileService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: UserProfileListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: UserProfileListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [UserProfileListPage].
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
        fun build(): UserProfileListPage =
            UserProfileListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfileListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "UserProfileListPage{service=$service, params=$params, response=$response}"
}
