package com.anthropic.models.messages

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

/** Where to act: either a viewport coordinate or an element reference. */
@JsonDeserialize(using = BrowserClickTarget.Deserializer::class)
@JsonSerialize(using = BrowserClickTarget.Serializer::class)
class BrowserClickTarget
private constructor(
    private val coordinate: BrowserCoordinateTarget? = null,
    private val ref: BrowserRefTarget? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            coordinate != null -> Type.COORDINATE
            ref != null -> Type.REF
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /**
     * A point in the browser viewport, in viewport pixels (the same frame as a full-viewport
     * screenshot).
     */
    fun coordinate(): Optional<BrowserCoordinateTarget> = Optional.ofNullable(coordinate)

    /**
     * An element on the page, identified by a reference from a prior `read_page` or `find` result.
     * References are scoped to the tab that produced them and become stale after navigation or a
     * major re-render.
     */
    fun ref(): Optional<BrowserRefTarget> = Optional.ofNullable(ref)

    fun isCoordinate(): Boolean = coordinate != null

    fun isRef(): Boolean = ref != null

    /**
     * A point in the browser viewport, in viewport pixels (the same frame as a full-viewport
     * screenshot).
     */
    fun asCoordinate(): BrowserCoordinateTarget = coordinate.getOrThrow("coordinate")

    /**
     * An element on the page, identified by a reference from a prior `read_page` or `find` result.
     * References are scoped to the tab that produced them and become stale after navigation or a
     * major re-render.
     */
    fun asRef(): BrowserRefTarget = ref.getOrThrow("ref")

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
     * Optional<String> result = browserClickTarget.accept(new BrowserClickTarget.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitCoordinate(BrowserCoordinateTarget coordinate) {
     *         return Optional.of(coordinate.toString());
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
            coordinate != null -> visitor.visitCoordinate(coordinate)
            ref != null -> visitor.visitRef(ref)
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
    fun validate(): BrowserClickTarget = apply {
        if (validated) {
            return@apply
        }

        when {
            coordinate != null -> coordinate.validate()
            ref != null -> ref.validate()
            else -> throw AnthropicInvalidDataException("Unknown BrowserClickTarget: $_json")
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
            coordinate != null -> coordinate.validity()
            ref != null -> ref.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BrowserClickTarget && coordinate == other.coordinate && ref == other.ref
    }

    override fun hashCode(): Int = Objects.hash(coordinate, ref)

    override fun toString(): String =
        when {
            coordinate != null -> "BrowserClickTarget{coordinate=$coordinate}"
            ref != null -> "BrowserClickTarget{ref=$ref}"
            _json != null -> "BrowserClickTarget{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BrowserClickTarget")
        }

    companion object {

        /**
         * A point in the browser viewport, in viewport pixels (the same frame as a full-viewport
         * screenshot).
         */
        @JvmStatic
        fun ofCoordinate(coordinate: BrowserCoordinateTarget) =
            BrowserClickTarget(coordinate = coordinate)

        /**
         * An element on the page, identified by a reference from a prior `read_page` or `find`
         * result. References are scoped to the tab that produced them and become stale after
         * navigation or a major re-render.
         */
        @JvmStatic fun ofRef(ref: BrowserRefTarget) = BrowserClickTarget(ref = ref)

        /**
         * Returns an immutable instance of [BrowserClickTarget] whose [ofRef] variant is built from
         * the given required [ref].
         */
        @JvmStatic fun ofRef(ref: String) = ofRef(BrowserRefTarget.of(ref))
    }

    /**
     * An interface that defines how to map each variant of [BrowserClickTarget] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        /**
         * A point in the browser viewport, in viewport pixels (the same frame as a full-viewport
         * screenshot).
         */
        fun visitCoordinate(coordinate: BrowserCoordinateTarget): T

        /**
         * An element on the page, identified by a reference from a prior `read_page` or `find`
         * result. References are scoped to the tab that produced them and become stale after
         * navigation or a major re-render.
         */
        fun visitRef(ref: BrowserRefTarget): T

        /**
         * Maps an unknown variant of [BrowserClickTarget] to a value of type [T].
         *
         * An instance of [BrowserClickTarget] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BrowserClickTarget: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BrowserClickTarget>(BrowserClickTarget::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BrowserClickTarget {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "coordinate" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserCoordinateTarget>())?.let {
                        BrowserClickTarget(coordinate = it, _json = json)
                    } ?: BrowserClickTarget(_json = json)
                }
                "ref" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserRefTarget>())?.let {
                        BrowserClickTarget(ref = it, _json = json)
                    } ?: BrowserClickTarget(_json = json)
                }
            }

            return BrowserClickTarget(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BrowserClickTarget>(BrowserClickTarget::class) {

        override fun serialize(
            value: BrowserClickTarget,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.coordinate != null -> generator.writeObject(value.coordinate)
                value.ref != null -> generator.writeObject(value.ref)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BrowserClickTarget")
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

            @JvmField val COORDINATE = Type(JsonField.of("coordinate"))

            @JvmField val REF = Type(JsonField.of("ref"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "coordinate" -> COORDINATE
                    "ref" -> REF
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            COORDINATE,
            REF,
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
            COORDINATE,
            REF,
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
                COORDINATE -> Value.COORDINATE
                REF -> Value.REF
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
                COORDINATE -> Known.COORDINATE
                REF -> Known.REF
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
