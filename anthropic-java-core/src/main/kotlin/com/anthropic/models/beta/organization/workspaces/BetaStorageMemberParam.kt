package com.anthropic.models.beta.organization.workspaces

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

@JsonDeserialize(using = BetaStorageMemberParam.Deserializer::class)
@JsonSerialize(using = BetaStorageMemberParam.Serializer::class)
class BetaStorageMemberParam
private constructor(
    private val internal_: BetaInternalStorageParam? = null,
    private val external: BetaExternalStorageMemberParam? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            internal_ != null -> Type.INTERNAL
            external != null -> Type.EXTERNAL
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** No External Storage for this purpose. */
    fun internal_(): Optional<BetaInternalStorageParam> = Optional.ofNullable(internal_)

    /** The organization's External Storage that `external_storage_id` names. */
    fun external(): Optional<BetaExternalStorageMemberParam> = Optional.ofNullable(external)

    fun isInternal(): Boolean = internal_ != null

    fun isExternal(): Boolean = external != null

    /** No External Storage for this purpose. */
    fun asInternal(): BetaInternalStorageParam = internal_.getOrThrow("internal_")

    /** The organization's External Storage that `external_storage_id` names. */
    fun asExternal(): BetaExternalStorageMemberParam = external.getOrThrow("external")

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
     * Optional<String> result = betaStorageMemberParam.accept(new BetaStorageMemberParam.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitInternal(BetaInternalStorageParam internal_) {
     *         return Optional.of(internal_.toString());
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
            internal_ != null -> visitor.visitInternal(internal_)
            external != null -> visitor.visitExternal(external)
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
    fun validate(): BetaStorageMemberParam = apply {
        if (validated) {
            return@apply
        }

        when {
            internal_ != null -> internal_.validate()
            external != null -> external.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaStorageMemberParam: $_json")
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
            internal_ != null -> internal_.validity()
            external != null -> external.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaStorageMemberParam &&
            internal_ == other.internal_ &&
            external == other.external
    }

    override fun hashCode(): Int = Objects.hash(internal_, external)

    override fun toString(): String =
        when {
            internal_ != null -> "BetaStorageMemberParam{internal_=$internal_}"
            external != null -> "BetaStorageMemberParam{external=$external}"
            _json != null -> "BetaStorageMemberParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaStorageMemberParam")
        }

    companion object {

        /** No External Storage for this purpose. */
        @JvmStatic
        fun ofInternal(internal_: BetaInternalStorageParam) =
            BetaStorageMemberParam(internal_ = internal_)

        /** The organization's External Storage that `external_storage_id` names. */
        @JvmStatic
        fun ofExternal(external: BetaExternalStorageMemberParam) =
            BetaStorageMemberParam(external = external)

        /**
         * Returns an immutable instance of [BetaStorageMemberParam] whose [ofExternal] variant is
         * built from the given required [externalStorageId].
         */
        @JvmStatic
        fun ofExternal(externalStorageId: String) =
            ofExternal(BetaExternalStorageMemberParam.of(externalStorageId))
    }

    /**
     * An interface that defines how to map each variant of [BetaStorageMemberParam] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        /** No External Storage for this purpose. */
        fun visitInternal(internal_: BetaInternalStorageParam): T

        /** The organization's External Storage that `external_storage_id` names. */
        fun visitExternal(external: BetaExternalStorageMemberParam): T

        /**
         * Maps an unknown variant of [BetaStorageMemberParam] to a value of type [T].
         *
         * An instance of [BetaStorageMemberParam] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaStorageMemberParam: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaStorageMemberParam>(BetaStorageMemberParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaStorageMemberParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "internal" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaInternalStorageParam>())?.let {
                        BetaStorageMemberParam(internal_ = it, _json = json)
                    } ?: BetaStorageMemberParam(_json = json)
                }
                "external" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaExternalStorageMemberParam>())
                        ?.let { BetaStorageMemberParam(external = it, _json = json) }
                        ?: BetaStorageMemberParam(_json = json)
                }
            }

            return BetaStorageMemberParam(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaStorageMemberParam>(BetaStorageMemberParam::class) {

        override fun serialize(
            value: BetaStorageMemberParam,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.internal_ != null -> generator.writeObject(value.internal_)
                value.external != null -> generator.writeObject(value.external)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaStorageMemberParam")
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

            @JvmField val INTERNAL = Type(JsonField.of("internal"))

            @JvmField val EXTERNAL = Type(JsonField.of("external"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "internal" -> INTERNAL
                    "external" -> EXTERNAL
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            INTERNAL,
            EXTERNAL,
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
            INTERNAL,
            EXTERNAL,
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
                INTERNAL -> Value.INTERNAL
                EXTERNAL -> Value.EXTERNAL
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
                INTERNAL -> Known.INTERNAL
                EXTERNAL -> Known.EXTERNAL
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
