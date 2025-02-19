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

@JsonDeserialize(using = BetaTextCitationParam.Deserializer::class)
@JsonSerialize(using = BetaTextCitationParam.Serializer::class)
class BetaTextCitationParam
private constructor(
    private val citationCharLocation: BetaCitationCharLocationParam? = null,
    private val citationPageLocation: BetaCitationPageLocationParam? = null,
    private val citationContentBlockLocation: BetaCitationContentBlockLocationParam? = null,
    private val _json: JsonValue? = null,
) {

    fun citationCharLocation(): Optional<BetaCitationCharLocationParam> =
        Optional.ofNullable(citationCharLocation)

    fun citationPageLocation(): Optional<BetaCitationPageLocationParam> =
        Optional.ofNullable(citationPageLocation)

    fun citationContentBlockLocation(): Optional<BetaCitationContentBlockLocationParam> =
        Optional.ofNullable(citationContentBlockLocation)

    fun isCitationCharLocation(): Boolean = citationCharLocation != null

    fun isCitationPageLocation(): Boolean = citationPageLocation != null

    fun isCitationContentBlockLocation(): Boolean = citationContentBlockLocation != null

    fun asCitationCharLocation(): BetaCitationCharLocationParam =
        citationCharLocation.getOrThrow("citationCharLocation")

    fun asCitationPageLocation(): BetaCitationPageLocationParam =
        citationPageLocation.getOrThrow("citationPageLocation")

    fun asCitationContentBlockLocation(): BetaCitationContentBlockLocationParam =
        citationContentBlockLocation.getOrThrow("citationContentBlockLocation")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            citationCharLocation != null -> visitor.visitCitationCharLocation(citationCharLocation)
            citationPageLocation != null -> visitor.visitCitationPageLocation(citationPageLocation)
            citationContentBlockLocation != null ->
                visitor.visitCitationContentBlockLocation(citationContentBlockLocation)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): BetaTextCitationParam = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitCitationCharLocation(
                    citationCharLocation: BetaCitationCharLocationParam
                ) {
                    citationCharLocation.validate()
                }

                override fun visitCitationPageLocation(
                    citationPageLocation: BetaCitationPageLocationParam
                ) {
                    citationPageLocation.validate()
                }

                override fun visitCitationContentBlockLocation(
                    citationContentBlockLocation: BetaCitationContentBlockLocationParam
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

        return /* spotless:off */ other is BetaTextCitationParam && citationCharLocation == other.citationCharLocation && citationPageLocation == other.citationPageLocation && citationContentBlockLocation == other.citationContentBlockLocation /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(citationCharLocation, citationPageLocation, citationContentBlockLocation) /* spotless:on */

    override fun toString(): String =
        when {
            citationCharLocation != null ->
                "BetaTextCitationParam{citationCharLocation=$citationCharLocation}"
            citationPageLocation != null ->
                "BetaTextCitationParam{citationPageLocation=$citationPageLocation}"
            citationContentBlockLocation != null ->
                "BetaTextCitationParam{citationContentBlockLocation=$citationContentBlockLocation}"
            _json != null -> "BetaTextCitationParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTextCitationParam")
        }

    companion object {

        @JvmStatic
        fun ofCitationCharLocation(citationCharLocation: BetaCitationCharLocationParam) =
            BetaTextCitationParam(citationCharLocation = citationCharLocation)

        @JvmStatic
        fun ofCitationPageLocation(citationPageLocation: BetaCitationPageLocationParam) =
            BetaTextCitationParam(citationPageLocation = citationPageLocation)

        @JvmStatic
        fun ofCitationContentBlockLocation(
            citationContentBlockLocation: BetaCitationContentBlockLocationParam
        ) = BetaTextCitationParam(citationContentBlockLocation = citationContentBlockLocation)
    }

    /**
     * An interface that defines how to map each variant of [BetaTextCitationParam] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitCitationCharLocation(citationCharLocation: BetaCitationCharLocationParam): T

        fun visitCitationPageLocation(citationPageLocation: BetaCitationPageLocationParam): T

        fun visitCitationContentBlockLocation(
            citationContentBlockLocation: BetaCitationContentBlockLocationParam
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
                    tryDeserialize(node, jacksonTypeRef<BetaCitationCharLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaTextCitationParam(citationCharLocation = it, _json = json)
                        }
                }
                "page_location" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaCitationPageLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaTextCitationParam(citationPageLocation = it, _json = json)
                        }
                }
                "content_block_location" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaCitationContentBlockLocationParam>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaTextCitationParam(
                                citationContentBlockLocation = it,
                                _json = json,
                            )
                        }
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
                value.citationCharLocation != null ->
                    generator.writeObject(value.citationCharLocation)
                value.citationPageLocation != null ->
                    generator.writeObject(value.citationPageLocation)
                value.citationContentBlockLocation != null ->
                    generator.writeObject(value.citationContentBlockLocation)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaTextCitationParam")
            }
        }
    }
}
