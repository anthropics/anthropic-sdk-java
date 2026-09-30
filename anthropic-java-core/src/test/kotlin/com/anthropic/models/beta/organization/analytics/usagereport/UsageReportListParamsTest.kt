package com.anthropic.models.beta.organization.analytics.usagereport

import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsInferenceGeoFilter
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsProductFilter
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UsageReportListParamsTest {

    @Test
    fun create() {
        UsageReportListParams.builder()
            .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .bucketWidth(UsageReportListParams.BucketWidth.DAY)
            .addClaudeTagCategory(BetaAnalyticsClaudeTagCategory.ENGAGED)
            .addClaudeTagUserId("U0123ABCDEF")
            .addContextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
            .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .addGroupBy(UsageReportListParams.GroupBy.CLAUDE_TAG_CATEGORY)
            .addInferenceGeo(BetaAnalyticsInferenceGeoFilter.GLOBAL)
            .limit(1L)
            .addModel("string")
            .page("page")
            .addProduct(BetaAnalyticsProductFilter.CHAT)
            .addRbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
            .addSlackChannelId("C0123ABCDEF")
            .addSpeed(UsageReportListParams.Speed.FAST)
            .addUserId("string")
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            UsageReportListParams.builder()
                .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .bucketWidth(UsageReportListParams.BucketWidth.DAY)
                .addClaudeTagCategory(BetaAnalyticsClaudeTagCategory.ENGAGED)
                .addClaudeTagUserId("U0123ABCDEF")
                .addContextWindow(BetaAnalyticsContextWindow.FROM_0_TO_200K)
                .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addGroupBy(UsageReportListParams.GroupBy.CLAUDE_TAG_CATEGORY)
                .addInferenceGeo(BetaAnalyticsInferenceGeoFilter.GLOBAL)
                .limit(1L)
                .addModel("string")
                .page("page")
                .addProduct(BetaAnalyticsProductFilter.CHAT)
                .addRbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .addSlackChannelId("C0123ABCDEF")
                .addSpeed(UsageReportListParams.Speed.FAST)
                .addUserId("string")
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
                    .put("group_by[]", "claude_tag_category")
                    .put("inference_geos[]", "global")
                    .put("limit", "1")
                    .put("models[]", "string")
                    .put("page", "page")
                    .put("products[]", "chat")
                    .put("rbac_group_ids[]", "rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                    .put("slack_channel_ids[]", "C0123ABCDEF")
                    .put("speeds[]", "fast")
                    .put("user_ids[]", "string")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params =
            UsageReportListParams.builder()
                .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("starting_at", "2019-12-27T18:11:19.117Z").build())
    }
}
