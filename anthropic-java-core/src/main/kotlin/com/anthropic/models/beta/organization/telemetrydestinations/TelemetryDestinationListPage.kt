package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.TelemetryDestinationService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see TelemetryDestinationService.list */
class TelemetryDestinationListPage
private constructor(
    private val service: TelemetryDestinationService,
    private val params: TelemetryDestinationListParams,
    private val response: TelemetryDestinationListPageResponse,
) : Page<BetaTelemetryDestination> {

    /**
     * Delegates to [TelemetryDestinationListPageResponse], but gracefully handles missing data.
     *
     * @see TelemetryDestinationListPageResponse.data
     */
    fun data(): List<BetaTelemetryDestination> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [TelemetryDestinationListPageResponse], but gracefully handles missing data.
     *
     * @see TelemetryDestinationListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaTelemetryDestination> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): TelemetryDestinationListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): TelemetryDestinationListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaTelemetryDestination> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): TelemetryDestinationListParams = params

    /** The response that this page was parsed from. */
    fun response(): TelemetryDestinationListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [TelemetryDestinationListPage].
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

    /** A builder for [TelemetryDestinationListPage]. */
    class Builder internal constructor() {

        private var service: TelemetryDestinationService? = null
        private var params: TelemetryDestinationListParams? = null
        private var response: TelemetryDestinationListPageResponse? = null

        @JvmSynthetic
        internal fun from(telemetryDestinationListPage: TelemetryDestinationListPage) = apply {
            service = telemetryDestinationListPage.service
            params = telemetryDestinationListPage.params
            response = telemetryDestinationListPage.response
        }

        fun service(service: TelemetryDestinationService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: TelemetryDestinationListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: TelemetryDestinationListPageResponse) = apply {
            this.response = response
        }

        /**
         * Returns an immutable instance of [TelemetryDestinationListPage].
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
        fun build(): TelemetryDestinationListPage =
            TelemetryDestinationListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TelemetryDestinationListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "TelemetryDestinationListPage{service=$service, params=$params, response=$response}"
}
