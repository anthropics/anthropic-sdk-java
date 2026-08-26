// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

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

class BetaGcpExternalKeyConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val keyName: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("key_name") @ExcludeMissing keyName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(keyName, type, mutableMapOf())

    /**
     * Full resource name of the Cloud KMS key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun keyName(): String = keyName.getRequired("key_name")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("gcp")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [keyName].
     *
     * Unlike [keyName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("key_name") @ExcludeMissing fun _keyName(): JsonField<String> = keyName

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
         * Returns a mutable builder for constructing an instance of [BetaGcpExternalKeyConfig].
         *
         * The following fields are required:
         * ```java
         * .keyName()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaGcpExternalKeyConfig] with the required [keyName]
         * set to the given value.
         */
        @JvmStatic fun of(keyName: String) = builder().keyName(keyName).build()
    }

    /** A builder for [BetaGcpExternalKeyConfig]. */
    class Builder internal constructor() {

        private var keyName: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("gcp")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaGcpExternalKeyConfig: BetaGcpExternalKeyConfig) = apply {
            keyName = betaGcpExternalKeyConfig.keyName
            type = betaGcpExternalKeyConfig.type
            additionalProperties = betaGcpExternalKeyConfig.additionalProperties.toMutableMap()
        }

        /** Full resource name of the Cloud KMS key. */
        fun keyName(keyName: String) = keyName(JsonField.of(keyName))

        /**
         * Sets [Builder.keyName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.keyName] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun keyName(keyName: JsonField<String>) = apply { this.keyName = keyName }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("gcp")
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
         * Returns an immutable instance of [BetaGcpExternalKeyConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .keyName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGcpExternalKeyConfig =
            BetaGcpExternalKeyConfig(
                checkRequired("keyName", keyName),
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
    fun validate(): BetaGcpExternalKeyConfig = apply {
        if (validated) {
            return@apply
        }

        keyName()
        _type().let {
            if (it != JsonValue.from("gcp")) {
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
        (if (keyName.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("gcp")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGcpExternalKeyConfig &&
            keyName == other.keyName &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(keyName, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGcpExternalKeyConfig{keyName=$keyName, type=$type, additionalProperties=$additionalProperties}"
}
