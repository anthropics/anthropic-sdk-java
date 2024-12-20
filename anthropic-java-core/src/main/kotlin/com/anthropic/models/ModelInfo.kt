// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.time.OffsetDateTime
import java.util.Objects

@JsonDeserialize(builder = ModelInfo.Builder::class)
@NoAutoDetect
class ModelInfo
private constructor(
    private val type: JsonField<Type>,
    private val id: JsonField<String>,
    private val displayName: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    /**
     * Object type.
     *
     * For Models, this is always `"model"`.
     */
    fun type(): Type = type.getRequired("type")

    /** Unique model identifier. */
    fun id(): String = id.getRequired("id")

    /** A human-readable name for the model. */
    fun displayName(): String = displayName.getRequired("display_name")

    /**
     * RFC 3339 datetime string representing the time at which the model was released. May be set to
     * an epoch value if the release date is unknown.
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Object type.
     *
     * For Models, this is always `"model"`.
     */
    @JsonProperty("type") @ExcludeMissing fun _type() = type

    /** Unique model identifier. */
    @JsonProperty("id") @ExcludeMissing fun _id() = id

    /** A human-readable name for the model. */
    @JsonProperty("display_name") @ExcludeMissing fun _displayName() = displayName

    /**
     * RFC 3339 datetime string representing the time at which the model was released. May be set to
     * an epoch value if the release date is unknown.
     */
    @JsonProperty("created_at") @ExcludeMissing fun _createdAt() = createdAt

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): ModelInfo = apply {
        if (!validated) {
            type()
            id()
            displayName()
            createdAt()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var type: JsonField<Type> = JsonMissing.of()
        private var id: JsonField<String> = JsonMissing.of()
        private var displayName: JsonField<String> = JsonMissing.of()
        private var createdAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(modelInfo: ModelInfo) = apply {
            this.type = modelInfo.type
            this.id = modelInfo.id
            this.displayName = modelInfo.displayName
            this.createdAt = modelInfo.createdAt
            additionalProperties(modelInfo.additionalProperties)
        }

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
        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** Unique model identifier. */
        fun id(id: String) = id(JsonField.of(id))

        /** Unique model identifier. */
        @JsonProperty("id") @ExcludeMissing fun id(id: JsonField<String>) = apply { this.id = id }

        /** A human-readable name for the model. */
        fun displayName(displayName: String) = displayName(JsonField.of(displayName))

        /** A human-readable name for the model. */
        @JsonProperty("display_name")
        @ExcludeMissing
        fun displayName(displayName: JsonField<String>) = apply { this.displayName = displayName }

        /**
         * RFC 3339 datetime string representing the time at which the model was released. May be
         * set to an epoch value if the release date is unknown.
         */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * RFC 3339 datetime string representing the time at which the model was released. May be
         * set to an epoch value if the release date is unknown.
         */
        @JsonProperty("created_at")
        @ExcludeMissing
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            this.additionalProperties.putAll(additionalProperties)
        }

        @JsonAnySetter
        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            this.additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun build(): ModelInfo =
            ModelInfo(
                type,
                id,
                displayName,
                createdAt,
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

        return /* spotless:off */ other is ModelInfo && type == other.type && id == other.id && displayName == other.displayName && createdAt == other.createdAt && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, id, displayName, createdAt, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ModelInfo{type=$type, id=$id, displayName=$displayName, createdAt=$createdAt, additionalProperties=$additionalProperties}"
}
