// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.organization.federation.IssuerServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see IssuerServiceAsync.list */
class IssuerListPageAsync
private constructor(
    private val service: IssuerServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: IssuerListParams,
    private val response: IssuerListPageResponse,
) : PageAsync<BetaFederationIssuer> {

    /**
     * Delegates to [IssuerListPageResponse], but gracefully handles missing data.
     *
     * @see IssuerListPageResponse.data
     */
    fun data(): List<BetaFederationIssuer> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [IssuerListPageResponse], but gracefully handles missing data.
     *
     * @see IssuerListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaFederationIssuer> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): IssuerListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<IssuerListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaFederationIssuer> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): IssuerListParams = params

    /** The response that this page was parsed from. */
    fun response(): IssuerListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [IssuerListPageAsync].
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

    /** A builder for [IssuerListPageAsync]. */
    class Builder internal constructor() {

        private var service: IssuerServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: IssuerListParams? = null
        private var response: IssuerListPageResponse? = null

        @JvmSynthetic
        internal fun from(issuerListPageAsync: IssuerListPageAsync) = apply {
            service = issuerListPageAsync.service
            streamHandlerExecutor = issuerListPageAsync.streamHandlerExecutor
            params = issuerListPageAsync.params
            response = issuerListPageAsync.response
        }

        fun service(service: IssuerServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: IssuerListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: IssuerListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [IssuerListPageAsync].
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
        fun build(): IssuerListPageAsync =
            IssuerListPageAsync(
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

        return other is IssuerListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "IssuerListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
