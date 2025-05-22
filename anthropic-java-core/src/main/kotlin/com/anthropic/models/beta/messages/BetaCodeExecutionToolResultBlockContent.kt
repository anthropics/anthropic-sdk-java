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

@JsonDeserialize(using = BetaCodeExecutionToolResultBlockContent.Deserializer::class)
@JsonSerialize(using = BetaCodeExecutionToolResultBlockContent.Serializer::class)
class BetaCodeExecutionToolResultBlockContent
private constructor(
    private val error: BetaCodeExecutionToolResultError? = null,
    private val resultBlock: BetaCodeExecutionResultBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun error(): Optional<BetaCodeExecutionToolResultError> = Optional.ofNullable(error)

    fun resultBlock(): Optional<BetaCodeExecutionResultBlock> = Optional.ofNullable(resultBlock)

    fun isError(): Boolean = error != null

    fun isResultBlock(): Boolean = resultBlock != null

    fun asError(): BetaCodeExecutionToolResultError = error.getOrThrow("error")

    fun asResultBlock(): BetaCodeExecutionResultBlock = resultBlock.getOrThrow("resultBlock")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            error != null -> visitor.visitError(error)
            resultBlock != null -> visitor.visitResultBlock(resultBlock)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): BetaCodeExecutionToolResultBlockContent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitError(error: BetaCodeExecutionToolResultError) {
                    error.validate()
                }

                override fun visitResultBlock(resultBlock: BetaCodeExecutionResultBlock) {
                    resultBlock.validate()
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
                override fun visitError(error: BetaCodeExecutionToolResultError) = error.validity()

                override fun visitResultBlock(resultBlock: BetaCodeExecutionResultBlock) =
                    resultBlock.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaCodeExecutionToolResultBlockContent && error == other.error && resultBlock == other.resultBlock /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(error, resultBlock) /* spotless:on */

    override fun toString(): String =
        when {
            error != null -> "BetaCodeExecutionToolResultBlockContent{error=$error}"
            resultBlock != null ->
                "BetaCodeExecutionToolResultBlockContent{resultBlock=$resultBlock}"
            _json != null -> "BetaCodeExecutionToolResultBlockContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaCodeExecutionToolResultBlockContent")
        }

    companion object {

        @JvmStatic
        fun ofError(error: BetaCodeExecutionToolResultError) =
            BetaCodeExecutionToolResultBlockContent(error = error)

        @JvmStatic
        fun ofResultBlock(resultBlock: BetaCodeExecutionResultBlock) =
            BetaCodeExecutionToolResultBlockContent(resultBlock = resultBlock)
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaCodeExecutionToolResultBlockContent] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitError(error: BetaCodeExecutionToolResultError): T

        fun visitResultBlock(resultBlock: BetaCodeExecutionResultBlock): T

        /**
         * Maps an unknown variant of [BetaCodeExecutionToolResultBlockContent] to a value of type
         * [T].
         *
         * An instance of [BetaCodeExecutionToolResultBlockContent] can contain an unknown variant
         * if it was deserialized from data that doesn't match any known variant. For example, if
         * the SDK is on an older version than the API, then the API may respond with new variants
         * that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaCodeExecutionToolResultBlockContent: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaCodeExecutionToolResultBlockContent>(
            BetaCodeExecutionToolResultBlockContent::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaCodeExecutionToolResultBlockContent {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionToolResultError>())
                            ?.let {
                                BetaCodeExecutionToolResultBlockContent(error = it, _json = json)
                            },
                        tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionResultBlock>())?.let {
                            BetaCodeExecutionToolResultBlockContent(resultBlock = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> BetaCodeExecutionToolResultBlockContent(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<BetaCodeExecutionToolResultBlockContent>(
            BetaCodeExecutionToolResultBlockContent::class
        ) {

        override fun serialize(
            value: BetaCodeExecutionToolResultBlockContent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.error != null -> generator.writeObject(value.error)
                value.resultBlock != null -> generator.writeObject(value.resultBlock)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException("Invalid BetaCodeExecutionToolResultBlockContent")
            }
        }
    }
}
