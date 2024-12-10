package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.models.*;

public final class Main {
    private Main() {}

    public static void main(String[] args) {
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();
        MessageCreateParams.Builder createParamsBuilder = MessageCreateParams.builder()
                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                .maxTokens(1000)
                .addMessage(MessageParam.builder()
                        .role(MessageParam.Role.USER)
                        .content(MessageParam.Content.ofString("Tell me a story about building the best SDK!"))
                        .build());

        // Having a conversation in a loop
        for (int i = 0; i < 4; i++) {
            Message message = client.messages().create(createParamsBuilder.build());

            message.content().stream()
                    .flatMap(contentBlock -> contentBlock.textBlock().stream())
                    .forEach(textBlock -> System.out.println(textBlock.text()));

            System.out.println("\n-----------------------------------\n");

            createParamsBuilder
                    .addMessage(message.toParam())
                    .addMessage(MessageParam.builder()
                            .role(MessageParam.Role.USER)
                            .content(MessageParam.Content.ofString("But why???"))
                            .build());
        }

        // Streaming example
        try (StreamResponse<RawMessageStreamEvent> streamResponse = client.messages().createStreaming(createParamsBuilder.build())) {
            streamResponse.stream()
                    .flatMap(event -> event.rawContentBlockDeltaEvent().stream())
                    .flatMap(deltaEvent -> deltaEvent.delta().textDelta().stream())
                    .forEach(textDelta -> System.out.print(textDelta.text()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
