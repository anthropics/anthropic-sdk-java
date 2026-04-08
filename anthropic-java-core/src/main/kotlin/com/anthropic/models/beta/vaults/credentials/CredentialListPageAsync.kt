// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.vaults.CredentialServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see CredentialServiceAsync.list */
class CredentialListPageAsync
private constructor(
    private val service: CredentialServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: CredentialListParams,
    private val response: CredentialListPageResponse,
) : PageAsync<BetaManagedAgentsCredential> {

    /**
     * Delegates to [CredentialListPageResponse], but gracefully handles missing data.
     *
     * @see CredentialListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsCredential> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [CredentialListPageResponse], but gracefully handles missing data.
     *
     * @see CredentialListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsCredential> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): CredentialListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<CredentialListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaManagedAgentsCredential> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): CredentialListParams = params

    /** The response that this page was parsed from. */
    fun response(): CredentialListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CredentialListPageAsync].
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

    /** A builder for [CredentialListPageAsync]. */
    class Builder internal constructor() {

        private var service: CredentialServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: CredentialListParams? = null
        private var response: CredentialListPageResponse? = null

        @JvmSynthetic
        internal fun from(credentialListPageAsync: CredentialListPageAsync) = apply {
            service = credentialListPageAsync.service
            streamHandlerExecutor = credentialListPageAsync.streamHandlerExecutor
            params = credentialListPageAsync.params
            response = credentialListPageAsync.response
        }

        fun service(service: CredentialServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: CredentialListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: CredentialListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [CredentialListPageAsync].
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
        fun build(): CredentialListPageAsync =
            CredentialListPageAsync(
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

        return other is CredentialListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "CredentialListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
