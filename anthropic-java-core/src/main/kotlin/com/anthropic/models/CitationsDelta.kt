// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
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
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun citation(): Citation = citation.getRequired("citation")

    fun type(): Type = type.getRequired("type")

    @JsonProperty("citation") @ExcludeMissing fun _citation(): JsonField<Citation> = citation

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): CitationsDelta = apply {
        if (validated) {
            return@apply
        }

        citation().validate()
        type()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var citation: JsonField<Citation>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(citationsDelta: CitationsDelta) = apply {
            citation = citationsDelta.citation
            type = citationsDelta.type
            additionalProperties = citationsDelta.additionalProperties.toMutableMap()
        }

        fun citation(citation: Citation) = citation(JsonField.of(citation))

        fun citation(citation: JsonField<Citation>) = apply { this.citation = citation }

        fun citation(citationCharLocation: CitationCharLocation) =
            citation(Citation.ofCitationCharLocation(citationCharLocation))

        fun citation(citationPageLocation: CitationPageLocation) =
            citation(Citation.ofCitationPageLocation(citationPageLocation))

        fun citation(citationContentBlockLocation: CitationContentBlockLocation) =
            citation(Citation.ofCitationContentBlockLocation(citationContentBlockLocation))

        fun type(type: Type) = type(JsonField.of(type))

        fun type(type: JsonField<Type>) = apply { this.type = type }

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
                checkRequired("type", type),
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = Citation.Deserializer::class)
    @JsonSerialize(using = Citation.Serializer::class)
    class Citation
    private constructor(
        private val citationCharLocation: CitationCharLocation? = null,
        private val citationPageLocation: CitationPageLocation? = null,
        private val citationContentBlockLocation: CitationContentBlockLocation? = null,
        private val _json: JsonValue? = null,
    ) {

        fun citationCharLocation(): Optional<CitationCharLocation> =
            Optional.ofNullable(citationCharLocation)

        fun citationPageLocation(): Optional<CitationPageLocation> =
            Optional.ofNullable(citationPageLocation)

        fun citationContentBlockLocation(): Optional<CitationContentBlockLocation> =
            Optional.ofNullable(citationContentBlockLocation)

        fun isCitationCharLocation(): Boolean = citationCharLocation != null

        fun isCitationPageLocation(): Boolean = citationPageLocation != null

        fun isCitationContentBlockLocation(): Boolean = citationContentBlockLocation != null

        fun asCitationCharLocation(): CitationCharLocation =
            citationCharLocation.getOrThrow("citationCharLocation")

        fun asCitationPageLocation(): CitationPageLocation =
            citationPageLocation.getOrThrow("citationPageLocation")

        fun asCitationContentBlockLocation(): CitationContentBlockLocation =
            citationContentBlockLocation.getOrThrow("citationContentBlockLocation")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                citationCharLocation != null ->
                    visitor.visitCitationCharLocation(citationCharLocation)
                citationPageLocation != null ->
                    visitor.visitCitationPageLocation(citationPageLocation)
                citationContentBlockLocation != null ->
                    visitor.visitCitationContentBlockLocation(citationContentBlockLocation)
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
                    override fun visitCitationCharLocation(
                        citationCharLocation: CitationCharLocation
                    ) {
                        citationCharLocation.validate()
                    }

                    override fun visitCitationPageLocation(
                        citationPageLocation: CitationPageLocation
                    ) {
                        citationPageLocation.validate()
                    }

                    override fun visitCitationContentBlockLocation(
                        citationContentBlockLocation: CitationContentBlockLocation
                    ) {
                        citationContentBlockLocation.validate()
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Citation && citationCharLocation == other.citationCharLocation && citationPageLocation == other.citationPageLocation && citationContentBlockLocation == other.citationContentBlockLocation /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(citationCharLocation, citationPageLocation, citationContentBlockLocation) /* spotless:on */

        override fun toString(): String =
            when {
                citationCharLocation != null ->
                    "Citation{citationCharLocation=$citationCharLocation}"
                citationPageLocation != null ->
                    "Citation{citationPageLocation=$citationPageLocation}"
                citationContentBlockLocation != null ->
                    "Citation{citationContentBlockLocation=$citationContentBlockLocation}"
                _json != null -> "Citation{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Citation")
            }

        companion object {

            @JvmStatic
            fun ofCitationCharLocation(citationCharLocation: CitationCharLocation) =
                Citation(citationCharLocation = citationCharLocation)

            @JvmStatic
            fun ofCitationPageLocation(citationPageLocation: CitationPageLocation) =
                Citation(citationPageLocation = citationPageLocation)

            @JvmStatic
            fun ofCitationContentBlockLocation(
                citationContentBlockLocation: CitationContentBlockLocation
            ) = Citation(citationContentBlockLocation = citationContentBlockLocation)
        }

        interface Visitor<out T> {

            fun visitCitationCharLocation(citationCharLocation: CitationCharLocation): T

            fun visitCitationPageLocation(citationPageLocation: CitationPageLocation): T

            fun visitCitationContentBlockLocation(
                citationContentBlockLocation: CitationContentBlockLocation
            ): T

            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Citation: $json")
            }
        }

        class Deserializer : BaseDeserializer<Citation>(Citation::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Citation {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "char_location" -> {
                        tryDeserialize(node, jacksonTypeRef<CitationCharLocation>()) {
                                it.validate()
                            }
                            ?.let {
                                return Citation(citationCharLocation = it, _json = json)
                            }
                    }
                    "page_location" -> {
                        tryDeserialize(node, jacksonTypeRef<CitationPageLocation>()) {
                                it.validate()
                            }
                            ?.let {
                                return Citation(citationPageLocation = it, _json = json)
                            }
                    }
                    "content_block_location" -> {
                        tryDeserialize(node, jacksonTypeRef<CitationContentBlockLocation>()) {
                                it.validate()
                            }
                            ?.let {
                                return Citation(citationContentBlockLocation = it, _json = json)
                            }
                    }
                }

                return Citation(_json = json)
            }
        }

        class Serializer : BaseSerializer<Citation>(Citation::class) {

            override fun serialize(
                value: Citation,
                generator: JsonGenerator,
                provider: SerializerProvider
            ) {
                when {
                    value.citationCharLocation != null ->
                        generator.writeObject(value.citationCharLocation)
                    value.citationPageLocation != null ->
                        generator.writeObject(value.citationPageLocation)
                    value.citationContentBlockLocation != null ->
                        generator.writeObject(value.citationContentBlockLocation)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Citation")
                }
            }
        }
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val CITATIONS_DELTA = of("citations_delta")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            CITATIONS_DELTA,
        }

        enum class Value {
            CITATIONS_DELTA,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                CITATIONS_DELTA -> Value.CITATIONS_DELTA
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                CITATIONS_DELTA -> Known.CITATIONS_DELTA
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
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
