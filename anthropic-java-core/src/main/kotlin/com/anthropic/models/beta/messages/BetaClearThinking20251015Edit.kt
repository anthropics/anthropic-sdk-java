// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
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

class BetaClearThinking20251015Edit
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val keep: JsonField<Keep>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("keep") @ExcludeMissing keep: JsonField<Keep> = JsonMissing.of(),
    ) : this(type, keep, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("clear_thinking_20251015")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Number of most recent assistant turns to keep thinking blocks for. Older turns will have
     * their thinking blocks removed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun keep(): Optional<Keep> = keep.getOptional("keep")

    /**
     * Returns the raw JSON value of [keep].
     *
     * Unlike [keep], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("keep") @ExcludeMissing fun _keep(): JsonField<Keep> = keep

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
         * [BetaClearThinking20251015Edit].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaClearThinking20251015Edit]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("clear_thinking_20251015")
        private var keep: JsonField<Keep> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaClearThinking20251015Edit: BetaClearThinking20251015Edit) = apply {
            type = betaClearThinking20251015Edit.type
            keep = betaClearThinking20251015Edit.keep
            additionalProperties = betaClearThinking20251015Edit.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("clear_thinking_20251015")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Number of most recent assistant turns to keep thinking blocks for. Older turns will have
         * their thinking blocks removed.
         */
        fun keep(keep: Keep) = keep(JsonField.of(keep))

        /**
         * Sets [Builder.keep] to an arbitrary JSON value.
         *
         * You should usually call [Builder.keep] with a well-typed [Keep] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun keep(keep: JsonField<Keep>) = apply { this.keep = keep }

        /** Alias for calling [keep] with `Keep.ofBetaThinkingTurns(betaThinkingTurns)`. */
        fun keep(betaThinkingTurns: BetaThinkingTurns) =
            keep(Keep.ofBetaThinkingTurns(betaThinkingTurns))

        /** Alias for calling [keep] with `Keep.ofBetaAllThinkingTurns(betaAllThinkingTurns)`. */
        fun keep(betaAllThinkingTurns: BetaAllThinkingTurns) =
            keep(Keep.ofBetaAllThinkingTurns(betaAllThinkingTurns))

        /** Alias for calling [keep] with `Keep.ofAll()`. */
        fun keepAll() = keep(Keep.ofAll())

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
         * Returns an immutable instance of [BetaClearThinking20251015Edit].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaClearThinking20251015Edit =
            BetaClearThinking20251015Edit(type, keep, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): BetaClearThinking20251015Edit = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("clear_thinking_20251015")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        keep().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("clear_thinking_20251015")) 1 else 0 } +
            (keep.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * Number of most recent assistant turns to keep thinking blocks for. Older turns will have
     * their thinking blocks removed.
     */
    @JsonDeserialize(using = Keep.Deserializer::class)
    @JsonSerialize(using = Keep.Serializer::class)
    class Keep
    private constructor(
        private val betaThinkingTurns: BetaThinkingTurns? = null,
        private val betaAllThinkingTurns: BetaAllThinkingTurns? = null,
        private val all: JsonValue? = null,
        private val _json: JsonValue? = null,
    ) {

        fun betaThinkingTurns(): Optional<BetaThinkingTurns> =
            Optional.ofNullable(betaThinkingTurns)

        fun betaAllThinkingTurns(): Optional<BetaAllThinkingTurns> =
            Optional.ofNullable(betaAllThinkingTurns)

        fun all(): Optional<JsonValue> = Optional.ofNullable(all)

        fun isBetaThinkingTurns(): Boolean = betaThinkingTurns != null

        fun isBetaAllThinkingTurns(): Boolean = betaAllThinkingTurns != null

        fun isAll(): Boolean = all != null

        fun asBetaThinkingTurns(): BetaThinkingTurns =
            betaThinkingTurns.getOrThrow("betaThinkingTurns")

        fun asBetaAllThinkingTurns(): BetaAllThinkingTurns =
            betaAllThinkingTurns.getOrThrow("betaAllThinkingTurns")

        fun asAll(): JsonValue = all.getOrThrow("all")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                betaThinkingTurns != null -> visitor.visitBetaThinkingTurns(betaThinkingTurns)
                betaAllThinkingTurns != null ->
                    visitor.visitBetaAllThinkingTurns(betaAllThinkingTurns)
                all != null -> visitor.visitAll(all)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Keep = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBetaThinkingTurns(betaThinkingTurns: BetaThinkingTurns) {
                        betaThinkingTurns.validate()
                    }

                    override fun visitBetaAllThinkingTurns(
                        betaAllThinkingTurns: BetaAllThinkingTurns
                    ) {
                        betaAllThinkingTurns.validate()
                    }

                    override fun visitAll(all: JsonValue) {
                        all.let {
                            if (it != JsonValue.from("all")) {
                                throw AnthropicInvalidDataException(
                                    "'all' is invalid, received $it"
                                )
                            }
                        }
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
                    override fun visitBetaThinkingTurns(betaThinkingTurns: BetaThinkingTurns) =
                        betaThinkingTurns.validity()

                    override fun visitBetaAllThinkingTurns(
                        betaAllThinkingTurns: BetaAllThinkingTurns
                    ) = betaAllThinkingTurns.validity()

                    override fun visitAll(all: JsonValue) =
                        all.let { if (it == JsonValue.from("all")) 1 else 0 }

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Keep &&
                betaThinkingTurns == other.betaThinkingTurns &&
                betaAllThinkingTurns == other.betaAllThinkingTurns &&
                all == other.all
        }

        override fun hashCode(): Int = Objects.hash(betaThinkingTurns, betaAllThinkingTurns, all)

        override fun toString(): String =
            when {
                betaThinkingTurns != null -> "Keep{betaThinkingTurns=$betaThinkingTurns}"
                betaAllThinkingTurns != null -> "Keep{betaAllThinkingTurns=$betaAllThinkingTurns}"
                all != null -> "Keep{all=$all}"
                _json != null -> "Keep{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Keep")
            }

        companion object {

            @JvmStatic
            fun ofBetaThinkingTurns(betaThinkingTurns: BetaThinkingTurns) =
                Keep(betaThinkingTurns = betaThinkingTurns)

            @JvmStatic
            fun ofBetaAllThinkingTurns(betaAllThinkingTurns: BetaAllThinkingTurns) =
                Keep(betaAllThinkingTurns = betaAllThinkingTurns)

            @JvmStatic fun ofAll() = Keep(all = JsonValue.from("all"))
        }

        /** An interface that defines how to map each variant of [Keep] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitBetaThinkingTurns(betaThinkingTurns: BetaThinkingTurns): T

            fun visitBetaAllThinkingTurns(betaAllThinkingTurns: BetaAllThinkingTurns): T

            fun visitAll(all: JsonValue): T

            /**
             * Maps an unknown variant of [Keep] to a value of type [T].
             *
             * An instance of [Keep] can contain an unknown variant if it was deserialized from data
             * that doesn't match any known variant. For example, if the SDK is on an older version
             * than the API, then the API may respond with new variants that the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Keep: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Keep>(Keep::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Keep {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<JsonValue>())
                                ?.let { Keep(all = it, _json = json) }
                                ?.takeIf { it.isValid() },
                            tryDeserialize(node, jacksonTypeRef<BetaThinkingTurns>())?.let {
                                Keep(betaThinkingTurns = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<BetaAllThinkingTurns>())?.let {
                                Keep(betaAllThinkingTurns = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from array).
                    0 -> Keep(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Keep>(Keep::class) {

            override fun serialize(
                value: Keep,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.betaThinkingTurns != null ->
                        generator.writeObject(value.betaThinkingTurns)
                    value.betaAllThinkingTurns != null ->
                        generator.writeObject(value.betaAllThinkingTurns)
                    value.all != null -> generator.writeObject(value.all)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Keep")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClearThinking20251015Edit &&
            type == other.type &&
            keep == other.keep &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, keep, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaClearThinking20251015Edit{type=$type, keep=$keep, additionalProperties=$additionalProperties}"
}
