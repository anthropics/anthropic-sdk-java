// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpServerUrlDefinitionTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpServerUrlDefinition =
            BetaManagedAgentsMcpServerUrlDefinition.builder()
                .name("example-mcp")
                .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                .url("https://example-server.modelcontextprotocol.io/sse")
                .build()

        assertThat(betaManagedAgentsMcpServerUrlDefinition.name()).isEqualTo("example-mcp")
        assertThat(betaManagedAgentsMcpServerUrlDefinition.type())
            .isEqualTo(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
        assertThat(betaManagedAgentsMcpServerUrlDefinition.url())
            .isEqualTo("https://example-server.modelcontextprotocol.io/sse")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpServerUrlDefinition =
            BetaManagedAgentsMcpServerUrlDefinition.builder()
                .name("example-mcp")
                .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                .url("https://example-server.modelcontextprotocol.io/sse")
                .build()

        val roundtrippedBetaManagedAgentsMcpServerUrlDefinition =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpServerUrlDefinition),
                jacksonTypeRef<BetaManagedAgentsMcpServerUrlDefinition>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpServerUrlDefinition)
            .isEqualTo(betaManagedAgentsMcpServerUrlDefinition)
    }
}
