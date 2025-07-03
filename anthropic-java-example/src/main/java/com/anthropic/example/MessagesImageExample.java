package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.*;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public final class MessagesImageExample {
    private MessagesImageExample() {}

    public static void main(String[] args) throws IOException {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        byte[] logoBytes = classloader.getResource("logo.png").openStream().readAllBytes();
        String logoBase64 = Base64.getEncoder().encodeToString(logoBytes);

        ContentBlockParam logoImageParam = ContentBlockParam.ofImage(ImageBlockParam.builder()
                .source(Base64ImageSource.builder()
                        .mediaType(Base64ImageSource.MediaType.IMAGE_PNG)
                        .data(logoBase64)
                        .build())
                .build());
        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_20250514)
                .addUserMessage("Describe this image.")
                .addUserMessageOfBlockParams(List.of(logoImageParam))
                .maxTokens(2048)
                .build();

        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
    }
}
