package com.anthropic.models.beta.organization.analytics.usage

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsActor
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsActorType
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsReportUserActor
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServiceTier
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSpeed
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageCostInferenceGeo
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsUsageGroupTest {

    @Test
    fun create() {
        val betaAnalyticsUsageGroup =
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

        assertThat(betaAnalyticsUsageGroup.actor())
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
        assertThat(betaAnalyticsUsageGroup.actorType()).contains(BetaAnalyticsActorType.USER_ACTOR)
        assertThat(betaAnalyticsUsageGroup.agentId()).contains("agent_01VHd3EAGJ6PLkP9LM7DDu3f")
        assertThat(betaAnalyticsUsageGroup.apiKeyId()).contains("apikey_01Rj2N8SVvo6BePZj99NhmiT")
        assertThat(betaAnalyticsUsageGroup.claudeTagCategory())
            .contains(BetaAnalyticsClaudeTagCategory.DM)
        assertThat(betaAnalyticsUsageGroup.claudeTagUserId()).contains("U0123ABCDEF")
        assertThat(betaAnalyticsUsageGroup.contextWindow())
            .contains(BetaAnalyticsContextWindow.FROM_0_TO_200K)
        assertThat(betaAnalyticsUsageGroup.deploymentId()).contains("depl_01JFC6ihzZHtAuyHsaENdJhY")
        assertThat(betaAnalyticsUsageGroup.environmentId()).contains("env_017oTjuGedHtjhJauVC5xGJD")
        assertThat(betaAnalyticsUsageGroup.inferenceGeo())
            .contains(BetaAnalyticsUsageCostInferenceGeo.GLOBAL)
        assertThat(betaAnalyticsUsageGroup.model()).contains("claude-opus-5-5")
        assertThat(betaAnalyticsUsageGroup.product()).contains("chat")
        assertThat(betaAnalyticsUsageGroup.rbacGroupId())
            .contains("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaAnalyticsUsageGroup.serviceTier())
            .contains(BetaAnalyticsServiceTier.STANDARD)
        assertThat(betaAnalyticsUsageGroup.sessionId()).contains("sesn_0187kPDyNCwibNNvfaPEApCJ")
        assertThat(betaAnalyticsUsageGroup.slackChannelId()).contains("C0123ABCDEF")
        assertThat(betaAnalyticsUsageGroup.speed()).contains(BetaAnalyticsSpeed.FAST)
        assertThat(betaAnalyticsUsageGroup.userProfileId())
            .contains("uprof_01M33NeYUQK2WV9PpYcsPHLi")
        assertThat(betaAnalyticsUsageGroup.workspaceId())
            .contains("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsUsageGroup =
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

        val roundtrippedBetaAnalyticsUsageGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsUsageGroup),
                jacksonTypeRef<BetaAnalyticsUsageGroup>(),
            )

        assertThat(roundtrippedBetaAnalyticsUsageGroup).isEqualTo(betaAnalyticsUsageGroup)
    }
}
