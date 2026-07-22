// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Model identifier and configuration. */
class BetaManagedAgentsModelConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<BetaManagedAgentsModel>,
    private val effort: JsonField<Effort>,
    private val speed: JsonField<Speed>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id")
        @ExcludeMissing
        id: JsonField<BetaManagedAgentsModel> = JsonMissing.of(),
        @JsonProperty("effort") @ExcludeMissing effort: JsonField<Effort> = JsonMissing.of(),
        @JsonProperty("speed") @ExcludeMissing speed: JsonField<Speed> = JsonMissing.of(),
    ) : this(id, effort, speed, mutableMapOf())

    /**
     * The model that will power your agent.
     *
     * See [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): BetaManagedAgentsModel = id.getRequired("id")

    /**
     * How hard Claude works on each turn. Sets `output_config.effort` on every Messages call the
     * session makes.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun effort(): Optional<Effort> = effort.getOptional("effort")

    /**
     * Inference speed mode. `fast` provides significantly faster output token generation at premium
     * pricing. Not all models support `fast`; invalid combinations are rejected at create time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun speed(): Optional<Speed> = speed.getOptional("speed")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<BetaManagedAgentsModel> = id

    /**
     * Returns the raw JSON value of [effort].
     *
     * Unlike [effort], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("effort") @ExcludeMissing fun _effort(): JsonField<Effort> = effort

    /**
     * Returns the raw JSON value of [speed].
     *
     * Unlike [speed], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("speed") @ExcludeMissing fun _speed(): JsonField<Speed> = speed

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsModelConfig].
         *
         * The following fields are required:
         * ```java
         * .id()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsModelConfig]. */
    class Builder internal constructor() {

        private var id: JsonField<BetaManagedAgentsModel>? = null
        private var effort: JsonField<Effort> = JsonMissing.of()
        private var speed: JsonField<Speed> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsModelConfig: BetaManagedAgentsModelConfig) = apply {
            id = betaManagedAgentsModelConfig.id
            effort = betaManagedAgentsModelConfig.effort
            speed = betaManagedAgentsModelConfig.speed
            additionalProperties = betaManagedAgentsModelConfig.additionalProperties.toMutableMap()
        }

        /**
         * The model that will power your agent.
         *
         * See [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
         * and options.
         */
        fun id(id: BetaManagedAgentsModel) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [BetaManagedAgentsModel] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun id(id: JsonField<BetaManagedAgentsModel>) = apply { this.id = id }

        /**
         * Sets [id] to an arbitrary [String].
         *
         * You should usually call [id] with a well-typed [BetaManagedAgentsModel] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun id(value: String) = id(BetaManagedAgentsModel.of(value))

        /**
         * How hard Claude works on each turn. Sets `output_config.effort` on every Messages call
         * the session makes.
         */
        fun effort(effort: Effort) = effort(JsonField.of(effort))

        /**
         * Sets [Builder.effort] to an arbitrary JSON value.
         *
         * You should usually call [Builder.effort] with a well-typed [Effort] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun effort(effort: JsonField<Effort>) = apply { this.effort = effort }

        /** Alias for calling [effort] with `Effort.ofLow(low)`. */
        fun effort(low: BetaManagedAgentsEffortLow) = effort(Effort.ofLow(low))

        /** Alias for calling [effort] with `Effort.ofMedium(medium)`. */
        fun effort(medium: BetaManagedAgentsEffortMedium) = effort(Effort.ofMedium(medium))

        /** Alias for calling [effort] with `Effort.ofHigh(high)`. */
        fun effort(high: BetaManagedAgentsEffortHigh) = effort(Effort.ofHigh(high))

        /** Alias for calling [effort] with `Effort.ofXhigh(xhigh)`. */
        fun effort(xhigh: BetaManagedAgentsEffortXhigh) = effort(Effort.ofXhigh(xhigh))

        /** Alias for calling [effort] with `Effort.ofMax(max)`. */
        fun effort(max: BetaManagedAgentsEffortMax) = effort(Effort.ofMax(max))

        /**
         * Inference speed mode. `fast` provides significantly faster output token generation at
         * premium pricing. Not all models support `fast`; invalid combinations are rejected at
         * create time.
         */
        fun speed(speed: Speed) = speed(JsonField.of(speed))

        /**
         * Sets [Builder.speed] to an arbitrary JSON value.
         *
         * You should usually call [Builder.speed] with a well-typed [Speed] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun speed(speed: JsonField<Speed>) = apply { this.speed = speed }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaManagedAgentsModelConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsModelConfig =
            BetaManagedAgentsModelConfig(
                checkRequired("id", id),
                effort,
                speed,
                additionalProperties.toMutableMap(),
            )
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
    fun validate(): BetaManagedAgentsModelConfig = apply {
        if (validated) {
            return@apply
        }

        id()
        effort().ifPresent { it.validate() }
        speed().ifPresent { it.validate() }
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (effort.asKnown().getOrNull()?.validity() ?: 0) +
            (speed.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * How hard Claude works on each turn. Sets `output_config.effort` on every Messages call the
     * session makes.
     */
    @JsonDeserialize(using = Effort.Deserializer::class)
    @JsonSerialize(using = Effort.Serializer::class)
    class Effort
    private constructor(
        private val low: BetaManagedAgentsEffortLow? = null,
        private val medium: BetaManagedAgentsEffortMedium? = null,
        private val high: BetaManagedAgentsEffortHigh? = null,
        private val xhigh: BetaManagedAgentsEffortXhigh? = null,
        private val max: BetaManagedAgentsEffortMax? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Low effort. Favors latency over reasoning depth. */
        fun low(): Optional<BetaManagedAgentsEffortLow> = Optional.ofNullable(low)

        /** Medium effort. Balances latency and reasoning depth. */
        fun medium(): Optional<BetaManagedAgentsEffortMedium> = Optional.ofNullable(medium)

        /** High effort. Favors reasoning depth. */
        fun high(): Optional<BetaManagedAgentsEffortHigh> = Optional.ofNullable(high)

        /** Extra-high effort. Not all models accept this level. */
        fun xhigh(): Optional<BetaManagedAgentsEffortXhigh> = Optional.ofNullable(xhigh)

        /** Maximum effort. Favors reasoning depth over latency. */
        fun max(): Optional<BetaManagedAgentsEffortMax> = Optional.ofNullable(max)

        fun isLow(): Boolean = low != null

        fun isMedium(): Boolean = medium != null

        fun isHigh(): Boolean = high != null

        fun isXhigh(): Boolean = xhigh != null

        fun isMax(): Boolean = max != null

        /** Low effort. Favors latency over reasoning depth. */
        fun asLow(): BetaManagedAgentsEffortLow = low.getOrThrow("low")

        /** Medium effort. Balances latency and reasoning depth. */
        fun asMedium(): BetaManagedAgentsEffortMedium = medium.getOrThrow("medium")

        /** High effort. Favors reasoning depth. */
        fun asHigh(): BetaManagedAgentsEffortHigh = high.getOrThrow("high")

        /** Extra-high effort. Not all models accept this level. */
        fun asXhigh(): BetaManagedAgentsEffortXhigh = xhigh.getOrThrow("xhigh")

        /** Maximum effort. Favors reasoning depth over latency. */
        fun asMax(): BetaManagedAgentsEffortMax = max.getOrThrow("max")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = effort.accept(new Effort.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitLow(BetaManagedAgentsEffortLow low) {
         *         return Optional.of(low.toString());
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
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                low != null -> visitor.visitLow(low)
                medium != null -> visitor.visitMedium(medium)
                high != null -> visitor.visitHigh(high)
                xhigh != null -> visitor.visitXhigh(xhigh)
                max != null -> visitor.visitMax(max)
                else -> visitor.unknown(_json)
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
        fun validate(): Effort = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitLow(low: BetaManagedAgentsEffortLow) {
                        low.validate()
                    }

                    override fun visitMedium(medium: BetaManagedAgentsEffortMedium) {
                        medium.validate()
                    }

                    override fun visitHigh(high: BetaManagedAgentsEffortHigh) {
                        high.validate()
                    }

                    override fun visitXhigh(xhigh: BetaManagedAgentsEffortXhigh) {
                        xhigh.validate()
                    }

                    override fun visitMax(max: BetaManagedAgentsEffortMax) {
                        max.validate()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitLow(low: BetaManagedAgentsEffortLow) = low.validity()

                    override fun visitMedium(medium: BetaManagedAgentsEffortMedium) =
                        medium.validity()

                    override fun visitHigh(high: BetaManagedAgentsEffortHigh) = high.validity()

                    override fun visitXhigh(xhigh: BetaManagedAgentsEffortXhigh) = xhigh.validity()

                    override fun visitMax(max: BetaManagedAgentsEffortMax) = max.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Effort &&
                low == other.low &&
                medium == other.medium &&
                high == other.high &&
                xhigh == other.xhigh &&
                max == other.max
        }

        override fun hashCode(): Int = Objects.hash(low, medium, high, xhigh, max)

        override fun toString(): String =
            when {
                low != null -> "Effort{low=$low}"
                medium != null -> "Effort{medium=$medium}"
                high != null -> "Effort{high=$high}"
                xhigh != null -> "Effort{xhigh=$xhigh}"
                max != null -> "Effort{max=$max}"
                _json != null -> "Effort{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Effort")
            }

        companion object {

            /** Low effort. Favors latency over reasoning depth. */
            @JvmStatic fun ofLow(low: BetaManagedAgentsEffortLow) = Effort(low = low)

            /** Medium effort. Balances latency and reasoning depth. */
            @JvmStatic fun ofMedium(medium: BetaManagedAgentsEffortMedium) = Effort(medium = medium)

            /** High effort. Favors reasoning depth. */
            @JvmStatic fun ofHigh(high: BetaManagedAgentsEffortHigh) = Effort(high = high)

            /** Extra-high effort. Not all models accept this level. */
            @JvmStatic fun ofXhigh(xhigh: BetaManagedAgentsEffortXhigh) = Effort(xhigh = xhigh)

            /** Maximum effort. Favors reasoning depth over latency. */
            @JvmStatic fun ofMax(max: BetaManagedAgentsEffortMax) = Effort(max = max)
        }

        /** An interface that defines how to map each variant of [Effort] to a value of type [T]. */
        interface Visitor<out T> {

            /** Low effort. Favors latency over reasoning depth. */
            fun visitLow(low: BetaManagedAgentsEffortLow): T

            /** Medium effort. Balances latency and reasoning depth. */
            fun visitMedium(medium: BetaManagedAgentsEffortMedium): T

            /** High effort. Favors reasoning depth. */
            fun visitHigh(high: BetaManagedAgentsEffortHigh): T

            /** Extra-high effort. Not all models accept this level. */
            fun visitXhigh(xhigh: BetaManagedAgentsEffortXhigh): T

            /** Maximum effort. Favors reasoning depth over latency. */
            fun visitMax(max: BetaManagedAgentsEffortMax): T

            /**
             * Maps an unknown variant of [Effort] to a value of type [T].
             *
             * An instance of [Effort] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Effort: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Effort>(Effort::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Effort {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "low" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEffortLow>())
                            ?.let { Effort(low = it, _json = json) } ?: Effort(_json = json)
                    }
                    "medium" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEffortMedium>())
                            ?.let { Effort(medium = it, _json = json) } ?: Effort(_json = json)
                    }
                    "high" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEffortHigh>())
                            ?.let { Effort(high = it, _json = json) } ?: Effort(_json = json)
                    }
                    "xhigh" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEffortXhigh>())
                            ?.let { Effort(xhigh = it, _json = json) } ?: Effort(_json = json)
                    }
                    "max" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEffortMax>())
                            ?.let { Effort(max = it, _json = json) } ?: Effort(_json = json)
                    }
                }

                return Effort(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Effort>(Effort::class) {

            override fun serialize(
                value: Effort,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.low != null -> generator.writeObject(value.low)
                    value.medium != null -> generator.writeObject(value.medium)
                    value.high != null -> generator.writeObject(value.high)
                    value.xhigh != null -> generator.writeObject(value.xhigh)
                    value.max != null -> generator.writeObject(value.max)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Effort")
                }
            }
        }
    }

    /**
     * Inference speed mode. `fast` provides significantly faster output token generation at premium
     * pricing. Not all models support `fast`; invalid combinations are rejected at create time.
     */
    class Speed @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val STANDARD = of("standard")

            @JvmField val FAST = of("fast")

            @JvmStatic fun of(value: String) = Speed(JsonField.of(value))
        }

        /** An enum containing [Speed]'s known values. */
        enum class Known {
            STANDARD,
            FAST,
        }

        /**
         * An enum containing [Speed]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Speed] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            STANDARD,
            FAST,
            /** An enum member indicating that [Speed] was instantiated with an unknown value. */
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
                STANDARD -> Value.STANDARD
                FAST -> Value.FAST
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
                STANDARD -> Known.STANDARD
                FAST -> Known.FAST
                else -> throw AnthropicInvalidDataException("Unknown Speed: $value")
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
        fun validate(): Speed = apply {
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

            return other is Speed && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsModelConfig &&
            id == other.id &&
            effort == other.effort &&
            speed == other.speed &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(id, effort, speed, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsModelConfig{id=$id, effort=$effort, speed=$speed, additionalProperties=$additionalProperties}"
}
