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

class BetaAdvisorRedactedResultBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val encryptedContent: JsonField<String>,
    private val stopReason: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("encrypted_content")
        @ExcludeMissing
        encryptedContent: JsonField<String> = JsonMissing.of(),
        @JsonProperty("stop_reason")
        @ExcludeMissing
        stopReason: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(encryptedContent, stopReason, type, mutableMapOf())

    fun toParam(): BetaAdvisorRedactedResultBlockParam =
        BetaAdvisorRedactedResultBlockParam.builder()
            .encryptedContent(_encryptedContent())
            .stopReason(_stopReason())
            .build()

    /**
     * Opaque blob containing the advisor's output. Round-trip verbatim; do not inspect or modify.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun encryptedContent(): String = encryptedContent.getRequired("encrypted_content")

    /**
     * The advisor sub-inference's stop reason (same values as the top-level message `stop_reason`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun stopReason(): Optional<String> = stopReason.getOptional("stop_reason")

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
         * [BetaAdvisorRedactedResultBlock].
         *
         * The following fields are required:
         * ```java
         * .encryptedContent()
         * .stopReason()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAdvisorRedactedResultBlock]. */
    class Builder internal constructor() {

        private var encryptedContent: JsonField<String>? = null
        private var stopReason: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("advisor_redacted_result")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAdvisorRedactedResultBlock: BetaAdvisorRedactedResultBlock) = apply {
            encryptedContent = betaAdvisorRedactedResultBlock.encryptedContent
            stopReason = betaAdvisorRedactedResultBlock.stopReason
            type = betaAdvisorRedactedResultBlock.type
            additionalProperties =
                betaAdvisorRedactedResultBlock.additionalProperties.toMutableMap()
        }

        /**
         * Opaque blob containing the advisor's output. Round-trip verbatim; do not inspect or
         * modify.
         */
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
         * The advisor sub-inference's stop reason (same values as the top-level message
         * `stop_reason`).
         */
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
         * Returns an immutable instance of [BetaAdvisorRedactedResultBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .encryptedContent()
         * .stopReason()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAdvisorRedactedResultBlock =
            BetaAdvisorRedactedResultBlock(
                checkRequired("encryptedContent", encryptedContent),
                checkRequired("stopReason", stopReason),
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
    fun validate(): BetaAdvisorRedactedResultBlock = apply {
        if (validated) {
            return@apply
        }

        encryptedContent()
        stopReason()
        _type().let {
            if (it != JsonValue.from("advisor_redacted_result")) {
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
        (if (encryptedContent.asKnown().isPresent) 1 else 0) +
            (if (stopReason.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("advisor_redacted_result")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAdvisorRedactedResultBlock &&
            encryptedContent == other.encryptedContent &&
            stopReason == other.stopReason &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(encryptedContent, stopReason, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAdvisorRedactedResultBlock{encryptedContent=$encryptedContent, stopReason=$stopReason, type=$type, additionalProperties=$additionalProperties}"
}
