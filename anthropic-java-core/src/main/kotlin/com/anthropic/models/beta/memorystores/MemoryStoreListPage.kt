// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.MemoryStoreService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see MemoryStoreService.list */
class MemoryStoreListPage
private constructor(
    private val service: MemoryStoreService,
    private val params: MemoryStoreListParams,
    private val response: MemoryStoreListPageResponse,
) : Page<BetaManagedAgentsMemoryStore> {

    /**
     * Delegates to [MemoryStoreListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryStoreListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsMemoryStore> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [MemoryStoreListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryStoreListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsMemoryStore> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): MemoryStoreListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): MemoryStoreListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaManagedAgentsMemoryStore> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): MemoryStoreListParams = params

    /** The response that this page was parsed from. */
    fun response(): MemoryStoreListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [MemoryStoreListPage].
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

    /** A builder for [MemoryStoreListPage]. */
    class Builder internal constructor() {

        private var service: MemoryStoreService? = null
        private var params: MemoryStoreListParams? = null
        private var response: MemoryStoreListPageResponse? = null

        @JvmSynthetic
        internal fun from(memoryStoreListPage: MemoryStoreListPage) = apply {
            service = memoryStoreListPage.service
            params = memoryStoreListPage.params
            response = memoryStoreListPage.response
        }

        fun service(service: MemoryStoreService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: MemoryStoreListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: MemoryStoreListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [MemoryStoreListPage].
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
        fun build(): MemoryStoreListPage =
            MemoryStoreListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MemoryStoreListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "MemoryStoreListPage{service=$service, params=$params, response=$response}"
}
