// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
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
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@NoAutoDetect
class CitationsDelta
@JsonCreator
private constructor(
    @JsonProperty("citation")
    @ExcludeMissing
    private val citation: JsonField<Citation> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun citation(): Citation = citation.getRequired("citation")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    @JsonProperty("citation") @ExcludeMissing fun _citation(): JsonField<Citation> = citation

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

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

        fun citation(citation: JsonField<Citation>) = apply { this.citation = citation }

        fun citation(charLocation: CitationCharLocation) =
            citation(Citation.ofCharLocation(charLocation))

        fun citation(pageLocation: CitationPageLocation) =
            citation(Citation.ofPageLocation(pageLocation))

        fun citation(contentBlockLocation: CitationContentBlockLocation) =
            citation(Citation.ofContentBlockLocation(contentBlockLocation))

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

        fun build(): CitationsDelta =
            CitationsDelta(
                checkRequired("citation", citation),
                type,
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = Citation.Deserializer::class)
    @JsonSerialize(using = Citation.Serializer::class)
    class Citation
    private constructor(
        private val charLocation: CitationCharLocation? = null,
        private val pageLocation: CitationPageLocation? = null,
        private val contentBlockLocation: CitationContentBlockLocation? = null,
        private val _json: JsonValue? = null,
    ) {

        fun charLocation(): Optional<CitationCharLocation> = Optional.ofNullable(charLocation)

        fun pageLocation(): Optional<CitationPageLocation> = Optional.ofNullable(pageLocation)

        fun contentBlockLocation(): Optional<CitationContentBlockLocation> =
            Optional.ofNullable(contentBlockLocation)

        fun isCharLocation(): Boolean = charLocation != null

        fun isPageLocation(): Boolean = pageLocation != null

        fun isContentBlockLocation(): Boolean = contentBlockLocation != null

        fun asCharLocation(): CitationCharLocation = charLocation.getOrThrow("charLocation")

        fun asPageLocation(): CitationPageLocation = pageLocation.getOrThrow("pageLocation")

        fun asContentBlockLocation(): CitationContentBlockLocation =
            contentBlockLocation.getOrThrow("contentBlockLocation")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                charLocation != null -> visitor.visitCharLocation(charLocation)
                pageLocation != null -> visitor.visitPageLocation(pageLocation)
                contentBlockLocation != null ->
                    visitor.visitContentBlockLocation(contentBlockLocation)
                else -> visitor.unknown(_json)
            }
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
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Citation && charLocation == other.charLocation && pageLocation == other.pageLocation && contentBlockLocation == other.contentBlockLocation /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(charLocation, pageLocation, contentBlockLocation) /* spotless:on */

        override fun toString(): String =
            when {
                charLocation != null -> "Citation{charLocation=$charLocation}"
                pageLocation != null -> "Citation{pageLocation=$pageLocation}"
                contentBlockLocation != null ->
                    "Citation{contentBlockLocation=$contentBlockLocation}"
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
        }

        /**
         * An interface that defines how to map each variant of [Citation] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitCharLocation(charLocation: CitationCharLocation): T

            fun visitPageLocation(pageLocation: CitationPageLocation): T

            fun visitContentBlockLocation(contentBlockLocation: CitationContentBlockLocation): T

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
                        tryDeserialize(node, jacksonTypeRef<CitationCharLocation>()) {
                                it.validate()
                            }
                            ?.let {
                                return Citation(charLocation = it, _json = json)
                            }
                    }
                    "page_location" -> {
                        tryDeserialize(node, jacksonTypeRef<CitationPageLocation>()) {
                                it.validate()
                            }
                            ?.let {
                                return Citation(pageLocation = it, _json = json)
                            }
                    }
                    "content_block_location" -> {
                        tryDeserialize(node, jacksonTypeRef<CitationContentBlockLocation>()) {
                                it.validate()
                            }
                            ?.let {
                                return Citation(contentBlockLocation = it, _json = json)
                            }
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
