// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.deployments.BetaManagedAgentsScheduleParams
import com.anthropic.models.beta.deployments.DeploymentArchiveParams
import com.anthropic.models.beta.deployments.DeploymentCreateParams
import com.anthropic.models.beta.deployments.DeploymentPauseParams
import com.anthropic.models.beta.deployments.DeploymentRetrieveParams
import com.anthropic.models.beta.deployments.DeploymentRunParams
import com.anthropic.models.beta.deployments.DeploymentUnpauseParams
import com.anthropic.models.beta.deployments.DeploymentUpdateParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsFileResourceParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class DeploymentServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentServiceAsync = client.beta().deployments()

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.create(
                DeploymentCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .agent("string")
                    .environmentId("x")
                    .addUserMessageInitialEvent(
                        listOf(
                            BetaManagedAgentsUserMessageEventParams.Content.ofText(
                                BetaManagedAgentsTextBlock.builder()
                                    .text("Where is my order #1234?")
                                    .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                    .build()
                            )
                        )
                    )
                    .name("x")
                    .description("description")
                    .metadata(
                        DeploymentCreateParams.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .addResource(
                        BetaManagedAgentsFileResourceParams.builder()
                            .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                            .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                            .mountPath("/uploads/receipt.pdf")
                            .build()
                    )
                    .schedule(
                        BetaManagedAgentsScheduleParams.builder()
                            .expression("0 9 * * 1-5")
                            .timezone("America/Los_Angeles")
                            .type(BetaManagedAgentsScheduleParams.Type.CRON)
                            .build()
                    )
                    .addVaultId("string")
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentServiceAsync = client.beta().deployments()

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.retrieve(
                DeploymentRetrieveParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentServiceAsync = client.beta().deployments()

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.update(
                DeploymentUpdateParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .agent("string")
                    .description("description")
                    .environmentId("environment_id")
                    .addUserMessageInitialEvent(
                        listOf(
                            BetaManagedAgentsUserMessageEventParams.Content.ofText(
                                BetaManagedAgentsTextBlock.builder()
                                    .text("Where is my order #1234?")
                                    .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                    .build()
                            )
                        )
                    )
                    .metadata(
                        DeploymentUpdateParams.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .name("name")
                    .addResource(
                        BetaManagedAgentsFileResourceParams.builder()
                            .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                            .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                            .mountPath("/uploads/receipt.pdf")
                            .build()
                    )
                    .schedule(
                        BetaManagedAgentsScheduleParams.builder()
                            .expression("0 9 * * 1-5")
                            .timezone("America/Los_Angeles")
                            .type(BetaManagedAgentsScheduleParams.Type.CRON)
                            .build()
                    )
                    .addVaultId("string")
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentServiceAsync = client.beta().deployments()

        val pageFuture = deploymentServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentServiceAsync = client.beta().deployments()

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.archive(
                DeploymentArchiveParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }

    @Test
    fun pause() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentServiceAsync = client.beta().deployments()

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.pause(
                DeploymentPauseParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }

    @Test
    fun run() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentServiceAsync = client.beta().deployments()

        val betaManagedAgentsDeploymentRunFuture =
            deploymentServiceAsync.run(
                DeploymentRunParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeploymentRun = betaManagedAgentsDeploymentRunFuture.get()
        betaManagedAgentsDeploymentRun.validate()
    }

    @Test
    fun unpause() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentServiceAsync = client.beta().deployments()

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.unpause(
                DeploymentUnpauseParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }
}
