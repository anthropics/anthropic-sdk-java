// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.environments.WorkService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see WorkService.list */
class WorkListPage
private constructor(
    private val service: WorkService,
    private val params: WorkListParams,
    private val response: BetaSelfHostedWorkListResponse,
) : Page<BetaSelfHostedWork> {

    /**
     * Delegates to [BetaSelfHostedWorkListResponse], but gracefully handles missing data.
     *
     * @see BetaSelfHostedWorkListResponse.data
     */
    fun data(): List<BetaSelfHostedWork> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [BetaSelfHostedWorkListResponse], but gracefully handles missing data.
     *
     * @see BetaSelfHostedWorkListResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaSelfHostedWork> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): WorkListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): WorkListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaSelfHostedWork> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): WorkListParams = params

    /** The response that this page was parsed from. */
    fun response(): BetaSelfHostedWorkListResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [WorkListPage].
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

    /** A builder for [WorkListPage]. */
    class Builder internal constructor() {

        private var service: WorkService? = null
        private var params: WorkListParams? = null
        private var response: BetaSelfHostedWorkListResponse? = null

        @JvmSynthetic
        internal fun from(workListPage: WorkListPage) = apply {
            service = workListPage.service
            params = workListPage.params
            response = workListPage.response
        }

        fun service(service: WorkService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: WorkListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: BetaSelfHostedWorkListResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [WorkListPage].
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
        fun build(): WorkListPage =
            WorkListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WorkListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "WorkListPage{service=$service, params=$params, response=$response}"
}
