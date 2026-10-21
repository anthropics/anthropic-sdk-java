package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAssistantMessageParamTest {

    @Test
    fun create() {
        val betaAssistantMessageParam =
            BetaAssistantMessageParam.builder()
                .contentOfContentBlockParams(
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
                                        .citedText("The grass is green. The sky is blue.")
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
                .build()

        assertThat(betaAssistantMessageParam.content())
            .isEqualTo(
                BetaMessageContentParam.ofContentBlockParams(
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
                                        .citedText("The grass is green. The sky is blue.")
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
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAssistantMessageParam =
            BetaAssistantMessageParam.builder()
                .contentOfContentBlockParams(
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
                                        .citedText("The grass is green. The sky is blue.")
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
                .build()

        val roundtrippedBetaAssistantMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAssistantMessageParam),
                jacksonTypeRef<BetaAssistantMessageParam>(),
            )

        assertThat(roundtrippedBetaAssistantMessageParam).isEqualTo(betaAssistantMessageParam)
    }
}
