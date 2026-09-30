package com.anthropic.models.beta.organization.plugins

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

class BetaPluginApiActor
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val apiKeyId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("api_key_id") @ExcludeMissing apiKeyId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(apiKeyId, type, mutableMapOf())

    /**
     * The key's ID.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun apiKeyId(): String = apiKeyId.getRequired("api_key_id")

    /**
     * An Admin API key, in the same form the Compliance API activity feed uses for it.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("api_actor")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [apiKeyId].
     *
     * Unlike [apiKeyId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("api_key_id") @ExcludeMissing fun _apiKeyId(): JsonField<String> = apiKeyId

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
         * Returns a mutable builder for constructing an instance of [BetaPluginApiActor].
         *
         * The following fields are required:
         * ```java
         * .apiKeyId()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaPluginApiActor] with the required [apiKeyId] set to
         * the given value.
         */
        @JvmStatic fun of(apiKeyId: String) = builder().apiKeyId(apiKeyId).build()
    }

    /** A builder for [BetaPluginApiActor]. */
    class Builder internal constructor() {

        private var apiKeyId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("api_actor")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaPluginApiActor: BetaPluginApiActor) = apply {
            apiKeyId = betaPluginApiActor.apiKeyId
            type = betaPluginApiActor.type
            additionalProperties = betaPluginApiActor.additionalProperties.toMutableMap()
        }

        /** The key's ID. */
        fun apiKeyId(apiKeyId: String) = apiKeyId(JsonField.of(apiKeyId))

        /**
         * Sets [Builder.apiKeyId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.apiKeyId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun apiKeyId(apiKeyId: JsonField<String>) = apply { this.apiKeyId = apiKeyId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("api_actor")
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
         * Returns an immutable instance of [BetaPluginApiActor].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .apiKeyId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaPluginApiActor =
            BetaPluginApiActor(
                checkRequired("apiKeyId", apiKeyId),
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
    fun validate(): BetaPluginApiActor = apply {
        if (validated) {
            return@apply
        }

        apiKeyId()
        _type().let {
            if (it != JsonValue.from("api_actor")) {
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
        (if (apiKeyId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("api_actor")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaPluginApiActor &&
            apiKeyId == other.apiKeyId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(apiKeyId, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaPluginApiActor{apiKeyId=$apiKeyId, type=$type, additionalProperties=$additionalProperties}"
}
