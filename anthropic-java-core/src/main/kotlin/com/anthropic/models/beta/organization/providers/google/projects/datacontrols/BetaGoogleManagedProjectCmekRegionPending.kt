package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

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

/**
 * The region is validating the configured key, or the key has passed validation and the region has
 * not started to apply it.
 */
class BetaGoogleManagedProjectCmekRegionPending
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val effectiveExternalKeyId: JsonField<String>,
    private val error: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("effective_external_key_id")
        @ExcludeMissing
        effectiveExternalKeyId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("error") @ExcludeMissing error: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(effectiveExternalKeyId, error, type, mutableMapOf())

    /**
     * The ID of the External Key that the region applies now (`ekey_...`). While a replacement key
     * is `pending` or `failed`, this is the previous key. `null` when the region applies no key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun effectiveExternalKeyId(): Optional<String> =
        effectiveExternalKeyId.getOptional("effective_external_key_id")

    /**
     * Why the latest validation attempt failed. `null` when no attempt has failed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun error(): Optional<String> = error.getOptional("error")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("pending")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [effectiveExternalKeyId].
     *
     * Unlike [effectiveExternalKeyId], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("effective_external_key_id")
    @ExcludeMissing
    fun _effectiveExternalKeyId(): JsonField<String> = effectiveExternalKeyId

    /**
     * Returns the raw JSON value of [error].
     *
     * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<String> = error

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
         * [BetaGoogleManagedProjectCmekRegionPending].
         *
         * The following fields are required:
         * ```java
         * .effectiveExternalKeyId()
         * .error()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedProjectCmekRegionPending]. */
    class Builder internal constructor() {

        private var effectiveExternalKeyId: JsonField<String>? = null
        private var error: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("pending")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaGoogleManagedProjectCmekRegionPending: BetaGoogleManagedProjectCmekRegionPending
        ) = apply {
            effectiveExternalKeyId =
                betaGoogleManagedProjectCmekRegionPending.effectiveExternalKeyId
            error = betaGoogleManagedProjectCmekRegionPending.error
            type = betaGoogleManagedProjectCmekRegionPending.type
            additionalProperties =
                betaGoogleManagedProjectCmekRegionPending.additionalProperties.toMutableMap()
        }

        /**
         * The ID of the External Key that the region applies now (`ekey_...`). While a replacement
         * key is `pending` or `failed`, this is the previous key. `null` when the region applies no
         * key.
         */
        fun effectiveExternalKeyId(effectiveExternalKeyId: String?) =
            effectiveExternalKeyId(JsonField.ofNullable(effectiveExternalKeyId))

        /**
         * Alias for calling [Builder.effectiveExternalKeyId] with
         * `effectiveExternalKeyId.orElse(null)`.
         */
        fun effectiveExternalKeyId(effectiveExternalKeyId: Optional<String>) =
            effectiveExternalKeyId(effectiveExternalKeyId.getOrNull())

        /**
         * Sets [Builder.effectiveExternalKeyId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.effectiveExternalKeyId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun effectiveExternalKeyId(effectiveExternalKeyId: JsonField<String>) = apply {
            this.effectiveExternalKeyId = effectiveExternalKeyId
        }

        /** Why the latest validation attempt failed. `null` when no attempt has failed. */
        fun error(error: String?) = error(JsonField.ofNullable(error))

        /** Alias for calling [Builder.error] with `error.orElse(null)`. */
        fun error(error: Optional<String>) = error(error.getOrNull())

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun error(error: JsonField<String>) = apply { this.error = error }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("pending")
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
         * Returns an immutable instance of [BetaGoogleManagedProjectCmekRegionPending].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .effectiveExternalKeyId()
         * .error()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedProjectCmekRegionPending =
            BetaGoogleManagedProjectCmekRegionPending(
                checkRequired("effectiveExternalKeyId", effectiveExternalKeyId),
                checkRequired("error", error),
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
    fun validate(): BetaGoogleManagedProjectCmekRegionPending = apply {
        if (validated) {
            return@apply
        }

        effectiveExternalKeyId()
        error()
        _type().let {
            if (it != JsonValue.from("pending")) {
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
        (if (effectiveExternalKeyId.asKnown().isPresent) 1 else 0) +
            (if (error.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("pending")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedProjectCmekRegionPending &&
            effectiveExternalKeyId == other.effectiveExternalKeyId &&
            error == other.error &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(effectiveExternalKeyId, error, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedProjectCmekRegionPending{effectiveExternalKeyId=$effectiveExternalKeyId, error=$error, type=$type, additionalProperties=$additionalProperties}"
}
