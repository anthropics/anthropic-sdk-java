// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

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

/** List API Keys */
class ApiKeyListParams
private constructor(
    private val afterId: String?,
    private val beforeId: String?,
    private val createdByUserId: String?,
    private val limit: Long?,
    private val status: Status?,
    private val workspaceId: String?,
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

    /** Filter by the ID of the User who created the object. */
    fun createdByUserId(): Optional<String> = Optional.ofNullable(createdByUserId)

    /**
     * Number of items to return per page.
     *
     * Defaults to `20`. Ranges from `1` to `1000`.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /** Filter by API key status. */
    fun status(): Optional<Status> = Optional.ofNullable(status)

    /** Filter by Workspace ID. */
    fun workspaceId(): Optional<String> = Optional.ofNullable(workspaceId)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): ApiKeyListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [ApiKeyListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ApiKeyListParams]. */
    class Builder internal constructor() {

        private var afterId: String? = null
        private var beforeId: String? = null
        private var createdByUserId: String? = null
        private var limit: Long? = null
        private var status: Status? = null
        private var workspaceId: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(apiKeyListParams: ApiKeyListParams) = apply {
            afterId = apiKeyListParams.afterId
            beforeId = apiKeyListParams.beforeId
            createdByUserId = apiKeyListParams.createdByUserId
            limit = apiKeyListParams.limit
            status = apiKeyListParams.status
            workspaceId = apiKeyListParams.workspaceId
            additionalHeaders = apiKeyListParams.additionalHeaders.toBuilder()
            additionalQueryParams = apiKeyListParams.additionalQueryParams.toBuilder()
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

        /** Filter by the ID of the User who created the object. */
        fun createdByUserId(createdByUserId: String?) = apply {
            this.createdByUserId = createdByUserId
        }

        /** Alias for calling [Builder.createdByUserId] with `createdByUserId.orElse(null)`. */
        fun createdByUserId(createdByUserId: Optional<String>) =
            createdByUserId(createdByUserId.getOrNull())

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

        /** Filter by API key status. */
        fun status(status: Status?) = apply { this.status = status }

        /** Alias for calling [Builder.status] with `status.orElse(null)`. */
        fun status(status: Optional<Status>) = status(status.getOrNull())

        /** Filter by Workspace ID. */
        fun workspaceId(workspaceId: String?) = apply { this.workspaceId = workspaceId }

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

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
         * Returns an immutable instance of [ApiKeyListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ApiKeyListParams =
            ApiKeyListParams(
                afterId,
                beforeId,
                createdByUserId,
                limit,
                status,
                workspaceId,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                afterId?.let { put("after_id", it) }
                beforeId?.let { put("before_id", it) }
                createdByUserId?.let { put("created_by_user_id", it) }
                limit?.let { put("limit", it.toString()) }
                status?.let { put("status", it.toString()) }
                workspaceId?.let { put("workspace_id", it) }
                putAll(additionalQueryParams)
            }
            .build()

    /** Filter by API key status. */
    class Status @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val ACTIVE = of("active")

            @JvmField val ARCHIVED = of("archived")

            @JvmField val EXPIRED = of("expired")

            @JvmField val INACTIVE = of("inactive")

            @JvmStatic fun of(value: String) = Status(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Status =
                value.asString().getOrNull()?.let { of(it) } ?: Status(value)
        }

        /** An enum containing [Status]'s known values. */
        enum class Known {
            ACTIVE,
            ARCHIVED,
            EXPIRED,
            INACTIVE,
        }

        /**
         * An enum containing [Status]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Status] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ACTIVE,
            ARCHIVED,
            EXPIRED,
            INACTIVE,
            /** An enum member indicating that [Status] was instantiated with an unknown value. */
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
                ACTIVE -> Value.ACTIVE
                ARCHIVED -> Value.ARCHIVED
                EXPIRED -> Value.EXPIRED
                INACTIVE -> Value.INACTIVE
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
                ACTIVE -> Known.ACTIVE
                ARCHIVED -> Known.ARCHIVED
                EXPIRED -> Known.EXPIRED
                INACTIVE -> Known.INACTIVE
                else -> throw AnthropicInvalidDataException("Unknown Status: $value")
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
        fun validate(): Status = apply {
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

            return other is Status && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ApiKeyListParams &&
            afterId == other.afterId &&
            beforeId == other.beforeId &&
            createdByUserId == other.createdByUserId &&
            limit == other.limit &&
            status == other.status &&
            workspaceId == other.workspaceId &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            afterId,
            beforeId,
            createdByUserId,
            limit,
            status,
            workspaceId,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "ApiKeyListParams{afterId=$afterId, beforeId=$beforeId, createdByUserId=$createdByUserId, limit=$limit, status=$status, workspaceId=$workspaceId, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
