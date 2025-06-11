// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.ModelService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see [ModelService.list] */
class ModelListPage
private constructor(
    private val service: ModelService,
    private val params: ModelListParams,
    private val response: ModelListPageResponse,
) : Page<BetaModelInfo> {

    /**
     * Delegates to [ModelListPageResponse], but gracefully handles missing data.
     *
     * @see [ModelListPageResponse.data]
     */
    fun data(): List<BetaModelInfo> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [ModelListPageResponse], but gracefully handles missing data.
     *
     * @see [ModelListPageResponse.hasMore]
     */
    fun hasMore(): Optional<Boolean> = response._hasMore().getOptional("has_more")

    /**
     * Delegates to [ModelListPageResponse], but gracefully handles missing data.
     *
     * @see [ModelListPageResponse.firstId]
     */
    fun firstId(): Optional<String> = response._firstId().getOptional("first_id")

    /**
     * Delegates to [ModelListPageResponse], but gracefully handles missing data.
     *
     * @see [ModelListPageResponse.lastId]
     */
    fun lastId(): Optional<String> = response._lastId().getOptional("last_id")

    override fun items(): List<BetaModelInfo> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && lastId().isPresent

    fun nextPageParams(): ModelListParams {
        val nextCursor =
            lastId().getOrNull() ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().afterId(nextCursor).build()
    }

    override fun nextPage(): ModelListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaModelInfo> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): ModelListParams = params

    /** The response that this page was parsed from. */
    fun response(): ModelListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ModelListPage].
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

    /** A builder for [ModelListPage]. */
    class Builder internal constructor() {

        private var service: ModelService? = null
        private var params: ModelListParams? = null
        private var response: ModelListPageResponse? = null

        @JvmSynthetic
        internal fun from(modelListPage: ModelListPage) = apply {
            service = modelListPage.service
            params = modelListPage.params
            response = modelListPage.response
        }

        fun service(service: ModelService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: ModelListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: ModelListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [ModelListPage].
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
        fun build(): ModelListPage =
            ModelListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ModelListPage && service == other.service && params == other.params && response == other.response /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(service, params, response) /* spotless:on */

    override fun toString() = "ModelListPage{service=$service, params=$params, response=$response}"
}
