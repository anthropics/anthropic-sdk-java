package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * For each setting, the settings level its value in `settings` comes from. Without
 * `include_inherited`, a setting the channel or workspace stores has a source that names the
 * channel or workspace itself, and one it does not store has null, or an empty list for
 * `instructions`. With `include_inherited=true` no member is null: a value that no settings level
 * sets has the source `default`.
 */
class BetaClaudeTagSettingSources
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val enabled: JsonField<BetaClaudeTagSettingSource>,
    private val environmentId: JsonField<BetaClaudeTagSettingSource>,
    private val guestPolicy: JsonField<BetaClaudeTagSettingSource>,
    private val instructions: JsonField<List<BetaClaudeTagSettingSource>>,
    private val model: JsonField<BetaClaudeTagSettingSource>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("enabled")
        @ExcludeMissing
        enabled: JsonField<BetaClaudeTagSettingSource> = JsonMissing.of(),
        @JsonProperty("environment_id")
        @ExcludeMissing
        environmentId: JsonField<BetaClaudeTagSettingSource> = JsonMissing.of(),
        @JsonProperty("guest_policy")
        @ExcludeMissing
        guestPolicy: JsonField<BetaClaudeTagSettingSource> = JsonMissing.of(),
        @JsonProperty("instructions")
        @ExcludeMissing
        instructions: JsonField<List<BetaClaudeTagSettingSource>> = JsonMissing.of(),
        @JsonProperty("model")
        @ExcludeMissing
        model: JsonField<BetaClaudeTagSettingSource> = JsonMissing.of(),
    ) : this(enabled, environmentId, guestPolicy, instructions, model, mutableMapOf())

    /**
     * The settings level `enabled` comes from.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun enabled(): Optional<BetaClaudeTagSettingSource> = enabled.getOptional("enabled")

    /**
     * The settings level `environment_id` comes from.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun environmentId(): Optional<BetaClaudeTagSettingSource> =
        environmentId.getOptional("environment_id")

    /**
     * The settings level `guest_policy` comes from.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun guestPolicy(): Optional<BetaClaudeTagSettingSource> =
        guestPolicy.getOptional("guest_policy")

    /**
     * Every settings level that contributes text to `instructions`, broadest first, in the order
     * their text is joined. Empty when none does.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun instructions(): List<BetaClaudeTagSettingSource> = instructions.getRequired("instructions")

    /**
     * The settings level `model` comes from.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun model(): Optional<BetaClaudeTagSettingSource> = model.getOptional("model")

    /**
     * Returns the raw JSON value of [enabled].
     *
     * Unlike [enabled], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("enabled")
    @ExcludeMissing
    fun _enabled(): JsonField<BetaClaudeTagSettingSource> = enabled

    /**
     * Returns the raw JSON value of [environmentId].
     *
     * Unlike [environmentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("environment_id")
    @ExcludeMissing
    fun _environmentId(): JsonField<BetaClaudeTagSettingSource> = environmentId

    /**
     * Returns the raw JSON value of [guestPolicy].
     *
     * Unlike [guestPolicy], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("guest_policy")
    @ExcludeMissing
    fun _guestPolicy(): JsonField<BetaClaudeTagSettingSource> = guestPolicy

    /**
     * Returns the raw JSON value of [instructions].
     *
     * Unlike [instructions], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("instructions")
    @ExcludeMissing
    fun _instructions(): JsonField<List<BetaClaudeTagSettingSource>> = instructions

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model")
    @ExcludeMissing
    fun _model(): JsonField<BetaClaudeTagSettingSource> = model

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
         * Returns a mutable builder for constructing an instance of [BetaClaudeTagSettingSources].
         *
         * The following fields are required:
         * ```java
         * .enabled()
         * .environmentId()
         * .guestPolicy()
         * .instructions()
         * .model()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaClaudeTagSettingSources]. */
    class Builder internal constructor() {

        private var enabled: JsonField<BetaClaudeTagSettingSource>? = null
        private var environmentId: JsonField<BetaClaudeTagSettingSource>? = null
        private var guestPolicy: JsonField<BetaClaudeTagSettingSource>? = null
        private var instructions: JsonField<MutableList<BetaClaudeTagSettingSource>>? = null
        private var model: JsonField<BetaClaudeTagSettingSource>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaClaudeTagSettingSources: BetaClaudeTagSettingSources) = apply {
            enabled = betaClaudeTagSettingSources.enabled
            environmentId = betaClaudeTagSettingSources.environmentId
            guestPolicy = betaClaudeTagSettingSources.guestPolicy
            instructions =
                betaClaudeTagSettingSources.instructions
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            model = betaClaudeTagSettingSources.model
            additionalProperties = betaClaudeTagSettingSources.additionalProperties.toMutableMap()
        }

        /** The settings level `enabled` comes from. */
        fun enabled(enabled: BetaClaudeTagSettingSource?) = enabled(JsonField.ofNullable(enabled))

        /** Alias for calling [Builder.enabled] with `enabled.orElse(null)`. */
        fun enabled(enabled: Optional<BetaClaudeTagSettingSource>) = enabled(enabled.getOrNull())

        /**
         * Sets [Builder.enabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enabled] with a well-typed [BetaClaudeTagSettingSource]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun enabled(enabled: JsonField<BetaClaudeTagSettingSource>) = apply {
            this.enabled = enabled
        }

        /**
         * Alias for calling [enabled] with
         * `BetaClaudeTagSettingSource.ofSlackChannel(slackChannel)`.
         */
        fun enabled(slackChannel: BetaClaudeTagSlackChannelSettingSource) =
            enabled(BetaClaudeTagSettingSource.ofSlackChannel(slackChannel))

        /**
         * Alias for calling [enabled] with the following:
         * ```java
         * BetaClaudeTagSlackChannelSettingSource.builder()
         *     .slackChannelId(slackChannelId)
         *     .build()
         * ```
         */
        fun slackChannelEnabled(slackChannelId: String) =
            enabled(
                BetaClaudeTagSlackChannelSettingSource.builder()
                    .slackChannelId(slackChannelId)
                    .build()
            )

        /**
         * Alias for calling [enabled] with
         * `BetaClaudeTagSettingSource.ofSlackWorkspace(slackWorkspace)`.
         */
        fun enabled(slackWorkspace: BetaClaudeTagSlackWorkspaceSettingSource) =
            enabled(BetaClaudeTagSettingSource.ofSlackWorkspace(slackWorkspace))

        /**
         * Alias for calling [enabled] with the following:
         * ```java
         * BetaClaudeTagSlackWorkspaceSettingSource.builder()
         *     .slackWorkspaceId(slackWorkspaceId)
         *     .build()
         * ```
         */
        fun slackWorkspaceEnabled(slackWorkspaceId: String) =
            enabled(
                BetaClaudeTagSlackWorkspaceSettingSource.builder()
                    .slackWorkspaceId(slackWorkspaceId)
                    .build()
            )

        /**
         * Alias for calling [enabled] with
         * `BetaClaudeTagSettingSource.ofSlackParentChannel(slackParentChannel)`.
         */
        fun enabled(slackParentChannel: BetaClaudeTagSlackParentChannelSettingSource) =
            enabled(BetaClaudeTagSettingSource.ofSlackParentChannel(slackParentChannel))

        /**
         * Alias for calling [enabled] with the following:
         * ```java
         * BetaClaudeTagSlackParentChannelSettingSource.builder()
         *     .slackChannelId(slackChannelId)
         *     .build()
         * ```
         */
        fun slackParentChannelEnabled(slackChannelId: String) =
            enabled(
                BetaClaudeTagSlackParentChannelSettingSource.builder()
                    .slackChannelId(slackChannelId)
                    .build()
            )

        /**
         * Alias for calling [enabled] with
         * `BetaClaudeTagSettingSource.ofSlackDefault(slackDefault)`.
         */
        fun enabled(slackDefault: BetaClaudeTagSlackDefaultSettingSource) =
            enabled(BetaClaudeTagSettingSource.ofSlackDefault(slackDefault))

        /** Alias for calling [enabled] with `BetaClaudeTagSettingSource.ofDefault(default_)`. */
        fun enabled(default_: BetaClaudeTagDefaultSettingSource) =
            enabled(BetaClaudeTagSettingSource.ofDefault(default_))

        /**
         * Alias for calling [enabled] with
         * `BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel)`.
         */
        fun enabled(slackGridChannel: BetaClaudeTagSlackGridChannelSettingSource) =
            enabled(BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel))

        /**
         * Alias for calling [enabled] with the following:
         * ```java
         * BetaClaudeTagSlackGridChannelSettingSource.builder()
         *     .slack(slack)
         *     .build()
         * ```
         */
        fun slackGridChannelEnabled(slack: BetaClaudeTagSlackChannelIdentifiers) =
            enabled(BetaClaudeTagSlackGridChannelSettingSource.builder().slack(slack).build())

        /** The settings level `environment_id` comes from. */
        fun environmentId(environmentId: BetaClaudeTagSettingSource?) =
            environmentId(JsonField.ofNullable(environmentId))

        /** Alias for calling [Builder.environmentId] with `environmentId.orElse(null)`. */
        fun environmentId(environmentId: Optional<BetaClaudeTagSettingSource>) =
            environmentId(environmentId.getOrNull())

        /**
         * Sets [Builder.environmentId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.environmentId] with a well-typed
         * [BetaClaudeTagSettingSource] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun environmentId(environmentId: JsonField<BetaClaudeTagSettingSource>) = apply {
            this.environmentId = environmentId
        }

        /**
         * Alias for calling [environmentId] with
         * `BetaClaudeTagSettingSource.ofSlackChannel(slackChannel)`.
         */
        fun environmentId(slackChannel: BetaClaudeTagSlackChannelSettingSource) =
            environmentId(BetaClaudeTagSettingSource.ofSlackChannel(slackChannel))

        /**
         * Alias for calling [environmentId] with the following:
         * ```java
         * BetaClaudeTagSlackChannelSettingSource.builder()
         *     .slackChannelId(slackChannelId)
         *     .build()
         * ```
         */
        fun slackChannelEnvironmentId(slackChannelId: String) =
            environmentId(
                BetaClaudeTagSlackChannelSettingSource.builder()
                    .slackChannelId(slackChannelId)
                    .build()
            )

        /**
         * Alias for calling [environmentId] with
         * `BetaClaudeTagSettingSource.ofSlackWorkspace(slackWorkspace)`.
         */
        fun environmentId(slackWorkspace: BetaClaudeTagSlackWorkspaceSettingSource) =
            environmentId(BetaClaudeTagSettingSource.ofSlackWorkspace(slackWorkspace))

        /**
         * Alias for calling [environmentId] with the following:
         * ```java
         * BetaClaudeTagSlackWorkspaceSettingSource.builder()
         *     .slackWorkspaceId(slackWorkspaceId)
         *     .build()
         * ```
         */
        fun slackWorkspaceEnvironmentId(slackWorkspaceId: String) =
            environmentId(
                BetaClaudeTagSlackWorkspaceSettingSource.builder()
                    .slackWorkspaceId(slackWorkspaceId)
                    .build()
            )

        /**
         * Alias for calling [environmentId] with
         * `BetaClaudeTagSettingSource.ofSlackParentChannel(slackParentChannel)`.
         */
        fun environmentId(slackParentChannel: BetaClaudeTagSlackParentChannelSettingSource) =
            environmentId(BetaClaudeTagSettingSource.ofSlackParentChannel(slackParentChannel))

        /**
         * Alias for calling [environmentId] with the following:
         * ```java
         * BetaClaudeTagSlackParentChannelSettingSource.builder()
         *     .slackChannelId(slackChannelId)
         *     .build()
         * ```
         */
        fun slackParentChannelEnvironmentId(slackChannelId: String) =
            environmentId(
                BetaClaudeTagSlackParentChannelSettingSource.builder()
                    .slackChannelId(slackChannelId)
                    .build()
            )

        /**
         * Alias for calling [environmentId] with
         * `BetaClaudeTagSettingSource.ofSlackDefault(slackDefault)`.
         */
        fun environmentId(slackDefault: BetaClaudeTagSlackDefaultSettingSource) =
            environmentId(BetaClaudeTagSettingSource.ofSlackDefault(slackDefault))

        /**
         * Alias for calling [environmentId] with `BetaClaudeTagSettingSource.ofDefault(default_)`.
         */
        fun environmentId(default_: BetaClaudeTagDefaultSettingSource) =
            environmentId(BetaClaudeTagSettingSource.ofDefault(default_))

        /**
         * Alias for calling [environmentId] with
         * `BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel)`.
         */
        fun environmentId(slackGridChannel: BetaClaudeTagSlackGridChannelSettingSource) =
            environmentId(BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel))

        /**
         * Alias for calling [environmentId] with the following:
         * ```java
         * BetaClaudeTagSlackGridChannelSettingSource.builder()
         *     .slack(slack)
         *     .build()
         * ```
         */
        fun slackGridChannelEnvironmentId(slack: BetaClaudeTagSlackChannelIdentifiers) =
            environmentId(BetaClaudeTagSlackGridChannelSettingSource.builder().slack(slack).build())

        /** The settings level `guest_policy` comes from. */
        fun guestPolicy(guestPolicy: BetaClaudeTagSettingSource?) =
            guestPolicy(JsonField.ofNullable(guestPolicy))

        /** Alias for calling [Builder.guestPolicy] with `guestPolicy.orElse(null)`. */
        fun guestPolicy(guestPolicy: Optional<BetaClaudeTagSettingSource>) =
            guestPolicy(guestPolicy.getOrNull())

        /**
         * Sets [Builder.guestPolicy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.guestPolicy] with a well-typed
         * [BetaClaudeTagSettingSource] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun guestPolicy(guestPolicy: JsonField<BetaClaudeTagSettingSource>) = apply {
            this.guestPolicy = guestPolicy
        }

        /**
         * Alias for calling [guestPolicy] with
         * `BetaClaudeTagSettingSource.ofSlackChannel(slackChannel)`.
         */
        fun guestPolicy(slackChannel: BetaClaudeTagSlackChannelSettingSource) =
            guestPolicy(BetaClaudeTagSettingSource.ofSlackChannel(slackChannel))

        /**
         * Alias for calling [guestPolicy] with the following:
         * ```java
         * BetaClaudeTagSlackChannelSettingSource.builder()
         *     .slackChannelId(slackChannelId)
         *     .build()
         * ```
         */
        fun slackChannelGuestPolicy(slackChannelId: String) =
            guestPolicy(
                BetaClaudeTagSlackChannelSettingSource.builder()
                    .slackChannelId(slackChannelId)
                    .build()
            )

        /**
         * Alias for calling [guestPolicy] with
         * `BetaClaudeTagSettingSource.ofSlackWorkspace(slackWorkspace)`.
         */
        fun guestPolicy(slackWorkspace: BetaClaudeTagSlackWorkspaceSettingSource) =
            guestPolicy(BetaClaudeTagSettingSource.ofSlackWorkspace(slackWorkspace))

        /**
         * Alias for calling [guestPolicy] with the following:
         * ```java
         * BetaClaudeTagSlackWorkspaceSettingSource.builder()
         *     .slackWorkspaceId(slackWorkspaceId)
         *     .build()
         * ```
         */
        fun slackWorkspaceGuestPolicy(slackWorkspaceId: String) =
            guestPolicy(
                BetaClaudeTagSlackWorkspaceSettingSource.builder()
                    .slackWorkspaceId(slackWorkspaceId)
                    .build()
            )

        /**
         * Alias for calling [guestPolicy] with
         * `BetaClaudeTagSettingSource.ofSlackParentChannel(slackParentChannel)`.
         */
        fun guestPolicy(slackParentChannel: BetaClaudeTagSlackParentChannelSettingSource) =
            guestPolicy(BetaClaudeTagSettingSource.ofSlackParentChannel(slackParentChannel))

        /**
         * Alias for calling [guestPolicy] with the following:
         * ```java
         * BetaClaudeTagSlackParentChannelSettingSource.builder()
         *     .slackChannelId(slackChannelId)
         *     .build()
         * ```
         */
        fun slackParentChannelGuestPolicy(slackChannelId: String) =
            guestPolicy(
                BetaClaudeTagSlackParentChannelSettingSource.builder()
                    .slackChannelId(slackChannelId)
                    .build()
            )

        /**
         * Alias for calling [guestPolicy] with
         * `BetaClaudeTagSettingSource.ofSlackDefault(slackDefault)`.
         */
        fun guestPolicy(slackDefault: BetaClaudeTagSlackDefaultSettingSource) =
            guestPolicy(BetaClaudeTagSettingSource.ofSlackDefault(slackDefault))

        /**
         * Alias for calling [guestPolicy] with `BetaClaudeTagSettingSource.ofDefault(default_)`.
         */
        fun guestPolicy(default_: BetaClaudeTagDefaultSettingSource) =
            guestPolicy(BetaClaudeTagSettingSource.ofDefault(default_))

        /**
         * Alias for calling [guestPolicy] with
         * `BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel)`.
         */
        fun guestPolicy(slackGridChannel: BetaClaudeTagSlackGridChannelSettingSource) =
            guestPolicy(BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel))

        /**
         * Alias for calling [guestPolicy] with the following:
         * ```java
         * BetaClaudeTagSlackGridChannelSettingSource.builder()
         *     .slack(slack)
         *     .build()
         * ```
         */
        fun slackGridChannelGuestPolicy(slack: BetaClaudeTagSlackChannelIdentifiers) =
            guestPolicy(BetaClaudeTagSlackGridChannelSettingSource.builder().slack(slack).build())

        /**
         * Every settings level that contributes text to `instructions`, broadest first, in the
         * order their text is joined. Empty when none does.
         */
        fun instructions(instructions: List<BetaClaudeTagSettingSource>) =
            instructions(JsonField.of(instructions))

        /**
         * Sets [Builder.instructions] to an arbitrary JSON value.
         *
         * You should usually call [Builder.instructions] with a well-typed
         * `List<BetaClaudeTagSettingSource>` value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun instructions(instructions: JsonField<List<BetaClaudeTagSettingSource>>) = apply {
            this.instructions = instructions.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaClaudeTagSettingSource] to [instructions].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addInstruction(instruction: BetaClaudeTagSettingSource) = apply {
            instructions =
                (instructions ?: JsonField.of(mutableListOf())).also {
                    checkKnown("instructions", it).add(instruction)
                }
        }

        /**
         * Alias for calling [addInstruction] with
         * `BetaClaudeTagSettingSource.ofSlackChannel(slackChannel)`.
         */
        fun addInstruction(slackChannel: BetaClaudeTagSlackChannelSettingSource) =
            addInstruction(BetaClaudeTagSettingSource.ofSlackChannel(slackChannel))

        /**
         * Alias for calling [addInstruction] with the following:
         * ```java
         * BetaClaudeTagSlackChannelSettingSource.builder()
         *     .slackChannelId(slackChannelId)
         *     .build()
         * ```
         */
        fun addSlackChannelInstruction(slackChannelId: String) =
            addInstruction(
                BetaClaudeTagSlackChannelSettingSource.builder()
                    .slackChannelId(slackChannelId)
                    .build()
            )

        /**
         * Alias for calling [addInstruction] with
         * `BetaClaudeTagSettingSource.ofSlackWorkspace(slackWorkspace)`.
         */
        fun addInstruction(slackWorkspace: BetaClaudeTagSlackWorkspaceSettingSource) =
            addInstruction(BetaClaudeTagSettingSource.ofSlackWorkspace(slackWorkspace))

        /**
         * Alias for calling [addInstruction] with the following:
         * ```java
         * BetaClaudeTagSlackWorkspaceSettingSource.builder()
         *     .slackWorkspaceId(slackWorkspaceId)
         *     .build()
         * ```
         */
        fun addSlackWorkspaceInstruction(slackWorkspaceId: String) =
            addInstruction(
                BetaClaudeTagSlackWorkspaceSettingSource.builder()
                    .slackWorkspaceId(slackWorkspaceId)
                    .build()
            )

        /**
         * Alias for calling [addInstruction] with
         * `BetaClaudeTagSettingSource.ofSlackParentChannel(slackParentChannel)`.
         */
        fun addInstruction(slackParentChannel: BetaClaudeTagSlackParentChannelSettingSource) =
            addInstruction(BetaClaudeTagSettingSource.ofSlackParentChannel(slackParentChannel))

        /**
         * Alias for calling [addInstruction] with the following:
         * ```java
         * BetaClaudeTagSlackParentChannelSettingSource.builder()
         *     .slackChannelId(slackChannelId)
         *     .build()
         * ```
         */
        fun addSlackParentChannelInstruction(slackChannelId: String) =
            addInstruction(
                BetaClaudeTagSlackParentChannelSettingSource.builder()
                    .slackChannelId(slackChannelId)
                    .build()
            )

        /**
         * Alias for calling [addInstruction] with
         * `BetaClaudeTagSettingSource.ofSlackDefault(slackDefault)`.
         */
        fun addInstruction(slackDefault: BetaClaudeTagSlackDefaultSettingSource) =
            addInstruction(BetaClaudeTagSettingSource.ofSlackDefault(slackDefault))

        /**
         * Alias for calling [addInstruction] with `BetaClaudeTagSettingSource.ofDefault(default_)`.
         */
        fun addInstruction(default_: BetaClaudeTagDefaultSettingSource) =
            addInstruction(BetaClaudeTagSettingSource.ofDefault(default_))

        /**
         * Alias for calling [addInstruction] with
         * `BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel)`.
         */
        fun addInstruction(slackGridChannel: BetaClaudeTagSlackGridChannelSettingSource) =
            addInstruction(BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel))

        /**
         * Alias for calling [addInstruction] with the following:
         * ```java
         * BetaClaudeTagSlackGridChannelSettingSource.builder()
         *     .slack(slack)
         *     .build()
         * ```
         */
        fun addSlackGridChannelInstruction(slack: BetaClaudeTagSlackChannelIdentifiers) =
            addInstruction(
                BetaClaudeTagSlackGridChannelSettingSource.builder().slack(slack).build()
            )

        /** The settings level `model` comes from. */
        fun model(model: BetaClaudeTagSettingSource?) = model(JsonField.ofNullable(model))

        /** Alias for calling [Builder.model] with `model.orElse(null)`. */
        fun model(model: Optional<BetaClaudeTagSettingSource>) = model(model.getOrNull())

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [BetaClaudeTagSettingSource]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun model(model: JsonField<BetaClaudeTagSettingSource>) = apply { this.model = model }

        /**
         * Alias for calling [model] with `BetaClaudeTagSettingSource.ofSlackChannel(slackChannel)`.
         */
        fun model(slackChannel: BetaClaudeTagSlackChannelSettingSource) =
            model(BetaClaudeTagSettingSource.ofSlackChannel(slackChannel))

        /**
         * Alias for calling [model] with the following:
         * ```java
         * BetaClaudeTagSlackChannelSettingSource.builder()
         *     .slackChannelId(slackChannelId)
         *     .build()
         * ```
         */
        fun slackChannelModel(slackChannelId: String) =
            model(
                BetaClaudeTagSlackChannelSettingSource.builder()
                    .slackChannelId(slackChannelId)
                    .build()
            )

        /**
         * Alias for calling [model] with
         * `BetaClaudeTagSettingSource.ofSlackWorkspace(slackWorkspace)`.
         */
        fun model(slackWorkspace: BetaClaudeTagSlackWorkspaceSettingSource) =
            model(BetaClaudeTagSettingSource.ofSlackWorkspace(slackWorkspace))

        /**
         * Alias for calling [model] with the following:
         * ```java
         * BetaClaudeTagSlackWorkspaceSettingSource.builder()
         *     .slackWorkspaceId(slackWorkspaceId)
         *     .build()
         * ```
         */
        fun slackWorkspaceModel(slackWorkspaceId: String) =
            model(
                BetaClaudeTagSlackWorkspaceSettingSource.builder()
                    .slackWorkspaceId(slackWorkspaceId)
                    .build()
            )

        /**
         * Alias for calling [model] with
         * `BetaClaudeTagSettingSource.ofSlackParentChannel(slackParentChannel)`.
         */
        fun model(slackParentChannel: BetaClaudeTagSlackParentChannelSettingSource) =
            model(BetaClaudeTagSettingSource.ofSlackParentChannel(slackParentChannel))

        /**
         * Alias for calling [model] with the following:
         * ```java
         * BetaClaudeTagSlackParentChannelSettingSource.builder()
         *     .slackChannelId(slackChannelId)
         *     .build()
         * ```
         */
        fun slackParentChannelModel(slackChannelId: String) =
            model(
                BetaClaudeTagSlackParentChannelSettingSource.builder()
                    .slackChannelId(slackChannelId)
                    .build()
            )

        /**
         * Alias for calling [model] with `BetaClaudeTagSettingSource.ofSlackDefault(slackDefault)`.
         */
        fun model(slackDefault: BetaClaudeTagSlackDefaultSettingSource) =
            model(BetaClaudeTagSettingSource.ofSlackDefault(slackDefault))

        /** Alias for calling [model] with `BetaClaudeTagSettingSource.ofDefault(default_)`. */
        fun model(default_: BetaClaudeTagDefaultSettingSource) =
            model(BetaClaudeTagSettingSource.ofDefault(default_))

        /**
         * Alias for calling [model] with
         * `BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel)`.
         */
        fun model(slackGridChannel: BetaClaudeTagSlackGridChannelSettingSource) =
            model(BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel))

        /**
         * Alias for calling [model] with the following:
         * ```java
         * BetaClaudeTagSlackGridChannelSettingSource.builder()
         *     .slack(slack)
         *     .build()
         * ```
         */
        fun slackGridChannelModel(slack: BetaClaudeTagSlackChannelIdentifiers) =
            model(BetaClaudeTagSlackGridChannelSettingSource.builder().slack(slack).build())

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
         * Returns an immutable instance of [BetaClaudeTagSettingSources].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .enabled()
         * .environmentId()
         * .guestPolicy()
         * .instructions()
         * .model()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaClaudeTagSettingSources =
            BetaClaudeTagSettingSources(
                checkRequired("enabled", enabled),
                checkRequired("environmentId", environmentId),
                checkRequired("guestPolicy", guestPolicy),
                checkRequired("instructions", instructions).map { it.toImmutable() },
                checkRequired("model", model),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaClaudeTagSettingSources = apply {
        if (validated) {
            return@apply
        }

        enabled().ifPresent { it.validate() }
        environmentId().ifPresent { it.validate() }
        guestPolicy().ifPresent { it.validate() }
        instructions().forEach { it.validate() }
        model().ifPresent { it.validate() }
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (enabled.asKnown().getOrNull()?.validity() ?: 0) +
            (environmentId.asKnown().getOrNull()?.validity() ?: 0) +
            (guestPolicy.asKnown().getOrNull()?.validity() ?: 0) +
            (instructions.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (model.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeTagSettingSources &&
            enabled == other.enabled &&
            environmentId == other.environmentId &&
            guestPolicy == other.guestPolicy &&
            instructions == other.instructions &&
            model == other.model &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(enabled, environmentId, guestPolicy, instructions, model, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaClaudeTagSettingSources{enabled=$enabled, environmentId=$environmentId, guestPolicy=$guestPolicy, instructions=$instructions, model=$model, additionalProperties=$additionalProperties}"
}
