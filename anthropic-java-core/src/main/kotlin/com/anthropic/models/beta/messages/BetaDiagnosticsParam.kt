// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

/**
 * Request-level diagnostics. Currently carries the previous response id for prompt-cache divergence
 * reporting.
 */
class BetaDiagnosticsParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val previousMessageId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("previous_message_id")
        @ExcludeMissing
        previousMessageId: JsonField<String> = JsonMissing.of()
    ) : this(previousMessageId, mutableMapOf())

    /**
     * The `id` (`msg_...`) from this client's previous /v1/messages response. The server compares
     * that request's prompt fingerprint against this one and returns
     * `diagnostics.cache_miss_reason` when the prompt-cache prefix could not be reused. Pass `null`
     * on the first turn to opt in without a prior message to compare.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun previousMessageId(): Optional<String> = previousMessageId.getOptional("previous_message_id")

    /**
     * Returns the raw JSON value of [previousMessageId].
     *
     * Unlike [previousMessageId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("previous_message_id")
    @ExcludeMissing
    fun _previousMessageId(): JsonField<String> = previousMessageId

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

        /** Returns a mutable builder for constructing an instance of [BetaDiagnosticsParam]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaDiagnosticsParam]. */
    class Builder internal constructor() {

        private var previousMessageId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaDiagnosticsParam: BetaDiagnosticsParam) = apply {
            previousMessageId = betaDiagnosticsParam.previousMessageId
            additionalProperties = betaDiagnosticsParam.additionalProperties.toMutableMap()
        }

        /**
         * The `id` (`msg_...`) from this client's previous /v1/messages response. The server
         * compares that request's prompt fingerprint against this one and returns
         * `diagnostics.cache_miss_reason` when the prompt-cache prefix could not be reused. Pass
         * `null` on the first turn to opt in without a prior message to compare.
         */
        fun previousMessageId(previousMessageId: String?) =
            previousMessageId(JsonField.ofNullable(previousMessageId))

        /** Alias for calling [Builder.previousMessageId] with `previousMessageId.orElse(null)`. */
        fun previousMessageId(previousMessageId: Optional<String>) =
            previousMessageId(previousMessageId.getOrNull())

        /**
         * Sets [Builder.previousMessageId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.previousMessageId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun previousMessageId(previousMessageId: JsonField<String>) = apply {
            this.previousMessageId = previousMessageId
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
         * Returns an immutable instance of [BetaDiagnosticsParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaDiagnosticsParam =
            BetaDiagnosticsParam(previousMessageId, additionalProperties.toMutableMap())
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
    fun validate(): BetaDiagnosticsParam = apply {
        if (validated) {
            return@apply
        }

        previousMessageId()
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
    internal fun validity(): Int = (if (previousMessageId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaDiagnosticsParam &&
            previousMessageId == other.previousMessageId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(previousMessageId, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaDiagnosticsParam{previousMessageId=$previousMessageId, additionalProperties=$additionalProperties}"
}
