package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectBucketReferenceTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectBucketReference =
            BetaGoogleManagedProjectBucketReference.builder()
                .bucketName("bucket_name")
                .projectNumber("project_number")
                .build()

        assertThat(betaGoogleManagedProjectBucketReference.bucketName()).isEqualTo("bucket_name")
        assertThat(betaGoogleManagedProjectBucketReference.projectNumber())
            .isEqualTo("project_number")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectBucketReference =
            BetaGoogleManagedProjectBucketReference.builder()
                .bucketName("bucket_name")
                .projectNumber("project_number")
                .build()

        val roundtrippedBetaGoogleManagedProjectBucketReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectBucketReference),
                jacksonTypeRef<BetaGoogleManagedProjectBucketReference>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectBucketReference)
            .isEqualTo(betaGoogleManagedProjectBucketReference)
    }
}
