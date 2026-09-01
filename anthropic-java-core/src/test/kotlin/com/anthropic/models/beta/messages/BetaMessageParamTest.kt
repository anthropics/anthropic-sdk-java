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
                .role(BetaMessageParam.Role.USER)
                .clearAt(BetaMessageParam.ClearAt.NEXT_USER_MESSAGE)
                .outputConfig(
                    BetaSystemMessageOutputConfig.builder()
                        .effort(BetaSystemMessageOutputConfig.Effort.LOW)
                        .build()
                )
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
        assertThat(betaMessageParam.role()).isEqualTo(BetaMessageParam.Role.USER)
        assertThat(betaMessageParam.clearAt()).contains(BetaMessageParam.ClearAt.NEXT_USER_MESSAGE)
        assertThat(betaMessageParam.outputConfig())
            .contains(
                BetaSystemMessageOutputConfig.builder()
                    .effort(BetaSystemMessageOutputConfig.Effort.LOW)
                    .build()
            )
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
                .role(BetaMessageParam.Role.USER)
                .clearAt(BetaMessageParam.ClearAt.NEXT_USER_MESSAGE)
                .outputConfig(
                    BetaSystemMessageOutputConfig.builder()
                        .effort(BetaSystemMessageOutputConfig.Effort.LOW)
                        .build()
                )
                .build()

        val roundtrippedBetaMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageParam),
                jacksonTypeRef<BetaMessageParam>(),
            )

        assertThat(roundtrippedBetaMessageParam).isEqualTo(betaMessageParam)
    }
}
