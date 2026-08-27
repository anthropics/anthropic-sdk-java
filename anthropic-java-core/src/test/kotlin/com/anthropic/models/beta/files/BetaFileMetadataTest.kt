// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFileMetadataTest {

    @Test
    fun create() {
        val betaFileMetadata =
            BetaFileMetadata.builder()
                .id("file_011CNha8iCJcU1wXNR6q4V8w")
                .createdAt(OffsetDateTime.parse("2025-04-15T18:37:24.100435Z"))
                .filename("document.pdf")
                .mimeType("application/pdf")
                .sizeBytes(102400L)
                .downloadable(false)
                .expiresAt(OffsetDateTime.parse("2025-05-15T18:37:24.100435Z"))
                .scope(BetaFileScope.of("id"))
                .build()

        assertThat(betaFileMetadata.id()).isEqualTo("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(betaFileMetadata.createdAt())
            .isEqualTo(OffsetDateTime.parse("2025-04-15T18:37:24.100435Z"))
        assertThat(betaFileMetadata.filename()).isEqualTo("document.pdf")
        assertThat(betaFileMetadata.mimeType()).isEqualTo("application/pdf")
        assertThat(betaFileMetadata.sizeBytes()).isEqualTo(102400L)
        assertThat(betaFileMetadata.downloadable()).contains(false)
        assertThat(betaFileMetadata.expiresAt())
            .contains(OffsetDateTime.parse("2025-05-15T18:37:24.100435Z"))
        assertThat(betaFileMetadata.scope()).contains(BetaFileScope.of("id"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFileMetadata =
            BetaFileMetadata.builder()
                .id("file_011CNha8iCJcU1wXNR6q4V8w")
                .createdAt(OffsetDateTime.parse("2025-04-15T18:37:24.100435Z"))
                .filename("document.pdf")
                .mimeType("application/pdf")
                .sizeBytes(102400L)
                .downloadable(false)
                .expiresAt(OffsetDateTime.parse("2025-05-15T18:37:24.100435Z"))
                .scope(BetaFileScope.of("id"))
                .build()

        val roundtrippedBetaFileMetadata =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFileMetadata),
                jacksonTypeRef<BetaFileMetadata>(),
            )

        assertThat(roundtrippedBetaFileMetadata).isEqualTo(betaFileMetadata)
    }
}
