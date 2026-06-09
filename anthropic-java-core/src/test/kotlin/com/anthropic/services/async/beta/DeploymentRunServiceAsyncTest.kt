// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.deploymentruns.DeploymentRunRetrieveParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class DeploymentRunServiceAsyncTest {

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentRunServiceAsync = client.beta().deploymentRuns()

        val betaManagedAgentsDeploymentRunFuture =
            deploymentRunServiceAsync.retrieve(
                DeploymentRunRetrieveParams.builder()
                    .deploymentRunId("deployment_run_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeploymentRun = betaManagedAgentsDeploymentRunFuture.get()
        betaManagedAgentsDeploymentRun.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentRunServiceAsync = client.beta().deploymentRuns()

        val pageFuture = deploymentRunServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
