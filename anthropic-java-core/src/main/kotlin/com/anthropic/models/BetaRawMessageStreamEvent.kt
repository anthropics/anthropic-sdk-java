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

@JsonDeserialize(using = BetaRawMessageStreamEvent.Deserializer::class)
@JsonSerialize(using = BetaRawMessageStreamEvent.Serializer::class)
class BetaRawMessageStreamEvent
private constructor(
    private val start: BetaRawMessageStartEvent? = null,
    private val delta: BetaRawMessageDeltaEvent? = null,
    private val stop: BetaRawMessageStopEvent? = null,
    private val contentBlockStart: BetaRawContentBlockStartEvent? = null,
    private val contentBlockDelta: BetaRawContentBlockDeltaEvent? = null,
    private val contentBlockStop: BetaRawContentBlockStopEvent? = null,
    private val _json: JsonValue? = null,
) {

    fun start(): Optional<BetaRawMessageStartEvent> = Optional.ofNullable(start)

    fun delta(): Optional<BetaRawMessageDeltaEvent> = Optional.ofNullable(delta)

    fun stop(): Optional<BetaRawMessageStopEvent> = Optional.ofNullable(stop)

    fun contentBlockStart(): Optional<BetaRawContentBlockStartEvent> =
        Optional.ofNullable(contentBlockStart)

    fun contentBlockDelta(): Optional<BetaRawContentBlockDeltaEvent> =
        Optional.ofNullable(contentBlockDelta)

    fun contentBlockStop(): Optional<BetaRawContentBlockStopEvent> =
        Optional.ofNullable(contentBlockStop)

    fun isStart(): Boolean = start != null

    fun isDelta(): Boolean = delta != null

    fun isStop(): Boolean = stop != null

    fun isContentBlockStart(): Boolean = contentBlockStart != null

    fun isContentBlockDelta(): Boolean = contentBlockDelta != null

    fun isContentBlockStop(): Boolean = contentBlockStop != null

    fun asStart(): BetaRawMessageStartEvent = start.getOrThrow("start")

    fun asDelta(): BetaRawMessageDeltaEvent = delta.getOrThrow("delta")

    fun asStop(): BetaRawMessageStopEvent = stop.getOrThrow("stop")

    fun asContentBlockStart(): BetaRawContentBlockStartEvent =
        contentBlockStart.getOrThrow("contentBlockStart")

    fun asContentBlockDelta(): BetaRawContentBlockDeltaEvent =
        contentBlockDelta.getOrThrow("contentBlockDelta")

    fun asContentBlockStop(): BetaRawContentBlockStopEvent =
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

    fun validate(): BetaRawMessageStreamEvent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitStart(start: BetaRawMessageStartEvent) {
                    start.validate()
                }

                override fun visitDelta(delta: BetaRawMessageDeltaEvent) {
                    delta.validate()
                }

                override fun visitStop(stop: BetaRawMessageStopEvent) {
                    stop.validate()
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaRawMessageStreamEvent && start == other.start && delta == other.delta && stop == other.stop && contentBlockStart == other.contentBlockStart && contentBlockDelta == other.contentBlockDelta && contentBlockStop == other.contentBlockStop /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(start, delta, stop, contentBlockStart, contentBlockDelta, contentBlockStop) /* spotless:on */

    override fun toString(): String =
        when {
            start != null -> "BetaRawMessageStreamEvent{start=$start}"
            delta != null -> "BetaRawMessageStreamEvent{delta=$delta}"
            stop != null -> "BetaRawMessageStreamEvent{stop=$stop}"
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
        fun ofStart(start: BetaRawMessageStartEvent) = BetaRawMessageStreamEvent(start = start)

        @JvmStatic
        fun ofDelta(delta: BetaRawMessageDeltaEvent) = BetaRawMessageStreamEvent(delta = delta)

        @JvmStatic
        fun ofStop(stop: BetaRawMessageStopEvent) = BetaRawMessageStreamEvent(stop = stop)

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

        fun visitStart(start: BetaRawMessageStartEvent): T

        fun visitDelta(delta: BetaRawMessageDeltaEvent): T

        fun visitStop(stop: BetaRawMessageStopEvent): T

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
                    tryDeserialize(node, jacksonTypeRef<BetaRawMessageStartEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaRawMessageStreamEvent(start = it, _json = json)
                        }
                }
                "message_delta" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRawMessageDeltaEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaRawMessageStreamEvent(delta = it, _json = json)
                        }
                }
                "message_stop" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRawMessageStopEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaRawMessageStreamEvent(stop = it, _json = json)
                        }
                }
                "content_block_start" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRawContentBlockStartEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaRawMessageStreamEvent(contentBlockStart = it, _json = json)
                        }
                }
                "content_block_delta" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRawContentBlockDeltaEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaRawMessageStreamEvent(contentBlockDelta = it, _json = json)
                        }
                }
                "content_block_stop" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRawContentBlockStopEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaRawMessageStreamEvent(contentBlockStop = it, _json = json)
                        }
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
                value.start != null -> generator.writeObject(value.start)
                value.delta != null -> generator.writeObject(value.delta)
                value.stop != null -> generator.writeObject(value.stop)
                value.contentBlockStart != null -> generator.writeObject(value.contentBlockStart)
                value.contentBlockDelta != null -> generator.writeObject(value.contentBlockDelta)
                value.contentBlockStop != null -> generator.writeObject(value.contentBlockStop)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaRawMessageStreamEvent")
            }
        }
    }
}
