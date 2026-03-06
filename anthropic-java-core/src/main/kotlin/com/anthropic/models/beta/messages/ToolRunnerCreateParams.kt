package com.anthropic.models.beta.messages

import com.anthropic.core.checkRequired
import com.anthropic.helpers.BetaMemoryToolHandler
import com.anthropic.services.blocking.beta.MessageService
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see MessageService.toolRunner */
class ToolRunnerCreateParams
private constructor(
    @get:JvmName("initialMessageParams") val initialMessageParams: MessageCreateParams,
    private val maxIterations: Long?,
    private val betaMemoryToolHandler: BetaMemoryToolHandler?,
) {

    fun maxIterations(): Optional<Long> = Optional.ofNullable(maxIterations)

    fun betaMemoryToolHandler(): Optional<BetaMemoryToolHandler> =
        Optional.ofNullable(betaMemoryToolHandler)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ToolRunnerCreateParams].
         *
         * The following fields are required:
         * ```java
         * .initialMessageParams()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ToolRunnerCreateParams]. */
    class Builder internal constructor() {

        private var initialMessageParams: MessageCreateParams? = null
        private var maxIterations: Long? = null
        private var betaMemoryToolHandler: BetaMemoryToolHandler? = null

        @JvmSynthetic
        internal fun from(toolRunnerCreateParams: ToolRunnerCreateParams) = apply {
            initialMessageParams = toolRunnerCreateParams.initialMessageParams
            maxIterations = toolRunnerCreateParams.maxIterations
            betaMemoryToolHandler = toolRunnerCreateParams.betaMemoryToolHandler
        }

        fun initialMessageParams(initialMessageParams: MessageCreateParams) = apply {
            this.initialMessageParams = initialMessageParams
        }

        fun maxIterations(maxIterations: Long) = apply { this.maxIterations = maxIterations }

        /**
         * Sets the implementation of the beta memory tool handler that will be invoked if memory
         * tool commands are received in tool-use responses. This is required if
         * [initialMessageParams] defines a memory tool.
         */
        fun betaMemoryToolHandler(betaMemoryToolHandler: BetaMemoryToolHandler) = apply {
            this.betaMemoryToolHandler = betaMemoryToolHandler
        }

        /**
         * Returns an immutable instance of [ToolRunnerCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .initialMessageParams()
         * ```
         *
         * @throws IllegalStateException If any required field is unset. If [initialMessageParams]
         *   defines a memory tool, then [betaMemoryToolHandler] must be set. If there is no memory
         *   tool defined, then [betaMemoryToolHandler] must not be set.
         */
        fun build(): ToolRunnerCreateParams {
            // Check this first, otherwise memory tool error messages may cause confusion.
            val initialMessageParams = checkRequired("initialMessageParams", initialMessageParams)
            val hasMemoryTool =
                initialMessageParams.tools().getOrNull()?.any { it.isMemoryTool20250818() } ?: false

            check(!hasMemoryTool || betaMemoryToolHandler != null) {
                "Memory tool defined, but `betaMemoryToolHandler` was not set."
            }

            check(hasMemoryTool || betaMemoryToolHandler == null) {
                "Memory tool not defined, but `betaMemoryToolHandler` was set."
            }

            return ToolRunnerCreateParams(
                initialMessageParams,
                maxIterations,
                betaMemoryToolHandler,
            )
        }
    }
}
