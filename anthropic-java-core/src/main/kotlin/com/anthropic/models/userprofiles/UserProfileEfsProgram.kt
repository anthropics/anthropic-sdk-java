package com.anthropic.models.userprofiles

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * A user profile's standing in Enterprise Frontier Safeguards, a program under which Anthropic
 * approves the entity the profile represents and may approve any of the program's features.
 */
class UserProfileEfsProgram
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val features: JsonField<Features>,
    private val status: JsonField<UserProfileProgramStatus>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("features") @ExcludeMissing features: JsonField<Features> = JsonMissing.of(),
        @JsonProperty("status")
        @ExcludeMissing
        status: JsonField<UserProfileProgramStatus> = JsonMissing.of(),
    ) : this(features, status, mutableMapOf())

    /**
     * The program's features, keyed by feature: `data_retention_exemption` (use of models that
     * normally require data retention, without the data being retained), `cmek` (customer-managed
     * encryption keys) and `storage` (customer-owned storage). Every feature that the caller's
     * organization may read has a key, whether or not it is approved; a missing key does not mean
     * that the feature is inactive. Anthropic may add features; ignore a key you do not recognize.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun features(): Features = features.getRequired("features")

    /**
     * Anthropic's decision on the entity the profile represents: `pending` while it is awaited,
     * `active` once Anthropic has approved the entity for the program, or `rejected` once Anthropic
     * has declined or has removed the entity from the program. `active` does not mean that any one
     * feature is approved: read the feature you need in `features`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): UserProfileProgramStatus = status.getRequired("status")

    /**
     * Returns the raw JSON value of [features].
     *
     * Unlike [features], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("features") @ExcludeMissing fun _features(): JsonField<Features> = features

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status")
    @ExcludeMissing
    fun _status(): JsonField<UserProfileProgramStatus> = status

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
         * Returns a mutable builder for constructing an instance of [UserProfileEfsProgram].
         *
         * The following fields are required:
         * ```java
         * .features()
         * .status()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserProfileEfsProgram]. */
    class Builder internal constructor() {

        private var features: JsonField<Features>? = null
        private var status: JsonField<UserProfileProgramStatus>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(userProfileEfsProgram: UserProfileEfsProgram) = apply {
            features = userProfileEfsProgram.features
            status = userProfileEfsProgram.status
            additionalProperties = userProfileEfsProgram.additionalProperties.toMutableMap()
        }

        /**
         * The program's features, keyed by feature: `data_retention_exemption` (use of models that
         * normally require data retention, without the data being retained), `cmek`
         * (customer-managed encryption keys) and `storage` (customer-owned storage). Every feature
         * that the caller's organization may read has a key, whether or not it is approved; a
         * missing key does not mean that the feature is inactive. Anthropic may add features;
         * ignore a key you do not recognize.
         */
        fun features(features: Features) = features(JsonField.of(features))

        /**
         * Sets [Builder.features] to an arbitrary JSON value.
         *
         * You should usually call [Builder.features] with a well-typed [Features] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun features(features: JsonField<Features>) = apply { this.features = features }

        /**
         * Anthropic's decision on the entity the profile represents: `pending` while it is awaited,
         * `active` once Anthropic has approved the entity for the program, or `rejected` once
         * Anthropic has declined or has removed the entity from the program. `active` does not mean
         * that any one feature is approved: read the feature you need in `features`.
         */
        fun status(status: UserProfileProgramStatus) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [UserProfileProgramStatus]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun status(status: JsonField<UserProfileProgramStatus>) = apply { this.status = status }

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
         * Returns an immutable instance of [UserProfileEfsProgram].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .features()
         * .status()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UserProfileEfsProgram =
            UserProfileEfsProgram(
                checkRequired("features", features),
                checkRequired("status", status),
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
    fun validate(): UserProfileEfsProgram = apply {
        if (validated) {
            return@apply
        }

        features().validate()
        status().validate()
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
        (features.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * The program's features, keyed by feature: `data_retention_exemption` (use of models that
     * normally require data retention, without the data being retained), `cmek` (customer-managed
     * encryption keys) and `storage` (customer-owned storage). Every feature that the caller's
     * organization may read has a key, whether or not it is approved; a missing key does not mean
     * that the feature is inactive. Anthropic may add features; ignore a key you do not recognize.
     */
    class Features
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Features]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Features]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(features: Features) = apply {
                additionalProperties = features.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Features].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Features = Features(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Features = apply {
            if (validated) {
                return@apply
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Features && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Features{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfileEfsProgram &&
            features == other.features &&
            status == other.status &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(features, status, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UserProfileEfsProgram{features=$features, status=$status, additionalProperties=$additionalProperties}"
}
