// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsStaticBearerAuthResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsStaticBearerAuthResponse =
            BetaManagedAgentsStaticBearerAuthResponse.builder()
                .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                .type(BetaManagedAgentsStaticBearerAuthResponse.Type.STATIC_BEARER)
                .build()

        assertThat(betaManagedAgentsStaticBearerAuthResponse.mcpServerUrl())
            .isEqualTo("https://example-server.modelcontextprotocol.io/sse")
        assertThat(betaManagedAgentsStaticBearerAuthResponse.type())
            .isEqualTo(BetaManagedAgentsStaticBearerAuthResponse.Type.STATIC_BEARER)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStaticBearerAuthResponse =
            BetaManagedAgentsStaticBearerAuthResponse.builder()
                .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                .type(BetaManagedAgentsStaticBearerAuthResponse.Type.STATIC_BEARER)
                .build()

        val roundtrippedBetaManagedAgentsStaticBearerAuthResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStaticBearerAuthResponse),
                jacksonTypeRef<BetaManagedAgentsStaticBearerAuthResponse>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStaticBearerAuthResponse)
            .isEqualTo(betaManagedAgentsStaticBearerAuthResponse)
    }
}
