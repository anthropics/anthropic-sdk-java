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

@JsonDeserialize(using = BetaTextCitationParam.Deserializer::class)
@JsonSerialize(using = BetaTextCitationParam.Serializer::class)
class BetaTextCitationParam
private constructor(
    private val charLocation: BetaCitationCharLocationParam? = null,
    private val pageLocation: BetaCitationPageLocationParam? = null,
    private val contentBlockLocation: BetaCitationContentBlockLocationParam? = null,
    private val webSearchResultLocation: BetaCitationWebSearchResultLocationParam? = null,
    private val searchResultLocation: BetaCitationSearchResultLocationParam? = null,
    private val _json: JsonValue? = null,
) {

    fun charLocation(): Optional<BetaCitationCharLocationParam> = Optional.ofNullable(charLocation)

    fun pageLocation(): Optional<BetaCitationPageLocationParam> = Optional.ofNullable(pageLocation)

    fun contentBlockLocation(): Optional<BetaCitationContentBlockLocationParam> =
        Optional.ofNullable(contentBlockLocation)

    fun webSearchResultLocation(): Optional<BetaCitationWebSearchResultLocationParam> =
        Optional.ofNullable(webSearchResultLocation)

    fun searchResultLocation(): Optional<BetaCitationSearchResultLocationParam> =
        Optional.ofNullable(searchResultLocation)

    fun isCharLocation(): Boolean = charLocation != null

    fun isPageLocation(): Boolean = pageLocation != null

    fun isContentBlockLocation(): Boolean = contentBlockLocation != null

    fun isWebSearchResultLocation(): Boolean = webSearchResultLocation != null

    fun isSearchResultLocation(): Boolean = searchResultLocation != null

    fun asCharLocation(): BetaCitationCharLocationParam = charLocation.getOrThrow("charLocation")

    fun asPageLocation(): BetaCitationPageLocationParam = pageLocation.getOrThrow("pageLocation")

    fun asContentBlockLocation(): BetaCitationContentBlockLocationParam =
        contentBlockLocation.getOrThrow("contentBlockLocation")

    fun asWebSearchResultLocation(): BetaCitationWebSearchResultLocationParam =
        webSearchResultLocation.getOrThrow("webSearchResultLocation")

    fun asSearchResultLocation(): BetaCitationSearchResultLocationParam =
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

    fun validate(): BetaTextCitationParam = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitCharLocation(charLocation: BetaCitationCharLocationParam) {
                    charLocation.validate()
                }

                override fun visitPageLocation(pageLocation: BetaCitationPageLocationParam) {
                    pageLocation.validate()
                }

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocationParam
                ) {
                    contentBlockLocation.validate()
                }

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationWebSearchResultLocationParam
                ) {
                    webSearchResultLocation.validate()
                }

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocationParam
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
                override fun visitCharLocation(charLocation: BetaCitationCharLocationParam) =
                    charLocation.validity()

                override fun visitPageLocation(pageLocation: BetaCitationPageLocationParam) =
                    pageLocation.validity()

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocationParam
                ) = contentBlockLocation.validity()

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationWebSearchResultLocationParam
                ) = webSearchResultLocation.validity()

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocationParam
                ) = searchResultLocation.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaTextCitationParam && charLocation == other.charLocation && pageLocation == other.pageLocation && contentBlockLocation == other.contentBlockLocation && webSearchResultLocation == other.webSearchResultLocation && searchResultLocation == other.searchResultLocation /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(charLocation, pageLocation, contentBlockLocation, webSearchResultLocation, searchResultLocation) /* spotless:on */

    override fun toString(): String =
        when {
            charLocation != null -> "BetaTextCitationParam{charLocation=$charLocation}"
            pageLocation != null -> "BetaTextCitationParam{pageLocation=$pageLocation}"
            contentBlockLocation != null ->
                "BetaTextCitationParam{contentBlockLocation=$contentBlockLocation}"
            webSearchResultLocation != null ->
                "BetaTextCitationParam{webSearchResultLocation=$webSearchResultLocation}"
            searchResultLocation != null ->
                "BetaTextCitationParam{searchResultLocation=$searchResultLocation}"
            _json != null -> "BetaTextCitationParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTextCitationParam")
        }

    companion object {

        @JvmStatic
        fun ofCharLocation(charLocation: BetaCitationCharLocationParam) =
            BetaTextCitationParam(charLocation = charLocation)

        @JvmStatic
        fun ofPageLocation(pageLocation: BetaCitationPageLocationParam) =
            BetaTextCitationParam(pageLocation = pageLocation)

        @JvmStatic
        fun ofContentBlockLocation(contentBlockLocation: BetaCitationContentBlockLocationParam) =
            BetaTextCitationParam(contentBlockLocation = contentBlockLocation)

        @JvmStatic
        fun ofWebSearchResultLocation(
            webSearchResultLocation: BetaCitationWebSearchResultLocationParam
        ) = BetaTextCitationParam(webSearchResultLocation = webSearchResultLocation)

        @JvmStatic
        fun ofSearchResultLocation(searchResultLocation: BetaCitationSearchResultLocationParam) =
            BetaTextCitationParam(searchResultLocation = searchResultLocation)
    }

    /**
     * An interface that defines how to map each variant of [BetaTextCitationParam] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitCharLocation(charLocation: BetaCitationCharLocationParam): T

        fun visitPageLocation(pageLocation: BetaCitationPageLocationParam): T

        fun visitContentBlockLocation(
            contentBlockLocation: BetaCitationContentBlockLocationParam
        ): T

        fun visitWebSearchResultLocation(
            webSearchResultLocation: BetaCitationWebSearchResultLocationParam
        ): T

        fun visitSearchResultLocation(
            searchResultLocation: BetaCitationSearchResultLocationParam
        ): T

        /**
         * Maps an unknown variant of [BetaTextCitationParam] to a value of type [T].
         *
         * An instance of [BetaTextCitationParam] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaTextCitationParam: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaTextCitationParam>(BetaTextCitationParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaTextCitationParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "char_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCitationCharLocationParam>())
                        ?.let { BetaTextCitationParam(charLocation = it, _json = json) }
                        ?: BetaTextCitationParam(_json = json)
                }
                "page_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCitationPageLocationParam>())
                        ?.let { BetaTextCitationParam(pageLocation = it, _json = json) }
                        ?: BetaTextCitationParam(_json = json)
                }
                "content_block_location" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaCitationContentBlockLocationParam>(),
                        )
                        ?.let { BetaTextCitationParam(contentBlockLocation = it, _json = json) }
                        ?: BetaTextCitationParam(_json = json)
                }
                "web_search_result_location" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaCitationWebSearchResultLocationParam>(),
                        )
                        ?.let { BetaTextCitationParam(webSearchResultLocation = it, _json = json) }
                        ?: BetaTextCitationParam(_json = json)
                }
                "search_result_location" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaCitationSearchResultLocationParam>(),
                        )
                        ?.let { BetaTextCitationParam(searchResultLocation = it, _json = json) }
                        ?: BetaTextCitationParam(_json = json)
                }
            }

            return BetaTextCitationParam(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaTextCitationParam>(BetaTextCitationParam::class) {

        override fun serialize(
            value: BetaTextCitationParam,
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
                else -> throw IllegalStateException("Invalid BetaTextCitationParam")
            }
        }
    }
}
