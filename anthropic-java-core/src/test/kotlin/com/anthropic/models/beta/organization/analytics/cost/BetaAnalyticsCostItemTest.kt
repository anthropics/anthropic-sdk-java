package com.anthropic.models.beta.organization.analytics.cost

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsActorType
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsGroupedCostType
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsReportUserActor
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServiceTier
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSpeed
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsTokenType
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageCostInferenceGeo
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsCostItemTest {

    @Test
    fun create() {
        val betaAnalyticsCostItem =
            BetaAnalyticsCostItem.builder()
                .cost(
                    BetaAnalyticsCostMetrics.builder()
                        .amount("41280.000000")
                        .currency("USD")
                        .listAmount("51600.000000")
                        .requests(128L)
                        .build()
                )
                .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .group(
                    BetaAnalyticsCostGroup.builder()
                        .actor(
                            BetaAnalyticsReportUserActor.builder()
                                .deleted(true)
                                .emailAddress("user@emaildomain.com")
                                .name("Jane Doe")
                                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .build()
                        )
                        .actorType(BetaAnalyticsActorType.API_ACTOR)
                        .agentId("agent_01VHd3EAGJ6PLkP9LM7DDu3f")
                        .apiKeyId("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                        .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                        .claudeTagUserId("U0123ABCDEF")
                        .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                        .costType(BetaAnalyticsGroupedCostType.CODE_EXECUTION)
                        .deploymentId("depl_01JFC6ihzZHtAuyHsaENdJhY")
                        .environmentId("env_017oTjuGedHtjhJauVC5xGJD")
                        .inferenceGeo(BetaAnalyticsUsageCostInferenceGeo.GLOBAL)
                        .model("claude-opus-5-5")
                        .product("chat")
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .serviceTier(BetaAnalyticsServiceTier.BATCH)
                        .sessionId("sesn_0187kPDyNCwibNNvfaPEApCJ")
                        .slackChannelId("C0123ABCDEF")
                        .speed(BetaAnalyticsSpeed.FAST)
                        .tokenType(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
                        .userProfileId("uprof_01M33NeYUQK2WV9PpYcsPHLi")
                        .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                        .build()
                )
                .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaAnalyticsCostItem.cost())
            .isEqualTo(
                BetaAnalyticsCostMetrics.builder()
                    .amount("41280.000000")
                    .currency("USD")
                    .listAmount("51600.000000")
                    .requests(128L)
                    .build()
            )
        assertThat(betaAnalyticsCostItem.endingAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaAnalyticsCostItem.group())
            .isEqualTo(
                BetaAnalyticsCostGroup.builder()
                    .actor(
                        BetaAnalyticsReportUserActor.builder()
                            .deleted(true)
                            .emailAddress("user@emaildomain.com")
                            .name("Jane Doe")
                            .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                            .build()
                    )
                    .actorType(BetaAnalyticsActorType.API_ACTOR)
                    .agentId("agent_01VHd3EAGJ6PLkP9LM7DDu3f")
                    .apiKeyId("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                    .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                    .claudeTagUserId("U0123ABCDEF")
                    .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                    .costType(BetaAnalyticsGroupedCostType.CODE_EXECUTION)
                    .deploymentId("depl_01JFC6ihzZHtAuyHsaENdJhY")
                    .environmentId("env_017oTjuGedHtjhJauVC5xGJD")
                    .inferenceGeo(BetaAnalyticsUsageCostInferenceGeo.GLOBAL)
                    .model("claude-opus-5-5")
                    .product("chat")
                    .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                    .serviceTier(BetaAnalyticsServiceTier.BATCH)
                    .sessionId("sesn_0187kPDyNCwibNNvfaPEApCJ")
                    .slackChannelId("C0123ABCDEF")
                    .speed(BetaAnalyticsSpeed.FAST)
                    .tokenType(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
                    .userProfileId("uprof_01M33NeYUQK2WV9PpYcsPHLi")
                    .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                    .build()
            )
        assertThat(betaAnalyticsCostItem.startingAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsCostItem =
            BetaAnalyticsCostItem.builder()
                .cost(
                    BetaAnalyticsCostMetrics.builder()
                        .amount("41280.000000")
                        .currency("USD")
                        .listAmount("51600.000000")
                        .requests(128L)
                        .build()
                )
                .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .group(
                    BetaAnalyticsCostGroup.builder()
                        .actor(
                            BetaAnalyticsReportUserActor.builder()
                                .deleted(true)
                                .emailAddress("user@emaildomain.com")
                                .name("Jane Doe")
                                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .build()
                        )
                        .actorType(BetaAnalyticsActorType.API_ACTOR)
                        .agentId("agent_01VHd3EAGJ6PLkP9LM7DDu3f")
                        .apiKeyId("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                        .claudeTagCategory(BetaAnalyticsClaudeTagCategory.DM)
                        .claudeTagUserId("U0123ABCDEF")
                        .contextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                        .costType(BetaAnalyticsGroupedCostType.CODE_EXECUTION)
                        .deploymentId("depl_01JFC6ihzZHtAuyHsaENdJhY")
                        .environmentId("env_017oTjuGedHtjhJauVC5xGJD")
                        .inferenceGeo(BetaAnalyticsUsageCostInferenceGeo.GLOBAL)
                        .model("claude-opus-5-5")
                        .product("chat")
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .serviceTier(BetaAnalyticsServiceTier.BATCH)
                        .sessionId("sesn_0187kPDyNCwibNNvfaPEApCJ")
                        .slackChannelId("C0123ABCDEF")
                        .speed(BetaAnalyticsSpeed.FAST)
                        .tokenType(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
                        .userProfileId("uprof_01M33NeYUQK2WV9PpYcsPHLi")
                        .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                        .build()
                )
                .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaAnalyticsCostItem =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsCostItem),
                jacksonTypeRef<BetaAnalyticsCostItem>(),
            )

        assertThat(roundtrippedBetaAnalyticsCostItem).isEqualTo(betaAnalyticsCostItem)
    }
}
