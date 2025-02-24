// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.services.blocking.beta.messages.BatchService
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects
import java.util.Optional
import java.util.stream.Stream
import java.util.stream.StreamSupport

/**
 * List all Message Batches within a Workspace. Most recently created batches are returned first.
 *
 * Learn more about the Message Batches API in our
 * [user guide](/en/docs/build-with-claude/batch-processing)
 */
class BetaMessageBatchListPage
private constructor(
    private val batchesService: BatchService,
    private val params: BetaMessageBatchListParams,
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

        return /* spotless:off */ other is BetaMessageBatchListPage && batchesService == other.batchesService && params == other.params && response == other.response /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(batchesService, params, response) /* spotless:on */

    override fun toString() =
        "BetaMessageBatchListPage{batchesService=$batchesService, params=$params, response=$response}"

    fun hasNextPage(): Boolean {
        if (data().isEmpty()) {
            return false
        }

        return lastId().isPresent
    }

    fun getNextPageParams(): Optional<BetaMessageBatchListParams> {
        if (!hasNextPage()) {
            return Optional.empty()
        }

        return Optional.of(
            BetaMessageBatchListParams.builder()
                .from(params)
                .apply { lastId().ifPresent { this.afterId(it) } }
                .build()
        )
    }

    fun getNextPage(): Optional<BetaMessageBatchListPage> {
        return getNextPageParams().map { batchesService.list(it) }
    }

    fun autoPager(): AutoPager = AutoPager(this)

    companion object {

        @JvmStatic
        fun of(
            batchesService: BatchService,
            params: BetaMessageBatchListParams,
            response: Response,
        ) = BetaMessageBatchListPage(batchesService, params, response)
    }

    @NoAutoDetect
    class Response
    @JsonCreator
    constructor(
        @JsonProperty("data")
        private val data: JsonField<List<BetaMessageBatch>> = JsonMissing.of(),
        @JsonProperty("has_more") private val hasMore: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("first_id") private val firstId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("last_id") private val lastId: JsonField<String> = JsonMissing.of(),
        @JsonAnySetter
        private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
    ) {

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

            fun build() =
                Response(data, hasMore, firstId, lastId, additionalProperties.toImmutable())
        }
    }

    class AutoPager(private val firstPage: BetaMessageBatchListPage) : Iterable<BetaMessageBatch> {

        override fun iterator(): Iterator<BetaMessageBatch> = iterator {
            var page = firstPage
            var index = 0
            while (true) {
                while (index < page.data().size) {
                    yield(page.data()[index++])
                }
                page = page.getNextPage().orElse(null) ?: break
                index = 0
            }
        }

        fun stream(): Stream<BetaMessageBatch> {
            return StreamSupport.stream(spliterator(), false)
        }
    }
}
