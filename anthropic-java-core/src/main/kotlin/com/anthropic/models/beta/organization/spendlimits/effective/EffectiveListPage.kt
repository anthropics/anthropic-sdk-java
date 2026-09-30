package com.anthropic.models.beta.organization.spendlimits.effective

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.spendlimits.BetaSpendSummary
import com.anthropic.services.blocking.beta.organization.spendlimits.EffectiveService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see EffectiveService.list */
class EffectiveListPage
private constructor(
    private val service: EffectiveService,
    private val params: EffectiveListParams,
    private val response: EffectiveListPageResponse,
) : Page<BetaSpendSummary> {

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

    override fun nextPage(): EffectiveListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaSpendSummary> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): EffectiveListParams = params

    /** The response that this page was parsed from. */
    fun response(): EffectiveListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [EffectiveListPage].
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

    /** A builder for [EffectiveListPage]. */
    class Builder internal constructor() {

        private var service: EffectiveService? = null
        private var params: EffectiveListParams? = null
        private var response: EffectiveListPageResponse? = null

        @JvmSynthetic
        internal fun from(effectiveListPage: EffectiveListPage) = apply {
            service = effectiveListPage.service
            params = effectiveListPage.params
            response = effectiveListPage.response
        }

        fun service(service: EffectiveService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: EffectiveListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: EffectiveListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [EffectiveListPage].
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
        fun build(): EffectiveListPage =
            EffectiveListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is EffectiveListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "EffectiveListPage{service=$service, params=$params, response=$response}"
}
