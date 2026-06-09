// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsFileResourceParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeploymentUpdateParamsTest {

    @Test
    fun create() {
        DeploymentUpdateParams.builder()
            .deploymentId("deployment_id")
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
                    .expression("x")
                    .timezone("x")
                    .type(BetaManagedAgentsScheduleParams.Type.CRON)
                    .build()
            )
            .addVaultId("string")
            .build()
    }

    @Test
    fun pathParams() {
        val params = DeploymentUpdateParams.builder().deploymentId("deployment_id").build()

        assertThat(params._pathParam(0)).isEqualTo("deployment_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            DeploymentUpdateParams.builder()
                .deploymentId("deployment_id")
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
                        .expression("x")
                        .timezone("x")
                        .type(BetaManagedAgentsScheduleParams.Type.CRON)
                        .build()
                )
                .addVaultId("string")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = DeploymentUpdateParams.builder().deploymentId("deployment_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            DeploymentUpdateParams.builder()
                .deploymentId("deployment_id")
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
                        .expression("x")
                        .timezone("x")
                        .type(BetaManagedAgentsScheduleParams.Type.CRON)
                        .build()
                )
                .addVaultId("string")
                .build()

        val body = params._body()

        assertThat(body.agent()).contains(DeploymentUpdateParams.Agent.ofString("string"))
        assertThat(body.description()).contains("description")
        assertThat(body.environmentId()).contains("environment_id")
        assertThat(body.initialEvents().getOrNull())
            .containsExactly(
                BetaManagedAgentsDeploymentInitialEventParams.ofUserMessage(
                    BetaManagedAgentsUserMessageEventParams.builder()
                        .addTextContent("Where is my order #1234?")
                        .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
                        .build()
                )
            )
        assertThat(body.metadata())
            .contains(
                DeploymentUpdateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(body.name()).contains("name")
        assertThat(body.resources().getOrNull())
            .containsExactly(
                DeploymentUpdateParams.Resource.ofFile(
                    BetaManagedAgentsFileResourceParams.builder()
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                        .mountPath("/uploads/receipt.pdf")
                        .build()
                )
            )
        assertThat(body.schedule())
            .contains(
                BetaManagedAgentsScheduleParams.builder()
                    .expression("x")
                    .timezone("x")
                    .type(BetaManagedAgentsScheduleParams.Type.CRON)
                    .build()
            )
        assertThat(body.vaultIds().getOrNull()).containsExactly("string")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = DeploymentUpdateParams.builder().deploymentId("deployment_id").build()

        val body = params._body()
    }
}
