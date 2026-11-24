package com.anthropic.models.beta.deployments

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsSystemContentBlock
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSystemMessageEventParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserDefineOutcomeEventParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsDeploymentInitialEventParamsTest {

    @Test
    fun ofUserMessage() {
        val userMessage =
            BetaManagedAgentsUserMessageEventParams.builder()
                .addTextContent("Where is my order #1234?")
                .build()

        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofUserMessage(userMessage)

        assertThat(presentVariants(betaManagedAgentsDeploymentInitialEventParams))
            .isEqualTo(mapOf("userMessage" to Optional.of(userMessage)))
    }

    @Test
    fun ofUserMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofUserMessage(
                BetaManagedAgentsUserMessageEventParams.builder()
                    .addTextContent("Where is my order #1234?")
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentInitialEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentInitialEventParams),
                jacksonTypeRef<BetaManagedAgentsDeploymentInitialEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentInitialEventParams)
            .isEqualTo(betaManagedAgentsDeploymentInitialEventParams)
    }

    @Test
    fun ofUserDefineOutcome() {
        val userDefineOutcome =
            BetaManagedAgentsUserDefineOutcomeEventParams.builder()
                .description("Produce a 2-page summary as summary.md")
                .textRubric("Must cover all five sections; cite sources inline.")
                .maxIterations(3)
                .build()

        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofUserDefineOutcome(userDefineOutcome)

        assertThat(presentVariants(betaManagedAgentsDeploymentInitialEventParams))
            .isEqualTo(mapOf("userDefineOutcome" to Optional.of(userDefineOutcome)))
    }

    @Test
    fun ofUserDefineOutcomeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofUserDefineOutcome(
                BetaManagedAgentsUserDefineOutcomeEventParams.builder()
                    .description("Produce a 2-page summary as summary.md")
                    .textRubric("Must cover all five sections; cite sources inline.")
                    .maxIterations(3)
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentInitialEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentInitialEventParams),
                jacksonTypeRef<BetaManagedAgentsDeploymentInitialEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentInitialEventParams)
            .isEqualTo(betaManagedAgentsDeploymentInitialEventParams)
    }

    @Test
    fun ofSystemMessage() {
        val systemMessage =
            BetaManagedAgentsSystemMessageEventParams.builder()
                .addContent(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
                .build()

        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofSystemMessage(systemMessage)

        assertThat(presentVariants(betaManagedAgentsDeploymentInitialEventParams))
            .isEqualTo(mapOf("systemMessage" to Optional.of(systemMessage)))
    }

    @Test
    fun ofSystemMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofSystemMessage(
                BetaManagedAgentsSystemMessageEventParams.builder()
                    .addContent(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentInitialEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentInitialEventParams),
                jacksonTypeRef<BetaManagedAgentsDeploymentInitialEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentInitialEventParams)
            .isEqualTo(betaManagedAgentsDeploymentInitialEventParams)
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
        val betaManagedAgentsDeploymentInitialEventParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsDeploymentInitialEventParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsDeploymentInitialEventParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaManagedAgentsDeploymentInitialEventParams: BetaManagedAgentsDeploymentInitialEventParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "userMessage" to betaManagedAgentsDeploymentInitialEventParams.userMessage(),
                "userDefineOutcome" to
                    betaManagedAgentsDeploymentInitialEventParams.userDefineOutcome(),
                "systemMessage" to betaManagedAgentsDeploymentInitialEventParams.systemMessage(),
            )
            .filterValues { it.isPresent }
}
