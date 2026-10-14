package com.anthropic.models.beta.organization.telemetrydestinations

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
 * Which of the organization's workspaces a telemetry destination covers, as a create or update
 * request sets it.
 */
@JsonDeserialize(using = BetaTelemetryDestinationWorkspacesParams.Deserializer::class)
@JsonSerialize(using = BetaTelemetryDestinationWorkspacesParams.Serializer::class)
class BetaTelemetryDestinationWorkspacesParams
private constructor(
    private val all: BetaTelemetryDestinationWorkspacesAllParams? = null,
    private val include: BetaTelemetryDestinationWorkspacesIncludeParams? = null,
    private val exclude: BetaTelemetryDestinationWorkspacesExcludeParams? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            all != null -> Type.ALL
            include != null -> Type.INCLUDE
            exclude != null -> Type.EXCLUDE
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun workspaceIds(): Optional<List<String>> =
        when {
            all != null -> Optional.empty()
            include != null -> include.workspaceIds()
            exclude != null -> exclude.workspaceIds()
            else -> _json.getProperty<List<String>>("workspace_ids").asKnown()
        }

    /**
     * Cover every workspace of the organization. Doesn't take `workspace_ids`.
     *
     * An update that sets `all` empties the destination's workspace list.
     */
    fun all(): Optional<BetaTelemetryDestinationWorkspacesAllParams> = Optional.ofNullable(all)

    /** Cover only the workspaces on the list. */
    fun include(): Optional<BetaTelemetryDestinationWorkspacesIncludeParams> =
        Optional.ofNullable(include)

    /** Cover every workspace of the organization except the workspaces on the list. */
    fun exclude(): Optional<BetaTelemetryDestinationWorkspacesExcludeParams> =
        Optional.ofNullable(exclude)

    fun isAll(): Boolean = all != null

    fun isInclude(): Boolean = include != null

    fun isExclude(): Boolean = exclude != null

    /**
     * Cover every workspace of the organization. Doesn't take `workspace_ids`.
     *
     * An update that sets `all` empties the destination's workspace list.
     */
    fun asAll(): BetaTelemetryDestinationWorkspacesAllParams = all.getOrThrow("all")

    /** Cover only the workspaces on the list. */
    fun asInclude(): BetaTelemetryDestinationWorkspacesIncludeParams = include.getOrThrow("include")

    /** Cover every workspace of the organization except the workspaces on the list. */
    fun asExclude(): BetaTelemetryDestinationWorkspacesExcludeParams = exclude.getOrThrow("exclude")

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
     * Optional<String> result = betaTelemetryDestinationWorkspacesParams.accept(new BetaTelemetryDestinationWorkspacesParams.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAll(BetaTelemetryDestinationWorkspacesAllParams all) {
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
    fun validate(): BetaTelemetryDestinationWorkspacesParams = apply {
        if (validated) {
            return@apply
        }

        when {
            all != null -> all.validate()
            include != null -> include.validate()
            exclude != null -> exclude.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaTelemetryDestinationWorkspacesParams: $_json"
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

        return other is BetaTelemetryDestinationWorkspacesParams &&
            all == other.all &&
            include == other.include &&
            exclude == other.exclude
    }

    override fun hashCode(): Int = Objects.hash(all, include, exclude)

    override fun toString(): String =
        when {
            all != null -> "BetaTelemetryDestinationWorkspacesParams{all=$all}"
            include != null -> "BetaTelemetryDestinationWorkspacesParams{include=$include}"
            exclude != null -> "BetaTelemetryDestinationWorkspacesParams{exclude=$exclude}"
            _json != null -> "BetaTelemetryDestinationWorkspacesParams{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTelemetryDestinationWorkspacesParams")
        }

    companion object {

        /**
         * Cover every workspace of the organization. Doesn't take `workspace_ids`.
         *
         * An update that sets `all` empties the destination's workspace list.
         */
        @JvmStatic
        fun ofAll(all: BetaTelemetryDestinationWorkspacesAllParams) =
            BetaTelemetryDestinationWorkspacesParams(all = all)

        /** Cover only the workspaces on the list. */
        @JvmStatic
        fun ofInclude(include: BetaTelemetryDestinationWorkspacesIncludeParams) =
            BetaTelemetryDestinationWorkspacesParams(include = include)

        /** Cover every workspace of the organization except the workspaces on the list. */
        @JvmStatic
        fun ofExclude(exclude: BetaTelemetryDestinationWorkspacesExcludeParams) =
            BetaTelemetryDestinationWorkspacesParams(exclude = exclude)
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaTelemetryDestinationWorkspacesParams] to a value of type [T].
     */
    interface Visitor<out T> {

        /**
         * Cover every workspace of the organization. Doesn't take `workspace_ids`.
         *
         * An update that sets `all` empties the destination's workspace list.
         */
        fun visitAll(all: BetaTelemetryDestinationWorkspacesAllParams): T

        /** Cover only the workspaces on the list. */
        fun visitInclude(include: BetaTelemetryDestinationWorkspacesIncludeParams): T

        /** Cover every workspace of the organization except the workspaces on the list. */
        fun visitExclude(exclude: BetaTelemetryDestinationWorkspacesExcludeParams): T

        /**
         * Maps an unknown variant of [BetaTelemetryDestinationWorkspacesParams] to a value of type
         * [T].
         *
         * An instance of [BetaTelemetryDestinationWorkspacesParams] can contain an unknown variant
         * if it was deserialized from data that doesn't match any known variant. For example, if
         * the SDK is on an older version than the API, then the API may respond with new variants
         * that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaTelemetryDestinationWorkspacesParams: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaTelemetryDestinationWorkspacesParams>(
            BetaTelemetryDestinationWorkspacesParams::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaTelemetryDestinationWorkspacesParams {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "all" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationWorkspacesAllParams>(),
                        )
                        ?.let { BetaTelemetryDestinationWorkspacesParams(all = it, _json = json) }
                        ?: BetaTelemetryDestinationWorkspacesParams(_json = json)
                }
                "include" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationWorkspacesIncludeParams>(),
                        )
                        ?.let {
                            BetaTelemetryDestinationWorkspacesParams(include = it, _json = json)
                        } ?: BetaTelemetryDestinationWorkspacesParams(_json = json)
                }
                "exclude" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationWorkspacesExcludeParams>(),
                        )
                        ?.let {
                            BetaTelemetryDestinationWorkspacesParams(exclude = it, _json = json)
                        } ?: BetaTelemetryDestinationWorkspacesParams(_json = json)
                }
            }

            return BetaTelemetryDestinationWorkspacesParams(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaTelemetryDestinationWorkspacesParams>(
            BetaTelemetryDestinationWorkspacesParams::class
        ) {

        override fun serialize(
            value: BetaTelemetryDestinationWorkspacesParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.all != null -> generator.writeObject(value.all)
                value.include != null -> generator.writeObject(value.include)
                value.exclude != null -> generator.writeObject(value.exclude)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException("Invalid BetaTelemetryDestinationWorkspacesParams")
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
