package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
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

/** The control is on and has a key for at least one region. */
class BetaGoogleManagedCmekEnabled
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val externalKeyIds: JsonField<ExternalKeyIds>,
    private val retiredKeys: JsonField<List<BetaGoogleManagedRetiredKey>>,
    private val status: JsonField<Status>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("external_key_ids")
        @ExcludeMissing
        externalKeyIds: JsonField<ExternalKeyIds> = JsonMissing.of(),
        @JsonProperty("retired_keys")
        @ExcludeMissing
        retiredKeys: JsonField<List<BetaGoogleManagedRetiredKey>> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(externalKeyIds, retiredKeys, status, type, mutableMapOf())

    /**
     * The ID of the External Key configured for each region (`ekey_...`), keyed by region code. A
     * region with no key is left out.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun externalKeyIds(): ExternalKeyIds = externalKeyIds.getRequired("external_key_ids")

    /**
     * Keys that a region has retired, earliest `expires_at` first.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun retiredKeys(): List<BetaGoogleManagedRetiredKey> = retiredKeys.getRequired("retired_keys")

    /**
     * Each region's status for the control, keyed by region code. A region that your organization
     * is not offered may have no entry, and Anthropic may add regions.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): Status = status.getRequired("status")

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
     * Returns the raw JSON value of [externalKeyIds].
     *
     * Unlike [externalKeyIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("external_key_ids")
    @ExcludeMissing
    fun _externalKeyIds(): JsonField<ExternalKeyIds> = externalKeyIds

    /**
     * Returns the raw JSON value of [retiredKeys].
     *
     * Unlike [retiredKeys], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("retired_keys")
    @ExcludeMissing
    fun _retiredKeys(): JsonField<List<BetaGoogleManagedRetiredKey>> = retiredKeys

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

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
         * Returns a mutable builder for constructing an instance of [BetaGoogleManagedCmekEnabled].
         *
         * The following fields are required:
         * ```java
         * .externalKeyIds()
         * .retiredKeys()
         * .status()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedCmekEnabled]. */
    class Builder internal constructor() {

        private var externalKeyIds: JsonField<ExternalKeyIds>? = null
        private var retiredKeys: JsonField<MutableList<BetaGoogleManagedRetiredKey>>? = null
        private var status: JsonField<Status>? = null
        private var type: JsonValue = JsonValue.from("enabled")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaGoogleManagedCmekEnabled: BetaGoogleManagedCmekEnabled) = apply {
            externalKeyIds = betaGoogleManagedCmekEnabled.externalKeyIds
            retiredKeys =
                betaGoogleManagedCmekEnabled.retiredKeys
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            status = betaGoogleManagedCmekEnabled.status
            type = betaGoogleManagedCmekEnabled.type
            additionalProperties = betaGoogleManagedCmekEnabled.additionalProperties.toMutableMap()
        }

        /**
         * The ID of the External Key configured for each region (`ekey_...`), keyed by region code.
         * A region with no key is left out.
         */
        fun externalKeyIds(externalKeyIds: ExternalKeyIds) =
            externalKeyIds(JsonField.of(externalKeyIds))

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

        /** Keys that a region has retired, earliest `expires_at` first. */
        fun retiredKeys(retiredKeys: List<BetaGoogleManagedRetiredKey>) =
            retiredKeys(JsonField.of(retiredKeys))

        /**
         * Sets [Builder.retiredKeys] to an arbitrary JSON value.
         *
         * You should usually call [Builder.retiredKeys] with a well-typed
         * `List<BetaGoogleManagedRetiredKey>` value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun retiredKeys(retiredKeys: JsonField<List<BetaGoogleManagedRetiredKey>>) = apply {
            this.retiredKeys = retiredKeys.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaGoogleManagedRetiredKey] to [retiredKeys].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRetiredKey(retiredKey: BetaGoogleManagedRetiredKey) = apply {
            retiredKeys =
                (retiredKeys ?: JsonField.of(mutableListOf())).also {
                    checkKnown("retiredKeys", it).add(retiredKey)
                }
        }

        /**
         * Each region's status for the control, keyed by region code. A region that your
         * organization is not offered may have no entry, and Anthropic may add regions.
         */
        fun status(status: Status) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [Status] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<Status>) = apply { this.status = status }

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
         * Returns an immutable instance of [BetaGoogleManagedCmekEnabled].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .externalKeyIds()
         * .retiredKeys()
         * .status()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedCmekEnabled =
            BetaGoogleManagedCmekEnabled(
                checkRequired("externalKeyIds", externalKeyIds),
                checkRequired("retiredKeys", retiredKeys).map { it.toImmutable() },
                checkRequired("status", status),
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
    fun validate(): BetaGoogleManagedCmekEnabled = apply {
        if (validated) {
            return@apply
        }

        externalKeyIds().validate()
        retiredKeys().forEach { it.validate() }
        status().validate()
        _type().let {
            if (it != JsonValue.from("enabled")) {
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
        (externalKeyIds.asKnown().getOrNull()?.validity() ?: 0) +
            (retiredKeys.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("enabled")) 1 else 0 }

    /**
     * The ID of the External Key configured for each region (`ekey_...`), keyed by region code. A
     * region with no key is left out.
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

    /**
     * Each region's status for the control, keyed by region code. A region that your organization
     * is not offered may have no entry, and Anthropic may add regions.
     */
    class Status
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

            /** Returns a mutable builder for constructing an instance of [Status]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Status]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(status: Status) = apply {
                additionalProperties = status.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Status].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Status = Status(additionalProperties.toImmutable())
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
        fun validate(): Status = apply {
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

            return other is Status && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Status{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedCmekEnabled &&
            externalKeyIds == other.externalKeyIds &&
            retiredKeys == other.retiredKeys &&
            status == other.status &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(externalKeyIds, retiredKeys, status, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedCmekEnabled{externalKeyIds=$externalKeyIds, retiredKeys=$retiredKeys, status=$status, type=$type, additionalProperties=$additionalProperties}"
}
