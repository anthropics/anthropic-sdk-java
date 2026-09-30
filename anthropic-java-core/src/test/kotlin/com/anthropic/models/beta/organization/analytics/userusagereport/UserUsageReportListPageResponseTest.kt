package com.anthropic.models.beta.organization.analytics.userusagereport

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServerToolUse
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageUsersItem
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUserActor
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserUsageReportListPageResponseTest {

    @Test
    fun create() {
        val userUsageReportListPageResponse =
            UserUsageReportListPageResponse.builder()
                .addData(
                    BetaAnalyticsUsageUsersItem.builder()
                        .actor(
                            BetaAnalyticsUserActor.builder()
                                .deleted(true)
                                .emailAddress("jane@example.com")
                                .name("Jane Smith")
                                .userId("user_01AbCdEfGhIjKlMnOpQrSt")
                                .build()
                        )
                        .cacheCreation(
                            BetaCacheCreation.builder()
                                .ephemeral1hInputTokens(0L)
                                .ephemeral5mInputTokens(0L)
                                .build()
                        )
                        .cacheReadInputTokens(3200000L)
                        .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                        .claudeTagUserId("U0123ABCDEF")
                        .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                        .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .inferenceGeo(BetaAnalyticsUsageUsersItem.InferenceGeo.GLOBAL)
                        .model("claude-opus-5")
                        .outputTokens(891000L)
                        .product("chat")
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .requests(128L)
                        .serverToolUse(BetaAnalyticsServerToolUse.of(10L))
                        .slackChannelId("C0123ABCDEF")
                        .speed(BetaAnalyticsUsageUsersItem.Speed.FAST)
                        .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .totalTokens(5377000L)
                        .uncachedInputTokens(1284500L)
                        .build()
                )
                .dataRefreshedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .hasMore(true)
                .nextPage("next_page")
                .organizationId("org_013FP9SaFPBg7Kw7fetjn6cF")
                .build()

        assertThat(userUsageReportListPageResponse.data())
            .containsExactly(
                BetaAnalyticsUsageUsersItem.builder()
                    .actor(
                        BetaAnalyticsUserActor.builder()
                            .deleted(true)
                            .emailAddress("jane@example.com")
                            .name("Jane Smith")
                            .userId("user_01AbCdEfGhIjKlMnOpQrSt")
                            .build()
                    )
                    .cacheCreation(
                        BetaCacheCreation.builder()
                            .ephemeral1hInputTokens(0L)
                            .ephemeral5mInputTokens(0L)
                            .build()
                    )
                    .cacheReadInputTokens(3200000L)
                    .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                    .claudeTagUserId("U0123ABCDEF")
                    .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                    .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .inferenceGeo(BetaAnalyticsUsageUsersItem.InferenceGeo.GLOBAL)
                    .model("claude-opus-5")
                    .outputTokens(891000L)
                    .product("chat")
                    .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                    .requests(128L)
                    .serverToolUse(BetaAnalyticsServerToolUse.of(10L))
                    .slackChannelId("C0123ABCDEF")
                    .speed(BetaAnalyticsUsageUsersItem.Speed.FAST)
                    .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .totalTokens(5377000L)
                    .uncachedInputTokens(1284500L)
                    .build()
            )
        assertThat(userUsageReportListPageResponse.dataRefreshedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(userUsageReportListPageResponse.hasMore()).isEqualTo(true)
        assertThat(userUsageReportListPageResponse.nextPage()).contains("next_page")
        assertThat(userUsageReportListPageResponse.organizationId())
            .isEqualTo("org_013FP9SaFPBg7Kw7fetjn6cF")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userUsageReportListPageResponse =
            UserUsageReportListPageResponse.builder()
                .addData(
                    BetaAnalyticsUsageUsersItem.builder()
                        .actor(
                            BetaAnalyticsUserActor.builder()
                                .deleted(true)
                                .emailAddress("jane@example.com")
                                .name("Jane Smith")
                                .userId("user_01AbCdEfGhIjKlMnOpQrSt")
                                .build()
                        )
                        .cacheCreation(
                            BetaCacheCreation.builder()
                                .ephemeral1hInputTokens(0L)
                                .ephemeral5mInputTokens(0L)
                                .build()
                        )
                        .cacheReadInputTokens(3200000L)
                        .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                        .claudeTagUserId("U0123ABCDEF")
                        .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                        .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .inferenceGeo(BetaAnalyticsUsageUsersItem.InferenceGeo.GLOBAL)
                        .model("claude-opus-5")
                        .outputTokens(891000L)
                        .product("chat")
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .requests(128L)
                        .serverToolUse(BetaAnalyticsServerToolUse.of(10L))
                        .slackChannelId("C0123ABCDEF")
                        .speed(BetaAnalyticsUsageUsersItem.Speed.FAST)
                        .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .totalTokens(5377000L)
                        .uncachedInputTokens(1284500L)
                        .build()
                )
                .dataRefreshedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .hasMore(true)
                .nextPage("next_page")
                .organizationId("org_013FP9SaFPBg7Kw7fetjn6cF")
                .build()

        val roundtrippedUserUsageReportListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userUsageReportListPageResponse),
                jacksonTypeRef<UserUsageReportListPageResponse>(),
            )

        assertThat(roundtrippedUserUsageReportListPageResponse)
            .isEqualTo(userUsageReportListPageResponse)
    }
}
