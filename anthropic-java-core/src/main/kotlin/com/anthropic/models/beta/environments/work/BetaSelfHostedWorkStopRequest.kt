// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

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

/** Request to stop a work item. */
class BetaSelfHostedWorkStopRequest
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val force: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("force") @ExcludeMissing force: JsonField<Boolean> = JsonMissing.of()
    ) : this(force, mutableMapOf())

    /**
     * If true, immediately stop work without graceful shutdown
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun force(): Optional<Boolean> = force.getOptional("force")

    /**
     * Returns the raw JSON value of [force].
     *
     * Unlike [force], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("force") @ExcludeMissing fun _force(): JsonField<Boolean> = force

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
         * [BetaSelfHostedWorkStopRequest].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaSelfHostedWorkStopRequest]. */
    class Builder internal constructor() {

        private var force: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaSelfHostedWorkStopRequest: BetaSelfHostedWorkStopRequest) = apply {
            force = betaSelfHostedWorkStopRequest.force
            additionalProperties = betaSelfHostedWorkStopRequest.additionalProperties.toMutableMap()
        }

        /** If true, immediately stop work without graceful shutdown */
        fun force(force: Boolean) = force(JsonField.of(force))

        /**
         * Sets [Builder.force] to an arbitrary JSON value.
         *
         * You should usually call [Builder.force] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun force(force: JsonField<Boolean>) = apply { this.force = force }

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
         * Returns an immutable instance of [BetaSelfHostedWorkStopRequest].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaSelfHostedWorkStopRequest =
            BetaSelfHostedWorkStopRequest(force, additionalProperties.toMutableMap())
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
    fun validate(): BetaSelfHostedWorkStopRequest = apply {
        if (validated) {
            return@apply
        }

        force()
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
    @JvmSynthetic internal fun validity(): Int = (if (force.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaSelfHostedWorkStopRequest &&
            force == other.force &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(force, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSelfHostedWorkStopRequest{force=$force, additionalProperties=$additionalProperties}"
}
