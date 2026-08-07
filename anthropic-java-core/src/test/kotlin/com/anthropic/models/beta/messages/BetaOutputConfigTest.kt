// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOutputConfigTest {

    @Test
    fun create() {
        val betaOutputConfig =
            BetaOutputConfig.builder()
                .effort(BetaOutputConfig.Effort.LOW)
                .format(
                    BetaJsonOutputFormat.of(
                        BetaJsonOutputFormat.Schema.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                )
                .taskBudget(BetaTokenTaskBudget.builder().total(1024L).remaining(0L).build())
                .build()

        assertThat(betaOutputConfig.effort()).contains(BetaOutputConfig.Effort.LOW)
        assertThat(betaOutputConfig.format())
            .contains(
                BetaJsonOutputFormat.of(
                    BetaJsonOutputFormat.Schema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
            )
        assertThat(betaOutputConfig.taskBudget())
            .contains(BetaTokenTaskBudget.builder().total(1024L).remaining(0L).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOutputConfig =
            BetaOutputConfig.builder()
                .effort(BetaOutputConfig.Effort.LOW)
                .format(
                    BetaJsonOutputFormat.of(
                        BetaJsonOutputFormat.Schema.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                )
                .taskBudget(BetaTokenTaskBudget.builder().total(1024L).remaining(0L).build())
                .build()

        val roundtrippedBetaOutputConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOutputConfig),
                jacksonTypeRef<BetaOutputConfig>(),
            )

        assertThat(roundtrippedBetaOutputConfig).isEqualTo(betaOutputConfig)
    }
}
