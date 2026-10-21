package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsStaticBearerCreateParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsStaticBearerCreateParams =
            BetaManagedAgentsStaticBearerCreateParams.builder()
                .token("bearer_exampletoken")
                .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                .build()

        assertThat(betaManagedAgentsStaticBearerCreateParams.token())
            .isEqualTo("bearer_exampletoken")
        assertThat(betaManagedAgentsStaticBearerCreateParams.mcpServerUrl())
            .isEqualTo("https://example-server.modelcontextprotocol.io/sse")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStaticBearerCreateParams =
            BetaManagedAgentsStaticBearerCreateParams.builder()
                .token("bearer_exampletoken")
                .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                .build()

        val roundtrippedBetaManagedAgentsStaticBearerCreateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStaticBearerCreateParams),
                jacksonTypeRef<BetaManagedAgentsStaticBearerCreateParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStaticBearerCreateParams)
            .isEqualTo(betaManagedAgentsStaticBearerCreateParams)
    }
}
