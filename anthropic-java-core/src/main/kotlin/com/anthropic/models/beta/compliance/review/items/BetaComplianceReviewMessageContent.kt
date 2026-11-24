package com.anthropic.models.beta.compliance.review.items

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

/** The stored copy of one call. */
@JsonDeserialize(using = BetaComplianceReviewMessageContent.Deserializer::class)
@JsonSerialize(using = BetaComplianceReviewMessageContent.Serializer::class)
class BetaComplianceReviewMessageContent
private constructor(
    private val createMessage: BetaComplianceReviewCreateMessageContent? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            createMessage != null -> Type.CREATE_MESSAGE
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** A request to create a message, and its response. */
    fun createMessage(): Optional<BetaComplianceReviewCreateMessageContent> =
        Optional.ofNullable(createMessage)

    fun isCreateMessage(): Boolean = createMessage != null

    /** A request to create a message, and its response. */
    fun asCreateMessage(): BetaComplianceReviewCreateMessageContent =
        createMessage.getOrThrow("createMessage")

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
     * Optional<String> result = betaComplianceReviewMessageContent.accept(new BetaComplianceReviewMessageContent.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitCreateMessage(BetaComplianceReviewCreateMessageContent createMessage) {
     *         return Optional.of(createMessage.toString());
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
            createMessage != null -> visitor.visitCreateMessage(createMessage)
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
    fun validate(): BetaComplianceReviewMessageContent = apply {
        if (validated) {
            return@apply
        }

        when {
            createMessage != null -> createMessage.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaComplianceReviewMessageContent: $_json"
                )
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
            createMessage != null -> createMessage.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewMessageContent && createMessage == other.createMessage
    }

    override fun hashCode(): Int = Objects.hash(createMessage)

    override fun toString(): String =
        when {
            createMessage != null ->
                "BetaComplianceReviewMessageContent{createMessage=$createMessage}"
            _json != null -> "BetaComplianceReviewMessageContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaComplianceReviewMessageContent")
        }

    companion object {

        /** A request to create a message, and its response. */
        @JvmStatic
        fun ofCreateMessage(createMessage: BetaComplianceReviewCreateMessageContent) =
            BetaComplianceReviewMessageContent(createMessage = createMessage)
    }

    /**
     * An interface that defines how to map each variant of [BetaComplianceReviewMessageContent] to
     * a value of type [T].
     */
    interface Visitor<out T> {

        /** A request to create a message, and its response. */
        fun visitCreateMessage(createMessage: BetaComplianceReviewCreateMessageContent): T

        /**
         * Maps an unknown variant of [BetaComplianceReviewMessageContent] to a value of type [T].
         *
         * An instance of [BetaComplianceReviewMessageContent] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaComplianceReviewMessageContent: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaComplianceReviewMessageContent>(
            BetaComplianceReviewMessageContent::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaComplianceReviewMessageContent {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            if (type == "create_message") {
                return tryDeserialize(
                        node,
                        jacksonTypeRef<BetaComplianceReviewCreateMessageContent>(),
                    )
                    ?.let { BetaComplianceReviewMessageContent(createMessage = it, _json = json) }
                    ?: BetaComplianceReviewMessageContent(_json = json)
            }

            return BetaComplianceReviewMessageContent(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaComplianceReviewMessageContent>(
            BetaComplianceReviewMessageContent::class
        ) {

        override fun serialize(
            value: BetaComplianceReviewMessageContent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.createMessage != null -> generator.writeObject(value.createMessage)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaComplianceReviewMessageContent")
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

            @JvmField val CREATE_MESSAGE = Type(JsonField.of("create_message"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "create_message" -> CREATE_MESSAGE
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            CREATE_MESSAGE
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
            CREATE_MESSAGE,
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
                CREATE_MESSAGE -> Value.CREATE_MESSAGE
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
                CREATE_MESSAGE -> Known.CREATE_MESSAGE
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
