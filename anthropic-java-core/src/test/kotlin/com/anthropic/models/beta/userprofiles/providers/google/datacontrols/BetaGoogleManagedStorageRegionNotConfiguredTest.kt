package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedStorageRegionNotConfiguredTest {

    @Test
    fun create() {
        val betaGoogleManagedStorageRegionNotConfigured =
            BetaGoogleManagedStorageRegionNotConfigured.of(
                BetaGoogleManagedBucketReference.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        assertThat(betaGoogleManagedStorageRegionNotConfigured.effectiveBucket())
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
        val betaGoogleManagedStorageRegionNotConfigured =
            BetaGoogleManagedStorageRegionNotConfigured.of(
                BetaGoogleManagedBucketReference.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        val roundtrippedBetaGoogleManagedStorageRegionNotConfigured =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageRegionNotConfigured),
                jacksonTypeRef<BetaGoogleManagedStorageRegionNotConfigured>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageRegionNotConfigured)
            .isEqualTo(betaGoogleManagedStorageRegionNotConfigured)
    }
}
