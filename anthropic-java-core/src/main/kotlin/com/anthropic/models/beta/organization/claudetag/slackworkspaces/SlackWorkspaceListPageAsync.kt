package com.anthropic.models.beta.organization.claudetag.slackworkspaces

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.claudetag.SlackWorkspaceServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see SlackWorkspaceServiceAsync.list */
class SlackWorkspaceListPageAsync
private constructor(
    private val service: SlackWorkspaceServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: SlackWorkspaceListParams,
    private val response: SlackWorkspaceListPageResponse,
) : PageAsync<BetaClaudeTagSlackWorkspace> {

    /**
     * Delegates to [SlackWorkspaceListPageResponse], but gracefully handles missing data.
     *
     * @see SlackWorkspaceListPageResponse.data
     */
    fun data(): List<BetaClaudeTagSlackWorkspace> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [SlackWorkspaceListPageResponse], but gracefully handles missing data.
     *
     * @see SlackWorkspaceListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaClaudeTagSlackWorkspace> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): SlackWorkspaceListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<SlackWorkspaceListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaClaudeTagSlackWorkspace> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): SlackWorkspaceListParams = params

    /** The response that this page was parsed from. */
    fun response(): SlackWorkspaceListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SlackWorkspaceListPageAsync].
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

    /** A builder for [SlackWorkspaceListPageAsync]. */
    class Builder internal constructor() {

        private var service: SlackWorkspaceServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: SlackWorkspaceListParams? = null
        private var response: SlackWorkspaceListPageResponse? = null

        @JvmSynthetic
        internal fun from(slackWorkspaceListPageAsync: SlackWorkspaceListPageAsync) = apply {
            service = slackWorkspaceListPageAsync.service
            streamHandlerExecutor = slackWorkspaceListPageAsync.streamHandlerExecutor
            params = slackWorkspaceListPageAsync.params
            response = slackWorkspaceListPageAsync.response
        }

        fun service(service: SlackWorkspaceServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: SlackWorkspaceListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: SlackWorkspaceListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [SlackWorkspaceListPageAsync].
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
        fun build(): SlackWorkspaceListPageAsync =
            SlackWorkspaceListPageAsync(
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

        return other is SlackWorkspaceListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "SlackWorkspaceListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
