// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.VaultServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see VaultServiceAsync.list */
class VaultListPageAsync
private constructor(
    private val service: VaultServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: VaultListParams,
    private val response: VaultListPageResponse,
) : PageAsync<BetaManagedAgentsVault> {

    /**
     * Delegates to [VaultListPageResponse], but gracefully handles missing data.
     *
     * @see VaultListPageResponse.data
     */
    fun data(): List<BetaManagedAgentsVault> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [VaultListPageResponse], but gracefully handles missing data.
     *
     * @see VaultListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaManagedAgentsVault> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): VaultListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<VaultListPageAsync> = service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaManagedAgentsVault> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): VaultListParams = params

    /** The response that this page was parsed from. */
    fun response(): VaultListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [VaultListPageAsync].
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

    /** A builder for [VaultListPageAsync]. */
    class Builder internal constructor() {

        private var service: VaultServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: VaultListParams? = null
        private var response: VaultListPageResponse? = null

        @JvmSynthetic
        internal fun from(vaultListPageAsync: VaultListPageAsync) = apply {
            service = vaultListPageAsync.service
            streamHandlerExecutor = vaultListPageAsync.streamHandlerExecutor
            params = vaultListPageAsync.params
            response = vaultListPageAsync.response
        }

        fun service(service: VaultServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: VaultListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: VaultListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [VaultListPageAsync].
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
        fun build(): VaultListPageAsync =
            VaultListPageAsync(
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

        return other is VaultListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "VaultListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
