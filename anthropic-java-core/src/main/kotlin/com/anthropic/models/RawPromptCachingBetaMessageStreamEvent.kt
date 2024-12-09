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

@JsonDeserialize(using = RawPromptCachingBetaMessageStreamEvent.Deserializer::class)
@JsonSerialize(using = RawPromptCachingBetaMessageStreamEvent.Serializer::class)
class RawPromptCachingBetaMessageStreamEvent
private constructor(
    private val rawPromptCachingBetaMessageStartEvent: RawPromptCachingBetaMessageStartEvent? =
        null,
    private val rawMessageDeltaEvent: RawMessageDeltaEvent? = null,
    private val rawMessageStopEvent: RawMessageStopEvent? = null,
    private val rawContentBlockStartEvent: RawContentBlockStartEvent? = null,
    private val rawContentBlockDeltaEvent: RawContentBlockDeltaEvent? = null,
    private val rawContentBlockStopEvent: RawContentBlockStopEvent? = null,
    private val _json: JsonValue? = null,
) {

    private var validated: Boolean = false

    fun rawPromptCachingBetaMessageStartEvent(): Optional<RawPromptCachingBetaMessageStartEvent> =
        Optional.ofNullable(rawPromptCachingBetaMessageStartEvent)

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

    fun isRawPromptCachingBetaMessageStartEvent(): Boolean =
        rawPromptCachingBetaMessageStartEvent != null

    fun isRawMessageDeltaEvent(): Boolean = rawMessageDeltaEvent != null

    fun isRawMessageStopEvent(): Boolean = rawMessageStopEvent != null

    fun isRawContentBlockStartEvent(): Boolean = rawContentBlockStartEvent != null

    fun isRawContentBlockDeltaEvent(): Boolean = rawContentBlockDeltaEvent != null

    fun isRawContentBlockStopEvent(): Boolean = rawContentBlockStopEvent != null

    fun asRawPromptCachingBetaMessageStartEvent(): RawPromptCachingBetaMessageStartEvent =
        rawPromptCachingBetaMessageStartEvent.getOrThrow("rawPromptCachingBetaMessageStartEvent")

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
            rawPromptCachingBetaMessageStartEvent != null ->
                visitor.visitRawPromptCachingBetaMessageStartEvent(
                    rawPromptCachingBetaMessageStartEvent
                )
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

    fun validate(): RawPromptCachingBetaMessageStreamEvent = apply {
        if (!validated) {
            if (
                rawPromptCachingBetaMessageStartEvent == null &&
                    rawMessageDeltaEvent == null &&
                    rawMessageStopEvent == null &&
                    rawContentBlockStartEvent == null &&
                    rawContentBlockDeltaEvent == null &&
                    rawContentBlockStopEvent == null
            ) {
                throw AnthropicInvalidDataException(
                    "Unknown RawPromptCachingBetaMessageStreamEvent: $_json"
                )
            }
            rawPromptCachingBetaMessageStartEvent?.validate()
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

        return /* spotless:off */ other is RawPromptCachingBetaMessageStreamEvent && rawPromptCachingBetaMessageStartEvent == other.rawPromptCachingBetaMessageStartEvent && rawMessageDeltaEvent == other.rawMessageDeltaEvent && rawMessageStopEvent == other.rawMessageStopEvent && rawContentBlockStartEvent == other.rawContentBlockStartEvent && rawContentBlockDeltaEvent == other.rawContentBlockDeltaEvent && rawContentBlockStopEvent == other.rawContentBlockStopEvent /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(rawPromptCachingBetaMessageStartEvent, rawMessageDeltaEvent, rawMessageStopEvent, rawContentBlockStartEvent, rawContentBlockDeltaEvent, rawContentBlockStopEvent) /* spotless:on */

    override fun toString(): String =
        when {
            rawPromptCachingBetaMessageStartEvent != null ->
                "RawPromptCachingBetaMessageStreamEvent{rawPromptCachingBetaMessageStartEvent=$rawPromptCachingBetaMessageStartEvent}"
            rawMessageDeltaEvent != null ->
                "RawPromptCachingBetaMessageStreamEvent{rawMessageDeltaEvent=$rawMessageDeltaEvent}"
            rawMessageStopEvent != null ->
                "RawPromptCachingBetaMessageStreamEvent{rawMessageStopEvent=$rawMessageStopEvent}"
            rawContentBlockStartEvent != null ->
                "RawPromptCachingBetaMessageStreamEvent{rawContentBlockStartEvent=$rawContentBlockStartEvent}"
            rawContentBlockDeltaEvent != null ->
                "RawPromptCachingBetaMessageStreamEvent{rawContentBlockDeltaEvent=$rawContentBlockDeltaEvent}"
            rawContentBlockStopEvent != null ->
                "RawPromptCachingBetaMessageStreamEvent{rawContentBlockStopEvent=$rawContentBlockStopEvent}"
            _json != null -> "RawPromptCachingBetaMessageStreamEvent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid RawPromptCachingBetaMessageStreamEvent")
        }

    companion object {

        @JvmStatic
        fun ofRawPromptCachingBetaMessageStartEvent(
            rawPromptCachingBetaMessageStartEvent: RawPromptCachingBetaMessageStartEvent
        ) =
            RawPromptCachingBetaMessageStreamEvent(
                rawPromptCachingBetaMessageStartEvent = rawPromptCachingBetaMessageStartEvent
            )

        @JvmStatic
        fun ofRawMessageDeltaEvent(rawMessageDeltaEvent: RawMessageDeltaEvent) =
            RawPromptCachingBetaMessageStreamEvent(rawMessageDeltaEvent = rawMessageDeltaEvent)

        @JvmStatic
        fun ofRawMessageStopEvent(rawMessageStopEvent: RawMessageStopEvent) =
            RawPromptCachingBetaMessageStreamEvent(rawMessageStopEvent = rawMessageStopEvent)

        @JvmStatic
        fun ofRawContentBlockStartEvent(rawContentBlockStartEvent: RawContentBlockStartEvent) =
            RawPromptCachingBetaMessageStreamEvent(
                rawContentBlockStartEvent = rawContentBlockStartEvent
            )

        @JvmStatic
        fun ofRawContentBlockDeltaEvent(rawContentBlockDeltaEvent: RawContentBlockDeltaEvent) =
            RawPromptCachingBetaMessageStreamEvent(
                rawContentBlockDeltaEvent = rawContentBlockDeltaEvent
            )

        @JvmStatic
        fun ofRawContentBlockStopEvent(rawContentBlockStopEvent: RawContentBlockStopEvent) =
            RawPromptCachingBetaMessageStreamEvent(
                rawContentBlockStopEvent = rawContentBlockStopEvent
            )
    }

    interface Visitor<out T> {

        fun visitRawPromptCachingBetaMessageStartEvent(
            rawPromptCachingBetaMessageStartEvent: RawPromptCachingBetaMessageStartEvent
        ): T

        fun visitRawMessageDeltaEvent(rawMessageDeltaEvent: RawMessageDeltaEvent): T

        fun visitRawMessageStopEvent(rawMessageStopEvent: RawMessageStopEvent): T

        fun visitRawContentBlockStartEvent(rawContentBlockStartEvent: RawContentBlockStartEvent): T

        fun visitRawContentBlockDeltaEvent(rawContentBlockDeltaEvent: RawContentBlockDeltaEvent): T

        fun visitRawContentBlockStopEvent(rawContentBlockStopEvent: RawContentBlockStopEvent): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown RawPromptCachingBetaMessageStreamEvent: $json"
            )
        }
    }

    class Deserializer :
        BaseDeserializer<RawPromptCachingBetaMessageStreamEvent>(
            RawPromptCachingBetaMessageStreamEvent::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): RawPromptCachingBetaMessageStreamEvent {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "message_start" -> {
                    tryDeserialize(node, jacksonTypeRef<RawPromptCachingBetaMessageStartEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return RawPromptCachingBetaMessageStreamEvent(
                                rawPromptCachingBetaMessageStartEvent = it,
                                _json = json
                            )
                        }
                }
                "message_delta" -> {
                    tryDeserialize(node, jacksonTypeRef<RawMessageDeltaEvent>()) { it.validate() }
                        ?.let {
                            return RawPromptCachingBetaMessageStreamEvent(
                                rawMessageDeltaEvent = it,
                                _json = json
                            )
                        }
                }
                "message_stop" -> {
                    tryDeserialize(node, jacksonTypeRef<RawMessageStopEvent>()) { it.validate() }
                        ?.let {
                            return RawPromptCachingBetaMessageStreamEvent(
                                rawMessageStopEvent = it,
                                _json = json
                            )
                        }
                }
                "content_block_start" -> {
                    tryDeserialize(node, jacksonTypeRef<RawContentBlockStartEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return RawPromptCachingBetaMessageStreamEvent(
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
                            return RawPromptCachingBetaMessageStreamEvent(
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
                            return RawPromptCachingBetaMessageStreamEvent(
                                rawContentBlockStopEvent = it,
                                _json = json
                            )
                        }
                }
            }

            return RawPromptCachingBetaMessageStreamEvent(_json = json)
        }
    }

    class Serializer :
        BaseSerializer<RawPromptCachingBetaMessageStreamEvent>(
            RawPromptCachingBetaMessageStreamEvent::class
        ) {

        override fun serialize(
            value: RawPromptCachingBetaMessageStreamEvent,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.rawPromptCachingBetaMessageStartEvent != null ->
                    generator.writeObject(value.rawPromptCachingBetaMessageStartEvent)
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
                else ->
                    throw IllegalStateException("Invalid RawPromptCachingBetaMessageStreamEvent")
            }
        }
    }
}
