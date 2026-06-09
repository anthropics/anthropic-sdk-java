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

internal class BetaManagedAgentsDeploymentPausedReasonTest {

    @Test
    fun ofManual() {
        val manual =
            BetaManagedAgentsManualDeploymentPausedReason.builder()
                .type(BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL)
                .build()

        val betaManagedAgentsDeploymentPausedReason =
            BetaManagedAgentsDeploymentPausedReason.ofManual(manual)

        assertThat(betaManagedAgentsDeploymentPausedReason.manual()).contains(manual)
        assertThat(betaManagedAgentsDeploymentPausedReason.error()).isEmpty
    }

    @Test
    fun ofManualRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReason =
            BetaManagedAgentsDeploymentPausedReason.ofManual(
                BetaManagedAgentsManualDeploymentPausedReason.builder()
                    .type(BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL)
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReason),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReason>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReason)
            .isEqualTo(betaManagedAgentsDeploymentPausedReason)
    }

    @Test
    fun ofError() {
        val error =
            BetaManagedAgentsErrorDeploymentPausedReason.builder()
                .error(
                    BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder()
                        .type(
                            BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.Type
                                .ENVIRONMENT_ARCHIVED_ERROR
                        )
                        .build()
                )
                .type(BetaManagedAgentsErrorDeploymentPausedReason.Type.ERROR)
                .build()

        val betaManagedAgentsDeploymentPausedReason =
            BetaManagedAgentsDeploymentPausedReason.ofError(error)

        assertThat(betaManagedAgentsDeploymentPausedReason.manual()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReason.error()).contains(error)
    }

    @Test
    fun ofErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReason =
            BetaManagedAgentsDeploymentPausedReason.ofError(
                BetaManagedAgentsErrorDeploymentPausedReason.builder()
                    .error(
                        BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder()
                            .type(
                                BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.Type
                                    .ENVIRONMENT_ARCHIVED_ERROR
                            )
                            .build()
                    )
                    .type(BetaManagedAgentsErrorDeploymentPausedReason.Type.ERROR)
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReason),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReason>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReason)
            .isEqualTo(betaManagedAgentsDeploymentPausedReason)
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
        val betaManagedAgentsDeploymentPausedReason =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsDeploymentPausedReason>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsDeploymentPausedReason.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
