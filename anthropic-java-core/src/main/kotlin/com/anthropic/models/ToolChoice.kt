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
@JsonDeserialize(using = ToolChoice.Deserializer::class)
@JsonSerialize(using = ToolChoice.Serializer::class)
class ToolChoice
private constructor(
    private val toolChoiceAuto: ToolChoiceAuto? = null,
    private val toolChoiceAny: ToolChoiceAny? = null,
    private val toolChoiceTool: ToolChoiceTool? = null,
    private val _json: JsonValue? = null,
) {

    private var validated: Boolean = false

    /** The model will automatically decide whether to use tools. */
    fun toolChoiceAuto(): Optional<ToolChoiceAuto> = Optional.ofNullable(toolChoiceAuto)

    /** The model will use any available tools. */
    fun toolChoiceAny(): Optional<ToolChoiceAny> = Optional.ofNullable(toolChoiceAny)

    /** The model will use the specified tool with `tool_choice.name`. */
    fun toolChoiceTool(): Optional<ToolChoiceTool> = Optional.ofNullable(toolChoiceTool)

    fun isToolChoiceAuto(): Boolean = toolChoiceAuto != null

    fun isToolChoiceAny(): Boolean = toolChoiceAny != null

    fun isToolChoiceTool(): Boolean = toolChoiceTool != null

    /** The model will automatically decide whether to use tools. */
    fun asToolChoiceAuto(): ToolChoiceAuto = toolChoiceAuto.getOrThrow("toolChoiceAuto")

    /** The model will use any available tools. */
    fun asToolChoiceAny(): ToolChoiceAny = toolChoiceAny.getOrThrow("toolChoiceAny")

    /** The model will use the specified tool with `tool_choice.name`. */
    fun asToolChoiceTool(): ToolChoiceTool = toolChoiceTool.getOrThrow("toolChoiceTool")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            toolChoiceAuto != null -> visitor.visitToolChoiceAuto(toolChoiceAuto)
            toolChoiceAny != null -> visitor.visitToolChoiceAny(toolChoiceAny)
            toolChoiceTool != null -> visitor.visitToolChoiceTool(toolChoiceTool)
            else -> visitor.unknown(_json)
        }
    }

    fun validate(): ToolChoice = apply {
        if (!validated) {
            if (toolChoiceAuto == null && toolChoiceAny == null && toolChoiceTool == null) {
                throw AnthropicInvalidDataException("Unknown ToolChoice: $_json")
            }
            toolChoiceAuto?.validate()
            toolChoiceAny?.validate()
            toolChoiceTool?.validate()
            validated = true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ToolChoice && toolChoiceAuto == other.toolChoiceAuto && toolChoiceAny == other.toolChoiceAny && toolChoiceTool == other.toolChoiceTool /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(toolChoiceAuto, toolChoiceAny, toolChoiceTool) /* spotless:on */

    override fun toString(): String =
        when {
            toolChoiceAuto != null -> "ToolChoice{toolChoiceAuto=$toolChoiceAuto}"
            toolChoiceAny != null -> "ToolChoice{toolChoiceAny=$toolChoiceAny}"
            toolChoiceTool != null -> "ToolChoice{toolChoiceTool=$toolChoiceTool}"
            _json != null -> "ToolChoice{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ToolChoice")
        }

    companion object {

        /** The model will automatically decide whether to use tools. */
        @JvmStatic
        fun ofToolChoiceAuto(toolChoiceAuto: ToolChoiceAuto) =
            ToolChoice(toolChoiceAuto = toolChoiceAuto)

        /** The model will use any available tools. */
        @JvmStatic
        fun ofToolChoiceAny(toolChoiceAny: ToolChoiceAny) =
            ToolChoice(toolChoiceAny = toolChoiceAny)

        /** The model will use the specified tool with `tool_choice.name`. */
        @JvmStatic
        fun ofToolChoiceTool(toolChoiceTool: ToolChoiceTool) =
            ToolChoice(toolChoiceTool = toolChoiceTool)
    }

    interface Visitor<out T> {

        fun visitToolChoiceAuto(toolChoiceAuto: ToolChoiceAuto): T

        fun visitToolChoiceAny(toolChoiceAny: ToolChoiceAny): T

        fun visitToolChoiceTool(toolChoiceTool: ToolChoiceTool): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ToolChoice: $json")
        }
    }

    class Deserializer : BaseDeserializer<ToolChoice>(ToolChoice::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ToolChoice {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "auto" -> {
                    tryDeserialize(node, jacksonTypeRef<ToolChoiceAuto>()) { it.validate() }
                        ?.let {
                            return ToolChoice(toolChoiceAuto = it, _json = json)
                        }
                }
                "any" -> {
                    tryDeserialize(node, jacksonTypeRef<ToolChoiceAny>()) { it.validate() }
                        ?.let {
                            return ToolChoice(toolChoiceAny = it, _json = json)
                        }
                }
                "tool" -> {
                    tryDeserialize(node, jacksonTypeRef<ToolChoiceTool>()) { it.validate() }
                        ?.let {
                            return ToolChoice(toolChoiceTool = it, _json = json)
                        }
                }
            }

            return ToolChoice(_json = json)
        }
    }

    class Serializer : BaseSerializer<ToolChoice>(ToolChoice::class) {

        override fun serialize(
            value: ToolChoice,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.toolChoiceAuto != null -> generator.writeObject(value.toolChoiceAuto)
                value.toolChoiceAny != null -> generator.writeObject(value.toolChoiceAny)
                value.toolChoiceTool != null -> generator.writeObject(value.toolChoiceTool)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ToolChoice")
            }
        }
    }
}
