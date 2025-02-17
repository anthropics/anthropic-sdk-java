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

@JsonDeserialize(using = TextCitation.Deserializer::class)
@JsonSerialize(using = TextCitation.Serializer::class)
class TextCitation
private constructor(
    private val citationCharLocation: CitationCharLocation? = null,
    private val citationPageLocation: CitationPageLocation? = null,
    private val citationContentBlockLocation: CitationContentBlockLocation? = null,
    private val _json: JsonValue? = null,
) {

    fun citationCharLocation(): Optional<CitationCharLocation> =
        Optional.ofNullable(citationCharLocation)

    fun citationPageLocation(): Optional<CitationPageLocation> =
        Optional.ofNullable(citationPageLocation)

    fun citationContentBlockLocation(): Optional<CitationContentBlockLocation> =
        Optional.ofNullable(citationContentBlockLocation)

    fun isCitationCharLocation(): Boolean = citationCharLocation != null

    fun isCitationPageLocation(): Boolean = citationPageLocation != null

    fun isCitationContentBlockLocation(): Boolean = citationContentBlockLocation != null

    fun asCitationCharLocation(): CitationCharLocation =
        citationCharLocation.getOrThrow("citationCharLocation")

    fun asCitationPageLocation(): CitationPageLocation =
        citationPageLocation.getOrThrow("citationPageLocation")

    fun asCitationContentBlockLocation(): CitationContentBlockLocation =
        citationContentBlockLocation.getOrThrow("citationContentBlockLocation")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            citationCharLocation != null -> visitor.visitCitationCharLocation(citationCharLocation)
            citationPageLocation != null -> visitor.visitCitationPageLocation(citationPageLocation)
            citationContentBlockLocation != null ->
                visitor.visitCitationContentBlockLocation(citationContentBlockLocation)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): TextCitation = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitCitationCharLocation(citationCharLocation: CitationCharLocation) {
                    citationCharLocation.validate()
                }

                override fun visitCitationPageLocation(citationPageLocation: CitationPageLocation) {
                    citationPageLocation.validate()
                }

                override fun visitCitationContentBlockLocation(
                    citationContentBlockLocation: CitationContentBlockLocation
                ) {
                    citationContentBlockLocation.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is TextCitation && citationCharLocation == other.citationCharLocation && citationPageLocation == other.citationPageLocation && citationContentBlockLocation == other.citationContentBlockLocation /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(citationCharLocation, citationPageLocation, citationContentBlockLocation) /* spotless:on */

    override fun toString(): String =
        when {
            citationCharLocation != null ->
                "TextCitation{citationCharLocation=$citationCharLocation}"
            citationPageLocation != null ->
                "TextCitation{citationPageLocation=$citationPageLocation}"
            citationContentBlockLocation != null ->
                "TextCitation{citationContentBlockLocation=$citationContentBlockLocation}"
            _json != null -> "TextCitation{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid TextCitation")
        }

    companion object {

        @JvmStatic
        fun ofCitationCharLocation(citationCharLocation: CitationCharLocation) =
            TextCitation(citationCharLocation = citationCharLocation)

        @JvmStatic
        fun ofCitationPageLocation(citationPageLocation: CitationPageLocation) =
            TextCitation(citationPageLocation = citationPageLocation)

        @JvmStatic
        fun ofCitationContentBlockLocation(
            citationContentBlockLocation: CitationContentBlockLocation
        ) = TextCitation(citationContentBlockLocation = citationContentBlockLocation)
    }

    /**
     * An interface that defines how to map each variant of [TextCitation] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitCitationCharLocation(citationCharLocation: CitationCharLocation): T

        fun visitCitationPageLocation(citationPageLocation: CitationPageLocation): T

        fun visitCitationContentBlockLocation(
            citationContentBlockLocation: CitationContentBlockLocation
        ): T

        /**
         * Maps an unknown variant of [TextCitation] to a value of type [T].
         *
         * An instance of [TextCitation] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown TextCitation: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<TextCitation>(TextCitation::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): TextCitation {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "char_location" -> {
                    tryDeserialize(node, jacksonTypeRef<CitationCharLocation>()) { it.validate() }
                        ?.let {
                            return TextCitation(citationCharLocation = it, _json = json)
                        }
                }
                "page_location" -> {
                    tryDeserialize(node, jacksonTypeRef<CitationPageLocation>()) { it.validate() }
                        ?.let {
                            return TextCitation(citationPageLocation = it, _json = json)
                        }
                }
                "content_block_location" -> {
                    tryDeserialize(node, jacksonTypeRef<CitationContentBlockLocation>()) {
                            it.validate()
                        }
                        ?.let {
                            return TextCitation(citationContentBlockLocation = it, _json = json)
                        }
                }
            }

            return TextCitation(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<TextCitation>(TextCitation::class) {

        override fun serialize(
            value: TextCitation,
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
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid TextCitation")
            }
        }
    }
}
