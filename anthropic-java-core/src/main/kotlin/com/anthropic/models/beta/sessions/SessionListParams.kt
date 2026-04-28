// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.fasterxml.jackson.annotation.JsonCreator
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** List Sessions */
class SessionListParams
private constructor(
    private val agentId: String?,
    private val agentVersion: Int?,
    private val createdAtGt: OffsetDateTime?,
    private val createdAtGte: OffsetDateTime?,
    private val createdAtLt: OffsetDateTime?,
    private val createdAtLte: OffsetDateTime?,
    private val includeArchived: Boolean?,
    private val limit: Int?,
    private val memoryStoreId: String?,
    private val order: Order?,
    private val page: String?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Filter sessions created with this agent ID. */
    fun agentId(): Optional<String> = Optional.ofNullable(agentId)

    /** Filter by agent version. Only applies when agent_id is also set. */
    fun agentVersion(): Optional<Int> = Optional.ofNullable(agentVersion)

    /** Return sessions created after this time (exclusive). */
    fun createdAtGt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGt)

    /** Return sessions created at or after this time (inclusive). */
    fun createdAtGte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGte)

    /** Return sessions created before this time (exclusive). */
    fun createdAtLt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLt)

    /** Return sessions created at or before this time (inclusive). */
    fun createdAtLte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLte)

    /** When true, includes archived sessions. Default: false (exclude archived). */
    fun includeArchived(): Optional<Boolean> = Optional.ofNullable(includeArchived)

    /** Maximum number of results to return. */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /** Filter sessions whose resources contain a memory_store with this memory store ID. */
    fun memoryStoreId(): Optional<String> = Optional.ofNullable(memoryStoreId)

    /** Sort direction for results, ordered by created_at. Defaults to desc (newest first). */
    fun order(): Optional<Order> = Optional.ofNullable(order)

    /** Opaque pagination cursor from a previous response's next_page. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): SessionListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [SessionListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionListParams]. */
    class Builder internal constructor() {

        private var agentId: String? = null
        private var agentVersion: Int? = null
        private var createdAtGt: OffsetDateTime? = null
        private var createdAtGte: OffsetDateTime? = null
        private var createdAtLt: OffsetDateTime? = null
        private var createdAtLte: OffsetDateTime? = null
        private var includeArchived: Boolean? = null
        private var limit: Int? = null
        private var memoryStoreId: String? = null
        private var order: Order? = null
        private var page: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(sessionListParams: SessionListParams) = apply {
            agentId = sessionListParams.agentId
            agentVersion = sessionListParams.agentVersion
            createdAtGt = sessionListParams.createdAtGt
            createdAtGte = sessionListParams.createdAtGte
            createdAtLt = sessionListParams.createdAtLt
            createdAtLte = sessionListParams.createdAtLte
            includeArchived = sessionListParams.includeArchived
            limit = sessionListParams.limit
            memoryStoreId = sessionListParams.memoryStoreId
            order = sessionListParams.order
            page = sessionListParams.page
            betas = sessionListParams.betas?.toMutableList()
            additionalHeaders = sessionListParams.additionalHeaders.toBuilder()
            additionalQueryParams = sessionListParams.additionalQueryParams.toBuilder()
        }

        /** Filter sessions created with this agent ID. */
        fun agentId(agentId: String?) = apply { this.agentId = agentId }

        /** Alias for calling [Builder.agentId] with `agentId.orElse(null)`. */
        fun agentId(agentId: Optional<String>) = agentId(agentId.getOrNull())

        /** Filter by agent version. Only applies when agent_id is also set. */
        fun agentVersion(agentVersion: Int?) = apply { this.agentVersion = agentVersion }

        /**
         * Alias for [Builder.agentVersion].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun agentVersion(agentVersion: Int) = agentVersion(agentVersion as Int?)

        /** Alias for calling [Builder.agentVersion] with `agentVersion.orElse(null)`. */
        fun agentVersion(agentVersion: Optional<Int>) = agentVersion(agentVersion.getOrNull())

        /** Return sessions created after this time (exclusive). */
        fun createdAtGt(createdAtGt: OffsetDateTime?) = apply { this.createdAtGt = createdAtGt }

        /** Alias for calling [Builder.createdAtGt] with `createdAtGt.orElse(null)`. */
        fun createdAtGt(createdAtGt: Optional<OffsetDateTime>) =
            createdAtGt(createdAtGt.getOrNull())

        /** Return sessions created at or after this time (inclusive). */
        fun createdAtGte(createdAtGte: OffsetDateTime?) = apply { this.createdAtGte = createdAtGte }

        /** Alias for calling [Builder.createdAtGte] with `createdAtGte.orElse(null)`. */
        fun createdAtGte(createdAtGte: Optional<OffsetDateTime>) =
            createdAtGte(createdAtGte.getOrNull())

        /** Return sessions created before this time (exclusive). */
        fun createdAtLt(createdAtLt: OffsetDateTime?) = apply { this.createdAtLt = createdAtLt }

        /** Alias for calling [Builder.createdAtLt] with `createdAtLt.orElse(null)`. */
        fun createdAtLt(createdAtLt: Optional<OffsetDateTime>) =
            createdAtLt(createdAtLt.getOrNull())

        /** Return sessions created at or before this time (inclusive). */
        fun createdAtLte(createdAtLte: OffsetDateTime?) = apply { this.createdAtLte = createdAtLte }

        /** Alias for calling [Builder.createdAtLte] with `createdAtLte.orElse(null)`. */
        fun createdAtLte(createdAtLte: Optional<OffsetDateTime>) =
            createdAtLte(createdAtLte.getOrNull())

        /** When true, includes archived sessions. Default: false (exclude archived). */
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

        /** Maximum number of results to return. */
        fun limit(limit: Int?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Int) = limit(limit as Int?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Int>) = limit(limit.getOrNull())

        /** Filter sessions whose resources contain a memory_store with this memory store ID. */
        fun memoryStoreId(memoryStoreId: String?) = apply { this.memoryStoreId = memoryStoreId }

        /** Alias for calling [Builder.memoryStoreId] with `memoryStoreId.orElse(null)`. */
        fun memoryStoreId(memoryStoreId: Optional<String>) =
            memoryStoreId(memoryStoreId.getOrNull())

        /** Sort direction for results, ordered by created_at. Defaults to desc (newest first). */
        fun order(order: Order?) = apply { this.order = order }

        /** Alias for calling [Builder.order] with `order.orElse(null)`. */
        fun order(order: Optional<Order>) = order(order.getOrNull())

        /** Opaque pagination cursor from a previous response's next_page. */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

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
         * Returns an immutable instance of [SessionListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): SessionListParams =
            SessionListParams(
                agentId,
                agentVersion,
                createdAtGt,
                createdAtGte,
                createdAtLt,
                createdAtLte,
                includeArchived,
                limit,
                memoryStoreId,
                order,
                page,
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
                agentId?.let { put("agent_id", it) }
                agentVersion?.let { put("agent_version", it.toString()) }
                createdAtGt?.let {
                    put("created_at[gt]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdAtGte?.let {
                    put("created_at[gte]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdAtLt?.let {
                    put("created_at[lt]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdAtLte?.let {
                    put("created_at[lte]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                includeArchived?.let { put("include_archived", it.toString()) }
                limit?.let { put("limit", it.toString()) }
                memoryStoreId?.let { put("memory_store_id", it) }
                order?.let { put("order", it.toString()) }
                page?.let { put("page", it) }
                putAll(additionalQueryParams)
            }
            .build()

    /** Sort direction for results, ordered by created_at. Defaults to desc (newest first). */
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

        return other is SessionListParams &&
            agentId == other.agentId &&
            agentVersion == other.agentVersion &&
            createdAtGt == other.createdAtGt &&
            createdAtGte == other.createdAtGte &&
            createdAtLt == other.createdAtLt &&
            createdAtLte == other.createdAtLte &&
            includeArchived == other.includeArchived &&
            limit == other.limit &&
            memoryStoreId == other.memoryStoreId &&
            order == other.order &&
            page == other.page &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            agentId,
            agentVersion,
            createdAtGt,
            createdAtGte,
            createdAtLt,
            createdAtLte,
            includeArchived,
            limit,
            memoryStoreId,
            order,
            page,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "SessionListParams{agentId=$agentId, agentVersion=$agentVersion, createdAtGt=$createdAtGt, createdAtGte=$createdAtGte, createdAtLt=$createdAtLt, createdAtLte=$createdAtLte, includeArchived=$includeArchived, limit=$limit, memoryStoreId=$memoryStoreId, order=$order, page=$page, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
