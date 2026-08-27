// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.WorkspaceServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see WorkspaceServiceAsync.list */
class WorkspaceListPageAsync
private constructor(
    private val service: WorkspaceServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: WorkspaceListParams,
    private val response: WorkspaceListPageResponse,
) : PageAsync<BetaWorkspace> {

    /**
     * Delegates to [WorkspaceListPageResponse], but gracefully handles missing data.
     *
     * @see WorkspaceListPageResponse.data
     */
    fun data(): List<BetaWorkspace> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [WorkspaceListPageResponse], but gracefully handles missing data.
     *
     * @see WorkspaceListPageResponse.hasMore
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [WorkspaceListPageResponse], but gracefully handles missing data.
     *
     * @see WorkspaceListPageResponse.firstId
     */
    fun firstId(): Optional<String> = response._firstId().getOptional("first_id")

    /**
     * Delegates to [WorkspaceListPageResponse], but gracefully handles missing data.
     *
     * @see WorkspaceListPageResponse.lastId
     */
    fun lastId(): Optional<String> = response._lastId().getOptional("last_id")

    override fun items(): List<BetaWorkspace> = data()

    override fun hasNextPage(): Boolean {
        if (items().isEmpty()) {
            return false
        }
        if (hasMore().getOrNull() == false) {
            return false
        }

        return if (params.beforeId().isPresent) firstId().isPresent else lastId().isPresent
    }

    fun nextPageParams(): WorkspaceListParams {
        if (params.beforeId().isPresent) {
            val previousCursor =
                firstId().getOrNull()
                    ?: throw IllegalStateException("Cannot construct next page params")
            return params.toBuilder().beforeId(previousCursor).build()
        }
        val nextCursor =
            lastId().getOrNull() ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().afterId(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<WorkspaceListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaWorkspace> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): WorkspaceListParams = params

    /** The response that this page was parsed from. */
    fun response(): WorkspaceListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [WorkspaceListPageAsync].
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

    /** A builder for [WorkspaceListPageAsync]. */
    class Builder internal constructor() {

        private var service: WorkspaceServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: WorkspaceListParams? = null
        private var response: WorkspaceListPageResponse? = null

        @JvmSynthetic
        internal fun from(workspaceListPageAsync: WorkspaceListPageAsync) = apply {
            service = workspaceListPageAsync.service
            streamHandlerExecutor = workspaceListPageAsync.streamHandlerExecutor
            params = workspaceListPageAsync.params
            response = workspaceListPageAsync.response
        }

        fun service(service: WorkspaceServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: WorkspaceListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: WorkspaceListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [WorkspaceListPageAsync].
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
        fun build(): WorkspaceListPageAsync =
            WorkspaceListPageAsync(
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

        return other is WorkspaceListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "WorkspaceListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
