package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.ModelListParams;

public final class ModelListExample {
    private ModelListExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        client.models()
                // TODO: Update this example once we support `.list()` without arguments.
                .list(ModelListParams.builder().build())
                .autoPager()
                .forEach(model -> System.out.println(model.id()));
    }
}
