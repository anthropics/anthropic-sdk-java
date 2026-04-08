// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpOAuthRefreshParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpOAuthRefreshParams =
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

        assertThat(betaManagedAgentsMcpOAuthRefreshParams.clientId()).isEqualTo("x")
        assertThat(betaManagedAgentsMcpOAuthRefreshParams.refreshToken()).isEqualTo("x")
        assertThat(betaManagedAgentsMcpOAuthRefreshParams.tokenEndpoint()).isEqualTo("x")
        assertThat(betaManagedAgentsMcpOAuthRefreshParams.tokenEndpointAuth())
            .isEqualTo(
                BetaManagedAgentsMcpOAuthRefreshParams.TokenEndpointAuth.ofNone(
                    BetaManagedAgentsTokenEndpointAuthNoneParam.builder()
                        .type(BetaManagedAgentsTokenEndpointAuthNoneParam.Type.NONE)
                        .build()
                )
            )
        assertThat(betaManagedAgentsMcpOAuthRefreshParams.resource()).contains("x")
        assertThat(betaManagedAgentsMcpOAuthRefreshParams.scope()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpOAuthRefreshParams =
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

        val roundtrippedBetaManagedAgentsMcpOAuthRefreshParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpOAuthRefreshParams),
                jacksonTypeRef<BetaManagedAgentsMcpOAuthRefreshParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpOAuthRefreshParams)
            .isEqualTo(betaManagedAgentsMcpOAuthRefreshParams)
    }
}
