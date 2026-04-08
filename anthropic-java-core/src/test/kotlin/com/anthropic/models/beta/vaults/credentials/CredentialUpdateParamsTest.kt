// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CredentialUpdateParamsTest {

    @Test
    fun create() {
        CredentialUpdateParams.builder()
            .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
            .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .auth(
                BetaManagedAgentsMcpOAuthUpdateParams.builder()
                    .type(BetaManagedAgentsMcpOAuthUpdateParams.Type.MCP_OAUTH)
                    .accessToken("x")
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .refresh(
                        BetaManagedAgentsMcpOAuthRefreshUpdateParams.builder()
                            .refreshToken("x")
                            .scope("scope")
                            .tokenEndpointAuth(
                                BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder()
                                    .type(
                                        BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.Type
                                            .CLIENT_SECRET_BASIC
                                    )
                                    .clientSecret("x")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
            .displayName("Example credential")
            .metadata(
                CredentialUpdateParams.Metadata.builder()
                    .putAdditionalProperty("environment", JsonValue.from("production"))
                    .build()
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            CredentialUpdateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("vlt_011CZkZDLs7fYzm1hXNPeRjv")
        assertThat(params._pathParam(1)).isEqualTo("vcrd_011CZkZEMt8gZan2iYOQfSkw")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            CredentialUpdateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .auth(
                    BetaManagedAgentsMcpOAuthUpdateParams.builder()
                        .type(BetaManagedAgentsMcpOAuthUpdateParams.Type.MCP_OAUTH)
                        .accessToken("x")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .refresh(
                            BetaManagedAgentsMcpOAuthRefreshUpdateParams.builder()
                                .refreshToken("x")
                                .scope("scope")
                                .tokenEndpointAuth(
                                    BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder()
                                        .type(
                                            BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.Type
                                                .CLIENT_SECRET_BASIC
                                        )
                                        .clientSecret("x")
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .displayName("Example credential")
                .metadata(
                    CredentialUpdateParams.Metadata.builder()
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
            CredentialUpdateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            CredentialUpdateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .auth(
                    BetaManagedAgentsMcpOAuthUpdateParams.builder()
                        .type(BetaManagedAgentsMcpOAuthUpdateParams.Type.MCP_OAUTH)
                        .accessToken("x")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .refresh(
                            BetaManagedAgentsMcpOAuthRefreshUpdateParams.builder()
                                .refreshToken("x")
                                .scope("scope")
                                .tokenEndpointAuth(
                                    BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder()
                                        .type(
                                            BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.Type
                                                .CLIENT_SECRET_BASIC
                                        )
                                        .clientSecret("x")
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .displayName("Example credential")
                .metadata(
                    CredentialUpdateParams.Metadata.builder()
                        .putAdditionalProperty("environment", JsonValue.from("production"))
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.auth())
            .contains(
                CredentialUpdateParams.Auth.ofMcpOAuth(
                    BetaManagedAgentsMcpOAuthUpdateParams.builder()
                        .type(BetaManagedAgentsMcpOAuthUpdateParams.Type.MCP_OAUTH)
                        .accessToken("x")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .refresh(
                            BetaManagedAgentsMcpOAuthRefreshUpdateParams.builder()
                                .refreshToken("x")
                                .scope("scope")
                                .tokenEndpointAuth(
                                    BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder()
                                        .type(
                                            BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.Type
                                                .CLIENT_SECRET_BASIC
                                        )
                                        .clientSecret("x")
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
            )
        assertThat(body.displayName()).contains("Example credential")
        assertThat(body.metadata())
            .contains(
                CredentialUpdateParams.Metadata.builder()
                    .putAdditionalProperty("environment", JsonValue.from("production"))
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            CredentialUpdateParams.builder()
                .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .build()

        val body = params._body()
    }
}
