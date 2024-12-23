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

@JsonDeserialize(using = BetaToolChoice.Deserializer::class)
@JsonSerialize(using = BetaToolChoice.Serializer::class)
class BetaToolChoice
private constructor(
    private val betaToolChoiceAuto: BetaToolChoiceAuto? = null,
    private val betaToolChoiceAny: BetaToolChoiceAny? = null,
    private val betaToolChoiceTool: BetaToolChoiceTool? = null,
    private val _json: JsonValue? = null,
) {

    private var validated: Boolean = false

    /** The model will automatically decide whether to use tools. */
    fun betaToolChoiceAuto(): Optional<BetaToolChoiceAuto> = Optional.ofNullable(betaToolChoiceAuto)
    /** The model will use any available tools. */
    fun betaToolChoiceAny(): Optional<BetaToolChoiceAny> = Optional.ofNullable(betaToolChoiceAny)
    /** The model will use the specified tool with `tool_choice.name`. */
    fun betaToolChoiceTool(): Optional<BetaToolChoiceTool> = Optional.ofNullable(betaToolChoiceTool)

    fun isBetaToolChoiceAuto(): Boolean = betaToolChoiceAuto != null

    fun isBetaToolChoiceAny(): Boolean = betaToolChoiceAny != null

    fun isBetaToolChoiceTool(): Boolean = betaToolChoiceTool != null

    fun asBetaToolChoiceAuto(): BetaToolChoiceAuto =
        betaToolChoiceAuto.getOrThrow("betaToolChoiceAuto")

    fun asBetaToolChoiceAny(): BetaToolChoiceAny = betaToolChoiceAny.getOrThrow("betaToolChoiceAny")

    fun asBetaToolChoiceTool(): BetaToolChoiceTool =
        betaToolChoiceTool.getOrThrow("betaToolChoiceTool")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            betaToolChoiceAuto != null -> visitor.visitBetaToolChoiceAuto(betaToolChoiceAuto)
            betaToolChoiceAny != null -> visitor.visitBetaToolChoiceAny(betaToolChoiceAny)
            betaToolChoiceTool != null -> visitor.visitBetaToolChoiceTool(betaToolChoiceTool)
            else -> visitor.unknown(_json)
        }
    }

    fun validate(): BetaToolChoice = apply {
        if (!validated) {
            if (
                betaToolChoiceAuto == null &&
                    betaToolChoiceAny == null &&
                    betaToolChoiceTool == null
            ) {
                throw AnthropicInvalidDataException("Unknown BetaToolChoice: $_json")
            }
            betaToolChoiceAuto?.validate()
            betaToolChoiceAny?.validate()
            betaToolChoiceTool?.validate()
            validated = true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaToolChoice && betaToolChoiceAuto == other.betaToolChoiceAuto && betaToolChoiceAny == other.betaToolChoiceAny && betaToolChoiceTool == other.betaToolChoiceTool /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaToolChoiceAuto, betaToolChoiceAny, betaToolChoiceTool) /* spotless:on */

    override fun toString(): String =
        when {
            betaToolChoiceAuto != null -> "BetaToolChoice{betaToolChoiceAuto=$betaToolChoiceAuto}"
            betaToolChoiceAny != null -> "BetaToolChoice{betaToolChoiceAny=$betaToolChoiceAny}"
            betaToolChoiceTool != null -> "BetaToolChoice{betaToolChoiceTool=$betaToolChoiceTool}"
            _json != null -> "BetaToolChoice{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaToolChoice")
        }

    companion object {

        @JvmStatic
        fun ofBetaToolChoiceAuto(betaToolChoiceAuto: BetaToolChoiceAuto) =
            BetaToolChoice(betaToolChoiceAuto = betaToolChoiceAuto)

        @JvmStatic
        fun ofBetaToolChoiceAny(betaToolChoiceAny: BetaToolChoiceAny) =
            BetaToolChoice(betaToolChoiceAny = betaToolChoiceAny)

        @JvmStatic
        fun ofBetaToolChoiceTool(betaToolChoiceTool: BetaToolChoiceTool) =
            BetaToolChoice(betaToolChoiceTool = betaToolChoiceTool)
    }

    interface Visitor<out T> {

        fun visitBetaToolChoiceAuto(betaToolChoiceAuto: BetaToolChoiceAuto): T

        fun visitBetaToolChoiceAny(betaToolChoiceAny: BetaToolChoiceAny): T

        fun visitBetaToolChoiceTool(betaToolChoiceTool: BetaToolChoiceTool): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaToolChoice: $json")
        }
    }

    class Deserializer : BaseDeserializer<BetaToolChoice>(BetaToolChoice::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaToolChoice {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "auto" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaToolChoiceAuto>()) { it.validate() }
                        ?.let {
                            return BetaToolChoice(betaToolChoiceAuto = it, _json = json)
                        }
                }
                "any" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaToolChoiceAny>()) { it.validate() }
                        ?.let {
                            return BetaToolChoice(betaToolChoiceAny = it, _json = json)
                        }
                }
                "tool" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaToolChoiceTool>()) { it.validate() }
                        ?.let {
                            return BetaToolChoice(betaToolChoiceTool = it, _json = json)
                        }
                }
            }

            return BetaToolChoice(_json = json)
        }
    }

    class Serializer : BaseSerializer<BetaToolChoice>(BetaToolChoice::class) {

        override fun serialize(
            value: BetaToolChoice,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.betaToolChoiceAuto != null -> generator.writeObject(value.betaToolChoiceAuto)
                value.betaToolChoiceAny != null -> generator.writeObject(value.betaToolChoiceAny)
                value.betaToolChoiceTool != null -> generator.writeObject(value.betaToolChoiceTool)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaToolChoice")
            }
        }
    }
}
