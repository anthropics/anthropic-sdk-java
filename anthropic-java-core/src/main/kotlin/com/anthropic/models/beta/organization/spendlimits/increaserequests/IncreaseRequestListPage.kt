package com.anthropic.models.beta.organization.spendlimits.increaserequests

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.spendlimits.IncreaseRequestService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see IncreaseRequestService.list */
class IncreaseRequestListPage
private constructor(
    private val service: IncreaseRequestService,
    private val params: IncreaseRequestListParams,
    private val response: IncreaseRequestListPageResponse,
) : Page<BetaSpendLimitIncreaseRequest> {

    /**
     * Delegates to [IncreaseRequestListPageResponse], but gracefully handles missing data.
     *
     * @see IncreaseRequestListPageResponse.data
     */
    fun data(): List<BetaSpendLimitIncreaseRequest> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [IncreaseRequestListPageResponse], but gracefully handles missing data.
     *
     * @see IncreaseRequestListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaSpendLimitIncreaseRequest> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): IncreaseRequestListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): IncreaseRequestListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaSpendLimitIncreaseRequest> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): IncreaseRequestListParams = params

    /** The response that this page was parsed from. */
    fun response(): IncreaseRequestListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [IncreaseRequestListPage].
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

    /** A builder for [IncreaseRequestListPage]. */
    class Builder internal constructor() {

        private var service: IncreaseRequestService? = null
        private var params: IncreaseRequestListParams? = null
        private var response: IncreaseRequestListPageResponse? = null

        @JvmSynthetic
        internal fun from(increaseRequestListPage: IncreaseRequestListPage) = apply {
            service = increaseRequestListPage.service
            params = increaseRequestListPage.params
            response = increaseRequestListPage.response
        }

        fun service(service: IncreaseRequestService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: IncreaseRequestListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: IncreaseRequestListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [IncreaseRequestListPage].
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
        fun build(): IncreaseRequestListPage =
            IncreaseRequestListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is IncreaseRequestListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "IncreaseRequestListPage{service=$service, params=$params, response=$response}"
}
