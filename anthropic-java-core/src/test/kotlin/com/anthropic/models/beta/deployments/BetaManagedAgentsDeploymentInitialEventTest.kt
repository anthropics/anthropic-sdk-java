// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsDeploymentInitialEventTest {

    @Test
    fun ofUserMessage() {
        val userMessage =
            BetaManagedAgentsDeploymentUserMessageEvent.builder()
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsDeploymentUserMessageEvent.Type.USER_MESSAGE)
                .build()

        val betaManagedAgentsDeploymentInitialEvent =
            BetaManagedAgentsDeploymentInitialEvent.ofUserMessage(userMessage)

        assertThat(betaManagedAgentsDeploymentInitialEvent.userMessage()).contains(userMessage)
        assertThat(betaManagedAgentsDeploymentInitialEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsDeploymentInitialEvent.systemMessage()).isEmpty
    }

    @Test
    fun ofUserMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentInitialEvent =
            BetaManagedAgentsDeploymentInitialEvent.ofUserMessage(
                BetaManagedAgentsDeploymentUserMessageEvent.builder()
                    .addTextContent("Where is my order #1234?")
                    .type(BetaManagedAgentsDeploymentUserMessageEvent.Type.USER_MESSAGE)
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentInitialEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentInitialEvent),
                jacksonTypeRef<BetaManagedAgentsDeploymentInitialEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentInitialEvent)
            .isEqualTo(betaManagedAgentsDeploymentInitialEvent)
    }

    @Test
    fun ofUserDefineOutcome() {
        val userDefineOutcome =
            BetaManagedAgentsDeploymentUserDefineOutcomeEvent.builder()
                .description("description")
                .fileRubric("file_id")
                .type(BetaManagedAgentsDeploymentUserDefineOutcomeEvent.Type.USER_DEFINE_OUTCOME)
                .maxIterations(0)
                .build()

        val betaManagedAgentsDeploymentInitialEvent =
            BetaManagedAgentsDeploymentInitialEvent.ofUserDefineOutcome(userDefineOutcome)

        assertThat(betaManagedAgentsDeploymentInitialEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsDeploymentInitialEvent.userDefineOutcome())
            .contains(userDefineOutcome)
        assertThat(betaManagedAgentsDeploymentInitialEvent.systemMessage()).isEmpty
    }

    @Test
    fun ofUserDefineOutcomeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentInitialEvent =
            BetaManagedAgentsDeploymentInitialEvent.ofUserDefineOutcome(
                BetaManagedAgentsDeploymentUserDefineOutcomeEvent.builder()
                    .description("description")
                    .fileRubric("file_id")
                    .type(
                        BetaManagedAgentsDeploymentUserDefineOutcomeEvent.Type.USER_DEFINE_OUTCOME
                    )
                    .maxIterations(0)
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentInitialEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentInitialEvent),
                jacksonTypeRef<BetaManagedAgentsDeploymentInitialEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentInitialEvent)
            .isEqualTo(betaManagedAgentsDeploymentInitialEvent)
    }

    @Test
    fun ofSystemMessage() {
        val systemMessage =
            BetaManagedAgentsDeploymentSystemMessageEvent.builder()
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsDeploymentSystemMessageEvent.Type.SYSTEM_MESSAGE)
                .build()

        val betaManagedAgentsDeploymentInitialEvent =
            BetaManagedAgentsDeploymentInitialEvent.ofSystemMessage(systemMessage)

        assertThat(betaManagedAgentsDeploymentInitialEvent.userMessage()).isEmpty
        assertThat(betaManagedAgentsDeploymentInitialEvent.userDefineOutcome()).isEmpty
        assertThat(betaManagedAgentsDeploymentInitialEvent.systemMessage()).contains(systemMessage)
    }

    @Test
    fun ofSystemMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentInitialEvent =
            BetaManagedAgentsDeploymentInitialEvent.ofSystemMessage(
                BetaManagedAgentsDeploymentSystemMessageEvent.builder()
                    .addTextContent("Where is my order #1234?")
                    .type(BetaManagedAgentsDeploymentSystemMessageEvent.Type.SYSTEM_MESSAGE)
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentInitialEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentInitialEvent),
                jacksonTypeRef<BetaManagedAgentsDeploymentInitialEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentInitialEvent)
            .isEqualTo(betaManagedAgentsDeploymentInitialEvent)
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
        val betaManagedAgentsDeploymentInitialEvent =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsDeploymentInitialEvent>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsDeploymentInitialEvent.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
