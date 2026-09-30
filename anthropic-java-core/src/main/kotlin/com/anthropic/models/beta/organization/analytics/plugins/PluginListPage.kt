package com.anthropic.models.beta.organization.analytics.plugins

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsPluginActivity
import com.anthropic.services.blocking.beta.organization.analytics.PluginService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see PluginService.list */
class PluginListPage
private constructor(
    private val service: PluginService,
    private val params: PluginListParams,
    private val response: PluginListPageResponse,
) : Page<BetaAnalyticsPluginActivity> {

    /**
     * Delegates to [PluginListPageResponse], but gracefully handles missing data.
     *
     * @see PluginListPageResponse.data
     */
    fun data(): List<BetaAnalyticsPluginActivity> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [PluginListPageResponse], but gracefully handles missing data.
     *
     * @see PluginListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsPluginActivity> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): PluginListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): PluginListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsPluginActivity> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): PluginListParams = params

    /** The response that this page was parsed from. */
    fun response(): PluginListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [PluginListPage].
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

    /** A builder for [PluginListPage]. */
    class Builder internal constructor() {

        private var service: PluginService? = null
        private var params: PluginListParams? = null
        private var response: PluginListPageResponse? = null

        @JvmSynthetic
        internal fun from(pluginListPage: PluginListPage) = apply {
            service = pluginListPage.service
            params = pluginListPage.params
            response = pluginListPage.response
        }

        fun service(service: PluginService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: PluginListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: PluginListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [PluginListPage].
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
        fun build(): PluginListPage =
            PluginListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is PluginListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "PluginListPage{service=$service, params=$params, response=$response}"
}
