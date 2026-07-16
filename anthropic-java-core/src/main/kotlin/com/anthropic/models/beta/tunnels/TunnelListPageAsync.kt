// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.TunnelServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see TunnelServiceAsync.list */
class TunnelListPageAsync
private constructor(
    private val service: TunnelServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: TunnelListParams,
    private val response: TunnelListPageResponse,
) : PageAsync<BetaTunnel> {

    /**
     * Delegates to [TunnelListPageResponse], but gracefully handles missing data.
     *
     * @see TunnelListPageResponse.data
     */
    fun data(): List<BetaTunnel> = response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [TunnelListPageResponse], but gracefully handles missing data.
     *
     * @see TunnelListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaTunnel> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): TunnelListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<TunnelListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaTunnel> = AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): TunnelListParams = params

    /** The response that this page was parsed from. */
    fun response(): TunnelListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [TunnelListPageAsync].
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

    /** A builder for [TunnelListPageAsync]. */
    class Builder internal constructor() {

        private var service: TunnelServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: TunnelListParams? = null
        private var response: TunnelListPageResponse? = null

        @JvmSynthetic
        internal fun from(tunnelListPageAsync: TunnelListPageAsync) = apply {
            service = tunnelListPageAsync.service
            streamHandlerExecutor = tunnelListPageAsync.streamHandlerExecutor
            params = tunnelListPageAsync.params
            response = tunnelListPageAsync.response
        }

        fun service(service: TunnelServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: TunnelListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: TunnelListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [TunnelListPageAsync].
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
        fun build(): TunnelListPageAsync =
            TunnelListPageAsync(
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

        return other is TunnelListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "TunnelListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
