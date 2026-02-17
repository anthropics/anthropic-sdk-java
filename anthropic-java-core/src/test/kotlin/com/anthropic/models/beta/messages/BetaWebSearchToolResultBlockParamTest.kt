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
                .contentOfResultBlock(
                    listOf(
                        BetaWebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("title")
                            .url("url")
                            .pageAge("page_age")
                            .build()
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaWebSearchToolResultBlockParam.content())
            .isEqualTo(
                BetaWebSearchToolResultBlockParamContent.ofResultBlock(
                    listOf(
                        BetaWebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("title")
                            .url("url")
                            .pageAge("page_age")
                            .build()
                    )
                )
            )
        assertThat(betaWebSearchToolResultBlockParam.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaWebSearchToolResultBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaWebSearchToolResultBlockParam.caller())
            .contains(
                BetaWebSearchToolResultBlockParam.Caller.ofDirect(
                    BetaDirectCaller.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchToolResultBlockParam =
            BetaWebSearchToolResultBlockParam.builder()
                .contentOfResultBlock(
                    listOf(
                        BetaWebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("title")
                            .url("url")
                            .pageAge("page_age")
                            .build()
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
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
