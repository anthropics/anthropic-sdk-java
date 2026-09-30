package com.anthropic.models.beta.organization.spendlimits.increaserequests

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitPeriod
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitUserActor
import com.anthropic.models.beta.organization.spendlimits.BetaSpendSummary
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
                    BetaSpendLimitIncreaseRequest.builder()
                        .id("id")
                        .actor(
                            BetaSpendLimitUserActor.builder()
                                .deleted(true)
                                .emailAddress("email_address")
                                .name("name")
                                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .build()
                        )
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .period(BetaSpendLimitPeriod.DAILY)
                        .resolvedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .resolvedBy(
                            BetaSpendLimitUserActor.builder()
                                .deleted(true)
                                .emailAddress("email_address")
                                .name("name")
                                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .build()
                        )
                        .spendSummary(
                            BetaSpendSummary.builder()
                                .actor(
                                    BetaSpendLimitUserActor.builder()
                                        .deleted(true)
                                        .emailAddress("email_address")
                                        .name("name")
                                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                        .build()
                                )
                                .amount("50000")
                                .currency("USD")
                                .period(BetaSpendLimitPeriod.DAILY)
                                .periodToDateSpend("12050.5")
                                .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .userSource("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .spendLimitId("spend_limit_id")
                                .build()
                        )
                        .status(BetaSpendLimitIncreaseRequestStatus.APPROVED)
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(increaseRequestListPageResponse.data())
            .containsExactly(
                BetaSpendLimitIncreaseRequest.builder()
                    .id("id")
                    .actor(
                        BetaSpendLimitUserActor.builder()
                            .deleted(true)
                            .emailAddress("email_address")
                            .name("name")
                            .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                            .build()
                    )
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .period(BetaSpendLimitPeriod.DAILY)
                    .resolvedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .resolvedBy(
                        BetaSpendLimitUserActor.builder()
                            .deleted(true)
                            .emailAddress("email_address")
                            .name("name")
                            .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                            .build()
                    )
                    .spendSummary(
                        BetaSpendSummary.builder()
                            .actor(
                                BetaSpendLimitUserActor.builder()
                                    .deleted(true)
                                    .emailAddress("email_address")
                                    .name("name")
                                    .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                    .build()
                            )
                            .amount("50000")
                            .currency("USD")
                            .period(BetaSpendLimitPeriod.DAILY)
                            .periodToDateSpend("12050.5")
                            .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                            .userSource("user_01WCz1FkmYMm4gnmykNKUu3Q")
                            .spendLimitId("spend_limit_id")
                            .build()
                    )
                    .status(BetaSpendLimitIncreaseRequestStatus.APPROVED)
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
                    BetaSpendLimitIncreaseRequest.builder()
                        .id("id")
                        .actor(
                            BetaSpendLimitUserActor.builder()
                                .deleted(true)
                                .emailAddress("email_address")
                                .name("name")
                                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .build()
                        )
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .period(BetaSpendLimitPeriod.DAILY)
                        .resolvedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .resolvedBy(
                            BetaSpendLimitUserActor.builder()
                                .deleted(true)
                                .emailAddress("email_address")
                                .name("name")
                                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .build()
                        )
                        .spendSummary(
                            BetaSpendSummary.builder()
                                .actor(
                                    BetaSpendLimitUserActor.builder()
                                        .deleted(true)
                                        .emailAddress("email_address")
                                        .name("name")
                                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                        .build()
                                )
                                .amount("50000")
                                .currency("USD")
                                .period(BetaSpendLimitPeriod.DAILY)
                                .periodToDateSpend("12050.5")
                                .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .userSource("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .spendLimitId("spend_limit_id")
                                .build()
                        )
                        .status(BetaSpendLimitIncreaseRequestStatus.APPROVED)
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
