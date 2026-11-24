package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedRetiredBucketTest {

    @Test
    fun create() {
        val betaGoogleManagedRetiredBucket =
            BetaGoogleManagedRetiredBucket.builder()
                .bucketName("bucket_name")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .projectNumber("project_number")
                .region("region")
                .build()

        assertThat(betaGoogleManagedRetiredBucket.bucketName()).isEqualTo("bucket_name")
        assertThat(betaGoogleManagedRetiredBucket.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaGoogleManagedRetiredBucket.projectNumber()).isEqualTo("project_number")
        assertThat(betaGoogleManagedRetiredBucket.region()).isEqualTo("region")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedRetiredBucket =
            BetaGoogleManagedRetiredBucket.builder()
                .bucketName("bucket_name")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .projectNumber("project_number")
                .region("region")
                .build()

        val roundtrippedBetaGoogleManagedRetiredBucket =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedRetiredBucket),
                jacksonTypeRef<BetaGoogleManagedRetiredBucket>(),
            )

        assertThat(roundtrippedBetaGoogleManagedRetiredBucket)
            .isEqualTo(betaGoogleManagedRetiredBucket)
    }
}
