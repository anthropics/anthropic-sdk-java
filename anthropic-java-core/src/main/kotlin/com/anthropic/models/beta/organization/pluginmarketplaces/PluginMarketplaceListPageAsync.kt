package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.PluginMarketplaceServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see PluginMarketplaceServiceAsync.list */
class PluginMarketplaceListPageAsync
private constructor(
    private val service: PluginMarketplaceServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: PluginMarketplaceListParams,
    private val response: PluginMarketplaceListPageResponse,
) : PageAsync<BetaPluginMarketplace> {

    /**
     * Delegates to [PluginMarketplaceListPageResponse], but gracefully handles missing data.
     *
     * @see PluginMarketplaceListPageResponse.data
     */
    fun data(): List<BetaPluginMarketplace> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [PluginMarketplaceListPageResponse], but gracefully handles missing data.
     *
     * @see PluginMarketplaceListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaPluginMarketplace> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): PluginMarketplaceListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<PluginMarketplaceListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaPluginMarketplace> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): PluginMarketplaceListParams = params

    /** The response that this page was parsed from. */
    fun response(): PluginMarketplaceListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [PluginMarketplaceListPageAsync].
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

    /** A builder for [PluginMarketplaceListPageAsync]. */
    class Builder internal constructor() {

        private var service: PluginMarketplaceServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: PluginMarketplaceListParams? = null
        private var response: PluginMarketplaceListPageResponse? = null

        @JvmSynthetic
        internal fun from(pluginMarketplaceListPageAsync: PluginMarketplaceListPageAsync) = apply {
            service = pluginMarketplaceListPageAsync.service
            streamHandlerExecutor = pluginMarketplaceListPageAsync.streamHandlerExecutor
            params = pluginMarketplaceListPageAsync.params
            response = pluginMarketplaceListPageAsync.response
        }

        fun service(service: PluginMarketplaceServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: PluginMarketplaceListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: PluginMarketplaceListPageResponse) = apply {
            this.response = response
        }

        /**
         * Returns an immutable instance of [PluginMarketplaceListPageAsync].
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
        fun build(): PluginMarketplaceListPageAsync =
            PluginMarketplaceListPageAsync(
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

        return other is PluginMarketplaceListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "PluginMarketplaceListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
