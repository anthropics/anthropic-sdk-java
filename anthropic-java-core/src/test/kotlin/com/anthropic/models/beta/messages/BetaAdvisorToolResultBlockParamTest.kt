// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAdvisorToolResultBlockParamTest {

    @Test
    fun create() {
        val betaAdvisorToolResultBlockParam =
            BetaAdvisorToolResultBlockParam.builder()
                .content(
                    BetaAdvisorToolResultErrorParam.builder()
                        .errorCode(BetaAdvisorToolResultErrorParam.ErrorCode.MAX_USES_EXCEEDED)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaAdvisorToolResultBlockParam.content())
            .isEqualTo(
                BetaAdvisorToolResultBlockParam.Content.ofBetaAdvisorToolResultErrorParam(
                    BetaAdvisorToolResultErrorParam.builder()
                        .errorCode(BetaAdvisorToolResultErrorParam.ErrorCode.MAX_USES_EXCEEDED)
                        .build()
                )
            )
        assertThat(betaAdvisorToolResultBlockParam.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaAdvisorToolResultBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAdvisorToolResultBlockParam =
            BetaAdvisorToolResultBlockParam.builder()
                .content(
                    BetaAdvisorToolResultErrorParam.builder()
                        .errorCode(BetaAdvisorToolResultErrorParam.ErrorCode.MAX_USES_EXCEEDED)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaAdvisorToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAdvisorToolResultBlockParam),
                jacksonTypeRef<BetaAdvisorToolResultBlockParam>(),
            )

        assertThat(roundtrippedBetaAdvisorToolResultBlockParam)
            .isEqualTo(betaAdvisorToolResultBlockParam)
    }
}
