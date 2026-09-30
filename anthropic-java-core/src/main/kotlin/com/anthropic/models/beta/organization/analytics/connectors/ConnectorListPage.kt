package com.anthropic.models.beta.organization.analytics.connectors

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsConnectorActivity
import com.anthropic.services.blocking.beta.organization.analytics.ConnectorService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see ConnectorService.list */
class ConnectorListPage
private constructor(
    private val service: ConnectorService,
    private val params: ConnectorListParams,
    private val response: ConnectorListPageResponse,
) : Page<BetaAnalyticsConnectorActivity> {

    /**
     * Delegates to [ConnectorListPageResponse], but gracefully handles missing data.
     *
     * @see ConnectorListPageResponse.data
     */
    fun data(): List<BetaAnalyticsConnectorActivity> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [ConnectorListPageResponse], but gracefully handles missing data.
     *
     * @see ConnectorListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsConnectorActivity> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): ConnectorListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): ConnectorListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsConnectorActivity> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): ConnectorListParams = params

    /** The response that this page was parsed from. */
    fun response(): ConnectorListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ConnectorListPage].
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

    /** A builder for [ConnectorListPage]. */
    class Builder internal constructor() {

        private var service: ConnectorService? = null
        private var params: ConnectorListParams? = null
        private var response: ConnectorListPageResponse? = null

        @JvmSynthetic
        internal fun from(connectorListPage: ConnectorListPage) = apply {
            service = connectorListPage.service
            params = connectorListPage.params
            response = connectorListPage.response
        }

        fun service(service: ConnectorService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: ConnectorListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ConnectorListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ConnectorListPage].
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
        fun build(): ConnectorListPage =
            ConnectorListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ConnectorListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "ConnectorListPage{service=$service, params=$params, response=$response}"
}
