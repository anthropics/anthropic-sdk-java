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

class BetaMetadata
private constructor(
    private val userId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("user_id") @ExcludeMissing userId: JsonField<String> = JsonMissing.of()
    ) : this(userId, mutableMapOf())

    /**
     * An external identifier for the user who is associated with the request.
     *
     * This should be a uuid, hash value, or other opaque identifier. Anthropic may use this id to
     * help detect abuse. Do not include any identifying information such as name, email address, or
     * phone number.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userId(): Optional<String> = userId.getOptional("user_id")

    /**
     * Returns the raw JSON value of [userId].
     *
     * Unlike [userId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_id") @ExcludeMissing fun _userId(): JsonField<String> = userId

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

        /** Returns a mutable builder for constructing an instance of [BetaMetadata]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMetadata]. */
    class Builder internal constructor() {

        private var userId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMetadata: BetaMetadata) = apply {
            userId = betaMetadata.userId
            additionalProperties = betaMetadata.additionalProperties.toMutableMap()
        }

        /**
         * An external identifier for the user who is associated with the request.
         *
         * This should be a uuid, hash value, or other opaque identifier. Anthropic may use this id
         * to help detect abuse. Do not include any identifying information such as name, email
         * address, or phone number.
         */
        fun userId(userId: String?) = userId(JsonField.ofNullable(userId))

        /** Alias for calling [Builder.userId] with `userId.orElse(null)`. */
        fun userId(userId: Optional<String>) = userId(userId.getOrNull())

        /**
         * Sets [Builder.userId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun userId(userId: JsonField<String>) = apply { this.userId = userId }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAlladditional_properties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAlladditional_properties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAlladditional_properties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaMetadata].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaMetadata = BetaMetadata(userId, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): BetaMetadata = apply {
        if (validated) {
            return@apply
        }

        userId()
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
    @JvmSynthetic internal fun validity(): Int = (if (userId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMetadata &&
            userId == other.userId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(userId, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMetadata{userId=$userId, additionalProperties=$additionalProperties}"
}
