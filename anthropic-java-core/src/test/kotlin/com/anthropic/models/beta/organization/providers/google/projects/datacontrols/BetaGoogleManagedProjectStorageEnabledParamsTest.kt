package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectStorageEnabledParamsTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectStorageEnabledParams =
            BetaGoogleManagedProjectStorageEnabledParams.builder()
                .buckets(
                    BetaGoogleManagedProjectStorageEnabledParams.Buckets.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(
                                mapOf(
                                    "bucket_name" to "bucket_name",
                                    "project_number" to "project_number",
                                )
                            ),
                        )
                        .build()
                )
                .build()

        assertThat(betaGoogleManagedProjectStorageEnabledParams.buckets())
            .contains(
                BetaGoogleManagedProjectStorageEnabledParams.Buckets.builder()
                    .putAdditionalProperty(
                        "foo",
                        JsonValue.from(
                            mapOf(
                                "bucket_name" to "bucket_name",
                                "project_number" to "project_number",
                            )
                        ),
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageEnabledParams =
            BetaGoogleManagedProjectStorageEnabledParams.builder()
                .buckets(
                    BetaGoogleManagedProjectStorageEnabledParams.Buckets.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(
                                mapOf(
                                    "bucket_name" to "bucket_name",
                                    "project_number" to "project_number",
                                )
                            ),
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaGoogleManagedProjectStorageEnabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageEnabledParams),
                jacksonTypeRef<BetaGoogleManagedProjectStorageEnabledParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageEnabledParams)
            .isEqualTo(betaGoogleManagedProjectStorageEnabledParams)
    }
}
