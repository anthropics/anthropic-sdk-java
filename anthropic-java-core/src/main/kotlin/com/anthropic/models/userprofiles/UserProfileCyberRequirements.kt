package com.anthropic.models.userprofiles

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

/**
 * What a request that names the profile must allow to get the relaxed safeguards of the profile's
 * `active` tiers. A field reads `true` when at least one of those tiers has the requirement and
 * Anthropic has not waived it on that tier for the profile. A request that does not meet a
 * requirement is still served, without the relaxed safeguards of the tiers that have it. The values
 * differ between profiles and can change, so read them rather than assuming them.
 */
class UserProfileCyberRequirements
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val dataRetention: JsonField<Boolean>,
    private val humanReview: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("data_retention")
        @ExcludeMissing
        dataRetention: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("human_review")
        @ExcludeMissing
        humanReview: JsonField<Boolean> = JsonMissing.of(),
    ) : this(dataRetention, humanReview, mutableMapOf())

    /**
     * Whether Anthropic must be able to retain the request's inference data.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun dataRetention(): Boolean = dataRetention.getRequired("data_retention")

    /**
     * Whether Anthropic personnel must be allowed to review the data retained from the request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun humanReview(): Boolean = humanReview.getRequired("human_review")

    /**
     * Returns the raw JSON value of [dataRetention].
     *
     * Unlike [dataRetention], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data_retention")
    @ExcludeMissing
    fun _dataRetention(): JsonField<Boolean> = dataRetention

    /**
     * Returns the raw JSON value of [humanReview].
     *
     * Unlike [humanReview], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("human_review")
    @ExcludeMissing
    fun _humanReview(): JsonField<Boolean> = humanReview

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
         * Returns a mutable builder for constructing an instance of [UserProfileCyberRequirements].
         *
         * The following fields are required:
         * ```java
         * .dataRetention()
         * .humanReview()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserProfileCyberRequirements]. */
    class Builder internal constructor() {

        private var dataRetention: JsonField<Boolean>? = null
        private var humanReview: JsonField<Boolean>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(userProfileCyberRequirements: UserProfileCyberRequirements) = apply {
            dataRetention = userProfileCyberRequirements.dataRetention
            humanReview = userProfileCyberRequirements.humanReview
            additionalProperties = userProfileCyberRequirements.additionalProperties.toMutableMap()
        }

        /** Whether Anthropic must be able to retain the request's inference data. */
        fun dataRetention(dataRetention: Boolean) = dataRetention(JsonField.of(dataRetention))

        /**
         * Sets [Builder.dataRetention] to an arbitrary JSON value.
         *
         * You should usually call [Builder.dataRetention] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun dataRetention(dataRetention: JsonField<Boolean>) = apply {
            this.dataRetention = dataRetention
        }

        /**
         * Whether Anthropic personnel must be allowed to review the data retained from the request.
         */
        fun humanReview(humanReview: Boolean) = humanReview(JsonField.of(humanReview))

        /**
         * Sets [Builder.humanReview] to an arbitrary JSON value.
         *
         * You should usually call [Builder.humanReview] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun humanReview(humanReview: JsonField<Boolean>) = apply { this.humanReview = humanReview }

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
         * Returns an immutable instance of [UserProfileCyberRequirements].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .dataRetention()
         * .humanReview()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UserProfileCyberRequirements =
            UserProfileCyberRequirements(
                checkRequired("dataRetention", dataRetention),
                checkRequired("humanReview", humanReview),
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
    fun validate(): UserProfileCyberRequirements = apply {
        if (validated) {
            return@apply
        }

        dataRetention()
        humanReview()
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
        (if (dataRetention.asKnown().isPresent) 1 else 0) +
            (if (humanReview.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfileCyberRequirements &&
            dataRetention == other.dataRetention &&
            humanReview == other.humanReview &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(dataRetention, humanReview, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UserProfileCyberRequirements{dataRetention=$dataRetention, humanReview=$humanReview, additionalProperties=$additionalProperties}"
}
