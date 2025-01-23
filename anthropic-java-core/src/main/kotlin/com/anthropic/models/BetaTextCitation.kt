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

@JsonDeserialize(using = BetaTextCitation.Deserializer::class)
@JsonSerialize(using = BetaTextCitation.Serializer::class)
class BetaTextCitation
private constructor(
    private val betaCitationCharLocation: BetaCitationCharLocation? = null,
    private val betaCitationPageLocation: BetaCitationPageLocation? = null,
    private val betaCitationContentBlockLocation: BetaCitationContentBlockLocation? = null,
    private val _json: JsonValue? = null,
) {

    fun betaCitationCharLocation(): Optional<BetaCitationCharLocation> =
        Optional.ofNullable(betaCitationCharLocation)

    fun betaCitationPageLocation(): Optional<BetaCitationPageLocation> =
        Optional.ofNullable(betaCitationPageLocation)

    fun betaCitationContentBlockLocation(): Optional<BetaCitationContentBlockLocation> =
        Optional.ofNullable(betaCitationContentBlockLocation)

    fun isBetaCitationCharLocation(): Boolean = betaCitationCharLocation != null

    fun isBetaCitationPageLocation(): Boolean = betaCitationPageLocation != null

    fun isBetaCitationContentBlockLocation(): Boolean = betaCitationContentBlockLocation != null

    fun asBetaCitationCharLocation(): BetaCitationCharLocation =
        betaCitationCharLocation.getOrThrow("betaCitationCharLocation")

    fun asBetaCitationPageLocation(): BetaCitationPageLocation =
        betaCitationPageLocation.getOrThrow("betaCitationPageLocation")

    fun asBetaCitationContentBlockLocation(): BetaCitationContentBlockLocation =
        betaCitationContentBlockLocation.getOrThrow("betaCitationContentBlockLocation")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            betaCitationCharLocation != null ->
                visitor.visitBetaCitationCharLocation(betaCitationCharLocation)
            betaCitationPageLocation != null ->
                visitor.visitBetaCitationPageLocation(betaCitationPageLocation)
            betaCitationContentBlockLocation != null ->
                visitor.visitBetaCitationContentBlockLocation(betaCitationContentBlockLocation)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): BetaTextCitation = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitBetaCitationCharLocation(
                    betaCitationCharLocation: BetaCitationCharLocation
                ) {
                    betaCitationCharLocation.validate()
                }

                override fun visitBetaCitationPageLocation(
                    betaCitationPageLocation: BetaCitationPageLocation
                ) {
                    betaCitationPageLocation.validate()
                }

                override fun visitBetaCitationContentBlockLocation(
                    betaCitationContentBlockLocation: BetaCitationContentBlockLocation
                ) {
                    betaCitationContentBlockLocation.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaTextCitation && betaCitationCharLocation == other.betaCitationCharLocation && betaCitationPageLocation == other.betaCitationPageLocation && betaCitationContentBlockLocation == other.betaCitationContentBlockLocation /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaCitationCharLocation, betaCitationPageLocation, betaCitationContentBlockLocation) /* spotless:on */

    override fun toString(): String =
        when {
            betaCitationCharLocation != null ->
                "BetaTextCitation{betaCitationCharLocation=$betaCitationCharLocation}"
            betaCitationPageLocation != null ->
                "BetaTextCitation{betaCitationPageLocation=$betaCitationPageLocation}"
            betaCitationContentBlockLocation != null ->
                "BetaTextCitation{betaCitationContentBlockLocation=$betaCitationContentBlockLocation}"
            _json != null -> "BetaTextCitation{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTextCitation")
        }

    companion object {

        @JvmStatic
        fun ofBetaCitationCharLocation(betaCitationCharLocation: BetaCitationCharLocation) =
            BetaTextCitation(betaCitationCharLocation = betaCitationCharLocation)

        @JvmStatic
        fun ofBetaCitationPageLocation(betaCitationPageLocation: BetaCitationPageLocation) =
            BetaTextCitation(betaCitationPageLocation = betaCitationPageLocation)

        @JvmStatic
        fun ofBetaCitationContentBlockLocation(
            betaCitationContentBlockLocation: BetaCitationContentBlockLocation
        ) = BetaTextCitation(betaCitationContentBlockLocation = betaCitationContentBlockLocation)
    }

    interface Visitor<out T> {

        fun visitBetaCitationCharLocation(betaCitationCharLocation: BetaCitationCharLocation): T

        fun visitBetaCitationPageLocation(betaCitationPageLocation: BetaCitationPageLocation): T

        fun visitBetaCitationContentBlockLocation(
            betaCitationContentBlockLocation: BetaCitationContentBlockLocation
        ): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaTextCitation: $json")
        }
    }

    class Deserializer : BaseDeserializer<BetaTextCitation>(BetaTextCitation::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaTextCitation {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "char_location" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaCitationCharLocation>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaTextCitation(betaCitationCharLocation = it, _json = json)
                        }
                }
                "page_location" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaCitationPageLocation>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaTextCitation(betaCitationPageLocation = it, _json = json)
                        }
                }
                "content_block_location" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaCitationContentBlockLocation>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaTextCitation(
                                betaCitationContentBlockLocation = it,
                                _json = json
                            )
                        }
                }
            }

            return BetaTextCitation(_json = json)
        }
    }

    class Serializer : BaseSerializer<BetaTextCitation>(BetaTextCitation::class) {

        override fun serialize(
            value: BetaTextCitation,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.betaCitationCharLocation != null ->
                    generator.writeObject(value.betaCitationCharLocation)
                value.betaCitationPageLocation != null ->
                    generator.writeObject(value.betaCitationPageLocation)
                value.betaCitationContentBlockLocation != null ->
                    generator.writeObject(value.betaCitationContentBlockLocation)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaTextCitation")
            }
        }
    }
}
