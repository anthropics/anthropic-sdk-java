// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchBlockTest {

    @Test
    fun create() {
        val webFetchBlock =
            WebFetchBlock.builder()
                .content(
                    DocumentBlock.builder()
                        .citations(CitationsConfig.builder().enabled(true).build())
                        .base64Source("U3RhaW5sZXNzIHJvY2tz")
                        .title("title")
                        .build()
                )
                .retrievedAt("retrieved_at")
                .url("url")
                .build()

        assertThat(webFetchBlock.content())
            .isEqualTo(
                DocumentBlock.builder()
                    .citations(CitationsConfig.builder().enabled(true).build())
                    .base64Source("U3RhaW5sZXNzIHJvY2tz")
                    .title("title")
                    .build()
            )
        assertThat(webFetchBlock.retrievedAt()).contains("retrieved_at")
        assertThat(webFetchBlock.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchBlock =
            WebFetchBlock.builder()
                .content(
                    DocumentBlock.builder()
                        .citations(CitationsConfig.builder().enabled(true).build())
                        .base64Source("U3RhaW5sZXNzIHJvY2tz")
                        .title("title")
                        .build()
                )
                .retrievedAt("retrieved_at")
                .url("url")
                .build()

        val roundtrippedWebFetchBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchBlock),
                jacksonTypeRef<WebFetchBlock>(),
            )

        assertThat(roundtrippedWebFetchBlock).isEqualTo(webFetchBlock)
    }
}
