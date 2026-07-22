// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
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

/** An object that defines additional configuration control over model use */
class BetaManagedAgentsModelConfigParams
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
     * How hard Claude works on each inference call. Accepts a bare level string (`"high"`) or
     * `{"type": "high"}`. On create, omitting it resolves the per-model default; on update,
     * omitting it leaves the stored value unchanged.
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
         * Returns a mutable builder for constructing an instance of
         * [BetaManagedAgentsModelConfigParams].
         *
         * The following fields are required:
         * ```java
         * .id()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsModelConfigParams]. */
    class Builder internal constructor() {

        private var id: JsonField<BetaManagedAgentsModel>? = null
        private var effort: JsonField<Effort> = JsonMissing.of()
        private var speed: JsonField<Speed> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsModelConfigParams: BetaManagedAgentsModelConfigParams) =
            apply {
                id = betaManagedAgentsModelConfigParams.id
                effort = betaManagedAgentsModelConfigParams.effort
                speed = betaManagedAgentsModelConfigParams.speed
                additionalProperties =
                    betaManagedAgentsModelConfigParams.additionalProperties.toMutableMap()
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
         * How hard Claude works on each inference call. Accepts a bare level string (`"high"`) or
         * `{"type": "high"}`. On create, omitting it resolves the per-model default; on update,
         * omitting it leaves the stored value unchanged.
         */
        fun effort(effort: Effort?) = effort(JsonField.ofNullable(effort))

        /** Alias for calling [Builder.effort] with `effort.orElse(null)`. */
        fun effort(effort: Optional<Effort>) = effort(effort.getOrNull())

        /**
         * Sets [Builder.effort] to an arbitrary JSON value.
         *
         * You should usually call [Builder.effort] with a well-typed [Effort] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun effort(effort: JsonField<Effort>) = apply { this.effort = effort }

        /**
         * Alias for calling [effort] with
         * `Effort.ofBetaManagedAgentsEffortLevel(betaManagedAgentsEffortLevel)`.
         */
        fun effort(betaManagedAgentsEffortLevel: Effort.BetaManagedAgentsEffortLevel) =
            effort(Effort.ofBetaManagedAgentsEffortLevel(betaManagedAgentsEffortLevel))

        /**
         * Alias for calling [effort] with
         * `Effort.ofBetaManagedAgentsEffortLow(betaManagedAgentsEffortLow)`.
         */
        fun effort(betaManagedAgentsEffortLow: BetaManagedAgentsEffortLow) =
            effort(Effort.ofBetaManagedAgentsEffortLow(betaManagedAgentsEffortLow))

        /**
         * Alias for calling [effort] with
         * `Effort.ofBetaManagedAgentsEffortMedium(betaManagedAgentsEffortMedium)`.
         */
        fun effort(betaManagedAgentsEffortMedium: BetaManagedAgentsEffortMedium) =
            effort(Effort.ofBetaManagedAgentsEffortMedium(betaManagedAgentsEffortMedium))

        /**
         * Alias for calling [effort] with
         * `Effort.ofBetaManagedAgentsEffortHigh(betaManagedAgentsEffortHigh)`.
         */
        fun effort(betaManagedAgentsEffortHigh: BetaManagedAgentsEffortHigh) =
            effort(Effort.ofBetaManagedAgentsEffortHigh(betaManagedAgentsEffortHigh))

        /**
         * Alias for calling [effort] with
         * `Effort.ofBetaManagedAgentsEffortXhigh(betaManagedAgentsEffortXhigh)`.
         */
        fun effort(betaManagedAgentsEffortXhigh: BetaManagedAgentsEffortXhigh) =
            effort(Effort.ofBetaManagedAgentsEffortXhigh(betaManagedAgentsEffortXhigh))

        /**
         * Alias for calling [effort] with
         * `Effort.ofBetaManagedAgentsEffortMax(betaManagedAgentsEffortMax)`.
         */
        fun effort(betaManagedAgentsEffortMax: BetaManagedAgentsEffortMax) =
            effort(Effort.ofBetaManagedAgentsEffortMax(betaManagedAgentsEffortMax))

        /**
         * Inference speed mode. `fast` provides significantly faster output token generation at
         * premium pricing. Not all models support `fast`; invalid combinations are rejected at
         * create time.
         */
        fun speed(speed: Speed?) = speed(JsonField.ofNullable(speed))

        /** Alias for calling [Builder.speed] with `speed.orElse(null)`. */
        fun speed(speed: Optional<Speed>) = speed(speed.getOrNull())

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
         * Returns an immutable instance of [BetaManagedAgentsModelConfigParams].
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
        fun build(): BetaManagedAgentsModelConfigParams =
            BetaManagedAgentsModelConfigParams(
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
    fun validate(): BetaManagedAgentsModelConfigParams = apply {
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
     * How hard Claude works on each inference call. Accepts a bare level string (`"high"`) or
     * `{"type": "high"}`. On create, omitting it resolves the per-model default; on update,
     * omitting it leaves the stored value unchanged.
     */
    @JsonDeserialize(using = Effort.Deserializer::class)
    @JsonSerialize(using = Effort.Serializer::class)
    class Effort
    private constructor(
        private val betaManagedAgentsEffortLevel: BetaManagedAgentsEffortLevel? = null,
        private val betaManagedAgentsEffortLow: BetaManagedAgentsEffortLow? = null,
        private val betaManagedAgentsEffortMedium: BetaManagedAgentsEffortMedium? = null,
        private val betaManagedAgentsEffortHigh: BetaManagedAgentsEffortHigh? = null,
        private val betaManagedAgentsEffortXhigh: BetaManagedAgentsEffortXhigh? = null,
        private val betaManagedAgentsEffortMax: BetaManagedAgentsEffortMax? = null,
        private val _json: JsonValue? = null,
    ) {

        /**
         * How hard Claude works on each turn. Higher levels favor reasoning depth over latency. Not
         * all models accept every level; invalid combinations are rejected at create time.
         */
        fun betaManagedAgentsEffortLevel(): Optional<BetaManagedAgentsEffortLevel> =
            Optional.ofNullable(betaManagedAgentsEffortLevel)

        /** Low effort. Favors latency over reasoning depth. */
        fun betaManagedAgentsEffortLow(): Optional<BetaManagedAgentsEffortLow> =
            Optional.ofNullable(betaManagedAgentsEffortLow)

        /** Medium effort. Balances latency and reasoning depth. */
        fun betaManagedAgentsEffortMedium(): Optional<BetaManagedAgentsEffortMedium> =
            Optional.ofNullable(betaManagedAgentsEffortMedium)

        /** High effort. Favors reasoning depth. */
        fun betaManagedAgentsEffortHigh(): Optional<BetaManagedAgentsEffortHigh> =
            Optional.ofNullable(betaManagedAgentsEffortHigh)

        /** Extra-high effort. Not all models accept this level. */
        fun betaManagedAgentsEffortXhigh(): Optional<BetaManagedAgentsEffortXhigh> =
            Optional.ofNullable(betaManagedAgentsEffortXhigh)

        /** Maximum effort. Favors reasoning depth over latency. */
        fun betaManagedAgentsEffortMax(): Optional<BetaManagedAgentsEffortMax> =
            Optional.ofNullable(betaManagedAgentsEffortMax)

        fun isBetaManagedAgentsEffortLevel(): Boolean = betaManagedAgentsEffortLevel != null

        fun isBetaManagedAgentsEffortLow(): Boolean = betaManagedAgentsEffortLow != null

        fun isBetaManagedAgentsEffortMedium(): Boolean = betaManagedAgentsEffortMedium != null

        fun isBetaManagedAgentsEffortHigh(): Boolean = betaManagedAgentsEffortHigh != null

        fun isBetaManagedAgentsEffortXhigh(): Boolean = betaManagedAgentsEffortXhigh != null

        fun isBetaManagedAgentsEffortMax(): Boolean = betaManagedAgentsEffortMax != null

        /**
         * How hard Claude works on each turn. Higher levels favor reasoning depth over latency. Not
         * all models accept every level; invalid combinations are rejected at create time.
         */
        fun asBetaManagedAgentsEffortLevel(): BetaManagedAgentsEffortLevel =
            betaManagedAgentsEffortLevel.getOrThrow("betaManagedAgentsEffortLevel")

        /** Low effort. Favors latency over reasoning depth. */
        fun asBetaManagedAgentsEffortLow(): BetaManagedAgentsEffortLow =
            betaManagedAgentsEffortLow.getOrThrow("betaManagedAgentsEffortLow")

        /** Medium effort. Balances latency and reasoning depth. */
        fun asBetaManagedAgentsEffortMedium(): BetaManagedAgentsEffortMedium =
            betaManagedAgentsEffortMedium.getOrThrow("betaManagedAgentsEffortMedium")

        /** High effort. Favors reasoning depth. */
        fun asBetaManagedAgentsEffortHigh(): BetaManagedAgentsEffortHigh =
            betaManagedAgentsEffortHigh.getOrThrow("betaManagedAgentsEffortHigh")

        /** Extra-high effort. Not all models accept this level. */
        fun asBetaManagedAgentsEffortXhigh(): BetaManagedAgentsEffortXhigh =
            betaManagedAgentsEffortXhigh.getOrThrow("betaManagedAgentsEffortXhigh")

        /** Maximum effort. Favors reasoning depth over latency. */
        fun asBetaManagedAgentsEffortMax(): BetaManagedAgentsEffortMax =
            betaManagedAgentsEffortMax.getOrThrow("betaManagedAgentsEffortMax")

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
         *     public Optional<String> visitBetaManagedAgentsEffortLevel(BetaManagedAgentsEffortLevel betaManagedAgentsEffortLevel) {
         *         return Optional.of(betaManagedAgentsEffortLevel.toString());
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
                betaManagedAgentsEffortLevel != null ->
                    visitor.visitBetaManagedAgentsEffortLevel(betaManagedAgentsEffortLevel)
                betaManagedAgentsEffortLow != null ->
                    visitor.visitBetaManagedAgentsEffortLow(betaManagedAgentsEffortLow)
                betaManagedAgentsEffortMedium != null ->
                    visitor.visitBetaManagedAgentsEffortMedium(betaManagedAgentsEffortMedium)
                betaManagedAgentsEffortHigh != null ->
                    visitor.visitBetaManagedAgentsEffortHigh(betaManagedAgentsEffortHigh)
                betaManagedAgentsEffortXhigh != null ->
                    visitor.visitBetaManagedAgentsEffortXhigh(betaManagedAgentsEffortXhigh)
                betaManagedAgentsEffortMax != null ->
                    visitor.visitBetaManagedAgentsEffortMax(betaManagedAgentsEffortMax)
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
                    override fun visitBetaManagedAgentsEffortLevel(
                        betaManagedAgentsEffortLevel: BetaManagedAgentsEffortLevel
                    ) {
                        betaManagedAgentsEffortLevel.validate()
                    }

                    override fun visitBetaManagedAgentsEffortLow(
                        betaManagedAgentsEffortLow: BetaManagedAgentsEffortLow
                    ) {
                        betaManagedAgentsEffortLow.validate()
                    }

                    override fun visitBetaManagedAgentsEffortMedium(
                        betaManagedAgentsEffortMedium: BetaManagedAgentsEffortMedium
                    ) {
                        betaManagedAgentsEffortMedium.validate()
                    }

                    override fun visitBetaManagedAgentsEffortHigh(
                        betaManagedAgentsEffortHigh: BetaManagedAgentsEffortHigh
                    ) {
                        betaManagedAgentsEffortHigh.validate()
                    }

                    override fun visitBetaManagedAgentsEffortXhigh(
                        betaManagedAgentsEffortXhigh: BetaManagedAgentsEffortXhigh
                    ) {
                        betaManagedAgentsEffortXhigh.validate()
                    }

                    override fun visitBetaManagedAgentsEffortMax(
                        betaManagedAgentsEffortMax: BetaManagedAgentsEffortMax
                    ) {
                        betaManagedAgentsEffortMax.validate()
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
                    override fun visitBetaManagedAgentsEffortLevel(
                        betaManagedAgentsEffortLevel: BetaManagedAgentsEffortLevel
                    ) = betaManagedAgentsEffortLevel.validity()

                    override fun visitBetaManagedAgentsEffortLow(
                        betaManagedAgentsEffortLow: BetaManagedAgentsEffortLow
                    ) = betaManagedAgentsEffortLow.validity()

                    override fun visitBetaManagedAgentsEffortMedium(
                        betaManagedAgentsEffortMedium: BetaManagedAgentsEffortMedium
                    ) = betaManagedAgentsEffortMedium.validity()

                    override fun visitBetaManagedAgentsEffortHigh(
                        betaManagedAgentsEffortHigh: BetaManagedAgentsEffortHigh
                    ) = betaManagedAgentsEffortHigh.validity()

                    override fun visitBetaManagedAgentsEffortXhigh(
                        betaManagedAgentsEffortXhigh: BetaManagedAgentsEffortXhigh
                    ) = betaManagedAgentsEffortXhigh.validity()

                    override fun visitBetaManagedAgentsEffortMax(
                        betaManagedAgentsEffortMax: BetaManagedAgentsEffortMax
                    ) = betaManagedAgentsEffortMax.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Effort &&
                betaManagedAgentsEffortLevel == other.betaManagedAgentsEffortLevel &&
                betaManagedAgentsEffortLow == other.betaManagedAgentsEffortLow &&
                betaManagedAgentsEffortMedium == other.betaManagedAgentsEffortMedium &&
                betaManagedAgentsEffortHigh == other.betaManagedAgentsEffortHigh &&
                betaManagedAgentsEffortXhigh == other.betaManagedAgentsEffortXhigh &&
                betaManagedAgentsEffortMax == other.betaManagedAgentsEffortMax
        }

        override fun hashCode(): Int =
            Objects.hash(
                betaManagedAgentsEffortLevel,
                betaManagedAgentsEffortLow,
                betaManagedAgentsEffortMedium,
                betaManagedAgentsEffortHigh,
                betaManagedAgentsEffortXhigh,
                betaManagedAgentsEffortMax,
            )

        override fun toString(): String =
            when {
                betaManagedAgentsEffortLevel != null ->
                    "Effort{betaManagedAgentsEffortLevel=$betaManagedAgentsEffortLevel}"
                betaManagedAgentsEffortLow != null ->
                    "Effort{betaManagedAgentsEffortLow=$betaManagedAgentsEffortLow}"
                betaManagedAgentsEffortMedium != null ->
                    "Effort{betaManagedAgentsEffortMedium=$betaManagedAgentsEffortMedium}"
                betaManagedAgentsEffortHigh != null ->
                    "Effort{betaManagedAgentsEffortHigh=$betaManagedAgentsEffortHigh}"
                betaManagedAgentsEffortXhigh != null ->
                    "Effort{betaManagedAgentsEffortXhigh=$betaManagedAgentsEffortXhigh}"
                betaManagedAgentsEffortMax != null ->
                    "Effort{betaManagedAgentsEffortMax=$betaManagedAgentsEffortMax}"
                _json != null -> "Effort{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Effort")
            }

        companion object {

            /**
             * How hard Claude works on each turn. Higher levels favor reasoning depth over latency.
             * Not all models accept every level; invalid combinations are rejected at create time.
             */
            @JvmStatic
            fun ofBetaManagedAgentsEffortLevel(
                betaManagedAgentsEffortLevel: BetaManagedAgentsEffortLevel
            ) = Effort(betaManagedAgentsEffortLevel = betaManagedAgentsEffortLevel)

            /** Low effort. Favors latency over reasoning depth. */
            @JvmStatic
            fun ofBetaManagedAgentsEffortLow(
                betaManagedAgentsEffortLow: BetaManagedAgentsEffortLow
            ) = Effort(betaManagedAgentsEffortLow = betaManagedAgentsEffortLow)

            /** Medium effort. Balances latency and reasoning depth. */
            @JvmStatic
            fun ofBetaManagedAgentsEffortMedium(
                betaManagedAgentsEffortMedium: BetaManagedAgentsEffortMedium
            ) = Effort(betaManagedAgentsEffortMedium = betaManagedAgentsEffortMedium)

            /** High effort. Favors reasoning depth. */
            @JvmStatic
            fun ofBetaManagedAgentsEffortHigh(
                betaManagedAgentsEffortHigh: BetaManagedAgentsEffortHigh
            ) = Effort(betaManagedAgentsEffortHigh = betaManagedAgentsEffortHigh)

            /** Extra-high effort. Not all models accept this level. */
            @JvmStatic
            fun ofBetaManagedAgentsEffortXhigh(
                betaManagedAgentsEffortXhigh: BetaManagedAgentsEffortXhigh
            ) = Effort(betaManagedAgentsEffortXhigh = betaManagedAgentsEffortXhigh)

            /** Maximum effort. Favors reasoning depth over latency. */
            @JvmStatic
            fun ofBetaManagedAgentsEffortMax(
                betaManagedAgentsEffortMax: BetaManagedAgentsEffortMax
            ) = Effort(betaManagedAgentsEffortMax = betaManagedAgentsEffortMax)
        }

        /** An interface that defines how to map each variant of [Effort] to a value of type [T]. */
        interface Visitor<out T> {

            /**
             * How hard Claude works on each turn. Higher levels favor reasoning depth over latency.
             * Not all models accept every level; invalid combinations are rejected at create time.
             */
            fun visitBetaManagedAgentsEffortLevel(
                betaManagedAgentsEffortLevel: BetaManagedAgentsEffortLevel
            ): T

            /** Low effort. Favors latency over reasoning depth. */
            fun visitBetaManagedAgentsEffortLow(
                betaManagedAgentsEffortLow: BetaManagedAgentsEffortLow
            ): T

            /** Medium effort. Balances latency and reasoning depth. */
            fun visitBetaManagedAgentsEffortMedium(
                betaManagedAgentsEffortMedium: BetaManagedAgentsEffortMedium
            ): T

            /** High effort. Favors reasoning depth. */
            fun visitBetaManagedAgentsEffortHigh(
                betaManagedAgentsEffortHigh: BetaManagedAgentsEffortHigh
            ): T

            /** Extra-high effort. Not all models accept this level. */
            fun visitBetaManagedAgentsEffortXhigh(
                betaManagedAgentsEffortXhigh: BetaManagedAgentsEffortXhigh
            ): T

            /** Maximum effort. Favors reasoning depth over latency. */
            fun visitBetaManagedAgentsEffortMax(
                betaManagedAgentsEffortMax: BetaManagedAgentsEffortMax
            ): T

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

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEffortLevel>())
                                ?.let { Effort(betaManagedAgentsEffortLevel = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEffortLow>())
                                ?.let { Effort(betaManagedAgentsEffortLow = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEffortMedium>())
                                ?.let { Effort(betaManagedAgentsEffortMedium = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEffortHigh>())
                                ?.let { Effort(betaManagedAgentsEffortHigh = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEffortXhigh>())
                                ?.let { Effort(betaManagedAgentsEffortXhigh = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEffortMax>())
                                ?.let { Effort(betaManagedAgentsEffortMax = it, _json = json) },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> Effort(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Effort>(Effort::class) {

            override fun serialize(
                value: Effort,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.betaManagedAgentsEffortLevel != null ->
                        generator.writeObject(value.betaManagedAgentsEffortLevel)
                    value.betaManagedAgentsEffortLow != null ->
                        generator.writeObject(value.betaManagedAgentsEffortLow)
                    value.betaManagedAgentsEffortMedium != null ->
                        generator.writeObject(value.betaManagedAgentsEffortMedium)
                    value.betaManagedAgentsEffortHigh != null ->
                        generator.writeObject(value.betaManagedAgentsEffortHigh)
                    value.betaManagedAgentsEffortXhigh != null ->
                        generator.writeObject(value.betaManagedAgentsEffortXhigh)
                    value.betaManagedAgentsEffortMax != null ->
                        generator.writeObject(value.betaManagedAgentsEffortMax)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Effort")
                }
            }
        }

        /**
         * How hard Claude works on each turn. Higher levels favor reasoning depth over latency. Not
         * all models accept every level; invalid combinations are rejected at create time.
         */
        class BetaManagedAgentsEffortLevel
        @JsonCreator
        private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val LOW = of("low")

                @JvmField val MEDIUM = of("medium")

                @JvmField val HIGH = of("high")

                @JvmField val XHIGH = of("xhigh")

                @JvmField val MAX = of("max")

                @JvmStatic fun of(value: String) = BetaManagedAgentsEffortLevel(JsonField.of(value))
            }

            /** An enum containing [BetaManagedAgentsEffortLevel]'s known values. */
            enum class Known {
                LOW,
                MEDIUM,
                HIGH,
                XHIGH,
                MAX,
            }

            /**
             * An enum containing [BetaManagedAgentsEffortLevel]'s known values, as well as an
             * [_UNKNOWN] member.
             *
             * An instance of [BetaManagedAgentsEffortLevel] can contain an unknown value in a
             * couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                LOW,
                MEDIUM,
                HIGH,
                XHIGH,
                MAX,
                /**
                 * An enum member indicating that [BetaManagedAgentsEffortLevel] was instantiated
                 * with an unknown value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    LOW -> Value.LOW
                    MEDIUM -> Value.MEDIUM
                    HIGH -> Value.HIGH
                    XHIGH -> Value.XHIGH
                    MAX -> Value.MAX
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    LOW -> Known.LOW
                    MEDIUM -> Known.MEDIUM
                    HIGH -> Known.HIGH
                    XHIGH -> Known.XHIGH
                    MAX -> Known.MAX
                    else ->
                        throw AnthropicInvalidDataException(
                            "Unknown BetaManagedAgentsEffortLevel: $value"
                        )
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
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
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): BetaManagedAgentsEffortLevel = apply {
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

                return other is BetaManagedAgentsEffortLevel && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
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

        return other is BetaManagedAgentsModelConfigParams &&
            id == other.id &&
            effort == other.effort &&
            speed == other.speed &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(id, effort, speed, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsModelConfigParams{id=$id, effort=$effort, speed=$speed, additionalProperties=$additionalProperties}"
}
