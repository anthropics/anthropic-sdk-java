// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.members

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.workspaces.BetaWorkspaceMember
import com.anthropic.services.async.beta.organization.workspaces.MemberServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see MemberServiceAsync.list */
class MemberListPageAsync
private constructor(
    private val service: MemberServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: MemberListParams,
    private val response: MemberListPageResponse,
) : PageAsync<BetaWorkspaceMember> {

    /**
     * Delegates to [MemberListPageResponse], but gracefully handles missing data.
     *
     * @see MemberListPageResponse.data
     */
    fun data(): List<BetaWorkspaceMember> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [MemberListPageResponse], but gracefully handles missing data.
     *
     * @see MemberListPageResponse.hasMore
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [MemberListPageResponse], but gracefully handles missing data.
     *
     * @see MemberListPageResponse.firstId
     */
    fun firstId(): Optional<String> = response._firstId().getOptional("first_id")

    /**
     * Delegates to [MemberListPageResponse], but gracefully handles missing data.
     *
     * @see MemberListPageResponse.lastId
     */
    fun lastId(): Optional<String> = response._lastId().getOptional("last_id")

    override fun items(): List<BetaWorkspaceMember> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && lastId().isPresent

    fun nextPageParams(): MemberListParams {
        val nextCursor =
            lastId().getOrNull() ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().afterId(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<MemberListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaWorkspaceMember> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): MemberListParams = params

    /** The response that this page was parsed from. */
    fun response(): MemberListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [MemberListPageAsync].
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

    /** A builder for [MemberListPageAsync]. */
    class Builder internal constructor() {

        private var service: MemberServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: MemberListParams? = null
        private var response: MemberListPageResponse? = null

        @JvmSynthetic
        internal fun from(memberListPageAsync: MemberListPageAsync) = apply {
            service = memberListPageAsync.service
            streamHandlerExecutor = memberListPageAsync.streamHandlerExecutor
            params = memberListPageAsync.params
            response = memberListPageAsync.response
        }

        fun service(service: MemberServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: MemberListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: MemberListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [MemberListPageAsync].
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
        fun build(): MemberListPageAsync =
            MemberListPageAsync(
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

        return other is MemberListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "MemberListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
