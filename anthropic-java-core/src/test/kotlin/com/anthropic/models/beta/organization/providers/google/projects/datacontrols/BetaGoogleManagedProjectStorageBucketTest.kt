package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectStorageBucketTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectStorageBucket =
            BetaGoogleManagedProjectStorageBucket.builder()
                .bucketName("bucket_name")
                .kmsKeyName("kms_key_name")
                .location("location")
                .projectId("project_id")
                .projectNumber("project_number")
                .build()

        assertThat(betaGoogleManagedProjectStorageBucket.bucketName()).isEqualTo("bucket_name")
        assertThat(betaGoogleManagedProjectStorageBucket.kmsKeyName()).contains("kms_key_name")
        assertThat(betaGoogleManagedProjectStorageBucket.location()).contains("location")
        assertThat(betaGoogleManagedProjectStorageBucket.projectId()).contains("project_id")
        assertThat(betaGoogleManagedProjectStorageBucket.projectNumber())
            .isEqualTo("project_number")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageBucket =
            BetaGoogleManagedProjectStorageBucket.builder()
                .bucketName("bucket_name")
                .kmsKeyName("kms_key_name")
                .location("location")
                .projectId("project_id")
                .projectNumber("project_number")
                .build()

        val roundtrippedBetaGoogleManagedProjectStorageBucket =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageBucket),
                jacksonTypeRef<BetaGoogleManagedProjectStorageBucket>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageBucket)
            .isEqualTo(betaGoogleManagedProjectStorageBucket)
    }
}
