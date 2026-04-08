// File generated from our OpenAPI spec by Stainless.

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
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
                .type(
                    BetaManagedAgentsMcpAuthenticationFailedError.Type
                        .MCP_AUTHENTICATION_FAILED_ERROR
                )
                .build()

        assertThat(betaManagedAgentsMcpAuthenticationFailedError.mcpServerName())
            .isEqualTo("mcp_server_name")
        assertThat(betaManagedAgentsMcpAuthenticationFailedError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsMcpAuthenticationFailedError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsMcpAuthenticationFailedError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
            )
        assertThat(betaManagedAgentsMcpAuthenticationFailedError.type())
            .isEqualTo(
                BetaManagedAgentsMcpAuthenticationFailedError.Type.MCP_AUTHENTICATION_FAILED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpAuthenticationFailedError =
            BetaManagedAgentsMcpAuthenticationFailedError.builder()
                .mcpServerName("mcp_server_name")
                .message("message")
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
                .type(
                    BetaManagedAgentsMcpAuthenticationFailedError.Type
                        .MCP_AUTHENTICATION_FAILED_ERROR
                )
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
