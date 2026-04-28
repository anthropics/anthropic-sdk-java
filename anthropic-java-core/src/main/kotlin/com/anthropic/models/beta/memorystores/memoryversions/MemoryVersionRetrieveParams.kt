// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.memorystores.memories.BetaManagedAgentsMemoryView
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Retrieve a memory version */
class MemoryVersionRetrieveParams
private constructor(
    private val memoryStoreId: String,
    private val memoryVersionId: String?,
    private val view: BetaManagedAgentsMemoryView?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun memoryStoreId(): String = memoryStoreId

    fun memoryVersionId(): Optional<String> = Optional.ofNullable(memoryVersionId)

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

        /**
         * Returns a mutable builder for constructing an instance of [MemoryVersionRetrieveParams].
         *
         * The following fields are required:
         * ```java
         * .memoryStoreId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MemoryVersionRetrieveParams]. */
    class Builder internal constructor() {

        private var memoryStoreId: String? = null
        private var memoryVersionId: String? = null
        private var view: BetaManagedAgentsMemoryView? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(memoryVersionRetrieveParams: MemoryVersionRetrieveParams) = apply {
            memoryStoreId = memoryVersionRetrieveParams.memoryStoreId
            memoryVersionId = memoryVersionRetrieveParams.memoryVersionId
            view = memoryVersionRetrieveParams.view
            betas = memoryVersionRetrieveParams.betas?.toMutableList()
            additionalHeaders = memoryVersionRetrieveParams.additionalHeaders.toBuilder()
            additionalQueryParams = memoryVersionRetrieveParams.additionalQueryParams.toBuilder()
        }

        fun memoryStoreId(memoryStoreId: String) = apply { this.memoryStoreId = memoryStoreId }

        fun memoryVersionId(memoryVersionId: String?) = apply {
            this.memoryVersionId = memoryVersionId
        }

        /** Alias for calling [Builder.memoryVersionId] with `memoryVersionId.orElse(null)`. */
        fun memoryVersionId(memoryVersionId: Optional<String>) =
            memoryVersionId(memoryVersionId.getOrNull())

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
         * Returns an immutable instance of [MemoryVersionRetrieveParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .memoryStoreId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): MemoryVersionRetrieveParams =
            MemoryVersionRetrieveParams(
                checkRequired("memoryStoreId", memoryStoreId),
                memoryVersionId,
                view,
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> memoryStoreId
            1 -> memoryVersionId ?: ""
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
                view?.let { put("view", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MemoryVersionRetrieveParams &&
            memoryStoreId == other.memoryStoreId &&
            memoryVersionId == other.memoryVersionId &&
            view == other.view &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            memoryStoreId,
            memoryVersionId,
            view,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "MemoryVersionRetrieveParams{memoryStoreId=$memoryStoreId, memoryVersionId=$memoryVersionId, view=$view, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
