// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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

class CitationsDelta
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
         * Returns a mutable builder for constructing an instance of [CitationsDelta].
         *
         * The following fields are required:
         * ```java
         * .citation()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CitationsDelta]. */
    class Builder internal constructor() {

        private var citation: JsonField<Citation>? = null
        private var type: JsonValue = JsonValue.from("citations_delta")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(citationsDelta: CitationsDelta) = apply {
            citation = citationsDelta.citation
            type = citationsDelta.type
            additionalProperties = citationsDelta.additionalProperties.toMutableMap()
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
        fun citation(charLocation: CitationCharLocation) =
            citation(Citation.ofCharLocation(charLocation))

        /** Alias for calling [citation] with `Citation.ofPageLocation(pageLocation)`. */
        fun citation(pageLocation: CitationPageLocation) =
            citation(Citation.ofPageLocation(pageLocation))

        /**
         * Alias for calling [citation] with
         * `Citation.ofContentBlockLocation(contentBlockLocation)`.
         */
        fun citation(contentBlockLocation: CitationContentBlockLocation) =
            citation(Citation.ofContentBlockLocation(contentBlockLocation))

        /**
         * Alias for calling [citation] with
         * `Citation.ofWebSearchResultLocation(webSearchResultLocation)`.
         */
        fun citation(webSearchResultLocation: CitationsWebSearchResultLocation) =
            citation(Citation.ofWebSearchResultLocation(webSearchResultLocation))

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
         * Returns an immutable instance of [CitationsDelta].
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
        fun build(): CitationsDelta =
            CitationsDelta(
                checkRequired("citation", citation),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): CitationsDelta = apply {
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
        private val charLocation: CitationCharLocation? = null,
        private val pageLocation: CitationPageLocation? = null,
        private val contentBlockLocation: CitationContentBlockLocation? = null,
        private val webSearchResultLocation: CitationsWebSearchResultLocation? = null,
        private val _json: JsonValue? = null,
    ) {

        fun charLocation(): Optional<CitationCharLocation> = Optional.ofNullable(charLocation)

        fun pageLocation(): Optional<CitationPageLocation> = Optional.ofNullable(pageLocation)

        fun contentBlockLocation(): Optional<CitationContentBlockLocation> =
            Optional.ofNullable(contentBlockLocation)

        fun webSearchResultLocation(): Optional<CitationsWebSearchResultLocation> =
            Optional.ofNullable(webSearchResultLocation)

        fun isCharLocation(): Boolean = charLocation != null

        fun isPageLocation(): Boolean = pageLocation != null

        fun isContentBlockLocation(): Boolean = contentBlockLocation != null

        fun isWebSearchResultLocation(): Boolean = webSearchResultLocation != null

        fun asCharLocation(): CitationCharLocation = charLocation.getOrThrow("charLocation")

        fun asPageLocation(): CitationPageLocation = pageLocation.getOrThrow("pageLocation")

        fun asContentBlockLocation(): CitationContentBlockLocation =
            contentBlockLocation.getOrThrow("contentBlockLocation")

        fun asWebSearchResultLocation(): CitationsWebSearchResultLocation =
            webSearchResultLocation.getOrThrow("webSearchResultLocation")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                charLocation != null -> visitor.visitCharLocation(charLocation)
                pageLocation != null -> visitor.visitPageLocation(pageLocation)
                contentBlockLocation != null ->
                    visitor.visitContentBlockLocation(contentBlockLocation)
                webSearchResultLocation != null ->
                    visitor.visitWebSearchResultLocation(webSearchResultLocation)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Citation = apply {
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

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Citation && charLocation == other.charLocation && pageLocation == other.pageLocation && contentBlockLocation == other.contentBlockLocation && webSearchResultLocation == other.webSearchResultLocation /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(charLocation, pageLocation, contentBlockLocation, webSearchResultLocation) /* spotless:on */

        override fun toString(): String =
            when {
                charLocation != null -> "Citation{charLocation=$charLocation}"
                pageLocation != null -> "Citation{pageLocation=$pageLocation}"
                contentBlockLocation != null ->
                    "Citation{contentBlockLocation=$contentBlockLocation}"
                webSearchResultLocation != null ->
                    "Citation{webSearchResultLocation=$webSearchResultLocation}"
                _json != null -> "Citation{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Citation")
            }

        companion object {

            @JvmStatic
            fun ofCharLocation(charLocation: CitationCharLocation) =
                Citation(charLocation = charLocation)

            @JvmStatic
            fun ofPageLocation(pageLocation: CitationPageLocation) =
                Citation(pageLocation = pageLocation)

            @JvmStatic
            fun ofContentBlockLocation(contentBlockLocation: CitationContentBlockLocation) =
                Citation(contentBlockLocation = contentBlockLocation)

            @JvmStatic
            fun ofWebSearchResultLocation(
                webSearchResultLocation: CitationsWebSearchResultLocation
            ) = Citation(webSearchResultLocation = webSearchResultLocation)
        }

        /**
         * An interface that defines how to map each variant of [Citation] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitCharLocation(charLocation: CitationCharLocation): T

            fun visitPageLocation(pageLocation: CitationPageLocation): T

            fun visitContentBlockLocation(contentBlockLocation: CitationContentBlockLocation): T

            fun visitWebSearchResultLocation(
                webSearchResultLocation: CitationsWebSearchResultLocation
            ): T

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
                        return tryDeserialize(node, jacksonTypeRef<CitationCharLocation>())?.let {
                            Citation(charLocation = it, _json = json)
                        } ?: Citation(_json = json)
                    }
                    "page_location" -> {
                        return tryDeserialize(node, jacksonTypeRef<CitationPageLocation>())?.let {
                            Citation(pageLocation = it, _json = json)
                        } ?: Citation(_json = json)
                    }
                    "content_block_location" -> {
                        return tryDeserialize(node, jacksonTypeRef<CitationContentBlockLocation>())
                            ?.let { Citation(contentBlockLocation = it, _json = json) }
                            ?: Citation(_json = json)
                    }
                    "web_search_result_location" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<CitationsWebSearchResultLocation>(),
                            )
                            ?.let { Citation(webSearchResultLocation = it, _json = json) }
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

        return /* spotless:off */ other is CitationsDelta && citation == other.citation && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(citation, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CitationsDelta{citation=$citation, type=$type, additionalProperties=$additionalProperties}"
}
