// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.users

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** List the organization's members. */
class UserListParams
private constructor(
    private val afterId: String?,
    private val beforeId: String?,
    private val email: String?,
    private val limit: Long?,
    private val roles: List<String>?,
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

    /** Filter by user email. */
    fun email(): Optional<String> = Optional.ofNullable(email)

    /**
     * Number of items to return per page.
     *
     * Defaults to `20`. Ranges from `1` to `1000`.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /**
     * Filter to items whose `role` equals one of the supplied values. Repeatable; values are OR'ed
     * together.
     *
     * Accepted values depend on the organization type: Console and API organizations accept `user`,
     * `developer`, `billing`, `admin`, and `claude_code_user`; Claude Enterprise organizations
     * accept `user`, `owner`, `primary_owner`, `membership_admin`, and `managed`.
     */
    fun roles(): Optional<List<String>> = Optional.ofNullable(roles)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): UserListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [UserListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserListParams]. */
    class Builder internal constructor() {

        private var afterId: String? = null
        private var beforeId: String? = null
        private var email: String? = null
        private var limit: Long? = null
        private var roles: MutableList<String>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(userListParams: UserListParams) = apply {
            afterId = userListParams.afterId
            beforeId = userListParams.beforeId
            email = userListParams.email
            limit = userListParams.limit
            roles = userListParams.roles?.toMutableList()
            additionalHeaders = userListParams.additionalHeaders.toBuilder()
            additionalQueryParams = userListParams.additionalQueryParams.toBuilder()
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

        /** Filter by user email. */
        fun email(email: String?) = apply { this.email = email }

        /** Alias for calling [Builder.email] with `email.orElse(null)`. */
        fun email(email: Optional<String>) = email(email.getOrNull())

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

        /**
         * Filter to items whose `role` equals one of the supplied values. Repeatable; values are
         * OR'ed together.
         *
         * Accepted values depend on the organization type: Console and API organizations accept
         * `user`, `developer`, `billing`, `admin`, and `claude_code_user`; Claude Enterprise
         * organizations accept `user`, `owner`, `primary_owner`, `membership_admin`, and `managed`.
         */
        fun roles(roles: List<String>?) = apply { this.roles = roles?.toMutableList() }

        /** Alias for calling [Builder.roles] with `roles.orElse(null)`. */
        fun roles(roles: Optional<List<String>>) = roles(roles.getOrNull())

        /**
         * Adds a single [String] to [roles].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRole(role: String) = apply { roles = (roles ?: mutableListOf()).apply { add(role) } }

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
         * Returns an immutable instance of [UserListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): UserListParams =
            UserListParams(
                afterId,
                beforeId,
                email,
                limit,
                roles?.toImmutable(),
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
                email?.let { put("email", it) }
                limit?.let { put("limit", it.toString()) }
                roles?.forEach { put("roles[]", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserListParams &&
            afterId == other.afterId &&
            beforeId == other.beforeId &&
            email == other.email &&
            limit == other.limit &&
            roles == other.roles &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            afterId,
            beforeId,
            email,
            limit,
            roles,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "UserListParams{afterId=$afterId, beforeId=$beforeId, email=$email, limit=$limit, roles=$roles, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
