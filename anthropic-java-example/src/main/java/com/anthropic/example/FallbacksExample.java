package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.RequestOptions;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.helpers.BetaFallbackState;
import com.anthropic.helpers.BetaMessageAccumulator;
import com.anthropic.helpers.BetaRefusalFallbackInterceptor;
import com.anthropic.models.beta.AnthropicBeta;
import com.anthropic.models.beta.messages.BetaFallbackParam;
import com.anthropic.models.beta.messages.BetaMessage;
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent;
import com.anthropic.models.beta.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

/**
 * An example of falling back to another model when a request is refused: server-side via the
 * {@code fallbacks} request parameter (preferred), or client-side via a
 * {@link BetaRefusalFallbackInterceptor} when the provider doesn't support server-side fallbacks.
 */
public final class FallbacksExample {
    private FallbacksExample() {}

    public static void main(String[] args) {
        // 1. Server-side fallbacks (preferred): the API retries a refusal itself — one request, a
        // plain client, no client-side logic. Use this when talking to the API directly.
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();
        BetaMessage served = client.beta()
                .messages()
                .create(MessageCreateParams.builder()
                        .model(Model.CLAUDE_FABLE_5)
                        .maxTokens(1024)
                        .addUserMessage("Some prompt that triggers a refusal")
                        .addFallback(BetaFallbackParam.builder()
                                .model(Model.CLAUDE_OPUS_4_8)
                                .build())
                        .addBeta(AnthropicBeta.SERVER_SIDE_FALLBACK_2026_06_01)
                        .build());
        System.out.println("server-side, served by: " + served.model().asString());

        // If your provider doesn't support server-side fallbacks, register the client-side
        // interceptor instead:
        AnthropicClient fallbackClient = AnthropicOkHttpClient.builder()
                .fromEnv()
                .addInterceptor(BetaRefusalFallbackInterceptor.builder()
                        .addFallback(Model.CLAUDE_OPUS_4_8)
                        .build())
                .build();
        // Pins follow-ups to the model that accepted.
        BetaFallbackState state = BetaFallbackState.create();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_FABLE_5)
                .maxTokens(1024)
                .addUserMessage("Some prompt that triggers a refusal")
                .build();

        // 2. Streaming: on a refusal the interceptor retries and splices the fallback's events
        // onto the open stream — one continuous message, with a `fallback` content block marking
        // the model boundary.
        BetaMessageAccumulator accumulator = BetaMessageAccumulator.create();
        try (StreamResponse<BetaRawMessageStreamEvent> streamResponse = fallbackClient
                .beta()
                .messages()
                .createStreaming(
                        createParams,
                        RequestOptions.builder().fallbackState(state).build())) {
            streamResponse.stream().peek(accumulator::accumulate).forEach(event -> {
                // Text streams continuously across the model boundary.
                event.contentBlockDelta()
                        .flatMap(deltaEvent -> deltaEvent.delta().text())
                        .ifPresent(textDelta -> System.out.print(textDelta.text()));
                event.contentBlockStart()
                        .flatMap(startEvent -> startEvent.contentBlock().fallback())
                        .ifPresent(fallback -> System.out.println("\n--- fell back: "
                                + fallback.from().model().asString() + " -> "
                                + fallback.to().model().asString() + " ---"));
            });
        }
        System.out.println(
                "\nstreaming, served by: " + accumulator.message().model().asString());

        // 3. Non-streaming: same interceptor, the retry just happens before you get the message
        // back.
        BetaMessage message = fallbackClient
                .beta()
                .messages()
                .create(
                        createParams,
                        // Reusing the state keeps the conversation pinned.
                        RequestOptions.builder().fallbackState(state).build());
        System.out.println("non-streaming, served by: " + message.model().asString());
    }
}
