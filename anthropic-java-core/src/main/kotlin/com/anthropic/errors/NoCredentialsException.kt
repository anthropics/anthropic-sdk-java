package com.anthropic.errors

enum class CredentialSourceState {
    NOT_SET,
    NOT_FOUND,
    LOAD_FAILED,
    PARTIAL,
}

class CredentialSource
internal constructor(
    @get:JvmName("name") val name: String,
    @get:JvmName("state") val state: CredentialSourceState,
    @get:JvmName("detail") val detail: String? = null,
) {
    override fun toString(): String = "CredentialSource(name=$name, state=$state, detail=$detail)"
}

/**
 * Thrown when no credentials could be obtained from any of the sources the client tried
 * (environment variables, auth tokens, configured profiles, etc.).
 *
 * [sources] records each source that was attempted and why it did not yield usable credentials, so
 * the error message can point the user at the most likely fix.
 */
class NoCredentialsException
internal constructor(@get:JvmName("sources") val sources: List<CredentialSource>) :
    AnthropicException(buildMessage(sources)) {
    companion object {
        private fun buildMessage(sources: List<CredentialSource>): String {
            val details =
                sources.joinToString("\n  - ") { source ->
                    "${source.name}: ${source.state}${source.detail?.let { " ($it)" } ?: ""}"
                }
            return "No credentials found. Tried:\n  - $details\n\nRun 'anthropic auth login' or set ANTHROPIC_API_KEY."
        }
    }
}
