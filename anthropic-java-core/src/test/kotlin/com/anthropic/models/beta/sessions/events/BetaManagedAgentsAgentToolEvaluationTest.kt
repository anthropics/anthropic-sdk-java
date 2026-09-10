package com.anthropic.models.beta.sessions.events

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsAgentToolEvaluationTest {

    @Test
    fun ofAlwaysAllow() {
        val alwaysAllow = BetaManagedAgentsAgentToolEvaluationAlwaysAllow.builder().build()

        val betaManagedAgentsAgentToolEvaluation =
            BetaManagedAgentsAgentToolEvaluation.ofAlwaysAllow(alwaysAllow)

        assertThat(betaManagedAgentsAgentToolEvaluation.alwaysAllow()).contains(alwaysAllow)
        assertThat(betaManagedAgentsAgentToolEvaluation.alwaysAsk()).isEmpty
        assertThat(betaManagedAgentsAgentToolEvaluation.auto()).isEmpty
    }

    @Test
    fun ofAlwaysAllowRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolEvaluation =
            BetaManagedAgentsAgentToolEvaluation.ofAlwaysAllow(
                BetaManagedAgentsAgentToolEvaluationAlwaysAllow.builder().build()
            )

        val roundtrippedBetaManagedAgentsAgentToolEvaluation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolEvaluation),
                jacksonTypeRef<BetaManagedAgentsAgentToolEvaluation>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolEvaluation)
            .isEqualTo(betaManagedAgentsAgentToolEvaluation)
    }

    @Test
    fun ofAlwaysAsk() {
        val alwaysAsk = BetaManagedAgentsAgentToolEvaluationAlwaysAsk.builder().build()

        val betaManagedAgentsAgentToolEvaluation =
            BetaManagedAgentsAgentToolEvaluation.ofAlwaysAsk(alwaysAsk)

        assertThat(betaManagedAgentsAgentToolEvaluation.alwaysAllow()).isEmpty
        assertThat(betaManagedAgentsAgentToolEvaluation.alwaysAsk()).contains(alwaysAsk)
        assertThat(betaManagedAgentsAgentToolEvaluation.auto()).isEmpty
    }

    @Test
    fun ofAlwaysAskRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolEvaluation =
            BetaManagedAgentsAgentToolEvaluation.ofAlwaysAsk(
                BetaManagedAgentsAgentToolEvaluationAlwaysAsk.builder().build()
            )

        val roundtrippedBetaManagedAgentsAgentToolEvaluation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolEvaluation),
                jacksonTypeRef<BetaManagedAgentsAgentToolEvaluation>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolEvaluation)
            .isEqualTo(betaManagedAgentsAgentToolEvaluation)
    }

    @Test
    fun ofAuto() {
        val auto =
            BetaManagedAgentsAgentToolEvaluationAuto.builder()
                .evaluatedPermission(
                    BetaManagedAgentsAgentAutoEvaluatedPermissionAllow.builder().build()
                )
                .build()

        val betaManagedAgentsAgentToolEvaluation = BetaManagedAgentsAgentToolEvaluation.ofAuto(auto)

        assertThat(betaManagedAgentsAgentToolEvaluation.alwaysAllow()).isEmpty
        assertThat(betaManagedAgentsAgentToolEvaluation.alwaysAsk()).isEmpty
        assertThat(betaManagedAgentsAgentToolEvaluation.auto()).contains(auto)
    }

    @Test
    fun ofAutoRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolEvaluation =
            BetaManagedAgentsAgentToolEvaluation.ofAuto(
                BetaManagedAgentsAgentToolEvaluationAuto.builder()
                    .evaluatedPermission(
                        BetaManagedAgentsAgentAutoEvaluatedPermissionAllow.builder().build()
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolEvaluation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolEvaluation),
                jacksonTypeRef<BetaManagedAgentsAgentToolEvaluation>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolEvaluation)
            .isEqualTo(betaManagedAgentsAgentToolEvaluation)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaManagedAgentsAgentToolEvaluation =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsAgentToolEvaluation>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsAgentToolEvaluation.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
