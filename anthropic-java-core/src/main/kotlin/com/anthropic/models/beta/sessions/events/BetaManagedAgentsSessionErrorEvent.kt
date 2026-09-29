package com.anthropic.models.beta.sessions.events

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** An error event indicating a problem occurred during session execution. */
class BetaManagedAgentsSessionErrorEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val error: JsonField<Error>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("error") @ExcludeMissing error: JsonField<Error> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(id, error, processedAt, type, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun error(): Error = error.getRequired("error")

    /**
     * Timestamp when the error occurred.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processedAt(): OffsetDateTime = processedAt.getRequired("processed_at")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [error].
     *
     * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<Error> = error

    /**
     * Returns the raw JSON value of [processedAt].
     *
     * Unlike [processedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("processed_at")
    @ExcludeMissing
    fun _processedAt(): JsonField<OffsetDateTime> = processedAt

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
         * [BetaManagedAgentsSessionErrorEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .error()
         * .processedAt()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionErrorEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var error: JsonField<Error>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsSessionErrorEvent: BetaManagedAgentsSessionErrorEvent) =
            apply {
                id = betaManagedAgentsSessionErrorEvent.id
                error = betaManagedAgentsSessionErrorEvent.error
                processedAt = betaManagedAgentsSessionErrorEvent.processedAt
                type = betaManagedAgentsSessionErrorEvent.type
                additionalProperties =
                    betaManagedAgentsSessionErrorEvent.additionalProperties.toMutableMap()
            }

        /** Unique identifier for this event. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        fun error(error: Error) = error(JsonField.of(error))

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed [Error] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun error(error: JsonField<Error>) = apply { this.error = error }

        /** Alias for calling [error] with `Error.ofUnknown(unknown)`. */
        fun error(unknown: BetaManagedAgentsUnknownError) = error(Error.ofUnknown(unknown))

        /** Alias for calling [error] with `Error.ofModelOverloaded(modelOverloaded)`. */
        fun error(modelOverloaded: BetaManagedAgentsModelOverloadedError) =
            error(Error.ofModelOverloaded(modelOverloaded))

        /** Alias for calling [error] with `Error.ofModelRateLimited(modelRateLimited)`. */
        fun error(modelRateLimited: BetaManagedAgentsModelRateLimitedError) =
            error(Error.ofModelRateLimited(modelRateLimited))

        /** Alias for calling [error] with `Error.ofModelRequestFailed(modelRequestFailed)`. */
        fun error(modelRequestFailed: BetaManagedAgentsModelRequestFailedError) =
            error(Error.ofModelRequestFailed(modelRequestFailed))

        /** Alias for calling [error] with `Error.ofMcpConnectionFailed(mcpConnectionFailed)`. */
        fun error(mcpConnectionFailed: BetaManagedAgentsMcpConnectionFailedError) =
            error(Error.ofMcpConnectionFailed(mcpConnectionFailed))

        /**
         * Alias for calling [error] with
         * `Error.ofMcpAuthenticationFailed(mcpAuthenticationFailed)`.
         */
        fun error(mcpAuthenticationFailed: BetaManagedAgentsMcpAuthenticationFailedError) =
            error(Error.ofMcpAuthenticationFailed(mcpAuthenticationFailed))

        /** Alias for calling [error] with `Error.ofBilling(billing)`. */
        fun error(billing: BetaManagedAgentsBillingError) = error(Error.ofBilling(billing))

        /**
         * Alias for calling [error] with
         * `Error.ofCredentialHostUnreachable(credentialHostUnreachable)`.
         */
        fun error(credentialHostUnreachable: BetaManagedAgentsCredentialHostUnreachableError) =
            error(Error.ofCredentialHostUnreachable(credentialHostUnreachable))

        /**
         * Alias for calling [error] with
         * `Error.ofRepositoryAuthentication(repositoryAuthentication)`.
         */
        fun error(repositoryAuthentication: BetaManagedAgentsRepositoryAuthenticationError) =
            error(Error.ofRepositoryAuthentication(repositoryAuthentication))

        /** Alias for calling [error] with `Error.ofRepositoryForbidden(repositoryForbidden)`. */
        fun error(repositoryForbidden: BetaManagedAgentsRepositoryForbiddenError) =
            error(Error.ofRepositoryForbidden(repositoryForbidden))

        /** Alias for calling [error] with `Error.ofRepositoryNotFound(repositoryNotFound)`. */
        fun error(repositoryNotFound: BetaManagedAgentsRepositoryNotFoundError) =
            error(Error.ofRepositoryNotFound(repositoryNotFound))

        /** Alias for calling [error] with `Error.ofRepositoryCheckout(repositoryCheckout)`. */
        fun error(repositoryCheckout: BetaManagedAgentsRepositoryCheckoutError) =
            error(Error.ofRepositoryCheckout(repositoryCheckout))

        /** Alias for calling [error] with `Error.ofRepositoryClone(repositoryClone)`. */
        fun error(repositoryClone: BetaManagedAgentsRepositoryCloneError) =
            error(Error.ofRepositoryClone(repositoryClone))

        /** Timestamp when the error occurred. */
        fun processedAt(processedAt: OffsetDateTime) = processedAt(JsonField.of(processedAt))

        /**
         * Sets [Builder.processedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.processedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun processedAt(processedAt: JsonField<OffsetDateTime>) = apply {
            this.processedAt = processedAt
        }

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
         * Returns an immutable instance of [BetaManagedAgentsSessionErrorEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .error()
         * .processedAt()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSessionErrorEvent =
            BetaManagedAgentsSessionErrorEvent(
                checkRequired("id", id),
                checkRequired("error", error),
                checkRequired("processedAt", processedAt),
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
    fun validate(): BetaManagedAgentsSessionErrorEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        error().validate()
        processedAt()
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (error.asKnown().getOrNull()?.validity() ?: 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0)

    @JsonDeserialize(using = Error.Deserializer::class)
    @JsonSerialize(using = Error.Serializer::class)
    class Error
    private constructor(
        private val unknown: BetaManagedAgentsUnknownError? = null,
        private val modelOverloaded: BetaManagedAgentsModelOverloadedError? = null,
        private val modelRateLimited: BetaManagedAgentsModelRateLimitedError? = null,
        private val modelRequestFailed: BetaManagedAgentsModelRequestFailedError? = null,
        private val mcpConnectionFailed: BetaManagedAgentsMcpConnectionFailedError? = null,
        private val mcpAuthenticationFailed: BetaManagedAgentsMcpAuthenticationFailedError? = null,
        private val billing: BetaManagedAgentsBillingError? = null,
        private val credentialHostUnreachable: BetaManagedAgentsCredentialHostUnreachableError? =
            null,
        private val repositoryAuthentication: BetaManagedAgentsRepositoryAuthenticationError? =
            null,
        private val repositoryForbidden: BetaManagedAgentsRepositoryForbiddenError? = null,
        private val repositoryNotFound: BetaManagedAgentsRepositoryNotFoundError? = null,
        private val repositoryCheckout: BetaManagedAgentsRepositoryCheckoutError? = null,
        private val repositoryClone: BetaManagedAgentsRepositoryCloneError? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitUnknown(unknown: BetaManagedAgentsUnknownError): Type =
                        Type.UNKNOWN_ERROR

                    override fun visitModelOverloaded(
                        modelOverloaded: BetaManagedAgentsModelOverloadedError
                    ): Type = Type.MODEL_OVERLOADED_ERROR

                    override fun visitModelRateLimited(
                        modelRateLimited: BetaManagedAgentsModelRateLimitedError
                    ): Type = Type.MODEL_RATE_LIMITED_ERROR

                    override fun visitModelRequestFailed(
                        modelRequestFailed: BetaManagedAgentsModelRequestFailedError
                    ): Type = Type.MODEL_REQUEST_FAILED_ERROR

                    override fun visitMcpConnectionFailed(
                        mcpConnectionFailed: BetaManagedAgentsMcpConnectionFailedError
                    ): Type = Type.MCP_CONNECTION_FAILED_ERROR

                    override fun visitMcpAuthenticationFailed(
                        mcpAuthenticationFailed: BetaManagedAgentsMcpAuthenticationFailedError
                    ): Type = Type.MCP_AUTHENTICATION_FAILED_ERROR

                    override fun visitBilling(billing: BetaManagedAgentsBillingError): Type =
                        Type.BILLING_ERROR

                    override fun visitCredentialHostUnreachable(
                        credentialHostUnreachable: BetaManagedAgentsCredentialHostUnreachableError
                    ): Type = Type.CREDENTIAL_HOST_UNREACHABLE_ERROR

                    override fun visitRepositoryAuthentication(
                        repositoryAuthentication: BetaManagedAgentsRepositoryAuthenticationError
                    ): Type = Type.REPOSITORY_AUTHENTICATION_ERROR

                    override fun visitRepositoryForbidden(
                        repositoryForbidden: BetaManagedAgentsRepositoryForbiddenError
                    ): Type = Type.REPOSITORY_FORBIDDEN_ERROR

                    override fun visitRepositoryNotFound(
                        repositoryNotFound: BetaManagedAgentsRepositoryNotFoundError
                    ): Type = Type.REPOSITORY_NOT_FOUND_ERROR

                    override fun visitRepositoryCheckout(
                        repositoryCheckout: BetaManagedAgentsRepositoryCheckoutError
                    ): Type = Type.REPOSITORY_CHECKOUT_ERROR

                    override fun visitRepositoryClone(
                        repositoryClone: BetaManagedAgentsRepositoryCloneError
                    ): Type = Type.REPOSITORY_CLONE_ERROR

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun message(): String =
            accept(
                object : Visitor<String> {
                    override fun visitUnknown(unknown: BetaManagedAgentsUnknownError): String =
                        unknown.message()

                    override fun visitModelOverloaded(
                        modelOverloaded: BetaManagedAgentsModelOverloadedError
                    ): String = modelOverloaded.message()

                    override fun visitModelRateLimited(
                        modelRateLimited: BetaManagedAgentsModelRateLimitedError
                    ): String = modelRateLimited.message()

                    override fun visitModelRequestFailed(
                        modelRequestFailed: BetaManagedAgentsModelRequestFailedError
                    ): String = modelRequestFailed.message()

                    override fun visitMcpConnectionFailed(
                        mcpConnectionFailed: BetaManagedAgentsMcpConnectionFailedError
                    ): String = mcpConnectionFailed.message()

                    override fun visitMcpAuthenticationFailed(
                        mcpAuthenticationFailed: BetaManagedAgentsMcpAuthenticationFailedError
                    ): String = mcpAuthenticationFailed.message()

                    override fun visitBilling(billing: BetaManagedAgentsBillingError): String =
                        billing.message()

                    override fun visitCredentialHostUnreachable(
                        credentialHostUnreachable: BetaManagedAgentsCredentialHostUnreachableError
                    ): String = credentialHostUnreachable.message()

                    override fun visitRepositoryAuthentication(
                        repositoryAuthentication: BetaManagedAgentsRepositoryAuthenticationError
                    ): String = repositoryAuthentication.message()

                    override fun visitRepositoryForbidden(
                        repositoryForbidden: BetaManagedAgentsRepositoryForbiddenError
                    ): String = repositoryForbidden.message()

                    override fun visitRepositoryNotFound(
                        repositoryNotFound: BetaManagedAgentsRepositoryNotFoundError
                    ): String = repositoryNotFound.message()

                    override fun visitRepositoryCheckout(
                        repositoryCheckout: BetaManagedAgentsRepositoryCheckoutError
                    ): String = repositoryCheckout.message()

                    override fun visitRepositoryClone(
                        repositoryClone: BetaManagedAgentsRepositoryCloneError
                    ): String = repositoryClone.message()

                    override fun unknown(json: JsonValue?): String =
                        json.getProperty<String>("message").getRequired("message")
                }
            )

        fun mcpServerName(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitUnknown(
                        unknown: BetaManagedAgentsUnknownError
                    ): Optional<String> = Optional.empty()

                    override fun visitModelOverloaded(
                        modelOverloaded: BetaManagedAgentsModelOverloadedError
                    ): Optional<String> = Optional.empty()

                    override fun visitModelRateLimited(
                        modelRateLimited: BetaManagedAgentsModelRateLimitedError
                    ): Optional<String> = Optional.empty()

                    override fun visitModelRequestFailed(
                        modelRequestFailed: BetaManagedAgentsModelRequestFailedError
                    ): Optional<String> = Optional.empty()

                    override fun visitMcpConnectionFailed(
                        mcpConnectionFailed: BetaManagedAgentsMcpConnectionFailedError
                    ): Optional<String> = Optional.of(mcpConnectionFailed.mcpServerName())

                    override fun visitMcpAuthenticationFailed(
                        mcpAuthenticationFailed: BetaManagedAgentsMcpAuthenticationFailedError
                    ): Optional<String> = Optional.of(mcpAuthenticationFailed.mcpServerName())

                    override fun visitBilling(
                        billing: BetaManagedAgentsBillingError
                    ): Optional<String> = Optional.empty()

                    override fun visitCredentialHostUnreachable(
                        credentialHostUnreachable: BetaManagedAgentsCredentialHostUnreachableError
                    ): Optional<String> = Optional.empty()

                    override fun visitRepositoryAuthentication(
                        repositoryAuthentication: BetaManagedAgentsRepositoryAuthenticationError
                    ): Optional<String> = Optional.empty()

                    override fun visitRepositoryForbidden(
                        repositoryForbidden: BetaManagedAgentsRepositoryForbiddenError
                    ): Optional<String> = Optional.empty()

                    override fun visitRepositoryNotFound(
                        repositoryNotFound: BetaManagedAgentsRepositoryNotFoundError
                    ): Optional<String> = Optional.empty()

                    override fun visitRepositoryCheckout(
                        repositoryCheckout: BetaManagedAgentsRepositoryCheckoutError
                    ): Optional<String> = Optional.empty()

                    override fun visitRepositoryClone(
                        repositoryClone: BetaManagedAgentsRepositoryCloneError
                    ): Optional<String> = Optional.empty()

                    override fun unknown(json: JsonValue?): Optional<String> =
                        json.getProperty<String>("mcp_server_name").asKnown()
                }
            )

        fun repositoryUrl(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitUnknown(
                        unknown: BetaManagedAgentsUnknownError
                    ): Optional<String> = Optional.empty()

                    override fun visitModelOverloaded(
                        modelOverloaded: BetaManagedAgentsModelOverloadedError
                    ): Optional<String> = Optional.empty()

                    override fun visitModelRateLimited(
                        modelRateLimited: BetaManagedAgentsModelRateLimitedError
                    ): Optional<String> = Optional.empty()

                    override fun visitModelRequestFailed(
                        modelRequestFailed: BetaManagedAgentsModelRequestFailedError
                    ): Optional<String> = Optional.empty()

                    override fun visitMcpConnectionFailed(
                        mcpConnectionFailed: BetaManagedAgentsMcpConnectionFailedError
                    ): Optional<String> = Optional.empty()

                    override fun visitMcpAuthenticationFailed(
                        mcpAuthenticationFailed: BetaManagedAgentsMcpAuthenticationFailedError
                    ): Optional<String> = Optional.empty()

                    override fun visitBilling(
                        billing: BetaManagedAgentsBillingError
                    ): Optional<String> = Optional.empty()

                    override fun visitCredentialHostUnreachable(
                        credentialHostUnreachable: BetaManagedAgentsCredentialHostUnreachableError
                    ): Optional<String> = Optional.empty()

                    override fun visitRepositoryAuthentication(
                        repositoryAuthentication: BetaManagedAgentsRepositoryAuthenticationError
                    ): Optional<String> = repositoryAuthentication.repositoryUrl()

                    override fun visitRepositoryForbidden(
                        repositoryForbidden: BetaManagedAgentsRepositoryForbiddenError
                    ): Optional<String> = repositoryForbidden.repositoryUrl()

                    override fun visitRepositoryNotFound(
                        repositoryNotFound: BetaManagedAgentsRepositoryNotFoundError
                    ): Optional<String> = repositoryNotFound.repositoryUrl()

                    override fun visitRepositoryCheckout(
                        repositoryCheckout: BetaManagedAgentsRepositoryCheckoutError
                    ): Optional<String> = repositoryCheckout.repositoryUrl()

                    override fun visitRepositoryClone(
                        repositoryClone: BetaManagedAgentsRepositoryCloneError
                    ): Optional<String> = repositoryClone.repositoryUrl()

                    override fun unknown(json: JsonValue?): Optional<String> =
                        json.getProperty<String>("repository_url").asKnown()
                }
            )

        /**
         * An unknown or unexpected error occurred during session execution. A fallback variant;
         * clients that don't recognize a new error code can match on `retry_status` and `message`
         * alone.
         */
        fun unknown(): Optional<BetaManagedAgentsUnknownError> = Optional.ofNullable(unknown)

        /** The model is currently overloaded. Emitted after automatic retries are exhausted. */
        fun modelOverloaded(): Optional<BetaManagedAgentsModelOverloadedError> =
            Optional.ofNullable(modelOverloaded)

        /** The model request was rate-limited. */
        fun modelRateLimited(): Optional<BetaManagedAgentsModelRateLimitedError> =
            Optional.ofNullable(modelRateLimited)

        /** A model request failed for a reason other than overload or rate-limiting. */
        fun modelRequestFailed(): Optional<BetaManagedAgentsModelRequestFailedError> =
            Optional.ofNullable(modelRequestFailed)

        /** Failed to connect to an MCP server. */
        fun mcpConnectionFailed(): Optional<BetaManagedAgentsMcpConnectionFailedError> =
            Optional.ofNullable(mcpConnectionFailed)

        /** Authentication to an MCP server failed. */
        fun mcpAuthenticationFailed(): Optional<BetaManagedAgentsMcpAuthenticationFailedError> =
            Optional.ofNullable(mcpAuthenticationFailed)

        /**
         * The caller's organization or workspace cannot make model requests — out of credits or
         * spend limit reached. Retrying with the same credentials will not succeed; the caller must
         * resolve the billing state.
         */
        fun billing(): Optional<BetaManagedAgentsBillingError> = Optional.ofNullable(billing)

        /**
         * An `environment_variable` credential's `auth.networking.allowed_hosts` includes a host
         * the environment's network policy does not permit.
         */
        fun credentialHostUnreachable(): Optional<BetaManagedAgentsCredentialHostUnreachableError> =
            Optional.ofNullable(credentialHostUnreachable)

        /**
         * The repository host rejected the credentials, or required credentials and received none.
         */
        fun repositoryAuthentication(): Optional<BetaManagedAgentsRepositoryAuthenticationError> =
            Optional.ofNullable(repositoryAuthentication)

        /** The repository host refused access to the repository. */
        fun repositoryForbidden(): Optional<BetaManagedAgentsRepositoryForbiddenError> =
            Optional.ofNullable(repositoryForbidden)

        /** The repository host reported the repository as not found. */
        fun repositoryNotFound(): Optional<BetaManagedAgentsRepositoryNotFoundError> =
            Optional.ofNullable(repositoryNotFound)

        /** The requested branch or commit does not exist in the repository. */
        fun repositoryCheckout(): Optional<BetaManagedAgentsRepositoryCheckoutError> =
            Optional.ofNullable(repositoryCheckout)

        /** The repository could not be cloned. */
        fun repositoryClone(): Optional<BetaManagedAgentsRepositoryCloneError> =
            Optional.ofNullable(repositoryClone)

        fun isUnknown(): Boolean = unknown != null

        fun isModelOverloaded(): Boolean = modelOverloaded != null

        fun isModelRateLimited(): Boolean = modelRateLimited != null

        fun isModelRequestFailed(): Boolean = modelRequestFailed != null

        fun isMcpConnectionFailed(): Boolean = mcpConnectionFailed != null

        fun isMcpAuthenticationFailed(): Boolean = mcpAuthenticationFailed != null

        fun isBilling(): Boolean = billing != null

        fun isCredentialHostUnreachable(): Boolean = credentialHostUnreachable != null

        fun isRepositoryAuthentication(): Boolean = repositoryAuthentication != null

        fun isRepositoryForbidden(): Boolean = repositoryForbidden != null

        fun isRepositoryNotFound(): Boolean = repositoryNotFound != null

        fun isRepositoryCheckout(): Boolean = repositoryCheckout != null

        fun isRepositoryClone(): Boolean = repositoryClone != null

        /**
         * An unknown or unexpected error occurred during session execution. A fallback variant;
         * clients that don't recognize a new error code can match on `retry_status` and `message`
         * alone.
         */
        fun asUnknown(): BetaManagedAgentsUnknownError = unknown.getOrThrow("unknown")

        /** The model is currently overloaded. Emitted after automatic retries are exhausted. */
        fun asModelOverloaded(): BetaManagedAgentsModelOverloadedError =
            modelOverloaded.getOrThrow("modelOverloaded")

        /** The model request was rate-limited. */
        fun asModelRateLimited(): BetaManagedAgentsModelRateLimitedError =
            modelRateLimited.getOrThrow("modelRateLimited")

        /** A model request failed for a reason other than overload or rate-limiting. */
        fun asModelRequestFailed(): BetaManagedAgentsModelRequestFailedError =
            modelRequestFailed.getOrThrow("modelRequestFailed")

        /** Failed to connect to an MCP server. */
        fun asMcpConnectionFailed(): BetaManagedAgentsMcpConnectionFailedError =
            mcpConnectionFailed.getOrThrow("mcpConnectionFailed")

        /** Authentication to an MCP server failed. */
        fun asMcpAuthenticationFailed(): BetaManagedAgentsMcpAuthenticationFailedError =
            mcpAuthenticationFailed.getOrThrow("mcpAuthenticationFailed")

        /**
         * The caller's organization or workspace cannot make model requests — out of credits or
         * spend limit reached. Retrying with the same credentials will not succeed; the caller must
         * resolve the billing state.
         */
        fun asBilling(): BetaManagedAgentsBillingError = billing.getOrThrow("billing")

        /**
         * An `environment_variable` credential's `auth.networking.allowed_hosts` includes a host
         * the environment's network policy does not permit.
         */
        fun asCredentialHostUnreachable(): BetaManagedAgentsCredentialHostUnreachableError =
            credentialHostUnreachable.getOrThrow("credentialHostUnreachable")

        /**
         * The repository host rejected the credentials, or required credentials and received none.
         */
        fun asRepositoryAuthentication(): BetaManagedAgentsRepositoryAuthenticationError =
            repositoryAuthentication.getOrThrow("repositoryAuthentication")

        /** The repository host refused access to the repository. */
        fun asRepositoryForbidden(): BetaManagedAgentsRepositoryForbiddenError =
            repositoryForbidden.getOrThrow("repositoryForbidden")

        /** The repository host reported the repository as not found. */
        fun asRepositoryNotFound(): BetaManagedAgentsRepositoryNotFoundError =
            repositoryNotFound.getOrThrow("repositoryNotFound")

        /** The requested branch or commit does not exist in the repository. */
        fun asRepositoryCheckout(): BetaManagedAgentsRepositoryCheckoutError =
            repositoryCheckout.getOrThrow("repositoryCheckout")

        /** The repository could not be cloned. */
        fun asRepositoryClone(): BetaManagedAgentsRepositoryCloneError =
            repositoryClone.getOrThrow("repositoryClone")

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
         * Optional<String> result = error.accept(new Error.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitUnknown(BetaManagedAgentsUnknownError unknown) {
         *         return Optional.of(unknown.toString());
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
                unknown != null -> visitor.visitUnknown(unknown)
                modelOverloaded != null -> visitor.visitModelOverloaded(modelOverloaded)
                modelRateLimited != null -> visitor.visitModelRateLimited(modelRateLimited)
                modelRequestFailed != null -> visitor.visitModelRequestFailed(modelRequestFailed)
                mcpConnectionFailed != null -> visitor.visitMcpConnectionFailed(mcpConnectionFailed)
                mcpAuthenticationFailed != null ->
                    visitor.visitMcpAuthenticationFailed(mcpAuthenticationFailed)
                billing != null -> visitor.visitBilling(billing)
                credentialHostUnreachable != null ->
                    visitor.visitCredentialHostUnreachable(credentialHostUnreachable)
                repositoryAuthentication != null ->
                    visitor.visitRepositoryAuthentication(repositoryAuthentication)
                repositoryForbidden != null -> visitor.visitRepositoryForbidden(repositoryForbidden)
                repositoryNotFound != null -> visitor.visitRepositoryNotFound(repositoryNotFound)
                repositoryCheckout != null -> visitor.visitRepositoryCheckout(repositoryCheckout)
                repositoryClone != null -> visitor.visitRepositoryClone(repositoryClone)
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
        fun validate(): Error = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitUnknown(unknown: BetaManagedAgentsUnknownError) {
                        unknown.validate()
                    }

                    override fun visitModelOverloaded(
                        modelOverloaded: BetaManagedAgentsModelOverloadedError
                    ) {
                        modelOverloaded.validate()
                    }

                    override fun visitModelRateLimited(
                        modelRateLimited: BetaManagedAgentsModelRateLimitedError
                    ) {
                        modelRateLimited.validate()
                    }

                    override fun visitModelRequestFailed(
                        modelRequestFailed: BetaManagedAgentsModelRequestFailedError
                    ) {
                        modelRequestFailed.validate()
                    }

                    override fun visitMcpConnectionFailed(
                        mcpConnectionFailed: BetaManagedAgentsMcpConnectionFailedError
                    ) {
                        mcpConnectionFailed.validate()
                    }

                    override fun visitMcpAuthenticationFailed(
                        mcpAuthenticationFailed: BetaManagedAgentsMcpAuthenticationFailedError
                    ) {
                        mcpAuthenticationFailed.validate()
                    }

                    override fun visitBilling(billing: BetaManagedAgentsBillingError) {
                        billing.validate()
                    }

                    override fun visitCredentialHostUnreachable(
                        credentialHostUnreachable: BetaManagedAgentsCredentialHostUnreachableError
                    ) {
                        credentialHostUnreachable.validate()
                    }

                    override fun visitRepositoryAuthentication(
                        repositoryAuthentication: BetaManagedAgentsRepositoryAuthenticationError
                    ) {
                        repositoryAuthentication.validate()
                    }

                    override fun visitRepositoryForbidden(
                        repositoryForbidden: BetaManagedAgentsRepositoryForbiddenError
                    ) {
                        repositoryForbidden.validate()
                    }

                    override fun visitRepositoryNotFound(
                        repositoryNotFound: BetaManagedAgentsRepositoryNotFoundError
                    ) {
                        repositoryNotFound.validate()
                    }

                    override fun visitRepositoryCheckout(
                        repositoryCheckout: BetaManagedAgentsRepositoryCheckoutError
                    ) {
                        repositoryCheckout.validate()
                    }

                    override fun visitRepositoryClone(
                        repositoryClone: BetaManagedAgentsRepositoryCloneError
                    ) {
                        repositoryClone.validate()
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
                    override fun visitUnknown(unknown: BetaManagedAgentsUnknownError) =
                        unknown.validity()

                    override fun visitModelOverloaded(
                        modelOverloaded: BetaManagedAgentsModelOverloadedError
                    ) = modelOverloaded.validity()

                    override fun visitModelRateLimited(
                        modelRateLimited: BetaManagedAgentsModelRateLimitedError
                    ) = modelRateLimited.validity()

                    override fun visitModelRequestFailed(
                        modelRequestFailed: BetaManagedAgentsModelRequestFailedError
                    ) = modelRequestFailed.validity()

                    override fun visitMcpConnectionFailed(
                        mcpConnectionFailed: BetaManagedAgentsMcpConnectionFailedError
                    ) = mcpConnectionFailed.validity()

                    override fun visitMcpAuthenticationFailed(
                        mcpAuthenticationFailed: BetaManagedAgentsMcpAuthenticationFailedError
                    ) = mcpAuthenticationFailed.validity()

                    override fun visitBilling(billing: BetaManagedAgentsBillingError) =
                        billing.validity()

                    override fun visitCredentialHostUnreachable(
                        credentialHostUnreachable: BetaManagedAgentsCredentialHostUnreachableError
                    ) = credentialHostUnreachable.validity()

                    override fun visitRepositoryAuthentication(
                        repositoryAuthentication: BetaManagedAgentsRepositoryAuthenticationError
                    ) = repositoryAuthentication.validity()

                    override fun visitRepositoryForbidden(
                        repositoryForbidden: BetaManagedAgentsRepositoryForbiddenError
                    ) = repositoryForbidden.validity()

                    override fun visitRepositoryNotFound(
                        repositoryNotFound: BetaManagedAgentsRepositoryNotFoundError
                    ) = repositoryNotFound.validity()

                    override fun visitRepositoryCheckout(
                        repositoryCheckout: BetaManagedAgentsRepositoryCheckoutError
                    ) = repositoryCheckout.validity()

                    override fun visitRepositoryClone(
                        repositoryClone: BetaManagedAgentsRepositoryCloneError
                    ) = repositoryClone.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Error &&
                unknown == other.unknown &&
                modelOverloaded == other.modelOverloaded &&
                modelRateLimited == other.modelRateLimited &&
                modelRequestFailed == other.modelRequestFailed &&
                mcpConnectionFailed == other.mcpConnectionFailed &&
                mcpAuthenticationFailed == other.mcpAuthenticationFailed &&
                billing == other.billing &&
                credentialHostUnreachable == other.credentialHostUnreachable &&
                repositoryAuthentication == other.repositoryAuthentication &&
                repositoryForbidden == other.repositoryForbidden &&
                repositoryNotFound == other.repositoryNotFound &&
                repositoryCheckout == other.repositoryCheckout &&
                repositoryClone == other.repositoryClone
        }

        override fun hashCode(): Int =
            Objects.hash(
                unknown,
                modelOverloaded,
                modelRateLimited,
                modelRequestFailed,
                mcpConnectionFailed,
                mcpAuthenticationFailed,
                billing,
                credentialHostUnreachable,
                repositoryAuthentication,
                repositoryForbidden,
                repositoryNotFound,
                repositoryCheckout,
                repositoryClone,
            )

        override fun toString(): String =
            when {
                unknown != null -> "Error{unknown=$unknown}"
                modelOverloaded != null -> "Error{modelOverloaded=$modelOverloaded}"
                modelRateLimited != null -> "Error{modelRateLimited=$modelRateLimited}"
                modelRequestFailed != null -> "Error{modelRequestFailed=$modelRequestFailed}"
                mcpConnectionFailed != null -> "Error{mcpConnectionFailed=$mcpConnectionFailed}"
                mcpAuthenticationFailed != null ->
                    "Error{mcpAuthenticationFailed=$mcpAuthenticationFailed}"
                billing != null -> "Error{billing=$billing}"
                credentialHostUnreachable != null ->
                    "Error{credentialHostUnreachable=$credentialHostUnreachable}"
                repositoryAuthentication != null ->
                    "Error{repositoryAuthentication=$repositoryAuthentication}"
                repositoryForbidden != null -> "Error{repositoryForbidden=$repositoryForbidden}"
                repositoryNotFound != null -> "Error{repositoryNotFound=$repositoryNotFound}"
                repositoryCheckout != null -> "Error{repositoryCheckout=$repositoryCheckout}"
                repositoryClone != null -> "Error{repositoryClone=$repositoryClone}"
                _json != null -> "Error{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Error")
            }

        companion object {

            /**
             * An unknown or unexpected error occurred during session execution. A fallback variant;
             * clients that don't recognize a new error code can match on `retry_status` and
             * `message` alone.
             */
            @JvmStatic
            fun ofUnknown(unknown: BetaManagedAgentsUnknownError) = Error(unknown = unknown)

            /** The model is currently overloaded. Emitted after automatic retries are exhausted. */
            @JvmStatic
            fun ofModelOverloaded(modelOverloaded: BetaManagedAgentsModelOverloadedError) =
                Error(modelOverloaded = modelOverloaded)

            /** The model request was rate-limited. */
            @JvmStatic
            fun ofModelRateLimited(modelRateLimited: BetaManagedAgentsModelRateLimitedError) =
                Error(modelRateLimited = modelRateLimited)

            /** A model request failed for a reason other than overload or rate-limiting. */
            @JvmStatic
            fun ofModelRequestFailed(modelRequestFailed: BetaManagedAgentsModelRequestFailedError) =
                Error(modelRequestFailed = modelRequestFailed)

            /** Failed to connect to an MCP server. */
            @JvmStatic
            fun ofMcpConnectionFailed(
                mcpConnectionFailed: BetaManagedAgentsMcpConnectionFailedError
            ) = Error(mcpConnectionFailed = mcpConnectionFailed)

            /** Authentication to an MCP server failed. */
            @JvmStatic
            fun ofMcpAuthenticationFailed(
                mcpAuthenticationFailed: BetaManagedAgentsMcpAuthenticationFailedError
            ) = Error(mcpAuthenticationFailed = mcpAuthenticationFailed)

            /**
             * The caller's organization or workspace cannot make model requests — out of credits or
             * spend limit reached. Retrying with the same credentials will not succeed; the caller
             * must resolve the billing state.
             */
            @JvmStatic
            fun ofBilling(billing: BetaManagedAgentsBillingError) = Error(billing = billing)

            /**
             * An `environment_variable` credential's `auth.networking.allowed_hosts` includes a
             * host the environment's network policy does not permit.
             */
            @JvmStatic
            fun ofCredentialHostUnreachable(
                credentialHostUnreachable: BetaManagedAgentsCredentialHostUnreachableError
            ) = Error(credentialHostUnreachable = credentialHostUnreachable)

            /**
             * The repository host rejected the credentials, or required credentials and received
             * none.
             */
            @JvmStatic
            fun ofRepositoryAuthentication(
                repositoryAuthentication: BetaManagedAgentsRepositoryAuthenticationError
            ) = Error(repositoryAuthentication = repositoryAuthentication)

            /** The repository host refused access to the repository. */
            @JvmStatic
            fun ofRepositoryForbidden(
                repositoryForbidden: BetaManagedAgentsRepositoryForbiddenError
            ) = Error(repositoryForbidden = repositoryForbidden)

            /** The repository host reported the repository as not found. */
            @JvmStatic
            fun ofRepositoryNotFound(repositoryNotFound: BetaManagedAgentsRepositoryNotFoundError) =
                Error(repositoryNotFound = repositoryNotFound)

            /** The requested branch or commit does not exist in the repository. */
            @JvmStatic
            fun ofRepositoryCheckout(repositoryCheckout: BetaManagedAgentsRepositoryCheckoutError) =
                Error(repositoryCheckout = repositoryCheckout)

            /** The repository could not be cloned. */
            @JvmStatic
            fun ofRepositoryClone(repositoryClone: BetaManagedAgentsRepositoryCloneError) =
                Error(repositoryClone = repositoryClone)
        }

        /** An interface that defines how to map each variant of [Error] to a value of type [T]. */
        interface Visitor<out T> {

            /**
             * An unknown or unexpected error occurred during session execution. A fallback variant;
             * clients that don't recognize a new error code can match on `retry_status` and
             * `message` alone.
             */
            fun visitUnknown(unknown: BetaManagedAgentsUnknownError): T

            /** The model is currently overloaded. Emitted after automatic retries are exhausted. */
            fun visitModelOverloaded(modelOverloaded: BetaManagedAgentsModelOverloadedError): T

            /** The model request was rate-limited. */
            fun visitModelRateLimited(modelRateLimited: BetaManagedAgentsModelRateLimitedError): T

            /** A model request failed for a reason other than overload or rate-limiting. */
            fun visitModelRequestFailed(
                modelRequestFailed: BetaManagedAgentsModelRequestFailedError
            ): T

            /** Failed to connect to an MCP server. */
            fun visitMcpConnectionFailed(
                mcpConnectionFailed: BetaManagedAgentsMcpConnectionFailedError
            ): T

            /** Authentication to an MCP server failed. */
            fun visitMcpAuthenticationFailed(
                mcpAuthenticationFailed: BetaManagedAgentsMcpAuthenticationFailedError
            ): T

            /**
             * The caller's organization or workspace cannot make model requests — out of credits or
             * spend limit reached. Retrying with the same credentials will not succeed; the caller
             * must resolve the billing state.
             */
            fun visitBilling(billing: BetaManagedAgentsBillingError): T

            /**
             * An `environment_variable` credential's `auth.networking.allowed_hosts` includes a
             * host the environment's network policy does not permit.
             */
            fun visitCredentialHostUnreachable(
                credentialHostUnreachable: BetaManagedAgentsCredentialHostUnreachableError
            ): T

            /**
             * The repository host rejected the credentials, or required credentials and received
             * none.
             */
            fun visitRepositoryAuthentication(
                repositoryAuthentication: BetaManagedAgentsRepositoryAuthenticationError
            ): T

            /** The repository host refused access to the repository. */
            fun visitRepositoryForbidden(
                repositoryForbidden: BetaManagedAgentsRepositoryForbiddenError
            ): T

            /** The repository host reported the repository as not found. */
            fun visitRepositoryNotFound(
                repositoryNotFound: BetaManagedAgentsRepositoryNotFoundError
            ): T

            /** The requested branch or commit does not exist in the repository. */
            fun visitRepositoryCheckout(
                repositoryCheckout: BetaManagedAgentsRepositoryCheckoutError
            ): T

            /** The repository could not be cloned. */
            fun visitRepositoryClone(repositoryClone: BetaManagedAgentsRepositoryCloneError): T

            /**
             * Maps an unknown variant of [Error] to a value of type [T].
             *
             * An instance of [Error] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Error: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Error>(Error::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Error {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "unknown_error" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsUnknownError>())
                            ?.let { Error(unknown = it, _json = json) } ?: Error(_json = json)
                    }
                    "model_overloaded_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsModelOverloadedError>(),
                            )
                            ?.let { Error(modelOverloaded = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "model_rate_limited_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsModelRateLimitedError>(),
                            )
                            ?.let { Error(modelRateLimited = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "model_request_failed_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsModelRequestFailedError>(),
                            )
                            ?.let { Error(modelRequestFailed = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "mcp_connection_failed_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsMcpConnectionFailedError>(),
                            )
                            ?.let { Error(mcpConnectionFailed = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "mcp_authentication_failed_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsMcpAuthenticationFailedError>(),
                            )
                            ?.let { Error(mcpAuthenticationFailed = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "billing_error" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsBillingError>())
                            ?.let { Error(billing = it, _json = json) } ?: Error(_json = json)
                    }
                    "credential_host_unreachable_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsCredentialHostUnreachableError>(),
                            )
                            ?.let { Error(credentialHostUnreachable = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "repository_authentication_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsRepositoryAuthenticationError>(),
                            )
                            ?.let { Error(repositoryAuthentication = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "repository_forbidden_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsRepositoryForbiddenError>(),
                            )
                            ?.let { Error(repositoryForbidden = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "repository_not_found_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsRepositoryNotFoundError>(),
                            )
                            ?.let { Error(repositoryNotFound = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "repository_checkout_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsRepositoryCheckoutError>(),
                            )
                            ?.let { Error(repositoryCheckout = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "repository_clone_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsRepositoryCloneError>(),
                            )
                            ?.let { Error(repositoryClone = it, _json = json) }
                            ?: Error(_json = json)
                    }
                }

                return Error(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Error>(Error::class) {

            override fun serialize(
                value: Error,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.unknown != null -> generator.writeObject(value.unknown)
                    value.modelOverloaded != null -> generator.writeObject(value.modelOverloaded)
                    value.modelRateLimited != null -> generator.writeObject(value.modelRateLimited)
                    value.modelRequestFailed != null ->
                        generator.writeObject(value.modelRequestFailed)
                    value.mcpConnectionFailed != null ->
                        generator.writeObject(value.mcpConnectionFailed)
                    value.mcpAuthenticationFailed != null ->
                        generator.writeObject(value.mcpAuthenticationFailed)
                    value.billing != null -> generator.writeObject(value.billing)
                    value.credentialHostUnreachable != null ->
                        generator.writeObject(value.credentialHostUnreachable)
                    value.repositoryAuthentication != null ->
                        generator.writeObject(value.repositoryAuthentication)
                    value.repositoryForbidden != null ->
                        generator.writeObject(value.repositoryForbidden)
                    value.repositoryNotFound != null ->
                        generator.writeObject(value.repositoryNotFound)
                    value.repositoryCheckout != null ->
                        generator.writeObject(value.repositoryCheckout)
                    value.repositoryClone != null -> generator.writeObject(value.repositoryClone)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Error")
                }
            }
        }

        class Type private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val UNKNOWN_ERROR = Type(JsonField.of("unknown_error"))

                @JvmField val MODEL_OVERLOADED_ERROR = Type(JsonField.of("model_overloaded_error"))

                @JvmField
                val MODEL_RATE_LIMITED_ERROR = Type(JsonField.of("model_rate_limited_error"))

                @JvmField
                val MODEL_REQUEST_FAILED_ERROR = Type(JsonField.of("model_request_failed_error"))

                @JvmField
                val MCP_CONNECTION_FAILED_ERROR = Type(JsonField.of("mcp_connection_failed_error"))

                @JvmField
                val MCP_AUTHENTICATION_FAILED_ERROR =
                    Type(JsonField.of("mcp_authentication_failed_error"))

                @JvmField val BILLING_ERROR = Type(JsonField.of("billing_error"))

                @JvmField
                val CREDENTIAL_HOST_UNREACHABLE_ERROR =
                    Type(JsonField.of("credential_host_unreachable_error"))

                @JvmField
                val REPOSITORY_AUTHENTICATION_ERROR =
                    Type(JsonField.of("repository_authentication_error"))

                @JvmField
                val REPOSITORY_FORBIDDEN_ERROR = Type(JsonField.of("repository_forbidden_error"))

                @JvmField
                val REPOSITORY_NOT_FOUND_ERROR = Type(JsonField.of("repository_not_found_error"))

                @JvmField
                val REPOSITORY_CHECKOUT_ERROR = Type(JsonField.of("repository_checkout_error"))

                @JvmField val REPOSITORY_CLONE_ERROR = Type(JsonField.of("repository_clone_error"))

                @JvmStatic
                fun of(value: String): Type =
                    // Intern known values so `==` works
                    when (value) {
                        "unknown_error" -> UNKNOWN_ERROR
                        "model_overloaded_error" -> MODEL_OVERLOADED_ERROR
                        "model_rate_limited_error" -> MODEL_RATE_LIMITED_ERROR
                        "model_request_failed_error" -> MODEL_REQUEST_FAILED_ERROR
                        "mcp_connection_failed_error" -> MCP_CONNECTION_FAILED_ERROR
                        "mcp_authentication_failed_error" -> MCP_AUTHENTICATION_FAILED_ERROR
                        "billing_error" -> BILLING_ERROR
                        "credential_host_unreachable_error" -> CREDENTIAL_HOST_UNREACHABLE_ERROR
                        "repository_authentication_error" -> REPOSITORY_AUTHENTICATION_ERROR
                        "repository_forbidden_error" -> REPOSITORY_FORBIDDEN_ERROR
                        "repository_not_found_error" -> REPOSITORY_NOT_FOUND_ERROR
                        "repository_checkout_error" -> REPOSITORY_CHECKOUT_ERROR
                        "repository_clone_error" -> REPOSITORY_CLONE_ERROR
                        else -> Type(JsonField.of(value))
                    }

                @JsonCreator
                @JvmStatic
                fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                UNKNOWN_ERROR,
                MODEL_OVERLOADED_ERROR,
                MODEL_RATE_LIMITED_ERROR,
                MODEL_REQUEST_FAILED_ERROR,
                MCP_CONNECTION_FAILED_ERROR,
                MCP_AUTHENTICATION_FAILED_ERROR,
                BILLING_ERROR,
                CREDENTIAL_HOST_UNREACHABLE_ERROR,
                REPOSITORY_AUTHENTICATION_ERROR,
                REPOSITORY_FORBIDDEN_ERROR,
                REPOSITORY_NOT_FOUND_ERROR,
                REPOSITORY_CHECKOUT_ERROR,
                REPOSITORY_CLONE_ERROR,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                UNKNOWN_ERROR,
                MODEL_OVERLOADED_ERROR,
                MODEL_RATE_LIMITED_ERROR,
                MODEL_REQUEST_FAILED_ERROR,
                MCP_CONNECTION_FAILED_ERROR,
                MCP_AUTHENTICATION_FAILED_ERROR,
                BILLING_ERROR,
                CREDENTIAL_HOST_UNREACHABLE_ERROR,
                REPOSITORY_AUTHENTICATION_ERROR,
                REPOSITORY_FORBIDDEN_ERROR,
                REPOSITORY_NOT_FOUND_ERROR,
                REPOSITORY_CHECKOUT_ERROR,
                REPOSITORY_CLONE_ERROR,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    UNKNOWN_ERROR -> Value.UNKNOWN_ERROR
                    MODEL_OVERLOADED_ERROR -> Value.MODEL_OVERLOADED_ERROR
                    MODEL_RATE_LIMITED_ERROR -> Value.MODEL_RATE_LIMITED_ERROR
                    MODEL_REQUEST_FAILED_ERROR -> Value.MODEL_REQUEST_FAILED_ERROR
                    MCP_CONNECTION_FAILED_ERROR -> Value.MCP_CONNECTION_FAILED_ERROR
                    MCP_AUTHENTICATION_FAILED_ERROR -> Value.MCP_AUTHENTICATION_FAILED_ERROR
                    BILLING_ERROR -> Value.BILLING_ERROR
                    CREDENTIAL_HOST_UNREACHABLE_ERROR -> Value.CREDENTIAL_HOST_UNREACHABLE_ERROR
                    REPOSITORY_AUTHENTICATION_ERROR -> Value.REPOSITORY_AUTHENTICATION_ERROR
                    REPOSITORY_FORBIDDEN_ERROR -> Value.REPOSITORY_FORBIDDEN_ERROR
                    REPOSITORY_NOT_FOUND_ERROR -> Value.REPOSITORY_NOT_FOUND_ERROR
                    REPOSITORY_CHECKOUT_ERROR -> Value.REPOSITORY_CHECKOUT_ERROR
                    REPOSITORY_CLONE_ERROR -> Value.REPOSITORY_CLONE_ERROR
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    UNKNOWN_ERROR -> Known.UNKNOWN_ERROR
                    MODEL_OVERLOADED_ERROR -> Known.MODEL_OVERLOADED_ERROR
                    MODEL_RATE_LIMITED_ERROR -> Known.MODEL_RATE_LIMITED_ERROR
                    MODEL_REQUEST_FAILED_ERROR -> Known.MODEL_REQUEST_FAILED_ERROR
                    MCP_CONNECTION_FAILED_ERROR -> Known.MCP_CONNECTION_FAILED_ERROR
                    MCP_AUTHENTICATION_FAILED_ERROR -> Known.MCP_AUTHENTICATION_FAILED_ERROR
                    BILLING_ERROR -> Known.BILLING_ERROR
                    CREDENTIAL_HOST_UNREACHABLE_ERROR -> Known.CREDENTIAL_HOST_UNREACHABLE_ERROR
                    REPOSITORY_AUTHENTICATION_ERROR -> Known.REPOSITORY_AUTHENTICATION_ERROR
                    REPOSITORY_FORBIDDEN_ERROR -> Known.REPOSITORY_FORBIDDEN_ERROR
                    REPOSITORY_NOT_FOUND_ERROR -> Known.REPOSITORY_NOT_FOUND_ERROR
                    REPOSITORY_CHECKOUT_ERROR -> Known.REPOSITORY_CHECKOUT_ERROR
                    REPOSITORY_CLONE_ERROR -> Known.REPOSITORY_CLONE_ERROR
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
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
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
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

            @JvmField val SESSION_ERROR = Type(JsonField.of("session.error"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "session.error" -> SESSION_ERROR
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SESSION_ERROR
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
            SESSION_ERROR,
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
                SESSION_ERROR -> Value.SESSION_ERROR
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
                SESSION_ERROR -> Known.SESSION_ERROR
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

        return other is BetaManagedAgentsSessionErrorEvent &&
            id == other.id &&
            error == other.error &&
            processedAt == other.processedAt &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, error, processedAt, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionErrorEvent{id=$id, error=$error, processedAt=$processedAt, type=$type, additionalProperties=$additionalProperties}"
}
