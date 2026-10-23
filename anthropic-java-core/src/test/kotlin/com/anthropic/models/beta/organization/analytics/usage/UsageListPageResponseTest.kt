package com.anthropic.models.beta.organization.analytics.usage

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsActorType
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsReportUserActor
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServerToolUse
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServiceTier
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSpeed
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageCostInferenceGeo
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UsageListPageResponseTest {

    @Test
    fun create() {
        val usageListPageResponse =
            UsageListPageResponse.builder()
                .addData(
                    BetaAnalyticsUsageItem.builder()
                        .endingAt(OffsetDateTime.parse("2026-10-01T00:00:00Z"))
                        .group(
                            BetaAnalyticsUsageGroup.builder()
                                .actor(
                                    BetaAnalyticsReportUserActor.builder()
                                        .deleted(false)
                                        .emailAddress("user@emaildomain.com")
                                        .name("Jane Doe")
                                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                        .build()
                                )
                                .actorType(BetaAnalyticsActorType.USER_ACTOR)
                                .agentId("agent_01VHd3EAGJ6PLkP9LM7DDu3f")
                                .apiKeyId("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                                .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                                .claudeTagUserId("U0123ABCDEF")
                                .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                                .deploymentId("depl_01JFC6ihzZHtAuyHsaENdJhY")
                                .environmentId("env_017oTjuGedHtjhJauVC5xGJD")
                                .inferenceGeo(BetaAnalyticsUsageCostInferenceGeo.GLOBAL)
                                .model("claude-opus-5-5")
                                .product("chat")
                                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                                .serviceTier(BetaAnalyticsServiceTier.STANDARD)
                                .sessionId("sesn_0187kPDyNCwibNNvfaPEApCJ")
                                .slackChannelId("C0123ABCDEF")
                                .speed(BetaAnalyticsSpeed.FAST)
                                .userProfileId("uprof_01M33NeYUQK2WV9PpYcsPHLi")
                                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                                .build()
                        )
                        .startingAt(OffsetDateTime.parse("2026-09-01T00:00:00Z"))
                        .usage(
                            BetaAnalyticsUsageMetrics.builder()
                                .cacheCreation(
                                    BetaCacheCreation.builder()
                                        .ephemeral1hInputTokens(0L)
                                        .ephemeral5mInputTokens(0L)
                                        .build()
                                )
                                .cacheReadInputTokens(3200000L)
                                .outputTokens(891000L)
                                .requests(128L)
                                .serverToolUse(BetaAnalyticsServerToolUse.of(10L))
                                .totalTokens(5377000L)
                                .uncachedInputTokens(1284500L)
                                .build()
                        )
                        .build()
                )
                .dataRefreshedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .hasMore(true)
                .nextPage("next_page")
                .build()

        assertThat(usageListPageResponse.data())
            .containsExactly(
                BetaAnalyticsUsageItem.builder()
                    .endingAt(OffsetDateTime.parse("2026-10-01T00:00:00Z"))
                    .group(
                        BetaAnalyticsUsageGroup.builder()
                            .actor(
                                BetaAnalyticsReportUserActor.builder()
                                    .deleted(false)
                                    .emailAddress("user@emaildomain.com")
                                    .name("Jane Doe")
                                    .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                    .build()
                            )
                            .actorType(BetaAnalyticsActorType.USER_ACTOR)
                            .agentId("agent_01VHd3EAGJ6PLkP9LM7DDu3f")
                            .apiKeyId("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                            .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                            .claudeTagUserId("U0123ABCDEF")
                            .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                            .deploymentId("depl_01JFC6ihzZHtAuyHsaENdJhY")
                            .environmentId("env_017oTjuGedHtjhJauVC5xGJD")
                            .inferenceGeo(BetaAnalyticsUsageCostInferenceGeo.GLOBAL)
                            .model("claude-opus-5-5")
                            .product("chat")
                            .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                            .serviceTier(BetaAnalyticsServiceTier.STANDARD)
                            .sessionId("sesn_0187kPDyNCwibNNvfaPEApCJ")
                            .slackChannelId("C0123ABCDEF")
                            .speed(BetaAnalyticsSpeed.FAST)
                            .userProfileId("uprof_01M33NeYUQK2WV9PpYcsPHLi")
                            .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                            .build()
                    )
                    .startingAt(OffsetDateTime.parse("2026-09-01T00:00:00Z"))
                    .usage(
                        BetaAnalyticsUsageMetrics.builder()
                            .cacheCreation(
                                BetaCacheCreation.builder()
                                    .ephemeral1hInputTokens(0L)
                                    .ephemeral5mInputTokens(0L)
                                    .build()
                            )
                            .cacheReadInputTokens(3200000L)
                            .outputTokens(891000L)
                            .requests(128L)
                            .serverToolUse(BetaAnalyticsServerToolUse.of(10L))
                            .totalTokens(5377000L)
                            .uncachedInputTokens(1284500L)
                            .build()
                    )
                    .build()
            )
        assertThat(usageListPageResponse.dataRefreshedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(usageListPageResponse.hasMore()).isEqualTo(true)
        assertThat(usageListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val usageListPageResponse =
            UsageListPageResponse.builder()
                .addData(
                    BetaAnalyticsUsageItem.builder()
                        .endingAt(OffsetDateTime.parse("2026-10-01T00:00:00Z"))
                        .group(
                            BetaAnalyticsUsageGroup.builder()
                                .actor(
                                    BetaAnalyticsReportUserActor.builder()
                                        .deleted(false)
                                        .emailAddress("user@emaildomain.com")
                                        .name("Jane Doe")
                                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                        .build()
                                )
                                .actorType(BetaAnalyticsActorType.USER_ACTOR)
                                .agentId("agent_01VHd3EAGJ6PLkP9LM7DDu3f")
                                .apiKeyId("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                                .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                                .claudeTagUserId("U0123ABCDEF")
                                .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                                .deploymentId("depl_01JFC6ihzZHtAuyHsaENdJhY")
                                .environmentId("env_017oTjuGedHtjhJauVC5xGJD")
                                .inferenceGeo(BetaAnalyticsUsageCostInferenceGeo.GLOBAL)
                                .model("claude-opus-5-5")
                                .product("chat")
                                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                                .serviceTier(BetaAnalyticsServiceTier.STANDARD)
                                .sessionId("sesn_0187kPDyNCwibNNvfaPEApCJ")
                                .slackChannelId("C0123ABCDEF")
                                .speed(BetaAnalyticsSpeed.FAST)
                                .userProfileId("uprof_01M33NeYUQK2WV9PpYcsPHLi")
                                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                                .build()
                        )
                        .startingAt(OffsetDateTime.parse("2026-09-01T00:00:00Z"))
                        .usage(
                            BetaAnalyticsUsageMetrics.builder()
                                .cacheCreation(
                                    BetaCacheCreation.builder()
                                        .ephemeral1hInputTokens(0L)
                                        .ephemeral5mInputTokens(0L)
                                        .build()
                                )
                                .cacheReadInputTokens(3200000L)
                                .outputTokens(891000L)
                                .requests(128L)
                                .serverToolUse(BetaAnalyticsServerToolUse.of(10L))
                                .totalTokens(5377000L)
                                .uncachedInputTokens(1284500L)
                                .build()
                        )
                        .build()
                )
                .dataRefreshedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .hasMore(true)
                .nextPage("next_page")
                .build()

        val roundtrippedUsageListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(usageListPageResponse),
                jacksonTypeRef<UsageListPageResponse>(),
            )

        assertThat(roundtrippedUsageListPageResponse).isEqualTo(usageListPageResponse)
    }
}
