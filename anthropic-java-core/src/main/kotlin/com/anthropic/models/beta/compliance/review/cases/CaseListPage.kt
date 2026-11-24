package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.compliance.review.CaseService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see CaseService.list */
class CaseListPage
private constructor(
    private val service: CaseService,
    private val params: CaseListParams,
    private val response: CaseListPageResponse,
) : Page<BetaComplianceReviewCase> {

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

    override fun nextPage(): CaseListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaComplianceReviewCase> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): CaseListParams = params

    /** The response that this page was parsed from. */
    fun response(): CaseListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CaseListPage].
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

    /** A builder for [CaseListPage]. */
    class Builder internal constructor() {

        private var service: CaseService? = null
        private var params: CaseListParams? = null
        private var response: CaseListPageResponse? = null

        @JvmSynthetic
        internal fun from(caseListPage: CaseListPage) = apply {
            service = caseListPage.service
            params = caseListPage.params
            response = caseListPage.response
        }

        fun service(service: CaseService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: CaseListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: CaseListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [CaseListPage].
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
        fun build(): CaseListPage =
            CaseListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CaseListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "CaseListPage{service=$service, params=$params, response=$response}"
}
