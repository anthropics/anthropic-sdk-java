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
    private val succeeded: BetaMessageBatchSucceededResult? = null,
    private val errored: BetaMessageBatchErroredResult? = null,
    private val canceled: BetaMessageBatchCanceledResult? = null,
    private val expired: BetaMessageBatchExpiredResult? = null,
    private val _json: JsonValue? = null,
) {

    fun succeeded(): Optional<BetaMessageBatchSucceededResult> = Optional.ofNullable(succeeded)

    fun errored(): Optional<BetaMessageBatchErroredResult> = Optional.ofNullable(errored)

    fun canceled(): Optional<BetaMessageBatchCanceledResult> = Optional.ofNullable(canceled)

    fun expired(): Optional<BetaMessageBatchExpiredResult> = Optional.ofNullable(expired)

    fun isSucceeded(): Boolean = succeeded != null

    fun isErrored(): Boolean = errored != null

    fun isCanceled(): Boolean = canceled != null

    fun isExpired(): Boolean = expired != null

    fun asSucceeded(): BetaMessageBatchSucceededResult = succeeded.getOrThrow("succeeded")

    fun asErrored(): BetaMessageBatchErroredResult = errored.getOrThrow("errored")

    fun asCanceled(): BetaMessageBatchCanceledResult = canceled.getOrThrow("canceled")

    fun asExpired(): BetaMessageBatchExpiredResult = expired.getOrThrow("expired")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            succeeded != null -> visitor.visitSucceeded(succeeded)
            errored != null -> visitor.visitErrored(errored)
            canceled != null -> visitor.visitCanceled(canceled)
            expired != null -> visitor.visitExpired(expired)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): BetaMessageBatchResult = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitSucceeded(succeeded: BetaMessageBatchSucceededResult) {
                    succeeded.validate()
                }

                override fun visitErrored(errored: BetaMessageBatchErroredResult) {
                    errored.validate()
                }

                override fun visitCanceled(canceled: BetaMessageBatchCanceledResult) {
                    canceled.validate()
                }

                override fun visitExpired(expired: BetaMessageBatchExpiredResult) {
                    expired.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaMessageBatchResult && succeeded == other.succeeded && errored == other.errored && canceled == other.canceled && expired == other.expired /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(succeeded, errored, canceled, expired) /* spotless:on */

    override fun toString(): String =
        when {
            succeeded != null -> "BetaMessageBatchResult{succeeded=$succeeded}"
            errored != null -> "BetaMessageBatchResult{errored=$errored}"
            canceled != null -> "BetaMessageBatchResult{canceled=$canceled}"
            expired != null -> "BetaMessageBatchResult{expired=$expired}"
            _json != null -> "BetaMessageBatchResult{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaMessageBatchResult")
        }

    companion object {

        @JvmStatic
        fun ofSucceeded(succeeded: BetaMessageBatchSucceededResult) =
            BetaMessageBatchResult(succeeded = succeeded)

        @JvmStatic
        fun ofErrored(errored: BetaMessageBatchErroredResult) =
            BetaMessageBatchResult(errored = errored)

        @JvmStatic
        fun ofCanceled(canceled: BetaMessageBatchCanceledResult) =
            BetaMessageBatchResult(canceled = canceled)

        @JvmStatic
        fun ofExpired(expired: BetaMessageBatchExpiredResult) =
            BetaMessageBatchResult(expired = expired)
    }

    /**
     * An interface that defines how to map each variant of [BetaMessageBatchResult] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitSucceeded(succeeded: BetaMessageBatchSucceededResult): T

        fun visitErrored(errored: BetaMessageBatchErroredResult): T

        fun visitCanceled(canceled: BetaMessageBatchCanceledResult): T

        fun visitExpired(expired: BetaMessageBatchExpiredResult): T

        /**
         * Maps an unknown variant of [BetaMessageBatchResult] to a value of type [T].
         *
         * An instance of [BetaMessageBatchResult] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaMessageBatchResult: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaMessageBatchResult>(BetaMessageBatchResult::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaMessageBatchResult {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "succeeded" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaMessageBatchSucceededResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaMessageBatchResult(succeeded = it, _json = json)
                        }
                }
                "errored" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaMessageBatchErroredResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaMessageBatchResult(errored = it, _json = json)
                        }
                }
                "canceled" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaMessageBatchCanceledResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaMessageBatchResult(canceled = it, _json = json)
                        }
                }
                "expired" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaMessageBatchExpiredResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaMessageBatchResult(expired = it, _json = json)
                        }
                }
            }

            return BetaMessageBatchResult(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaMessageBatchResult>(BetaMessageBatchResult::class) {

        override fun serialize(
            value: BetaMessageBatchResult,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.succeeded != null -> generator.writeObject(value.succeeded)
                value.errored != null -> generator.writeObject(value.errored)
                value.canceled != null -> generator.writeObject(value.canceled)
                value.expired != null -> generator.writeObject(value.expired)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaMessageBatchResult")
            }
        }
    }
}
