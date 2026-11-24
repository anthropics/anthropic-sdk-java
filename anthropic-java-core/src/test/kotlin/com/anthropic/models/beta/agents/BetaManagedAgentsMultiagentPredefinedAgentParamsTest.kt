package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
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

        assertThat(presentVariants(betaManagedAgentsMultiagentPredefinedAgentParams))
            .isEqualTo(mapOf("string" to Optional.of(string)))
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
            BetaManagedAgentsAgentParams.builder().id("x").version(0).build()

        val betaManagedAgentsMultiagentPredefinedAgentParams =
            BetaManagedAgentsMultiagentPredefinedAgentParams.ofBetaManagedAgentsAgentParams(
                betaManagedAgentsAgentParams
            )

        assertThat(presentVariants(betaManagedAgentsMultiagentPredefinedAgentParams))
            .isEqualTo(
                mapOf("betaManagedAgentsAgentParams" to Optional.of(betaManagedAgentsAgentParams))
            )
    }

    @Test
    fun ofBetaManagedAgentsAgentParamsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentPredefinedAgentParams =
            BetaManagedAgentsMultiagentPredefinedAgentParams.ofBetaManagedAgentsAgentParams(
                BetaManagedAgentsAgentParams.builder().id("x").version(0).build()
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
        val self = BetaManagedAgentsMultiagentSelfParams.builder().build()

        val betaManagedAgentsMultiagentPredefinedAgentParams =
            BetaManagedAgentsMultiagentPredefinedAgentParams.ofSelf(self)

        assertThat(presentVariants(betaManagedAgentsMultiagentPredefinedAgentParams))
            .isEqualTo(mapOf("self" to Optional.of(self)))
    }

    @Test
    fun ofSelfRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentPredefinedAgentParams =
            BetaManagedAgentsMultiagentPredefinedAgentParams.ofSelf(
                BetaManagedAgentsMultiagentSelfParams.builder().build()
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

    private fun presentVariants(
        betaManagedAgentsMultiagentPredefinedAgentParams:
            BetaManagedAgentsMultiagentPredefinedAgentParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "string" to betaManagedAgentsMultiagentPredefinedAgentParams.string(),
                "betaManagedAgentsAgentParams" to
                    betaManagedAgentsMultiagentPredefinedAgentParams.betaManagedAgentsAgentParams(),
                "self" to betaManagedAgentsMultiagentPredefinedAgentParams.self(),
            )
            .filterValues { it.isPresent }
}
