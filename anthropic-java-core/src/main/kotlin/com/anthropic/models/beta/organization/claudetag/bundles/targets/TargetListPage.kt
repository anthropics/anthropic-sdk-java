package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.claudetag.bundles.TargetService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see TargetService.list */
class TargetListPage
private constructor(
    private val service: TargetService,
    private val params: TargetListParams,
    private val response: TargetListPageResponse,
) : Page<BetaClaudeTagBundleTarget> {

    /**
     * Delegates to [TargetListPageResponse], but gracefully handles missing data.
     *
     * @see TargetListPageResponse.data
     */
    fun data(): List<BetaClaudeTagBundleTarget> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [TargetListPageResponse], but gracefully handles missing data.
     *
     * @see TargetListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaClaudeTagBundleTarget> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): TargetListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): TargetListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaClaudeTagBundleTarget> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): TargetListParams = params

    /** The response that this page was parsed from. */
    fun response(): TargetListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [TargetListPage].
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

    /** A builder for [TargetListPage]. */
    class Builder internal constructor() {

        private var service: TargetService? = null
        private var params: TargetListParams? = null
        private var response: TargetListPageResponse? = null

        @JvmSynthetic
        internal fun from(targetListPage: TargetListPage) = apply {
            service = targetListPage.service
            params = targetListPage.params
            response = targetListPage.response
        }

        fun service(service: TargetService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: TargetListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: TargetListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [TargetListPage].
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
        fun build(): TargetListPage =
            TargetListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TargetListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "TargetListPage{service=$service, params=$params, response=$response}"
}
