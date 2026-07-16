// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
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
internal class TunnelServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelServiceAsync = client.beta().tunnels()

        val betaTunnelFuture =
            tunnelServiceAsync.create(
                TunnelCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .displayName("x")
                    .build()
            )

        val betaTunnel = betaTunnelFuture.get()
        betaTunnel.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelServiceAsync = client.beta().tunnels()

        val betaTunnelFuture =
            tunnelServiceAsync.retrieve(
                TunnelRetrieveParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaTunnel = betaTunnelFuture.get()
        betaTunnel.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelServiceAsync = client.beta().tunnels()

        val pageFuture = tunnelServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelServiceAsync = client.beta().tunnels()

        val betaTunnelFuture =
            tunnelServiceAsync.archive(
                TunnelArchiveParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaTunnel = betaTunnelFuture.get()
        betaTunnel.validate()
    }

    @Test
    fun revealToken() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelServiceAsync = client.beta().tunnels()

        val betaTunnelTokenFuture =
            tunnelServiceAsync.revealToken(
                TunnelRevealTokenParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaTunnelToken = betaTunnelTokenFuture.get()
        betaTunnelToken.validate()
    }

    @Test
    fun rotateToken() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val tunnelServiceAsync = client.beta().tunnels()

        val betaTunnelTokenFuture =
            tunnelServiceAsync.rotateToken(
                TunnelRotateTokenParams.builder()
                    .tunnelId("tunnel_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .reason("reason")
                    .build()
            )

        val betaTunnelToken = betaTunnelTokenFuture.get()
        betaTunnelToken.validate()
    }
}
