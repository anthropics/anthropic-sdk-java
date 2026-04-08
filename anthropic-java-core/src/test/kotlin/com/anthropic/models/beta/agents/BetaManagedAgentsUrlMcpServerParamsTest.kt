// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUrlMcpServerParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsUrlMcpServerParams =
            BetaManagedAgentsUrlMcpServerParams.builder()
                .name("example-mcp")
                .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                .url("https://example-server.modelcontextprotocol.io/sse")
                .build()

        assertThat(betaManagedAgentsUrlMcpServerParams.name()).isEqualTo("example-mcp")
        assertThat(betaManagedAgentsUrlMcpServerParams.type())
            .isEqualTo(BetaManagedAgentsUrlMcpServerParams.Type.URL)
        assertThat(betaManagedAgentsUrlMcpServerParams.url())
            .isEqualTo("https://example-server.modelcontextprotocol.io/sse")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUrlMcpServerParams =
            BetaManagedAgentsUrlMcpServerParams.builder()
                .name("example-mcp")
                .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                .url("https://example-server.modelcontextprotocol.io/sse")
                .build()

        val roundtrippedBetaManagedAgentsUrlMcpServerParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUrlMcpServerParams),
                jacksonTypeRef<BetaManagedAgentsUrlMcpServerParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUrlMcpServerParams)
            .isEqualTo(betaManagedAgentsUrlMcpServerParams)
    }
}
