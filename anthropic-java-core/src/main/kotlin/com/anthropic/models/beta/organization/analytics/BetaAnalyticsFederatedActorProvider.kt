package com.anthropic.models.beta.organization.analytics

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

@JsonDeserialize(using = BetaAnalyticsFederatedActorProvider.Deserializer::class)
@JsonSerialize(using = BetaAnalyticsFederatedActorProvider.Serializer::class)
class BetaAnalyticsFederatedActorProvider
private constructor(
    private val aws: BetaAnalyticsFederatedActorAwsProvider? = null,
    private val azure: BetaAnalyticsFederatedActorAzureProvider? = null,
    private val gcp: BetaAnalyticsFederatedActorGcpProvider? = null,
    private val oidc: BetaAnalyticsFederatedActorOidcProvider? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            aws != null -> Type.AWS
            azure != null -> Type.AZURE
            gcp != null -> Type.GCP
            oidc != null -> Type.OIDC
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun aws(): Optional<BetaAnalyticsFederatedActorAwsProvider> = Optional.ofNullable(aws)

    fun azure(): Optional<BetaAnalyticsFederatedActorAzureProvider> = Optional.ofNullable(azure)

    fun gcp(): Optional<BetaAnalyticsFederatedActorGcpProvider> = Optional.ofNullable(gcp)

    fun oidc(): Optional<BetaAnalyticsFederatedActorOidcProvider> = Optional.ofNullable(oidc)

    fun isAws(): Boolean = aws != null

    fun isAzure(): Boolean = azure != null

    fun isGcp(): Boolean = gcp != null

    fun isOidc(): Boolean = oidc != null

    fun asAws(): BetaAnalyticsFederatedActorAwsProvider = aws.getOrThrow("aws")

    fun asAzure(): BetaAnalyticsFederatedActorAzureProvider = azure.getOrThrow("azure")

    fun asGcp(): BetaAnalyticsFederatedActorGcpProvider = gcp.getOrThrow("gcp")

    fun asOidc(): BetaAnalyticsFederatedActorOidcProvider = oidc.getOrThrow("oidc")

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
     * Optional<String> result = betaAnalyticsFederatedActorProvider.accept(new BetaAnalyticsFederatedActorProvider.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAws(BetaAnalyticsFederatedActorAwsProvider aws) {
     *         return Optional.of(aws.toString());
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
            aws != null -> visitor.visitAws(aws)
            azure != null -> visitor.visitAzure(azure)
            gcp != null -> visitor.visitGcp(gcp)
            oidc != null -> visitor.visitOidc(oidc)
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
    fun validate(): BetaAnalyticsFederatedActorProvider = apply {
        if (validated) {
            return@apply
        }

        when {
            aws != null -> aws.validate()
            azure != null -> azure.validate()
            gcp != null -> gcp.validate()
            oidc != null -> oidc.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaAnalyticsFederatedActorProvider: $_json"
                )
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
            aws != null -> aws.validity()
            azure != null -> azure.validity()
            gcp != null -> gcp.validity()
            oidc != null -> oidc.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsFederatedActorProvider &&
            aws == other.aws &&
            azure == other.azure &&
            gcp == other.gcp &&
            oidc == other.oidc
    }

    override fun hashCode(): Int = Objects.hash(aws, azure, gcp, oidc)

    override fun toString(): String =
        when {
            aws != null -> "BetaAnalyticsFederatedActorProvider{aws=$aws}"
            azure != null -> "BetaAnalyticsFederatedActorProvider{azure=$azure}"
            gcp != null -> "BetaAnalyticsFederatedActorProvider{gcp=$gcp}"
            oidc != null -> "BetaAnalyticsFederatedActorProvider{oidc=$oidc}"
            _json != null -> "BetaAnalyticsFederatedActorProvider{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaAnalyticsFederatedActorProvider")
        }

    companion object {

        @JvmStatic
        fun ofAws(aws: BetaAnalyticsFederatedActorAwsProvider) =
            BetaAnalyticsFederatedActorProvider(aws = aws)

        @JvmStatic
        fun ofAzure(azure: BetaAnalyticsFederatedActorAzureProvider) =
            BetaAnalyticsFederatedActorProvider(azure = azure)

        /**
         * Returns an immutable instance of [BetaAnalyticsFederatedActorProvider] whose [ofAzure]
         * variant is built from the given required [subscriptionId].
         */
        @JvmStatic
        fun ofAzure(subscriptionId: String) =
            ofAzure(BetaAnalyticsFederatedActorAzureProvider.of(subscriptionId))

        @JvmStatic
        fun ofGcp(gcp: BetaAnalyticsFederatedActorGcpProvider) =
            BetaAnalyticsFederatedActorProvider(gcp = gcp)

        /**
         * Returns an immutable instance of [BetaAnalyticsFederatedActorProvider] whose [ofGcp]
         * variant is built from the given required [projectNumber].
         */
        @JvmStatic
        fun ofGcp(projectNumber: String) =
            ofGcp(BetaAnalyticsFederatedActorGcpProvider.of(projectNumber))

        @JvmStatic
        fun ofOidc(oidc: BetaAnalyticsFederatedActorOidcProvider) =
            BetaAnalyticsFederatedActorProvider(oidc = oidc)

        /**
         * Returns an immutable instance of [BetaAnalyticsFederatedActorProvider] whose [ofOidc]
         * variant is built from the given required [issuer].
         */
        @JvmStatic
        fun ofOidc(issuer: String?) = ofOidc(BetaAnalyticsFederatedActorOidcProvider.of(issuer))

        /** Alias for calling [ofOidc] with `issuer.orElse(null)`. */
        @JvmStatic fun ofOidc(issuer: Optional<String>) = ofOidc(issuer.getOrNull())
    }

    /**
     * An interface that defines how to map each variant of [BetaAnalyticsFederatedActorProvider] to
     * a value of type [T].
     */
    interface Visitor<out T> {

        fun visitAws(aws: BetaAnalyticsFederatedActorAwsProvider): T

        fun visitAzure(azure: BetaAnalyticsFederatedActorAzureProvider): T

        fun visitGcp(gcp: BetaAnalyticsFederatedActorGcpProvider): T

        fun visitOidc(oidc: BetaAnalyticsFederatedActorOidcProvider): T

        /**
         * Maps an unknown variant of [BetaAnalyticsFederatedActorProvider] to a value of type [T].
         *
         * An instance of [BetaAnalyticsFederatedActorProvider] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaAnalyticsFederatedActorProvider: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaAnalyticsFederatedActorProvider>(
            BetaAnalyticsFederatedActorProvider::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaAnalyticsFederatedActorProvider {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "aws" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaAnalyticsFederatedActorAwsProvider>(),
                        )
                        ?.let { BetaAnalyticsFederatedActorProvider(aws = it, _json = json) }
                        ?: BetaAnalyticsFederatedActorProvider(_json = json)
                }
                "azure" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaAnalyticsFederatedActorAzureProvider>(),
                        )
                        ?.let { BetaAnalyticsFederatedActorProvider(azure = it, _json = json) }
                        ?: BetaAnalyticsFederatedActorProvider(_json = json)
                }
                "gcp" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaAnalyticsFederatedActorGcpProvider>(),
                        )
                        ?.let { BetaAnalyticsFederatedActorProvider(gcp = it, _json = json) }
                        ?: BetaAnalyticsFederatedActorProvider(_json = json)
                }
                "oidc" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaAnalyticsFederatedActorOidcProvider>(),
                        )
                        ?.let { BetaAnalyticsFederatedActorProvider(oidc = it, _json = json) }
                        ?: BetaAnalyticsFederatedActorProvider(_json = json)
                }
            }

            return BetaAnalyticsFederatedActorProvider(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaAnalyticsFederatedActorProvider>(
            BetaAnalyticsFederatedActorProvider::class
        ) {

        override fun serialize(
            value: BetaAnalyticsFederatedActorProvider,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.aws != null -> generator.writeObject(value.aws)
                value.azure != null -> generator.writeObject(value.azure)
                value.gcp != null -> generator.writeObject(value.gcp)
                value.oidc != null -> generator.writeObject(value.oidc)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaAnalyticsFederatedActorProvider")
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

            @JvmField val AWS = Type(JsonField.of("aws"))

            @JvmField val AZURE = Type(JsonField.of("azure"))

            @JvmField val GCP = Type(JsonField.of("gcp"))

            @JvmField val OIDC = Type(JsonField.of("oidc"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "aws" -> AWS
                    "azure" -> AZURE
                    "gcp" -> GCP
                    "oidc" -> OIDC
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            AWS,
            AZURE,
            GCP,
            OIDC,
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
            AWS,
            AZURE,
            GCP,
            OIDC,
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
                AWS -> Value.AWS
                AZURE -> Value.AZURE
                GCP -> Value.GCP
                OIDC -> Value.OIDC
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
                AWS -> Known.AWS
                AZURE -> Known.AZURE
                GCP -> Known.GCP
                OIDC -> Known.OIDC
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
