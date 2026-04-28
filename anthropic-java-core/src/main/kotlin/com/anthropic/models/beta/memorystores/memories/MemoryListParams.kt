// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.fasterxml.jackson.annotation.JsonCreator
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** List memories */
class MemoryListParams
private constructor(
    private val memoryStoreId: String?,
    private val depth: Int?,
    private val limit: Int?,
    private val order: Order?,
    private val orderBy: String?,
    private val page: String?,
    private val pathPrefix: String?,
    private val view: BetaManagedAgentsMemoryView?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun memoryStoreId(): Optional<String> = Optional.ofNullable(memoryStoreId)

    /** Query parameter for depth */
    fun depth(): Optional<Int> = Optional.ofNullable(depth)

    /** Query parameter for limit */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /** Query parameter for order */
    fun order(): Optional<Order> = Optional.ofNullable(order)

    /** Query parameter for order_by */
    fun orderBy(): Optional<String> = Optional.ofNullable(orderBy)

    /** Query parameter for page */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /**
     * Optional path prefix filter (raw string-prefix match; include a trailing slash for
     * directory-scoped lists). This value appears in request URLs. Do not include secrets or
     * personally identifiable information.
     */
    fun pathPrefix(): Optional<String> = Optional.ofNullable(pathPrefix)

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

        @JvmStatic fun none(): MemoryListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [MemoryListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MemoryListParams]. */
    class Builder internal constructor() {

        private var memoryStoreId: String? = null
        private var depth: Int? = null
        private var limit: Int? = null
        private var order: Order? = null
        private var orderBy: String? = null
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
            order = memoryListParams.order
            orderBy = memoryListParams.orderBy
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

        /** Query parameter for depth */
        fun depth(depth: Int?) = apply { this.depth = depth }

        /**
         * Alias for [Builder.depth].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun depth(depth: Int) = depth(depth as Int?)

        /** Alias for calling [Builder.depth] with `depth.orElse(null)`. */
        fun depth(depth: Optional<Int>) = depth(depth.getOrNull())

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

        /** Query parameter for order */
        fun order(order: Order?) = apply { this.order = order }

        /** Alias for calling [Builder.order] with `order.orElse(null)`. */
        fun order(order: Optional<Order>) = order(order.getOrNull())

        /** Query parameter for order_by */
        fun orderBy(orderBy: String?) = apply { this.orderBy = orderBy }

        /** Alias for calling [Builder.orderBy] with `orderBy.orElse(null)`. */
        fun orderBy(orderBy: Optional<String>) = orderBy(orderBy.getOrNull())

        /** Query parameter for page */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /**
         * Optional path prefix filter (raw string-prefix match; include a trailing slash for
         * directory-scoped lists). This value appears in request URLs. Do not include secrets or
         * personally identifiable information.
         */
        fun pathPrefix(pathPrefix: String?) = apply { this.pathPrefix = pathPrefix }

        /** Alias for calling [Builder.pathPrefix] with `pathPrefix.orElse(null)`. */
        fun pathPrefix(pathPrefix: Optional<String>) = pathPrefix(pathPrefix.getOrNull())

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
         * Returns an immutable instance of [MemoryListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): MemoryListParams =
            MemoryListParams(
                memoryStoreId,
                depth,
                limit,
                order,
                orderBy,
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
                order?.let { put("order", it.toString()) }
                orderBy?.let { put("order_by", it) }
                page?.let { put("page", it) }
                pathPrefix?.let { put("path_prefix", it) }
                view?.let { put("view", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    /** Query parameter for order */
    class Order @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val ASC = of("asc")

            @JvmField val DESC = of("desc")

            @JvmStatic fun of(value: String) = Order(JsonField.of(value))
        }

        /** An enum containing [Order]'s known values. */
        enum class Known {
            ASC,
            DESC,
        }

        /**
         * An enum containing [Order]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Order] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ASC,
            DESC,
            /** An enum member indicating that [Order] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                ASC -> Value.ASC
                DESC -> Value.DESC
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                ASC -> Known.ASC
                DESC -> Known.DESC
                else -> throw AnthropicInvalidDataException("Unknown Order: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): Order = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Order && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MemoryListParams &&
            memoryStoreId == other.memoryStoreId &&
            depth == other.depth &&
            limit == other.limit &&
            order == other.order &&
            orderBy == other.orderBy &&
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
            order,
            orderBy,
            page,
            pathPrefix,
            view,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "MemoryListParams{memoryStoreId=$memoryStoreId, depth=$depth, limit=$limit, order=$order, orderBy=$orderBy, page=$page, pathPrefix=$pathPrefix, view=$view, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
