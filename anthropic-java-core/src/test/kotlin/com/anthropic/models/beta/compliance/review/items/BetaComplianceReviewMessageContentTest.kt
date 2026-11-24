package com.anthropic.models.beta.compliance.review.items

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.anthropic.models.beta.messages.BetaClearToolUses20250919EditResponse
import com.anthropic.models.beta.messages.BetaContainer
import com.anthropic.models.beta.messages.BetaContainerSkill
import com.anthropic.models.beta.messages.BetaContextManagementResponse
import com.anthropic.models.beta.messages.BetaFallbackCreditRedeemed
import com.anthropic.models.beta.messages.BetaFallbackCreditUsage
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageIterationUsage
import com.anthropic.models.beta.messages.BetaOutputTokensDetails
import com.anthropic.models.beta.messages.BetaServerToolUsage
import com.anthropic.models.beta.messages.BetaStopReason
import com.anthropic.models.beta.messages.BetaTextBlock
import com.anthropic.models.beta.messages.BetaThinkingDroppedInputTransformation
import com.anthropic.models.beta.messages.BetaUsage
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaComplianceReviewMessageContentTest {

    @Test
    fun ofCreateMessage() {
        val createMessage =
            BetaComplianceReviewCreateMessageContent.builder()
                .request(
                    BetaComplianceReviewCreateMessageContent.Request.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .response(
                    BetaMessage.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .container(
                            BetaContainer.builder()
                                .id("container_011CpZohnwH4vuy7gazohgSP")
                                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .addSkill(
                                    BetaContainerSkill.builder()
                                        .skillId("pdf")
                                        .type(BetaContainerSkill.Type.ANTHROPIC)
                                        .version("latest")
                                        .build()
                                )
                                .build()
                        )
                        .addContent(
                            BetaTextBlock.builder()
                                .citations(null)
                                .text("Hi! My name is Claude.")
                                .build()
                        )
                        .contextManagement(
                            BetaContextManagementResponse.builder()
                                .addAppliedEdit(
                                    BetaClearToolUses20250919EditResponse.builder()
                                        .clearedInputTokens(0L)
                                        .clearedToolUses(0L)
                                        .build()
                                )
                                .build()
                        )
                        .diagnostics(null)
                        .model(Model.CLAUDE_OPUS_5_5)
                        .stopDetails(null)
                        .stopReason(BetaStopReason.END_TURN)
                        .stopSequence(null)
                        .usage(
                            BetaUsage.builder()
                                .cacheCreation(
                                    BetaCacheCreation.builder()
                                        .ephemeral1hInputTokens(0L)
                                        .ephemeral5mInputTokens(0L)
                                        .build()
                                )
                                .cacheCreationInputTokens(2051L)
                                .cacheReadInputTokens(2051L)
                                .fallbackCredit(
                                    BetaFallbackCreditUsage.builder()
                                        .status(BetaFallbackCreditRedeemed.builder().build())
                                        .build()
                                )
                                .inferenceGeo("global")
                                .inputTokens(2095L)
                                .addIteration(
                                    BetaMessageIterationUsage.builder()
                                        .cacheCreation(
                                            BetaCacheCreation.builder()
                                                .ephemeral1hInputTokens(0L)
                                                .ephemeral5mInputTokens(0L)
                                                .build()
                                        )
                                        .cacheCreationInputTokens(0L)
                                        .cacheReadInputTokens(0L)
                                        .inputTokens(0L)
                                        .model(Model.CLAUDE_FABLE_5_2)
                                        .outputTokens(0L)
                                        .build()
                                )
                                .outputTokens(503L)
                                .outputTokensDetails(BetaOutputTokensDetails.of(0L))
                                .serverToolUse(
                                    BetaServerToolUsage.builder()
                                        .webFetchRequests(2L)
                                        .webSearchRequests(0L)
                                        .build()
                                )
                                .serviceTier(BetaUsage.ServiceTier.STANDARD)
                                .speed(BetaUsage.Speed.STANDARD)
                                .build()
                        )
                        .addInputTransformation(
                            BetaThinkingDroppedInputTransformation.builder()
                                .path("path")
                                .reason(
                                    BetaThinkingDroppedInputTransformation.Reason
                                        .MODEL_BINDING_MISMATCH
                                )
                                .build()
                        )
                        .build()
                )
                .build()

        val betaComplianceReviewMessageContent =
            BetaComplianceReviewMessageContent.ofCreateMessage(createMessage)

        assertThat(presentVariants(betaComplianceReviewMessageContent))
            .isEqualTo(mapOf("createMessage" to Optional.of(createMessage)))
    }

    @Test
    fun ofCreateMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewMessageContent =
            BetaComplianceReviewMessageContent.ofCreateMessage(
                BetaComplianceReviewCreateMessageContent.builder()
                    .request(
                        BetaComplianceReviewCreateMessageContent.Request.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .response(
                        BetaMessage.builder()
                            .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                            .container(
                                BetaContainer.builder()
                                    .id("container_011CpZohnwH4vuy7gazohgSP")
                                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                    .addSkill(
                                        BetaContainerSkill.builder()
                                            .skillId("pdf")
                                            .type(BetaContainerSkill.Type.ANTHROPIC)
                                            .version("latest")
                                            .build()
                                    )
                                    .build()
                            )
                            .addContent(
                                BetaTextBlock.builder()
                                    .citations(null)
                                    .text("Hi! My name is Claude.")
                                    .build()
                            )
                            .contextManagement(
                                BetaContextManagementResponse.builder()
                                    .addAppliedEdit(
                                        BetaClearToolUses20250919EditResponse.builder()
                                            .clearedInputTokens(0L)
                                            .clearedToolUses(0L)
                                            .build()
                                    )
                                    .build()
                            )
                            .diagnostics(null)
                            .model(Model.CLAUDE_OPUS_5_5)
                            .stopDetails(null)
                            .stopReason(BetaStopReason.END_TURN)
                            .stopSequence(null)
                            .usage(
                                BetaUsage.builder()
                                    .cacheCreation(
                                        BetaCacheCreation.builder()
                                            .ephemeral1hInputTokens(0L)
                                            .ephemeral5mInputTokens(0L)
                                            .build()
                                    )
                                    .cacheCreationInputTokens(2051L)
                                    .cacheReadInputTokens(2051L)
                                    .fallbackCredit(
                                        BetaFallbackCreditUsage.builder()
                                            .status(BetaFallbackCreditRedeemed.builder().build())
                                            .build()
                                    )
                                    .inferenceGeo("global")
                                    .inputTokens(2095L)
                                    .addIteration(
                                        BetaMessageIterationUsage.builder()
                                            .cacheCreation(
                                                BetaCacheCreation.builder()
                                                    .ephemeral1hInputTokens(0L)
                                                    .ephemeral5mInputTokens(0L)
                                                    .build()
                                            )
                                            .cacheCreationInputTokens(0L)
                                            .cacheReadInputTokens(0L)
                                            .inputTokens(0L)
                                            .model(Model.CLAUDE_FABLE_5_2)
                                            .outputTokens(0L)
                                            .build()
                                    )
                                    .outputTokens(503L)
                                    .outputTokensDetails(BetaOutputTokensDetails.of(0L))
                                    .serverToolUse(
                                        BetaServerToolUsage.builder()
                                            .webFetchRequests(2L)
                                            .webSearchRequests(0L)
                                            .build()
                                    )
                                    .serviceTier(BetaUsage.ServiceTier.STANDARD)
                                    .speed(BetaUsage.Speed.STANDARD)
                                    .build()
                            )
                            .addInputTransformation(
                                BetaThinkingDroppedInputTransformation.builder()
                                    .path("path")
                                    .reason(
                                        BetaThinkingDroppedInputTransformation.Reason
                                            .MODEL_BINDING_MISMATCH
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaComplianceReviewMessageContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewMessageContent),
                jacksonTypeRef<BetaComplianceReviewMessageContent>(),
            )

        assertThat(roundtrippedBetaComplianceReviewMessageContent)
            .isEqualTo(betaComplianceReviewMessageContent)
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
        val betaComplianceReviewMessageContent =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaComplianceReviewMessageContent>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaComplianceReviewMessageContent.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaComplianceReviewMessageContent: BetaComplianceReviewMessageContent
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "createMessage" to betaComplianceReviewMessageContent.createMessage()
            )
            .filterValues { it.isPresent }
}
