package com.anthropic.models.beta.organization.claudetag.bundles.targets

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
 * Attach a Bundle to a Slack channel or a Slack workspace. A Bundle attached to a workspace can be
 * used in the workspace and in every channel in it. Attaching a Bundle to a channel or workspace it
 * is already attached to returns the existing Bundle Target. The channel or workspace must be set
 * up: attaching never sets a channel up. A group direct message takes no Bundle and returns 400.
 */
class TargetCreateParams
private constructor(
    private val bundleId: String?,
    private val betas: List<AnthropicBeta>?,
    private val target: BetaClaudeTagBundleTargetCreate,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** The Bundle's identifier, starting with `capp_`. */
    fun bundleId(): Optional<String> = Optional.ofNullable(bundleId)

    /**
     * This endpoint is in beta: requests must send `experimental-claude-tag-2026-10-21` in this
     * header.
     */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    fun target(): BetaClaudeTagBundleTargetCreate = target

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [TargetCreateParams].
         *
         * The following fields are required:
         * ```java
         * .target()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TargetCreateParams]. */
    class Builder internal constructor() {

        private var bundleId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var target: BetaClaudeTagBundleTargetCreate? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(targetCreateParams: TargetCreateParams) = apply {
            bundleId = targetCreateParams.bundleId
            betas = targetCreateParams.betas?.toMutableList()
            target = targetCreateParams.target
            additionalHeaders = targetCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = targetCreateParams.additionalQueryParams.toBuilder()
        }

        /** The Bundle's identifier, starting with `capp_`. */
        fun bundleId(bundleId: String?) = apply { this.bundleId = bundleId }

        /** Alias for calling [Builder.bundleId] with `bundleId.orElse(null)`. */
        fun bundleId(bundleId: Optional<String>) = bundleId(bundleId.getOrNull())

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

        fun target(target: BetaClaudeTagBundleTargetCreate) = apply { this.target = target }

        /**
         * Alias for calling [target] with
         * `BetaClaudeTagBundleTargetCreate.ofSlackChannel(slackChannel)`.
         */
        fun target(slackChannel: BetaClaudeTagSlackChannelBundleTargetCreate) =
            target(BetaClaudeTagBundleTargetCreate.ofSlackChannel(slackChannel))

        /**
         * Alias for calling [target] with the following:
         * ```java
         * BetaClaudeTagSlackChannelBundleTargetCreate.builder()
         *     .slackChannelId(slackChannelId)
         *     .build()
         * ```
         */
        fun slackChannelTarget(slackChannelId: String) =
            target(
                BetaClaudeTagSlackChannelBundleTargetCreate.builder()
                    .slackChannelId(slackChannelId)
                    .build()
            )

        /**
         * Alias for calling [target] with
         * `BetaClaudeTagBundleTargetCreate.ofSlackWorkspace(slackWorkspace)`.
         */
        fun target(slackWorkspace: BetaClaudeTagSlackWorkspaceBundleTargetCreate) =
            target(BetaClaudeTagBundleTargetCreate.ofSlackWorkspace(slackWorkspace))

        /**
         * Alias for calling [target] with the following:
         * ```java
         * BetaClaudeTagSlackWorkspaceBundleTargetCreate.builder()
         *     .slackWorkspaceId(slackWorkspaceId)
         *     .build()
         * ```
         */
        fun slackWorkspaceTarget(slackWorkspaceId: String) =
            target(
                BetaClaudeTagSlackWorkspaceBundleTargetCreate.builder()
                    .slackWorkspaceId(slackWorkspaceId)
                    .build()
            )

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
         * Returns an immutable instance of [TargetCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .target()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): TargetCreateParams =
            TargetCreateParams(
                bundleId,
                betas?.toImmutable(),
                checkRequired("target", target),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): BetaClaudeTagBundleTargetCreate = target

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> bundleId ?: ""
            else -> ""
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

    override fun _queryParams(): QueryParams = additionalQueryParams

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.organization.claude_tag.bundles.targets.create",
            route = "/v1/organizations/claude_tag/bundles/{bundle_id}/targets",
            attributes = mapOf("anthropic.organization.claude_tag.bundle.id" to _pathParam(0)),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TargetCreateParams &&
            bundleId == other.bundleId &&
            betas == other.betas &&
            target == other.target &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(bundleId, betas, target, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "TargetCreateParams{bundleId=$bundleId, betas=$betas, target=$target, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
