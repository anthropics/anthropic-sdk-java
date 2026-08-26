// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.federation.RuleService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see RuleService.list */
class RuleListPage
private constructor(
    private val service: RuleService,
    private val params: RuleListParams,
    private val response: RuleListPageResponse,
) : Page<BetaFederationRule> {

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

    override fun nextPage(): RuleListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaFederationRule> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): RuleListParams = params

    /** The response that this page was parsed from. */
    fun response(): RuleListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [RuleListPage].
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

    /** A builder for [RuleListPage]. */
    class Builder internal constructor() {

        private var service: RuleService? = null
        private var params: RuleListParams? = null
        private var response: RuleListPageResponse? = null

        @JvmSynthetic
        internal fun from(ruleListPage: RuleListPage) = apply {
            service = ruleListPage.service
            params = ruleListPage.params
            response = ruleListPage.response
        }

        fun service(service: RuleService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: RuleListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: RuleListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [RuleListPage].
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
        fun build(): RuleListPage =
            RuleListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RuleListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "RuleListPage{service=$service, params=$params, response=$response}"
}
