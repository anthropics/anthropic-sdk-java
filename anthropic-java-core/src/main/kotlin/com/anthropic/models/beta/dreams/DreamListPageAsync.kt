// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.DreamServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see DreamServiceAsync.list */
class DreamListPageAsync
private constructor(
    private val service: DreamServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: DreamListParams,
    private val response: DreamListPageResponse,
) : PageAsync<BetaDream> {

    /**
     * Delegates to [DreamListPageResponse], but gracefully handles missing data.
     *
     * @see DreamListPageResponse.data
     */
    fun data(): List<BetaDream> = response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [DreamListPageResponse], but gracefully handles missing data.
     *
     * @see DreamListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaDream> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): DreamListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<DreamListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaDream> = AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): DreamListParams = params

    /** The response that this page was parsed from. */
    fun response(): DreamListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [DreamListPageAsync].
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

    /** A builder for [DreamListPageAsync]. */
    class Builder internal constructor() {

        private var service: DreamServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: DreamListParams? = null
        private var response: DreamListPageResponse? = null

        @JvmSynthetic
        internal fun from(dreamListPageAsync: DreamListPageAsync) = apply {
            service = dreamListPageAsync.service
            streamHandlerExecutor = dreamListPageAsync.streamHandlerExecutor
            params = dreamListPageAsync.params
            response = dreamListPageAsync.response
        }

        fun service(service: DreamServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: DreamListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: DreamListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [DreamListPageAsync].
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
        fun build(): DreamListPageAsync =
            DreamListPageAsync(
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

        return other is DreamListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "DreamListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
