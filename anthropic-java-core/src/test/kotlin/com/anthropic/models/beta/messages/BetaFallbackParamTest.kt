// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFallbackParamTest {

    @Test
    fun create() {
        val betaFallbackParam =
            BetaFallbackParam.builder()
                .model(Model.CLAUDE_FABLE_5)
                .maxTokens(0L)
                .outputConfig(
                    BetaOutputConfig.builder()
                        .effort(BetaOutputConfig.Effort.LOW)
                        .format(
                            BetaJsonOutputFormat.builder()
                                .schema(
                                    BetaJsonOutputFormat.Schema.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
                                .build()
                        )
                        .taskBudget(
                            BetaTokenTaskBudget.builder().total(1024L).remaining(0L).build()
                        )
                        .build()
                )
                .speed(BetaFallbackParam.Speed.STANDARD)
                .thinking(
                    BetaThinkingConfigEnabled.builder()
                        .budgetTokens(1024L)
                        .display(BetaThinkingConfigEnabled.Display.SUMMARIZED)
                        .build()
                )
                .build()

        assertThat(betaFallbackParam.model()).isEqualTo(Model.CLAUDE_FABLE_5)
        assertThat(betaFallbackParam.maxTokens()).contains(0L)
        assertThat(betaFallbackParam.outputConfig())
            .contains(
                BetaOutputConfig.builder()
                    .effort(BetaOutputConfig.Effort.LOW)
                    .format(
                        BetaJsonOutputFormat.builder()
                            .schema(
                                BetaJsonOutputFormat.Schema.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .build()
                    )
                    .taskBudget(BetaTokenTaskBudget.builder().total(1024L).remaining(0L).build())
                    .build()
            )
        assertThat(betaFallbackParam.speed()).contains(BetaFallbackParam.Speed.STANDARD)
        assertThat(betaFallbackParam.thinking())
            .contains(
                BetaFallbackParam.Thinking.ofEnabled(
                    BetaThinkingConfigEnabled.builder()
                        .budgetTokens(1024L)
                        .display(BetaThinkingConfigEnabled.Display.SUMMARIZED)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackParam =
            BetaFallbackParam.builder()
                .model(Model.CLAUDE_FABLE_5)
                .maxTokens(0L)
                .outputConfig(
                    BetaOutputConfig.builder()
                        .effort(BetaOutputConfig.Effort.LOW)
                        .format(
                            BetaJsonOutputFormat.builder()
                                .schema(
                                    BetaJsonOutputFormat.Schema.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
                                .build()
                        )
                        .taskBudget(
                            BetaTokenTaskBudget.builder().total(1024L).remaining(0L).build()
                        )
                        .build()
                )
                .speed(BetaFallbackParam.Speed.STANDARD)
                .thinking(
                    BetaThinkingConfigEnabled.builder()
                        .budgetTokens(1024L)
                        .display(BetaThinkingConfigEnabled.Display.SUMMARIZED)
                        .build()
                )
                .build()

        val roundtrippedBetaFallbackParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackParam),
                jacksonTypeRef<BetaFallbackParam>(),
            )

        assertThat(roundtrippedBetaFallbackParam).isEqualTo(betaFallbackParam)
    }
}
