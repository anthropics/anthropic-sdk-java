package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

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

@JsonDeserialize(using = BetaClaudeCodeRbacGroupStatus.Deserializer::class)
@JsonSerialize(using = BetaClaudeCodeRbacGroupStatus.Serializer::class)
class BetaClaudeCodeRbacGroupStatus
private constructor(
    private val active: BetaClaudeCodeRbacGroupStatusActive? = null,
    private val deleted: BetaClaudeCodeRbacGroupStatusDeleted? = null,
    private val unknown: BetaClaudeCodeRbacGroupStatusUnknown? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            active != null -> Type.ACTIVE
            deleted != null -> Type.DELETED
            unknown != null -> Type.UNKNOWN
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun active(): Optional<BetaClaudeCodeRbacGroupStatusActive> = Optional.ofNullable(active)

    fun deleted(): Optional<BetaClaudeCodeRbacGroupStatusDeleted> = Optional.ofNullable(deleted)

    fun unknown(): Optional<BetaClaudeCodeRbacGroupStatusUnknown> = Optional.ofNullable(unknown)

    fun isActive(): Boolean = active != null

    fun isDeleted(): Boolean = deleted != null

    fun isUnknown(): Boolean = unknown != null

    fun asActive(): BetaClaudeCodeRbacGroupStatusActive = active.getOrThrow("active")

    fun asDeleted(): BetaClaudeCodeRbacGroupStatusDeleted = deleted.getOrThrow("deleted")

    fun asUnknown(): BetaClaudeCodeRbacGroupStatusUnknown = unknown.getOrThrow("unknown")

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
     * Optional<String> result = betaClaudeCodeRbacGroupStatus.accept(new BetaClaudeCodeRbacGroupStatus.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitActive(BetaClaudeCodeRbacGroupStatusActive active) {
     *         return Optional.of(active.toString());
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
            active != null -> visitor.visitActive(active)
            deleted != null -> visitor.visitDeleted(deleted)
            unknown != null -> visitor.visitUnknown(unknown)
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
    fun validate(): BetaClaudeCodeRbacGroupStatus = apply {
        if (validated) {
            return@apply
        }

        when {
            active != null -> active.validate()
            deleted != null -> deleted.validate()
            unknown != null -> unknown.validate()
            else ->
                throw AnthropicInvalidDataException("Unknown BetaClaudeCodeRbacGroupStatus: $_json")
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
            active != null -> active.validity()
            deleted != null -> deleted.validity()
            unknown != null -> unknown.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeCodeRbacGroupStatus &&
            active == other.active &&
            deleted == other.deleted &&
            unknown == other.unknown
    }

    override fun hashCode(): Int = Objects.hash(active, deleted, unknown)

    override fun toString(): String =
        when {
            active != null -> "BetaClaudeCodeRbacGroupStatus{active=$active}"
            deleted != null -> "BetaClaudeCodeRbacGroupStatus{deleted=$deleted}"
            unknown != null -> "BetaClaudeCodeRbacGroupStatus{unknown=$unknown}"
            _json != null -> "BetaClaudeCodeRbacGroupStatus{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaClaudeCodeRbacGroupStatus")
        }

    companion object {

        @JvmStatic
        fun ofActive(active: BetaClaudeCodeRbacGroupStatusActive) =
            BetaClaudeCodeRbacGroupStatus(active = active)

        @JvmStatic
        fun ofDeleted(deleted: BetaClaudeCodeRbacGroupStatusDeleted) =
            BetaClaudeCodeRbacGroupStatus(deleted = deleted)

        @JvmStatic
        fun ofUnknown(unknown: BetaClaudeCodeRbacGroupStatusUnknown) =
            BetaClaudeCodeRbacGroupStatus(unknown = unknown)
    }

    /**
     * An interface that defines how to map each variant of [BetaClaudeCodeRbacGroupStatus] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        fun visitActive(active: BetaClaudeCodeRbacGroupStatusActive): T

        fun visitDeleted(deleted: BetaClaudeCodeRbacGroupStatusDeleted): T

        fun visitUnknown(unknown: BetaClaudeCodeRbacGroupStatusUnknown): T

        /**
         * Maps an unknown variant of [BetaClaudeCodeRbacGroupStatus] to a value of type [T].
         *
         * An instance of [BetaClaudeCodeRbacGroupStatus] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaClaudeCodeRbacGroupStatus: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaClaudeCodeRbacGroupStatus>(BetaClaudeCodeRbacGroupStatus::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaClaudeCodeRbacGroupStatus {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "active" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeCodeRbacGroupStatusActive>(),
                        )
                        ?.let { BetaClaudeCodeRbacGroupStatus(active = it, _json = json) }
                        ?: BetaClaudeCodeRbacGroupStatus(_json = json)
                }
                "deleted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeCodeRbacGroupStatusDeleted>(),
                        )
                        ?.let { BetaClaudeCodeRbacGroupStatus(deleted = it, _json = json) }
                        ?: BetaClaudeCodeRbacGroupStatus(_json = json)
                }
                "unknown" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeCodeRbacGroupStatusUnknown>(),
                        )
                        ?.let { BetaClaudeCodeRbacGroupStatus(unknown = it, _json = json) }
                        ?: BetaClaudeCodeRbacGroupStatus(_json = json)
                }
            }

            return BetaClaudeCodeRbacGroupStatus(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaClaudeCodeRbacGroupStatus>(BetaClaudeCodeRbacGroupStatus::class) {

        override fun serialize(
            value: BetaClaudeCodeRbacGroupStatus,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.active != null -> generator.writeObject(value.active)
                value.deleted != null -> generator.writeObject(value.deleted)
                value.unknown != null -> generator.writeObject(value.unknown)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaClaudeCodeRbacGroupStatus")
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

            @JvmField val ACTIVE = Type(JsonField.of("active"))

            @JvmField val DELETED = Type(JsonField.of("deleted"))

            @JvmField val UNKNOWN = Type(JsonField.of("unknown"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "active" -> ACTIVE
                    "deleted" -> DELETED
                    "unknown" -> UNKNOWN
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            ACTIVE,
            DELETED,
            UNKNOWN,
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
            ACTIVE,
            DELETED,
            UNKNOWN,
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
                ACTIVE -> Value.ACTIVE
                DELETED -> Value.DELETED
                UNKNOWN -> Value.UNKNOWN
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
                ACTIVE -> Known.ACTIVE
                DELETED -> Known.DELETED
                UNKNOWN -> Known.UNKNOWN
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
