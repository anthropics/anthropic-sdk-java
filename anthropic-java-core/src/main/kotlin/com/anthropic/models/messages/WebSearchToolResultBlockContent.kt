// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
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

@JsonDeserialize(using = WebSearchToolResultBlockContent.Deserializer::class)
@JsonSerialize(using = WebSearchToolResultBlockContent.Serializer::class)
class WebSearchToolResultBlockContent
private constructor(
    private val error: WebSearchToolResultError? = null,
    private val resultBlocks: List<WebSearchResultBlock>? = null,
    private val _json: JsonValue? = null,
) {

    fun toParam(): WebSearchToolResultBlockParamContent =
        accept(
            object : Visitor<WebSearchToolResultBlockParamContent> {
                override fun visitError(
                    error: WebSearchToolResultError
                ): WebSearchToolResultBlockParamContent =
                    WebSearchToolResultBlockParamContent.ofRequestError(
                        WebSearchToolRequestError.builder().errorCode(error._errorCode()).build()
                    )

                override fun visitResultBlocks(
                    resultBlocks: List<WebSearchResultBlock>
                ): WebSearchToolResultBlockParamContent =
                    WebSearchToolResultBlockParamContent.ofItem(resultBlocks.map { it.toParam() })
            }
        )

    fun error(): Optional<WebSearchToolResultError> = Optional.ofNullable(error)

    fun resultBlocks(): Optional<List<WebSearchResultBlock>> = Optional.ofNullable(resultBlocks)

    fun isError(): Boolean = error != null

    fun isResultBlocks(): Boolean = resultBlocks != null

    fun asError(): WebSearchToolResultError = error.getOrThrow("error")

    fun asResultBlocks(): List<WebSearchResultBlock> = resultBlocks.getOrThrow("resultBlocks")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            error != null -> visitor.visitError(error)
            resultBlocks != null -> visitor.visitResultBlocks(resultBlocks)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): WebSearchToolResultBlockContent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitError(error: WebSearchToolResultError) {
                    error.validate()
                }

                override fun visitResultBlocks(resultBlocks: List<WebSearchResultBlock>) {
                    resultBlocks.forEach { it.validate() }
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
                override fun visitError(error: WebSearchToolResultError) = error.validity()

                override fun visitResultBlocks(resultBlocks: List<WebSearchResultBlock>) =
                    resultBlocks.sumOf { it.validity().toInt() }

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WebSearchToolResultBlockContent &&
            error == other.error &&
            resultBlocks == other.resultBlocks
    }

    override fun hashCode(): Int = Objects.hash(error, resultBlocks)

    override fun toString(): String =
        when {
            error != null -> "WebSearchToolResultBlockContent{error=$error}"
            resultBlocks != null -> "WebSearchToolResultBlockContent{resultBlocks=$resultBlocks}"
            _json != null -> "WebSearchToolResultBlockContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid WebSearchToolResultBlockContent")
        }

    companion object {

        @JvmStatic
        fun ofError(error: WebSearchToolResultError) =
            WebSearchToolResultBlockContent(error = error)

        @JvmStatic
        fun ofResultBlocks(resultBlocks: List<WebSearchResultBlock>) =
            WebSearchToolResultBlockContent(resultBlocks = resultBlocks.toImmutable())
    }

    /**
     * An interface that defines how to map each variant of [WebSearchToolResultBlockContent] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        fun visitError(error: WebSearchToolResultError): T

        fun visitResultBlocks(resultBlocks: List<WebSearchResultBlock>): T

        /**
         * Maps an unknown variant of [WebSearchToolResultBlockContent] to a value of type [T].
         *
         * An instance of [WebSearchToolResultBlockContent] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown WebSearchToolResultBlockContent: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<WebSearchToolResultBlockContent>(WebSearchToolResultBlockContent::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): WebSearchToolResultBlockContent {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<WebSearchToolResultError>())?.let {
                            WebSearchToolResultBlockContent(error = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<List<WebSearchResultBlock>>())?.let {
                            WebSearchToolResultBlockContent(resultBlocks = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> WebSearchToolResultBlockContent(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<WebSearchToolResultBlockContent>(WebSearchToolResultBlockContent::class) {

        override fun serialize(
            value: WebSearchToolResultBlockContent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.error != null -> generator.writeObject(value.error)
                value.resultBlocks != null -> generator.writeObject(value.resultBlocks)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid WebSearchToolResultBlockContent")
            }
        }
    }
}
