// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.ratelimits

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.workspaces.RateLimitServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see RateLimitServiceAsync.list */
class RateLimitListPageAsync
private constructor(
    private val service: RateLimitServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: RateLimitListParams,
    private val response: RateLimitListPageResponse,
) : PageAsync<BetaWorkspaceRateLimit> {

    /**
     * Delegates to [RateLimitListPageResponse], but gracefully handles missing data.
     *
     * @see RateLimitListPageResponse.data
     */
    fun data(): List<BetaWorkspaceRateLimit> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [RateLimitListPageResponse], but gracefully handles missing data.
     *
     * @see RateLimitListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaWorkspaceRateLimit> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): RateLimitListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<RateLimitListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaWorkspaceRateLimit> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): RateLimitListParams = params

    /** The response that this page was parsed from. */
    fun response(): RateLimitListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [RateLimitListPageAsync].
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

    /** A builder for [RateLimitListPageAsync]. */
    class Builder internal constructor() {

        private var service: RateLimitServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: RateLimitListParams? = null
        private var response: RateLimitListPageResponse? = null

        @JvmSynthetic
        internal fun from(rateLimitListPageAsync: RateLimitListPageAsync) = apply {
            service = rateLimitListPageAsync.service
            streamHandlerExecutor = rateLimitListPageAsync.streamHandlerExecutor
            params = rateLimitListPageAsync.params
            response = rateLimitListPageAsync.response
        }

        fun service(service: RateLimitServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: RateLimitListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: RateLimitListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [RateLimitListPageAsync].
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
        fun build(): RateLimitListPageAsync =
            RateLimitListPageAsync(
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

        return other is RateLimitListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "RateLimitListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
