// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.vaults.CredentialService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see CredentialService.list */
class CredentialListPage
private constructor(
    private val service: CredentialService,
    private val params: CredentialListParams,
    private val response: CredentialListPageResponse,
) : Page<BetaManagedAgentsCredential> {

    /**
     * Delegates to [CredentialListPageResponse], but gracefully handles missing data.
     *
     * @see CredentialListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsCredential> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [CredentialListPageResponse], but gracefully handles missing data.
     *
     * @see CredentialListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsCredential> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): CredentialListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CredentialListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaManagedAgentsCredential> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): CredentialListParams = params

    /** The response that this page was parsed from. */
    fun response(): CredentialListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CredentialListPage].
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

    /** A builder for [CredentialListPage]. */
    class Builder internal constructor() {

        private var service: CredentialService? = null
        private var params: CredentialListParams? = null
        private var response: CredentialListPageResponse? = null

        @JvmSynthetic
        internal fun from(credentialListPage: CredentialListPage) = apply {
            service = credentialListPage.service
            params = credentialListPage.params
            response = credentialListPage.response
        }

        fun service(service: CredentialService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: CredentialListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: CredentialListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [CredentialListPage].
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
        fun build(): CredentialListPage =
            CredentialListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CredentialListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "CredentialListPage{service=$service, params=$params, response=$response}"
}
