// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
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

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitMessageStart(messageStart: BetaRawMessageStartEvent): Type =
                    Type.MESSAGE_START

                override fun visitMessageDelta(messageDelta: BetaRawMessageDeltaEvent): Type =
                    Type.MESSAGE_DELTA

                override fun visitMessageStop(messageStop: BetaRawMessageStopEvent): Type =
                    Type.MESSAGE_STOP

                override fun visitContentBlockStart(
                    contentBlockStart: BetaRawContentBlockStartEvent
                ): Type = Type.CONTENT_BLOCK_START

                override fun visitContentBlockDelta(
                    contentBlockDelta: BetaRawContentBlockDeltaEvent
                ): Type = Type.CONTENT_BLOCK_DELTA

                override fun visitContentBlockStop(
                    contentBlockStop: BetaRawContentBlockStopEvent
                ): Type = Type.CONTENT_BLOCK_STOP

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun index(): Optional<Long> =
        accept(
            object : Visitor<Optional<Long>> {
                override fun visitMessageStart(
                    messageStart: BetaRawMessageStartEvent
                ): Optional<Long> = Optional.empty()

                override fun visitMessageDelta(
                    messageDelta: BetaRawMessageDeltaEvent
                ): Optional<Long> = Optional.empty()

                override fun visitMessageStop(
                    messageStop: BetaRawMessageStopEvent
                ): Optional<Long> = Optional.empty()

                override fun visitContentBlockStart(
                    contentBlockStart: BetaRawContentBlockStartEvent
                ): Optional<Long> = Optional.of(contentBlockStart.index())

                override fun visitContentBlockDelta(
                    contentBlockDelta: BetaRawContentBlockDeltaEvent
                ): Optional<Long> = Optional.of(contentBlockDelta.index())

                override fun visitContentBlockStop(
                    contentBlockStop: BetaRawContentBlockStopEvent
                ): Optional<Long> = Optional.of(contentBlockStop.index())
            }
        )

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

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaRawMessageStreamEvent.accept(new BetaRawMessageStreamEvent.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitMessageStart(BetaRawMessageStartEvent messageStart) {
     *         return Optional.of(messageStart.toString());
     *     }
     *
     *     // ...
     *
     *     @Override
     *     public Optional<String> unknown(JsonValue json) {
     *         // Or inspect the `json`.
     *         return Optional.empty();
     *     }
     * });
     * ```
     *
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
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

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
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

        /**
         * Returns an immutable instance of [BetaRawMessageStreamEvent] whose [ofMessageStart]
         * variant is built from the given required [message].
         */
        @JvmStatic
        fun ofMessageStart(message: BetaMessage) =
            ofMessageStart(BetaRawMessageStartEvent.of(message))

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

        /**
         * Returns an immutable instance of [BetaRawMessageStreamEvent] whose [ofContentBlockStop]
         * variant is built from the given required [index].
         */
        @JvmStatic
        fun ofContentBlockStop(index: Long) =
            ofContentBlockStop(BetaRawContentBlockStopEvent.of(index))
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

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val MESSAGE_START = of("message_start")

            @JvmField val MESSAGE_DELTA = of("message_delta")

            @JvmField val MESSAGE_STOP = of("message_stop")

            @JvmField val CONTENT_BLOCK_START = of("content_block_start")

            @JvmField val CONTENT_BLOCK_DELTA = of("content_block_delta")

            @JvmField val CONTENT_BLOCK_STOP = of("content_block_stop")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonValue): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            MESSAGE_START,
            MESSAGE_DELTA,
            MESSAGE_STOP,
            CONTENT_BLOCK_START,
            CONTENT_BLOCK_DELTA,
            CONTENT_BLOCK_STOP,
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            MESSAGE_START,
            MESSAGE_DELTA,
            MESSAGE_STOP,
            CONTENT_BLOCK_START,
            CONTENT_BLOCK_DELTA,
            CONTENT_BLOCK_STOP,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                MESSAGE_START -> Value.MESSAGE_START
                MESSAGE_DELTA -> Value.MESSAGE_DELTA
                MESSAGE_STOP -> Value.MESSAGE_STOP
                CONTENT_BLOCK_START -> Value.CONTENT_BLOCK_START
                CONTENT_BLOCK_DELTA -> Value.CONTENT_BLOCK_DELTA
                CONTENT_BLOCK_STOP -> Value.CONTENT_BLOCK_STOP
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                MESSAGE_START -> Known.MESSAGE_START
                MESSAGE_DELTA -> Known.MESSAGE_DELTA
                MESSAGE_STOP -> Known.MESSAGE_STOP
                CONTENT_BLOCK_START -> Known.CONTENT_BLOCK_START
                CONTENT_BLOCK_DELTA -> Known.CONTENT_BLOCK_DELTA
                CONTENT_BLOCK_STOP -> Known.CONTENT_BLOCK_STOP
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Type = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
