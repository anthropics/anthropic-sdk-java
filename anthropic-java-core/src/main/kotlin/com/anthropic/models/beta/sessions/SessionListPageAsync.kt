// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.SessionServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see SessionServiceAsync.list */
class SessionListPageAsync
private constructor(
    private val service: SessionServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: SessionListParams,
    private val response: SessionListPageResponse,
) : PageAsync<BetaManagedAgentsSession> {

    /**
     * Delegates to [SessionListPageResponse], but gracefully handles missing data.
     *
     * @see SessionListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsSession> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [SessionListPageResponse], but gracefully handles missing data.
     *
     * @see SessionListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsSession> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): SessionListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<SessionListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaManagedAgentsSession> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): SessionListParams = params

    /** The response that this page was parsed from. */
    fun response(): SessionListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SessionListPageAsync].
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

    /** A builder for [SessionListPageAsync]. */
    class Builder internal constructor() {

        private var service: SessionServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: SessionListParams? = null
        private var response: SessionListPageResponse? = null

        @JvmSynthetic
        internal fun from(sessionListPageAsync: SessionListPageAsync) = apply {
            service = sessionListPageAsync.service
            streamHandlerExecutor = sessionListPageAsync.streamHandlerExecutor
            params = sessionListPageAsync.params
            response = sessionListPageAsync.response
        }

        fun service(service: SessionServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: SessionListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: SessionListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [SessionListPageAsync].
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
        fun build(): SessionListPageAsync =
            SessionListPageAsync(
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

        return other is SessionListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "SessionListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
