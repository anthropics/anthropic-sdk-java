// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.environments.WorkServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see WorkServiceAsync.list */
class WorkListPageAsync
private constructor(
    private val service: WorkServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: WorkListParams,
    private val response: BetaSelfHostedWorkListResponse,
) : PageAsync<BetaSelfHostedWork> {

    /**
     * Delegates to [BetaSelfHostedWorkListResponse], but gracefully handles missing data.
     *
     * @see BetaSelfHostedWorkListResponse.data
     */
    fun data(): List<BetaSelfHostedWork> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [BetaSelfHostedWorkListResponse], but gracefully handles missing data.
     *
     * @see BetaSelfHostedWorkListResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaSelfHostedWork> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): WorkListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<WorkListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaSelfHostedWork> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): WorkListParams = params

    /** The response that this page was parsed from. */
    fun response(): BetaSelfHostedWorkListResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [WorkListPageAsync].
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

    /** A builder for [WorkListPageAsync]. */
    class Builder internal constructor() {

        private var service: WorkServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: WorkListParams? = null
        private var response: BetaSelfHostedWorkListResponse? = null

        @JvmSynthetic
        internal fun from(workListPageAsync: WorkListPageAsync) = apply {
            service = workListPageAsync.service
            streamHandlerExecutor = workListPageAsync.streamHandlerExecutor
            params = workListPageAsync.params
            response = workListPageAsync.response
        }

        fun service(service: WorkServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: WorkListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: BetaSelfHostedWorkListResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [WorkListPageAsync].
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
        fun build(): WorkListPageAsync =
            WorkListPageAsync(
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

        return other is WorkListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "WorkListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
