// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebSearchResultBlockParamTest {

    @Test
    fun create() {
        val betaWebSearchResultBlockParam =
            BetaWebSearchResultBlockParam.builder()
                .encryptedContent("encrypted_content")
                .title("title")
                .url("url")
                .pageAge("page_age")
                .build()

        assertThat(betaWebSearchResultBlockParam.encryptedContent()).isEqualTo("encrypted_content")
        assertThat(betaWebSearchResultBlockParam.title()).isEqualTo("title")
        assertThat(betaWebSearchResultBlockParam.url()).isEqualTo("url")
        assertThat(betaWebSearchResultBlockParam.pageAge()).contains("page_age")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchResultBlockParam =
            BetaWebSearchResultBlockParam.builder()
                .encryptedContent("encrypted_content")
                .title("title")
                .url("url")
                .pageAge("page_age")
                .build()

        val roundtrippedBetaWebSearchResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchResultBlockParam),
                jacksonTypeRef<BetaWebSearchResultBlockParam>(),
            )

        assertThat(roundtrippedBetaWebSearchResultBlockParam)
            .isEqualTo(betaWebSearchResultBlockParam)
    }
}
