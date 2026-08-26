// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceUpdateParamsTest {

    @Test
    fun create() {
        WorkspaceUpdateParams.builder()
            .workspaceId("workspace_id")
            .dataResidency(
                BetaDataResidencyUpdateConfig.builder()
                    .allowedInferenceGeosUnrestricted()
                    .defaultInferenceGeo(BetaDataResidencyUpdateConfig.DefaultInferenceGeo.GLOBAL)
                    .build()
            )
            .displayColor("#6C5BB9")
            .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
            .name("x")
            .tags(
                WorkspaceUpdateParams.Tags.builder()
                    .putAdditionalProperty("env", JsonValue.from("prod"))
                    .putAdditionalProperty("team", JsonValue.from("platform"))
                    .build()
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params = WorkspaceUpdateParams.builder().workspaceId("workspace_id").build()

        assertThat(params._pathParam(0)).isEqualTo("workspace_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            WorkspaceUpdateParams.builder()
                .workspaceId("workspace_id")
                .dataResidency(
                    BetaDataResidencyUpdateConfig.builder()
                        .allowedInferenceGeosUnrestricted()
                        .defaultInferenceGeo(
                            BetaDataResidencyUpdateConfig.DefaultInferenceGeo.GLOBAL
                        )
                        .build()
                )
                .displayColor("#6C5BB9")
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .name("x")
                .tags(
                    WorkspaceUpdateParams.Tags.builder()
                        .putAdditionalProperty("env", JsonValue.from("prod"))
                        .putAdditionalProperty("team", JsonValue.from("platform"))
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.dataResidency())
            .contains(
                BetaDataResidencyUpdateConfig.builder()
                    .allowedInferenceGeosUnrestricted()
                    .defaultInferenceGeo(BetaDataResidencyUpdateConfig.DefaultInferenceGeo.GLOBAL)
                    .build()
            )
        assertThat(body.displayColor()).contains("#6C5BB9")
        assertThat(body.externalKeyId()).contains("ekey_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(body.name()).contains("x")
        assertThat(body.tags())
            .contains(
                WorkspaceUpdateParams.Tags.builder()
                    .putAdditionalProperty("env", JsonValue.from("prod"))
                    .putAdditionalProperty("team", JsonValue.from("platform"))
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = WorkspaceUpdateParams.builder().workspaceId("workspace_id").build()

        val body = params._body()
    }
}
