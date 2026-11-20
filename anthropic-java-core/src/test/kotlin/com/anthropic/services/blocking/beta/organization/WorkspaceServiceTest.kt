package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.workspaces.BetaAnthropicHumanReviewParam
import com.anthropic.models.beta.organization.workspaces.BetaDataResidencyCreateConfig
import com.anthropic.models.beta.organization.workspaces.BetaDataResidencyUpdateConfig
import com.anthropic.models.beta.organization.workspaces.BetaInferenceDataPrivacyCreateParam
import com.anthropic.models.beta.organization.workspaces.BetaInferenceDataPrivacyUpdateParam
import com.anthropic.models.beta.organization.workspaces.BetaInternalStorageParam
import com.anthropic.models.beta.organization.workspaces.WorkspaceArchiveParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceCreateParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceRetrieveParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WorkspaceServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val workspaceService =
            client.beta().organization().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaWorkspace =
            workspaceService.create(
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
            )

        betaWorkspace.validate()
    }

    @Test
    fun retrieve() {
        val workspaceService =
            client.beta().organization().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaWorkspace =
            workspaceService.retrieve(
                WorkspaceRetrieveParams.builder()
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaWorkspace.validate()
    }

    @Test
    fun update() {
        val workspaceService =
            client.beta().organization().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaWorkspace =
            workspaceService.update(
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
            )

        betaWorkspace.validate()
    }

    @Test
    fun list() {
        val workspaceService =
            client.beta().organization().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = workspaceService.list()

        page.response().validate()
    }

    @Test
    fun archive() {
        val workspaceService =
            client.beta().organization().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaWorkspace =
            workspaceService.archive(
                WorkspaceArchiveParams.builder()
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaWorkspace.validate()
    }
}
