package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
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

    fun type(): Type =
        when {
            charLocation != null -> Type.CHAR_LOCATION
            pageLocation != null -> Type.PAGE_LOCATION
            contentBlockLocation != null -> Type.CONTENT_BLOCK_LOCATION
            webSearchResultLocation != null -> Type.WEB_SEARCH_RESULT_LOCATION
            searchResultLocation != null -> Type.SEARCH_RESULT_LOCATION
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun citedText(): String =
        when {
            charLocation != null -> charLocation.citedText()
            pageLocation != null -> pageLocation.citedText()
            contentBlockLocation != null -> contentBlockLocation.citedText()
            webSearchResultLocation != null -> webSearchResultLocation.citedText()
            searchResultLocation != null -> searchResultLocation.citedText()
            else -> _json.getProperty<String>("cited_text").getRequired("cited_text")
        }

    fun documentIndex(): Optional<Long> =
        when {
            charLocation != null -> Optional.of(charLocation.documentIndex())
            pageLocation != null -> Optional.of(pageLocation.documentIndex())
            contentBlockLocation != null -> Optional.of(contentBlockLocation.documentIndex())
            webSearchResultLocation != null -> Optional.empty()
            searchResultLocation != null -> Optional.empty()
            else -> _json.getProperty<Long>("document_index").asKnown()
        }

    fun documentTitle(): Optional<String> =
        when {
            charLocation != null -> charLocation.documentTitle()
            pageLocation != null -> pageLocation.documentTitle()
            contentBlockLocation != null -> contentBlockLocation.documentTitle()
            webSearchResultLocation != null -> Optional.empty()
            searchResultLocation != null -> Optional.empty()
            else -> _json.getProperty<String>("document_title").asKnown()
        }

    fun endBlockIndex(): Optional<Long> =
        when {
            charLocation != null -> Optional.empty()
            pageLocation != null -> Optional.empty()
            contentBlockLocation != null -> Optional.of(contentBlockLocation.endBlockIndex())
            webSearchResultLocation != null -> Optional.empty()
            searchResultLocation != null -> Optional.of(searchResultLocation.endBlockIndex())
            else -> _json.getProperty<Long>("end_block_index").asKnown()
        }

    fun startBlockIndex(): Optional<Long> =
        when {
            charLocation != null -> Optional.empty()
            pageLocation != null -> Optional.empty()
            contentBlockLocation != null -> Optional.of(contentBlockLocation.startBlockIndex())
            webSearchResultLocation != null -> Optional.empty()
            searchResultLocation != null -> Optional.of(searchResultLocation.startBlockIndex())
            else -> _json.getProperty<Long>("start_block_index").asKnown()
        }

    fun title(): Optional<String> =
        when {
            charLocation != null -> Optional.empty()
            pageLocation != null -> Optional.empty()
            contentBlockLocation != null -> Optional.empty()
            webSearchResultLocation != null -> webSearchResultLocation.title()
            searchResultLocation != null -> searchResultLocation.title()
            else -> _json.getProperty<String>("title").asKnown()
        }

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

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaTextCitationParam.accept(new BetaTextCitationParam.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitCharLocation(BetaCitationCharLocationParam charLocation) {
     *         return Optional.of(charLocation.toString());
     *     }
     *
     *     // ...
     *
     *     @Override
     *     public Optional<String> unknown(JsonValue json) {
     *         // Or inspect the `json`.
     *         return Optional.empty();
     *     }
     * });
     * ```
     *
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
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

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaTextCitationParam = apply {
        if (validated) {
            return@apply
        }

        when {
            charLocation != null -> charLocation.validate()
            pageLocation != null -> pageLocation.validate()
            contentBlockLocation != null -> contentBlockLocation.validate()
            webSearchResultLocation != null -> webSearchResultLocation.validate()
            searchResultLocation != null -> searchResultLocation.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaTextCitationParam: $_json")
        }
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
        when {
            charLocation != null -> charLocation.validity()
            pageLocation != null -> pageLocation.validity()
            contentBlockLocation != null -> contentBlockLocation.validity()
            webSearchResultLocation != null -> webSearchResultLocation.validity()
            searchResultLocation != null -> searchResultLocation.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTextCitationParam &&
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

    class Type private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val CHAR_LOCATION = Type(JsonField.of("char_location"))

            @JvmField val PAGE_LOCATION = Type(JsonField.of("page_location"))

            @JvmField val CONTENT_BLOCK_LOCATION = Type(JsonField.of("content_block_location"))

            @JvmField
            val WEB_SEARCH_RESULT_LOCATION = Type(JsonField.of("web_search_result_location"))

            @JvmField val SEARCH_RESULT_LOCATION = Type(JsonField.of("search_result_location"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "char_location" -> CHAR_LOCATION
                    "page_location" -> PAGE_LOCATION
                    "content_block_location" -> CONTENT_BLOCK_LOCATION
                    "web_search_result_location" -> WEB_SEARCH_RESULT_LOCATION
                    "search_result_location" -> SEARCH_RESULT_LOCATION
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            CHAR_LOCATION,
            PAGE_LOCATION,
            CONTENT_BLOCK_LOCATION,
            WEB_SEARCH_RESULT_LOCATION,
            SEARCH_RESULT_LOCATION,
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            CHAR_LOCATION,
            PAGE_LOCATION,
            CONTENT_BLOCK_LOCATION,
            WEB_SEARCH_RESULT_LOCATION,
            SEARCH_RESULT_LOCATION,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                CHAR_LOCATION -> Value.CHAR_LOCATION
                PAGE_LOCATION -> Value.PAGE_LOCATION
                CONTENT_BLOCK_LOCATION -> Value.CONTENT_BLOCK_LOCATION
                WEB_SEARCH_RESULT_LOCATION -> Value.WEB_SEARCH_RESULT_LOCATION
                SEARCH_RESULT_LOCATION -> Value.SEARCH_RESULT_LOCATION
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                CHAR_LOCATION -> Known.CHAR_LOCATION
                PAGE_LOCATION -> Known.PAGE_LOCATION
                CONTENT_BLOCK_LOCATION -> Known.CONTENT_BLOCK_LOCATION
                WEB_SEARCH_RESULT_LOCATION -> Known.WEB_SEARCH_RESULT_LOCATION
                SEARCH_RESULT_LOCATION -> Known.SEARCH_RESULT_LOCATION
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Type = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
