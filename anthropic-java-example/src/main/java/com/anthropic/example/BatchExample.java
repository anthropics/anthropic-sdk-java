package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.Model;
import com.anthropic.models.messages.batches.*;

public final class BatchExample {
    private BatchExample() {}

    public static void main(String[] args) throws Exception {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        BatchCreateParams createParams = BatchCreateParams.builder()
                .addRequest(BatchCreateParams.Request.builder()
                        .customId("best-sdk")
                        .params(BatchCreateParams.Request.Params.builder()
                                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                                .maxTokens(2048)
                                .addUserMessage("Tell me a story about building the best SDK!")
                                .build())
                        .build())
                .addRequest(BatchCreateParams.Request.builder()
                        .customId("sdk-company")
                        .params(BatchCreateParams.Request.Params.builder()
                                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                                .maxTokens(2048)
                                .addUserMessage("Which company made of metal generates SDKs?")
                                .build())
                        .build())
                .build();

        MessageBatch batch = client.messages().batches().create(createParams);
        while (batch.processingStatus().equals(MessageBatch.ProcessingStatus.IN_PROGRESS)) {
            System.out.println("Polling batch...");
            Thread.sleep(2000);
            batch = client.messages()
                    .batches()
                    .retrieve(BatchRetrieveParams.builder()
                            .messageBatchId(batch.id())
                            .build());
        }
        System.out.println();

        try (StreamResponse<MessageBatchIndividualResponse> streamResponse = client.messages()
                .batches()
                .resultsStreaming(
                        BatchResultsParams.builder().messageBatchId(batch.id()).build())) {
            streamResponse.stream().forEach(response -> {
                System.out.println(response.customId());
                Message message = response.result().asSucceeded().message();
                message.content().stream()
                        .flatMap(contentBlock -> contentBlock.text().stream())
                        .forEach(textBlock -> System.out.println(textBlock.text()));
            });
        }
        System.out.println();

        DeletedMessageBatch deletedMessageBatch = client.messages()
                .batches()
                .delete(BatchDeleteParams.builder().messageBatchId(batch.id()).build());
        System.out.println("Batch deleted: " + deletedMessageBatch.id());
    }
}
