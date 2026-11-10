package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageTest {

    @Test
    fun create() {
        val message =
            Message.builder()
                .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                .container(
                    Container.builder()
                        .id("container_011CpZohnwH4vuy7gazohgSP")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .addSkill(
                            ContainerSkill.builder()
                                .skillId("pdf")
                                .type(ContainerSkill.Type.ANTHROPIC)
                                .version("latest")
                                .build()
                        )
                        .build()
                )
                .addContent(
                    TextBlock.builder().citations(null).text("Hi! My name is Claude.").build()
                )
                .diagnostics(null)
                .model(Model.CLAUDE_OPUS_5_5)
                .stopDetails(null)
                .stopReason(StopReason.END_TURN)
                .stopSequence(null)
                .usage(
                    Usage.builder()
                        .cacheCreation(
                            CacheCreation.builder()
                                .ephemeral1hInputTokens(0L)
                                .ephemeral5mInputTokens(0L)
                                .build()
                        )
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inferenceGeo("global")
                        .inputTokens(2095L)
                        .outputTokens(503L)
                        .outputTokensDetails(OutputTokensDetails.of(0L))
                        .serverToolUse(
                            ServerToolUsage.builder()
                                .webFetchRequests(2L)
                                .webSearchRequests(0L)
                                .build()
                        )
                        .serviceTier(Usage.ServiceTier.STANDARD)
                        .speed(Usage.Speed.STANDARD)
                        .build()
                )
                .build()

        assertThat(message.id()).isEqualTo("msg_013Zva2CMHLNnXjNJJKqJ2EF")
        assertThat(message.container())
            .contains(
                Container.builder()
                    .id("container_011CpZohnwH4vuy7gazohgSP")
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .addSkill(
                        ContainerSkill.builder()
                            .skillId("pdf")
                            .type(ContainerSkill.Type.ANTHROPIC)
                            .version("latest")
                            .build()
                    )
                    .build()
            )
        assertThat(message.content())
            .containsExactly(
                ContentBlock.ofText(
                    TextBlock.builder().citations(null).text("Hi! My name is Claude.").build()
                )
            )
        assertThat(message.diagnostics()).isEmpty
        assertThat(message.model()).isEqualTo(Model.CLAUDE_OPUS_5_5)
        assertThat(message.stopDetails()).isEmpty
        assertThat(message.stopReason()).contains(StopReason.END_TURN)
        assertThat(message.stopSequence()).isEmpty
        assertThat(message.usage())
            .isEqualTo(
                Usage.builder()
                    .cacheCreation(
                        CacheCreation.builder()
                            .ephemeral1hInputTokens(0L)
                            .ephemeral5mInputTokens(0L)
                            .build()
                    )
                    .cacheCreationInputTokens(2051L)
                    .cacheReadInputTokens(2051L)
                    .inferenceGeo("global")
                    .inputTokens(2095L)
                    .outputTokens(503L)
                    .outputTokensDetails(OutputTokensDetails.of(0L))
                    .serverToolUse(
                        ServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
                    )
                    .serviceTier(Usage.ServiceTier.STANDARD)
                    .speed(Usage.Speed.STANDARD)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val message =
            Message.builder()
                .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                .container(
                    Container.builder()
                        .id("container_011CpZohnwH4vuy7gazohgSP")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .addSkill(
                            ContainerSkill.builder()
                                .skillId("pdf")
                                .type(ContainerSkill.Type.ANTHROPIC)
                                .version("latest")
                                .build()
                        )
                        .build()
                )
                .addContent(
                    TextBlock.builder().citations(null).text("Hi! My name is Claude.").build()
                )
                .diagnostics(null)
                .model(Model.CLAUDE_OPUS_5_5)
                .stopDetails(null)
                .stopReason(StopReason.END_TURN)
                .stopSequence(null)
                .usage(
                    Usage.builder()
                        .cacheCreation(
                            CacheCreation.builder()
                                .ephemeral1hInputTokens(0L)
                                .ephemeral5mInputTokens(0L)
                                .build()
                        )
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inferenceGeo("global")
                        .inputTokens(2095L)
                        .outputTokens(503L)
                        .outputTokensDetails(OutputTokensDetails.of(0L))
                        .serverToolUse(
                            ServerToolUsage.builder()
                                .webFetchRequests(2L)
                                .webSearchRequests(0L)
                                .build()
                        )
                        .serviceTier(Usage.ServiceTier.STANDARD)
                        .speed(Usage.Speed.STANDARD)
                        .build()
                )
                .build()

        val roundtrippedMessage =
            jsonMapper.readValue(jsonMapper.writeValueAsString(message), jacksonTypeRef<Message>())

        assertThat(roundtrippedMessage).isEqualTo(message)
    }
}
