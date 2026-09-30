package com.anthropic.models.beta.organization.analytics.apps.chat.projects

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsProjectActivity
import com.anthropic.services.blocking.beta.organization.analytics.apps.chat.ProjectService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see ProjectService.list */
class ProjectListPage
private constructor(
    private val service: ProjectService,
    private val params: ProjectListParams,
    private val response: ProjectListPageResponse,
) : Page<BetaAnalyticsProjectActivity> {

    /**
     * Delegates to [ProjectListPageResponse], but gracefully handles missing data.
     *
     * @see ProjectListPageResponse.data
     */
    fun data(): List<BetaAnalyticsProjectActivity> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [ProjectListPageResponse], but gracefully handles missing data.
     *
     * @see ProjectListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsProjectActivity> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): ProjectListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): ProjectListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsProjectActivity> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): ProjectListParams = params

    /** The response that this page was parsed from. */
    fun response(): ProjectListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ProjectListPage].
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

    /** A builder for [ProjectListPage]. */
    class Builder internal constructor() {

        private var service: ProjectService? = null
        private var params: ProjectListParams? = null
        private var response: ProjectListPageResponse? = null

        @JvmSynthetic
        internal fun from(projectListPage: ProjectListPage) = apply {
            service = projectListPage.service
            params = projectListPage.params
            response = projectListPage.response
        }

        fun service(service: ProjectService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: ProjectListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ProjectListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ProjectListPage].
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
        fun build(): ProjectListPage =
            ProjectListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ProjectListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "ProjectListPage{service=$service, params=$params, response=$response}"
}
