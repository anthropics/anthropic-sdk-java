// File generated from our OpenAPI spec by Stainless.

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
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
                .type(BetaManagedAgentsMcpConnectionFailedError.Type.MCP_CONNECTION_FAILED_ERROR)
                .build()

        assertThat(betaManagedAgentsMcpConnectionFailedError.mcpServerName())
            .isEqualTo("mcp_server_name")
        assertThat(betaManagedAgentsMcpConnectionFailedError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsMcpConnectionFailedError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsMcpConnectionFailedError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
            )
        assertThat(betaManagedAgentsMcpConnectionFailedError.type())
            .isEqualTo(BetaManagedAgentsMcpConnectionFailedError.Type.MCP_CONNECTION_FAILED_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpConnectionFailedError =
            BetaManagedAgentsMcpConnectionFailedError.builder()
                .mcpServerName("mcp_server_name")
                .message("message")
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
                .type(BetaManagedAgentsMcpConnectionFailedError.Type.MCP_CONNECTION_FAILED_ERROR)
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
