// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.Objects

@NoAutoDetect
class ModelInfo
@JsonCreator
private constructor(
    @JsonProperty("id") @ExcludeMissing private val id: JsonField<String> = JsonMissing.of(),
    @JsonProperty("created_at")
    @ExcludeMissing
    private val createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    @JsonProperty("display_name")
    @ExcludeMissing
    private val displayName: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /** Unique model identifier. */
    fun id(): String = id.getRequired("id")

    /**
     * RFC 3339 datetime string representing the time at which the model was released. May be set to
     * an epoch value if the release date is unknown.
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /** A human-readable name for the model. */
    fun displayName(): String = displayName.getRequired("display_name")

    /**
     * Object type.
     *
     * For Models, this is always `"model"`.
     */
    fun type(): Type = type.getRequired("type")

    /** Unique model identifier. */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * RFC 3339 datetime string representing the time at which the model was released. May be set to
     * an epoch value if the release date is unknown.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /** A human-readable name for the model. */
    @JsonProperty("display_name")
    @ExcludeMissing
    fun _displayName(): JsonField<String> = displayName

    /**
     * Object type.
     *
     * For Models, this is always `"model"`.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): ModelInfo = apply {
        if (!validated) {
            id()
            createdAt()
            displayName()
            type()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var displayName: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(modelInfo: ModelInfo) = apply {
            id = modelInfo.id
            createdAt = modelInfo.createdAt
            displayName = modelInfo.displayName
            type = modelInfo.type
            additionalProperties = modelInfo.additionalProperties.toMutableMap()
        }

        /** Unique model identifier. */
        fun id(id: String) = id(JsonField.of(id))

        /** Unique model identifier. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * RFC 3339 datetime string representing the time at which the model was released. May be
         * set to an epoch value if the release date is unknown.
         */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * RFC 3339 datetime string representing the time at which the model was released. May be
         * set to an epoch value if the release date is unknown.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** A human-readable name for the model. */
        fun displayName(displayName: String) = displayName(JsonField.of(displayName))

        /** A human-readable name for the model. */
        fun displayName(displayName: JsonField<String>) = apply { this.displayName = displayName }

        /**
         * Object type.
         *
         * For Models, this is always `"model"`.
         */
        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Object type.
         *
         * For Models, this is always `"model"`.
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

        fun build(): ModelInfo =
            ModelInfo(
                checkNotNull(id) { "`id` is required but was not set" },
                checkNotNull(createdAt) { "`createdAt` is required but was not set" },
                checkNotNull(displayName) { "`displayName` is required but was not set" },
                checkNotNull(type) { "`type` is required but was not set" },
                additionalProperties.toImmutable(),
            )
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val MODEL = of("model")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            MODEL,
        }

        enum class Value {
            MODEL,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                MODEL -> Value.MODEL
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                MODEL -> Known.MODEL
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

        return /* spotless:off */ other is ModelInfo && id == other.id && createdAt == other.createdAt && displayName == other.displayName && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(id, createdAt, displayName, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ModelInfo{id=$id, createdAt=$createdAt, displayName=$displayName, type=$type, additionalProperties=$additionalProperties}"
}
