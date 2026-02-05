package com.anthropic.helpers

import com.anthropic.models.beta.messages.BetaThinkingConfigParam
import com.anthropic.models.messages.ThinkingConfigParam

/** Models that should emit a warning when used with thinking.type=enabled */
private val MODELS_TO_WARN_WITH_THINKING_ENABLED = setOf("claude-opus-4-6")

/**
 * Checks if the model and thinking config should emit a warning, and prints it to stderr if needed.
 * Warns when using thinking.type=enabled with specific models (e.g., claude-opus-4-6).
 */
internal fun warnIfThinkingEnabled(model: String, thinking: ThinkingConfigParam?) {
    if (
        thinking != null &&
            MODELS_TO_WARN_WITH_THINKING_ENABLED.contains(model) &&
            thinking.isEnabled()
    ) {
        System.err.println(
            "WARNING: Using Claude with $model and 'thinking.type=enabled' is deprecated. Use thinking.type=adaptive instead which results in better model performance in our testing: https://platform.claude.com/docs/en/build-with-claude/adaptive-thinking"
        )
    }
}

/**
 * Checks if the model and thinking config should emit a warning, and prints it to stderr if needed
 * (beta version). Warns when using thinking.type=enabled with specific models (e.g.,
 * claude-opus-4-6).
 */
internal fun warnIfThinkingEnabled(model: String, thinking: BetaThinkingConfigParam?) {
    if (
        thinking != null &&
            MODELS_TO_WARN_WITH_THINKING_ENABLED.contains(model) &&
            thinking.isEnabled()
    ) {
        System.err.println(
            "WARNING: Using Claude with $model and 'thinking.type=enabled' is deprecated. Use thinking.type=adaptive instead which results in better model performance in our testing: https://platform.claude.com/docs/en/build-with-claude/adaptive-thinking"
        )
    }
}
