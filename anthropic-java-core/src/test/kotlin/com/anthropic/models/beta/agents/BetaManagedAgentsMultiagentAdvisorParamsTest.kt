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

internal class BetaManagedAgentsMultiagentAdvisorParamsTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaManagedAgentsMultiagentAdvisorEnabledParams.of("claude-opus-5-5")

        val betaManagedAgentsMultiagentAdvisorParams =
            BetaManagedAgentsMultiagentAdvisorParams.ofEnabled(enabled)

        assertThat(presentVariants(betaManagedAgentsMultiagentAdvisorParams))
            .isEqualTo(mapOf("enabled" to Optional.of(enabled)))
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentAdvisorParams =
            BetaManagedAgentsMultiagentAdvisorParams.ofEnabled(
                BetaManagedAgentsMultiagentAdvisorEnabledParams.of("claude-opus-5-5")
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

        assertThat(presentVariants(betaManagedAgentsMultiagentAdvisorParams))
            .isEqualTo(mapOf("disabled" to Optional.of(disabled)))
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

    private fun presentVariants(
        betaManagedAgentsMultiagentAdvisorParams: BetaManagedAgentsMultiagentAdvisorParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "enabled" to betaManagedAgentsMultiagentAdvisorParams.enabled(),
                "disabled" to betaManagedAgentsMultiagentAdvisorParams.disabled(),
            )
            .filterValues { it.isPresent }
}
