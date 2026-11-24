package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedStorageRegionFailedTest {

    @Test
    fun create() {
        val betaGoogleManagedStorageRegionFailed =
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

        assertThat(betaGoogleManagedStorageRegionFailed.effectiveBucket())
            .contains(
                BetaGoogleManagedBucketReference.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )
        assertThat(betaGoogleManagedStorageRegionFailed.failedChecks())
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
        val betaGoogleManagedStorageRegionFailed =
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

        val roundtrippedBetaGoogleManagedStorageRegionFailed =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageRegionFailed),
                jacksonTypeRef<BetaGoogleManagedStorageRegionFailed>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageRegionFailed)
            .isEqualTo(betaGoogleManagedStorageRegionFailed)
    }
}
