package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectEffectiveBucketTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectEffectiveBucket =
            BetaGoogleManagedProjectEffectiveBucket.builder()
                .bucketName("bucket_name")
                .projectNumber("project_number")
                .build()

        assertThat(betaGoogleManagedProjectEffectiveBucket.bucketName()).isEqualTo("bucket_name")
        assertThat(betaGoogleManagedProjectEffectiveBucket.projectNumber())
            .isEqualTo("project_number")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectEffectiveBucket =
            BetaGoogleManagedProjectEffectiveBucket.builder()
                .bucketName("bucket_name")
                .projectNumber("project_number")
                .build()

        val roundtrippedBetaGoogleManagedProjectEffectiveBucket =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectEffectiveBucket),
                jacksonTypeRef<BetaGoogleManagedProjectEffectiveBucket>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectEffectiveBucket)
            .isEqualTo(betaGoogleManagedProjectEffectiveBucket)
    }
}
