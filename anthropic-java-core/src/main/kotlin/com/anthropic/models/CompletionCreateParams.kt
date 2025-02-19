// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects
import java.util.Optional

/**
 * [Legacy] Create a Text Completion.
 *
 * The Text Completions API is a legacy API. We recommend using the
 * [Messages API](https://docs.anthropic.com/en/api/messages) going forward.
 *
 * Future models and features will not be compatible with Text Completions. See our
 * [migration guide](https://docs.anthropic.com/en/api/migrating-from-text-completions-to-messages)
 * for guidance in migrating from Text Completions to Messages.
 */
class CompletionCreateParams
private constructor(
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * The maximum number of tokens to generate before stopping.
     *
     * Note that our models may stop _before_ reaching this maximum. This parameter only specifies
     * the absolute maximum number of tokens to generate.
     */
    fun maxTokensToSample(): Long = body.maxTokensToSample()

    /**
     * The model that will complete your prompt.\n\nSee
     * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     */
    fun model(): Model = body.model()

    /**
     * The prompt that you want Claude to complete.
     *
     * For proper response generation you will need to format your prompt using alternating
     * `\n\nHuman:` and `\n\nAssistant:` conversational turns. For example:
     * ```
     * "\n\nHuman: {userQuestion}\n\nAssistant:"
     * ```
     *
     * See [prompt validation](https://docs.anthropic.com/en/api/prompt-validation) and our guide to
     * [prompt design](https://docs.anthropic.com/en/docs/intro-to-prompting) for more details.
     */
    fun prompt(): String = body.prompt()

    /** An object describing metadata about the request. */
    fun metadata(): Optional<Metadata> = body.metadata()

    /**
     * Sequences that will cause the model to stop generating.
     *
     * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences in the
     * future. By providing the stop_sequences parameter, you may include additional strings that
     * will cause the model to stop generating.
     */
    fun stopSequences(): Optional<List<String>> = body.stopSequences()

    /**
     * Amount of randomness injected into the response.
     *
     * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
     * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
     *
     * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
     */
    fun temperature(): Optional<Double> = body.temperature()

    /**
     * Only sample from the top K options for each subsequent token.
     *
     * Used to remove "long tail" low probability responses.
     * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
     *
     * Recommended for advanced use cases only. You usually only need to use `temperature`.
     */
    fun topK(): Optional<Long> = body.topK()

    /**
     * Use nucleus sampling.
     *
     * In nucleus sampling, we compute the cumulative distribution over all the options for each
     * subsequent token in decreasing probability order and cut it off once it reaches a particular
     * probability specified by `top_p`. You should either alter `temperature` or `top_p`, but not
     * both.
     *
     * Recommended for advanced use cases only. You usually only need to use `temperature`.
     */
    fun topP(): Optional<Double> = body.topP()

    /**
     * The maximum number of tokens to generate before stopping.
     *
     * Note that our models may stop _before_ reaching this maximum. This parameter only specifies
     * the absolute maximum number of tokens to generate.
     */
    fun _maxTokensToSample(): JsonField<Long> = body._maxTokensToSample()

    /**
     * The model that will complete your prompt.\n\nSee
     * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     */
    fun _model(): JsonField<Model> = body._model()

    /**
     * The prompt that you want Claude to complete.
     *
     * For proper response generation you will need to format your prompt using alternating
     * `\n\nHuman:` and `\n\nAssistant:` conversational turns. For example:
     * ```
     * "\n\nHuman: {userQuestion}\n\nAssistant:"
     * ```
     *
     * See [prompt validation](https://docs.anthropic.com/en/api/prompt-validation) and our guide to
     * [prompt design](https://docs.anthropic.com/en/docs/intro-to-prompting) for more details.
     */
    fun _prompt(): JsonField<String> = body._prompt()

    /** An object describing metadata about the request. */
    fun _metadata(): JsonField<Metadata> = body._metadata()

    /**
     * Sequences that will cause the model to stop generating.
     *
     * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences in the
     * future. By providing the stop_sequences parameter, you may include additional strings that
     * will cause the model to stop generating.
     */
    fun _stopSequences(): JsonField<List<String>> = body._stopSequences()

    /**
     * Amount of randomness injected into the response.
     *
     * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
     * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
     *
     * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
     */
    fun _temperature(): JsonField<Double> = body._temperature()

    /**
     * Only sample from the top K options for each subsequent token.
     *
     * Used to remove "long tail" low probability responses.
     * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
     *
     * Recommended for advanced use cases only. You usually only need to use `temperature`.
     */
    fun _topK(): JsonField<Long> = body._topK()

    /**
     * Use nucleus sampling.
     *
     * In nucleus sampling, we compute the cumulative distribution over all the options for each
     * subsequent token in decreasing probability order and cut it off once it reaches a particular
     * probability specified by `top_p`. You should either alter `temperature` or `top_p`, but not
     * both.
     *
     * Recommended for advanced use cases only. You usually only need to use `temperature`.
     */
    fun _topP(): JsonField<Double> = body._topP()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    @JvmSynthetic internal fun _body(): Body = body

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    @NoAutoDetect
    class Body
    @JsonCreator
    private constructor(
        @JsonProperty("max_tokens_to_sample")
        @ExcludeMissing
        private val maxTokensToSample: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("model")
        @ExcludeMissing
        private val model: JsonField<Model> = JsonMissing.of(),
        @JsonProperty("prompt")
        @ExcludeMissing
        private val prompt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("metadata")
        @ExcludeMissing
        private val metadata: JsonField<Metadata> = JsonMissing.of(),
        @JsonProperty("stop_sequences")
        @ExcludeMissing
        private val stopSequences: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("temperature")
        @ExcludeMissing
        private val temperature: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("top_k") @ExcludeMissing private val topK: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("top_p")
        @ExcludeMissing
        private val topP: JsonField<Double> = JsonMissing.of(),
        @JsonAnySetter
        private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
    ) {

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         */
        fun maxTokensToSample(): Long = maxTokensToSample.getRequired("max_tokens_to_sample")

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(): Model = model.getRequired("model")

        /**
         * The prompt that you want Claude to complete.
         *
         * For proper response generation you will need to format your prompt using alternating
         * `\n\nHuman:` and `\n\nAssistant:` conversational turns. For example:
         * ```
         * "\n\nHuman: {userQuestion}\n\nAssistant:"
         * ```
         *
         * See [prompt validation](https://docs.anthropic.com/en/api/prompt-validation) and our
         * guide to [prompt design](https://docs.anthropic.com/en/docs/intro-to-prompting) for more
         * details.
         */
        fun prompt(): String = prompt.getRequired("prompt")

        /** An object describing metadata about the request. */
        fun metadata(): Optional<Metadata> = Optional.ofNullable(metadata.getNullable("metadata"))

        /**
         * Sequences that will cause the model to stop generating.
         *
         * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences in
         * the future. By providing the stop_sequences parameter, you may include additional strings
         * that will cause the model to stop generating.
         */
        fun stopSequences(): Optional<List<String>> =
            Optional.ofNullable(stopSequences.getNullable("stop_sequences"))

        /**
         * Amount of randomness injected into the response.
         *
         * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
         * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
         *
         * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
         */
        fun temperature(): Optional<Double> =
            Optional.ofNullable(temperature.getNullable("temperature"))

        /**
         * Only sample from the top K options for each subsequent token.
         *
         * Used to remove "long tail" low probability responses.
         * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        fun topK(): Optional<Long> = Optional.ofNullable(topK.getNullable("top_k"))

        /**
         * Use nucleus sampling.
         *
         * In nucleus sampling, we compute the cumulative distribution over all the options for each
         * subsequent token in decreasing probability order and cut it off once it reaches a
         * particular probability specified by `top_p`. You should either alter `temperature` or
         * `top_p`, but not both.
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        fun topP(): Optional<Double> = Optional.ofNullable(topP.getNullable("top_p"))

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         */
        @JsonProperty("max_tokens_to_sample")
        @ExcludeMissing
        fun _maxTokensToSample(): JsonField<Long> = maxTokensToSample

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<Model> = model

        /**
         * The prompt that you want Claude to complete.
         *
         * For proper response generation you will need to format your prompt using alternating
         * `\n\nHuman:` and `\n\nAssistant:` conversational turns. For example:
         * ```
         * "\n\nHuman: {userQuestion}\n\nAssistant:"
         * ```
         *
         * See [prompt validation](https://docs.anthropic.com/en/api/prompt-validation) and our
         * guide to [prompt design](https://docs.anthropic.com/en/docs/intro-to-prompting) for more
         * details.
         */
        @JsonProperty("prompt") @ExcludeMissing fun _prompt(): JsonField<String> = prompt

        /** An object describing metadata about the request. */
        @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

        /**
         * Sequences that will cause the model to stop generating.
         *
         * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences in
         * the future. By providing the stop_sequences parameter, you may include additional strings
         * that will cause the model to stop generating.
         */
        @JsonProperty("stop_sequences")
        @ExcludeMissing
        fun _stopSequences(): JsonField<List<String>> = stopSequences

        /**
         * Amount of randomness injected into the response.
         *
         * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
         * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
         *
         * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
         */
        @JsonProperty("temperature")
        @ExcludeMissing
        fun _temperature(): JsonField<Double> = temperature

        /**
         * Only sample from the top K options for each subsequent token.
         *
         * Used to remove "long tail" low probability responses.
         * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        @JsonProperty("top_k") @ExcludeMissing fun _topK(): JsonField<Long> = topK

        /**
         * Use nucleus sampling.
         *
         * In nucleus sampling, we compute the cumulative distribution over all the options for each
         * subsequent token in decreasing probability order and cut it off once it reaches a
         * particular probability specified by `top_p`. You should either alter `temperature` or
         * `top_p`, but not both.
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        @JsonProperty("top_p") @ExcludeMissing fun _topP(): JsonField<Double> = topP

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        private var validated: Boolean = false

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            maxTokensToSample()
            model()
            prompt()
            metadata().ifPresent { it.validate() }
            stopSequences()
            temperature()
            topK()
            topP()
            validated = true
        }

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var maxTokensToSample: JsonField<Long>? = null
            private var model: JsonField<Model>? = null
            private var prompt: JsonField<String>? = null
            private var metadata: JsonField<Metadata> = JsonMissing.of()
            private var stopSequences: JsonField<MutableList<String>>? = null
            private var temperature: JsonField<Double> = JsonMissing.of()
            private var topK: JsonField<Long> = JsonMissing.of()
            private var topP: JsonField<Double> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                maxTokensToSample = body.maxTokensToSample
                model = body.model
                prompt = body.prompt
                metadata = body.metadata
                stopSequences = body.stopSequences.map { it.toMutableList() }
                temperature = body.temperature
                topK = body.topK
                topP = body.topP
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * The maximum number of tokens to generate before stopping.
             *
             * Note that our models may stop _before_ reaching this maximum. This parameter only
             * specifies the absolute maximum number of tokens to generate.
             */
            fun maxTokensToSample(maxTokensToSample: Long) =
                maxTokensToSample(JsonField.of(maxTokensToSample))

            /**
             * The maximum number of tokens to generate before stopping.
             *
             * Note that our models may stop _before_ reaching this maximum. This parameter only
             * specifies the absolute maximum number of tokens to generate.
             */
            fun maxTokensToSample(maxTokensToSample: JsonField<Long>) = apply {
                this.maxTokensToSample = maxTokensToSample
            }

            /**
             * The model that will complete your prompt.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            fun model(model: Model) = model(JsonField.of(model))

            /**
             * The model that will complete your prompt.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            fun model(model: JsonField<Model>) = apply { this.model = model }

            /**
             * The model that will complete your prompt.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            fun model(value: String) = model(Model.of(value))

            /**
             * The prompt that you want Claude to complete.
             *
             * For proper response generation you will need to format your prompt using alternating
             * `\n\nHuman:` and `\n\nAssistant:` conversational turns. For example:
             * ```
             * "\n\nHuman: {userQuestion}\n\nAssistant:"
             * ```
             *
             * See [prompt validation](https://docs.anthropic.com/en/api/prompt-validation) and our
             * guide to [prompt design](https://docs.anthropic.com/en/docs/intro-to-prompting) for
             * more details.
             */
            fun prompt(prompt: String) = prompt(JsonField.of(prompt))

            /**
             * The prompt that you want Claude to complete.
             *
             * For proper response generation you will need to format your prompt using alternating
             * `\n\nHuman:` and `\n\nAssistant:` conversational turns. For example:
             * ```
             * "\n\nHuman: {userQuestion}\n\nAssistant:"
             * ```
             *
             * See [prompt validation](https://docs.anthropic.com/en/api/prompt-validation) and our
             * guide to [prompt design](https://docs.anthropic.com/en/docs/intro-to-prompting) for
             * more details.
             */
            fun prompt(prompt: JsonField<String>) = apply { this.prompt = prompt }

            /** An object describing metadata about the request. */
            fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

            /** An object describing metadata about the request. */
            fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

            /**
             * Sequences that will cause the model to stop generating.
             *
             * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences
             * in the future. By providing the stop_sequences parameter, you may include additional
             * strings that will cause the model to stop generating.
             */
            fun stopSequences(stopSequences: List<String>) =
                stopSequences(JsonField.of(stopSequences))

            /**
             * Sequences that will cause the model to stop generating.
             *
             * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences
             * in the future. By providing the stop_sequences parameter, you may include additional
             * strings that will cause the model to stop generating.
             */
            fun stopSequences(stopSequences: JsonField<List<String>>) = apply {
                this.stopSequences = stopSequences.map { it.toMutableList() }
            }

            /**
             * Sequences that will cause the model to stop generating.
             *
             * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences
             * in the future. By providing the stop_sequences parameter, you may include additional
             * strings that will cause the model to stop generating.
             */
            fun addStopSequence(stopSequence: String) = apply {
                stopSequences =
                    (stopSequences ?: JsonField.of(mutableListOf())).apply {
                        asKnown()
                            .orElseThrow {
                                IllegalStateException(
                                    "Field was set to non-list type: ${javaClass.simpleName}"
                                )
                            }
                            .add(stopSequence)
                    }
            }

            /**
             * Amount of randomness injected into the response.
             *
             * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
             * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
             *
             * Note that even with `temperature` of `0.0`, the results will not be fully
             * deterministic.
             */
            fun temperature(temperature: Double) = temperature(JsonField.of(temperature))

            /**
             * Amount of randomness injected into the response.
             *
             * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
             * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
             *
             * Note that even with `temperature` of `0.0`, the results will not be fully
             * deterministic.
             */
            fun temperature(temperature: JsonField<Double>) = apply {
                this.temperature = temperature
            }

            /**
             * Only sample from the top K options for each subsequent token.
             *
             * Used to remove "long tail" low probability responses.
             * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
             */
            fun topK(topK: Long) = topK(JsonField.of(topK))

            /**
             * Only sample from the top K options for each subsequent token.
             *
             * Used to remove "long tail" low probability responses.
             * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
             */
            fun topK(topK: JsonField<Long>) = apply { this.topK = topK }

            /**
             * Use nucleus sampling.
             *
             * In nucleus sampling, we compute the cumulative distribution over all the options for
             * each subsequent token in decreasing probability order and cut it off once it reaches
             * a particular probability specified by `top_p`. You should either alter `temperature`
             * or `top_p`, but not both.
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
             */
            fun topP(topP: Double) = topP(JsonField.of(topP))

            /**
             * Use nucleus sampling.
             *
             * In nucleus sampling, we compute the cumulative distribution over all the options for
             * each subsequent token in decreasing probability order and cut it off once it reaches
             * a particular probability specified by `top_p`. You should either alter `temperature`
             * or `top_p`, but not both.
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
             */
            fun topP(topP: JsonField<Double>) = apply { this.topP = topP }

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

            fun build(): Body =
                Body(
                    checkRequired("maxTokensToSample", maxTokensToSample),
                    checkRequired("model", model),
                    checkRequired("prompt", prompt),
                    metadata,
                    (stopSequences ?: JsonMissing.of()).map { it.toImmutable() },
                    temperature,
                    topK,
                    topP,
                    additionalProperties.toImmutable(),
                )
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Body && maxTokensToSample == other.maxTokensToSample && model == other.model && prompt == other.prompt && metadata == other.metadata && stopSequences == other.stopSequences && temperature == other.temperature && topK == other.topK && topP == other.topP && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(maxTokensToSample, model, prompt, metadata, stopSequences, temperature, topK, topP, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{maxTokensToSample=$maxTokensToSample, model=$model, prompt=$prompt, metadata=$metadata, stopSequences=$stopSequences, temperature=$temperature, topK=$topK, topP=$topP, additionalProperties=$additionalProperties}"
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CompletionCreateParams]. */
    @NoAutoDetect
    class Builder internal constructor() {

        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(completionCreateParams: CompletionCreateParams) = apply {
            body = completionCreateParams.body.toBuilder()
            additionalHeaders = completionCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = completionCreateParams.additionalQueryParams.toBuilder()
        }

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         */
        fun maxTokensToSample(maxTokensToSample: Long) = apply {
            body.maxTokensToSample(maxTokensToSample)
        }

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         */
        fun maxTokensToSample(maxTokensToSample: JsonField<Long>) = apply {
            body.maxTokensToSample(maxTokensToSample)
        }

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(model: Model) = apply { body.model(model) }

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(model: JsonField<Model>) = apply { body.model(model) }

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(value: String) = apply { body.model(value) }

        /**
         * The prompt that you want Claude to complete.
         *
         * For proper response generation you will need to format your prompt using alternating
         * `\n\nHuman:` and `\n\nAssistant:` conversational turns. For example:
         * ```
         * "\n\nHuman: {userQuestion}\n\nAssistant:"
         * ```
         *
         * See [prompt validation](https://docs.anthropic.com/en/api/prompt-validation) and our
         * guide to [prompt design](https://docs.anthropic.com/en/docs/intro-to-prompting) for more
         * details.
         */
        fun prompt(prompt: String) = apply { body.prompt(prompt) }

        /**
         * The prompt that you want Claude to complete.
         *
         * For proper response generation you will need to format your prompt using alternating
         * `\n\nHuman:` and `\n\nAssistant:` conversational turns. For example:
         * ```
         * "\n\nHuman: {userQuestion}\n\nAssistant:"
         * ```
         *
         * See [prompt validation](https://docs.anthropic.com/en/api/prompt-validation) and our
         * guide to [prompt design](https://docs.anthropic.com/en/docs/intro-to-prompting) for more
         * details.
         */
        fun prompt(prompt: JsonField<String>) = apply { body.prompt(prompt) }

        /** An object describing metadata about the request. */
        fun metadata(metadata: Metadata) = apply { body.metadata(metadata) }

        /** An object describing metadata about the request. */
        fun metadata(metadata: JsonField<Metadata>) = apply { body.metadata(metadata) }

        /**
         * Sequences that will cause the model to stop generating.
         *
         * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences in
         * the future. By providing the stop_sequences parameter, you may include additional strings
         * that will cause the model to stop generating.
         */
        fun stopSequences(stopSequences: List<String>) = apply { body.stopSequences(stopSequences) }

        /**
         * Sequences that will cause the model to stop generating.
         *
         * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences in
         * the future. By providing the stop_sequences parameter, you may include additional strings
         * that will cause the model to stop generating.
         */
        fun stopSequences(stopSequences: JsonField<List<String>>) = apply {
            body.stopSequences(stopSequences)
        }

        /**
         * Sequences that will cause the model to stop generating.
         *
         * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences in
         * the future. By providing the stop_sequences parameter, you may include additional strings
         * that will cause the model to stop generating.
         */
        fun addStopSequence(stopSequence: String) = apply { body.addStopSequence(stopSequence) }

        /**
         * Amount of randomness injected into the response.
         *
         * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
         * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
         *
         * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
         */
        fun temperature(temperature: Double) = apply { body.temperature(temperature) }

        /**
         * Amount of randomness injected into the response.
         *
         * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
         * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
         *
         * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
         */
        fun temperature(temperature: JsonField<Double>) = apply { body.temperature(temperature) }

        /**
         * Only sample from the top K options for each subsequent token.
         *
         * Used to remove "long tail" low probability responses.
         * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        fun topK(topK: Long) = apply { body.topK(topK) }

        /**
         * Only sample from the top K options for each subsequent token.
         *
         * Used to remove "long tail" low probability responses.
         * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        fun topK(topK: JsonField<Long>) = apply { body.topK(topK) }

        /**
         * Use nucleus sampling.
         *
         * In nucleus sampling, we compute the cumulative distribution over all the options for each
         * subsequent token in decreasing probability order and cut it off once it reaches a
         * particular probability specified by `top_p`. You should either alter `temperature` or
         * `top_p`, but not both.
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        fun topP(topP: Double) = apply { body.topP(topP) }

        /**
         * Use nucleus sampling.
         *
         * In nucleus sampling, we compute the cumulative distribution over all the options for each
         * subsequent token in decreasing probability order and cut it off once it reaches a
         * particular probability specified by `top_p`. You should either alter `temperature` or
         * `top_p`, but not both.
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        fun topP(topP: JsonField<Double>) = apply { body.topP(topP) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        fun build(): CompletionCreateParams =
            CompletionCreateParams(
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is CompletionCreateParams && body == other.body && additionalHeaders == other.additionalHeaders && additionalQueryParams == other.additionalQueryParams /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(body, additionalHeaders, additionalQueryParams) /* spotless:on */

    override fun toString() =
        "CompletionCreateParams{body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
