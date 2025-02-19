// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

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
class DeletedMessageBatch
@JsonCreator
private constructor(
    @JsonProperty("id") @ExcludeMissing private val id: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /** ID of the Message Batch. */
    fun id(): String = id.getRequired("id")

    /**
     * Deleted object type.
     *
     * For Message Batches, this is always `"message_batch_deleted"`.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /** ID of the Message Batch. */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): DeletedMessageBatch = apply {
        if (validated) {
            return@apply
        }

        id()
        _type().let {
            if (it != JsonValue.from("message_batch_deleted")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [DeletedMessageBatch]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("message_batch_deleted")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(deletedMessageBatch: DeletedMessageBatch) = apply {
            id = deletedMessageBatch.id
            type = deletedMessageBatch.type
            additionalProperties = deletedMessageBatch.additionalProperties.toMutableMap()
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

        fun build(): DeletedMessageBatch =
            DeletedMessageBatch(checkRequired("id", id), type, additionalProperties.toImmutable())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is DeletedMessageBatch && id == other.id && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(id, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "DeletedMessageBatch{id=$id, type=$type, additionalProperties=$additionalProperties}"
}
