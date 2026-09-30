package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsCostReportTimeBucketTest {

    @Test
    fun create() {
        val betaAnalyticsCostReportTimeBucket =
            BetaAnalyticsCostReportTimeBucket.builder()
                .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addResult(
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
                )
                .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaAnalyticsCostReportTimeBucket.endingAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaAnalyticsCostReportTimeBucket.results())
            .containsExactly(
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
            )
        assertThat(betaAnalyticsCostReportTimeBucket.startingAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsCostReportTimeBucket =
            BetaAnalyticsCostReportTimeBucket.builder()
                .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addResult(
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
                )
                .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaAnalyticsCostReportTimeBucket =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsCostReportTimeBucket),
                jacksonTypeRef<BetaAnalyticsCostReportTimeBucket>(),
            )

        assertThat(roundtrippedBetaAnalyticsCostReportTimeBucket)
            .isEqualTo(betaAnalyticsCostReportTimeBucket)
    }
}
