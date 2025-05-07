// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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
    private val citationWebSearchResultLocation: CitationWebSearchResultLocationParam? = null,
    private val _json: JsonValue? = null,
) {

    fun citationCharLocation(): Optional<CitationCharLocationParam> =
        Optional.ofNullable(citationCharLocation)

    fun citationPageLocation(): Optional<CitationPageLocationParam> =
        Optional.ofNullable(citationPageLocation)

    fun citationContentBlockLocation(): Optional<CitationContentBlockLocationParam> =
        Optional.ofNullable(citationContentBlockLocation)

    fun citationWebSearchResultLocation(): Optional<CitationWebSearchResultLocationParam> =
        Optional.ofNullable(citationWebSearchResultLocation)

    fun isCitationCharLocation(): Boolean = citationCharLocation != null

    fun isCitationPageLocation(): Boolean = citationPageLocation != null

    fun isCitationContentBlockLocation(): Boolean = citationContentBlockLocation != null

    fun isCitationWebSearchResultLocation(): Boolean = citationWebSearchResultLocation != null

    fun asCitationCharLocation(): CitationCharLocationParam =
        citationCharLocation.getOrThrow("citationCharLocation")

    fun asCitationPageLocation(): CitationPageLocationParam =
        citationPageLocation.getOrThrow("citationPageLocation")

    fun asCitationContentBlockLocation(): CitationContentBlockLocationParam =
        citationContentBlockLocation.getOrThrow("citationContentBlockLocation")

    fun asCitationWebSearchResultLocation(): CitationWebSearchResultLocationParam =
        citationWebSearchResultLocation.getOrThrow("citationWebSearchResultLocation")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            citationCharLocation != null -> visitor.visitCitationCharLocation(citationCharLocation)
            citationPageLocation != null -> visitor.visitCitationPageLocation(citationPageLocation)
            citationContentBlockLocation != null ->
                visitor.visitCitationContentBlockLocation(citationContentBlockLocation)
            citationWebSearchResultLocation != null ->
                visitor.visitCitationWebSearchResultLocation(citationWebSearchResultLocation)
            else -> visitor.unknown(_json)
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

                override fun visitCitationWebSearchResultLocation(
                    citationWebSearchResultLocation: CitationWebSearchResultLocationParam
                ) {
                    citationWebSearchResultLocation.validate()
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
                    citationCharLocation: CitationCharLocationParam
                ) = citationCharLocation.validity()

                override fun visitCitationPageLocation(
                    citationPageLocation: CitationPageLocationParam
                ) = citationPageLocation.validity()

                override fun visitCitationContentBlockLocation(
                    citationContentBlockLocation: CitationContentBlockLocationParam
                ) = citationContentBlockLocation.validity()

                override fun visitCitationWebSearchResultLocation(
                    citationWebSearchResultLocation: CitationWebSearchResultLocationParam
                ) = citationWebSearchResultLocation.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is TextCitationParam && citationCharLocation == other.citationCharLocation && citationPageLocation == other.citationPageLocation && citationContentBlockLocation == other.citationContentBlockLocation && citationWebSearchResultLocation == other.citationWebSearchResultLocation /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(citationCharLocation, citationPageLocation, citationContentBlockLocation, citationWebSearchResultLocation) /* spotless:on */

    override fun toString(): String =
        when {
            citationCharLocation != null ->
                "TextCitationParam{citationCharLocation=$citationCharLocation}"
            citationPageLocation != null ->
                "TextCitationParam{citationPageLocation=$citationPageLocation}"
            citationContentBlockLocation != null ->
                "TextCitationParam{citationContentBlockLocation=$citationContentBlockLocation}"
            citationWebSearchResultLocation != null ->
                "TextCitationParam{citationWebSearchResultLocation=$citationWebSearchResultLocation}"
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

        @JvmStatic
        fun ofCitationWebSearchResultLocation(
            citationWebSearchResultLocation: CitationWebSearchResultLocationParam
        ) = TextCitationParam(citationWebSearchResultLocation = citationWebSearchResultLocation)
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

        fun visitCitationWebSearchResultLocation(
            citationWebSearchResultLocation: CitationWebSearchResultLocationParam
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
                    return tryDeserialize(node, jacksonTypeRef<CitationCharLocationParam>())?.let {
                        TextCitationParam(citationCharLocation = it, _json = json)
                    } ?: TextCitationParam(_json = json)
                }
                "page_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<CitationPageLocationParam>())?.let {
                        TextCitationParam(citationPageLocation = it, _json = json)
                    } ?: TextCitationParam(_json = json)
                }
                "content_block_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<CitationContentBlockLocationParam>())
                        ?.let { TextCitationParam(citationContentBlockLocation = it, _json = json) }
                        ?: TextCitationParam(_json = json)
                }
                "web_search_result_location" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CitationWebSearchResultLocationParam>(),
                        )
                        ?.let {
                            TextCitationParam(citationWebSearchResultLocation = it, _json = json)
                        } ?: TextCitationParam(_json = json)
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
                value.citationWebSearchResultLocation != null ->
                    generator.writeObject(value.citationWebSearchResultLocation)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid TextCitationParam")
            }
        }
    }
}
