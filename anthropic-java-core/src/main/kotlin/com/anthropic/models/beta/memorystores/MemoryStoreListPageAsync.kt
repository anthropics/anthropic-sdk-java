// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.MemoryStoreServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see MemoryStoreServiceAsync.list */
class MemoryStoreListPageAsync
private constructor(
    private val service: MemoryStoreServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: MemoryStoreListParams,
    private val response: MemoryStoreListPageResponse,
) : PageAsync<BetaManagedAgentsMemoryStore> {

    /**
     * Delegates to [MemoryStoreListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryStoreListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsMemoryStore> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [MemoryStoreListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryStoreListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsMemoryStore> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): MemoryStoreListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<MemoryStoreListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaManagedAgentsMemoryStore> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): MemoryStoreListParams = params

    /** The response that this page was parsed from. */
    fun response(): MemoryStoreListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [MemoryStoreListPageAsync].
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

    /** A builder for [MemoryStoreListPageAsync]. */
    class Builder internal constructor() {

        private var service: MemoryStoreServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: MemoryStoreListParams? = null
        private var response: MemoryStoreListPageResponse? = null

        @JvmSynthetic
        internal fun from(memoryStoreListPageAsync: MemoryStoreListPageAsync) = apply {
            service = memoryStoreListPageAsync.service
            streamHandlerExecutor = memoryStoreListPageAsync.streamHandlerExecutor
            params = memoryStoreListPageAsync.params
            response = memoryStoreListPageAsync.response
        }

        fun service(service: MemoryStoreServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: MemoryStoreListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: MemoryStoreListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [MemoryStoreListPageAsync].
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
        fun build(): MemoryStoreListPageAsync =
            MemoryStoreListPageAsync(
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

        return other is MemoryStoreListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "MemoryStoreListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
