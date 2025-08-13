// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaCitationsDelta
private constructor(
    private val citation: JsonField<Citation>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("citation") @ExcludeMissing citation: JsonField<Citation> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(citation, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun citation(): Citation = citation.getRequired("citation")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("citations_delta")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [citation].
     *
     * Unlike [citation], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("citation") @ExcludeMissing fun _citation(): JsonField<Citation> = citation

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaCitationsDelta].
         *
         * The following fields are required:
         * ```java
         * .citation()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCitationsDelta]. */
    class Builder internal constructor() {

        private var citation: JsonField<Citation>? = null
        private var type: JsonValue = JsonValue.from("citations_delta")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCitationsDelta: BetaCitationsDelta) = apply {
            citation = betaCitationsDelta.citation
            type = betaCitationsDelta.type
            additionalProperties = betaCitationsDelta.additionalProperties.toMutableMap()
        }

        fun citation(citation: Citation) = citation(JsonField.of(citation))

        /**
         * Sets [Builder.citation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citation] with a well-typed [Citation] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun citation(citation: JsonField<Citation>) = apply { this.citation = citation }

        /** Alias for calling [citation] with `Citation.ofCharLocation(charLocation)`. */
        fun citation(charLocation: BetaCitationCharLocation) =
            citation(Citation.ofCharLocation(charLocation))

        /** Alias for calling [citation] with `Citation.ofPageLocation(pageLocation)`. */
        fun citation(pageLocation: BetaCitationPageLocation) =
            citation(Citation.ofPageLocation(pageLocation))

        /**
         * Alias for calling [citation] with
         * `Citation.ofContentBlockLocation(contentBlockLocation)`.
         */
        fun citation(contentBlockLocation: BetaCitationContentBlockLocation) =
            citation(Citation.ofContentBlockLocation(contentBlockLocation))

        /**
         * Alias for calling [citation] with
         * `Citation.ofWebSearchResultLocation(webSearchResultLocation)`.
         */
        fun citation(webSearchResultLocation: BetaCitationsWebSearchResultLocation) =
            citation(Citation.ofWebSearchResultLocation(webSearchResultLocation))

        /**
         * Alias for calling [citation] with
         * `Citation.ofSearchResultLocation(searchResultLocation)`.
         */
        fun citation(searchResultLocation: BetaCitationSearchResultLocation) =
            citation(Citation.ofSearchResultLocation(searchResultLocation))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("citations_delta")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaCitationsDelta].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .citation()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaCitationsDelta =
            BetaCitationsDelta(
                checkRequired("citation", citation),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaCitationsDelta = apply {
        if (validated) {
            return@apply
        }

        citation().validate()
        _type().let {
            if (it != JsonValue.from("citations_delta")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
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
        (citation.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("citations_delta")) 1 else 0 }

    @JsonDeserialize(using = Citation.Deserializer::class)
    @JsonSerialize(using = Citation.Serializer::class)
    class Citation
    private constructor(
        private val charLocation: BetaCitationCharLocation? = null,
        private val pageLocation: BetaCitationPageLocation? = null,
        private val contentBlockLocation: BetaCitationContentBlockLocation? = null,
        private val webSearchResultLocation: BetaCitationsWebSearchResultLocation? = null,
        private val searchResultLocation: BetaCitationSearchResultLocation? = null,
        private val _json: JsonValue? = null,
    ) {

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
                contentBlockLocation != null ->
                    visitor.visitContentBlockLocation(contentBlockLocation)
                webSearchResultLocation != null ->
                    visitor.visitWebSearchResultLocation(webSearchResultLocation)
                searchResultLocation != null ->
                    visitor.visitSearchResultLocation(searchResultLocation)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Citation = apply {
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
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

            return other is Citation &&
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
                charLocation != null -> "Citation{charLocation=$charLocation}"
                pageLocation != null -> "Citation{pageLocation=$pageLocation}"
                contentBlockLocation != null ->
                    "Citation{contentBlockLocation=$contentBlockLocation}"
                webSearchResultLocation != null ->
                    "Citation{webSearchResultLocation=$webSearchResultLocation}"
                searchResultLocation != null ->
                    "Citation{searchResultLocation=$searchResultLocation}"
                _json != null -> "Citation{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Citation")
            }

        companion object {

            @JvmStatic
            fun ofCharLocation(charLocation: BetaCitationCharLocation) =
                Citation(charLocation = charLocation)

            @JvmStatic
            fun ofPageLocation(pageLocation: BetaCitationPageLocation) =
                Citation(pageLocation = pageLocation)

            @JvmStatic
            fun ofContentBlockLocation(contentBlockLocation: BetaCitationContentBlockLocation) =
                Citation(contentBlockLocation = contentBlockLocation)

            @JvmStatic
            fun ofWebSearchResultLocation(
                webSearchResultLocation: BetaCitationsWebSearchResultLocation
            ) = Citation(webSearchResultLocation = webSearchResultLocation)

            @JvmStatic
            fun ofSearchResultLocation(searchResultLocation: BetaCitationSearchResultLocation) =
                Citation(searchResultLocation = searchResultLocation)
        }

        /**
         * An interface that defines how to map each variant of [Citation] to a value of type [T].
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
             * Maps an unknown variant of [Citation] to a value of type [T].
             *
             * An instance of [Citation] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Citation: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Citation>(Citation::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Citation {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "char_location" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaCitationCharLocation>())
                            ?.let { Citation(charLocation = it, _json = json) }
                            ?: Citation(_json = json)
                    }
                    "page_location" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaCitationPageLocation>())
                            ?.let { Citation(pageLocation = it, _json = json) }
                            ?: Citation(_json = json)
                    }
                    "content_block_location" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaCitationContentBlockLocation>(),
                            )
                            ?.let { Citation(contentBlockLocation = it, _json = json) }
                            ?: Citation(_json = json)
                    }
                    "web_search_result_location" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaCitationsWebSearchResultLocation>(),
                            )
                            ?.let { Citation(webSearchResultLocation = it, _json = json) }
                            ?: Citation(_json = json)
                    }
                    "search_result_location" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaCitationSearchResultLocation>(),
                            )
                            ?.let { Citation(searchResultLocation = it, _json = json) }
                            ?: Citation(_json = json)
                    }
                }

                return Citation(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Citation>(Citation::class) {

            override fun serialize(
                value: Citation,
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
                    else -> throw IllegalStateException("Invalid Citation")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaCitationsDelta &&
            citation == other.citation &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(citation, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCitationsDelta{citation=$citation, type=$type, additionalProperties=$additionalProperties}"
}
