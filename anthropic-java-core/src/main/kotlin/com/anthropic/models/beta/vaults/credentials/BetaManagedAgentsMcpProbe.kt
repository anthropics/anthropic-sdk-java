// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The failing step of an MCP validation probe. */
class BetaManagedAgentsMcpProbe
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val httpResponse: JsonField<BetaManagedAgentsRefreshHttpResponse>,
    private val method: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("http_response")
        @ExcludeMissing
        httpResponse: JsonField<BetaManagedAgentsRefreshHttpResponse> = JsonMissing.of(),
        @JsonProperty("method") @ExcludeMissing method: JsonField<String> = JsonMissing.of(),
    ) : this(httpResponse, method, mutableMapOf())

    /**
     * An HTTP response captured during a credential validation probe.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun httpResponse(): Optional<BetaManagedAgentsRefreshHttpResponse> =
        httpResponse.getOptional("http_response")

    /**
     * The MCP method that failed (for example `initialize` or `tools/list`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun method(): String = method.getRequired("method")

    /**
     * Returns the raw JSON value of [httpResponse].
     *
     * Unlike [httpResponse], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("http_response")
    @ExcludeMissing
    fun _httpResponse(): JsonField<BetaManagedAgentsRefreshHttpResponse> = httpResponse

    /**
     * Returns the raw JSON value of [method].
     *
     * Unlike [method], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("method") @ExcludeMissing fun _method(): JsonField<String> = method

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
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsMcpProbe].
         *
         * The following fields are required:
         * ```java
         * .httpResponse()
         * .method()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMcpProbe]. */
    class Builder internal constructor() {

        private var httpResponse: JsonField<BetaManagedAgentsRefreshHttpResponse>? = null
        private var method: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsMcpProbe: BetaManagedAgentsMcpProbe) = apply {
            httpResponse = betaManagedAgentsMcpProbe.httpResponse
            method = betaManagedAgentsMcpProbe.method
            additionalProperties = betaManagedAgentsMcpProbe.additionalProperties.toMutableMap()
        }

        /** An HTTP response captured during a credential validation probe. */
        fun httpResponse(httpResponse: BetaManagedAgentsRefreshHttpResponse?) =
            httpResponse(JsonField.ofNullable(httpResponse))

        /** Alias for calling [Builder.httpResponse] with `httpResponse.orElse(null)`. */
        fun httpResponse(httpResponse: Optional<BetaManagedAgentsRefreshHttpResponse>) =
            httpResponse(httpResponse.getOrNull())

        /**
         * Sets [Builder.httpResponse] to an arbitrary JSON value.
         *
         * You should usually call [Builder.httpResponse] with a well-typed
         * [BetaManagedAgentsRefreshHttpResponse] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun httpResponse(httpResponse: JsonField<BetaManagedAgentsRefreshHttpResponse>) = apply {
            this.httpResponse = httpResponse
        }

        /** The MCP method that failed (for example `initialize` or `tools/list`). */
        fun method(method: String) = method(JsonField.of(method))

        /**
         * Sets [Builder.method] to an arbitrary JSON value.
         *
         * You should usually call [Builder.method] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun method(method: JsonField<String>) = apply { this.method = method }

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
         * Returns an immutable instance of [BetaManagedAgentsMcpProbe].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .httpResponse()
         * .method()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMcpProbe =
            BetaManagedAgentsMcpProbe(
                checkRequired("httpResponse", httpResponse),
                checkRequired("method", method),
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
    fun validate(): BetaManagedAgentsMcpProbe = apply {
        if (validated) {
            return@apply
        }

        httpResponse().ifPresent { it.validate() }
        method()
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
        (httpResponse.asKnown().getOrNull()?.validity() ?: 0) +
            (if (method.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMcpProbe &&
            httpResponse == other.httpResponse &&
            method == other.method &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(httpResponse, method, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMcpProbe{httpResponse=$httpResponse, method=$method, additionalProperties=$additionalProperties}"
}
