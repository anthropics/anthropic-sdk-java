// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationWebSearchResultLocationParamTest {

    @Test
    fun create() {
        val betaCitationWebSearchResultLocationParam =
            BetaCitationWebSearchResultLocationParam.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("x")
                .url("x")
                .build()

        assertThat(betaCitationWebSearchResultLocationParam.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationWebSearchResultLocationParam.encryptedIndex())
            .isEqualTo("encrypted_index")
        assertThat(betaCitationWebSearchResultLocationParam.title()).contains("x")
        assertThat(betaCitationWebSearchResultLocationParam.url()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationWebSearchResultLocationParam =
            BetaCitationWebSearchResultLocationParam.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("x")
                .url("x")
                .build()

        val roundtrippedBetaCitationWebSearchResultLocationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCitationWebSearchResultLocationParam),
                jacksonTypeRef<BetaCitationWebSearchResultLocationParam>(),
            )

        assertThat(roundtrippedBetaCitationWebSearchResultLocationParam)
            .isEqualTo(betaCitationWebSearchResultLocationParam)
    }
}
