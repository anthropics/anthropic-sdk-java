package com.anthropic.services.async.beta.environments

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.environments.work.BetaSelfHostedWorkStopRequest
import com.anthropic.models.beta.environments.work.BetaSelfHostedWorkUpdateRequest
import com.anthropic.models.beta.environments.work.WorkAckParams
import com.anthropic.models.beta.environments.work.WorkHeartbeatParams
import com.anthropic.models.beta.environments.work.WorkPollParams
import com.anthropic.models.beta.environments.work.WorkRetrieveParams
import com.anthropic.models.beta.environments.work.WorkStatsParams
import com.anthropic.models.beta.environments.work.WorkStopParams
import com.anthropic.models.beta.environments.work.WorkUpdateParams
import kotlin.jvm.optionals.getOrNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WorkServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val workServiceAsync =
            client.beta().environments().work().withOptions { it.putQueryParam("beta", "true") }

        val betaSelfHostedWorkFuture =
            workServiceAsync.retrieve(
                WorkRetrieveParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .workId("work_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaSelfHostedWork = betaSelfHostedWorkFuture.get()
        betaSelfHostedWork.validate()
    }

    @Test
    fun update() {
        val workServiceAsync =
            client.beta().environments().work().withOptions { it.putQueryParam("beta", "true") }

        val betaSelfHostedWorkFuture =
            workServiceAsync.update(
                WorkUpdateParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .workId("work_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .betaSelfHostedWorkUpdateRequest(
                        BetaSelfHostedWorkUpdateRequest.of(
                            BetaSelfHostedWorkUpdateRequest.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                    )
                    .build()
            )

        val betaSelfHostedWork = betaSelfHostedWorkFuture.get()
        betaSelfHostedWork.validate()
    }

    @Test
    fun list() {
        val workServiceAsync =
            client.beta().environments().work().withOptions { it.putQueryParam("beta", "true") }

        val pageFuture = workServiceAsync.list("env_011CZkZ9X2dpNyB7HsEFoRfW")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun ack() {
        val workServiceAsync =
            client.beta().environments().work().withOptions { it.putQueryParam("beta", "true") }

        val betaSelfHostedWorkFuture =
            workServiceAsync.ack(
                WorkAckParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .workId("work_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaSelfHostedWork = betaSelfHostedWorkFuture.get()
        betaSelfHostedWork.validate()
    }

    @Test
    fun heartbeat() {
        val workServiceAsync =
            client.beta().environments().work().withOptions { it.putQueryParam("beta", "true") }

        val betaSelfHostedWorkHeartbeatResponseFuture =
            workServiceAsync.heartbeat(
                WorkHeartbeatParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .workId("work_id")
                    .desiredTtlSeconds(0L)
                    .expectedLastHeartbeat("expected_last_heartbeat")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaSelfHostedWorkHeartbeatResponse = betaSelfHostedWorkHeartbeatResponseFuture.get()
        betaSelfHostedWorkHeartbeatResponse.validate()
    }

    @Test
    fun poll() {
        val workServiceAsync =
            client.beta().environments().work().withOptions { it.putQueryParam("beta", "true") }

        val betaSelfHostedWorkFuture =
            workServiceAsync.poll(
                WorkPollParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .blockMs(1L)
                    .reclaimOlderThanMs(1L)
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .anthropicWorkerId("Anthropic-Worker-ID")
                    .build()
            )

        val betaSelfHostedWork = betaSelfHostedWorkFuture.get()
        val unwrappedBetaSelfHostedWork = betaSelfHostedWork.getOrNull()
        unwrappedBetaSelfHostedWork?.validate()
    }

    @Test
    fun stats() {
        val workServiceAsync =
            client.beta().environments().work().withOptions { it.putQueryParam("beta", "true") }

        val betaSelfHostedWorkQueueStatsFuture =
            workServiceAsync.stats(
                WorkStatsParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaSelfHostedWorkQueueStats = betaSelfHostedWorkQueueStatsFuture.get()
        betaSelfHostedWorkQueueStats.validate()
    }

    @Test
    fun stop() {
        val workServiceAsync =
            client.beta().environments().work().withOptions { it.putQueryParam("beta", "true") }

        val betaSelfHostedWorkFuture =
            workServiceAsync.stop(
                WorkStopParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .workId("work_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .betaSelfHostedWorkStopRequest(
                        BetaSelfHostedWorkStopRequest.builder().force(true).build()
                    )
                    .build()
            )

        val betaSelfHostedWork = betaSelfHostedWorkFuture.get()
        betaSelfHostedWork.validate()
    }
}
