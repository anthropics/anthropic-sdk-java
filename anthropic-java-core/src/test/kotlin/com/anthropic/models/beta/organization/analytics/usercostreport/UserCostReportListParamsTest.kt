package com.anthropic.models.beta.organization.analytics.usercostreport

import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsInferenceGeoFilter
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsProductFilter
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserCostReportListParamsTest {

    @Test
    fun create() {
        UserCostReportListParams.builder()
            .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .bucketWidth(UserCostReportListParams.BucketWidth.DAY)
            .addClaudeTagCategory(BetaAnalyticsClaudeTagCategory.ENGAGED)
            .addClaudeTagUserId("U0123ABCDEF")
            .addContextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
            .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .excludeDeletedUsers(true)
            .addGroupBy(UserCostReportListParams.GroupBy.CLAUDE_TAG_CATEGORY)
            .addInferenceGeo(BetaAnalyticsInferenceGeoFilter.GLOBAL)
            .limit(1L)
            .addModel("string")
            .order(UserCostReportListParams.Order.ASC)
            .orderBy(UserCostReportListParams.OrderBy.AMOUNT)
            .page("page")
            .addProduct(BetaAnalyticsProductFilter.CHAT)
            .addRbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
            .addSlackChannelId("C0123ABCDEF")
            .addSpeed(UserCostReportListParams.Speed.FAST)
            .addUserId("user_01AbCdEfGhIjKlMnOpQrSt")
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            UserCostReportListParams.builder()
                .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .bucketWidth(UserCostReportListParams.BucketWidth.DAY)
                .addClaudeTagCategory(BetaAnalyticsClaudeTagCategory.ENGAGED)
                .addClaudeTagUserId("U0123ABCDEF")
                .addContextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .excludeDeletedUsers(true)
                .addGroupBy(UserCostReportListParams.GroupBy.CLAUDE_TAG_CATEGORY)
                .addInferenceGeo(BetaAnalyticsInferenceGeoFilter.GLOBAL)
                .limit(1L)
                .addModel("string")
                .order(UserCostReportListParams.Order.ASC)
                .orderBy(UserCostReportListParams.OrderBy.AMOUNT)
                .page("page")
                .addProduct(BetaAnalyticsProductFilter.CHAT)
                .addRbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .addSlackChannelId("C0123ABCDEF")
                .addSpeed(UserCostReportListParams.Speed.FAST)
                .addUserId("user_01AbCdEfGhIjKlMnOpQrSt")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("starting_at", "2019-12-27T18:11:19.117Z")
                    .put("bucket_width", "1d")
                    .put("claude_tag_categories[]", "engaged")
                    .put("claude_tag_user_ids[]", "U0123ABCDEF")
                    .put("context_windows[]", "0-200k")
                    .put("ending_at", "2019-12-27T18:11:19.117Z")
                    .put("exclude_deleted_users", "true")
                    .put("group_by[]", "claude_tag_category")
                    .put("inference_geos[]", "global")
                    .put("limit", "1")
                    .put("models[]", "string")
                    .put("order", "asc")
                    .put("order_by", "amount")
                    .put("page", "page")
                    .put("products[]", "chat")
                    .put("rbac_group_ids[]", "rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                    .put("slack_channel_ids[]", "C0123ABCDEF")
                    .put("speeds[]", "fast")
                    .put("user_ids[]", "user_01AbCdEfGhIjKlMnOpQrSt")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params =
            UserCostReportListParams.builder()
                .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("starting_at", "2019-12-27T18:11:19.117Z").build())
    }
}
