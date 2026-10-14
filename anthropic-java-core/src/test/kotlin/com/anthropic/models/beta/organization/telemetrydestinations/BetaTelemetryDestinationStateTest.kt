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

internal class BetaTelemetryDestinationStateTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaTelemetryDestinationStateEnabled.builder().build()

        val betaTelemetryDestinationState = BetaTelemetryDestinationState.ofEnabled(enabled)

        assertThat(betaTelemetryDestinationState.enabled()).contains(enabled)
        assertThat(betaTelemetryDestinationState.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationState =
            BetaTelemetryDestinationState.ofEnabled(
                BetaTelemetryDestinationStateEnabled.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationState =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationState),
                jacksonTypeRef<BetaTelemetryDestinationState>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationState)
            .isEqualTo(betaTelemetryDestinationState)
    }

    @Test
    fun ofDisabled() {
        val disabled =
            BetaTelemetryDestinationStateDisabled.builder()
                .reason(BetaTelemetryDestinationDisabledReasonCaller.builder().build())
                .build()

        val betaTelemetryDestinationState = BetaTelemetryDestinationState.ofDisabled(disabled)

        assertThat(betaTelemetryDestinationState.enabled()).isEmpty
        assertThat(betaTelemetryDestinationState.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationState =
            BetaTelemetryDestinationState.ofDisabled(
                BetaTelemetryDestinationStateDisabled.builder()
                    .reason(BetaTelemetryDestinationDisabledReasonCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaTelemetryDestinationState =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationState),
                jacksonTypeRef<BetaTelemetryDestinationState>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationState)
            .isEqualTo(betaTelemetryDestinationState)
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
        val betaTelemetryDestinationState =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaTelemetryDestinationState>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaTelemetryDestinationState.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
