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
    private val rawMessageStartEvent: RawMessageStartEvent? = null,
    private val rawMessageDeltaEvent: RawMessageDeltaEvent? = null,
    private val rawMessageStopEvent: RawMessageStopEvent? = null,
    private val rawContentBlockStartEvent: RawContentBlockStartEvent? = null,
    private val rawContentBlockDeltaEvent: RawContentBlockDeltaEvent? = null,
    private val rawContentBlockStopEvent: RawContentBlockStopEvent? = null,
    private val _json: JsonValue? = null,
) {

    private var validated: Boolean = false

    fun rawMessageStartEvent(): Optional<RawMessageStartEvent> =
        Optional.ofNullable(rawMessageStartEvent)

    fun rawMessageDeltaEvent(): Optional<RawMessageDeltaEvent> =
        Optional.ofNullable(rawMessageDeltaEvent)

    fun rawMessageStopEvent(): Optional<RawMessageStopEvent> =
        Optional.ofNullable(rawMessageStopEvent)

    fun rawContentBlockStartEvent(): Optional<RawContentBlockStartEvent> =
        Optional.ofNullable(rawContentBlockStartEvent)

    fun rawContentBlockDeltaEvent(): Optional<RawContentBlockDeltaEvent> =
        Optional.ofNullable(rawContentBlockDeltaEvent)

    fun rawContentBlockStopEvent(): Optional<RawContentBlockStopEvent> =
        Optional.ofNullable(rawContentBlockStopEvent)

    fun isRawMessageStartEvent(): Boolean = rawMessageStartEvent != null

    fun isRawMessageDeltaEvent(): Boolean = rawMessageDeltaEvent != null

    fun isRawMessageStopEvent(): Boolean = rawMessageStopEvent != null

    fun isRawContentBlockStartEvent(): Boolean = rawContentBlockStartEvent != null

    fun isRawContentBlockDeltaEvent(): Boolean = rawContentBlockDeltaEvent != null

    fun isRawContentBlockStopEvent(): Boolean = rawContentBlockStopEvent != null

    fun asRawMessageStartEvent(): RawMessageStartEvent =
        rawMessageStartEvent.getOrThrow("rawMessageStartEvent")

    fun asRawMessageDeltaEvent(): RawMessageDeltaEvent =
        rawMessageDeltaEvent.getOrThrow("rawMessageDeltaEvent")

    fun asRawMessageStopEvent(): RawMessageStopEvent =
        rawMessageStopEvent.getOrThrow("rawMessageStopEvent")

    fun asRawContentBlockStartEvent(): RawContentBlockStartEvent =
        rawContentBlockStartEvent.getOrThrow("rawContentBlockStartEvent")

    fun asRawContentBlockDeltaEvent(): RawContentBlockDeltaEvent =
        rawContentBlockDeltaEvent.getOrThrow("rawContentBlockDeltaEvent")

    fun asRawContentBlockStopEvent(): RawContentBlockStopEvent =
        rawContentBlockStopEvent.getOrThrow("rawContentBlockStopEvent")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            rawMessageStartEvent != null -> visitor.visitRawMessageStartEvent(rawMessageStartEvent)
            rawMessageDeltaEvent != null -> visitor.visitRawMessageDeltaEvent(rawMessageDeltaEvent)
            rawMessageStopEvent != null -> visitor.visitRawMessageStopEvent(rawMessageStopEvent)
            rawContentBlockStartEvent != null ->
                visitor.visitRawContentBlockStartEvent(rawContentBlockStartEvent)
            rawContentBlockDeltaEvent != null ->
                visitor.visitRawContentBlockDeltaEvent(rawContentBlockDeltaEvent)
            rawContentBlockStopEvent != null ->
                visitor.visitRawContentBlockStopEvent(rawContentBlockStopEvent)
            else -> visitor.unknown(_json)
        }
    }

    fun validate(): RawMessageStreamEvent = apply {
        if (!validated) {
            if (
                rawMessageStartEvent == null &&
                    rawMessageDeltaEvent == null &&
                    rawMessageStopEvent == null &&
                    rawContentBlockStartEvent == null &&
                    rawContentBlockDeltaEvent == null &&
                    rawContentBlockStopEvent == null
            ) {
                throw AnthropicInvalidDataException("Unknown RawMessageStreamEvent: $_json")
            }
            rawMessageStartEvent?.validate()
            rawMessageDeltaEvent?.validate()
            rawMessageStopEvent?.validate()
            rawContentBlockStartEvent?.validate()
            rawContentBlockDeltaEvent?.validate()
            rawContentBlockStopEvent?.validate()
            validated = true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is RawMessageStreamEvent && rawMessageStartEvent == other.rawMessageStartEvent && rawMessageDeltaEvent == other.rawMessageDeltaEvent && rawMessageStopEvent == other.rawMessageStopEvent && rawContentBlockStartEvent == other.rawContentBlockStartEvent && rawContentBlockDeltaEvent == other.rawContentBlockDeltaEvent && rawContentBlockStopEvent == other.rawContentBlockStopEvent /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(rawMessageStartEvent, rawMessageDeltaEvent, rawMessageStopEvent, rawContentBlockStartEvent, rawContentBlockDeltaEvent, rawContentBlockStopEvent) /* spotless:on */

    override fun toString(): String =
        when {
            rawMessageStartEvent != null ->
                "RawMessageStreamEvent{rawMessageStartEvent=$rawMessageStartEvent}"
            rawMessageDeltaEvent != null ->
                "RawMessageStreamEvent{rawMessageDeltaEvent=$rawMessageDeltaEvent}"
            rawMessageStopEvent != null ->
                "RawMessageStreamEvent{rawMessageStopEvent=$rawMessageStopEvent}"
            rawContentBlockStartEvent != null ->
                "RawMessageStreamEvent{rawContentBlockStartEvent=$rawContentBlockStartEvent}"
            rawContentBlockDeltaEvent != null ->
                "RawMessageStreamEvent{rawContentBlockDeltaEvent=$rawContentBlockDeltaEvent}"
            rawContentBlockStopEvent != null ->
                "RawMessageStreamEvent{rawContentBlockStopEvent=$rawContentBlockStopEvent}"
            _json != null -> "RawMessageStreamEvent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid RawMessageStreamEvent")
        }

    companion object {

        @JvmStatic
        fun ofRawMessageStartEvent(rawMessageStartEvent: RawMessageStartEvent) =
            RawMessageStreamEvent(rawMessageStartEvent = rawMessageStartEvent)

        @JvmStatic
        fun ofRawMessageDeltaEvent(rawMessageDeltaEvent: RawMessageDeltaEvent) =
            RawMessageStreamEvent(rawMessageDeltaEvent = rawMessageDeltaEvent)

        @JvmStatic
        fun ofRawMessageStopEvent(rawMessageStopEvent: RawMessageStopEvent) =
            RawMessageStreamEvent(rawMessageStopEvent = rawMessageStopEvent)

        @JvmStatic
        fun ofRawContentBlockStartEvent(rawContentBlockStartEvent: RawContentBlockStartEvent) =
            RawMessageStreamEvent(rawContentBlockStartEvent = rawContentBlockStartEvent)

        @JvmStatic
        fun ofRawContentBlockDeltaEvent(rawContentBlockDeltaEvent: RawContentBlockDeltaEvent) =
            RawMessageStreamEvent(rawContentBlockDeltaEvent = rawContentBlockDeltaEvent)

        @JvmStatic
        fun ofRawContentBlockStopEvent(rawContentBlockStopEvent: RawContentBlockStopEvent) =
            RawMessageStreamEvent(rawContentBlockStopEvent = rawContentBlockStopEvent)
    }

    interface Visitor<out T> {

        fun visitRawMessageStartEvent(rawMessageStartEvent: RawMessageStartEvent): T

        fun visitRawMessageDeltaEvent(rawMessageDeltaEvent: RawMessageDeltaEvent): T

        fun visitRawMessageStopEvent(rawMessageStopEvent: RawMessageStopEvent): T

        fun visitRawContentBlockStartEvent(rawContentBlockStartEvent: RawContentBlockStartEvent): T

        fun visitRawContentBlockDeltaEvent(rawContentBlockDeltaEvent: RawContentBlockDeltaEvent): T

        fun visitRawContentBlockStopEvent(rawContentBlockStopEvent: RawContentBlockStopEvent): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown RawMessageStreamEvent: $json")
        }
    }

    class Deserializer : BaseDeserializer<RawMessageStreamEvent>(RawMessageStreamEvent::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): RawMessageStreamEvent {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "message_start" -> {
                    tryDeserialize(node, jacksonTypeRef<RawMessageStartEvent>()) { it.validate() }
                        ?.let {
                            return RawMessageStreamEvent(rawMessageStartEvent = it, _json = json)
                        }
                }
                "message_delta" -> {
                    tryDeserialize(node, jacksonTypeRef<RawMessageDeltaEvent>()) { it.validate() }
                        ?.let {
                            return RawMessageStreamEvent(rawMessageDeltaEvent = it, _json = json)
                        }
                }
                "message_stop" -> {
                    tryDeserialize(node, jacksonTypeRef<RawMessageStopEvent>()) { it.validate() }
                        ?.let {
                            return RawMessageStreamEvent(rawMessageStopEvent = it, _json = json)
                        }
                }
                "content_block_start" -> {
                    tryDeserialize(node, jacksonTypeRef<RawContentBlockStartEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return RawMessageStreamEvent(
                                rawContentBlockStartEvent = it,
                                _json = json
                            )
                        }
                }
                "content_block_delta" -> {
                    tryDeserialize(node, jacksonTypeRef<RawContentBlockDeltaEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return RawMessageStreamEvent(
                                rawContentBlockDeltaEvent = it,
                                _json = json
                            )
                        }
                }
                "content_block_stop" -> {
                    tryDeserialize(node, jacksonTypeRef<RawContentBlockStopEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return RawMessageStreamEvent(
                                rawContentBlockStopEvent = it,
                                _json = json
                            )
                        }
                }
            }

            return RawMessageStreamEvent(_json = json)
        }
    }

    class Serializer : BaseSerializer<RawMessageStreamEvent>(RawMessageStreamEvent::class) {

        override fun serialize(
            value: RawMessageStreamEvent,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.rawMessageStartEvent != null ->
                    generator.writeObject(value.rawMessageStartEvent)
                value.rawMessageDeltaEvent != null ->
                    generator.writeObject(value.rawMessageDeltaEvent)
                value.rawMessageStopEvent != null ->
                    generator.writeObject(value.rawMessageStopEvent)
                value.rawContentBlockStartEvent != null ->
                    generator.writeObject(value.rawContentBlockStartEvent)
                value.rawContentBlockDeltaEvent != null ->
                    generator.writeObject(value.rawContentBlockDeltaEvent)
                value.rawContentBlockStopEvent != null ->
                    generator.writeObject(value.rawContentBlockStopEvent)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid RawMessageStreamEvent")
            }
        }
    }
}
