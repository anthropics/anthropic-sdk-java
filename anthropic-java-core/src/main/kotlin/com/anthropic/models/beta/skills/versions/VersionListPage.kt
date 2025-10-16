// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.skills.VersionService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see VersionService.list */
class VersionListPage
private constructor(
    private val service: VersionService,
    private val params: VersionListParams,
    private val response: VersionListPageResponse,
) : Page<VersionListResponse> {

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

    override fun nextPage(): VersionListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<VersionListResponse> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): VersionListParams = params

    /** The response that this page was parsed from. */
    fun response(): VersionListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [VersionListPage].
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

    /** A builder for [VersionListPage]. */
    class Builder internal constructor() {

        private var service: VersionService? = null
        private var params: VersionListParams? = null
        private var response: VersionListPageResponse? = null

        @JvmSynthetic
        internal fun from(versionListPage: VersionListPage) = apply {
            service = versionListPage.service
            params = versionListPage.params
            response = versionListPage.response
        }

        fun service(service: VersionService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: VersionListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: VersionListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [VersionListPage].
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
        fun build(): VersionListPage =
            VersionListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is VersionListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "VersionListPage{service=$service, params=$params, response=$response}"
}
