package com.anthropic.models.beta.organization.rbacroles.permissions

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.rbacroles.PermissionService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see PermissionService.list */
class PermissionListPage
private constructor(
    private val service: PermissionService,
    private val params: PermissionListParams,
    private val response: PermissionListPageResponse,
) : Page<BetaRbacRolePermission> {

    /**
     * Delegates to [PermissionListPageResponse], but gracefully handles missing data.
     *
     * @see PermissionListPageResponse.data
     */
    fun data(): List<BetaRbacRolePermission> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [PermissionListPageResponse], but gracefully handles missing data.
     *
     * @see PermissionListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaRbacRolePermission> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): PermissionListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): PermissionListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaRbacRolePermission> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): PermissionListParams = params

    /** The response that this page was parsed from. */
    fun response(): PermissionListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [PermissionListPage].
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

    /** A builder for [PermissionListPage]. */
    class Builder internal constructor() {

        private var service: PermissionService? = null
        private var params: PermissionListParams? = null
        private var response: PermissionListPageResponse? = null

        @JvmSynthetic
        internal fun from(permissionListPage: PermissionListPage) = apply {
            service = permissionListPage.service
            params = permissionListPage.params
            response = permissionListPage.response
        }

        fun service(service: PermissionService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: PermissionListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: PermissionListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [PermissionListPage].
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
        fun build(): PermissionListPage =
            PermissionListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is PermissionListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "PermissionListPage{service=$service, params=$params, response=$response}"
}
