// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.services.blocking.beta.ModelService
import java.util.Objects
import java.util.Optional
import java.util.stream.Stream
import java.util.stream.StreamSupport
import kotlin.jvm.optionals.getOrNull

/**
 * List available models.
 *
 * The Models API response can be used to determine which models are available for use in the API.
 * More recently released models are listed first.
 */
class ModelListPage
private constructor(
    private val modelsService: ModelService,
    private val params: ModelListParams,
    private val response: ModelListPageResponse,
) {

    /** Returns the response that this page was parsed from. */
    fun response(): ModelListPageResponse = response

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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ModelListPage && modelsService == other.modelsService && params == other.params && response == other.response /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(modelsService, params, response) /* spotless:on */

    override fun toString() =
        "ModelListPage{modelsService=$modelsService, params=$params, response=$response}"

    fun hasNextPage(): Boolean = data().isNotEmpty() && lastId().isPresent

    fun getNextPageParams(): Optional<ModelListParams> {
        if (!hasNextPage()) {
            return Optional.empty()
        }

        return Optional.of(params.toBuilder().apply { lastId().ifPresent { afterId(it) } }.build())
    }

    fun getNextPage(): Optional<ModelListPage> {
        return getNextPageParams().map { modelsService.list(it) }
    }

    fun autoPager(): AutoPager = AutoPager(this)

    companion object {

        @JvmStatic
        fun of(
            modelsService: ModelService,
            params: ModelListParams,
            response: ModelListPageResponse,
        ) = ModelListPage(modelsService, params, response)
    }

    class AutoPager(private val firstPage: ModelListPage) : Iterable<BetaModelInfo> {

        override fun iterator(): Iterator<BetaModelInfo> = iterator {
            var page = firstPage
            var index = 0
            while (true) {
                while (index < page.data().size) {
                    yield(page.data()[index++])
                }
                page = page.getNextPage().getOrNull() ?: break
                index = 0
            }
        }

        fun stream(): Stream<BetaModelInfo> {
            return StreamSupport.stream(spliterator(), false)
        }
    }
}
