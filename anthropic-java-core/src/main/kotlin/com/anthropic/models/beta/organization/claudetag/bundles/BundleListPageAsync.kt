package com.anthropic.models.beta.organization.claudetag.bundles

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.claudetag.BundleServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see BundleServiceAsync.list */
class BundleListPageAsync
private constructor(
    private val service: BundleServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: BundleListParams,
    private val response: BundleListPageResponse,
) : PageAsync<BetaClaudeTagBundle> {

    /**
     * Delegates to [BundleListPageResponse], but gracefully handles missing data.
     *
     * @see BundleListPageResponse.data
     */
    fun data(): List<BetaClaudeTagBundle> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [BundleListPageResponse], but gracefully handles missing data.
     *
     * @see BundleListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaClaudeTagBundle> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): BundleListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<BundleListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaClaudeTagBundle> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): BundleListParams = params

    /** The response that this page was parsed from. */
    fun response(): BundleListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BundleListPageAsync].
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

    /** A builder for [BundleListPageAsync]. */
    class Builder internal constructor() {

        private var service: BundleServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: BundleListParams? = null
        private var response: BundleListPageResponse? = null

        @JvmSynthetic
        internal fun from(bundleListPageAsync: BundleListPageAsync) = apply {
            service = bundleListPageAsync.service
            streamHandlerExecutor = bundleListPageAsync.streamHandlerExecutor
            params = bundleListPageAsync.params
            response = bundleListPageAsync.response
        }

        fun service(service: BundleServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: BundleListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: BundleListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [BundleListPageAsync].
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
        fun build(): BundleListPageAsync =
            BundleListPageAsync(
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

        return other is BundleListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "BundleListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
