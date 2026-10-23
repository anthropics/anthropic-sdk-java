package com.anthropic.helpers;

import com.anthropic.core.JsonSchemaLocalValidation;
import com.anthropic.core.RequestOptions;
import com.anthropic.models.beta.messages.BetaMessage;
import com.anthropic.models.beta.messages.BetaMessageParam;
import com.anthropic.models.beta.messages.BetaOutputConfig;
import com.anthropic.models.beta.messages.StructuredMessage;
import com.anthropic.models.beta.messages.StructuredOutputConfig;
import com.anthropic.models.beta.messages.StructuredThreadCreateParams;
import com.anthropic.models.beta.messages.ThreadCreateParams;
import com.anthropic.services.async.beta.MessageServiceAsync;
import com.anthropic.services.blocking.beta.MessageService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Calls on a message thread whose overloads have to resolve from Java source. */
public final class MessageThreadJavaCalls {
    private MessageThreadJavaCalls() {}

    /** Sends five turns, one with each kind of message, and appends each kind before the last. */
    public static void sendEachKind(BetaMessageThread thread, BetaMessageParam param) {
        BetaMessage reply = thread.send("Text");
        thread.send(reply);
        thread.send(param);
        reply = thread.send(Collections.singletonList(param));
        thread.append(reply);
        thread.append(BetaMessageParam.ofUser("Appended"));
        thread.append(Collections.singletonList(param));
        thread.send(builder -> builder.addUserMessage("Modifier"));
    }

    /** Does the same on an asynchronous thread, waiting for each turn. */
    public static void sendEachKind(BetaMessageThreadAsync thread, BetaMessageParam param) {
        BetaMessage reply = thread.send("Text").join();
        thread.send(reply).join();
        thread.send(param).join();
        reply = thread.send(Collections.singletonList(param)).join();
        thread.append(reply);
        thread.append(BetaMessageParam.ofUser("Appended"));
        thread.append(Collections.singletonList(param));
        thread.send(builder -> builder.addUserMessage("Modifier")).join();
    }

    /** Creates a thread of a class with and without request options, and sends a turn on each. */
    public static <T> List<StructuredMessage<T>> sendStructured(MessageService messages, Class<T> outputType) {
        StructuredBetaMessageThread<T> thread = messages.thread(paramsOfClass(outputType));
        StructuredBetaMessageThread<T> withOptions = messages.thread(paramsOfConfig(outputType), RequestOptions.none());
        return Arrays.asList(
                thread.send("Text"), withOptions.fork().send(builder -> builder.addUserMessage("Modifier")));
    }

    /** Does the same on an asynchronous client, waiting for each turn. */
    public static <T> List<StructuredMessage<T>> sendStructured(MessageServiceAsync messages, Class<T> outputType) {
        StructuredBetaMessageThreadAsync<T> thread = messages.thread(paramsOfClass(outputType));
        StructuredBetaMessageThreadAsync<T> withOptions =
                messages.thread(paramsOfConfig(outputType), RequestOptions.none());
        return Arrays.asList(
                thread.send("Text").join(),
                withOptions
                        .fork()
                        .send(builder -> builder.addUserMessage("Modifier"))
                        .join());
    }

    private static <T> StructuredThreadCreateParams<T> paramsOfClass(Class<T> outputType) {
        return ThreadCreateParams.builder()
                .model("claude-sonnet-4-5")
                .outputConfig(outputType)
                .maxTokens(1024)
                .build();
    }

    private static <T> StructuredThreadCreateParams<T> paramsOfConfig(Class<T> outputType) {
        return ThreadCreateParams.builder()
                .model("claude-sonnet-4-5")
                .maxTokens(1024)
                .outputConfig(StructuredOutputConfig.<T>builder()
                        .format(outputType, JsonSchemaLocalValidation.NO)
                        .effort(BetaOutputConfig.Effort.LOW)
                        .build())
                .build();
    }
}
