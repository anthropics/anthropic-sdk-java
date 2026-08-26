// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceListPageResponseTest {

    @Test
    fun create() {
        val workspaceListPageResponse =
            WorkspaceListPageResponse.builder()
                .addData(
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
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(workspaceListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(workspaceListPageResponse.firstId()).contains("first_id")
        assertThat(workspaceListPageResponse.hasMore()).isEqualTo(true)
        assertThat(workspaceListPageResponse.lastId()).contains("last_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceListPageResponse =
            WorkspaceListPageResponse.builder()
                .addData(
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
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        val roundtrippedWorkspaceListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceListPageResponse),
                jacksonTypeRef<WorkspaceListPageResponse>(),
            )

        assertThat(roundtrippedWorkspaceListPageResponse).isEqualTo(workspaceListPageResponse)
    }
}
