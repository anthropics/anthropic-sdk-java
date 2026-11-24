package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

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

internal class BetaGoogleManagedStorageControlTest {

    @Test
    fun ofDisabled() {
        val disabled =
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

        val betaGoogleManagedStorageControl = BetaGoogleManagedStorageControl.ofDisabled(disabled)

        assertThat(betaGoogleManagedStorageControl.disabled()).contains(disabled)
        assertThat(betaGoogleManagedStorageControl.enabled()).isEmpty
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageControl =
            BetaGoogleManagedStorageControl.ofDisabled(
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
            )

        val roundtrippedBetaGoogleManagedStorageControl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageControl),
                jacksonTypeRef<BetaGoogleManagedStorageControl>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageControl)
            .isEqualTo(betaGoogleManagedStorageControl)
    }

    @Test
    fun ofEnabled() {
        val enabled =
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

        val betaGoogleManagedStorageControl = BetaGoogleManagedStorageControl.ofEnabled(enabled)

        assertThat(betaGoogleManagedStorageControl.disabled()).isEmpty
        assertThat(betaGoogleManagedStorageControl.enabled()).contains(enabled)
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageControl =
            BetaGoogleManagedStorageControl.ofEnabled(
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
            )

        val roundtrippedBetaGoogleManagedStorageControl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageControl),
                jacksonTypeRef<BetaGoogleManagedStorageControl>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageControl)
            .isEqualTo(betaGoogleManagedStorageControl)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaGoogleManagedStorageControl =
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
                                        "region" to "region",
                                    )
                                ),
                        )
                    ),
                    jacksonTypeRef<BetaGoogleManagedStorageControl>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedStorageControl.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedStorageControl.retiredBuckets())
            .containsExactly(
                BetaGoogleManagedRetiredBucket.builder()
                    .bucketName("bucket_name")
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .projectNumber("project_number")
                    .region("region")
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
        val betaGoogleManagedStorageControl =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaGoogleManagedStorageControl>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedStorageControl.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> {
            betaGoogleManagedStorageControl.retiredBuckets()
        }
    }
}
