package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedStorageRegionPendingTest {

    @Test
    fun create() {
        val betaGoogleManagedStorageRegionPending =
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

        assertThat(betaGoogleManagedStorageRegionPending.effectiveBucket())
            .contains(
                BetaGoogleManagedBucketReference.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )
        assertThat(betaGoogleManagedStorageRegionPending.failedChecks())
            .containsExactly(
                BetaGoogleManagedStorageFailedCheck.builder()
                    .check(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
                    .message("message")
                    .role(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageRegionPending =
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

        val roundtrippedBetaGoogleManagedStorageRegionPending =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageRegionPending),
                jacksonTypeRef<BetaGoogleManagedStorageRegionPending>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageRegionPending)
            .isEqualTo(betaGoogleManagedStorageRegionPending)
    }
}
