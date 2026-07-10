// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.DreamService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see DreamService.list */
class DreamListPage
private constructor(
    private val service: DreamService,
    private val params: DreamListParams,
    private val response: DreamListPageResponse,
) : Page<BetaDream> {

    /**
     * Delegates to [DreamListPageResponse], but gracefully handles missing data.
     *
     * @see DreamListPageResponse.data
     */
    fun data(): List<BetaDream> = response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [DreamListPageResponse], but gracefully handles missing data.
     *
     * @see DreamListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaDream> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): DreamListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): DreamListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaDream> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): DreamListParams = params

    /** The response that this page was parsed from. */
    fun response(): DreamListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [DreamListPage].
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

    /** A builder for [DreamListPage]. */
    class Builder internal constructor() {

        private var service: DreamService? = null
        private var params: DreamListParams? = null
        private var response: DreamListPageResponse? = null

        @JvmSynthetic
        internal fun from(dreamListPage: DreamListPage) = apply {
            service = dreamListPage.service
            params = dreamListPage.params
            response = dreamListPage.response
        }

        fun service(service: DreamService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: DreamListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: DreamListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [DreamListPage].
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
        fun build(): DreamListPage =
            DreamListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is DreamListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "DreamListPage{service=$service, params=$params, response=$response}"
}
