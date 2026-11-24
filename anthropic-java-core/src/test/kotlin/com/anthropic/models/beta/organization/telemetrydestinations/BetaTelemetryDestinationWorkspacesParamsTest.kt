package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaTelemetryDestinationWorkspacesParamsTest {

    @Test
    fun ofAll() {
        val all = BetaTelemetryDestinationWorkspacesAllParams.builder().build()

        val betaTelemetryDestinationWorkspacesParams =
            BetaTelemetryDestinationWorkspacesParams.ofAll(all)

        assertThat(presentVariants(betaTelemetryDestinationWorkspacesParams))
            .isEqualTo(mapOf("all" to Optional.of(all)))
    }

    @Test
    fun ofAllRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspacesParams =
            BetaTelemetryDestinationWorkspacesParams.ofAll(
                BetaTelemetryDestinationWorkspacesAllParams.builder().build()
            )

        val roundtrippedBetaTelemetryDestinationWorkspacesParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspacesParams),
                jacksonTypeRef<BetaTelemetryDestinationWorkspacesParams>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspacesParams)
            .isEqualTo(betaTelemetryDestinationWorkspacesParams)
    }

    @Test
    fun ofInclude() {
        val include =
            BetaTelemetryDestinationWorkspacesIncludeParams.builder()
                .addWorkspaceId("string")
                .build()

        val betaTelemetryDestinationWorkspacesParams =
            BetaTelemetryDestinationWorkspacesParams.ofInclude(include)

        assertThat(presentVariants(betaTelemetryDestinationWorkspacesParams))
            .isEqualTo(mapOf("include" to Optional.of(include)))
    }

    @Test
    fun ofIncludeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspacesParams =
            BetaTelemetryDestinationWorkspacesParams.ofInclude(
                BetaTelemetryDestinationWorkspacesIncludeParams.builder()
                    .addWorkspaceId("string")
                    .build()
            )

        val roundtrippedBetaTelemetryDestinationWorkspacesParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspacesParams),
                jacksonTypeRef<BetaTelemetryDestinationWorkspacesParams>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspacesParams)
            .isEqualTo(betaTelemetryDestinationWorkspacesParams)
    }

    @Test
    fun ofExclude() {
        val exclude =
            BetaTelemetryDestinationWorkspacesExcludeParams.builder()
                .addWorkspaceId("string")
                .build()

        val betaTelemetryDestinationWorkspacesParams =
            BetaTelemetryDestinationWorkspacesParams.ofExclude(exclude)

        assertThat(presentVariants(betaTelemetryDestinationWorkspacesParams))
            .isEqualTo(mapOf("exclude" to Optional.of(exclude)))
    }

    @Test
    fun ofExcludeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspacesParams =
            BetaTelemetryDestinationWorkspacesParams.ofExclude(
                BetaTelemetryDestinationWorkspacesExcludeParams.builder()
                    .addWorkspaceId("string")
                    .build()
            )

        val roundtrippedBetaTelemetryDestinationWorkspacesParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspacesParams),
                jacksonTypeRef<BetaTelemetryDestinationWorkspacesParams>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspacesParams)
            .isEqualTo(betaTelemetryDestinationWorkspacesParams)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaTelemetryDestinationWorkspacesParams =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "workspace_ids" to listOf("string"))
                    ),
                    jacksonTypeRef<BetaTelemetryDestinationWorkspacesParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaTelemetryDestinationWorkspacesParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaTelemetryDestinationWorkspacesParams.workspaceIds().getOrNull())
            .containsExactly("string")
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
        val betaTelemetryDestinationWorkspacesParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaTelemetryDestinationWorkspacesParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaTelemetryDestinationWorkspacesParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaTelemetryDestinationWorkspacesParams.workspaceIds()).isEmpty
    }

    private fun presentVariants(
        betaTelemetryDestinationWorkspacesParams: BetaTelemetryDestinationWorkspacesParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "all" to betaTelemetryDestinationWorkspacesParams.all(),
                "include" to betaTelemetryDestinationWorkspacesParams.include(),
                "exclude" to betaTelemetryDestinationWorkspacesParams.exclude(),
            )
            .filterValues { it.isPresent }
}
