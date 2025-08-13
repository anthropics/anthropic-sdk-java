// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
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
    private val textEditor20250429: ToolTextEditor20250429? = null,
    private val textEditor20250728: ToolTextEditor20250728? = null,
    private val webSearchTool20250305: WebSearchTool20250305? = null,
    private val _json: JsonValue? = null,
) {

    fun tool(): Optional<Tool> = Optional.ofNullable(tool)

    fun bash20250124(): Optional<ToolBash20250124> = Optional.ofNullable(bash20250124)

    fun textEditor20250124(): Optional<ToolTextEditor20250124> =
        Optional.ofNullable(textEditor20250124)

    fun textEditor20250429(): Optional<ToolTextEditor20250429> =
        Optional.ofNullable(textEditor20250429)

    fun textEditor20250728(): Optional<ToolTextEditor20250728> =
        Optional.ofNullable(textEditor20250728)

    fun webSearchTool20250305(): Optional<WebSearchTool20250305> =
        Optional.ofNullable(webSearchTool20250305)

    fun isTool(): Boolean = tool != null

    fun isBash20250124(): Boolean = bash20250124 != null

    fun isTextEditor20250124(): Boolean = textEditor20250124 != null

    fun isTextEditor20250429(): Boolean = textEditor20250429 != null

    fun isTextEditor20250728(): Boolean = textEditor20250728 != null

    fun isWebSearchTool20250305(): Boolean = webSearchTool20250305 != null

    fun asTool(): Tool = tool.getOrThrow("tool")

    fun asBash20250124(): ToolBash20250124 = bash20250124.getOrThrow("bash20250124")

    fun asTextEditor20250124(): ToolTextEditor20250124 =
        textEditor20250124.getOrThrow("textEditor20250124")

    fun asTextEditor20250429(): ToolTextEditor20250429 =
        textEditor20250429.getOrThrow("textEditor20250429")

    fun asTextEditor20250728(): ToolTextEditor20250728 =
        textEditor20250728.getOrThrow("textEditor20250728")

    fun asWebSearchTool20250305(): WebSearchTool20250305 =
        webSearchTool20250305.getOrThrow("webSearchTool20250305")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            tool != null -> visitor.visitTool(tool)
            bash20250124 != null -> visitor.visitBash20250124(bash20250124)
            textEditor20250124 != null -> visitor.visitTextEditor20250124(textEditor20250124)
            textEditor20250429 != null -> visitor.visitTextEditor20250429(textEditor20250429)
            textEditor20250728 != null -> visitor.visitTextEditor20250728(textEditor20250728)
            webSearchTool20250305 != null ->
                visitor.visitWebSearchTool20250305(webSearchTool20250305)
            else -> visitor.unknown(_json)
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

                override fun visitTextEditor20250429(textEditor20250429: ToolTextEditor20250429) {
                    textEditor20250429.validate()
                }

                override fun visitTextEditor20250728(textEditor20250728: ToolTextEditor20250728) {
                    textEditor20250728.validate()
                }

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: WebSearchTool20250305
                ) {
                    webSearchTool20250305.validate()
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
                override fun visitTool(tool: Tool) = tool.validity()

                override fun visitBash20250124(bash20250124: ToolBash20250124) =
                    bash20250124.validity()

                override fun visitTextEditor20250124(textEditor20250124: ToolTextEditor20250124) =
                    textEditor20250124.validity()

                override fun visitTextEditor20250429(textEditor20250429: ToolTextEditor20250429) =
                    textEditor20250429.validity()

                override fun visitTextEditor20250728(textEditor20250728: ToolTextEditor20250728) =
                    textEditor20250728.validity()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: WebSearchTool20250305
                ) = webSearchTool20250305.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ToolUnion &&
            tool == other.tool &&
            bash20250124 == other.bash20250124 &&
            textEditor20250124 == other.textEditor20250124 &&
            textEditor20250429 == other.textEditor20250429 &&
            textEditor20250728 == other.textEditor20250728 &&
            webSearchTool20250305 == other.webSearchTool20250305
    }

    override fun hashCode(): Int =
        Objects.hash(
            tool,
            bash20250124,
            textEditor20250124,
            textEditor20250429,
            textEditor20250728,
            webSearchTool20250305,
        )

    override fun toString(): String =
        when {
            tool != null -> "ToolUnion{tool=$tool}"
            bash20250124 != null -> "ToolUnion{bash20250124=$bash20250124}"
            textEditor20250124 != null -> "ToolUnion{textEditor20250124=$textEditor20250124}"
            textEditor20250429 != null -> "ToolUnion{textEditor20250429=$textEditor20250429}"
            textEditor20250728 != null -> "ToolUnion{textEditor20250728=$textEditor20250728}"
            webSearchTool20250305 != null ->
                "ToolUnion{webSearchTool20250305=$webSearchTool20250305}"
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

        @JvmStatic
        fun ofTextEditor20250429(textEditor20250429: ToolTextEditor20250429) =
            ToolUnion(textEditor20250429 = textEditor20250429)

        @JvmStatic
        fun ofTextEditor20250728(textEditor20250728: ToolTextEditor20250728) =
            ToolUnion(textEditor20250728 = textEditor20250728)

        @JvmStatic
        fun ofWebSearchTool20250305(webSearchTool20250305: WebSearchTool20250305) =
            ToolUnion(webSearchTool20250305 = webSearchTool20250305)
    }

    /** An interface that defines how to map each variant of [ToolUnion] to a value of type [T]. */
    interface Visitor<out T> {

        fun visitTool(tool: Tool): T

        fun visitBash20250124(bash20250124: ToolBash20250124): T

        fun visitTextEditor20250124(textEditor20250124: ToolTextEditor20250124): T

        fun visitTextEditor20250429(textEditor20250429: ToolTextEditor20250429): T

        fun visitTextEditor20250728(textEditor20250728: ToolTextEditor20250728): T

        fun visitWebSearchTool20250305(webSearchTool20250305: WebSearchTool20250305): T

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

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<Tool>())?.let {
                            ToolUnion(tool = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolBash20250124>())?.let {
                            ToolUnion(bash20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250124>())?.let {
                            ToolUnion(textEditor20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250429>())?.let {
                            ToolUnion(textEditor20250429 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250728>())?.let {
                            ToolUnion(textEditor20250728 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<WebSearchTool20250305>())?.let {
                            ToolUnion(webSearchTool20250305 = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> ToolUnion(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
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
                value.textEditor20250429 != null -> generator.writeObject(value.textEditor20250429)
                value.textEditor20250728 != null -> generator.writeObject(value.textEditor20250728)
                value.webSearchTool20250305 != null ->
                    generator.writeObject(value.webSearchTool20250305)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ToolUnion")
            }
        }
    }
}
