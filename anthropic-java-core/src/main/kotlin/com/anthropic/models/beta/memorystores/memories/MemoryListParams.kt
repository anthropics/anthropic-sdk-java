// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** List memories */
class MemoryListParams
private constructor(
    private val memoryStoreId: String?,
    private val depth: Int?,
    private val limit: Int?,
    private val page: String?,
    private val pathPrefix: String?,
    private val view: BetaManagedAgentsMemoryView?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun memoryStoreId(): Optional<String> = Optional.ofNullable(memoryStoreId)

    /**
     * `0` (or omitted) returns all descendants below `path_prefix` (recursive). `1` returns
     * immediate children only; deeper entries roll up as `memory_prefix` items. `depth=1` behaves
     * like `ls`; omitting `depth` behaves like `find`.
     */
    fun depth(): Optional<Int> = Optional.ofNullable(depth)

    /**
     * Maximum number of items to return per page. Must be between 1 and 100. Defaults to 20 when
     * omitted. Capped at 20 when `view=full`. Both `memory` and `memory_prefix` items count toward
     * the limit.
     */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /**
     * Opaque pagination cursor (a `page_...` value). Pass the `next_page` value from a previous
     * response to fetch the next page; omit for the first page.
     */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /**
     * Optional path prefix filter. Must end with `/` (segment-aligned), e.g., `/notes/`. This value
     * appears in request URLs. Do not include secrets or personally identifiable information.
     */
    fun pathPrefix(): Optional<String> = Optional.ofNullable(pathPrefix)

    /**
     * Which projection of each `memory` to return. Defaults to `basic` (content omitted). `full`
     * populates `content` on each item and caps `limit` at 20; use this as the bulk-read path for
     * export and sync.
     */
    fun view(): Optional<BetaManagedAgentsMemoryView> = Optional.ofNullable(view)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): MemoryListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [MemoryListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MemoryListParams]. */
    class Builder internal constructor() {

        private var memoryStoreId: String? = null
        private var depth: Int? = null
        private var limit: Int? = null
        private var page: String? = null
        private var pathPrefix: String? = null
        private var view: BetaManagedAgentsMemoryView? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(memoryListParams: MemoryListParams) = apply {
            memoryStoreId = memoryListParams.memoryStoreId
            depth = memoryListParams.depth
            limit = memoryListParams.limit
            page = memoryListParams.page
            pathPrefix = memoryListParams.pathPrefix
            view = memoryListParams.view
            betas = memoryListParams.betas?.toMutableList()
            additionalHeaders = memoryListParams.additionalHeaders.toBuilder()
            additionalQueryParams = memoryListParams.additionalQueryParams.toBuilder()
        }

        fun memoryStoreId(memoryStoreId: String?) = apply { this.memoryStoreId = memoryStoreId }

        /** Alias for calling [Builder.memoryStoreId] with `memoryStoreId.orElse(null)`. */
        fun memoryStoreId(memoryStoreId: Optional<String>) =
            memoryStoreId(memoryStoreId.getOrNull())

        /**
         * `0` (or omitted) returns all descendants below `path_prefix` (recursive). `1` returns
         * immediate children only; deeper entries roll up as `memory_prefix` items. `depth=1`
         * behaves like `ls`; omitting `depth` behaves like `find`.
         */
        fun depth(depth: Int?) = apply { this.depth = depth }

        /**
         * Alias for [Builder.depth].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun depth(depth: Int) = depth(depth as Int?)

        /** Alias for calling [Builder.depth] with `depth.orElse(null)`. */
        fun depth(depth: Optional<Int>) = depth(depth.getOrNull())

        /**
         * Maximum number of items to return per page. Must be between 1 and 100. Defaults to 20
         * when omitted. Capped at 20 when `view=full`. Both `memory` and `memory_prefix` items
         * count toward the limit.
         */
        fun limit(limit: Int?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Int) = limit(limit as Int?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Int>) = limit(limit.getOrNull())

        /**
         * Opaque pagination cursor (a `page_...` value). Pass the `next_page` value from a previous
         * response to fetch the next page; omit for the first page.
         */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /**
         * Optional path prefix filter. Must end with `/` (segment-aligned), e.g., `/notes/`. This
         * value appears in request URLs. Do not include secrets or personally identifiable
         * information.
         */
        fun pathPrefix(pathPrefix: String?) = apply { this.pathPrefix = pathPrefix }

        /** Alias for calling [Builder.pathPrefix] with `pathPrefix.orElse(null)`. */
        fun pathPrefix(pathPrefix: Optional<String>) = pathPrefix(pathPrefix.getOrNull())

        /**
         * Which projection of each `memory` to return. Defaults to `basic` (content omitted).
         * `full` populates `content` on each item and caps `limit` at 20; use this as the bulk-read
         * path for export and sync.
         */
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
         * Returns an immutable instance of [MemoryListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): MemoryListParams =
            MemoryListParams(
                memoryStoreId,
                depth,
                limit,
                page,
                pathPrefix,
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
                depth?.let { put("depth", it.toString()) }
                limit?.let { put("limit", it.toString()) }
                page?.let { put("page", it) }
                pathPrefix?.let { put("path_prefix", it) }
                view?.let { put("view", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MemoryListParams &&
            memoryStoreId == other.memoryStoreId &&
            depth == other.depth &&
            limit == other.limit &&
            page == other.page &&
            pathPrefix == other.pathPrefix &&
            view == other.view &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            memoryStoreId,
            depth,
            limit,
            page,
            pathPrefix,
            view,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "MemoryListParams{memoryStoreId=$memoryStoreId, depth=$depth, limit=$limit, page=$page, pathPrefix=$pathPrefix, view=$view, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
