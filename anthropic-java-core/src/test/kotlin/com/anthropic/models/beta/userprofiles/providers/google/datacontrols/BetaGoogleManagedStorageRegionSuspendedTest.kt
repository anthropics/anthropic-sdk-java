package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedStorageRegionSuspendedTest {

    @Test
    fun create() {
        val betaGoogleManagedStorageRegionSuspended =
            BetaGoogleManagedStorageRegionSuspended.of(
                BetaGoogleManagedBucketReference.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        assertThat(betaGoogleManagedStorageRegionSuspended.effectiveBucket())
            .contains(
                BetaGoogleManagedBucketReference.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageRegionSuspended =
            BetaGoogleManagedStorageRegionSuspended.of(
                BetaGoogleManagedBucketReference.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        val roundtrippedBetaGoogleManagedStorageRegionSuspended =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageRegionSuspended),
                jacksonTypeRef<BetaGoogleManagedStorageRegionSuspended>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageRegionSuspended)
            .isEqualTo(betaGoogleManagedStorageRegionSuspended)
    }
}
