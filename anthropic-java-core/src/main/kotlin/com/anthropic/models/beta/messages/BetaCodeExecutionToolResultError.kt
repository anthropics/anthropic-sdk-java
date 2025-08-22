// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

class BetaCodeExecutionToolResultError
private constructor(
    private val errorCode: JsonField<BetaCodeExecutionToolResultErrorCode>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code")
        @ExcludeMissing
        errorCode: JsonField<BetaCodeExecutionToolResultErrorCode> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(errorCode, type, mutableMapOf())

    fun toParam(): BetaCodeExecutionToolResultErrorParam =
        BetaCodeExecutionToolResultErrorParam.builder().errorCode(_errorCode()).build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun errorCode(): BetaCodeExecutionToolResultErrorCode = errorCode.getRequired("error_code")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("code_execution_tool_result_error")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [errorCode].
     *
     * Unlike [errorCode], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error_code")
    @ExcludeMissing
    fun _errorCode(): JsonField<BetaCodeExecutionToolResultErrorCode> = errorCode

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
         * [BetaCodeExecutionToolResultError].
         *
         * The following fields are required:
         * ```java
         * .errorCode()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCodeExecutionToolResultError]. */
    class Builder internal constructor() {

        private var errorCode: JsonField<BetaCodeExecutionToolResultErrorCode>? = null
        private var type: JsonValue = JsonValue.from("code_execution_tool_result_error")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCodeExecutionToolResultError: BetaCodeExecutionToolResultError) =
            apply {
                errorCode = betaCodeExecutionToolResultError.errorCode
                type = betaCodeExecutionToolResultError.type
                additionalProperties =
                    betaCodeExecutionToolResultError.additionalProperties.toMutableMap()
            }

        fun errorCode(errorCode: BetaCodeExecutionToolResultErrorCode) =
            errorCode(JsonField.of(errorCode))

        /**
         * Sets [Builder.errorCode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.errorCode] with a well-typed
         * [BetaCodeExecutionToolResultErrorCode] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun errorCode(errorCode: JsonField<BetaCodeExecutionToolResultErrorCode>) = apply {
            this.errorCode = errorCode
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("code_execution_tool_result_error")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAlladditional_properties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAlladditional_properties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAlladditional_properties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaCodeExecutionToolResultError].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .errorCode()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaCodeExecutionToolResultError =
            BetaCodeExecutionToolResultError(
                checkRequired("errorCode", errorCode),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaCodeExecutionToolResultError = apply {
        if (validated) {
            return@apply
        }

        errorCode().validate()
        _type().let {
            if (it != JsonValue.from("code_execution_tool_result_error")) {
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
        (errorCode.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("code_execution_tool_result_error")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaCodeExecutionToolResultError &&
            errorCode == other.errorCode &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(errorCode, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCodeExecutionToolResultError{errorCode=$errorCode, type=$type, additionalProperties=$additionalProperties}"
}
