package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Parameters for updating an environment variable credential. `secret_name` is immutable. */
class BetaManagedAgentsEnvironmentVariableUpdateParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val injectionLocation: JsonField<BetaManagedAgentsInjectionLocationUpdateParams>,
    private val networking: JsonField<BetaManagedAgentsCredentialNetworkingParams>,
    private val secretValue: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("injection_location")
        @ExcludeMissing
        injectionLocation: JsonField<BetaManagedAgentsInjectionLocationUpdateParams> =
            JsonMissing.of(),
        @JsonProperty("networking")
        @ExcludeMissing
        networking: JsonField<BetaManagedAgentsCredentialNetworkingParams> = JsonMissing.of(),
        @JsonProperty("secret_value")
        @ExcludeMissing
        secretValue: JsonField<String> = JsonMissing.of(),
    ) : this(type, injectionLocation, networking, secretValue, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("environment_variable")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Updated injection location.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun injectionLocation(): Optional<BetaManagedAgentsInjectionLocationUpdateParams> =
        injectionLocation.getOptional("injection_location")

    /**
     * The new networking scope, which replaces the stored one as a whole.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun networking(): Optional<BetaManagedAgentsCredentialNetworkingParams> =
        networking.getOptional("networking")

    /**
     * The new secret value to replace the stored one.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun secretValue(): Optional<String> = secretValue.getOptional("secret_value")

    /**
     * Returns the raw JSON value of [injectionLocation].
     *
     * Unlike [injectionLocation], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("injection_location")
    @ExcludeMissing
    fun _injectionLocation(): JsonField<BetaManagedAgentsInjectionLocationUpdateParams> =
        injectionLocation

    /**
     * Returns the raw JSON value of [networking].
     *
     * Unlike [networking], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("networking")
    @ExcludeMissing
    fun _networking(): JsonField<BetaManagedAgentsCredentialNetworkingParams> = networking

    /**
     * Returns the raw JSON value of [secretValue].
     *
     * Unlike [secretValue], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("secret_value")
    @ExcludeMissing
    fun _secretValue(): JsonField<String> = secretValue

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
         * [BetaManagedAgentsEnvironmentVariableUpdateParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsEnvironmentVariableUpdateParams]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("environment_variable")
        private var injectionLocation: JsonField<BetaManagedAgentsInjectionLocationUpdateParams> =
            JsonMissing.of()
        private var networking: JsonField<BetaManagedAgentsCredentialNetworkingParams> =
            JsonMissing.of()
        private var secretValue: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsEnvironmentVariableUpdateParams:
                BetaManagedAgentsEnvironmentVariableUpdateParams
        ) = apply {
            type = betaManagedAgentsEnvironmentVariableUpdateParams.type
            injectionLocation = betaManagedAgentsEnvironmentVariableUpdateParams.injectionLocation
            networking = betaManagedAgentsEnvironmentVariableUpdateParams.networking
            secretValue = betaManagedAgentsEnvironmentVariableUpdateParams.secretValue
            additionalProperties =
                betaManagedAgentsEnvironmentVariableUpdateParams.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("environment_variable")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Updated injection location. */
        fun injectionLocation(injectionLocation: BetaManagedAgentsInjectionLocationUpdateParams) =
            injectionLocation(JsonField.of(injectionLocation))

        /**
         * Sets [Builder.injectionLocation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.injectionLocation] with a well-typed
         * [BetaManagedAgentsInjectionLocationUpdateParams] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun injectionLocation(
            injectionLocation: JsonField<BetaManagedAgentsInjectionLocationUpdateParams>
        ) = apply { this.injectionLocation = injectionLocation }

        /** The new networking scope, which replaces the stored one as a whole. */
        fun networking(networking: BetaManagedAgentsCredentialNetworkingParams?) =
            networking(JsonField.ofNullable(networking))

        /** Alias for calling [Builder.networking] with `networking.orElse(null)`. */
        fun networking(networking: Optional<BetaManagedAgentsCredentialNetworkingParams>) =
            networking(networking.getOrNull())

        /**
         * Sets [Builder.networking] to an arbitrary JSON value.
         *
         * You should usually call [Builder.networking] with a well-typed
         * [BetaManagedAgentsCredentialNetworkingParams] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun networking(networking: JsonField<BetaManagedAgentsCredentialNetworkingParams>) = apply {
            this.networking = networking
        }

        /**
         * Alias for calling [networking] with
         * `BetaManagedAgentsCredentialNetworkingParams.ofUnrestricted(unrestricted)`.
         */
        fun networking(unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingParams) =
            networking(BetaManagedAgentsCredentialNetworkingParams.ofUnrestricted(unrestricted))

        /**
         * Alias for calling [networking] with
         * `BetaManagedAgentsCredentialNetworkingParams.ofLimited(limited)`.
         */
        fun networking(limited: BetaManagedAgentsLimitedCredentialNetworkingParams) =
            networking(BetaManagedAgentsCredentialNetworkingParams.ofLimited(limited))

        /**
         * Alias for calling [networking] with the following:
         * ```java
         * BetaManagedAgentsLimitedCredentialNetworkingParams.builder()
         *     .allowedHosts(allowedHosts)
         *     .build()
         * ```
         */
        fun limitedNetworking(allowedHosts: List<String>) =
            networking(
                BetaManagedAgentsLimitedCredentialNetworkingParams.builder()
                    .allowedHosts(allowedHosts)
                    .build()
            )

        /** The new secret value to replace the stored one. */
        fun secretValue(secretValue: String?) = secretValue(JsonField.ofNullable(secretValue))

        /** Alias for calling [Builder.secretValue] with `secretValue.orElse(null)`. */
        fun secretValue(secretValue: Optional<String>) = secretValue(secretValue.getOrNull())

        /**
         * Sets [Builder.secretValue] to an arbitrary JSON value.
         *
         * You should usually call [Builder.secretValue] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun secretValue(secretValue: JsonField<String>) = apply { this.secretValue = secretValue }

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
         * Returns an immutable instance of [BetaManagedAgentsEnvironmentVariableUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsEnvironmentVariableUpdateParams =
            BetaManagedAgentsEnvironmentVariableUpdateParams(
                type,
                injectionLocation,
                networking,
                secretValue,
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
    fun validate(): BetaManagedAgentsEnvironmentVariableUpdateParams = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("environment_variable")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        injectionLocation().ifPresent { it.validate() }
        networking().ifPresent { it.validate() }
        secretValue()
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
        type.let { if (it == JsonValue.from("environment_variable")) 1 else 0 } +
            (injectionLocation.asKnown().getOrNull()?.validity() ?: 0) +
            (networking.asKnown().getOrNull()?.validity() ?: 0) +
            (if (secretValue.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsEnvironmentVariableUpdateParams &&
            type == other.type &&
            injectionLocation == other.injectionLocation &&
            networking == other.networking &&
            secretValue == other.secretValue &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, injectionLocation, networking, secretValue, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsEnvironmentVariableUpdateParams{type=$type, injectionLocation=$injectionLocation, networking=$networking, secretValue=$secretValue, additionalProperties=$additionalProperties}"
}
