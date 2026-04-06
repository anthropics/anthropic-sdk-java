// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileMetadataTest {

    @Test
    fun create() {
        val fileMetadata =
            FileMetadata.builder()
                .id("file_011CNha8iCJcU1wXNR6q4V8w")
                .createdAt(OffsetDateTime.parse("2025-04-15T18:37:24.100435Z"))
                .filename("document.pdf")
                .mimeType("application/pdf")
                .sizeBytes(102400L)
                .downloadable(false)
                .build()

        assertThat(fileMetadata.id()).isEqualTo("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(fileMetadata.createdAt())
            .isEqualTo(OffsetDateTime.parse("2025-04-15T18:37:24.100435Z"))
        assertThat(fileMetadata.filename()).isEqualTo("document.pdf")
        assertThat(fileMetadata.mimeType()).isEqualTo("application/pdf")
        assertThat(fileMetadata.sizeBytes()).isEqualTo(102400L)
        assertThat(fileMetadata.downloadable()).contains(false)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val fileMetadata =
            FileMetadata.builder()
                .id("file_011CNha8iCJcU1wXNR6q4V8w")
                .createdAt(OffsetDateTime.parse("2025-04-15T18:37:24.100435Z"))
                .filename("document.pdf")
                .mimeType("application/pdf")
                .sizeBytes(102400L)
                .downloadable(false)
                .build()

        val roundtrippedFileMetadata =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(fileMetadata),
                jacksonTypeRef<FileMetadata>(),
            )

        assertThat(roundtrippedFileMetadata).isEqualTo(fileMetadata)
    }
}
