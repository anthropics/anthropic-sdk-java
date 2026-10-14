package com.anthropic.models.beta.organization.telemetrydestinations

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
 * Which of the organization's workspaces a telemetry destination covers.
 *
 * Several destinations can cover the same workspace. Each of them receives the workspace's spans
 * while it is enabled.
 *
 * The workspace list of an `include` or `exclude` destination isn't part of the destination object.
 * List the destination's workspaces to read it. Add or remove one workspace at a time, or replace
 * the whole list with an update. A list holds at most 1,000 workspaces.
 */
@JsonDeserialize(using = BetaTelemetryDestinationWorkspaces.Deserializer::class)
@JsonSerialize(using = BetaTelemetryDestinationWorkspaces.Serializer::class)
class BetaTelemetryDestinationWorkspaces
private constructor(
    private val all: BetaTelemetryDestinationWorkspacesAll? = null,
    private val include: BetaTelemetryDestinationWorkspacesInclude? = null,
    private val exclude: BetaTelemetryDestinationWorkspacesExclude? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            all != null -> Type.ALL
            include != null -> Type.INCLUDE
            exclude != null -> Type.EXCLUDE
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /**
     * The destination covers every workspace of the organization, including the default workspace,
     * archived workspaces, and workspaces created later. It doesn't have a list.
     */
    fun all(): Optional<BetaTelemetryDestinationWorkspacesAll> = Optional.ofNullable(all)

    /** The destination covers the workspaces on its list, and no others. */
    fun include(): Optional<BetaTelemetryDestinationWorkspacesInclude> =
        Optional.ofNullable(include)

    /**
     * The destination covers every workspace of the organization except the workspaces on its list.
     * A workspace created later is covered until it is added to the list.
     */
    fun exclude(): Optional<BetaTelemetryDestinationWorkspacesExclude> =
        Optional.ofNullable(exclude)

    fun isAll(): Boolean = all != null

    fun isInclude(): Boolean = include != null

    fun isExclude(): Boolean = exclude != null

    /**
     * The destination covers every workspace of the organization, including the default workspace,
     * archived workspaces, and workspaces created later. It doesn't have a list.
     */
    fun asAll(): BetaTelemetryDestinationWorkspacesAll = all.getOrThrow("all")

    /** The destination covers the workspaces on its list, and no others. */
    fun asInclude(): BetaTelemetryDestinationWorkspacesInclude = include.getOrThrow("include")

    /**
     * The destination covers every workspace of the organization except the workspaces on its list.
     * A workspace created later is covered until it is added to the list.
     */
    fun asExclude(): BetaTelemetryDestinationWorkspacesExclude = exclude.getOrThrow("exclude")

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
     * Optional<String> result = betaTelemetryDestinationWorkspaces.accept(new BetaTelemetryDestinationWorkspaces.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAll(BetaTelemetryDestinationWorkspacesAll all) {
     *         return Optional.of(all.toString());
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
            all != null -> visitor.visitAll(all)
            include != null -> visitor.visitInclude(include)
            exclude != null -> visitor.visitExclude(exclude)
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
    fun validate(): BetaTelemetryDestinationWorkspaces = apply {
        if (validated) {
            return@apply
        }

        when {
            all != null -> all.validate()
            include != null -> include.validate()
            exclude != null -> exclude.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaTelemetryDestinationWorkspaces: $_json"
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
            all != null -> all.validity()
            include != null -> include.validity()
            exclude != null -> exclude.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTelemetryDestinationWorkspaces &&
            all == other.all &&
            include == other.include &&
            exclude == other.exclude
    }

    override fun hashCode(): Int = Objects.hash(all, include, exclude)

    override fun toString(): String =
        when {
            all != null -> "BetaTelemetryDestinationWorkspaces{all=$all}"
            include != null -> "BetaTelemetryDestinationWorkspaces{include=$include}"
            exclude != null -> "BetaTelemetryDestinationWorkspaces{exclude=$exclude}"
            _json != null -> "BetaTelemetryDestinationWorkspaces{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTelemetryDestinationWorkspaces")
        }

    companion object {

        /**
         * The destination covers every workspace of the organization, including the default
         * workspace, archived workspaces, and workspaces created later. It doesn't have a list.
         */
        @JvmStatic
        fun ofAll(all: BetaTelemetryDestinationWorkspacesAll) =
            BetaTelemetryDestinationWorkspaces(all = all)

        /** The destination covers the workspaces on its list, and no others. */
        @JvmStatic
        fun ofInclude(include: BetaTelemetryDestinationWorkspacesInclude) =
            BetaTelemetryDestinationWorkspaces(include = include)

        /**
         * The destination covers every workspace of the organization except the workspaces on its
         * list. A workspace created later is covered until it is added to the list.
         */
        @JvmStatic
        fun ofExclude(exclude: BetaTelemetryDestinationWorkspacesExclude) =
            BetaTelemetryDestinationWorkspaces(exclude = exclude)
    }

    /**
     * An interface that defines how to map each variant of [BetaTelemetryDestinationWorkspaces] to
     * a value of type [T].
     */
    interface Visitor<out T> {

        /**
         * The destination covers every workspace of the organization, including the default
         * workspace, archived workspaces, and workspaces created later. It doesn't have a list.
         */
        fun visitAll(all: BetaTelemetryDestinationWorkspacesAll): T

        /** The destination covers the workspaces on its list, and no others. */
        fun visitInclude(include: BetaTelemetryDestinationWorkspacesInclude): T

        /**
         * The destination covers every workspace of the organization except the workspaces on its
         * list. A workspace created later is covered until it is added to the list.
         */
        fun visitExclude(exclude: BetaTelemetryDestinationWorkspacesExclude): T

        /**
         * Maps an unknown variant of [BetaTelemetryDestinationWorkspaces] to a value of type [T].
         *
         * An instance of [BetaTelemetryDestinationWorkspaces] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaTelemetryDestinationWorkspaces: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaTelemetryDestinationWorkspaces>(
            BetaTelemetryDestinationWorkspaces::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaTelemetryDestinationWorkspaces {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "all" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationWorkspacesAll>(),
                        )
                        ?.let { BetaTelemetryDestinationWorkspaces(all = it, _json = json) }
                        ?: BetaTelemetryDestinationWorkspaces(_json = json)
                }
                "include" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationWorkspacesInclude>(),
                        )
                        ?.let { BetaTelemetryDestinationWorkspaces(include = it, _json = json) }
                        ?: BetaTelemetryDestinationWorkspaces(_json = json)
                }
                "exclude" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationWorkspacesExclude>(),
                        )
                        ?.let { BetaTelemetryDestinationWorkspaces(exclude = it, _json = json) }
                        ?: BetaTelemetryDestinationWorkspaces(_json = json)
                }
            }

            return BetaTelemetryDestinationWorkspaces(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaTelemetryDestinationWorkspaces>(
            BetaTelemetryDestinationWorkspaces::class
        ) {

        override fun serialize(
            value: BetaTelemetryDestinationWorkspaces,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.all != null -> generator.writeObject(value.all)
                value.include != null -> generator.writeObject(value.include)
                value.exclude != null -> generator.writeObject(value.exclude)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaTelemetryDestinationWorkspaces")
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

            @JvmField val ALL = Type(JsonField.of("all"))

            @JvmField val INCLUDE = Type(JsonField.of("include"))

            @JvmField val EXCLUDE = Type(JsonField.of("exclude"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "all" -> ALL
                    "include" -> INCLUDE
                    "exclude" -> EXCLUDE
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            ALL,
            INCLUDE,
            EXCLUDE,
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
            ALL,
            INCLUDE,
            EXCLUDE,
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
                ALL -> Value.ALL
                INCLUDE -> Value.INCLUDE
                EXCLUDE -> Value.EXCLUDE
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
                ALL -> Known.ALL
                INCLUDE -> Known.INCLUDE
                EXCLUDE -> Known.EXCLUDE
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
