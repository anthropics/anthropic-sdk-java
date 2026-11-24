package com.anthropic.models.beta.compliance.review.cases

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

/** One flagged message of a review case. */
class BetaComplianceReviewTranscript
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val itemId: JsonField<String>,
    private val userProfileId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("item_id") @ExcludeMissing itemId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("user_profile_id")
        @ExcludeMissing
        userProfileId: JsonField<String> = JsonMissing.of(),
    ) : this(itemId, userProfileId, mutableMapOf())

    /**
     * The ID of the flagged message.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun itemId(): String = itemId.getRequired("item_id")

    /**
     * The ID of the user profile that the message belongs to. Null when the message belongs to
     * none, or when the profile can no longer be looked up.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userProfileId(): Optional<String> = userProfileId.getOptional("user_profile_id")

    /**
     * Returns the raw JSON value of [itemId].
     *
     * Unlike [itemId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("item_id") @ExcludeMissing fun _itemId(): JsonField<String> = itemId

    /**
     * Returns the raw JSON value of [userProfileId].
     *
     * Unlike [userProfileId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_profile_id")
    @ExcludeMissing
    fun _userProfileId(): JsonField<String> = userProfileId

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
         * [BetaComplianceReviewTranscript].
         *
         * The following fields are required:
         * ```java
         * .itemId()
         * .userProfileId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaComplianceReviewTranscript]. */
    class Builder internal constructor() {

        private var itemId: JsonField<String>? = null
        private var userProfileId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComplianceReviewTranscript: BetaComplianceReviewTranscript) = apply {
            itemId = betaComplianceReviewTranscript.itemId
            userProfileId = betaComplianceReviewTranscript.userProfileId
            additionalProperties =
                betaComplianceReviewTranscript.additionalProperties.toMutableMap()
        }

        /** The ID of the flagged message. */
        fun itemId(itemId: String) = itemId(JsonField.of(itemId))

        /**
         * Sets [Builder.itemId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.itemId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun itemId(itemId: JsonField<String>) = apply { this.itemId = itemId }

        /**
         * The ID of the user profile that the message belongs to. Null when the message belongs to
         * none, or when the profile can no longer be looked up.
         */
        fun userProfileId(userProfileId: String?) =
            userProfileId(JsonField.ofNullable(userProfileId))

        /** Alias for calling [Builder.userProfileId] with `userProfileId.orElse(null)`. */
        fun userProfileId(userProfileId: Optional<String>) =
            userProfileId(userProfileId.getOrNull())

        /**
         * Sets [Builder.userProfileId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userProfileId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun userProfileId(userProfileId: JsonField<String>) = apply {
            this.userProfileId = userProfileId
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
         * Returns an immutable instance of [BetaComplianceReviewTranscript].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .itemId()
         * .userProfileId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaComplianceReviewTranscript =
            BetaComplianceReviewTranscript(
                checkRequired("itemId", itemId),
                checkRequired("userProfileId", userProfileId),
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
    fun validate(): BetaComplianceReviewTranscript = apply {
        if (validated) {
            return@apply
        }

        itemId()
        userProfileId()
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
        (if (itemId.asKnown().isPresent) 1 else 0) +
            (if (userProfileId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewTranscript &&
            itemId == other.itemId &&
            userProfileId == other.userProfileId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(itemId, userProfileId, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaComplianceReviewTranscript{itemId=$itemId, userProfileId=$userProfileId, additionalProperties=$additionalProperties}"
}
