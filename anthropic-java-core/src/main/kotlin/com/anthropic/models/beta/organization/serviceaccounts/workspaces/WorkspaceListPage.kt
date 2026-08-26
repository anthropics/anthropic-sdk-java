// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.serviceaccounts.workspaces

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.serviceaccounts.BetaServiceAccountWorkspaceMember
import com.anthropic.services.blocking.beta.organization.serviceaccounts.WorkspaceService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see WorkspaceService.list */
class WorkspaceListPage
private constructor(
    private val service: WorkspaceService,
    private val params: WorkspaceListParams,
    private val response: WorkspaceListPageResponse,
) : Page<BetaServiceAccountWorkspaceMember> {

    /**
     * Delegates to [WorkspaceListPageResponse], but gracefully handles missing data.
     *
     * @see WorkspaceListPageResponse.data
     */
    fun data(): List<BetaServiceAccountWorkspaceMember> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [WorkspaceListPageResponse], but gracefully handles missing data.
     *
     * @see WorkspaceListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaServiceAccountWorkspaceMember> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): WorkspaceListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): WorkspaceListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaServiceAccountWorkspaceMember> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): WorkspaceListParams = params

    /** The response that this page was parsed from. */
    fun response(): WorkspaceListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [WorkspaceListPage].
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

    /** A builder for [WorkspaceListPage]. */
    class Builder internal constructor() {

        private var service: WorkspaceService? = null
        private var params: WorkspaceListParams? = null
        private var response: WorkspaceListPageResponse? = null

        @JvmSynthetic
        internal fun from(workspaceListPage: WorkspaceListPage) = apply {
            service = workspaceListPage.service
            params = workspaceListPage.params
            response = workspaceListPage.response
        }

        fun service(service: WorkspaceService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: WorkspaceListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: WorkspaceListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [WorkspaceListPage].
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
        fun build(): WorkspaceListPage =
            WorkspaceListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WorkspaceListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "WorkspaceListPage{service=$service, params=$params, response=$response}"
}
