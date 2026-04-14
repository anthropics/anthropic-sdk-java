// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageParamTest {

    @Test
    fun create() {
        val betaMessageParam =
            BetaMessageParam.builder()
                .contentOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofText(
                            BetaTextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    BetaCacheControlEphemeral.builder()
                                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    BetaCitationCharLocationParam.builder()
                                        .citedText("cited_text")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                )
                .role(BetaMessageParam.Role.USER)
                .build()

        assertThat(betaMessageParam.content())
            .isEqualTo(
                BetaMessageParam.Content.ofBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofText(
                            BetaTextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    BetaCacheControlEphemeral.builder()
                                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    BetaCitationCharLocationParam.builder()
                                        .citedText("cited_text")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                )
            )
        assertThat(betaMessageParam.role()).isEqualTo(BetaMessageParam.Role.USER)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageParam =
            BetaMessageParam.builder()
                .contentOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofText(
                            BetaTextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    BetaCacheControlEphemeral.builder()
                                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    BetaCitationCharLocationParam.builder()
                                        .citedText("cited_text")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                )
                .role(BetaMessageParam.Role.USER)
                .build()

        val roundtrippedBetaMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageParam),
                jacksonTypeRef<BetaMessageParam>(),
            )

        assertThat(roundtrippedBetaMessageParam).isEqualTo(betaMessageParam)
    }
}
