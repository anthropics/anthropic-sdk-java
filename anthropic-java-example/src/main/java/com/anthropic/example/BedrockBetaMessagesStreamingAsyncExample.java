package com.anthropic.example;

import com.anthropic.bedrock.backends.BedrockBackend;
import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.core.http.AsyncStreamResponse;
import com.anthropic.models.BetaMessageCreateParams;
import com.anthropic.models.BetaRawMessageStreamEvent;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * An example of retrieving "beta" messages asynchronously in a streaming
 * response from an Anthropic model running on the Amazon Bedrock backend.
 * </p>
 * <p>
 * To use the [BedrockBackend], the SDK module for Bedrock must be added to the
 * dependencies. See the SDK documentation for details.
 * </p>
 * <p>
 * AWS credentials must be configured to access Amazon Bedrock. This example
 * resolves the required credentials from the environment. The credentials can
 * be in system properties, environment variables, AWS CLI configuration files,
 * an AWS SSO configuration, etc. See AWS documentation for details.
 * </p>
 * <p>
 * With an Anthropic model deployed on a Bedrock service, it may be sufficient
 * to set the following environment variables to run this example:
 * </p>
 * <ul>
 *   <li>{@code AWS_REGION}:
 *       The name of the AWS region hosting the service.</li>
 *   <li>{@code AWS_ACCESS_KEY_ID}:
 *       The AWS access key ID identifying the service user.</li>
 *   <li>{@code AWS_SECRET_ACCESS_KEY}:
 *       The AWS secret access key used to sign the requests to the AWS
 *       service.</li>
 * </ul>
 */
public final class BedrockBetaMessagesStreamingAsyncExample {
    private BedrockBetaMessagesStreamingAsyncExample() {}

    public static void main(String[] args) {
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.builder()
                .backend(BedrockBackend.fromEnv())
                .build();

        BetaMessageCreateParams createParams = BetaMessageCreateParams.builder()
                .model("anthropic.claude-3-sonnet-20240229-v1:0")
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        CompletableFuture<Void> onCompleteFuture = new CompletableFuture<>();

        // TODO: Update this example once we support expose an `onCompleteFuture()` method.
        client.beta().messages().createStreaming(createParams)
                .subscribe(new AsyncStreamResponse.Handler<>() {
            @Override
            public void onNext(BetaRawMessageStreamEvent event) {
                event.contentBlockDelta().stream()
                        .flatMap(deltaEvent -> deltaEvent.delta().betaText().stream())
                        .forEach(textDelta -> System.out.print(textDelta.text()));
            }

            @Override
            public void onComplete(Optional<Throwable> error) {
                onCompleteFuture.complete(null);
            }
        });

        onCompleteFuture.join();
    }
}
