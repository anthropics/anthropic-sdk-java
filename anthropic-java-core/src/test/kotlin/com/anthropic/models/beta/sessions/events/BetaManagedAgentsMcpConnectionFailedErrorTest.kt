package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpConnectionFailedErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpConnectionFailedError =
            BetaManagedAgentsMcpConnectionFailedError.builder()
                .mcpServerName("mcp_server_name")
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        assertThat(betaManagedAgentsMcpConnectionFailedError.mcpServerName())
            .isEqualTo("mcp_server_name")
        assertThat(betaManagedAgentsMcpConnectionFailedError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsMcpConnectionFailedError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsMcpConnectionFailedError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpConnectionFailedError =
            BetaManagedAgentsMcpConnectionFailedError.builder()
                .mcpServerName("mcp_server_name")
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsMcpConnectionFailedError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpConnectionFailedError),
                jacksonTypeRef<BetaManagedAgentsMcpConnectionFailedError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpConnectionFailedError)
            .isEqualTo(betaManagedAgentsMcpConnectionFailedError)
    }
}
