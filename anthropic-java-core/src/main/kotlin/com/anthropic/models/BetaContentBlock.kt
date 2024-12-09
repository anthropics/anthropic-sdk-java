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
    private val betaTextBlock: BetaTextBlock? = null,
    private val betaToolUseBlock: BetaToolUseBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun toParam(): BetaContentBlockParam =
        accept(
            object : Visitor<BetaContentBlockParam> {
                override fun visitBetaTextBlock(
                    betaTextBlock: BetaTextBlock
                ): BetaContentBlockParam =
                    BetaContentBlockParam.ofBetaTextBlockParam(betaTextBlock.toParam())

                override fun visitBetaToolUseBlock(
                    betaToolUseBlock: BetaToolUseBlock
                ): BetaContentBlockParam =
                    BetaContentBlockParam.ofBetaToolUseBlockParam(betaToolUseBlock.toParam())
            }
        )

    private var validated: Boolean = false

    fun betaTextBlock(): Optional<BetaTextBlock> = Optional.ofNullable(betaTextBlock)

    fun betaToolUseBlock(): Optional<BetaToolUseBlock> = Optional.ofNullable(betaToolUseBlock)

    fun isBetaTextBlock(): Boolean = betaTextBlock != null

    fun isBetaToolUseBlock(): Boolean = betaToolUseBlock != null

    fun asBetaTextBlock(): BetaTextBlock = betaTextBlock.getOrThrow("betaTextBlock")

    fun asBetaToolUseBlock(): BetaToolUseBlock = betaToolUseBlock.getOrThrow("betaToolUseBlock")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            betaTextBlock != null -> visitor.visitBetaTextBlock(betaTextBlock)
            betaToolUseBlock != null -> visitor.visitBetaToolUseBlock(betaToolUseBlock)
            else -> visitor.unknown(_json)
        }
    }

    fun validate(): BetaContentBlock = apply {
        if (!validated) {
            if (betaTextBlock == null && betaToolUseBlock == null) {
                throw AnthropicInvalidDataException("Unknown BetaContentBlock: $_json")
            }
            betaTextBlock?.validate()
            betaToolUseBlock?.validate()
            validated = true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaContentBlock && betaTextBlock == other.betaTextBlock && betaToolUseBlock == other.betaToolUseBlock /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaTextBlock, betaToolUseBlock) /* spotless:on */

    override fun toString(): String =
        when {
            betaTextBlock != null -> "BetaContentBlock{betaTextBlock=$betaTextBlock}"
            betaToolUseBlock != null -> "BetaContentBlock{betaToolUseBlock=$betaToolUseBlock}"
            _json != null -> "BetaContentBlock{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaContentBlock")
        }

    companion object {

        @JvmStatic
        fun ofBetaTextBlock(betaTextBlock: BetaTextBlock) =
            BetaContentBlock(betaTextBlock = betaTextBlock)

        @JvmStatic
        fun ofBetaToolUseBlock(betaToolUseBlock: BetaToolUseBlock) =
            BetaContentBlock(betaToolUseBlock = betaToolUseBlock)
    }

    interface Visitor<out T> {

        fun visitBetaTextBlock(betaTextBlock: BetaTextBlock): T

        fun visitBetaToolUseBlock(betaToolUseBlock: BetaToolUseBlock): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaContentBlock: $json")
        }
    }

    class Deserializer : BaseDeserializer<BetaContentBlock>(BetaContentBlock::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaContentBlock {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaTextBlock>()) { it.validate() }
                        ?.let {
                            return BetaContentBlock(betaTextBlock = it, _json = json)
                        }
                }
                "tool_use" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaToolUseBlock>()) { it.validate() }
                        ?.let {
                            return BetaContentBlock(betaToolUseBlock = it, _json = json)
                        }
                }
            }

            return BetaContentBlock(_json = json)
        }
    }

    class Serializer : BaseSerializer<BetaContentBlock>(BetaContentBlock::class) {

        override fun serialize(
            value: BetaContentBlock,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.betaTextBlock != null -> generator.writeObject(value.betaTextBlock)
                value.betaToolUseBlock != null -> generator.writeObject(value.betaToolUseBlock)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlock")
            }
        }
    }
}
