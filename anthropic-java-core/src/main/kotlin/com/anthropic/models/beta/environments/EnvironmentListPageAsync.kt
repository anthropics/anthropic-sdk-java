// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.EnvironmentServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see EnvironmentServiceAsync.list */
class EnvironmentListPageAsync
private constructor(
    private val service: EnvironmentServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: EnvironmentListParams,
    private val response: EnvironmentListPageResponse,
) : PageAsync<BetaEnvironment> {

    /**
     * Delegates to [EnvironmentListPageResponse], but gracefully handles missing data.
     *
     * @see EnvironmentListPageResponse.data
     */
    fun data(): List<BetaEnvironment> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [EnvironmentListPageResponse], but gracefully handles missing data.
     *
     * @see EnvironmentListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaEnvironment> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): EnvironmentListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<EnvironmentListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaEnvironment> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): EnvironmentListParams = params

    /** The response that this page was parsed from. */
    fun response(): EnvironmentListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [EnvironmentListPageAsync].
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

    /** A builder for [EnvironmentListPageAsync]. */
    class Builder internal constructor() {

        private var service: EnvironmentServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: EnvironmentListParams? = null
        private var response: EnvironmentListPageResponse? = null

        @JvmSynthetic
        internal fun from(environmentListPageAsync: EnvironmentListPageAsync) = apply {
            service = environmentListPageAsync.service
            streamHandlerExecutor = environmentListPageAsync.streamHandlerExecutor
            params = environmentListPageAsync.params
            response = environmentListPageAsync.response
        }

        fun service(service: EnvironmentServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: EnvironmentListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: EnvironmentListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [EnvironmentListPageAsync].
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
        fun build(): EnvironmentListPageAsync =
            EnvironmentListPageAsync(
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

        return other is EnvironmentListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "EnvironmentListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
