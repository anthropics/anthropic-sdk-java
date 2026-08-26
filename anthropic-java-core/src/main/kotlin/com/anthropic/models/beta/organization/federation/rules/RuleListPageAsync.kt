// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.federation.RuleServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see RuleServiceAsync.list */
class RuleListPageAsync
private constructor(
    private val service: RuleServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: RuleListParams,
    private val response: RuleListPageResponse,
) : PageAsync<BetaFederationRule> {

    /**
     * Delegates to [RuleListPageResponse], but gracefully handles missing data.
     *
     * @see RuleListPageResponse.data
     */
    fun data(): List<BetaFederationRule> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [RuleListPageResponse], but gracefully handles missing data.
     *
     * @see RuleListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaFederationRule> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): RuleListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<RuleListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaFederationRule> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): RuleListParams = params

    /** The response that this page was parsed from. */
    fun response(): RuleListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [RuleListPageAsync].
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

    /** A builder for [RuleListPageAsync]. */
    class Builder internal constructor() {

        private var service: RuleServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: RuleListParams? = null
        private var response: RuleListPageResponse? = null

        @JvmSynthetic
        internal fun from(ruleListPageAsync: RuleListPageAsync) = apply {
            service = ruleListPageAsync.service
            streamHandlerExecutor = ruleListPageAsync.streamHandlerExecutor
            params = ruleListPageAsync.params
            response = ruleListPageAsync.response
        }

        fun service(service: RuleServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: RuleListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: RuleListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [RuleListPageAsync].
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
        fun build(): RuleListPageAsync =
            RuleListPageAsync(
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

        return other is RuleListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "RuleListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
