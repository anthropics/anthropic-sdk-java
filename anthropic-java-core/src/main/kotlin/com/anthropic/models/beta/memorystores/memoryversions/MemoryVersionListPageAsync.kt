// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.memorystores.MemoryVersionServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see MemoryVersionServiceAsync.list */
class MemoryVersionListPageAsync
private constructor(
    private val service: MemoryVersionServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: MemoryVersionListParams,
    private val response: MemoryVersionListPageResponse,
) : PageAsync<BetaManagedAgentsMemoryVersion> {

    /**
     * Delegates to [MemoryVersionListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryVersionListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsMemoryVersion> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [MemoryVersionListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryVersionListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsMemoryVersion> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): MemoryVersionListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<MemoryVersionListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaManagedAgentsMemoryVersion> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): MemoryVersionListParams = params

    /** The response that this page was parsed from. */
    fun response(): MemoryVersionListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [MemoryVersionListPageAsync].
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

    /** A builder for [MemoryVersionListPageAsync]. */
    class Builder internal constructor() {

        private var service: MemoryVersionServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: MemoryVersionListParams? = null
        private var response: MemoryVersionListPageResponse? = null

        @JvmSynthetic
        internal fun from(memoryVersionListPageAsync: MemoryVersionListPageAsync) = apply {
            service = memoryVersionListPageAsync.service
            streamHandlerExecutor = memoryVersionListPageAsync.streamHandlerExecutor
            params = memoryVersionListPageAsync.params
            response = memoryVersionListPageAsync.response
        }

        fun service(service: MemoryVersionServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: MemoryVersionListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: MemoryVersionListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [MemoryVersionListPageAsync].
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
        fun build(): MemoryVersionListPageAsync =
            MemoryVersionListPageAsync(
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

        return other is MemoryVersionListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "MemoryVersionListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
