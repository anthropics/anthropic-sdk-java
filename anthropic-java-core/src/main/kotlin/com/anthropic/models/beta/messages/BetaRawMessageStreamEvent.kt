// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

@JsonDeserialize(using = BetaRawMessageStreamEvent.Deserializer::class)
@JsonSerialize(using = BetaRawMessageStreamEvent.Serializer::class)
class BetaRawMessageStreamEvent
private constructor(
    private val messageStart: BetaRawMessageStartEvent? = null,
    private val messageDelta: BetaRawMessageDeltaEvent? = null,
    private val messageStop: BetaRawMessageStopEvent? = null,
    private val contentBlockStart: BetaRawContentBlockStartEvent? = null,
    private val contentBlockDelta: BetaRawContentBlockDeltaEvent? = null,
    private val contentBlockStop: BetaRawContentBlockStopEvent? = null,
    private val _json: JsonValue? = null,
) {

    fun messageStart(): Optional<BetaRawMessageStartEvent> = Optional.ofNullable(messageStart)

    fun messageDelta(): Optional<BetaRawMessageDeltaEvent> = Optional.ofNullable(messageDelta)

    fun messageStop(): Optional<BetaRawMessageStopEvent> = Optional.ofNullable(messageStop)

    fun contentBlockStart(): Optional<BetaRawContentBlockStartEvent> =
        Optional.ofNullable(contentBlockStart)

    fun contentBlockDelta(): Optional<BetaRawContentBlockDeltaEvent> =
        Optional.ofNullable(contentBlockDelta)

    fun contentBlockStop(): Optional<BetaRawContentBlockStopEvent> =
        Optional.ofNullable(contentBlockStop)

    fun isMessageStart(): Boolean = messageStart != null

    fun isMessageDelta(): Boolean = messageDelta != null

    fun isMessageStop(): Boolean = messageStop != null

    fun isContentBlockStart(): Boolean = contentBlockStart != null

    fun isContentBlockDelta(): Boolean = contentBlockDelta != null

    fun isContentBlockStop(): Boolean = contentBlockStop != null

    fun asMessageStart(): BetaRawMessageStartEvent = messageStart.getOrThrow("messageStart")

    fun asMessageDelta(): BetaRawMessageDeltaEvent = messageDelta.getOrThrow("messageDelta")

    fun asMessageStop(): BetaRawMessageStopEvent = messageStop.getOrThrow("messageStop")

    fun asContentBlockStart(): BetaRawContentBlockStartEvent =
        contentBlockStart.getOrThrow("contentBlockStart")

    fun asContentBlockDelta(): BetaRawContentBlockDeltaEvent =
        contentBlockDelta.getOrThrow("contentBlockDelta")

    fun asContentBlockStop(): BetaRawContentBlockStopEvent =
        contentBlockStop.getOrThrow("contentBlockStop")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            messageStart != null -> visitor.visitMessageStart(messageStart)
            messageDelta != null -> visitor.visitMessageDelta(messageDelta)
            messageStop != null -> visitor.visitMessageStop(messageStop)
            contentBlockStart != null -> visitor.visitContentBlockStart(contentBlockStart)
            contentBlockDelta != null -> visitor.visitContentBlockDelta(contentBlockDelta)
            contentBlockStop != null -> visitor.visitContentBlockStop(contentBlockStop)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): BetaRawMessageStreamEvent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitMessageStart(messageStart: BetaRawMessageStartEvent) {
                    messageStart.validate()
                }

                override fun visitMessageDelta(messageDelta: BetaRawMessageDeltaEvent) {
                    messageDelta.validate()
                }

                override fun visitMessageStop(messageStop: BetaRawMessageStopEvent) {
                    messageStop.validate()
                }

                override fun visitContentBlockStart(
                    contentBlockStart: BetaRawContentBlockStartEvent
                ) {
                    contentBlockStart.validate()
                }

                override fun visitContentBlockDelta(
                    contentBlockDelta: BetaRawContentBlockDeltaEvent
                ) {
                    contentBlockDelta.validate()
                }

                override fun visitContentBlockStop(contentBlockStop: BetaRawContentBlockStopEvent) {
                    contentBlockStop.validate()
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
                override fun visitMessageStart(messageStart: BetaRawMessageStartEvent) =
                    messageStart.validity()

                override fun visitMessageDelta(messageDelta: BetaRawMessageDeltaEvent) =
                    messageDelta.validity()

                override fun visitMessageStop(messageStop: BetaRawMessageStopEvent) =
                    messageStop.validity()

                override fun visitContentBlockStart(
                    contentBlockStart: BetaRawContentBlockStartEvent
                ) = contentBlockStart.validity()

                override fun visitContentBlockDelta(
                    contentBlockDelta: BetaRawContentBlockDeltaEvent
                ) = contentBlockDelta.validity()

                override fun visitContentBlockStop(contentBlockStop: BetaRawContentBlockStopEvent) =
                    contentBlockStop.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRawMessageStreamEvent &&
            messageStart == other.messageStart &&
            messageDelta == other.messageDelta &&
            messageStop == other.messageStop &&
            contentBlockStart == other.contentBlockStart &&
            contentBlockDelta == other.contentBlockDelta &&
            contentBlockStop == other.contentBlockStop
    }

    override fun hashCode(): Int =
        Objects.hash(
            messageStart,
            messageDelta,
            messageStop,
            contentBlockStart,
            contentBlockDelta,
            contentBlockStop,
        )

    override fun toString(): String =
        when {
            messageStart != null -> "BetaRawMessageStreamEvent{messageStart=$messageStart}"
            messageDelta != null -> "BetaRawMessageStreamEvent{messageDelta=$messageDelta}"
            messageStop != null -> "BetaRawMessageStreamEvent{messageStop=$messageStop}"
            contentBlockStart != null ->
                "BetaRawMessageStreamEvent{contentBlockStart=$contentBlockStart}"
            contentBlockDelta != null ->
                "BetaRawMessageStreamEvent{contentBlockDelta=$contentBlockDelta}"
            contentBlockStop != null ->
                "BetaRawMessageStreamEvent{contentBlockStop=$contentBlockStop}"
            _json != null -> "BetaRawMessageStreamEvent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaRawMessageStreamEvent")
        }

    companion object {

        @JvmStatic
        fun ofMessageStart(messageStart: BetaRawMessageStartEvent) =
            BetaRawMessageStreamEvent(messageStart = messageStart)

        @JvmStatic
        fun ofMessageDelta(messageDelta: BetaRawMessageDeltaEvent) =
            BetaRawMessageStreamEvent(messageDelta = messageDelta)

        @JvmStatic
        fun ofMessageStop(messageStop: BetaRawMessageStopEvent) =
            BetaRawMessageStreamEvent(messageStop = messageStop)

        @JvmStatic
        fun ofContentBlockStart(contentBlockStart: BetaRawContentBlockStartEvent) =
            BetaRawMessageStreamEvent(contentBlockStart = contentBlockStart)

        @JvmStatic
        fun ofContentBlockDelta(contentBlockDelta: BetaRawContentBlockDeltaEvent) =
            BetaRawMessageStreamEvent(contentBlockDelta = contentBlockDelta)

        @JvmStatic
        fun ofContentBlockStop(contentBlockStop: BetaRawContentBlockStopEvent) =
            BetaRawMessageStreamEvent(contentBlockStop = contentBlockStop)
    }

    /**
     * An interface that defines how to map each variant of [BetaRawMessageStreamEvent] to a value
     * of type [T].
     */
    interface Visitor<out T> {

        fun visitMessageStart(messageStart: BetaRawMessageStartEvent): T

        fun visitMessageDelta(messageDelta: BetaRawMessageDeltaEvent): T

        fun visitMessageStop(messageStop: BetaRawMessageStopEvent): T

        fun visitContentBlockStart(contentBlockStart: BetaRawContentBlockStartEvent): T

        fun visitContentBlockDelta(contentBlockDelta: BetaRawContentBlockDeltaEvent): T

        fun visitContentBlockStop(contentBlockStop: BetaRawContentBlockStopEvent): T

        /**
         * Maps an unknown variant of [BetaRawMessageStreamEvent] to a value of type [T].
         *
         * An instance of [BetaRawMessageStreamEvent] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaRawMessageStreamEvent: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaRawMessageStreamEvent>(BetaRawMessageStreamEvent::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaRawMessageStreamEvent {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "message_start" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRawMessageStartEvent>())?.let {
                        BetaRawMessageStreamEvent(messageStart = it, _json = json)
                    } ?: BetaRawMessageStreamEvent(_json = json)
                }
                "message_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRawMessageDeltaEvent>())?.let {
                        BetaRawMessageStreamEvent(messageDelta = it, _json = json)
                    } ?: BetaRawMessageStreamEvent(_json = json)
                }
                "message_stop" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRawMessageStopEvent>())?.let {
                        BetaRawMessageStreamEvent(messageStop = it, _json = json)
                    } ?: BetaRawMessageStreamEvent(_json = json)
                }
                "content_block_start" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRawContentBlockStartEvent>())
                        ?.let { BetaRawMessageStreamEvent(contentBlockStart = it, _json = json) }
                        ?: BetaRawMessageStreamEvent(_json = json)
                }
                "content_block_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRawContentBlockDeltaEvent>())
                        ?.let { BetaRawMessageStreamEvent(contentBlockDelta = it, _json = json) }
                        ?: BetaRawMessageStreamEvent(_json = json)
                }
                "content_block_stop" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRawContentBlockStopEvent>())
                        ?.let { BetaRawMessageStreamEvent(contentBlockStop = it, _json = json) }
                        ?: BetaRawMessageStreamEvent(_json = json)
                }
            }

            return BetaRawMessageStreamEvent(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaRawMessageStreamEvent>(BetaRawMessageStreamEvent::class) {

        override fun serialize(
            value: BetaRawMessageStreamEvent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.messageStart != null -> generator.writeObject(value.messageStart)
                value.messageDelta != null -> generator.writeObject(value.messageDelta)
                value.messageStop != null -> generator.writeObject(value.messageStop)
                value.contentBlockStart != null -> generator.writeObject(value.contentBlockStart)
                value.contentBlockDelta != null -> generator.writeObject(value.contentBlockDelta)
                value.contentBlockStop != null -> generator.writeObject(value.contentBlockStop)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaRawMessageStreamEvent")
            }
        }
    }
}
