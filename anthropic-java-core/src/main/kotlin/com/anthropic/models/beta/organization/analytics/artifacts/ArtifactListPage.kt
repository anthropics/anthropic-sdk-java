package com.anthropic.models.beta.organization.analytics.artifacts

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsArtifactActivity
import com.anthropic.services.blocking.beta.organization.analytics.ArtifactService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see ArtifactService.list */
class ArtifactListPage
private constructor(
    private val service: ArtifactService,
    private val params: ArtifactListParams,
    private val response: ArtifactListPageResponse,
) : Page<BetaAnalyticsArtifactActivity> {

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

    override fun nextPage(): ArtifactListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaAnalyticsArtifactActivity> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): ArtifactListParams = params

    /** The response that this page was parsed from. */
    fun response(): ArtifactListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ArtifactListPage].
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

    /** A builder for [ArtifactListPage]. */
    class Builder internal constructor() {

        private var service: ArtifactService? = null
        private var params: ArtifactListParams? = null
        private var response: ArtifactListPageResponse? = null

        @JvmSynthetic
        internal fun from(artifactListPage: ArtifactListPage) = apply {
            service = artifactListPage.service
            params = artifactListPage.params
            response = artifactListPage.response
        }

        fun service(service: ArtifactService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: ArtifactListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ArtifactListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ArtifactListPage].
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
        fun build(): ArtifactListPage =
            ArtifactListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ArtifactListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "ArtifactListPage{service=$service, params=$params, response=$response}"
}
