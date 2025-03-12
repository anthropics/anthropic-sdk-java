// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.messages

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.messages.CacheControlEphemeral
import com.anthropic.models.messages.CitationCharLocationParam
import com.anthropic.models.messages.Metadata
import com.anthropic.models.messages.Model
import com.anthropic.models.messages.TextBlockParam
import com.anthropic.models.messages.Tool
import com.anthropic.models.messages.ToolChoiceAuto
import com.anthropic.models.messages.batches.BatchCancelParams
import com.anthropic.models.messages.batches.BatchCreateParams
import com.anthropic.models.messages.batches.BatchDeleteParams
import com.anthropic.models.messages.batches.BatchResultsParams
import com.anthropic.models.messages.batches.BatchRetrieveParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
class BatchServiceAsyncTest {

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
                                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
                                    .metadata(
                                        Metadata.builder()
                                            .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                            .build()
                                    )
                                    .addStopSequence("string")
                                    .stream(true)
                                    .systemOfTextBlockParams(
                                        listOf(
                                            TextBlockParam.builder()
                                                .text("Today's date is 2024-06-01.")
                                                .cacheControl(
                                                    CacheControlEphemeral.builder().build()
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
                                                        JsonValue.from(
                                                            mapOf(
                                                                "location" to
                                                                    mapOf(
                                                                        "description" to
                                                                            "The city and state, e.g. San Francisco, CA",
                                                                        "type" to "string",
                                                                    ),
                                                                "unit" to
                                                                    mapOf(
                                                                        "description" to
                                                                            "Unit for the output - one of (celsius, fahrenheit)",
                                                                        "type" to "string",
                                                                    ),
                                                            )
                                                        )
                                                    )
                                                    .build()
                                            )
                                            .name("name")
                                            .cacheControl(CacheControlEphemeral.builder().build())
                                            .description(
                                                "Get the current weather in a given location"
                                            )
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

        val messageBatchFuture =
            batchServiceAsync.retrieve(
                BatchRetrieveParams.builder().messageBatchId("message_batch_id").build()
            )

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

        val deletedMessageBatchFuture =
            batchServiceAsync.delete(
                BatchDeleteParams.builder().messageBatchId("message_batch_id").build()
            )

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

        val messageBatchFuture =
            batchServiceAsync.cancel(
                BatchCancelParams.builder().messageBatchId("message_batch_id").build()
            )

        val messageBatch = messageBatchFuture.get()
        messageBatch.validate()
    }

    @Disabled("Prism doesn't support JSONL responses yet")
    @Test
    fun resultsStreaming() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchServiceAsync = client.messages().batches()

        val messageBatchIndividualResponseStreamResponse =
            batchServiceAsync.resultsStreaming(
                BatchResultsParams.builder().messageBatchId("message_batch_id").build()
            )

        val onCompleteFuture =
            messageBatchIndividualResponseStreamResponse
                .subscribe { messageBatchIndividualResponse ->
                    messageBatchIndividualResponse.validate()
                }
                .onCompleteFuture()
        onCompleteFuture.get()
    }
}
