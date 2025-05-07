// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
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

@JsonDeserialize(using = BetaWebSearchToolResultBlockParamContent.Deserializer::class)
@JsonSerialize(using = BetaWebSearchToolResultBlockParamContent.Serializer::class)
class BetaWebSearchToolResultBlockParamContent
private constructor(
    private val item: List<BetaWebSearchResultBlockParam>? = null,
    private val requestError: BetaWebSearchToolRequestError? = null,
    private val _json: JsonValue? = null,
) {

    fun item(): Optional<List<BetaWebSearchResultBlockParam>> = Optional.ofNullable(item)

    fun requestError(): Optional<BetaWebSearchToolRequestError> = Optional.ofNullable(requestError)

    fun isItem(): Boolean = item != null

    fun isRequestError(): Boolean = requestError != null

    fun asItem(): List<BetaWebSearchResultBlockParam> = item.getOrThrow("item")

    fun asRequestError(): BetaWebSearchToolRequestError = requestError.getOrThrow("requestError")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            item != null -> visitor.visitItem(item)
            requestError != null -> visitor.visitRequestError(requestError)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): BetaWebSearchToolResultBlockParamContent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitItem(item: List<BetaWebSearchResultBlockParam>) {
                    item.forEach { it.validate() }
                }

                override fun visitRequestError(requestError: BetaWebSearchToolRequestError) {
                    requestError.validate()
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
                override fun visitItem(item: List<BetaWebSearchResultBlockParam>) =
                    item.sumOf { it.validity().toInt() }

                override fun visitRequestError(requestError: BetaWebSearchToolRequestError) =
                    requestError.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaWebSearchToolResultBlockParamContent && item == other.item && requestError == other.requestError /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(item, requestError) /* spotless:on */

    override fun toString(): String =
        when {
            item != null -> "BetaWebSearchToolResultBlockParamContent{item=$item}"
            requestError != null ->
                "BetaWebSearchToolResultBlockParamContent{requestError=$requestError}"
            _json != null -> "BetaWebSearchToolResultBlockParamContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaWebSearchToolResultBlockParamContent")
        }

    companion object {

        @JvmStatic
        fun ofItem(item: List<BetaWebSearchResultBlockParam>) =
            BetaWebSearchToolResultBlockParamContent(item = item)

        @JvmStatic
        fun ofRequestError(requestError: BetaWebSearchToolRequestError) =
            BetaWebSearchToolResultBlockParamContent(requestError = requestError)
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaWebSearchToolResultBlockParamContent] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitItem(item: List<BetaWebSearchResultBlockParam>): T

        fun visitRequestError(requestError: BetaWebSearchToolRequestError): T

        /**
         * Maps an unknown variant of [BetaWebSearchToolResultBlockParamContent] to a value of type
         * [T].
         *
         * An instance of [BetaWebSearchToolResultBlockParamContent] can contain an unknown variant
         * if it was deserialized from data that doesn't match any known variant. For example, if
         * the SDK is on an older version than the API, then the API may respond with new variants
         * that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaWebSearchToolResultBlockParamContent: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaWebSearchToolResultBlockParamContent>(
            BetaWebSearchToolResultBlockParamContent::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaWebSearchToolResultBlockParamContent {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<BetaWebSearchToolRequestError>())?.let {
                            BetaWebSearchToolResultBlockParamContent(
                                requestError = it,
                                _json = json,
                            )
                        },
                        tryDeserialize(node, jacksonTypeRef<List<BetaWebSearchResultBlockParam>>())
                            ?.let {
                                BetaWebSearchToolResultBlockParamContent(item = it, _json = json)
                            },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> BetaWebSearchToolResultBlockParamContent(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<BetaWebSearchToolResultBlockParamContent>(
            BetaWebSearchToolResultBlockParamContent::class
        ) {

        override fun serialize(
            value: BetaWebSearchToolResultBlockParamContent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.item != null -> generator.writeObject(value.item)
                value.requestError != null -> generator.writeObject(value.requestError)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException("Invalid BetaWebSearchToolResultBlockParamContent")
            }
        }
    }
}
