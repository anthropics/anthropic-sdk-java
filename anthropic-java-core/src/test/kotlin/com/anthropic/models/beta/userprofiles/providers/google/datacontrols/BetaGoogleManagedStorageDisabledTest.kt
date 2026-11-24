package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedStorageDisabledTest {

    @Test
    fun create() {
        val betaGoogleManagedStorageDisabled =
            BetaGoogleManagedStorageDisabled.builder()
                .addRetiredBucket(
                    BetaGoogleManagedRetiredBucket.builder()
                        .bucketName("bucket_name")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .projectNumber("project_number")
                        .region("region")
                        .build()
                )
                .build()

        assertThat(betaGoogleManagedStorageDisabled.retiredBuckets())
            .containsExactly(
                BetaGoogleManagedRetiredBucket.builder()
                    .bucketName("bucket_name")
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .projectNumber("project_number")
                    .region("region")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageDisabled =
            BetaGoogleManagedStorageDisabled.builder()
                .addRetiredBucket(
                    BetaGoogleManagedRetiredBucket.builder()
                        .bucketName("bucket_name")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .projectNumber("project_number")
                        .region("region")
                        .build()
                )
                .build()

        val roundtrippedBetaGoogleManagedStorageDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageDisabled),
                jacksonTypeRef<BetaGoogleManagedStorageDisabled>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageDisabled)
            .isEqualTo(betaGoogleManagedStorageDisabled)
    }
}
