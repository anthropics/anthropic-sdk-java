package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * List the RBAC Groups that have Claude Code managed settings, highest precedence first.
 *
 * Each entry gives the group's status and timestamps, not its settings. The list includes deleted
 * groups that still have settings, and leaves out the default settings. If the order changes
 * between pages, the request returns `409`: list again from the first page.
 *
 * **Accepted credentials:** an organization-level Admin API key with the
 * `read:claude_code_managed_settings` scope.
 *
 * Every request must include the beta header `anthropic-beta:
 * ce-claude-code-managed-settings-2026-10-06`. A request without it, or from an organization not
 * enabled for this beta, returns `404`. The Claude Code Managed Settings API is in beta and is
 * available to Claude Enterprise organizations only.
 */
class RbacGroupListParams
private constructor(
    private val limit: Long?,
    private val page: String?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * Number of items to return per page.
     *
     * Defaults to `20`. Ranges from `1` to `100`.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /** Optionally set to the `next_page` token from the previous response. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /**
     * This endpoint is in beta: requests must send `ce-claude-code-managed-settings-2026-10-06` in
     * this header.
     */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): RbacGroupListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [RbacGroupListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [RbacGroupListParams]. */
    class Builder internal constructor() {

        private var limit: Long? = null
        private var page: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(rbacGroupListParams: RbacGroupListParams) = apply {
            limit = rbacGroupListParams.limit
            page = rbacGroupListParams.page
            betas = rbacGroupListParams.betas?.toMutableList()
            additionalHeaders = rbacGroupListParams.additionalHeaders.toBuilder()
            additionalQueryParams = rbacGroupListParams.additionalQueryParams.toBuilder()
        }

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

        /** Optionally set to the `next_page` token from the previous response. */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /**
         * This endpoint is in beta: requests must send `ce-claude-code-managed-settings-2026-10-06`
         * in this header.
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
         * Returns an immutable instance of [RbacGroupListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): RbacGroupListParams =
            RbacGroupListParams(
                limit,
                page,
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
                                .let {
                                    it + (listOf("ce-claude-code-managed-settings-2026-10-06") - it)
                                }
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
                putAll(additionalQueryParams)
            }
            .build()

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.organization.claude_code.managed_settings.rbac_groups.list",
            route = "/v1/organizations/claude_code/managed_settings/rbac_groups",
            queryParams = listOf("limit", "page"),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RbacGroupListParams &&
            limit == other.limit &&
            page == other.page &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(limit, page, betas, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "RbacGroupListParams{limit=$limit, page=$page, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
