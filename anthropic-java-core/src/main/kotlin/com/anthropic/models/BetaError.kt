// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

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

@JsonDeserialize(using = BetaError.Deserializer::class)
@JsonSerialize(using = BetaError.Serializer::class)
class BetaError
private constructor(
    private val betaInvalidRequestError: BetaInvalidRequestError? = null,
    private val betaAuthenticationError: BetaAuthenticationError? = null,
    private val betaPermissionError: BetaPermissionError? = null,
    private val betaNotFoundError: BetaNotFoundError? = null,
    private val betaRateLimitError: BetaRateLimitError? = null,
    private val betaApiError: BetaApiError? = null,
    private val betaOverloadedError: BetaOverloadedError? = null,
    private val _json: JsonValue? = null,
) {

    private var validated: Boolean = false

    fun betaInvalidRequestError(): Optional<BetaInvalidRequestError> =
        Optional.ofNullable(betaInvalidRequestError)

    fun betaAuthenticationError(): Optional<BetaAuthenticationError> =
        Optional.ofNullable(betaAuthenticationError)

    fun betaPermissionError(): Optional<BetaPermissionError> =
        Optional.ofNullable(betaPermissionError)

    fun betaNotFoundError(): Optional<BetaNotFoundError> = Optional.ofNullable(betaNotFoundError)

    fun betaRateLimitError(): Optional<BetaRateLimitError> = Optional.ofNullable(betaRateLimitError)

    fun betaApiError(): Optional<BetaApiError> = Optional.ofNullable(betaApiError)

    fun betaOverloadedError(): Optional<BetaOverloadedError> =
        Optional.ofNullable(betaOverloadedError)

    fun isBetaInvalidRequestError(): Boolean = betaInvalidRequestError != null

    fun isBetaAuthenticationError(): Boolean = betaAuthenticationError != null

    fun isBetaPermissionError(): Boolean = betaPermissionError != null

    fun isBetaNotFoundError(): Boolean = betaNotFoundError != null

    fun isBetaRateLimitError(): Boolean = betaRateLimitError != null

    fun isBetaApiError(): Boolean = betaApiError != null

    fun isBetaOverloadedError(): Boolean = betaOverloadedError != null

    fun asBetaInvalidRequestError(): BetaInvalidRequestError =
        betaInvalidRequestError.getOrThrow("betaInvalidRequestError")

    fun asBetaAuthenticationError(): BetaAuthenticationError =
        betaAuthenticationError.getOrThrow("betaAuthenticationError")

    fun asBetaPermissionError(): BetaPermissionError =
        betaPermissionError.getOrThrow("betaPermissionError")

    fun asBetaNotFoundError(): BetaNotFoundError = betaNotFoundError.getOrThrow("betaNotFoundError")

    fun asBetaRateLimitError(): BetaRateLimitError =
        betaRateLimitError.getOrThrow("betaRateLimitError")

    fun asBetaApiError(): BetaApiError = betaApiError.getOrThrow("betaApiError")

    fun asBetaOverloadedError(): BetaOverloadedError =
        betaOverloadedError.getOrThrow("betaOverloadedError")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            betaInvalidRequestError != null ->
                visitor.visitBetaInvalidRequestError(betaInvalidRequestError)
            betaAuthenticationError != null ->
                visitor.visitBetaAuthenticationError(betaAuthenticationError)
            betaPermissionError != null -> visitor.visitBetaPermissionError(betaPermissionError)
            betaNotFoundError != null -> visitor.visitBetaNotFoundError(betaNotFoundError)
            betaRateLimitError != null -> visitor.visitBetaRateLimitError(betaRateLimitError)
            betaApiError != null -> visitor.visitBetaApiError(betaApiError)
            betaOverloadedError != null -> visitor.visitBetaOverloadedError(betaOverloadedError)
            else -> visitor.unknown(_json)
        }
    }

    fun validate(): BetaError = apply {
        if (!validated) {
            if (
                betaInvalidRequestError == null &&
                    betaAuthenticationError == null &&
                    betaPermissionError == null &&
                    betaNotFoundError == null &&
                    betaRateLimitError == null &&
                    betaApiError == null &&
                    betaOverloadedError == null
            ) {
                throw AnthropicInvalidDataException("Unknown BetaError: $_json")
            }
            betaInvalidRequestError?.validate()
            betaAuthenticationError?.validate()
            betaPermissionError?.validate()
            betaNotFoundError?.validate()
            betaRateLimitError?.validate()
            betaApiError?.validate()
            betaOverloadedError?.validate()
            validated = true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaError && betaInvalidRequestError == other.betaInvalidRequestError && betaAuthenticationError == other.betaAuthenticationError && betaPermissionError == other.betaPermissionError && betaNotFoundError == other.betaNotFoundError && betaRateLimitError == other.betaRateLimitError && betaApiError == other.betaApiError && betaOverloadedError == other.betaOverloadedError /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaInvalidRequestError, betaAuthenticationError, betaPermissionError, betaNotFoundError, betaRateLimitError, betaApiError, betaOverloadedError) /* spotless:on */

    override fun toString(): String =
        when {
            betaInvalidRequestError != null ->
                "BetaError{betaInvalidRequestError=$betaInvalidRequestError}"
            betaAuthenticationError != null ->
                "BetaError{betaAuthenticationError=$betaAuthenticationError}"
            betaPermissionError != null -> "BetaError{betaPermissionError=$betaPermissionError}"
            betaNotFoundError != null -> "BetaError{betaNotFoundError=$betaNotFoundError}"
            betaRateLimitError != null -> "BetaError{betaRateLimitError=$betaRateLimitError}"
            betaApiError != null -> "BetaError{betaApiError=$betaApiError}"
            betaOverloadedError != null -> "BetaError{betaOverloadedError=$betaOverloadedError}"
            _json != null -> "BetaError{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaError")
        }

    companion object {

        @JvmStatic
        fun ofBetaInvalidRequestError(betaInvalidRequestError: BetaInvalidRequestError) =
            BetaError(betaInvalidRequestError = betaInvalidRequestError)

        @JvmStatic
        fun ofBetaAuthenticationError(betaAuthenticationError: BetaAuthenticationError) =
            BetaError(betaAuthenticationError = betaAuthenticationError)

        @JvmStatic
        fun ofBetaPermissionError(betaPermissionError: BetaPermissionError) =
            BetaError(betaPermissionError = betaPermissionError)

        @JvmStatic
        fun ofBetaNotFoundError(betaNotFoundError: BetaNotFoundError) =
            BetaError(betaNotFoundError = betaNotFoundError)

        @JvmStatic
        fun ofBetaRateLimitError(betaRateLimitError: BetaRateLimitError) =
            BetaError(betaRateLimitError = betaRateLimitError)

        @JvmStatic
        fun ofBetaApiError(betaApiError: BetaApiError) = BetaError(betaApiError = betaApiError)

        @JvmStatic
        fun ofBetaOverloadedError(betaOverloadedError: BetaOverloadedError) =
            BetaError(betaOverloadedError = betaOverloadedError)
    }

    interface Visitor<out T> {

        fun visitBetaInvalidRequestError(betaInvalidRequestError: BetaInvalidRequestError): T

        fun visitBetaAuthenticationError(betaAuthenticationError: BetaAuthenticationError): T

        fun visitBetaPermissionError(betaPermissionError: BetaPermissionError): T

        fun visitBetaNotFoundError(betaNotFoundError: BetaNotFoundError): T

        fun visitBetaRateLimitError(betaRateLimitError: BetaRateLimitError): T

        fun visitBetaApiError(betaApiError: BetaApiError): T

        fun visitBetaOverloadedError(betaOverloadedError: BetaOverloadedError): T

        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaError: $json")
        }
    }

    class Deserializer : BaseDeserializer<BetaError>(BetaError::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaError {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "invalid_request_error" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaInvalidRequestError>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaError(betaInvalidRequestError = it, _json = json)
                        }
                }
                "authentication_error" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaAuthenticationError>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaError(betaAuthenticationError = it, _json = json)
                        }
                }
                "permission_error" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaPermissionError>()) { it.validate() }
                        ?.let {
                            return BetaError(betaPermissionError = it, _json = json)
                        }
                }
                "not_found_error" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaNotFoundError>()) { it.validate() }
                        ?.let {
                            return BetaError(betaNotFoundError = it, _json = json)
                        }
                }
                "rate_limit_error" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaRateLimitError>()) { it.validate() }
                        ?.let {
                            return BetaError(betaRateLimitError = it, _json = json)
                        }
                }
                "api_error" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaApiError>()) { it.validate() }
                        ?.let {
                            return BetaError(betaApiError = it, _json = json)
                        }
                }
                "overloaded_error" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaOverloadedError>()) { it.validate() }
                        ?.let {
                            return BetaError(betaOverloadedError = it, _json = json)
                        }
                }
            }

            return BetaError(_json = json)
        }
    }

    class Serializer : BaseSerializer<BetaError>(BetaError::class) {

        override fun serialize(
            value: BetaError,
            generator: JsonGenerator,
            provider: SerializerProvider
        ) {
            when {
                value.betaInvalidRequestError != null ->
                    generator.writeObject(value.betaInvalidRequestError)
                value.betaAuthenticationError != null ->
                    generator.writeObject(value.betaAuthenticationError)
                value.betaPermissionError != null ->
                    generator.writeObject(value.betaPermissionError)
                value.betaNotFoundError != null -> generator.writeObject(value.betaNotFoundError)
                value.betaRateLimitError != null -> generator.writeObject(value.betaRateLimitError)
                value.betaApiError != null -> generator.writeObject(value.betaApiError)
                value.betaOverloadedError != null ->
                    generator.writeObject(value.betaOverloadedError)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaError")
            }
        }
    }
}
