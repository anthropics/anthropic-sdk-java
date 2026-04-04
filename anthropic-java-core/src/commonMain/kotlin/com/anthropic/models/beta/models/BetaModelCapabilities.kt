// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

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
import com.anthropic.core.contentHash

/** Model capability information. */
class BetaModelCapabilities
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val batch: JsonField<BetaCapabilitySupport>,
    private val citations: JsonField<BetaCapabilitySupport>,
    private val codeExecution: JsonField<BetaCapabilitySupport>,
    private val contextManagement: JsonField<BetaContextManagementCapability>,
    private val effort: JsonField<BetaEffortCapability>,
    private val imageInput: JsonField<BetaCapabilitySupport>,
    private val pdfInput: JsonField<BetaCapabilitySupport>,
    private val structuredOutputs: JsonField<BetaCapabilitySupport>,
    private val thinking: JsonField<BetaThinkingCapability>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("batch")
        @ExcludeMissing
        batch: JsonField<BetaCapabilitySupport> = JsonMissing.of(),
        @JsonProperty("citations")
        @ExcludeMissing
        citations: JsonField<BetaCapabilitySupport> = JsonMissing.of(),
        @JsonProperty("code_execution")
        @ExcludeMissing
        codeExecution: JsonField<BetaCapabilitySupport> = JsonMissing.of(),
        @JsonProperty("context_management")
        @ExcludeMissing
        contextManagement: JsonField<BetaContextManagementCapability> = JsonMissing.of(),
        @JsonProperty("effort")
        @ExcludeMissing
        effort: JsonField<BetaEffortCapability> = JsonMissing.of(),
        @JsonProperty("image_input")
        @ExcludeMissing
        imageInput: JsonField<BetaCapabilitySupport> = JsonMissing.of(),
        @JsonProperty("pdf_input")
        @ExcludeMissing
        pdfInput: JsonField<BetaCapabilitySupport> = JsonMissing.of(),
        @JsonProperty("structured_outputs")
        @ExcludeMissing
        structuredOutputs: JsonField<BetaCapabilitySupport> = JsonMissing.of(),
        @JsonProperty("thinking")
        @ExcludeMissing
        thinking: JsonField<BetaThinkingCapability> = JsonMissing.of(),
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
    fun batch(): BetaCapabilitySupport = batch.getRequired("batch")

    /**
     * Whether the model supports citation generation.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun citations(): BetaCapabilitySupport = citations.getRequired("citations")

    /**
     * Whether the model supports code execution tools.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun codeExecution(): BetaCapabilitySupport = codeExecution.getRequired("code_execution")

    /**
     * Context management support and available strategies.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun contextManagement(): BetaContextManagementCapability =
        contextManagement.getRequired("context_management")

    /**
     * Effort (reasoning_effort) support and available levels.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun effort(): BetaEffortCapability = effort.getRequired("effort")

    /**
     * Whether the model accepts image content blocks.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun imageInput(): BetaCapabilitySupport = imageInput.getRequired("image_input")

    /**
     * Whether the model accepts PDF content blocks.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pdfInput(): BetaCapabilitySupport = pdfInput.getRequired("pdf_input")

    /**
     * Whether the model supports structured output / JSON mode / strict tool schemas.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun structuredOutputs(): BetaCapabilitySupport =
        structuredOutputs.getRequired("structured_outputs")

    /**
     * Thinking capability and supported type configurations.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun thinking(): BetaThinkingCapability = thinking.getRequired("thinking")

    /**
     * Returns the raw JSON value of [batch].
     *
     * Unlike [batch], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("batch") @ExcludeMissing fun _batch(): JsonField<BetaCapabilitySupport> = batch

    /**
     * Returns the raw JSON value of [citations].
     *
     * Unlike [citations], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<BetaCapabilitySupport> = citations

    /**
     * Returns the raw JSON value of [codeExecution].
     *
     * Unlike [codeExecution], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("code_execution")
    @ExcludeMissing
    fun _codeExecution(): JsonField<BetaCapabilitySupport> = codeExecution

    /**
     * Returns the raw JSON value of [contextManagement].
     *
     * Unlike [contextManagement], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("context_management")
    @ExcludeMissing
    fun _contextManagement(): JsonField<BetaContextManagementCapability> = contextManagement

    /**
     * Returns the raw JSON value of [effort].
     *
     * Unlike [effort], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("effort") @ExcludeMissing fun _effort(): JsonField<BetaEffortCapability> = effort

    /**
     * Returns the raw JSON value of [imageInput].
     *
     * Unlike [imageInput], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("image_input")
    @ExcludeMissing
    fun _imageInput(): JsonField<BetaCapabilitySupport> = imageInput

    /**
     * Returns the raw JSON value of [pdfInput].
     *
     * Unlike [pdfInput], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("pdf_input")
    @ExcludeMissing
    fun _pdfInput(): JsonField<BetaCapabilitySupport> = pdfInput

    /**
     * Returns the raw JSON value of [structuredOutputs].
     *
     * Unlike [structuredOutputs], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("structured_outputs")
    @ExcludeMissing
    fun _structuredOutputs(): JsonField<BetaCapabilitySupport> = structuredOutputs

    /**
     * Returns the raw JSON value of [thinking].
     *
     * Unlike [thinking], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("thinking")
    @ExcludeMissing
    fun _thinking(): JsonField<BetaThinkingCapability> = thinking

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
         * Returns a mutable builder for constructing an instance of [BetaModelCapabilities].
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

    /** A builder for [BetaModelCapabilities]. */
    class Builder internal constructor() {

        private var batch: JsonField<BetaCapabilitySupport>? = null
        private var citations: JsonField<BetaCapabilitySupport>? = null
        private var codeExecution: JsonField<BetaCapabilitySupport>? = null
        private var contextManagement: JsonField<BetaContextManagementCapability>? = null
        private var effort: JsonField<BetaEffortCapability>? = null
        private var imageInput: JsonField<BetaCapabilitySupport>? = null
        private var pdfInput: JsonField<BetaCapabilitySupport>? = null
        private var structuredOutputs: JsonField<BetaCapabilitySupport>? = null
        private var thinking: JsonField<BetaThinkingCapability>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(betaModelCapabilities: BetaModelCapabilities) = apply {
            batch = betaModelCapabilities.batch
            citations = betaModelCapabilities.citations
            codeExecution = betaModelCapabilities.codeExecution
            contextManagement = betaModelCapabilities.contextManagement
            effort = betaModelCapabilities.effort
            imageInput = betaModelCapabilities.imageInput
            pdfInput = betaModelCapabilities.pdfInput
            structuredOutputs = betaModelCapabilities.structuredOutputs
            thinking = betaModelCapabilities.thinking
            additionalProperties = betaModelCapabilities.additionalProperties.toMutableMap()
        }

        /** Whether the model supports the Batch API. */
        fun batch(batch: BetaCapabilitySupport) = batch(JsonField.of(batch))

        /**
         * Sets [Builder.batch] to an arbitrary JSON value.
         *
         * You should usually call [Builder.batch] with a well-typed [BetaCapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun batch(batch: JsonField<BetaCapabilitySupport>) = apply { this.batch = batch }

        /** Whether the model supports citation generation. */
        fun citations(citations: BetaCapabilitySupport) = citations(JsonField.of(citations))

        /**
         * Sets [Builder.citations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citations] with a well-typed [BetaCapabilitySupport]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun citations(citations: JsonField<BetaCapabilitySupport>) = apply {
            this.citations = citations
        }

        /** Whether the model supports code execution tools. */
        fun codeExecution(codeExecution: BetaCapabilitySupport) =
            codeExecution(JsonField.of(codeExecution))

        /**
         * Sets [Builder.codeExecution] to an arbitrary JSON value.
         *
         * You should usually call [Builder.codeExecution] with a well-typed [BetaCapabilitySupport]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun codeExecution(codeExecution: JsonField<BetaCapabilitySupport>) = apply {
            this.codeExecution = codeExecution
        }

        /** Context management support and available strategies. */
        fun contextManagement(contextManagement: BetaContextManagementCapability) =
            contextManagement(JsonField.of(contextManagement))

        /**
         * Sets [Builder.contextManagement] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contextManagement] with a well-typed
         * [BetaContextManagementCapability] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun contextManagement(contextManagement: JsonField<BetaContextManagementCapability>) =
            apply {
                this.contextManagement = contextManagement
            }

        /** Effort (reasoning_effort) support and available levels. */
        fun effort(effort: BetaEffortCapability) = effort(JsonField.of(effort))

        /**
         * Sets [Builder.effort] to an arbitrary JSON value.
         *
         * You should usually call [Builder.effort] with a well-typed [BetaEffortCapability] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun effort(effort: JsonField<BetaEffortCapability>) = apply { this.effort = effort }

        /** Whether the model accepts image content blocks. */
        fun imageInput(imageInput: BetaCapabilitySupport) = imageInput(JsonField.of(imageInput))

        /**
         * Sets [Builder.imageInput] to an arbitrary JSON value.
         *
         * You should usually call [Builder.imageInput] with a well-typed [BetaCapabilitySupport]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun imageInput(imageInput: JsonField<BetaCapabilitySupport>) = apply {
            this.imageInput = imageInput
        }

        /** Whether the model accepts PDF content blocks. */
        fun pdfInput(pdfInput: BetaCapabilitySupport) = pdfInput(JsonField.of(pdfInput))

        /**
         * Sets [Builder.pdfInput] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pdfInput] with a well-typed [BetaCapabilitySupport]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun pdfInput(pdfInput: JsonField<BetaCapabilitySupport>) = apply {
            this.pdfInput = pdfInput
        }

        /** Whether the model supports structured output / JSON mode / strict tool schemas. */
        fun structuredOutputs(structuredOutputs: BetaCapabilitySupport) =
            structuredOutputs(JsonField.of(structuredOutputs))

        /**
         * Sets [Builder.structuredOutputs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.structuredOutputs] with a well-typed
         * [BetaCapabilitySupport] value instead. This method is primarily for setting the field to
         * an undocumented or not yet supported value.
         */
        fun structuredOutputs(structuredOutputs: JsonField<BetaCapabilitySupport>) = apply {
            this.structuredOutputs = structuredOutputs
        }

        /** Thinking capability and supported type configurations. */
        fun thinking(thinking: BetaThinkingCapability) = thinking(JsonField.of(thinking))

        /**
         * Sets [Builder.thinking] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinking] with a well-typed [BetaThinkingCapability]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun thinking(thinking: JsonField<BetaThinkingCapability>) = apply {
            this.thinking = thinking
        }

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
         * Returns an immutable instance of [BetaModelCapabilities].
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
        fun build(): BetaModelCapabilities =
            BetaModelCapabilities(
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

    fun validate(): BetaModelCapabilities = apply {
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

        return other is BetaModelCapabilities &&
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
        "BetaModelCapabilities{batch=$batch, citations=$citations, codeExecution=$codeExecution, contextManagement=$contextManagement, effort=$effort, imageInput=$imageInput, pdfInput=$pdfInput, structuredOutputs=$structuredOutputs, thinking=$thinking, additionalProperties=$additionalProperties}"
}
