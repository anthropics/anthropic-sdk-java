package com.anthropic.models.beta.dreams

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

/**
 * Returned with status 409 when a request to create a dream sets `output_behavior` to
 * `update_existing` and another dream that writes into the same memory store hasn't fully stopped.
 *
 * The other dream is `pending` or `running`, or it has just stopped and is still finishing its last
 * writes. `message` gives the ID of the other dream when the server can identify it. If that dream
 * has already reached `completed`, `failed`, or `canceled`, retry after a short wait. Otherwise,
 * wait for the other dream to end or cancel it, then retry. The response sets the `x-should-retry`
 * header to `false`.
 */
class BetaTargetStoreHeldError
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val message: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
    ) : this(type, message, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("conflict_error")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * A human-readable explanation of why the memory store can't be used yet, with the ID of the
     * dream that is using it when the server can identify it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun message(): Optional<String> = message.getOptional("message")

    /**
     * Returns the raw JSON value of [message].
     *
     * Unlike [message], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

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

        /** Returns a mutable builder for constructing an instance of [BetaTargetStoreHeldError]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTargetStoreHeldError]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("conflict_error")
        private var message: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaTargetStoreHeldError: BetaTargetStoreHeldError) = apply {
            type = betaTargetStoreHeldError.type
            message = betaTargetStoreHeldError.message
            additionalProperties = betaTargetStoreHeldError.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("conflict_error")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * A human-readable explanation of why the memory store can't be used yet, with the ID of
         * the dream that is using it when the server can identify it.
         */
        fun message(message: String) = message(JsonField.of(message))

        /**
         * Sets [Builder.message] to an arbitrary JSON value.
         *
         * You should usually call [Builder.message] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun message(message: JsonField<String>) = apply { this.message = message }

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
         * Returns an immutable instance of [BetaTargetStoreHeldError].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaTargetStoreHeldError =
            BetaTargetStoreHeldError(type, message, additionalProperties.toMutableMap())
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
    fun validate(): BetaTargetStoreHeldError = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("conflict_error")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        message()
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
        type.let { if (it == JsonValue.from("conflict_error")) 1 else 0 } +
            (if (message.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTargetStoreHeldError &&
            type == other.type &&
            message == other.message &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, message, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTargetStoreHeldError{type=$type, message=$message, additionalProperties=$additionalProperties}"
}
