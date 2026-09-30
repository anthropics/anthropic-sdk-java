package com.anthropic.models.beta.organization.rbacgroups

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.RbacGroupServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see RbacGroupServiceAsync.list */
class RbacGroupListPageAsync
private constructor(
    private val service: RbacGroupServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: RbacGroupListParams,
    private val response: RbacGroupListPageResponse,
) : PageAsync<BetaRbacGroup> {

    /**
     * Delegates to [RbacGroupListPageResponse], but gracefully handles missing data.
     *
     * @see RbacGroupListPageResponse.data
     */
    fun data(): List<BetaRbacGroup> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [RbacGroupListPageResponse], but gracefully handles missing data.
     *
     * @see RbacGroupListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaRbacGroup> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): RbacGroupListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<RbacGroupListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaRbacGroup> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): RbacGroupListParams = params

    /** The response that this page was parsed from. */
    fun response(): RbacGroupListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [RbacGroupListPageAsync].
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

    /** A builder for [RbacGroupListPageAsync]. */
    class Builder internal constructor() {

        private var service: RbacGroupServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: RbacGroupListParams? = null
        private var response: RbacGroupListPageResponse? = null

        @JvmSynthetic
        internal fun from(rbacGroupListPageAsync: RbacGroupListPageAsync) = apply {
            service = rbacGroupListPageAsync.service
            streamHandlerExecutor = rbacGroupListPageAsync.streamHandlerExecutor
            params = rbacGroupListPageAsync.params
            response = rbacGroupListPageAsync.response
        }

        fun service(service: RbacGroupServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: RbacGroupListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: RbacGroupListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [RbacGroupListPageAsync].
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
        fun build(): RbacGroupListPageAsync =
            RbacGroupListPageAsync(
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

        return other is RbacGroupListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "RbacGroupListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
