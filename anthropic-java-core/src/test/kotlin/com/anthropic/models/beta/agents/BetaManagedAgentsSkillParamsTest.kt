// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsSkillParamsTest {

    @Test
    fun ofAnthropic() {
        val anthropic =
            BetaManagedAgentsAnthropicSkillParams.builder()
                .skillId("xlsx")
                .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                .version("1")
                .build()

        val betaManagedAgentsSkillParams = BetaManagedAgentsSkillParams.ofAnthropic(anthropic)

        assertThat(betaManagedAgentsSkillParams.anthropic()).contains(anthropic)
        assertThat(betaManagedAgentsSkillParams.custom()).isEmpty
    }

    @Test
    fun ofAnthropicRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSkillParams =
            BetaManagedAgentsSkillParams.ofAnthropic(
                BetaManagedAgentsAnthropicSkillParams.builder()
                    .skillId("xlsx")
                    .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                    .version("1")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSkillParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSkillParams),
                jacksonTypeRef<BetaManagedAgentsSkillParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSkillParams).isEqualTo(betaManagedAgentsSkillParams)
    }

    @Test
    fun ofCustom() {
        val custom =
            BetaManagedAgentsCustomSkillParams.builder()
                .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                .type(BetaManagedAgentsCustomSkillParams.Type.CUSTOM)
                .version("2")
                .build()

        val betaManagedAgentsSkillParams = BetaManagedAgentsSkillParams.ofCustom(custom)

        assertThat(betaManagedAgentsSkillParams.anthropic()).isEmpty
        assertThat(betaManagedAgentsSkillParams.custom()).contains(custom)
    }

    @Test
    fun ofCustomRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSkillParams =
            BetaManagedAgentsSkillParams.ofCustom(
                BetaManagedAgentsCustomSkillParams.builder()
                    .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                    .type(BetaManagedAgentsCustomSkillParams.Type.CUSTOM)
                    .version("2")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSkillParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSkillParams),
                jacksonTypeRef<BetaManagedAgentsSkillParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSkillParams).isEqualTo(betaManagedAgentsSkillParams)
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
        val betaManagedAgentsSkillParams =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsSkillParams>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaManagedAgentsSkillParams.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
