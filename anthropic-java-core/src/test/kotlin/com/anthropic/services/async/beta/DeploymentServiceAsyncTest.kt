package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.BetaCurrency
import com.anthropic.models.beta.BetaMonetaryAmount
import com.anthropic.models.beta.deployments.BetaManagedAgentsScheduleParams
import com.anthropic.models.beta.deployments.DeploymentArchiveParams
import com.anthropic.models.beta.deployments.DeploymentCreateParams
import com.anthropic.models.beta.deployments.DeploymentPauseParams
import com.anthropic.models.beta.deployments.DeploymentRetrieveParams
import com.anthropic.models.beta.deployments.DeploymentRunParams
import com.anthropic.models.beta.deployments.DeploymentUnpauseParams
import com.anthropic.models.beta.deployments.DeploymentUpdateParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsBudgetLimit
import com.anthropic.models.beta.sessions.BetaManagedAgentsFileResourceParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class DeploymentServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val deploymentServiceAsync =
            client.beta().deployments().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.create(
                DeploymentCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .agent("string")
                    .environmentId("x")
                    .addUserMessageInitialEvent(
                        listOf(
                            BetaManagedAgentsUserMessageEventParams.Content.ofText(
                                "Where is my order #1234?"
                            )
                        )
                    )
                    .name("x")
                    .budget(
                        BetaManagedAgentsBudgetLimit.of(
                            BetaMonetaryAmount.builder()
                                .amount("2500")
                                .currency(BetaCurrency.USD)
                                .build()
                        )
                    )
                    .description("description")
                    .metadata(
                        DeploymentCreateParams.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .addResource(
                        BetaManagedAgentsFileResourceParams.builder()
                            .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                            .mountPath("/uploads/receipt.pdf")
                            .build()
                    )
                    .schedule(
                        BetaManagedAgentsScheduleParams.builder()
                            .expression("0 9 * * 1-5")
                            .timezone("America/Los_Angeles")
                            .build()
                    )
                    .addVaultId("string")
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }

    @Test
    fun retrieve() {
        val deploymentServiceAsync =
            client.beta().deployments().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.retrieve(
                DeploymentRetrieveParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }

    @Test
    fun update() {
        val deploymentServiceAsync =
            client.beta().deployments().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.update(
                DeploymentUpdateParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .agent("string")
                    .budget(
                        BetaManagedAgentsBudgetLimit.of(
                            BetaMonetaryAmount.builder()
                                .amount("2500")
                                .currency(BetaCurrency.USD)
                                .build()
                        )
                    )
                    .description("description")
                    .environmentId("environment_id")
                    .addUserMessageInitialEvent(
                        listOf(
                            BetaManagedAgentsUserMessageEventParams.Content.ofText(
                                "Where is my order #1234?"
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
                            .mountPath("/uploads/receipt.pdf")
                            .build()
                    )
                    .schedule(
                        BetaManagedAgentsScheduleParams.builder()
                            .expression("0 9 * * 1-5")
                            .timezone("America/Los_Angeles")
                            .build()
                    )
                    .addVaultId("string")
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }

    @Test
    fun list() {
        val deploymentServiceAsync =
            client.beta().deployments().withOptions { it.putQueryParam("beta", "true") }

        val pageFuture = deploymentServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun archive() {
        val deploymentServiceAsync =
            client.beta().deployments().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.archive(
                DeploymentArchiveParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }

    @Test
    fun pause() {
        val deploymentServiceAsync =
            client.beta().deployments().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.pause(
                DeploymentPauseParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }

    @Test
    fun run() {
        val deploymentServiceAsync =
            client.beta().deployments().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsDeploymentRunFuture =
            deploymentServiceAsync.run(
                DeploymentRunParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaManagedAgentsDeploymentRun = betaManagedAgentsDeploymentRunFuture.get()
        betaManagedAgentsDeploymentRun.validate()
    }

    @Test
    fun unpause() {
        val deploymentServiceAsync =
            client.beta().deployments().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsDeploymentFuture =
            deploymentServiceAsync.unpause(
                DeploymentUnpauseParams.builder()
                    .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaManagedAgentsDeployment = betaManagedAgentsDeploymentFuture.get()
        betaManagedAgentsDeployment.validate()
    }
}
