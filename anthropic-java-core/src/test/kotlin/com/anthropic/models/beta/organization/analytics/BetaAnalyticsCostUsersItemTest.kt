package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsCostUsersItemTest {

    @Test
    fun create() {
        val betaAnalyticsCostUsersItem =
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

        assertThat(betaAnalyticsCostUsersItem.actor())
            .isEqualTo(
                BetaAnalyticsUserActor.builder()
                    .deleted(true)
                    .emailAddress("jane@example.com")
                    .name("Jane Smith")
                    .userId("user_01AbCdEfGhIjKlMnOpQrSt")
                    .build()
            )
        assertThat(betaAnalyticsCostUsersItem.amount()).isEqualTo("41280.000000")
        assertThat(betaAnalyticsCostUsersItem.claudeTagCategory())
            .contains(BetaAnalyticsClaudeTagCategory.DM)
        assertThat(betaAnalyticsCostUsersItem.claudeTagUserId()).contains("U0123ABCDEF")
        assertThat(betaAnalyticsCostUsersItem.contextWindow())
            .contains(BetaAnalyticsContextWindow.FROM_0_TO_200K)
        assertThat(betaAnalyticsCostUsersItem.costType())
            .contains(BetaAnalyticsCostType.CODE_EXECUTION)
        assertThat(betaAnalyticsCostUsersItem.currency()).isEqualTo("USD")
        assertThat(betaAnalyticsCostUsersItem.endingAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaAnalyticsCostUsersItem.inferenceGeo())
            .contains(BetaAnalyticsCostUsersItem.InferenceGeo.GLOBAL)
        assertThat(betaAnalyticsCostUsersItem.listAmount()).isEqualTo("51600.000000")
        assertThat(betaAnalyticsCostUsersItem.model()).contains("claude-opus-5")
        assertThat(betaAnalyticsCostUsersItem.product()).contains("chat")
        assertThat(betaAnalyticsCostUsersItem.rbacGroupId())
            .contains("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaAnalyticsCostUsersItem.requests()).contains(128L)
        assertThat(betaAnalyticsCostUsersItem.slackChannelId()).contains("C0123ABCDEF")
        assertThat(betaAnalyticsCostUsersItem.speed())
            .contains(BetaAnalyticsCostUsersItem.Speed.FAST)
        assertThat(betaAnalyticsCostUsersItem.startingAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaAnalyticsCostUsersItem.tokenType())
            .contains(BetaAnalyticsTokenType.CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsCostUsersItem =
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

        val roundtrippedBetaAnalyticsCostUsersItem =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsCostUsersItem),
                jacksonTypeRef<BetaAnalyticsCostUsersItem>(),
            )

        assertThat(roundtrippedBetaAnalyticsCostUsersItem).isEqualTo(betaAnalyticsCostUsersItem)
    }
}
