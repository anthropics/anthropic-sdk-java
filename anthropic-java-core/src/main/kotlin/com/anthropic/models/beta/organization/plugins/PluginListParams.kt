package com.anthropic.models.beta.organization.plugins

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

/**
 * List the Plugins created under the organization, newest first: those in the organization's own
 * plugin marketplaces and those in members' personal plugin marketplaces.
 *
 * Plugins in members' personal marketplaces are listed with the same detail as the organization's
 * own, and their files can be downloaded through the version archive endpoint, which records each
 * such download on the Compliance API activity feed.
 *
 * **Accepted credentials:** an Admin API key with the `read:plugins` or `read:org_audit` scope, or
 * a Compliance Access Key with the `read:compliance_org_data` scope.
 *
 * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
 * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in beta
 * and is available to Claude Enterprise organizations only. It is not available to Claude Platform
 * (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
 */
class PluginListParams
private constructor(
    private val createdAtGt: OffsetDateTime?,
    private val createdAtGte: OffsetDateTime?,
    private val createdAtLt: OffsetDateTime?,
    private val createdAtLte: OffsetDateTime?,
    private val limit: Long?,
    private val marketplaceId: String?,
    private val organizationId: String?,
    private val ownerType: OwnerType?,
    private val ownerUserId: String?,
    private val page: String?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** RFC 3339 timestamp bound; combine [gte], [gt], [lte], [lt]. */
    fun createdAtGt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGt)

    /** RFC 3339 timestamp bound; combine [gte], [gt], [lte], [lt]. */
    fun createdAtGte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGte)

    /** RFC 3339 timestamp bound; combine [gte], [gt], [lte], [lt]. */
    fun createdAtLt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLt)

    /** RFC 3339 timestamp bound; combine [gte], [gt], [lte], [lt]. */
    fun createdAtLte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLte)

    /**
     * Number of items to return per page.
     *
     * Defaults to `20`. Ranges from `1` to `100`.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /** Only Plugins in this plugin marketplace (prefixed `marketplace_`). */
    fun marketplaceId(): Optional<String> = Optional.ofNullable(marketplaceId)

    /**
     * For a `read:org_audit` or `read:compliance_org_data` key created for all of a parent
     * organization's linked organizations: a child organization of that parent to read instead of
     * the organization the key was created in, given as the organization's UUID or its
     * `org_`-prefixed ID. A value that is neither returns a 400; an organization that is not a
     * child of the key's parent, or where the Plugins API is not available, returns a 404. Any
     * other key may pass only its own organization's ID here; another organization returns a 404.
     */
    fun organizationId(): Optional<String> = Optional.ofNullable(organizationId)

    /**
     * `organization` for Plugins in the organization's plugin marketplaces, `user` for Plugins in
     * members' personal plugin marketplaces.
     */
    fun ownerType(): Optional<OwnerType> = Optional.ofNullable(ownerType)

    /**
     * Only Plugins in this member's personal plugin marketplaces (prefixed `user_`); a removed
     * member's ID is accepted.
     */
    fun ownerUserId(): Optional<String> = Optional.ofNullable(ownerUserId)

    /** Optionally set to the `next_page` token from the previous response. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** This endpoint is in beta: requests must send `ce-plugins-2026-09-01` in this header. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): PluginListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [PluginListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [PluginListParams]. */
    class Builder internal constructor() {

        private var createdAtGt: OffsetDateTime? = null
        private var createdAtGte: OffsetDateTime? = null
        private var createdAtLt: OffsetDateTime? = null
        private var createdAtLte: OffsetDateTime? = null
        private var limit: Long? = null
        private var marketplaceId: String? = null
        private var organizationId: String? = null
        private var ownerType: OwnerType? = null
        private var ownerUserId: String? = null
        private var page: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(pluginListParams: PluginListParams) = apply {
            createdAtGt = pluginListParams.createdAtGt
            createdAtGte = pluginListParams.createdAtGte
            createdAtLt = pluginListParams.createdAtLt
            createdAtLte = pluginListParams.createdAtLte
            limit = pluginListParams.limit
            marketplaceId = pluginListParams.marketplaceId
            organizationId = pluginListParams.organizationId
            ownerType = pluginListParams.ownerType
            ownerUserId = pluginListParams.ownerUserId
            page = pluginListParams.page
            betas = pluginListParams.betas?.toMutableList()
            additionalHeaders = pluginListParams.additionalHeaders.toBuilder()
            additionalQueryParams = pluginListParams.additionalQueryParams.toBuilder()
        }

        /** RFC 3339 timestamp bound; combine [gte], [gt], [lte], [lt]. */
        fun createdAtGt(createdAtGt: OffsetDateTime?) = apply { this.createdAtGt = createdAtGt }

        /** Alias for calling [Builder.createdAtGt] with `createdAtGt.orElse(null)`. */
        fun createdAtGt(createdAtGt: Optional<OffsetDateTime>) =
            createdAtGt(createdAtGt.getOrNull())

        /** RFC 3339 timestamp bound; combine [gte], [gt], [lte], [lt]. */
        fun createdAtGte(createdAtGte: OffsetDateTime?) = apply { this.createdAtGte = createdAtGte }

        /** Alias for calling [Builder.createdAtGte] with `createdAtGte.orElse(null)`. */
        fun createdAtGte(createdAtGte: Optional<OffsetDateTime>) =
            createdAtGte(createdAtGte.getOrNull())

        /** RFC 3339 timestamp bound; combine [gte], [gt], [lte], [lt]. */
        fun createdAtLt(createdAtLt: OffsetDateTime?) = apply { this.createdAtLt = createdAtLt }

        /** Alias for calling [Builder.createdAtLt] with `createdAtLt.orElse(null)`. */
        fun createdAtLt(createdAtLt: Optional<OffsetDateTime>) =
            createdAtLt(createdAtLt.getOrNull())

        /** RFC 3339 timestamp bound; combine [gte], [gt], [lte], [lt]. */
        fun createdAtLte(createdAtLte: OffsetDateTime?) = apply { this.createdAtLte = createdAtLte }

        /** Alias for calling [Builder.createdAtLte] with `createdAtLte.orElse(null)`. */
        fun createdAtLte(createdAtLte: Optional<OffsetDateTime>) =
            createdAtLte(createdAtLte.getOrNull())

        /**
         * Number of items to return per page.
         *
         * Defaults to `20`. Ranges from `1` to `100`.
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

        /** Only Plugins in this plugin marketplace (prefixed `marketplace_`). */
        fun marketplaceId(marketplaceId: String?) = apply { this.marketplaceId = marketplaceId }

        /** Alias for calling [Builder.marketplaceId] with `marketplaceId.orElse(null)`. */
        fun marketplaceId(marketplaceId: Optional<String>) =
            marketplaceId(marketplaceId.getOrNull())

        /**
         * For a `read:org_audit` or `read:compliance_org_data` key created for all of a parent
         * organization's linked organizations: a child organization of that parent to read instead
         * of the organization the key was created in, given as the organization's UUID or its
         * `org_`-prefixed ID. A value that is neither returns a 400; an organization that is not a
         * child of the key's parent, or where the Plugins API is not available, returns a 404. Any
         * other key may pass only its own organization's ID here; another organization returns
         * a 404.
         */
        fun organizationId(organizationId: String?) = apply { this.organizationId = organizationId }

        /** Alias for calling [Builder.organizationId] with `organizationId.orElse(null)`. */
        fun organizationId(organizationId: Optional<String>) =
            organizationId(organizationId.getOrNull())

        /**
         * `organization` for Plugins in the organization's plugin marketplaces, `user` for Plugins
         * in members' personal plugin marketplaces.
         */
        fun ownerType(ownerType: OwnerType?) = apply { this.ownerType = ownerType }

        /** Alias for calling [Builder.ownerType] with `ownerType.orElse(null)`. */
        fun ownerType(ownerType: Optional<OwnerType>) = ownerType(ownerType.getOrNull())

        /**
         * Only Plugins in this member's personal plugin marketplaces (prefixed `user_`); a removed
         * member's ID is accepted.
         */
        fun ownerUserId(ownerUserId: String?) = apply { this.ownerUserId = ownerUserId }

        /** Alias for calling [Builder.ownerUserId] with `ownerUserId.orElse(null)`. */
        fun ownerUserId(ownerUserId: Optional<String>) = ownerUserId(ownerUserId.getOrNull())

        /** Optionally set to the `next_page` token from the previous response. */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /** This endpoint is in beta: requests must send `ce-plugins-2026-09-01` in this header. */
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
         * Returns an immutable instance of [PluginListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): PluginListParams =
            PluginListParams(
                createdAtGt,
                createdAtGte,
                createdAtLt,
                createdAtLte,
                limit,
                marketplaceId,
                organizationId,
                ownerType,
                ownerUserId,
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
                limit?.let { put("limit", it.toString()) }
                marketplaceId?.let { put("marketplace_id", it) }
                organizationId?.let { put("organization_id", it) }
                ownerType?.let { put("owner_type", it.toString()) }
                ownerUserId?.let { put("owner_user_id", it) }
                page?.let { put("page", it) }
                putAll(additionalQueryParams)
            }
            .build()

    /**
     * `organization` for Plugins in the organization's plugin marketplaces, `user` for Plugins in
     * members' personal plugin marketplaces.
     */
    class OwnerType private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val ORGANIZATION = OwnerType(JsonField.of("organization"))

            @JvmField val USER = OwnerType(JsonField.of("user"))

            @JvmStatic
            fun of(value: String): OwnerType =
                // Intern known values so `==` works
                when (value) {
                    "organization" -> ORGANIZATION
                    "user" -> USER
                    else -> OwnerType(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): OwnerType =
                value.asString().getOrNull()?.let { of(it) } ?: OwnerType(value)
        }

        /** An enum containing [OwnerType]'s known values. */
        enum class Known {
            ORGANIZATION,
            USER,
        }

        /**
         * An enum containing [OwnerType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [OwnerType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ORGANIZATION,
            USER,
            /**
             * An enum member indicating that [OwnerType] was instantiated with an unknown value.
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
                USER -> Value.USER
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
                USER -> Known.USER
                else -> throw AnthropicInvalidDataException("Unknown OwnerType: $value")
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
        fun validate(): OwnerType = apply {
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

            return other is OwnerType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is PluginListParams &&
            createdAtGt == other.createdAtGt &&
            createdAtGte == other.createdAtGte &&
            createdAtLt == other.createdAtLt &&
            createdAtLte == other.createdAtLte &&
            limit == other.limit &&
            marketplaceId == other.marketplaceId &&
            organizationId == other.organizationId &&
            ownerType == other.ownerType &&
            ownerUserId == other.ownerUserId &&
            page == other.page &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            createdAtGt,
            createdAtGte,
            createdAtLt,
            createdAtLte,
            limit,
            marketplaceId,
            organizationId,
            ownerType,
            ownerUserId,
            page,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "PluginListParams{createdAtGt=$createdAtGt, createdAtGte=$createdAtGte, createdAtLt=$createdAtLt, createdAtLte=$createdAtLte, limit=$limit, marketplaceId=$marketplaceId, organizationId=$organizationId, ownerType=$ownerType, ownerUserId=$ownerUserId, page=$page, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
