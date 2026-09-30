package com.anthropic.models.beta.organization.analytics.artifacts

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsArtifactActivity
import com.anthropic.services.async.beta.organization.analytics.ArtifactServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see ArtifactServiceAsync.list */
class ArtifactListPageAsync
private constructor(
    private val service: ArtifactServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: ArtifactListParams,
    private val response: ArtifactListPageResponse,
) : PageAsync<BetaAnalyticsArtifactActivity> {

    /**
     * Delegates to [ArtifactListPageResponse], but gracefully handles missing data.
     *
     * @see ArtifactListPageResponse.data
     */
    fun data(): List<BetaAnalyticsArtifactActivity> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [ArtifactListPageResponse], but gracefully handles missing data.
     *
     * @see ArtifactListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaAnalyticsArtifactActivity> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): ArtifactListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<ArtifactListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaAnalyticsArtifactActivity> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): ArtifactListParams = params

    /** The response that this page was parsed from. */
    fun response(): ArtifactListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ArtifactListPageAsync].
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

    /** A builder for [ArtifactListPageAsync]. */
    class Builder internal constructor() {

        private var service: ArtifactServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: ArtifactListParams? = null
        private var response: ArtifactListPageResponse? = null

        @JvmSynthetic
        internal fun from(artifactListPageAsync: ArtifactListPageAsync) = apply {
            service = artifactListPageAsync.service
            streamHandlerExecutor = artifactListPageAsync.streamHandlerExecutor
            params = artifactListPageAsync.params
            response = artifactListPageAsync.response
        }

        fun service(service: ArtifactServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: ArtifactListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ArtifactListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ArtifactListPageAsync].
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
        fun build(): ArtifactListPageAsync =
            ArtifactListPageAsync(
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

        return other is ArtifactListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "ArtifactListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
