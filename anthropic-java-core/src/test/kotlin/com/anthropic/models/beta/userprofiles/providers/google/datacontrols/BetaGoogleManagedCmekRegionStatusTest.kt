package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

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

internal class BetaGoogleManagedCmekRegionStatusTest {

    @Test
    fun ofNotConfigured() {
        val notConfigured = BetaGoogleManagedCmekRegionNotConfigured.of("effective_external_key_id")

        val betaGoogleManagedCmekRegionStatus =
            BetaGoogleManagedCmekRegionStatus.ofNotConfigured(notConfigured)

        assertThat(betaGoogleManagedCmekRegionStatus.notConfigured()).contains(notConfigured)
        assertThat(betaGoogleManagedCmekRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofNotConfiguredRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekRegionStatus =
            BetaGoogleManagedCmekRegionStatus.ofNotConfigured(
                BetaGoogleManagedCmekRegionNotConfigured.of("effective_external_key_id")
            )

        val roundtrippedBetaGoogleManagedCmekRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekRegionStatus),
                jacksonTypeRef<BetaGoogleManagedCmekRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekRegionStatus)
            .isEqualTo(betaGoogleManagedCmekRegionStatus)
    }

    @Test
    fun ofPending() {
        val pending =
            BetaGoogleManagedCmekRegionPending.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        val betaGoogleManagedCmekRegionStatus = BetaGoogleManagedCmekRegionStatus.ofPending(pending)

        assertThat(betaGoogleManagedCmekRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.pending()).contains(pending)
        assertThat(betaGoogleManagedCmekRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofPendingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekRegionStatus =
            BetaGoogleManagedCmekRegionStatus.ofPending(
                BetaGoogleManagedCmekRegionPending.builder()
                    .effectiveExternalKeyId("effective_external_key_id")
                    .error("error")
                    .build()
            )

        val roundtrippedBetaGoogleManagedCmekRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekRegionStatus),
                jacksonTypeRef<BetaGoogleManagedCmekRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekRegionStatus)
            .isEqualTo(betaGoogleManagedCmekRegionStatus)
    }

    @Test
    fun ofActive() {
        val active =
            BetaGoogleManagedCmekRegionActive.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .lastCheckedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaGoogleManagedCmekRegionStatus = BetaGoogleManagedCmekRegionStatus.ofActive(active)

        assertThat(betaGoogleManagedCmekRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.active()).contains(active)
        assertThat(betaGoogleManagedCmekRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofActiveRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekRegionStatus =
            BetaGoogleManagedCmekRegionStatus.ofActive(
                BetaGoogleManagedCmekRegionActive.builder()
                    .effectiveExternalKeyId("effective_external_key_id")
                    .error("error")
                    .lastCheckedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaGoogleManagedCmekRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekRegionStatus),
                jacksonTypeRef<BetaGoogleManagedCmekRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekRegionStatus)
            .isEqualTo(betaGoogleManagedCmekRegionStatus)
    }

    @Test
    fun ofFailed() {
        val failed =
            BetaGoogleManagedCmekRegionFailed.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        val betaGoogleManagedCmekRegionStatus = BetaGoogleManagedCmekRegionStatus.ofFailed(failed)

        assertThat(betaGoogleManagedCmekRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.failed()).contains(failed)
        assertThat(betaGoogleManagedCmekRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekRegionStatus =
            BetaGoogleManagedCmekRegionStatus.ofFailed(
                BetaGoogleManagedCmekRegionFailed.builder()
                    .effectiveExternalKeyId("effective_external_key_id")
                    .error("error")
                    .build()
            )

        val roundtrippedBetaGoogleManagedCmekRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekRegionStatus),
                jacksonTypeRef<BetaGoogleManagedCmekRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekRegionStatus)
            .isEqualTo(betaGoogleManagedCmekRegionStatus)
    }

    @Test
    fun ofSuspended() {
        val suspended = BetaGoogleManagedCmekRegionSuspended.of("effective_external_key_id")

        val betaGoogleManagedCmekRegionStatus =
            BetaGoogleManagedCmekRegionStatus.ofSuspended(suspended)

        assertThat(betaGoogleManagedCmekRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.suspended()).contains(suspended)
    }

    @Test
    fun ofSuspendedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekRegionStatus =
            BetaGoogleManagedCmekRegionStatus.ofSuspended(
                BetaGoogleManagedCmekRegionSuspended.of("effective_external_key_id")
            )

        val roundtrippedBetaGoogleManagedCmekRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekRegionStatus),
                jacksonTypeRef<BetaGoogleManagedCmekRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekRegionStatus)
            .isEqualTo(betaGoogleManagedCmekRegionStatus)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaGoogleManagedCmekRegionStatus =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "effective_external_key_id" to "effective_external_key_id",
                            "error" to "error",
                        )
                    ),
                    jacksonTypeRef<BetaGoogleManagedCmekRegionStatus>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedCmekRegionStatus.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedCmekRegionStatus.effectiveExternalKeyId())
            .contains("effective_external_key_id")
        assertThat(betaGoogleManagedCmekRegionStatus.error()).contains("error")

        val mismatchedBetaGoogleManagedCmekRegionStatus =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "effective_external_key_id" to listOf("invalid"),
                            "error" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaGoogleManagedCmekRegionStatus>(),
                )

        assertThat(mismatchedBetaGoogleManagedCmekRegionStatus.effectiveExternalKeyId()).isEmpty
        assertThat(mismatchedBetaGoogleManagedCmekRegionStatus.error()).isEmpty
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
        val betaGoogleManagedCmekRegionStatus =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaGoogleManagedCmekRegionStatus>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedCmekRegionStatus.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedCmekRegionStatus.effectiveExternalKeyId()).isEmpty
        assertThat(betaGoogleManagedCmekRegionStatus.error()).isEmpty
    }
}
