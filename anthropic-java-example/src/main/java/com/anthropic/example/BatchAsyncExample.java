package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.*;
import com.anthropic.models.MessageBatch.ProcessingStatus;
import java.util.concurrent.CompletableFuture;

public final class BatchAsyncExample {
    private BatchAsyncExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

        MessageBatchCreateParams createParams = MessageBatchCreateParams.builder()
                .addRequest(MessageBatchCreateParams.Request.builder()
                        .customId("best-sdk")
                        .params(MessageBatchCreateParams.Request.Params.builder()
                                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                                .maxTokens(2048)
                                .addUserMessage("Tell me a story about building the best SDK!")
                                .build())
                        .build())
                .addRequest(MessageBatchCreateParams.Request.builder()
                        .customId("sdk-company")
                        .params(MessageBatchCreateParams.Request.Params.builder()
                                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                                .maxTokens(2048)
                                .addUserMessage("Which company made of metal generates SDKs?")
                                .build())
                        .build())
                .build();

        client.messages()
                .batches()
                .create(createParams)
                .thenComposeAsync(batch -> pollBatch(client, batch))
                .thenComposeAsync(batch -> {
                    System.out.println();
                    return client.messages()
                            .batches()
                            .resultsStreaming(MessageBatchResultsParams.builder()
                                    .messageBatchId(batch.id())
                                    .build())
                            .subscribe(response -> {
                                System.out.println(response.customId());
                                Message message =
                                        response.result().asSucceeded().message();
                                message.content().stream()
                                        .flatMap(contentBlock -> contentBlock.text().stream())
                                        .forEach(textBlock -> System.out.println(textBlock.text()));
                            })
                            .onCompleteFuture()
                            .thenApply(unused -> batch);
                })
                .thenComposeAsync(batch -> client.messages()
                        .batches()
                        .delete(MessageBatchDeleteParams.builder()
                                .messageBatchId(batch.id())
                                .build()))
                .thenAccept(deletedMessageBatch -> System.out.println("Batch deleted: " + deletedMessageBatch.id()))
                .join();
    }

    private static CompletableFuture<MessageBatch> pollBatch(AnthropicClientAsync client, MessageBatch batch) {
        if (!batch.processingStatus().equals(ProcessingStatus.IN_PROGRESS)) {
            return CompletableFuture.completedFuture(batch);
        }

        System.out.println("Polling batch...");
        try {
            java.lang.Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return client.messages()
                .batches()
                .retrieve(MessageBatchRetrieveParams.builder()
                        .messageBatchId(batch.id())
                        .build())
                .thenComposeAsync(newBatch -> pollBatch(client, newBatch));
    }
}
