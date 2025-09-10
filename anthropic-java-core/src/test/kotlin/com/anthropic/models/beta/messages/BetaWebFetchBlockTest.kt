// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchBlockTest {

    @Test
    fun create() {
        val betaWebFetchBlock =
            BetaWebFetchBlock.builder()
                .content(
                    BetaDocumentBlock.builder()
                        .citations(BetaCitationConfig.builder().enabled(true).build())
                        .base64Source("U3RhaW5sZXNzIHJvY2tz")
                        .title("title")
                        .build()
                )
                .retrievedAt("retrieved_at")
                .url("url")
                .build()

        assertThat(betaWebFetchBlock.content())
            .isEqualTo(
                BetaDocumentBlock.builder()
                    .citations(BetaCitationConfig.builder().enabled(true).build())
                    .base64Source("U3RhaW5sZXNzIHJvY2tz")
                    .title("title")
                    .build()
            )
        assertThat(betaWebFetchBlock.retrievedAt()).contains("retrieved_at")
        assertThat(betaWebFetchBlock.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchBlock =
            BetaWebFetchBlock.builder()
                .content(
                    BetaDocumentBlock.builder()
                        .citations(BetaCitationConfig.builder().enabled(true).build())
                        .base64Source("U3RhaW5sZXNzIHJvY2tz")
                        .title("title")
                        .build()
                )
                .retrievedAt("retrieved_at")
                .url("url")
                .build()

        val roundtrippedBetaWebFetchBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchBlock),
                jacksonTypeRef<BetaWebFetchBlock>(),
            )

        assertThat(roundtrippedBetaWebFetchBlock).isEqualTo(betaWebFetchBlock)
    }
}
