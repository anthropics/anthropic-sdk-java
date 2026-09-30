package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.SpendLimitService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see SpendLimitService.list */
class SpendLimitListPage
private constructor(
    private val service: SpendLimitService,
    private val params: SpendLimitListParams,
    private val response: SpendLimitListPageResponse,
) : Page<BetaSpendLimit> {

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

    override fun nextPage(): SpendLimitListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaSpendLimit> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): SpendLimitListParams = params

    /** The response that this page was parsed from. */
    fun response(): SpendLimitListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SpendLimitListPage].
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

    /** A builder for [SpendLimitListPage]. */
    class Builder internal constructor() {

        private var service: SpendLimitService? = null
        private var params: SpendLimitListParams? = null
        private var response: SpendLimitListPageResponse? = null

        @JvmSynthetic
        internal fun from(spendLimitListPage: SpendLimitListPage) = apply {
            service = spendLimitListPage.service
            params = spendLimitListPage.params
            response = spendLimitListPage.response
        }

        fun service(service: SpendLimitService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: SpendLimitListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: SpendLimitListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [SpendLimitListPage].
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
        fun build(): SpendLimitListPage =
            SpendLimitListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SpendLimitListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "SpendLimitListPage{service=$service, params=$params, response=$response}"
}
