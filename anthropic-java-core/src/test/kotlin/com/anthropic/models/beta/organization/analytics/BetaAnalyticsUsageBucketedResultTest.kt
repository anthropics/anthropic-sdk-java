package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsUsageBucketedResultTest {

    @Test
    fun create() {
        val betaAnalyticsUsageBucketedResult =
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

        assertThat(betaAnalyticsUsageBucketedResult.cacheCreation())
            .isEqualTo(
                BetaCacheCreation.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(betaAnalyticsUsageBucketedResult.cacheReadInputTokens()).isEqualTo(0L)
        assertThat(betaAnalyticsUsageBucketedResult.claudeTagCategory())
            .contains(BetaAnalyticsClaudeTagCategory.DM)
        assertThat(betaAnalyticsUsageBucketedResult.claudeTagUserId()).contains("U0123ABCDEF")
        assertThat(betaAnalyticsUsageBucketedResult.contextWindow())
            .contains(BetaAnalyticsContextWindow.FROM_0_TO_200K)
        assertThat(betaAnalyticsUsageBucketedResult.inferenceGeo())
            .contains(BetaAnalyticsUsageBucketedResult.InferenceGeo.GLOBAL)
        assertThat(betaAnalyticsUsageBucketedResult.model()).contains("claude-opus-5")
        assertThat(betaAnalyticsUsageBucketedResult.outputTokens()).isEqualTo(0L)
        assertThat(betaAnalyticsUsageBucketedResult.product()).contains("chat")
        assertThat(betaAnalyticsUsageBucketedResult.rbacGroupId())
            .contains("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaAnalyticsUsageBucketedResult.requests()).contains(0L)
        assertThat(betaAnalyticsUsageBucketedResult.serverToolUse())
            .isEqualTo(BetaAnalyticsServerToolUse.of(10L))
        assertThat(betaAnalyticsUsageBucketedResult.slackChannelId()).contains("C0123ABCDEF")
        assertThat(betaAnalyticsUsageBucketedResult.speed())
            .contains(BetaAnalyticsUsageBucketedResult.Speed.FAST)
        assertThat(betaAnalyticsUsageBucketedResult.uncachedInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsUsageBucketedResult =
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

        val roundtrippedBetaAnalyticsUsageBucketedResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsUsageBucketedResult),
                jacksonTypeRef<BetaAnalyticsUsageBucketedResult>(),
            )

        assertThat(roundtrippedBetaAnalyticsUsageBucketedResult)
            .isEqualTo(betaAnalyticsUsageBucketedResult)
    }
}
