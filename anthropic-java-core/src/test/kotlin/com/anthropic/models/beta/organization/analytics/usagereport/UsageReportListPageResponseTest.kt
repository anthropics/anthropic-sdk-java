package com.anthropic.models.beta.organization.analytics.usagereport

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServerToolUse
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageBucketedResult
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageReportTimeBucket
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UsageReportListPageResponseTest {

    @Test
    fun create() {
        val usageReportListPageResponse =
            UsageReportListPageResponse.builder()
                .addData(
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
                )
                .dataRefreshedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .hasMore(true)
                .nextPage("next_page")
                .organizationId("org_013FP9SaFPBg7Kw7fetjn6cF")
                .build()

        assertThat(usageReportListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(usageReportListPageResponse.dataRefreshedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(usageReportListPageResponse.hasMore()).isEqualTo(true)
        assertThat(usageReportListPageResponse.nextPage()).contains("next_page")
        assertThat(usageReportListPageResponse.organizationId())
            .isEqualTo("org_013FP9SaFPBg7Kw7fetjn6cF")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val usageReportListPageResponse =
            UsageReportListPageResponse.builder()
                .addData(
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
                )
                .dataRefreshedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .hasMore(true)
                .nextPage("next_page")
                .organizationId("org_013FP9SaFPBg7Kw7fetjn6cF")
                .build()

        val roundtrippedUsageReportListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(usageReportListPageResponse),
                jacksonTypeRef<UsageReportListPageResponse>(),
            )

        assertThat(roundtrippedUsageReportListPageResponse).isEqualTo(usageReportListPageResponse)
    }
}
