package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.deploymentruns.DeploymentRunRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class DeploymentRunServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val deploymentRunService = client.beta().deploymentRuns()

        val betaManagedAgentsDeploymentRun =
            deploymentRunService.retrieve(
                DeploymentRunRetrieveParams.builder()
                    .deploymentRunId("deployment_run_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaManagedAgentsDeploymentRun.validate()
    }

    @Test
    fun list() {
        val deploymentRunService = client.beta().deploymentRuns()

        val page = deploymentRunService.list()

        page.response().validate()
    }
}
