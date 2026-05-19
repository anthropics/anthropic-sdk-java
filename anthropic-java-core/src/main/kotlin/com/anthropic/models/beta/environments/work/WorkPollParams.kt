// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Note: these endpoints are called automatically by the pre-built environment worker provided in
 * the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They are
 * included here as a reference; you do not need to invoke them directly.
 *
 * Long poll for work items in the queue.
 */
class WorkPollParams
private constructor(
    private val environmentId: String?,
    private val blockMs: Long?,
    private val reclaimOlderThanMs: Long?,
    private val betas: List<AnthropicBeta>?,
    private val anthropicWorkerId: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun environmentId(): Optional<String> = Optional.ofNullable(environmentId)

    /**
     * How long to wait for work to arrive before returning. Must be 1-999 in milliseconds. Defaults
     * to non-blocking (returns immediately if no work is available).
     */
    fun blockMs(): Optional<Long> = Optional.ofNullable(blockMs)

    /**
     * Reclaim unacknowledged work items older than this many milliseconds. If omitted, uses the
     * default (5000ms).
     */
    fun reclaimOlderThanMs(): Optional<Long> = Optional.ofNullable(reclaimOlderThanMs)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Unique identifier for the specific worker polling, used to track aggregated environment-level
     * work metrics in Console
     */
    fun anthropicWorkerId(): Optional<String> = Optional.ofNullable(anthropicWorkerId)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): WorkPollParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [WorkPollParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WorkPollParams]. */
    class Builder internal constructor() {

        private var environmentId: String? = null
        private var blockMs: Long? = null
        private var reclaimOlderThanMs: Long? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var anthropicWorkerId: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(workPollParams: WorkPollParams) = apply {
            environmentId = workPollParams.environmentId
            blockMs = workPollParams.blockMs
            reclaimOlderThanMs = workPollParams.reclaimOlderThanMs
            betas = workPollParams.betas?.toMutableList()
            anthropicWorkerId = workPollParams.anthropicWorkerId
            additionalHeaders = workPollParams.additionalHeaders.toBuilder()
            additionalQueryParams = workPollParams.additionalQueryParams.toBuilder()
        }

        fun environmentId(environmentId: String?) = apply { this.environmentId = environmentId }

        /** Alias for calling [Builder.environmentId] with `environmentId.orElse(null)`. */
        fun environmentId(environmentId: Optional<String>) =
            environmentId(environmentId.getOrNull())

        /**
         * How long to wait for work to arrive before returning. Must be 1-999 in milliseconds.
         * Defaults to non-blocking (returns immediately if no work is available).
         */
        fun blockMs(blockMs: Long?) = apply { this.blockMs = blockMs }

        /**
         * Alias for [Builder.blockMs].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun blockMs(blockMs: Long) = blockMs(blockMs as Long?)

        /** Alias for calling [Builder.blockMs] with `blockMs.orElse(null)`. */
        fun blockMs(blockMs: Optional<Long>) = blockMs(blockMs.getOrNull())

        /**
         * Reclaim unacknowledged work items older than this many milliseconds. If omitted, uses the
         * default (5000ms).
         */
        fun reclaimOlderThanMs(reclaimOlderThanMs: Long?) = apply {
            this.reclaimOlderThanMs = reclaimOlderThanMs
        }

        /**
         * Alias for [Builder.reclaimOlderThanMs].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun reclaimOlderThanMs(reclaimOlderThanMs: Long) =
            reclaimOlderThanMs(reclaimOlderThanMs as Long?)

        /**
         * Alias for calling [Builder.reclaimOlderThanMs] with `reclaimOlderThanMs.orElse(null)`.
         */
        fun reclaimOlderThanMs(reclaimOlderThanMs: Optional<Long>) =
            reclaimOlderThanMs(reclaimOlderThanMs.getOrNull())

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /**
         * Unique identifier for the specific worker polling, used to track aggregated
         * environment-level work metrics in Console
         */
        fun anthropicWorkerId(anthropicWorkerId: String?) = apply {
            this.anthropicWorkerId = anthropicWorkerId
        }

        /** Alias for calling [Builder.anthropicWorkerId] with `anthropicWorkerId.orElse(null)`. */
        fun anthropicWorkerId(anthropicWorkerId: Optional<String>) =
            anthropicWorkerId(anthropicWorkerId.getOrNull())

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [WorkPollParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): WorkPollParams =
            WorkPollParams(
                environmentId,
                blockMs,
                reclaimOlderThanMs,
                betas?.toImmutable(),
                anthropicWorkerId,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> environmentId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                anthropicWorkerId?.let { put("Anthropic-Worker-ID", it) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                blockMs?.let { put("block_ms", it.toString()) }
                reclaimOlderThanMs?.let { put("reclaim_older_than_ms", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WorkPollParams &&
            environmentId == other.environmentId &&
            blockMs == other.blockMs &&
            reclaimOlderThanMs == other.reclaimOlderThanMs &&
            betas == other.betas &&
            anthropicWorkerId == other.anthropicWorkerId &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            environmentId,
            blockMs,
            reclaimOlderThanMs,
            betas,
            anthropicWorkerId,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "WorkPollParams{environmentId=$environmentId, blockMs=$blockMs, reclaimOlderThanMs=$reclaimOlderThanMs, betas=$betas, anthropicWorkerId=$anthropicWorkerId, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
