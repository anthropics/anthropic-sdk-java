// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels.certificates

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.tunnels.CertificateService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see CertificateService.list */
class CertificateListPage
private constructor(
    private val service: CertificateService,
    private val params: CertificateListParams,
    private val response: CertificateListPageResponse,
) : Page<BetaTunnelCertificate> {

    /**
     * Delegates to [CertificateListPageResponse], but gracefully handles missing data.
     *
     * @see CertificateListPageResponse.data
     */
    fun data(): List<BetaTunnelCertificate> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [CertificateListPageResponse], but gracefully handles missing data.
     *
     * @see CertificateListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaTunnelCertificate> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): CertificateListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CertificateListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaTunnelCertificate> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): CertificateListParams = params

    /** The response that this page was parsed from. */
    fun response(): CertificateListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CertificateListPage].
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

    /** A builder for [CertificateListPage]. */
    class Builder internal constructor() {

        private var service: CertificateService? = null
        private var params: CertificateListParams? = null
        private var response: CertificateListPageResponse? = null

        @JvmSynthetic
        internal fun from(certificateListPage: CertificateListPage) = apply {
            service = certificateListPage.service
            params = certificateListPage.params
            response = certificateListPage.response
        }

        fun service(service: CertificateService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: CertificateListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: CertificateListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [CertificateListPage].
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
        fun build(): CertificateListPage =
            CertificateListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CertificateListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "CertificateListPage{service=$service, params=$params, response=$response}"
}
