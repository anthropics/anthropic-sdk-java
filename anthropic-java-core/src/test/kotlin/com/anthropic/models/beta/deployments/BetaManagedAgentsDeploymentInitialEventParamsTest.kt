// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSystemMessageEventParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserDefineOutcomeEventParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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
                .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
                .build()

        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofUserMessage(userMessage)

        assertThat(betaManagedAgentsDeploymentInitialEventParams.userMessage())
            .contains(userMessage)
        assertThat(betaManagedAgentsDeploymentInitialEventParams.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsDeploymentInitialEventParams.systemMessage()).isEmpty
    }

    @Test
    fun ofUserMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofUserMessage(
                BetaManagedAgentsUserMessageEventParams.builder()
                    .addTextContent("Where is my order #1234?")
                    .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
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
                .type(BetaManagedAgentsUserDefineOutcomeEventParams.Type.USER_DEFINE_OUTCOME)
                .maxIterations(3)
                .build()

        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofUserDefineOutcome(userDefineOutcome)

        assertThat(betaManagedAgentsDeploymentInitialEventParams.userMessage()).isEmpty
        assertThat(betaManagedAgentsDeploymentInitialEventParams.userDefineOutcome())
            .contains(userDefineOutcome)
        assertThat(betaManagedAgentsDeploymentInitialEventParams.systemMessage()).isEmpty
    }

    @Test
    fun ofUserDefineOutcomeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofUserDefineOutcome(
                BetaManagedAgentsUserDefineOutcomeEventParams.builder()
                    .description("Produce a 2-page summary as summary.md")
                    .textRubric("Must cover all five sections; cite sources inline.")
                    .type(BetaManagedAgentsUserDefineOutcomeEventParams.Type.USER_DEFINE_OUTCOME)
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
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsSystemMessageEventParams.Type.SYSTEM_MESSAGE)
                .build()

        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofSystemMessage(systemMessage)

        assertThat(betaManagedAgentsDeploymentInitialEventParams.userMessage()).isEmpty
        assertThat(betaManagedAgentsDeploymentInitialEventParams.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsDeploymentInitialEventParams.systemMessage())
            .contains(systemMessage)
    }

    @Test
    fun ofSystemMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentInitialEventParams =
            BetaManagedAgentsDeploymentInitialEventParams.ofSystemMessage(
                BetaManagedAgentsSystemMessageEventParams.builder()
                    .addTextContent("Where is my order #1234?")
                    .type(BetaManagedAgentsSystemMessageEventParams.Type.SYSTEM_MESSAGE)
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
}
