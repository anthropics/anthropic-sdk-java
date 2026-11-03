package com.anthropic.models.files

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileExpiredErrorDetailsTest {

    @Test
    fun create() {
        val fileExpiredErrorDetails = FileExpiredErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val fileExpiredErrorDetails = FileExpiredErrorDetails.builder().build()

        val roundtrippedFileExpiredErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(fileExpiredErrorDetails),
                jacksonTypeRef<FileExpiredErrorDetails>(),
            )

        assertThat(roundtrippedFileExpiredErrorDetails).isEqualTo(fileExpiredErrorDetails)
    }
}
