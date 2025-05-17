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
    private val charLocation: CitationCharLocationParam? = null,
    private val pageLocation: CitationPageLocationParam? = null,
    private val contentBlockLocation: CitationContentBlockLocationParam? = null,
    private val webSearchResultLocation: CitationWebSearchResultLocationParam? = null,
    private val _json: JsonValue? = null,
) {

    fun charLocation(): Optional<CitationCharLocationParam> = Optional.ofNullable(charLocation)

    fun pageLocation(): Optional<CitationPageLocationParam> = Optional.ofNullable(pageLocation)

    fun contentBlockLocation(): Optional<CitationContentBlockLocationParam> =
        Optional.ofNullable(contentBlockLocation)

    fun webSearchResultLocation(): Optional<CitationWebSearchResultLocationParam> =
        Optional.ofNullable(webSearchResultLocation)

    fun isCharLocation(): Boolean = charLocation != null

    fun isPageLocation(): Boolean = pageLocation != null

    fun isContentBlockLocation(): Boolean = contentBlockLocation != null

    fun isWebSearchResultLocation(): Boolean = webSearchResultLocation != null

    fun asCharLocation(): CitationCharLocationParam = charLocation.getOrThrow("charLocation")

    fun asPageLocation(): CitationPageLocationParam = pageLocation.getOrThrow("pageLocation")

    fun asContentBlockLocation(): CitationContentBlockLocationParam =
        contentBlockLocation.getOrThrow("contentBlockLocation")

    fun asWebSearchResultLocation(): CitationWebSearchResultLocationParam =
        webSearchResultLocation.getOrThrow("webSearchResultLocation")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            charLocation != null -> visitor.visitCharLocation(charLocation)
            pageLocation != null -> visitor.visitPageLocation(pageLocation)
            contentBlockLocation != null -> visitor.visitContentBlockLocation(contentBlockLocation)
            webSearchResultLocation != null ->
                visitor.visitWebSearchResultLocation(webSearchResultLocation)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): TextCitationParam = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitCharLocation(charLocation: CitationCharLocationParam) {
                    charLocation.validate()
                }

                override fun visitPageLocation(pageLocation: CitationPageLocationParam) {
                    pageLocation.validate()
                }

                override fun visitContentBlockLocation(
                    contentBlockLocation: CitationContentBlockLocationParam
                ) {
                    contentBlockLocation.validate()
                }

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: CitationWebSearchResultLocationParam
                ) {
                    webSearchResultLocation.validate()
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
                override fun visitCharLocation(charLocation: CitationCharLocationParam) =
                    charLocation.validity()

                override fun visitPageLocation(pageLocation: CitationPageLocationParam) =
                    pageLocation.validity()

                override fun visitContentBlockLocation(
                    contentBlockLocation: CitationContentBlockLocationParam
                ) = contentBlockLocation.validity()

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: CitationWebSearchResultLocationParam
                ) = webSearchResultLocation.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is TextCitationParam && charLocation == other.charLocation && pageLocation == other.pageLocation && contentBlockLocation == other.contentBlockLocation && webSearchResultLocation == other.webSearchResultLocation /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(charLocation, pageLocation, contentBlockLocation, webSearchResultLocation) /* spotless:on */

    override fun toString(): String =
        when {
            charLocation != null -> "TextCitationParam{charLocation=$charLocation}"
            pageLocation != null -> "TextCitationParam{pageLocation=$pageLocation}"
            contentBlockLocation != null ->
                "TextCitationParam{contentBlockLocation=$contentBlockLocation}"
            webSearchResultLocation != null ->
                "TextCitationParam{webSearchResultLocation=$webSearchResultLocation}"
            _json != null -> "TextCitationParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid TextCitationParam")
        }

    companion object {

        @JvmStatic
        fun ofCharLocation(charLocation: CitationCharLocationParam) =
            TextCitationParam(charLocation = charLocation)

        @JvmStatic
        fun ofPageLocation(pageLocation: CitationPageLocationParam) =
            TextCitationParam(pageLocation = pageLocation)

        @JvmStatic
        fun ofContentBlockLocation(contentBlockLocation: CitationContentBlockLocationParam) =
            TextCitationParam(contentBlockLocation = contentBlockLocation)

        @JvmStatic
        fun ofWebSearchResultLocation(
            webSearchResultLocation: CitationWebSearchResultLocationParam
        ) = TextCitationParam(webSearchResultLocation = webSearchResultLocation)
    }

    /**
     * An interface that defines how to map each variant of [TextCitationParam] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitCharLocation(charLocation: CitationCharLocationParam): T

        fun visitPageLocation(pageLocation: CitationPageLocationParam): T

        fun visitContentBlockLocation(contentBlockLocation: CitationContentBlockLocationParam): T

        fun visitWebSearchResultLocation(
            webSearchResultLocation: CitationWebSearchResultLocationParam
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
                        TextCitationParam(charLocation = it, _json = json)
                    } ?: TextCitationParam(_json = json)
                }
                "page_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<CitationPageLocationParam>())?.let {
                        TextCitationParam(pageLocation = it, _json = json)
                    } ?: TextCitationParam(_json = json)
                }
                "content_block_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<CitationContentBlockLocationParam>())
                        ?.let { TextCitationParam(contentBlockLocation = it, _json = json) }
                        ?: TextCitationParam(_json = json)
                }
                "web_search_result_location" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CitationWebSearchResultLocationParam>(),
                        )
                        ?.let { TextCitationParam(webSearchResultLocation = it, _json = json) }
                        ?: TextCitationParam(_json = json)
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
                value.charLocation != null -> generator.writeObject(value.charLocation)
                value.pageLocation != null -> generator.writeObject(value.pageLocation)
                value.contentBlockLocation != null ->
                    generator.writeObject(value.contentBlockLocation)
                value.webSearchResultLocation != null ->
                    generator.writeObject(value.webSearchResultLocation)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid TextCitationParam")
            }
        }
    }
}
