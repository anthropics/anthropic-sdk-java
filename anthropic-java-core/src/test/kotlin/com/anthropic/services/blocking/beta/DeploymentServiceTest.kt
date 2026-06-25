// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
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
internal class DeploymentServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentService = client.beta().deployments()

        val betaManagedAgentsDeployment =
            deploymentService.create(
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

        betaManagedAgentsDeployment.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentService = client.beta().deployments()

        val betaManagedAgentsDeployment =
            deploymentService.retrieve(
                DeploymentRetrieveParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsDeployment.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentService = client.beta().deployments()

        val betaManagedAgentsDeployment =
            deploymentService.update(
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

        betaManagedAgentsDeployment.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentService = client.beta().deployments()

        val page = deploymentService.list()

        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentService = client.beta().deployments()

        val betaManagedAgentsDeployment =
            deploymentService.archive(
                DeploymentArchiveParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsDeployment.validate()
    }

    @Test
    fun pause() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentService = client.beta().deployments()

        val betaManagedAgentsDeployment =
            deploymentService.pause(
                DeploymentPauseParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsDeployment.validate()
    }

    @Test
    fun run() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentService = client.beta().deployments()

        val betaManagedAgentsDeploymentRun =
            deploymentService.run(
                DeploymentRunParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsDeploymentRun.validate()
    }

    @Test
    fun unpause() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val deploymentService = client.beta().deployments()

        val betaManagedAgentsDeployment =
            deploymentService.unpause(
                DeploymentUnpauseParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsDeployment.validate()
    }
}
