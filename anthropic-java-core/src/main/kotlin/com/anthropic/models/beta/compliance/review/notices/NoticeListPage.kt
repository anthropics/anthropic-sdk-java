package com.anthropic.models.beta.compliance.review.notices

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.compliance.review.NoticeService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see NoticeService.list */
class NoticeListPage
private constructor(
    private val service: NoticeService,
    private val params: NoticeListParams,
    private val response: NoticeListPageResponse,
) : Page<BetaComplianceReviewNotice> {

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

    override fun nextPage(): NoticeListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaComplianceReviewNotice> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): NoticeListParams = params

    /** The response that this page was parsed from. */
    fun response(): NoticeListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [NoticeListPage].
         *
         * The following fields are required:
         * ```java
         * .service()
         * .params()
         * .response()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [NoticeListPage]. */
    class Builder internal constructor() {

        private var service: NoticeService? = null
        private var params: NoticeListParams? = null
        private var response: NoticeListPageResponse? = null

        @JvmSynthetic
        internal fun from(noticeListPage: NoticeListPage) = apply {
            service = noticeListPage.service
            params = noticeListPage.params
            response = noticeListPage.response
        }

        fun service(service: NoticeService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: NoticeListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: NoticeListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [NoticeListPage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .service()
         * .params()
         * .response()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): NoticeListPage =
            NoticeListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is NoticeListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "NoticeListPage{service=$service, params=$params, response=$response}"
}
