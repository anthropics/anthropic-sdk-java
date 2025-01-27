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

@JsonDeserialize(using = BetaContentBlock.Deserializer::class)
@JsonSerialize(using = BetaContentBlock.Serializer::class)
class BetaContentBlock
private constructor(
    private val text: BetaTextBlock? = null,
    private val toolUse: BetaToolUseBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun toParam(): BetaContentBlockParam =
        accept(
            object : Visitor<BetaContentBlockParam> {
                override fun visitText(text: BetaTextBlock): BetaContentBlockParam =
                    BetaContentBlockParam.ofText(text.toParam())

                override fun visitToolUse(toolUse: BetaToolUseBlock): BetaContentBlockParam =
                    BetaContentBlockParam.ofToolUse(toolUse.toParam())
            }
        )

    fun text(): Optional<BetaTextBlock> = Optional.ofNullable(text)

    fun toolUse(): Optional<BetaToolUseBlock> = Optional.ofNullable(toolUse)

    fun isText(): Boolean = text != null

    fun isToolUse(): Boolean = toolUse != null

    fun asText(): BetaTextBlock = text.getOrThrow("text")

    fun asToolUse(): BetaToolUseBlock = toolUse.getOrThrow("toolUse")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            text != null -> visitor.visitText(text)
            toolUse != null -> visitor.visitToolUse(toolUse)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): BetaContentBlock = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitText(text: BetaTextBlock) {
                    text.validate()
                }

                override fun visitToolUse(toolUse: BetaToolUseBlock) {
                    toolUse.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaContentBlock && text == other.text && toolUse == other.toolUse /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(text, toolUse) /* spotless:on */

    override fun toString(): String =
        when {
            text != null -> "BetaContentBlock{text=$text}"
            toolUse != null -> "BetaContentBlock{toolUse=$toolUse}"
            _json != null -> "BetaContentBlock{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaContentBlock")
        }

    companion object {

        @JvmStatic fun ofText(text: BetaTextBlock) = BetaContentBlock(text = text)

        @JvmStatic fun ofToolUse(toolUse: BetaToolUseBlock) = BetaContentBlock(toolUse = toolUse)
    }

    /**
     * An interface that defines how to map each variant of [BetaContentBlock] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitText(text: BetaTextBlock): T

        fun visitToolUse(toolUse: BetaToolUseBlock): T

        /**
         * Maps an unknown variant of [BetaContentBlock] to a value of type [T].
         *
         * An instance of [BetaContentBlock] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaContentBlock: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaContentBlock>(BetaContentBlock::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaContentBlock {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaTextBlock>()) { it.validate() }
                        ?.let {
                            return BetaContentBlock(text = it, _json = json)
                        }
                }
                "tool_use" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaToolUseBlock>()) { it.validate() }
                        ?.let {
                            return BetaContentBlock(toolUse = it, _json = json)
                        }
                }
            }

            return BetaContentBlock(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaContentBlock>(BetaContentBlock::class) {

        override fun serialize(
            value: BetaContentBlock,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.text != null -> generator.writeObject(value.text)
                value.toolUse != null -> generator.writeObject(value.toolUse)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlock")
            }
        }
    }
}
