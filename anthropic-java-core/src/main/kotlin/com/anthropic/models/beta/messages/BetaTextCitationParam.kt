// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
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
        accept(
            object : Visitor<Type> {
                override fun visitCharLocation(charLocation: BetaCitationCharLocationParam): Type =
                    Type.CHAR_LOCATION

                override fun visitPageLocation(pageLocation: BetaCitationPageLocationParam): Type =
                    Type.PAGE_LOCATION

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocationParam
                ): Type = Type.CONTENT_BLOCK_LOCATION

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationWebSearchResultLocationParam
                ): Type = Type.WEB_SEARCH_RESULT_LOCATION

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocationParam
                ): Type = Type.SEARCH_RESULT_LOCATION

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun citedText(): String =
        accept(
            object : Visitor<String> {
                override fun visitCharLocation(
                    charLocation: BetaCitationCharLocationParam
                ): String = charLocation.citedText()

                override fun visitPageLocation(
                    pageLocation: BetaCitationPageLocationParam
                ): String = pageLocation.citedText()

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocationParam
                ): String = contentBlockLocation.citedText()

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationWebSearchResultLocationParam
                ): String = webSearchResultLocation.citedText()

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocationParam
                ): String = searchResultLocation.citedText()
            }
        )

    fun documentIndex(): Optional<Long> =
        accept(
            object : Visitor<Optional<Long>> {
                override fun visitCharLocation(
                    charLocation: BetaCitationCharLocationParam
                ): Optional<Long> = Optional.of(charLocation.documentIndex())

                override fun visitPageLocation(
                    pageLocation: BetaCitationPageLocationParam
                ): Optional<Long> = Optional.of(pageLocation.documentIndex())

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocationParam
                ): Optional<Long> = Optional.of(contentBlockLocation.documentIndex())

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationWebSearchResultLocationParam
                ): Optional<Long> = Optional.empty()

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocationParam
                ): Optional<Long> = Optional.empty()
            }
        )

    fun documentTitle(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitCharLocation(
                    charLocation: BetaCitationCharLocationParam
                ): Optional<String> = charLocation.documentTitle()

                override fun visitPageLocation(
                    pageLocation: BetaCitationPageLocationParam
                ): Optional<String> = pageLocation.documentTitle()

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocationParam
                ): Optional<String> = contentBlockLocation.documentTitle()

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationWebSearchResultLocationParam
                ): Optional<String> = Optional.empty()

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocationParam
                ): Optional<String> = Optional.empty()
            }
        )

    fun endBlockIndex(): Optional<Long> =
        accept(
            object : Visitor<Optional<Long>> {
                override fun visitCharLocation(
                    charLocation: BetaCitationCharLocationParam
                ): Optional<Long> = Optional.empty()

                override fun visitPageLocation(
                    pageLocation: BetaCitationPageLocationParam
                ): Optional<Long> = Optional.empty()

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocationParam
                ): Optional<Long> = Optional.of(contentBlockLocation.endBlockIndex())

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationWebSearchResultLocationParam
                ): Optional<Long> = Optional.empty()

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocationParam
                ): Optional<Long> = Optional.of(searchResultLocation.endBlockIndex())
            }
        )

    fun startBlockIndex(): Optional<Long> =
        accept(
            object : Visitor<Optional<Long>> {
                override fun visitCharLocation(
                    charLocation: BetaCitationCharLocationParam
                ): Optional<Long> = Optional.empty()

                override fun visitPageLocation(
                    pageLocation: BetaCitationPageLocationParam
                ): Optional<Long> = Optional.empty()

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocationParam
                ): Optional<Long> = Optional.of(contentBlockLocation.startBlockIndex())

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationWebSearchResultLocationParam
                ): Optional<Long> = Optional.empty()

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocationParam
                ): Optional<Long> = Optional.of(searchResultLocation.startBlockIndex())
            }
        )

    fun title(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitCharLocation(
                    charLocation: BetaCitationCharLocationParam
                ): Optional<String> = Optional.empty()

                override fun visitPageLocation(
                    pageLocation: BetaCitationPageLocationParam
                ): Optional<String> = Optional.empty()

                override fun visitContentBlockLocation(
                    contentBlockLocation: BetaCitationContentBlockLocationParam
                ): Optional<String> = Optional.empty()

                override fun visitWebSearchResultLocation(
                    webSearchResultLocation: BetaCitationWebSearchResultLocationParam
                ): Optional<String> = webSearchResultLocation.title()

                override fun visitSearchResultLocation(
                    searchResultLocation: BetaCitationSearchResultLocationParam
                ): Optional<String> = searchResultLocation.title()
            }
        )

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

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val CHAR_LOCATION = of("char_location")

            @JvmField val PAGE_LOCATION = of("page_location")

            @JvmField val CONTENT_BLOCK_LOCATION = of("content_block_location")

            @JvmField val WEB_SEARCH_RESULT_LOCATION = of("web_search_result_location")

            @JvmField val SEARCH_RESULT_LOCATION = of("search_result_location")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonValue): Type =
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
