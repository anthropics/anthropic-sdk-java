// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

@NoAutoDetect
class BetaDeletedMessageBatch
@JsonCreator
private constructor(
    @JsonProperty("id") @ExcludeMissing private val id: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /** ID of the Message Batch. */
    fun id(): String = id.getRequired("id")

    /**
     * Deleted object type.
     *
     * For Message Batches, this is always `"message_batch_deleted"`.
     */
    fun type(): Type = type.getRequired("type")

    /** ID of the Message Batch. */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Deleted object type.
     *
     * For Message Batches, this is always `"message_batch_deleted"`.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaDeletedMessageBatch = apply {
        if (validated) {
            return@apply
        }

        id()
        type()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaDeletedMessageBatch: BetaDeletedMessageBatch) = apply {
            id = betaDeletedMessageBatch.id
            type = betaDeletedMessageBatch.type
            additionalProperties = betaDeletedMessageBatch.additionalProperties.toMutableMap()
        }

        /** ID of the Message Batch. */
        fun id(id: String) = id(JsonField.of(id))

        /** ID of the Message Batch. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * Deleted object type.
         *
         * For Message Batches, this is always `"message_batch_deleted"`.
         */
        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Deleted object type.
         *
         * For Message Batches, this is always `"message_batch_deleted"`.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

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

        fun build(): BetaDeletedMessageBatch =
            BetaDeletedMessageBatch(
                checkRequired("id", id),
                checkRequired("type", type),
                additionalProperties.toImmutable(),
            )
    }

    /**
     * Deleted object type.
     *
     * For Message Batches, this is always `"message_batch_deleted"`.
     */
    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val MESSAGE_BATCH_DELETED = of("message_batch_deleted")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            MESSAGE_BATCH_DELETED,
        }

        enum class Value {
            MESSAGE_BATCH_DELETED,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                MESSAGE_BATCH_DELETED -> Value.MESSAGE_BATCH_DELETED
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                MESSAGE_BATCH_DELETED -> Known.MESSAGE_BATCH_DELETED
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaDeletedMessageBatch && id == other.id && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(id, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaDeletedMessageBatch{id=$id, type=$type, additionalProperties=$additionalProperties}"
}
