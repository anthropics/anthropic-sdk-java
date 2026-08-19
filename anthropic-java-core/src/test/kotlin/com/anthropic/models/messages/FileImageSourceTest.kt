// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileImageSourceTest {

    @Test
    fun create() {
        val fileImageSource = FileImageSource.of("file_id")

        assertThat(fileImageSource.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val fileImageSource = FileImageSource.of("file_id")

        val roundtrippedFileImageSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(fileImageSource),
                jacksonTypeRef<FileImageSource>(),
            )

        assertThat(roundtrippedFileImageSource).isEqualTo(fileImageSource)
    }
}
