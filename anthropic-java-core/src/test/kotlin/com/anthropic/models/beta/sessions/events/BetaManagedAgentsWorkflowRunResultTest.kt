package com.anthropic.models.beta.sessions.events

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsWorkflowRunResultTest {

    @Test
    fun ofCompleted() {
        val completed = BetaManagedAgentsWorkflowRunResultCompleted.builder().build()

        val betaManagedAgentsWorkflowRunResult =
            BetaManagedAgentsWorkflowRunResult.ofCompleted(completed)

        assertThat(betaManagedAgentsWorkflowRunResult.completed()).contains(completed)
        assertThat(betaManagedAgentsWorkflowRunResult.error()).isEmpty
        assertThat(betaManagedAgentsWorkflowRunResult.stopped()).isEmpty
    }

    @Test
    fun ofCompletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunResult =
            BetaManagedAgentsWorkflowRunResult.ofCompleted(
                BetaManagedAgentsWorkflowRunResultCompleted.builder().build()
            )

        val roundtrippedBetaManagedAgentsWorkflowRunResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunResult),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunResult>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunResult)
            .isEqualTo(betaManagedAgentsWorkflowRunResult)
    }

    @Test
    fun ofError() {
        val error =
            BetaManagedAgentsWorkflowRunResultError.builder()
                .timeoutError("The workflow run reached its time limit.")
                .build()

        val betaManagedAgentsWorkflowRunResult = BetaManagedAgentsWorkflowRunResult.ofError(error)

        assertThat(betaManagedAgentsWorkflowRunResult.completed()).isEmpty
        assertThat(betaManagedAgentsWorkflowRunResult.error()).contains(error)
        assertThat(betaManagedAgentsWorkflowRunResult.stopped()).isEmpty
    }

    @Test
    fun ofErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunResult =
            BetaManagedAgentsWorkflowRunResult.ofError(
                BetaManagedAgentsWorkflowRunResultError.builder()
                    .timeoutError("The workflow run reached its time limit.")
                    .build()
            )

        val roundtrippedBetaManagedAgentsWorkflowRunResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunResult),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunResult>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunResult)
            .isEqualTo(betaManagedAgentsWorkflowRunResult)
    }

    @Test
    fun ofStopped() {
        val stopped = BetaManagedAgentsWorkflowRunResultStopped.builder().build()

        val betaManagedAgentsWorkflowRunResult =
            BetaManagedAgentsWorkflowRunResult.ofStopped(stopped)

        assertThat(betaManagedAgentsWorkflowRunResult.completed()).isEmpty
        assertThat(betaManagedAgentsWorkflowRunResult.error()).isEmpty
        assertThat(betaManagedAgentsWorkflowRunResult.stopped()).contains(stopped)
    }

    @Test
    fun ofStoppedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunResult =
            BetaManagedAgentsWorkflowRunResult.ofStopped(
                BetaManagedAgentsWorkflowRunResultStopped.builder().build()
            )

        val roundtrippedBetaManagedAgentsWorkflowRunResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunResult),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunResult>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunResult)
            .isEqualTo(betaManagedAgentsWorkflowRunResult)
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
        val betaManagedAgentsWorkflowRunResult =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsWorkflowRunResult>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsWorkflowRunResult.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
