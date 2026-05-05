// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentSelfParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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

        assertThat(betaManagedAgentsMultiagentRosterEntryParams.string()).contains(string)
        assertThat(betaManagedAgentsMultiagentRosterEntryParams.agent()).isEmpty
        assertThat(betaManagedAgentsMultiagentRosterEntryParams.self()).isEmpty
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
        val agent =
            BetaManagedAgentsAgentParams.builder()
                .id("x")
                .type(BetaManagedAgentsAgentParams.Type.AGENT)
                .version(0)
                .build()

        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofAgent(agent)

        assertThat(betaManagedAgentsMultiagentRosterEntryParams.string()).isEmpty
        assertThat(betaManagedAgentsMultiagentRosterEntryParams.agent()).contains(agent)
        assertThat(betaManagedAgentsMultiagentRosterEntryParams.self()).isEmpty
    }

    @Test
    fun ofAgentRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofAgent(
                BetaManagedAgentsAgentParams.builder()
                    .id("x")
                    .type(BetaManagedAgentsAgentParams.Type.AGENT)
                    .version(0)
                    .build()
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
        val self =
            BetaManagedAgentsMultiagentSelfParams.builder()
                .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                .build()

        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofSelf(self)

        assertThat(betaManagedAgentsMultiagentRosterEntryParams.string()).isEmpty
        assertThat(betaManagedAgentsMultiagentRosterEntryParams.agent()).isEmpty
        assertThat(betaManagedAgentsMultiagentRosterEntryParams.self()).contains(self)
    }

    @Test
    fun ofSelfRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentRosterEntryParams =
            BetaManagedAgentsMultiagentRosterEntryParams.ofSelf(
                BetaManagedAgentsMultiagentSelfParams.builder()
                    .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                    .build()
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
}
