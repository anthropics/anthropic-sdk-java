// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebSearchResultBlockTest {

    @Test
    fun create() {
        val betaWebSearchResultBlock =
            BetaWebSearchResultBlock.builder()
                .encryptedContent("encrypted_content")
                .pageAge("page_age")
                .title("title")
                .url("url")
                .build()

        assertThat(betaWebSearchResultBlock.encryptedContent()).isEqualTo("encrypted_content")
        assertThat(betaWebSearchResultBlock.pageAge()).contains("page_age")
        assertThat(betaWebSearchResultBlock.title()).isEqualTo("title")
        assertThat(betaWebSearchResultBlock.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchResultBlock =
            BetaWebSearchResultBlock.builder()
                .encryptedContent("encrypted_content")
                .pageAge("page_age")
                .title("title")
                .url("url")
                .build()

        val roundtrippedBetaWebSearchResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchResultBlock),
                jacksonTypeRef<BetaWebSearchResultBlock>(),
            )

        assertThat(roundtrippedBetaWebSearchResultBlock).isEqualTo(betaWebSearchResultBlock)
    }
}
