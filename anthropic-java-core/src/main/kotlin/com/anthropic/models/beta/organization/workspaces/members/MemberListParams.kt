// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.members

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** List Workspace Members */
class MemberListParams
private constructor(
    private val workspaceId: String?,
    private val afterId: String?,
    private val beforeId: String?,
    private val limit: Long?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** ID of the Workspace. */
    fun workspaceId(): Optional<String> = Optional.ofNullable(workspaceId)

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

    /**
     * Number of items to return per page.
     *
     * Defaults to `20`. Ranges from `1` to `1000`.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): MemberListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [MemberListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MemberListParams]. */
    class Builder internal constructor() {

        private var workspaceId: String? = null
        private var afterId: String? = null
        private var beforeId: String? = null
        private var limit: Long? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(memberListParams: MemberListParams) = apply {
            workspaceId = memberListParams.workspaceId
            afterId = memberListParams.afterId
            beforeId = memberListParams.beforeId
            limit = memberListParams.limit
            additionalHeaders = memberListParams.additionalHeaders.toBuilder()
            additionalQueryParams = memberListParams.additionalQueryParams.toBuilder()
        }

        /** ID of the Workspace. */
        fun workspaceId(workspaceId: String?) = apply { this.workspaceId = workspaceId }

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

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
         * Returns an immutable instance of [MemberListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): MemberListParams =
            MemberListParams(
                workspaceId,
                afterId,
                beforeId,
                limit,
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
                afterId?.let { put("after_id", it) }
                beforeId?.let { put("before_id", it) }
                limit?.let { put("limit", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MemberListParams &&
            workspaceId == other.workspaceId &&
            afterId == other.afterId &&
            beforeId == other.beforeId &&
            limit == other.limit &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            workspaceId,
            afterId,
            beforeId,
            limit,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "MemberListParams{workspaceId=$workspaceId, afterId=$afterId, beforeId=$beforeId, limit=$limit, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
