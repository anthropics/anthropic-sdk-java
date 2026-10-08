package com.anthropic.models.beta.organization.spendlimits

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

/**
 * List the organization's spend limits.
 *
 * A Claude Console organization's limits come in an order that is stable across pages. A Claude
 * Enterprise organization's are grouped by scope type, in the order `organization`, `seat_tier`,
 * `rbac_group`, `organization_service`, `user`; within a type they come in a fixed order that is
 * not creation order. Listing Claude Console limits is in an early access preview. To request
 * access, contact your Anthropic account team.
 */
class SpendLimitListParams
private constructor(
    private val limit: Long?,
    private val page: String?,
    private val scopeType: List<ScopeType>?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Maximum number of limits per page. Defaults to `20`. */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /** Opaque cursor from a previous response's `next_page` field. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /**
     * Return only limits with these scope types. A Claude Console organization has `organization`
     * and `workspace` limits; a Claude Enterprise organization has `organization`, `seat_tier`,
     * `rbac_group`, `organization_service` and `user` limits. Omit for all.
     */
    fun scopeType(): Optional<List<ScopeType>> = Optional.ofNullable(scopeType)

    /**
     * This endpoint is in beta: requests must send `spend-limit-reads-2026-09-26` in this header.
     */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): SpendLimitListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [SpendLimitListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SpendLimitListParams]. */
    class Builder internal constructor() {

        private var limit: Long? = null
        private var page: String? = null
        private var scopeType: MutableList<ScopeType>? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(spendLimitListParams: SpendLimitListParams) = apply {
            limit = spendLimitListParams.limit
            page = spendLimitListParams.page
            scopeType = spendLimitListParams.scopeType?.toMutableList()
            betas = spendLimitListParams.betas?.toMutableList()
            additionalHeaders = spendLimitListParams.additionalHeaders.toBuilder()
            additionalQueryParams = spendLimitListParams.additionalQueryParams.toBuilder()
        }

        /** Maximum number of limits per page. Defaults to `20`. */
        fun limit(limit: Long?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Long) = limit(limit as Long?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Long>) = limit(limit.getOrNull())

        /** Opaque cursor from a previous response's `next_page` field. */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /**
         * Return only limits with these scope types. A Claude Console organization has
         * `organization` and `workspace` limits; a Claude Enterprise organization has
         * `organization`, `seat_tier`, `rbac_group`, `organization_service` and `user` limits. Omit
         * for all.
         */
        fun scopeType(scopeType: List<ScopeType>?) = apply {
            this.scopeType = scopeType?.toMutableList()
        }

        /** Alias for calling [Builder.scopeType] with `scopeType.orElse(null)`. */
        fun scopeType(scopeType: Optional<List<ScopeType>>) = scopeType(scopeType.getOrNull())

        /**
         * Adds a single [ScopeType] to [Builder.scopeType].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addScopeType(scopeType: ScopeType) = apply {
            this.scopeType = (this.scopeType ?: mutableListOf()).apply { add(scopeType) }
        }

        /**
         * This endpoint is in beta: requests must send `spend-limit-reads-2026-09-26` in this
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
         * Returns an immutable instance of [SpendLimitListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): SpendLimitListParams =
            SpendLimitListParams(
                limit,
                page,
                scopeType?.toImmutable(),
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.let {
                    if (it.isNotEmpty()) {
                        put(
                            "anthropic-beta",
                            it.map { it.toString() }
                                .let { it + (listOf("spend-limit-reads-2026-09-26") - it) }
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
                scopeType?.forEach { put("scope_type[]", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    class ScopeType private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val ORGANIZATION = ScopeType(JsonField.of("organization"))

            @JvmField val ORGANIZATION_SERVICE = ScopeType(JsonField.of("organization_service"))

            @JvmField val RBAC_GROUP = ScopeType(JsonField.of("rbac_group"))

            @JvmField val SEAT_TIER = ScopeType(JsonField.of("seat_tier"))

            @JvmField val USER = ScopeType(JsonField.of("user"))

            @JvmField val WORKSPACE = ScopeType(JsonField.of("workspace"))

            @JvmStatic
            fun of(value: String): ScopeType =
                // Intern known values so `==` works
                when (value) {
                    "organization" -> ORGANIZATION
                    "organization_service" -> ORGANIZATION_SERVICE
                    "rbac_group" -> RBAC_GROUP
                    "seat_tier" -> SEAT_TIER
                    "user" -> USER
                    "workspace" -> WORKSPACE
                    else -> ScopeType(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): ScopeType =
                value.asString().getOrNull()?.let { of(it) } ?: ScopeType(value)
        }

        /** An enum containing [ScopeType]'s known values. */
        enum class Known {
            ORGANIZATION,
            ORGANIZATION_SERVICE,
            RBAC_GROUP,
            SEAT_TIER,
            USER,
            WORKSPACE,
        }

        /**
         * An enum containing [ScopeType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ScopeType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ORGANIZATION,
            ORGANIZATION_SERVICE,
            RBAC_GROUP,
            SEAT_TIER,
            USER,
            WORKSPACE,
            /**
             * An enum member indicating that [ScopeType] was instantiated with an unknown value.
             */
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
                ORGANIZATION -> Value.ORGANIZATION
                ORGANIZATION_SERVICE -> Value.ORGANIZATION_SERVICE
                RBAC_GROUP -> Value.RBAC_GROUP
                SEAT_TIER -> Value.SEAT_TIER
                USER -> Value.USER
                WORKSPACE -> Value.WORKSPACE
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
                ORGANIZATION -> Known.ORGANIZATION
                ORGANIZATION_SERVICE -> Known.ORGANIZATION_SERVICE
                RBAC_GROUP -> Known.RBAC_GROUP
                SEAT_TIER -> Known.SEAT_TIER
                USER -> Known.USER
                WORKSPACE -> Known.WORKSPACE
                else -> throw AnthropicInvalidDataException("Unknown ScopeType: $value")
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

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): ScopeType = apply {
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

            return other is ScopeType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SpendLimitListParams &&
            limit == other.limit &&
            page == other.page &&
            scopeType == other.scopeType &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(limit, page, scopeType, betas, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "SpendLimitListParams{limit=$limit, page=$page, scopeType=$scopeType, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
