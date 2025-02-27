package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.models.*;
import org.jetbrains.annotations.NotNull;

public final class MessagesStreamingExample {

    private static class StreamTextRecorder {
        private final StringBuilder text = new StringBuilder();

        public void record(TextDelta textDelta) {
            text.append(textDelta.text());
        }

        public String text() {
            return text.toString();
        }
    }

    private static class StreamMessageRecorder {
        private final Message.Builder messageBuilder = Message.builder();

        public void record(RawMessageStreamEvent event) {
            event.accept(new RawMessageStreamEvent.Visitor<Void>() {
                @Override
                public Void visitStart(RawMessageStartEvent start) {
                    System.out.println("START: " + start);
                    return null;
                }

                @Override
                public Void visitDelta(RawMessageDeltaEvent delta) {
                    System.out.println("DELTA: " + delta);
                    return null;
                }

                @Override
                public Void visitStop(RawMessageStopEvent stop) {
                    System.out.println("STOP: " + stop);
                    return null;
                }

                @Override
                public Void visitContentBlockStart(RawContentBlockStartEvent contentBlockStart) {
                    contentBlockStart.contentBlock().accept(new RawContentBlockStartEvent.ContentBlock.Visitor<Void>() {
                        @Override
                        public Void visitToolUse(@NotNull ToolUseBlock toolUse) {
                            messageBuilder.addContent(toolUse);
                            return null;
                        }

                        @Override
                        public Void visitText(@NotNull TextBlock text) {
                            messageBuilder.addContent(text);
                            return null;
                        }

                        @Override
                        public Void visitThinking(@NotNull ThinkingBlock thinking) {
                            return null;
                        }

                        @Override
                        public Void visitRedactedThinking(@NotNull RedactedThinkingBlock redactedThinking) {
                            return null;
                        }
                    });
                    //                    messageBuilder.addContent(contentBlockStart.contentBlock().)
                    System.out.println("CONTENT BLOCK START: " + contentBlockStart);
                    return null;
                }

                @Override
                public Void visitContentBlockDelta(RawContentBlockDeltaEvent contentBlockDelta) {
                    System.out.println("CONTENT BLOCK DELTA: " + contentBlockDelta);
                    return null;
                }

                @Override
                public Void visitContentBlockStop(RawContentBlockStopEvent contentBlockStop) {
                    System.out.println("CONTENT BLOCK STOP: " + contentBlockStop);
                    return null;
                }
            });
        }

        public Message message() {
            return messageBuilder.build();
        }
    }

    private MessagesStreamingExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        StreamMessageRecorder messageRecorder = new StreamMessageRecorder();
        StreamTextRecorder textRecorder = new StreamTextRecorder();

        try (StreamResponse<RawMessageStreamEvent> streamResponse =
                client.messages().createStreaming(createParams)) {
            streamResponse.stream()
                    .peek(messageRecorder::record)
                    .flatMap(event -> event.contentBlockDelta().stream())
                    .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
                    .peek(textRecorder::record)
                    .forEach(textDelta -> System.out.print(textDelta.text()));
        }

        System.out.printf(
                "\n\nResponse length: %,d characters.\n", textRecorder.text().length());
    }
}
