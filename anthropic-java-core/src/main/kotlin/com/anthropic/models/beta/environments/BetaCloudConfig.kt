// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

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

/** `cloud` environment configuration. */
class BetaCloudConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val networking: JsonField<Networking>,
    private val packages: JsonField<BetaPackages>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("networking")
        @ExcludeMissing
        networking: JsonField<Networking> = JsonMissing.of(),
        @JsonProperty("packages")
        @ExcludeMissing
        packages: JsonField<BetaPackages> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(networking, packages, type, mutableMapOf())

    /**
     * Network configuration policy.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun networking(): Networking = networking.getRequired("networking")

    /**
     * Package manager configuration.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun packages(): BetaPackages = packages.getRequired("packages")

    /**
     * Environment type
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("cloud")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [networking].
     *
     * Unlike [networking], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("networking")
    @ExcludeMissing
    fun _networking(): JsonField<Networking> = networking

    /**
     * Returns the raw JSON value of [packages].
     *
     * Unlike [packages], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("packages") @ExcludeMissing fun _packages(): JsonField<BetaPackages> = packages

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
         * Returns a mutable builder for constructing an instance of [BetaCloudConfig].
         *
         * The following fields are required:
         * ```java
         * .networking()
         * .packages()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCloudConfig]. */
    class Builder internal constructor() {

        private var networking: JsonField<Networking>? = null
        private var packages: JsonField<BetaPackages>? = null
        private var type: JsonValue = JsonValue.from("cloud")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCloudConfig: BetaCloudConfig) = apply {
            networking = betaCloudConfig.networking
            packages = betaCloudConfig.packages
            type = betaCloudConfig.type
            additionalProperties = betaCloudConfig.additionalProperties.toMutableMap()
        }

        /** Network configuration policy. */
        fun networking(networking: Networking) = networking(JsonField.of(networking))

        /**
         * Sets [Builder.networking] to an arbitrary JSON value.
         *
         * You should usually call [Builder.networking] with a well-typed [Networking] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun networking(networking: JsonField<Networking>) = apply { this.networking = networking }

        /** Alias for calling [networking] with `Networking.ofUnrestricted(unrestricted)`. */
        fun networking(unrestricted: BetaUnrestrictedNetwork) =
            networking(Networking.ofUnrestricted(unrestricted))

        /** Alias for calling [networking] with `Networking.ofLimited(limited)`. */
        fun networking(limited: BetaLimitedNetwork) = networking(Networking.ofLimited(limited))

        /** Package manager configuration. */
        fun packages(packages: BetaPackages) = packages(JsonField.of(packages))

        /**
         * Sets [Builder.packages] to an arbitrary JSON value.
         *
         * You should usually call [Builder.packages] with a well-typed [BetaPackages] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun packages(packages: JsonField<BetaPackages>) = apply { this.packages = packages }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("cloud")
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
         * Returns an immutable instance of [BetaCloudConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .networking()
         * .packages()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaCloudConfig =
            BetaCloudConfig(
                checkRequired("networking", networking),
                checkRequired("packages", packages),
                type,
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
    fun validate(): BetaCloudConfig = apply {
        if (validated) {
            return@apply
        }

        networking().validate()
        packages().validate()
        _type().let {
            if (it != JsonValue.from("cloud")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
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
        (networking.asKnown().getOrNull()?.validity() ?: 0) +
            (packages.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("cloud")) 1 else 0 }

    /** Network configuration policy. */
    @JsonDeserialize(using = Networking.Deserializer::class)
    @JsonSerialize(using = Networking.Serializer::class)
    class Networking
    private constructor(
        private val unrestricted: BetaUnrestrictedNetwork? = null,
        private val limited: BetaLimitedNetwork? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Unrestricted network access. */
        fun unrestricted(): Optional<BetaUnrestrictedNetwork> = Optional.ofNullable(unrestricted)

        /** Limited network access. */
        fun limited(): Optional<BetaLimitedNetwork> = Optional.ofNullable(limited)

        fun isUnrestricted(): Boolean = unrestricted != null

        fun isLimited(): Boolean = limited != null

        /** Unrestricted network access. */
        fun asUnrestricted(): BetaUnrestrictedNetwork = unrestricted.getOrThrow("unrestricted")

        /** Limited network access. */
        fun asLimited(): BetaLimitedNetwork = limited.getOrThrow("limited")

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
         * Optional<String> result = networking.accept(new Networking.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitUnrestricted(BetaUnrestrictedNetwork unrestricted) {
         *         return Optional.of(unrestricted.toString());
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
                unrestricted != null -> visitor.visitUnrestricted(unrestricted)
                limited != null -> visitor.visitLimited(limited)
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
        fun validate(): Networking = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitUnrestricted(unrestricted: BetaUnrestrictedNetwork) {
                        unrestricted.validate()
                    }

                    override fun visitLimited(limited: BetaLimitedNetwork) {
                        limited.validate()
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
                    override fun visitUnrestricted(unrestricted: BetaUnrestrictedNetwork) =
                        unrestricted.validity()

                    override fun visitLimited(limited: BetaLimitedNetwork) = limited.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Networking &&
                unrestricted == other.unrestricted &&
                limited == other.limited
        }

        override fun hashCode(): Int = Objects.hash(unrestricted, limited)

        override fun toString(): String =
            when {
                unrestricted != null -> "Networking{unrestricted=$unrestricted}"
                limited != null -> "Networking{limited=$limited}"
                _json != null -> "Networking{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Networking")
            }

        companion object {

            /** Unrestricted network access. */
            @JvmStatic
            fun ofUnrestricted(unrestricted: BetaUnrestrictedNetwork) =
                Networking(unrestricted = unrestricted)

            /** Limited network access. */
            @JvmStatic fun ofLimited(limited: BetaLimitedNetwork) = Networking(limited = limited)
        }

        /**
         * An interface that defines how to map each variant of [Networking] to a value of type [T].
         */
        interface Visitor<out T> {

            /** Unrestricted network access. */
            fun visitUnrestricted(unrestricted: BetaUnrestrictedNetwork): T

            /** Limited network access. */
            fun visitLimited(limited: BetaLimitedNetwork): T

            /**
             * Maps an unknown variant of [Networking] to a value of type [T].
             *
             * An instance of [Networking] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Networking: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Networking>(Networking::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Networking {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "unrestricted" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaUnrestrictedNetwork>())
                            ?.let { Networking(unrestricted = it, _json = json) }
                            ?: Networking(_json = json)
                    }
                    "limited" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaLimitedNetwork>())?.let {
                            Networking(limited = it, _json = json)
                        } ?: Networking(_json = json)
                    }
                }

                return Networking(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Networking>(Networking::class) {

            override fun serialize(
                value: Networking,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.unrestricted != null -> generator.writeObject(value.unrestricted)
                    value.limited != null -> generator.writeObject(value.limited)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Networking")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaCloudConfig &&
            networking == other.networking &&
            packages == other.packages &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(networking, packages, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCloudConfig{networking=$networking, packages=$packages, type=$type, additionalProperties=$additionalProperties}"
}
