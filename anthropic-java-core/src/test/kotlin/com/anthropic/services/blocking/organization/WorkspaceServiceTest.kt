package com.anthropic.services.blocking.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.organization.workspaces.DataResidencyCreateConfig
import com.anthropic.models.organization.workspaces.DataResidencyUpdateConfig
import com.anthropic.models.organization.workspaces.WorkspaceCreateParams
import com.anthropic.models.organization.workspaces.WorkspaceUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WorkspaceServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workspaceService = client.organization().workspaces()

        val workspace =
            workspaceService.create(
                WorkspaceCreateParams.builder()
                    .name("x")
                    .dataResidency(
                        DataResidencyCreateConfig.builder()
                            .allowedInferenceGeosUnrestricted()
                            .defaultInferenceGeo(
                                DataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL
                            )
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
                    .build()
            )

        workspace.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workspaceService = client.organization().workspaces()

        val workspace = workspaceService.retrieve("workspace_id")

        workspace.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workspaceService = client.organization().workspaces()

        val workspace =
            workspaceService.update(
                WorkspaceUpdateParams.builder()
                    .workspaceId("workspace_id")
                    .dataResidency(
                        DataResidencyUpdateConfig.builder()
                            .allowedInferenceGeosUnrestricted()
                            .defaultInferenceGeo(
                                DataResidencyUpdateConfig.DefaultInferenceGeo.GLOBAL
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
            )

        workspace.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workspaceService = client.organization().workspaces()

        val page = workspaceService.list()

        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workspaceService = client.organization().workspaces()

        val workspace = workspaceService.archive("workspace_id")

        workspace.validate()
    }
}
