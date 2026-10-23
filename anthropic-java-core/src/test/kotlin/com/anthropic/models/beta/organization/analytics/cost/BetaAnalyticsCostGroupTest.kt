package com.anthropic.models.beta.organization.analytics.cost

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsActor
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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsCostGroupTest {

    @Test
    fun create() {
        val betaAnalyticsCostGroup =
            BetaAnalyticsCostGroup.builder()
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
                .costType(BetaAnalyticsGroupedCostType.TOKENS)
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
                .tokenType(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
                .userProfileId("uprof_01M33NeYUQK2WV9PpYcsPHLi")
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .build()

        assertThat(betaAnalyticsCostGroup.actor())
            .contains(
                BetaAnalyticsActor.ofUser(
                    BetaAnalyticsReportUserActor.builder()
                        .deleted(false)
                        .emailAddress("user@emaildomain.com")
                        .name("Jane Doe")
                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .build()
                )
            )
        assertThat(betaAnalyticsCostGroup.actorType()).contains(BetaAnalyticsActorType.USER_ACTOR)
        assertThat(betaAnalyticsCostGroup.agentId()).contains("agent_01VHd3EAGJ6PLkP9LM7DDu3f")
        assertThat(betaAnalyticsCostGroup.apiKeyId()).contains("apikey_01Rj2N8SVvo6BePZj99NhmiT")
        assertThat(betaAnalyticsCostGroup.claudeTagCategory())
            .contains(BetaAnalyticsClaudeTagCategory.DM)
        assertThat(betaAnalyticsCostGroup.claudeTagUserId()).contains("U0123ABCDEF")
        assertThat(betaAnalyticsCostGroup.contextWindow())
            .contains(BetaAnalyticsContextWindow.FROM_0_TO_200K)
        assertThat(betaAnalyticsCostGroup.costType()).contains(BetaAnalyticsGroupedCostType.TOKENS)
        assertThat(betaAnalyticsCostGroup.deploymentId()).contains("depl_01JFC6ihzZHtAuyHsaENdJhY")
        assertThat(betaAnalyticsCostGroup.environmentId()).contains("env_017oTjuGedHtjhJauVC5xGJD")
        assertThat(betaAnalyticsCostGroup.inferenceGeo())
            .contains(BetaAnalyticsUsageCostInferenceGeo.GLOBAL)
        assertThat(betaAnalyticsCostGroup.model()).contains("claude-opus-5-5")
        assertThat(betaAnalyticsCostGroup.product()).contains("chat")
        assertThat(betaAnalyticsCostGroup.rbacGroupId())
            .contains("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaAnalyticsCostGroup.serviceTier()).contains(BetaAnalyticsServiceTier.STANDARD)
        assertThat(betaAnalyticsCostGroup.sessionId()).contains("sesn_0187kPDyNCwibNNvfaPEApCJ")
        assertThat(betaAnalyticsCostGroup.slackChannelId()).contains("C0123ABCDEF")
        assertThat(betaAnalyticsCostGroup.speed()).contains(BetaAnalyticsSpeed.FAST)
        assertThat(betaAnalyticsCostGroup.tokenType())
            .contains(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
        assertThat(betaAnalyticsCostGroup.userProfileId())
            .contains("uprof_01M33NeYUQK2WV9PpYcsPHLi")
        assertThat(betaAnalyticsCostGroup.workspaceId()).contains("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsCostGroup =
            BetaAnalyticsCostGroup.builder()
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
                .costType(BetaAnalyticsGroupedCostType.TOKENS)
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
                .tokenType(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
                .userProfileId("uprof_01M33NeYUQK2WV9PpYcsPHLi")
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .build()

        val roundtrippedBetaAnalyticsCostGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsCostGroup),
                jacksonTypeRef<BetaAnalyticsCostGroup>(),
            )

        assertThat(roundtrippedBetaAnalyticsCostGroup).isEqualTo(betaAnalyticsCostGroup)
    }
}
