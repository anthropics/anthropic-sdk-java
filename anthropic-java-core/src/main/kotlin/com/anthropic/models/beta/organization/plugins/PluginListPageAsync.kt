package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.PluginServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see PluginServiceAsync.list */
class PluginListPageAsync
private constructor(
    private val service: PluginServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: PluginListParams,
    private val response: PluginListPageResponse,
) : PageAsync<BetaPlugin> {

    /**
     * Delegates to [PluginListPageResponse], but gracefully handles missing data.
     *
     * @see PluginListPageResponse.data
     */
    fun data(): List<BetaPlugin> = response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [PluginListPageResponse], but gracefully handles missing data.
     *
     * @see PluginListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaPlugin> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): PluginListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<PluginListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaPlugin> = AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): PluginListParams = params

    /** The response that this page was parsed from. */
    fun response(): PluginListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [PluginListPageAsync].
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

    /** A builder for [PluginListPageAsync]. */
    class Builder internal constructor() {

        private var service: PluginServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: PluginListParams? = null
        private var response: PluginListPageResponse? = null

        @JvmSynthetic
        internal fun from(pluginListPageAsync: PluginListPageAsync) = apply {
            service = pluginListPageAsync.service
            streamHandlerExecutor = pluginListPageAsync.streamHandlerExecutor
            params = pluginListPageAsync.params
            response = pluginListPageAsync.response
        }

        fun service(service: PluginServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: PluginListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: PluginListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [PluginListPageAsync].
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
        fun build(): PluginListPageAsync =
            PluginListPageAsync(
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

        return other is PluginListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "PluginListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
