// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.tunnels.TunnelArchiveParams
import com.anthropic.models.beta.tunnels.TunnelCreateParams
import com.anthropic.models.beta.tunnels.TunnelRetrieveParams
import com.anthropic.models.beta.tunnels.TunnelRevealTokenParams
import com.anthropic.models.beta.tunnels.TunnelRotateTokenParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class TunnelServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelService = client.beta().tunnels()

        val betaTunnel =
            tunnelService.create(
                TunnelCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .displayName("x")
                    .build()
            )

        betaTunnel.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelService = client.beta().tunnels()

        val betaTunnel =
            tunnelService.retrieve(
                TunnelRetrieveParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaTunnel.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelService = client.beta().tunnels()

        val page = tunnelService.list()

        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelService = client.beta().tunnels()

        val betaTunnel =
            tunnelService.archive(
                TunnelArchiveParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaTunnel.validate()
    }

    @Test
    fun revealToken() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelService = client.beta().tunnels()

        val betaTunnelToken =
            tunnelService.revealToken(
                TunnelRevealTokenParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaTunnelToken.validate()
    }

    @Test
    fun rotateToken() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelService = client.beta().tunnels()

        val betaTunnelToken =
            tunnelService.rotateToken(
                TunnelRotateTokenParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .reason("reason")
                    .build()
            )

        betaTunnelToken.validate()
    }
}
