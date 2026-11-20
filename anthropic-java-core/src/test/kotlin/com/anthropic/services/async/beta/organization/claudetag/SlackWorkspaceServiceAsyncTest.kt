package com.anthropic.services.async.beta.organization.claudetag

import com.anthropic.CompletionExecutorAssertingInterceptor
import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagGuestPolicyAllow
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagReplyModeAutomatic
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettingsUpdate
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceRetrieveParams
import com.anthropic.models.beta.organization.claudetag.slackworkspaces.SlackWorkspaceUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SlackWorkspaceServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .addInterceptor(CompletionExecutorAssertingInterceptor())
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val slackWorkspaceServiceAsync =
            client.beta().organization().claudeTag().slackWorkspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagSlackWorkspaceFuture =
            slackWorkspaceServiceAsync.retrieve(
                SlackWorkspaceRetrieveParams.builder()
                    .id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
                    .includeInherited(true)
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaClaudeTagSlackWorkspace = betaClaudeTagSlackWorkspaceFuture.get()
        betaClaudeTagSlackWorkspace.validate()
    }

    @Test
    fun update() {
        val slackWorkspaceServiceAsync =
            client.beta().organization().claudeTag().slackWorkspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagSlackWorkspaceFuture =
            slackWorkspaceServiceAsync.update(
                SlackWorkspaceUpdateParams.builder()
                    .id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .settings(
                        BetaClaudeTagSettingsUpdate.builder()
                            .enabled(true)
                            .environmentId("x")
                            .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                            .instructions("instructions")
                            .model("x")
                            .replyMode(BetaClaudeTagReplyModeAutomatic.builder().build())
                            .build()
                    )
                    .build()
            )

        val betaClaudeTagSlackWorkspace = betaClaudeTagSlackWorkspaceFuture.get()
        betaClaudeTagSlackWorkspace.validate()
    }

    @Test
    fun list() {
        val slackWorkspaceServiceAsync =
            client.beta().organization().claudeTag().slackWorkspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = slackWorkspaceServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
