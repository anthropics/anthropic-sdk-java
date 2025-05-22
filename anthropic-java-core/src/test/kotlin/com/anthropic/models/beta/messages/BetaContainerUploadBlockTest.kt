// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContainerUploadBlockTest {

    @Test
    fun create() {
        val betaContainerUploadBlock = BetaContainerUploadBlock.builder().fileId("file_id").build()

        assertThat(betaContainerUploadBlock.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaContainerUploadBlock = BetaContainerUploadBlock.builder().fileId("file_id").build()

        val roundtrippedBetaContainerUploadBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContainerUploadBlock),
                jacksonTypeRef<BetaContainerUploadBlock>(),
            )

        assertThat(roundtrippedBetaContainerUploadBlock).isEqualTo(betaContainerUploadBlock)
    }
}
