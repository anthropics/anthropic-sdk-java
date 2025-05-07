// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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
    private val citationCharLocation: BetaCitationCharLocation? = null,
    private val citationPageLocation: BetaCitationPageLocation? = null,
    private val citationContentBlockLocation: BetaCitationContentBlockLocation? = null,
    private val citationsWebSearchResultLocation: BetaCitationsWebSearchResultLocation? = null,
    private val _json: JsonValue? = null,
) {

    fun citationCharLocation(): Optional<BetaCitationCharLocation> =
        Optional.ofNullable(citationCharLocation)

    fun citationPageLocation(): Optional<BetaCitationPageLocation> =
        Optional.ofNullable(citationPageLocation)

    fun citationContentBlockLocation(): Optional<BetaCitationContentBlockLocation> =
        Optional.ofNullable(citationContentBlockLocation)

    fun citationsWebSearchResultLocation(): Optional<BetaCitationsWebSearchResultLocation> =
        Optional.ofNullable(citationsWebSearchResultLocation)

    fun isCitationCharLocation(): Boolean = citationCharLocation != null

    fun isCitationPageLocation(): Boolean = citationPageLocation != null

    fun isCitationContentBlockLocation(): Boolean = citationContentBlockLocation != null

    fun isCitationsWebSearchResultLocation(): Boolean = citationsWebSearchResultLocation != null

    fun asCitationCharLocation(): BetaCitationCharLocation =
        citationCharLocation.getOrThrow("citationCharLocation")

    fun asCitationPageLocation(): BetaCitationPageLocation =
        citationPageLocation.getOrThrow("citationPageLocation")

    fun asCitationContentBlockLocation(): BetaCitationContentBlockLocation =
        citationContentBlockLocation.getOrThrow("citationContentBlockLocation")

    fun asCitationsWebSearchResultLocation(): BetaCitationsWebSearchResultLocation =
        citationsWebSearchResultLocation.getOrThrow("citationsWebSearchResultLocation")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            citationCharLocation != null -> visitor.visitCitationCharLocation(citationCharLocation)
            citationPageLocation != null -> visitor.visitCitationPageLocation(citationPageLocation)
            citationContentBlockLocation != null ->
                visitor.visitCitationContentBlockLocation(citationContentBlockLocation)
            citationsWebSearchResultLocation != null ->
                visitor.visitCitationsWebSearchResultLocation(citationsWebSearchResultLocation)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): BetaTextCitation = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitCitationCharLocation(
                    citationCharLocation: BetaCitationCharLocation
                ) {
                    citationCharLocation.validate()
                }

                override fun visitCitationPageLocation(
                    citationPageLocation: BetaCitationPageLocation
                ) {
                    citationPageLocation.validate()
                }

                override fun visitCitationContentBlockLocation(
                    citationContentBlockLocation: BetaCitationContentBlockLocation
                ) {
                    citationContentBlockLocation.validate()
                }

                override fun visitCitationsWebSearchResultLocation(
                    citationsWebSearchResultLocation: BetaCitationsWebSearchResultLocation
                ) {
                    citationsWebSearchResultLocation.validate()
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
                override fun visitCitationCharLocation(
                    citationCharLocation: BetaCitationCharLocation
                ) = citationCharLocation.validity()

                override fun visitCitationPageLocation(
                    citationPageLocation: BetaCitationPageLocation
                ) = citationPageLocation.validity()

                override fun visitCitationContentBlockLocation(
                    citationContentBlockLocation: BetaCitationContentBlockLocation
                ) = citationContentBlockLocation.validity()

                override fun visitCitationsWebSearchResultLocation(
                    citationsWebSearchResultLocation: BetaCitationsWebSearchResultLocation
                ) = citationsWebSearchResultLocation.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaTextCitation && citationCharLocation == other.citationCharLocation && citationPageLocation == other.citationPageLocation && citationContentBlockLocation == other.citationContentBlockLocation && citationsWebSearchResultLocation == other.citationsWebSearchResultLocation /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(citationCharLocation, citationPageLocation, citationContentBlockLocation, citationsWebSearchResultLocation) /* spotless:on */

    override fun toString(): String =
        when {
            citationCharLocation != null ->
                "BetaTextCitation{citationCharLocation=$citationCharLocation}"
            citationPageLocation != null ->
                "BetaTextCitation{citationPageLocation=$citationPageLocation}"
            citationContentBlockLocation != null ->
                "BetaTextCitation{citationContentBlockLocation=$citationContentBlockLocation}"
            citationsWebSearchResultLocation != null ->
                "BetaTextCitation{citationsWebSearchResultLocation=$citationsWebSearchResultLocation}"
            _json != null -> "BetaTextCitation{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTextCitation")
        }

    companion object {

        @JvmStatic
        fun ofCitationCharLocation(citationCharLocation: BetaCitationCharLocation) =
            BetaTextCitation(citationCharLocation = citationCharLocation)

        @JvmStatic
        fun ofCitationPageLocation(citationPageLocation: BetaCitationPageLocation) =
            BetaTextCitation(citationPageLocation = citationPageLocation)

        @JvmStatic
        fun ofCitationContentBlockLocation(
            citationContentBlockLocation: BetaCitationContentBlockLocation
        ) = BetaTextCitation(citationContentBlockLocation = citationContentBlockLocation)

        @JvmStatic
        fun ofCitationsWebSearchResultLocation(
            citationsWebSearchResultLocation: BetaCitationsWebSearchResultLocation
        ) = BetaTextCitation(citationsWebSearchResultLocation = citationsWebSearchResultLocation)
    }

    /**
     * An interface that defines how to map each variant of [BetaTextCitation] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitCitationCharLocation(citationCharLocation: BetaCitationCharLocation): T

        fun visitCitationPageLocation(citationPageLocation: BetaCitationPageLocation): T

        fun visitCitationContentBlockLocation(
            citationContentBlockLocation: BetaCitationContentBlockLocation
        ): T

        fun visitCitationsWebSearchResultLocation(
            citationsWebSearchResultLocation: BetaCitationsWebSearchResultLocation
        ): T

        /**
         * Maps an unknown variant of [BetaTextCitation] to a value of type [T].
         *
         * An instance of [BetaTextCitation] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaTextCitation: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaTextCitation>(BetaTextCitation::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaTextCitation {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "char_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCitationCharLocation>())?.let {
                        BetaTextCitation(citationCharLocation = it, _json = json)
                    } ?: BetaTextCitation(_json = json)
                }
                "page_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCitationPageLocation>())?.let {
                        BetaTextCitation(citationPageLocation = it, _json = json)
                    } ?: BetaTextCitation(_json = json)
                }
                "content_block_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCitationContentBlockLocation>())
                        ?.let { BetaTextCitation(citationContentBlockLocation = it, _json = json) }
                        ?: BetaTextCitation(_json = json)
                }
                "web_search_result_location" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaCitationsWebSearchResultLocation>(),
                        )
                        ?.let {
                            BetaTextCitation(citationsWebSearchResultLocation = it, _json = json)
                        } ?: BetaTextCitation(_json = json)
                }
            }

            return BetaTextCitation(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaTextCitation>(BetaTextCitation::class) {

        override fun serialize(
            value: BetaTextCitation,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.citationCharLocation != null ->
                    generator.writeObject(value.citationCharLocation)
                value.citationPageLocation != null ->
                    generator.writeObject(value.citationPageLocation)
                value.citationContentBlockLocation != null ->
                    generator.writeObject(value.citationContentBlockLocation)
                value.citationsWebSearchResultLocation != null ->
                    generator.writeObject(value.citationsWebSearchResultLocation)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaTextCitation")
            }
        }
    }
}
