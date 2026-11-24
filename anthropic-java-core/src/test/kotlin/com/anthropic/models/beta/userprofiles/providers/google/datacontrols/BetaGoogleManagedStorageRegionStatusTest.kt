package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Optional
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaGoogleManagedStorageRegionStatusTest {

    @Test
    fun ofNotConfigured() {
        val notConfigured =
            BetaGoogleManagedStorageRegionNotConfigured.of(
                BetaGoogleManagedBucketReference.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        val betaGoogleManagedStorageRegionStatus =
            BetaGoogleManagedStorageRegionStatus.ofNotConfigured(notConfigured)

        assertThat(presentVariants(betaGoogleManagedStorageRegionStatus))
            .isEqualTo(mapOf("notConfigured" to Optional.of(notConfigured)))
    }

    @Test
    fun ofNotConfiguredRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageRegionStatus =
            BetaGoogleManagedStorageRegionStatus.ofNotConfigured(
                BetaGoogleManagedStorageRegionNotConfigured.of(
                    BetaGoogleManagedBucketReference.builder()
                        .bucketName("bucket_name")
                        .projectNumber("project_number")
                        .build()
                )
            )

        val roundtrippedBetaGoogleManagedStorageRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageRegionStatus),
                jacksonTypeRef<BetaGoogleManagedStorageRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageRegionStatus)
            .isEqualTo(betaGoogleManagedStorageRegionStatus)
    }

    @Test
    fun ofPending() {
        val pending =
            BetaGoogleManagedStorageRegionPending.builder()
                .effectiveBucket(
                    BetaGoogleManagedBucketReference.builder()
                        .bucketName("bucket_name")
                        .projectNumber("project_number")
                        .build()
                )
                .addFailedCheck(
                    BetaGoogleManagedStorageFailedCheck.builder()
                        .check(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
                        .message("message")
                        .role(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
                        .build()
                )
                .build()

        val betaGoogleManagedStorageRegionStatus =
            BetaGoogleManagedStorageRegionStatus.ofPending(pending)

        assertThat(presentVariants(betaGoogleManagedStorageRegionStatus))
            .isEqualTo(mapOf("pending" to Optional.of(pending)))
    }

    @Test
    fun ofPendingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageRegionStatus =
            BetaGoogleManagedStorageRegionStatus.ofPending(
                BetaGoogleManagedStorageRegionPending.builder()
                    .effectiveBucket(
                        BetaGoogleManagedBucketReference.builder()
                            .bucketName("bucket_name")
                            .projectNumber("project_number")
                            .build()
                    )
                    .addFailedCheck(
                        BetaGoogleManagedStorageFailedCheck.builder()
                            .check(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
                            .message("message")
                            .role(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaGoogleManagedStorageRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageRegionStatus),
                jacksonTypeRef<BetaGoogleManagedStorageRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageRegionStatus)
            .isEqualTo(betaGoogleManagedStorageRegionStatus)
    }

    @Test
    fun ofActive() {
        val active =
            BetaGoogleManagedStorageRegionActive.builder()
                .effectiveBucket(
                    BetaGoogleManagedBucketReference.builder()
                        .bucketName("bucket_name")
                        .projectNumber("project_number")
                        .build()
                )
                .addFailedCheck(
                    BetaGoogleManagedStorageFailedCheck.builder()
                        .check(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
                        .message("message")
                        .role(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
                        .build()
                )
                .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaGoogleManagedStorageRegionStatus =
            BetaGoogleManagedStorageRegionStatus.ofActive(active)

        assertThat(presentVariants(betaGoogleManagedStorageRegionStatus))
            .isEqualTo(mapOf("active" to Optional.of(active)))
    }

    @Test
    fun ofActiveRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageRegionStatus =
            BetaGoogleManagedStorageRegionStatus.ofActive(
                BetaGoogleManagedStorageRegionActive.builder()
                    .effectiveBucket(
                        BetaGoogleManagedBucketReference.builder()
                            .bucketName("bucket_name")
                            .projectNumber("project_number")
                            .build()
                    )
                    .addFailedCheck(
                        BetaGoogleManagedStorageFailedCheck.builder()
                            .check(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
                            .message("message")
                            .role(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
                            .build()
                    )
                    .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaGoogleManagedStorageRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageRegionStatus),
                jacksonTypeRef<BetaGoogleManagedStorageRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageRegionStatus)
            .isEqualTo(betaGoogleManagedStorageRegionStatus)
    }

    @Test
    fun ofFailed() {
        val failed =
            BetaGoogleManagedStorageRegionFailed.builder()
                .effectiveBucket(
                    BetaGoogleManagedBucketReference.builder()
                        .bucketName("bucket_name")
                        .projectNumber("project_number")
                        .build()
                )
                .addFailedCheck(
                    BetaGoogleManagedStorageFailedCheck.builder()
                        .check(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
                        .message("message")
                        .role(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
                        .build()
                )
                .build()

        val betaGoogleManagedStorageRegionStatus =
            BetaGoogleManagedStorageRegionStatus.ofFailed(failed)

        assertThat(presentVariants(betaGoogleManagedStorageRegionStatus))
            .isEqualTo(mapOf("failed" to Optional.of(failed)))
    }

    @Test
    fun ofFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageRegionStatus =
            BetaGoogleManagedStorageRegionStatus.ofFailed(
                BetaGoogleManagedStorageRegionFailed.builder()
                    .effectiveBucket(
                        BetaGoogleManagedBucketReference.builder()
                            .bucketName("bucket_name")
                            .projectNumber("project_number")
                            .build()
                    )
                    .addFailedCheck(
                        BetaGoogleManagedStorageFailedCheck.builder()
                            .check(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
                            .message("message")
                            .role(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaGoogleManagedStorageRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageRegionStatus),
                jacksonTypeRef<BetaGoogleManagedStorageRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageRegionStatus)
            .isEqualTo(betaGoogleManagedStorageRegionStatus)
    }

    @Test
    fun ofSuspended() {
        val suspended =
            BetaGoogleManagedStorageRegionSuspended.of(
                BetaGoogleManagedBucketReference.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        val betaGoogleManagedStorageRegionStatus =
            BetaGoogleManagedStorageRegionStatus.ofSuspended(suspended)

        assertThat(presentVariants(betaGoogleManagedStorageRegionStatus))
            .isEqualTo(mapOf("suspended" to Optional.of(suspended)))
    }

    @Test
    fun ofSuspendedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageRegionStatus =
            BetaGoogleManagedStorageRegionStatus.ofSuspended(
                BetaGoogleManagedStorageRegionSuspended.of(
                    BetaGoogleManagedBucketReference.builder()
                        .bucketName("bucket_name")
                        .projectNumber("project_number")
                        .build()
                )
            )

        val roundtrippedBetaGoogleManagedStorageRegionStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageRegionStatus),
                jacksonTypeRef<BetaGoogleManagedStorageRegionStatus>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageRegionStatus)
            .isEqualTo(betaGoogleManagedStorageRegionStatus)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaGoogleManagedStorageRegionStatus =
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
                                        "check" to "label_mismatch",
                                        "message" to "message",
                                        "role" to "ingest",
                                    )
                                ),
                        )
                    ),
                    jacksonTypeRef<BetaGoogleManagedStorageRegionStatus>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedStorageRegionStatus.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedStorageRegionStatus.effectiveBucket())
            .contains(
                BetaGoogleManagedBucketReference.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )
        assertThat(betaGoogleManagedStorageRegionStatus.failedChecks().getOrNull())
            .containsExactly(
                BetaGoogleManagedStorageFailedCheck.builder()
                    .check(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
                    .message("message")
                    .role(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
                    .build()
            )

        val mismatchedBetaGoogleManagedStorageRegionStatus =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "effective_bucket" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaGoogleManagedStorageRegionStatus>(),
                )

        assertThat(mismatchedBetaGoogleManagedStorageRegionStatus.effectiveBucket()).isEmpty
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
        val betaGoogleManagedStorageRegionStatus =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaGoogleManagedStorageRegionStatus>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedStorageRegionStatus.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedStorageRegionStatus.effectiveBucket()).isEmpty
        assertThat(betaGoogleManagedStorageRegionStatus.failedChecks()).isEmpty
    }

    private fun presentVariants(
        betaGoogleManagedStorageRegionStatus: BetaGoogleManagedStorageRegionStatus
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "notConfigured" to betaGoogleManagedStorageRegionStatus.notConfigured(),
                "pending" to betaGoogleManagedStorageRegionStatus.pending(),
                "active" to betaGoogleManagedStorageRegionStatus.active(),
                "failed" to betaGoogleManagedStorageRegionStatus.failed(),
                "suspended" to betaGoogleManagedStorageRegionStatus.suspended(),
            )
            .filterValues { it.isPresent }
}
