// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TunnelListPageResponseTest {

    @Test
    fun create() {
        val tunnelListPageResponse =
            TunnelListPageResponse.builder()
                .addData(
                    BetaTunnel.builder()
                        .id("id")
                        .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .displayName("display_name")
                        .domain("domain")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(tunnelListPageResponse.data())
            .containsExactly(
                BetaTunnel.builder()
                    .id("id")
                    .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .displayName("display_name")
                    .domain("domain")
                    .build()
            )
        assertThat(tunnelListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val tunnelListPageResponse =
            TunnelListPageResponse.builder()
                .addData(
                    BetaTunnel.builder()
                        .id("id")
                        .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .displayName("display_name")
                        .domain("domain")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedTunnelListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(tunnelListPageResponse),
                jacksonTypeRef<TunnelListPageResponse>(),
            )

        assertThat(roundtrippedTunnelListPageResponse).isEqualTo(tunnelListPageResponse)
    }
}
