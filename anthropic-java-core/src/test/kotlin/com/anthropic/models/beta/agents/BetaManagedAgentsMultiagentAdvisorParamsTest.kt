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

internal class BetaManagedAgentsMultiagentAdvisorParamsTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaManagedAgentsMultiagentAdvisorEnabledParams.of("claude-fable-5")

        val betaManagedAgentsMultiagentAdvisorParams =
            BetaManagedAgentsMultiagentAdvisorParams.ofEnabled(enabled)

        assertThat(betaManagedAgentsMultiagentAdvisorParams.enabled()).contains(enabled)
        assertThat(betaManagedAgentsMultiagentAdvisorParams.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentAdvisorParams =
            BetaManagedAgentsMultiagentAdvisorParams.ofEnabled(
                BetaManagedAgentsMultiagentAdvisorEnabledParams.of("claude-fable-5")
            )

        val roundtrippedBetaManagedAgentsMultiagentAdvisorParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentAdvisorParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentAdvisorParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentAdvisorParams)
            .isEqualTo(betaManagedAgentsMultiagentAdvisorParams)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build()

        val betaManagedAgentsMultiagentAdvisorParams =
            BetaManagedAgentsMultiagentAdvisorParams.ofDisabled(disabled)

        assertThat(betaManagedAgentsMultiagentAdvisorParams.enabled()).isEmpty
        assertThat(betaManagedAgentsMultiagentAdvisorParams.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentAdvisorParams =
            BetaManagedAgentsMultiagentAdvisorParams.ofDisabled(
                BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build()
            )

        val roundtrippedBetaManagedAgentsMultiagentAdvisorParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentAdvisorParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentAdvisorParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentAdvisorParams)
            .isEqualTo(betaManagedAgentsMultiagentAdvisorParams)
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
        val betaManagedAgentsMultiagentAdvisorParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsMultiagentAdvisorParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMultiagentAdvisorParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
