// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.toImmutable
import com.anthropic.services.blocking.messages.BatchService
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.Objects
import java.util.Optional
import java.util.stream.Stream
import java.util.stream.StreamSupport

class MessageBatchListPage
private constructor(
    private val batchesService: BatchService,
    private val params: MessageBatchListParams,
    private val response: Response,
) {

    fun response(): Response = response

    fun data(): List<MessageBatch> = response().data()

    fun hasMore(): Optional<Boolean> = response().hasMore()

    fun firstId(): Optional<String> = response().firstId()

    fun lastId(): Optional<String> = response().lastId()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is MessageBatchListPage && batchesService == other.batchesService && params == other.params && response == other.response /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(batchesService, params, response) /* spotless:on */

    override fun toString() =
        "MessageBatchListPage{batchesService=$batchesService, params=$params, response=$response}"

    fun hasNextPage(): Boolean {
        if (data().isEmpty()) {
            return false
        }

        return lastId().isPresent
    }

    fun getNextPageParams(): Optional<MessageBatchListParams> {
        if (!hasNextPage()) {
            return Optional.empty()
        }

        return Optional.of(
            MessageBatchListParams.builder()
                .from(params)
                .apply { lastId().ifPresent { this.afterId(it) } }
                .build()
        )
    }

    fun getNextPage(): Optional<MessageBatchListPage> {
        return getNextPageParams().map { batchesService.list(it) }
    }

    fun autoPager(): AutoPager = AutoPager(this)

    companion object {

        @JvmStatic
        fun of(batchesService: BatchService, params: MessageBatchListParams, response: Response) =
            MessageBatchListPage(
                batchesService,
                params,
                response,
            )
    }

    @JsonDeserialize(builder = Response.Builder::class)
    @NoAutoDetect
    class Response
    constructor(
        private val data: JsonField<List<MessageBatch>>,
        private val hasMore: JsonField<Boolean>,
        private val firstId: JsonField<String>,
        private val lastId: JsonField<String>,
        private val additionalProperties: Map<String, JsonValue>,
    ) {

        private var validated: Boolean = false

        fun data(): List<MessageBatch> = data.getNullable("data") ?: listOf()

        fun hasMore(): Optional<Boolean> = Optional.ofNullable(hasMore.getNullable("has_more"))

        fun firstId(): Optional<String> = Optional.ofNullable(firstId.getNullable("first_id"))

        fun lastId(): Optional<String> = Optional.ofNullable(lastId.getNullable("last_id"))

        @JsonProperty("data")
        fun _data(): Optional<JsonField<List<MessageBatch>>> = Optional.ofNullable(data)

        @JsonProperty("has_more")
        fun _hasMore(): Optional<JsonField<Boolean>> = Optional.ofNullable(hasMore)

        @JsonProperty("first_id")
        fun _firstId(): Optional<JsonField<String>> = Optional.ofNullable(firstId)

        @JsonProperty("last_id")
        fun _lastId(): Optional<JsonField<String>> = Optional.ofNullable(lastId)

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun validate(): Response = apply {
            if (!validated) {
                data().map { it.validate() }
                hasMore()
                firstId()
                lastId()
                validated = true
            }
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

            private var data: JsonField<List<MessageBatch>> = JsonMissing.of()
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

            fun data(data: List<MessageBatch>) = data(JsonField.of(data))

            @JsonProperty("data")
            fun data(data: JsonField<List<MessageBatch>>) = apply { this.data = data }

            fun hasMore(hasMore: Boolean) = hasMore(JsonField.of(hasMore))

            @JsonProperty("has_more")
            fun hasMore(hasMore: JsonField<Boolean>) = apply { this.hasMore = hasMore }

            fun firstId(firstId: String) = firstId(JsonField.of(firstId))

            @JsonProperty("first_id")
            fun firstId(firstId: JsonField<String>) = apply { this.firstId = firstId }

            fun lastId(lastId: String) = lastId(JsonField.of(lastId))

            @JsonProperty("last_id")
            fun lastId(lastId: JsonField<String>) = apply { this.lastId = lastId }

            @JsonAnySetter
            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                this.additionalProperties.put(key, value)
            }

            fun build() =
                Response(
                    data,
                    hasMore,
                    firstId,
                    lastId,
                    additionalProperties.toImmutable(),
                )
        }
    }

    class AutoPager
    constructor(
        private val firstPage: MessageBatchListPage,
    ) : Iterable<MessageBatch> {

        override fun iterator(): Iterator<MessageBatch> = iterator {
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

        fun stream(): Stream<MessageBatch> {
            return StreamSupport.stream(spliterator(), false)
        }
    }
}
