// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaAdvisorRedactedResultBlockParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val encryptedContent: JsonField<String>,
    private val type: JsonValue,
    private val stopReason: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("encrypted_content")
        @ExcludeMissing
        encryptedContent: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("stop_reason")
        @ExcludeMissing
        stopReason: JsonField<String> = JsonMissing.of(),
    ) : this(encryptedContent, type, stopReason, mutableMapOf())

    /**
     * Opaque blob produced by a prior response; must be round-tripped verbatim.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun encryptedContent(): String = encryptedContent.getRequired("encrypted_content")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("advisor_redacted_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun stopReason(): Optional<String> = stopReason.getOptional("stop_reason")

    /**
     * Returns the raw JSON value of [encryptedContent].
     *
     * Unlike [encryptedContent], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("encrypted_content")
    @ExcludeMissing
    fun _encryptedContent(): JsonField<String> = encryptedContent

    /**
     * Returns the raw JSON value of [stopReason].
     *
     * Unlike [stopReason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("stop_reason") @ExcludeMissing fun _stopReason(): JsonField<String> = stopReason

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
         * [BetaAdvisorRedactedResultBlockParam].
         *
         * The following fields are required:
         * ```java
         * .encryptedContent()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAdvisorRedactedResultBlockParam]. */
    class Builder internal constructor() {

        private var encryptedContent: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("advisor_redacted_result")
        private var stopReason: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaAdvisorRedactedResultBlockParam: BetaAdvisorRedactedResultBlockParam
        ) = apply {
            encryptedContent = betaAdvisorRedactedResultBlockParam.encryptedContent
            type = betaAdvisorRedactedResultBlockParam.type
            stopReason = betaAdvisorRedactedResultBlockParam.stopReason
            additionalProperties =
                betaAdvisorRedactedResultBlockParam.additionalProperties.toMutableMap()
        }

        /** Opaque blob produced by a prior response; must be round-tripped verbatim. */
        fun encryptedContent(encryptedContent: String) =
            encryptedContent(JsonField.of(encryptedContent))

        /**
         * Sets [Builder.encryptedContent] to an arbitrary JSON value.
         *
         * You should usually call [Builder.encryptedContent] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun encryptedContent(encryptedContent: JsonField<String>) = apply {
            this.encryptedContent = encryptedContent
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("advisor_redacted_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun stopReason(stopReason: String?) = stopReason(JsonField.ofNullable(stopReason))

        /** Alias for calling [Builder.stopReason] with `stopReason.orElse(null)`. */
        fun stopReason(stopReason: Optional<String>) = stopReason(stopReason.getOrNull())

        /**
         * Sets [Builder.stopReason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stopReason] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun stopReason(stopReason: JsonField<String>) = apply { this.stopReason = stopReason }

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
         * Returns an immutable instance of [BetaAdvisorRedactedResultBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .encryptedContent()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAdvisorRedactedResultBlockParam =
            BetaAdvisorRedactedResultBlockParam(
                checkRequired("encryptedContent", encryptedContent),
                type,
                stopReason,
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
    fun validate(): BetaAdvisorRedactedResultBlockParam = apply {
        if (validated) {
            return@apply
        }

        encryptedContent()
        _type().let {
            if (it != JsonValue.from("advisor_redacted_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        stopReason()
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
        (if (encryptedContent.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("advisor_redacted_result")) 1 else 0 } +
            (if (stopReason.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAdvisorRedactedResultBlockParam &&
            encryptedContent == other.encryptedContent &&
            type == other.type &&
            stopReason == other.stopReason &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(encryptedContent, type, stopReason, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAdvisorRedactedResultBlockParam{encryptedContent=$encryptedContent, type=$type, stopReason=$stopReason, additionalProperties=$additionalProperties}"
}
