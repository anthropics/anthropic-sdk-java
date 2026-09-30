package com.anthropic.models.beta.organization.analytics.summaries

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSingleDayActivitySummary
import com.anthropic.services.async.beta.organization.analytics.SummaryServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see SummaryServiceAsync.list */
class SummaryListPageAsync
private constructor(
    private val service: SummaryServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: SummaryListParams,
    private val response: SummaryListPageResponse,
) : PageAsync<BetaAnalyticsSingleDayActivitySummary> {

    /**
     * Delegates to [SummaryListPageResponse], but gracefully handles missing data.
     *
     * @see SummaryListPageResponse.data
     */
    fun data(): List<BetaAnalyticsSingleDayActivitySummary> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [SummaryListPageResponse], but gracefully handles missing data.
     *
     * @see SummaryListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsSingleDayActivitySummary> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): SummaryListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<SummaryListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaAnalyticsSingleDayActivitySummary> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): SummaryListParams = params

    /** The response that this page was parsed from. */
    fun response(): SummaryListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SummaryListPageAsync].
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

    /** A builder for [SummaryListPageAsync]. */
    class Builder internal constructor() {

        private var service: SummaryServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: SummaryListParams? = null
        private var response: SummaryListPageResponse? = null

        @JvmSynthetic
        internal fun from(summaryListPageAsync: SummaryListPageAsync) = apply {
            service = summaryListPageAsync.service
            streamHandlerExecutor = summaryListPageAsync.streamHandlerExecutor
            params = summaryListPageAsync.params
            response = summaryListPageAsync.response
        }

        fun service(service: SummaryServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: SummaryListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: SummaryListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [SummaryListPageAsync].
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
        fun build(): SummaryListPageAsync =
            SummaryListPageAsync(
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

        return other is SummaryListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "SummaryListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
