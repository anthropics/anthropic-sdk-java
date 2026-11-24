package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaGoogleManagedProjectCmekRegionStatusTest {

    @Test
    fun ofNotConfigured() {
        val notConfigured =
            BetaGoogleManagedProjectCmekRegionNotConfigured.of("effective_external_key_id")

        val betaGoogleManagedProjectCmekRegionStatus =
            BetaGoogleManagedProjectCmekRegionStatus.ofNotConfigured(notConfigured)

        assertThat(betaGoogleManagedProjectCmekRegionStatus.notConfigured()).contains(notConfigured)
        assertThat(betaGoogleManagedProjectCmekRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofNotConfiguredRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekRegionStatus =
            BetaGoogleManagedProjectCmekRegionStatus.ofNotConfigured(
                BetaGoogleManagedProjectCmekRegionNotConfigured.of("effective_external_key_id")
            )

        val roundtrippedBetaGoogleManagedProjectCmekRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekRegionStatus),
                jacksonTypeRef<BetaGoogleManagedProjectCmekRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekRegionStatus)
            .isEqualTo(betaGoogleManagedProjectCmekRegionStatus)
    }

    @Test
    fun ofPending() {
        val pending =
            BetaGoogleManagedProjectCmekRegionPending.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        val betaGoogleManagedProjectCmekRegionStatus =
            BetaGoogleManagedProjectCmekRegionStatus.ofPending(pending)

        assertThat(betaGoogleManagedProjectCmekRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.pending()).contains(pending)
        assertThat(betaGoogleManagedProjectCmekRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofPendingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekRegionStatus =
            BetaGoogleManagedProjectCmekRegionStatus.ofPending(
                BetaGoogleManagedProjectCmekRegionPending.builder()
                    .effectiveExternalKeyId("effective_external_key_id")
                    .error("error")
                    .build()
            )

        val roundtrippedBetaGoogleManagedProjectCmekRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekRegionStatus),
                jacksonTypeRef<BetaGoogleManagedProjectCmekRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekRegionStatus)
            .isEqualTo(betaGoogleManagedProjectCmekRegionStatus)
    }

    @Test
    fun ofActive() {
        val active =
            BetaGoogleManagedProjectCmekRegionActive.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .lastCheckedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaGoogleManagedProjectCmekRegionStatus =
            BetaGoogleManagedProjectCmekRegionStatus.ofActive(active)

        assertThat(betaGoogleManagedProjectCmekRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.active()).contains(active)
        assertThat(betaGoogleManagedProjectCmekRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofActiveRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekRegionStatus =
            BetaGoogleManagedProjectCmekRegionStatus.ofActive(
                BetaGoogleManagedProjectCmekRegionActive.builder()
                    .effectiveExternalKeyId("effective_external_key_id")
                    .error("error")
                    .lastCheckedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaGoogleManagedProjectCmekRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekRegionStatus),
                jacksonTypeRef<BetaGoogleManagedProjectCmekRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekRegionStatus)
            .isEqualTo(betaGoogleManagedProjectCmekRegionStatus)
    }

    @Test
    fun ofFailed() {
        val failed =
            BetaGoogleManagedProjectCmekRegionFailed.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        val betaGoogleManagedProjectCmekRegionStatus =
            BetaGoogleManagedProjectCmekRegionStatus.ofFailed(failed)

        assertThat(betaGoogleManagedProjectCmekRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.failed()).contains(failed)
        assertThat(betaGoogleManagedProjectCmekRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekRegionStatus =
            BetaGoogleManagedProjectCmekRegionStatus.ofFailed(
                BetaGoogleManagedProjectCmekRegionFailed.builder()
                    .effectiveExternalKeyId("effective_external_key_id")
                    .error("error")
                    .build()
            )

        val roundtrippedBetaGoogleManagedProjectCmekRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekRegionStatus),
                jacksonTypeRef<BetaGoogleManagedProjectCmekRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekRegionStatus)
            .isEqualTo(betaGoogleManagedProjectCmekRegionStatus)
    }

    @Test
    fun ofSuspended() {
        val suspended = BetaGoogleManagedProjectCmekRegionSuspended.of("effective_external_key_id")

        val betaGoogleManagedProjectCmekRegionStatus =
            BetaGoogleManagedProjectCmekRegionStatus.ofSuspended(suspended)

        assertThat(betaGoogleManagedProjectCmekRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.suspended()).contains(suspended)
    }

    @Test
    fun ofSuspendedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekRegionStatus =
            BetaGoogleManagedProjectCmekRegionStatus.ofSuspended(
                BetaGoogleManagedProjectCmekRegionSuspended.of("effective_external_key_id")
            )

        val roundtrippedBetaGoogleManagedProjectCmekRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekRegionStatus),
                jacksonTypeRef<BetaGoogleManagedProjectCmekRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekRegionStatus)
            .isEqualTo(betaGoogleManagedProjectCmekRegionStatus)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaGoogleManagedProjectCmekRegionStatus =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "effective_external_key_id" to "effective_external_key_id",
                            "error" to "error",
                        )
                    ),
                    jacksonTypeRef<BetaGoogleManagedProjectCmekRegionStatus>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedProjectCmekRegionStatus.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedProjectCmekRegionStatus.effectiveExternalKeyId())
            .contains("effective_external_key_id")
        assertThat(betaGoogleManagedProjectCmekRegionStatus.error()).contains("error")

        val mismatchedBetaGoogleManagedProjectCmekRegionStatus =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "effective_external_key_id" to listOf("invalid"),
                            "error" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaGoogleManagedProjectCmekRegionStatus>(),
                )

        assertThat(mismatchedBetaGoogleManagedProjectCmekRegionStatus.effectiveExternalKeyId())
            .isEmpty
        assertThat(mismatchedBetaGoogleManagedProjectCmekRegionStatus.error()).isEmpty
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
        val betaGoogleManagedProjectCmekRegionStatus =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaGoogleManagedProjectCmekRegionStatus>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedProjectCmekRegionStatus.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedProjectCmekRegionStatus.effectiveExternalKeyId()).isEmpty
        assertThat(betaGoogleManagedProjectCmekRegionStatus.error()).isEmpty
    }
}
