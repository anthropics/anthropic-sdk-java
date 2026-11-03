package com.anthropic.models.beta.organization.claudetag

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
 * The request set the channel up, and `settings` was then refused. The channel stays set up with
 * every setting unset.
 */
class BetaSlackChannelSetUpSettingsRefusedErrorDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val errorCode: JsonValue,
    private val slackChannelId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code") @ExcludeMissing errorCode: JsonValue = JsonMissing.of(),
        @JsonProperty("slack_channel_id")
        @ExcludeMissing
        slackChannelId: JsonField<String> = JsonMissing.of(),
    ) : this(errorCode, slackChannelId, mutableMapOf())

    /**
     * A stable code that names the cause.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("slack_channel_set_up_settings_refused")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("error_code") @ExcludeMissing fun _errorCode(): JsonValue = errorCode

    /**
     * The identifier of the channel the request set up, starting with `slch_`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun slackChannelId(): String = slackChannelId.getRequired("slack_channel_id")

    /**
     * Returns the raw JSON value of [slackChannelId].
     *
     * Unlike [slackChannelId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("slack_channel_id")
    @ExcludeMissing
    fun _slackChannelId(): JsonField<String> = slackChannelId

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
         * [BetaSlackChannelSetUpSettingsRefusedErrorDetails].
         *
         * The following fields are required:
         * ```java
         * .slackChannelId()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaSlackChannelSetUpSettingsRefusedErrorDetails] with
         * the required [slackChannelId] set to the given value.
         */
        @JvmStatic fun of(slackChannelId: String) = builder().slackChannelId(slackChannelId).build()
    }

    /** A builder for [BetaSlackChannelSetUpSettingsRefusedErrorDetails]. */
    class Builder internal constructor() {

        private var errorCode: JsonValue = JsonValue.from("slack_channel_set_up_settings_refused")
        private var slackChannelId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaSlackChannelSetUpSettingsRefusedErrorDetails:
                BetaSlackChannelSetUpSettingsRefusedErrorDetails
        ) = apply {
            errorCode = betaSlackChannelSetUpSettingsRefusedErrorDetails.errorCode
            slackChannelId = betaSlackChannelSetUpSettingsRefusedErrorDetails.slackChannelId
            additionalProperties =
                betaSlackChannelSetUpSettingsRefusedErrorDetails.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("slack_channel_set_up_settings_refused")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun errorCode(errorCode: JsonValue) = apply { this.errorCode = errorCode }

        /** The identifier of the channel the request set up, starting with `slch_`. */
        fun slackChannelId(slackChannelId: String) = slackChannelId(JsonField.of(slackChannelId))

        /**
         * Sets [Builder.slackChannelId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.slackChannelId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun slackChannelId(slackChannelId: JsonField<String>) = apply {
            this.slackChannelId = slackChannelId
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
         * Returns an immutable instance of [BetaSlackChannelSetUpSettingsRefusedErrorDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .slackChannelId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaSlackChannelSetUpSettingsRefusedErrorDetails =
            BetaSlackChannelSetUpSettingsRefusedErrorDetails(
                errorCode,
                checkRequired("slackChannelId", slackChannelId),
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
    fun validate(): BetaSlackChannelSetUpSettingsRefusedErrorDetails = apply {
        if (validated) {
            return@apply
        }

        _errorCode().let {
            if (it != JsonValue.from("slack_channel_set_up_settings_refused")) {
                throw AnthropicInvalidDataException("'errorCode' is invalid, received $it")
            }
        }
        slackChannelId()
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
        errorCode.let {
            if (it == JsonValue.from("slack_channel_set_up_settings_refused")) 1 else 0
        } + (if (slackChannelId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaSlackChannelSetUpSettingsRefusedErrorDetails &&
            errorCode == other.errorCode &&
            slackChannelId == other.slackChannelId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(errorCode, slackChannelId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSlackChannelSetUpSettingsRefusedErrorDetails{errorCode=$errorCode, slackChannelId=$slackChannelId, additionalProperties=$additionalProperties}"
}
