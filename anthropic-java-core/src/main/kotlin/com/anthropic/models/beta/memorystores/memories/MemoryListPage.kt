// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.memorystores.MemoryService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see MemoryService.list */
class MemoryListPage
private constructor(
    private val service: MemoryService,
    private val params: MemoryListParams,
    private val response: MemoryListPageResponse,
) : Page<BetaManagedAgentsMemoryListItem> {

    /**
     * Delegates to [MemoryListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsMemoryListItem> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [MemoryListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsMemoryListItem> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): MemoryListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): MemoryListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaManagedAgentsMemoryListItem> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): MemoryListParams = params

    /** The response that this page was parsed from. */
    fun response(): MemoryListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [MemoryListPage].
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

    /** A builder for [MemoryListPage]. */
    class Builder internal constructor() {

        private var service: MemoryService? = null
        private var params: MemoryListParams? = null
        private var response: MemoryListPageResponse? = null

        @JvmSynthetic
        internal fun from(memoryListPage: MemoryListPage) = apply {
            service = memoryListPage.service
            params = memoryListPage.params
            response = memoryListPage.response
        }

        fun service(service: MemoryService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: MemoryListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: MemoryListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [MemoryListPage].
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
        fun build(): MemoryListPage =
            MemoryListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MemoryListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "MemoryListPage{service=$service, params=$params, response=$response}"
}
