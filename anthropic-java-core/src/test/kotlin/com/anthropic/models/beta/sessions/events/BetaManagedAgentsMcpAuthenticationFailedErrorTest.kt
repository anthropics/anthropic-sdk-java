package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpAuthenticationFailedErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpAuthenticationFailedError =
            BetaManagedAgentsMcpAuthenticationFailedError.builder()
                .mcpServerName("mcp_server_name")
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        assertThat(betaManagedAgentsMcpAuthenticationFailedError.mcpServerName())
            .isEqualTo("mcp_server_name")
        assertThat(betaManagedAgentsMcpAuthenticationFailedError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsMcpAuthenticationFailedError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsMcpAuthenticationFailedError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpAuthenticationFailedError =
            BetaManagedAgentsMcpAuthenticationFailedError.builder()
                .mcpServerName("mcp_server_name")
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsMcpAuthenticationFailedError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpAuthenticationFailedError),
                jacksonTypeRef<BetaManagedAgentsMcpAuthenticationFailedError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpAuthenticationFailedError)
            .isEqualTo(betaManagedAgentsMcpAuthenticationFailedError)
    }
}
