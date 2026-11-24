package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedStorageBucketTest {

    @Test
    fun create() {
        val betaGoogleManagedStorageBucket =
            BetaGoogleManagedStorageBucket.builder()
                .bucketName("bucket_name")
                .kmsKeyName("kms_key_name")
                .location("location")
                .projectId("project_id")
                .projectNumber("project_number")
                .build()

        assertThat(betaGoogleManagedStorageBucket.bucketName()).isEqualTo("bucket_name")
        assertThat(betaGoogleManagedStorageBucket.kmsKeyName()).contains("kms_key_name")
        assertThat(betaGoogleManagedStorageBucket.location()).contains("location")
        assertThat(betaGoogleManagedStorageBucket.projectId()).contains("project_id")
        assertThat(betaGoogleManagedStorageBucket.projectNumber()).isEqualTo("project_number")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageBucket =
            BetaGoogleManagedStorageBucket.builder()
                .bucketName("bucket_name")
                .kmsKeyName("kms_key_name")
                .location("location")
                .projectId("project_id")
                .projectNumber("project_number")
                .build()

        val roundtrippedBetaGoogleManagedStorageBucket =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageBucket),
                jacksonTypeRef<BetaGoogleManagedStorageBucket>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageBucket)
            .isEqualTo(betaGoogleManagedStorageBucket)
    }
}
