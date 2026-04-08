package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.JsonValue;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.models.beta.agents.AgentCreateParams;
import com.anthropic.models.beta.agents.AgentUpdateParams;
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401Params;
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomToolInputSchema;
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomToolParams;
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpToolsetParams;
import com.anthropic.models.beta.agents.BetaManagedAgentsModel;
import com.anthropic.models.beta.agents.BetaManagedAgentsUrlMcpServerParams;
import com.anthropic.models.beta.environments.EnvironmentCreateParams;
import com.anthropic.models.beta.sessions.SessionCreateParams;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsStreamSessionEvents;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserCustomToolResultEventParams;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams;
import com.anthropic.models.beta.sessions.events.EventSendParams;
import com.anthropic.models.beta.skills.SkillCreateParams;
import com.anthropic.models.beta.vaults.VaultCreateParams;
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsStaticBearerCreateParams;
import com.anthropic.models.beta.vaults.credentials.CredentialCreateParams;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class ManagedAgentsComprehensiveExample {
    private ManagedAgentsComprehensiveExample() {}

    private static final String MCP_SERVER_NAME = "github";
    private static final String MCP_SERVER_URL = "https://api.githubcopilot.com/mcp/";

    private static final String PROMPT =
            "Hi! List every tool and skill you have access to, grouped by where they "
                    + "came from (built-in toolset, custom tool, MCP server, skills).";

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        String githubToken = System.getenv("GITHUB_TOKEN");
        if (githubToken == null || githubToken.isEmpty()) {
            throw new RuntimeException(
                    "GITHUB_TOKEN is required (use a fine-grained PAT with public-repo read only)");
        }

        // Create an environment
        var environment = client.beta().environments().create(
                EnvironmentCreateParams.builder()
                        .name("comprehensive-example-environment")
                        .build());
        System.out.println("Created environment: " + environment.id());

        // Create a vault and store the MCP server credential in it
        var vault = client.beta().vaults().create(
                VaultCreateParams.builder()
                        .displayName("comprehensive-example-vault")
                        .build());
        System.out.println("Created vault: " + vault.id());

        var credential = client.beta().vaults().credentials().create(
                CredentialCreateParams.builder()
                        .vaultId(vault.id())
                        .displayName("github-mcp")
                        .auth(BetaManagedAgentsStaticBearerCreateParams.builder()
                                .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
                                .mcpServerUrl(MCP_SERVER_URL)
                                .token(githubToken)
                                .build())
                        .build());
        System.out.println("Created credential: " + credential.id());

        // Upload a custom skill
        var skill = client.beta().skills().create(
                SkillCreateParams.builder()
                        .displayTitle("comprehensive-greeting-" + System.currentTimeMillis())
                        .addFile(Paths.get("greeting-SKILL.md"))
                        .build());
        System.out.println("Created skill: " + skill.id());

        // Create v1 of the agent with the built-in toolset, an MCP server, and a custom tool
        var agentV1 = client.beta().agents().create(
                AgentCreateParams.builder()
                        .name("comprehensive-example-agent")
                        .model(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
                        .system("You are a helpful assistant.")
                        .addMcpServer(BetaManagedAgentsUrlMcpServerParams.builder()
                                .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                                .name(MCP_SERVER_NAME)
                                .url(MCP_SERVER_URL)
                                .build())
                        .addTool(BetaManagedAgentsAgentToolset20260401Params.builder()
                                .type(BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401)
                                .build())
                        .addTool(BetaManagedAgentsMcpToolsetParams.builder()
                                .type(BetaManagedAgentsMcpToolsetParams.Type.MCP_TOOLSET)
                                .mcpServerName(MCP_SERVER_NAME)
                                .build())
                        .addTool(BetaManagedAgentsCustomToolParams.builder()
                                .type(BetaManagedAgentsCustomToolParams.Type.CUSTOM)
                                .name("get_weather")
                                .description("Look up the current weather for a city.")
                                .inputSchema(BetaManagedAgentsCustomToolInputSchema.builder()
                                        .type(BetaManagedAgentsCustomToolInputSchema.Type.OBJECT)
                                        .properties(BetaManagedAgentsCustomToolInputSchema.Properties.builder()
                                                .putAdditionalProperty("city", JsonValue.from(Map.of(
                                                        "type", "string")))
                                                .build())
                                        .required(List.of("city"))
                                        .build())
                                .build())
                        .build());
        System.out.println("Created agent v1: " + agentV1.id());

        // Patch the agent to v2 by adding skills; each update bumps the version
        var agent = client.beta().agents().update(
                AgentUpdateParams.builder()
                        .agentId(agentV1.id())
                        .version(agentV1.version())
                        .addCustomSkill(skill.id())
                        .addAnthropicSkill("xlsx")
                        .build());
        System.out.println("Patched agent to v2: " + agent.id());

        var versions = client.beta().agents().versions().list(agent.id());
        System.out.println("Agent versions: " + versions.data());

        // Create a session pinned to v2; the vault supplies the MCP credential
        var session = client.beta().sessions().create(
                SessionCreateParams.builder()
                        .environmentId(environment.id())
                        .agent(agent.id())
                        .addVaultId(vault.id())
                        .build());
        System.out.println("Created session: " + session.id());

        // Send a prompt and stream events, answering the custom tool if called
        client.beta().sessions().events().send(
                EventSendParams.builder()
                        .sessionId(session.id())
                        .addUserMessageEvent(List.of(
                                BetaManagedAgentsUserMessageEventParams.Content.ofText(
                                        BetaManagedAgentsTextBlock.builder()
                                                .text(PROMPT)
                                                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                                .build())))
                        .build());

        System.out.println("Streaming events:");
        try (StreamResponse<BetaManagedAgentsStreamSessionEvents> streamResponse =
                client.beta().sessions().events().streamStreaming(session.id())) {
            Iterator<BetaManagedAgentsStreamSessionEvents> it = streamResponse.stream().iterator();
            while (it.hasNext()) {
                BetaManagedAgentsStreamSessionEvents event = it.next();
                System.out.println(event);

                // If the agent calls the custom get_weather tool, provide a result
                if (event.isAgentCustomToolUse()) {
                    var toolUse = event.asAgentCustomToolUse();
                    if ("get_weather".equals(toolUse.name())) {
                        client.beta().sessions().events().send(
                                EventSendParams.builder()
                                        .sessionId(session.id())
                                        .addEvent(BetaManagedAgentsUserCustomToolResultEventParams.builder()
                                                .type(BetaManagedAgentsUserCustomToolResultEventParams.Type.USER_CUSTOM_TOOL_RESULT)
                                                .customToolUseId(toolUse.id())
                                                .addTextContent("{\"temperature_c\": 14}")
                                                .build())
                                        .build());
                    }
                }

                // Stop once we reach a terminal idle state (end_turn)
                if (event.isSessionStatusIdle()) {
                    var idle = event.asSessionStatusIdle();
                    if (idle.stopReason().isEndTurn()) {
                        break;
                    }
                }
            }
        }
    }
}
