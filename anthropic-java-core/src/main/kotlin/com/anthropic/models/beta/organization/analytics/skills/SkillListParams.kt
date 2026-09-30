package com.anthropic.models.beta.organization.analytics.skills

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.Params
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
 * Get per-skill usage for a given day, with cursor-based pagination.
 *
 * Returns skill usage metrics for the organization, sorted by skill name. Use `group_by[]` to break
 * usage out per member, per RBAC group, or per product surface, and `filter[]` to scope results;
 * the parameter descriptions list the supported dimensions. Available to organizations on a Claude
 * Enterprise plan. Requires an API key with the `read:analytics` scope.
 */
class SkillListParams
private constructor(
    private val date: LocalDate?,
    private val endingDate: LocalDate?,
    private val filter: List<String>?,
    private val groupBy: List<GroupBy>?,
    private val limit: Long?,
    private val order: Order?,
    private val orderBy: String?,
    private val page: String?,
    private val startingDate: LocalDate?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * UTC date in YYYY-MM-DD format. The day to get skill usage for. Data is typically available
     * with a 1-day lag (varies by query; the error for a too-recent date names the latest available
     * day) and may be revised by a few percent over the following days. No earlier than 2026-01-01.
     */
    fun date(): Optional<LocalDate> = Optional.ofNullable(date)

    /**
     * UTC date in YYYY-MM-DD format. End of the date range (exclusive); only valid with
     * `starting_date`. Data is typically available with a 1-day lag (varies by query; the error for
     * a too-recent date names the latest available day), so this can be at most today — which is
     * also the default when omitted, resolved once when the first page is served and reused for the
     * rest of the pagination sequence. At most 366 days after `starting_date`.
     */
    fun endingDate(): Optional<LocalDate> = Optional.ofNullable(endingDate)

    /**
     * Filters as `dimension:value`, e.g. `filter[]=rbac_group_id:{id}`. Repeat the param for OR
     * within a dimension and across dimensions for AND. Supported dimensions on this endpoint:
     * `product`, `rbac_group_id`, `share_status`, `skill_name`, `user_id`. Value forms: `product`
     * is one of `chat`, `claude_code`, `cowork`, or `office_agent`; `rbac_group_id` takes the
     * tagged id (`rbac_group_...`, as emitted in responses and by the spend-limits API) or a bare
     * group UUID, and matches users who held the group at any point during each covered UTC day
     * (time-of-usage attribution); `share_status` is one of `organization`, `private`, or `public`;
     * `skill_name` matches case-insensitively; `user_id` takes a tagged user id (`user_...`), as
     * emitted in responses. An unsupported dimension returns 400. At most 100 entries.
     */
    fun filter(): Optional<List<String>> = Optional.ofNullable(filter)

    /**
     * Dimensions to break results out by (e.g. `group_by[]=user_id`). Supported on this endpoint:
     * `product`, `rbac_group_id`, `user_id`. Grouped rows carry the requested dimension values as
     * additional fields and paginate like ungrouped responses via `next_page`; an unsupported
     * dimension returns 400. `rbac_group_id` attributes a user to every group they held at any
     * point during each covered UTC day, so grouped rows are not an exclusive partition and can sum
     * above org-level totals. At most 100 entries.
     */
    fun groupBy(): Optional<List<GroupBy>> = Optional.ofNullable(groupBy)

    /** Number of results per page (1-1000, default 100). */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /**
     * Sort direction: `asc` or `desc`. Defaults to `asc` for the endpoint's sort column and to
     * `desc` when `order_by` names a metric (a top-N ranking). Applies to `order_by`, or to the
     * endpoint's default sort field when `order_by` is omitted.
     */
    fun order(): Optional<Order> = Optional.ofNullable(order)

    /**
     * Sort field. Restricted to the endpoint's sort column plus its rankable metrics (metrics
     * default to descending; a few metrics rank in date-range mode only, per the endpoint's
     * documented orderable set).
     */
    fun orderBy(): Optional<String> = Optional.ofNullable(orderBy)

    /** Opaque cursor from a previous response's `next_page` field. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /**
     * UTC date in YYYY-MM-DD format. Start of a date range (inclusive). Enables rollup mode: one
     * row per entity aggregated over the whole range — addable counters are summed across days, and
     * a distinct count is never summed where summing could double-count (a field's range value is
     * recomputed exactly over the window, approximate via HLL with typical error under 2%, null, or
     * — for the creation-event counts, whose per-day values cannot overlap — a per-day sum that is
     * itself exact; each field's own description says which). Use either `date` or `starting_date`,
     * not both. Data is typically available with a 1-day lag (varies by query; the error for a
     * too-recent date names the latest available day) and may be revised by a few percent over the
     * following days. No earlier than 2026-01-01.
     */
    fun startingDate(): Optional<LocalDate> = Optional.ofNullable(startingDate)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): SkillListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [SkillListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SkillListParams]. */
    class Builder internal constructor() {

        private var date: LocalDate? = null
        private var endingDate: LocalDate? = null
        private var filter: MutableList<String>? = null
        private var groupBy: MutableList<GroupBy>? = null
        private var limit: Long? = null
        private var order: Order? = null
        private var orderBy: String? = null
        private var page: String? = null
        private var startingDate: LocalDate? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(skillListParams: SkillListParams) = apply {
            date = skillListParams.date
            endingDate = skillListParams.endingDate
            filter = skillListParams.filter?.toMutableList()
            groupBy = skillListParams.groupBy?.toMutableList()
            limit = skillListParams.limit
            order = skillListParams.order
            orderBy = skillListParams.orderBy
            page = skillListParams.page
            startingDate = skillListParams.startingDate
            additionalHeaders = skillListParams.additionalHeaders.toBuilder()
            additionalQueryParams = skillListParams.additionalQueryParams.toBuilder()
        }

        /**
         * UTC date in YYYY-MM-DD format. The day to get skill usage for. Data is typically
         * available with a 1-day lag (varies by query; the error for a too-recent date names the
         * latest available day) and may be revised by a few percent over the following days. No
         * earlier than 2026-01-01.
         */
        fun date(date: LocalDate?) = apply { this.date = date }

        /** Alias for calling [Builder.date] with `date.orElse(null)`. */
        fun date(date: Optional<LocalDate>) = date(date.getOrNull())

        /**
         * UTC date in YYYY-MM-DD format. End of the date range (exclusive); only valid with
         * `starting_date`. Data is typically available with a 1-day lag (varies by query; the error
         * for a too-recent date names the latest available day), so this can be at most today —
         * which is also the default when omitted, resolved once when the first page is served and
         * reused for the rest of the pagination sequence. At most 366 days after `starting_date`.
         */
        fun endingDate(endingDate: LocalDate?) = apply { this.endingDate = endingDate }

        /** Alias for calling [Builder.endingDate] with `endingDate.orElse(null)`. */
        fun endingDate(endingDate: Optional<LocalDate>) = endingDate(endingDate.getOrNull())

        /**
         * Filters as `dimension:value`, e.g. `filter[]=rbac_group_id:{id}`. Repeat the param for OR
         * within a dimension and across dimensions for AND. Supported dimensions on this endpoint:
         * `product`, `rbac_group_id`, `share_status`, `skill_name`, `user_id`. Value forms:
         * `product` is one of `chat`, `claude_code`, `cowork`, or `office_agent`; `rbac_group_id`
         * takes the tagged id (`rbac_group_...`, as emitted in responses and by the spend-limits
         * API) or a bare group UUID, and matches users who held the group at any point during each
         * covered UTC day (time-of-usage attribution); `share_status` is one of `organization`,
         * `private`, or `public`; `skill_name` matches case-insensitively; `user_id` takes a tagged
         * user id (`user_...`), as emitted in responses. An unsupported dimension returns 400. At
         * most 100 entries.
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
         * Dimensions to break results out by (e.g. `group_by[]=user_id`). Supported on this
         * endpoint: `product`, `rbac_group_id`, `user_id`. Grouped rows carry the requested
         * dimension values as additional fields and paginate like ungrouped responses via
         * `next_page`; an unsupported dimension returns 400. `rbac_group_id` attributes a user to
         * every group they held at any point during each covered UTC day, so grouped rows are not
         * an exclusive partition and can sum above org-level totals. At most 100 entries.
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

        /** Number of results per page (1-1000, default 100). */
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
         * Sort direction: `asc` or `desc`. Defaults to `asc` for the endpoint's sort column and to
         * `desc` when `order_by` names a metric (a top-N ranking). Applies to `order_by`, or to the
         * endpoint's default sort field when `order_by` is omitted.
         */
        fun order(order: Order?) = apply { this.order = order }

        /** Alias for calling [Builder.order] with `order.orElse(null)`. */
        fun order(order: Optional<Order>) = order(order.getOrNull())

        /**
         * Sort field. Restricted to the endpoint's sort column plus its rankable metrics (metrics
         * default to descending; a few metrics rank in date-range mode only, per the endpoint's
         * documented orderable set).
         */
        fun orderBy(orderBy: String?) = apply { this.orderBy = orderBy }

        /** Alias for calling [Builder.orderBy] with `orderBy.orElse(null)`. */
        fun orderBy(orderBy: Optional<String>) = orderBy(orderBy.getOrNull())

        /** Opaque cursor from a previous response's `next_page` field. */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /**
         * UTC date in YYYY-MM-DD format. Start of a date range (inclusive). Enables rollup mode:
         * one row per entity aggregated over the whole range — addable counters are summed across
         * days, and a distinct count is never summed where summing could double-count (a field's
         * range value is recomputed exactly over the window, approximate via HLL with typical error
         * under 2%, null, or — for the creation-event counts, whose per-day values cannot overlap —
         * a per-day sum that is itself exact; each field's own description says which). Use either
         * `date` or `starting_date`, not both. Data is typically available with a 1-day lag (varies
         * by query; the error for a too-recent date names the latest available day) and may be
         * revised by a few percent over the following days. No earlier than 2026-01-01.
         */
        fun startingDate(startingDate: LocalDate?) = apply { this.startingDate = startingDate }

        /** Alias for calling [Builder.startingDate] with `startingDate.orElse(null)`. */
        fun startingDate(startingDate: Optional<LocalDate>) = startingDate(startingDate.getOrNull())

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
         * Returns an immutable instance of [SkillListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): SkillListParams =
            SkillListParams(
                date,
                endingDate,
                filter?.toImmutable(),
                groupBy?.toImmutable(),
                limit,
                order,
                orderBy,
                page,
                startingDate,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                date?.let { put("date", it.toString()) }
                endingDate?.let { put("ending_date", it.toString()) }
                filter?.forEach { put("filter[]", it) }
                groupBy?.forEach { put("group_by[]", it.toString()) }
                limit?.let { put("limit", it.toString()) }
                order?.let { put("order", it.toString()) }
                orderBy?.let { put("order_by", it) }
                page?.let { put("page", it) }
                startingDate?.let { put("starting_date", it.toString()) }
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

    /**
     * Sort direction: `asc` or `desc`. Defaults to `asc` for the endpoint's sort column and to
     * `desc` when `order_by` names a metric (a top-N ranking). Applies to `order_by`, or to the
     * endpoint's default sort field when `order_by` is omitted.
     */
    class Order private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val ASC = Order(JsonField.of("asc"))

            @JvmField val DESC = Order(JsonField.of("desc"))

            @JvmStatic
            fun of(value: String): Order =
                // Intern known values so `==` works
                when (value) {
                    "asc" -> ASC
                    "desc" -> DESC
                    else -> Order(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Order =
                value.asString().getOrNull()?.let { of(it) } ?: Order(value)
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

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
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

        return other is SkillListParams &&
            date == other.date &&
            endingDate == other.endingDate &&
            filter == other.filter &&
            groupBy == other.groupBy &&
            limit == other.limit &&
            order == other.order &&
            orderBy == other.orderBy &&
            page == other.page &&
            startingDate == other.startingDate &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            date,
            endingDate,
            filter,
            groupBy,
            limit,
            order,
            orderBy,
            page,
            startingDate,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "SkillListParams{date=$date, endingDate=$endingDate, filter=$filter, groupBy=$groupBy, limit=$limit, order=$order, orderBy=$orderBy, page=$page, startingDate=$startingDate, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
