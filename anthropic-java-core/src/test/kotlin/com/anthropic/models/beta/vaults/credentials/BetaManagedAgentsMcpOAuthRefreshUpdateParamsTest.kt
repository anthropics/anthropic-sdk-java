// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpOAuthRefreshUpdateParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpOAuthRefreshUpdateParams =
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

        assertThat(betaManagedAgentsMcpOAuthRefreshUpdateParams.refreshToken()).contains("x")
        assertThat(betaManagedAgentsMcpOAuthRefreshUpdateParams.scope()).contains("scope")
        assertThat(betaManagedAgentsMcpOAuthRefreshUpdateParams.tokenEndpointAuth())
            .contains(
                BetaManagedAgentsMcpOAuthRefreshUpdateParams.TokenEndpointAuth.ofClientSecretBasic(
                    BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder()
                        .type(
                            BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.Type
                                .CLIENT_SECRET_BASIC
                        )
                        .clientSecret("x")
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpOAuthRefreshUpdateParams =
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

        val roundtrippedBetaManagedAgentsMcpOAuthRefreshUpdateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpOAuthRefreshUpdateParams),
                jacksonTypeRef<BetaManagedAgentsMcpOAuthRefreshUpdateParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpOAuthRefreshUpdateParams)
            .isEqualTo(betaManagedAgentsMcpOAuthRefreshUpdateParams)
    }
}
