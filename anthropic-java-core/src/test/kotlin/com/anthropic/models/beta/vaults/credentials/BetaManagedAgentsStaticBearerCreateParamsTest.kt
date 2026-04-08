// File generated from our OpenAPI spec by Stainless.

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
                .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
                .build()

        assertThat(betaManagedAgentsStaticBearerCreateParams.token())
            .isEqualTo("bearer_exampletoken")
        assertThat(betaManagedAgentsStaticBearerCreateParams.mcpServerUrl())
            .isEqualTo("https://example-server.modelcontextprotocol.io/sse")
        assertThat(betaManagedAgentsStaticBearerCreateParams.type())
            .isEqualTo(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStaticBearerCreateParams =
            BetaManagedAgentsStaticBearerCreateParams.builder()
                .token("bearer_exampletoken")
                .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
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
