package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaTelemetryDestinationTestResultTest {

    @Test
    fun ofSuccess() {
        val success = BetaTelemetryDestinationTestSuccess.builder().build()

        val betaTelemetryDestinationTestResult =
            BetaTelemetryDestinationTestResult.ofSuccess(success)

        assertThat(betaTelemetryDestinationTestResult.success()).contains(success)
        assertThat(betaTelemetryDestinationTestResult.failure()).isEmpty
    }

    @Test
    fun ofSuccessRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationTestResult =
            BetaTelemetryDestinationTestResult.ofSuccess(
                BetaTelemetryDestinationTestSuccess.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationTestResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationTestResult),
                jacksonTypeRef<BetaTelemetryDestinationTestResult>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationTestResult)
            .isEqualTo(betaTelemetryDestinationTestResult)
    }

    @Test
    fun ofFailure() {
        val failure = BetaTelemetryDestinationTestFailure.of("HTTP error (status 403)")

        val betaTelemetryDestinationTestResult =
            BetaTelemetryDestinationTestResult.ofFailure(failure)

        assertThat(betaTelemetryDestinationTestResult.success()).isEmpty
        assertThat(betaTelemetryDestinationTestResult.failure()).contains(failure)
    }

    @Test
    fun ofFailureRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationTestResult =
            BetaTelemetryDestinationTestResult.ofFailure(
                BetaTelemetryDestinationTestFailure.of("HTTP error (status 403)")
            )

        val roundtrippedBetaTelemetryDestinationTestResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationTestResult),
                jacksonTypeRef<BetaTelemetryDestinationTestResult>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationTestResult)
            .isEqualTo(betaTelemetryDestinationTestResult)
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
        val betaTelemetryDestinationTestResult =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaTelemetryDestinationTestResult>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaTelemetryDestinationTestResult.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
