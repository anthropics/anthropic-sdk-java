// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
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
@JsonDeserialize(using = ToolChoice.Deserializer::class)
@JsonSerialize(using = ToolChoice.Serializer::class)
class ToolChoice
private constructor(
    private val auto: ToolChoiceAuto? = null,
    private val any: ToolChoiceAny? = null,
    private val tool: ToolChoiceTool? = null,
    private val none: ToolChoiceNone? = null,
    private val _json: JsonValue? = null,
) {

    /** The model will automatically decide whether to use tools. */
    fun auto(): Optional<ToolChoiceAuto> = Optional.ofNullable(auto)

    /** The model will use any available tools. */
    fun any(): Optional<ToolChoiceAny> = Optional.ofNullable(any)

    /** The model will use the specified tool with `tool_choice.name`. */
    fun tool(): Optional<ToolChoiceTool> = Optional.ofNullable(tool)

    /** The model will not be allowed to use tools. */
    fun none(): Optional<ToolChoiceNone> = Optional.ofNullable(none)

    fun isAuto(): Boolean = auto != null

    fun isAny(): Boolean = any != null

    fun isTool(): Boolean = tool != null

    fun isNone(): Boolean = none != null

    /** The model will automatically decide whether to use tools. */
    fun asAuto(): ToolChoiceAuto = auto.getOrThrow("auto")

    /** The model will use any available tools. */
    fun asAny(): ToolChoiceAny = any.getOrThrow("any")

    /** The model will use the specified tool with `tool_choice.name`. */
    fun asTool(): ToolChoiceTool = tool.getOrThrow("tool")

    /** The model will not be allowed to use tools. */
    fun asNone(): ToolChoiceNone = none.getOrThrow("none")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            auto != null -> visitor.visitAuto(auto)
            any != null -> visitor.visitAny(any)
            tool != null -> visitor.visitTool(tool)
            none != null -> visitor.visitNone(none)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): ToolChoice = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitAuto(auto: ToolChoiceAuto) {
                    auto.validate()
                }

                override fun visitAny(any: ToolChoiceAny) {
                    any.validate()
                }

                override fun visitTool(tool: ToolChoiceTool) {
                    tool.validate()
                }

                override fun visitNone(none: ToolChoiceNone) {
                    none.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ToolChoice && auto == other.auto && any == other.any && tool == other.tool && none == other.none /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(auto, any, tool, none) /* spotless:on */

    override fun toString(): String =
        when {
            auto != null -> "ToolChoice{auto=$auto}"
            any != null -> "ToolChoice{any=$any}"
            tool != null -> "ToolChoice{tool=$tool}"
            none != null -> "ToolChoice{none=$none}"
            _json != null -> "ToolChoice{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ToolChoice")
        }

    companion object {

        /** The model will automatically decide whether to use tools. */
        @JvmStatic fun ofAuto(auto: ToolChoiceAuto) = ToolChoice(auto = auto)

        /** The model will use any available tools. */
        @JvmStatic fun ofAny(any: ToolChoiceAny) = ToolChoice(any = any)

        /** The model will use the specified tool with `tool_choice.name`. */
        @JvmStatic fun ofTool(tool: ToolChoiceTool) = ToolChoice(tool = tool)

        /** The model will not be allowed to use tools. */
        @JvmStatic fun ofNone(none: ToolChoiceNone) = ToolChoice(none = none)
    }

    /** An interface that defines how to map each variant of [ToolChoice] to a value of type [T]. */
    interface Visitor<out T> {

        /** The model will automatically decide whether to use tools. */
        fun visitAuto(auto: ToolChoiceAuto): T

        /** The model will use any available tools. */
        fun visitAny(any: ToolChoiceAny): T

        /** The model will use the specified tool with `tool_choice.name`. */
        fun visitTool(tool: ToolChoiceTool): T

        /** The model will not be allowed to use tools. */
        fun visitNone(none: ToolChoiceNone): T

        /**
         * Maps an unknown variant of [ToolChoice] to a value of type [T].
         *
         * An instance of [ToolChoice] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ToolChoice: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<ToolChoice>(ToolChoice::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ToolChoice {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "auto" -> {
                    return ToolChoice(
                        auto = deserialize(node, jacksonTypeRef<ToolChoiceAuto>()),
                        _json = json,
                    )
                }
                "any" -> {
                    return ToolChoice(
                        any = deserialize(node, jacksonTypeRef<ToolChoiceAny>()),
                        _json = json,
                    )
                }
                "tool" -> {
                    return ToolChoice(
                        tool = deserialize(node, jacksonTypeRef<ToolChoiceTool>()),
                        _json = json,
                    )
                }
                "none" -> {
                    return ToolChoice(
                        none = deserialize(node, jacksonTypeRef<ToolChoiceNone>()),
                        _json = json,
                    )
                }
            }

            return ToolChoice(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<ToolChoice>(ToolChoice::class) {

        override fun serialize(
            value: ToolChoice,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.auto != null -> generator.writeObject(value.auto)
                value.any != null -> generator.writeObject(value.any)
                value.tool != null -> generator.writeObject(value.tool)
                value.none != null -> generator.writeObject(value.none)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ToolChoice")
            }
        }
    }
}
