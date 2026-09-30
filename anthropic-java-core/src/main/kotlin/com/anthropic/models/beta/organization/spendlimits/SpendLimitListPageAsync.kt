package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.SpendLimitServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see SpendLimitServiceAsync.list */
class SpendLimitListPageAsync
private constructor(
    private val service: SpendLimitServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: SpendLimitListParams,
    private val response: SpendLimitListPageResponse,
) : PageAsync<BetaSpendLimit> {

    /**
     * Delegates to [SpendLimitListPageResponse], but gracefully handles missing data.
     *
     * @see SpendLimitListPageResponse.data
     */
    fun data(): List<BetaSpendLimit> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [SpendLimitListPageResponse], but gracefully handles missing data.
     *
     * @see SpendLimitListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaSpendLimit> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): SpendLimitListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<SpendLimitListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaSpendLimit> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): SpendLimitListParams = params

    /** The response that this page was parsed from. */
    fun response(): SpendLimitListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SpendLimitListPageAsync].
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

    /** A builder for [SpendLimitListPageAsync]. */
    class Builder internal constructor() {

        private var service: SpendLimitServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: SpendLimitListParams? = null
        private var response: SpendLimitListPageResponse? = null

        @JvmSynthetic
        internal fun from(spendLimitListPageAsync: SpendLimitListPageAsync) = apply {
            service = spendLimitListPageAsync.service
            streamHandlerExecutor = spendLimitListPageAsync.streamHandlerExecutor
            params = spendLimitListPageAsync.params
            response = spendLimitListPageAsync.response
        }

        fun service(service: SpendLimitServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: SpendLimitListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: SpendLimitListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [SpendLimitListPageAsync].
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
        fun build(): SpendLimitListPageAsync =
            SpendLimitListPageAsync(
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

        return other is SpendLimitListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "SpendLimitListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
