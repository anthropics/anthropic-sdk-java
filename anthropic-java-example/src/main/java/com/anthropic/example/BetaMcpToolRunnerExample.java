package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.helpers.BetaToolRunner;
import com.anthropic.helpers.McpBetaTool;
import com.anthropic.mcp.BetaMcp;
import com.anthropic.models.beta.messages.BetaMessage;
import com.anthropic.models.beta.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientStreamableHttpTransport;
import io.modelcontextprotocol.json.McpJsonDefaults;
import io.modelcontextprotocol.spec.McpSchema;
import java.util.List;

/**
 * Example showing how to use MCP helpers with toolRunner().
 *
 * <p>Connects to the remote GitHub MCP server over HTTP, converts its tools to
 * Anthropic-compatible tools using {@link BetaMcp#addMcpTools}, and runs them in a {@link
 * BetaToolRunner} loop.
 *
 * <p>Prerequisites:
 *
 * <ul>
 *   <li>Set the {@code ANTHROPIC_API_KEY} environment variable.
 *   <li>Set the {@code GITHUB_PERSONAL_ACCESS_TOKEN} environment variable.
 * </ul>
 *
 * <p>Run with:
 *
 * <pre>{@code
 * ./gradlew :anthropic-java-example:run -Pexample=BetaMcpToolRunner
 * }</pre>
 */
public final class BetaMcpToolRunnerExample {
    private BetaMcpToolRunnerExample() {}

    public static void main(String[] args) throws Exception {
        AnthropicClient anthropic = AnthropicOkHttpClient.fromEnv();

        String githubToken = System.getenv("GITHUB_TOKEN");

        // Connect to the remote GitHub MCP server over streamable HTTP.
        HttpClientStreamableHttpTransport transport = HttpClientStreamableHttpTransport.builder(
                        "https://api.githubcopilot.com/mcp/")
                .jsonMapper(McpJsonDefaults.getMapper())
                .customizeRequest(req -> req.header("Authorization", "Bearer " + githubToken))
                .supportedProtocolVersions(List.of("2025-03-26"))
                .build();

        try (McpSyncClient mcpClient = McpClient.sync(transport)
                .clientInfo(new McpSchema.Implementation("anthropic-java-example", "1.0.0"))
                .build()) {

            mcpClient.initialize();

            // Convert MCP tools into Anthropic-compatible tool objects.
            List<McpBetaTool> betaTools = BetaMcp.mcpTools(mcpClient.listTools().tools(), mcpClient);

            System.out.printf("Connected to MCP server with %d tools:%n", betaTools.size());
            for (McpBetaTool tool : betaTools) {
                System.out.println("  - " + tool.name());
            }
            System.out.println();

            MessageCreateParams params = MessageCreateParams.builder()
                    .model(Model.CLAUDE_SONNET_4_5)
                    .maxTokens(1024)
                    .addUserMessage(
                            "List the 5 most recently opened issues in the github/github-mcp-server repository. For"
                                    + " each, include the issue number, title, and who opened it.")
                    .addTools(betaTools)
                    .build();

            // Run a conversation with toolRunner().
            BetaToolRunner runner = anthropic.beta().messages().toolRunner(params);
            for (BetaMessage message : runner) {
                System.out.println(message);
            }
        }
    }
}
