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

@JsonDeserialize(using = TextCitationParam.Deserializer::class)
@JsonSerialize(using = TextCitationParam.Serializer::class)
class TextCitationParam
private constructor(
    private val citationCharLocation: CitationCharLocationParam? = null,
    private val citationPageLocation: CitationPageLocationParam? = null,
    private val citationContentBlockLocation: CitationContentBlockLocationParam? = null,
    private val _json: JsonValue? = null,
) {

    fun citationCharLocation(): Optional<CitationCharLocationParam> =
        Optional.ofNullable(citationCharLocation)

    fun citationPageLocation(): Optional<CitationPageLocationParam> =
        Optional.ofNullable(citationPageLocation)

    fun citationContentBlockLocation(): Optional<CitationContentBlockLocationParam> =
        Optional.ofNullable(citationContentBlockLocation)

    fun isCitationCharLocation(): Boolean = citationCharLocation != null

    fun isCitationPageLocation(): Boolean = citationPageLocation != null

    fun isCitationContentBlockLocation(): Boolean = citationContentBlockLocation != null

    fun asCitationCharLocation(): CitationCharLocationParam =
        citationCharLocation.getOrThrow("citationCharLocation")

    fun asCitationPageLocation(): CitationPageLocationParam =
        citationPageLocation.getOrThrow("citationPageLocation")

    fun asCitationContentBlockLocation(): CitationContentBlockLocationParam =
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

    fun validate(): TextCitationParam = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitCitationCharLocation(
                    citationCharLocation: CitationCharLocationParam
                ) {
                    citationCharLocation.validate()
                }

                override fun visitCitationPageLocation(
                    citationPageLocation: CitationPageLocationParam
                ) {
                    citationPageLocation.validate()
                }

                override fun visitCitationContentBlockLocation(
                    citationContentBlockLocation: CitationContentBlockLocationParam
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

        return /* spotless:off */ other is TextCitationParam && citationCharLocation == other.citationCharLocation && citationPageLocation == other.citationPageLocation && citationContentBlockLocation == other.citationContentBlockLocation /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(citationCharLocation, citationPageLocation, citationContentBlockLocation) /* spotless:on */

    override fun toString(): String =
        when {
            citationCharLocation != null ->
                "TextCitationParam{citationCharLocation=$citationCharLocation}"
            citationPageLocation != null ->
                "TextCitationParam{citationPageLocation=$citationPageLocation}"
            citationContentBlockLocation != null ->
                "TextCitationParam{citationContentBlockLocation=$citationContentBlockLocation}"
            _json != null -> "TextCitationParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid TextCitationParam")
        }

    companion object {

        @JvmStatic
        fun ofCitationCharLocation(citationCharLocation: CitationCharLocationParam) =
            TextCitationParam(citationCharLocation = citationCharLocation)

        @JvmStatic
        fun ofCitationPageLocation(citationPageLocation: CitationPageLocationParam) =
            TextCitationParam(citationPageLocation = citationPageLocation)

        @JvmStatic
        fun ofCitationContentBlockLocation(
            citationContentBlockLocation: CitationContentBlockLocationParam
        ) = TextCitationParam(citationContentBlockLocation = citationContentBlockLocation)
    }

    /**
     * An interface that defines how to map each variant of [TextCitationParam] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitCitationCharLocation(citationCharLocation: CitationCharLocationParam): T

        fun visitCitationPageLocation(citationPageLocation: CitationPageLocationParam): T

        fun visitCitationContentBlockLocation(
            citationContentBlockLocation: CitationContentBlockLocationParam
        ): T

        /**
         * Maps an unknown variant of [TextCitationParam] to a value of type [T].
         *
         * An instance of [TextCitationParam] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown TextCitationParam: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<TextCitationParam>(TextCitationParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): TextCitationParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "char_location" -> {
                    tryDeserialize(node, jacksonTypeRef<CitationCharLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return TextCitationParam(citationCharLocation = it, _json = json)
                        }
                }
                "page_location" -> {
                    tryDeserialize(node, jacksonTypeRef<CitationPageLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return TextCitationParam(citationPageLocation = it, _json = json)
                        }
                }
                "content_block_location" -> {
                    tryDeserialize(node, jacksonTypeRef<CitationContentBlockLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return TextCitationParam(
                                citationContentBlockLocation = it,
                                _json = json,
                            )
                        }
                }
            }

            return TextCitationParam(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<TextCitationParam>(TextCitationParam::class) {

        override fun serialize(
            value: TextCitationParam,
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
                else -> throw IllegalStateException("Invalid TextCitationParam")
            }
        }
    }
}
