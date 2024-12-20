// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.Objects
import java.util.Optional

class CompletionCreateParams
constructor(
    private val maxTokensToSample: Long,
    private val model: Model,
    private val prompt: String,
    private val metadata: Metadata?,
    private val stopSequences: List<String>?,
    private val temperature: Double?,
    private val topK: Long?,
    private val topP: Double?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
    private val additionalBodyProperties: Map<String, JsonValue>,
) {

    fun maxTokensToSample(): Long = maxTokensToSample

    fun model(): Model = model

    fun prompt(): String = prompt

    fun metadata(): Optional<Metadata> = Optional.ofNullable(metadata)

    fun stopSequences(): Optional<List<String>> = Optional.ofNullable(stopSequences)

    fun temperature(): Optional<Double> = Optional.ofNullable(temperature)

    fun topK(): Optional<Long> = Optional.ofNullable(topK)

    fun topP(): Optional<Double> = Optional.ofNullable(topP)

    fun _additionalHeaders(): Headers = additionalHeaders

    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun _additionalBodyProperties(): Map<String, JsonValue> = additionalBodyProperties

    @JvmSynthetic
    internal fun getBody(): CompletionCreateBody {
        return CompletionCreateBody(
            maxTokensToSample,
            model,
            prompt,
            metadata,
            stopSequences,
            temperature,
            topK,
            topP,
            additionalBodyProperties,
        )
    }

    @JvmSynthetic internal fun getHeaders(): Headers = additionalHeaders

    @JvmSynthetic internal fun getQueryParams(): QueryParams = additionalQueryParams

    @JsonDeserialize(builder = CompletionCreateBody.Builder::class)
    @NoAutoDetect
    class CompletionCreateBody
    internal constructor(
        private val maxTokensToSample: Long,
        private val model: Model,
        private val prompt: String,
        private val metadata: Metadata?,
        private val stopSequences: List<String>?,
        private val temperature: Double?,
        private val topK: Long?,
        private val topP: Double?,
        private val additionalProperties: Map<String, JsonValue>,
    ) {

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         */
        @JsonProperty("max_tokens_to_sample") fun maxTokensToSample(): Long = maxTokensToSample

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        @JsonProperty("model") fun model(): Model = model

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
        @JsonProperty("prompt") fun prompt(): String = prompt

        /** An object describing metadata about the request. */
        @JsonProperty("metadata") fun metadata(): Optional<Metadata> = Optional.ofNullable(metadata)

        /**
         * Sequences that will cause the model to stop generating.
         *
         * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences in
         * the future. By providing the stop_sequences parameter, you may include additional strings
         * that will cause the model to stop generating.
         */
        @JsonProperty("stop_sequences")
        fun stopSequences(): Optional<List<String>> = Optional.ofNullable(stopSequences)

        /**
         * Amount of randomness injected into the response.
         *
         * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
         * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
         *
         * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
         */
        @JsonProperty("temperature")
        fun temperature(): Optional<Double> = Optional.ofNullable(temperature)

        /**
         * Only sample from the top K options for each subsequent token.
         *
         * Used to remove "long tail" low probability responses.
         * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        @JsonProperty("top_k") fun topK(): Optional<Long> = Optional.ofNullable(topK)

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
        @JsonProperty("top_p") fun topP(): Optional<Double> = Optional.ofNullable(topP)

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            @JvmStatic fun builder() = Builder()
        }

        class Builder {

            private var maxTokensToSample: Long? = null
            private var model: Model? = null
            private var prompt: String? = null
            private var metadata: Metadata? = null
            private var stopSequences: List<String>? = null
            private var temperature: Double? = null
            private var topK: Long? = null
            private var topP: Double? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(completionCreateBody: CompletionCreateBody) = apply {
                maxTokensToSample = completionCreateBody.maxTokensToSample
                model = completionCreateBody.model
                prompt = completionCreateBody.prompt
                metadata = completionCreateBody.metadata
                stopSequences = completionCreateBody.stopSequences?.toMutableList()
                temperature = completionCreateBody.temperature
                topK = completionCreateBody.topK
                topP = completionCreateBody.topP
                additionalProperties = completionCreateBody.additionalProperties.toMutableMap()
            }

            /**
             * The maximum number of tokens to generate before stopping.
             *
             * Note that our models may stop _before_ reaching this maximum. This parameter only
             * specifies the absolute maximum number of tokens to generate.
             */
            @JsonProperty("max_tokens_to_sample")
            fun maxTokensToSample(maxTokensToSample: Long) = apply {
                this.maxTokensToSample = maxTokensToSample
            }

            /**
             * The model that will complete your prompt.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            @JsonProperty("model") fun model(model: Model) = apply { this.model = model }

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
            @JsonProperty("prompt") fun prompt(prompt: String) = apply { this.prompt = prompt }

            /** An object describing metadata about the request. */
            @JsonProperty("metadata")
            fun metadata(metadata: Metadata) = apply { this.metadata = metadata }

            /**
             * Sequences that will cause the model to stop generating.
             *
             * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences
             * in the future. By providing the stop_sequences parameter, you may include additional
             * strings that will cause the model to stop generating.
             */
            @JsonProperty("stop_sequences")
            fun stopSequences(stopSequences: List<String>) = apply {
                this.stopSequences = stopSequences
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
            @JsonProperty("temperature")
            fun temperature(temperature: Double) = apply { this.temperature = temperature }

            /**
             * Only sample from the top K options for each subsequent token.
             *
             * Used to remove "long tail" low probability responses.
             * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
             *
             * Recommended for advanced use cases only. You usually only need to use `temperature`.
             */
            @JsonProperty("top_k") fun topK(topK: Long) = apply { this.topK = topK }

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
            @JsonProperty("top_p") fun topP(topP: Double) = apply { this.topP = topP }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            @JsonAnySetter
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

            fun build(): CompletionCreateBody =
                CompletionCreateBody(
                    checkNotNull(maxTokensToSample) {
                        "`maxTokensToSample` is required but was not set"
                    },
                    checkNotNull(model) { "`model` is required but was not set" },
                    checkNotNull(prompt) { "`prompt` is required but was not set" },
                    metadata,
                    stopSequences?.toImmutable(),
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

            return /* spotless:off */ other is CompletionCreateBody && maxTokensToSample == other.maxTokensToSample && model == other.model && prompt == other.prompt && metadata == other.metadata && stopSequences == other.stopSequences && temperature == other.temperature && topK == other.topK && topP == other.topP && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(maxTokensToSample, model, prompt, metadata, stopSequences, temperature, topK, topP, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "CompletionCreateBody{maxTokensToSample=$maxTokensToSample, model=$model, prompt=$prompt, metadata=$metadata, stopSequences=$stopSequences, temperature=$temperature, topK=$topK, topP=$topP, additionalProperties=$additionalProperties}"
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    @NoAutoDetect
    class Builder {

        private var maxTokensToSample: Long? = null
        private var model: Model? = null
        private var prompt: String? = null
        private var metadata: Metadata? = null
        private var stopSequences: MutableList<String> = mutableListOf()
        private var temperature: Double? = null
        private var topK: Long? = null
        private var topP: Double? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()
        private var additionalBodyProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(completionCreateParams: CompletionCreateParams) = apply {
            maxTokensToSample = completionCreateParams.maxTokensToSample
            model = completionCreateParams.model
            prompt = completionCreateParams.prompt
            metadata = completionCreateParams.metadata
            stopSequences = completionCreateParams.stopSequences?.toMutableList() ?: mutableListOf()
            temperature = completionCreateParams.temperature
            topK = completionCreateParams.topK
            topP = completionCreateParams.topP
            additionalHeaders = completionCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = completionCreateParams.additionalQueryParams.toBuilder()
            additionalBodyProperties =
                completionCreateParams.additionalBodyProperties.toMutableMap()
        }

        /**
         * The maximum number of tokens to generate before stopping.
         *
         * Note that our models may stop _before_ reaching this maximum. This parameter only
         * specifies the absolute maximum number of tokens to generate.
         */
        fun maxTokensToSample(maxTokensToSample: Long) = apply {
            this.maxTokensToSample = maxTokensToSample
        }

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(model: Model) = apply { this.model = model }

        /**
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(value: String) = apply { this.model = Model.of(value) }

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
        fun prompt(prompt: String) = apply { this.prompt = prompt }

        /** An object describing metadata about the request. */
        fun metadata(metadata: Metadata) = apply { this.metadata = metadata }

        /**
         * Sequences that will cause the model to stop generating.
         *
         * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences in
         * the future. By providing the stop_sequences parameter, you may include additional strings
         * that will cause the model to stop generating.
         */
        fun stopSequences(stopSequences: List<String>) = apply {
            this.stopSequences.clear()
            this.stopSequences.addAll(stopSequences)
        }

        /**
         * Sequences that will cause the model to stop generating.
         *
         * Our models stop on `"\n\nHuman:"`, and may include additional built-in stop sequences in
         * the future. By providing the stop_sequences parameter, you may include additional strings
         * that will cause the model to stop generating.
         */
        fun addStopSequence(stopSequence: String) = apply { this.stopSequences.add(stopSequence) }

        /**
         * Amount of randomness injected into the response.
         *
         * Defaults to `1.0`. Ranges from `0.0` to `1.0`. Use `temperature` closer to `0.0` for
         * analytical / multiple choice, and closer to `1.0` for creative and generative tasks.
         *
         * Note that even with `temperature` of `0.0`, the results will not be fully deterministic.
         */
        fun temperature(temperature: Double) = apply { this.temperature = temperature }

        /**
         * Only sample from the top K options for each subsequent token.
         *
         * Used to remove "long tail" low probability responses.
         * [Learn more technical details here](https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277).
         *
         * Recommended for advanced use cases only. You usually only need to use `temperature`.
         */
        fun topK(topK: Long) = apply { this.topK = topK }

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
        fun topP(topP: Double) = apply { this.topP = topP }

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

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            this.additionalBodyProperties.clear()
            putAllAdditionalBodyProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            additionalBodyProperties.put(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                this.additionalBodyProperties.putAll(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply {
            additionalBodyProperties.remove(key)
        }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalBodyProperty)
        }

        fun build(): CompletionCreateParams =
            CompletionCreateParams(
                checkNotNull(maxTokensToSample) {
                    "`maxTokensToSample` is required but was not set"
                },
                checkNotNull(model) { "`model` is required but was not set" },
                checkNotNull(prompt) { "`prompt` is required but was not set" },
                metadata,
                stopSequences.toImmutable().ifEmpty { null },
                temperature,
                topK,
                topP,
                additionalHeaders.build(),
                additionalQueryParams.build(),
                additionalBodyProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is CompletionCreateParams && maxTokensToSample == other.maxTokensToSample && model == other.model && prompt == other.prompt && metadata == other.metadata && stopSequences == other.stopSequences && temperature == other.temperature && topK == other.topK && topP == other.topP && additionalHeaders == other.additionalHeaders && additionalQueryParams == other.additionalQueryParams && additionalBodyProperties == other.additionalBodyProperties /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(maxTokensToSample, model, prompt, metadata, stopSequences, temperature, topK, topP, additionalHeaders, additionalQueryParams, additionalBodyProperties) /* spotless:on */

    override fun toString() =
        "CompletionCreateParams{maxTokensToSample=$maxTokensToSample, model=$model, prompt=$prompt, metadata=$metadata, stopSequences=$stopSequences, temperature=$temperature, topK=$topK, topP=$topP, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams, additionalBodyProperties=$additionalBodyProperties}"
}
