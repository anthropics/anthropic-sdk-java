// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpOAuthRefreshResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpOAuthRefreshResponse =
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

        assertThat(betaManagedAgentsMcpOAuthRefreshResponse.clientId()).isEqualTo("client_id")
        assertThat(betaManagedAgentsMcpOAuthRefreshResponse.tokenEndpoint())
            .isEqualTo("token_endpoint")
        assertThat(betaManagedAgentsMcpOAuthRefreshResponse.tokenEndpointAuth())
            .isEqualTo(
                BetaManagedAgentsMcpOAuthRefreshResponse.TokenEndpointAuth.ofNone(
                    BetaManagedAgentsTokenEndpointAuthNoneResponse.builder()
                        .type(BetaManagedAgentsTokenEndpointAuthNoneResponse.Type.NONE)
                        .build()
                )
            )
        assertThat(betaManagedAgentsMcpOAuthRefreshResponse.resource()).contains("resource")
        assertThat(betaManagedAgentsMcpOAuthRefreshResponse.scope()).contains("scope")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpOAuthRefreshResponse =
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

        val roundtrippedBetaManagedAgentsMcpOAuthRefreshResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpOAuthRefreshResponse),
                jacksonTypeRef<BetaManagedAgentsMcpOAuthRefreshResponse>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpOAuthRefreshResponse)
            .isEqualTo(betaManagedAgentsMcpOAuthRefreshResponse)
    }
}
