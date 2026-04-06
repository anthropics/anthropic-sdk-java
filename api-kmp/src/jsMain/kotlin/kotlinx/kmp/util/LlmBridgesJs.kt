package kotlinx.kmp.util

actual fun createPlatformClient(
    provider: LlmProvider,
    apiKey: String,
    httpClient: io.ktor.client.HttpClient?,
): LlmProviderClient = error("No platform-specific LLM client on JS for ${provider::class.simpleName}")
