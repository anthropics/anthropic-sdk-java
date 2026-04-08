// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpOAuthUpdateParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpOAuthUpdateParams =
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

        assertThat(betaManagedAgentsMcpOAuthUpdateParams.type())
            .isEqualTo(BetaManagedAgentsMcpOAuthUpdateParams.Type.MCP_OAUTH)
        assertThat(betaManagedAgentsMcpOAuthUpdateParams.accessToken()).contains("x")
        assertThat(betaManagedAgentsMcpOAuthUpdateParams.expiresAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsMcpOAuthUpdateParams.refresh())
            .contains(
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
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpOAuthUpdateParams =
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

        val roundtrippedBetaManagedAgentsMcpOAuthUpdateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpOAuthUpdateParams),
                jacksonTypeRef<BetaManagedAgentsMcpOAuthUpdateParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpOAuthUpdateParams)
            .isEqualTo(betaManagedAgentsMcpOAuthUpdateParams)
    }
}
