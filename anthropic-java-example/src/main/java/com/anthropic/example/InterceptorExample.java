package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.Interceptor;
import com.anthropic.core.http.HttpRequest;
import com.anthropic.core.http.HttpResponse;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public final class InterceptorExample {
    private InterceptorExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .fromEnv()
                .addInterceptor(new TimingInterceptor())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_20250514)
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        // Sync
        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));

        // Async
        client.async()
                .messages()
                .create(createParams)
                .thenAccept(message -> message.content().stream()
                        .flatMap(contentBlock -> contentBlock.text().stream())
                        .forEach(textBlock -> System.out.println(textBlock.text())))
                .join();
    }

    // NOTE: If you only use either sync or async, then you can just implement one of these and throw an exception in
    // the other!
    private static final class TimingInterceptor implements Interceptor {

        @Override
        public HttpResponse intercept(Chain chain) {
            HttpRequest request = chain.request();

            long t1 = System.nanoTime();
            System.out.println("Sending request...");

            HttpResponse response = chain.proceed(request);

            long t2 = System.nanoTime();
            System.out.printf("Received response in %s!\n", Duration.ofNanos(t2 - t1));

            return response;
        }

        @Override
        public CompletableFuture<HttpResponse> interceptAsync(AsyncChain chain) {
            HttpRequest request = chain.request();

            long t1 = System.nanoTime();
            System.out.println("Sending request...");

            return chain.proceed(request).thenApply(response -> {
                long t2 = System.nanoTime();
                System.out.printf("Received response in %s!\n", Duration.ofNanos(t2 - t1));

                return response;
            });
        }
    }
}
