// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.services.async.beta.messages.BatchServiceAsync
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.function.Predicate

/**
 * List all Message Batches within a Workspace. Most recently created batches are returned first.
 *
 * Learn more about the Message Batches API in our
 * [user guide](/en/docs/build-with-claude/batch-processing)
 */
class BatchListPageAsync
private constructor(
    private val batchesService: BatchServiceAsync,
    private val params: BatchListParams,
    private val response: Response,
) {

    fun response(): Response = response

    fun data(): List<BetaMessageBatch> = response().data()

    fun hasMore(): Optional<Boolean> = response().hasMore()

    fun firstId(): Optional<String> = response().firstId()

    fun lastId(): Optional<String> = response().lastId()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BatchListPageAsync && batchesService == other.batchesService && params == other.params && response == other.response /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(batchesService, params, response) /* spotless:on */

    override fun toString() =
        "BatchListPageAsync{batchesService=$batchesService, params=$params, response=$response}"

    fun hasNextPage(): Boolean {
        if (data().isEmpty()) {
            return false
        }

        return lastId().isPresent
    }

    fun getNextPageParams(): Optional<BatchListParams> {
        if (!hasNextPage()) {
            return Optional.empty()
        }

        return Optional.of(
            BatchListParams.builder()
                .from(params)
                .apply { lastId().ifPresent { this.afterId(it) } }
                .build()
        )
    }

    fun getNextPage(): CompletableFuture<Optional<BatchListPageAsync>> {
        return getNextPageParams()
            .map { batchesService.list(it).thenApply { Optional.of(it) } }
            .orElseGet { CompletableFuture.completedFuture(Optional.empty()) }
    }

    fun autoPager(): AutoPager = AutoPager(this)

    companion object {

        @JvmStatic
        fun of(batchesService: BatchServiceAsync, params: BatchListParams, response: Response) =
            BatchListPageAsync(batchesService, params, response)
    }

    class Response(
        private val data: JsonField<List<BetaMessageBatch>>,
        private val hasMore: JsonField<Boolean>,
        private val firstId: JsonField<String>,
        private val lastId: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("data") data: JsonField<List<BetaMessageBatch>> = JsonMissing.of(),
            @JsonProperty("has_more") hasMore: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("first_id") firstId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("last_id") lastId: JsonField<String> = JsonMissing.of(),
        ) : this(data, hasMore, firstId, lastId, mutableMapOf())

        fun data(): List<BetaMessageBatch> = data.getNullable("data") ?: listOf()

        fun hasMore(): Optional<Boolean> = Optional.ofNullable(hasMore.getNullable("has_more"))

        fun firstId(): Optional<String> = Optional.ofNullable(firstId.getNullable("first_id"))

        fun lastId(): Optional<String> = Optional.ofNullable(lastId.getNullable("last_id"))

        @JsonProperty("data")
        fun _data(): Optional<JsonField<List<BetaMessageBatch>>> = Optional.ofNullable(data)

        @JsonProperty("has_more")
        fun _hasMore(): Optional<JsonField<Boolean>> = Optional.ofNullable(hasMore)

        @JsonProperty("first_id")
        fun _firstId(): Optional<JsonField<String>> = Optional.ofNullable(firstId)

        @JsonProperty("last_id")
        fun _lastId(): Optional<JsonField<String>> = Optional.ofNullable(lastId)

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

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

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
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

            /** Returns a mutable builder for constructing an instance of [BatchListPageAsync]. */
            @JvmStatic fun builder() = Builder()
        }

        class Builder {

            private var data: JsonField<List<BetaMessageBatch>> = JsonMissing.of()
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

            fun data(data: List<BetaMessageBatch>) = data(JsonField.of(data))

            fun data(data: JsonField<List<BetaMessageBatch>>) = apply { this.data = data }

            fun hasMore(hasMore: Boolean) = hasMore(JsonField.of(hasMore))

            fun hasMore(hasMore: JsonField<Boolean>) = apply { this.hasMore = hasMore }

            fun firstId(firstId: String) = firstId(JsonField.of(firstId))

            fun firstId(firstId: JsonField<String>) = apply { this.firstId = firstId }

            fun lastId(lastId: String) = lastId(JsonField.of(lastId))

            fun lastId(lastId: JsonField<String>) = apply { this.lastId = lastId }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                this.additionalProperties.put(key, value)
            }

            /**
             * Returns an immutable instance of [Response].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Response =
                Response(data, hasMore, firstId, lastId, additionalProperties.toMutableMap())
        }
    }

    class AutoPager(private val firstPage: BatchListPageAsync) {

        fun forEach(
            action: Predicate<BetaMessageBatch>,
            executor: Executor,
        ): CompletableFuture<Void> {
            fun CompletableFuture<Optional<BatchListPageAsync>>.forEach(
                action: (BetaMessageBatch) -> Boolean,
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

        fun toList(executor: Executor): CompletableFuture<List<BetaMessageBatch>> {
            val values = mutableListOf<BetaMessageBatch>()
            return forEach(values::add, executor).thenApply { values }
        }
    }
}
