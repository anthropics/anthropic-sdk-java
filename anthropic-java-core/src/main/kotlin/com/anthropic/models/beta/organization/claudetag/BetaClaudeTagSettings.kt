package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
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
 * Claude Tag settings. Without `include_inherited`, a setting that is null is not set on the
 * settings level and is inherited. With it, `enabled` and `guest_policy` are never null, and
 * `instructions`, `model` and `environment_id` are null when no settings level sets them.
 */
class BetaClaudeTagSettings
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val enabled: JsonField<Boolean>,
    private val environmentId: JsonField<String>,
    private val guestPolicy: JsonField<BetaClaudeTagGuestPolicy>,
    private val instructions: JsonField<String>,
    private val model: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("enabled") @ExcludeMissing enabled: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("environment_id")
        @ExcludeMissing
        environmentId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("guest_policy")
        @ExcludeMissing
        guestPolicy: JsonField<BetaClaudeTagGuestPolicy> = JsonMissing.of(),
        @JsonProperty("instructions")
        @ExcludeMissing
        instructions: JsonField<String> = JsonMissing.of(),
        @JsonProperty("model") @ExcludeMissing model: JsonField<String> = JsonMissing.of(),
    ) : this(enabled, environmentId, guestPolicy, instructions, model, mutableMapOf())

    /**
     * Whether Claude is on. The nearest settings level that sets it decides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun enabled(): Optional<Boolean> = enabled.getOptional("enabled")

    /**
     * The environment Claude's sessions run in, starting with `env_`, or the identifier of a
     * self-hosted runner pool, starting with `ccpool_`. The nearest settings level that sets it
     * decides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun environmentId(): Optional<String> = environmentId.getOptional("environment_id")

    /**
     * What Claude does in a channel while a Slack guest is in it. The nearest settings level that
     * sets it decides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun guestPolicy(): Optional<BetaClaudeTagGuestPolicy> = guestPolicy.getOptional("guest_policy")

    /**
     * Instructions for Claude. Instructions add up: the text of every settings level that sets it
     * is joined, broadest first.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun instructions(): Optional<String> = instructions.getOptional("instructions")

    /**
     * The model Claude uses: a model identifier or the name of a model line. The nearest settings
     * level that sets it decides. When none does, Claude uses the product's default model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun model(): Optional<String> = model.getOptional("model")

    /**
     * Returns the raw JSON value of [enabled].
     *
     * Unlike [enabled], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("enabled") @ExcludeMissing fun _enabled(): JsonField<Boolean> = enabled

    /**
     * Returns the raw JSON value of [environmentId].
     *
     * Unlike [environmentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("environment_id")
    @ExcludeMissing
    fun _environmentId(): JsonField<String> = environmentId

    /**
     * Returns the raw JSON value of [guestPolicy].
     *
     * Unlike [guestPolicy], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("guest_policy")
    @ExcludeMissing
    fun _guestPolicy(): JsonField<BetaClaudeTagGuestPolicy> = guestPolicy

    /**
     * Returns the raw JSON value of [instructions].
     *
     * Unlike [instructions], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("instructions")
    @ExcludeMissing
    fun _instructions(): JsonField<String> = instructions

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<String> = model

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
         * Returns a mutable builder for constructing an instance of [BetaClaudeTagSettings].
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

    /** A builder for [BetaClaudeTagSettings]. */
    class Builder internal constructor() {

        private var enabled: JsonField<Boolean>? = null
        private var environmentId: JsonField<String>? = null
        private var guestPolicy: JsonField<BetaClaudeTagGuestPolicy>? = null
        private var instructions: JsonField<String>? = null
        private var model: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaClaudeTagSettings: BetaClaudeTagSettings) = apply {
            enabled = betaClaudeTagSettings.enabled
            environmentId = betaClaudeTagSettings.environmentId
            guestPolicy = betaClaudeTagSettings.guestPolicy
            instructions = betaClaudeTagSettings.instructions
            model = betaClaudeTagSettings.model
            additionalProperties = betaClaudeTagSettings.additionalProperties.toMutableMap()
        }

        /** Whether Claude is on. The nearest settings level that sets it decides. */
        fun enabled(enabled: Boolean?) = enabled(JsonField.ofNullable(enabled))

        /**
         * Alias for [Builder.enabled].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun enabled(enabled: Boolean) = enabled(enabled as Boolean?)

        /** Alias for calling [Builder.enabled] with `enabled.orElse(null)`. */
        fun enabled(enabled: Optional<Boolean>) = enabled(enabled.getOrNull())

        /**
         * Sets [Builder.enabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enabled] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun enabled(enabled: JsonField<Boolean>) = apply { this.enabled = enabled }

        /**
         * The environment Claude's sessions run in, starting with `env_`, or the identifier of a
         * self-hosted runner pool, starting with `ccpool_`. The nearest settings level that sets it
         * decides.
         */
        fun environmentId(environmentId: String?) =
            environmentId(JsonField.ofNullable(environmentId))

        /** Alias for calling [Builder.environmentId] with `environmentId.orElse(null)`. */
        fun environmentId(environmentId: Optional<String>) =
            environmentId(environmentId.getOrNull())

        /**
         * Sets [Builder.environmentId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.environmentId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun environmentId(environmentId: JsonField<String>) = apply {
            this.environmentId = environmentId
        }

        /**
         * What Claude does in a channel while a Slack guest is in it. The nearest settings level
         * that sets it decides.
         */
        fun guestPolicy(guestPolicy: BetaClaudeTagGuestPolicy?) =
            guestPolicy(JsonField.ofNullable(guestPolicy))

        /** Alias for calling [Builder.guestPolicy] with `guestPolicy.orElse(null)`. */
        fun guestPolicy(guestPolicy: Optional<BetaClaudeTagGuestPolicy>) =
            guestPolicy(guestPolicy.getOrNull())

        /**
         * Sets [Builder.guestPolicy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.guestPolicy] with a well-typed
         * [BetaClaudeTagGuestPolicy] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun guestPolicy(guestPolicy: JsonField<BetaClaudeTagGuestPolicy>) = apply {
            this.guestPolicy = guestPolicy
        }

        /** Alias for calling [guestPolicy] with `BetaClaudeTagGuestPolicy.ofAllow(allow)`. */
        fun guestPolicy(allow: BetaClaudeTagGuestPolicyAllow) =
            guestPolicy(BetaClaudeTagGuestPolicy.ofAllow(allow))

        /** Alias for calling [guestPolicy] with `BetaClaudeTagGuestPolicy.ofRestrict(restrict)`. */
        fun guestPolicy(restrict: BetaClaudeTagGuestPolicyRestrict) =
            guestPolicy(BetaClaudeTagGuestPolicy.ofRestrict(restrict))

        /**
         * Alias for calling [guestPolicy] with
         * `BetaClaudeTagGuestPolicy.ofChannelOnly(channelOnly)`.
         */
        fun guestPolicy(channelOnly: BetaClaudeTagGuestPolicyChannelOnly) =
            guestPolicy(BetaClaudeTagGuestPolicy.ofChannelOnly(channelOnly))

        /**
         * Instructions for Claude. Instructions add up: the text of every settings level that sets
         * it is joined, broadest first.
         */
        fun instructions(instructions: String?) = instructions(JsonField.ofNullable(instructions))

        /** Alias for calling [Builder.instructions] with `instructions.orElse(null)`. */
        fun instructions(instructions: Optional<String>) = instructions(instructions.getOrNull())

        /**
         * Sets [Builder.instructions] to an arbitrary JSON value.
         *
         * You should usually call [Builder.instructions] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun instructions(instructions: JsonField<String>) = apply {
            this.instructions = instructions
        }

        /**
         * The model Claude uses: a model identifier or the name of a model line. The nearest
         * settings level that sets it decides. When none does, Claude uses the product's default
         * model.
         */
        fun model(model: String?) = model(JsonField.ofNullable(model))

        /** Alias for calling [Builder.model] with `model.orElse(null)`. */
        fun model(model: Optional<String>) = model(model.getOrNull())

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(model: JsonField<String>) = apply { this.model = model }

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
         * Returns an immutable instance of [BetaClaudeTagSettings].
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
        fun build(): BetaClaudeTagSettings =
            BetaClaudeTagSettings(
                checkRequired("enabled", enabled),
                checkRequired("environmentId", environmentId),
                checkRequired("guestPolicy", guestPolicy),
                checkRequired("instructions", instructions),
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
    fun validate(): BetaClaudeTagSettings = apply {
        if (validated) {
            return@apply
        }

        enabled()
        environmentId()
        guestPolicy().ifPresent { it.validate() }
        instructions()
        model()
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
        (if (enabled.asKnown().isPresent) 1 else 0) +
            (if (environmentId.asKnown().isPresent) 1 else 0) +
            (guestPolicy.asKnown().getOrNull()?.validity() ?: 0) +
            (if (instructions.asKnown().isPresent) 1 else 0) +
            (if (model.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeTagSettings &&
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
        "BetaClaudeTagSettings{enabled=$enabled, environmentId=$environmentId, guestPolicy=$guestPolicy, instructions=$instructions, model=$model, additionalProperties=$additionalProperties}"
}
