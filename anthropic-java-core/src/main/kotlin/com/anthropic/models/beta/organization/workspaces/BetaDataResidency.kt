// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
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

class BetaDataResidency
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val allowedInferenceGeos: JsonField<AllowedInferenceGeos>,
    private val defaultInferenceGeo: JsonField<String>,
    private val workspaceGeo: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("allowed_inference_geos")
        @ExcludeMissing
        allowedInferenceGeos: JsonField<AllowedInferenceGeos> = JsonMissing.of(),
        @JsonProperty("default_inference_geo")
        @ExcludeMissing
        defaultInferenceGeo: JsonField<String> = JsonMissing.of(),
        @JsonProperty("workspace_geo")
        @ExcludeMissing
        workspaceGeo: JsonField<String> = JsonMissing.of(),
    ) : this(allowedInferenceGeos, defaultInferenceGeo, workspaceGeo, mutableMapOf())

    /**
     * Permitted inference geo values. 'unrestricted' means all geos are allowed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun allowedInferenceGeos(): AllowedInferenceGeos =
        allowedInferenceGeos.getRequired("allowed_inference_geos")

    /**
     * Default inference geo applied when requests omit the parameter.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun defaultInferenceGeo(): String = defaultInferenceGeo.getRequired("default_inference_geo")

    /**
     * Geographic region for workspace data storage. Immutable after creation.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceGeo(): String = workspaceGeo.getRequired("workspace_geo")

    /**
     * Returns the raw JSON value of [allowedInferenceGeos].
     *
     * Unlike [allowedInferenceGeos], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("allowed_inference_geos")
    @ExcludeMissing
    fun _allowedInferenceGeos(): JsonField<AllowedInferenceGeos> = allowedInferenceGeos

    /**
     * Returns the raw JSON value of [defaultInferenceGeo].
     *
     * Unlike [defaultInferenceGeo], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("default_inference_geo")
    @ExcludeMissing
    fun _defaultInferenceGeo(): JsonField<String> = defaultInferenceGeo

    /**
     * Returns the raw JSON value of [workspaceGeo].
     *
     * Unlike [workspaceGeo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_geo")
    @ExcludeMissing
    fun _workspaceGeo(): JsonField<String> = workspaceGeo

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
         * Returns a mutable builder for constructing an instance of [BetaDataResidency].
         *
         * The following fields are required:
         * ```java
         * .allowedInferenceGeos()
         * .defaultInferenceGeo()
         * .workspaceGeo()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaDataResidency]. */
    class Builder internal constructor() {

        private var allowedInferenceGeos: JsonField<AllowedInferenceGeos>? = null
        private var defaultInferenceGeo: JsonField<String>? = null
        private var workspaceGeo: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaDataResidency: BetaDataResidency) = apply {
            allowedInferenceGeos = betaDataResidency.allowedInferenceGeos
            defaultInferenceGeo = betaDataResidency.defaultInferenceGeo
            workspaceGeo = betaDataResidency.workspaceGeo
            additionalProperties = betaDataResidency.additionalProperties.toMutableMap()
        }

        /** Permitted inference geo values. 'unrestricted' means all geos are allowed. */
        fun allowedInferenceGeos(allowedInferenceGeos: AllowedInferenceGeos) =
            allowedInferenceGeos(JsonField.of(allowedInferenceGeos))

        /**
         * Sets [Builder.allowedInferenceGeos] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowedInferenceGeos] with a well-typed
         * [AllowedInferenceGeos] value instead. This method is primarily for setting the field to
         * an undocumented or not yet supported value.
         */
        fun allowedInferenceGeos(allowedInferenceGeos: JsonField<AllowedInferenceGeos>) = apply {
            this.allowedInferenceGeos = allowedInferenceGeos
        }

        /** Alias for calling [allowedInferenceGeos] with `AllowedInferenceGeos.ofGeos(geos)`. */
        fun allowedInferenceGeosOfGeos(geos: List<String>) =
            allowedInferenceGeos(AllowedInferenceGeos.ofGeos(geos))

        /**
         * Alias for calling [allowedInferenceGeos] with `AllowedInferenceGeos.ofUnrestricted()`.
         */
        fun allowedInferenceGeosUnrestricted() =
            allowedInferenceGeos(AllowedInferenceGeos.ofUnrestricted())

        /** Default inference geo applied when requests omit the parameter. */
        fun defaultInferenceGeo(defaultInferenceGeo: String) =
            defaultInferenceGeo(JsonField.of(defaultInferenceGeo))

        /**
         * Sets [Builder.defaultInferenceGeo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.defaultInferenceGeo] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun defaultInferenceGeo(defaultInferenceGeo: JsonField<String>) = apply {
            this.defaultInferenceGeo = defaultInferenceGeo
        }

        /** Geographic region for workspace data storage. Immutable after creation. */
        fun workspaceGeo(workspaceGeo: String) = workspaceGeo(JsonField.of(workspaceGeo))

        /**
         * Sets [Builder.workspaceGeo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceGeo] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workspaceGeo(workspaceGeo: JsonField<String>) = apply {
            this.workspaceGeo = workspaceGeo
        }

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
         * Returns an immutable instance of [BetaDataResidency].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .allowedInferenceGeos()
         * .defaultInferenceGeo()
         * .workspaceGeo()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaDataResidency =
            BetaDataResidency(
                checkRequired("allowedInferenceGeos", allowedInferenceGeos),
                checkRequired("defaultInferenceGeo", defaultInferenceGeo),
                checkRequired("workspaceGeo", workspaceGeo),
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
    fun validate(): BetaDataResidency = apply {
        if (validated) {
            return@apply
        }

        allowedInferenceGeos().validate()
        defaultInferenceGeo()
        workspaceGeo()
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
        (allowedInferenceGeos.asKnown().getOrNull()?.validity() ?: 0) +
            (if (defaultInferenceGeo.asKnown().isPresent) 1 else 0) +
            (if (workspaceGeo.asKnown().isPresent) 1 else 0)

    /** Permitted inference geo values. 'unrestricted' means all geos are allowed. */
    @JsonDeserialize(using = AllowedInferenceGeos.Deserializer::class)
    @JsonSerialize(using = AllowedInferenceGeos.Serializer::class)
    class AllowedInferenceGeos
    private constructor(
        private val geos: List<String>? = null,
        private val unrestricted: JsonValue? = null,
        private val _json: JsonValue? = null,
    ) {

        fun geos(): Optional<List<String>> = Optional.ofNullable(geos)

        fun unrestricted(): Optional<JsonValue> = Optional.ofNullable(unrestricted)

        fun isGeos(): Boolean = geos != null

        fun isUnrestricted(): Boolean = unrestricted != null

        fun asGeos(): List<String> = geos.getOrThrow("geos")

        fun asUnrestricted(): JsonValue = unrestricted.getOrThrow("unrestricted")

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
         * Optional<String> result = allowedInferenceGeos.accept(new AllowedInferenceGeos.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitGeos(List<String> geos) {
         *         return Optional.of(geos.toString());
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
                geos != null -> visitor.visitGeos(geos)
                unrestricted != null -> visitor.visitUnrestricted(unrestricted)
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
        fun validate(): AllowedInferenceGeos = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitGeos(geos: List<String>) {}

                    override fun visitUnrestricted(unrestricted: JsonValue) {
                        unrestricted.let {
                            if (it != JsonValue.from("unrestricted")) {
                                throw AnthropicInvalidDataException(
                                    "'unrestricted' is invalid, received $it"
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
                    override fun visitGeos(geos: List<String>) = geos.size

                    override fun visitUnrestricted(unrestricted: JsonValue) =
                        unrestricted.let { if (it == JsonValue.from("unrestricted")) 1 else 0 }

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is AllowedInferenceGeos &&
                geos == other.geos &&
                unrestricted == other.unrestricted
        }

        override fun hashCode(): Int = Objects.hash(geos, unrestricted)

        override fun toString(): String =
            when {
                geos != null -> "AllowedInferenceGeos{geos=$geos}"
                unrestricted != null -> "AllowedInferenceGeos{unrestricted=$unrestricted}"
                _json != null -> "AllowedInferenceGeos{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid AllowedInferenceGeos")
            }

        companion object {

            @JvmStatic
            fun ofGeos(geos: List<String>) = AllowedInferenceGeos(geos = geos.toImmutable())

            @JvmStatic
            fun ofUnrestricted() =
                AllowedInferenceGeos(unrestricted = JsonValue.from("unrestricted"))
        }

        /**
         * An interface that defines how to map each variant of [AllowedInferenceGeos] to a value of
         * type [T].
         */
        interface Visitor<out T> {

            fun visitGeos(geos: List<String>): T

            fun visitUnrestricted(unrestricted: JsonValue): T

            /**
             * Maps an unknown variant of [AllowedInferenceGeos] to a value of type [T].
             *
             * An instance of [AllowedInferenceGeos] can contain an unknown variant if it was
             * deserialized from data that doesn't match any known variant. For example, if the SDK
             * is on an older version than the API, then the API may respond with new variants that
             * the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown AllowedInferenceGeos: $json")
            }
        }

        internal class Deserializer :
            BaseDeserializer<AllowedInferenceGeos>(AllowedInferenceGeos::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): AllowedInferenceGeos {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<JsonValue>())
                                ?.let { AllowedInferenceGeos(unrestricted = it, _json = json) }
                                ?.takeIf { it.isValid() },
                            tryDeserialize(node, jacksonTypeRef<List<String>>())?.let {
                                AllowedInferenceGeos(geos = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> AllowedInferenceGeos(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer :
            BaseSerializer<AllowedInferenceGeos>(AllowedInferenceGeos::class) {

            override fun serialize(
                value: AllowedInferenceGeos,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.geos != null -> generator.writeObject(value.geos)
                    value.unrestricted != null -> generator.writeObject(value.unrestricted)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid AllowedInferenceGeos")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaDataResidency &&
            allowedInferenceGeos == other.allowedInferenceGeos &&
            defaultInferenceGeo == other.defaultInferenceGeo &&
            workspaceGeo == other.workspaceGeo &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(allowedInferenceGeos, defaultInferenceGeo, workspaceGeo, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaDataResidency{allowedInferenceGeos=$allowedInferenceGeos, defaultInferenceGeo=$defaultInferenceGeo, workspaceGeo=$workspaceGeo, additionalProperties=$additionalProperties}"
}
