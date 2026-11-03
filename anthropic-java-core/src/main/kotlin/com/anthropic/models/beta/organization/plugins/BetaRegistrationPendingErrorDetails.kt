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

/**
 * The Plugin version was stored but is not yet usable in claude.ai. `plugin_id` and
 * `plugin_version_id` identify what was stored.
 */
class BetaRegistrationPendingErrorDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val errorCode: JsonValue,
    private val pluginId: JsonField<String>,
    private val pluginVersionId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code") @ExcludeMissing errorCode: JsonValue = JsonMissing.of(),
        @JsonProperty("plugin_id") @ExcludeMissing pluginId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("plugin_version_id")
        @ExcludeMissing
        pluginVersionId: JsonField<String> = JsonMissing.of(),
    ) : this(errorCode, pluginId, pluginVersionId, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("registration_pending")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("error_code") @ExcludeMissing fun _errorCode(): JsonValue = errorCode

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pluginId(): String = pluginId.getRequired("plugin_id")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pluginVersionId(): String = pluginVersionId.getRequired("plugin_version_id")

    /**
     * Returns the raw JSON value of [pluginId].
     *
     * Unlike [pluginId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("plugin_id") @ExcludeMissing fun _pluginId(): JsonField<String> = pluginId

    /**
     * Returns the raw JSON value of [pluginVersionId].
     *
     * Unlike [pluginVersionId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("plugin_version_id")
    @ExcludeMissing
    fun _pluginVersionId(): JsonField<String> = pluginVersionId

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
         * [BetaRegistrationPendingErrorDetails].
         *
         * The following fields are required:
         * ```java
         * .pluginId()
         * .pluginVersionId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRegistrationPendingErrorDetails]. */
    class Builder internal constructor() {

        private var errorCode: JsonValue = JsonValue.from("registration_pending")
        private var pluginId: JsonField<String>? = null
        private var pluginVersionId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaRegistrationPendingErrorDetails: BetaRegistrationPendingErrorDetails
        ) = apply {
            errorCode = betaRegistrationPendingErrorDetails.errorCode
            pluginId = betaRegistrationPendingErrorDetails.pluginId
            pluginVersionId = betaRegistrationPendingErrorDetails.pluginVersionId
            additionalProperties =
                betaRegistrationPendingErrorDetails.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("registration_pending")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun errorCode(errorCode: JsonValue) = apply { this.errorCode = errorCode }

        fun pluginId(pluginId: String) = pluginId(JsonField.of(pluginId))

        /**
         * Sets [Builder.pluginId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pluginId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun pluginId(pluginId: JsonField<String>) = apply { this.pluginId = pluginId }

        fun pluginVersionId(pluginVersionId: String) =
            pluginVersionId(JsonField.of(pluginVersionId))

        /**
         * Sets [Builder.pluginVersionId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pluginVersionId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun pluginVersionId(pluginVersionId: JsonField<String>) = apply {
            this.pluginVersionId = pluginVersionId
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
         * Returns an immutable instance of [BetaRegistrationPendingErrorDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .pluginId()
         * .pluginVersionId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaRegistrationPendingErrorDetails =
            BetaRegistrationPendingErrorDetails(
                errorCode,
                checkRequired("pluginId", pluginId),
                checkRequired("pluginVersionId", pluginVersionId),
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
    fun validate(): BetaRegistrationPendingErrorDetails = apply {
        if (validated) {
            return@apply
        }

        _errorCode().let {
            if (it != JsonValue.from("registration_pending")) {
                throw AnthropicInvalidDataException("'errorCode' is invalid, received $it")
            }
        }
        pluginId()
        pluginVersionId()
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
        errorCode.let { if (it == JsonValue.from("registration_pending")) 1 else 0 } +
            (if (pluginId.asKnown().isPresent) 1 else 0) +
            (if (pluginVersionId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRegistrationPendingErrorDetails &&
            errorCode == other.errorCode &&
            pluginId == other.pluginId &&
            pluginVersionId == other.pluginVersionId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(errorCode, pluginId, pluginVersionId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRegistrationPendingErrorDetails{errorCode=$errorCode, pluginId=$pluginId, pluginVersionId=$pluginVersionId, additionalProperties=$additionalProperties}"
}
