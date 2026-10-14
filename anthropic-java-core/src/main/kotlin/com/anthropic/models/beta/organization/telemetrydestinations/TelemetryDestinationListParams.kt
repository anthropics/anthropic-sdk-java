package com.anthropic.models.beta.organization.telemetrydestinations

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
 * List the organization's destinations, newest first, optionally only those that cover given
 * workspaces.
 *
 * See the
 * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#set-up-api-tracing)
 * to learn more about destinations.
 */
class TelemetryDestinationListParams
private constructor(
    private val limit: Int?,
    private val page: String?,
    private val workspaceIds: List<String>?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Number of destinations to return per page, between 1 and 1,000. Defaults to 20. */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /** Opaque cursor from a previous response's `next_page`. Leave it out to get the first page. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /**
     * Return only the destinations that cover at least one of these workspaces (`wrkspc_...`).
     *
     * Send at most 100 IDs, as `workspace_ids[]`. A destination covers a workspace when its
     * `workspaces.type` is `all`, when its `include` list has the workspace, or when its `exclude`
     * list doesn't have it. Destinations in either state can match. An ID that isn't the ID of one
     * of the organization's workspaces doesn't match any destination, not even one whose type is
     * `all`.
     */
    fun workspaceIds(): Optional<List<String>> = Optional.ofNullable(workspaceIds)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): TelemetryDestinationListParams = builder().build()

        /**
         * Returns a mutable builder for constructing an instance of
         * [TelemetryDestinationListParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TelemetryDestinationListParams]. */
    class Builder internal constructor() {

        private var limit: Int? = null
        private var page: String? = null
        private var workspaceIds: MutableList<String>? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(telemetryDestinationListParams: TelemetryDestinationListParams) = apply {
            limit = telemetryDestinationListParams.limit
            page = telemetryDestinationListParams.page
            workspaceIds = telemetryDestinationListParams.workspaceIds?.toMutableList()
            betas = telemetryDestinationListParams.betas?.toMutableList()
            additionalHeaders = telemetryDestinationListParams.additionalHeaders.toBuilder()
            additionalQueryParams = telemetryDestinationListParams.additionalQueryParams.toBuilder()
        }

        /** Number of destinations to return per page, between 1 and 1,000. Defaults to 20. */
        fun limit(limit: Int?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Int) = limit(limit as Int?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Int>) = limit(limit.getOrNull())

        /**
         * Opaque cursor from a previous response's `next_page`. Leave it out to get the first page.
         */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /**
         * Return only the destinations that cover at least one of these workspaces (`wrkspc_...`).
         *
         * Send at most 100 IDs, as `workspace_ids[]`. A destination covers a workspace when its
         * `workspaces.type` is `all`, when its `include` list has the workspace, or when its
         * `exclude` list doesn't have it. Destinations in either state can match. An ID that isn't
         * the ID of one of the organization's workspaces doesn't match any destination, not even
         * one whose type is `all`.
         */
        fun workspaceIds(workspaceIds: List<String>?) = apply {
            this.workspaceIds = workspaceIds?.toMutableList()
        }

        /** Alias for calling [Builder.workspaceIds] with `workspaceIds.orElse(null)`. */
        fun workspaceIds(workspaceIds: Optional<List<String>>) =
            workspaceIds(workspaceIds.getOrNull())

        /**
         * Adds a single [String] to [workspaceIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addWorkspaceId(workspaceId: String) = apply {
            workspaceIds = (workspaceIds ?: mutableListOf()).apply { add(workspaceId) }
        }

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
         * Returns an immutable instance of [TelemetryDestinationListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): TelemetryDestinationListParams =
            TelemetryDestinationListParams(
                limit,
                page,
                workspaceIds?.toImmutable(),
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
                                .let { it + (listOf("telemetry-destinations-2026-08-11") - it) }
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
                workspaceIds?.forEach { put("workspace_ids[]", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.organization.telemetry_destinations.list",
            route = "/v1/organizations/telemetry_destinations",
            queryParams = listOf("limit", "page", "workspace_ids"),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TelemetryDestinationListParams &&
            limit == other.limit &&
            page == other.page &&
            workspaceIds == other.workspaceIds &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(limit, page, workspaceIds, betas, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "TelemetryDestinationListParams{limit=$limit, page=$page, workspaceIds=$workspaceIds, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
