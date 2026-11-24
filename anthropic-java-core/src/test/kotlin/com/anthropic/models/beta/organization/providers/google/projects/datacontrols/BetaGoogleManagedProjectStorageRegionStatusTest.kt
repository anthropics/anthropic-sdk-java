package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaGoogleManagedProjectStorageRegionStatusTest {

    @Test
    fun ofNotConfigured() {
        val notConfigured =
            BetaGoogleManagedProjectStorageRegionNotConfigured.of(
                BetaGoogleManagedProjectEffectiveBucket.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        val betaGoogleManagedProjectStorageRegionStatus =
            BetaGoogleManagedProjectStorageRegionStatus.ofNotConfigured(notConfigured)

        assertThat(betaGoogleManagedProjectStorageRegionStatus.notConfigured())
            .contains(notConfigured)
        assertThat(betaGoogleManagedProjectStorageRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofNotConfiguredRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageRegionStatus =
            BetaGoogleManagedProjectStorageRegionStatus.ofNotConfigured(
                BetaGoogleManagedProjectStorageRegionNotConfigured.of(
                    BetaGoogleManagedProjectEffectiveBucket.builder()
                        .bucketName("bucket_name")
                        .projectNumber("project_number")
                        .build()
                )
            )

        val roundtrippedBetaGoogleManagedProjectStorageRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageRegionStatus),
                jacksonTypeRef<BetaGoogleManagedProjectStorageRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageRegionStatus)
            .isEqualTo(betaGoogleManagedProjectStorageRegionStatus)
    }

    @Test
    fun ofPending() {
        val pending =
            BetaGoogleManagedProjectStorageRegionPending.builder()
                .effectiveBucket(
                    BetaGoogleManagedProjectEffectiveBucket.builder()
                        .bucketName("bucket_name")
                        .projectNumber("project_number")
                        .build()
                )
                .addFailedCheck(
                    BetaGoogleManagedProjectStorageFailedCheck.builder()
                        .check(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
                        .message("message")
                        .role(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
                        .build()
                )
                .build()

        val betaGoogleManagedProjectStorageRegionStatus =
            BetaGoogleManagedProjectStorageRegionStatus.ofPending(pending)

        assertThat(betaGoogleManagedProjectStorageRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.pending()).contains(pending)
        assertThat(betaGoogleManagedProjectStorageRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofPendingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageRegionStatus =
            BetaGoogleManagedProjectStorageRegionStatus.ofPending(
                BetaGoogleManagedProjectStorageRegionPending.builder()
                    .effectiveBucket(
                        BetaGoogleManagedProjectEffectiveBucket.builder()
                            .bucketName("bucket_name")
                            .projectNumber("project_number")
                            .build()
                    )
                    .addFailedCheck(
                        BetaGoogleManagedProjectStorageFailedCheck.builder()
                            .check(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
                            .message("message")
                            .role(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaGoogleManagedProjectStorageRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageRegionStatus),
                jacksonTypeRef<BetaGoogleManagedProjectStorageRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageRegionStatus)
            .isEqualTo(betaGoogleManagedProjectStorageRegionStatus)
    }

    @Test
    fun ofActive() {
        val active =
            BetaGoogleManagedProjectStorageRegionActive.builder()
                .effectiveBucket(
                    BetaGoogleManagedProjectEffectiveBucket.builder()
                        .bucketName("bucket_name")
                        .projectNumber("project_number")
                        .build()
                )
                .addFailedCheck(
                    BetaGoogleManagedProjectStorageFailedCheck.builder()
                        .check(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
                        .message("message")
                        .role(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
                        .build()
                )
                .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaGoogleManagedProjectStorageRegionStatus =
            BetaGoogleManagedProjectStorageRegionStatus.ofActive(active)

        assertThat(betaGoogleManagedProjectStorageRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.active()).contains(active)
        assertThat(betaGoogleManagedProjectStorageRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofActiveRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageRegionStatus =
            BetaGoogleManagedProjectStorageRegionStatus.ofActive(
                BetaGoogleManagedProjectStorageRegionActive.builder()
                    .effectiveBucket(
                        BetaGoogleManagedProjectEffectiveBucket.builder()
                            .bucketName("bucket_name")
                            .projectNumber("project_number")
                            .build()
                    )
                    .addFailedCheck(
                        BetaGoogleManagedProjectStorageFailedCheck.builder()
                            .check(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
                            .message("message")
                            .role(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
                            .build()
                    )
                    .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaGoogleManagedProjectStorageRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageRegionStatus),
                jacksonTypeRef<BetaGoogleManagedProjectStorageRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageRegionStatus)
            .isEqualTo(betaGoogleManagedProjectStorageRegionStatus)
    }

    @Test
    fun ofFailed() {
        val failed =
            BetaGoogleManagedProjectStorageRegionFailed.builder()
                .effectiveBucket(
                    BetaGoogleManagedProjectEffectiveBucket.builder()
                        .bucketName("bucket_name")
                        .projectNumber("project_number")
                        .build()
                )
                .addFailedCheck(
                    BetaGoogleManagedProjectStorageFailedCheck.builder()
                        .check(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
                        .message("message")
                        .role(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
                        .build()
                )
                .build()

        val betaGoogleManagedProjectStorageRegionStatus =
            BetaGoogleManagedProjectStorageRegionStatus.ofFailed(failed)

        assertThat(betaGoogleManagedProjectStorageRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.failed()).contains(failed)
        assertThat(betaGoogleManagedProjectStorageRegionStatus.suspended()).isEmpty
    }

    @Test
    fun ofFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageRegionStatus =
            BetaGoogleManagedProjectStorageRegionStatus.ofFailed(
                BetaGoogleManagedProjectStorageRegionFailed.builder()
                    .effectiveBucket(
                        BetaGoogleManagedProjectEffectiveBucket.builder()
                            .bucketName("bucket_name")
                            .projectNumber("project_number")
                            .build()
                    )
                    .addFailedCheck(
                        BetaGoogleManagedProjectStorageFailedCheck.builder()
                            .check(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
                            .message("message")
                            .role(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaGoogleManagedProjectStorageRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageRegionStatus),
                jacksonTypeRef<BetaGoogleManagedProjectStorageRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageRegionStatus)
            .isEqualTo(betaGoogleManagedProjectStorageRegionStatus)
    }

    @Test
    fun ofSuspended() {
        val suspended =
            BetaGoogleManagedProjectStorageRegionSuspended.of(
                BetaGoogleManagedProjectEffectiveBucket.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        val betaGoogleManagedProjectStorageRegionStatus =
            BetaGoogleManagedProjectStorageRegionStatus.ofSuspended(suspended)

        assertThat(betaGoogleManagedProjectStorageRegionStatus.notConfigured()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.pending()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.active()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.failed()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.suspended()).contains(suspended)
    }

    @Test
    fun ofSuspendedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageRegionStatus =
            BetaGoogleManagedProjectStorageRegionStatus.ofSuspended(
                BetaGoogleManagedProjectStorageRegionSuspended.of(
                    BetaGoogleManagedProjectEffectiveBucket.builder()
                        .bucketName("bucket_name")
                        .projectNumber("project_number")
                        .build()
                )
            )

        val roundtrippedBetaGoogleManagedProjectStorageRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageRegionStatus),
                jacksonTypeRef<BetaGoogleManagedProjectStorageRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageRegionStatus)
            .isEqualTo(betaGoogleManagedProjectStorageRegionStatus)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaGoogleManagedProjectStorageRegionStatus =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "effective_bucket" to
                                mapOf(
                                    "bucket_name" to "bucket_name",
                                    "project_number" to "project_number",
                                ),
                            "failed_checks" to
                                listOf(
                                    mapOf(
                                        "check" to "bucket_settings",
                                        "message" to "message",
                                        "role" to "ingest",
                                    )
                                ),
                        )
                    ),
                    jacksonTypeRef<BetaGoogleManagedProjectStorageRegionStatus>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedProjectStorageRegionStatus.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedProjectStorageRegionStatus.effectiveBucket())
            .contains(
                BetaGoogleManagedProjectEffectiveBucket.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )
        assertThat(betaGoogleManagedProjectStorageRegionStatus.failedChecks().getOrNull())
            .containsExactly(
                BetaGoogleManagedProjectStorageFailedCheck.builder()
                    .check(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
                    .message("message")
                    .role(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
                    .build()
            )

        val mismatchedBetaGoogleManagedProjectStorageRegionStatus =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "effective_bucket" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaGoogleManagedProjectStorageRegionStatus>(),
                )

        assertThat(mismatchedBetaGoogleManagedProjectStorageRegionStatus.effectiveBucket()).isEmpty
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
        val betaGoogleManagedProjectStorageRegionStatus =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaGoogleManagedProjectStorageRegionStatus>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedProjectStorageRegionStatus.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedProjectStorageRegionStatus.effectiveBucket()).isEmpty
        assertThat(betaGoogleManagedProjectStorageRegionStatus.failedChecks()).isEmpty
    }
}
