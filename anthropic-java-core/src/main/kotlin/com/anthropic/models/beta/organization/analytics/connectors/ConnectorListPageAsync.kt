package com.anthropic.models.beta.organization.analytics.connectors

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsConnectorActivity
import com.anthropic.services.async.beta.organization.analytics.ConnectorServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see ConnectorServiceAsync.list */
class ConnectorListPageAsync
private constructor(
    private val service: ConnectorServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: ConnectorListParams,
    private val response: ConnectorListPageResponse,
) : PageAsync<BetaAnalyticsConnectorActivity> {

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

    override fun nextPage(): CompletableFuture<ConnectorListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaAnalyticsConnectorActivity> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): ConnectorListParams = params

    /** The response that this page was parsed from. */
    fun response(): ConnectorListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ConnectorListPageAsync].
         *
         * The following fields are required:
         * ```java
         * .service()
         * .streamHandlerExecutor()
         * .params()
         * .response()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ConnectorListPageAsync]. */
    class Builder internal constructor() {

        private var service: ConnectorServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: ConnectorListParams? = null
        private var response: ConnectorListPageResponse? = null

        @JvmSynthetic
        internal fun from(connectorListPageAsync: ConnectorListPageAsync) = apply {
            service = connectorListPageAsync.service
            streamHandlerExecutor = connectorListPageAsync.streamHandlerExecutor
            params = connectorListPageAsync.params
            response = connectorListPageAsync.response
        }

        fun service(service: ConnectorServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: ConnectorListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ConnectorListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ConnectorListPageAsync].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .service()
         * .streamHandlerExecutor()
         * .params()
         * .response()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ConnectorListPageAsync =
            ConnectorListPageAsync(
                checkRequired("service", service),
                checkRequired("streamHandlerExecutor", streamHandlerExecutor),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ConnectorListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "ConnectorListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
