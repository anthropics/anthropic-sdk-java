package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedStorageEnabledTest {

    @Test
    fun create() {
        val betaGoogleManagedStorageEnabled =
            BetaGoogleManagedStorageEnabled.builder()
                .buckets(
                    BetaGoogleManagedStorageEnabled.Buckets.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(
                                mapOf(
                                    "bucket_name" to "bucket_name",
                                    "kms_key_name" to "kms_key_name",
                                    "location" to "location",
                                    "project_id" to "project_id",
                                    "project_number" to "project_number",
                                )
                            ),
                        )
                        .build()
                )
                .addRetiredBucket(
                    BetaGoogleManagedRetiredBucket.builder()
                        .bucketName("bucket_name")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .projectNumber("project_number")
                        .region("region")
                        .build()
                )
                .status(
                    BetaGoogleManagedStorageEnabled.Status.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(
                                mapOf(
                                    "effective_bucket" to
                                        mapOf(
                                            "bucket_name" to "bucket_name",
                                            "project_number" to "project_number",
                                        ),
                                    "type" to "not_configured",
                                )
                            ),
                        )
                        .build()
                )
                .build()

        assertThat(betaGoogleManagedStorageEnabled.buckets())
            .isEqualTo(
                BetaGoogleManagedStorageEnabled.Buckets.builder()
                    .putAdditionalProperty(
                        "foo",
                        JsonValue.from(
                            mapOf(
                                "bucket_name" to "bucket_name",
                                "kms_key_name" to "kms_key_name",
                                "location" to "location",
                                "project_id" to "project_id",
                                "project_number" to "project_number",
                            )
                        ),
                    )
                    .build()
            )
        assertThat(betaGoogleManagedStorageEnabled.retiredBuckets())
            .containsExactly(
                BetaGoogleManagedRetiredBucket.builder()
                    .bucketName("bucket_name")
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .projectNumber("project_number")
                    .region("region")
                    .build()
            )
        assertThat(betaGoogleManagedStorageEnabled.status())
            .isEqualTo(
                BetaGoogleManagedStorageEnabled.Status.builder()
                    .putAdditionalProperty(
                        "foo",
                        JsonValue.from(
                            mapOf(
                                "effective_bucket" to
                                    mapOf(
                                        "bucket_name" to "bucket_name",
                                        "project_number" to "project_number",
                                    ),
                                "type" to "not_configured",
                            )
                        ),
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageEnabled =
            BetaGoogleManagedStorageEnabled.builder()
                .buckets(
                    BetaGoogleManagedStorageEnabled.Buckets.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(
                                mapOf(
                                    "bucket_name" to "bucket_name",
                                    "kms_key_name" to "kms_key_name",
                                    "location" to "location",
                                    "project_id" to "project_id",
                                    "project_number" to "project_number",
                                )
                            ),
                        )
                        .build()
                )
                .addRetiredBucket(
                    BetaGoogleManagedRetiredBucket.builder()
                        .bucketName("bucket_name")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .projectNumber("project_number")
                        .region("region")
                        .build()
                )
                .status(
                    BetaGoogleManagedStorageEnabled.Status.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(
                                mapOf(
                                    "effective_bucket" to
                                        mapOf(
                                            "bucket_name" to "bucket_name",
                                            "project_number" to "project_number",
                                        ),
                                    "type" to "not_configured",
                                )
                            ),
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaGoogleManagedStorageEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageEnabled),
                jacksonTypeRef<BetaGoogleManagedStorageEnabled>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageEnabled)
            .isEqualTo(betaGoogleManagedStorageEnabled)
    }
}
