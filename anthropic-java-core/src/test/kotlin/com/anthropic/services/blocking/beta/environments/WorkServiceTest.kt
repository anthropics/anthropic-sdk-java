// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.environments

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
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
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WorkServiceTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workService = client.beta().environments().work()

        val betaSelfHostedWork =
            workService.retrieve(
                WorkRetrieveParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .workId("work_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaSelfHostedWork.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workService = client.beta().environments().work()

        val betaSelfHostedWork =
            workService.update(
                WorkUpdateParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .workId("work_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .betaSelfHostedWorkUpdateRequest(
                        BetaSelfHostedWorkUpdateRequest.builder()
                            .metadata(
                                BetaSelfHostedWorkUpdateRequest.Metadata.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("string"))
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        betaSelfHostedWork.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workService = client.beta().environments().work()

        val page = workService.list("env_011CZkZ9X2dpNyB7HsEFoRfW")

        page.response().validate()
    }

    @Test
    fun ack() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workService = client.beta().environments().work()

        val betaSelfHostedWork =
            workService.ack(
                WorkAckParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .workId("work_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaSelfHostedWork.validate()
    }

    @Test
    fun heartbeat() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workService = client.beta().environments().work()

        val betaSelfHostedWorkHeartbeatResponse =
            workService.heartbeat(
                WorkHeartbeatParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .workId("work_id")
                    .desiredTtlSeconds(0L)
                    .expectedLastHeartbeat("expected_last_heartbeat")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaSelfHostedWorkHeartbeatResponse.validate()
    }

    @Test
    fun poll() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workService = client.beta().environments().work()

        val betaSelfHostedWork =
            workService.poll(
                WorkPollParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .blockMs(1L)
                    .reclaimOlderThanMs(1L)
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .anthropicWorkerId("Anthropic-Worker-ID")
                    .build()
            )

        val unwrappedBetaSelfHostedWork = betaSelfHostedWork.getOrNull()
        unwrappedBetaSelfHostedWork?.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun stats() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workService = client.beta().environments().work()

        val betaSelfHostedWorkQueueStats =
            workService.stats(
                WorkStatsParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaSelfHostedWorkQueueStats.validate()
    }

    @Test
    fun stop() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workService = client.beta().environments().work()

        val betaSelfHostedWork =
            workService.stop(
                WorkStopParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .workId("work_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .betaSelfHostedWorkStopRequest(
                        BetaSelfHostedWorkStopRequest.builder().force(true).build()
                    )
                    .build()
            )

        betaSelfHostedWork.validate()
    }
}
