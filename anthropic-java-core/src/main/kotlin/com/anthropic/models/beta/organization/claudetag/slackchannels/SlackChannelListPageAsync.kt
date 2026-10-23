package com.anthropic.models.beta.organization.claudetag.slackchannels

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.claudetag.SlackChannelServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see SlackChannelServiceAsync.list */
class SlackChannelListPageAsync
private constructor(
    private val service: SlackChannelServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: SlackChannelListParams,
    private val response: SlackChannelListPageResponse,
) : PageAsync<BetaClaudeTagSlackChannel> {

    /**
     * Delegates to [SlackChannelListPageResponse], but gracefully handles missing data.
     *
     * @see SlackChannelListPageResponse.data
     */
    fun data(): List<BetaClaudeTagSlackChannel> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [SlackChannelListPageResponse], but gracefully handles missing data.
     *
     * @see SlackChannelListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaClaudeTagSlackChannel> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): SlackChannelListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<SlackChannelListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaClaudeTagSlackChannel> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): SlackChannelListParams = params

    /** The response that this page was parsed from. */
    fun response(): SlackChannelListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SlackChannelListPageAsync].
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

    /** A builder for [SlackChannelListPageAsync]. */
    class Builder internal constructor() {

        private var service: SlackChannelServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: SlackChannelListParams? = null
        private var response: SlackChannelListPageResponse? = null

        @JvmSynthetic
        internal fun from(slackChannelListPageAsync: SlackChannelListPageAsync) = apply {
            service = slackChannelListPageAsync.service
            streamHandlerExecutor = slackChannelListPageAsync.streamHandlerExecutor
            params = slackChannelListPageAsync.params
            response = slackChannelListPageAsync.response
        }

        fun service(service: SlackChannelServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: SlackChannelListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: SlackChannelListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [SlackChannelListPageAsync].
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
        fun build(): SlackChannelListPageAsync =
            SlackChannelListPageAsync(
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

        return other is SlackChannelListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "SlackChannelListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
