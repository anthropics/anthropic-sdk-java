package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsUsageReportTimeBucketTest {

    @Test
    fun create() {
        val betaAnalyticsUsageReportTimeBucket =
            BetaAnalyticsUsageReportTimeBucket.builder()
                .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addResult(
                    BetaAnalyticsUsageBucketedResult.builder()
                        .cacheCreation(
                            BetaCacheCreation.builder()
                                .ephemeral1hInputTokens(0L)
                                .ephemeral5mInputTokens(0L)
                                .build()
                        )
                        .cacheReadInputTokens(0L)
                        .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                        .claudeTagUserId("U0123ABCDEF")
                        .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                        .inferenceGeo(BetaAnalyticsUsageBucketedResult.InferenceGeo.GLOBAL)
                        .model("claude-opus-5")
                        .outputTokens(0L)
                        .product("chat")
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .requests(0L)
                        .serverToolUse(BetaAnalyticsServerToolUse.of(10L))
                        .slackChannelId("C0123ABCDEF")
                        .speed(BetaAnalyticsUsageBucketedResult.Speed.FAST)
                        .uncachedInputTokens(0L)
                        .build()
                )
                .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaAnalyticsUsageReportTimeBucket.endingAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaAnalyticsUsageReportTimeBucket.results())
            .containsExactly(
                BetaAnalyticsUsageBucketedResult.builder()
                    .cacheCreation(
                        BetaCacheCreation.builder()
                            .ephemeral1hInputTokens(0L)
                            .ephemeral5mInputTokens(0L)
                            .build()
                    )
                    .cacheReadInputTokens(0L)
                    .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                    .claudeTagUserId("U0123ABCDEF")
                    .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                    .inferenceGeo(BetaAnalyticsUsageBucketedResult.InferenceGeo.GLOBAL)
                    .model("claude-opus-5")
                    .outputTokens(0L)
                    .product("chat")
                    .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                    .requests(0L)
                    .serverToolUse(BetaAnalyticsServerToolUse.of(10L))
                    .slackChannelId("C0123ABCDEF")
                    .speed(BetaAnalyticsUsageBucketedResult.Speed.FAST)
                    .uncachedInputTokens(0L)
                    .build()
            )
        assertThat(betaAnalyticsUsageReportTimeBucket.startingAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsUsageReportTimeBucket =
            BetaAnalyticsUsageReportTimeBucket.builder()
                .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addResult(
                    BetaAnalyticsUsageBucketedResult.builder()
                        .cacheCreation(
                            BetaCacheCreation.builder()
                                .ephemeral1hInputTokens(0L)
                                .ephemeral5mInputTokens(0L)
                                .build()
                        )
                        .cacheReadInputTokens(0L)
                        .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                        .claudeTagUserId("U0123ABCDEF")
                        .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                        .inferenceGeo(BetaAnalyticsUsageBucketedResult.InferenceGeo.GLOBAL)
                        .model("claude-opus-5")
                        .outputTokens(0L)
                        .product("chat")
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .requests(0L)
                        .serverToolUse(BetaAnalyticsServerToolUse.of(10L))
                        .slackChannelId("C0123ABCDEF")
                        .speed(BetaAnalyticsUsageBucketedResult.Speed.FAST)
                        .uncachedInputTokens(0L)
                        .build()
                )
                .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaAnalyticsUsageReportTimeBucket =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsUsageReportTimeBucket),
                jacksonTypeRef<BetaAnalyticsUsageReportTimeBucket>(),
            )

        assertThat(roundtrippedBetaAnalyticsUsageReportTimeBucket)
            .isEqualTo(betaAnalyticsUsageReportTimeBucket)
    }
}
