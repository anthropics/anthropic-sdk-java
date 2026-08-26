// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.ratelimits

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * List rate-limit overrides configured for a workspace.
 *
 * Returns only the groups and limiter types that have a workspace-level override. Groups without
 * overrides inherit the organization limits and are not listed; use `GET
 * /v1/organizations/rate_limits` to see those.
 *
 * This endpoint currently returns every matching entry in a single page regardless of `limit`;
 * follow `next_page` so that clients keep working when pagination is enabled.
 */
class RateLimitListParams
private constructor(
    private val workspaceId: String?,
    private val groupType: GroupType?,
    private val limit: Long?,
    private val page: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** The ID of the workspace. */
    fun workspaceId(): Optional<String> = Optional.ofNullable(workspaceId)

    /** Filter by group type. */
    fun groupType(): Optional<GroupType> = Optional.ofNullable(groupType)

    /**
     * Maximum number of items to return per page. Ranges from `1` to `1000`.
     *
     * Accepted for request-shape compatibility and currently ignored: every entry is returned in a
     * single page.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /** Opaque cursor from a previous response's `next_page`. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): RateLimitListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [RateLimitListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [RateLimitListParams]. */
    class Builder internal constructor() {

        private var workspaceId: String? = null
        private var groupType: GroupType? = null
        private var limit: Long? = null
        private var page: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(rateLimitListParams: RateLimitListParams) = apply {
            workspaceId = rateLimitListParams.workspaceId
            groupType = rateLimitListParams.groupType
            limit = rateLimitListParams.limit
            page = rateLimitListParams.page
            additionalHeaders = rateLimitListParams.additionalHeaders.toBuilder()
            additionalQueryParams = rateLimitListParams.additionalQueryParams.toBuilder()
        }

        /** The ID of the workspace. */
        fun workspaceId(workspaceId: String?) = apply { this.workspaceId = workspaceId }

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

        /** Filter by group type. */
        fun groupType(groupType: GroupType?) = apply { this.groupType = groupType }

        /** Alias for calling [Builder.groupType] with `groupType.orElse(null)`. */
        fun groupType(groupType: Optional<GroupType>) = groupType(groupType.getOrNull())

        /**
         * Maximum number of items to return per page. Ranges from `1` to `1000`.
         *
         * Accepted for request-shape compatibility and currently ignored: every entry is returned
         * in a single page.
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

        /** Opaque cursor from a previous response's `next_page`. */
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
         * Returns an immutable instance of [RateLimitListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): RateLimitListParams =
            RateLimitListParams(
                workspaceId,
                groupType,
                limit,
                page,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> workspaceId ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                groupType?.let { put("group_type", it.toString()) }
                limit?.let { put("limit", it.toString()) }
                page?.let { put("page", it) }
                putAll(additionalQueryParams)
            }
            .build()

    /** Filter by group type. */
    class GroupType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val BATCH = of("batch")

            @JvmField val FILES = of("files")

            @JvmField val MODEL_GROUP = of("model_group")

            @JvmField val SKILLS = of("skills")

            @JvmField val TOKEN_COUNT = of("token_count")

            @JvmField val WEB_SEARCH = of("web_search")

            @JvmStatic fun of(value: String) = GroupType(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): GroupType =
                value.asString().getOrNull()?.let { of(it) } ?: GroupType(value)
        }

        /** An enum containing [GroupType]'s known values. */
        enum class Known {
            BATCH,
            FILES,
            MODEL_GROUP,
            SKILLS,
            TOKEN_COUNT,
            WEB_SEARCH,
        }

        /**
         * An enum containing [GroupType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [GroupType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            BATCH,
            FILES,
            MODEL_GROUP,
            SKILLS,
            TOKEN_COUNT,
            WEB_SEARCH,
            /**
             * An enum member indicating that [GroupType] was instantiated with an unknown value.
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
                BATCH -> Value.BATCH
                FILES -> Value.FILES
                MODEL_GROUP -> Value.MODEL_GROUP
                SKILLS -> Value.SKILLS
                TOKEN_COUNT -> Value.TOKEN_COUNT
                WEB_SEARCH -> Value.WEB_SEARCH
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
                BATCH -> Known.BATCH
                FILES -> Known.FILES
                MODEL_GROUP -> Known.MODEL_GROUP
                SKILLS -> Known.SKILLS
                TOKEN_COUNT -> Known.TOKEN_COUNT
                WEB_SEARCH -> Known.WEB_SEARCH
                else -> throw AnthropicInvalidDataException("Unknown GroupType: $value")
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
        fun validate(): GroupType = apply {
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

            return other is GroupType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RateLimitListParams &&
            workspaceId == other.workspaceId &&
            groupType == other.groupType &&
            limit == other.limit &&
            page == other.page &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(workspaceId, groupType, limit, page, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "RateLimitListParams{workspaceId=$workspaceId, groupType=$groupType, limit=$limit, page=$page, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
