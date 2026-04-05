// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import kotlinx.kmp.util.core.ExcludeMissing
import kotlinx.kmp.util.core.JsonField
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.kmp.util.core.contentHash

/** Model capability information. */
class ModelCapabilities
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val batch: JsonField<CapabilitySupport>,
    private val citations: JsonField<CapabilitySupport>,
    private val codeExecution: JsonField<CapabilitySupport>,
    private val contextManagement: JsonField<ContextManagementCapability>,
    private val effort: JsonField<EffortCapability>,
    private val imageInput: JsonField<CapabilitySupport>,
    private val pdfInput: JsonField<CapabilitySupport>,
    private val structuredOutputs: JsonField<CapabilitySupport>,
    private val thinking: JsonField<ThinkingCapability>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("batch")
        @ExcludeMissing
        batch: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("citations")
        @ExcludeMissing
        citations: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("code_execution")
        @ExcludeMissing
        codeExecution: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("context_management")
        @ExcludeMissing
        contextManagement: JsonField<ContextManagementCapability> = JsonMissing.of(),
        @JsonProperty("effort")
        @ExcludeMissing
        effort: JsonField<EffortCapability> = JsonMissing.of(),
        @JsonProperty("image_input")
        @ExcludeMissing
        imageInput: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("pdf_input")
        @ExcludeMissing
        pdfInput: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("structured_outputs")
        @ExcludeMissing
        structuredOutputs: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("thinking")
        @ExcludeMissing
        thinking: JsonField<ThinkingCapability> = JsonMissing.of(),
    ) : this(
        batch,
        citations,
        codeExecution,
        contextManagement,
        effort,
        imageInput,
        pdfInput,
        structuredOutputs,
        thinking,
        mutableMapOf(),
    )

    /**
     * Whether the model supports the Batch API.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun batch(): CapabilitySupport = batch.getRequired("batch")

    /**
     * Whether the model supports citation generation.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun citations(): CapabilitySupport = citations.getRequired("citations")

    /**
     * Whether the model supports code execution tools.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun codeExecution(): CapabilitySupport = codeExecution.getRequired("code_execution")

    /**
     * Context management support and available strategies.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun contextManagement(): ContextManagementCapability =
        contextManagement.getRequired("context_management")

    /**
     * Effort (reasoning_effort) support and available levels.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun effort(): EffortCapability = effort.getRequired("effort")

    /**
     * Whether the model accepts image content blocks.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun imageInput(): CapabilitySupport = imageInput.getRequired("image_input")

    /**
     * Whether the model accepts PDF content blocks.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pdfInput(): CapabilitySupport = pdfInput.getRequired("pdf_input")

    /**
     * Whether the model supports structured output / JSON mode / strict tool schemas.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun structuredOutputs(): CapabilitySupport = structuredOutputs.getRequired("structured_outputs")

    /**
     * Thinking capability and supported type configurations.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun thinking(): ThinkingCapability = thinking.getRequired("thinking")

    /**
     * Returns the raw JSON value of [batch].
     *
     * Unlike [batch], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("batch") @ExcludeMissing fun _batch(): JsonField<CapabilitySupport> = batch

    /**
     * Returns the raw JSON value of [citations].
     *
     * Unlike [citations], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<CapabilitySupport> = citations

    /**
     * Returns the raw JSON value of [codeExecution].
     *
     * Unlike [codeExecution], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("code_execution")
    @ExcludeMissing
    fun _codeExecution(): JsonField<CapabilitySupport> = codeExecution

    /**
     * Returns the raw JSON value of [contextManagement].
     *
     * Unlike [contextManagement], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("context_management")
    @ExcludeMissing
    fun _contextManagement(): JsonField<ContextManagementCapability> = contextManagement

    /**
     * Returns the raw JSON value of [effort].
     *
     * Unlike [effort], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("effort") @ExcludeMissing fun _effort(): JsonField<EffortCapability> = effort

    /**
     * Returns the raw JSON value of [imageInput].
     *
     * Unlike [imageInput], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("image_input")
    @ExcludeMissing
    fun _imageInput(): JsonField<CapabilitySupport> = imageInput

    /**
     * Returns the raw JSON value of [pdfInput].
     *
     * Unlike [pdfInput], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("pdf_input")
    @ExcludeMissing
    fun _pdfInput(): JsonField<CapabilitySupport> = pdfInput

    /**
     * Returns the raw JSON value of [structuredOutputs].
     *
     * Unlike [structuredOutputs], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("structured_outputs")
    @ExcludeMissing
    fun _structuredOutputs(): JsonField<CapabilitySupport> = structuredOutputs

    /**
     * Returns the raw JSON value of [thinking].
     *
     * Unlike [thinking], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("thinking")
    @ExcludeMissing
    fun _thinking(): JsonField<ThinkingCapability> = thinking

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        additionalProperties.toMap()

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ModelCapabilities].
         *
         * The following fields are required:
         * ```java
         * .batch()
         * .citations()
         * .codeExecution()
         * .contextManagement()
         * .effort()
         * .imageInput()
         * .pdfInput()
         * .structuredOutputs()
         * .thinking()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ModelCapabilities]. */
    class Builder internal constructor() {

        private var batch: JsonField<CapabilitySupport>? = null
        private var citations: JsonField<CapabilitySupport>? = null
        private var codeExecution: JsonField<CapabilitySupport>? = null
        private var contextManagement: JsonField<ContextManagementCapability>? = null
        private var effort: JsonField<EffortCapability>? = null
        private var imageInput: JsonField<CapabilitySupport>? = null
        private var pdfInput: JsonField<CapabilitySupport>? = null
        private var structuredOutputs: JsonField<CapabilitySupport>? = null
        private var thinking: JsonField<ThinkingCapability>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(modelCapabilities: ModelCapabilities) = apply {
            batch = modelCapabilities.batch
            citations = modelCapabilities.citations
            codeExecution = modelCapabilities.codeExecution
            contextManagement = modelCapabilities.contextManagement
            effort = modelCapabilities.effort
            imageInput = modelCapabilities.imageInput
            pdfInput = modelCapabilities.pdfInput
            structuredOutputs = modelCapabilities.structuredOutputs
            thinking = modelCapabilities.thinking
            additionalProperties = modelCapabilities.additionalProperties.toMutableMap()
        }

        /** Whether the model supports the Batch API. */
        fun batch(batch: CapabilitySupport) = batch(JsonField.of(batch))

        /**
         * Sets [Builder.batch] to an arbitrary JSON value.
         *
         * You should usually call [Builder.batch] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun batch(batch: JsonField<CapabilitySupport>) = apply { this.batch = batch }

        /** Whether the model supports citation generation. */
        fun citations(citations: CapabilitySupport) = citations(JsonField.of(citations))

        /**
         * Sets [Builder.citations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citations] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun citations(citations: JsonField<CapabilitySupport>) = apply {
            this.citations = citations
        }

        /** Whether the model supports code execution tools. */
        fun codeExecution(codeExecution: CapabilitySupport) =
            codeExecution(JsonField.of(codeExecution))

        /**
         * Sets [Builder.codeExecution] to an arbitrary JSON value.
         *
         * You should usually call [Builder.codeExecution] with a well-typed [CapabilitySupport]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun codeExecution(codeExecution: JsonField<CapabilitySupport>) = apply {
            this.codeExecution = codeExecution
        }

        /** Context management support and available strategies. */
        fun contextManagement(contextManagement: ContextManagementCapability) =
            contextManagement(JsonField.of(contextManagement))

        /**
         * Sets [Builder.contextManagement] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contextManagement] with a well-typed
         * [ContextManagementCapability] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun contextManagement(contextManagement: JsonField<ContextManagementCapability>) = apply {
            this.contextManagement = contextManagement
        }

        /** Effort (reasoning_effort) support and available levels. */
        fun effort(effort: EffortCapability) = effort(JsonField.of(effort))

        /**
         * Sets [Builder.effort] to an arbitrary JSON value.
         *
         * You should usually call [Builder.effort] with a well-typed [EffortCapability] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun effort(effort: JsonField<EffortCapability>) = apply { this.effort = effort }

        /** Whether the model accepts image content blocks. */
        fun imageInput(imageInput: CapabilitySupport) = imageInput(JsonField.of(imageInput))

        /**
         * Sets [Builder.imageInput] to an arbitrary JSON value.
         *
         * You should usually call [Builder.imageInput] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun imageInput(imageInput: JsonField<CapabilitySupport>) = apply {
            this.imageInput = imageInput
        }

        /** Whether the model accepts PDF content blocks. */
        fun pdfInput(pdfInput: CapabilitySupport) = pdfInput(JsonField.of(pdfInput))

        /**
         * Sets [Builder.pdfInput] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pdfInput] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun pdfInput(pdfInput: JsonField<CapabilitySupport>) = apply { this.pdfInput = pdfInput }

        /** Whether the model supports structured output / JSON mode / strict tool schemas. */
        fun structuredOutputs(structuredOutputs: CapabilitySupport) =
            structuredOutputs(JsonField.of(structuredOutputs))

        /**
         * Sets [Builder.structuredOutputs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.structuredOutputs] with a well-typed [CapabilitySupport]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun structuredOutputs(structuredOutputs: JsonField<CapabilitySupport>) = apply {
            this.structuredOutputs = structuredOutputs
        }

        /** Thinking capability and supported type configurations. */
        fun thinking(thinking: ThinkingCapability) = thinking(JsonField.of(thinking))

        /**
         * Sets [Builder.thinking] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinking] with a well-typed [ThinkingCapability] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun thinking(thinking: JsonField<ThinkingCapability>) = apply { this.thinking = thinking }

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
         * Returns an immutable instance of [ModelCapabilities].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .batch()
         * .citations()
         * .codeExecution()
         * .contextManagement()
         * .effort()
         * .imageInput()
         * .pdfInput()
         * .structuredOutputs()
         * .thinking()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ModelCapabilities =
            ModelCapabilities(
                checkRequired("batch", batch),
                checkRequired("citations", citations),
                checkRequired("codeExecution", codeExecution),
                checkRequired("contextManagement", contextManagement),
                checkRequired("effort", effort),
                checkRequired("imageInput", imageInput),
                checkRequired("pdfInput", pdfInput),
                checkRequired("structuredOutputs", structuredOutputs),
                checkRequired("thinking", thinking),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ModelCapabilities = apply {
        if (validated) {
            return@apply
        }

        batch().validate()
        citations().validate()
        codeExecution().validate()
        contextManagement().validate()
        effort().validate()
        imageInput().validate()
        pdfInput().validate()
        structuredOutputs().validate()
        thinking().validate()
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
    internal fun validity(): Int =
        (batch.asKnown()?.validity() ?: 0) +
            (citations.asKnown()?.validity() ?: 0) +
            (codeExecution.asKnown()?.validity() ?: 0) +
            (contextManagement.asKnown()?.validity() ?: 0) +
            (effort.asKnown()?.validity() ?: 0) +
            (imageInput.asKnown()?.validity() ?: 0) +
            (pdfInput.asKnown()?.validity() ?: 0) +
            (structuredOutputs.asKnown()?.validity() ?: 0) +
            (thinking.asKnown()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ModelCapabilities &&
            batch == other.batch &&
            citations == other.citations &&
            codeExecution == other.codeExecution &&
            contextManagement == other.contextManagement &&
            effort == other.effort &&
            imageInput == other.imageInput &&
            pdfInput == other.pdfInput &&
            structuredOutputs == other.structuredOutputs &&
            thinking == other.thinking &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        contentHash(
            batch,
            citations,
            codeExecution,
            contextManagement,
            effort,
            imageInput,
            pdfInput,
            structuredOutputs,
            thinking,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ModelCapabilities{batch=$batch, citations=$citations, codeExecution=$codeExecution, contextManagement=$contextManagement, effort=$effort, imageInput=$imageInput, pdfInput=$pdfInput, structuredOutputs=$structuredOutputs, thinking=$thinking, additionalProperties=$additionalProperties}"
}
