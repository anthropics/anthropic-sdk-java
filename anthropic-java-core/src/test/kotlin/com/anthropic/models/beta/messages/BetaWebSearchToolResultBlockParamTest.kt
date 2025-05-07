// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebSearchToolResultBlockParamTest {

    @Test
    fun create() {
        val betaWebSearchToolResultBlockParam =
            BetaWebSearchToolResultBlockParam.builder()
                .contentOfItem(
                    listOf(
                        BetaWebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("x")
                            .url("x")
                            .pageAge("page_age")
                            .build()
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        assertThat(betaWebSearchToolResultBlockParam.content())
            .isEqualTo(
                BetaWebSearchToolResultBlockParamContent.ofItem(
                    listOf(
                        BetaWebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("x")
                            .url("x")
                            .pageAge("page_age")
                            .build()
                    )
                )
            )
        assertThat(betaWebSearchToolResultBlockParam.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaWebSearchToolResultBlockParam.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchToolResultBlockParam =
            BetaWebSearchToolResultBlockParam.builder()
                .contentOfItem(
                    listOf(
                        BetaWebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("x")
                            .url("x")
                            .pageAge("page_age")
                            .build()
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val roundtrippedBetaWebSearchToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchToolResultBlockParam),
                jacksonTypeRef<BetaWebSearchToolResultBlockParam>(),
            )

        assertThat(roundtrippedBetaWebSearchToolResultBlockParam)
            .isEqualTo(betaWebSearchToolResultBlockParam)
    }
}
