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
    private val betaRawMessageStartEvent: BetaRawMessageStartEvent? = null,
    private val betaRawMessageDeltaEvent: BetaRawMessageDeltaEvent? = null,
    private val betaRawMessageStopEvent: BetaRawMessageStopEvent? = null,
    private val betaRawContentBlockStartEvent: BetaRawContentBlockStartEvent? = null,
    private val betaRawContentBlockDeltaEvent: BetaRawContentBlockDeltaEvent? = null,
    private val betaRawContentBlockStopEvent: BetaRawContentBlockStopEvent? = null,
    private val _json: JsonValue? = null,
) {

    private var validated: Boolean = false

    fun betaRawMessageStartEvent(): Optional<BetaRawMessageStartEvent> =
        Optional.ofNullable(betaRawMessageStartEvent)

    fun betaRawMessageDeltaEvent(): Optional<BetaRawMessageDeltaEvent> =
        Optional.ofNullable(betaRawMessageDeltaEvent)

    fun betaRawMessageStopEvent(): Optional<BetaRawMessageStopEvent> =
        Optional.ofNullable(betaRawMessageStopEvent)

    fun betaRawContentBlockStartEvent(): Optional<BetaRawContentBlockStartEvent> =
        Optional.ofNullable(betaRawContentBlockStartEvent)

    fun betaRawContentBlockDeltaEvent(): Optional<BetaRawContentBlockDeltaEvent> =
        Optional.ofNullable(betaRawContentBlockDeltaEvent)

    fun betaRawContentBlockStopEvent(): Optional<BetaRawContentBlockStopEvent> =
        Optional.ofNullable(betaRawContentBlockStopEvent)

    fun isBetaRawMessageStartEvent(): Boolean = betaRawMessageStartEvent != null

    fun isBetaRawMessageDeltaEvent(): Boolean = betaRawMessageDeltaEvent != null

    fun isBetaRawMessageStopEvent(): Boolean = betaRawMessageStopEvent != null

    fun isBetaRawContentBlockStartEvent(): Boolean = betaRawContentBlockStartEvent != null

    fun isBetaRawContentBlockDeltaEvent(): Boolean = betaRawContentBlockDeltaEvent != null

    fun isBetaRawContentBlockStopEvent(): Boolean = betaRawContentBlockStopEvent != null

    fun asBetaRawMessageStartEvent(): BetaRawMessageStartEvent =
        betaRawMessageStartEvent.getOrThrow("betaRawMessageStartEvent")

    fun asBetaRawMessageDeltaEvent(): BetaRawMessageDeltaEvent =
        betaRawMessageDeltaEvent.getOrThrow("betaRawMessageDeltaEvent")

    fun asBetaRawMessageStopEvent(): BetaRawMessageStopEvent =
        betaRawMessageStopEvent.getOrThrow("betaRawMessageStopEvent")

    fun asBetaRawContentBlockStartEvent(): BetaRawContentBlockStartEvent =
        betaRawContentBlockStartEvent.getOrThrow("betaRawContentBlockStartEvent")

    fun asBetaRawContentBlockDeltaEvent(): BetaRawContentBlockDeltaEvent =
        betaRawContentBlockDeltaEvent.getOrThrow("betaRawContentBlockDeltaEvent")

    fun asBetaRawContentBlockStopEvent(): BetaRawContentBlockStopEvent =
        betaRawContentBlockStopEvent.getOrThrow("betaRawContentBlockStopEvent")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            betaRawMessageStartEvent != null ->
                visitor.visitBetaRawMessageStartEvent(betaRawMessageStartEvent)
            betaRawMessageDeltaEvent != null ->
                visitor.visitBetaRawMessageDeltaEvent(betaRawMessageDeltaEvent)
            betaRawMessageStopEvent != null ->
                visitor.visitBetaRawMessageStopEvent(betaRawMessageStopEvent)
            betaRawContentBlockStartEvent != null ->
                visitor.visitBetaRawContentBlockStartEvent(betaRawContentBlockStartEvent)
            betaRawContentBlockDeltaEvent != null ->
                visitor.visitBetaRawContentBlockDeltaEvent(betaRawContentBlockDeltaEvent)
            betaRawContentBlockStopEvent != null ->
                visitor.visitBetaRawContentBlockStopEvent(betaRawContentBlockStopEvent)
            else -> visitor.unknown(_json)
        }
    }

    fun validate(): BetaRawMessageStreamEvent = apply {
        if (!validated) {
            if (
                betaRawMessageStartEvent == null &&
                    betaRawMessageDeltaEvent == null &&
                    betaRawMessageStopEvent == null &&
                    betaRawContentBlockStartEvent == null &&
                    betaRawContentBlockDeltaEvent == null &&
                    betaRawContentBlockStopEvent == null
            ) {
                throw AnthropicInvalidDataException("Unknown BetaRawMessageStreamEvent: $_json")
            }
            betaRawMessageStartEvent?.validate()
            betaRawMessageDeltaEvent?.validate()
            betaRawMessageStopEvent?.validate()
            betaRawContentBlockStartEvent?.validate()
            betaRawContentBlockDeltaEvent?.validate()
            betaRawContentBlockStopEvent?.validate()
            validated = true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaRawMessageStreamEvent && betaRawMessageStartEvent == other.betaRawMessageStartEvent && betaRawMessageDeltaEvent == other.betaRawMessageDeltaEvent && betaRawMessageStopEvent == other.betaRawMessageStopEvent && betaRawContentBlockStartEvent == other.betaRawContentBlockStartEvent && betaRawContentBlockDeltaEvent == other.betaRawContentBlockDeltaEvent && betaRawContentBlockStopEvent == other.betaRawContentBlockStopEvent /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaRawMessageStartEvent, betaRawMessageDeltaEvent, betaRawMessageStopEvent, betaRawContentBlockStartEvent, betaRawContentBlockDeltaEvent, betaRawContentBlockStopEvent) /* spotless:on */

    override fun toString(): String =
        when {
            betaRawMessageStartEvent != null ->
                "BetaRawMessageStreamEvent{betaRawMessageStartEvent=$betaRawMessageStartEvent}"
            betaRawMessageDeltaEvent != null ->
                "BetaRawMessageStreamEvent{betaRawMessageDeltaEvent=$betaRawMessageDeltaEvent}"
            betaRawMessageStopEvent != null ->
                "BetaRawMessageStreamEvent{betaRawMessageStopEvent=$betaRawMessageStopEvent}"
            betaRawContentBlockStartEvent != null ->
                "BetaRawMessageStreamEvent{betaRawContentBlockStartEvent=$betaRawContentBlockStartEvent}"
            betaRawContentBlockDeltaEvent != null ->
                "BetaRawMessageStreamEvent{betaRawContentBlockDeltaEvent=$betaRawContentBlockDeltaEvent}"
            betaRawContentBlockStopEvent != null ->
                "BetaRawMessageStreamEvent{betaRawContentBlockStopEvent=$betaRawContentBlockStopEvent}"
            _json != null -> "BetaRawMessageStreamEvent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaRawMessageStreamEvent")
        }

    companion object {

        @JvmStatic
        fun ofBetaRawMessageStartEvent(betaRawMessageStartEvent: BetaRawMessageStartEvent) =
            BetaRawMessageStreamEvent(betaRawMessageStartEvent = betaRawMessageStartEvent)

        @JvmStatic
        fun ofBetaRawMessageDeltaEvent(betaRawMessageDeltaEvent: BetaRawMessageDeltaEvent) =
            BetaRawMessageStreamEvent(betaRawMessageDeltaEvent = betaRawMessageDeltaEvent)

        @JvmStatic
        fun ofBetaRawMessageStopEvent(betaRawMessageStopEvent: BetaRawMessageStopEvent) =
            BetaRawMessageStreamEvent(betaRawMessageStopEvent = betaRawMessageStopEvent)

        @JvmStatic
        fun ofBetaRawContentBlockStartEvent(
            betaRawContentBlockStartEvent: BetaRawContentBlockStartEvent
        ) = BetaRawMessageStreamEvent(betaRawContentBlockStartEvent = betaRawContentBlockStartEvent)

        @JvmStatic
        fun ofBetaRawContentBlockDeltaEvent(
            betaRawContentBlockDeltaEvent: BetaRawContentBlockDeltaEvent
        ) = BetaRawMessageStreamEvent(betaRawContentBlockDeltaEvent = betaRawContentBlockDeltaEvent)

        @JvmStatic
        fun ofBetaRawContentBlockStopEvent(
            betaRawContentBlockStopEvent: BetaRawContentBlockStopEvent
        ) = BetaRawMessageStreamEvent(betaRawContentBlockStopEvent = betaRawContentBlockStopEvent)
    }

    interface Visitor<out T> {

        fun visitBetaRawMessageStartEvent(betaRawMessageStartEvent: BetaRawMessageStartEvent): T

        fun visitBetaRawMessageDeltaEvent(betaRawMessageDeltaEvent: BetaRawMessageDeltaEvent): T

        fun visitBetaRawMessageStopEvent(betaRawMessageStopEvent: BetaRawMessageStopEvent): T

        fun visitBetaRawContentBlockStartEvent(
            betaRawContentBlockStartEvent: BetaRawContentBlockStartEvent
        ): T

        fun visitBetaRawContentBlockDeltaEvent(
            betaRawContentBlockDeltaEvent: BetaRawContentBlockDeltaEvent
        ): T

        fun visitBetaRawContentBlockStopEvent(
            betaRawContentBlockStopEvent: BetaRawContentBlockStopEvent
        ): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaRawMessageStreamEvent: $json")
        }
    }

    class Deserializer :
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
                            return BetaRawMessageStreamEvent(
                                betaRawMessageStartEvent = it,
                                _json = json
                            )
                        }
                }
                "message_delta" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRawMessageDeltaEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaRawMessageStreamEvent(
                                betaRawMessageDeltaEvent = it,
                                _json = json
                            )
                        }
                }
                "message_stop" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRawMessageStopEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaRawMessageStreamEvent(
                                betaRawMessageStopEvent = it,
                                _json = json
                            )
                        }
                }
                "content_block_start" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRawContentBlockStartEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaRawMessageStreamEvent(
                                betaRawContentBlockStartEvent = it,
                                _json = json
                            )
                        }
                }
                "content_block_delta" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRawContentBlockDeltaEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaRawMessageStreamEvent(
                                betaRawContentBlockDeltaEvent = it,
                                _json = json
                            )
                        }
                }
                "content_block_stop" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRawContentBlockStopEvent>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaRawMessageStreamEvent(
                                betaRawContentBlockStopEvent = it,
                                _json = json
                            )
                        }
                }
            }

            return BetaRawMessageStreamEvent(_json = json)
        }
    }

    class Serializer : BaseSerializer<BetaRawMessageStreamEvent>(BetaRawMessageStreamEvent::class) {

        override fun serialize(
            value: BetaRawMessageStreamEvent,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.betaRawMessageStartEvent != null ->
                    generator.writeObject(value.betaRawMessageStartEvent)
                value.betaRawMessageDeltaEvent != null ->
                    generator.writeObject(value.betaRawMessageDeltaEvent)
                value.betaRawMessageStopEvent != null ->
                    generator.writeObject(value.betaRawMessageStopEvent)
                value.betaRawContentBlockStartEvent != null ->
                    generator.writeObject(value.betaRawContentBlockStartEvent)
                value.betaRawContentBlockDeltaEvent != null ->
                    generator.writeObject(value.betaRawContentBlockDeltaEvent)
                value.betaRawContentBlockStopEvent != null ->
                    generator.writeObject(value.betaRawContentBlockStopEvent)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaRawMessageStreamEvent")
            }
        }
    }
}
