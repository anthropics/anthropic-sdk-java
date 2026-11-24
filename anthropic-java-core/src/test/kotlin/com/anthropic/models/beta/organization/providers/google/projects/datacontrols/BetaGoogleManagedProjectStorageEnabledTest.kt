package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectStorageEnabledTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectStorageEnabled =
            BetaGoogleManagedProjectStorageEnabled.builder()
                .buckets(
                    BetaGoogleManagedProjectStorageEnabled.Buckets.builder()
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
                    BetaGoogleManagedProjectRetiredBucket.builder()
                        .bucketName("bucket_name")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .projectNumber("project_number")
                        .region("us")
                        .build()
                )
                .status(
                    BetaGoogleManagedProjectStorageEnabled.Status.builder()
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

        assertThat(betaGoogleManagedProjectStorageEnabled.buckets())
            .isEqualTo(
                BetaGoogleManagedProjectStorageEnabled.Buckets.builder()
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
        assertThat(betaGoogleManagedProjectStorageEnabled.retiredBuckets())
            .containsExactly(
                BetaGoogleManagedProjectRetiredBucket.builder()
                    .bucketName("bucket_name")
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .projectNumber("project_number")
                    .region("us")
                    .build()
            )
        assertThat(betaGoogleManagedProjectStorageEnabled.status())
            .isEqualTo(
                BetaGoogleManagedProjectStorageEnabled.Status.builder()
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
        val betaGoogleManagedProjectStorageEnabled =
            BetaGoogleManagedProjectStorageEnabled.builder()
                .buckets(
                    BetaGoogleManagedProjectStorageEnabled.Buckets.builder()
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
                    BetaGoogleManagedProjectRetiredBucket.builder()
                        .bucketName("bucket_name")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .projectNumber("project_number")
                        .region("us")
                        .build()
                )
                .status(
                    BetaGoogleManagedProjectStorageEnabled.Status.builder()
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

        val roundtrippedBetaGoogleManagedProjectStorageEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageEnabled),
                jacksonTypeRef<BetaGoogleManagedProjectStorageEnabled>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageEnabled)
            .isEqualTo(betaGoogleManagedProjectStorageEnabled)
    }
}
