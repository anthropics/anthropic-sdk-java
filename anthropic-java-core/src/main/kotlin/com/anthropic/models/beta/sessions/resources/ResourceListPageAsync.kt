// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.sessions.ResourceServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see ResourceServiceAsync.list */
class ResourceListPageAsync
private constructor(
    private val service: ResourceServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: ResourceListParams,
    private val response: ResourceListPageResponse,
) : PageAsync<BetaManagedAgentsSessionResource> {

    /**
     * Delegates to [ResourceListPageResponse], but gracefully handles missing data.
     *
     * @see ResourceListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsSessionResource> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [ResourceListPageResponse], but gracefully handles missing data.
     *
     * @see ResourceListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsSessionResource> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): ResourceListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<ResourceListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaManagedAgentsSessionResource> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): ResourceListParams = params

    /** The response that this page was parsed from. */
    fun response(): ResourceListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ResourceListPageAsync].
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

    /** A builder for [ResourceListPageAsync]. */
    class Builder internal constructor() {

        private var service: ResourceServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: ResourceListParams? = null
        private var response: ResourceListPageResponse? = null

        @JvmSynthetic
        internal fun from(resourceListPageAsync: ResourceListPageAsync) = apply {
            service = resourceListPageAsync.service
            streamHandlerExecutor = resourceListPageAsync.streamHandlerExecutor
            params = resourceListPageAsync.params
            response = resourceListPageAsync.response
        }

        fun service(service: ResourceServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: ResourceListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ResourceListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ResourceListPageAsync].
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
        fun build(): ResourceListPageAsync =
            ResourceListPageAsync(
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

        return other is ResourceListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "ResourceListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
