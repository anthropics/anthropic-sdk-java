package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * List the Slack channels and Slack workspaces a Bundle is attached to, newest first by
 * `created_at`. Only channels and workspaces the organization owns are listed; a Bundle that is
 * attached to anything else has no Bundle Target for it.
 */
class TargetListParams
private constructor(
    private val bundleId: String?,
    private val limit: Int?,
    private val page: String?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** The Bundle's identifier, starting with `capp_`. */
    fun bundleId(): Optional<String> = Optional.ofNullable(bundleId)

    /** Number of items to return per page, between 1 and 100. Defaults to 20. */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /** Opaque cursor from a previous response's `next_page`. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /**
     * This endpoint is in beta: requests must send `experimental-claude-tag-2026-10-21` in this
     * header.
     */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): TargetListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [TargetListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TargetListParams]. */
    class Builder internal constructor() {

        private var bundleId: String? = null
        private var limit: Int? = null
        private var page: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(targetListParams: TargetListParams) = apply {
            bundleId = targetListParams.bundleId
            limit = targetListParams.limit
            page = targetListParams.page
            betas = targetListParams.betas?.toMutableList()
            additionalHeaders = targetListParams.additionalHeaders.toBuilder()
            additionalQueryParams = targetListParams.additionalQueryParams.toBuilder()
        }

        /** The Bundle's identifier, starting with `capp_`. */
        fun bundleId(bundleId: String?) = apply { this.bundleId = bundleId }

        /** Alias for calling [Builder.bundleId] with `bundleId.orElse(null)`. */
        fun bundleId(bundleId: Optional<String>) = bundleId(bundleId.getOrNull())

        /** Number of items to return per page, between 1 and 100. Defaults to 20. */
        fun limit(limit: Int?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Int) = limit(limit as Int?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Int>) = limit(limit.getOrNull())

        /** Opaque cursor from a previous response's `next_page`. */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /**
         * This endpoint is in beta: requests must send `experimental-claude-tag-2026-10-21` in this
         * header.
         */
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
         * Returns an immutable instance of [TargetListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): TargetListParams =
            TargetListParams(
                bundleId,
                limit,
                page,
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> bundleId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.let {
                    if (it.isNotEmpty()) {
                        put(
                            "anthropic-beta",
                            it.map { it.toString() }
                                .let { it + (listOf("experimental-claude-tag-2026-10-21") - it) }
                                .joinToString(","),
                        )
                    }
                }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                limit?.let { put("limit", it.toString()) }
                page?.let { put("page", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.organization.claude_tag.bundles.targets.list",
            route = "/v1/organizations/claude_tag/bundles/{bundle_id}/targets",
            attributes = mapOf("anthropic.organization.claude_tag.bundle.id" to _pathParam(0)),
            queryParams = listOf("limit", "page"),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TargetListParams &&
            bundleId == other.bundleId &&
            limit == other.limit &&
            page == other.page &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(bundleId, limit, page, betas, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "TargetListParams{bundleId=$bundleId, limit=$limit, page=$page, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
