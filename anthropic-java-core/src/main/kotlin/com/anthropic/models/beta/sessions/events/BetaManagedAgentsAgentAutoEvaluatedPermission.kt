package com.anthropic.models.beta.sessions.events

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
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * The server's per-invocation judgement under the auto permission policy. Its type always equals
 * the event's top-level evaluated_permission. Open union: clients must tolerate unknown variants.
 */
@JsonDeserialize(using = BetaManagedAgentsAgentAutoEvaluatedPermission.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsAgentAutoEvaluatedPermission.Serializer::class)
class BetaManagedAgentsAgentAutoEvaluatedPermission
private constructor(
    private val allow: BetaManagedAgentsAgentAutoEvaluatedPermissionAllow? = null,
    private val ask: BetaManagedAgentsAgentAutoEvaluatedPermissionAsk? = null,
    private val deny: BetaManagedAgentsAgentAutoEvaluatedPermissionDeny? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitAllow(
                    allow: BetaManagedAgentsAgentAutoEvaluatedPermissionAllow
                ): Type = Type.ALLOW

                override fun visitAsk(ask: BetaManagedAgentsAgentAutoEvaluatedPermissionAsk): Type =
                    Type.ASK

                override fun visitDeny(
                    deny: BetaManagedAgentsAgentAutoEvaluatedPermissionDeny
                ): Type = Type.DENY

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun reasonCode(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitAllow(
                    allow: BetaManagedAgentsAgentAutoEvaluatedPermissionAllow
                ): Optional<String> = Optional.empty()

                override fun visitAsk(
                    ask: BetaManagedAgentsAgentAutoEvaluatedPermissionAsk
                ): Optional<String> = Optional.of(ask.reasonCode())

                override fun visitDeny(
                    deny: BetaManagedAgentsAgentAutoEvaluatedPermissionDeny
                ): Optional<String> = Optional.of(deny.reasonCode())

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("reason_code").asKnown()
            }
        )

    /** The server judged the invocation safe to execute without client approval. */
    fun allow(): Optional<BetaManagedAgentsAgentAutoEvaluatedPermissionAllow> =
        Optional.ofNullable(allow)

    /** The server reached no judgement; the invocation is held for client approval. */
    fun ask(): Optional<BetaManagedAgentsAgentAutoEvaluatedPermissionAsk> = Optional.ofNullable(ask)

    /**
     * The server judged the invocation high-risk; it does not execute and a synthetic error tool
     * result is appended.
     */
    fun deny(): Optional<BetaManagedAgentsAgentAutoEvaluatedPermissionDeny> =
        Optional.ofNullable(deny)

    fun isAllow(): Boolean = allow != null

    fun isAsk(): Boolean = ask != null

    fun isDeny(): Boolean = deny != null

    /** The server judged the invocation safe to execute without client approval. */
    fun asAllow(): BetaManagedAgentsAgentAutoEvaluatedPermissionAllow = allow.getOrThrow("allow")

    /** The server reached no judgement; the invocation is held for client approval. */
    fun asAsk(): BetaManagedAgentsAgentAutoEvaluatedPermissionAsk = ask.getOrThrow("ask")

    /**
     * The server judged the invocation high-risk; it does not execute and a synthetic error tool
     * result is appended.
     */
    fun asDeny(): BetaManagedAgentsAgentAutoEvaluatedPermissionDeny = deny.getOrThrow("deny")

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
     * Optional<String> result = betaManagedAgentsAgentAutoEvaluatedPermission.accept(new BetaManagedAgentsAgentAutoEvaluatedPermission.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAllow(BetaManagedAgentsAgentAutoEvaluatedPermissionAllow allow) {
     *         return Optional.of(allow.toString());
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
            allow != null -> visitor.visitAllow(allow)
            ask != null -> visitor.visitAsk(ask)
            deny != null -> visitor.visitDeny(deny)
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
    fun validate(): BetaManagedAgentsAgentAutoEvaluatedPermission = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitAllow(allow: BetaManagedAgentsAgentAutoEvaluatedPermissionAllow) {
                    allow.validate()
                }

                override fun visitAsk(ask: BetaManagedAgentsAgentAutoEvaluatedPermissionAsk) {
                    ask.validate()
                }

                override fun visitDeny(deny: BetaManagedAgentsAgentAutoEvaluatedPermissionDeny) {
                    deny.validate()
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
                override fun visitAllow(allow: BetaManagedAgentsAgentAutoEvaluatedPermissionAllow) =
                    allow.validity()

                override fun visitAsk(ask: BetaManagedAgentsAgentAutoEvaluatedPermissionAsk) =
                    ask.validity()

                override fun visitDeny(deny: BetaManagedAgentsAgentAutoEvaluatedPermissionDeny) =
                    deny.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentAutoEvaluatedPermission &&
            allow == other.allow &&
            ask == other.ask &&
            deny == other.deny
    }

    override fun hashCode(): Int = Objects.hash(allow, ask, deny)

    override fun toString(): String =
        when {
            allow != null -> "BetaManagedAgentsAgentAutoEvaluatedPermission{allow=$allow}"
            ask != null -> "BetaManagedAgentsAgentAutoEvaluatedPermission{ask=$ask}"
            deny != null -> "BetaManagedAgentsAgentAutoEvaluatedPermission{deny=$deny}"
            _json != null -> "BetaManagedAgentsAgentAutoEvaluatedPermission{_unknown=$_json}"
            else ->
                throw IllegalStateException("Invalid BetaManagedAgentsAgentAutoEvaluatedPermission")
        }

    companion object {

        /** The server judged the invocation safe to execute without client approval. */
        @JvmStatic
        fun ofAllow(allow: BetaManagedAgentsAgentAutoEvaluatedPermissionAllow) =
            BetaManagedAgentsAgentAutoEvaluatedPermission(allow = allow)

        /** The server reached no judgement; the invocation is held for client approval. */
        @JvmStatic
        fun ofAsk(ask: BetaManagedAgentsAgentAutoEvaluatedPermissionAsk) =
            BetaManagedAgentsAgentAutoEvaluatedPermission(ask = ask)

        /**
         * Returns an immutable instance of [BetaManagedAgentsAgentAutoEvaluatedPermission] whose
         * [ofAsk] variant is built from the given required [reasonCode].
         */
        @JvmStatic
        fun ofAsk(reasonCode: String) =
            ofAsk(BetaManagedAgentsAgentAutoEvaluatedPermissionAsk.of(reasonCode))

        /**
         * The server judged the invocation high-risk; it does not execute and a synthetic error
         * tool result is appended.
         */
        @JvmStatic
        fun ofDeny(deny: BetaManagedAgentsAgentAutoEvaluatedPermissionDeny) =
            BetaManagedAgentsAgentAutoEvaluatedPermission(deny = deny)

        /**
         * Returns an immutable instance of [BetaManagedAgentsAgentAutoEvaluatedPermission] whose
         * [ofDeny] variant is built from the given required [reasonCode].
         */
        @JvmStatic
        fun ofDeny(reasonCode: String) =
            ofDeny(BetaManagedAgentsAgentAutoEvaluatedPermissionDeny.of(reasonCode))
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsAgentAutoEvaluatedPermission] to a value of type [T].
     */
    interface Visitor<out T> {

        /** The server judged the invocation safe to execute without client approval. */
        fun visitAllow(allow: BetaManagedAgentsAgentAutoEvaluatedPermissionAllow): T

        /** The server reached no judgement; the invocation is held for client approval. */
        fun visitAsk(ask: BetaManagedAgentsAgentAutoEvaluatedPermissionAsk): T

        /**
         * The server judged the invocation high-risk; it does not execute and a synthetic error
         * tool result is appended.
         */
        fun visitDeny(deny: BetaManagedAgentsAgentAutoEvaluatedPermissionDeny): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsAgentAutoEvaluatedPermission] to a value of
         * type [T].
         *
         * An instance of [BetaManagedAgentsAgentAutoEvaluatedPermission] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsAgentAutoEvaluatedPermission: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsAgentAutoEvaluatedPermission>(
            BetaManagedAgentsAgentAutoEvaluatedPermission::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsAgentAutoEvaluatedPermission {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "allow" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermissionAllow>(),
                        )
                        ?.let {
                            BetaManagedAgentsAgentAutoEvaluatedPermission(allow = it, _json = json)
                        } ?: BetaManagedAgentsAgentAutoEvaluatedPermission(_json = json)
                }
                "ask" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermissionAsk>(),
                        )
                        ?.let {
                            BetaManagedAgentsAgentAutoEvaluatedPermission(ask = it, _json = json)
                        } ?: BetaManagedAgentsAgentAutoEvaluatedPermission(_json = json)
                }
                "deny" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermissionDeny>(),
                        )
                        ?.let {
                            BetaManagedAgentsAgentAutoEvaluatedPermission(deny = it, _json = json)
                        } ?: BetaManagedAgentsAgentAutoEvaluatedPermission(_json = json)
                }
            }

            return BetaManagedAgentsAgentAutoEvaluatedPermission(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsAgentAutoEvaluatedPermission>(
            BetaManagedAgentsAgentAutoEvaluatedPermission::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsAgentAutoEvaluatedPermission,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.allow != null -> generator.writeObject(value.allow)
                value.ask != null -> generator.writeObject(value.ask)
                value.deny != null -> generator.writeObject(value.deny)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaManagedAgentsAgentAutoEvaluatedPermission"
                    )
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

            @JvmField val ALLOW = of("allow")

            @JvmField val ASK = of("ask")

            @JvmField val DENY = of("deny")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            ALLOW,
            ASK,
            DENY,
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
            ALLOW,
            ASK,
            DENY,
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
                ALLOW -> Value.ALLOW
                ASK -> Value.ASK
                DENY -> Value.DENY
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
                ALLOW -> Known.ALLOW
                ASK -> Known.ASK
                DENY -> Known.DENY
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
