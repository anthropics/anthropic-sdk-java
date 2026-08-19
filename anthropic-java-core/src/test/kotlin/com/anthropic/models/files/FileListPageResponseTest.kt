// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.files

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileListPageResponseTest {

    @Test
    fun create() {
        val fileListPageResponse =
            FileListPageResponse.builder()
                .addData(
                    FileMetadata.builder()
                        .id("file_011CNha8iCJcU1wXNR6q4V8w")
                        .createdAt(OffsetDateTime.parse("2025-04-15T18:37:24.100435Z"))
                        .filename("document.pdf")
                        .mimeType("application/pdf")
                        .sizeBytes(102400L)
                        .downloadable(false)
                        .expiresAt(OffsetDateTime.parse("2025-05-15T18:37:24.100435Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(fileListPageResponse.data())
            .containsExactly(
                FileMetadata.builder()
                    .id("file_011CNha8iCJcU1wXNR6q4V8w")
                    .createdAt(OffsetDateTime.parse("2025-04-15T18:37:24.100435Z"))
                    .filename("document.pdf")
                    .mimeType("application/pdf")
                    .sizeBytes(102400L)
                    .downloadable(false)
                    .expiresAt(OffsetDateTime.parse("2025-05-15T18:37:24.100435Z"))
                    .build()
            )
        assertThat(fileListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val fileListPageResponse =
            FileListPageResponse.builder()
                .addData(
                    FileMetadata.builder()
                        .id("file_011CNha8iCJcU1wXNR6q4V8w")
                        .createdAt(OffsetDateTime.parse("2025-04-15T18:37:24.100435Z"))
                        .filename("document.pdf")
                        .mimeType("application/pdf")
                        .sizeBytes(102400L)
                        .downloadable(false)
                        .expiresAt(OffsetDateTime.parse("2025-05-15T18:37:24.100435Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedFileListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(fileListPageResponse),
                jacksonTypeRef<FileListPageResponse>(),
            )

        assertThat(roundtrippedFileListPageResponse).isEqualTo(fileListPageResponse)
    }
}
