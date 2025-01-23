// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

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
    private val citationCharLocationParam: CitationCharLocationParam? = null,
    private val citationPageLocationParam: CitationPageLocationParam? = null,
    private val citationContentBlockLocationParam: CitationContentBlockLocationParam? = null,
    private val _json: JsonValue? = null,
) {

    fun citationCharLocationParam(): Optional<CitationCharLocationParam> =
        Optional.ofNullable(citationCharLocationParam)

    fun citationPageLocationParam(): Optional<CitationPageLocationParam> =
        Optional.ofNullable(citationPageLocationParam)

    fun citationContentBlockLocationParam(): Optional<CitationContentBlockLocationParam> =
        Optional.ofNullable(citationContentBlockLocationParam)

    fun isCitationCharLocationParam(): Boolean = citationCharLocationParam != null

    fun isCitationPageLocationParam(): Boolean = citationPageLocationParam != null

    fun isCitationContentBlockLocationParam(): Boolean = citationContentBlockLocationParam != null

    fun asCitationCharLocationParam(): CitationCharLocationParam =
        citationCharLocationParam.getOrThrow("citationCharLocationParam")

    fun asCitationPageLocationParam(): CitationPageLocationParam =
        citationPageLocationParam.getOrThrow("citationPageLocationParam")

    fun asCitationContentBlockLocationParam(): CitationContentBlockLocationParam =
        citationContentBlockLocationParam.getOrThrow("citationContentBlockLocationParam")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            citationCharLocationParam != null ->
                visitor.visitCitationCharLocationParam(citationCharLocationParam)
            citationPageLocationParam != null ->
                visitor.visitCitationPageLocationParam(citationPageLocationParam)
            citationContentBlockLocationParam != null ->
                visitor.visitCitationContentBlockLocationParam(citationContentBlockLocationParam)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): TextCitationParam = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitCitationCharLocationParam(
                    citationCharLocationParam: CitationCharLocationParam
                ) {
                    citationCharLocationParam.validate()
                }

                override fun visitCitationPageLocationParam(
                    citationPageLocationParam: CitationPageLocationParam
                ) {
                    citationPageLocationParam.validate()
                }

                override fun visitCitationContentBlockLocationParam(
                    citationContentBlockLocationParam: CitationContentBlockLocationParam
                ) {
                    citationContentBlockLocationParam.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is TextCitationParam && citationCharLocationParam == other.citationCharLocationParam && citationPageLocationParam == other.citationPageLocationParam && citationContentBlockLocationParam == other.citationContentBlockLocationParam /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(citationCharLocationParam, citationPageLocationParam, citationContentBlockLocationParam) /* spotless:on */

    override fun toString(): String =
        when {
            citationCharLocationParam != null ->
                "TextCitationParam{citationCharLocationParam=$citationCharLocationParam}"
            citationPageLocationParam != null ->
                "TextCitationParam{citationPageLocationParam=$citationPageLocationParam}"
            citationContentBlockLocationParam != null ->
                "TextCitationParam{citationContentBlockLocationParam=$citationContentBlockLocationParam}"
            _json != null -> "TextCitationParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid TextCitationParam")
        }

    companion object {

        @JvmStatic
        fun ofCitationCharLocationParam(citationCharLocationParam: CitationCharLocationParam) =
            TextCitationParam(citationCharLocationParam = citationCharLocationParam)

        @JvmStatic
        fun ofCitationPageLocationParam(citationPageLocationParam: CitationPageLocationParam) =
            TextCitationParam(citationPageLocationParam = citationPageLocationParam)

        @JvmStatic
        fun ofCitationContentBlockLocationParam(
            citationContentBlockLocationParam: CitationContentBlockLocationParam
        ) = TextCitationParam(citationContentBlockLocationParam = citationContentBlockLocationParam)
    }

    interface Visitor<out T> {

        fun visitCitationCharLocationParam(citationCharLocationParam: CitationCharLocationParam): T

        fun visitCitationPageLocationParam(citationPageLocationParam: CitationPageLocationParam): T

        fun visitCitationContentBlockLocationParam(
            citationContentBlockLocationParam: CitationContentBlockLocationParam
        ): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown TextCitationParam: $json")
        }
    }

    class Deserializer : BaseDeserializer<TextCitationParam>(TextCitationParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): TextCitationParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "char_location" -> {
                    tryDeserialize(node, jacksonTypeRef<CitationCharLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return TextCitationParam(citationCharLocationParam = it, _json = json)
                        }
                }
                "page_location" -> {
                    tryDeserialize(node, jacksonTypeRef<CitationPageLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return TextCitationParam(citationPageLocationParam = it, _json = json)
                        }
                }
                "content_block_location" -> {
                    tryDeserialize(node, jacksonTypeRef<CitationContentBlockLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return TextCitationParam(
                                citationContentBlockLocationParam = it,
                                _json = json
                            )
                        }
                }
            }

            return TextCitationParam(_json = json)
        }
    }

    class Serializer : BaseSerializer<TextCitationParam>(TextCitationParam::class) {

        override fun serialize(
            value: TextCitationParam,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.citationCharLocationParam != null ->
                    generator.writeObject(value.citationCharLocationParam)
                value.citationPageLocationParam != null ->
                    generator.writeObject(value.citationPageLocationParam)
                value.citationContentBlockLocationParam != null ->
                    generator.writeObject(value.citationContentBlockLocationParam)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid TextCitationParam")
            }
        }
    }
}
