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

/** Resume from the response named by ``previous_message_id``. */
class BetaThreadContinueParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val previousMessageId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("previous_message_id")
        @ExcludeMissing
        previousMessageId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(previousMessageId, type, mutableMapOf())

    /**
     * The `id` of the previous response in this Thread.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun previousMessageId(): String = previousMessageId.getRequired("previous_message_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("continue")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

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

        /**
         * Returns a mutable builder for constructing an instance of [BetaThreadContinueParams].
         *
         * The following fields are required:
         * ```java
         * .previousMessageId()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaThreadContinueParams] with the required
         * [previousMessageId] set to the given value.
         */
        @JvmStatic
        fun of(previousMessageId: String) = builder().previousMessageId(previousMessageId).build()
    }

    /** A builder for [BetaThreadContinueParams]. */
    class Builder internal constructor() {

        private var previousMessageId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("continue")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaThreadContinueParams: BetaThreadContinueParams) = apply {
            previousMessageId = betaThreadContinueParams.previousMessageId
            type = betaThreadContinueParams.type
            additionalProperties = betaThreadContinueParams.additionalProperties.toMutableMap()
        }

        /** The `id` of the previous response in this Thread. */
        fun previousMessageId(previousMessageId: String) =
            previousMessageId(JsonField.of(previousMessageId))

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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("continue")
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
         * Returns an immutable instance of [BetaThreadContinueParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .previousMessageId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaThreadContinueParams =
            BetaThreadContinueParams(
                checkRequired("previousMessageId", previousMessageId),
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
    fun validate(): BetaThreadContinueParams = apply {
        if (validated) {
            return@apply
        }

        previousMessageId()
        _type().let {
            if (it != JsonValue.from("continue")) {
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
        (if (previousMessageId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("continue")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaThreadContinueParams &&
            previousMessageId == other.previousMessageId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(previousMessageId, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaThreadContinueParams{previousMessageId=$previousMessageId, type=$type, additionalProperties=$additionalProperties}"
}
