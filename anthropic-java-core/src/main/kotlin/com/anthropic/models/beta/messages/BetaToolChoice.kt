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
 * How the model should use the provided tools. The model can use a specific tool, any available
 * tool, decide by itself, or not use tools at all.
 */
@JsonDeserialize(using = BetaToolChoice.Deserializer::class)
@JsonSerialize(using = BetaToolChoice.Serializer::class)
class BetaToolChoice
private constructor(
    private val auto: BetaToolChoiceAuto? = null,
    private val any: BetaToolChoiceAny? = null,
    private val tool: BetaToolChoiceTool? = null,
    private val none: BetaToolChoiceNone? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitAuto(auto: BetaToolChoiceAuto): Type = Type.AUTO

                override fun visitAny(any: BetaToolChoiceAny): Type = Type.ANY

                override fun visitTool(tool: BetaToolChoiceTool): Type = Type.TOOL

                override fun visitNone(none: BetaToolChoiceNone): Type = Type.NONE

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun disableParallelToolUse(): Optional<Boolean> =
        accept(
            object : Visitor<Optional<Boolean>> {
                override fun visitAuto(auto: BetaToolChoiceAuto): Optional<Boolean> =
                    auto.disableParallelToolUse()

                override fun visitAny(any: BetaToolChoiceAny): Optional<Boolean> =
                    any.disableParallelToolUse()

                override fun visitTool(tool: BetaToolChoiceTool): Optional<Boolean> =
                    tool.disableParallelToolUse()

                override fun visitNone(none: BetaToolChoiceNone): Optional<Boolean> =
                    Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Boolean> =
                    json.getProperty<Boolean>("disable_parallel_tool_use").asKnown()
            }
        )

    /** The model will automatically decide whether to use tools. */
    fun auto(): Optional<BetaToolChoiceAuto> = Optional.ofNullable(auto)

    /** The model will use any available tools. */
    fun any(): Optional<BetaToolChoiceAny> = Optional.ofNullable(any)

    /** The model will use the specified tool with `tool_choice.name`. */
    fun tool(): Optional<BetaToolChoiceTool> = Optional.ofNullable(tool)

    /** The model will not be allowed to use tools. */
    fun none(): Optional<BetaToolChoiceNone> = Optional.ofNullable(none)

    fun isAuto(): Boolean = auto != null

    fun isAny(): Boolean = any != null

    fun isTool(): Boolean = tool != null

    fun isNone(): Boolean = none != null

    /** The model will automatically decide whether to use tools. */
    fun asAuto(): BetaToolChoiceAuto = auto.getOrThrow("auto")

    /** The model will use any available tools. */
    fun asAny(): BetaToolChoiceAny = any.getOrThrow("any")

    /** The model will use the specified tool with `tool_choice.name`. */
    fun asTool(): BetaToolChoiceTool = tool.getOrThrow("tool")

    /** The model will not be allowed to use tools. */
    fun asNone(): BetaToolChoiceNone = none.getOrThrow("none")

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
     * Optional<String> result = betaToolChoice.accept(new BetaToolChoice.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAuto(BetaToolChoiceAuto auto) {
     *         return Optional.of(auto.toString());
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
            auto != null -> visitor.visitAuto(auto)
            any != null -> visitor.visitAny(any)
            tool != null -> visitor.visitTool(tool)
            none != null -> visitor.visitNone(none)
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
    fun validate(): BetaToolChoice = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitAuto(auto: BetaToolChoiceAuto) {
                    auto.validate()
                }

                override fun visitAny(any: BetaToolChoiceAny) {
                    any.validate()
                }

                override fun visitTool(tool: BetaToolChoiceTool) {
                    tool.validate()
                }

                override fun visitNone(none: BetaToolChoiceNone) {
                    none.validate()
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
                override fun visitAuto(auto: BetaToolChoiceAuto) = auto.validity()

                override fun visitAny(any: BetaToolChoiceAny) = any.validity()

                override fun visitTool(tool: BetaToolChoiceTool) = tool.validity()

                override fun visitNone(none: BetaToolChoiceNone) = none.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaToolChoice &&
            auto == other.auto &&
            any == other.any &&
            tool == other.tool &&
            none == other.none
    }

    override fun hashCode(): Int = Objects.hash(auto, any, tool, none)

    override fun toString(): String =
        when {
            auto != null -> "BetaToolChoice{auto=$auto}"
            any != null -> "BetaToolChoice{any=$any}"
            tool != null -> "BetaToolChoice{tool=$tool}"
            none != null -> "BetaToolChoice{none=$none}"
            _json != null -> "BetaToolChoice{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaToolChoice")
        }

    companion object {

        /** The model will automatically decide whether to use tools. */
        @JvmStatic fun ofAuto(auto: BetaToolChoiceAuto) = BetaToolChoice(auto = auto)

        /** The model will use any available tools. */
        @JvmStatic fun ofAny(any: BetaToolChoiceAny) = BetaToolChoice(any = any)

        /** The model will use the specified tool with `tool_choice.name`. */
        @JvmStatic fun ofTool(tool: BetaToolChoiceTool) = BetaToolChoice(tool = tool)

        /**
         * Returns an immutable instance of [BetaToolChoice] whose [ofTool] variant is built from
         * the given required [name].
         */
        @JvmStatic fun ofTool(name: String) = ofTool(BetaToolChoiceTool.of(name))

        /** The model will not be allowed to use tools. */
        @JvmStatic fun ofNone(none: BetaToolChoiceNone) = BetaToolChoice(none = none)
    }

    /**
     * An interface that defines how to map each variant of [BetaToolChoice] to a value of type [T].
     */
    interface Visitor<out T> {

        /** The model will automatically decide whether to use tools. */
        fun visitAuto(auto: BetaToolChoiceAuto): T

        /** The model will use any available tools. */
        fun visitAny(any: BetaToolChoiceAny): T

        /** The model will use the specified tool with `tool_choice.name`. */
        fun visitTool(tool: BetaToolChoiceTool): T

        /** The model will not be allowed to use tools. */
        fun visitNone(none: BetaToolChoiceNone): T

        /**
         * Maps an unknown variant of [BetaToolChoice] to a value of type [T].
         *
         * An instance of [BetaToolChoice] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaToolChoice: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaToolChoice>(BetaToolChoice::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaToolChoice {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "auto" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaToolChoiceAuto>())?.let {
                        BetaToolChoice(auto = it, _json = json)
                    } ?: BetaToolChoice(_json = json)
                }
                "any" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaToolChoiceAny>())?.let {
                        BetaToolChoice(any = it, _json = json)
                    } ?: BetaToolChoice(_json = json)
                }
                "tool" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaToolChoiceTool>())?.let {
                        BetaToolChoice(tool = it, _json = json)
                    } ?: BetaToolChoice(_json = json)
                }
                "none" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaToolChoiceNone>())?.let {
                        BetaToolChoice(none = it, _json = json)
                    } ?: BetaToolChoice(_json = json)
                }
            }

            return BetaToolChoice(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaToolChoice>(BetaToolChoice::class) {

        override fun serialize(
            value: BetaToolChoice,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.auto != null -> generator.writeObject(value.auto)
                value.any != null -> generator.writeObject(value.any)
                value.tool != null -> generator.writeObject(value.tool)
                value.none != null -> generator.writeObject(value.none)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaToolChoice")
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

            @JvmField val AUTO = of("auto")

            @JvmField val ANY = of("any")

            @JvmField val TOOL = of("tool")

            @JvmField val NONE = of("none")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            AUTO,
            ANY,
            TOOL,
            NONE,
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
            AUTO,
            ANY,
            TOOL,
            NONE,
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
                AUTO -> Value.AUTO
                ANY -> Value.ANY
                TOOL -> Value.TOOL
                NONE -> Value.NONE
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
                AUTO -> Known.AUTO
                ANY -> Known.ANY
                TOOL -> Known.TOOL
                NONE -> Known.NONE
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
