package com.anthropic.googlecloud.backends

import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.agents.AgentCreateParams
import com.anthropic.models.beta.agents.AgentListParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAgent
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
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

    @Test
    fun organizationRoute() {
        val workspaceId = requireEnv("ANTHROPIC_GOOGLE_CLOUD_WORKSPACE_ID")
        val client = newClient(GoogleCloudBackend.fromEnv())

        val workspace = client.organization().workspaces().retrieve(workspaceId)
        assertThat(workspace.id()).isEqualTo(workspaceId)
    }

    @Test
    fun managedAgentsRoutes() {
        val client = newClient(GoogleCloudBackend.fromEnv())
        val agents = client.beta().agents()

        val agent =
            agents.create(
                AgentCreateParams.builder()
                    .model(BetaManagedAgentsModel.of(liveModel))
                    .name("sdk-live-test")
                    .build()
            )
        val archived: BetaManagedAgentsAgent
        try {
            assertThat(agents.retrieve(agent.id()).id()).isEqualTo(agent.id())
            assertThat(agents.list(AgentListParams.builder().limit(1).build()).data()).isNotEmpty
        } finally {
            archived = agents.archive(agent.id())
        }
        assertThat(archived.archivedAt()).isPresent
    }

    private fun newClient(backend: GoogleCloudBackend): AnthropicClient =
        AnthropicOkHttpClient.builder().backend(backend).maxRetries(0).build()

    private fun requireEnv(name: String): String = System.getenv(name) ?: error("$name required")
}
