package com.anthropic.models.beta.organization.analytics.userusagereport

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageUsersItem
import com.anthropic.services.async.beta.organization.analytics.UserUsageReportServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see UserUsageReportServiceAsync.list */
class UserUsageReportListPageAsync
private constructor(
    private val service: UserUsageReportServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: UserUsageReportListParams,
    private val response: UserUsageReportListPageResponse,
) : PageAsync<BetaAnalyticsUsageUsersItem> {

    /**
     * Delegates to [UserUsageReportListPageResponse], but gracefully handles missing data.
     *
     * @see UserUsageReportListPageResponse.data
     */
    fun data(): List<BetaAnalyticsUsageUsersItem> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [UserUsageReportListPageResponse], but gracefully handles missing data.
     *
     * @see UserUsageReportListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsUsageUsersItem> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): UserUsageReportListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<UserUsageReportListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaAnalyticsUsageUsersItem> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): UserUsageReportListParams = params

    /** The response that this page was parsed from. */
    fun response(): UserUsageReportListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UserUsageReportListPageAsync].
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

    /** A builder for [UserUsageReportListPageAsync]. */
    class Builder internal constructor() {

        private var service: UserUsageReportServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: UserUsageReportListParams? = null
        private var response: UserUsageReportListPageResponse? = null

        @JvmSynthetic
        internal fun from(userUsageReportListPageAsync: UserUsageReportListPageAsync) = apply {
            service = userUsageReportListPageAsync.service
            streamHandlerExecutor = userUsageReportListPageAsync.streamHandlerExecutor
            params = userUsageReportListPageAsync.params
            response = userUsageReportListPageAsync.response
        }

        fun service(service: UserUsageReportServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: UserUsageReportListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: UserUsageReportListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [UserUsageReportListPageAsync].
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
        fun build(): UserUsageReportListPageAsync =
            UserUsageReportListPageAsync(
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

        return other is UserUsageReportListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "UserUsageReportListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
