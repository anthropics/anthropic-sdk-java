package com.anthropic.models.beta.organization.analytics.artifacts

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import java.time.LocalDate
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Get artifact-creation activity for a given day, broken out by MIME type.
 *
 * Returns the full (`artifact_type`, `is_shared`) cube for the organization; `next_page` is null
 * except for grouped queries, which paginate. The cube can be broken out per product, per member,
 * or per RBAC group via `group_by[]`, and scoped via `filter[]`. Requires an API key with the
 * `read:analytics` scope.
 */
class ArtifactListParams
private constructor(
    private val date: LocalDate,
    private val filter: List<String>?,
    private val groupBy: List<GroupBy>?,
    private val limit: Long?,
    private val page: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * UTC date in YYYY-MM-DD format. The day to get artifact activity for. Data is typically
     * available with a 1-day lag (varies by query; the error for a too-recent date names the latest
     * available day) and may be revised by a few percent over the following days. No earlier than
     * 2026-01-01.
     */
    fun date(): LocalDate = date

    /**
     * Filters as `dimension:value`, e.g. `filter[]=rbac_group_id:{id}`. Repeat the param for OR
     * within a dimension and across dimensions for AND. Supported dimensions on this endpoint:
     * `artifact_type`, `is_shared`, `product`, `rbac_group_id`, `user_id`. Value forms:
     * `artifact_type` is a canonical artifact MIME type (e.g. `text/markdown`) or `other`;
     * `is_shared` is `true` or `false`; `product` is `chat`, `claude_code`, or `cowork` (the
     * surfaces that create artifacts); `rbac_group_id` takes the tagged id (`rbac_group_...`, as
     * emitted in responses and by the spend-limits API) or a bare group UUID, and matches users who
     * held the group at any point during each covered UTC day (time-of-usage attribution);
     * `user_id` takes a tagged user id (`user_...`), as emitted in responses. An unsupported
     * dimension returns 400. At most 100 entries.
     */
    fun filter(): Optional<List<String>> = Optional.ofNullable(filter)

    /**
     * Dimensions to break results out by: `product`, `user_id` and/or `rbac_group_id`. The
     * ungrouped artifact-type cube is finite and returned in full; grouped queries multiply the
     * cube and paginate via `next_page`. `product` takes the values `chat`, `claude_code`, or
     * `cowork` (the surfaces that create artifacts). `rbac_group_id` attributes a user to every
     * group they held at any point during the requested UTC day, so grouped rows are not an
     * exclusive partition. At most 100 entries.
     */
    fun groupBy(): Optional<List<GroupBy>> = Optional.ofNullable(groupBy)

    /**
     * Maximum rows to return (1-1000, default 100). The ungrouped artifact-type cube is finite and
     * returned in full; `limit` is the page size only when `group_by[]` multiplies the cube.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /**
     * Opaque cursor from a previous response's `next_page` field. Only valid with `group_by[]` —
     * the ungrouped cube is never paginated.
     */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ArtifactListParams].
         *
         * The following fields are required:
         * ```java
         * .date()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ArtifactListParams]. */
    class Builder internal constructor() {

        private var date: LocalDate? = null
        private var filter: MutableList<String>? = null
        private var groupBy: MutableList<GroupBy>? = null
        private var limit: Long? = null
        private var page: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(artifactListParams: ArtifactListParams) = apply {
            date = artifactListParams.date
            filter = artifactListParams.filter?.toMutableList()
            groupBy = artifactListParams.groupBy?.toMutableList()
            limit = artifactListParams.limit
            page = artifactListParams.page
            additionalHeaders = artifactListParams.additionalHeaders.toBuilder()
            additionalQueryParams = artifactListParams.additionalQueryParams.toBuilder()
        }

        /**
         * UTC date in YYYY-MM-DD format. The day to get artifact activity for. Data is typically
         * available with a 1-day lag (varies by query; the error for a too-recent date names the
         * latest available day) and may be revised by a few percent over the following days. No
         * earlier than 2026-01-01.
         */
        fun date(date: LocalDate) = apply { this.date = date }

        /**
         * Filters as `dimension:value`, e.g. `filter[]=rbac_group_id:{id}`. Repeat the param for OR
         * within a dimension and across dimensions for AND. Supported dimensions on this endpoint:
         * `artifact_type`, `is_shared`, `product`, `rbac_group_id`, `user_id`. Value forms:
         * `artifact_type` is a canonical artifact MIME type (e.g. `text/markdown`) or `other`;
         * `is_shared` is `true` or `false`; `product` is `chat`, `claude_code`, or `cowork` (the
         * surfaces that create artifacts); `rbac_group_id` takes the tagged id (`rbac_group_...`,
         * as emitted in responses and by the spend-limits API) or a bare group UUID, and matches
         * users who held the group at any point during each covered UTC day (time-of-usage
         * attribution); `user_id` takes a tagged user id (`user_...`), as emitted in responses. An
         * unsupported dimension returns 400. At most 100 entries.
         */
        fun filter(filter: List<String>?) = apply { this.filter = filter?.toMutableList() }

        /** Alias for calling [Builder.filter] with `filter.orElse(null)`. */
        fun filter(filter: Optional<List<String>>) = filter(filter.getOrNull())

        /**
         * Adds a single [String] to [Builder.filter].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addFilter(filter: String) = apply {
            this.filter = (this.filter ?: mutableListOf()).apply { add(filter) }
        }

        /**
         * Dimensions to break results out by: `product`, `user_id` and/or `rbac_group_id`. The
         * ungrouped artifact-type cube is finite and returned in full; grouped queries multiply the
         * cube and paginate via `next_page`. `product` takes the values `chat`, `claude_code`, or
         * `cowork` (the surfaces that create artifacts). `rbac_group_id` attributes a user to every
         * group they held at any point during the requested UTC day, so grouped rows are not an
         * exclusive partition. At most 100 entries.
         */
        fun groupBy(groupBy: List<GroupBy>?) = apply { this.groupBy = groupBy?.toMutableList() }

        /** Alias for calling [Builder.groupBy] with `groupBy.orElse(null)`. */
        fun groupBy(groupBy: Optional<List<GroupBy>>) = groupBy(groupBy.getOrNull())

        /**
         * Adds a single [GroupBy] to [Builder.groupBy].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addGroupBy(groupBy: GroupBy) = apply {
            this.groupBy = (this.groupBy ?: mutableListOf()).apply { add(groupBy) }
        }

        /**
         * Maximum rows to return (1-1000, default 100). The ungrouped artifact-type cube is finite
         * and returned in full; `limit` is the page size only when `group_by[]` multiplies the
         * cube.
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

        /**
         * Opaque cursor from a previous response's `next_page` field. Only valid with `group_by[]`
         * — the ungrouped cube is never paginated.
         */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

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
         * Returns an immutable instance of [ArtifactListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .date()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ArtifactListParams =
            ArtifactListParams(
                checkRequired("date", date),
                filter?.toImmutable(),
                groupBy?.toImmutable(),
                limit,
                page,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                put("date", date.toString())
                filter?.forEach { put("filter[]", it) }
                groupBy?.forEach { put("group_by[]", it.toString()) }
                limit?.let { put("limit", it.toString()) }
                page?.let { put("page", it) }
                putAll(additionalQueryParams)
            }
            .build()

    class GroupBy private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val PRODUCT = GroupBy(JsonField.of("product"))

            @JvmField val RBAC_GROUP_ID = GroupBy(JsonField.of("rbac_group_id"))

            @JvmField val USER_ID = GroupBy(JsonField.of("user_id"))

            @JvmStatic
            fun of(value: String): GroupBy =
                // Intern known values so `==` works
                when (value) {
                    "product" -> PRODUCT
                    "rbac_group_id" -> RBAC_GROUP_ID
                    "user_id" -> USER_ID
                    else -> GroupBy(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): GroupBy =
                value.asString().getOrNull()?.let { of(it) } ?: GroupBy(value)
        }

        /** An enum containing [GroupBy]'s known values. */
        enum class Known {
            PRODUCT,
            RBAC_GROUP_ID,
            USER_ID,
        }

        /**
         * An enum containing [GroupBy]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [GroupBy] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            PRODUCT,
            RBAC_GROUP_ID,
            USER_ID,
            /** An enum member indicating that [GroupBy] was instantiated with an unknown value. */
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
                PRODUCT -> Value.PRODUCT
                RBAC_GROUP_ID -> Value.RBAC_GROUP_ID
                USER_ID -> Value.USER_ID
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
                PRODUCT -> Known.PRODUCT
                RBAC_GROUP_ID -> Known.RBAC_GROUP_ID
                USER_ID -> Known.USER_ID
                else -> throw AnthropicInvalidDataException("Unknown GroupBy: $value")
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
        fun validate(): GroupBy = apply {
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

            return other is GroupBy && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ArtifactListParams &&
            date == other.date &&
            filter == other.filter &&
            groupBy == other.groupBy &&
            limit == other.limit &&
            page == other.page &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(date, filter, groupBy, limit, page, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "ArtifactListParams{date=$date, filter=$filter, groupBy=$groupBy, limit=$limit, page=$page, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
