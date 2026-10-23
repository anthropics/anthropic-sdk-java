package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.claudetag.bundles.TargetServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see TargetServiceAsync.list */
class TargetListPageAsync
private constructor(
    private val service: TargetServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: TargetListParams,
    private val response: TargetListPageResponse,
) : PageAsync<BetaClaudeTagBundleTarget> {

    /**
     * Delegates to [TargetListPageResponse], but gracefully handles missing data.
     *
     * @see TargetListPageResponse.data
     */
    fun data(): List<BetaClaudeTagBundleTarget> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [TargetListPageResponse], but gracefully handles missing data.
     *
     * @see TargetListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaClaudeTagBundleTarget> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): TargetListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<TargetListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaClaudeTagBundleTarget> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): TargetListParams = params

    /** The response that this page was parsed from. */
    fun response(): TargetListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [TargetListPageAsync].
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

    /** A builder for [TargetListPageAsync]. */
    class Builder internal constructor() {

        private var service: TargetServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: TargetListParams? = null
        private var response: TargetListPageResponse? = null

        @JvmSynthetic
        internal fun from(targetListPageAsync: TargetListPageAsync) = apply {
            service = targetListPageAsync.service
            streamHandlerExecutor = targetListPageAsync.streamHandlerExecutor
            params = targetListPageAsync.params
            response = targetListPageAsync.response
        }

        fun service(service: TargetServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: TargetListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: TargetListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [TargetListPageAsync].
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
        fun build(): TargetListPageAsync =
            TargetListPageAsync(
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

        return other is TargetListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "TargetListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
