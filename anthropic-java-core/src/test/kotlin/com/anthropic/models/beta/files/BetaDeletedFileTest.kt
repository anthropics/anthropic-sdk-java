// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedFileTest {

    @Test
    fun create() {
        val betaDeletedFile =
            BetaDeletedFile.builder()
                .id("file_011CNha8iCJcU1wXNR6q4V8w")
                .type(BetaDeletedFile.Type.FILE_DELETED)
                .build()

        assertThat(betaDeletedFile.id()).isEqualTo("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(betaDeletedFile.type()).contains(BetaDeletedFile.Type.FILE_DELETED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedFile =
            BetaDeletedFile.builder()
                .id("file_011CNha8iCJcU1wXNR6q4V8w")
                .type(BetaDeletedFile.Type.FILE_DELETED)
                .build()

        val roundtrippedBetaDeletedFile =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedFile),
                jacksonTypeRef<BetaDeletedFile>(),
            )

        assertThat(roundtrippedBetaDeletedFile).isEqualTo(betaDeletedFile)
    }
}
