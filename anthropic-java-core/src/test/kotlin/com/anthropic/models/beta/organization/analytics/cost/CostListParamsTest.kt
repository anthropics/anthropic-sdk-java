package com.anthropic.models.beta.organization.analytics.cost

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsActorType
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsInferenceGeoFilter
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServiceTier
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSpeed
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsTimeBucketWidth
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageCostProductFilter
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageCostSortOrder
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CostListParamsTest {

    @Test
    fun create() {
        CostListParams.builder()
            .startingAt(OffsetDateTime.parse("2026-09-01T00:00:00Z"))
            .addActorId("user_01WCz1FkmYMm4gnmykNKUu3Q")
            .addActorType(BetaAnalyticsActorType.SERVICE_ACCOUNT_ACTOR)
            .addAgentId("agent_01VHd3EAGJ6PLkP9LM7DDu3f")
            .addApiKeyId("apikey_01Rj2N8SVvo6BePZj99NhmiT")
            .addClaudeTagCategory(BetaAnalyticsClaudeTagCategory.ENGAGED)
            .addClaudeTagUserId("U0123ABCDEF")
            .addContextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
            .addDeploymentId("depl_01JFC6ihzZHtAuyHsaENdJhY")
            .endingAt(OffsetDateTime.parse("2026-10-01T00:00:00Z"))
            .addEnvironmentId("env_017oTjuGedHtjhJauVC5xGJD")
            .excludeDeletedUsers(true)
            .addGroupBy(BetaAnalyticsCostGroupBy.ACTOR)
            .addInferenceGeo(BetaAnalyticsInferenceGeoFilter.GLOBAL)
            .limit(1L)
            .addModel("string")
            .order(BetaAnalyticsUsageCostSortOrder.ASC)
            .orderBy(BetaAnalyticsCostOrderBy.AMOUNT)
            .page("page")
            .addProduct(BetaAnalyticsUsageCostProductFilter.CHAT)
            .addRbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
            .addServiceTier(BetaAnalyticsServiceTier.BATCH)
            .addSessionId("sesn_0187kPDyNCwibNNvfaPEApCJ")
            .addSlackChannelId("C0123ABCDEF")
            .addSpeed(BetaAnalyticsSpeed.FAST)
            .timeBucketWidth(BetaAnalyticsTimeBucketWidth.DAY)
            .addUserProfileId("uprof_01M33NeYUQK2WV9PpYcsPHLi")
            .addWorkspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun headers() {
        val params =
            CostListParams.builder()
                .startingAt(OffsetDateTime.parse("2026-09-01T00:00:00Z"))
                .addActorId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .addActorType(BetaAnalyticsActorType.SERVICE_ACCOUNT_ACTOR)
                .addAgentId("agent_01VHd3EAGJ6PLkP9LM7DDu3f")
                .addApiKeyId("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                .addClaudeTagCategory(BetaAnalyticsClaudeTagCategory.ENGAGED)
                .addClaudeTagUserId("U0123ABCDEF")
                .addContextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                .addDeploymentId("depl_01JFC6ihzZHtAuyHsaENdJhY")
                .endingAt(OffsetDateTime.parse("2026-10-01T00:00:00Z"))
                .addEnvironmentId("env_017oTjuGedHtjhJauVC5xGJD")
                .excludeDeletedUsers(true)
                .addGroupBy(BetaAnalyticsCostGroupBy.ACTOR)
                .addInferenceGeo(BetaAnalyticsInferenceGeoFilter.GLOBAL)
                .limit(1L)
                .addModel("string")
                .order(BetaAnalyticsUsageCostSortOrder.ASC)
                .orderBy(BetaAnalyticsCostOrderBy.AMOUNT)
                .page("page")
                .addProduct(BetaAnalyticsUsageCostProductFilter.CHAT)
                .addRbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .addServiceTier(BetaAnalyticsServiceTier.BATCH)
                .addSessionId("sesn_0187kPDyNCwibNNvfaPEApCJ")
                .addSlackChannelId("C0123ABCDEF")
                .addSpeed(BetaAnalyticsSpeed.FAST)
                .timeBucketWidth(BetaAnalyticsTimeBucketWidth.DAY)
                .addUserProfileId("uprof_01M33NeYUQK2WV9PpYcsPHLi")
                .addWorkspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "analytics-usage-cost-2026-08-24")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            CostListParams.builder()
                .startingAt(OffsetDateTime.parse("2026-09-01T00:00:00Z"))
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            CostListParams.builder()
                .startingAt(OffsetDateTime.parse("2026-09-01T00:00:00Z"))
                .addActorId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .addActorType(BetaAnalyticsActorType.SERVICE_ACCOUNT_ACTOR)
                .addAgentId("agent_01VHd3EAGJ6PLkP9LM7DDu3f")
                .addApiKeyId("apikey_01Rj2N8SVvo6BePZj99NhmiT")
                .addClaudeTagCategory(BetaAnalyticsClaudeTagCategory.ENGAGED)
                .addClaudeTagUserId("U0123ABCDEF")
                .addContextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                .addDeploymentId("depl_01JFC6ihzZHtAuyHsaENdJhY")
                .endingAt(OffsetDateTime.parse("2026-10-01T00:00:00Z"))
                .addEnvironmentId("env_017oTjuGedHtjhJauVC5xGJD")
                .excludeDeletedUsers(true)
                .addGroupBy(BetaAnalyticsCostGroupBy.ACTOR)
                .addInferenceGeo(BetaAnalyticsInferenceGeoFilter.GLOBAL)
                .limit(1L)
                .addModel("string")
                .order(BetaAnalyticsUsageCostSortOrder.ASC)
                .orderBy(BetaAnalyticsCostOrderBy.AMOUNT)
                .page("page")
                .addProduct(BetaAnalyticsUsageCostProductFilter.CHAT)
                .addRbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .addServiceTier(BetaAnalyticsServiceTier.BATCH)
                .addSessionId("sesn_0187kPDyNCwibNNvfaPEApCJ")
                .addSlackChannelId("C0123ABCDEF")
                .addSpeed(BetaAnalyticsSpeed.FAST)
                .timeBucketWidth(BetaAnalyticsTimeBucketWidth.DAY)
                .addUserProfileId("uprof_01M33NeYUQK2WV9PpYcsPHLi")
                .addWorkspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("starting_at", "2026-09-01T00:00:00Z")
                    .put("actor_ids[]", "user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .put("actor_types[]", "service_account_actor")
                    .put("agent_ids[]", "agent_01VHd3EAGJ6PLkP9LM7DDu3f")
                    .put("api_key_ids[]", "apikey_01Rj2N8SVvo6BePZj99NhmiT")
                    .put("claude_tag_categories[]", "engaged")
                    .put("claude_tag_user_ids[]", "U0123ABCDEF")
                    .put("context_windows[]", "0-200k")
                    .put("deployment_ids[]", "depl_01JFC6ihzZHtAuyHsaENdJhY")
                    .put("ending_at", "2026-10-01T00:00:00Z")
                    .put("environment_ids[]", "env_017oTjuGedHtjhJauVC5xGJD")
                    .put("exclude_deleted_users", "true")
                    .put("group_by[]", "actor")
                    .put("inference_geos[]", "global")
                    .put("limit", "1")
                    .put("models[]", "string")
                    .put("order", "asc")
                    .put("order_by", "amount")
                    .put("page", "page")
                    .put("products[]", "chat")
                    .put("rbac_group_ids[]", "rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                    .put("service_tiers[]", "batch")
                    .put("session_ids[]", "sesn_0187kPDyNCwibNNvfaPEApCJ")
                    .put("slack_channel_ids[]", "C0123ABCDEF")
                    .put("speeds[]", "fast")
                    .put("time_bucket_width", "1d")
                    .put("user_profile_ids[]", "uprof_01M33NeYUQK2WV9PpYcsPHLi")
                    .put("workspace_ids[]", "wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params =
            CostListParams.builder()
                .startingAt(OffsetDateTime.parse("2026-09-01T00:00:00Z"))
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("starting_at", "2026-09-01T00:00:00Z").build())
    }
}
