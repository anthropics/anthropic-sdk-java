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

@JsonDeserialize(using = ToolUnion.Deserializer::class)
@JsonSerialize(using = ToolUnion.Serializer::class)
class ToolUnion
private constructor(
    private val tool: Tool? = null,
    private val bash20250124: ToolBash20250124? = null,
    private val textEditor20250124: ToolTextEditor20250124? = null,
    private val _json: JsonValue? = null,
) {

    fun tool(): Optional<Tool> = Optional.ofNullable(tool)

    fun bash20250124(): Optional<ToolBash20250124> = Optional.ofNullable(bash20250124)

    fun textEditor20250124(): Optional<ToolTextEditor20250124> =
        Optional.ofNullable(textEditor20250124)

    fun isTool(): Boolean = tool != null

    fun isBash20250124(): Boolean = bash20250124 != null

    fun isTextEditor20250124(): Boolean = textEditor20250124 != null

    fun asTool(): Tool = tool.getOrThrow("tool")

    fun asBash20250124(): ToolBash20250124 = bash20250124.getOrThrow("bash20250124")

    fun asTextEditor20250124(): ToolTextEditor20250124 =
        textEditor20250124.getOrThrow("textEditor20250124")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            tool != null -> visitor.visitTool(tool)
            bash20250124 != null -> visitor.visitBash20250124(bash20250124)
            textEditor20250124 != null -> visitor.visitTextEditor20250124(textEditor20250124)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): ToolUnion = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitTool(tool: Tool) {
                    tool.validate()
                }

                override fun visitBash20250124(bash20250124: ToolBash20250124) {
                    bash20250124.validate()
                }

                override fun visitTextEditor20250124(textEditor20250124: ToolTextEditor20250124) {
                    textEditor20250124.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ToolUnion && tool == other.tool && bash20250124 == other.bash20250124 && textEditor20250124 == other.textEditor20250124 /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(tool, bash20250124, textEditor20250124) /* spotless:on */

    override fun toString(): String =
        when {
            tool != null -> "ToolUnion{tool=$tool}"
            bash20250124 != null -> "ToolUnion{bash20250124=$bash20250124}"
            textEditor20250124 != null -> "ToolUnion{textEditor20250124=$textEditor20250124}"
            _json != null -> "ToolUnion{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ToolUnion")
        }

    companion object {

        @JvmStatic fun ofTool(tool: Tool) = ToolUnion(tool = tool)

        @JvmStatic
        fun ofBash20250124(bash20250124: ToolBash20250124) = ToolUnion(bash20250124 = bash20250124)

        @JvmStatic
        fun ofTextEditor20250124(textEditor20250124: ToolTextEditor20250124) =
            ToolUnion(textEditor20250124 = textEditor20250124)
    }

    /** An interface that defines how to map each variant of [ToolUnion] to a value of type [T]. */
    interface Visitor<out T> {

        fun visitTool(tool: Tool): T

        fun visitBash20250124(bash20250124: ToolBash20250124): T

        fun visitTextEditor20250124(textEditor20250124: ToolTextEditor20250124): T

        /**
         * Maps an unknown variant of [ToolUnion] to a value of type [T].
         *
         * An instance of [ToolUnion] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ToolUnion: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<ToolUnion>(ToolUnion::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ToolUnion {
            val json = JsonValue.fromJsonNode(node)

            tryDeserialize(node, jacksonTypeRef<Tool>()) { it.validate() }
                ?.let {
                    return ToolUnion(tool = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<ToolBash20250124>()) { it.validate() }
                ?.let {
                    return ToolUnion(bash20250124 = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250124>()) { it.validate() }
                ?.let {
                    return ToolUnion(textEditor20250124 = it, _json = json)
                }

            return ToolUnion(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<ToolUnion>(ToolUnion::class) {

        override fun serialize(
            value: ToolUnion,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.tool != null -> generator.writeObject(value.tool)
                value.bash20250124 != null -> generator.writeObject(value.bash20250124)
                value.textEditor20250124 != null -> generator.writeObject(value.textEditor20250124)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ToolUnion")
            }
        }
    }
}
