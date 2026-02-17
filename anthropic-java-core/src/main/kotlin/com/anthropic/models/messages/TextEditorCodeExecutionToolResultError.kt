// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class TextEditorCodeExecutionToolResultError
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val errorCode: JsonField<TextEditorCodeExecutionToolResultErrorCode>,
    private val errorMessage: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code")
        @ExcludeMissing
        errorCode: JsonField<TextEditorCodeExecutionToolResultErrorCode> = JsonMissing.of(),
        @JsonProperty("error_message")
        @ExcludeMissing
        errorMessage: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(errorCode, errorMessage, type, mutableMapOf())

    fun toParam(): TextEditorCodeExecutionToolResultErrorParam =
        TextEditorCodeExecutionToolResultErrorParam.builder()
            .errorCode(_errorCode())
            .errorMessage(_errorMessage())
            .build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun errorCode(): TextEditorCodeExecutionToolResultErrorCode =
        errorCode.getRequired("error_code")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun errorMessage(): Optional<String> = errorMessage.getOptional("error_message")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("text_editor_code_execution_tool_result_error")
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
    fun _errorCode(): JsonField<TextEditorCodeExecutionToolResultErrorCode> = errorCode

    /**
     * Returns the raw JSON value of [errorMessage].
     *
     * Unlike [errorMessage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error_message")
    @ExcludeMissing
    fun _errorMessage(): JsonField<String> = errorMessage

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
         * [TextEditorCodeExecutionToolResultError].
         *
         * The following fields are required:
         * ```java
         * .errorCode()
         * .errorMessage()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TextEditorCodeExecutionToolResultError]. */
    class Builder internal constructor() {

        private var errorCode: JsonField<TextEditorCodeExecutionToolResultErrorCode>? = null
        private var errorMessage: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("text_editor_code_execution_tool_result_error")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            textEditorCodeExecutionToolResultError: TextEditorCodeExecutionToolResultError
        ) = apply {
            errorCode = textEditorCodeExecutionToolResultError.errorCode
            errorMessage = textEditorCodeExecutionToolResultError.errorMessage
            type = textEditorCodeExecutionToolResultError.type
            additionalProperties =
                textEditorCodeExecutionToolResultError.additionalProperties.toMutableMap()
        }

        fun errorCode(errorCode: TextEditorCodeExecutionToolResultErrorCode) =
            errorCode(JsonField.of(errorCode))

        /**
         * Sets [Builder.errorCode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.errorCode] with a well-typed
         * [TextEditorCodeExecutionToolResultErrorCode] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun errorCode(errorCode: JsonField<TextEditorCodeExecutionToolResultErrorCode>) = apply {
            this.errorCode = errorCode
        }

        fun errorMessage(errorMessage: String?) = errorMessage(JsonField.ofNullable(errorMessage))

        /** Alias for calling [Builder.errorMessage] with `errorMessage.orElse(null)`. */
        fun errorMessage(errorMessage: Optional<String>) = errorMessage(errorMessage.getOrNull())

        /**
         * Sets [Builder.errorMessage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.errorMessage] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun errorMessage(errorMessage: JsonField<String>) = apply {
            this.errorMessage = errorMessage
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("text_editor_code_execution_tool_result_error")
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
         * Returns an immutable instance of [TextEditorCodeExecutionToolResultError].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .errorCode()
         * .errorMessage()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): TextEditorCodeExecutionToolResultError =
            TextEditorCodeExecutionToolResultError(
                checkRequired("errorCode", errorCode),
                checkRequired("errorMessage", errorMessage),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): TextEditorCodeExecutionToolResultError = apply {
        if (validated) {
            return@apply
        }

        errorCode().validate()
        errorMessage()
        _type().let {
            if (it != JsonValue.from("text_editor_code_execution_tool_result_error")) {
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
            (if (errorMessage.asKnown().isPresent) 1 else 0) +
            type.let {
                if (it == JsonValue.from("text_editor_code_execution_tool_result_error")) 1 else 0
            }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TextEditorCodeExecutionToolResultError &&
            errorCode == other.errorCode &&
            errorMessage == other.errorMessage &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(errorCode, errorMessage, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "TextEditorCodeExecutionToolResultError{errorCode=$errorCode, errorMessage=$errorMessage, type=$type, additionalProperties=$additionalProperties}"
}
