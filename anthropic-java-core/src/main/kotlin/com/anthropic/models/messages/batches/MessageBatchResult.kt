// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.ErrorResponse
import com.anthropic.models.messages.Message
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

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitSucceeded(succeeded: MessageBatchSucceededResult): Type =
                    Type.SUCCEEDED

                override fun visitErrored(errored: MessageBatchErroredResult): Type = Type.ERRORED

                override fun visitCanceled(canceled: MessageBatchCanceledResult): Type =
                    Type.CANCELED

                override fun visitExpired(expired: MessageBatchExpiredResult): Type = Type.EXPIRED

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

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
     * Optional<String> result = messageBatchResult.accept(new MessageBatchResult.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitSucceeded(MessageBatchSucceededResult succeeded) {
     *         return Optional.of(succeeded.toString());
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
            succeeded != null -> visitor.visitSucceeded(succeeded)
            errored != null -> visitor.visitErrored(errored)
            canceled != null -> visitor.visitCanceled(canceled)
            expired != null -> visitor.visitExpired(expired)
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
                override fun visitSucceeded(succeeded: MessageBatchSucceededResult) =
                    succeeded.validity()

                override fun visitErrored(errored: MessageBatchErroredResult) = errored.validity()

                override fun visitCanceled(canceled: MessageBatchCanceledResult) =
                    canceled.validity()

                override fun visitExpired(expired: MessageBatchExpiredResult) = expired.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MessageBatchResult &&
            succeeded == other.succeeded &&
            errored == other.errored &&
            canceled == other.canceled &&
            expired == other.expired
    }

    override fun hashCode(): Int = Objects.hash(succeeded, errored, canceled, expired)

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

        /**
         * Returns an immutable instance of [MessageBatchResult] whose [ofSucceeded] variant is
         * built from the given required [message].
         */
        @JvmStatic
        fun ofSucceeded(message: Message) = ofSucceeded(MessageBatchSucceededResult.of(message))

        @JvmStatic
        fun ofErrored(errored: MessageBatchErroredResult) = MessageBatchResult(errored = errored)

        /**
         * Returns an immutable instance of [MessageBatchResult] whose [ofErrored] variant is built
         * from the given required [error].
         */
        @JvmStatic
        fun ofErrored(error: ErrorResponse) = ofErrored(MessageBatchErroredResult.of(error))

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
                    return tryDeserialize(node, jacksonTypeRef<MessageBatchSucceededResult>())
                        ?.let { MessageBatchResult(succeeded = it, _json = json) }
                        ?: MessageBatchResult(_json = json)
                }
                "errored" -> {
                    return tryDeserialize(node, jacksonTypeRef<MessageBatchErroredResult>())?.let {
                        MessageBatchResult(errored = it, _json = json)
                    } ?: MessageBatchResult(_json = json)
                }
                "canceled" -> {
                    return tryDeserialize(node, jacksonTypeRef<MessageBatchCanceledResult>())?.let {
                        MessageBatchResult(canceled = it, _json = json)
                    } ?: MessageBatchResult(_json = json)
                }
                "expired" -> {
                    return tryDeserialize(node, jacksonTypeRef<MessageBatchExpiredResult>())?.let {
                        MessageBatchResult(expired = it, _json = json)
                    } ?: MessageBatchResult(_json = json)
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

            @JvmField val SUCCEEDED = of("succeeded")

            @JvmField val ERRORED = of("errored")

            @JvmField val CANCELED = of("canceled")

            @JvmField val EXPIRED = of("expired")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonValue): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SUCCEEDED,
            ERRORED,
            CANCELED,
            EXPIRED,
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
            SUCCEEDED,
            ERRORED,
            CANCELED,
            EXPIRED,
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
                SUCCEEDED -> Value.SUCCEEDED
                ERRORED -> Value.ERRORED
                CANCELED -> Value.CANCELED
                EXPIRED -> Value.EXPIRED
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
                SUCCEEDED -> Known.SUCCEEDED
                ERRORED -> Known.ERRORED
                CANCELED -> Known.CANCELED
                EXPIRED -> Known.EXPIRED
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
