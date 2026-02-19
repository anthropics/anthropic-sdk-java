// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.messages

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.messages.CacheControlEphemeral
import com.anthropic.models.messages.CitationCharLocationParam
import com.anthropic.models.messages.JsonOutputFormat
import com.anthropic.models.messages.Metadata
import com.anthropic.models.messages.Model
import com.anthropic.models.messages.OutputConfig
import com.anthropic.models.messages.TextBlockParam
import com.anthropic.models.messages.Tool
import com.anthropic.models.messages.ToolChoiceAuto
import com.anthropic.models.messages.batches.BatchCreateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class BatchServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchServiceAsync = client.messages().batches()

        val messageBatchFuture =
            batchServiceAsync.create(
                BatchCreateParams.builder()
                    .addRequest(
                        BatchCreateParams.Request.builder()
                            .customId("my-custom-id-1")
                            .params(
                                BatchCreateParams.Request.Params.builder()
                                    .maxTokens(1024L)
                                    .addUserMessage("Hello, world")
                                    .model(Model.CLAUDE_OPUS_4_6)
                                    .cacheControl(
                                        CacheControlEphemeral.builder()
                                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                            .build()
                                    )
                                    .container("container")
                                    .inferenceGeo("inference_geo")
                                    .metadata(
                                        Metadata.builder()
                                            .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                            .build()
                                    )
                                    .outputConfig(
                                        OutputConfig.builder()
                                            .effort(OutputConfig.Effort.LOW)
                                            .format(
                                                JsonOutputFormat.builder()
                                                    .schema(
                                                        JsonOutputFormat.Schema.builder()
                                                            .putAdditionalProperty(
                                                                "foo",
                                                                JsonValue.from("bar"),
                                                            )
                                                            .build()
                                                    )
                                                    .build()
                                            )
                                            .build()
                                    )
                                    .serviceTier(BatchCreateParams.Request.Params.ServiceTier.AUTO)
                                    .addStopSequence("string")
                                    .stream(true)
                                    .systemOfTextBlockParams(
                                        listOf(
                                            TextBlockParam.builder()
                                                .text("Today's date is 2024-06-01.")
                                                .cacheControl(
                                                    CacheControlEphemeral.builder()
                                                        .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                                        .build()
                                                )
                                                .addCitation(
                                                    CitationCharLocationParam.builder()
                                                        .citedText("cited_text")
                                                        .documentIndex(0L)
                                                        .documentTitle("x")
                                                        .endCharIndex(0L)
                                                        .startCharIndex(0L)
                                                        .build()
                                                )
                                                .build()
                                        )
                                    )
                                    .temperature(1.0)
                                    .enabledThinking(1024L)
                                    .toolChoice(
                                        ToolChoiceAuto.builder()
                                            .disableParallelToolUse(true)
                                            .build()
                                    )
                                    .addTool(
                                        Tool.builder()
                                            .inputSchema(
                                                Tool.InputSchema.builder()
                                                    .properties(
                                                        Tool.InputSchema.Properties.builder()
                                                            .putAdditionalProperty(
                                                                "location",
                                                                JsonValue.from("bar"),
                                                            )
                                                            .putAdditionalProperty(
                                                                "unit",
                                                                JsonValue.from("bar"),
                                                            )
                                                            .build()
                                                    )
                                                    .addRequired("location")
                                                    .build()
                                            )
                                            .name("name")
                                            .addAllowedCaller(Tool.AllowedCaller.DIRECT)
                                            .cacheControl(
                                                CacheControlEphemeral.builder()
                                                    .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                                    .build()
                                            )
                                            .deferLoading(true)
                                            .description(
                                                "Get the current weather in a given location"
                                            )
                                            .eagerInputStreaming(true)
                                            .addInputExample(
                                                Tool.InputExample.builder()
                                                    .putAdditionalProperty(
                                                        "foo",
                                                        JsonValue.from("bar"),
                                                    )
                                                    .build()
                                            )
                                            .strict(true)
                                            .type(Tool.Type.CUSTOM)
                                            .build()
                                    )
                                    .topK(5L)
                                    .topP(0.7)
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val messageBatch = messageBatchFuture.get()
        messageBatch.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchServiceAsync = client.messages().batches()

        val messageBatchFuture = batchServiceAsync.retrieve("message_batch_id")

        val messageBatch = messageBatchFuture.get()
        messageBatch.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchServiceAsync = client.messages().batches()

        val pageFuture = batchServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchServiceAsync = client.messages().batches()

        val deletedMessageBatchFuture = batchServiceAsync.delete("message_batch_id")

        val deletedMessageBatch = deletedMessageBatchFuture.get()
        deletedMessageBatch.validate()
    }

    @Test
    fun cancel() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchServiceAsync = client.messages().batches()

        val messageBatchFuture = batchServiceAsync.cancel("message_batch_id")

        val messageBatch = messageBatchFuture.get()
        messageBatch.validate()
    }

    @Disabled("Mock server doesn't support application/x-jsonl responses")
    @Test
    fun resultsStreaming() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchServiceAsync = client.messages().batches()

        val messageBatchIndividualResponseStreamResponse =
            batchServiceAsync.resultsStreaming("message_batch_id")

        val onCompleteFuture =
            messageBatchIndividualResponseStreamResponse
                .subscribe { messageBatchIndividualResponse ->
                    messageBatchIndividualResponse.validate()
                }
                .onCompleteFuture()
        onCompleteFuture.get()
    }
}
