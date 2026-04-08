// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileResourceTest {

    @Test
    fun create() {
        val betaManagedAgentsFileResource =
            BetaManagedAgentsFileResource.builder()
                .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .mountPath("/uploads/receipt.pdf")
                .type(BetaManagedAgentsFileResource.Type.FILE)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        assertThat(betaManagedAgentsFileResource.id()).isEqualTo("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
        assertThat(betaManagedAgentsFileResource.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsFileResource.fileId())
            .isEqualTo("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(betaManagedAgentsFileResource.mountPath()).isEqualTo("/uploads/receipt.pdf")
        assertThat(betaManagedAgentsFileResource.type())
            .isEqualTo(BetaManagedAgentsFileResource.Type.FILE)
        assertThat(betaManagedAgentsFileResource.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileResource =
            BetaManagedAgentsFileResource.builder()
                .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .mountPath("/uploads/receipt.pdf")
                .type(BetaManagedAgentsFileResource.Type.FILE)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        val roundtrippedBetaManagedAgentsFileResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileResource),
                jacksonTypeRef<BetaManagedAgentsFileResource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileResource)
            .isEqualTo(betaManagedAgentsFileResource)
    }
}
