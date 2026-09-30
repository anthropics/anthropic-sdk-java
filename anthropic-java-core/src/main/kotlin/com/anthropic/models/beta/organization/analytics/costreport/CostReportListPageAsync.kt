package com.anthropic.models.beta.organization.analytics.costreport

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsCostReportTimeBucket
import com.anthropic.services.async.beta.organization.analytics.CostReportServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see CostReportServiceAsync.list */
class CostReportListPageAsync
private constructor(
    private val service: CostReportServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: CostReportListParams,
    private val response: CostReportListPageResponse,
) : PageAsync<BetaAnalyticsCostReportTimeBucket> {

    /**
     * Delegates to [CostReportListPageResponse], but gracefully handles missing data.
     *
     * @see CostReportListPageResponse.data
     */
    fun data(): List<BetaAnalyticsCostReportTimeBucket> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [CostReportListPageResponse], but gracefully handles missing data.
     *
     * @see CostReportListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsCostReportTimeBucket> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): CostReportListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<CostReportListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaAnalyticsCostReportTimeBucket> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): CostReportListParams = params

    /** The response that this page was parsed from. */
    fun response(): CostReportListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CostReportListPageAsync].
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

    /** A builder for [CostReportListPageAsync]. */
    class Builder internal constructor() {

        private var service: CostReportServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: CostReportListParams? = null
        private var response: CostReportListPageResponse? = null

        @JvmSynthetic
        internal fun from(costReportListPageAsync: CostReportListPageAsync) = apply {
            service = costReportListPageAsync.service
            streamHandlerExecutor = costReportListPageAsync.streamHandlerExecutor
            params = costReportListPageAsync.params
            response = costReportListPageAsync.response
        }

        fun service(service: CostReportServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: CostReportListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: CostReportListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [CostReportListPageAsync].
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
        fun build(): CostReportListPageAsync =
            CostReportListPageAsync(
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

        return other is CostReportListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "CostReportListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
