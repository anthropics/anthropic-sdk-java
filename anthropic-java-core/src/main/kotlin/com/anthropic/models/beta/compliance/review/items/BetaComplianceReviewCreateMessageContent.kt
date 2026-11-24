package com.anthropic.models.beta.compliance.review.items

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.messages.BetaMessage
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** A request to create a message, and its response. */
class BetaComplianceReviewCreateMessageContent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val request: JsonField<Request>,
    private val response: JsonField<BetaMessage>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("request") @ExcludeMissing request: JsonField<Request> = JsonMissing.of(),
        @JsonProperty("response")
        @ExcludeMissing
        response: JsonField<BetaMessage> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(request, response, type, mutableMapOf())

    /**
     * The `POST /v1/messages` request body, without thinking blocks or unpublished fields. For a
     * user's message from an Anthropic app, such as Claude Code, `system` is a placeholder.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun request(): Request = request.getRequired("request")

    /**
     * The model's `Message`, without thinking blocks or unpublished fields. Null when no response
     * was stored, for example because the client disconnected.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun response(): Optional<BetaMessage> = response.getOptional("response")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("create_message")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [request].
     *
     * Unlike [request], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("request") @ExcludeMissing fun _request(): JsonField<Request> = request

    /**
     * Returns the raw JSON value of [response].
     *
     * Unlike [response], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("response") @ExcludeMissing fun _response(): JsonField<BetaMessage> = response

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
         * [BetaComplianceReviewCreateMessageContent].
         *
         * The following fields are required:
         * ```java
         * .request()
         * .response()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaComplianceReviewCreateMessageContent]. */
    class Builder internal constructor() {

        private var request: JsonField<Request>? = null
        private var response: JsonField<BetaMessage>? = null
        private var type: JsonValue = JsonValue.from("create_message")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaComplianceReviewCreateMessageContent: BetaComplianceReviewCreateMessageContent
        ) = apply {
            request = betaComplianceReviewCreateMessageContent.request
            response = betaComplianceReviewCreateMessageContent.response
            type = betaComplianceReviewCreateMessageContent.type
            additionalProperties =
                betaComplianceReviewCreateMessageContent.additionalProperties.toMutableMap()
        }

        /**
         * The `POST /v1/messages` request body, without thinking blocks or unpublished fields. For
         * a user's message from an Anthropic app, such as Claude Code, `system` is a placeholder.
         */
        fun request(request: Request) = request(JsonField.of(request))

        /**
         * Sets [Builder.request] to an arbitrary JSON value.
         *
         * You should usually call [Builder.request] with a well-typed [Request] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun request(request: JsonField<Request>) = apply { this.request = request }

        /**
         * The model's `Message`, without thinking blocks or unpublished fields. Null when no
         * response was stored, for example because the client disconnected.
         */
        fun response(response: BetaMessage?) = response(JsonField.ofNullable(response))

        /** Alias for calling [Builder.response] with `response.orElse(null)`. */
        fun response(response: Optional<BetaMessage>) = response(response.getOrNull())

        /**
         * Sets [Builder.response] to an arbitrary JSON value.
         *
         * You should usually call [Builder.response] with a well-typed [BetaMessage] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun response(response: JsonField<BetaMessage>) = apply { this.response = response }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("create_message")
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
         * Returns an immutable instance of [BetaComplianceReviewCreateMessageContent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .request()
         * .response()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaComplianceReviewCreateMessageContent =
            BetaComplianceReviewCreateMessageContent(
                checkRequired("request", request),
                checkRequired("response", response),
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
    fun validate(): BetaComplianceReviewCreateMessageContent = apply {
        if (validated) {
            return@apply
        }

        request().validate()
        response().ifPresent { it.validate() }
        _type().let {
            if (it != JsonValue.from("create_message")) {
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
        (request.asKnown().getOrNull()?.validity() ?: 0) +
            (response.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("create_message")) 1 else 0 }

    /**
     * The `POST /v1/messages` request body, without thinking blocks or unpublished fields. For a
     * user's message from an Anthropic app, such as Claude Code, `system` is a placeholder.
     */
    class Request
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Request]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Request]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(request: Request) = apply {
                additionalProperties = request.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Request].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Request = Request(additionalProperties.toImmutable())
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
        fun validate(): Request = apply {
            if (validated) {
                return@apply
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Request && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Request{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewCreateMessageContent &&
            request == other.request &&
            response == other.response &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(request, response, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaComplianceReviewCreateMessageContent{request=$request, response=$response, type=$type, additionalProperties=$additionalProperties}"
}
