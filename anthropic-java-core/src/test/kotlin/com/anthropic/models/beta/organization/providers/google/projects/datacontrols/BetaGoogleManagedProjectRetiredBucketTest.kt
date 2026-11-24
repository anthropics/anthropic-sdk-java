package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectRetiredBucketTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectRetiredBucket =
            BetaGoogleManagedProjectRetiredBucket.builder()
                .bucketName("bucket_name")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .projectNumber("project_number")
                .region("us")
                .build()

        assertThat(betaGoogleManagedProjectRetiredBucket.bucketName()).isEqualTo("bucket_name")
        assertThat(betaGoogleManagedProjectRetiredBucket.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaGoogleManagedProjectRetiredBucket.projectNumber())
            .isEqualTo("project_number")
        assertThat(betaGoogleManagedProjectRetiredBucket.region()).isEqualTo("us")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectRetiredBucket =
            BetaGoogleManagedProjectRetiredBucket.builder()
                .bucketName("bucket_name")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .projectNumber("project_number")
                .region("us")
                .build()

        val roundtrippedBetaGoogleManagedProjectRetiredBucket =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectRetiredBucket),
                jacksonTypeRef<BetaGoogleManagedProjectRetiredBucket>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectRetiredBucket)
            .isEqualTo(betaGoogleManagedProjectRetiredBucket)
    }
}
