package com.anthropic.models.beta.organization.spendlimits.increaserequests

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.spendlimits.IncreaseRequestServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see IncreaseRequestServiceAsync.list */
class IncreaseRequestListPageAsync
private constructor(
    private val service: IncreaseRequestServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: IncreaseRequestListParams,
    private val response: IncreaseRequestListPageResponse,
) : PageAsync<BetaSpendLimitIncreaseRequest> {

    /**
     * Delegates to [IncreaseRequestListPageResponse], but gracefully handles missing data.
     *
     * @see IncreaseRequestListPageResponse.data
     */
    fun data(): List<BetaSpendLimitIncreaseRequest> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [IncreaseRequestListPageResponse], but gracefully handles missing data.
     *
     * @see IncreaseRequestListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaSpendLimitIncreaseRequest> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): IncreaseRequestListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<IncreaseRequestListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaSpendLimitIncreaseRequest> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): IncreaseRequestListParams = params

    /** The response that this page was parsed from. */
    fun response(): IncreaseRequestListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [IncreaseRequestListPageAsync].
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

    /** A builder for [IncreaseRequestListPageAsync]. */
    class Builder internal constructor() {

        private var service: IncreaseRequestServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: IncreaseRequestListParams? = null
        private var response: IncreaseRequestListPageResponse? = null

        @JvmSynthetic
        internal fun from(increaseRequestListPageAsync: IncreaseRequestListPageAsync) = apply {
            service = increaseRequestListPageAsync.service
            streamHandlerExecutor = increaseRequestListPageAsync.streamHandlerExecutor
            params = increaseRequestListPageAsync.params
            response = increaseRequestListPageAsync.response
        }

        fun service(service: IncreaseRequestServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: IncreaseRequestListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: IncreaseRequestListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [IncreaseRequestListPageAsync].
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
        fun build(): IncreaseRequestListPageAsync =
            IncreaseRequestListPageAsync(
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

        return other is IncreaseRequestListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "IncreaseRequestListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
