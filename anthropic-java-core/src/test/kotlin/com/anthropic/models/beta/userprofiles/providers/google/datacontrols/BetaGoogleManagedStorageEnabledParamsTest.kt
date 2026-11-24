package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedStorageEnabledParamsTest {

    @Test
    fun create() {
        val betaGoogleManagedStorageEnabledParams =
            BetaGoogleManagedStorageEnabledParams.builder()
                .buckets(
                    BetaGoogleManagedStorageEnabledParams.Buckets.builder()
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

        assertThat(betaGoogleManagedStorageEnabledParams.buckets())
            .contains(
                BetaGoogleManagedStorageEnabledParams.Buckets.builder()
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
        val betaGoogleManagedStorageEnabledParams =
            BetaGoogleManagedStorageEnabledParams.builder()
                .buckets(
                    BetaGoogleManagedStorageEnabledParams.Buckets.builder()
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

        val roundtrippedBetaGoogleManagedStorageEnabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageEnabledParams),
                jacksonTypeRef<BetaGoogleManagedStorageEnabledParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageEnabledParams)
            .isEqualTo(betaGoogleManagedStorageEnabledParams)
    }
}
