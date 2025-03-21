// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

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
import java.time.OffsetDateTime
import java.util.Objects

@NoAutoDetect
class BetaModelInfo
@JsonCreator
private constructor(
    @JsonProperty("id") @ExcludeMissing private val id: JsonField<String> = JsonMissing.of(),
    @JsonProperty("created_at")
    @ExcludeMissing
    private val createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    @JsonProperty("display_name")
    @ExcludeMissing
    private val displayName: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
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
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

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

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaModelInfo = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        displayName()
        _type().let {
            if (it != JsonValue.from("model")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaModelInfo].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .displayName()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaModelInfo]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var displayName: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("model")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaModelInfo: BetaModelInfo) = apply {
            id = betaModelInfo.id
            createdAt = betaModelInfo.createdAt
            displayName = betaModelInfo.displayName
            type = betaModelInfo.type
            additionalProperties = betaModelInfo.additionalProperties.toMutableMap()
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

        fun build(): BetaModelInfo =
            BetaModelInfo(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("displayName", displayName),
                type,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaModelInfo && id == other.id && createdAt == other.createdAt && displayName == other.displayName && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(id, createdAt, displayName, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaModelInfo{id=$id, createdAt=$createdAt, displayName=$displayName, type=$type, additionalProperties=$additionalProperties}"
}
