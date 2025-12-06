// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolSearchToolSearchResultBlockParamTest {

    @Test
    fun create() {
        val betaToolSearchToolSearchResultBlockParam =
            BetaToolSearchToolSearchResultBlockParam.builder()
                .addToolReference(
                    BetaToolReferenceBlockParam.builder()
                        .toolName("tool_name")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(betaToolSearchToolSearchResultBlockParam.toolReferences())
            .containsExactly(
                BetaToolReferenceBlockParam.builder()
                    .toolName("tool_name")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolSearchToolSearchResultBlockParam =
            BetaToolSearchToolSearchResultBlockParam.builder()
                .addToolReference(
                    BetaToolReferenceBlockParam.builder()
                        .toolName("tool_name")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaToolSearchToolSearchResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolSearchToolSearchResultBlockParam),
                jacksonTypeRef<BetaToolSearchToolSearchResultBlockParam>(),
            )

        assertThat(roundtrippedBetaToolSearchToolSearchResultBlockParam)
            .isEqualTo(betaToolSearchToolSearchResultBlockParam)
    }
}
