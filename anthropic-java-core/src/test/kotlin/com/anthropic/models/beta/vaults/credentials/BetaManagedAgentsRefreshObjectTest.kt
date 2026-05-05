// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsRefreshObjectTest {

    @Test
    fun create() {
        val betaManagedAgentsRefreshObject =
            BetaManagedAgentsRefreshObject.builder()
                .httpResponse(
                    BetaManagedAgentsRefreshHttpResponse.builder()
                        .body("body")
                        .bodyTruncated(true)
                        .contentType("content_type")
                        .statusCode(0)
                        .build()
                )
                .status(BetaManagedAgentsRefreshObject.Status.SUCCEEDED)
                .build()

        assertThat(betaManagedAgentsRefreshObject.httpResponse())
            .contains(
                BetaManagedAgentsRefreshHttpResponse.builder()
                    .body("body")
                    .bodyTruncated(true)
                    .contentType("content_type")
                    .statusCode(0)
                    .build()
            )
        assertThat(betaManagedAgentsRefreshObject.status())
            .isEqualTo(BetaManagedAgentsRefreshObject.Status.SUCCEEDED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsRefreshObject =
            BetaManagedAgentsRefreshObject.builder()
                .httpResponse(
                    BetaManagedAgentsRefreshHttpResponse.builder()
                        .body("body")
                        .bodyTruncated(true)
                        .contentType("content_type")
                        .statusCode(0)
                        .build()
                )
                .status(BetaManagedAgentsRefreshObject.Status.SUCCEEDED)
                .build()

        val roundtrippedBetaManagedAgentsRefreshObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsRefreshObject),
                jacksonTypeRef<BetaManagedAgentsRefreshObject>(),
            )

        assertThat(roundtrippedBetaManagedAgentsRefreshObject)
            .isEqualTo(betaManagedAgentsRefreshObject)
    }
}
