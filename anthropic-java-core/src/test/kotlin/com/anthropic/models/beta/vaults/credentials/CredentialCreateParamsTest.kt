// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CredentialCreateParamsTest {

    @Test
    fun create() {
        CredentialCreateParams.builder()
            .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .auth(
                BetaManagedAgentsStaticBearerCreateParams.builder()
                    .token("bearer_exampletoken")
                    .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                    .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
                    .build()
            )
            .displayName("Example credential")
            .metadata(
                CredentialCreateParams.Metadata.builder()
                    .putAdditionalProperty("environment", JsonValue.from("production"))
                    .build()
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            CredentialCreateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .auth(
                    BetaManagedAgentsStaticBearerCreateParams.builder()
                        .token("bearer_exampletoken")
                        .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                        .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
                        .build()
                )
                .build()

        assertThat(params._pathParam(0)).isEqualTo("vlt_011CZkZDLs7fYzm1hXNPeRjv")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            CredentialCreateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .auth(
                    BetaManagedAgentsStaticBearerCreateParams.builder()
                        .token("bearer_exampletoken")
                        .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                        .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
                        .build()
                )
                .displayName("Example credential")
                .metadata(
                    CredentialCreateParams.Metadata.builder()
                        .putAdditionalProperty("environment", JsonValue.from("production"))
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            CredentialCreateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .auth(
                    BetaManagedAgentsStaticBearerCreateParams.builder()
                        .token("bearer_exampletoken")
                        .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                        .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            CredentialCreateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .auth(
                    BetaManagedAgentsStaticBearerCreateParams.builder()
                        .token("bearer_exampletoken")
                        .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                        .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
                        .build()
                )
                .displayName("Example credential")
                .metadata(
                    CredentialCreateParams.Metadata.builder()
                        .putAdditionalProperty("environment", JsonValue.from("production"))
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.auth())
            .isEqualTo(
                CredentialCreateParams.Auth.ofStaticBearer(
                    BetaManagedAgentsStaticBearerCreateParams.builder()
                        .token("bearer_exampletoken")
                        .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                        .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
                        .build()
                )
            )
        assertThat(body.displayName()).contains("Example credential")
        assertThat(body.metadata())
            .contains(
                CredentialCreateParams.Metadata.builder()
                    .putAdditionalProperty("environment", JsonValue.from("production"))
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            CredentialCreateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .auth(
                    BetaManagedAgentsStaticBearerCreateParams.builder()
                        .token("bearer_exampletoken")
                        .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                        .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.auth())
            .isEqualTo(
                CredentialCreateParams.Auth.ofStaticBearer(
                    BetaManagedAgentsStaticBearerCreateParams.builder()
                        .token("bearer_exampletoken")
                        .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                        .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
                        .build()
                )
            )
    }
}
