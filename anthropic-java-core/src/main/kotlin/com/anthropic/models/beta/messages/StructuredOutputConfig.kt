package com.anthropic.models.beta.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.JsonValue
import com.anthropic.core.betaOutputFormatFromClass
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A wrapper for [BetaOutputConfig] that provides a type-safe [Builder] that can record the
 * [outputType] used to derive a JSON schema for the output format when using the _Structured
 * Outputs_ feature. Unlike passing only the output type to
 * [MessageCreateParams.Builder.outputConfig], this allows the other output configuration options
 * (such as the effort level) to be set alongside the output type. See the SDK documentation for
 * more details on _Structured Outputs_.
 *
 * @param T The type of the class that will be used to derive the JSON schema in the request and to
 *   which the JSON response will be deserialized.
 */
class StructuredOutputConfig<T : Any>
private constructor(
    @get:JvmName("outputType") val outputType: Class<T>,
    private val delegate: BetaOutputConfig,
) {

    /** The raw, underlying output configuration wrapped by this structured instance. */
    @get:JvmName("rawOutputConfig")
    val rawOutputConfig: BetaOutputConfig
        get() = delegate

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see BetaOutputConfig.effort
     */
    fun effort(): Optional<BetaOutputConfig.Effort> = delegate.effort()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see BetaOutputConfig.format
     */
    fun format(): Optional<BetaJsonOutputFormat> = delegate.format()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see BetaOutputConfig.taskBudget
     */
    fun taskBudget(): Optional<BetaTokenTaskBudget> = delegate.taskBudget()

    /**
     * Returns the raw JSON value of [effort].
     *
     * Unlike [effort], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _effort(): JsonField<BetaOutputConfig.Effort> = delegate._effort()

    /**
     * Returns the raw JSON value of [format].
     *
     * Unlike [format], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _format(): JsonField<BetaJsonOutputFormat> = delegate._format()

    /**
     * Returns the raw JSON value of [taskBudget].
     *
     * Unlike [taskBudget], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _taskBudget(): JsonField<BetaTokenTaskBudget> = delegate._taskBudget()

    /** @see BetaOutputConfig._additionalProperties */
    fun _additionalProperties(): Map<String, JsonValue> = delegate._additionalProperties()

    /** @see BetaOutputConfig.validate */
    fun validate(): StructuredOutputConfig<T> = apply { delegate.validate() }

    /** @see BetaOutputConfig.isValid */
    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    fun toBuilder() = Builder<T>().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [StructuredOutputConfig].
         *
         * The following fields are required:
         * ```java
         * .format()
         * ```
         */
        @JvmStatic fun <T : Any> builder() = Builder<T>()
    }

    /** A builder for [StructuredOutputConfig]. */
    class Builder<T : Any> internal constructor() {

        private var outputType: Class<T>? = null
        private var delegate: BetaOutputConfig.Builder = BetaOutputConfig.builder()

        /** Injects a given `BetaOutputConfig.Builder`. For use only when testing. */
        @JvmSynthetic
        internal fun inject(delegate: BetaOutputConfig.Builder) = apply { this.delegate = delegate }

        @JvmSynthetic
        internal fun from(structuredOutputConfig: StructuredOutputConfig<T>) = apply {
            outputType = structuredOutputConfig.outputType
            delegate = structuredOutputConfig.delegate.toBuilder()
        }

        /** @see BetaOutputConfig.Builder.effort */
        fun effort(effort: BetaOutputConfig.Effort?) = apply { delegate.effort(effort) }

        /** Alias for calling [Builder.effort] with `effort.orElse(null)`. */
        fun effort(effort: Optional<BetaOutputConfig.Effort>) = effort(effort.getOrNull())

        /**
         * Sets [Builder.effort] to an arbitrary JSON value.
         *
         * You should usually call [Builder.effort] with a well-typed [BetaOutputConfig.Effort]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun effort(effort: JsonField<BetaOutputConfig.Effort>) = apply { delegate.effort(effort) }

        /**
         * Sets the output format to a JSON schema derived from the structure of the given class.
         *
         * @param outputType The class from which the JSON schema will be derived.
         * @param localValidation [JsonSchemaLocalValidation.YES] (the default) to validate the JSON
         *   schema locally when it is generated by this method to confirm that it adheres to the
         *   requirements and restrictions on JSON schemas imposed by the Anthropic specification;
         *   or [JsonSchemaLocalValidation.NO] to skip local validation and rely only on remote
         *   validation. See the SDK documentation for more details.
         * @throws IllegalArgumentException If local validation is enabled, but it fails because a
         *   valid JSON schema cannot be derived from the given class; or if the given class is a
         *   non-static inner class, a local class or an anonymous class. The kind of class is
         *   checked even when [localValidation] is [JsonSchemaLocalValidation.NO].
         * @see BetaOutputConfig.Builder.format
         */
        @JvmOverloads
        fun format(
            outputType: Class<T>,
            localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
        ) = apply {
            this.outputType = outputType
            delegate.format(betaOutputFormatFromClass(outputType, localValidation))
        }

        /** @see BetaOutputConfig.Builder.taskBudget */
        fun taskBudget(taskBudget: BetaTokenTaskBudget?) = apply { delegate.taskBudget(taskBudget) }

        /** Alias for calling [Builder.taskBudget] with `taskBudget.orElse(null)`. */
        fun taskBudget(taskBudget: Optional<BetaTokenTaskBudget>) =
            taskBudget(taskBudget.getOrNull())

        /**
         * Sets [Builder.taskBudget] to an arbitrary JSON value.
         *
         * You should usually call [Builder.taskBudget] with a well-typed [BetaTokenTaskBudget]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun taskBudget(taskBudget: JsonField<BetaTokenTaskBudget>) = apply {
            delegate.taskBudget(taskBudget)
        }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            delegate.additionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            delegate.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            delegate.putAllAdditionalProperties(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { delegate.removeAdditionalProperty(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            delegate.removeAllAdditionalProperties(keys)
        }

        /**
         * Returns an immutable instance of [StructuredOutputConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .format()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): StructuredOutputConfig<T> =
            StructuredOutputConfig(checkRequired("format", outputType), delegate.build())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is StructuredOutputConfig<*> &&
            outputType == other.outputType &&
            delegate == other.delegate
    }

    override fun hashCode(): Int = Objects.hash(outputType, delegate)

    override fun toString() =
        "StructuredOutputConfig{outputType=$outputType, rawOutputConfig=$delegate}"
}
