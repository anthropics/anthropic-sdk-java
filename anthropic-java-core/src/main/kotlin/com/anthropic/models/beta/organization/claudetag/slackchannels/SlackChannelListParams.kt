package com.anthropic.models.beta.organization.claudetag.slackchannels

import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * List the set-up Slack channels of one Slack workspace, newest first by `created_at`. A channel
 * that is not set up is not listed: Slack holds the full list of a workspace's channels. Pass
 * `slack_channel_id` as well to look one channel up by its Slack identifiers: `data` then holds
 * that channel, or is empty when it is not set up, and `limit` and `page` are ignored. A workspace
 * that the organization owns only through a Slack Enterprise Grid returns 404 until it is set up.
 * With `include_inherited=true` each channel's `settings` holds what applies in it, and the request
 * fails with 503 rather than resolve against a settings level that could not be read. A `page`
 * cursor resumes only a request with the same `include_inherited`.
 */
class SlackChannelListParams
private constructor(
    private val slackWorkspaceId: String,
    private val includeInherited: Boolean?,
    private val limit: Int?,
    private val page: String?,
    private val slackChannelId: String?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * The Slack workspace whose channels to list: Slack's identifier for it, as in the channel
     * object's `slack.workspace_id`, not the workspace object's `id`.
     */
    fun slackWorkspaceId(): String = slackWorkspaceId

    /**
     * When `true`, `settings` holds what applies in each channel once the settings levels above it
     * are taken into account, and every member of `sources` names the level a value comes from.
     * When `false` or left out, `settings` holds what each channel itself stores.
     */
    fun includeInherited(): Optional<Boolean> = Optional.ofNullable(includeInherited)

    /** Number of items to return per page, between 1 and 100. Defaults to 20. */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /** Opaque cursor from a previous response's `next_page`. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /**
     * Slack's identifier for one channel of that workspace, as in the channel object's
     * `slack.channel_id`. It is not the channel object's `id`. With it `data` holds that channel
     * alone, or nothing when the channel is not set up.
     */
    fun slackChannelId(): Optional<String> = Optional.ofNullable(slackChannelId)

    /**
     * This endpoint is in beta: requests must send `experimental-claude-tag-2026-10-21` in this
     * header.
     */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SlackChannelListParams].
         *
         * The following fields are required:
         * ```java
         * .slackWorkspaceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SlackChannelListParams]. */
    class Builder internal constructor() {

        private var slackWorkspaceId: String? = null
        private var includeInherited: Boolean? = null
        private var limit: Int? = null
        private var page: String? = null
        private var slackChannelId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(slackChannelListParams: SlackChannelListParams) = apply {
            slackWorkspaceId = slackChannelListParams.slackWorkspaceId
            includeInherited = slackChannelListParams.includeInherited
            limit = slackChannelListParams.limit
            page = slackChannelListParams.page
            slackChannelId = slackChannelListParams.slackChannelId
            betas = slackChannelListParams.betas?.toMutableList()
            additionalHeaders = slackChannelListParams.additionalHeaders.toBuilder()
            additionalQueryParams = slackChannelListParams.additionalQueryParams.toBuilder()
        }

        /**
         * The Slack workspace whose channels to list: Slack's identifier for it, as in the channel
         * object's `slack.workspace_id`, not the workspace object's `id`.
         */
        fun slackWorkspaceId(slackWorkspaceId: String) = apply {
            this.slackWorkspaceId = slackWorkspaceId
        }

        /**
         * When `true`, `settings` holds what applies in each channel once the settings levels above
         * it are taken into account, and every member of `sources` names the level a value comes
         * from. When `false` or left out, `settings` holds what each channel itself stores.
         */
        fun includeInherited(includeInherited: Boolean?) = apply {
            this.includeInherited = includeInherited
        }

        /**
         * Alias for [Builder.includeInherited].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun includeInherited(includeInherited: Boolean) =
            includeInherited(includeInherited as Boolean?)

        /** Alias for calling [Builder.includeInherited] with `includeInherited.orElse(null)`. */
        fun includeInherited(includeInherited: Optional<Boolean>) =
            includeInherited(includeInherited.getOrNull())

        /** Number of items to return per page, between 1 and 100. Defaults to 20. */
        fun limit(limit: Int?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Int) = limit(limit as Int?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Int>) = limit(limit.getOrNull())

        /** Opaque cursor from a previous response's `next_page`. */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /**
         * Slack's identifier for one channel of that workspace, as in the channel object's
         * `slack.channel_id`. It is not the channel object's `id`. With it `data` holds that
         * channel alone, or nothing when the channel is not set up.
         */
        fun slackChannelId(slackChannelId: String?) = apply { this.slackChannelId = slackChannelId }

        /** Alias for calling [Builder.slackChannelId] with `slackChannelId.orElse(null)`. */
        fun slackChannelId(slackChannelId: Optional<String>) =
            slackChannelId(slackChannelId.getOrNull())

        /**
         * This endpoint is in beta: requests must send `experimental-claude-tag-2026-10-21` in this
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
         * Returns an immutable instance of [SlackChannelListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .slackWorkspaceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SlackChannelListParams =
            SlackChannelListParams(
                checkRequired("slackWorkspaceId", slackWorkspaceId),
                includeInherited,
                limit,
                page,
                slackChannelId,
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
                                .let { it + (listOf("experimental-claude-tag-2026-10-21") - it) }
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
                put("slack_workspace_id", slackWorkspaceId)
                includeInherited?.let { put("include_inherited", it.toString()) }
                limit?.let { put("limit", it.toString()) }
                page?.let { put("page", it) }
                slackChannelId?.let { put("slack_channel_id", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.organization.claude_tag.slack_channels.list",
            route = "/v1/organizations/claude_tag/slack_channels",
            queryParams =
                listOf(
                    "include_inherited",
                    "limit",
                    "page",
                    "slack_channel_id",
                    "slack_workspace_id",
                ),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SlackChannelListParams &&
            slackWorkspaceId == other.slackWorkspaceId &&
            includeInherited == other.includeInherited &&
            limit == other.limit &&
            page == other.page &&
            slackChannelId == other.slackChannelId &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            slackWorkspaceId,
            includeInherited,
            limit,
            page,
            slackChannelId,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "SlackChannelListParams{slackWorkspaceId=$slackWorkspaceId, includeInherited=$includeInherited, limit=$limit, page=$page, slackChannelId=$slackChannelId, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
