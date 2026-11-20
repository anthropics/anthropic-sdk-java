package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceUpdateParamsTest {

    @Test
    fun create() {
        WorkspaceUpdateParams.builder()
            .workspaceId("workspace_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .dataResidency(
                BetaDataResidencyUpdateConfig.builder()
                    .allowedInferenceGeosUnrestricted()
                    .defaultInferenceGeo(BetaDataResidencyUpdateConfig.DefaultInferenceGeo.GLOBAL)
                    .build()
            )
            .displayColor("#6C5BB9")
            .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
            .inferenceDataPrivacy(
                BetaInferenceDataPrivacyUpdateParam.builder()
                    .humanReview(
                        BetaAnthropicHumanReviewParam.of(
                            BetaAnthropicHumanReviewParam.Type.DISABLED
                        )
                    )
                    .safeguardsStorage(BetaInternalStorageParam.builder().build())
                    .transcriptsStorage(BetaInternalStorageParam.builder().build())
                    .build()
            )
            .name("Workspace Name")
            .tags(
                WorkspaceUpdateParams.Tags.builder()
                    .putAdditionalProperty("env", JsonValue.from("prod"))
                    .putAdditionalProperty("team", JsonValue.from("platform"))
                    .build()
            )
            .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
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
    fun headers() {
        val params =
            WorkspaceUpdateParams.builder()
                .workspaceId("workspace_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
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
                .inferenceDataPrivacy(
                    BetaInferenceDataPrivacyUpdateParam.builder()
                        .humanReview(
                            BetaAnthropicHumanReviewParam.of(
                                BetaAnthropicHumanReviewParam.Type.DISABLED
                            )
                        )
                        .safeguardsStorage(BetaInternalStorageParam.builder().build())
                        .transcriptsStorage(BetaInternalStorageParam.builder().build())
                        .build()
                )
                .name("Workspace Name")
                .tags(
                    WorkspaceUpdateParams.Tags.builder()
                        .putAdditionalProperty("env", JsonValue.from("prod"))
                        .putAdditionalProperty("team", JsonValue.from("platform"))
                        .build()
                )
                .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("computer-use-2024-10-22").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = WorkspaceUpdateParams.builder().workspaceId("workspace_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            WorkspaceUpdateParams.builder()
                .workspaceId("workspace_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
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
                .inferenceDataPrivacy(
                    BetaInferenceDataPrivacyUpdateParam.builder()
                        .humanReview(
                            BetaAnthropicHumanReviewParam.of(
                                BetaAnthropicHumanReviewParam.Type.DISABLED
                            )
                        )
                        .safeguardsStorage(BetaInternalStorageParam.builder().build())
                        .transcriptsStorage(BetaInternalStorageParam.builder().build())
                        .build()
                )
                .name("Workspace Name")
                .tags(
                    WorkspaceUpdateParams.Tags.builder()
                        .putAdditionalProperty("env", JsonValue.from("prod"))
                        .putAdditionalProperty("team", JsonValue.from("platform"))
                        .build()
                )
                .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
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
        assertThat(body.inferenceDataPrivacy())
            .contains(
                BetaInferenceDataPrivacyUpdateParam.builder()
                    .humanReview(
                        BetaAnthropicHumanReviewParam.of(
                            BetaAnthropicHumanReviewParam.Type.DISABLED
                        )
                    )
                    .safeguardsStorage(BetaInternalStorageParam.builder().build())
                    .transcriptsStorage(BetaInternalStorageParam.builder().build())
                    .build()
            )
        assertThat(body.name()).contains("Workspace Name")
        assertThat(body.tags())
            .contains(
                WorkspaceUpdateParams.Tags.builder()
                    .putAdditionalProperty("env", JsonValue.from("prod"))
                    .putAdditionalProperty("team", JsonValue.from("platform"))
                    .build()
            )
        assertThat(body.userProfileId()).contains("uprof_01SDCCSbTxrXDpWc1phhtcfK")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = WorkspaceUpdateParams.builder().workspaceId("workspace_id").build()

        val body = params._body()
    }
}
