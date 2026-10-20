package com.anthropic.models.beta.userprofiles.actionurls

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

/** The action that the person who opens the URL takes, discriminated by `type`. */
@JsonDeserialize(using = BetaUserProfileAction.Deserializer::class)
@JsonSerialize(using = BetaUserProfileAction.Serializer::class)
class BetaUserProfileAction
private constructor(
    private val enrollEndUser: BetaUserProfileEnrollEndUserAction? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            enrollEndUser != null -> Type.ENROLL_END_USER
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** The end-user that the profile represents enrolls in a program. */
    fun enrollEndUser(): Optional<BetaUserProfileEnrollEndUserAction> =
        Optional.ofNullable(enrollEndUser)

    fun isEnrollEndUser(): Boolean = enrollEndUser != null

    /** The end-user that the profile represents enrolls in a program. */
    fun asEnrollEndUser(): BetaUserProfileEnrollEndUserAction =
        enrollEndUser.getOrThrow("enrollEndUser")

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
     * Optional<String> result = betaUserProfileAction.accept(new BetaUserProfileAction.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitEnrollEndUser(BetaUserProfileEnrollEndUserAction enrollEndUser) {
     *         return Optional.of(enrollEndUser.toString());
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
            enrollEndUser != null -> visitor.visitEnrollEndUser(enrollEndUser)
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
    fun validate(): BetaUserProfileAction = apply {
        if (validated) {
            return@apply
        }

        when {
            enrollEndUser != null -> enrollEndUser.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaUserProfileAction: $_json")
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
            enrollEndUser != null -> enrollEndUser.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaUserProfileAction && enrollEndUser == other.enrollEndUser
    }

    override fun hashCode(): Int = Objects.hash(enrollEndUser)

    override fun toString(): String =
        when {
            enrollEndUser != null -> "BetaUserProfileAction{enrollEndUser=$enrollEndUser}"
            _json != null -> "BetaUserProfileAction{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaUserProfileAction")
        }

    companion object {

        /** The end-user that the profile represents enrolls in a program. */
        @JvmStatic
        fun ofEnrollEndUser(enrollEndUser: BetaUserProfileEnrollEndUserAction) =
            BetaUserProfileAction(enrollEndUser = enrollEndUser)
    }

    /**
     * An interface that defines how to map each variant of [BetaUserProfileAction] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        /** The end-user that the profile represents enrolls in a program. */
        fun visitEnrollEndUser(enrollEndUser: BetaUserProfileEnrollEndUserAction): T

        /**
         * Maps an unknown variant of [BetaUserProfileAction] to a value of type [T].
         *
         * An instance of [BetaUserProfileAction] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaUserProfileAction: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaUserProfileAction>(BetaUserProfileAction::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaUserProfileAction {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            if (type == "enroll_end_user") {
                return tryDeserialize(node, jacksonTypeRef<BetaUserProfileEnrollEndUserAction>())
                    ?.let { BetaUserProfileAction(enrollEndUser = it, _json = json) }
                    ?: BetaUserProfileAction(_json = json)
            }

            return BetaUserProfileAction(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaUserProfileAction>(BetaUserProfileAction::class) {

        override fun serialize(
            value: BetaUserProfileAction,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.enrollEndUser != null -> generator.writeObject(value.enrollEndUser)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaUserProfileAction")
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

            @JvmField val ENROLL_END_USER = Type(JsonField.of("enroll_end_user"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "enroll_end_user" -> ENROLL_END_USER
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            ENROLL_END_USER
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
            ENROLL_END_USER,
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
                ENROLL_END_USER -> Value.ENROLL_END_USER
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
                ENROLL_END_USER -> Known.ENROLL_END_USER
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
