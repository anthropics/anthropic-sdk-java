package com.anthropic.models.beta.organization.analytics.usercostreport

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsCostType
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsCostUsersItem
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsTokenType
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUserActor
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserCostReportListPageResponseTest {

    @Test
    fun create() {
        val userCostReportListPageResponse =
            UserCostReportListPageResponse.builder()
                .addData(
                    BetaAnalyticsCostUsersItem.builder()
                        .actor(
                            BetaAnalyticsUserActor.builder()
                                .deleted(true)
                                .emailAddress("jane@example.com")
                                .name("Jane Smith")
                                .userId("user_01AbCdEfGhIjKlMnOpQrSt")
                                .build()
                        )
                        .amount("41280.000000")
                        .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                        .claudeTagUserId("U0123ABCDEF")
                        .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                        .costType(BetaAnalyticsCostType.CODE_EXECUTION)
                        .currency("USD")
                        .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .inferenceGeo(BetaAnalyticsCostUsersItem.InferenceGeo.GLOBAL)
                        .listAmount("51600.000000")
                        .model("claude-opus-5")
                        .product("chat")
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .requests(128L)
                        .slackChannelId("C0123ABCDEF")
                        .speed(BetaAnalyticsCostUsersItem.Speed.FAST)
                        .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .tokenType(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
                        .build()
                )
                .dataRefreshedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .hasMore(true)
                .nextPage("next_page")
                .organizationId("org_013FP9SaFPBg7Kw7fetjn6cF")
                .build()

        assertThat(userCostReportListPageResponse.data())
            .containsExactly(
                BetaAnalyticsCostUsersItem.builder()
                    .actor(
                        BetaAnalyticsUserActor.builder()
                            .deleted(true)
                            .emailAddress("jane@example.com")
                            .name("Jane Smith")
                            .userId("user_01AbCdEfGhIjKlMnOpQrSt")
                            .build()
                    )
                    .amount("41280.000000")
                    .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                    .claudeTagUserId("U0123ABCDEF")
                    .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                    .costType(BetaAnalyticsCostType.CODE_EXECUTION)
                    .currency("USD")
                    .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .inferenceGeo(BetaAnalyticsCostUsersItem.InferenceGeo.GLOBAL)
                    .listAmount("51600.000000")
                    .model("claude-opus-5")
                    .product("chat")
                    .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                    .requests(128L)
                    .slackChannelId("C0123ABCDEF")
                    .speed(BetaAnalyticsCostUsersItem.Speed.FAST)
                    .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .tokenType(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
                    .build()
            )
        assertThat(userCostReportListPageResponse.dataRefreshedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(userCostReportListPageResponse.hasMore()).isEqualTo(true)
        assertThat(userCostReportListPageResponse.nextPage()).contains("next_page")
        assertThat(userCostReportListPageResponse.organizationId())
            .isEqualTo("org_013FP9SaFPBg7Kw7fetjn6cF")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userCostReportListPageResponse =
            UserCostReportListPageResponse.builder()
                .addData(
                    BetaAnalyticsCostUsersItem.builder()
                        .actor(
                            BetaAnalyticsUserActor.builder()
                                .deleted(true)
                                .emailAddress("jane@example.com")
                                .name("Jane Smith")
                                .userId("user_01AbCdEfGhIjKlMnOpQrSt")
                                .build()
                        )
                        .amount("41280.000000")
                        .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                        .claudeTagUserId("U0123ABCDEF")
                        .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                        .costType(BetaAnalyticsCostType.CODE_EXECUTION)
                        .currency("USD")
                        .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .inferenceGeo(BetaAnalyticsCostUsersItem.InferenceGeo.GLOBAL)
                        .listAmount("51600.000000")
                        .model("claude-opus-5")
                        .product("chat")
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .requests(128L)
                        .slackChannelId("C0123ABCDEF")
                        .speed(BetaAnalyticsCostUsersItem.Speed.FAST)
                        .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .tokenType(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
                        .build()
                )
                .dataRefreshedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .hasMore(true)
                .nextPage("next_page")
                .organizationId("org_013FP9SaFPBg7Kw7fetjn6cF")
                .build()

        val roundtrippedUserCostReportListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userCostReportListPageResponse),
                jacksonTypeRef<UserCostReportListPageResponse>(),
            )

        assertThat(roundtrippedUserCostReportListPageResponse)
            .isEqualTo(userCostReportListPageResponse)
    }
}
