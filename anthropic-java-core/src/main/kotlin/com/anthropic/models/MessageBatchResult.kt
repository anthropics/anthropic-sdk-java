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

@JsonDeserialize(using = MessageBatchResult.Deserializer::class)
@JsonSerialize(using = MessageBatchResult.Serializer::class)
class MessageBatchResult
private constructor(
    private val messageBatchSucceededResult: MessageBatchSucceededResult? = null,
    private val messageBatchErroredResult: MessageBatchErroredResult? = null,
    private val messageBatchCanceledResult: MessageBatchCanceledResult? = null,
    private val messageBatchExpiredResult: MessageBatchExpiredResult? = null,
    private val _json: JsonValue? = null,
) {

    private var validated: Boolean = false

    fun messageBatchSucceededResult(): Optional<MessageBatchSucceededResult> =
        Optional.ofNullable(messageBatchSucceededResult)

    fun messageBatchErroredResult(): Optional<MessageBatchErroredResult> =
        Optional.ofNullable(messageBatchErroredResult)

    fun messageBatchCanceledResult(): Optional<MessageBatchCanceledResult> =
        Optional.ofNullable(messageBatchCanceledResult)

    fun messageBatchExpiredResult(): Optional<MessageBatchExpiredResult> =
        Optional.ofNullable(messageBatchExpiredResult)

    fun isMessageBatchSucceededResult(): Boolean = messageBatchSucceededResult != null

    fun isMessageBatchErroredResult(): Boolean = messageBatchErroredResult != null

    fun isMessageBatchCanceledResult(): Boolean = messageBatchCanceledResult != null

    fun isMessageBatchExpiredResult(): Boolean = messageBatchExpiredResult != null

    fun asMessageBatchSucceededResult(): MessageBatchSucceededResult =
        messageBatchSucceededResult.getOrThrow("messageBatchSucceededResult")

    fun asMessageBatchErroredResult(): MessageBatchErroredResult =
        messageBatchErroredResult.getOrThrow("messageBatchErroredResult")

    fun asMessageBatchCanceledResult(): MessageBatchCanceledResult =
        messageBatchCanceledResult.getOrThrow("messageBatchCanceledResult")

    fun asMessageBatchExpiredResult(): MessageBatchExpiredResult =
        messageBatchExpiredResult.getOrThrow("messageBatchExpiredResult")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            messageBatchSucceededResult != null ->
                visitor.visitMessageBatchSucceededResult(messageBatchSucceededResult)
            messageBatchErroredResult != null ->
                visitor.visitMessageBatchErroredResult(messageBatchErroredResult)
            messageBatchCanceledResult != null ->
                visitor.visitMessageBatchCanceledResult(messageBatchCanceledResult)
            messageBatchExpiredResult != null ->
                visitor.visitMessageBatchExpiredResult(messageBatchExpiredResult)
            else -> visitor.unknown(_json)
        }
    }

    fun validate(): MessageBatchResult = apply {
        if (!validated) {
            if (
                messageBatchSucceededResult == null &&
                    messageBatchErroredResult == null &&
                    messageBatchCanceledResult == null &&
                    messageBatchExpiredResult == null
            ) {
                throw AnthropicInvalidDataException("Unknown MessageBatchResult: $_json")
            }
            messageBatchSucceededResult?.validate()
            messageBatchErroredResult?.validate()
            messageBatchCanceledResult?.validate()
            messageBatchExpiredResult?.validate()
            validated = true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is MessageBatchResult && messageBatchSucceededResult == other.messageBatchSucceededResult && messageBatchErroredResult == other.messageBatchErroredResult && messageBatchCanceledResult == other.messageBatchCanceledResult && messageBatchExpiredResult == other.messageBatchExpiredResult /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(messageBatchSucceededResult, messageBatchErroredResult, messageBatchCanceledResult, messageBatchExpiredResult) /* spotless:on */

    override fun toString(): String =
        when {
            messageBatchSucceededResult != null ->
                "MessageBatchResult{messageBatchSucceededResult=$messageBatchSucceededResult}"
            messageBatchErroredResult != null ->
                "MessageBatchResult{messageBatchErroredResult=$messageBatchErroredResult}"
            messageBatchCanceledResult != null ->
                "MessageBatchResult{messageBatchCanceledResult=$messageBatchCanceledResult}"
            messageBatchExpiredResult != null ->
                "MessageBatchResult{messageBatchExpiredResult=$messageBatchExpiredResult}"
            _json != null -> "MessageBatchResult{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid MessageBatchResult")
        }

    companion object {

        @JvmStatic
        fun ofMessageBatchSucceededResult(
            messageBatchSucceededResult: MessageBatchSucceededResult
        ) = MessageBatchResult(messageBatchSucceededResult = messageBatchSucceededResult)

        @JvmStatic
        fun ofMessageBatchErroredResult(messageBatchErroredResult: MessageBatchErroredResult) =
            MessageBatchResult(messageBatchErroredResult = messageBatchErroredResult)

        @JvmStatic
        fun ofMessageBatchCanceledResult(messageBatchCanceledResult: MessageBatchCanceledResult) =
            MessageBatchResult(messageBatchCanceledResult = messageBatchCanceledResult)

        @JvmStatic
        fun ofMessageBatchExpiredResult(messageBatchExpiredResult: MessageBatchExpiredResult) =
            MessageBatchResult(messageBatchExpiredResult = messageBatchExpiredResult)
    }

    interface Visitor<out T> {

        fun visitMessageBatchSucceededResult(
            messageBatchSucceededResult: MessageBatchSucceededResult
        ): T

        fun visitMessageBatchErroredResult(messageBatchErroredResult: MessageBatchErroredResult): T

        fun visitMessageBatchCanceledResult(
            messageBatchCanceledResult: MessageBatchCanceledResult
        ): T

        fun visitMessageBatchExpiredResult(messageBatchExpiredResult: MessageBatchExpiredResult): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown MessageBatchResult: $json")
        }
    }

    class Deserializer : BaseDeserializer<MessageBatchResult>(MessageBatchResult::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): MessageBatchResult {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "succeeded" -> {
                    tryDeserialize(node, jacksonTypeRef<MessageBatchSucceededResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return MessageBatchResult(
                                messageBatchSucceededResult = it,
                                _json = json
                            )
                        }
                }
                "errored" -> {
                    tryDeserialize(node, jacksonTypeRef<MessageBatchErroredResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return MessageBatchResult(messageBatchErroredResult = it, _json = json)
                        }
                }
                "canceled" -> {
                    tryDeserialize(node, jacksonTypeRef<MessageBatchCanceledResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return MessageBatchResult(messageBatchCanceledResult = it, _json = json)
                        }
                }
                "expired" -> {
                    tryDeserialize(node, jacksonTypeRef<MessageBatchExpiredResult>()) {
                            it.validate()
                        }
                        ?.let {
                            return MessageBatchResult(messageBatchExpiredResult = it, _json = json)
                        }
                }
            }

            return MessageBatchResult(_json = json)
        }
    }

    class Serializer : BaseSerializer<MessageBatchResult>(MessageBatchResult::class) {

        override fun serialize(
            value: MessageBatchResult,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.messageBatchSucceededResult != null ->
                    generator.writeObject(value.messageBatchSucceededResult)
                value.messageBatchErroredResult != null ->
                    generator.writeObject(value.messageBatchErroredResult)
                value.messageBatchCanceledResult != null ->
                    generator.writeObject(value.messageBatchCanceledResult)
                value.messageBatchExpiredResult != null ->
                    generator.writeObject(value.messageBatchExpiredResult)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid MessageBatchResult")
            }
        }
    }
}
