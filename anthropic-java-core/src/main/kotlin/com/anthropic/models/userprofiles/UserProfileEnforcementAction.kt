package com.anthropic.models.userprofiles

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A limit Anthropic has placed on the entity this profile represents and not yet resolved,
 * discriminated by `type`: `restriction` or `ban`. More types may be added; read a `type` you do
 * not recognize as `restriction`.
 */
@JsonDeserialize(using = UserProfileEnforcementAction.Deserializer::class)
@JsonSerialize(using = UserProfileEnforcementAction.Serializer::class)
class UserProfileEnforcementAction
private constructor(
    private val restriction: UserProfileEnforcementActionRestriction? = null,
    private val ban: UserProfileEnforcementActionBan? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            restriction != null -> Type.RESTRICTION
            ban != null -> Type.BAN
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun id(): String =
        when {
            restriction != null -> restriction.id()
            ban != null -> ban.id()
            else -> _json.getProperty<String>("id").getRequired("id")
        }

    fun action(): Optional<UserProfileEnforcementActionNextStep> =
        when {
            restriction != null -> restriction.action()
            ban != null -> ban.action()
            else -> _json.getProperty<UserProfileEnforcementActionNextStep>("action").asKnown()
        }

    fun expiresAt(): Optional<OffsetDateTime> =
        when {
            restriction != null -> restriction.expiresAt()
            ban != null -> ban.expiresAt()
            else -> _json.getProperty<OffsetDateTime>("expires_at").asKnown()
        }

    fun openedAt(): OffsetDateTime =
        when {
            restriction != null -> restriction.openedAt()
            ban != null -> ban.openedAt()
            else -> _json.getProperty<OffsetDateTime>("opened_at").getRequired("opened_at")
        }

    fun reason(): UserProfileEnforcementActionReason =
        when {
            restriction != null -> restriction.reason()
            ban != null -> ban.reason()
            else ->
                _json
                    .getProperty<UserProfileEnforcementActionReason>("reason")
                    .getRequired("reason")
        }

    /**
     * Anthropic has limited how it serves the end user until the action is resolved. The end user
     * may still be served in part.
     */
    fun restriction(): Optional<UserProfileEnforcementActionRestriction> =
        Optional.ofNullable(restriction)

    /**
     * Anthropic has stopped serving the end user where the ban applies and does not plan to serve
     * them there again.
     */
    fun ban(): Optional<UserProfileEnforcementActionBan> = Optional.ofNullable(ban)

    fun isRestriction(): Boolean = restriction != null

    fun isBan(): Boolean = ban != null

    /**
     * Anthropic has limited how it serves the end user until the action is resolved. The end user
     * may still be served in part.
     */
    fun asRestriction(): UserProfileEnforcementActionRestriction =
        restriction.getOrThrow("restriction")

    /**
     * Anthropic has stopped serving the end user where the ban applies and does not plan to serve
     * them there again.
     */
    fun asBan(): UserProfileEnforcementActionBan = ban.getOrThrow("ban")

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
     * Optional<String> result = userProfileEnforcementAction.accept(new UserProfileEnforcementAction.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitRestriction(UserProfileEnforcementActionRestriction restriction) {
     *         return Optional.of(restriction.toString());
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
            restriction != null -> visitor.visitRestriction(restriction)
            ban != null -> visitor.visitBan(ban)
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
    fun validate(): UserProfileEnforcementAction = apply {
        if (validated) {
            return@apply
        }

        when {
            restriction != null -> restriction.validate()
            ban != null -> ban.validate()
            else ->
                throw AnthropicInvalidDataException("Unknown UserProfileEnforcementAction: $_json")
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
            restriction != null -> restriction.validity()
            ban != null -> ban.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfileEnforcementAction &&
            restriction == other.restriction &&
            ban == other.ban
    }

    override fun hashCode(): Int = Objects.hash(restriction, ban)

    override fun toString(): String =
        when {
            restriction != null -> "UserProfileEnforcementAction{restriction=$restriction}"
            ban != null -> "UserProfileEnforcementAction{ban=$ban}"
            _json != null -> "UserProfileEnforcementAction{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid UserProfileEnforcementAction")
        }

    companion object {

        /**
         * Anthropic has limited how it serves the end user until the action is resolved. The end
         * user may still be served in part.
         */
        @JvmStatic
        fun ofRestriction(restriction: UserProfileEnforcementActionRestriction) =
            UserProfileEnforcementAction(restriction = restriction)

        /**
         * Anthropic has stopped serving the end user where the ban applies and does not plan to
         * serve them there again.
         */
        @JvmStatic
        fun ofBan(ban: UserProfileEnforcementActionBan) = UserProfileEnforcementAction(ban = ban)
    }

    /**
     * An interface that defines how to map each variant of [UserProfileEnforcementAction] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /**
         * Anthropic has limited how it serves the end user until the action is resolved. The end
         * user may still be served in part.
         */
        fun visitRestriction(restriction: UserProfileEnforcementActionRestriction): T

        /**
         * Anthropic has stopped serving the end user where the ban applies and does not plan to
         * serve them there again.
         */
        fun visitBan(ban: UserProfileEnforcementActionBan): T

        /**
         * Maps an unknown variant of [UserProfileEnforcementAction] to a value of type [T].
         *
         * An instance of [UserProfileEnforcementAction] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown UserProfileEnforcementAction: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<UserProfileEnforcementAction>(UserProfileEnforcementAction::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): UserProfileEnforcementAction {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "restriction" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<UserProfileEnforcementActionRestriction>(),
                        )
                        ?.let { UserProfileEnforcementAction(restriction = it, _json = json) }
                        ?: UserProfileEnforcementAction(_json = json)
                }
                "ban" -> {
                    return tryDeserialize(node, jacksonTypeRef<UserProfileEnforcementActionBan>())
                        ?.let { UserProfileEnforcementAction(ban = it, _json = json) }
                        ?: UserProfileEnforcementAction(_json = json)
                }
            }

            return UserProfileEnforcementAction(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<UserProfileEnforcementAction>(UserProfileEnforcementAction::class) {

        override fun serialize(
            value: UserProfileEnforcementAction,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.restriction != null -> generator.writeObject(value.restriction)
                value.ban != null -> generator.writeObject(value.ban)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid UserProfileEnforcementAction")
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

            @JvmField val RESTRICTION = Type(JsonField.of("restriction"))

            @JvmField val BAN = Type(JsonField.of("ban"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "restriction" -> RESTRICTION
                    "ban" -> BAN
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            RESTRICTION,
            BAN,
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
            RESTRICTION,
            BAN,
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
                RESTRICTION -> Value.RESTRICTION
                BAN -> Value.BAN
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
                RESTRICTION -> Known.RESTRICTION
                BAN -> Known.BAN
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
