package com.anthropic.models.beta.compliance.review.notices

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.compliance.review.NoticeServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see NoticeServiceAsync.list */
class NoticeListPageAsync
private constructor(
    private val service: NoticeServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: NoticeListParams,
    private val response: NoticeListPageResponse,
) : PageAsync<BetaComplianceReviewNotice> {

    /**
     * Delegates to [NoticeListPageResponse], but gracefully handles missing data.
     *
     * @see NoticeListPageResponse.data
     */
    fun data(): List<BetaComplianceReviewNotice> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [NoticeListPageResponse], but gracefully handles missing data.
     *
     * @see NoticeListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaComplianceReviewNotice> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): NoticeListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<NoticeListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaComplianceReviewNotice> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): NoticeListParams = params

    /** The response that this page was parsed from. */
    fun response(): NoticeListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [NoticeListPageAsync].
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

    /** A builder for [NoticeListPageAsync]. */
    class Builder internal constructor() {

        private var service: NoticeServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: NoticeListParams? = null
        private var response: NoticeListPageResponse? = null

        @JvmSynthetic
        internal fun from(noticeListPageAsync: NoticeListPageAsync) = apply {
            service = noticeListPageAsync.service
            streamHandlerExecutor = noticeListPageAsync.streamHandlerExecutor
            params = noticeListPageAsync.params
            response = noticeListPageAsync.response
        }

        fun service(service: NoticeServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: NoticeListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: NoticeListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [NoticeListPageAsync].
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
        fun build(): NoticeListPageAsync =
            NoticeListPageAsync(
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

        return other is NoticeListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "NoticeListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
