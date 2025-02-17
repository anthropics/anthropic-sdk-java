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
@JsonDeserialize(using = MessageBatchResult.Deserializer::class)
@JsonSerialize(using = MessageBatchResult.Serializer::class)
class MessageBatchResult
private constructor(
    private val succeeded: MessageBatchSucceededResult? = null,
    private val errored: MessageBatchErroredResult? = null,
    private val canceled: MessageBatchCanceledResult? = null,
    private val expired: MessageBatchExpiredResult? = null,
    private val _json: JsonValue? = null,
) {

    fun succeeded(): Optional<MessageBatchSucceededResult> = Optional.ofNullable(succeeded)

    fun errored(): Optional<MessageBatchErroredResult> = Optional.ofNullable(errored)

    fun canceled(): Optional<MessageBatchCanceledResult> = Optional.ofNullable(canceled)

    fun expired(): Optional<MessageBatchExpiredResult> = Optional.ofNullable(expired)

    fun isSucceeded(): Boolean = succeeded != null

    fun isErrored(): Boolean = errored != null

    fun isCanceled(): Boolean = canceled != null

    fun isExpired(): Boolean = expired != null

    fun asSucceeded(): MessageBatchSucceededResult = succeeded.getOrThrow("succeeded")

    fun asErrored(): MessageBatchErroredResult = errored.getOrThrow("errored")

    fun asCanceled(): MessageBatchCanceledResult = canceled.getOrThrow("canceled")

    fun asExpired(): MessageBatchExpiredResult = expired.getOrThrow("expired")

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

    fun validate(): MessageBatchResult = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitSucceeded(succeeded: MessageBatchSucceededResult) {
                    succeeded.validate()
                }

                override fun visitErrored(errored: MessageBatchErroredResult) {
                    errored.validate()
                }

                override fun visitCanceled(canceled: MessageBatchCanceledResult) {
                    canceled.validate()
                }

                override fun visitExpired(expired: MessageBatchExpiredResult) {
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

        return /* spotless:off */ other is MessageBatchResult && succeeded == other.succeeded && errored == other.errored && canceled == other.canceled && expired == other.expired /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(succeeded, errored, canceled, expired) /* spotless:on */

    override fun toString(): String =
        when {
            succeeded != null -> "MessageBatchResult{succeeded=$succeeded}"
            errored != null -> "MessageBatchResult{errored=$errored}"
            canceled != null -> "MessageBatchResult{canceled=$canceled}"
            expired != null -> "MessageBatchResult{expired=$expired}"
            _json != null -> "MessageBatchResult{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid MessageBatchResult")
        }

    companion object {

        @JvmStatic
        fun ofSucceeded(succeeded: MessageBatchSucceededResult) =
            MessageBatchResult(succeeded = succeeded)

        @JvmStatic
        fun ofErrored(errored: MessageBatchErroredResult) = MessageBatchResult(errored = errored)

        @JvmStatic
        fun ofCanceled(canceled: MessageBatchCanceledResult) =
            MessageBatchResult(canceled = canceled)

        @JvmStatic
        fun ofExpired(expired: MessageBatchExpiredResult) = MessageBatchResult(expired = expired)
    }

    /**
     * An interface that defines how to map each variant of [MessageBatchResult] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitSucceeded(succeeded: MessageBatchSucceededResult): T

        fun visitErrored(errored: MessageBatchErroredResult): T

        fun visitCanceled(canceled: MessageBatchCanceledResult): T

        fun visitExpired(expired: MessageBatchExpiredResult): T

        /**
         * Maps an unknown variant of [MessageBatchResult] to a value of type [T].
         *
         * An instance of [MessageBatchResult] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown MessageBatchResult: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<MessageBatchResult>(MessageBatchResult::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): MessageBatchResult {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "succeeded" -> {
                    tryDeserialize(node, jacksonTypeRef<MessageBatchSucceededResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return MessageBatchResult(succeeded = it, _json = json)
                        }
                }
                "errored" -> {
                    tryDeserialize(node, jacksonTypeRef<MessageBatchErroredResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return MessageBatchResult(errored = it, _json = json)
                        }
                }
                "canceled" -> {
                    tryDeserialize(node, jacksonTypeRef<MessageBatchCanceledResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return MessageBatchResult(canceled = it, _json = json)
                        }
                }
                "expired" -> {
                    tryDeserialize(node, jacksonTypeRef<MessageBatchExpiredResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return MessageBatchResult(expired = it, _json = json)
                        }
                }
            }

            return MessageBatchResult(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<MessageBatchResult>(MessageBatchResult::class) {

        override fun serialize(
            value: MessageBatchResult,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.succeeded != null -> generator.writeObject(value.succeeded)
                value.errored != null -> generator.writeObject(value.errored)
                value.canceled != null -> generator.writeObject(value.canceled)
                value.expired != null -> generator.writeObject(value.expired)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid MessageBatchResult")
            }
        }
    }
}
