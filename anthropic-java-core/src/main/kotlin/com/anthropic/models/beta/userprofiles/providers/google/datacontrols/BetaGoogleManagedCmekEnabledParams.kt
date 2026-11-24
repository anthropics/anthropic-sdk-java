package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Turns the control on, or changes its keys while it is on. */
class BetaGoogleManagedCmekEnabledParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val externalKeyIds: JsonField<ExternalKeyIds>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("external_key_ids")
        @ExcludeMissing
        externalKeyIds: JsonField<ExternalKeyIds> = JsonMissing.of(),
    ) : this(type, externalKeyIds, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("enabled")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The External Key to use in each region, keyed by region code, such as `us` or `eu`. An
     * External Key ID (`ekey_...`) sets that region's key, `null` removes it, and a region you
     * leave out keeps its key. While the control is on, at least one region must have a key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun externalKeyIds(): Optional<ExternalKeyIds> = externalKeyIds.getOptional("external_key_ids")

    /**
     * Returns the raw JSON value of [externalKeyIds].
     *
     * Unlike [externalKeyIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("external_key_ids")
    @ExcludeMissing
    fun _externalKeyIds(): JsonField<ExternalKeyIds> = externalKeyIds

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
         * [BetaGoogleManagedCmekEnabledParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedCmekEnabledParams]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("enabled")
        private var externalKeyIds: JsonField<ExternalKeyIds> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaGoogleManagedCmekEnabledParams: BetaGoogleManagedCmekEnabledParams) =
            apply {
                type = betaGoogleManagedCmekEnabledParams.type
                externalKeyIds = betaGoogleManagedCmekEnabledParams.externalKeyIds
                additionalProperties =
                    betaGoogleManagedCmekEnabledParams.additionalProperties.toMutableMap()
            }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("enabled")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * The External Key to use in each region, keyed by region code, such as `us` or `eu`. An
         * External Key ID (`ekey_...`) sets that region's key, `null` removes it, and a region you
         * leave out keeps its key. While the control is on, at least one region must have a key.
         */
        fun externalKeyIds(externalKeyIds: ExternalKeyIds?) =
            externalKeyIds(JsonField.ofNullable(externalKeyIds))

        /** Alias for calling [Builder.externalKeyIds] with `externalKeyIds.orElse(null)`. */
        fun externalKeyIds(externalKeyIds: Optional<ExternalKeyIds>) =
            externalKeyIds(externalKeyIds.getOrNull())

        /**
         * Sets [Builder.externalKeyIds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.externalKeyIds] with a well-typed [ExternalKeyIds] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun externalKeyIds(externalKeyIds: JsonField<ExternalKeyIds>) = apply {
            this.externalKeyIds = externalKeyIds
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
         * Returns an immutable instance of [BetaGoogleManagedCmekEnabledParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaGoogleManagedCmekEnabledParams =
            BetaGoogleManagedCmekEnabledParams(
                type,
                externalKeyIds,
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
    fun validate(): BetaGoogleManagedCmekEnabledParams = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("enabled")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        externalKeyIds().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("enabled")) 1 else 0 } +
            (externalKeyIds.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * The External Key to use in each region, keyed by region code, such as `us` or `eu`. An
     * External Key ID (`ekey_...`) sets that region's key, `null` removes it, and a region you
     * leave out keeps its key. While the control is on, at least one region must have a key.
     */
    class ExternalKeyIds
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

            /** Returns a mutable builder for constructing an instance of [ExternalKeyIds]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [ExternalKeyIds]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(externalKeyIds: ExternalKeyIds) = apply {
                additionalProperties = externalKeyIds.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [ExternalKeyIds].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): ExternalKeyIds = ExternalKeyIds(additionalProperties.toImmutable())
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
        fun validate(): ExternalKeyIds = apply {
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

            return other is ExternalKeyIds && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "ExternalKeyIds{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedCmekEnabledParams &&
            type == other.type &&
            externalKeyIds == other.externalKeyIds &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, externalKeyIds, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedCmekEnabledParams{type=$type, externalKeyIds=$externalKeyIds, additionalProperties=$additionalProperties}"
}
