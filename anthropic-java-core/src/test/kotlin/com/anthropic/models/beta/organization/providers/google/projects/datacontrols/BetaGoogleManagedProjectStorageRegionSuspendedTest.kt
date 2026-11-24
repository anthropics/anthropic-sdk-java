package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectStorageRegionSuspendedTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectStorageRegionSuspended =
            BetaGoogleManagedProjectStorageRegionSuspended.of(
                BetaGoogleManagedProjectEffectiveBucket.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        assertThat(betaGoogleManagedProjectStorageRegionSuspended.effectiveBucket())
            .contains(
                BetaGoogleManagedProjectEffectiveBucket.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageRegionSuspended =
            BetaGoogleManagedProjectStorageRegionSuspended.of(
                BetaGoogleManagedProjectEffectiveBucket.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        val roundtrippedBetaGoogleManagedProjectStorageRegionSuspended =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageRegionSuspended),
                jacksonTypeRef<BetaGoogleManagedProjectStorageRegionSuspended>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageRegionSuspended)
            .isEqualTo(betaGoogleManagedProjectStorageRegionSuspended)
    }
}
