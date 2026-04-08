package com.anthropic.bedrock.backends

import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.messages.MessageCreateParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import software.amazon.awssdk.regions.Region

/**
 * Live integration tests for Bedrock Mantle. Skipped unless ANTHROPIC_LIVE=true env var is set.
 *
 * Required env vars vary by auth mode:
 * - API key: AWS_BEARER_TOKEN_BEDROCK (or ANTHROPIC_AWS_API_KEY), AWS_REGION
 * - SigV4: AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY, AWS_REGION
 *
 * Run: ANTHROPIC_LIVE=true ./gradlew :anthropic-java-bedrock:test --tests '*BedrockMantleLiveTest*'
 */
@EnabledIfEnvironmentVariable(named = "ANTHROPIC_LIVE", matches = "true")
internal class BedrockMantleLiveTest {

    private val liveModel = System.getenv("ANTHROPIC_LIVE_MODEL") ?: "claude-sonnet-4-6"

    private val messageParams =
        MessageCreateParams.builder()
            .model(liveModel)
            .maxTokens(32)
            .addUserMessage("Say exactly: hello")
            .build()

    @Test
    fun apiKeyMode() {
        val apiKey =
            System.getenv("AWS_BEARER_TOKEN_BEDROCK")
                ?: System.getenv("ANTHROPIC_AWS_API_KEY")
                ?: error("AWS_BEARER_TOKEN_BEDROCK or ANTHROPIC_AWS_API_KEY required")
        val region = System.getenv("AWS_REGION") ?: error("AWS_REGION required")

        val client =
            AnthropicOkHttpClient.builder()
                .backend(
                    BedrockMantleBackend.builder().apiKey(apiKey).region(Region.of(region)).build()
                )
                .build()

        val message = client.messages().create(messageParams)
        assertThat(message.content()).isNotEmpty
    }

    @Test
    fun sigV4ExplicitCreds() {
        val accessKey = System.getenv("AWS_ACCESS_KEY_ID") ?: error("AWS_ACCESS_KEY_ID required")
        val secretKey =
            System.getenv("AWS_SECRET_ACCESS_KEY") ?: error("AWS_SECRET_ACCESS_KEY required")
        val region = System.getenv("AWS_REGION") ?: error("AWS_REGION required")

        val builder =
            BedrockMantleBackend.builder()
                .awsAccessKey(accessKey)
                .awsSecretAccessKey(secretKey)
                .region(Region.of(region))

        System.getenv("AWS_SESSION_TOKEN")?.let { builder.awsSessionToken(it) }
        System.getenv("ANTHROPIC_BEDROCK_MANTLE_BASE_URL")?.let { builder.baseUrl(it) }

        val client = AnthropicOkHttpClient.builder().backend(builder.build()).build()

        val message = client.messages().create(messageParams)
        assertThat(message.content()).isNotEmpty
    }

    @Test
    fun sigV4DefaultChain() {
        val client = AnthropicOkHttpClient.builder().backend(BedrockMantleBackend.fromEnv()).build()

        val message = client.messages().create(messageParams)
        assertThat(message.content()).isNotEmpty
    }

    @Test
    fun sigV4Profile() {
        val profile =
            System.getenv("AWS_PROFILE") ?: error("AWS_PROFILE required (e.g. anthropic-mantle)")
        val region = System.getenv("AWS_REGION") ?: error("AWS_REGION required")

        val builder = BedrockMantleBackend.builder().awsProfile(profile).region(Region.of(region))

        System.getenv("ANTHROPIC_BEDROCK_MANTLE_BASE_URL")?.let { builder.baseUrl(it) }

        val client = AnthropicOkHttpClient.builder().backend(builder.build()).build()

        val message = client.messages().create(messageParams)
        assertThat(message.content()).isNotEmpty
    }
}
