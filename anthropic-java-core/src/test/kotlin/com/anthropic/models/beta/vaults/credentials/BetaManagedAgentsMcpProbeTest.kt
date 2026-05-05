// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpProbeTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpProbe =
            BetaManagedAgentsMcpProbe.builder()
                .httpResponse(
                    BetaManagedAgentsRefreshHttpResponse.builder()
                        .body("body")
                        .bodyTruncated(true)
                        .contentType("content_type")
                        .statusCode(0)
                        .build()
                )
                .method("method")
                .build()

        assertThat(betaManagedAgentsMcpProbe.httpResponse())
            .contains(
                BetaManagedAgentsRefreshHttpResponse.builder()
                    .body("body")
                    .bodyTruncated(true)
                    .contentType("content_type")
                    .statusCode(0)
                    .build()
            )
        assertThat(betaManagedAgentsMcpProbe.method()).isEqualTo("method")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpProbe =
            BetaManagedAgentsMcpProbe.builder()
                .httpResponse(
                    BetaManagedAgentsRefreshHttpResponse.builder()
                        .body("body")
                        .bodyTruncated(true)
                        .contentType("content_type")
                        .statusCode(0)
                        .build()
                )
                .method("method")
                .build()

        val roundtrippedBetaManagedAgentsMcpProbe =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpProbe),
                jacksonTypeRef<BetaManagedAgentsMcpProbe>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpProbe).isEqualTo(betaManagedAgentsMcpProbe)
    }
}
