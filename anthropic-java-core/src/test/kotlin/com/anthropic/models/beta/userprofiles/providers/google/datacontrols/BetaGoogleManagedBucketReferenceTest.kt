package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedBucketReferenceTest {

    @Test
    fun create() {
        val betaGoogleManagedBucketReference =
            BetaGoogleManagedBucketReference.builder()
                .bucketName("bucket_name")
                .projectNumber("project_number")
                .build()

        assertThat(betaGoogleManagedBucketReference.bucketName()).isEqualTo("bucket_name")
        assertThat(betaGoogleManagedBucketReference.projectNumber()).isEqualTo("project_number")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedBucketReference =
            BetaGoogleManagedBucketReference.builder()
                .bucketName("bucket_name")
                .projectNumber("project_number")
                .build()

        val roundtrippedBetaGoogleManagedBucketReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedBucketReference),
                jacksonTypeRef<BetaGoogleManagedBucketReference>(),
            )

        assertThat(roundtrippedBetaGoogleManagedBucketReference)
            .isEqualTo(betaGoogleManagedBucketReference)
    }
}
