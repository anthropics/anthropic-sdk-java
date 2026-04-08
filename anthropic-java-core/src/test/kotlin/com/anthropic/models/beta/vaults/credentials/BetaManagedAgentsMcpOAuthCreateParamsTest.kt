// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpOAuthCreateParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpOAuthCreateParams =
            BetaManagedAgentsMcpOAuthCreateParams.builder()
                .accessToken("x")
                .mcpServerUrl("x")
                .type(BetaManagedAgentsMcpOAuthCreateParams.Type.MCP_OAUTH)
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .refresh(
                    BetaManagedAgentsMcpOAuthRefreshParams.builder()
                        .clientId("x")
                        .refreshToken("x")
                        .tokenEndpoint("x")
                        .tokenEndpointAuth(
                            BetaManagedAgentsTokenEndpointAuthNoneParam.builder()
                                .type(BetaManagedAgentsTokenEndpointAuthNoneParam.Type.NONE)
                                .build()
                        )
                        .resource("x")
                        .scope("x")
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMcpOAuthCreateParams.accessToken()).isEqualTo("x")
        assertThat(betaManagedAgentsMcpOAuthCreateParams.mcpServerUrl()).isEqualTo("x")
        assertThat(betaManagedAgentsMcpOAuthCreateParams.type())
            .isEqualTo(BetaManagedAgentsMcpOAuthCreateParams.Type.MCP_OAUTH)
        assertThat(betaManagedAgentsMcpOAuthCreateParams.expiresAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsMcpOAuthCreateParams.refresh())
            .contains(
                BetaManagedAgentsMcpOAuthRefreshParams.builder()
                    .clientId("x")
                    .refreshToken("x")
                    .tokenEndpoint("x")
                    .tokenEndpointAuth(
                        BetaManagedAgentsTokenEndpointAuthNoneParam.builder()
                            .type(BetaManagedAgentsTokenEndpointAuthNoneParam.Type.NONE)
                            .build()
                    )
                    .resource("x")
                    .scope("x")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpOAuthCreateParams =
            BetaManagedAgentsMcpOAuthCreateParams.builder()
                .accessToken("x")
                .mcpServerUrl("x")
                .type(BetaManagedAgentsMcpOAuthCreateParams.Type.MCP_OAUTH)
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .refresh(
                    BetaManagedAgentsMcpOAuthRefreshParams.builder()
                        .clientId("x")
                        .refreshToken("x")
                        .tokenEndpoint("x")
                        .tokenEndpointAuth(
                            BetaManagedAgentsTokenEndpointAuthNoneParam.builder()
                                .type(BetaManagedAgentsTokenEndpointAuthNoneParam.Type.NONE)
                                .build()
                        )
                        .resource("x")
                        .scope("x")
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMcpOAuthCreateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpOAuthCreateParams),
                jacksonTypeRef<BetaManagedAgentsMcpOAuthCreateParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpOAuthCreateParams)
            .isEqualTo(betaManagedAgentsMcpOAuthCreateParams)
    }
}
