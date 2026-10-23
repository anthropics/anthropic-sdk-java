package com.anthropic.models.beta.organization.analytics.cost

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.analytics.CostServiceAsync
import java.time.OffsetDateTime
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see CostServiceAsync.list */
class CostListPageAsync
private constructor(
    private val service: CostServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: CostListParams,
    private val response: CostListPageResponse,
) : PageAsync<BetaAnalyticsCostItem> {

    /**
     * Delegates to [CostListPageResponse], but gracefully handles missing data.
     *
     * @see CostListPageResponse.data
     */
    fun data(): List<BetaAnalyticsCostItem> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [CostListPageResponse], but gracefully handles missing data.
     *
     * @see CostListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    /**
     * Delegates to [CostListPageResponse], but gracefully handles missing data.
     *
     * @see CostListPageResponse.hasMore
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [CostListPageResponse], but gracefully handles missing data.
     *
     * @see CostListPageResponse.dataRefreshedAt
     */
    fun dataRefreshedAt(): Optional<OffsetDateTime> =
        response._dataRefreshedAt().getOptional("data_refreshed_at")

    override fun items(): List<BetaAnalyticsCostItem> = data()

    override fun hasNextPage(): Boolean {
        if (hasMore().getOrNull() == false) {
            return false
        }

        return nextPageRaw().isPresent
    }

    fun nextPageParams(): CostListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<CostListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaAnalyticsCostItem> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): CostListParams = params

    /** The response that this page was parsed from. */
    fun response(): CostListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CostListPageAsync].
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

    /** A builder for [CostListPageAsync]. */
    class Builder internal constructor() {

        private var service: CostServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: CostListParams? = null
        private var response: CostListPageResponse? = null

        @JvmSynthetic
        internal fun from(costListPageAsync: CostListPageAsync) = apply {
            service = costListPageAsync.service
            streamHandlerExecutor = costListPageAsync.streamHandlerExecutor
            params = costListPageAsync.params
            response = costListPageAsync.response
        }

        fun service(service: CostServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: CostListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: CostListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [CostListPageAsync].
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
        fun build(): CostListPageAsync =
            CostListPageAsync(
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

        return other is CostListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "CostListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
