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

/**
 * Server-held conversation state (a Thread). `{"type": "create"}` asks the server to hold this
 * conversation's state under the response's `id`; `{"type": "continue", "previous_message_id":
 * ...}` resumes from a previous response so only the new turn's messages are sent. The response
 * shape is unchanged.
 */
@JsonDeserialize(using = BetaMessageThreadParam.Deserializer::class)
@JsonSerialize(using = BetaMessageThreadParam.Serializer::class)
class BetaMessageThreadParam
private constructor(
    private val create: BetaThreadCreateParams? = null,
    private val continue_: BetaThreadContinueParams? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            create != null -> Type.CREATE
            continue_ != null -> Type.CONTINUE
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** Start holding conversation state under this response's ``id``. */
    fun create(): Optional<BetaThreadCreateParams> = Optional.ofNullable(create)

    /** Resume from the response named by ``previous_message_id``. */
    fun continue_(): Optional<BetaThreadContinueParams> = Optional.ofNullable(continue_)

    fun isCreate(): Boolean = create != null

    fun isContinue(): Boolean = continue_ != null

    /** Start holding conversation state under this response's ``id``. */
    fun asCreate(): BetaThreadCreateParams = create.getOrThrow("create")

    /** Resume from the response named by ``previous_message_id``. */
    fun asContinue(): BetaThreadContinueParams = continue_.getOrThrow("continue_")

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
     * Optional<String> result = betaMessageThreadParam.accept(new BetaMessageThreadParam.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitCreate(BetaThreadCreateParams create) {
     *         return Optional.of(create.toString());
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
            create != null -> visitor.visitCreate(create)
            continue_ != null -> visitor.visitContinue(continue_)
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
    fun validate(): BetaMessageThreadParam = apply {
        if (validated) {
            return@apply
        }

        when {
            create != null -> create.validate()
            continue_ != null -> continue_.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaMessageThreadParam: $_json")
        }
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
        when {
            create != null -> create.validity()
            continue_ != null -> continue_.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMessageThreadParam &&
            create == other.create &&
            continue_ == other.continue_
    }

    override fun hashCode(): Int = Objects.hash(create, continue_)

    override fun toString(): String =
        when {
            create != null -> "BetaMessageThreadParam{create=$create}"
            continue_ != null -> "BetaMessageThreadParam{continue_=$continue_}"
            _json != null -> "BetaMessageThreadParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaMessageThreadParam")
        }

    companion object {

        /** Start holding conversation state under this response's ``id``. */
        @JvmStatic
        fun ofCreate(create: BetaThreadCreateParams) = BetaMessageThreadParam(create = create)

        /** Resume from the response named by ``previous_message_id``. */
        @JvmStatic
        fun ofContinue(continue_: BetaThreadContinueParams) =
            BetaMessageThreadParam(continue_ = continue_)

        /**
         * Returns an immutable instance of [BetaMessageThreadParam] whose [ofContinue] variant is
         * built from the given required [previousMessageId].
         */
        @JvmStatic
        fun ofContinue(previousMessageId: String) =
            ofContinue(BetaThreadContinueParams.of(previousMessageId))
    }

    /**
     * An interface that defines how to map each variant of [BetaMessageThreadParam] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        /** Start holding conversation state under this response's ``id``. */
        fun visitCreate(create: BetaThreadCreateParams): T

        /** Resume from the response named by ``previous_message_id``. */
        fun visitContinue(continue_: BetaThreadContinueParams): T

        /**
         * Maps an unknown variant of [BetaMessageThreadParam] to a value of type [T].
         *
         * An instance of [BetaMessageThreadParam] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaMessageThreadParam: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaMessageThreadParam>(BetaMessageThreadParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaMessageThreadParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "create" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaThreadCreateParams>())?.let {
                        BetaMessageThreadParam(create = it, _json = json)
                    } ?: BetaMessageThreadParam(_json = json)
                }
                "continue" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaThreadContinueParams>())?.let {
                        BetaMessageThreadParam(continue_ = it, _json = json)
                    } ?: BetaMessageThreadParam(_json = json)
                }
            }

            return BetaMessageThreadParam(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaMessageThreadParam>(BetaMessageThreadParam::class) {

        override fun serialize(
            value: BetaMessageThreadParam,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.create != null -> generator.writeObject(value.create)
                value.continue_ != null -> generator.writeObject(value.continue_)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaMessageThreadParam")
            }
        }
    }

    class Type private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val CREATE = Type(JsonField.of("create"))

            @JvmField val CONTINUE = Type(JsonField.of("continue"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "create" -> CREATE
                    "continue" -> CONTINUE
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            CREATE,
            CONTINUE,
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
            CREATE,
            CONTINUE,
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
                CREATE -> Value.CREATE
                CONTINUE -> Value.CONTINUE
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
                CREATE -> Known.CREATE
                CONTINUE -> Known.CONTINUE
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
