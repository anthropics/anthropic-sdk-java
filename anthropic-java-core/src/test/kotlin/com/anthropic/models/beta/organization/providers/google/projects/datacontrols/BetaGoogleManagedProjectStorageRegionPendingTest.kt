package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectStorageRegionPendingTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectStorageRegionPending =
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

        assertThat(betaGoogleManagedProjectStorageRegionPending.effectiveBucket())
            .contains(
                BetaGoogleManagedProjectEffectiveBucket.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )
        assertThat(betaGoogleManagedProjectStorageRegionPending.failedChecks())
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
        val betaGoogleManagedProjectStorageRegionPending =
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

        val roundtrippedBetaGoogleManagedProjectStorageRegionPending =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageRegionPending),
                jacksonTypeRef<BetaGoogleManagedProjectStorageRegionPending>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageRegionPending)
            .isEqualTo(betaGoogleManagedProjectStorageRegionPending)
    }
}
