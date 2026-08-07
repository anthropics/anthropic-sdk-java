// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDocumentBlockTest {

    @Test
    fun create() {
        val betaDocumentBlock =
            BetaDocumentBlock.builder()
                .citations(BetaCitationConfig.of(true))
                .base64Source("U3RhaW5sZXNzIHJvY2tz")
                .title("title")
                .build()

        assertThat(betaDocumentBlock.citations()).contains(BetaCitationConfig.of(true))
        assertThat(betaDocumentBlock.source())
            .isEqualTo(BetaDocumentBlock.Source.ofBase64("U3RhaW5sZXNzIHJvY2tz"))
        assertThat(betaDocumentBlock.title()).contains("title")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDocumentBlock =
            BetaDocumentBlock.builder()
                .citations(BetaCitationConfig.of(true))
                .base64Source("U3RhaW5sZXNzIHJvY2tz")
                .title("title")
                .build()

        val roundtrippedBetaDocumentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDocumentBlock),
                jacksonTypeRef<BetaDocumentBlock>(),
            )

        assertThat(roundtrippedBetaDocumentBlock).isEqualTo(betaDocumentBlock)
    }
}
