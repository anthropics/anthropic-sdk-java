package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedStorageRegionActiveTest {

    @Test
    fun create() {
        val betaGoogleManagedStorageRegionActive =
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

        assertThat(betaGoogleManagedStorageRegionActive.effectiveBucket())
            .contains(
                BetaGoogleManagedBucketReference.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )
        assertThat(betaGoogleManagedStorageRegionActive.failedChecks())
            .containsExactly(
                BetaGoogleManagedStorageFailedCheck.builder()
                    .check(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
                    .message("message")
                    .role(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
                    .build()
            )
        assertThat(betaGoogleManagedStorageRegionActive.validatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageRegionActive =
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

        val roundtrippedBetaGoogleManagedStorageRegionActive =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageRegionActive),
                jacksonTypeRef<BetaGoogleManagedStorageRegionActive>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageRegionActive)
            .isEqualTo(betaGoogleManagedStorageRegionActive)
    }
}
