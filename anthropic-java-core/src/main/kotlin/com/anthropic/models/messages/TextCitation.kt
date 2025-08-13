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

@JsonDeserialize(using = TextCitation.Deserializer::class)
@JsonSerialize(using = TextCitation.Serializer::class)
class TextCitation
private constructor(
    private val charLocation: CitationCharLocation? = null,
    private val pageLocation: CitationPageLocation? = null,
    private val contentBlockLocation: CitationContentBlockLocation? = null,
    private val webSearchResultLocation: CitationsWebSearchResultLocation? = null,
    private val searchResultLocation: CitationsSearchResultLocation? = null,
    private val _json: JsonValue? = null,
) {

    fun charLocation(): Optional<CitationCharLocation> = Optional.ofNullable(charLocation)

    fun pageLocation(): Optional<CitationPageLocation> = Optional.ofNullable(pageLocation)

    fun contentBlockLocation(): Optional<CitationContentBlockLocation> =
        Optional.ofNullable(contentBlockLocation)

    fun webSearchResultLocation(): Optional<CitationsWebSearchResultLocation> =
        Optional.ofNullable(webSearchResultLocation)

    fun searchResultLocation(): Optional<CitationsSearchResultLocation> =
        Optional.ofNullable(searchResultLocation)

    fun isCharLocation(): Boolean = charLocation != null

    fun isPageLocation(): Boolean = pageLocation != null

    fun isContentBlockLocation(): Boolean = contentBlockLocation != null

    fun isWebSearchResultLocation(): Boolean = webSearchResultLocation != null

    fun isSearchResultLocation(): Boolean = searchResultLocation != null

    fun asCharLocation(): CitationCharLocation = charLocation.getOrThrow("charLocation")

    fun asPageLocation(): CitationPageLocation = pageLocation.getOrThrow("pageLocation")

    fun asContentBlockLocation(): CitationContentBlockLocation =
        contentBlockLocation.getOrThrow("contentBlockLocation")

    fun asWebSearchResultLocation(): CitationsWebSearchResultLocation =
        webSearchResultLocation.getOrThrow("webSearchResultLocation")

    fun asSearchResultLocation(): CitationsSearchResultLocation =
        searchResultLocation.getOrThrow("searchResultLocation")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            charLocation != null -> visitor.visitCharLocation(charLocation)
            pageLocation != null -> visitor.visitPageLocation(pageLocation)
            contentBlockLocation != null -> visitor.visitContentBlockLocation(contentBlockLocation)
            webSearchResultLocation != null ->
                visitor.visitWebSearchResultLocation(webSearchResultLocation)
            searchResultLocation != null -> visitor.visitSearchResultLocation(searchResultLocation)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): TextCitation = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitCharLocation(charLocation: CitationCharLocation) {
                    charLocation.validate()
                }

                override fun visitPageLocation(pageLocation: CitationPageLocation) {
                    pageLocation.validate()
                }

                override fun visitContentBlockLocation(
                    contentBlockLocation: CitationContentBlockLocation
                ) {
                    contentBlockLocation.validate()
                }

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: CitationsWebSearchResultLocation
                ) {
                    webSearchResultLocation.validate()
                }

                override fun visitSearchResultLocation(
                    searchResultLocation: CitationsSearchResultLocation
                ) {
                    searchResultLocation.validate()
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
                override fun visitCharLocation(charLocation: CitationCharLocation) =
                    charLocation.validity()

                override fun visitPageLocation(pageLocation: CitationPageLocation) =
                    pageLocation.validity()

                override fun visitContentBlockLocation(
                    contentBlockLocation: CitationContentBlockLocation
                ) = contentBlockLocation.validity()

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: CitationsWebSearchResultLocation
                ) = webSearchResultLocation.validity()

                override fun visitSearchResultLocation(
                    searchResultLocation: CitationsSearchResultLocation
                ) = searchResultLocation.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TextCitation &&
            charLocation == other.charLocation &&
            pageLocation == other.pageLocation &&
            contentBlockLocation == other.contentBlockLocation &&
            webSearchResultLocation == other.webSearchResultLocation &&
            searchResultLocation == other.searchResultLocation
    }

    override fun hashCode(): Int =
        Objects.hash(
            charLocation,
            pageLocation,
            contentBlockLocation,
            webSearchResultLocation,
            searchResultLocation,
        )

    override fun toString(): String =
        when {
            charLocation != null -> "TextCitation{charLocation=$charLocation}"
            pageLocation != null -> "TextCitation{pageLocation=$pageLocation}"
            contentBlockLocation != null ->
                "TextCitation{contentBlockLocation=$contentBlockLocation}"
            webSearchResultLocation != null ->
                "TextCitation{webSearchResultLocation=$webSearchResultLocation}"
            searchResultLocation != null ->
                "TextCitation{searchResultLocation=$searchResultLocation}"
            _json != null -> "TextCitation{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid TextCitation")
        }

    companion object {

        @JvmStatic
        fun ofCharLocation(charLocation: CitationCharLocation) =
            TextCitation(charLocation = charLocation)

        @JvmStatic
        fun ofPageLocation(pageLocation: CitationPageLocation) =
            TextCitation(pageLocation = pageLocation)

        @JvmStatic
        fun ofContentBlockLocation(contentBlockLocation: CitationContentBlockLocation) =
            TextCitation(contentBlockLocation = contentBlockLocation)

        @JvmStatic
        fun ofWebSearchResultLocation(webSearchResultLocation: CitationsWebSearchResultLocation) =
            TextCitation(webSearchResultLocation = webSearchResultLocation)

        @JvmStatic
        fun ofSearchResultLocation(searchResultLocation: CitationsSearchResultLocation) =
            TextCitation(searchResultLocation = searchResultLocation)
    }

    /**
     * An interface that defines how to map each variant of [TextCitation] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitCharLocation(charLocation: CitationCharLocation): T

        fun visitPageLocation(pageLocation: CitationPageLocation): T

        fun visitContentBlockLocation(contentBlockLocation: CitationContentBlockLocation): T

        fun visitWebSearchResultLocation(
            webSearchResultLocation: CitationsWebSearchResultLocation
        ): T

        fun visitSearchResultLocation(searchResultLocation: CitationsSearchResultLocation): T

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
                    return tryDeserialize(node, jacksonTypeRef<CitationCharLocation>())?.let {
                        TextCitation(charLocation = it, _json = json)
                    } ?: TextCitation(_json = json)
                }
                "page_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<CitationPageLocation>())?.let {
                        TextCitation(pageLocation = it, _json = json)
                    } ?: TextCitation(_json = json)
                }
                "content_block_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<CitationContentBlockLocation>())
                        ?.let { TextCitation(contentBlockLocation = it, _json = json) }
                        ?: TextCitation(_json = json)
                }
                "web_search_result_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<CitationsWebSearchResultLocation>())
                        ?.let { TextCitation(webSearchResultLocation = it, _json = json) }
                        ?: TextCitation(_json = json)
                }
                "search_result_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<CitationsSearchResultLocation>())
                        ?.let { TextCitation(searchResultLocation = it, _json = json) }
                        ?: TextCitation(_json = json)
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
                value.charLocation != null -> generator.writeObject(value.charLocation)
                value.pageLocation != null -> generator.writeObject(value.pageLocation)
                value.contentBlockLocation != null ->
                    generator.writeObject(value.contentBlockLocation)
                value.webSearchResultLocation != null ->
                    generator.writeObject(value.webSearchResultLocation)
                value.searchResultLocation != null ->
                    generator.writeObject(value.searchResultLocation)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid TextCitation")
            }
        }
    }
}
