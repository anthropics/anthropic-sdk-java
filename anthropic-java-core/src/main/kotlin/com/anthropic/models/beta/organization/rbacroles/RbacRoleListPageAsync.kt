package com.anthropic.models.beta.organization.rbacroles

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.RbacRoleServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see RbacRoleServiceAsync.list */
class RbacRoleListPageAsync
private constructor(
    private val service: RbacRoleServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: RbacRoleListParams,
    private val response: RbacRoleListPageResponse,
) : PageAsync<BetaRbacRole> {

    /**
     * Delegates to [RbacRoleListPageResponse], but gracefully handles missing data.
     *
     * @see RbacRoleListPageResponse.data
     */
    fun data(): List<BetaRbacRole> = response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [RbacRoleListPageResponse], but gracefully handles missing data.
     *
     * @see RbacRoleListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaRbacRole> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): RbacRoleListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<RbacRoleListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaRbacRole> = AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): RbacRoleListParams = params

    /** The response that this page was parsed from. */
    fun response(): RbacRoleListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [RbacRoleListPageAsync].
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

    /** A builder for [RbacRoleListPageAsync]. */
    class Builder internal constructor() {

        private var service: RbacRoleServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: RbacRoleListParams? = null
        private var response: RbacRoleListPageResponse? = null

        @JvmSynthetic
        internal fun from(rbacRoleListPageAsync: RbacRoleListPageAsync) = apply {
            service = rbacRoleListPageAsync.service
            streamHandlerExecutor = rbacRoleListPageAsync.streamHandlerExecutor
            params = rbacRoleListPageAsync.params
            response = rbacRoleListPageAsync.response
        }

        fun service(service: RbacRoleServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: RbacRoleListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: RbacRoleListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [RbacRoleListPageAsync].
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
        fun build(): RbacRoleListPageAsync =
            RbacRoleListPageAsync(
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

        return other is RbacRoleListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "RbacRoleListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
