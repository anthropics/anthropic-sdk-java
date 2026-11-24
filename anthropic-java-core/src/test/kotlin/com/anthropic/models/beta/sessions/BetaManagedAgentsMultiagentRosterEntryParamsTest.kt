package com.anthropic.models.beta.sessions

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentSelfParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsMultiagentRosterEntryParamsTest {

    @Test
    fun ofString() {
        val string = "string"

        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofString(string)

        assertThat(presentVariants(betaManagedAgentsMultiagentRosterEntryParams))
            .isEqualTo(mapOf("string" to Optional.of(string)))
    }

    @Test
    fun ofStringRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofString("string")

        val roundtrippedBetaManagedAgentsMultiagentRosterEntryParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentRosterEntryParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentRosterEntryParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentRosterEntryParams)
            .isEqualTo(betaManagedAgentsMultiagentRosterEntryParams)
    }

    @Test
    fun ofAgent() {
        val agent = BetaManagedAgentsAgentParams.builder().id("x").version(0).build()

        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofAgent(agent)

        assertThat(presentVariants(betaManagedAgentsMultiagentRosterEntryParams))
            .isEqualTo(mapOf("agent" to Optional.of(agent)))
    }

    @Test
    fun ofAgentRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofAgent(
                BetaManagedAgentsAgentParams.builder().id("x").version(0).build()
            )

        val roundtrippedBetaManagedAgentsMultiagentRosterEntryParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentRosterEntryParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentRosterEntryParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentRosterEntryParams)
            .isEqualTo(betaManagedAgentsMultiagentRosterEntryParams)
    }

    @Test
    fun ofSelf() {
        val self = BetaManagedAgentsMultiagentSelfParams.builder().build()

        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofSelf(self)

        assertThat(presentVariants(betaManagedAgentsMultiagentRosterEntryParams))
            .isEqualTo(mapOf("self" to Optional.of(self)))
    }

    @Test
    fun ofSelfRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofSelf(
                BetaManagedAgentsMultiagentSelfParams.builder().build()
            )

        val roundtrippedBetaManagedAgentsMultiagentRosterEntryParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentRosterEntryParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentRosterEntryParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentRosterEntryParams)
            .isEqualTo(betaManagedAgentsMultiagentRosterEntryParams)
    }

    @Test
    fun ofAdvisor() {
        val advisor = BetaManagedAgentsAdvisorParams.of("claude-opus-5-5")

        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofAdvisor(advisor)

        assertThat(presentVariants(betaManagedAgentsMultiagentRosterEntryParams))
            .isEqualTo(mapOf("advisor" to Optional.of(advisor)))
    }

    @Test
    fun ofAdvisorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofAdvisor(
                BetaManagedAgentsAdvisorParams.of("claude-opus-5-5")
            )

        val roundtrippedBetaManagedAgentsMultiagentRosterEntryParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentRosterEntryParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentRosterEntryParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentRosterEntryParams)
            .isEqualTo(betaManagedAgentsMultiagentRosterEntryParams)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaManagedAgentsMultiagentRosterEntryParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsMultiagentRosterEntryParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMultiagentRosterEntryParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaManagedAgentsMultiagentRosterEntryParams: BetaManagedAgentsMultiagentRosterEntryParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "string" to betaManagedAgentsMultiagentRosterEntryParams.string(),
                "agent" to betaManagedAgentsMultiagentRosterEntryParams.agent(),
                "self" to betaManagedAgentsMultiagentRosterEntryParams.self(),
                "advisor" to betaManagedAgentsMultiagentRosterEntryParams.advisor(),
            )
            .filterValues { it.isPresent }
}
