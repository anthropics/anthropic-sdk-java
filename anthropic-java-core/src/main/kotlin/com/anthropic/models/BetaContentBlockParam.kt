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

@JsonDeserialize(using = BetaContentBlockParam.Deserializer::class)
@JsonSerialize(using = BetaContentBlockParam.Serializer::class)
class BetaContentBlockParam
private constructor(
    private val betaTextBlockParam: BetaTextBlockParam? = null,
    private val betaImageBlockParam: BetaImageBlockParam? = null,
    private val betaToolUseBlockParam: BetaToolUseBlockParam? = null,
    private val betaToolResultBlockParam: BetaToolResultBlockParam? = null,
    private val betaBase64PdfBlock: BetaBase64PdfBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun betaTextBlockParam(): Optional<BetaTextBlockParam> = Optional.ofNullable(betaTextBlockParam)

    fun betaImageBlockParam(): Optional<BetaImageBlockParam> =
        Optional.ofNullable(betaImageBlockParam)

    fun betaToolUseBlockParam(): Optional<BetaToolUseBlockParam> =
        Optional.ofNullable(betaToolUseBlockParam)

    fun betaToolResultBlockParam(): Optional<BetaToolResultBlockParam> =
        Optional.ofNullable(betaToolResultBlockParam)

    fun betaBase64PdfBlock(): Optional<BetaBase64PdfBlock> = Optional.ofNullable(betaBase64PdfBlock)

    fun isBetaTextBlockParam(): Boolean = betaTextBlockParam != null

    fun isBetaImageBlockParam(): Boolean = betaImageBlockParam != null

    fun isBetaToolUseBlockParam(): Boolean = betaToolUseBlockParam != null

    fun isBetaToolResultBlockParam(): Boolean = betaToolResultBlockParam != null

    fun isBetaBase64PdfBlock(): Boolean = betaBase64PdfBlock != null

    fun asBetaTextBlockParam(): BetaTextBlockParam =
        betaTextBlockParam.getOrThrow("betaTextBlockParam")

    fun asBetaImageBlockParam(): BetaImageBlockParam =
        betaImageBlockParam.getOrThrow("betaImageBlockParam")

    fun asBetaToolUseBlockParam(): BetaToolUseBlockParam =
        betaToolUseBlockParam.getOrThrow("betaToolUseBlockParam")

    fun asBetaToolResultBlockParam(): BetaToolResultBlockParam =
        betaToolResultBlockParam.getOrThrow("betaToolResultBlockParam")

    fun asBetaBase64PdfBlock(): BetaBase64PdfBlock =
        betaBase64PdfBlock.getOrThrow("betaBase64PdfBlock")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            betaTextBlockParam != null -> visitor.visitBetaTextBlockParam(betaTextBlockParam)
            betaImageBlockParam != null -> visitor.visitBetaImageBlockParam(betaImageBlockParam)
            betaToolUseBlockParam != null ->
                visitor.visitBetaToolUseBlockParam(betaToolUseBlockParam)
            betaToolResultBlockParam != null ->
                visitor.visitBetaToolResultBlockParam(betaToolResultBlockParam)
            betaBase64PdfBlock != null -> visitor.visitBetaBase64PdfBlock(betaBase64PdfBlock)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): BetaContentBlockParam = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitBetaTextBlockParam(betaTextBlockParam: BetaTextBlockParam) {
                    betaTextBlockParam.validate()
                }

                override fun visitBetaImageBlockParam(betaImageBlockParam: BetaImageBlockParam) {
                    betaImageBlockParam.validate()
                }

                override fun visitBetaToolUseBlockParam(
                    betaToolUseBlockParam: BetaToolUseBlockParam
                ) {
                    betaToolUseBlockParam.validate()
                }

                override fun visitBetaToolResultBlockParam(
                    betaToolResultBlockParam: BetaToolResultBlockParam
                ) {
                    betaToolResultBlockParam.validate()
                }

                override fun visitBetaBase64PdfBlock(betaBase64PdfBlock: BetaBase64PdfBlock) {
                    betaBase64PdfBlock.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaContentBlockParam && betaTextBlockParam == other.betaTextBlockParam && betaImageBlockParam == other.betaImageBlockParam && betaToolUseBlockParam == other.betaToolUseBlockParam && betaToolResultBlockParam == other.betaToolResultBlockParam && betaBase64PdfBlock == other.betaBase64PdfBlock /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaTextBlockParam, betaImageBlockParam, betaToolUseBlockParam, betaToolResultBlockParam, betaBase64PdfBlock) /* spotless:on */

    override fun toString(): String =
        when {
            betaTextBlockParam != null ->
                "BetaContentBlockParam{betaTextBlockParam=$betaTextBlockParam}"
            betaImageBlockParam != null ->
                "BetaContentBlockParam{betaImageBlockParam=$betaImageBlockParam}"
            betaToolUseBlockParam != null ->
                "BetaContentBlockParam{betaToolUseBlockParam=$betaToolUseBlockParam}"
            betaToolResultBlockParam != null ->
                "BetaContentBlockParam{betaToolResultBlockParam=$betaToolResultBlockParam}"
            betaBase64PdfBlock != null ->
                "BetaContentBlockParam{betaBase64PdfBlock=$betaBase64PdfBlock}"
            _json != null -> "BetaContentBlockParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaContentBlockParam")
        }

    companion object {

        @JvmStatic
        fun ofBetaTextBlockParam(betaTextBlockParam: BetaTextBlockParam) =
            BetaContentBlockParam(betaTextBlockParam = betaTextBlockParam)

        @JvmStatic
        fun ofBetaImageBlockParam(betaImageBlockParam: BetaImageBlockParam) =
            BetaContentBlockParam(betaImageBlockParam = betaImageBlockParam)

        @JvmStatic
        fun ofBetaToolUseBlockParam(betaToolUseBlockParam: BetaToolUseBlockParam) =
            BetaContentBlockParam(betaToolUseBlockParam = betaToolUseBlockParam)

        @JvmStatic
        fun ofBetaToolResultBlockParam(betaToolResultBlockParam: BetaToolResultBlockParam) =
            BetaContentBlockParam(betaToolResultBlockParam = betaToolResultBlockParam)

        @JvmStatic
        fun ofBetaBase64PdfBlock(betaBase64PdfBlock: BetaBase64PdfBlock) =
            BetaContentBlockParam(betaBase64PdfBlock = betaBase64PdfBlock)
    }

    interface Visitor<out T> {

        fun visitBetaTextBlockParam(betaTextBlockParam: BetaTextBlockParam): T

        fun visitBetaImageBlockParam(betaImageBlockParam: BetaImageBlockParam): T

        fun visitBetaToolUseBlockParam(betaToolUseBlockParam: BetaToolUseBlockParam): T

        fun visitBetaToolResultBlockParam(betaToolResultBlockParam: BetaToolResultBlockParam): T

        fun visitBetaBase64PdfBlock(betaBase64PdfBlock: BetaBase64PdfBlock): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaContentBlockParam: $json")
        }
    }

    class Deserializer : BaseDeserializer<BetaContentBlockParam>(BetaContentBlockParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaContentBlockParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaTextBlockParam>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockParam(betaTextBlockParam = it, _json = json)
                        }
                }
                "image" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaImageBlockParam>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockParam(betaImageBlockParam = it, _json = json)
                        }
                }
                "tool_use" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaToolUseBlockParam>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockParam(betaToolUseBlockParam = it, _json = json)
                        }
                }
                "tool_result" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaToolResultBlockParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaContentBlockParam(
                                betaToolResultBlockParam = it,
                                _json = json
                            )
                        }
                }
                "document" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaBase64PdfBlock>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockParam(betaBase64PdfBlock = it, _json = json)
                        }
                }
            }

            return BetaContentBlockParam(_json = json)
        }
    }

    class Serializer : BaseSerializer<BetaContentBlockParam>(BetaContentBlockParam::class) {

        override fun serialize(
            value: BetaContentBlockParam,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.betaTextBlockParam != null -> generator.writeObject(value.betaTextBlockParam)
                value.betaImageBlockParam != null ->
                    generator.writeObject(value.betaImageBlockParam)
                value.betaToolUseBlockParam != null ->
                    generator.writeObject(value.betaToolUseBlockParam)
                value.betaToolResultBlockParam != null ->
                    generator.writeObject(value.betaToolResultBlockParam)
                value.betaBase64PdfBlock != null -> generator.writeObject(value.betaBase64PdfBlock)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlockParam")
            }
        }
    }
}
