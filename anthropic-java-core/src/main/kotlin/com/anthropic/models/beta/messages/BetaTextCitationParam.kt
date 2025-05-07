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
    private val citationCharLocation: BetaCitationCharLocationParam? = null,
    private val citationPageLocation: BetaCitationPageLocationParam? = null,
    private val citationContentBlockLocation: BetaCitationContentBlockLocationParam? = null,
    private val citationWebSearchResultLocation: BetaCitationWebSearchResultLocationParam? = null,
    private val _json: JsonValue? = null,
) {

    fun citationCharLocation(): Optional<BetaCitationCharLocationParam> =
        Optional.ofNullable(citationCharLocation)

    fun citationPageLocation(): Optional<BetaCitationPageLocationParam> =
        Optional.ofNullable(citationPageLocation)

    fun citationContentBlockLocation(): Optional<BetaCitationContentBlockLocationParam> =
        Optional.ofNullable(citationContentBlockLocation)

    fun citationWebSearchResultLocation(): Optional<BetaCitationWebSearchResultLocationParam> =
        Optional.ofNullable(citationWebSearchResultLocation)

    fun isCitationCharLocation(): Boolean = citationCharLocation != null

    fun isCitationPageLocation(): Boolean = citationPageLocation != null

    fun isCitationContentBlockLocation(): Boolean = citationContentBlockLocation != null

    fun isCitationWebSearchResultLocation(): Boolean = citationWebSearchResultLocation != null

    fun asCitationCharLocation(): BetaCitationCharLocationParam =
        citationCharLocation.getOrThrow("citationCharLocation")

    fun asCitationPageLocation(): BetaCitationPageLocationParam =
        citationPageLocation.getOrThrow("citationPageLocation")

    fun asCitationContentBlockLocation(): BetaCitationContentBlockLocationParam =
        citationContentBlockLocation.getOrThrow("citationContentBlockLocation")

    fun asCitationWebSearchResultLocation(): BetaCitationWebSearchResultLocationParam =
        citationWebSearchResultLocation.getOrThrow("citationWebSearchResultLocation")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            citationCharLocation != null -> visitor.visitCitationCharLocation(citationCharLocation)
            citationPageLocation != null -> visitor.visitCitationPageLocation(citationPageLocation)
            citationContentBlockLocation != null ->
                visitor.visitCitationContentBlockLocation(citationContentBlockLocation)
            citationWebSearchResultLocation != null ->
                visitor.visitCitationWebSearchResultLocation(citationWebSearchResultLocation)
            else -> visitor.unknown(_json)
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

                override fun visitCitationWebSearchResultLocation(
                    citationWebSearchResultLocation: BetaCitationWebSearchResultLocationParam
                ) {
                    citationWebSearchResultLocation.validate()
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
                override fun visitCitationCharLocation(
                    citationCharLocation: BetaCitationCharLocationParam
                ) = citationCharLocation.validity()

                override fun visitCitationPageLocation(
                    citationPageLocation: BetaCitationPageLocationParam
                ) = citationPageLocation.validity()

                override fun visitCitationContentBlockLocation(
                    citationContentBlockLocation: BetaCitationContentBlockLocationParam
                ) = citationContentBlockLocation.validity()

                override fun visitCitationWebSearchResultLocation(
                    citationWebSearchResultLocation: BetaCitationWebSearchResultLocationParam
                ) = citationWebSearchResultLocation.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaTextCitationParam && citationCharLocation == other.citationCharLocation && citationPageLocation == other.citationPageLocation && citationContentBlockLocation == other.citationContentBlockLocation && citationWebSearchResultLocation == other.citationWebSearchResultLocation /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(citationCharLocation, citationPageLocation, citationContentBlockLocation, citationWebSearchResultLocation) /* spotless:on */

    override fun toString(): String =
        when {
            citationCharLocation != null ->
                "BetaTextCitationParam{citationCharLocation=$citationCharLocation}"
            citationPageLocation != null ->
                "BetaTextCitationParam{citationPageLocation=$citationPageLocation}"
            citationContentBlockLocation != null ->
                "BetaTextCitationParam{citationContentBlockLocation=$citationContentBlockLocation}"
            citationWebSearchResultLocation != null ->
                "BetaTextCitationParam{citationWebSearchResultLocation=$citationWebSearchResultLocation}"
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

        @JvmStatic
        fun ofCitationWebSearchResultLocation(
            citationWebSearchResultLocation: BetaCitationWebSearchResultLocationParam
        ) = BetaTextCitationParam(citationWebSearchResultLocation = citationWebSearchResultLocation)
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

        fun visitCitationWebSearchResultLocation(
            citationWebSearchResultLocation: BetaCitationWebSearchResultLocationParam
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
                        ?.let { BetaTextCitationParam(citationCharLocation = it, _json = json) }
                        ?: BetaTextCitationParam(_json = json)
                }
                "page_location" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCitationPageLocationParam>())
                        ?.let { BetaTextCitationParam(citationPageLocation = it, _json = json) }
                        ?: BetaTextCitationParam(_json = json)
                }
                "content_block_location" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaCitationContentBlockLocationParam>(),
                        )
                        ?.let {
                            BetaTextCitationParam(citationContentBlockLocation = it, _json = json)
                        } ?: BetaTextCitationParam(_json = json)
                }
                "web_search_result_location" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaCitationWebSearchResultLocationParam>(),
                        )
                        ?.let {
                            BetaTextCitationParam(
                                citationWebSearchResultLocation = it,
                                _json = json,
                            )
                        } ?: BetaTextCitationParam(_json = json)
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
                value.citationWebSearchResultLocation != null ->
                    generator.writeObject(value.citationWebSearchResultLocation)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaTextCitationParam")
            }
        }
    }
}
