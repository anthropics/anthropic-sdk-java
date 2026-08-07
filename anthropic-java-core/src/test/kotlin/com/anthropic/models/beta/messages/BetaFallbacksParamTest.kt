// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaFallbacksParamTest {

    @Test
    fun ofFallbackParams() {
        val fallbackParams =
            listOf(
                BetaFallbackParam.builder()
                    .model(Model.CLAUDE_SONNET_5)
                    .maxTokens(0L)
                    .outputConfig(
                        BetaOutputConfig.builder()
                            .effort(BetaOutputConfig.Effort.LOW)
                            .format(
                                BetaJsonOutputFormat.of(
                                    BetaJsonOutputFormat.Schema.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
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
            )

        val betaFallbacksParam = BetaFallbacksParam.ofFallbackParams(fallbackParams)

        assertThat(betaFallbacksParam.fallbackParams()).contains(fallbackParams)
        assertThat(betaFallbacksParam.default_()).isEmpty
    }

    @Test
    fun ofFallbackParamsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbacksParam =
            BetaFallbacksParam.ofFallbackParams(
                listOf(
                    BetaFallbackParam.builder()
                        .model(Model.CLAUDE_SONNET_5)
                        .maxTokens(0L)
                        .outputConfig(
                            BetaOutputConfig.builder()
                                .effort(BetaOutputConfig.Effort.LOW)
                                .format(
                                    BetaJsonOutputFormat.of(
                                        BetaJsonOutputFormat.Schema.builder()
                                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                                            .build()
                                    )
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
                )
            )

        val roundtrippedBetaFallbacksParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbacksParam),
                jacksonTypeRef<BetaFallbacksParam>(),
            )

        assertThat(roundtrippedBetaFallbacksParam).isEqualTo(betaFallbacksParam)
    }

    @Test
    fun ofDefault() {
        val betaFallbacksParam = BetaFallbacksParam.ofDefault()

        assertThat(betaFallbacksParam.fallbackParams()).isEmpty
        assertThat(betaFallbacksParam.default_()).contains(JsonValue.from("default"))
    }

    @Test
    fun ofDefaultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbacksParam = BetaFallbacksParam.ofDefault()

        val roundtrippedBetaFallbacksParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbacksParam),
                jacksonTypeRef<BetaFallbacksParam>(),
            )

        assertThat(roundtrippedBetaFallbacksParam).isEqualTo(betaFallbacksParam)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        OBJECT(JsonValue.from(mapOf("invalid" to "object"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaFallbacksParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaFallbacksParam>())

        val e = assertThrows<AnthropicInvalidDataException> { betaFallbacksParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
