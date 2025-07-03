package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent;
import com.anthropic.models.beta.messages.BetaRequestMcpServerToolConfiguration;
import com.anthropic.models.beta.messages.BetaRequestMcpServerUrlDefinition;
import com.anthropic.models.beta.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import java.util.Arrays;
import java.util.List;

public final class MessagesMcpExample {

    private MessagesMcpExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        BetaRequestMcpServerToolConfiguration toolConfiguration = BetaRequestMcpServerToolConfiguration.builder()
                .enabled(true)
                .allowedTools(Arrays.asList("echo", "add"))
                .build();

        BetaRequestMcpServerUrlDefinition mcpServer = BetaRequestMcpServerUrlDefinition.builder()
                .url("http://example-server.modelcontextprotocol.io/sse")
                .name("example")
                .toolConfiguration(toolConfiguration)
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_20250514)
                .maxTokens(1000)
                .mcpServers(List.of(mcpServer))
                .addUserMessage("Calculate 1+2")
                .putAdditionalHeader("anthropic-beta", "mcp-client-2025-04-04")
                .build();

        try (StreamResponse<BetaRawMessageStreamEvent> streamResponse =
                client.beta().messages().createStreaming(createParams)) {
            streamResponse.stream()
                    .flatMap(event -> event.contentBlockDelta().stream())
                    .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
                    .forEach(textDelta -> System.out.print(textDelta.text()));
            System.out.println();
        }
    }
}
