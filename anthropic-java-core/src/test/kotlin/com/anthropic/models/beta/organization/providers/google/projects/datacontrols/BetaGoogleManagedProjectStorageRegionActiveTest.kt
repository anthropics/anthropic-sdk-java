package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectStorageRegionActiveTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectStorageRegionActive =
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

        assertThat(betaGoogleManagedProjectStorageRegionActive.effectiveBucket())
            .contains(
                BetaGoogleManagedProjectEffectiveBucket.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )
        assertThat(betaGoogleManagedProjectStorageRegionActive.failedChecks())
            .containsExactly(
                BetaGoogleManagedProjectStorageFailedCheck.builder()
                    .check(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
                    .message("message")
                    .role(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
                    .build()
            )
        assertThat(betaGoogleManagedProjectStorageRegionActive.validatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageRegionActive =
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

        val roundtrippedBetaGoogleManagedProjectStorageRegionActive =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageRegionActive),
                jacksonTypeRef<BetaGoogleManagedProjectStorageRegionActive>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageRegionActive)
            .isEqualTo(betaGoogleManagedProjectStorageRegionActive)
    }
}
