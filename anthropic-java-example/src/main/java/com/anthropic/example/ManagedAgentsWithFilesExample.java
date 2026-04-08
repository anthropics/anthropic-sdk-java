package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.models.beta.agents.AgentCreateParams;
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401Params;
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfigParams;
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy;
import com.anthropic.models.beta.agents.BetaManagedAgentsModel;
import com.anthropic.models.beta.environments.EnvironmentCreateParams;
import com.anthropic.models.beta.files.FileUploadParams;
import com.anthropic.models.beta.sessions.BetaManagedAgentsFileResourceParams;
import com.anthropic.models.beta.sessions.SessionCreateParams;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsStreamSessionEvents;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams;
import com.anthropic.models.beta.sessions.events.EventSendParams;
import com.anthropic.models.beta.sessions.resources.ResourceListParams;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public final class ManagedAgentsWithFilesExample {
    private ManagedAgentsWithFilesExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        // Create an environment
        var environment = client.beta().environments().create(
                EnvironmentCreateParams.builder()
                        .name("files-example-environment")
                        .build());
        System.out.println("Created environment: " + environment.id());

        // Create an agent with the built-in toolset and an always-allow permission policy
        var agent = client.beta().agents().create(
                AgentCreateParams.builder()
                        .name("files-example-agent")
                        .model(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
                        .addTool(BetaManagedAgentsAgentToolset20260401Params.builder()
                                .type(BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401)
                                .defaultConfig(BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                        .enabled(true)
                                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder()
                                                .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                                .build())
                                        .build())
                                .build())
                        .build());
        System.out.println("Created agent: " + agent.id());

        // Upload a file (resolved relative to the example working directory)
        var file = client.beta().files().upload(
                FileUploadParams.builder()
                        .file(Paths.get("data.csv"))
                        .build());
        System.out.println("Uploaded file: " + file.id());

        // Create a session with the file mounted as a resource
        var session = client.beta().sessions().create(
                SessionCreateParams.builder()
                        .environmentId(environment.id())
                        .agent(agent.id())
                        .addResource(BetaManagedAgentsFileResourceParams.builder()
                                .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                                .fileId(file.id())
                                .mountPath("data.csv")
                                .build())
                        .build());
        System.out.println("Created session: " + session.id());

        // List session resources
        var resources = client.beta().sessions().resources().list(
                ResourceListParams.builder()
                        .sessionId(session.id())
                        .build());
        System.out.println("Listed session resources: " + resources.data());

        // Send a prompt asking the agent to read the mounted file
        client.beta().sessions().events().send(
                EventSendParams.builder()
                        .sessionId(session.id())
                        .addUserMessageEvent(List.of(
                                BetaManagedAgentsUserMessageEventParams.Content.ofText(
                                        BetaManagedAgentsTextBlock.builder()
                                                .text("Read /uploads/data.csv and tell me the column names.")
                                                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                                .build())))
                        .build());

        // Stream events until the session goes idle
        System.out.println("Streaming events:");
        try (StreamResponse<BetaManagedAgentsStreamSessionEvents> streamResponse =
                client.beta().sessions().events().streamStreaming(session.id())) {
            Iterator<BetaManagedAgentsStreamSessionEvents> it = streamResponse.stream().iterator();
            while (it.hasNext()) {
                BetaManagedAgentsStreamSessionEvents event = it.next();
                System.out.println(event);
                if (event.isSessionStatusIdle()) {
                    break;
                }
            }
        }
    }
}
