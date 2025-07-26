// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * List available models.
 *
 * The Models API response can be used to determine which models are available for use in the API.
 * More recently released models are listed first.
 */
class ModelListParams
private constructor(
    private val afterId: String?,
    private val beforeId: String?,
    private val limit: Long?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * ID of the object to use as a cursor for pagination. When provided, returns the page of
     * results immediately after this object.
     */
    fun afterId(): Optional<String> = Optional.ofNullable(afterId)

    /**
     * ID of the object to use as a cursor for pagination. When provided, returns the page of
     * results immediately before this object.
     */
    fun beforeId(): Optional<String> = Optional.ofNullable(beforeId)

    /**
     * Number of items to return per page.
     *
     * Defaults to `20`. Ranges from `1` to `1000`.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): ModelListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [ModelListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ModelListParams]. */
    class Builder internal constructor() {

        private var afterId: String? = null
        private var beforeId: String? = null
        private var limit: Long? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(modelListParams: ModelListParams) = apply {
            afterId = modelListParams.afterId
            beforeId = modelListParams.beforeId
            limit = modelListParams.limit
            betas = modelListParams.betas?.toMutableList()
            additionalHeaders = modelListParams.additionalHeaders.toBuilder()
            additionalQueryParams = modelListParams.additionalQueryParams.toBuilder()
        }

        /**
         * ID of the object to use as a cursor for pagination. When provided, returns the page of
         * results immediately after this object.
         */
        fun afterId(afterId: String?) = apply { this.afterId = afterId }

        /** Alias for calling [Builder.afterId] with `afterId.orElse(null)`. */
        fun afterId(afterId: Optional<String>) = afterId(afterId.getOrNull())

        /**
         * ID of the object to use as a cursor for pagination. When provided, returns the page of
         * results immediately before this object.
         */
        fun beforeId(beforeId: String?) = apply { this.beforeId = beforeId }

        /** Alias for calling [Builder.beforeId] with `beforeId.orElse(null)`. */
        fun beforeId(beforeId: Optional<String>) = beforeId(beforeId.getOrNull())

        /**
         * Number of items to return per page.
         *
         * Defaults to `20`. Ranges from `1` to `1000`.
         */
        fun limit(limit: Long?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Long) = limit(limit as Long?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Long>) = limit(limit.getOrNull())

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
         * Returns an immutable instance of [ModelListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ModelListParams =
            ModelListParams(
                afterId,
                beforeId,
                limit,
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
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
                afterId?.let { put("after_id", it) }
                beforeId?.let { put("before_id", it) }
                limit?.let { put("limit", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ModelListParams && afterId == other.afterId && beforeId == other.beforeId && limit == other.limit && betas == other.betas && additionalHeaders == other.additionalHeaders && additionalQueryParams == other.additionalQueryParams /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(afterId, beforeId, limit, betas, additionalHeaders, additionalQueryParams) /* spotless:on */

    override fun toString() =
        "ModelListParams{afterId=$afterId, beforeId=$beforeId, limit=$limit, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
