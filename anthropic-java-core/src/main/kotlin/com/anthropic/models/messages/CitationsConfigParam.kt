// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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
import java.util.Objects
import java.util.Optional

@NoAutoDetect
class CitationsConfigParam
@JsonCreator
private constructor(
    @JsonProperty("enabled")
    @ExcludeMissing
    private val enabled: JsonField<Boolean> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun enabled(): Optional<Boolean> = Optional.ofNullable(enabled.getNullable("enabled"))

    /**
     * Returns the raw JSON value of [enabled].
     *
     * Unlike [enabled], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("enabled") @ExcludeMissing fun _enabled(): JsonField<Boolean> = enabled

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): CitationsConfigParam = apply {
        if (validated) {
            return@apply
        }

        enabled()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Returns a mutable builder for constructing an instance of [CitationsConfigParam]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CitationsConfigParam]. */
    class Builder internal constructor() {

        private var enabled: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(citationsConfigParam: CitationsConfigParam) = apply {
            enabled = citationsConfigParam.enabled
            additionalProperties = citationsConfigParam.additionalProperties.toMutableMap()
        }

        fun enabled(enabled: Boolean) = enabled(JsonField.of(enabled))

        /**
         * Sets [Builder.enabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enabled] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun enabled(enabled: JsonField<Boolean>) = apply { this.enabled = enabled }

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
         * Returns an immutable instance of [CitationsConfigParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): CitationsConfigParam =
            CitationsConfigParam(enabled, additionalProperties.toImmutable())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is CitationsConfigParam && enabled == other.enabled && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(enabled, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CitationsConfigParam{enabled=$enabled, additionalProperties=$additionalProperties}"
}
