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

@JsonDeserialize(using = BetaToolUnion.Deserializer::class)
@JsonSerialize(using = BetaToolUnion.Serializer::class)
class BetaToolUnion
private constructor(
    private val betaTool: BetaTool? = null,
    private val betaToolComputerUse20241022: BetaToolComputerUse20241022? = null,
    private val betaToolBash20241022: BetaToolBash20241022? = null,
    private val betaToolTextEditor20241022: BetaToolTextEditor20241022? = null,
    private val _json: JsonValue? = null,
) {

    private var validated: Boolean = false

    fun betaTool(): Optional<BetaTool> = Optional.ofNullable(betaTool)

    fun betaToolComputerUse20241022(): Optional<BetaToolComputerUse20241022> =
        Optional.ofNullable(betaToolComputerUse20241022)

    fun betaToolBash20241022(): Optional<BetaToolBash20241022> =
        Optional.ofNullable(betaToolBash20241022)

    fun betaToolTextEditor20241022(): Optional<BetaToolTextEditor20241022> =
        Optional.ofNullable(betaToolTextEditor20241022)

    fun isBetaTool(): Boolean = betaTool != null

    fun isBetaToolComputerUse20241022(): Boolean = betaToolComputerUse20241022 != null

    fun isBetaToolBash20241022(): Boolean = betaToolBash20241022 != null

    fun isBetaToolTextEditor20241022(): Boolean = betaToolTextEditor20241022 != null

    fun asBetaTool(): BetaTool = betaTool.getOrThrow("betaTool")

    fun asBetaToolComputerUse20241022(): BetaToolComputerUse20241022 =
        betaToolComputerUse20241022.getOrThrow("betaToolComputerUse20241022")

    fun asBetaToolBash20241022(): BetaToolBash20241022 =
        betaToolBash20241022.getOrThrow("betaToolBash20241022")

    fun asBetaToolTextEditor20241022(): BetaToolTextEditor20241022 =
        betaToolTextEditor20241022.getOrThrow("betaToolTextEditor20241022")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            betaTool != null -> visitor.visitBetaTool(betaTool)
            betaToolComputerUse20241022 != null ->
                visitor.visitBetaToolComputerUse20241022(betaToolComputerUse20241022)
            betaToolBash20241022 != null -> visitor.visitBetaToolBash20241022(betaToolBash20241022)
            betaToolTextEditor20241022 != null ->
                visitor.visitBetaToolTextEditor20241022(betaToolTextEditor20241022)
            else -> visitor.unknown(_json)
        }
    }

    fun validate(): BetaToolUnion = apply {
        if (!validated) {
            if (
                betaTool == null &&
                    betaToolComputerUse20241022 == null &&
                    betaToolBash20241022 == null &&
                    betaToolTextEditor20241022 == null
            ) {
                throw AnthropicInvalidDataException("Unknown BetaToolUnion: $_json")
            }
            betaTool?.validate()
            betaToolComputerUse20241022?.validate()
            betaToolBash20241022?.validate()
            betaToolTextEditor20241022?.validate()
            validated = true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaToolUnion && betaTool == other.betaTool && betaToolComputerUse20241022 == other.betaToolComputerUse20241022 && betaToolBash20241022 == other.betaToolBash20241022 && betaToolTextEditor20241022 == other.betaToolTextEditor20241022 /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaTool, betaToolComputerUse20241022, betaToolBash20241022, betaToolTextEditor20241022) /* spotless:on */

    override fun toString(): String =
        when {
            betaTool != null -> "BetaToolUnion{betaTool=$betaTool}"
            betaToolComputerUse20241022 != null ->
                "BetaToolUnion{betaToolComputerUse20241022=$betaToolComputerUse20241022}"
            betaToolBash20241022 != null ->
                "BetaToolUnion{betaToolBash20241022=$betaToolBash20241022}"
            betaToolTextEditor20241022 != null ->
                "BetaToolUnion{betaToolTextEditor20241022=$betaToolTextEditor20241022}"
            _json != null -> "BetaToolUnion{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaToolUnion")
        }

    companion object {

        @JvmStatic fun ofBetaTool(betaTool: BetaTool) = BetaToolUnion(betaTool = betaTool)

        @JvmStatic
        fun ofBetaToolComputerUse20241022(
            betaToolComputerUse20241022: BetaToolComputerUse20241022
        ) = BetaToolUnion(betaToolComputerUse20241022 = betaToolComputerUse20241022)

        @JvmStatic
        fun ofBetaToolBash20241022(betaToolBash20241022: BetaToolBash20241022) =
            BetaToolUnion(betaToolBash20241022 = betaToolBash20241022)

        @JvmStatic
        fun ofBetaToolTextEditor20241022(betaToolTextEditor20241022: BetaToolTextEditor20241022) =
            BetaToolUnion(betaToolTextEditor20241022 = betaToolTextEditor20241022)
    }

    interface Visitor<out T> {

        fun visitBetaTool(betaTool: BetaTool): T

        fun visitBetaToolComputerUse20241022(
            betaToolComputerUse20241022: BetaToolComputerUse20241022
        ): T

        fun visitBetaToolBash20241022(betaToolBash20241022: BetaToolBash20241022): T

        fun visitBetaToolTextEditor20241022(
            betaToolTextEditor20241022: BetaToolTextEditor20241022
        ): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaToolUnion: $json")
        }
    }

    class Deserializer : BaseDeserializer<BetaToolUnion>(BetaToolUnion::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaToolUnion {
            val json = JsonValue.fromJsonNode(node)

            tryDeserialize(node, jacksonTypeRef<BetaTool>()) { it.validate() }
                ?.let {
                    return BetaToolUnion(betaTool = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20241022>()) { it.validate() }
                ?.let {
                    return BetaToolUnion(betaToolComputerUse20241022 = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<BetaToolBash20241022>()) { it.validate() }
                ?.let {
                    return BetaToolUnion(betaToolBash20241022 = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20241022>()) { it.validate() }
                ?.let {
                    return BetaToolUnion(betaToolTextEditor20241022 = it, _json = json)
                }

            return BetaToolUnion(_json = json)
        }
    }

    class Serializer : BaseSerializer<BetaToolUnion>(BetaToolUnion::class) {

        override fun serialize(
            value: BetaToolUnion,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.betaTool != null -> generator.writeObject(value.betaTool)
                value.betaToolComputerUse20241022 != null ->
                    generator.writeObject(value.betaToolComputerUse20241022)
                value.betaToolBash20241022 != null ->
                    generator.writeObject(value.betaToolBash20241022)
                value.betaToolTextEditor20241022 != null ->
                    generator.writeObject(value.betaToolTextEditor20241022)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaToolUnion")
            }
        }
    }
}
