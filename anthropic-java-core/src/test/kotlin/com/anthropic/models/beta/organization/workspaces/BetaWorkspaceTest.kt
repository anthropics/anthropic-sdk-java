// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWorkspaceTest {

    @Test
    fun create() {
        val betaWorkspace =
            BetaWorkspace.builder()
                .id("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .archivedAt(OffsetDateTime.parse("2024-11-01T23:59:27.427722Z"))
                .compartmentId("f8a7b6c5-4d3e-4f1a-8b9c-0d1e2f3a4b5c")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .dataResidency(
                    BetaDataResidency.builder()
                        .allowedInferenceGeosUnrestricted()
                        .defaultInferenceGeo("default_inference_geo")
                        .workspaceGeo("workspace_geo")
                        .build()
                )
                .displayColor("#6C5BB9")
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .name("Workspace Name")
                .tags(
                    BetaWorkspace.Tags.builder()
                        .putAdditionalProperty("env", JsonValue.from("prod"))
                        .putAdditionalProperty("team", JsonValue.from("platform"))
                        .build()
                )
                .build()

        assertThat(betaWorkspace.id()).isEqualTo("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
        assertThat(betaWorkspace.archivedAt())
            .contains(OffsetDateTime.parse("2024-11-01T23:59:27.427722Z"))
        assertThat(betaWorkspace.compartmentId()).isEqualTo("f8a7b6c5-4d3e-4f1a-8b9c-0d1e2f3a4b5c")
        assertThat(betaWorkspace.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaWorkspace.dataResidency())
            .isEqualTo(
                BetaDataResidency.builder()
                    .allowedInferenceGeosUnrestricted()
                    .defaultInferenceGeo("default_inference_geo")
                    .workspaceGeo("workspace_geo")
                    .build()
            )
        assertThat(betaWorkspace.displayColor()).isEqualTo("#6C5BB9")
        assertThat(betaWorkspace.externalKeyId()).contains("ekey_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(betaWorkspace.name()).isEqualTo("Workspace Name")
        assertThat(betaWorkspace.tags())
            .isEqualTo(
                BetaWorkspace.Tags.builder()
                    .putAdditionalProperty("env", JsonValue.from("prod"))
                    .putAdditionalProperty("team", JsonValue.from("platform"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWorkspace =
            BetaWorkspace.builder()
                .id("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .archivedAt(OffsetDateTime.parse("2024-11-01T23:59:27.427722Z"))
                .compartmentId("f8a7b6c5-4d3e-4f1a-8b9c-0d1e2f3a4b5c")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .dataResidency(
                    BetaDataResidency.builder()
                        .allowedInferenceGeosUnrestricted()
                        .defaultInferenceGeo("default_inference_geo")
                        .workspaceGeo("workspace_geo")
                        .build()
                )
                .displayColor("#6C5BB9")
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .name("Workspace Name")
                .tags(
                    BetaWorkspace.Tags.builder()
                        .putAdditionalProperty("env", JsonValue.from("prod"))
                        .putAdditionalProperty("team", JsonValue.from("platform"))
                        .build()
                )
                .build()

        val roundtrippedBetaWorkspace =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWorkspace),
                jacksonTypeRef<BetaWorkspace>(),
            )

        assertThat(roundtrippedBetaWorkspace).isEqualTo(betaWorkspace)
    }
}
