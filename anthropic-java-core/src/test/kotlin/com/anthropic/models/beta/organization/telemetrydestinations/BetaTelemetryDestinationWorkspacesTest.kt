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

internal class BetaTelemetryDestinationWorkspacesTest {

    @Test
    fun ofAll() {
        val all = BetaTelemetryDestinationWorkspacesAll.builder().build()

        val betaTelemetryDestinationWorkspaces = BetaTelemetryDestinationWorkspaces.ofAll(all)

        assertThat(presentVariants(betaTelemetryDestinationWorkspaces))
            .isEqualTo(mapOf("all" to Optional.of(all)))
    }

    @Test
    fun ofAllRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspaces =
            BetaTelemetryDestinationWorkspaces.ofAll(
                BetaTelemetryDestinationWorkspacesAll.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationWorkspaces =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspaces),
                jacksonTypeRef<BetaTelemetryDestinationWorkspaces>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspaces)
            .isEqualTo(betaTelemetryDestinationWorkspaces)
    }

    @Test
    fun ofInclude() {
        val include = BetaTelemetryDestinationWorkspacesInclude.builder().build()

        val betaTelemetryDestinationWorkspaces =
            BetaTelemetryDestinationWorkspaces.ofInclude(include)

        assertThat(presentVariants(betaTelemetryDestinationWorkspaces))
            .isEqualTo(mapOf("include" to Optional.of(include)))
    }

    @Test
    fun ofIncludeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspaces =
            BetaTelemetryDestinationWorkspaces.ofInclude(
                BetaTelemetryDestinationWorkspacesInclude.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationWorkspaces =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspaces),
                jacksonTypeRef<BetaTelemetryDestinationWorkspaces>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspaces)
            .isEqualTo(betaTelemetryDestinationWorkspaces)
    }

    @Test
    fun ofExclude() {
        val exclude = BetaTelemetryDestinationWorkspacesExclude.builder().build()

        val betaTelemetryDestinationWorkspaces =
            BetaTelemetryDestinationWorkspaces.ofExclude(exclude)

        assertThat(presentVariants(betaTelemetryDestinationWorkspaces))
            .isEqualTo(mapOf("exclude" to Optional.of(exclude)))
    }

    @Test
    fun ofExcludeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspaces =
            BetaTelemetryDestinationWorkspaces.ofExclude(
                BetaTelemetryDestinationWorkspacesExclude.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationWorkspaces =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspaces),
                jacksonTypeRef<BetaTelemetryDestinationWorkspaces>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspaces)
            .isEqualTo(betaTelemetryDestinationWorkspaces)
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
        val betaTelemetryDestinationWorkspaces =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaTelemetryDestinationWorkspaces>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaTelemetryDestinationWorkspaces.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaTelemetryDestinationWorkspaces: BetaTelemetryDestinationWorkspaces
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "all" to betaTelemetryDestinationWorkspaces.all(),
                "include" to betaTelemetryDestinationWorkspaces.include(),
                "exclude" to betaTelemetryDestinationWorkspaces.exclude(),
            )
            .filterValues { it.isPresent }
}
