package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSystemMessageParamTest {

    @Test
    fun create() {
        val betaSystemMessageParam =
            BetaSystemMessageParam.builder()
                .clearAt(BetaSystemMessageParam.ClearAt.NEXT_USER_MESSAGE)
                .content("string")
                .outputConfig(
                    BetaSystemMessageOutputConfig.builder()
                        .effort(BetaSystemMessageOutputConfig.Effort.LOW)
                        .build()
                )
                .build()

        assertThat(betaSystemMessageParam.clearAt())
            .contains(BetaSystemMessageParam.ClearAt.NEXT_USER_MESSAGE)
        assertThat(betaSystemMessageParam.content())
            .contains(BetaSystemMessageParam.Content.ofString("string"))
        assertThat(betaSystemMessageParam.outputConfig())
            .contains(
                BetaSystemMessageOutputConfig.builder()
                    .effort(BetaSystemMessageOutputConfig.Effort.LOW)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSystemMessageParam =
            BetaSystemMessageParam.builder()
                .clearAt(BetaSystemMessageParam.ClearAt.NEXT_USER_MESSAGE)
                .content("string")
                .outputConfig(
                    BetaSystemMessageOutputConfig.builder()
                        .effort(BetaSystemMessageOutputConfig.Effort.LOW)
                        .build()
                )
                .build()

        val roundtrippedBetaSystemMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSystemMessageParam),
                jacksonTypeRef<BetaSystemMessageParam>(),
            )

        assertThat(roundtrippedBetaSystemMessageParam).isEqualTo(betaSystemMessageParam)
    }
}
