package com.anthropic.models.beta.organization.rbacgroups

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.RbacGroupService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see RbacGroupService.list */
class RbacGroupListPage
private constructor(
    private val service: RbacGroupService,
    private val params: RbacGroupListParams,
    private val response: RbacGroupListPageResponse,
) : Page<BetaRbacGroup> {

    /**
     * Delegates to [RbacGroupListPageResponse], but gracefully handles missing data.
     *
     * @see RbacGroupListPageResponse.data
     */
    fun data(): List<BetaRbacGroup> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [RbacGroupListPageResponse], but gracefully handles missing data.
     *
     * @see RbacGroupListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaRbacGroup> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): RbacGroupListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): RbacGroupListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaRbacGroup> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): RbacGroupListParams = params

    /** The response that this page was parsed from. */
    fun response(): RbacGroupListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [RbacGroupListPage].
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

    /** A builder for [RbacGroupListPage]. */
    class Builder internal constructor() {

        private var service: RbacGroupService? = null
        private var params: RbacGroupListParams? = null
        private var response: RbacGroupListPageResponse? = null

        @JvmSynthetic
        internal fun from(rbacGroupListPage: RbacGroupListPage) = apply {
            service = rbacGroupListPage.service
            params = rbacGroupListPage.params
            response = rbacGroupListPage.response
        }

        fun service(service: RbacGroupService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: RbacGroupListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: RbacGroupListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [RbacGroupListPage].
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
        fun build(): RbacGroupListPage =
            RbacGroupListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RbacGroupListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "RbacGroupListPage{service=$service, params=$params, response=$response}"
}
