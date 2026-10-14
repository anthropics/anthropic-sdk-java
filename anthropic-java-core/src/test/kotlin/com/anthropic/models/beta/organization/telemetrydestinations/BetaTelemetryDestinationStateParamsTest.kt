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

internal class BetaTelemetryDestinationStateParamsTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaTelemetryDestinationStateEnabledParams.builder().build()

        val betaTelemetryDestinationStateParams =
            BetaTelemetryDestinationStateParams.ofEnabled(enabled)

        assertThat(betaTelemetryDestinationStateParams.enabled()).contains(enabled)
        assertThat(betaTelemetryDestinationStateParams.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationStateParams =
            BetaTelemetryDestinationStateParams.ofEnabled(
                BetaTelemetryDestinationStateEnabledParams.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationStateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationStateParams),
                jacksonTypeRef<BetaTelemetryDestinationStateParams>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationStateParams)
            .isEqualTo(betaTelemetryDestinationStateParams)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaTelemetryDestinationStateDisabledParams.builder().build()

        val betaTelemetryDestinationStateParams =
            BetaTelemetryDestinationStateParams.ofDisabled(disabled)

        assertThat(betaTelemetryDestinationStateParams.enabled()).isEmpty
        assertThat(betaTelemetryDestinationStateParams.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationStateParams =
            BetaTelemetryDestinationStateParams.ofDisabled(
                BetaTelemetryDestinationStateDisabledParams.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationStateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationStateParams),
                jacksonTypeRef<BetaTelemetryDestinationStateParams>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationStateParams)
            .isEqualTo(betaTelemetryDestinationStateParams)
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
        val betaTelemetryDestinationStateParams =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaTelemetryDestinationStateParams>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaTelemetryDestinationStateParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
