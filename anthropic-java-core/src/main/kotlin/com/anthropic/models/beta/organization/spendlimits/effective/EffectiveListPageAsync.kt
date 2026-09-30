package com.anthropic.models.beta.organization.spendlimits.effective

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.spendlimits.BetaSpendSummary
import com.anthropic.services.async.beta.organization.spendlimits.EffectiveServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see EffectiveServiceAsync.list */
class EffectiveListPageAsync
private constructor(
    private val service: EffectiveServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: EffectiveListParams,
    private val response: EffectiveListPageResponse,
) : PageAsync<BetaSpendSummary> {

    /**
     * Delegates to [EffectiveListPageResponse], but gracefully handles missing data.
     *
     * @see EffectiveListPageResponse.data
     */
    fun data(): List<BetaSpendSummary> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [EffectiveListPageResponse], but gracefully handles missing data.
     *
     * @see EffectiveListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaSpendSummary> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): EffectiveListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<EffectiveListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaSpendSummary> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): EffectiveListParams = params

    /** The response that this page was parsed from. */
    fun response(): EffectiveListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [EffectiveListPageAsync].
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

    /** A builder for [EffectiveListPageAsync]. */
    class Builder internal constructor() {

        private var service: EffectiveServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: EffectiveListParams? = null
        private var response: EffectiveListPageResponse? = null

        @JvmSynthetic
        internal fun from(effectiveListPageAsync: EffectiveListPageAsync) = apply {
            service = effectiveListPageAsync.service
            streamHandlerExecutor = effectiveListPageAsync.streamHandlerExecutor
            params = effectiveListPageAsync.params
            response = effectiveListPageAsync.response
        }

        fun service(service: EffectiveServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: EffectiveListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: EffectiveListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [EffectiveListPageAsync].
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
        fun build(): EffectiveListPageAsync =
            EffectiveListPageAsync(
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

        return other is EffectiveListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "EffectiveListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
