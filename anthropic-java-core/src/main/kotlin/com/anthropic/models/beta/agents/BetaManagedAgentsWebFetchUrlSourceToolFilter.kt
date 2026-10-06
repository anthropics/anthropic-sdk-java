package com.anthropic.models.beta.agents

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

/** Which tools' results contribute URLs that may be fetched. */
@JsonDeserialize(using = BetaManagedAgentsWebFetchUrlSourceToolFilter.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsWebFetchUrlSourceToolFilter.Serializer::class)
class BetaManagedAgentsWebFetchUrlSourceToolFilter
private constructor(
    private val all: BetaManagedAgentsWebFetchUrlSourceAll? = null,
    private val none: BetaManagedAgentsWebFetchUrlSourceNone? = null,
    private val only: BetaManagedAgentsWebFetchUrlSourceOnly? = null,
    private val except: BetaManagedAgentsWebFetchUrlSourceExcept? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            all != null -> Type.ALL
            none != null -> Type.NONE
            only != null -> Type.ONLY
            except != null -> Type.EXCEPT
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun tools(): Optional<List<BetaManagedAgentsWebFetchUrlSourceToolReference>> =
        when {
            all != null -> Optional.empty()
            none != null -> Optional.empty()
            only != null -> Optional.of(only.tools())
            except != null -> Optional.of(except.tools())
            else ->
                _json
                    .getProperty<List<BetaManagedAgentsWebFetchUrlSourceToolReference>>("tools")
                    .asKnown()
        }

    /** Every URL from this source may be fetched. This is the default. */
    fun all(): Optional<BetaManagedAgentsWebFetchUrlSourceAll> = Optional.ofNullable(all)

    /** This source contributes no URLs that may be fetched. */
    fun none(): Optional<BetaManagedAgentsWebFetchUrlSourceNone> = Optional.ofNullable(none)

    /** Only the named tools' results contribute URLs that may be fetched. */
    fun only(): Optional<BetaManagedAgentsWebFetchUrlSourceOnly> = Optional.ofNullable(only)

    /**
     * Every tool's results contribute URLs that may be fetched, except the named tools' results.
     */
    fun except(): Optional<BetaManagedAgentsWebFetchUrlSourceExcept> = Optional.ofNullable(except)

    fun isAll(): Boolean = all != null

    fun isNone(): Boolean = none != null

    fun isOnly(): Boolean = only != null

    fun isExcept(): Boolean = except != null

    /** Every URL from this source may be fetched. This is the default. */
    fun asAll(): BetaManagedAgentsWebFetchUrlSourceAll = all.getOrThrow("all")

    /** This source contributes no URLs that may be fetched. */
    fun asNone(): BetaManagedAgentsWebFetchUrlSourceNone = none.getOrThrow("none")

    /** Only the named tools' results contribute URLs that may be fetched. */
    fun asOnly(): BetaManagedAgentsWebFetchUrlSourceOnly = only.getOrThrow("only")

    /**
     * Every tool's results contribute URLs that may be fetched, except the named tools' results.
     */
    fun asExcept(): BetaManagedAgentsWebFetchUrlSourceExcept = except.getOrThrow("except")

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
     * Optional<String> result = betaManagedAgentsWebFetchUrlSourceToolFilter.accept(new BetaManagedAgentsWebFetchUrlSourceToolFilter.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAll(BetaManagedAgentsWebFetchUrlSourceAll all) {
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
            none != null -> visitor.visitNone(none)
            only != null -> visitor.visitOnly(only)
            except != null -> visitor.visitExcept(except)
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
    fun validate(): BetaManagedAgentsWebFetchUrlSourceToolFilter = apply {
        if (validated) {
            return@apply
        }

        when {
            all != null -> all.validate()
            none != null -> none.validate()
            only != null -> only.validate()
            except != null -> except.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaManagedAgentsWebFetchUrlSourceToolFilter: $_json"
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
            none != null -> none.validity()
            only != null -> only.validity()
            except != null -> except.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWebFetchUrlSourceToolFilter &&
            all == other.all &&
            none == other.none &&
            only == other.only &&
            except == other.except
    }

    override fun hashCode(): Int = Objects.hash(all, none, only, except)

    override fun toString(): String =
        when {
            all != null -> "BetaManagedAgentsWebFetchUrlSourceToolFilter{all=$all}"
            none != null -> "BetaManagedAgentsWebFetchUrlSourceToolFilter{none=$none}"
            only != null -> "BetaManagedAgentsWebFetchUrlSourceToolFilter{only=$only}"
            except != null -> "BetaManagedAgentsWebFetchUrlSourceToolFilter{except=$except}"
            _json != null -> "BetaManagedAgentsWebFetchUrlSourceToolFilter{_unknown=$_json}"
            else ->
                throw IllegalStateException("Invalid BetaManagedAgentsWebFetchUrlSourceToolFilter")
        }

    companion object {

        /** Every URL from this source may be fetched. This is the default. */
        @JvmStatic
        fun ofAll(all: BetaManagedAgentsWebFetchUrlSourceAll) =
            BetaManagedAgentsWebFetchUrlSourceToolFilter(all = all)

        /** This source contributes no URLs that may be fetched. */
        @JvmStatic
        fun ofNone(none: BetaManagedAgentsWebFetchUrlSourceNone) =
            BetaManagedAgentsWebFetchUrlSourceToolFilter(none = none)

        /** Only the named tools' results contribute URLs that may be fetched. */
        @JvmStatic
        fun ofOnly(only: BetaManagedAgentsWebFetchUrlSourceOnly) =
            BetaManagedAgentsWebFetchUrlSourceToolFilter(only = only)

        /**
         * Returns an immutable instance of [BetaManagedAgentsWebFetchUrlSourceToolFilter] whose
         * [ofOnly] variant is built from the given required [tools].
         */
        @JvmStatic
        fun ofOnly(tools: List<BetaManagedAgentsWebFetchUrlSourceToolReference>) =
            ofOnly(BetaManagedAgentsWebFetchUrlSourceOnly.of(tools))

        /**
         * Every tool's results contribute URLs that may be fetched, except the named tools'
         * results.
         */
        @JvmStatic
        fun ofExcept(except: BetaManagedAgentsWebFetchUrlSourceExcept) =
            BetaManagedAgentsWebFetchUrlSourceToolFilter(except = except)

        /**
         * Returns an immutable instance of [BetaManagedAgentsWebFetchUrlSourceToolFilter] whose
         * [ofExcept] variant is built from the given required [tools].
         */
        @JvmStatic
        fun ofExcept(tools: List<BetaManagedAgentsWebFetchUrlSourceToolReference>) =
            ofExcept(BetaManagedAgentsWebFetchUrlSourceExcept.of(tools))
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsWebFetchUrlSourceToolFilter] to a value of type [T].
     */
    interface Visitor<out T> {

        /** Every URL from this source may be fetched. This is the default. */
        fun visitAll(all: BetaManagedAgentsWebFetchUrlSourceAll): T

        /** This source contributes no URLs that may be fetched. */
        fun visitNone(none: BetaManagedAgentsWebFetchUrlSourceNone): T

        /** Only the named tools' results contribute URLs that may be fetched. */
        fun visitOnly(only: BetaManagedAgentsWebFetchUrlSourceOnly): T

        /**
         * Every tool's results contribute URLs that may be fetched, except the named tools'
         * results.
         */
        fun visitExcept(except: BetaManagedAgentsWebFetchUrlSourceExcept): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsWebFetchUrlSourceToolFilter] to a value of
         * type [T].
         *
         * An instance of [BetaManagedAgentsWebFetchUrlSourceToolFilter] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsWebFetchUrlSourceToolFilter: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsWebFetchUrlSourceToolFilter>(
            BetaManagedAgentsWebFetchUrlSourceToolFilter::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsWebFetchUrlSourceToolFilter {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "all" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceAll>(),
                        )
                        ?.let {
                            BetaManagedAgentsWebFetchUrlSourceToolFilter(all = it, _json = json)
                        } ?: BetaManagedAgentsWebFetchUrlSourceToolFilter(_json = json)
                }
                "none" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceNone>(),
                        )
                        ?.let {
                            BetaManagedAgentsWebFetchUrlSourceToolFilter(none = it, _json = json)
                        } ?: BetaManagedAgentsWebFetchUrlSourceToolFilter(_json = json)
                }
                "only" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceOnly>(),
                        )
                        ?.let {
                            BetaManagedAgentsWebFetchUrlSourceToolFilter(only = it, _json = json)
                        } ?: BetaManagedAgentsWebFetchUrlSourceToolFilter(_json = json)
                }
                "except" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceExcept>(),
                        )
                        ?.let {
                            BetaManagedAgentsWebFetchUrlSourceToolFilter(except = it, _json = json)
                        } ?: BetaManagedAgentsWebFetchUrlSourceToolFilter(_json = json)
                }
            }

            return BetaManagedAgentsWebFetchUrlSourceToolFilter(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsWebFetchUrlSourceToolFilter>(
            BetaManagedAgentsWebFetchUrlSourceToolFilter::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsWebFetchUrlSourceToolFilter,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.all != null -> generator.writeObject(value.all)
                value.none != null -> generator.writeObject(value.none)
                value.only != null -> generator.writeObject(value.only)
                value.except != null -> generator.writeObject(value.except)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaManagedAgentsWebFetchUrlSourceToolFilter"
                    )
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

            @JvmField val NONE = Type(JsonField.of("none"))

            @JvmField val ONLY = Type(JsonField.of("only"))

            @JvmField val EXCEPT = Type(JsonField.of("except"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "all" -> ALL
                    "none" -> NONE
                    "only" -> ONLY
                    "except" -> EXCEPT
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
            NONE,
            ONLY,
            EXCEPT,
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
            NONE,
            ONLY,
            EXCEPT,
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
                NONE -> Value.NONE
                ONLY -> Value.ONLY
                EXCEPT -> Value.EXCEPT
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
                NONE -> Known.NONE
                ONLY -> Known.ONLY
                EXCEPT -> Known.EXCEPT
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
