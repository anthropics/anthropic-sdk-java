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

@JsonDeserialize(using = BetaContentBlockSourceContent.Deserializer::class)
@JsonSerialize(using = BetaContentBlockSourceContent.Serializer::class)
class BetaContentBlockSourceContent
private constructor(
    private val betaTextBlockParam: BetaTextBlockParam? = null,
    private val betaImageBlockParam: BetaImageBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    fun betaTextBlockParam(): Optional<BetaTextBlockParam> = Optional.ofNullable(betaTextBlockParam)

    fun betaImageBlockParam(): Optional<BetaImageBlockParam> =
        Optional.ofNullable(betaImageBlockParam)

    fun isBetaTextBlockParam(): Boolean = betaTextBlockParam != null

    fun isBetaImageBlockParam(): Boolean = betaImageBlockParam != null

    fun asBetaTextBlockParam(): BetaTextBlockParam =
        betaTextBlockParam.getOrThrow("betaTextBlockParam")

    fun asBetaImageBlockParam(): BetaImageBlockParam =
        betaImageBlockParam.getOrThrow("betaImageBlockParam")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            betaTextBlockParam != null -> visitor.visitBetaTextBlockParam(betaTextBlockParam)
            betaImageBlockParam != null -> visitor.visitBetaImageBlockParam(betaImageBlockParam)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): BetaContentBlockSourceContent = apply {
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
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaContentBlockSourceContent && betaTextBlockParam == other.betaTextBlockParam && betaImageBlockParam == other.betaImageBlockParam /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaTextBlockParam, betaImageBlockParam) /* spotless:on */

    override fun toString(): String =
        when {
            betaTextBlockParam != null ->
                "BetaContentBlockSourceContent{betaTextBlockParam=$betaTextBlockParam}"
            betaImageBlockParam != null ->
                "BetaContentBlockSourceContent{betaImageBlockParam=$betaImageBlockParam}"
            _json != null -> "BetaContentBlockSourceContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaContentBlockSourceContent")
        }

    companion object {

        @JvmStatic
        fun ofBetaTextBlockParam(betaTextBlockParam: BetaTextBlockParam) =
            BetaContentBlockSourceContent(betaTextBlockParam = betaTextBlockParam)

        @JvmStatic
        fun ofBetaImageBlockParam(betaImageBlockParam: BetaImageBlockParam) =
            BetaContentBlockSourceContent(betaImageBlockParam = betaImageBlockParam)
    }

    interface Visitor<out T> {

        fun visitBetaTextBlockParam(betaTextBlockParam: BetaTextBlockParam): T

        fun visitBetaImageBlockParam(betaImageBlockParam: BetaImageBlockParam): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaContentBlockSourceContent: $json")
        }
    }

    class Deserializer :
        BaseDeserializer<BetaContentBlockSourceContent>(BetaContentBlockSourceContent::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaContentBlockSourceContent {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaTextBlockParam>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockSourceContent(
                                betaTextBlockParam = it,
                                _json = json
                            )
                        }
                }
                "image" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaImageBlockParam>()) { it.validate() }
                        ?.let {
                            return BetaContentBlockSourceContent(
                                betaImageBlockParam = it,
                                _json = json
                            )
                        }
                }
            }

            return BetaContentBlockSourceContent(_json = json)
        }
    }

    class Serializer :
        BaseSerializer<BetaContentBlockSourceContent>(BetaContentBlockSourceContent::class) {

        override fun serialize(
            value: BetaContentBlockSourceContent,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.betaTextBlockParam != null -> generator.writeObject(value.betaTextBlockParam)
                value.betaImageBlockParam != null ->
                    generator.writeObject(value.betaImageBlockParam)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlockSourceContent")
            }
        }
    }
}
