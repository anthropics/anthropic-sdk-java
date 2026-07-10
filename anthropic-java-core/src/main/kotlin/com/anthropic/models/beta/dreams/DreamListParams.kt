// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** List Dreams */
class DreamListParams
private constructor(
    private val createdAtGt: OffsetDateTime?,
    private val createdAtLt: OffsetDateTime?,
    private val includeArchived: Boolean?,
    private val limit: Int?,
    private val page: String?,
    private val statuses: List<BetaDreamStatus>?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * Return dreams with `created_at` strictly after this timestamp (exclusive lower bound, RFC
     * 3339). Unset applies no lower bound.
     */
    fun createdAtGt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGt)

    /**
     * Return dreams with `created_at` strictly before this timestamp (exclusive upper bound, RFC
     * 3339). Unset applies no upper bound.
     */
    fun createdAtLt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLt)

    /** Query parameter for include_archived */
    fun includeArchived(): Optional<Boolean> = Optional.ofNullable(includeArchived)

    /** Query parameter for limit */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /** Query parameter for page */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /**
     * Filter by lifecycle status. Repeat the parameter to match any of multiple statuses. Empty
     * applies no status filter.
     */
    fun statuses(): Optional<List<BetaDreamStatus>> = Optional.ofNullable(statuses)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): DreamListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [DreamListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [DreamListParams]. */
    class Builder internal constructor() {

        private var createdAtGt: OffsetDateTime? = null
        private var createdAtLt: OffsetDateTime? = null
        private var includeArchived: Boolean? = null
        private var limit: Int? = null
        private var page: String? = null
        private var statuses: MutableList<BetaDreamStatus>? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(dreamListParams: DreamListParams) = apply {
            createdAtGt = dreamListParams.createdAtGt
            createdAtLt = dreamListParams.createdAtLt
            includeArchived = dreamListParams.includeArchived
            limit = dreamListParams.limit
            page = dreamListParams.page
            statuses = dreamListParams.statuses?.toMutableList()
            betas = dreamListParams.betas?.toMutableList()
            additionalHeaders = dreamListParams.additionalHeaders.toBuilder()
            additionalQueryParams = dreamListParams.additionalQueryParams.toBuilder()
        }

        /**
         * Return dreams with `created_at` strictly after this timestamp (exclusive lower bound, RFC
         * 3339). Unset applies no lower bound.
         */
        fun createdAtGt(createdAtGt: OffsetDateTime?) = apply { this.createdAtGt = createdAtGt }

        /** Alias for calling [Builder.createdAtGt] with `createdAtGt.orElse(null)`. */
        fun createdAtGt(createdAtGt: Optional<OffsetDateTime>) =
            createdAtGt(createdAtGt.getOrNull())

        /**
         * Return dreams with `created_at` strictly before this timestamp (exclusive upper bound,
         * RFC 3339). Unset applies no upper bound.
         */
        fun createdAtLt(createdAtLt: OffsetDateTime?) = apply { this.createdAtLt = createdAtLt }

        /** Alias for calling [Builder.createdAtLt] with `createdAtLt.orElse(null)`. */
        fun createdAtLt(createdAtLt: Optional<OffsetDateTime>) =
            createdAtLt(createdAtLt.getOrNull())

        /** Query parameter for include_archived */
        fun includeArchived(includeArchived: Boolean?) = apply {
            this.includeArchived = includeArchived
        }

        /**
         * Alias for [Builder.includeArchived].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun includeArchived(includeArchived: Boolean) = includeArchived(includeArchived as Boolean?)

        /** Alias for calling [Builder.includeArchived] with `includeArchived.orElse(null)`. */
        fun includeArchived(includeArchived: Optional<Boolean>) =
            includeArchived(includeArchived.getOrNull())

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

        /** Query parameter for page */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /**
         * Filter by lifecycle status. Repeat the parameter to match any of multiple statuses. Empty
         * applies no status filter.
         */
        fun statuses(statuses: List<BetaDreamStatus>?) = apply {
            this.statuses = statuses?.toMutableList()
        }

        /** Alias for calling [Builder.statuses] with `statuses.orElse(null)`. */
        fun statuses(statuses: Optional<List<BetaDreamStatus>>) = statuses(statuses.getOrNull())

        /**
         * Adds a single [BetaDreamStatus] to [statuses].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addStatus(status: BetaDreamStatus) = apply {
            statuses = (statuses ?: mutableListOf()).apply { add(status) }
        }

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
         * Returns an immutable instance of [DreamListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): DreamListParams =
            DreamListParams(
                createdAtGt,
                createdAtLt,
                includeArchived,
                limit,
                page,
                statuses?.toImmutable(),
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
                createdAtGt?.let {
                    put("created_at[gt]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdAtLt?.let {
                    put("created_at[lt]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                includeArchived?.let { put("include_archived", it.toString()) }
                limit?.let { put("limit", it.toString()) }
                page?.let { put("page", it) }
                statuses?.forEach { put("statuses[]", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is DreamListParams &&
            createdAtGt == other.createdAtGt &&
            createdAtLt == other.createdAtLt &&
            includeArchived == other.includeArchived &&
            limit == other.limit &&
            page == other.page &&
            statuses == other.statuses &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            createdAtGt,
            createdAtLt,
            includeArchived,
            limit,
            page,
            statuses,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "DreamListParams{createdAtGt=$createdAtGt, createdAtLt=$createdAtLt, includeArchived=$includeArchived, limit=$limit, page=$page, statuses=$statuses, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
