// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.federation.IssuerService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see IssuerService.list */
class IssuerListPage
private constructor(
    private val service: IssuerService,
    private val params: IssuerListParams,
    private val response: IssuerListPageResponse,
) : Page<BetaFederationIssuer> {

    /**
     * Delegates to [IssuerListPageResponse], but gracefully handles missing data.
     *
     * @see IssuerListPageResponse.data
     */
    fun data(): List<BetaFederationIssuer> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [IssuerListPageResponse], but gracefully handles missing data.
     *
     * @see IssuerListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaFederationIssuer> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): IssuerListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): IssuerListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaFederationIssuer> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): IssuerListParams = params

    /** The response that this page was parsed from. */
    fun response(): IssuerListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [IssuerListPage].
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

    /** A builder for [IssuerListPage]. */
    class Builder internal constructor() {

        private var service: IssuerService? = null
        private var params: IssuerListParams? = null
        private var response: IssuerListPageResponse? = null

        @JvmSynthetic
        internal fun from(issuerListPage: IssuerListPage) = apply {
            service = issuerListPage.service
            params = issuerListPage.params
            response = issuerListPage.response
        }

        fun service(service: IssuerService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: IssuerListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: IssuerListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [IssuerListPage].
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
        fun build(): IssuerListPage =
            IssuerListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is IssuerListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "IssuerListPage{service=$service, params=$params, response=$response}"
}
