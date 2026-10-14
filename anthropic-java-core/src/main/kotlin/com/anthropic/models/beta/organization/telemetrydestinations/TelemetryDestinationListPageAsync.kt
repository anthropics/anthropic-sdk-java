package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.TelemetryDestinationServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see TelemetryDestinationServiceAsync.list */
class TelemetryDestinationListPageAsync
private constructor(
    private val service: TelemetryDestinationServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: TelemetryDestinationListParams,
    private val response: TelemetryDestinationListPageResponse,
) : PageAsync<BetaTelemetryDestination> {

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

    override fun nextPage(): CompletableFuture<TelemetryDestinationListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaTelemetryDestination> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): TelemetryDestinationListParams = params

    /** The response that this page was parsed from. */
    fun response(): TelemetryDestinationListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [TelemetryDestinationListPageAsync].
         *
         * The following fields are required:
         * ```java
         * .service()
         * .streamHandlerExecutor()
         * .params()
         * .response()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TelemetryDestinationListPageAsync]. */
    class Builder internal constructor() {

        private var service: TelemetryDestinationServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: TelemetryDestinationListParams? = null
        private var response: TelemetryDestinationListPageResponse? = null

        @JvmSynthetic
        internal fun from(telemetryDestinationListPageAsync: TelemetryDestinationListPageAsync) =
            apply {
                service = telemetryDestinationListPageAsync.service
                streamHandlerExecutor = telemetryDestinationListPageAsync.streamHandlerExecutor
                params = telemetryDestinationListPageAsync.params
                response = telemetryDestinationListPageAsync.response
            }

        fun service(service: TelemetryDestinationServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: TelemetryDestinationListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: TelemetryDestinationListPageResponse) = apply {
            this.response = response
        }

        /**
         * Returns an immutable instance of [TelemetryDestinationListPageAsync].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .service()
         * .streamHandlerExecutor()
         * .params()
         * .response()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): TelemetryDestinationListPageAsync =
            TelemetryDestinationListPageAsync(
                checkRequired("service", service),
                checkRequired("streamHandlerExecutor", streamHandlerExecutor),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TelemetryDestinationListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "TelemetryDestinationListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
