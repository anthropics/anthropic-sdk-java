// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.services.async.ModelServiceAsync
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.function.Predicate

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
    private val response: Response,
) {

    fun response(): Response = response

    fun data(): List<ModelInfo> = response().data()

    fun hasMore(): Optional<Boolean> = response().hasMore()

    fun firstId(): Optional<String> = response().firstId()

    fun lastId(): Optional<String> = response().lastId()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ModelListPageAsync && modelsService == other.modelsService && params == other.params && response == other.response /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(modelsService, params, response) /* spotless:on */

    override fun toString() =
        "ModelListPageAsync{modelsService=$modelsService, params=$params, response=$response}"

    fun hasNextPage(): Boolean {
        if (data().isEmpty()) {
            return false
        }

        return lastId().isPresent
    }

    fun getNextPageParams(): Optional<ModelListParams> {
        if (!hasNextPage()) {
            return Optional.empty()
        }

        return Optional.of(
            ModelListParams.builder()
                .from(params)
                .apply { lastId().ifPresent { this.afterId(it) } }
                .build()
        )
    }

    fun getNextPage(): CompletableFuture<Optional<ModelListPageAsync>> {
        return getNextPageParams()
            .map { modelsService.list(it).thenApply { Optional.of(it) } }
            .orElseGet { CompletableFuture.completedFuture(Optional.empty()) }
    }

    fun autoPager(): AutoPager = AutoPager(this)

    companion object {

        @JvmStatic
        fun of(modelsService: ModelServiceAsync, params: ModelListParams, response: Response) =
            ModelListPageAsync(modelsService, params, response)
    }

    @NoAutoDetect
    class Response
    @JsonCreator
    constructor(
        @JsonProperty("data") private val data: JsonField<List<ModelInfo>> = JsonMissing.of(),
        @JsonProperty("has_more") private val hasMore: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("first_id") private val firstId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("last_id") private val lastId: JsonField<String> = JsonMissing.of(),
        @JsonAnySetter
        private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
    ) {

        fun data(): List<ModelInfo> = data.getNullable("data") ?: listOf()

        fun hasMore(): Optional<Boolean> = Optional.ofNullable(hasMore.getNullable("has_more"))

        fun firstId(): Optional<String> = Optional.ofNullable(firstId.getNullable("first_id"))

        fun lastId(): Optional<String> = Optional.ofNullable(lastId.getNullable("last_id"))

        @JsonProperty("data")
        fun _data(): Optional<JsonField<List<ModelInfo>>> = Optional.ofNullable(data)

        @JsonProperty("has_more")
        fun _hasMore(): Optional<JsonField<Boolean>> = Optional.ofNullable(hasMore)

        @JsonProperty("first_id")
        fun _firstId(): Optional<JsonField<String>> = Optional.ofNullable(firstId)

        @JsonProperty("last_id")
        fun _lastId(): Optional<JsonField<String>> = Optional.ofNullable(lastId)

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        private var validated: Boolean = false

        fun validate(): Response = apply {
            if (validated) {
                return@apply
            }

            data().map { it.validate() }
            hasMore()
            firstId()
            lastId()
            validated = true
        }

        fun toBuilder() = Builder().from(this)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Response && data == other.data && hasMore == other.hasMore && firstId == other.firstId && lastId == other.lastId && additionalProperties == other.additionalProperties /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(data, hasMore, firstId, lastId, additionalProperties) /* spotless:on */

        override fun toString() =
            "Response{data=$data, hasMore=$hasMore, firstId=$firstId, lastId=$lastId, additionalProperties=$additionalProperties}"

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder {

            private var data: JsonField<List<ModelInfo>> = JsonMissing.of()
            private var hasMore: JsonField<Boolean> = JsonMissing.of()
            private var firstId: JsonField<String> = JsonMissing.of()
            private var lastId: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(page: Response) = apply {
                this.data = page.data
                this.hasMore = page.hasMore
                this.firstId = page.firstId
                this.lastId = page.lastId
                this.additionalProperties.putAll(page.additionalProperties)
            }

            fun data(data: List<ModelInfo>) = data(JsonField.of(data))

            fun data(data: JsonField<List<ModelInfo>>) = apply { this.data = data }

            fun hasMore(hasMore: Boolean) = hasMore(JsonField.of(hasMore))

            fun hasMore(hasMore: JsonField<Boolean>) = apply { this.hasMore = hasMore }

            fun firstId(firstId: String) = firstId(JsonField.of(firstId))

            fun firstId(firstId: JsonField<String>) = apply { this.firstId = firstId }

            fun lastId(lastId: String) = lastId(JsonField.of(lastId))

            fun lastId(lastId: JsonField<String>) = apply { this.lastId = lastId }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                this.additionalProperties.put(key, value)
            }

            fun build() =
                Response(data, hasMore, firstId, lastId, additionalProperties.toImmutable())
        }
    }

    class AutoPager(private val firstPage: ModelListPageAsync) {

        fun forEach(action: Predicate<ModelInfo>, executor: Executor): CompletableFuture<Void> {
            fun CompletableFuture<Optional<ModelListPageAsync>>.forEach(
                action: (ModelInfo) -> Boolean,
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

        fun toList(executor: Executor): CompletableFuture<List<ModelInfo>> {
            val values = mutableListOf<ModelInfo>()
            return forEach(values::add, executor).thenApply { values }
        }
    }
}
