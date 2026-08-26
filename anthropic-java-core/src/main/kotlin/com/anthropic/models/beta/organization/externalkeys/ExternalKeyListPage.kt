// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.ExternalKeyService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see ExternalKeyService.list */
class ExternalKeyListPage
private constructor(
    private val service: ExternalKeyService,
    private val params: ExternalKeyListParams,
    private val response: ExternalKeyListPageResponse,
) : Page<BetaExternalKey> {

    /**
     * Delegates to [ExternalKeyListPageResponse], but gracefully handles missing data.
     *
     * @see ExternalKeyListPageResponse.data
     */
    fun data(): List<BetaExternalKey> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [ExternalKeyListPageResponse], but gracefully handles missing data.
     *
     * @see ExternalKeyListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaExternalKey> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): ExternalKeyListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): ExternalKeyListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaExternalKey> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): ExternalKeyListParams = params

    /** The response that this page was parsed from. */
    fun response(): ExternalKeyListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ExternalKeyListPage].
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

    /** A builder for [ExternalKeyListPage]. */
    class Builder internal constructor() {

        private var service: ExternalKeyService? = null
        private var params: ExternalKeyListParams? = null
        private var response: ExternalKeyListPageResponse? = null

        @JvmSynthetic
        internal fun from(externalKeyListPage: ExternalKeyListPage) = apply {
            service = externalKeyListPage.service
            params = externalKeyListPage.params
            response = externalKeyListPage.response
        }

        fun service(service: ExternalKeyService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: ExternalKeyListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ExternalKeyListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ExternalKeyListPage].
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
        fun build(): ExternalKeyListPage =
            ExternalKeyListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ExternalKeyListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "ExternalKeyListPage{service=$service, params=$params, response=$response}"
}
