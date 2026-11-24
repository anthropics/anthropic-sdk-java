package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class RawMessageStreamEventTest {

    @Test
    fun ofMessageStart() {
        val messageStart =
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

        val rawMessageStreamEvent = RawMessageStreamEvent.ofMessageStart(messageStart)

        assertThat(presentVariants(rawMessageStreamEvent))
            .isEqualTo(mapOf("messageStart" to Optional.of(messageStart)))
    }

    @Test
    fun ofMessageStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofMessageStart(
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
                            TextBlock.builder()
                                .citations(null)
                                .text("Hi! My name is Claude.")
                                .build()
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
            )

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
    }

    @Test
    fun ofMessageDelta() {
        val messageDelta =
            RawMessageDeltaEvent.builder()
                .delta(
                    RawMessageDeltaEvent.Delta.builder()
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
                        .stopDetails(
                            RefusalStopDetails.builder()
                                .category(RefusalStopDetails.Category.CYBER)
                                .explanation(
                                    "This request was declined because it conflicts with Anthropic's Usage Policy."
                                )
                                .build()
                        )
                        .stopReason(StopReason.END_TURN)
                        .stopSequence("stop_sequence")
                        .build()
                )
                .usage(
                    MessageDeltaUsage.builder()
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inputTokens(2095L)
                        .outputTokens(503L)
                        .outputTokensDetails(OutputTokensDetails.of(0L))
                        .serverToolUse(
                            ServerToolUsage.builder()
                                .webFetchRequests(2L)
                                .webSearchRequests(0L)
                                .build()
                        )
                        .build()
                )
                .build()

        val rawMessageStreamEvent = RawMessageStreamEvent.ofMessageDelta(messageDelta)

        assertThat(presentVariants(rawMessageStreamEvent))
            .isEqualTo(mapOf("messageDelta" to Optional.of(messageDelta)))
    }

    @Test
    fun ofMessageDeltaRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofMessageDelta(
                RawMessageDeltaEvent.builder()
                    .delta(
                        RawMessageDeltaEvent.Delta.builder()
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
                            .stopDetails(
                                RefusalStopDetails.builder()
                                    .category(RefusalStopDetails.Category.CYBER)
                                    .explanation(
                                        "This request was declined because it conflicts with Anthropic's Usage Policy."
                                    )
                                    .build()
                            )
                            .stopReason(StopReason.END_TURN)
                            .stopSequence("stop_sequence")
                            .build()
                    )
                    .usage(
                        MessageDeltaUsage.builder()
                            .cacheCreationInputTokens(2051L)
                            .cacheReadInputTokens(2051L)
                            .inputTokens(2095L)
                            .outputTokens(503L)
                            .outputTokensDetails(OutputTokensDetails.of(0L))
                            .serverToolUse(
                                ServerToolUsage.builder()
                                    .webFetchRequests(2L)
                                    .webSearchRequests(0L)
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
    }

    @Test
    fun ofMessageStop() {
        val messageStop = RawMessageStopEvent.builder().build()

        val rawMessageStreamEvent = RawMessageStreamEvent.ofMessageStop(messageStop)

        assertThat(presentVariants(rawMessageStreamEvent))
            .isEqualTo(mapOf("messageStop" to Optional.of(messageStop)))
    }

    @Test
    fun ofMessageStopRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofMessageStop(RawMessageStopEvent.builder().build())

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
    }

    @Test
    fun ofContentBlockStart() {
        val contentBlockStart =
            RawContentBlockStartEvent.builder()
                .contentBlock(
                    TextBlock.builder()
                        .addCitation(
                            CitationCharLocation.builder()
                                .citedText("The grass is green. The sky is blue.")
                                .documentIndex(0L)
                                .documentTitle("My Document")
                                .endCharIndex(0L)
                                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                                .startCharIndex(0L)
                                .build()
                        )
                        .text("text")
                        .build()
                )
                .index(0L)
                .build()

        val rawMessageStreamEvent = RawMessageStreamEvent.ofContentBlockStart(contentBlockStart)

        assertThat(presentVariants(rawMessageStreamEvent))
            .isEqualTo(mapOf("contentBlockStart" to Optional.of(contentBlockStart)))
    }

    @Test
    fun ofContentBlockStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofContentBlockStart(
                RawContentBlockStartEvent.builder()
                    .contentBlock(
                        TextBlock.builder()
                            .addCitation(
                                CitationCharLocation.builder()
                                    .citedText("The grass is green. The sky is blue.")
                                    .documentIndex(0L)
                                    .documentTitle("My Document")
                                    .endCharIndex(0L)
                                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                                    .startCharIndex(0L)
                                    .build()
                            )
                            .text("text")
                            .build()
                    )
                    .index(0L)
                    .build()
            )

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
    }

    @Test
    fun ofContentBlockDelta() {
        val contentBlockDelta =
            RawContentBlockDeltaEvent.builder().textDelta("text").index(0L).build()

        val rawMessageStreamEvent = RawMessageStreamEvent.ofContentBlockDelta(contentBlockDelta)

        assertThat(presentVariants(rawMessageStreamEvent))
            .isEqualTo(mapOf("contentBlockDelta" to Optional.of(contentBlockDelta)))
    }

    @Test
    fun ofContentBlockDeltaRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofContentBlockDelta(
                RawContentBlockDeltaEvent.builder().textDelta("text").index(0L).build()
            )

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
    }

    @Test
    fun ofContentBlockStop() {
        val contentBlockStop = RawContentBlockStopEvent.of(0L)

        val rawMessageStreamEvent = RawMessageStreamEvent.ofContentBlockStop(contentBlockStop)

        assertThat(presentVariants(rawMessageStreamEvent))
            .isEqualTo(mapOf("contentBlockStop" to Optional.of(contentBlockStop)))
    }

    @Test
    fun ofContentBlockStopRoundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStreamEvent =
            RawMessageStreamEvent.ofContentBlockStop(RawContentBlockStopEvent.of(0L))

        val roundtrippedRawMessageStreamEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStreamEvent),
                jacksonTypeRef<RawMessageStreamEvent>(),
            )

        assertThat(roundtrippedRawMessageStreamEvent).isEqualTo(rawMessageStreamEvent)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val rawMessageStreamEvent =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("type" to "unknown_variant", "index" to 0)),
                    jacksonTypeRef<RawMessageStreamEvent>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { rawMessageStreamEvent.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(rawMessageStreamEvent.index()).contains(0L)

        val mismatchedRawMessageStreamEvent =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "index" to listOf("invalid"))
                    ),
                    jacksonTypeRef<RawMessageStreamEvent>(),
                )

        assertThat(mismatchedRawMessageStreamEvent.index()).isEmpty
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val rawMessageStreamEvent =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<RawMessageStreamEvent>())

        val e = assertThrows<AnthropicInvalidDataException> { rawMessageStreamEvent.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(rawMessageStreamEvent.index()).isEmpty
    }

    private fun presentVariants(
        rawMessageStreamEvent: RawMessageStreamEvent
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "messageStart" to rawMessageStreamEvent.messageStart(),
                "messageDelta" to rawMessageStreamEvent.messageDelta(),
                "messageStop" to rawMessageStreamEvent.messageStop(),
                "contentBlockStart" to rawMessageStreamEvent.contentBlockStart(),
                "contentBlockDelta" to rawMessageStreamEvent.contentBlockDelta(),
                "contentBlockStop" to rawMessageStreamEvent.contentBlockStop(),
            )
            .filterValues { it.isPresent }
}
