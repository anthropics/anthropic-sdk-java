package com.anthropic.models.beta.organization.analytics.costreport

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsCostBucketedResult
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsCostReportTimeBucket
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsCostType
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsTokenType
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CostReportListPageResponseTest {

    @Test
    fun create() {
        val costReportListPageResponse =
            CostReportListPageResponse.builder()
                .addData(
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
                                .tokenType(
                                    BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS
                                )
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

        assertThat(costReportListPageResponse.data())
            .containsExactly(
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
                            .tokenType(
                                BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS
                            )
                            .build()
                    )
                    .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(costReportListPageResponse.dataRefreshedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(costReportListPageResponse.hasMore()).isEqualTo(true)
        assertThat(costReportListPageResponse.nextPage()).contains("next_page")
        assertThat(costReportListPageResponse.organizationId())
            .isEqualTo("org_013FP9SaFPBg7Kw7fetjn6cF")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val costReportListPageResponse =
            CostReportListPageResponse.builder()
                .addData(
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
                                .tokenType(
                                    BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS
                                )
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

        val roundtrippedCostReportListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(costReportListPageResponse),
                jacksonTypeRef<CostReportListPageResponse>(),
            )

        assertThat(roundtrippedCostReportListPageResponse).isEqualTo(costReportListPageResponse)
    }
}
