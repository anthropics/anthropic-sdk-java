package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectStorageRegionFailedTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectStorageRegionFailed =
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

        assertThat(betaGoogleManagedProjectStorageRegionFailed.effectiveBucket())
            .contains(
                BetaGoogleManagedProjectEffectiveBucket.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )
        assertThat(betaGoogleManagedProjectStorageRegionFailed.failedChecks())
            .containsExactly(
                BetaGoogleManagedProjectStorageFailedCheck.builder()
                    .check(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
                    .message("message")
                    .role(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageRegionFailed =
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

        val roundtrippedBetaGoogleManagedProjectStorageRegionFailed =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageRegionFailed),
                jacksonTypeRef<BetaGoogleManagedProjectStorageRegionFailed>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageRegionFailed)
            .isEqualTo(betaGoogleManagedProjectStorageRegionFailed)
    }
}
