// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

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

/** Why a deployment is paused. Non-null exactly when `status` is `paused`. */
@JsonDeserialize(using = BetaManagedAgentsDeploymentPausedReason.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsDeploymentPausedReason.Serializer::class)
class BetaManagedAgentsDeploymentPausedReason
private constructor(
    private val manual: BetaManagedAgentsManualDeploymentPausedReason? = null,
    private val error: BetaManagedAgentsErrorDeploymentPausedReason? = null,
    private val _json: JsonValue? = null,
) {

    /** The caller invoked the pause endpoint on the deployment. */
    fun manual(): Optional<BetaManagedAgentsManualDeploymentPausedReason> =
        Optional.ofNullable(manual)

    /** A scheduled fire recorded a failed run whose error auto-pauses the deployment. */
    fun error(): Optional<BetaManagedAgentsErrorDeploymentPausedReason> = Optional.ofNullable(error)

    fun isManual(): Boolean = manual != null

    fun isError(): Boolean = error != null

    /** The caller invoked the pause endpoint on the deployment. */
    fun asManual(): BetaManagedAgentsManualDeploymentPausedReason = manual.getOrThrow("manual")

    /** A scheduled fire recorded a failed run whose error auto-pauses the deployment. */
    fun asError(): BetaManagedAgentsErrorDeploymentPausedReason = error.getOrThrow("error")

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
     * Optional<String> result = betaManagedAgentsDeploymentPausedReason.accept(new BetaManagedAgentsDeploymentPausedReason.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitManual(BetaManagedAgentsManualDeploymentPausedReason manual) {
     *         return Optional.of(manual.toString());
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
            manual != null -> visitor.visitManual(manual)
            error != null -> visitor.visitError(error)
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
    fun validate(): BetaManagedAgentsDeploymentPausedReason = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitManual(manual: BetaManagedAgentsManualDeploymentPausedReason) {
                    manual.validate()
                }

                override fun visitError(error: BetaManagedAgentsErrorDeploymentPausedReason) {
                    error.validate()
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
                override fun visitManual(manual: BetaManagedAgentsManualDeploymentPausedReason) =
                    manual.validity()

                override fun visitError(error: BetaManagedAgentsErrorDeploymentPausedReason) =
                    error.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsDeploymentPausedReason &&
            manual == other.manual &&
            error == other.error
    }

    override fun hashCode(): Int = Objects.hash(manual, error)

    override fun toString(): String =
        when {
            manual != null -> "BetaManagedAgentsDeploymentPausedReason{manual=$manual}"
            error != null -> "BetaManagedAgentsDeploymentPausedReason{error=$error}"
            _json != null -> "BetaManagedAgentsDeploymentPausedReason{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsDeploymentPausedReason")
        }

    companion object {

        /** The caller invoked the pause endpoint on the deployment. */
        @JvmStatic
        fun ofManual(manual: BetaManagedAgentsManualDeploymentPausedReason) =
            BetaManagedAgentsDeploymentPausedReason(manual = manual)

        /** A scheduled fire recorded a failed run whose error auto-pauses the deployment. */
        @JvmStatic
        fun ofError(error: BetaManagedAgentsErrorDeploymentPausedReason) =
            BetaManagedAgentsDeploymentPausedReason(error = error)
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsDeploymentPausedReason] to a value of type [T].
     */
    interface Visitor<out T> {

        /** The caller invoked the pause endpoint on the deployment. */
        fun visitManual(manual: BetaManagedAgentsManualDeploymentPausedReason): T

        /** A scheduled fire recorded a failed run whose error auto-pauses the deployment. */
        fun visitError(error: BetaManagedAgentsErrorDeploymentPausedReason): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsDeploymentPausedReason] to a value of type
         * [T].
         *
         * An instance of [BetaManagedAgentsDeploymentPausedReason] can contain an unknown variant
         * if it was deserialized from data that doesn't match any known variant. For example, if
         * the SDK is on an older version than the API, then the API may respond with new variants
         * that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsDeploymentPausedReason: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsDeploymentPausedReason>(
            BetaManagedAgentsDeploymentPausedReason::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsDeploymentPausedReason {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "manual" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsManualDeploymentPausedReason>(),
                        )
                        ?.let { BetaManagedAgentsDeploymentPausedReason(manual = it, _json = json) }
                        ?: BetaManagedAgentsDeploymentPausedReason(_json = json)
                }
                "error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsErrorDeploymentPausedReason>(),
                        )
                        ?.let { BetaManagedAgentsDeploymentPausedReason(error = it, _json = json) }
                        ?: BetaManagedAgentsDeploymentPausedReason(_json = json)
                }
            }

            return BetaManagedAgentsDeploymentPausedReason(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsDeploymentPausedReason>(
            BetaManagedAgentsDeploymentPausedReason::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsDeploymentPausedReason,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.manual != null -> generator.writeObject(value.manual)
                value.error != null -> generator.writeObject(value.error)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException("Invalid BetaManagedAgentsDeploymentPausedReason")
            }
        }
    }
}
