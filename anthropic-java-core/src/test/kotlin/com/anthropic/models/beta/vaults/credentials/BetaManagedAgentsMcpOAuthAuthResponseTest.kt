// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpOAuthAuthResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpOAuthAuthResponse =
            BetaManagedAgentsMcpOAuthAuthResponse.builder()
                .mcpServerUrl("mcp_server_url")
                .type(BetaManagedAgentsMcpOAuthAuthResponse.Type.MCP_OAUTH)
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .refresh(
                    BetaManagedAgentsMcpOAuthRefreshResponse.builder()
                        .clientId("client_id")
                        .tokenEndpoint("token_endpoint")
                        .tokenEndpointAuth(
                            BetaManagedAgentsTokenEndpointAuthNoneResponse.builder()
                                .type(BetaManagedAgentsTokenEndpointAuthNoneResponse.Type.NONE)
                                .build()
                        )
                        .resource("resource")
                        .scope("scope")
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMcpOAuthAuthResponse.mcpServerUrl()).isEqualTo("mcp_server_url")
        assertThat(betaManagedAgentsMcpOAuthAuthResponse.type())
            .isEqualTo(BetaManagedAgentsMcpOAuthAuthResponse.Type.MCP_OAUTH)
        assertThat(betaManagedAgentsMcpOAuthAuthResponse.expiresAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsMcpOAuthAuthResponse.refresh())
            .contains(
                BetaManagedAgentsMcpOAuthRefreshResponse.builder()
                    .clientId("client_id")
                    .tokenEndpoint("token_endpoint")
                    .tokenEndpointAuth(
                        BetaManagedAgentsTokenEndpointAuthNoneResponse.builder()
                            .type(BetaManagedAgentsTokenEndpointAuthNoneResponse.Type.NONE)
                            .build()
                    )
                    .resource("resource")
                    .scope("scope")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpOAuthAuthResponse =
            BetaManagedAgentsMcpOAuthAuthResponse.builder()
                .mcpServerUrl("mcp_server_url")
                .type(BetaManagedAgentsMcpOAuthAuthResponse.Type.MCP_OAUTH)
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .refresh(
                    BetaManagedAgentsMcpOAuthRefreshResponse.builder()
                        .clientId("client_id")
                        .tokenEndpoint("token_endpoint")
                        .tokenEndpointAuth(
                            BetaManagedAgentsTokenEndpointAuthNoneResponse.builder()
                                .type(BetaManagedAgentsTokenEndpointAuthNoneResponse.Type.NONE)
                                .build()
                        )
                        .resource("resource")
                        .scope("scope")
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMcpOAuthAuthResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpOAuthAuthResponse),
                jacksonTypeRef<BetaManagedAgentsMcpOAuthAuthResponse>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpOAuthAuthResponse)
            .isEqualTo(betaManagedAgentsMcpOAuthAuthResponse)
    }
}
