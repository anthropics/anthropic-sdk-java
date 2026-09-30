package com.anthropic.models.beta.organization.rbacroles

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.RbacRoleService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see RbacRoleService.list */
class RbacRoleListPage
private constructor(
    private val service: RbacRoleService,
    private val params: RbacRoleListParams,
    private val response: RbacRoleListPageResponse,
) : Page<BetaRbacRole> {

    /**
     * Delegates to [RbacRoleListPageResponse], but gracefully handles missing data.
     *
     * @see RbacRoleListPageResponse.data
     */
    fun data(): List<BetaRbacRole> = response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [RbacRoleListPageResponse], but gracefully handles missing data.
     *
     * @see RbacRoleListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaRbacRole> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): RbacRoleListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): RbacRoleListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaRbacRole> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): RbacRoleListParams = params

    /** The response that this page was parsed from. */
    fun response(): RbacRoleListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [RbacRoleListPage].
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

    /** A builder for [RbacRoleListPage]. */
    class Builder internal constructor() {

        private var service: RbacRoleService? = null
        private var params: RbacRoleListParams? = null
        private var response: RbacRoleListPageResponse? = null

        @JvmSynthetic
        internal fun from(rbacRoleListPage: RbacRoleListPage) = apply {
            service = rbacRoleListPage.service
            params = rbacRoleListPage.params
            response = rbacRoleListPage.response
        }

        fun service(service: RbacRoleService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: RbacRoleListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: RbacRoleListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [RbacRoleListPage].
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
        fun build(): RbacRoleListPage =
            RbacRoleListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RbacRoleListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "RbacRoleListPage{service=$service, params=$params, response=$response}"
}
