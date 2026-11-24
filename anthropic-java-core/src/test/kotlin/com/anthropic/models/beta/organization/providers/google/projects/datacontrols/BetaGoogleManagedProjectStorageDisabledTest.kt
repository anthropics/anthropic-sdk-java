package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectStorageDisabledTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectStorageDisabled =
            BetaGoogleManagedProjectStorageDisabled.builder()
                .addRetiredBucket(
                    BetaGoogleManagedProjectRetiredBucket.builder()
                        .bucketName("bucket_name")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .projectNumber("project_number")
                        .region("us")
                        .build()
                )
                .build()

        assertThat(betaGoogleManagedProjectStorageDisabled.retiredBuckets())
            .containsExactly(
                BetaGoogleManagedProjectRetiredBucket.builder()
                    .bucketName("bucket_name")
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .projectNumber("project_number")
                    .region("us")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageDisabled =
            BetaGoogleManagedProjectStorageDisabled.builder()
                .addRetiredBucket(
                    BetaGoogleManagedProjectRetiredBucket.builder()
                        .bucketName("bucket_name")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .projectNumber("project_number")
                        .region("us")
                        .build()
                )
                .build()

        val roundtrippedBetaGoogleManagedProjectStorageDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageDisabled),
                jacksonTypeRef<BetaGoogleManagedProjectStorageDisabled>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageDisabled)
            .isEqualTo(betaGoogleManagedProjectStorageDisabled)
    }
}
