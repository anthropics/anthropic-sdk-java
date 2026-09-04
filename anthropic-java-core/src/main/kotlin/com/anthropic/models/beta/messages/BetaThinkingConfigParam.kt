// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Configuration for enabling Claude's extended thinking.
 *
 * When enabled, responses include `thinking` content blocks showing Claude's thinking process
 * before the final answer. Requires a minimum budget of 1,024 tokens and counts towards your
 * `max_tokens` limit.
 *
 * See [extended thinking](https://platform.claude.com/docs/en/build-with-claude/extended-thinking)
 * for details.
 */
@JsonDeserialize(using = BetaThinkingConfigParam.Deserializer::class)
@JsonSerialize(using = BetaThinkingConfigParam.Serializer::class)
class BetaThinkingConfigParam
private constructor(
    private val enabled: BetaThinkingConfigEnabled? = null,
    private val disabled: BetaThinkingConfigDisabled? = null,
    private val adaptive: BetaThinkingConfigAdaptive? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitEnabled(enabled: BetaThinkingConfigEnabled): Type = Type.ENABLED

                override fun visitDisabled(disabled: BetaThinkingConfigDisabled): Type =
                    Type.DISABLED

                override fun visitAdaptive(adaptive: BetaThinkingConfigAdaptive): Type =
                    Type.ADAPTIVE

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun blockBinding(): Optional<BetaThinkingBlockBinding> =
        accept(
            object : Visitor<Optional<BetaThinkingBlockBinding>> {
                override fun visitEnabled(
                    enabled: BetaThinkingConfigEnabled
                ): Optional<BetaThinkingBlockBinding> = enabled.blockBinding()

                override fun visitDisabled(
                    disabled: BetaThinkingConfigDisabled
                ): Optional<BetaThinkingBlockBinding> = Optional.empty()

                override fun visitAdaptive(
                    adaptive: BetaThinkingConfigAdaptive
                ): Optional<BetaThinkingBlockBinding> = adaptive.blockBinding()

                override fun unknown(json: JsonValue?): Optional<BetaThinkingBlockBinding> =
                    json.getProperty<BetaThinkingBlockBinding>("block_binding").asKnown()
            }
        )

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
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaThinkingConfigParam.accept(new BetaThinkingConfigParam.Visitor<Optional<String>>() {
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
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
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
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaThinkingConfigParam = apply {
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitEnabled(enabled: BetaThinkingConfigEnabled) = enabled.validity()

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

        return other is BetaThinkingConfigParam &&
            enabled == other.enabled &&
            disabled == other.disabled &&
            adaptive == other.adaptive
    }

    override fun hashCode(): Int = Objects.hash(enabled, disabled, adaptive)

    override fun toString(): String =
        when {
            enabled != null -> "BetaThinkingConfigParam{enabled=$enabled}"
            disabled != null -> "BetaThinkingConfigParam{disabled=$disabled}"
            adaptive != null -> "BetaThinkingConfigParam{adaptive=$adaptive}"
            _json != null -> "BetaThinkingConfigParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaThinkingConfigParam")
        }

    companion object {

        @JvmStatic
        fun ofEnabled(enabled: BetaThinkingConfigEnabled) =
            BetaThinkingConfigParam(enabled = enabled)

        /**
         * Returns an immutable instance of [BetaThinkingConfigParam] whose [ofEnabled] variant is
         * built from the given required [budgetTokens].
         */
        @JvmStatic
        fun ofEnabled(budgetTokens: Long) = ofEnabled(BetaThinkingConfigEnabled.of(budgetTokens))

        @JvmStatic
        fun ofDisabled(disabled: BetaThinkingConfigDisabled) =
            BetaThinkingConfigParam(disabled = disabled)

        @JvmStatic
        fun ofAdaptive(adaptive: BetaThinkingConfigAdaptive) =
            BetaThinkingConfigParam(adaptive = adaptive)
    }

    /**
     * An interface that defines how to map each variant of [BetaThinkingConfigParam] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitEnabled(enabled: BetaThinkingConfigEnabled): T

        fun visitDisabled(disabled: BetaThinkingConfigDisabled): T

        fun visitAdaptive(adaptive: BetaThinkingConfigAdaptive): T

        /**
         * Maps an unknown variant of [BetaThinkingConfigParam] to a value of type [T].
         *
         * An instance of [BetaThinkingConfigParam] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaThinkingConfigParam: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaThinkingConfigParam>(BetaThinkingConfigParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaThinkingConfigParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "enabled" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaThinkingConfigEnabled>())?.let {
                        BetaThinkingConfigParam(enabled = it, _json = json)
                    } ?: BetaThinkingConfigParam(_json = json)
                }
                "disabled" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaThinkingConfigDisabled>())?.let {
                        BetaThinkingConfigParam(disabled = it, _json = json)
                    } ?: BetaThinkingConfigParam(_json = json)
                }
                "adaptive" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaThinkingConfigAdaptive>())?.let {
                        BetaThinkingConfigParam(adaptive = it, _json = json)
                    } ?: BetaThinkingConfigParam(_json = json)
                }
            }

            return BetaThinkingConfigParam(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaThinkingConfigParam>(BetaThinkingConfigParam::class) {

        override fun serialize(
            value: BetaThinkingConfigParam,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.enabled != null -> generator.writeObject(value.enabled)
                value.disabled != null -> generator.writeObject(value.disabled)
                value.adaptive != null -> generator.writeObject(value.adaptive)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaThinkingConfigParam")
            }
        }
    }

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val ENABLED = of("enabled")

            @JvmField val DISABLED = of("disabled")

            @JvmField val ADAPTIVE = of("adaptive")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            ENABLED,
            DISABLED,
            ADAPTIVE,
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ENABLED,
            DISABLED,
            ADAPTIVE,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
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
                ENABLED -> Value.ENABLED
                DISABLED -> Value.DISABLED
                ADAPTIVE -> Value.ADAPTIVE
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
                ENABLED -> Known.ENABLED
                DISABLED -> Known.DISABLED
                ADAPTIVE -> Known.ADAPTIVE
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
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
        fun validate(): Type = apply {
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

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
