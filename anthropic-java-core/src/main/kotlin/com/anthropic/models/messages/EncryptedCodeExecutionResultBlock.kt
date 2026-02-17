// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/** Code execution result with encrypted stdout for PFC + web_search results. */
class EncryptedCodeExecutionResultBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<List<CodeExecutionOutputBlock>>,
    private val encryptedStdout: JsonField<String>,
    private val returnCode: JsonField<Long>,
    private val stderr: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<List<CodeExecutionOutputBlock>> = JsonMissing.of(),
        @JsonProperty("encrypted_stdout")
        @ExcludeMissing
        encryptedStdout: JsonField<String> = JsonMissing.of(),
        @JsonProperty("return_code") @ExcludeMissing returnCode: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("stderr") @ExcludeMissing stderr: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(content, encryptedStdout, returnCode, stderr, type, mutableMapOf())

    fun toParam(): EncryptedCodeExecutionResultBlockParam =
        EncryptedCodeExecutionResultBlockParam.builder()
            .content(_content().map { it.map { it.toParam() } })
            .encryptedStdout(_encryptedStdout())
            .returnCode(_returnCode())
            .stderr(_stderr())
            .build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): List<CodeExecutionOutputBlock> = content.getRequired("content")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun encryptedStdout(): String = encryptedStdout.getRequired("encrypted_stdout")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun returnCode(): Long = returnCode.getRequired("return_code")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun stderr(): String = stderr.getRequired("stderr")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("encrypted_code_execution_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content")
    @ExcludeMissing
    fun _content(): JsonField<List<CodeExecutionOutputBlock>> = content

    /**
     * Returns the raw JSON value of [encryptedStdout].
     *
     * Unlike [encryptedStdout], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("encrypted_stdout")
    @ExcludeMissing
    fun _encryptedStdout(): JsonField<String> = encryptedStdout

    /**
     * Returns the raw JSON value of [returnCode].
     *
     * Unlike [returnCode], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("return_code") @ExcludeMissing fun _returnCode(): JsonField<Long> = returnCode

    /**
     * Returns the raw JSON value of [stderr].
     *
     * Unlike [stderr], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("stderr") @ExcludeMissing fun _stderr(): JsonField<String> = stderr

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
         * [EncryptedCodeExecutionResultBlock].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .encryptedStdout()
         * .returnCode()
         * .stderr()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [EncryptedCodeExecutionResultBlock]. */
    class Builder internal constructor() {

        private var content: JsonField<MutableList<CodeExecutionOutputBlock>>? = null
        private var encryptedStdout: JsonField<String>? = null
        private var returnCode: JsonField<Long>? = null
        private var stderr: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("encrypted_code_execution_result")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(encryptedCodeExecutionResultBlock: EncryptedCodeExecutionResultBlock) =
            apply {
                content = encryptedCodeExecutionResultBlock.content.map { it.toMutableList() }
                encryptedStdout = encryptedCodeExecutionResultBlock.encryptedStdout
                returnCode = encryptedCodeExecutionResultBlock.returnCode
                stderr = encryptedCodeExecutionResultBlock.stderr
                type = encryptedCodeExecutionResultBlock.type
                additionalProperties =
                    encryptedCodeExecutionResultBlock.additionalProperties.toMutableMap()
            }

        fun content(content: List<CodeExecutionOutputBlock>) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed
         * `List<CodeExecutionOutputBlock>` value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<List<CodeExecutionOutputBlock>>) = apply {
            this.content = content.map { it.toMutableList() }
        }

        /**
         * Adds a single [CodeExecutionOutputBlock] to [Builder.content].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addContent(content: CodeExecutionOutputBlock) = apply {
            this.content =
                (this.content ?: JsonField.of(mutableListOf())).also {
                    checkKnown("content", it).add(content)
                }
        }

        fun encryptedStdout(encryptedStdout: String) =
            encryptedStdout(JsonField.of(encryptedStdout))

        /**
         * Sets [Builder.encryptedStdout] to an arbitrary JSON value.
         *
         * You should usually call [Builder.encryptedStdout] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun encryptedStdout(encryptedStdout: JsonField<String>) = apply {
            this.encryptedStdout = encryptedStdout
        }

        fun returnCode(returnCode: Long) = returnCode(JsonField.of(returnCode))

        /**
         * Sets [Builder.returnCode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.returnCode] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun returnCode(returnCode: JsonField<Long>) = apply { this.returnCode = returnCode }

        fun stderr(stderr: String) = stderr(JsonField.of(stderr))

        /**
         * Sets [Builder.stderr] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stderr] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun stderr(stderr: JsonField<String>) = apply { this.stderr = stderr }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("encrypted_code_execution_result")
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
         * Returns an immutable instance of [EncryptedCodeExecutionResultBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .encryptedStdout()
         * .returnCode()
         * .stderr()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): EncryptedCodeExecutionResultBlock =
            EncryptedCodeExecutionResultBlock(
                checkRequired("content", content).map { it.toImmutable() },
                checkRequired("encryptedStdout", encryptedStdout),
                checkRequired("returnCode", returnCode),
                checkRequired("stderr", stderr),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): EncryptedCodeExecutionResultBlock = apply {
        if (validated) {
            return@apply
        }

        content().forEach { it.validate() }
        encryptedStdout()
        returnCode()
        stderr()
        _type().let {
            if (it != JsonValue.from("encrypted_code_execution_result")) {
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
        (content.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (encryptedStdout.asKnown().isPresent) 1 else 0) +
            (if (returnCode.asKnown().isPresent) 1 else 0) +
            (if (stderr.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("encrypted_code_execution_result")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is EncryptedCodeExecutionResultBlock &&
            content == other.content &&
            encryptedStdout == other.encryptedStdout &&
            returnCode == other.returnCode &&
            stderr == other.stderr &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, encryptedStdout, returnCode, stderr, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "EncryptedCodeExecutionResultBlock{content=$content, encryptedStdout=$encryptedStdout, returnCode=$returnCode, stderr=$stderr, type=$type, additionalProperties=$additionalProperties}"
}
