// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContainerUploadBlockTest {

    @Test
    fun create() {
        val containerUploadBlock = ContainerUploadBlock.builder().fileId("file_id").build()

        assertThat(containerUploadBlock.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val containerUploadBlock = ContainerUploadBlock.builder().fileId("file_id").build()

        val roundtrippedContainerUploadBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(containerUploadBlock),
                jacksonTypeRef<ContainerUploadBlock>(),
            )

        assertThat(roundtrippedContainerUploadBlock).isEqualTo(containerUploadBlock)
    }
}
