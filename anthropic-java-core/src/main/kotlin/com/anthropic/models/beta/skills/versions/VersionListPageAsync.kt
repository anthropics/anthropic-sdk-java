// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.skills.VersionServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see VersionServiceAsync.list */
class VersionListPageAsync
private constructor(
    private val service: VersionServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: VersionListParams,
    private val response: VersionListPageResponse,
) : PageAsync<VersionListResponse> {

    /**
     * Delegates to [VersionListPageResponse], but gracefully handles missing data.
     *
     * @see VersionListPageResponse.data
     */
    fun data(): List<VersionListResponse> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [VersionListPageResponse], but gracefully handles missing data.
     *
     * @see VersionListPageResponse.hasMore
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [VersionListPageResponse], but gracefully handles missing data.
     *
     * @see VersionListPageResponse.nextPage
     */
    fun nextPage(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<VersionListResponse> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPage().isPresent

    fun nextPageParams(): VersionListParams {
        val nextCursor =
            nextPage().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<VersionListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<VersionListResponse> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): VersionListParams = params

    /** The response that this page was parsed from. */
    fun response(): VersionListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [VersionListPageAsync].
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

    /** A builder for [VersionListPageAsync]. */
    class Builder internal constructor() {

        private var service: VersionServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: VersionListParams? = null
        private var response: VersionListPageResponse? = null

        @JvmSynthetic
        internal fun from(versionListPageAsync: VersionListPageAsync) = apply {
            service = versionListPageAsync.service
            streamHandlerExecutor = versionListPageAsync.streamHandlerExecutor
            params = versionListPageAsync.params
            response = versionListPageAsync.response
        }

        fun service(service: VersionServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: VersionListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: VersionListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [VersionListPageAsync].
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
        fun build(): VersionListPageAsync =
            VersionListPageAsync(
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

        return other is VersionListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "VersionListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
