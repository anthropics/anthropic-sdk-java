package com.anthropic.models.beta.organization.telemetrydestinations

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

internal class BetaTelemetryDestinationStateParamsTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaTelemetryDestinationStateEnabledParams.builder().build()

        val betaTelemetryDestinationStateParams =
            BetaTelemetryDestinationStateParams.ofEnabled(enabled)

        assertThat(presentVariants(betaTelemetryDestinationStateParams))
            .isEqualTo(mapOf("enabled" to Optional.of(enabled)))
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

        assertThat(presentVariants(betaTelemetryDestinationStateParams))
            .isEqualTo(mapOf("disabled" to Optional.of(disabled)))
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

    private fun presentVariants(
        betaTelemetryDestinationStateParams: BetaTelemetryDestinationStateParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "enabled" to betaTelemetryDestinationStateParams.enabled(),
                "disabled" to betaTelemetryDestinationStateParams.disabled(),
            )
            .filterValues { it.isPresent }
}
