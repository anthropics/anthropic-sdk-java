package com.anthropic.models.beta.organization.plugins.shares

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.plugins.ShareService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see ShareService.list */
class ShareListPage
private constructor(
    private val service: ShareService,
    private val params: ShareListParams,
    private val response: ShareListPageResponse,
) : Page<BetaPluginShare> {

    /**
     * Delegates to [ShareListPageResponse], but gracefully handles missing data.
     *
     * @see ShareListPageResponse.data
     */
    fun data(): List<BetaPluginShare> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [ShareListPageResponse], but gracefully handles missing data.
     *
     * @see ShareListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaPluginShare> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): ShareListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): ShareListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaPluginShare> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): ShareListParams = params

    /** The response that this page was parsed from. */
    fun response(): ShareListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ShareListPage].
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

    /** A builder for [ShareListPage]. */
    class Builder internal constructor() {

        private var service: ShareService? = null
        private var params: ShareListParams? = null
        private var response: ShareListPageResponse? = null

        @JvmSynthetic
        internal fun from(shareListPage: ShareListPage) = apply {
            service = shareListPage.service
            params = shareListPage.params
            response = shareListPage.response
        }

        fun service(service: ShareService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: ShareListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ShareListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ShareListPage].
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
        fun build(): ShareListPage =
            ShareListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ShareListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "ShareListPage{service=$service, params=$params, response=$response}"
}
