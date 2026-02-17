// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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

/** Code execution result with encrypted stdout for PFC + web_search results. */
@JsonDeserialize(using = CodeExecutionToolResultBlockContent.Deserializer::class)
@JsonSerialize(using = CodeExecutionToolResultBlockContent.Serializer::class)
class CodeExecutionToolResultBlockContent
private constructor(
    private val error: CodeExecutionToolResultError? = null,
    private val resultBlock: CodeExecutionResultBlock? = null,
    private val encryptedCodeExecutionResultBlock: EncryptedCodeExecutionResultBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun error(): Optional<CodeExecutionToolResultError> = Optional.ofNullable(error)

    fun resultBlock(): Optional<CodeExecutionResultBlock> = Optional.ofNullable(resultBlock)

    /** Code execution result with encrypted stdout for PFC + web_search results. */
    fun encryptedCodeExecutionResultBlock(): Optional<EncryptedCodeExecutionResultBlock> =
        Optional.ofNullable(encryptedCodeExecutionResultBlock)

    fun isError(): Boolean = error != null

    fun isResultBlock(): Boolean = resultBlock != null

    fun isEncryptedCodeExecutionResultBlock(): Boolean = encryptedCodeExecutionResultBlock != null

    fun asError(): CodeExecutionToolResultError = error.getOrThrow("error")

    fun asResultBlock(): CodeExecutionResultBlock = resultBlock.getOrThrow("resultBlock")

    /** Code execution result with encrypted stdout for PFC + web_search results. */
    fun asEncryptedCodeExecutionResultBlock(): EncryptedCodeExecutionResultBlock =
        encryptedCodeExecutionResultBlock.getOrThrow("encryptedCodeExecutionResultBlock")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            error != null -> visitor.visitError(error)
            resultBlock != null -> visitor.visitResultBlock(resultBlock)
            encryptedCodeExecutionResultBlock != null ->
                visitor.visitEncryptedCodeExecutionResultBlock(encryptedCodeExecutionResultBlock)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): CodeExecutionToolResultBlockContent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitError(error: CodeExecutionToolResultError) {
                    error.validate()
                }

                override fun visitResultBlock(resultBlock: CodeExecutionResultBlock) {
                    resultBlock.validate()
                }

                override fun visitEncryptedCodeExecutionResultBlock(
                    encryptedCodeExecutionResultBlock: EncryptedCodeExecutionResultBlock
                ) {
                    encryptedCodeExecutionResultBlock.validate()
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
                override fun visitError(error: CodeExecutionToolResultError) = error.validity()

                override fun visitResultBlock(resultBlock: CodeExecutionResultBlock) =
                    resultBlock.validity()

                override fun visitEncryptedCodeExecutionResultBlock(
                    encryptedCodeExecutionResultBlock: EncryptedCodeExecutionResultBlock
                ) = encryptedCodeExecutionResultBlock.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CodeExecutionToolResultBlockContent &&
            error == other.error &&
            resultBlock == other.resultBlock &&
            encryptedCodeExecutionResultBlock == other.encryptedCodeExecutionResultBlock
    }

    override fun hashCode(): Int =
        Objects.hash(error, resultBlock, encryptedCodeExecutionResultBlock)

    override fun toString(): String =
        when {
            error != null -> "CodeExecutionToolResultBlockContent{error=$error}"
            resultBlock != null -> "CodeExecutionToolResultBlockContent{resultBlock=$resultBlock}"
            encryptedCodeExecutionResultBlock != null ->
                "CodeExecutionToolResultBlockContent{encryptedCodeExecutionResultBlock=$encryptedCodeExecutionResultBlock}"
            _json != null -> "CodeExecutionToolResultBlockContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid CodeExecutionToolResultBlockContent")
        }

    companion object {

        @JvmStatic
        fun ofError(error: CodeExecutionToolResultError) =
            CodeExecutionToolResultBlockContent(error = error)

        @JvmStatic
        fun ofResultBlock(resultBlock: CodeExecutionResultBlock) =
            CodeExecutionToolResultBlockContent(resultBlock = resultBlock)

        /** Code execution result with encrypted stdout for PFC + web_search results. */
        @JvmStatic
        fun ofEncryptedCodeExecutionResultBlock(
            encryptedCodeExecutionResultBlock: EncryptedCodeExecutionResultBlock
        ) =
            CodeExecutionToolResultBlockContent(
                encryptedCodeExecutionResultBlock = encryptedCodeExecutionResultBlock
            )
    }

    /**
     * An interface that defines how to map each variant of [CodeExecutionToolResultBlockContent] to
     * a value of type [T].
     */
    interface Visitor<out T> {

        fun visitError(error: CodeExecutionToolResultError): T

        fun visitResultBlock(resultBlock: CodeExecutionResultBlock): T

        /** Code execution result with encrypted stdout for PFC + web_search results. */
        fun visitEncryptedCodeExecutionResultBlock(
            encryptedCodeExecutionResultBlock: EncryptedCodeExecutionResultBlock
        ): T

        /**
         * Maps an unknown variant of [CodeExecutionToolResultBlockContent] to a value of type [T].
         *
         * An instance of [CodeExecutionToolResultBlockContent] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown CodeExecutionToolResultBlockContent: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<CodeExecutionToolResultBlockContent>(
            CodeExecutionToolResultBlockContent::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): CodeExecutionToolResultBlockContent {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<CodeExecutionToolResultError>())?.let {
                            CodeExecutionToolResultBlockContent(error = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<CodeExecutionResultBlock>())?.let {
                            CodeExecutionToolResultBlockContent(resultBlock = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<EncryptedCodeExecutionResultBlock>())
                            ?.let {
                                CodeExecutionToolResultBlockContent(
                                    encryptedCodeExecutionResultBlock = it,
                                    _json = json,
                                )
                            },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> CodeExecutionToolResultBlockContent(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<CodeExecutionToolResultBlockContent>(
            CodeExecutionToolResultBlockContent::class
        ) {

        override fun serialize(
            value: CodeExecutionToolResultBlockContent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.error != null -> generator.writeObject(value.error)
                value.resultBlock != null -> generator.writeObject(value.resultBlock)
                value.encryptedCodeExecutionResultBlock != null ->
                    generator.writeObject(value.encryptedCodeExecutionResultBlock)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid CodeExecutionToolResultBlockContent")
            }
        }
    }
}
