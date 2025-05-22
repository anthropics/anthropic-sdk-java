// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileMetadataTest {

    @Test
    fun create() {
        val fileMetadata =
            FileMetadata.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .filename("x")
                .mimeType("x")
                .sizeBytes(0L)
                .downloadable(true)
                .build()

        assertThat(fileMetadata.id()).isEqualTo("id")
        assertThat(fileMetadata.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(fileMetadata.filename()).isEqualTo("x")
        assertThat(fileMetadata.mimeType()).isEqualTo("x")
        assertThat(fileMetadata.sizeBytes()).isEqualTo(0L)
        assertThat(fileMetadata.downloadable()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val fileMetadata =
            FileMetadata.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .filename("x")
                .mimeType("x")
                .sizeBytes(0L)
                .downloadable(true)
                .build()

        val roundtrippedFileMetadata =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(fileMetadata),
                jacksonTypeRef<FileMetadata>(),
            )

        assertThat(roundtrippedFileMetadata).isEqualTo(fileMetadata)
    }
}
