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

class TextEditorCodeExecutionCreateResultBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val isFileUpdate: JsonField<Boolean>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("is_file_update")
        @ExcludeMissing
        isFileUpdate: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(isFileUpdate, type, mutableMapOf())

    fun toParam(): TextEditorCodeExecutionCreateResultBlockParam =
        TextEditorCodeExecutionCreateResultBlockParam.builder()
            .isFileUpdate(_isFileUpdate())
            .build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun isFileUpdate(): Boolean = isFileUpdate.getRequired("is_file_update")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("text_editor_code_execution_create_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [isFileUpdate].
     *
     * Unlike [isFileUpdate], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("is_file_update")
    @ExcludeMissing
    fun _isFileUpdate(): JsonField<Boolean> = isFileUpdate

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
         * [TextEditorCodeExecutionCreateResultBlock].
         *
         * The following fields are required:
         * ```java
         * .isFileUpdate()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TextEditorCodeExecutionCreateResultBlock]. */
    class Builder internal constructor() {

        private var isFileUpdate: JsonField<Boolean>? = null
        private var type: JsonValue = JsonValue.from("text_editor_code_execution_create_result")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            textEditorCodeExecutionCreateResultBlock: TextEditorCodeExecutionCreateResultBlock
        ) = apply {
            isFileUpdate = textEditorCodeExecutionCreateResultBlock.isFileUpdate
            type = textEditorCodeExecutionCreateResultBlock.type
            additionalProperties =
                textEditorCodeExecutionCreateResultBlock.additionalProperties.toMutableMap()
        }

        fun isFileUpdate(isFileUpdate: Boolean) = isFileUpdate(JsonField.of(isFileUpdate))

        /**
         * Sets [Builder.isFileUpdate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.isFileUpdate] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun isFileUpdate(isFileUpdate: JsonField<Boolean>) = apply {
            this.isFileUpdate = isFileUpdate
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("text_editor_code_execution_create_result")
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
         * Returns an immutable instance of [TextEditorCodeExecutionCreateResultBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .isFileUpdate()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): TextEditorCodeExecutionCreateResultBlock =
            TextEditorCodeExecutionCreateResultBlock(
                checkRequired("isFileUpdate", isFileUpdate),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): TextEditorCodeExecutionCreateResultBlock = apply {
        if (validated) {
            return@apply
        }

        isFileUpdate()
        _type().let {
            if (it != JsonValue.from("text_editor_code_execution_create_result")) {
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
        (if (isFileUpdate.asKnown().isPresent) 1 else 0) +
            type.let {
                if (it == JsonValue.from("text_editor_code_execution_create_result")) 1 else 0
            }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TextEditorCodeExecutionCreateResultBlock &&
            isFileUpdate == other.isFileUpdate &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(isFileUpdate, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "TextEditorCodeExecutionCreateResultBlock{isFileUpdate=$isFileUpdate, type=$type, additionalProperties=$additionalProperties}"
}
