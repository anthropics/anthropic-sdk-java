// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
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
 * Substitute the secret on any host the session's Environment network policy permits egress to. The
 * Environment's network policy is the only boundary on where the secret can reach.
 */
@JsonDeserialize(using = BetaManagedAgentsCredentialNetworkingParams.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsCredentialNetworkingParams.Serializer::class)
class BetaManagedAgentsCredentialNetworkingParams
private constructor(
    private val unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingParams? = null,
    private val limited: BetaManagedAgentsLimitedCredentialNetworkingParams? = null,
    private val _json: JsonValue? = null,
) {

    /**
     * Substitute the secret on any host the session's Environment network policy permits egress to.
     * The Environment's network policy is the only boundary on where the secret can reach.
     */
    fun unrestricted(): Optional<BetaManagedAgentsUnrestrictedCredentialNetworkingParams> =
        Optional.ofNullable(unrestricted)

    /** Substitute the secret only on requests to the listed hosts. */
    fun limited(): Optional<BetaManagedAgentsLimitedCredentialNetworkingParams> =
        Optional.ofNullable(limited)

    fun isUnrestricted(): Boolean = unrestricted != null

    fun isLimited(): Boolean = limited != null

    /**
     * Substitute the secret on any host the session's Environment network policy permits egress to.
     * The Environment's network policy is the only boundary on where the secret can reach.
     */
    fun asUnrestricted(): BetaManagedAgentsUnrestrictedCredentialNetworkingParams =
        unrestricted.getOrThrow("unrestricted")

    /** Substitute the secret only on requests to the listed hosts. */
    fun asLimited(): BetaManagedAgentsLimitedCredentialNetworkingParams =
        limited.getOrThrow("limited")

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
     * Optional<String> result = betaManagedAgentsCredentialNetworkingParams.accept(new BetaManagedAgentsCredentialNetworkingParams.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitUnrestricted(BetaManagedAgentsUnrestrictedCredentialNetworkingParams unrestricted) {
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
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            unrestricted != null -> visitor.visitUnrestricted(unrestricted)
            limited != null -> visitor.visitLimited(limited)
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
    fun validate(): BetaManagedAgentsCredentialNetworkingParams = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitUnrestricted(
                    unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingParams
                ) {
                    unrestricted.validate()
                }

                override fun visitLimited(
                    limited: BetaManagedAgentsLimitedCredentialNetworkingParams
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitUnrestricted(
                    unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingParams
                ) = unrestricted.validity()

                override fun visitLimited(
                    limited: BetaManagedAgentsLimitedCredentialNetworkingParams
                ) = limited.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsCredentialNetworkingParams &&
            unrestricted == other.unrestricted &&
            limited == other.limited
    }

    override fun hashCode(): Int = Objects.hash(unrestricted, limited)

    override fun toString(): String =
        when {
            unrestricted != null ->
                "BetaManagedAgentsCredentialNetworkingParams{unrestricted=$unrestricted}"
            limited != null -> "BetaManagedAgentsCredentialNetworkingParams{limited=$limited}"
            _json != null -> "BetaManagedAgentsCredentialNetworkingParams{_unknown=$_json}"
            else ->
                throw IllegalStateException("Invalid BetaManagedAgentsCredentialNetworkingParams")
        }

    companion object {

        /**
         * Substitute the secret on any host the session's Environment network policy permits egress
         * to. The Environment's network policy is the only boundary on where the secret can reach.
         */
        @JvmStatic
        fun ofUnrestricted(unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingParams) =
            BetaManagedAgentsCredentialNetworkingParams(unrestricted = unrestricted)

        /** Substitute the secret only on requests to the listed hosts. */
        @JvmStatic
        fun ofLimited(limited: BetaManagedAgentsLimitedCredentialNetworkingParams) =
            BetaManagedAgentsCredentialNetworkingParams(limited = limited)
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsCredentialNetworkingParams] to a value of type [T].
     */
    interface Visitor<out T> {

        /**
         * Substitute the secret on any host the session's Environment network policy permits egress
         * to. The Environment's network policy is the only boundary on where the secret can reach.
         */
        fun visitUnrestricted(
            unrestricted: BetaManagedAgentsUnrestrictedCredentialNetworkingParams
        ): T

        /** Substitute the secret only on requests to the listed hosts. */
        fun visitLimited(limited: BetaManagedAgentsLimitedCredentialNetworkingParams): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsCredentialNetworkingParams] to a value of
         * type [T].
         *
         * An instance of [BetaManagedAgentsCredentialNetworkingParams] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsCredentialNetworkingParams: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsCredentialNetworkingParams>(
            BetaManagedAgentsCredentialNetworkingParams::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsCredentialNetworkingParams {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "unrestricted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsUnrestrictedCredentialNetworkingParams
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsCredentialNetworkingParams(
                                unrestricted = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsCredentialNetworkingParams(_json = json)
                }
                "limited" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsLimitedCredentialNetworkingParams>(),
                        )
                        ?.let {
                            BetaManagedAgentsCredentialNetworkingParams(limited = it, _json = json)
                        } ?: BetaManagedAgentsCredentialNetworkingParams(_json = json)
                }
            }

            return BetaManagedAgentsCredentialNetworkingParams(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsCredentialNetworkingParams>(
            BetaManagedAgentsCredentialNetworkingParams::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsCredentialNetworkingParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.unrestricted != null -> generator.writeObject(value.unrestricted)
                value.limited != null -> generator.writeObject(value.limited)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaManagedAgentsCredentialNetworkingParams"
                    )
            }
        }
    }
}
