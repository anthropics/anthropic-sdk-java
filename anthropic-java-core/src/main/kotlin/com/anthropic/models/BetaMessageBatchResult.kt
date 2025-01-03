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

/**
 * Processing result for this request.
 *
 * Contains a Message output if processing was successful, an error response if processing failed,
 * or the reason why processing was not attempted, such as cancellation or expiration.
 */
@JsonDeserialize(using = BetaMessageBatchResult.Deserializer::class)
@JsonSerialize(using = BetaMessageBatchResult.Serializer::class)
class BetaMessageBatchResult
private constructor(
    private val betaMessageBatchSucceededResult: BetaMessageBatchSucceededResult? = null,
    private val betaMessageBatchErroredResult: BetaMessageBatchErroredResult? = null,
    private val betaMessageBatchCanceledResult: BetaMessageBatchCanceledResult? = null,
    private val betaMessageBatchExpiredResult: BetaMessageBatchExpiredResult? = null,
    private val _json: JsonValue? = null,
) {

    private var validated: Boolean = false

    fun betaMessageBatchSucceededResult(): Optional<BetaMessageBatchSucceededResult> =
        Optional.ofNullable(betaMessageBatchSucceededResult)

    fun betaMessageBatchErroredResult(): Optional<BetaMessageBatchErroredResult> =
        Optional.ofNullable(betaMessageBatchErroredResult)

    fun betaMessageBatchCanceledResult(): Optional<BetaMessageBatchCanceledResult> =
        Optional.ofNullable(betaMessageBatchCanceledResult)

    fun betaMessageBatchExpiredResult(): Optional<BetaMessageBatchExpiredResult> =
        Optional.ofNullable(betaMessageBatchExpiredResult)

    fun isBetaMessageBatchSucceededResult(): Boolean = betaMessageBatchSucceededResult != null

    fun isBetaMessageBatchErroredResult(): Boolean = betaMessageBatchErroredResult != null

    fun isBetaMessageBatchCanceledResult(): Boolean = betaMessageBatchCanceledResult != null

    fun isBetaMessageBatchExpiredResult(): Boolean = betaMessageBatchExpiredResult != null

    fun asBetaMessageBatchSucceededResult(): BetaMessageBatchSucceededResult =
        betaMessageBatchSucceededResult.getOrThrow("betaMessageBatchSucceededResult")

    fun asBetaMessageBatchErroredResult(): BetaMessageBatchErroredResult =
        betaMessageBatchErroredResult.getOrThrow("betaMessageBatchErroredResult")

    fun asBetaMessageBatchCanceledResult(): BetaMessageBatchCanceledResult =
        betaMessageBatchCanceledResult.getOrThrow("betaMessageBatchCanceledResult")

    fun asBetaMessageBatchExpiredResult(): BetaMessageBatchExpiredResult =
        betaMessageBatchExpiredResult.getOrThrow("betaMessageBatchExpiredResult")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            betaMessageBatchSucceededResult != null ->
                visitor.visitBetaMessageBatchSucceededResult(betaMessageBatchSucceededResult)
            betaMessageBatchErroredResult != null ->
                visitor.visitBetaMessageBatchErroredResult(betaMessageBatchErroredResult)
            betaMessageBatchCanceledResult != null ->
                visitor.visitBetaMessageBatchCanceledResult(betaMessageBatchCanceledResult)
            betaMessageBatchExpiredResult != null ->
                visitor.visitBetaMessageBatchExpiredResult(betaMessageBatchExpiredResult)
            else -> visitor.unknown(_json)
        }
    }

    fun validate(): BetaMessageBatchResult = apply {
        if (!validated) {
            if (
                betaMessageBatchSucceededResult == null &&
                    betaMessageBatchErroredResult == null &&
                    betaMessageBatchCanceledResult == null &&
                    betaMessageBatchExpiredResult == null
            ) {
                throw AnthropicInvalidDataException("Unknown BetaMessageBatchResult: $_json")
            }
            betaMessageBatchSucceededResult?.validate()
            betaMessageBatchErroredResult?.validate()
            betaMessageBatchCanceledResult?.validate()
            betaMessageBatchExpiredResult?.validate()
            validated = true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaMessageBatchResult && betaMessageBatchSucceededResult == other.betaMessageBatchSucceededResult && betaMessageBatchErroredResult == other.betaMessageBatchErroredResult && betaMessageBatchCanceledResult == other.betaMessageBatchCanceledResult && betaMessageBatchExpiredResult == other.betaMessageBatchExpiredResult /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaMessageBatchSucceededResult, betaMessageBatchErroredResult, betaMessageBatchCanceledResult, betaMessageBatchExpiredResult) /* spotless:on */

    override fun toString(): String =
        when {
            betaMessageBatchSucceededResult != null ->
                "BetaMessageBatchResult{betaMessageBatchSucceededResult=$betaMessageBatchSucceededResult}"
            betaMessageBatchErroredResult != null ->
                "BetaMessageBatchResult{betaMessageBatchErroredResult=$betaMessageBatchErroredResult}"
            betaMessageBatchCanceledResult != null ->
                "BetaMessageBatchResult{betaMessageBatchCanceledResult=$betaMessageBatchCanceledResult}"
            betaMessageBatchExpiredResult != null ->
                "BetaMessageBatchResult{betaMessageBatchExpiredResult=$betaMessageBatchExpiredResult}"
            _json != null -> "BetaMessageBatchResult{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaMessageBatchResult")
        }

    companion object {

        @JvmStatic
        fun ofBetaMessageBatchSucceededResult(
            betaMessageBatchSucceededResult: BetaMessageBatchSucceededResult
        ) =
            BetaMessageBatchResult(
                betaMessageBatchSucceededResult = betaMessageBatchSucceededResult
            )

        @JvmStatic
        fun ofBetaMessageBatchErroredResult(
            betaMessageBatchErroredResult: BetaMessageBatchErroredResult
        ) = BetaMessageBatchResult(betaMessageBatchErroredResult = betaMessageBatchErroredResult)

        @JvmStatic
        fun ofBetaMessageBatchCanceledResult(
            betaMessageBatchCanceledResult: BetaMessageBatchCanceledResult
        ) = BetaMessageBatchResult(betaMessageBatchCanceledResult = betaMessageBatchCanceledResult)

        @JvmStatic
        fun ofBetaMessageBatchExpiredResult(
            betaMessageBatchExpiredResult: BetaMessageBatchExpiredResult
        ) = BetaMessageBatchResult(betaMessageBatchExpiredResult = betaMessageBatchExpiredResult)
    }

    interface Visitor<out T> {

        fun visitBetaMessageBatchSucceededResult(
            betaMessageBatchSucceededResult: BetaMessageBatchSucceededResult
        ): T

        fun visitBetaMessageBatchErroredResult(
            betaMessageBatchErroredResult: BetaMessageBatchErroredResult
        ): T

        fun visitBetaMessageBatchCanceledResult(
            betaMessageBatchCanceledResult: BetaMessageBatchCanceledResult
        ): T

        fun visitBetaMessageBatchExpiredResult(
            betaMessageBatchExpiredResult: BetaMessageBatchExpiredResult
        ): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaMessageBatchResult: $json")
        }
    }

    class Deserializer : BaseDeserializer<BetaMessageBatchResult>(BetaMessageBatchResult::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaMessageBatchResult {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "succeeded" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaMessageBatchSucceededResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaMessageBatchResult(
                                betaMessageBatchSucceededResult = it,
                                _json = json
                            )
                        }
                }
                "errored" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaMessageBatchErroredResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaMessageBatchResult(
                                betaMessageBatchErroredResult = it,
                                _json = json
                            )
                        }
                }
                "canceled" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaMessageBatchCanceledResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaMessageBatchResult(
                                betaMessageBatchCanceledResult = it,
                                _json = json
                            )
                        }
                }
                "expired" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaMessageBatchExpiredResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaMessageBatchResult(
                                betaMessageBatchExpiredResult = it,
                                _json = json
                            )
                        }
                }
            }

            return BetaMessageBatchResult(_json = json)
        }
    }

    class Serializer : BaseSerializer<BetaMessageBatchResult>(BetaMessageBatchResult::class) {

        override fun serialize(
            value: BetaMessageBatchResult,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.betaMessageBatchSucceededResult != null ->
                    generator.writeObject(value.betaMessageBatchSucceededResult)
                value.betaMessageBatchErroredResult != null ->
                    generator.writeObject(value.betaMessageBatchErroredResult)
                value.betaMessageBatchCanceledResult != null ->
                    generator.writeObject(value.betaMessageBatchCanceledResult)
                value.betaMessageBatchExpiredResult != null ->
                    generator.writeObject(value.betaMessageBatchExpiredResult)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaMessageBatchResult")
            }
        }
    }
}
