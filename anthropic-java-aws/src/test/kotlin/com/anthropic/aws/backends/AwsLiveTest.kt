package com.anthropic.aws.backends

import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.messages.MessageCreateParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import software.amazon.awssdk.regions.Region

/**
 * Live integration tests for the AWS gateway backend. Skipped unless ANTHROPIC_LIVE=true env var is
 * set.
 *
 * Run: ANTHROPIC_LIVE=true ./gradlew :anthropic-java-aws:test --tests '*AwsLiveTest*'
 */
@EnabledIfEnvironmentVariable(named = "ANTHROPIC_LIVE", matches = "true")
internal class AwsLiveTest {

    private val liveModel = System.getenv("ANTHROPIC_LIVE_MODEL") ?: "claude-sonnet-4-6"

    private val messageParams =
        MessageCreateParams.builder()
            .model(liveModel)
            .maxTokens(32)
            .addUserMessage("Say exactly: hello")
            .build()

    @Test
    fun sigV4ExplicitCreds() {
        val accessKey = System.getenv("AWS_ACCESS_KEY_ID") ?: error("AWS_ACCESS_KEY_ID required")
        val secretKey =
            System.getenv("AWS_SECRET_ACCESS_KEY") ?: error("AWS_SECRET_ACCESS_KEY required")
        val region = System.getenv("AWS_REGION") ?: error("AWS_REGION required")
        val workspaceId =
            System.getenv("ANTHROPIC_AWS_WORKSPACE_ID")
                ?: error("ANTHROPIC_AWS_WORKSPACE_ID required")

        val builder =
            AwsBackend.builder()
                .awsAccessKey(accessKey)
                .awsSecretAccessKey(secretKey)
                .region(Region.of(region))
                .workspaceId(workspaceId)

        System.getenv("AWS_SESSION_TOKEN")?.let { builder.awsSessionToken(it) }
        System.getenv("ANTHROPIC_AWS_BASE_URL")?.let { builder.baseUrl(it) }

        val client = AnthropicOkHttpClient.builder().backend(builder.build()).build()

        val message = client.messages().create(messageParams)
        assertThat(message.content()).isNotEmpty
        println("Response: ${message.content().first()}")
    }

    @Test
    fun sigV4Profile() {
        val profile =
            System.getenv("AWS_PROFILE") ?: error("AWS_PROFILE required (e.g. anthropic-aws)")
        val workspaceId =
            System.getenv("ANTHROPIC_AWS_WORKSPACE_ID")
                ?: error("ANTHROPIC_AWS_WORKSPACE_ID required")

        val region = System.getenv("AWS_REGION") ?: error("AWS_REGION required")

        val builder =
            AwsBackend.builder()
                .awsProfile(profile)
                .workspaceId(workspaceId)
                .region(Region.of(region))

        System.getenv("ANTHROPIC_AWS_BASE_URL")?.let { builder.baseUrl(it) }

        val client = AnthropicOkHttpClient.builder().backend(builder.build()).build()

        val message = client.messages().create(messageParams)
        assertThat(message.content()).isNotEmpty
    }
}
