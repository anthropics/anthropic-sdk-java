// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * One entry in the `fallbacks` chain on a `/v1/messages` request.
 *
 * `model` is required. The override fields (`max_tokens`, `thinking`, `output_config`, and `speed`)
 * set the corresponding parameter for this attempt only and are validated as if the request were
 * made to `model`. Any other key is rejected at parse time.
 */
class BetaFallbackParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val model: JsonField<Model>,
    private val maxTokens: JsonField<Long>,
    private val outputConfig: JsonField<BetaOutputConfig>,
    private val speed: JsonField<Speed>,
    private val thinking: JsonField<Thinking>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("model") @ExcludeMissing model: JsonField<Model> = JsonMissing.of(),
        @JsonProperty("max_tokens") @ExcludeMissing maxTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("output_config")
        @ExcludeMissing
        outputConfig: JsonField<BetaOutputConfig> = JsonMissing.of(),
        @JsonProperty("speed") @ExcludeMissing speed: JsonField<Speed> = JsonMissing.of(),
        @JsonProperty("thinking") @ExcludeMissing thinking: JsonField<Thinking> = JsonMissing.of(),
    ) : this(model, maxTokens, outputConfig, speed, thinking, mutableMapOf())

    /**
     * The model that will complete your prompt.
     *
     * See [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun model(): Model = model.getRequired("model")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxTokens(): Optional<Long> = maxTokens.getOptional("max_tokens")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun outputConfig(): Optional<BetaOutputConfig> = outputConfig.getOptional("output_config")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun speed(): Optional<Speed> = speed.getOptional("speed")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun thinking(): Optional<Thinking> = thinking.getOptional("thinking")

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<Model> = model

    /**
     * Returns the raw JSON value of [maxTokens].
     *
     * Unlike [maxTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max_tokens") @ExcludeMissing fun _maxTokens(): JsonField<Long> = maxTokens

    /**
     * Returns the raw JSON value of [outputConfig].
     *
     * Unlike [outputConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("output_config")
    @ExcludeMissing
    fun _outputConfig(): JsonField<BetaOutputConfig> = outputConfig

    /**
     * Returns the raw JSON value of [speed].
     *
     * Unlike [speed], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("speed") @ExcludeMissing fun _speed(): JsonField<Speed> = speed

    /**
     * Returns the raw JSON value of [thinking].
     *
     * Unlike [thinking], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("thinking") @ExcludeMissing fun _thinking(): JsonField<Thinking> = thinking

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
         * Returns a mutable builder for constructing an instance of [BetaFallbackParam].
         *
         * The following fields are required:
         * ```java
         * .model()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaFallbackParam]. */
    class Builder internal constructor() {

        private var model: JsonField<Model>? = null
        private var maxTokens: JsonField<Long> = JsonMissing.of()
        private var outputConfig: JsonField<BetaOutputConfig> = JsonMissing.of()
        private var speed: JsonField<Speed> = JsonMissing.of()
        private var thinking: JsonField<Thinking> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaFallbackParam: BetaFallbackParam) = apply {
            model = betaFallbackParam.model
            maxTokens = betaFallbackParam.maxTokens
            outputConfig = betaFallbackParam.outputConfig
            speed = betaFallbackParam.speed
            thinking = betaFallbackParam.thinking
            additionalProperties = betaFallbackParam.additionalProperties.toMutableMap()
        }

        /**
         * The model that will complete your prompt.
         *
         * See [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
         * and options.
         */
        fun model(model: Model) = model(JsonField.of(model))

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [Model] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(model: JsonField<Model>) = apply { this.model = model }

        /**
         * Sets [model] to an arbitrary [String].
         *
         * You should usually call [model] with a well-typed [Model] constant instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(value: String) = model(Model.of(value))

        fun maxTokens(maxTokens: Long?) = maxTokens(JsonField.ofNullable(maxTokens))

        /**
         * Alias for [Builder.maxTokens].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxTokens(maxTokens: Long) = maxTokens(maxTokens as Long?)

        /** Alias for calling [Builder.maxTokens] with `maxTokens.orElse(null)`. */
        fun maxTokens(maxTokens: Optional<Long>) = maxTokens(maxTokens.getOrNull())

        /**
         * Sets [Builder.maxTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxTokens] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun maxTokens(maxTokens: JsonField<Long>) = apply { this.maxTokens = maxTokens }

        fun outputConfig(outputConfig: BetaOutputConfig?) =
            outputConfig(JsonField.ofNullable(outputConfig))

        /** Alias for calling [Builder.outputConfig] with `outputConfig.orElse(null)`. */
        fun outputConfig(outputConfig: Optional<BetaOutputConfig>) =
            outputConfig(outputConfig.getOrNull())

        /**
         * Sets [Builder.outputConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputConfig] with a well-typed [BetaOutputConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun outputConfig(outputConfig: JsonField<BetaOutputConfig>) = apply {
            this.outputConfig = outputConfig
        }

        fun speed(speed: Speed?) = speed(JsonField.ofNullable(speed))

        /** Alias for calling [Builder.speed] with `speed.orElse(null)`. */
        fun speed(speed: Optional<Speed>) = speed(speed.getOrNull())

        /**
         * Sets [Builder.speed] to an arbitrary JSON value.
         *
         * You should usually call [Builder.speed] with a well-typed [Speed] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun speed(speed: JsonField<Speed>) = apply { this.speed = speed }

        fun thinking(thinking: Thinking?) = thinking(JsonField.ofNullable(thinking))

        /** Alias for calling [Builder.thinking] with `thinking.orElse(null)`. */
        fun thinking(thinking: Optional<Thinking>) = thinking(thinking.getOrNull())

        /**
         * Sets [Builder.thinking] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinking] with a well-typed [Thinking] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun thinking(thinking: JsonField<Thinking>) = apply { this.thinking = thinking }

        /** Alias for calling [thinking] with `Thinking.ofEnabled(enabled)`. */
        fun thinking(enabled: BetaThinkingConfigEnabled) = thinking(Thinking.ofEnabled(enabled))

        /**
         * Alias for calling [thinking] with the following:
         * ```java
         * BetaThinkingConfigEnabled.builder()
         *     .budgetTokens(budgetTokens)
         *     .build()
         * ```
         */
        fun enabledThinking(budgetTokens: Long) =
            thinking(BetaThinkingConfigEnabled.builder().budgetTokens(budgetTokens).build())

        /** Alias for calling [thinking] with `Thinking.ofDisabled(disabled)`. */
        fun thinking(disabled: BetaThinkingConfigDisabled) = thinking(Thinking.ofDisabled(disabled))

        /** Alias for calling [thinking] with `Thinking.ofAdaptive(adaptive)`. */
        fun thinking(adaptive: BetaThinkingConfigAdaptive) = thinking(Thinking.ofAdaptive(adaptive))

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
         * Returns an immutable instance of [BetaFallbackParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .model()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaFallbackParam =
            BetaFallbackParam(
                checkRequired("model", model),
                maxTokens,
                outputConfig,
                speed,
                thinking,
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
    fun validate(): BetaFallbackParam = apply {
        if (validated) {
            return@apply
        }

        model()
        maxTokens()
        outputConfig().ifPresent { it.validate() }
        speed().ifPresent { it.validate() }
        thinking().ifPresent { it.validate() }
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
        (if (model.asKnown().isPresent) 1 else 0) +
            (if (maxTokens.asKnown().isPresent) 1 else 0) +
            (outputConfig.asKnown().getOrNull()?.validity() ?: 0) +
            (speed.asKnown().getOrNull()?.validity() ?: 0) +
            (thinking.asKnown().getOrNull()?.validity() ?: 0)

    class Speed @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val STANDARD = of("standard")

            @JvmField val FAST = of("fast")

            @JvmStatic fun of(value: String) = Speed(JsonField.of(value))
        }

        /** An enum containing [Speed]'s known values. */
        enum class Known {
            STANDARD,
            FAST,
        }

        /**
         * An enum containing [Speed]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Speed] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            STANDARD,
            FAST,
            /** An enum member indicating that [Speed] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                STANDARD -> Value.STANDARD
                FAST -> Value.FAST
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                STANDARD -> Known.STANDARD
                FAST -> Known.FAST
                else -> throw AnthropicInvalidDataException("Unknown Speed: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
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
        fun validate(): Speed = apply {
            if (validated) {
                return@apply
            }

            known()
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
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Speed && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    @JsonDeserialize(using = Thinking.Deserializer::class)
    @JsonSerialize(using = Thinking.Serializer::class)
    class Thinking
    private constructor(
        private val enabled: BetaThinkingConfigEnabled? = null,
        private val disabled: BetaThinkingConfigDisabled? = null,
        private val adaptive: BetaThinkingConfigAdaptive? = null,
        private val _json: JsonValue? = null,
    ) {

        fun enabled(): Optional<BetaThinkingConfigEnabled> = Optional.ofNullable(enabled)

        fun disabled(): Optional<BetaThinkingConfigDisabled> = Optional.ofNullable(disabled)

        fun adaptive(): Optional<BetaThinkingConfigAdaptive> = Optional.ofNullable(adaptive)

        fun isEnabled(): Boolean = enabled != null

        fun isDisabled(): Boolean = disabled != null

        fun isAdaptive(): Boolean = adaptive != null

        fun asEnabled(): BetaThinkingConfigEnabled = enabled.getOrThrow("enabled")

        fun asDisabled(): BetaThinkingConfigDisabled = disabled.getOrThrow("disabled")

        fun asAdaptive(): BetaThinkingConfigAdaptive = adaptive.getOrThrow("adaptive")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = thinking.accept(new Thinking.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitEnabled(BetaThinkingConfigEnabled enabled) {
         *         return Optional.of(enabled.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                enabled != null -> visitor.visitEnabled(enabled)
                disabled != null -> visitor.visitDisabled(disabled)
                adaptive != null -> visitor.visitAdaptive(adaptive)
                else -> visitor.unknown(_json)
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
        fun validate(): Thinking = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitEnabled(enabled: BetaThinkingConfigEnabled) {
                        enabled.validate()
                    }

                    override fun visitDisabled(disabled: BetaThinkingConfigDisabled) {
                        disabled.validate()
                    }

                    override fun visitAdaptive(adaptive: BetaThinkingConfigAdaptive) {
                        adaptive.validate()
                    }
                }
            )
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
            accept(
                object : Visitor<Int> {
                    override fun visitEnabled(enabled: BetaThinkingConfigEnabled) =
                        enabled.validity()

                    override fun visitDisabled(disabled: BetaThinkingConfigDisabled) =
                        disabled.validity()

                    override fun visitAdaptive(adaptive: BetaThinkingConfigAdaptive) =
                        adaptive.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Thinking &&
                enabled == other.enabled &&
                disabled == other.disabled &&
                adaptive == other.adaptive
        }

        override fun hashCode(): Int = Objects.hash(enabled, disabled, adaptive)

        override fun toString(): String =
            when {
                enabled != null -> "Thinking{enabled=$enabled}"
                disabled != null -> "Thinking{disabled=$disabled}"
                adaptive != null -> "Thinking{adaptive=$adaptive}"
                _json != null -> "Thinking{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Thinking")
            }

        companion object {

            @JvmStatic
            fun ofEnabled(enabled: BetaThinkingConfigEnabled) = Thinking(enabled = enabled)

            @JvmStatic
            fun ofDisabled(disabled: BetaThinkingConfigDisabled) = Thinking(disabled = disabled)

            @JvmStatic
            fun ofAdaptive(adaptive: BetaThinkingConfigAdaptive) = Thinking(adaptive = adaptive)
        }

        /**
         * An interface that defines how to map each variant of [Thinking] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitEnabled(enabled: BetaThinkingConfigEnabled): T

            fun visitDisabled(disabled: BetaThinkingConfigDisabled): T

            fun visitAdaptive(adaptive: BetaThinkingConfigAdaptive): T

            /**
             * Maps an unknown variant of [Thinking] to a value of type [T].
             *
             * An instance of [Thinking] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Thinking: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Thinking>(Thinking::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Thinking {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "enabled" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaThinkingConfigEnabled>())
                            ?.let { Thinking(enabled = it, _json = json) } ?: Thinking(_json = json)
                    }
                    "disabled" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaThinkingConfigDisabled>())
                            ?.let { Thinking(disabled = it, _json = json) }
                            ?: Thinking(_json = json)
                    }
                    "adaptive" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaThinkingConfigAdaptive>())
                            ?.let { Thinking(adaptive = it, _json = json) }
                            ?: Thinking(_json = json)
                    }
                }

                return Thinking(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Thinking>(Thinking::class) {

            override fun serialize(
                value: Thinking,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.enabled != null -> generator.writeObject(value.enabled)
                    value.disabled != null -> generator.writeObject(value.disabled)
                    value.adaptive != null -> generator.writeObject(value.adaptive)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Thinking")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaFallbackParam &&
            model == other.model &&
            maxTokens == other.maxTokens &&
            outputConfig == other.outputConfig &&
            speed == other.speed &&
            thinking == other.thinking &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(model, maxTokens, outputConfig, speed, thinking, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaFallbackParam{model=$model, maxTokens=$maxTokens, outputConfig=$outputConfig, speed=$speed, thinking=$thinking, additionalProperties=$additionalProperties}"
}
