package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsCostBucketedResultTest {

    @Test
    fun create() {
        val betaAnalyticsCostBucketedResult =
            BetaAnalyticsCostBucketedResult.builder()
                .amount("amount")
                .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                .claudeTagUserId("U0123ABCDEF")
                .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                .costType(BetaAnalyticsCostType.CODE_EXECUTION)
                .currency("USD")
                .inferenceGeo(BetaAnalyticsCostBucketedResult.InferenceGeo.GLOBAL)
                .listAmount("list_amount")
                .model("claude-opus-5")
                .product("chat")
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .requests(0L)
                .slackChannelId("C0123ABCDEF")
                .speed(BetaAnalyticsCostBucketedResult.Speed.FAST)
                .tokenType(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
                .build()

        assertThat(betaAnalyticsCostBucketedResult.amount()).isEqualTo("amount")
        assertThat(betaAnalyticsCostBucketedResult.claudeTagCategory())
            .contains(BetaAnalyticsClaudeTagCategory.DM)
        assertThat(betaAnalyticsCostBucketedResult.claudeTagUserId()).contains("U0123ABCDEF")
        assertThat(betaAnalyticsCostBucketedResult.contextWindow())
            .contains(BetaAnalyticsContextWindow.FROM_0_TO_200K)
        assertThat(betaAnalyticsCostBucketedResult.costType())
            .contains(BetaAnalyticsCostType.CODE_EXECUTION)
        assertThat(betaAnalyticsCostBucketedResult.currency()).isEqualTo("USD")
        assertThat(betaAnalyticsCostBucketedResult.inferenceGeo())
            .contains(BetaAnalyticsCostBucketedResult.InferenceGeo.GLOBAL)
        assertThat(betaAnalyticsCostBucketedResult.listAmount()).isEqualTo("list_amount")
        assertThat(betaAnalyticsCostBucketedResult.model()).contains("claude-opus-5")
        assertThat(betaAnalyticsCostBucketedResult.product()).contains("chat")
        assertThat(betaAnalyticsCostBucketedResult.rbacGroupId())
            .contains("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaAnalyticsCostBucketedResult.requests()).contains(0L)
        assertThat(betaAnalyticsCostBucketedResult.slackChannelId()).contains("C0123ABCDEF")
        assertThat(betaAnalyticsCostBucketedResult.speed())
            .contains(BetaAnalyticsCostBucketedResult.Speed.FAST)
        assertThat(betaAnalyticsCostBucketedResult.tokenType())
            .contains(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsCostBucketedResult =
            BetaAnalyticsCostBucketedResult.builder()
                .amount("amount")
                .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                .claudeTagUserId("U0123ABCDEF")
                .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                .costType(BetaAnalyticsCostType.CODE_EXECUTION)
                .currency("USD")
                .inferenceGeo(BetaAnalyticsCostBucketedResult.InferenceGeo.GLOBAL)
                .listAmount("list_amount")
                .model("claude-opus-5")
                .product("chat")
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .requests(0L)
                .slackChannelId("C0123ABCDEF")
                .speed(BetaAnalyticsCostBucketedResult.Speed.FAST)
                .tokenType(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
                .build()

        val roundtrippedBetaAnalyticsCostBucketedResult =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsCostBucketedResult),
                jacksonTypeRef<BetaAnalyticsCostBucketedResult>(),
            )

        assertThat(roundtrippedBetaAnalyticsCostBucketedResult)
            .isEqualTo(betaAnalyticsCostBucketedResult)
    }
}
