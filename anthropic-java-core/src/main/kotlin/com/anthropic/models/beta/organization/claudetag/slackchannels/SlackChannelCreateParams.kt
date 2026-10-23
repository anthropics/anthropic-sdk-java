package com.anthropic.models.beta.organization.claudetag.slackchannels

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettingsCreate
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSlackChannelIdentifiers
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Set a Slack channel up for Claude Tag and return it with its identifier. If the channel is
 * already set up, whether in claude.ai, by Claude's first use in it or by an earlier request, the
 * existing channel is returned, so the request can be repeated. `settings`, when sent, is applied
 * as an update applies it. Before a channel is set up, Claude's Slack app confirms with Slack that
 * the channel is in the workspace, is not archived and is visible to the app; a channel that fails
 * the confirmation returns 404. Setting the channel up and applying `settings` are two steps: when
 * the second is refused, the response is the refusal, the channel stays set up with every setting
 * unset, and the error's `details.slack_channel_id` holds the channel's identifier.
 */
class SlackChannelCreateParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * This endpoint is in beta: requests must send `experimental-claude-tag-2026-10-21` in this
     * header.
     */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Slack's own identifiers for the channel to set up. The organization must own the workspace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun slack(): BetaClaudeTagSlackChannelIdentifiers = body.slack()

    /**
     * Settings to store on the channel, applied as Update Slack Channel applies them.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun settings(): Optional<BetaClaudeTagSettingsCreate> = body.settings()

    /**
     * Returns the raw JSON value of [slack].
     *
     * Unlike [slack], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _slack(): JsonField<BetaClaudeTagSlackChannelIdentifiers> = body._slack()

    /**
     * Returns the raw JSON value of [settings].
     *
     * Unlike [settings], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _settings(): JsonField<BetaClaudeTagSettingsCreate> = body._settings()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SlackChannelCreateParams].
         *
         * The following fields are required:
         * ```java
         * .slack()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SlackChannelCreateParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(slackChannelCreateParams: SlackChannelCreateParams) = apply {
            betas = slackChannelCreateParams.betas?.toMutableList()
            body = slackChannelCreateParams.body.toBuilder()
            additionalHeaders = slackChannelCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = slackChannelCreateParams.additionalQueryParams.toBuilder()
        }

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

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [slack]
         * - [settings]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * Slack's own identifiers for the channel to set up. The organization must own the
         * workspace.
         */
        fun slack(slack: BetaClaudeTagSlackChannelIdentifiers) = apply { body.slack(slack) }

        /**
         * Sets [Builder.slack] to an arbitrary JSON value.
         *
         * You should usually call [Builder.slack] with a well-typed
         * [BetaClaudeTagSlackChannelIdentifiers] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun slack(slack: JsonField<BetaClaudeTagSlackChannelIdentifiers>) = apply {
            body.slack(slack)
        }

        /** Settings to store on the channel, applied as Update Slack Channel applies them. */
        fun settings(settings: BetaClaudeTagSettingsCreate) = apply { body.settings(settings) }

        /**
         * Sets [Builder.settings] to an arbitrary JSON value.
         *
         * You should usually call [Builder.settings] with a well-typed
         * [BetaClaudeTagSettingsCreate] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun settings(settings: JsonField<BetaClaudeTagSettingsCreate>) = apply {
            body.settings(settings)
        }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

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
         * Returns an immutable instance of [SlackChannelCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .slack()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SlackChannelCreateParams =
            SlackChannelCreateParams(
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

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
            name = "anthropic.organization.claude_tag.slack_channels.create",
            route = "/v1/organizations/claude_tag/slack_channels",
        )

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val slack: JsonField<BetaClaudeTagSlackChannelIdentifiers>,
        private val settings: JsonField<BetaClaudeTagSettingsCreate>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("slack")
            @ExcludeMissing
            slack: JsonField<BetaClaudeTagSlackChannelIdentifiers> = JsonMissing.of(),
            @JsonProperty("settings")
            @ExcludeMissing
            settings: JsonField<BetaClaudeTagSettingsCreate> = JsonMissing.of(),
        ) : this(slack, settings, mutableMapOf())

        /**
         * Slack's own identifiers for the channel to set up. The organization must own the
         * workspace.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun slack(): BetaClaudeTagSlackChannelIdentifiers = slack.getRequired("slack")

        /**
         * Settings to store on the channel, applied as Update Slack Channel applies them.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun settings(): Optional<BetaClaudeTagSettingsCreate> = settings.getOptional("settings")

        /**
         * Returns the raw JSON value of [slack].
         *
         * Unlike [slack], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("slack")
        @ExcludeMissing
        fun _slack(): JsonField<BetaClaudeTagSlackChannelIdentifiers> = slack

        /**
         * Returns the raw JSON value of [settings].
         *
         * Unlike [settings], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("settings")
        @ExcludeMissing
        fun _settings(): JsonField<BetaClaudeTagSettingsCreate> = settings

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [Body].
             *
             * The following fields are required:
             * ```java
             * .slack()
             * ```
             */
            @JvmStatic fun builder() = Builder()

            /**
             * Returns an immutable instance of [Body] with the required [slack] set to the given
             * value.
             */
            @JvmStatic
            fun of(slack: BetaClaudeTagSlackChannelIdentifiers) = builder().slack(slack).build()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var slack: JsonField<BetaClaudeTagSlackChannelIdentifiers>? = null
            private var settings: JsonField<BetaClaudeTagSettingsCreate> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                slack = body.slack
                settings = body.settings
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * Slack's own identifiers for the channel to set up. The organization must own the
             * workspace.
             */
            fun slack(slack: BetaClaudeTagSlackChannelIdentifiers) = slack(JsonField.of(slack))

            /**
             * Sets [Builder.slack] to an arbitrary JSON value.
             *
             * You should usually call [Builder.slack] with a well-typed
             * [BetaClaudeTagSlackChannelIdentifiers] value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun slack(slack: JsonField<BetaClaudeTagSlackChannelIdentifiers>) = apply {
                this.slack = slack
            }

            /** Settings to store on the channel, applied as Update Slack Channel applies them. */
            fun settings(settings: BetaClaudeTagSettingsCreate) = settings(JsonField.of(settings))

            /**
             * Sets [Builder.settings] to an arbitrary JSON value.
             *
             * You should usually call [Builder.settings] with a well-typed
             * [BetaClaudeTagSettingsCreate] value instead. This method is primarily for setting the
             * field to an undocumented or not yet supported value.
             */
            fun settings(settings: JsonField<BetaClaudeTagSettingsCreate>) = apply {
                this.settings = settings
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .slack()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(checkRequired("slack", slack), settings, additionalProperties.toMutableMap())
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
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            slack().validate()
            settings().ifPresent { it.validate() }
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
        @JvmSynthetic
        internal fun validity(): Int =
            (slack.asKnown().getOrNull()?.validity() ?: 0) +
                (settings.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                slack == other.slack &&
                settings == other.settings &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(slack, settings, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{slack=$slack, settings=$settings, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SlackChannelCreateParams &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "SlackChannelCreateParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
