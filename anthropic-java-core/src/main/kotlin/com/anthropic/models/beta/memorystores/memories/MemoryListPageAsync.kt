// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.memorystores.MemoryServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see MemoryServiceAsync.list */
class MemoryListPageAsync
private constructor(
    private val service: MemoryServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: MemoryListParams,
    private val response: MemoryListPageResponse,
) : PageAsync<BetaManagedAgentsMemoryListItem> {

    /**
     * Delegates to [MemoryListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsMemoryListItem> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [MemoryListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsMemoryListItem> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): MemoryListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<MemoryListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaManagedAgentsMemoryListItem> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): MemoryListParams = params

    /** The response that this page was parsed from. */
    fun response(): MemoryListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [MemoryListPageAsync].
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

    /** A builder for [MemoryListPageAsync]. */
    class Builder internal constructor() {

        private var service: MemoryServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: MemoryListParams? = null
        private var response: MemoryListPageResponse? = null

        @JvmSynthetic
        internal fun from(memoryListPageAsync: MemoryListPageAsync) = apply {
            service = memoryListPageAsync.service
            streamHandlerExecutor = memoryListPageAsync.streamHandlerExecutor
            params = memoryListPageAsync.params
            response = memoryListPageAsync.response
        }

        fun service(service: MemoryServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: MemoryListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: MemoryListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [MemoryListPageAsync].
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
        fun build(): MemoryListPageAsync =
            MemoryListPageAsync(
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

        return other is MemoryListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "MemoryListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
