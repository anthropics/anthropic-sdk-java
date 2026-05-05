// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsRefreshHttpResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsRefreshHttpResponse =
            BetaManagedAgentsRefreshHttpResponse.builder()
                .body("body")
                .bodyTruncated(true)
                .contentType("content_type")
                .statusCode(0)
                .build()

        assertThat(betaManagedAgentsRefreshHttpResponse.body()).isEqualTo("body")
        assertThat(betaManagedAgentsRefreshHttpResponse.bodyTruncated()).isEqualTo(true)
        assertThat(betaManagedAgentsRefreshHttpResponse.contentType()).isEqualTo("content_type")
        assertThat(betaManagedAgentsRefreshHttpResponse.statusCode()).isEqualTo(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsRefreshHttpResponse =
            BetaManagedAgentsRefreshHttpResponse.builder()
                .body("body")
                .bodyTruncated(true)
                .contentType("content_type")
                .statusCode(0)
                .build()

        val roundtrippedBetaManagedAgentsRefreshHttpResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsRefreshHttpResponse),
                jacksonTypeRef<BetaManagedAgentsRefreshHttpResponse>(),
            )

        assertThat(roundtrippedBetaManagedAgentsRefreshHttpResponse)
            .isEqualTo(betaManagedAgentsRefreshHttpResponse)
    }
}
