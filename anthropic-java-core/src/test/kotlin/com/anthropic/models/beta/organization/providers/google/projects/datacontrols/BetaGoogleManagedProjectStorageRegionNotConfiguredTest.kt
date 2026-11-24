package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectStorageRegionNotConfiguredTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectStorageRegionNotConfigured =
            BetaGoogleManagedProjectStorageRegionNotConfigured.of(
                BetaGoogleManagedProjectEffectiveBucket.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        assertThat(betaGoogleManagedProjectStorageRegionNotConfigured.effectiveBucket())
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
        val betaGoogleManagedProjectStorageRegionNotConfigured =
            BetaGoogleManagedProjectStorageRegionNotConfigured.of(
                BetaGoogleManagedProjectEffectiveBucket.builder()
                    .bucketName("bucket_name")
                    .projectNumber("project_number")
                    .build()
            )

        val roundtrippedBetaGoogleManagedProjectStorageRegionNotConfigured =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageRegionNotConfigured),
                jacksonTypeRef<BetaGoogleManagedProjectStorageRegionNotConfigured>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageRegionNotConfigured)
            .isEqualTo(betaGoogleManagedProjectStorageRegionNotConfigured)
    }
}
