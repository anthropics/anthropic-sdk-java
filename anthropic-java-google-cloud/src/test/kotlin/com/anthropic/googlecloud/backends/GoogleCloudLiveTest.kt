package com.anthropic.googlecloud.backends

import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.messages.MessageCreateParams
import com.google.auth.oauth2.GoogleCredentials
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable

/**
 * Live integration tests for the Google Cloud gateway backend. Skipped unless ANTHROPIC_LIVE=true
 * is set.
 *
 * Run: ANTHROPIC_LIVE=true ./gradlew :anthropic-java-google-cloud:test --tests
 * '*GoogleCloudLiveTest*'
 */
@EnabledIfEnvironmentVariable(named = "ANTHROPIC_LIVE", matches = "true")
internal class GoogleCloudLiveTest {

    private val liveModel = System.getenv("ANTHROPIC_LIVE_MODEL") ?: "claude-sonnet-4-6"

    private val messageParams =
        MessageCreateParams.builder()
            .model(liveModel)
            .maxTokens(32)
            .addUserMessage("Say exactly: hello")
            .build()

    @Test
    fun adc() {
        val client = newClient(GoogleCloudBackend.fromEnv())

        val message = client.messages().create(messageParams)
        assertThat(message.content()).isNotEmpty
    }

    @Test
    fun explicitCredentials() {
        val credentials =
            GoogleCredentials.getApplicationDefault().createScoped(GoogleCloudBackend.SCOPE)
        val builder =
            GoogleCloudBackend.builder()
                .googleCredentials(credentials)
                .workspaceId(requireEnv("ANTHROPIC_GOOGLE_CLOUD_WORKSPACE_ID"))
        System.getenv("ANTHROPIC_GOOGLE_CLOUD_BASE_URL")?.let { builder.baseUrl(it) }
        System.getenv("ANTHROPIC_GOOGLE_CLOUD_PROJECT")?.let { builder.project(it) }
        System.getenv("ANTHROPIC_GOOGLE_CLOUD_LOCATION")?.let { builder.location(it) }

        val client = newClient(builder.build())

        val message = client.messages().create(messageParams)
        assertThat(message.content()).isNotEmpty
    }

    private fun newClient(backend: GoogleCloudBackend): AnthropicClient =
        AnthropicOkHttpClient.builder().backend(backend).maxRetries(0).build()

    private fun requireEnv(name: String): String = System.getenv(name) ?: error("$name required")
}
