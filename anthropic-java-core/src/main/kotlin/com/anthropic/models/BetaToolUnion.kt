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
    private val computerUse20241022: BetaToolComputerUse20241022? = null,
    private val bash20241022: BetaToolBash20241022? = null,
    private val textEditor20241022: BetaToolTextEditor20241022? = null,
    private val computerUse20250124: BetaToolComputerUse20250124? = null,
    private val bash20250124: BetaToolBash20250124? = null,
    private val textEditor20250124: BetaToolTextEditor20250124? = null,
    private val _json: JsonValue? = null,
) {

    fun betaTool(): Optional<BetaTool> = Optional.ofNullable(betaTool)

    fun computerUse20241022(): Optional<BetaToolComputerUse20241022> =
        Optional.ofNullable(computerUse20241022)

    fun bash20241022(): Optional<BetaToolBash20241022> = Optional.ofNullable(bash20241022)

    fun textEditor20241022(): Optional<BetaToolTextEditor20241022> =
        Optional.ofNullable(textEditor20241022)

    fun computerUse20250124(): Optional<BetaToolComputerUse20250124> =
        Optional.ofNullable(computerUse20250124)

    fun bash20250124(): Optional<BetaToolBash20250124> = Optional.ofNullable(bash20250124)

    fun textEditor20250124(): Optional<BetaToolTextEditor20250124> =
        Optional.ofNullable(textEditor20250124)

    fun isBetaTool(): Boolean = betaTool != null

    fun isComputerUse20241022(): Boolean = computerUse20241022 != null

    fun isBash20241022(): Boolean = bash20241022 != null

    fun isTextEditor20241022(): Boolean = textEditor20241022 != null

    fun isComputerUse20250124(): Boolean = computerUse20250124 != null

    fun isBash20250124(): Boolean = bash20250124 != null

    fun isTextEditor20250124(): Boolean = textEditor20250124 != null

    fun asBetaTool(): BetaTool = betaTool.getOrThrow("betaTool")

    fun asComputerUse20241022(): BetaToolComputerUse20241022 =
        computerUse20241022.getOrThrow("computerUse20241022")

    fun asBash20241022(): BetaToolBash20241022 = bash20241022.getOrThrow("bash20241022")

    fun asTextEditor20241022(): BetaToolTextEditor20241022 =
        textEditor20241022.getOrThrow("textEditor20241022")

    fun asComputerUse20250124(): BetaToolComputerUse20250124 =
        computerUse20250124.getOrThrow("computerUse20250124")

    fun asBash20250124(): BetaToolBash20250124 = bash20250124.getOrThrow("bash20250124")

    fun asTextEditor20250124(): BetaToolTextEditor20250124 =
        textEditor20250124.getOrThrow("textEditor20250124")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            betaTool != null -> visitor.visitBetaTool(betaTool)
            computerUse20241022 != null -> visitor.visitComputerUse20241022(computerUse20241022)
            bash20241022 != null -> visitor.visitBash20241022(bash20241022)
            textEditor20241022 != null -> visitor.visitTextEditor20241022(textEditor20241022)
            computerUse20250124 != null -> visitor.visitComputerUse20250124(computerUse20250124)
            bash20250124 != null -> visitor.visitBash20250124(bash20250124)
            textEditor20250124 != null -> visitor.visitTextEditor20250124(textEditor20250124)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): BetaToolUnion = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitBetaTool(betaTool: BetaTool) {
                    betaTool.validate()
                }

                override fun visitComputerUse20241022(
                    computerUse20241022: BetaToolComputerUse20241022
                ) {
                    computerUse20241022.validate()
                }

                override fun visitBash20241022(bash20241022: BetaToolBash20241022) {
                    bash20241022.validate()
                }

                override fun visitTextEditor20241022(
                    textEditor20241022: BetaToolTextEditor20241022
                ) {
                    textEditor20241022.validate()
                }

                override fun visitComputerUse20250124(
                    computerUse20250124: BetaToolComputerUse20250124
                ) {
                    computerUse20250124.validate()
                }

                override fun visitBash20250124(bash20250124: BetaToolBash20250124) {
                    bash20250124.validate()
                }

                override fun visitTextEditor20250124(
                    textEditor20250124: BetaToolTextEditor20250124
                ) {
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

        return /* spotless:off */ other is BetaToolUnion && betaTool == other.betaTool && computerUse20241022 == other.computerUse20241022 && bash20241022 == other.bash20241022 && textEditor20241022 == other.textEditor20241022 && computerUse20250124 == other.computerUse20250124 && bash20250124 == other.bash20250124 && textEditor20250124 == other.textEditor20250124 /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaTool, computerUse20241022, bash20241022, textEditor20241022, computerUse20250124, bash20250124, textEditor20250124) /* spotless:on */

    override fun toString(): String =
        when {
            betaTool != null -> "BetaToolUnion{betaTool=$betaTool}"
            computerUse20241022 != null -> "BetaToolUnion{computerUse20241022=$computerUse20241022}"
            bash20241022 != null -> "BetaToolUnion{bash20241022=$bash20241022}"
            textEditor20241022 != null -> "BetaToolUnion{textEditor20241022=$textEditor20241022}"
            computerUse20250124 != null -> "BetaToolUnion{computerUse20250124=$computerUse20250124}"
            bash20250124 != null -> "BetaToolUnion{bash20250124=$bash20250124}"
            textEditor20250124 != null -> "BetaToolUnion{textEditor20250124=$textEditor20250124}"
            _json != null -> "BetaToolUnion{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaToolUnion")
        }

    companion object {

        @JvmStatic fun ofBetaTool(betaTool: BetaTool) = BetaToolUnion(betaTool = betaTool)

        @JvmStatic
        fun ofComputerUse20241022(computerUse20241022: BetaToolComputerUse20241022) =
            BetaToolUnion(computerUse20241022 = computerUse20241022)

        @JvmStatic
        fun ofBash20241022(bash20241022: BetaToolBash20241022) =
            BetaToolUnion(bash20241022 = bash20241022)

        @JvmStatic
        fun ofTextEditor20241022(textEditor20241022: BetaToolTextEditor20241022) =
            BetaToolUnion(textEditor20241022 = textEditor20241022)

        @JvmStatic
        fun ofComputerUse20250124(computerUse20250124: BetaToolComputerUse20250124) =
            BetaToolUnion(computerUse20250124 = computerUse20250124)

        @JvmStatic
        fun ofBash20250124(bash20250124: BetaToolBash20250124) =
            BetaToolUnion(bash20250124 = bash20250124)

        @JvmStatic
        fun ofTextEditor20250124(textEditor20250124: BetaToolTextEditor20250124) =
            BetaToolUnion(textEditor20250124 = textEditor20250124)
    }

    /**
     * An interface that defines how to map each variant of [BetaToolUnion] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitBetaTool(betaTool: BetaTool): T

        fun visitComputerUse20241022(computerUse20241022: BetaToolComputerUse20241022): T

        fun visitBash20241022(bash20241022: BetaToolBash20241022): T

        fun visitTextEditor20241022(textEditor20241022: BetaToolTextEditor20241022): T

        fun visitComputerUse20250124(computerUse20250124: BetaToolComputerUse20250124): T

        fun visitBash20250124(bash20250124: BetaToolBash20250124): T

        fun visitTextEditor20250124(textEditor20250124: BetaToolTextEditor20250124): T

        /**
         * Maps an unknown variant of [BetaToolUnion] to a value of type [T].
         *
         * An instance of [BetaToolUnion] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaToolUnion: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaToolUnion>(BetaToolUnion::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaToolUnion {
            val json = JsonValue.fromJsonNode(node)

            tryDeserialize(node, jacksonTypeRef<BetaTool>()) { it.validate() }
                ?.let {
                    return BetaToolUnion(betaTool = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20241022>()) { it.validate() }
                ?.let {
                    return BetaToolUnion(computerUse20241022 = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<BetaToolBash20241022>()) { it.validate() }
                ?.let {
                    return BetaToolUnion(bash20241022 = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20241022>()) { it.validate() }
                ?.let {
                    return BetaToolUnion(textEditor20241022 = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20250124>()) { it.validate() }
                ?.let {
                    return BetaToolUnion(computerUse20250124 = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<BetaToolBash20250124>()) { it.validate() }
                ?.let {
                    return BetaToolUnion(bash20250124 = it, _json = json)
                }
            tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20250124>()) { it.validate() }
                ?.let {
                    return BetaToolUnion(textEditor20250124 = it, _json = json)
                }

            return BetaToolUnion(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaToolUnion>(BetaToolUnion::class) {

        override fun serialize(
            value: BetaToolUnion,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.betaTool != null -> generator.writeObject(value.betaTool)
                value.computerUse20241022 != null ->
                    generator.writeObject(value.computerUse20241022)
                value.bash20241022 != null -> generator.writeObject(value.bash20241022)
                value.textEditor20241022 != null -> generator.writeObject(value.textEditor20241022)
                value.computerUse20250124 != null ->
                    generator.writeObject(value.computerUse20250124)
                value.bash20250124 != null -> generator.writeObject(value.bash20250124)
                value.textEditor20250124 != null -> generator.writeObject(value.textEditor20250124)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaToolUnion")
            }
        }
    }
}
