// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileDocumentSourceTest {

    @Test
    fun create() {
        val fileDocumentSource = FileDocumentSource.of("file_id")

        assertThat(fileDocumentSource.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val fileDocumentSource = FileDocumentSource.of("file_id")

        val roundtrippedFileDocumentSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(fileDocumentSource),
                jacksonTypeRef<FileDocumentSource>(),
            )

        assertThat(roundtrippedFileDocumentSource).isEqualTo(fileDocumentSource)
    }
}
