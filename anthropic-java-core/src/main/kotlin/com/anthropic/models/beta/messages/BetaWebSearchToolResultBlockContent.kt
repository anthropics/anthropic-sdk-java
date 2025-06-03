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

@JsonDeserialize(using = BetaWebSearchToolResultBlockContent.Deserializer::class)
@JsonSerialize(using = BetaWebSearchToolResultBlockContent.Serializer::class)
class BetaWebSearchToolResultBlockContent
private constructor(
    private val error: BetaWebSearchToolResultError? = null,
    private val resultBlocks: List<BetaWebSearchResultBlock>? = null,
    private val _json: JsonValue? = null,
) {

    fun toParam(): BetaWebSearchToolResultBlockParamContent =
        accept(
            object : Visitor<BetaWebSearchToolResultBlockParamContent> {
                override fun visitError(
                    error: BetaWebSearchToolResultError
                ): BetaWebSearchToolResultBlockParamContent =
                    BetaWebSearchToolResultBlockParamContent.ofRequestError(
                        BetaWebSearchToolRequestError.builder()
                            .errorCode(error._errorCode())
                            .build()
                    )

                override fun visitResultBlocks(
                    resultBlocks: List<BetaWebSearchResultBlock>
                ): BetaWebSearchToolResultBlockParamContent =
                    BetaWebSearchToolResultBlockParamContent.ofResultBlock(
                        resultBlocks.map { it.toParam() }
                    )
            }
        )

    fun error(): Optional<BetaWebSearchToolResultError> = Optional.ofNullable(error)

    fun resultBlocks(): Optional<List<BetaWebSearchResultBlock>> = Optional.ofNullable(resultBlocks)

    fun isError(): Boolean = error != null

    fun isResultBlocks(): Boolean = resultBlocks != null

    fun asError(): BetaWebSearchToolResultError = error.getOrThrow("error")

    fun asResultBlocks(): List<BetaWebSearchResultBlock> = resultBlocks.getOrThrow("resultBlocks")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            error != null -> visitor.visitError(error)
            resultBlocks != null -> visitor.visitResultBlocks(resultBlocks)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): BetaWebSearchToolResultBlockContent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitError(error: BetaWebSearchToolResultError) {
                    error.validate()
                }

                override fun visitResultBlocks(resultBlocks: List<BetaWebSearchResultBlock>) {
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
                override fun visitError(error: BetaWebSearchToolResultError) = error.validity()

                override fun visitResultBlocks(resultBlocks: List<BetaWebSearchResultBlock>) =
                    resultBlocks.sumOf { it.validity().toInt() }

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaWebSearchToolResultBlockContent && error == other.error && resultBlocks == other.resultBlocks /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(error, resultBlocks) /* spotless:on */

    override fun toString(): String =
        when {
            error != null -> "BetaWebSearchToolResultBlockContent{error=$error}"
            resultBlocks != null ->
                "BetaWebSearchToolResultBlockContent{resultBlocks=$resultBlocks}"
            _json != null -> "BetaWebSearchToolResultBlockContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaWebSearchToolResultBlockContent")
        }

    companion object {

        @JvmStatic
        fun ofError(error: BetaWebSearchToolResultError) =
            BetaWebSearchToolResultBlockContent(error = error)

        @JvmStatic
        fun ofResultBlocks(resultBlocks: List<BetaWebSearchResultBlock>) =
            BetaWebSearchToolResultBlockContent(resultBlocks = resultBlocks)
    }

    /**
     * An interface that defines how to map each variant of [BetaWebSearchToolResultBlockContent] to
     * a value of type [T].
     */
    interface Visitor<out T> {

        fun visitError(error: BetaWebSearchToolResultError): T

        fun visitResultBlocks(resultBlocks: List<BetaWebSearchResultBlock>): T

        /**
         * Maps an unknown variant of [BetaWebSearchToolResultBlockContent] to a value of type [T].
         *
         * An instance of [BetaWebSearchToolResultBlockContent] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaWebSearchToolResultBlockContent: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaWebSearchToolResultBlockContent>(
            BetaWebSearchToolResultBlockContent::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaWebSearchToolResultBlockContent {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<BetaWebSearchToolResultError>())?.let {
                            BetaWebSearchToolResultBlockContent(error = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<List<BetaWebSearchResultBlock>>())
                            ?.let {
                                BetaWebSearchToolResultBlockContent(resultBlocks = it, _json = json)
                            },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> BetaWebSearchToolResultBlockContent(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<BetaWebSearchToolResultBlockContent>(
            BetaWebSearchToolResultBlockContent::class
        ) {

        override fun serialize(
            value: BetaWebSearchToolResultBlockContent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.error != null -> generator.writeObject(value.error)
                value.resultBlocks != null -> generator.writeObject(value.resultBlocks)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaWebSearchToolResultBlockContent")
            }
        }
    }
}
