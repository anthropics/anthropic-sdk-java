package com.anthropic.models.organization.spendlimits.increaserequests

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.spendlimits.SpendLimitPeriod
import com.anthropic.models.organization.spendlimits.SpendLimitUserActor
import com.anthropic.models.organization.spendlimits.SpendSummary
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IncreaseRequestListPageResponseTest {

    @Test
    fun create() {
        val increaseRequestListPageResponse =
            IncreaseRequestListPageResponse.builder()
                .addData(
                    SpendLimitIncreaseRequest.builder()
                        .id("id")
                        .actor(
                            SpendLimitUserActor.builder()
                                .deleted(true)
                                .emailAddress("email_address")
                                .name("name")
                                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                .build()
                        )
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .period(SpendLimitPeriod.DAILY)
                        .resolvedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .resolvedBy(
                            SpendLimitUserActor.builder()
                                .deleted(true)
                                .emailAddress("email_address")
                                .name("name")
                                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                .build()
                        )
                        .spendSummary(
                            SpendSummary.builder()
                                .actor(
                                    SpendLimitUserActor.builder()
                                        .deleted(true)
                                        .emailAddress("email_address")
                                        .name("name")
                                        .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                        .build()
                                )
                                .amount("50000")
                                .currency("USD")
                                .period(SpendLimitPeriod.DAILY)
                                .periodToDateSpend("12050.5")
                                .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                .userSource("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                .spendLimitId("spend_limit_id")
                                .build()
                        )
                        .status(SpendLimitIncreaseRequestStatus.APPROVED)
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(increaseRequestListPageResponse.data())
            .containsExactly(
                SpendLimitIncreaseRequest.builder()
                    .id("id")
                    .actor(
                        SpendLimitUserActor.builder()
                            .deleted(true)
                            .emailAddress("email_address")
                            .name("name")
                            .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                            .build()
                    )
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .period(SpendLimitPeriod.DAILY)
                    .resolvedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .resolvedBy(
                        SpendLimitUserActor.builder()
                            .deleted(true)
                            .emailAddress("email_address")
                            .name("name")
                            .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                            .build()
                    )
                    .spendSummary(
                        SpendSummary.builder()
                            .actor(
                                SpendLimitUserActor.builder()
                                    .deleted(true)
                                    .emailAddress("email_address")
                                    .name("name")
                                    .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                    .build()
                            )
                            .amount("50000")
                            .currency("USD")
                            .period(SpendLimitPeriod.DAILY)
                            .periodToDateSpend("12050.5")
                            .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                            .userSource("user_01WxYzAbCdEfGhJkMnPqRsTu")
                            .spendLimitId("spend_limit_id")
                            .build()
                    )
                    .status(SpendLimitIncreaseRequestStatus.APPROVED)
                    .build()
            )
        assertThat(increaseRequestListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val increaseRequestListPageResponse =
            IncreaseRequestListPageResponse.builder()
                .addData(
                    SpendLimitIncreaseRequest.builder()
                        .id("id")
                        .actor(
                            SpendLimitUserActor.builder()
                                .deleted(true)
                                .emailAddress("email_address")
                                .name("name")
                                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                .build()
                        )
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .period(SpendLimitPeriod.DAILY)
                        .resolvedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .resolvedBy(
                            SpendLimitUserActor.builder()
                                .deleted(true)
                                .emailAddress("email_address")
                                .name("name")
                                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                .build()
                        )
                        .spendSummary(
                            SpendSummary.builder()
                                .actor(
                                    SpendLimitUserActor.builder()
                                        .deleted(true)
                                        .emailAddress("email_address")
                                        .name("name")
                                        .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                        .build()
                                )
                                .amount("50000")
                                .currency("USD")
                                .period(SpendLimitPeriod.DAILY)
                                .periodToDateSpend("12050.5")
                                .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                .userSource("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                .spendLimitId("spend_limit_id")
                                .build()
                        )
                        .status(SpendLimitIncreaseRequestStatus.APPROVED)
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedIncreaseRequestListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(increaseRequestListPageResponse),
                jacksonTypeRef<IncreaseRequestListPageResponse>(),
            )

        assertThat(roundtrippedIncreaseRequestListPageResponse)
            .isEqualTo(increaseRequestListPageResponse)
    }
}
