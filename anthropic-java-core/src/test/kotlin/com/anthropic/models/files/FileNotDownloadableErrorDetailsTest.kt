package com.anthropic.models.files

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileNotDownloadableErrorDetailsTest {

    @Test
    fun create() {
        val fileNotDownloadableErrorDetails = FileNotDownloadableErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val fileNotDownloadableErrorDetails = FileNotDownloadableErrorDetails.builder().build()

        val roundtrippedFileNotDownloadableErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(fileNotDownloadableErrorDetails),
                jacksonTypeRef<FileNotDownloadableErrorDetails>(),
            )

        assertThat(roundtrippedFileNotDownloadableErrorDetails)
            .isEqualTo(fileNotDownloadableErrorDetails)
    }
}
