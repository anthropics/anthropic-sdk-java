package com.anthropic.models.beta.organization.claudetag.bundles

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.claudetag.BundleService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see BundleService.list */
class BundleListPage
private constructor(
    private val service: BundleService,
    private val params: BundleListParams,
    private val response: BundleListPageResponse,
) : Page<BetaClaudeTagBundle> {

    /**
     * Delegates to [BundleListPageResponse], but gracefully handles missing data.
     *
     * @see BundleListPageResponse.data
     */
    fun data(): List<BetaClaudeTagBundle> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [BundleListPageResponse], but gracefully handles missing data.
     *
     * @see BundleListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaClaudeTagBundle> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): BundleListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): BundleListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaClaudeTagBundle> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): BundleListParams = params

    /** The response that this page was parsed from. */
    fun response(): BundleListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BundleListPage].
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

    /** A builder for [BundleListPage]. */
    class Builder internal constructor() {

        private var service: BundleService? = null
        private var params: BundleListParams? = null
        private var response: BundleListPageResponse? = null

        @JvmSynthetic
        internal fun from(bundleListPage: BundleListPage) = apply {
            service = bundleListPage.service
            params = bundleListPage.params
            response = bundleListPage.response
        }

        fun service(service: BundleService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: BundleListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: BundleListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [BundleListPage].
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
        fun build(): BundleListPage =
            BundleListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BundleListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "BundleListPage{service=$service, params=$params, response=$response}"
}
