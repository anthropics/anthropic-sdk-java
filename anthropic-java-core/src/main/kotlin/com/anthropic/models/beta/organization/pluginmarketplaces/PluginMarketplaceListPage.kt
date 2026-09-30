package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.PluginMarketplaceService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see PluginMarketplaceService.list */
class PluginMarketplaceListPage
private constructor(
    private val service: PluginMarketplaceService,
    private val params: PluginMarketplaceListParams,
    private val response: PluginMarketplaceListPageResponse,
) : Page<BetaPluginMarketplace> {

    /**
     * Delegates to [PluginMarketplaceListPageResponse], but gracefully handles missing data.
     *
     * @see PluginMarketplaceListPageResponse.data
     */
    fun data(): List<BetaPluginMarketplace> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [PluginMarketplaceListPageResponse], but gracefully handles missing data.
     *
     * @see PluginMarketplaceListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaPluginMarketplace> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): PluginMarketplaceListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): PluginMarketplaceListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaPluginMarketplace> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): PluginMarketplaceListParams = params

    /** The response that this page was parsed from. */
    fun response(): PluginMarketplaceListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [PluginMarketplaceListPage].
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

    /** A builder for [PluginMarketplaceListPage]. */
    class Builder internal constructor() {

        private var service: PluginMarketplaceService? = null
        private var params: PluginMarketplaceListParams? = null
        private var response: PluginMarketplaceListPageResponse? = null

        @JvmSynthetic
        internal fun from(pluginMarketplaceListPage: PluginMarketplaceListPage) = apply {
            service = pluginMarketplaceListPage.service
            params = pluginMarketplaceListPage.params
            response = pluginMarketplaceListPage.response
        }

        fun service(service: PluginMarketplaceService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: PluginMarketplaceListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: PluginMarketplaceListPageResponse) = apply {
            this.response = response
        }

        /**
         * Returns an immutable instance of [PluginMarketplaceListPage].
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
        fun build(): PluginMarketplaceListPage =
            PluginMarketplaceListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is PluginMarketplaceListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "PluginMarketplaceListPage{service=$service, params=$params, response=$response}"
}
