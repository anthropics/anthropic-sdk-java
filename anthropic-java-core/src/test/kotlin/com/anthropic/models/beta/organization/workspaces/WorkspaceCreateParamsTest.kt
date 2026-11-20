package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceCreateParamsTest {

    @Test
    fun create() {
        WorkspaceCreateParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .name("Workspace Name")
            .dataResidency(
                BetaDataResidencyCreateConfig.builder()
                    .allowedInferenceGeosUnrestricted()
                    .defaultInferenceGeo(BetaDataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
                    .workspaceGeo(BetaDataResidencyCreateConfig.WorkspaceGeo.US)
                    .build()
            )
            .displayColor("#6C5BB9")
            .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
            .inferenceDataPrivacy(
                BetaInferenceDataPrivacyCreateParam.builder()
                    .humanReview(
                        BetaAnthropicHumanReviewParam.of(
                            BetaAnthropicHumanReviewParam.Type.DISABLED
                        )
                    )
                    .safeguardsStorage(BetaInternalStorageParam.builder().build())
                    .transcriptsStorage(BetaInternalStorageParam.builder().build())
                    .build()
            )
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
    fun headers() {
        val params =
            WorkspaceCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .name("Workspace Name")
                .dataResidency(
                    BetaDataResidencyCreateConfig.builder()
                        .allowedInferenceGeosUnrestricted()
                        .defaultInferenceGeo(
                            BetaDataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL
                        )
                        .workspaceGeo(BetaDataResidencyCreateConfig.WorkspaceGeo.US)
                        .build()
                )
                .displayColor("#6C5BB9")
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .inferenceDataPrivacy(
                    BetaInferenceDataPrivacyCreateParam.builder()
                        .humanReview(
                            BetaAnthropicHumanReviewParam.of(
                                BetaAnthropicHumanReviewParam.Type.DISABLED
                            )
                        )
                        .safeguardsStorage(BetaInternalStorageParam.builder().build())
                        .transcriptsStorage(BetaInternalStorageParam.builder().build())
                        .build()
                )
                .tags(
                    WorkspaceCreateParams.Tags.builder()
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
        val params = WorkspaceCreateParams.builder().name("Workspace Name").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            WorkspaceCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .name("Workspace Name")
                .dataResidency(
                    BetaDataResidencyCreateConfig.builder()
                        .allowedInferenceGeosUnrestricted()
                        .defaultInferenceGeo(
                            BetaDataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL
                        )
                        .workspaceGeo(BetaDataResidencyCreateConfig.WorkspaceGeo.US)
                        .build()
                )
                .displayColor("#6C5BB9")
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .inferenceDataPrivacy(
                    BetaInferenceDataPrivacyCreateParam.builder()
                        .humanReview(
                            BetaAnthropicHumanReviewParam.of(
                                BetaAnthropicHumanReviewParam.Type.DISABLED
                            )
                        )
                        .safeguardsStorage(BetaInternalStorageParam.builder().build())
                        .transcriptsStorage(BetaInternalStorageParam.builder().build())
                        .build()
                )
                .tags(
                    WorkspaceCreateParams.Tags.builder()
                        .putAdditionalProperty("env", JsonValue.from("prod"))
                        .putAdditionalProperty("team", JsonValue.from("platform"))
                        .build()
                )
                .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
                .build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("Workspace Name")
        assertThat(body.dataResidency())
            .contains(
                BetaDataResidencyCreateConfig.builder()
                    .allowedInferenceGeosUnrestricted()
                    .defaultInferenceGeo(BetaDataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
                    .workspaceGeo(BetaDataResidencyCreateConfig.WorkspaceGeo.US)
                    .build()
            )
        assertThat(body.displayColor()).contains("#6C5BB9")
        assertThat(body.externalKeyId()).contains("ekey_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(body.inferenceDataPrivacy())
            .contains(
                BetaInferenceDataPrivacyCreateParam.builder()
                    .humanReview(
                        BetaAnthropicHumanReviewParam.of(
                            BetaAnthropicHumanReviewParam.Type.DISABLED
                        )
                    )
                    .safeguardsStorage(BetaInternalStorageParam.builder().build())
                    .transcriptsStorage(BetaInternalStorageParam.builder().build())
                    .build()
            )
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
        val params = WorkspaceCreateParams.builder().name("Workspace Name").build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("Workspace Name")
    }
}
