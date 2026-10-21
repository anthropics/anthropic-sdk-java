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
                .accessToken("x")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .refresh(
                    BetaManagedAgentsMcpOAuthRefreshUpdateParams.builder()
                        .refreshToken("x")
                        .scope("scope")
                        .tokenEndpointAuth(
                            BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder()
                                .clientSecret("x")
                                .build()
                        )
                        .build()
                )
                .build()

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
                .accessToken("x")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .refresh(
                    BetaManagedAgentsMcpOAuthRefreshUpdateParams.builder()
                        .refreshToken("x")
                        .scope("scope")
                        .tokenEndpointAuth(
                            BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder()
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
