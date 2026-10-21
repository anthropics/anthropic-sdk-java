package com.anthropic.models.beta.dreams

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

/** The memory store that holds a dream's result, as an entry in `outputs`. */
class BetaDreamMemoryStoreOutput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val memoryStoreId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("memory_store_id")
        @ExcludeMissing
        memoryStoreId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(memoryStoreId, type, mutableMapOf())

    /**
     * The ID of the memory store that the dream writes its result to (`memstore_...`).
     *
     * With `output_behavior` set to `create_new`, this is a new memory store. With
     * `update_existing`, it is the input memory store.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun memoryStoreId(): String = memoryStoreId.getRequired("memory_store_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("memory_store")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [memoryStoreId].
     *
     * Unlike [memoryStoreId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("memory_store_id")
    @ExcludeMissing
    fun _memoryStoreId(): JsonField<String> = memoryStoreId

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
         * Returns a mutable builder for constructing an instance of [BetaDreamMemoryStoreOutput].
         *
         * The following fields are required:
         * ```java
         * .memoryStoreId()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaDreamMemoryStoreOutput] with the required
         * [memoryStoreId] set to the given value.
         */
        @JvmStatic fun of(memoryStoreId: String) = builder().memoryStoreId(memoryStoreId).build()
    }

    /** A builder for [BetaDreamMemoryStoreOutput]. */
    class Builder internal constructor() {

        private var memoryStoreId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("memory_store")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaDreamMemoryStoreOutput: BetaDreamMemoryStoreOutput) = apply {
            memoryStoreId = betaDreamMemoryStoreOutput.memoryStoreId
            type = betaDreamMemoryStoreOutput.type
            additionalProperties = betaDreamMemoryStoreOutput.additionalProperties.toMutableMap()
        }

        /**
         * The ID of the memory store that the dream writes its result to (`memstore_...`).
         *
         * With `output_behavior` set to `create_new`, this is a new memory store. With
         * `update_existing`, it is the input memory store.
         */
        fun memoryStoreId(memoryStoreId: String) = memoryStoreId(JsonField.of(memoryStoreId))

        /**
         * Sets [Builder.memoryStoreId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.memoryStoreId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun memoryStoreId(memoryStoreId: JsonField<String>) = apply {
            this.memoryStoreId = memoryStoreId
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("memory_store")
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
         * Returns an immutable instance of [BetaDreamMemoryStoreOutput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .memoryStoreId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaDreamMemoryStoreOutput =
            BetaDreamMemoryStoreOutput(
                checkRequired("memoryStoreId", memoryStoreId),
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
    fun validate(): BetaDreamMemoryStoreOutput = apply {
        if (validated) {
            return@apply
        }

        memoryStoreId()
        _type().let {
            if (it != JsonValue.from("memory_store")) {
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
        (if (memoryStoreId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("memory_store")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaDreamMemoryStoreOutput &&
            memoryStoreId == other.memoryStoreId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(memoryStoreId, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaDreamMemoryStoreOutput{memoryStoreId=$memoryStoreId, type=$type, additionalProperties=$additionalProperties}"
}
