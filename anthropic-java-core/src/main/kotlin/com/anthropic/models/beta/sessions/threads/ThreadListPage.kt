// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.sessions.ThreadService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see ThreadService.list */
class ThreadListPage
private constructor(
    private val service: ThreadService,
    private val params: ThreadListParams,
    private val response: ThreadListPageResponse,
) : Page<BetaManagedAgentsSessionThread> {

    /**
     * Delegates to [ThreadListPageResponse], but gracefully handles missing data.
     *
     * @see ThreadListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsSessionThread> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [ThreadListPageResponse], but gracefully handles missing data.
     *
     * @see ThreadListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsSessionThread> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): ThreadListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): ThreadListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaManagedAgentsSessionThread> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): ThreadListParams = params

    /** The response that this page was parsed from. */
    fun response(): ThreadListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ThreadListPage].
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

    /** A builder for [ThreadListPage]. */
    class Builder internal constructor() {

        private var service: ThreadService? = null
        private var params: ThreadListParams? = null
        private var response: ThreadListPageResponse? = null

        @JvmSynthetic
        internal fun from(threadListPage: ThreadListPage) = apply {
            service = threadListPage.service
            params = threadListPage.params
            response = threadListPage.response
        }

        fun service(service: ThreadService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: ThreadListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ThreadListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ThreadListPage].
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
        fun build(): ThreadListPage =
            ThreadListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ThreadListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "ThreadListPage{service=$service, params=$params, response=$response}"
}
