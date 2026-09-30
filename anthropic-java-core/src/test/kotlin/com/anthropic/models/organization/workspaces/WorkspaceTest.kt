package com.anthropic.models.organization.workspaces

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceTest {

    @Test
    fun create() {
        val workspace =
            Workspace.builder()
                .id("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .archivedAt(OffsetDateTime.parse("2024-11-01T23:59:27.427722Z"))
                .compartmentId("f8a7b6c5-4d3e-4f1a-8b9c-0d1e2f3a4b5c")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .dataResidency(
                    DataResidency.builder()
                        .allowedInferenceGeosUnrestricted()
                        .defaultInferenceGeo(DataResidency.DefaultInferenceGeo.GLOBAL)
                        .workspaceGeo(DataResidency.WorkspaceGeo.US)
                        .build()
                )
                .displayColor("#6C5BB9")
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .name("Workspace Name")
                .tags(
                    Workspace.Tags.builder()
                        .putAdditionalProperty("env", JsonValue.from("prod"))
                        .putAdditionalProperty("team", JsonValue.from("platform"))
                        .build()
                )
                .build()

        assertThat(workspace.id()).isEqualTo("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
        assertThat(workspace.archivedAt())
            .contains(OffsetDateTime.parse("2024-11-01T23:59:27.427722Z"))
        assertThat(workspace.compartmentId()).isEqualTo("f8a7b6c5-4d3e-4f1a-8b9c-0d1e2f3a4b5c")
        assertThat(workspace.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(workspace.dataResidency())
            .isEqualTo(
                DataResidency.builder()
                    .allowedInferenceGeosUnrestricted()
                    .defaultInferenceGeo(DataResidency.DefaultInferenceGeo.GLOBAL)
                    .workspaceGeo(DataResidency.WorkspaceGeo.US)
                    .build()
            )
        assertThat(workspace.displayColor()).isEqualTo("#6C5BB9")
        assertThat(workspace.externalKeyId()).contains("ekey_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(workspace.name()).isEqualTo("Workspace Name")
        assertThat(workspace.tags())
            .isEqualTo(
                Workspace.Tags.builder()
                    .putAdditionalProperty("env", JsonValue.from("prod"))
                    .putAdditionalProperty("team", JsonValue.from("platform"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspace =
            Workspace.builder()
                .id("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .archivedAt(OffsetDateTime.parse("2024-11-01T23:59:27.427722Z"))
                .compartmentId("f8a7b6c5-4d3e-4f1a-8b9c-0d1e2f3a4b5c")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .dataResidency(
                    DataResidency.builder()
                        .allowedInferenceGeosUnrestricted()
                        .defaultInferenceGeo(DataResidency.DefaultInferenceGeo.GLOBAL)
                        .workspaceGeo(DataResidency.WorkspaceGeo.US)
                        .build()
                )
                .displayColor("#6C5BB9")
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .name("Workspace Name")
                .tags(
                    Workspace.Tags.builder()
                        .putAdditionalProperty("env", JsonValue.from("prod"))
                        .putAdditionalProperty("team", JsonValue.from("platform"))
                        .build()
                )
                .build()

        val roundtrippedWorkspace =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspace),
                jacksonTypeRef<Workspace>(),
            )

        assertThat(roundtrippedWorkspace).isEqualTo(workspace)
    }
}
