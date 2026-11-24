package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaGoogleManagedProjectStorageControlTest {

    @Test
    fun ofDisabled() {
        val disabled =
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

        val betaGoogleManagedProjectStorageControl =
            BetaGoogleManagedProjectStorageControl.ofDisabled(disabled)

        assertThat(betaGoogleManagedProjectStorageControl.disabled()).contains(disabled)
        assertThat(betaGoogleManagedProjectStorageControl.enabled()).isEmpty
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageControl =
            BetaGoogleManagedProjectStorageControl.ofDisabled(
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
            )

        val roundtrippedBetaGoogleManagedProjectStorageControl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageControl),
                jacksonTypeRef<BetaGoogleManagedProjectStorageControl>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageControl)
            .isEqualTo(betaGoogleManagedProjectStorageControl)
    }

    @Test
    fun ofEnabled() {
        val enabled =
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

        val betaGoogleManagedProjectStorageControl =
            BetaGoogleManagedProjectStorageControl.ofEnabled(enabled)

        assertThat(betaGoogleManagedProjectStorageControl.disabled()).isEmpty
        assertThat(betaGoogleManagedProjectStorageControl.enabled()).contains(enabled)
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageControl =
            BetaGoogleManagedProjectStorageControl.ofEnabled(
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
            )

        val roundtrippedBetaGoogleManagedProjectStorageControl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageControl),
                jacksonTypeRef<BetaGoogleManagedProjectStorageControl>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageControl)
            .isEqualTo(betaGoogleManagedProjectStorageControl)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaGoogleManagedProjectStorageControl =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "retired_buckets" to
                                listOf(
                                    mapOf(
                                        "bucket_name" to "bucket_name",
                                        "expires_at" to "2019-12-27T18:11:19.117Z",
                                        "project_number" to "project_number",
                                        "region" to "us",
                                    )
                                ),
                        )
                    ),
                    jacksonTypeRef<BetaGoogleManagedProjectStorageControl>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedProjectStorageControl.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedProjectStorageControl.retiredBuckets())
            .containsExactly(
                BetaGoogleManagedProjectRetiredBucket.builder()
                    .bucketName("bucket_name")
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .projectNumber("project_number")
                    .region("us")
                    .build()
            )
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaGoogleManagedProjectStorageControl =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaGoogleManagedProjectStorageControl>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedProjectStorageControl.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> {
            betaGoogleManagedProjectStorageControl.retiredBuckets()
        }
    }
}
