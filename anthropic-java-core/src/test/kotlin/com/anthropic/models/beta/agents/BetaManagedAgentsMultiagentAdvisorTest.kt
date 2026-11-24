package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsMultiagentAdvisorTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaManagedAgentsMultiagentAdvisorEnabled.of("claude-opus-5-5")

        val betaManagedAgentsMultiagentAdvisor =
            BetaManagedAgentsMultiagentAdvisor.ofEnabled(enabled)

        assertThat(presentVariants(betaManagedAgentsMultiagentAdvisor))
            .isEqualTo(mapOf("enabled" to Optional.of(enabled)))
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentAdvisor =
            BetaManagedAgentsMultiagentAdvisor.ofEnabled(
                BetaManagedAgentsMultiagentAdvisorEnabled.of("claude-opus-5-5")
            )

        val roundtrippedBetaManagedAgentsMultiagentAdvisor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentAdvisor),
                jacksonTypeRef<BetaManagedAgentsMultiagentAdvisor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentAdvisor)
            .isEqualTo(betaManagedAgentsMultiagentAdvisor)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaManagedAgentsMultiagentAdvisorDisabled.builder().build()

        val betaManagedAgentsMultiagentAdvisor =
            BetaManagedAgentsMultiagentAdvisor.ofDisabled(disabled)

        assertThat(presentVariants(betaManagedAgentsMultiagentAdvisor))
            .isEqualTo(mapOf("disabled" to Optional.of(disabled)))
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentAdvisor =
            BetaManagedAgentsMultiagentAdvisor.ofDisabled(
                BetaManagedAgentsMultiagentAdvisorDisabled.builder().build()
            )

        val roundtrippedBetaManagedAgentsMultiagentAdvisor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentAdvisor),
                jacksonTypeRef<BetaManagedAgentsMultiagentAdvisor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentAdvisor)
            .isEqualTo(betaManagedAgentsMultiagentAdvisor)
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
        val betaManagedAgentsMultiagentAdvisor =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsMultiagentAdvisor>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMultiagentAdvisor.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaManagedAgentsMultiagentAdvisor: BetaManagedAgentsMultiagentAdvisor
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "enabled" to betaManagedAgentsMultiagentAdvisor.enabled(),
                "disabled" to betaManagedAgentsMultiagentAdvisor.disabled(),
            )
            .filterValues { it.isPresent }
}
