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
import java.util.Objects

@JsonDeserialize(builder = BetaMessageBatchErroredResult.Builder::class)
@NoAutoDetect
class BetaMessageBatchErroredResult
private constructor(
    private val type: JsonField<Type>,
    private val error: JsonField<BetaErrorResponse>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    fun type(): Type = type.getRequired("type")

    fun error(): BetaErrorResponse = error.getRequired("error")

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonProperty("error") @ExcludeMissing fun _error() = error

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): BetaMessageBatchErroredResult = apply {
        if (!validated) {
            type()
            error().validate()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var type: JsonField<Type> = JsonMissing.of()
        private var error: JsonField<BetaErrorResponse> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageBatchErroredResult: BetaMessageBatchErroredResult) = apply {
            this.type = betaMessageBatchErroredResult.type
            this.error = betaMessageBatchErroredResult.error
            additionalProperties(betaMessageBatchErroredResult.additionalProperties)
        }

        fun type(type: Type) = type(JsonField.of(type))

        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun error(error: BetaErrorResponse) = error(JsonField.of(error))

        @JsonProperty("error")
        @ExcludeMissing
        fun error(error: JsonField<BetaErrorResponse>) = apply { this.error = error }

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

        fun build(): BetaMessageBatchErroredResult =
            BetaMessageBatchErroredResult(
                type,
                error,
                additionalProperties.toImmutable(),
            )
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()

        companion object {

            @JvmField val ERRORED = Type(JsonField.of("errored"))

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            ERRORED,
        }

        enum class Value {
            ERRORED,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                ERRORED -> Value.ERRORED
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                ERRORED -> Known.ERRORED
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaMessageBatchErroredResult && type == other.type && error == other.error && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, error, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageBatchErroredResult{type=$type, error=$error, additionalProperties=$additionalProperties}"
}
