package com.anthropic.models.organization.workspaces

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceCreateParamsTest {

    @Test
    fun create() {
        WorkspaceCreateParams.builder()
            .name("x")
            .dataResidency(
                DataResidencyCreateConfig.builder()
                    .allowedInferenceGeosUnrestricted()
                    .defaultInferenceGeo(DataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
                    .workspaceGeo(DataResidencyCreateConfig.WorkspaceGeo.US)
                    .build()
            )
            .displayColor("#6C5BB9")
            .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
            .tags(
                WorkspaceCreateParams.Tags.builder()
                    .putAdditionalProperty("env", JsonValue.from("prod"))
                    .putAdditionalProperty("team", JsonValue.from("platform"))
                    .build()
            )
            .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
            .build()
    }

    @Test
    fun body() {
        val params =
            WorkspaceCreateParams.builder()
                .name("x")
                .dataResidency(
                    DataResidencyCreateConfig.builder()
                        .allowedInferenceGeosUnrestricted()
                        .defaultInferenceGeo(DataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
                        .workspaceGeo(DataResidencyCreateConfig.WorkspaceGeo.US)
                        .build()
                )
                .displayColor("#6C5BB9")
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .tags(
                    WorkspaceCreateParams.Tags.builder()
                        .putAdditionalProperty("env", JsonValue.from("prod"))
                        .putAdditionalProperty("team", JsonValue.from("platform"))
                        .build()
                )
                .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
                .build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("x")
        assertThat(body.dataResidency())
            .contains(
                DataResidencyCreateConfig.builder()
                    .allowedInferenceGeosUnrestricted()
                    .defaultInferenceGeo(DataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
                    .workspaceGeo(DataResidencyCreateConfig.WorkspaceGeo.US)
                    .build()
            )
        assertThat(body.displayColor()).contains("#6C5BB9")
        assertThat(body.externalKeyId()).contains("ekey_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(body.tags())
            .contains(
                WorkspaceCreateParams.Tags.builder()
                    .putAdditionalProperty("env", JsonValue.from("prod"))
                    .putAdditionalProperty("team", JsonValue.from("platform"))
                    .build()
            )
        assertThat(body.userProfileId()).contains("uprof_01SDCCSbTxrXDpWc1phhtcfK")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = WorkspaceCreateParams.builder().name("x").build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("x")
    }
}
