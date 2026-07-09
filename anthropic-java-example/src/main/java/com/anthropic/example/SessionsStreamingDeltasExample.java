package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.helpers.BetaManagedAgentsEventAccumulator;
import com.anthropic.models.beta.agents.AgentCreateParams;
import com.anthropic.models.beta.agents.BetaManagedAgentsModel;
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfigParams;
import com.anthropic.models.beta.environments.EnvironmentCreateParams;
import com.anthropic.models.beta.sessions.BetaManagedAgentsDeltaType;
import com.anthropic.models.beta.sessions.SessionCreateParams;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsStreamSessionEvents;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock;
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams;
import com.anthropic.models.beta.sessions.events.EventSendParams;
import com.anthropic.models.beta.sessions.events.EventStreamParams;
import java.util.Iterator;
import java.util.List;

/**
 * Streams a session with {@code event_deltas} enabled and folds the {@code event_start} /
 * {@code event_delta} previews into {@code agent.message} snapshots with
 * {@link BetaManagedAgentsEventAccumulator}, printing the text as it arrives.
 */
public final class SessionsStreamingDeltasExample {
    private SessionsStreamingDeltasExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        // Create an environment
        var environment = client.beta()
                .environments()
                .create(EnvironmentCreateParams.builder()
                        .name("streaming-deltas-example")
                        .build());
        System.out.println("Created environment: " + environment.id());

        // Create an agent
        var agent = client.beta()
                .agents()
                .create(AgentCreateParams.builder()
                        .name("streaming-deltas-example")
                        .model(BetaManagedAgentsModelConfigParams.builder()
                                .id(BetaManagedAgentsModel.CLAUDE_SONNET_5)
                                .build())
                        .build());
        System.out.println("Created agent: " + agent.id());

        // Create a session pinned to the environment and agent
        var session = client.beta()
                .sessions()
                .create(SessionCreateParams.builder()
                        .environmentId(environment.id())
                        .agent(agent.id())
                        .build());
        System.out.println("Created session: " + session.id());

        // Fold delta previews into `agent.message` snapshots as they stream in
        var accumulator = BetaManagedAgentsEventAccumulator.create();

        // Open the event stream with `agent.message` deltas enabled so message text arrives
        // incrementally as `event_start` / `event_delta` previews before the buffered final event
        try (StreamResponse<BetaManagedAgentsStreamSessionEvents> streamResponse = client.beta()
                .sessions()
                .events()
                .streamStreaming(
                        session.id(),
                        EventStreamParams.builder()
                                .addEventDelta(BetaManagedAgentsDeltaType.AGENT_MESSAGE)
                                .build())) {
            // Send a user message
            client.beta()
                    .sessions()
                    .events()
                    .send(EventSendParams.builder()
                            .sessionId(session.id())
                            .addUserMessageEvent(List.of(BetaManagedAgentsUserMessageEventParams.Content.ofText(
                                    BetaManagedAgentsTextBlock.builder()
                                            .text("Write a short haiku about the ocean.")
                                            .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                            .build())))
                            .build());

            System.out.println("Streaming:");
            Iterator<BetaManagedAgentsStreamSessionEvents> it =
                    streamResponse.stream().iterator();
            while (it.hasNext()) {
                BetaManagedAgentsStreamSessionEvents event = accumulator.accumulate(it.next());

                if (event.isEventDelta()) {
                    // Redraw the in-progress preview text as each fragment arrives
                    System.out.print("\r"
                            + accumulator
                                    .agentMessageText(event.asEventDelta().eventId())
                                    .orElse(""));
                } else if (event.isAgentMessage()) {
                    // The canonical event replaced the lossy preview in the accumulator, so its
                    // text is guaranteed to be present
                    System.out.println();
                    System.out.println("[final] "
                            + accumulator
                                    .agentMessageText(event.asAgentMessage().id())
                                    .orElseThrow());
                } else if (event.isSessionStatusIdle()) {
                    // The session is no longer doing work and the stream stays open, so stop
                    // reading once the turn ends
                    if (event.asSessionStatusIdle().stopReason().isEndTurn()) {
                        break;
                    }
                } else if (event.isSessionError()) {
                    System.err.println("[error] " + event.asSessionError().error());
                    break;
                }
            }
        }
    }
}
