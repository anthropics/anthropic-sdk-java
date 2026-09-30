package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsUsageUsersItemTest {

    @Test
    fun create() {
        val betaAnalyticsUsageUsersItem =
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

        assertThat(betaAnalyticsUsageUsersItem.actor())
            .isEqualTo(
                BetaAnalyticsUserActor.builder()
                    .deleted(true)
                    .emailAddress("jane@example.com")
                    .name("Jane Smith")
                    .userId("user_01AbCdEfGhIjKlMnOpQrSt")
                    .build()
            )
        assertThat(betaAnalyticsUsageUsersItem.cacheCreation())
            .isEqualTo(
                BetaCacheCreation.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(betaAnalyticsUsageUsersItem.cacheReadInputTokens()).isEqualTo(3200000L)
        assertThat(betaAnalyticsUsageUsersItem.claudeTagCategory())
            .contains(BetaAnalyticsClaudeTagCategory.DM)
        assertThat(betaAnalyticsUsageUsersItem.claudeTagUserId()).contains("U0123ABCDEF")
        assertThat(betaAnalyticsUsageUsersItem.contextWindow())
            .contains(BetaAnalyticsContextWindow.FROM_0_TO_200K)
        assertThat(betaAnalyticsUsageUsersItem.endingAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaAnalyticsUsageUsersItem.inferenceGeo())
            .contains(BetaAnalyticsUsageUsersItem.InferenceGeo.GLOBAL)
        assertThat(betaAnalyticsUsageUsersItem.model()).contains("claude-opus-5")
        assertThat(betaAnalyticsUsageUsersItem.outputTokens()).isEqualTo(891000L)
        assertThat(betaAnalyticsUsageUsersItem.product()).contains("chat")
        assertThat(betaAnalyticsUsageUsersItem.rbacGroupId())
            .contains("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaAnalyticsUsageUsersItem.requests()).contains(128L)
        assertThat(betaAnalyticsUsageUsersItem.serverToolUse())
            .isEqualTo(BetaAnalyticsServerToolUse.of(10L))
        assertThat(betaAnalyticsUsageUsersItem.slackChannelId()).contains("C0123ABCDEF")
        assertThat(betaAnalyticsUsageUsersItem.speed())
            .contains(BetaAnalyticsUsageUsersItem.Speed.FAST)
        assertThat(betaAnalyticsUsageUsersItem.startingAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaAnalyticsUsageUsersItem.totalTokens()).isEqualTo(5377000L)
        assertThat(betaAnalyticsUsageUsersItem.uncachedInputTokens()).isEqualTo(1284500L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsUsageUsersItem =
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

        val roundtrippedBetaAnalyticsUsageUsersItem =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsUsageUsersItem),
                jacksonTypeRef<BetaAnalyticsUsageUsersItem>(),
            )

        assertThat(roundtrippedBetaAnalyticsUsageUsersItem).isEqualTo(betaAnalyticsUsageUsersItem)
    }
}
