// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

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
 * The default destination: the job creates a new output memory store as a clone of the memory_store
 * input and writes the consolidated memories into it. The input store is never mutated.
 */
@JsonDeserialize(using = BetaOutputBehavior.Deserializer::class)
@JsonSerialize(using = BetaOutputBehavior.Serializer::class)
class BetaOutputBehavior
private constructor(
    private val createNew: BetaOutputBehaviorCreateNew? = null,
    private val updateExisting: BetaOutputBehaviorUpdateExisting? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitCreateNew(createNew: BetaOutputBehaviorCreateNew): Type =
                    Type.CREATE_NEW

                override fun visitUpdateExisting(
                    updateExisting: BetaOutputBehaviorUpdateExisting
                ): Type = Type.UPDATE_EXISTING

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    /**
     * The default destination: the job creates a new output memory store as a clone of the
     * memory_store input and writes the consolidated memories into it. The input store is never
     * mutated.
     */
    fun createNew(): Optional<BetaOutputBehaviorCreateNew> = Optional.ofNullable(createNew)

    /**
     * The job writes the consolidated memories into this existing memory store instead of creating
     * one. In EAP the store must be the job's own memory_store input, so the job consolidates the
     * store in place.
     */
    fun updateExisting(): Optional<BetaOutputBehaviorUpdateExisting> =
        Optional.ofNullable(updateExisting)

    fun isCreateNew(): Boolean = createNew != null

    fun isUpdateExisting(): Boolean = updateExisting != null

    /**
     * The default destination: the job creates a new output memory store as a clone of the
     * memory_store input and writes the consolidated memories into it. The input store is never
     * mutated.
     */
    fun asCreateNew(): BetaOutputBehaviorCreateNew = createNew.getOrThrow("createNew")

    /**
     * The job writes the consolidated memories into this existing memory store instead of creating
     * one. In EAP the store must be the job's own memory_store input, so the job consolidates the
     * store in place.
     */
    fun asUpdateExisting(): BetaOutputBehaviorUpdateExisting =
        updateExisting.getOrThrow("updateExisting")

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
     * Optional<String> result = betaOutputBehavior.accept(new BetaOutputBehavior.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitCreateNew(BetaOutputBehaviorCreateNew createNew) {
     *         return Optional.of(createNew.toString());
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
            createNew != null -> visitor.visitCreateNew(createNew)
            updateExisting != null -> visitor.visitUpdateExisting(updateExisting)
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
    fun validate(): BetaOutputBehavior = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitCreateNew(createNew: BetaOutputBehaviorCreateNew) {
                    createNew.validate()
                }

                override fun visitUpdateExisting(updateExisting: BetaOutputBehaviorUpdateExisting) {
                    updateExisting.validate()
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
                override fun visitCreateNew(createNew: BetaOutputBehaviorCreateNew) =
                    createNew.validity()

                override fun visitUpdateExisting(updateExisting: BetaOutputBehaviorUpdateExisting) =
                    updateExisting.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaOutputBehavior &&
            createNew == other.createNew &&
            updateExisting == other.updateExisting
    }

    override fun hashCode(): Int = Objects.hash(createNew, updateExisting)

    override fun toString(): String =
        when {
            createNew != null -> "BetaOutputBehavior{createNew=$createNew}"
            updateExisting != null -> "BetaOutputBehavior{updateExisting=$updateExisting}"
            _json != null -> "BetaOutputBehavior{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaOutputBehavior")
        }

    companion object {

        /**
         * The default destination: the job creates a new output memory store as a clone of the
         * memory_store input and writes the consolidated memories into it. The input store is never
         * mutated.
         */
        @JvmStatic
        fun ofCreateNew(createNew: BetaOutputBehaviorCreateNew) =
            BetaOutputBehavior(createNew = createNew)

        /**
         * Returns an immutable instance of [BetaOutputBehavior] whose [ofCreateNew] variant is
         * built from the given required [type].
         */
        @JvmStatic
        fun ofCreateNew(type: BetaOutputBehaviorCreateNew.Type) =
            ofCreateNew(BetaOutputBehaviorCreateNew.of(type))

        /**
         * The job writes the consolidated memories into this existing memory store instead of
         * creating one. In EAP the store must be the job's own memory_store input, so the job
         * consolidates the store in place.
         */
        @JvmStatic
        fun ofUpdateExisting(updateExisting: BetaOutputBehaviorUpdateExisting) =
            BetaOutputBehavior(updateExisting = updateExisting)

        /**
         * Returns an immutable instance of [BetaOutputBehavior] whose [ofUpdateExisting] variant is
         * built from the given required [memoryStoreId].
         */
        @JvmStatic
        fun ofUpdateExisting(memoryStoreId: String) =
            ofUpdateExisting(
                BetaOutputBehaviorUpdateExisting.builder()
                    .type(BetaOutputBehaviorUpdateExisting.Type.UPDATE_EXISTING)
                    .memoryStoreId(memoryStoreId)
                    .build()
            )
    }

    /**
     * An interface that defines how to map each variant of [BetaOutputBehavior] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        /**
         * The default destination: the job creates a new output memory store as a clone of the
         * memory_store input and writes the consolidated memories into it. The input store is never
         * mutated.
         */
        fun visitCreateNew(createNew: BetaOutputBehaviorCreateNew): T

        /**
         * The job writes the consolidated memories into this existing memory store instead of
         * creating one. In EAP the store must be the job's own memory_store input, so the job
         * consolidates the store in place.
         */
        fun visitUpdateExisting(updateExisting: BetaOutputBehaviorUpdateExisting): T

        /**
         * Maps an unknown variant of [BetaOutputBehavior] to a value of type [T].
         *
         * An instance of [BetaOutputBehavior] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaOutputBehavior: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaOutputBehavior>(BetaOutputBehavior::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaOutputBehavior {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "create_new" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaOutputBehaviorCreateNew>())
                        ?.let { BetaOutputBehavior(createNew = it, _json = json) }
                        ?: BetaOutputBehavior(_json = json)
                }
                "update_existing" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaOutputBehaviorUpdateExisting>())
                        ?.let { BetaOutputBehavior(updateExisting = it, _json = json) }
                        ?: BetaOutputBehavior(_json = json)
                }
            }

            return BetaOutputBehavior(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaOutputBehavior>(BetaOutputBehavior::class) {

        override fun serialize(
            value: BetaOutputBehavior,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.createNew != null -> generator.writeObject(value.createNew)
                value.updateExisting != null -> generator.writeObject(value.updateExisting)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaOutputBehavior")
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

            @JvmField val CREATE_NEW = of("create_new")

            @JvmField val UPDATE_EXISTING = of("update_existing")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonValue): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            CREATE_NEW,
            UPDATE_EXISTING,
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
            CREATE_NEW,
            UPDATE_EXISTING,
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
                CREATE_NEW -> Value.CREATE_NEW
                UPDATE_EXISTING -> Value.UPDATE_EXISTING
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
                CREATE_NEW -> Known.CREATE_NEW
                UPDATE_EXISTING -> Known.UPDATE_EXISTING
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
