package com.anthropic.models.beta.organization.claudetag.slackchannels

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
 * Get one Slack channel and its settings. By default `settings` holds what the channel itself
 * stores: a setting that is null there is not set on the channel and is inherited. With
 * `include_inherited=true`, `settings` holds what applies in the channel once the settings levels
 * above it are taken into account, and the request fails with 503 rather than resolve against a
 * settings level that could not be read. Either way `sources` names the settings level each value
 * comes from, so a caller tells the channel's own value from an inherited one by its source, not by
 * whether it is null.
 */
class SlackChannelRetrieveParams
private constructor(
    private val id: String?,
    private val includeInherited: Boolean?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** The channel's identifier, starting with `slch_`. */
    fun id(): Optional<String> = Optional.ofNullable(id)

    /**
     * When `true`, `settings` holds what applies in the channel once the settings levels above it
     * are taken into account, and every member of `sources` names the level a value comes from.
     * When `false` or left out, `settings` holds what the channel itself stores.
     */
    fun includeInherited(): Optional<Boolean> = Optional.ofNullable(includeInherited)

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

        @JvmStatic fun none(): SlackChannelRetrieveParams = builder().build()

        /**
         * Returns a mutable builder for constructing an instance of [SlackChannelRetrieveParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SlackChannelRetrieveParams]. */
    class Builder internal constructor() {

        private var id: String? = null
        private var includeInherited: Boolean? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(slackChannelRetrieveParams: SlackChannelRetrieveParams) = apply {
            id = slackChannelRetrieveParams.id
            includeInherited = slackChannelRetrieveParams.includeInherited
            betas = slackChannelRetrieveParams.betas?.toMutableList()
            additionalHeaders = slackChannelRetrieveParams.additionalHeaders.toBuilder()
            additionalQueryParams = slackChannelRetrieveParams.additionalQueryParams.toBuilder()
        }

        /** The channel's identifier, starting with `slch_`. */
        fun id(id: String?) = apply { this.id = id }

        /** Alias for calling [Builder.id] with `id.orElse(null)`. */
        fun id(id: Optional<String>) = id(id.getOrNull())

        /**
         * When `true`, `settings` holds what applies in the channel once the settings levels above
         * it are taken into account, and every member of `sources` names the level a value comes
         * from. When `false` or left out, `settings` holds what the channel itself stores.
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
         * Returns an immutable instance of [SlackChannelRetrieveParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): SlackChannelRetrieveParams =
            SlackChannelRetrieveParams(
                id,
                includeInherited,
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> id ?: ""
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

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                includeInherited?.let { put("include_inherited", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.organization.claude_tag.slack_channels.retrieve",
            route = "/v1/organizations/claude_tag/slack_channels/{id}",
            attributes =
                mapOf("anthropic.organization.claude_tag.slack_channel.id" to _pathParam(0)),
            queryParams = listOf("include_inherited"),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SlackChannelRetrieveParams &&
            id == other.id &&
            includeInherited == other.includeInherited &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(id, includeInherited, betas, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "SlackChannelRetrieveParams{id=$id, includeInherited=$includeInherited, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
