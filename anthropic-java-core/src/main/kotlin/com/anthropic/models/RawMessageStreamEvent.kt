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

@JsonDeserialize(using = RawMessageStreamEvent.Deserializer::class)
@JsonSerialize(using = RawMessageStreamEvent.Serializer::class)
class RawMessageStreamEvent
private constructor(
    private val start: RawMessageStartEvent? = null,
    private val delta: RawMessageDeltaEvent? = null,
    private val stop: RawMessageStopEvent? = null,
    private val contentBlockStart: RawContentBlockStartEvent? = null,
    private val contentBlockDelta: RawContentBlockDeltaEvent? = null,
    private val contentBlockStop: RawContentBlockStopEvent? = null,
    private val _json: JsonValue? = null,
) {

    fun start(): Optional<RawMessageStartEvent> = Optional.ofNullable(start)

    fun delta(): Optional<RawMessageDeltaEvent> = Optional.ofNullable(delta)

    fun stop(): Optional<RawMessageStopEvent> = Optional.ofNullable(stop)

    fun contentBlockStart(): Optional<RawContentBlockStartEvent> =
        Optional.ofNullable(contentBlockStart)

    fun contentBlockDelta(): Optional<RawContentBlockDeltaEvent> =
        Optional.ofNullable(contentBlockDelta)

    fun contentBlockStop(): Optional<RawContentBlockStopEvent> =
        Optional.ofNullable(contentBlockStop)

    fun isStart(): Boolean = start != null

    fun isDelta(): Boolean = delta != null

    fun isStop(): Boolean = stop != null

    fun isContentBlockStart(): Boolean = contentBlockStart != null

    fun isContentBlockDelta(): Boolean = contentBlockDelta != null

    fun isContentBlockStop(): Boolean = contentBlockStop != null

    fun asStart(): RawMessageStartEvent = start.getOrThrow("start")

    fun asDelta(): RawMessageDeltaEvent = delta.getOrThrow("delta")

    fun asStop(): RawMessageStopEvent = stop.getOrThrow("stop")

    fun asContentBlockStart(): RawContentBlockStartEvent =
        contentBlockStart.getOrThrow("contentBlockStart")

    fun asContentBlockDelta(): RawContentBlockDeltaEvent =
        contentBlockDelta.getOrThrow("contentBlockDelta")

    fun asContentBlockStop(): RawContentBlockStopEvent =
        contentBlockStop.getOrThrow("contentBlockStop")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            start != null -> visitor.visitStart(start)
            delta != null -> visitor.visitDelta(delta)
            stop != null -> visitor.visitStop(stop)
            contentBlockStart != null -> visitor.visitContentBlockStart(contentBlockStart)
            contentBlockDelta != null -> visitor.visitContentBlockDelta(contentBlockDelta)
            contentBlockStop != null -> visitor.visitContentBlockStop(contentBlockStop)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): RawMessageStreamEvent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitStart(start: RawMessageStartEvent) {
                    start.validate()
                }

                override fun visitDelta(delta: RawMessageDeltaEvent) {
                    delta.validate()
                }

                override fun visitStop(stop: RawMessageStopEvent) {
                    stop.validate()
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is RawMessageStreamEvent && start == other.start && delta == other.delta && stop == other.stop && contentBlockStart == other.contentBlockStart && contentBlockDelta == other.contentBlockDelta && contentBlockStop == other.contentBlockStop /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(start, delta, stop, contentBlockStart, contentBlockDelta, contentBlockStop) /* spotless:on */

    override fun toString(): String =
        when {
            start != null -> "RawMessageStreamEvent{start=$start}"
            delta != null -> "RawMessageStreamEvent{delta=$delta}"
            stop != null -> "RawMessageStreamEvent{stop=$stop}"
            contentBlockStart != null ->
                "RawMessageStreamEvent{contentBlockStart=$contentBlockStart}"
            contentBlockDelta != null ->
                "RawMessageStreamEvent{contentBlockDelta=$contentBlockDelta}"
            contentBlockStop != null -> "RawMessageStreamEvent{contentBlockStop=$contentBlockStop}"
            _json != null -> "RawMessageStreamEvent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid RawMessageStreamEvent")
        }

    companion object {

        @JvmStatic fun ofStart(start: RawMessageStartEvent) = RawMessageStreamEvent(start = start)

        @JvmStatic fun ofDelta(delta: RawMessageDeltaEvent) = RawMessageStreamEvent(delta = delta)

        @JvmStatic fun ofStop(stop: RawMessageStopEvent) = RawMessageStreamEvent(stop = stop)

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

        fun visitStart(start: RawMessageStartEvent): T

        fun visitDelta(delta: RawMessageDeltaEvent): T

        fun visitStop(stop: RawMessageStopEvent): T

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
                    tryDeserialize(node, jacksonTypeRef<RawMessageStartEvent>()) { it.validate() }
                        ?.let {
                            return RawMessageStreamEvent(start = it, _json = json)
                        }
                }
                "message_delta" -> {
                    tryDeserialize(node, jacksonTypeRef<RawMessageDeltaEvent>()) { it.validate() }
                        ?.let {
                            return RawMessageStreamEvent(delta = it, _json = json)
                        }
                }
                "message_stop" -> {
                    tryDeserialize(node, jacksonTypeRef<RawMessageStopEvent>()) { it.validate() }
                        ?.let {
                            return RawMessageStreamEvent(stop = it, _json = json)
                        }
                }
                "content_block_start" -> {
                    tryDeserialize(node, jacksonTypeRef<RawContentBlockStartEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return RawMessageStreamEvent(contentBlockStart = it, _json = json)
                        }
                }
                "content_block_delta" -> {
                    tryDeserialize(node, jacksonTypeRef<RawContentBlockDeltaEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return RawMessageStreamEvent(contentBlockDelta = it, _json = json)
                        }
                }
                "content_block_stop" -> {
                    tryDeserialize(node, jacksonTypeRef<RawContentBlockStopEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return RawMessageStreamEvent(contentBlockStop = it, _json = json)
                        }
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
                value.start != null -> generator.writeObject(value.start)
                value.delta != null -> generator.writeObject(value.delta)
                value.stop != null -> generator.writeObject(value.stop)
                value.contentBlockStart != null -> generator.writeObject(value.contentBlockStart)
                value.contentBlockDelta != null -> generator.writeObject(value.contentBlockDelta)
                value.contentBlockStop != null -> generator.writeObject(value.contentBlockStop)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid RawMessageStreamEvent")
            }
        }
    }
}
