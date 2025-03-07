package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.ModelListPageAsync;
import java.util.concurrent.CompletableFuture;

public final class ModelListAsyncExample {
    private ModelListAsyncExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

        CompletableFuture<ModelListPageAsync> pageFuture = client.models().list();
        pageFuture
                .thenComposeAsync(page -> page.autoPager()
                        .forEach(
                                model -> {
                                    System.out.println(model.id());
                                    // Keep iterating
                                    return true;
                                },
                                pageFuture.defaultExecutor()))
                .join();
    }
}
