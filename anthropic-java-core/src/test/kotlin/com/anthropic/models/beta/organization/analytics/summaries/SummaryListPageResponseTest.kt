package com.anthropic.models.beta.organization.analytics.summaries

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSingleDayActivitySummary
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SummaryListPageResponseTest {

    @Test
    fun create() {
        val summaryListPageResponse =
            SummaryListPageResponse.builder()
                .addData(
                    BetaAnalyticsSingleDayActivitySummary.builder()
                        .assignedSeatCount(0L)
                        .coworkDailyActiveUserCount(0L)
                        .coworkMonthlyActiveUserCount(0L)
                        .coworkWeeklyActiveUserCount(0L)
                        .dailyActiveUserCount(0L)
                        .dailyAdoptionRate(0.0)
                        .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .monthlyActiveUserCount(0L)
                        .monthlyAdoptionRate(0.0)
                        .pendingInviteCount(0L)
                        .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .weeklyActiveUserCount(0L)
                        .weeklyAdoptionRate(0.0)
                        .chatDailyActiveUserCount(0L)
                        .chatMonthlyActiveUserCount(0L)
                        .chatWeeklyActiveUserCount(0L)
                        .claudeCodeDailyActiveUserCount(0L)
                        .claudeCodeMonthlyActiveUserCount(0L)
                        .claudeCodeWeeklyActiveUserCount(0L)
                        .claudeDesignDailyActiveUserCount(0L)
                        .claudeDesignMonthlyActiveUserCount(0L)
                        .claudeDesignWeeklyActiveUserCount(0L)
                        .officeAgentDailyActiveUserCount(0L)
                        .officeAgentMonthlyActiveUserCount(0L)
                        .officeAgentWeeklyActiveUserCount(0L)
                        .scienceDailyActiveUserCount(0L)
                        .scienceEntitledUserCount(0L)
                        .scienceMonthlyActiveUserCount(0L)
                        .scienceWeeklyActiveUserCount(0L)
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(summaryListPageResponse.data())
            .containsExactly(
                BetaAnalyticsSingleDayActivitySummary.builder()
                    .assignedSeatCount(0L)
                    .coworkDailyActiveUserCount(0L)
                    .coworkMonthlyActiveUserCount(0L)
                    .coworkWeeklyActiveUserCount(0L)
                    .dailyActiveUserCount(0L)
                    .dailyAdoptionRate(0.0)
                    .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .monthlyActiveUserCount(0L)
                    .monthlyAdoptionRate(0.0)
                    .pendingInviteCount(0L)
                    .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .weeklyActiveUserCount(0L)
                    .weeklyAdoptionRate(0.0)
                    .chatDailyActiveUserCount(0L)
                    .chatMonthlyActiveUserCount(0L)
                    .chatWeeklyActiveUserCount(0L)
                    .claudeCodeDailyActiveUserCount(0L)
                    .claudeCodeMonthlyActiveUserCount(0L)
                    .claudeCodeWeeklyActiveUserCount(0L)
                    .claudeDesignDailyActiveUserCount(0L)
                    .claudeDesignMonthlyActiveUserCount(0L)
                    .claudeDesignWeeklyActiveUserCount(0L)
                    .officeAgentDailyActiveUserCount(0L)
                    .officeAgentMonthlyActiveUserCount(0L)
                    .officeAgentWeeklyActiveUserCount(0L)
                    .scienceDailyActiveUserCount(0L)
                    .scienceEntitledUserCount(0L)
                    .scienceMonthlyActiveUserCount(0L)
                    .scienceWeeklyActiveUserCount(0L)
                    .build()
            )
        assertThat(summaryListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val summaryListPageResponse =
            SummaryListPageResponse.builder()
                .addData(
                    BetaAnalyticsSingleDayActivitySummary.builder()
                        .assignedSeatCount(0L)
                        .coworkDailyActiveUserCount(0L)
                        .coworkMonthlyActiveUserCount(0L)
                        .coworkWeeklyActiveUserCount(0L)
                        .dailyActiveUserCount(0L)
                        .dailyAdoptionRate(0.0)
                        .endingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .monthlyActiveUserCount(0L)
                        .monthlyAdoptionRate(0.0)
                        .pendingInviteCount(0L)
                        .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .weeklyActiveUserCount(0L)
                        .weeklyAdoptionRate(0.0)
                        .chatDailyActiveUserCount(0L)
                        .chatMonthlyActiveUserCount(0L)
                        .chatWeeklyActiveUserCount(0L)
                        .claudeCodeDailyActiveUserCount(0L)
                        .claudeCodeMonthlyActiveUserCount(0L)
                        .claudeCodeWeeklyActiveUserCount(0L)
                        .claudeDesignDailyActiveUserCount(0L)
                        .claudeDesignMonthlyActiveUserCount(0L)
                        .claudeDesignWeeklyActiveUserCount(0L)
                        .officeAgentDailyActiveUserCount(0L)
                        .officeAgentMonthlyActiveUserCount(0L)
                        .officeAgentWeeklyActiveUserCount(0L)
                        .scienceDailyActiveUserCount(0L)
                        .scienceEntitledUserCount(0L)
                        .scienceMonthlyActiveUserCount(0L)
                        .scienceWeeklyActiveUserCount(0L)
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedSummaryListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(summaryListPageResponse),
                jacksonTypeRef<SummaryListPageResponse>(),
            )

        assertThat(roundtrippedSummaryListPageResponse).isEqualTo(summaryListPageResponse)
    }
}
