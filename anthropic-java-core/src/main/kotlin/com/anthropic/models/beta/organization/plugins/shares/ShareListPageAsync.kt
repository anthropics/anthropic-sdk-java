package com.anthropic.models.beta.organization.plugins.shares

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.plugins.ShareServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see ShareServiceAsync.list */
class ShareListPageAsync
private constructor(
    private val service: ShareServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: ShareListParams,
    private val response: ShareListPageResponse,
) : PageAsync<BetaPluginShare> {

    /**
     * Delegates to [ShareListPageResponse], but gracefully handles missing data.
     *
     * @see ShareListPageResponse.data
     */
    fun data(): List<BetaPluginShare> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [ShareListPageResponse], but gracefully handles missing data.
     *
     * @see ShareListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaPluginShare> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): ShareListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<ShareListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaPluginShare> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): ShareListParams = params

    /** The response that this page was parsed from. */
    fun response(): ShareListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ShareListPageAsync].
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

    /** A builder for [ShareListPageAsync]. */
    class Builder internal constructor() {

        private var service: ShareServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: ShareListParams? = null
        private var response: ShareListPageResponse? = null

        @JvmSynthetic
        internal fun from(shareListPageAsync: ShareListPageAsync) = apply {
            service = shareListPageAsync.service
            streamHandlerExecutor = shareListPageAsync.streamHandlerExecutor
            params = shareListPageAsync.params
            response = shareListPageAsync.response
        }

        fun service(service: ShareServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: ShareListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ShareListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ShareListPageAsync].
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
        fun build(): ShareListPageAsync =
            ShareListPageAsync(
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

        return other is ShareListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "ShareListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
