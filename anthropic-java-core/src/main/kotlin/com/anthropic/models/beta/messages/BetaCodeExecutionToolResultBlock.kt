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

class BetaCodeExecutionToolResultBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<BetaCodeExecutionToolResultBlockContent>,
    private val toolUseId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<BetaCodeExecutionToolResultBlockContent> = JsonMissing.of(),
        @JsonProperty("tool_use_id")
        @ExcludeMissing
        toolUseId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(content, toolUseId, type, mutableMapOf())

    fun toParam(): BetaCodeExecutionToolResultBlockParam =
        BetaCodeExecutionToolResultBlockParam.builder()
            .content(
                _content().map {
                    it.accept(
                        object :
                            BetaCodeExecutionToolResultBlockContent.Visitor<
                                BetaCodeExecutionToolResultBlockParamContent
                            > {
                            override fun visitError(
                                error: BetaCodeExecutionToolResultError
                            ): BetaCodeExecutionToolResultBlockParamContent =
                                BetaCodeExecutionToolResultBlockParamContent.ofErrorParam(
                                    error.toParam()
                                )

                            override fun visitResultBlock(
                                resultBlock: BetaCodeExecutionResultBlock
                            ): BetaCodeExecutionToolResultBlockParamContent =
                                BetaCodeExecutionToolResultBlockParamContent.ofResultBlockParam(
                                    resultBlock.toParam()
                                )

                            override fun visitEncryptedCodeExecutionResultBlock(
                                encryptedCodeExecutionResultBlock:
                                    BetaEncryptedCodeExecutionResultBlock
                            ): BetaCodeExecutionToolResultBlockParamContent =
                                BetaCodeExecutionToolResultBlockParamContent
                                    .ofEncryptedCodeExecutionResultBlockParam(
                                        encryptedCodeExecutionResultBlock.toParam()
                                    )
                        }
                    )
                }
            )
            .toolUseId(_toolUseId())
            .build()

    /**
     * Code execution result with encrypted stdout for PFC + web_search results.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): BetaCodeExecutionToolResultBlockContent = content.getRequired("content")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun toolUseId(): String = toolUseId.getRequired("tool_use_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("code_execution_tool_result")
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
    fun _content(): JsonField<BetaCodeExecutionToolResultBlockContent> = content

    /**
     * Returns the raw JSON value of [toolUseId].
     *
     * Unlike [toolUseId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool_use_id") @ExcludeMissing fun _toolUseId(): JsonField<String> = toolUseId

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
         * [BetaCodeExecutionToolResultBlock].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .toolUseId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCodeExecutionToolResultBlock]. */
    class Builder internal constructor() {

        private var content: JsonField<BetaCodeExecutionToolResultBlockContent>? = null
        private var toolUseId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("code_execution_tool_result")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCodeExecutionToolResultBlock: BetaCodeExecutionToolResultBlock) =
            apply {
                content = betaCodeExecutionToolResultBlock.content
                toolUseId = betaCodeExecutionToolResultBlock.toolUseId
                type = betaCodeExecutionToolResultBlock.type
                additionalProperties =
                    betaCodeExecutionToolResultBlock.additionalProperties.toMutableMap()
            }

        /** Code execution result with encrypted stdout for PFC + web_search results. */
        fun content(content: BetaCodeExecutionToolResultBlockContent) =
            content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed
         * [BetaCodeExecutionToolResultBlockContent] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<BetaCodeExecutionToolResultBlockContent>) = apply {
            this.content = content
        }

        /**
         * Alias for calling [content] with
         * `BetaCodeExecutionToolResultBlockContent.ofError(error)`.
         */
        fun content(error: BetaCodeExecutionToolResultError) =
            content(BetaCodeExecutionToolResultBlockContent.ofError(error))

        /**
         * Alias for calling [content] with
         * `BetaCodeExecutionToolResultBlockContent.ofResultBlock(resultBlock)`.
         */
        fun content(resultBlock: BetaCodeExecutionResultBlock) =
            content(BetaCodeExecutionToolResultBlockContent.ofResultBlock(resultBlock))

        /**
         * Alias for calling [content] with
         * `BetaCodeExecutionToolResultBlockContent.ofEncryptedCodeExecutionResultBlock(encryptedCodeExecutionResultBlock)`.
         */
        fun content(encryptedCodeExecutionResultBlock: BetaEncryptedCodeExecutionResultBlock) =
            content(
                BetaCodeExecutionToolResultBlockContent.ofEncryptedCodeExecutionResultBlock(
                    encryptedCodeExecutionResultBlock
                )
            )

        fun toolUseId(toolUseId: String) = toolUseId(JsonField.of(toolUseId))

        /**
         * Sets [Builder.toolUseId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolUseId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun toolUseId(toolUseId: JsonField<String>) = apply { this.toolUseId = toolUseId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("code_execution_tool_result")
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
         * Returns an immutable instance of [BetaCodeExecutionToolResultBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .toolUseId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaCodeExecutionToolResultBlock =
            BetaCodeExecutionToolResultBlock(
                checkRequired("content", content),
                checkRequired("toolUseId", toolUseId),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaCodeExecutionToolResultBlock = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        toolUseId()
        _type().let {
            if (it != JsonValue.from("code_execution_tool_result")) {
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
        (content.asKnown().getOrNull()?.validity() ?: 0) +
            (if (toolUseId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("code_execution_tool_result")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaCodeExecutionToolResultBlock &&
            content == other.content &&
            toolUseId == other.toolUseId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, toolUseId, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCodeExecutionToolResultBlock{content=$content, toolUseId=$toolUseId, type=$type, additionalProperties=$additionalProperties}"
}
