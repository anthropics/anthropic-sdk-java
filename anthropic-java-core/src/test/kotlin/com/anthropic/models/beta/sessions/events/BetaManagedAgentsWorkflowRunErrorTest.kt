package com.anthropic.models.beta.sessions.events

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

internal class BetaManagedAgentsWorkflowRunErrorTest {

    @Test
    fun ofTimeout() {
        val timeout =
            BetaManagedAgentsTimeoutWorkflowRunError.of("The workflow run reached its time limit.")

        val betaManagedAgentsWorkflowRunError = BetaManagedAgentsWorkflowRunError.ofTimeout(timeout)

        assertThat(presentVariants(betaManagedAgentsWorkflowRunError))
            .isEqualTo(mapOf("timeout" to Optional.of(timeout)))
    }

    @Test
    fun ofTimeoutRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunError =
            BetaManagedAgentsWorkflowRunError.ofTimeout(
                BetaManagedAgentsTimeoutWorkflowRunError.of(
                    "The workflow run reached its time limit."
                )
            )

        val roundtrippedBetaManagedAgentsWorkflowRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunError),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunError)
            .isEqualTo(betaManagedAgentsWorkflowRunError)
    }

    @Test
    fun ofProgram() {
        val program = BetaManagedAgentsProgramWorkflowRunError.of("The workflow run's plan failed.")

        val betaManagedAgentsWorkflowRunError = BetaManagedAgentsWorkflowRunError.ofProgram(program)

        assertThat(presentVariants(betaManagedAgentsWorkflowRunError))
            .isEqualTo(mapOf("program" to Optional.of(program)))
    }

    @Test
    fun ofProgramRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunError =
            BetaManagedAgentsWorkflowRunError.ofProgram(
                BetaManagedAgentsProgramWorkflowRunError.of("The workflow run's plan failed.")
            )

        val roundtrippedBetaManagedAgentsWorkflowRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunError),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunError)
            .isEqualTo(betaManagedAgentsWorkflowRunError)
    }

    @Test
    fun ofUnknown() {
        val unknown = BetaManagedAgentsUnknownWorkflowRunError.of("The workflow run failed.")

        val betaManagedAgentsWorkflowRunError = BetaManagedAgentsWorkflowRunError.ofUnknown(unknown)

        assertThat(presentVariants(betaManagedAgentsWorkflowRunError))
            .isEqualTo(mapOf("unknown" to Optional.of(unknown)))
    }

    @Test
    fun ofUnknownRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunError =
            BetaManagedAgentsWorkflowRunError.ofUnknown(
                BetaManagedAgentsUnknownWorkflowRunError.of("The workflow run failed.")
            )

        val roundtrippedBetaManagedAgentsWorkflowRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunError),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunError)
            .isEqualTo(betaManagedAgentsWorkflowRunError)
    }

    @Test
    fun ofThreadLimit() {
        val threadLimit =
            BetaManagedAgentsThreadLimitWorkflowRunError.of(
                "The workflow run exceeded its limit of threads."
            )

        val betaManagedAgentsWorkflowRunError =
            BetaManagedAgentsWorkflowRunError.ofThreadLimit(threadLimit)

        assertThat(presentVariants(betaManagedAgentsWorkflowRunError))
            .isEqualTo(mapOf("threadLimit" to Optional.of(threadLimit)))
    }

    @Test
    fun ofThreadLimitRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunError =
            BetaManagedAgentsWorkflowRunError.ofThreadLimit(
                BetaManagedAgentsThreadLimitWorkflowRunError.of(
                    "The workflow run exceeded its limit of threads."
                )
            )

        val roundtrippedBetaManagedAgentsWorkflowRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunError),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunError)
            .isEqualTo(betaManagedAgentsWorkflowRunError)
    }

    @Test
    fun ofMaxWorkflowRuns() {
        val maxWorkflowRuns =
            BetaManagedAgentsMaxWorkflowRunsWorkflowRunError.of(
                "The session is at its limit of open workflow runs."
            )

        val betaManagedAgentsWorkflowRunError =
            BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(maxWorkflowRuns)

        assertThat(presentVariants(betaManagedAgentsWorkflowRunError))
            .isEqualTo(mapOf("maxWorkflowRuns" to Optional.of(maxWorkflowRuns)))
    }

    @Test
    fun ofMaxWorkflowRunsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunError =
            BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(
                BetaManagedAgentsMaxWorkflowRunsWorkflowRunError.of(
                    "The session is at its limit of open workflow runs."
                )
            )

        val roundtrippedBetaManagedAgentsWorkflowRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunError),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunError)
            .isEqualTo(betaManagedAgentsWorkflowRunError)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaManagedAgentsWorkflowRunError =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "message" to "The workflow run reached its time limit.",
                        )
                    ),
                    jacksonTypeRef<BetaManagedAgentsWorkflowRunError>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsWorkflowRunError.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsWorkflowRunError.message())
            .isEqualTo("The workflow run reached its time limit.")

        val mismatchedBetaManagedAgentsWorkflowRunError =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "message" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaManagedAgentsWorkflowRunError>(),
                )

        assertThrows<AnthropicInvalidDataException> {
            mismatchedBetaManagedAgentsWorkflowRunError.message()
        }
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
        val betaManagedAgentsWorkflowRunError =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsWorkflowRunError>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsWorkflowRunError.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { betaManagedAgentsWorkflowRunError.message() }
    }

    private fun presentVariants(
        betaManagedAgentsWorkflowRunError: BetaManagedAgentsWorkflowRunError
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "timeout" to betaManagedAgentsWorkflowRunError.timeout(),
                "program" to betaManagedAgentsWorkflowRunError.program(),
                "unknown" to betaManagedAgentsWorkflowRunError.unknown(),
                "threadLimit" to betaManagedAgentsWorkflowRunError.threadLimit(),
                "maxWorkflowRuns" to betaManagedAgentsWorkflowRunError.maxWorkflowRuns(),
            )
            .filterValues { it.isPresent }
}
