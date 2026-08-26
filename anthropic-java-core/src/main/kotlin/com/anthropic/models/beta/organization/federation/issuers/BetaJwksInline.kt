// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

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

/** JWKS supplied directly; no network fetch. */
class BetaJwksInline
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val keys: JsonField<List<Key>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("keys") @ExcludeMissing keys: JsonField<List<Key>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(keys, type, mutableMapOf())

    /**
     * Inline JWK objects.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun keys(): List<Key> = keys.getRequired("keys")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("inline")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [keys].
     *
     * Unlike [keys], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("keys") @ExcludeMissing fun _keys(): JsonField<List<Key>> = keys

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
         * Returns a mutable builder for constructing an instance of [BetaJwksInline].
         *
         * The following fields are required:
         * ```java
         * .keys()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaJwksInline] with the required [keys] set to the
         * given value.
         */
        @JvmStatic fun of(keys: List<Key>) = builder().keys(keys).build()
    }

    /** A builder for [BetaJwksInline]. */
    class Builder internal constructor() {

        private var keys: JsonField<MutableList<Key>>? = null
        private var type: JsonValue = JsonValue.from("inline")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaJwksInline: BetaJwksInline) = apply {
            keys = betaJwksInline.keys.map { it.toMutableList() }.takeUnless { it.isMissing() }
            type = betaJwksInline.type
            additionalProperties = betaJwksInline.additionalProperties.toMutableMap()
        }

        /** Inline JWK objects. */
        fun keys(keys: List<Key>) = keys(JsonField.of(keys))

        /**
         * Sets [Builder.keys] to an arbitrary JSON value.
         *
         * You should usually call [Builder.keys] with a well-typed `List<Key>` value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun keys(keys: JsonField<List<Key>>) = apply { this.keys = keys.map { it.toMutableList() } }

        /**
         * Adds a single [Key] to [keys].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addKey(key: Key) = apply {
            keys = (keys ?: JsonField.of(mutableListOf())).also { checkKnown("keys", it).add(key) }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("inline")
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
         * Returns an immutable instance of [BetaJwksInline].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .keys()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaJwksInline =
            BetaJwksInline(
                checkRequired("keys", keys).map { it.toImmutable() },
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
    fun validate(): BetaJwksInline = apply {
        if (validated) {
            return@apply
        }

        keys().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("inline")) {
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
        (keys.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            type.let { if (it == JsonValue.from("inline")) 1 else 0 }

    class Key
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

            /** Returns a mutable builder for constructing an instance of [Key]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Key]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(key: Key) = apply {
                additionalProperties = key.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Key].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Key = Key(additionalProperties.toImmutable())
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
        fun validate(): Key = apply {
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

            return other is Key && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Key{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaJwksInline &&
            keys == other.keys &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(keys, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaJwksInline{keys=$keys, type=$type, additionalProperties=$additionalProperties}"
}
