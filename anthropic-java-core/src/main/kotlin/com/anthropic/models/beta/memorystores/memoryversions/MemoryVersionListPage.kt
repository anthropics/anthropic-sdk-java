// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.memorystores.MemoryVersionService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see MemoryVersionService.list */
class MemoryVersionListPage
private constructor(
    private val service: MemoryVersionService,
    private val params: MemoryVersionListParams,
    private val response: MemoryVersionListPageResponse,
) : Page<BetaManagedAgentsMemoryVersion> {

    /**
     * Delegates to [MemoryVersionListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryVersionListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsMemoryVersion> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [MemoryVersionListPageResponse], but gracefully handles missing data.
     *
     * @see MemoryVersionListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsMemoryVersion> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): MemoryVersionListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): MemoryVersionListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaManagedAgentsMemoryVersion> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): MemoryVersionListParams = params

    /** The response that this page was parsed from. */
    fun response(): MemoryVersionListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [MemoryVersionListPage].
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

    /** A builder for [MemoryVersionListPage]. */
    class Builder internal constructor() {

        private var service: MemoryVersionService? = null
        private var params: MemoryVersionListParams? = null
        private var response: MemoryVersionListPageResponse? = null

        @JvmSynthetic
        internal fun from(memoryVersionListPage: MemoryVersionListPage) = apply {
            service = memoryVersionListPage.service
            params = memoryVersionListPage.params
            response = memoryVersionListPage.response
        }

        fun service(service: MemoryVersionService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: MemoryVersionListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: MemoryVersionListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [MemoryVersionListPage].
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
        fun build(): MemoryVersionListPage =
            MemoryVersionListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MemoryVersionListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "MemoryVersionListPage{service=$service, params=$params, response=$response}"
}
