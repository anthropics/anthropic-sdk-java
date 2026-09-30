package com.anthropic.models.beta.organization.analytics.usercostreport

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsCostUsersItem
import com.anthropic.services.async.beta.organization.analytics.UserCostReportServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see UserCostReportServiceAsync.list */
class UserCostReportListPageAsync
private constructor(
    private val service: UserCostReportServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: UserCostReportListParams,
    private val response: UserCostReportListPageResponse,
) : PageAsync<BetaAnalyticsCostUsersItem> {

    /**
     * Delegates to [UserCostReportListPageResponse], but gracefully handles missing data.
     *
     * @see UserCostReportListPageResponse.data
     */
    fun data(): List<BetaAnalyticsCostUsersItem> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [UserCostReportListPageResponse], but gracefully handles missing data.
     *
     * @see UserCostReportListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsCostUsersItem> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): UserCostReportListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<UserCostReportListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaAnalyticsCostUsersItem> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): UserCostReportListParams = params

    /** The response that this page was parsed from. */
    fun response(): UserCostReportListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UserCostReportListPageAsync].
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

    /** A builder for [UserCostReportListPageAsync]. */
    class Builder internal constructor() {

        private var service: UserCostReportServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: UserCostReportListParams? = null
        private var response: UserCostReportListPageResponse? = null

        @JvmSynthetic
        internal fun from(userCostReportListPageAsync: UserCostReportListPageAsync) = apply {
            service = userCostReportListPageAsync.service
            streamHandlerExecutor = userCostReportListPageAsync.streamHandlerExecutor
            params = userCostReportListPageAsync.params
            response = userCostReportListPageAsync.response
        }

        fun service(service: UserCostReportServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: UserCostReportListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: UserCostReportListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [UserCostReportListPageAsync].
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
        fun build(): UserCostReportListPageAsync =
            UserCostReportListPageAsync(
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

        return other is UserCostReportListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "UserCostReportListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
