// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMidConversationSystemBlockParamTest {

    @Test
    fun create() {
        val betaMidConversationSystemBlockParam =
            BetaMidConversationSystemBlockParam.builder()
                .addContent(
                    BetaTextBlockParam.builder()
                        .text("x")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .addCitation(
                            BetaCitationCharLocationParam.builder()
                                .citedText("The grass is green. The sky is blue.")
                                .documentIndex(0L)
                                .documentTitle("x")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .build()
                )
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaMidConversationSystemBlockParam.content())
            .containsExactly(
                BetaTextBlockParam.builder()
                    .text("x")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .addCitation(
                        BetaCitationCharLocationParam.builder()
                            .citedText("The grass is green. The sky is blue.")
                            .documentIndex(0L)
                            .documentTitle("x")
                            .endCharIndex(0L)
                            .startCharIndex(0L)
                            .build()
                    )
                    .build()
            )
        assertThat(betaMidConversationSystemBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMidConversationSystemBlockParam =
            BetaMidConversationSystemBlockParam.builder()
                .addContent(
                    BetaTextBlockParam.builder()
                        .text("x")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .addCitation(
                            BetaCitationCharLocationParam.builder()
                                .citedText("The grass is green. The sky is blue.")
                                .documentIndex(0L)
                                .documentTitle("x")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .build()
                )
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaMidConversationSystemBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMidConversationSystemBlockParam),
                jacksonTypeRef<BetaMidConversationSystemBlockParam>(),
            )

        assertThat(roundtrippedBetaMidConversationSystemBlockParam)
            .isEqualTo(betaMidConversationSystemBlockParam)
    }
}
