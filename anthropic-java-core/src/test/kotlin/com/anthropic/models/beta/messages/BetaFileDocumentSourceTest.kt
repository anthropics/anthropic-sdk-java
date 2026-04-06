// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFileDocumentSourceTest {

    @Test
    fun create() {
        val betaFileDocumentSource = BetaFileDocumentSource.builder().fileId("file_id").build()

        assertThat(betaFileDocumentSource.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFileDocumentSource = BetaFileDocumentSource.builder().fileId("file_id").build()

        val roundtrippedBetaFileDocumentSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFileDocumentSource),
                jacksonTypeRef<BetaFileDocumentSource>(),
            )

        assertThat(roundtrippedBetaFileDocumentSource).isEqualTo(betaFileDocumentSource)
    }
}
