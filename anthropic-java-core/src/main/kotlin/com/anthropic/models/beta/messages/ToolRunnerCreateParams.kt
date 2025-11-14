package com.anthropic.models.beta.messages

import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.MessageService
import java.util.Optional

/** @see MessageService.toolRunner */
class ToolRunnerCreateParams
private constructor(
    @get:JvmName("initialMessageParams") val initialMessageParams: MessageCreateParams,
    private val maxIterations: Long?,
) {

    fun maxIterations(): Optional<Long> = Optional.ofNullable(maxIterations)

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

        @JvmSynthetic
        internal fun from(toolRunnerCreateParams: ToolRunnerCreateParams) = apply {
            initialMessageParams = toolRunnerCreateParams.initialMessageParams
            maxIterations = toolRunnerCreateParams.maxIterations
        }

        fun initialMessageParams(initialMessageParams: MessageCreateParams) = apply {
            this.initialMessageParams = initialMessageParams
        }

        fun maxIterations(maxIterations: Long) = apply { this.maxIterations = maxIterations }

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
         * @throws IllegalStateException if any required field is unset.
         */
        fun build() =
            ToolRunnerCreateParams(
                checkRequired("initialMessageParams", initialMessageParams),
                maxIterations,
            )
    }
}
