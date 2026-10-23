package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.tunnels.TunnelArchiveParams
import com.anthropic.models.beta.tunnels.TunnelCreateParams
import com.anthropic.models.beta.tunnels.TunnelRetrieveParams
import com.anthropic.models.beta.tunnels.TunnelRevealTokenParams
import com.anthropic.models.beta.tunnels.TunnelRotateTokenParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class TunnelServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val tunnelService = client.beta().tunnels().withOptions { it.putQueryParam("beta", "true") }

        val betaTunnel =
            tunnelService.create(
                TunnelCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .displayName("x")
                    .build()
            )

        betaTunnel.validate()
    }

    @Test
    fun retrieve() {
        val tunnelService = client.beta().tunnels().withOptions { it.putQueryParam("beta", "true") }

        val betaTunnel =
            tunnelService.retrieve(
                TunnelRetrieveParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaTunnel.validate()
    }

    @Test
    fun list() {
        val tunnelService = client.beta().tunnels().withOptions { it.putQueryParam("beta", "true") }

        val page = tunnelService.list()

        page.response().validate()
    }

    @Test
    fun archive() {
        val tunnelService = client.beta().tunnels().withOptions { it.putQueryParam("beta", "true") }

        val betaTunnel =
            tunnelService.archive(
                TunnelArchiveParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaTunnel.validate()
    }

    @Test
    fun revealToken() {
        val tunnelService = client.beta().tunnels().withOptions { it.putQueryParam("beta", "true") }

        val betaTunnelToken =
            tunnelService.revealToken(
                TunnelRevealTokenParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaTunnelToken.validate()
    }

    @Test
    fun rotateToken() {
        val tunnelService = client.beta().tunnels().withOptions { it.putQueryParam("beta", "true") }

        val betaTunnelToken =
            tunnelService.rotateToken(
                TunnelRotateTokenParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .reason("reason")
                    .build()
            )

        betaTunnelToken.validate()
    }
}
