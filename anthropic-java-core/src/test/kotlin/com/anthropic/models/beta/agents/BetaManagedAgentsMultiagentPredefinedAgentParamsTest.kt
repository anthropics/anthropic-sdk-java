package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsMultiagentPredefinedAgentParamsTest {

    @Test
    fun ofString() {
        val string = "string"

        val betaManagedAgentsMultiagentPredefinedAgentParams =
            BetaManagedAgentsMultiagentPredefinedAgentParams.ofString(string)

        assertThat(betaManagedAgentsMultiagentPredefinedAgentParams.string()).contains(string)
        assertThat(betaManagedAgentsMultiagentPredefinedAgentParams.betaManagedAgentsAgentParams())
            .isEmpty
        assertThat(betaManagedAgentsMultiagentPredefinedAgentParams.self()).isEmpty
    }

    @Test
    fun ofStringRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentPredefinedAgentParams =
            BetaManagedAgentsMultiagentPredefinedAgentParams.ofString("string")

        val roundtrippedBetaManagedAgentsMultiagentPredefinedAgentParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentPredefinedAgentParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentPredefinedAgentParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentPredefinedAgentParams)
            .isEqualTo(betaManagedAgentsMultiagentPredefinedAgentParams)
    }

    @Test
    fun ofBetaManagedAgentsAgentParams() {
        val betaManagedAgentsAgentParams =
            BetaManagedAgentsAgentParams.builder()
                .id("x")
                .type(BetaManagedAgentsAgentParams.Type.AGENT)
                .version(0)
                .build()

        val betaManagedAgentsMultiagentPredefinedAgentParams =
            BetaManagedAgentsMultiagentPredefinedAgentParams.ofBetaManagedAgentsAgentParams(
                betaManagedAgentsAgentParams
            )

        assertThat(betaManagedAgentsMultiagentPredefinedAgentParams.string()).isEmpty
        assertThat(betaManagedAgentsMultiagentPredefinedAgentParams.betaManagedAgentsAgentParams())
            .contains(betaManagedAgentsAgentParams)
        assertThat(betaManagedAgentsMultiagentPredefinedAgentParams.self()).isEmpty
    }

    @Test
    fun ofBetaManagedAgentsAgentParamsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentPredefinedAgentParams =
            BetaManagedAgentsMultiagentPredefinedAgentParams.ofBetaManagedAgentsAgentParams(
                BetaManagedAgentsAgentParams.builder()
                    .id("x")
                    .type(BetaManagedAgentsAgentParams.Type.AGENT)
                    .version(0)
                    .build()
            )

        val roundtrippedBetaManagedAgentsMultiagentPredefinedAgentParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentPredefinedAgentParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentPredefinedAgentParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentPredefinedAgentParams)
            .isEqualTo(betaManagedAgentsMultiagentPredefinedAgentParams)
    }

    @Test
    fun ofSelf() {
        val self =
            BetaManagedAgentsMultiagentSelfParams.of(
                BetaManagedAgentsMultiagentSelfParams.Type.SELF
            )

        val betaManagedAgentsMultiagentPredefinedAgentParams =
            BetaManagedAgentsMultiagentPredefinedAgentParams.ofSelf(self)

        assertThat(betaManagedAgentsMultiagentPredefinedAgentParams.string()).isEmpty
        assertThat(betaManagedAgentsMultiagentPredefinedAgentParams.betaManagedAgentsAgentParams())
            .isEmpty
        assertThat(betaManagedAgentsMultiagentPredefinedAgentParams.self()).contains(self)
    }

    @Test
    fun ofSelfRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentPredefinedAgentParams =
            BetaManagedAgentsMultiagentPredefinedAgentParams.ofSelf(
                BetaManagedAgentsMultiagentSelfParams.of(
                    BetaManagedAgentsMultiagentSelfParams.Type.SELF
                )
            )

        val roundtrippedBetaManagedAgentsMultiagentPredefinedAgentParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentPredefinedAgentParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentPredefinedAgentParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentPredefinedAgentParams)
            .isEqualTo(betaManagedAgentsMultiagentPredefinedAgentParams)
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
        val betaManagedAgentsMultiagentPredefinedAgentParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsMultiagentPredefinedAgentParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMultiagentPredefinedAgentParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
