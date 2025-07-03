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
    private val charLocation: BetaCitationCharLocation? = null,
    private val pageLocation: BetaCitationPageLocation? = null,
    private val contentBlockLocation: BetaCitationContentBlockLocation? = null,
    private val webSearchResultLocation: BetaCitationsWebSearchResultLocation? = null,
    private val searchResultLocation: BetaCitationSearchResultLocation? = null,
    private val _json: JsonValue? = null,
) {

    fun toParam(): BetaTextCitationParam =
        accept(
            object : Visitor<BetaTextCitationParam> {
                override fun visitCharLocation(
                    charLocation: BetaCitationCharLocation
                ): BetaTextCitationParam =
                    BetaTextCitationParam.ofCharLocation(charLocation.toParam())

                override fun visitPageLocation(
                    pageLocation: BetaCitationPageLocation
                ): BetaTextCitationParam =
                    BetaTextCitationParam.ofPageLocation(pageLocation.toParam())

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocation
                ): BetaTextCitationParam =
                    BetaTextCitationParam.ofContentBlockLocation(contentBlockLocation.toParam())

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationsWebSearchResultLocation
                ): BetaTextCitationParam =
                    BetaTextCitationParam.ofWebSearchResultLocation(
                        webSearchResultLocation.toParam()
                    )

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocation
                ): BetaTextCitationParam =
                    BetaTextCitationParam.ofSearchResultLocation(searchResultLocation.toParam())
            }
        )

    fun charLocation(): Optional<BetaCitationCharLocation> = Optional.ofNullable(charLocation)

    fun pageLocation(): Optional<BetaCitationPageLocation> = Optional.ofNullable(pageLocation)

    fun contentBlockLocation(): Optional<BetaCitationContentBlockLocation> =
        Optional.ofNullable(contentBlockLocation)

    fun webSearchResultLocation(): Optional<BetaCitationsWebSearchResultLocation> =
        Optional.ofNullable(webSearchResultLocation)

    fun searchResultLocation(): Optional<BetaCitationSearchResultLocation> =
        Optional.ofNullable(searchResultLocation)

    fun isCharLocation(): Boolean = charLocation != null

    fun isPageLocation(): Boolean = pageLocation != null

    fun isContentBlockLocation(): Boolean = contentBlockLocation != null

    fun isWebSearchResultLocation(): Boolean = webSearchResultLocation != null

    fun isSearchResultLocation(): Boolean = searchResultLocation != null

    fun asCharLocation(): BetaCitationCharLocation = charLocation.getOrThrow("charLocation")

    fun asPageLocation(): BetaCitationPageLocation = pageLocation.getOrThrow("pageLocation")

    fun asContentBlockLocation(): BetaCitationContentBlockLocation =
        contentBlockLocation.getOrThrow("contentBlockLocation")

    fun asWebSearchResultLocation(): BetaCitationsWebSearchResultLocation =
        webSearchResultLocation.getOrThrow("webSearchResultLocation")

    fun asSearchResultLocation(): BetaCitationSearchResultLocation =
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

    fun validate(): BetaTextCitation = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitCharLocation(charLocation: BetaCitationCharLocation) {
                    charLocation.validate()
                }

                override fun visitPageLocation(pageLocation: BetaCitationPageLocation) {
                    pageLocation.validate()
                }

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocation
                ) {
                    contentBlockLocation.validate()
                }

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationsWebSearchResultLocation
                ) {
                    webSearchResultLocation.validate()
                }

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocation
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
                override fun visitCharLocation(charLocation: BetaCitationCharLocation) =
                    charLocation.validity()

                override fun visitPageLocation(pageLocation: BetaCitationPageLocation) =
                    pageLocation.validity()

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocation
                ) = contentBlockLocation.validity()

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationsWebSearchResultLocation
                ) = webSearchResultLocation.validity()

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocation
                ) = searchResultLocation.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaTextCitation && charLocation == other.charLocation && pageLocation == other.pageLocation && contentBlockLocation == other.contentBlockLocation && webSearchResultLocation == other.webSearchResultLocation && searchResultLocation == other.searchResultLocation /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(charLocation, pageLocation, contentBlockLocation, webSearchResultLocation, searchResultLocation) /* spotless:on */

    override fun toString(): String =
        when {
            charLocation != null -> "BetaTextCitation{charLocation=$charLocation}"
            pageLocation != null -> "BetaTextCitation{pageLocation=$pageLocation}"
            contentBlockLocation != null ->
                "BetaTextCitation{contentBlockLocation=$contentBlockLocation}"
            webSearchResultLocation != null ->
                "BetaTextCitation{webSearchResultLocation=$webSearchResultLocation}"
            searchResultLocation != null ->
                "BetaTextCitation{searchResultLocation=$searchResultLocation}"
            _json != null -> "BetaTextCitation{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTextCitation")
        }

    companion object {

        @JvmStatic
        fun ofCharLocation(charLocation: BetaCitationCharLocation) =
            BetaTextCitation(charLocation = charLocation)

        @JvmStatic
        fun ofPageLocation(pageLocation: BetaCitationPageLocation) =
            BetaTextCitation(pageLocation = pageLocation)

        @JvmStatic
        fun ofContentBlockLocation(contentBlockLocation: BetaCitationContentBlockLocation) =
            BetaTextCitation(contentBlockLocation = contentBlockLocation)

        @JvmStatic
        fun ofWebSearchResultLocation(
            webSearchResultLocation: BetaCitationsWebSearchResultLocation
        ) = BetaTextCitation(webSearchResultLocation = webSearchResultLocation)

        @JvmStatic
        fun ofSearchResultLocation(searchResultLocation: BetaCitationSearchResultLocation) =
            BetaTextCitation(searchResultLocation = searchResultLocation)
    }

    /**
     * An interface that defines how to map each variant of [BetaTextCitation] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitCharLocation(charLocation: BetaCitationCharLocation): T

        fun visitPageLocation(pageLocation: BetaCitationPageLocation): T

        fun visitContentBlockLocation(contentBlockLocation: BetaCitationContentBlockLocation): T

        fun visitWebSearchResultLocation(
            webSearchResultLocation: BetaCitationsWebSearchResultLocation
        ): T

        fun visitSearchResultLocation(searchResultLocation: BetaCitationSearchResultLocation): T

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
                        BetaTextCitation(charLocation = it, _json = json)
                    } ?: BetaTextCitation(_json = json)
                }
                "page_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCitationPageLocation>())?.let {
                        BetaTextCitation(pageLocation = it, _json = json)
                    } ?: BetaTextCitation(_json = json)
                }
                "content_block_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCitationContentBlockLocation>())
                        ?.let { BetaTextCitation(contentBlockLocation = it, _json = json) }
                        ?: BetaTextCitation(_json = json)
                }
                "web_search_result_location" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaCitationsWebSearchResultLocation>(),
                        )
                        ?.let { BetaTextCitation(webSearchResultLocation = it, _json = json) }
                        ?: BetaTextCitation(_json = json)
                }
                "search_result_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCitationSearchResultLocation>())
                        ?.let { BetaTextCitation(searchResultLocation = it, _json = json) }
                        ?: BetaTextCitation(_json = json)
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
                value.charLocation != null -> generator.writeObject(value.charLocation)
                value.pageLocation != null -> generator.writeObject(value.pageLocation)
                value.contentBlockLocation != null ->
                    generator.writeObject(value.contentBlockLocation)
                value.webSearchResultLocation != null ->
                    generator.writeObject(value.webSearchResultLocation)
                value.searchResultLocation != null ->
                    generator.writeObject(value.searchResultLocation)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaTextCitation")
            }
        }
    }
}
