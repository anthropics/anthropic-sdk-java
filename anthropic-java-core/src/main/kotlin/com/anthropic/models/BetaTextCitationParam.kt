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

@JsonDeserialize(using = BetaTextCitationParam.Deserializer::class)
@JsonSerialize(using = BetaTextCitationParam.Serializer::class)
class BetaTextCitationParam
private constructor(
    private val betaCitationCharLocationParam: BetaCitationCharLocationParam? = null,
    private val betaCitationPageLocationParam: BetaCitationPageLocationParam? = null,
    private val betaCitationContentBlockLocationParam: BetaCitationContentBlockLocationParam? =
        null,
    private val _json: JsonValue? = null,
) {

    fun betaCitationCharLocationParam(): Optional<BetaCitationCharLocationParam> =
        Optional.ofNullable(betaCitationCharLocationParam)

    fun betaCitationPageLocationParam(): Optional<BetaCitationPageLocationParam> =
        Optional.ofNullable(betaCitationPageLocationParam)

    fun betaCitationContentBlockLocationParam(): Optional<BetaCitationContentBlockLocationParam> =
        Optional.ofNullable(betaCitationContentBlockLocationParam)

    fun isBetaCitationCharLocationParam(): Boolean = betaCitationCharLocationParam != null

    fun isBetaCitationPageLocationParam(): Boolean = betaCitationPageLocationParam != null

    fun isBetaCitationContentBlockLocationParam(): Boolean =
        betaCitationContentBlockLocationParam != null

    fun asBetaCitationCharLocationParam(): BetaCitationCharLocationParam =
        betaCitationCharLocationParam.getOrThrow("betaCitationCharLocationParam")

    fun asBetaCitationPageLocationParam(): BetaCitationPageLocationParam =
        betaCitationPageLocationParam.getOrThrow("betaCitationPageLocationParam")

    fun asBetaCitationContentBlockLocationParam(): BetaCitationContentBlockLocationParam =
        betaCitationContentBlockLocationParam.getOrThrow("betaCitationContentBlockLocationParam")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            betaCitationCharLocationParam != null ->
                visitor.visitBetaCitationCharLocationParam(betaCitationCharLocationParam)
            betaCitationPageLocationParam != null ->
                visitor.visitBetaCitationPageLocationParam(betaCitationPageLocationParam)
            betaCitationContentBlockLocationParam != null ->
                visitor.visitBetaCitationContentBlockLocationParam(
                    betaCitationContentBlockLocationParam
                )
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): BetaTextCitationParam = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitBetaCitationCharLocationParam(
                    betaCitationCharLocationParam: BetaCitationCharLocationParam
                ) {
                    betaCitationCharLocationParam.validate()
                }

                override fun visitBetaCitationPageLocationParam(
                    betaCitationPageLocationParam: BetaCitationPageLocationParam
                ) {
                    betaCitationPageLocationParam.validate()
                }

                override fun visitBetaCitationContentBlockLocationParam(
                    betaCitationContentBlockLocationParam: BetaCitationContentBlockLocationParam
                ) {
                    betaCitationContentBlockLocationParam.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaTextCitationParam && betaCitationCharLocationParam == other.betaCitationCharLocationParam && betaCitationPageLocationParam == other.betaCitationPageLocationParam && betaCitationContentBlockLocationParam == other.betaCitationContentBlockLocationParam /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaCitationCharLocationParam, betaCitationPageLocationParam, betaCitationContentBlockLocationParam) /* spotless:on */

    override fun toString(): String =
        when {
            betaCitationCharLocationParam != null ->
                "BetaTextCitationParam{betaCitationCharLocationParam=$betaCitationCharLocationParam}"
            betaCitationPageLocationParam != null ->
                "BetaTextCitationParam{betaCitationPageLocationParam=$betaCitationPageLocationParam}"
            betaCitationContentBlockLocationParam != null ->
                "BetaTextCitationParam{betaCitationContentBlockLocationParam=$betaCitationContentBlockLocationParam}"
            _json != null -> "BetaTextCitationParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTextCitationParam")
        }

    companion object {

        @JvmStatic
        fun ofBetaCitationCharLocationParam(
            betaCitationCharLocationParam: BetaCitationCharLocationParam
        ) = BetaTextCitationParam(betaCitationCharLocationParam = betaCitationCharLocationParam)

        @JvmStatic
        fun ofBetaCitationPageLocationParam(
            betaCitationPageLocationParam: BetaCitationPageLocationParam
        ) = BetaTextCitationParam(betaCitationPageLocationParam = betaCitationPageLocationParam)

        @JvmStatic
        fun ofBetaCitationContentBlockLocationParam(
            betaCitationContentBlockLocationParam: BetaCitationContentBlockLocationParam
        ) =
            BetaTextCitationParam(
                betaCitationContentBlockLocationParam = betaCitationContentBlockLocationParam
            )
    }

    interface Visitor<out T> {

        fun visitBetaCitationCharLocationParam(
            betaCitationCharLocationParam: BetaCitationCharLocationParam
        ): T

        fun visitBetaCitationPageLocationParam(
            betaCitationPageLocationParam: BetaCitationPageLocationParam
        ): T

        fun visitBetaCitationContentBlockLocationParam(
            betaCitationContentBlockLocationParam: BetaCitationContentBlockLocationParam
        ): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaTextCitationParam: $json")
        }
    }

    class Deserializer : BaseDeserializer<BetaTextCitationParam>(BetaTextCitationParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaTextCitationParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "char_location" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaCitationCharLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaTextCitationParam(
                                betaCitationCharLocationParam = it,
                                _json = json
                            )
                        }
                }
                "page_location" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaCitationPageLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaTextCitationParam(
                                betaCitationPageLocationParam = it,
                                _json = json
                            )
                        }
                }
                "content_block_location" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaCitationContentBlockLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaTextCitationParam(
                                betaCitationContentBlockLocationParam = it,
                                _json = json
                            )
                        }
                }
            }

            return BetaTextCitationParam(_json = json)
        }
    }

    class Serializer : BaseSerializer<BetaTextCitationParam>(BetaTextCitationParam::class) {

        override fun serialize(
            value: BetaTextCitationParam,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.betaCitationCharLocationParam != null ->
                    generator.writeObject(value.betaCitationCharLocationParam)
                value.betaCitationPageLocationParam != null ->
                    generator.writeObject(value.betaCitationPageLocationParam)
                value.betaCitationContentBlockLocationParam != null ->
                    generator.writeObject(value.betaCitationContentBlockLocationParam)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaTextCitationParam")
            }
        }
    }
}
