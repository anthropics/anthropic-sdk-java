// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.ApiKeyService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see ApiKeyService.list */
class ApiKeyListPage
private constructor(
    private val service: ApiKeyService,
    private val params: ApiKeyListParams,
    private val response: ApiKeyListPageResponse,
) : Page<BetaApiKey> {

    /**
     * Delegates to [ApiKeyListPageResponse], but gracefully handles missing data.
     *
     * @see ApiKeyListPageResponse.data
     */
    fun data(): List<BetaApiKey> = response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [ApiKeyListPageResponse], but gracefully handles missing data.
     *
     * @see ApiKeyListPageResponse.hasMore
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [ApiKeyListPageResponse], but gracefully handles missing data.
     *
     * @see ApiKeyListPageResponse.firstId
     */
    fun firstId(): Optional<String> = response._firstId().getOptional("first_id")

    /**
     * Delegates to [ApiKeyListPageResponse], but gracefully handles missing data.
     *
     * @see ApiKeyListPageResponse.lastId
     */
    fun lastId(): Optional<String> = response._lastId().getOptional("last_id")

    override fun items(): List<BetaApiKey> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && lastId().isPresent

    fun nextPageParams(): ApiKeyListParams {
        val nextCursor =
            lastId().getOrNull() ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().afterId(nextCursor).build()
    }

    override fun nextPage(): ApiKeyListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaApiKey> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): ApiKeyListParams = params

    /** The response that this page was parsed from. */
    fun response(): ApiKeyListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ApiKeyListPage].
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

    /** A builder for [ApiKeyListPage]. */
    class Builder internal constructor() {

        private var service: ApiKeyService? = null
        private var params: ApiKeyListParams? = null
        private var response: ApiKeyListPageResponse? = null

        @JvmSynthetic
        internal fun from(apiKeyListPage: ApiKeyListPage) = apply {
            service = apiKeyListPage.service
            params = apiKeyListPage.params
            response = apiKeyListPage.response
        }

        fun service(service: ApiKeyService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: ApiKeyListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ApiKeyListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ApiKeyListPage].
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
        fun build(): ApiKeyListPage =
            ApiKeyListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ApiKeyListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() = "ApiKeyListPage{service=$service, params=$params, response=$response}"
}
