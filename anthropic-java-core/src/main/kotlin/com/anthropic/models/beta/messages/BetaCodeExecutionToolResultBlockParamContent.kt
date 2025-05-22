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

@JsonDeserialize(using = BetaCodeExecutionToolResultBlockParamContent.Deserializer::class)
@JsonSerialize(using = BetaCodeExecutionToolResultBlockParamContent.Serializer::class)
class BetaCodeExecutionToolResultBlockParamContent
private constructor(
    private val errorParam: BetaCodeExecutionToolResultErrorParam? = null,
    private val resultBlockParam: BetaCodeExecutionResultBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    fun errorParam(): Optional<BetaCodeExecutionToolResultErrorParam> =
        Optional.ofNullable(errorParam)

    fun resultBlockParam(): Optional<BetaCodeExecutionResultBlockParam> =
        Optional.ofNullable(resultBlockParam)

    fun isErrorParam(): Boolean = errorParam != null

    fun isResultBlockParam(): Boolean = resultBlockParam != null

    fun asErrorParam(): BetaCodeExecutionToolResultErrorParam = errorParam.getOrThrow("errorParam")

    fun asResultBlockParam(): BetaCodeExecutionResultBlockParam =
        resultBlockParam.getOrThrow("resultBlockParam")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            errorParam != null -> visitor.visitErrorParam(errorParam)
            resultBlockParam != null -> visitor.visitResultBlockParam(resultBlockParam)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): BetaCodeExecutionToolResultBlockParamContent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitErrorParam(errorParam: BetaCodeExecutionToolResultErrorParam) {
                    errorParam.validate()
                }

                override fun visitResultBlockParam(
                    resultBlockParam: BetaCodeExecutionResultBlockParam
                ) {
                    resultBlockParam.validate()
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
                override fun visitErrorParam(errorParam: BetaCodeExecutionToolResultErrorParam) =
                    errorParam.validity()

                override fun visitResultBlockParam(
                    resultBlockParam: BetaCodeExecutionResultBlockParam
                ) = resultBlockParam.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaCodeExecutionToolResultBlockParamContent && errorParam == other.errorParam && resultBlockParam == other.resultBlockParam /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(errorParam, resultBlockParam) /* spotless:on */

    override fun toString(): String =
        when {
            errorParam != null ->
                "BetaCodeExecutionToolResultBlockParamContent{errorParam=$errorParam}"
            resultBlockParam != null ->
                "BetaCodeExecutionToolResultBlockParamContent{resultBlockParam=$resultBlockParam}"
            _json != null -> "BetaCodeExecutionToolResultBlockParamContent{_unknown=$_json}"
            else ->
                throw IllegalStateException("Invalid BetaCodeExecutionToolResultBlockParamContent")
        }

    companion object {

        @JvmStatic
        fun ofErrorParam(errorParam: BetaCodeExecutionToolResultErrorParam) =
            BetaCodeExecutionToolResultBlockParamContent(errorParam = errorParam)

        @JvmStatic
        fun ofResultBlockParam(resultBlockParam: BetaCodeExecutionResultBlockParam) =
            BetaCodeExecutionToolResultBlockParamContent(resultBlockParam = resultBlockParam)
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaCodeExecutionToolResultBlockParamContent] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitErrorParam(errorParam: BetaCodeExecutionToolResultErrorParam): T

        fun visitResultBlockParam(resultBlockParam: BetaCodeExecutionResultBlockParam): T

        /**
         * Maps an unknown variant of [BetaCodeExecutionToolResultBlockParamContent] to a value of
         * type [T].
         *
         * An instance of [BetaCodeExecutionToolResultBlockParamContent] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaCodeExecutionToolResultBlockParamContent: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaCodeExecutionToolResultBlockParamContent>(
            BetaCodeExecutionToolResultBlockParamContent::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaCodeExecutionToolResultBlockParamContent {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(
                                node,
                                jacksonTypeRef<BetaCodeExecutionToolResultErrorParam>(),
                            )
                            ?.let {
                                BetaCodeExecutionToolResultBlockParamContent(
                                    errorParam = it,
                                    _json = json,
                                )
                            },
                        tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionResultBlockParam>())
                            ?.let {
                                BetaCodeExecutionToolResultBlockParamContent(
                                    resultBlockParam = it,
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
                0 -> BetaCodeExecutionToolResultBlockParamContent(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<BetaCodeExecutionToolResultBlockParamContent>(
            BetaCodeExecutionToolResultBlockParamContent::class
        ) {

        override fun serialize(
            value: BetaCodeExecutionToolResultBlockParamContent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.errorParam != null -> generator.writeObject(value.errorParam)
                value.resultBlockParam != null -> generator.writeObject(value.resultBlockParam)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaCodeExecutionToolResultBlockParamContent"
                    )
            }
        }
    }
}
