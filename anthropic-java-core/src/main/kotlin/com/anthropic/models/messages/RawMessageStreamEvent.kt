// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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

@JsonDeserialize(using = RawMessageStreamEvent.Deserializer::class)
@JsonSerialize(using = RawMessageStreamEvent.Serializer::class)
class RawMessageStreamEvent
private constructor(
    private val messageStart: RawMessageStartEvent? = null,
    private val messageDelta: RawMessageDeltaEvent? = null,
    private val messageStop: RawMessageStopEvent? = null,
    private val contentBlockStart: RawContentBlockStartEvent? = null,
    private val contentBlockDelta: RawContentBlockDeltaEvent? = null,
    private val contentBlockStop: RawContentBlockStopEvent? = null,
    private val _json: JsonValue? = null,
) {

    fun messageStart(): Optional<RawMessageStartEvent> = Optional.ofNullable(messageStart)

    fun messageDelta(): Optional<RawMessageDeltaEvent> = Optional.ofNullable(messageDelta)

    fun messageStop(): Optional<RawMessageStopEvent> = Optional.ofNullable(messageStop)

    fun contentBlockStart(): Optional<RawContentBlockStartEvent> =
        Optional.ofNullable(contentBlockStart)

    fun contentBlockDelta(): Optional<RawContentBlockDeltaEvent> =
        Optional.ofNullable(contentBlockDelta)

    fun contentBlockStop(): Optional<RawContentBlockStopEvent> =
        Optional.ofNullable(contentBlockStop)

    fun isMessageStart(): Boolean = messageStart != null

    fun isMessageDelta(): Boolean = messageDelta != null

    fun isMessageStop(): Boolean = messageStop != null

    fun isContentBlockStart(): Boolean = contentBlockStart != null

    fun isContentBlockDelta(): Boolean = contentBlockDelta != null

    fun isContentBlockStop(): Boolean = contentBlockStop != null

    fun asMessageStart(): RawMessageStartEvent = messageStart.getOrThrow("messageStart")

    fun asMessageDelta(): RawMessageDeltaEvent = messageDelta.getOrThrow("messageDelta")

    fun asMessageStop(): RawMessageStopEvent = messageStop.getOrThrow("messageStop")

    fun asContentBlockStart(): RawContentBlockStartEvent =
        contentBlockStart.getOrThrow("contentBlockStart")

    fun asContentBlockDelta(): RawContentBlockDeltaEvent =
        contentBlockDelta.getOrThrow("contentBlockDelta")

    fun asContentBlockStop(): RawContentBlockStopEvent =
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

    fun validate(): RawMessageStreamEvent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitMessageStart(messageStart: RawMessageStartEvent) {
                    messageStart.validate()
                }

                override fun visitMessageDelta(messageDelta: RawMessageDeltaEvent) {
                    messageDelta.validate()
                }

                override fun visitMessageStop(messageStop: RawMessageStopEvent) {
                    messageStop.validate()
                }

                override fun visitContentBlockStart(contentBlockStart: RawContentBlockStartEvent) {
                    contentBlockStart.validate()
                }

                override fun visitContentBlockDelta(contentBlockDelta: RawContentBlockDeltaEvent) {
                    contentBlockDelta.validate()
                }

                override fun visitContentBlockStop(contentBlockStop: RawContentBlockStopEvent) {
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
                override fun visitMessageStart(messageStart: RawMessageStartEvent) =
                    messageStart.validity()

                override fun visitMessageDelta(messageDelta: RawMessageDeltaEvent) =
                    messageDelta.validity()

                override fun visitMessageStop(messageStop: RawMessageStopEvent) =
                    messageStop.validity()

                override fun visitContentBlockStart(contentBlockStart: RawContentBlockStartEvent) =
                    contentBlockStart.validity()

                override fun visitContentBlockDelta(contentBlockDelta: RawContentBlockDeltaEvent) =
                    contentBlockDelta.validity()

                override fun visitContentBlockStop(contentBlockStop: RawContentBlockStopEvent) =
                    contentBlockStop.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is RawMessageStreamEvent && messageStart == other.messageStart && messageDelta == other.messageDelta && messageStop == other.messageStop && contentBlockStart == other.contentBlockStart && contentBlockDelta == other.contentBlockDelta && contentBlockStop == other.contentBlockStop /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(messageStart, messageDelta, messageStop, contentBlockStart, contentBlockDelta, contentBlockStop) /* spotless:on */

    override fun toString(): String =
        when {
            messageStart != null -> "RawMessageStreamEvent{messageStart=$messageStart}"
            messageDelta != null -> "RawMessageStreamEvent{messageDelta=$messageDelta}"
            messageStop != null -> "RawMessageStreamEvent{messageStop=$messageStop}"
            contentBlockStart != null ->
                "RawMessageStreamEvent{contentBlockStart=$contentBlockStart}"
            contentBlockDelta != null ->
                "RawMessageStreamEvent{contentBlockDelta=$contentBlockDelta}"
            contentBlockStop != null -> "RawMessageStreamEvent{contentBlockStop=$contentBlockStop}"
            _json != null -> "RawMessageStreamEvent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid RawMessageStreamEvent")
        }

    companion object {

        @JvmStatic
        fun ofMessageStart(messageStart: RawMessageStartEvent) =
            RawMessageStreamEvent(messageStart = messageStart)

        @JvmStatic
        fun ofMessageDelta(messageDelta: RawMessageDeltaEvent) =
            RawMessageStreamEvent(messageDelta = messageDelta)

        @JvmStatic
        fun ofMessageStop(messageStop: RawMessageStopEvent) =
            RawMessageStreamEvent(messageStop = messageStop)

        @JvmStatic
        fun ofContentBlockStart(contentBlockStart: RawContentBlockStartEvent) =
            RawMessageStreamEvent(contentBlockStart = contentBlockStart)

        @JvmStatic
        fun ofContentBlockDelta(contentBlockDelta: RawContentBlockDeltaEvent) =
            RawMessageStreamEvent(contentBlockDelta = contentBlockDelta)

        @JvmStatic
        fun ofContentBlockStop(contentBlockStop: RawContentBlockStopEvent) =
            RawMessageStreamEvent(contentBlockStop = contentBlockStop)
    }

    /**
     * An interface that defines how to map each variant of [RawMessageStreamEvent] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitMessageStart(messageStart: RawMessageStartEvent): T

        fun visitMessageDelta(messageDelta: RawMessageDeltaEvent): T

        fun visitMessageStop(messageStop: RawMessageStopEvent): T

        fun visitContentBlockStart(contentBlockStart: RawContentBlockStartEvent): T

        fun visitContentBlockDelta(contentBlockDelta: RawContentBlockDeltaEvent): T

        fun visitContentBlockStop(contentBlockStop: RawContentBlockStopEvent): T

        /**
         * Maps an unknown variant of [RawMessageStreamEvent] to a value of type [T].
         *
         * An instance of [RawMessageStreamEvent] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown RawMessageStreamEvent: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<RawMessageStreamEvent>(RawMessageStreamEvent::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): RawMessageStreamEvent {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "message_start" -> {
                    return tryDeserialize(node, jacksonTypeRef<RawMessageStartEvent>())?.let {
                        RawMessageStreamEvent(messageStart = it, _json = json)
                    } ?: RawMessageStreamEvent(_json = json)
                }
                "message_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<RawMessageDeltaEvent>())?.let {
                        RawMessageStreamEvent(messageDelta = it, _json = json)
                    } ?: RawMessageStreamEvent(_json = json)
                }
                "message_stop" -> {
                    return tryDeserialize(node, jacksonTypeRef<RawMessageStopEvent>())?.let {
                        RawMessageStreamEvent(messageStop = it, _json = json)
                    } ?: RawMessageStreamEvent(_json = json)
                }
                "content_block_start" -> {
                    return tryDeserialize(node, jacksonTypeRef<RawContentBlockStartEvent>())?.let {
                        RawMessageStreamEvent(contentBlockStart = it, _json = json)
                    } ?: RawMessageStreamEvent(_json = json)
                }
                "content_block_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<RawContentBlockDeltaEvent>())?.let {
                        RawMessageStreamEvent(contentBlockDelta = it, _json = json)
                    } ?: RawMessageStreamEvent(_json = json)
                }
                "content_block_stop" -> {
                    return tryDeserialize(node, jacksonTypeRef<RawContentBlockStopEvent>())?.let {
                        RawMessageStreamEvent(contentBlockStop = it, _json = json)
                    } ?: RawMessageStreamEvent(_json = json)
                }
            }

            return RawMessageStreamEvent(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<RawMessageStreamEvent>(RawMessageStreamEvent::class) {

        override fun serialize(
            value: RawMessageStreamEvent,
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
                else -> throw IllegalStateException("Invalid RawMessageStreamEvent")
            }
        }
    }
}
