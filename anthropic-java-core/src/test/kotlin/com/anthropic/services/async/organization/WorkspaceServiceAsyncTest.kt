package com.anthropic.services.async.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.organization.workspaces.DataResidencyCreateConfig
import com.anthropic.models.organization.workspaces.DataResidencyUpdateConfig
import com.anthropic.models.organization.workspaces.WorkspaceCreateParams
import com.anthropic.models.organization.workspaces.WorkspaceUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WorkspaceServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val workspaceServiceAsync = client.organization().workspaces()

        val workspaceFuture =
            workspaceServiceAsync.create(
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

        val workspace = workspaceFuture.get()
        workspace.validate()
    }

    @Test
    fun retrieve() {
        val workspaceServiceAsync = client.organization().workspaces()

        val workspaceFuture = workspaceServiceAsync.retrieve("workspace_id")

        val workspace = workspaceFuture.get()
        workspace.validate()
    }

    @Test
    fun update() {
        val workspaceServiceAsync = client.organization().workspaces()

        val workspaceFuture =
            workspaceServiceAsync.update(
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

        val workspace = workspaceFuture.get()
        workspace.validate()
    }

    @Test
    fun list() {
        val workspaceServiceAsync = client.organization().workspaces()

        val pageFuture = workspaceServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun archive() {
        val workspaceServiceAsync = client.organization().workspaces()

        val workspaceFuture = workspaceServiceAsync.archive("workspace_id")

        val workspace = workspaceFuture.get()
        workspace.validate()
    }
}
