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

@JsonDeserialize(using = MessageCountTokensTool.Deserializer::class)
@JsonSerialize(using = MessageCountTokensTool.Serializer::class)
class MessageCountTokensTool
private constructor(
    private val tool: Tool? = null,
    private val toolBash20250124: ToolBash20250124? = null,
    private val toolTextEditor20250124: ToolTextEditor20250124? = null,
    private val _json: JsonValue? = null,
) {

    fun tool(): Optional<Tool> = Optional.ofNullable(tool)

    fun toolBash20250124(): Optional<ToolBash20250124> = Optional.ofNullable(toolBash20250124)

    fun toolTextEditor20250124(): Optional<ToolTextEditor20250124> =
        Optional.ofNullable(toolTextEditor20250124)

    fun isTool(): Boolean = tool != null

    fun isToolBash20250124(): Boolean = toolBash20250124 != null

    fun isToolTextEditor20250124(): Boolean = toolTextEditor20250124 != null

    fun asTool(): Tool = tool.getOrThrow("tool")

    fun asToolBash20250124(): ToolBash20250124 = toolBash20250124.getOrThrow("toolBash20250124")

    fun asToolTextEditor20250124(): ToolTextEditor20250124 =
        toolTextEditor20250124.getOrThrow("toolTextEditor20250124")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            tool != null -> visitor.visitTool(tool)
            toolBash20250124 != null -> visitor.visitToolBash20250124(toolBash20250124)
            toolTextEditor20250124 != null ->
                visitor.visitToolTextEditor20250124(toolTextEditor20250124)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): MessageCountTokensTool = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitTool(tool: Tool) {
                    tool.validate()
                }

                override fun visitToolBash20250124(toolBash20250124: ToolBash20250124) {
                    toolBash20250124.validate()
                }

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: ToolTextEditor20250124
                ) {
                    toolTextEditor20250124.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is MessageCountTokensTool && tool == other.tool && toolBash20250124 == other.toolBash20250124 && toolTextEditor20250124 == other.toolTextEditor20250124 /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(tool, toolBash20250124, toolTextEditor20250124) /* spotless:on */

    override fun toString(): String =
        when {
            tool != null -> "MessageCountTokensTool{tool=$tool}"
            toolBash20250124 != null -> "MessageCountTokensTool{toolBash20250124=$toolBash20250124}"
            toolTextEditor20250124 != null ->
                "MessageCountTokensTool{toolTextEditor20250124=$toolTextEditor20250124}"
            _json != null -> "MessageCountTokensTool{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid MessageCountTokensTool")
        }

    companion object {

        @JvmStatic fun ofTool(tool: Tool) = MessageCountTokensTool(tool = tool)

        @JvmStatic
        fun ofToolBash20250124(toolBash20250124: ToolBash20250124) =
            MessageCountTokensTool(toolBash20250124 = toolBash20250124)

        @JvmStatic
        fun ofToolTextEditor20250124(toolTextEditor20250124: ToolTextEditor20250124) =
            MessageCountTokensTool(toolTextEditor20250124 = toolTextEditor20250124)
    }

    /**
     * An interface that defines how to map each variant of [MessageCountTokensTool] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitTool(tool: Tool): T

        fun visitToolBash20250124(toolBash20250124: ToolBash20250124): T

        fun visitToolTextEditor20250124(toolTextEditor20250124: ToolTextEditor20250124): T

        /**
         * Maps an unknown variant of [MessageCountTokensTool] to a value of type [T].
         *
         * An instance of [MessageCountTokensTool] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown MessageCountTokensTool: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<MessageCountTokensTool>(MessageCountTokensTool::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): MessageCountTokensTool {
            val json = JsonValue.fromJsonNode(node)

            tryDeserialize(node, jacksonTypeRef<Tool>()) { it.validate() }
                ?.let {
                    return MessageCountTokensTool(tool = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<ToolBash20250124>()) { it.validate() }
                ?.let {
                    return MessageCountTokensTool(toolBash20250124 = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250124>()) { it.validate() }
                ?.let {
                    return MessageCountTokensTool(toolTextEditor20250124 = it, _json = json)
                }

            return MessageCountTokensTool(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<MessageCountTokensTool>(MessageCountTokensTool::class) {

        override fun serialize(
            value: MessageCountTokensTool,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.tool != null -> generator.writeObject(value.tool)
                value.toolBash20250124 != null -> generator.writeObject(value.toolBash20250124)
                value.toolTextEditor20250124 != null ->
                    generator.writeObject(value.toolTextEditor20250124)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid MessageCountTokensTool")
            }
        }
    }
}
