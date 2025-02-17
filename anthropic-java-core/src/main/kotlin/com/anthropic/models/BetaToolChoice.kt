// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

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
 * tool, or decide by itself.
 */
@JsonDeserialize(using = BetaToolChoice.Deserializer::class)
@JsonSerialize(using = BetaToolChoice.Serializer::class)
class BetaToolChoice
private constructor(
    private val auto: BetaToolChoiceAuto? = null,
    private val any: BetaToolChoiceAny? = null,
    private val tool: BetaToolChoiceTool? = null,
    private val _json: JsonValue? = null,
) {

    /** The model will automatically decide whether to use tools. */
    fun auto(): Optional<BetaToolChoiceAuto> = Optional.ofNullable(auto)

    /** The model will use any available tools. */
    fun any(): Optional<BetaToolChoiceAny> = Optional.ofNullable(any)

    /** The model will use the specified tool with `tool_choice.name`. */
    fun tool(): Optional<BetaToolChoiceTool> = Optional.ofNullable(tool)

    fun isAuto(): Boolean = auto != null

    fun isAny(): Boolean = any != null

    fun isTool(): Boolean = tool != null

    /** The model will automatically decide whether to use tools. */
    fun asAuto(): BetaToolChoiceAuto = auto.getOrThrow("auto")

    /** The model will use any available tools. */
    fun asAny(): BetaToolChoiceAny = any.getOrThrow("any")

    /** The model will use the specified tool with `tool_choice.name`. */
    fun asTool(): BetaToolChoiceTool = tool.getOrThrow("tool")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            auto != null -> visitor.visitAuto(auto)
            any != null -> visitor.visitAny(any)
            tool != null -> visitor.visitTool(tool)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

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
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaToolChoice && auto == other.auto && any == other.any && tool == other.tool /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(auto, any, tool) /* spotless:on */

    override fun toString(): String =
        when {
            auto != null -> "BetaToolChoice{auto=$auto}"
            any != null -> "BetaToolChoice{any=$any}"
            tool != null -> "BetaToolChoice{tool=$tool}"
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
                    tryDeserialize(node, jacksonTypeRef<BetaToolChoiceAuto>()) { it.validate() }
                        ?.let {
                            return BetaToolChoice(auto = it, _json = json)
                        }
                }
                "any" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaToolChoiceAny>()) { it.validate() }
                        ?.let {
                            return BetaToolChoice(any = it, _json = json)
                        }
                }
                "tool" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaToolChoiceTool>()) { it.validate() }
                        ?.let {
                            return BetaToolChoice(tool = it, _json = json)
                        }
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
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaToolChoice")
            }
        }
    }
}
