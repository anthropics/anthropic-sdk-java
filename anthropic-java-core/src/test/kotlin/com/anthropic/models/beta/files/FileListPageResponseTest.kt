// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

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
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .filename("x")
                        .mimeType("x")
                        .sizeBytes(0L)
                        .downloadable(true)
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(fileListPageResponse.data())
            .containsExactly(
                FileMetadata.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .filename("x")
                    .mimeType("x")
                    .sizeBytes(0L)
                    .downloadable(true)
                    .build()
            )
        assertThat(fileListPageResponse.firstId()).contains("first_id")
        assertThat(fileListPageResponse.hasMore()).contains(true)
        assertThat(fileListPageResponse.lastId()).contains("last_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val fileListPageResponse =
            FileListPageResponse.builder()
                .addData(
                    FileMetadata.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .filename("x")
                        .mimeType("x")
                        .sizeBytes(0L)
                        .downloadable(true)
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        val roundtrippedFileListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(fileListPageResponse),
                jacksonTypeRef<FileListPageResponse>(),
            )

        assertThat(roundtrippedFileListPageResponse).isEqualTo(fileListPageResponse)
    }
}
