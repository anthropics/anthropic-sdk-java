package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.compliance.review.CaseServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see CaseServiceAsync.list */
class CaseListPageAsync
private constructor(
    private val service: CaseServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: CaseListParams,
    private val response: CaseListPageResponse,
) : PageAsync<BetaComplianceReviewCase> {

    /**
     * Delegates to [CaseListPageResponse], but gracefully handles missing data.
     *
     * @see CaseListPageResponse.data
     */
    fun data(): List<BetaComplianceReviewCase> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [CaseListPageResponse], but gracefully handles missing data.
     *
     * @see CaseListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaComplianceReviewCase> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): CaseListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<CaseListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaComplianceReviewCase> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): CaseListParams = params

    /** The response that this page was parsed from. */
    fun response(): CaseListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CaseListPageAsync].
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

    /** A builder for [CaseListPageAsync]. */
    class Builder internal constructor() {

        private var service: CaseServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: CaseListParams? = null
        private var response: CaseListPageResponse? = null

        @JvmSynthetic
        internal fun from(caseListPageAsync: CaseListPageAsync) = apply {
            service = caseListPageAsync.service
            streamHandlerExecutor = caseListPageAsync.streamHandlerExecutor
            params = caseListPageAsync.params
            response = caseListPageAsync.response
        }

        fun service(service: CaseServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: CaseListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: CaseListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [CaseListPageAsync].
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
        fun build(): CaseListPageAsync =
            CaseListPageAsync(
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

        return other is CaseListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "CaseListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
