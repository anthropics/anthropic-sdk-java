package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.models.beta.agents.AgentCreateParams;
import com.anthropic.models.beta.agents.BetaManagedAgentsModel;
import com.anthropic.models.beta.environments.EnvironmentCreateParams;
import com.anthropic.models.beta.sessions.SessionCreateParams;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsStreamSessionEvents;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams;
import com.anthropic.models.beta.sessions.events.EventSendParams;
import java.util.Iterator;

public final class ManagedAgentsExample {
    private ManagedAgentsExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        // Create an environment
        var environment = client.beta().environments().create(
                EnvironmentCreateParams.builder()
                        .name("simple-example-environment")
                        .build());
        System.out.println("Created environment: " + environment.id());

        // Create an agent
        var agent = client.beta().agents().create(
                AgentCreateParams.builder()
                        .name("simple-example-agent")
                        .model(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
                        .build());
        System.out.println("Created agent: " + agent.id());

        // Create a session pinned to the agent version
        var session = client.beta().sessions().create(
                SessionCreateParams.builder()
                        .environmentId(environment.id())
                        .agent(agent.id())
                        .build());
        System.out.println("Created session: " + session.id());

        // Send a user message
        client.beta().sessions().events().send(
                EventSendParams.builder()
                        .sessionId(session.id())
                        .addUserMessageEvent(java.util.List.of(
                                BetaManagedAgentsUserMessageEventParams.Content.ofText(
                                        BetaManagedAgentsTextBlock.builder()
                                                .text("Hello Claude!")
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
