package com.anthropic.models.organization.spendlimits.increaserequests

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.spendlimits.SpendLimit
import com.anthropic.models.organization.spendlimits.SpendLimitPeriod
import com.anthropic.models.organization.spendlimits.SpendLimitUserActor
import com.anthropic.models.organization.spendlimits.SpendSummary
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IncreaseRequestApproveResponseTest {

    @Test
    fun create() {
        val increaseRequestApproveResponse =
            IncreaseRequestApproveResponse.builder()
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
                .spendLimit(
                    SpendLimit.builder()
                        .id("id")
                        .amount("50000")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .currency("USD")
                        .isEnabled(true)
                        .period(SpendLimitPeriod.DAILY)
                        .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
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

        assertThat(increaseRequestApproveResponse.id()).isEqualTo("id")
        assertThat(increaseRequestApproveResponse.actor())
            .isEqualTo(
                SpendLimitIncreaseRequestActor.ofUser(
                    SpendLimitUserActor.builder()
                        .deleted(true)
                        .emailAddress("email_address")
                        .name("name")
                        .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .build()
                )
            )
        assertThat(increaseRequestApproveResponse.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(increaseRequestApproveResponse.period()).isEqualTo(SpendLimitPeriod.DAILY)
        assertThat(increaseRequestApproveResponse.resolvedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(increaseRequestApproveResponse.resolvedBy())
            .contains(
                SpendLimitIncreaseRequestResolvedBy.ofUserActor(
                    SpendLimitUserActor.builder()
                        .deleted(true)
                        .emailAddress("email_address")
                        .name("name")
                        .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .build()
                )
            )
        assertThat(increaseRequestApproveResponse.spendLimit())
            .contains(
                SpendLimit.builder()
                    .id("id")
                    .amount("50000")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .currency("USD")
                    .isEnabled(true)
                    .period(SpendLimitPeriod.DAILY)
                    .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(increaseRequestApproveResponse.spendSummary())
            .contains(
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
        assertThat(increaseRequestApproveResponse.status())
            .isEqualTo(SpendLimitIncreaseRequestStatus.APPROVED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val increaseRequestApproveResponse =
            IncreaseRequestApproveResponse.builder()
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
                .spendLimit(
                    SpendLimit.builder()
                        .id("id")
                        .amount("50000")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .currency("USD")
                        .isEnabled(true)
                        .period(SpendLimitPeriod.DAILY)
                        .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
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

        val roundtrippedIncreaseRequestApproveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(increaseRequestApproveResponse),
                jacksonTypeRef<IncreaseRequestApproveResponse>(),
            )

        assertThat(roundtrippedIncreaseRequestApproveResponse)
            .isEqualTo(increaseRequestApproveResponse)
    }
}
