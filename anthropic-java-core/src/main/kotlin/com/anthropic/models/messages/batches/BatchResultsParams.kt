// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Streams the results of a Message Batch as a `.jsonl` file.
 *
 * Each line in the file is a JSON object containing the result of a single request in the Message
 * Batch. Results are not guaranteed to be in the same order as requests. Use the `custom_id` field
 * to match results to requests.
 *
 * Learn more about the Message Batches API in our
 * [user guide](/en/docs/build-with-claude/batch-processing)
 */
class BatchResultsParams
private constructor(
    private val messageBatchId: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** ID of the Message Batch. */
    fun messageBatchId(): Optional<String> = Optional.ofNullable(messageBatchId)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): BatchResultsParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [BatchResultsParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BatchResultsParams]. */
    class Builder internal constructor() {

        private var messageBatchId: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(batchResultsParams: BatchResultsParams) = apply {
            messageBatchId = batchResultsParams.messageBatchId
            additionalHeaders = batchResultsParams.additionalHeaders.toBuilder()
            additionalQueryParams = batchResultsParams.additionalQueryParams.toBuilder()
        }

        /** ID of the Message Batch. */
        fun messageBatchId(messageBatchId: String?) = apply { this.messageBatchId = messageBatchId }

        /** Alias for calling [Builder.messageBatchId] with `messageBatchId.orElse(null)`. */
        fun messageBatchId(messageBatchId: Optional<String>) =
            messageBatchId(messageBatchId.getOrNull())

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAlladditional_headers(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAlladditional_headers(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putadditional_headers(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAlladditional_headers(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAlladditional_headers(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceadditional_headers(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceadditional_headers(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAlladditional_headers(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAlladditional_headers(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeadditional_headers(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAlladditional_headers(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAlladditional_query_params(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAlladditional_query_params(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putadditional_query_params(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAlladditional_query_params(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAlladditional_query_params(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceadditional_query_params(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceadditional_query_params(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAlladditional_query_params(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAlladditional_query_params(
            additionalQueryParams: Map<String, Iterable<String>>
        ) = apply { this.additionalQueryParams.replaceAll(additionalQueryParams) }

        fun removeadditional_query_params(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAlladditional_query_params(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [BatchResultsParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BatchResultsParams =
            BatchResultsParams(
                messageBatchId,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> messageBatchId ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BatchResultsParams &&
            messageBatchId == other.messageBatchId &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(messageBatchId, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "BatchResultsParams{messageBatchId=$messageBatchId, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
