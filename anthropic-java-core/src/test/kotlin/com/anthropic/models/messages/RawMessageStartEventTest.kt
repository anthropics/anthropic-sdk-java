package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RawMessageStartEventTest {

    @Test
    fun create() {
        val rawMessageStartEvent =
            RawMessageStartEvent.of(
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
            )

        assertThat(rawMessageStartEvent.message())
            .isEqualTo(
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
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStartEvent =
            RawMessageStartEvent.of(
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
            )

        val roundtrippedRawMessageStartEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStartEvent),
                jacksonTypeRef<RawMessageStartEvent>(),
            )

        assertThat(roundtrippedRawMessageStartEvent).isEqualTo(rawMessageStartEvent)
    }
}
