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

internal class BetaTelemetryDestinationDisabledReasonTest {

    @Test
    fun ofCaller() {
        val caller = BetaTelemetryDestinationDisabledReasonCaller.builder().build()

        val betaTelemetryDestinationDisabledReason =
            BetaTelemetryDestinationDisabledReason.ofCaller(caller)

        assertThat(presentVariants(betaTelemetryDestinationDisabledReason))
            .isEqualTo(mapOf("caller" to Optional.of(caller)))
    }

    @Test
    fun ofCallerRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationDisabledReason =
            BetaTelemetryDestinationDisabledReason.ofCaller(
                BetaTelemetryDestinationDisabledReasonCaller.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationDisabledReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationDisabledReason),
                jacksonTypeRef<BetaTelemetryDestinationDisabledReason>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationDisabledReason)
            .isEqualTo(betaTelemetryDestinationDisabledReason)
    }

    @Test
    fun ofSustainedFailure() {
        val sustainedFailure =
            BetaTelemetryDestinationDisabledReasonSustainedFailure.builder().build()

        val betaTelemetryDestinationDisabledReason =
            BetaTelemetryDestinationDisabledReason.ofSustainedFailure(sustainedFailure)

        assertThat(presentVariants(betaTelemetryDestinationDisabledReason))
            .isEqualTo(mapOf("sustainedFailure" to Optional.of(sustainedFailure)))
    }

    @Test
    fun ofSustainedFailureRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationDisabledReason =
            BetaTelemetryDestinationDisabledReason.ofSustainedFailure(
                BetaTelemetryDestinationDisabledReasonSustainedFailure.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationDisabledReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationDisabledReason),
                jacksonTypeRef<BetaTelemetryDestinationDisabledReason>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationDisabledReason)
            .isEqualTo(betaTelemetryDestinationDisabledReason)
    }

    @Test
    fun ofInvalidAddress() {
        val invalidAddress = BetaTelemetryDestinationDisabledReasonInvalidAddress.builder().build()

        val betaTelemetryDestinationDisabledReason =
            BetaTelemetryDestinationDisabledReason.ofInvalidAddress(invalidAddress)

        assertThat(presentVariants(betaTelemetryDestinationDisabledReason))
            .isEqualTo(mapOf("invalidAddress" to Optional.of(invalidAddress)))
    }

    @Test
    fun ofInvalidAddressRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationDisabledReason =
            BetaTelemetryDestinationDisabledReason.ofInvalidAddress(
                BetaTelemetryDestinationDisabledReasonInvalidAddress.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationDisabledReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationDisabledReason),
                jacksonTypeRef<BetaTelemetryDestinationDisabledReason>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationDisabledReason)
            .isEqualTo(betaTelemetryDestinationDisabledReason)
    }

    @Test
    fun ofRedirect() {
        val redirect = BetaTelemetryDestinationDisabledReasonRedirect.builder().build()

        val betaTelemetryDestinationDisabledReason =
            BetaTelemetryDestinationDisabledReason.ofRedirect(redirect)

        assertThat(presentVariants(betaTelemetryDestinationDisabledReason))
            .isEqualTo(mapOf("redirect" to Optional.of(redirect)))
    }

    @Test
    fun ofRedirectRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationDisabledReason =
            BetaTelemetryDestinationDisabledReason.ofRedirect(
                BetaTelemetryDestinationDisabledReasonRedirect.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationDisabledReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationDisabledReason),
                jacksonTypeRef<BetaTelemetryDestinationDisabledReason>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationDisabledReason)
            .isEqualTo(betaTelemetryDestinationDisabledReason)
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
        val betaTelemetryDestinationDisabledReason =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaTelemetryDestinationDisabledReason>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaTelemetryDestinationDisabledReason.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaTelemetryDestinationDisabledReason: BetaTelemetryDestinationDisabledReason
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "caller" to betaTelemetryDestinationDisabledReason.caller(),
                "sustainedFailure" to betaTelemetryDestinationDisabledReason.sustainedFailure(),
                "invalidAddress" to betaTelemetryDestinationDisabledReason.invalidAddress(),
                "redirect" to betaTelemetryDestinationDisabledReason.redirect(),
            )
            .filterValues { it.isPresent }
}
