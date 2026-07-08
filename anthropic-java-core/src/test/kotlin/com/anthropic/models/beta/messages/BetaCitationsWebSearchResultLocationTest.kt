// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationsWebSearchResultLocationTest {

    @Test
    fun create() {
        val betaCitationsWebSearchResultLocation =
            BetaCitationsWebSearchResultLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .encryptedIndex("encrypted_index")
                .title("title")
                .url("url")
                .build()

        assertThat(betaCitationsWebSearchResultLocation.citedText())
            .isEqualTo("The grass is green. The sky is blue.")
        assertThat(betaCitationsWebSearchResultLocation.encryptedIndex())
            .isEqualTo("encrypted_index")
        assertThat(betaCitationsWebSearchResultLocation.title()).contains("title")
        assertThat(betaCitationsWebSearchResultLocation.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationsWebSearchResultLocation =
            BetaCitationsWebSearchResultLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .encryptedIndex("encrypted_index")
                .title("title")
                .url("url")
                .build()

        val roundtrippedBetaCitationsWebSearchResultLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCitationsWebSearchResultLocation),
                jacksonTypeRef<BetaCitationsWebSearchResultLocation>(),
            )

        assertThat(roundtrippedBetaCitationsWebSearchResultLocation)
            .isEqualTo(betaCitationsWebSearchResultLocation)
    }
}
