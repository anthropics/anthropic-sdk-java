package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.config.AuthenticationConfig;
import com.anthropic.config.AuthenticationType;
import com.anthropic.config.IdentityTokenConfig;
import com.anthropic.config.InMemoryProfileConfigProvider;
import com.anthropic.config.ProfileConfig;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

public class WorkloadIdentityExample {

    public static void main(String[] args) {
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .fromEnv()
                .configurationProvider(InMemoryProfileConfigProvider.of(ProfileConfig.builder()
                        .organizationId("1577a8a6-174a-4705-916c-6b22ae7a49e6")
                        .authentication(AuthenticationConfig.builder()
                                .type(AuthenticationType.OIDC_FEDERATION)
                                .federationRuleId("fdrl_01LYr6LE7tLbLLMB7smAV538")
                                .serviceAccountId("svac_014NJumCU6vTzKhnRyfKqGRT")
                                .identityToken(IdentityTokenConfig.builder()
                                        .source("value")
                                        .path(System.getenv("OIDC_TOKEN"))
                                        .build())
                                .build())
                        .build()))
                .build();

        Message message = client.messages()
                .create(MessageCreateParams.builder()
                        .model(Model.CLAUDE_OPUS_4_20250514)
                        .maxTokens(1024)
                        .addUserMessage("Hello")
                        .build());

        System.out.println(message);
    }
}
