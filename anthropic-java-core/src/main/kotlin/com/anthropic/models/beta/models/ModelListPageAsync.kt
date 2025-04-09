// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.services.async.beta.ModelServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.function.Predicate
import kotlin.jvm.optionals.getOrNull

/**
 * List available models.
 *
 * The Models API response can be used to determine which models are available for use in the API.
 * More recently released models are listed first.
 */
class ModelListPageAsync
private constructor(
    private val modelsService: ModelServiceAsync,
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

        return /* spotless:off */ other is ModelListPageAsync && modelsService == other.modelsService && params == other.params && response == other.response /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(modelsService, params, response) /* spotless:on */

    override fun toString() =
        "ModelListPageAsync{modelsService=$modelsService, params=$params, response=$response}"

    fun hasNextPage(): Boolean = data().isNotEmpty() && lastId().isPresent

    fun getNextPageParams(): Optional<ModelListParams> {
        if (!hasNextPage()) {
            return Optional.empty()
        }

        return Optional.of(params.toBuilder().apply { lastId().ifPresent { afterId(it) } }.build())
    }

    fun getNextPage(): CompletableFuture<Optional<ModelListPageAsync>> {
        return getNextPageParams()
            .map { modelsService.list(it).thenApply { Optional.of(it) } }
            .orElseGet { CompletableFuture.completedFuture(Optional.empty()) }
    }

    fun autoPager(): AutoPager = AutoPager(this)

    companion object {

        @JvmStatic
        fun of(
            modelsService: ModelServiceAsync,
            params: ModelListParams,
            response: ModelListPageResponse,
        ) = ModelListPageAsync(modelsService, params, response)
    }

    class AutoPager(private val firstPage: ModelListPageAsync) {

        fun forEach(action: Predicate<BetaModelInfo>, executor: Executor): CompletableFuture<Void> {
            fun CompletableFuture<Optional<ModelListPageAsync>>.forEach(
                action: (BetaModelInfo) -> Boolean,
                executor: Executor,
            ): CompletableFuture<Void> =
                thenComposeAsync(
                    { page ->
                        page
                            .filter { it.data().all(action) }
                            .map { it.getNextPage().forEach(action, executor) }
                            .orElseGet { CompletableFuture.completedFuture(null) }
                    },
                    executor,
                )
            return CompletableFuture.completedFuture(Optional.of(firstPage))
                .forEach(action::test, executor)
        }

        fun toList(executor: Executor): CompletableFuture<List<BetaModelInfo>> {
            val values = mutableListOf<BetaModelInfo>()
            return forEach(values::add, executor).thenApply { values }
        }
    }
}
