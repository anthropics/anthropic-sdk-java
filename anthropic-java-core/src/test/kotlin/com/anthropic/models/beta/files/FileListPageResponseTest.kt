// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

import kotlinx.kmp.util.core.jsonMapper
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
                        .build()
                )
                .firstId("file_011CNha8iCJcU1wXNR6q4V8w")
                .hasMore(true)
                .lastId("file_013Zva2CMHLNnXjNJJKqJ2EF")
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
                    .build()
            )
        assertThat(fileListPageResponse.firstId()).contains("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(fileListPageResponse.hasMore()).contains(true)
        assertThat(fileListPageResponse.lastId()).contains("file_013Zva2CMHLNnXjNJJKqJ2EF")
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
                        .build()
                )
                .firstId("file_011CNha8iCJcU1wXNR6q4V8w")
                .hasMore(true)
                .lastId("file_013Zva2CMHLNnXjNJJKqJ2EF")
                .build()

        val roundtrippedFileListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(fileListPageResponse),
                jacksonTypeRef<FileListPageResponse>(),
            )

        assertThat(roundtrippedFileListPageResponse).isEqualTo(fileListPageResponse)
    }
}
