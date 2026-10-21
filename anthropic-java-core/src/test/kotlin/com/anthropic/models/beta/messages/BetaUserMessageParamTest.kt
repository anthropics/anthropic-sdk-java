package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUserMessageParamTest {

    @Test
    fun create() {
        val betaUserMessageParam =
            BetaUserMessageParam.builder()
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

        assertThat(betaUserMessageParam.content())
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
        val betaUserMessageParam =
            BetaUserMessageParam.builder()
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

        val roundtrippedBetaUserMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUserMessageParam),
                jacksonTypeRef<BetaUserMessageParam>(),
            )

        assertThat(roundtrippedBetaUserMessageParam).isEqualTo(betaUserMessageParam)
    }
}
