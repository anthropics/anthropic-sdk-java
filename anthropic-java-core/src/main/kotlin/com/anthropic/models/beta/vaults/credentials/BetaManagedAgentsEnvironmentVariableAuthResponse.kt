// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

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

/** Environment variable credential details. The secret value is never returned. */
class BetaManagedAgentsEnvironmentVariableAuthResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val injectionLocation: JsonField<BetaManagedAgentsInjectionLocationResponse>,
    private val networking: JsonField<Networking>,
    private val secretName: JsonField<String>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("injection_location")
        @ExcludeMissing
        injectionLocation: JsonField<BetaManagedAgentsInjectionLocationResponse> = JsonMissing.of(),
        @JsonProperty("networking")
        @ExcludeMissing
        networking: JsonField<Networking> = JsonMissing.of(),
        @JsonProperty("secret_name")
        @ExcludeMissing
        secretName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(injectionLocation, networking, secretName, type, mutableMapOf())

    /**
     * Where in the outbound request the secret value is substituted.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun injectionLocation(): BetaManagedAgentsInjectionLocationResponse =
        injectionLocation.getRequired("injection_location")

    /**
     * Outbound hosts the secret value is substituted on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun networking(): Networking = networking.getRequired("networking")

    /**
     * Name of the environment variable.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun secretName(): String = secretName.getRequired("secret_name")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Returns the raw JSON value of [injectionLocation].
     *
     * Unlike [injectionLocation], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("injection_location")
    @ExcludeMissing
    fun _injectionLocation(): JsonField<BetaManagedAgentsInjectionLocationResponse> =
        injectionLocation

    /**
     * Returns the raw JSON value of [networking].
     *
     * Unlike [networking], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("networking")
    @ExcludeMissing
    fun _networking(): JsonField<Networking> = networking

    /**
     * Returns the raw JSON value of [secretName].
     *
     * Unlike [secretName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("secret_name") @ExcludeMissing fun _secretName(): JsonField<String> = secretName

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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
         * [BetaManagedAgentsEnvironmentVariableAuthResponse].
         *
         * The following fields are required:
         * ```java
         * .injectionLocation()
         * .networking()
         * .secretName()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsEnvironmentVariableAuthResponse]. */
    class Builder internal constructor() {

        private var injectionLocation: JsonField<BetaManagedAgentsInjectionLocationResponse>? = null
        private var networking: JsonField<Networking>? = null
        private var secretName: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsEnvironmentVariableAuthResponse:
                BetaManagedAgentsEnvironmentVariableAuthResponse
        ) = apply {
            injectionLocation = betaManagedAgentsEnvironmentVariableAuthResponse.injectionLocation
            networking = betaManagedAgentsEnvironmentVariableAuthResponse.networking
            secretName = betaManagedAgentsEnvironmentVariableAuthResponse.secretName
            type = betaManagedAgentsEnvironmentVariableAuthResponse.type
            additionalProperties =
                betaManagedAgentsEnvironmentVariableAuthResponse.additionalProperties.toMutableMap()
        }

        /** Where in the outbound request the secret value is substituted. */
        fun injectionLocation(injectionLocation: BetaManagedAgentsInjectionLocationResponse) =
            injectionLocation(JsonField.of(injectionLocation))

        /**
         * Sets [Builder.injectionLocation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.injectionLocation] with a well-typed
         * [BetaManagedAgentsInjectionLocationResponse] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun injectionLocation(
            injectionLocation: JsonField<BetaManagedAgentsInjectionLocationResponse>
        ) = apply { this.injectionLocation = injectionLocation }

        /** Outbound hosts the secret value is substituted on. */
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
        fun networking(unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingResponse) =
            networking(Networking.ofUnrestricted(unrestricted))

        /** Alias for calling [networking] with `Networking.ofLimited(limited)`. */
        fun networking(limited: BetaManagedAgentsLimitedCredentialNetworkingResponse) =
            networking(Networking.ofLimited(limited))

        /**
         * Alias for calling [networking] with the following:
         * ```java
         * BetaManagedAgentsLimitedCredentialNetworkingResponse.builder()
         *     .type(BetaManagedAgentsLimitedCredentialNetworkingResponse.Type.LIMITED)
         *     .allowedHosts(allowedHosts)
         *     .build()
         * ```
         */
        fun limitedNetworking(allowedHosts: List<String>) =
            networking(
                BetaManagedAgentsLimitedCredentialNetworkingResponse.builder()
                    .type(BetaManagedAgentsLimitedCredentialNetworkingResponse.Type.LIMITED)
                    .allowedHosts(allowedHosts)
                    .build()
            )

        /** Name of the environment variable. */
        fun secretName(secretName: String) = secretName(JsonField.of(secretName))

        /**
         * Sets [Builder.secretName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.secretName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun secretName(secretName: JsonField<String>) = apply { this.secretName = secretName }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaManagedAgentsEnvironmentVariableAuthResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .injectionLocation()
         * .networking()
         * .secretName()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsEnvironmentVariableAuthResponse =
            BetaManagedAgentsEnvironmentVariableAuthResponse(
                checkRequired("injectionLocation", injectionLocation),
                checkRequired("networking", networking),
                checkRequired("secretName", secretName),
                checkRequired("type", type),
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
    fun validate(): BetaManagedAgentsEnvironmentVariableAuthResponse = apply {
        if (validated) {
            return@apply
        }

        injectionLocation().validate()
        networking().validate()
        secretName()
        type().validate()
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
        (injectionLocation.asKnown().getOrNull()?.validity() ?: 0) +
            (networking.asKnown().getOrNull()?.validity() ?: 0) +
            (if (secretName.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0)

    /** Outbound hosts the secret value is substituted on. */
    @JsonDeserialize(using = Networking.Deserializer::class)
    @JsonSerialize(using = Networking.Serializer::class)
    class Networking
    private constructor(
        private val unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingResponse? = null,
        private val limited: BetaManagedAgentsLimitedCredentialNetworkingResponse? = null,
        private val _json: JsonValue? = null,
    ) {

        /**
         * The secret is substituted on any host the session's Environment network policy permits
         * egress to.
         */
        fun unrestricted(): Optional<BetaManagedAgentsUnrestrictedCredentialNetworkingResponse> =
            Optional.ofNullable(unrestricted)

        /** The secret is substituted only on requests to the listed hosts. */
        fun limited(): Optional<BetaManagedAgentsLimitedCredentialNetworkingResponse> =
            Optional.ofNullable(limited)

        fun isUnrestricted(): Boolean = unrestricted != null

        fun isLimited(): Boolean = limited != null

        /**
         * The secret is substituted on any host the session's Environment network policy permits
         * egress to.
         */
        fun asUnrestricted(): BetaManagedAgentsUnrestrictedCredentialNetworkingResponse =
            unrestricted.getOrThrow("unrestricted")

        /** The secret is substituted only on requests to the listed hosts. */
        fun asLimited(): BetaManagedAgentsLimitedCredentialNetworkingResponse =
            limited.getOrThrow("limited")

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
         *     public Optional<String> visitUnrestricted(BetaManagedAgentsUnrestrictedCredentialNetworkingResponse unrestricted) {
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
                    override fun visitUnrestricted(
                        unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingResponse
                    ) {
                        unrestricted.validate()
                    }

                    override fun visitLimited(
                        limited: BetaManagedAgentsLimitedCredentialNetworkingResponse
                    ) {
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
                    override fun visitUnrestricted(
                        unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingResponse
                    ) = unrestricted.validity()

                    override fun visitLimited(
                        limited: BetaManagedAgentsLimitedCredentialNetworkingResponse
                    ) = limited.validity()

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

            /**
             * The secret is substituted on any host the session's Environment network policy
             * permits egress to.
             */
            @JvmStatic
            fun ofUnrestricted(
                unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingResponse
            ) = Networking(unrestricted = unrestricted)

            /** The secret is substituted only on requests to the listed hosts. */
            @JvmStatic
            fun ofLimited(limited: BetaManagedAgentsLimitedCredentialNetworkingResponse) =
                Networking(limited = limited)
        }

        /**
         * An interface that defines how to map each variant of [Networking] to a value of type [T].
         */
        interface Visitor<out T> {

            /**
             * The secret is substituted on any host the session's Environment network policy
             * permits egress to.
             */
            fun visitUnrestricted(
                unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingResponse
            ): T

            /** The secret is substituted only on requests to the listed hosts. */
            fun visitLimited(limited: BetaManagedAgentsLimitedCredentialNetworkingResponse): T

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
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<
                                    BetaManagedAgentsUnrestrictedCredentialNetworkingResponse
                                >(),
                            )
                            ?.let { Networking(unrestricted = it, _json = json) }
                            ?: Networking(_json = json)
                    }
                    "limited" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<
                                    BetaManagedAgentsLimitedCredentialNetworkingResponse
                                >(),
                            )
                            ?.let { Networking(limited = it, _json = json) }
                            ?: Networking(_json = json)
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

            @JvmField val ENVIRONMENT_VARIABLE = of("environment_variable")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            ENVIRONMENT_VARIABLE
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
            ENVIRONMENT_VARIABLE,
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
                ENVIRONMENT_VARIABLE -> Value.ENVIRONMENT_VARIABLE
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
                ENVIRONMENT_VARIABLE -> Known.ENVIRONMENT_VARIABLE
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsEnvironmentVariableAuthResponse &&
            injectionLocation == other.injectionLocation &&
            networking == other.networking &&
            secretName == other.secretName &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(injectionLocation, networking, secretName, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsEnvironmentVariableAuthResponse{injectionLocation=$injectionLocation, networking=$networking, secretName=$secretName, type=$type, additionalProperties=$additionalProperties}"
}
