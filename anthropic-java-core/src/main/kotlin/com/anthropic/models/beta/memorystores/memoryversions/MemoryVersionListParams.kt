// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsMemoryView
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** ListMemoryVersions */
class MemoryVersionListParams
private constructor(
    private val memoryStoreId: String?,
    private val apiKeyId: String?,
    private val createdAtGte: OffsetDateTime?,
    private val createdAtLte: OffsetDateTime?,
    private val limit: Int?,
    private val memoryId: String?,
    private val operation: BetaManagedAgentsMemoryVersionOperation?,
    private val page: String?,
    private val sessionId: String?,
    private val view: BetaManagedAgentsMemoryView?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun memoryStoreId(): Optional<String> = Optional.ofNullable(memoryStoreId)

    /** Query parameter for api_key_id */
    fun apiKeyId(): Optional<String> = Optional.ofNullable(apiKeyId)

    /** Return versions created at or after this time (inclusive). */
    fun createdAtGte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGte)

    /** Return versions created at or before this time (inclusive). */
    fun createdAtLte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLte)

    /** Query parameter for limit */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /** Query parameter for memory_id */
    fun memoryId(): Optional<String> = Optional.ofNullable(memoryId)

    /** Query parameter for operation */
    fun operation(): Optional<BetaManagedAgentsMemoryVersionOperation> =
        Optional.ofNullable(operation)

    /** Query parameter for page */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** Query parameter for session_id */
    fun sessionId(): Optional<String> = Optional.ofNullable(sessionId)

    /** Query parameter for view */
    fun view(): Optional<BetaManagedAgentsMemoryView> = Optional.ofNullable(view)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): MemoryVersionListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [MemoryVersionListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MemoryVersionListParams]. */
    class Builder internal constructor() {

        private var memoryStoreId: String? = null
        private var apiKeyId: String? = null
        private var createdAtGte: OffsetDateTime? = null
        private var createdAtLte: OffsetDateTime? = null
        private var limit: Int? = null
        private var memoryId: String? = null
        private var operation: BetaManagedAgentsMemoryVersionOperation? = null
        private var page: String? = null
        private var sessionId: String? = null
        private var view: BetaManagedAgentsMemoryView? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(memoryVersionListParams: MemoryVersionListParams) = apply {
            memoryStoreId = memoryVersionListParams.memoryStoreId
            apiKeyId = memoryVersionListParams.apiKeyId
            createdAtGte = memoryVersionListParams.createdAtGte
            createdAtLte = memoryVersionListParams.createdAtLte
            limit = memoryVersionListParams.limit
            memoryId = memoryVersionListParams.memoryId
            operation = memoryVersionListParams.operation
            page = memoryVersionListParams.page
            sessionId = memoryVersionListParams.sessionId
            view = memoryVersionListParams.view
            betas = memoryVersionListParams.betas?.toMutableList()
            additionalHeaders = memoryVersionListParams.additionalHeaders.toBuilder()
            additionalQueryParams = memoryVersionListParams.additionalQueryParams.toBuilder()
        }

        fun memoryStoreId(memoryStoreId: String?) = apply { this.memoryStoreId = memoryStoreId }

        /** Alias for calling [Builder.memoryStoreId] with `memoryStoreId.orElse(null)`. */
        fun memoryStoreId(memoryStoreId: Optional<String>) =
            memoryStoreId(memoryStoreId.getOrNull())

        /** Query parameter for api_key_id */
        fun apiKeyId(apiKeyId: String?) = apply { this.apiKeyId = apiKeyId }

        /** Alias for calling [Builder.apiKeyId] with `apiKeyId.orElse(null)`. */
        fun apiKeyId(apiKeyId: Optional<String>) = apiKeyId(apiKeyId.getOrNull())

        /** Return versions created at or after this time (inclusive). */
        fun createdAtGte(createdAtGte: OffsetDateTime?) = apply { this.createdAtGte = createdAtGte }

        /** Alias for calling [Builder.createdAtGte] with `createdAtGte.orElse(null)`. */
        fun createdAtGte(createdAtGte: Optional<OffsetDateTime>) =
            createdAtGte(createdAtGte.getOrNull())

        /** Return versions created at or before this time (inclusive). */
        fun createdAtLte(createdAtLte: OffsetDateTime?) = apply { this.createdAtLte = createdAtLte }

        /** Alias for calling [Builder.createdAtLte] with `createdAtLte.orElse(null)`. */
        fun createdAtLte(createdAtLte: Optional<OffsetDateTime>) =
            createdAtLte(createdAtLte.getOrNull())

        /** Query parameter for limit */
        fun limit(limit: Int?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Int) = limit(limit as Int?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Int>) = limit(limit.getOrNull())

        /** Query parameter for memory_id */
        fun memoryId(memoryId: String?) = apply { this.memoryId = memoryId }

        /** Alias for calling [Builder.memoryId] with `memoryId.orElse(null)`. */
        fun memoryId(memoryId: Optional<String>) = memoryId(memoryId.getOrNull())

        /** Query parameter for operation */
        fun operation(operation: BetaManagedAgentsMemoryVersionOperation?) = apply {
            this.operation = operation
        }

        /** Alias for calling [Builder.operation] with `operation.orElse(null)`. */
        fun operation(operation: Optional<BetaManagedAgentsMemoryVersionOperation>) =
            operation(operation.getOrNull())

        /** Query parameter for page */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /** Query parameter for session_id */
        fun sessionId(sessionId: String?) = apply { this.sessionId = sessionId }

        /** Alias for calling [Builder.sessionId] with `sessionId.orElse(null)`. */
        fun sessionId(sessionId: Optional<String>) = sessionId(sessionId.getOrNull())

        /** Query parameter for view */
        fun view(view: BetaManagedAgentsMemoryView?) = apply { this.view = view }

        /** Alias for calling [Builder.view] with `view.orElse(null)`. */
        fun view(view: Optional<BetaManagedAgentsMemoryView>) = view(view.getOrNull())

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
         * Returns an immutable instance of [MemoryVersionListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): MemoryVersionListParams =
            MemoryVersionListParams(
                memoryStoreId,
                apiKeyId,
                createdAtGte,
                createdAtLte,
                limit,
                memoryId,
                operation,
                page,
                sessionId,
                view,
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> memoryStoreId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                apiKeyId?.let { put("api_key_id", it) }
                createdAtGte?.let {
                    put("created_at[gte]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdAtLte?.let {
                    put("created_at[lte]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                limit?.let { put("limit", it.toString()) }
                memoryId?.let { put("memory_id", it) }
                operation?.let { put("operation", it.toString()) }
                page?.let { put("page", it) }
                sessionId?.let { put("session_id", it) }
                view?.let { put("view", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MemoryVersionListParams &&
            memoryStoreId == other.memoryStoreId &&
            apiKeyId == other.apiKeyId &&
            createdAtGte == other.createdAtGte &&
            createdAtLte == other.createdAtLte &&
            limit == other.limit &&
            memoryId == other.memoryId &&
            operation == other.operation &&
            page == other.page &&
            sessionId == other.sessionId &&
            view == other.view &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            memoryStoreId,
            apiKeyId,
            createdAtGte,
            createdAtLte,
            limit,
            memoryId,
            operation,
            page,
            sessionId,
            view,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "MemoryVersionListParams{memoryStoreId=$memoryStoreId, apiKeyId=$apiKeyId, createdAtGte=$createdAtGte, createdAtLte=$createdAtLte, limit=$limit, memoryId=$memoryId, operation=$operation, page=$page, sessionId=$sessionId, view=$view, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
