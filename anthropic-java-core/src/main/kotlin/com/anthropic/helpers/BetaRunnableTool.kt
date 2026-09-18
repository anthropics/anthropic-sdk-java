package com.anthropic.helpers

import com.anthropic.core.JsonField
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.outputTypeFromJson
import com.anthropic.core.toJsonString
import com.anthropic.core.toolFromClass
import com.anthropic.models.beta.messages.BetaTool
import com.anthropic.models.beta.messages.BetaToolResultBlockParam
import java.util.function.Function
import java.util.function.Supplier

/**
 * A tool that [BetaToolRunner] can run when the model calls it.
 *
 * Holds both the tool definition (name, description, input schema) and the function that executes
 * the tool. Pass to [com.anthropic.models.beta.messages.MessageCreateParams.Builder.addTool] to
 * wire it into a request.
 */
class BetaRunnableTool
private constructor(
    private val definition: BetaTool,
    private val run: Function<in String, out BetaToolResultBlockParam.Content>,
) {

    @JvmSynthetic internal fun name(): String = definition.name()

    @JvmSynthetic internal fun definition(): BetaTool = definition

    @JvmSynthetic
    internal fun run(input: JsonField<*>): BetaToolResultBlockParam.Content =
        run.apply(toJsonString(input))

    companion object {

        /**
         * Creates a tool whose name, description and input schema are derived from
         * [parametersType]. When the model calls the tool, the tool input is parsed into an
         * instance of [parametersType] and passed to [run], which returns the tool result.
         *
         * Local validation of the derived JSON schema can be performed to check if the schema is
         * likely to pass remote validation by the AI model. By default, local validation is
         * enabled; disable it by setting [localValidation] to [JsonSchemaLocalValidation.NO].
         *
         * @throws IllegalArgumentException If [parametersType] is a non-static inner class, a local
         *   class or an anonymous class, or if local validation is enabled, but it fails because a
         *   valid JSON schema cannot be derived from the given class. The kind of class is checked
         *   even when [localValidation] is [JsonSchemaLocalValidation.NO].
         */
        @JvmStatic
        @JvmOverloads
        fun <T : Any> of(
            parametersType: Class<T>,
            run: Function<in T, out BetaToolResultBlockParam.Content>,
            localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
        ): BetaRunnableTool =
            BetaRunnableTool(toolFromClass(parametersType, localValidation)) { input ->
                run.apply(outputTypeFromJson(input, parametersType))
            }

        /**
         * Creates a tool from an existing tool [definition]. When the model calls the tool, [run]
         * receives the tool input as its raw JSON string and returns the tool result. This differs
         * from the overload that takes a [Class], where [run] receives the input parsed into an
         * instance of that class.
         */
        @JvmStatic
        fun of(
            definition: BetaTool,
            run: Function<in String, out BetaToolResultBlockParam.Content>,
        ): BetaRunnableTool = BetaRunnableTool(definition, run)

        @JvmSynthetic
        internal fun <T : Any> ofSupplier(
            parametersType: Class<T>,
            localValidation: JsonSchemaLocalValidation,
        ): BetaRunnableTool = of(parametersType, { runSupplier(it) }, localValidation)

        private fun runSupplier(parameters: Any): BetaToolResultBlockParam.Content {
            check(parameters is Supplier<*>) { "Cannot run non-`Supplier` tool" }
            return when (val output = parameters.get()) {
                is String -> BetaToolResultBlockParam.Content.ofString(output)
                is BetaToolResultBlockParam.Content -> output
                else ->
                    error("Expected tool to return `String` or `BetaToolResultBlockParam.Content`")
            }
        }
    }
}
