// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTunnelTest {

    @Test
    fun create() {
        val betaTunnel =
            BetaTunnel.builder()
                .id("id")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .displayName("display_name")
                .domain("domain")
                .build()

        assertThat(betaTunnel.id()).isEqualTo("id")
        assertThat(betaTunnel.archivedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaTunnel.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaTunnel.displayName()).contains("display_name")
        assertThat(betaTunnel.domain()).isEqualTo("domain")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTunnel =
            BetaTunnel.builder()
                .id("id")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .displayName("display_name")
                .domain("domain")
                .build()

        val roundtrippedBetaTunnel =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTunnel),
                jacksonTypeRef<BetaTunnel>(),
            )

        assertThat(roundtrippedBetaTunnel).isEqualTo(betaTunnel)
    }
}
