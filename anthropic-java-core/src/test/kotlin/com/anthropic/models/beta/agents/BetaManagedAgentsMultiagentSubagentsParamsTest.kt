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

internal class BetaManagedAgentsMultiagentSubagentsParamsTest {

    @Test
    fun ofEnabled() {
        val enabled =
            BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                .inlineAgents(
                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                )
                .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                .addPredefinedAgent(BetaManagedAgentsMultiagentSelfParams.builder().build())
                .build()

        val betaManagedAgentsMultiagentSubagentsParams =
            BetaManagedAgentsMultiagentSubagentsParams.ofEnabled(enabled)

        assertThat(presentVariants(betaManagedAgentsMultiagentSubagentsParams))
            .isEqualTo(mapOf("enabled" to Optional.of(enabled)))
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentSubagentsParams =
            BetaManagedAgentsMultiagentSubagentsParams.ofEnabled(
                BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                    .inlineAgents(
                        BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                    )
                    .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                    .addPredefinedAgent(BetaManagedAgentsMultiagentSelfParams.builder().build())
                    .build()
            )

        val roundtrippedBetaManagedAgentsMultiagentSubagentsParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentSubagentsParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentSubagentsParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentSubagentsParams)
            .isEqualTo(betaManagedAgentsMultiagentSubagentsParams)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaManagedAgentsMultiagentSubagentsDisabledParams.builder().build()

        val betaManagedAgentsMultiagentSubagentsParams =
            BetaManagedAgentsMultiagentSubagentsParams.ofDisabled(disabled)

        assertThat(presentVariants(betaManagedAgentsMultiagentSubagentsParams))
            .isEqualTo(mapOf("disabled" to Optional.of(disabled)))
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentSubagentsParams =
            BetaManagedAgentsMultiagentSubagentsParams.ofDisabled(
                BetaManagedAgentsMultiagentSubagentsDisabledParams.builder().build()
            )

        val roundtrippedBetaManagedAgentsMultiagentSubagentsParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentSubagentsParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentSubagentsParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentSubagentsParams)
            .isEqualTo(betaManagedAgentsMultiagentSubagentsParams)
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
        val betaManagedAgentsMultiagentSubagentsParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsMultiagentSubagentsParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMultiagentSubagentsParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaManagedAgentsMultiagentSubagentsParams: BetaManagedAgentsMultiagentSubagentsParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "enabled" to betaManagedAgentsMultiagentSubagentsParams.enabled(),
                "disabled" to betaManagedAgentsMultiagentSubagentsParams.disabled(),
            )
            .filterValues { it.isPresent }
}
