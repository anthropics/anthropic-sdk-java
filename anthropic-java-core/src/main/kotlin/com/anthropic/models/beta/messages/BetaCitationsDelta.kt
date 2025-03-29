// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
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

class BetaCitationsDelta
private constructor(
    private val citation: JsonField<Citation>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("citation") @ExcludeMissing citation: JsonField<Citation> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(citation, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun citation(): Citation = citation.getRequired("citation")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("citations_delta")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [citation].
     *
     * Unlike [citation], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("citation") @ExcludeMissing fun _citation(): JsonField<Citation> = citation

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
         * Returns a mutable builder for constructing an instance of [BetaCitationsDelta].
         *
         * The following fields are required:
         * ```java
         * .citation()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCitationsDelta]. */
    class Builder internal constructor() {

        private var citation: JsonField<Citation>? = null
        private var type: JsonValue = JsonValue.from("citations_delta")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCitationsDelta: BetaCitationsDelta) = apply {
            citation = betaCitationsDelta.citation
            type = betaCitationsDelta.type
            additionalProperties = betaCitationsDelta.additionalProperties.toMutableMap()
        }

        fun citation(citation: Citation) = citation(JsonField.of(citation))

        /**
         * Sets [Builder.citation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citation] with a well-typed [Citation] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun citation(citation: JsonField<Citation>) = apply { this.citation = citation }

        /**
         * Alias for calling [citation] with
         * `Citation.ofBetaCitationCharLocation(betaCitationCharLocation)`.
         */
        fun citation(betaCitationCharLocation: BetaCitationCharLocation) =
            citation(Citation.ofBetaCitationCharLocation(betaCitationCharLocation))

        /**
         * Alias for calling [citation] with
         * `Citation.ofBetaCitationPageLocation(betaCitationPageLocation)`.
         */
        fun citation(betaCitationPageLocation: BetaCitationPageLocation) =
            citation(Citation.ofBetaCitationPageLocation(betaCitationPageLocation))

        /**
         * Alias for calling [citation] with
         * `Citation.ofBetaCitationContentBlockLocation(betaCitationContentBlockLocation)`.
         */
        fun citation(betaCitationContentBlockLocation: BetaCitationContentBlockLocation) =
            citation(Citation.ofBetaCitationContentBlockLocation(betaCitationContentBlockLocation))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("citations_delta")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaCitationsDelta].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .citation()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaCitationsDelta =
            BetaCitationsDelta(
                checkRequired("citation", citation),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaCitationsDelta = apply {
        if (validated) {
            return@apply
        }

        citation().validate()
        _type().let {
            if (it != JsonValue.from("citations_delta")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    @JsonDeserialize(using = Citation.Deserializer::class)
    @JsonSerialize(using = Citation.Serializer::class)
    class Citation
    private constructor(
        private val betaCitationCharLocation: BetaCitationCharLocation? = null,
        private val betaCitationPageLocation: BetaCitationPageLocation? = null,
        private val betaCitationContentBlockLocation: BetaCitationContentBlockLocation? = null,
        private val _json: JsonValue? = null,
    ) {

        fun betaCitationCharLocation(): Optional<BetaCitationCharLocation> =
            Optional.ofNullable(betaCitationCharLocation)

        fun betaCitationPageLocation(): Optional<BetaCitationPageLocation> =
            Optional.ofNullable(betaCitationPageLocation)

        fun betaCitationContentBlockLocation(): Optional<BetaCitationContentBlockLocation> =
            Optional.ofNullable(betaCitationContentBlockLocation)

        fun isBetaCitationCharLocation(): Boolean = betaCitationCharLocation != null

        fun isBetaCitationPageLocation(): Boolean = betaCitationPageLocation != null

        fun isBetaCitationContentBlockLocation(): Boolean = betaCitationContentBlockLocation != null

        fun asBetaCitationCharLocation(): BetaCitationCharLocation =
            betaCitationCharLocation.getOrThrow("betaCitationCharLocation")

        fun asBetaCitationPageLocation(): BetaCitationPageLocation =
            betaCitationPageLocation.getOrThrow("betaCitationPageLocation")

        fun asBetaCitationContentBlockLocation(): BetaCitationContentBlockLocation =
            betaCitationContentBlockLocation.getOrThrow("betaCitationContentBlockLocation")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                betaCitationCharLocation != null ->
                    visitor.visitBetaCitationCharLocation(betaCitationCharLocation)
                betaCitationPageLocation != null ->
                    visitor.visitBetaCitationPageLocation(betaCitationPageLocation)
                betaCitationContentBlockLocation != null ->
                    visitor.visitBetaCitationContentBlockLocation(betaCitationContentBlockLocation)
                else -> visitor.unknown(_json)
            }
        }

        private var validated: Boolean = false

        fun validate(): Citation = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBetaCitationCharLocation(
                        betaCitationCharLocation: BetaCitationCharLocation
                    ) {
                        betaCitationCharLocation.validate()
                    }

                    override fun visitBetaCitationPageLocation(
                        betaCitationPageLocation: BetaCitationPageLocation
                    ) {
                        betaCitationPageLocation.validate()
                    }

                    override fun visitBetaCitationContentBlockLocation(
                        betaCitationContentBlockLocation: BetaCitationContentBlockLocation
                    ) {
                        betaCitationContentBlockLocation.validate()
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Citation && betaCitationCharLocation == other.betaCitationCharLocation && betaCitationPageLocation == other.betaCitationPageLocation && betaCitationContentBlockLocation == other.betaCitationContentBlockLocation /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaCitationCharLocation, betaCitationPageLocation, betaCitationContentBlockLocation) /* spotless:on */

        override fun toString(): String =
            when {
                betaCitationCharLocation != null ->
                    "Citation{betaCitationCharLocation=$betaCitationCharLocation}"
                betaCitationPageLocation != null ->
                    "Citation{betaCitationPageLocation=$betaCitationPageLocation}"
                betaCitationContentBlockLocation != null ->
                    "Citation{betaCitationContentBlockLocation=$betaCitationContentBlockLocation}"
                _json != null -> "Citation{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Citation")
            }

        companion object {

            @JvmStatic
            fun ofBetaCitationCharLocation(betaCitationCharLocation: BetaCitationCharLocation) =
                Citation(betaCitationCharLocation = betaCitationCharLocation)

            @JvmStatic
            fun ofBetaCitationPageLocation(betaCitationPageLocation: BetaCitationPageLocation) =
                Citation(betaCitationPageLocation = betaCitationPageLocation)

            @JvmStatic
            fun ofBetaCitationContentBlockLocation(
                betaCitationContentBlockLocation: BetaCitationContentBlockLocation
            ) = Citation(betaCitationContentBlockLocation = betaCitationContentBlockLocation)
        }

        /**
         * An interface that defines how to map each variant of [Citation] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitBetaCitationCharLocation(betaCitationCharLocation: BetaCitationCharLocation): T

            fun visitBetaCitationPageLocation(betaCitationPageLocation: BetaCitationPageLocation): T

            fun visitBetaCitationContentBlockLocation(
                betaCitationContentBlockLocation: BetaCitationContentBlockLocation
            ): T

            /**
             * Maps an unknown variant of [Citation] to a value of type [T].
             *
             * An instance of [Citation] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Citation: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Citation>(Citation::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Citation {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "char_location" -> {
                        return Citation(
                            betaCitationCharLocation =
                                deserialize(node, jacksonTypeRef<BetaCitationCharLocation>()),
                            _json = json,
                        )
                    }
                    "page_location" -> {
                        return Citation(
                            betaCitationPageLocation =
                                deserialize(node, jacksonTypeRef<BetaCitationPageLocation>()),
                            _json = json,
                        )
                    }
                    "content_block_location" -> {
                        return Citation(
                            betaCitationContentBlockLocation =
                                deserialize(
                                    node,
                                    jacksonTypeRef<BetaCitationContentBlockLocation>(),
                                ),
                            _json = json,
                        )
                    }
                }

                return Citation(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Citation>(Citation::class) {

            override fun serialize(
                value: Citation,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.betaCitationCharLocation != null ->
                        generator.writeObject(value.betaCitationCharLocation)
                    value.betaCitationPageLocation != null ->
                        generator.writeObject(value.betaCitationPageLocation)
                    value.betaCitationContentBlockLocation != null ->
                        generator.writeObject(value.betaCitationContentBlockLocation)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Citation")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaCitationsDelta && citation == other.citation && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(citation, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCitationsDelta{citation=$citation, type=$type, additionalProperties=$additionalProperties}"
}
