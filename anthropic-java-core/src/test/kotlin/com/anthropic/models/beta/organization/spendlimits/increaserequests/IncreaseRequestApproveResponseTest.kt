package com.anthropic.models.beta.organization.spendlimits.increaserequests

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimit
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitPeriod
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitUserActor
import com.anthropic.models.beta.organization.spendlimits.BetaSpendSummary
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
                    BetaSpendLimitUserActor.builder()
                        .deleted(true)
                        .emailAddress("email_address")
                        .name("name")
                        .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
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
                        .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .build()
                )
                .spendLimit(
                    BetaSpendLimit.builder()
                        .id("id")
                        .amount("50000")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .currency("USD")
                        .isEnabled(true)
                        .period(BetaSpendLimitPeriod.DAILY)
                        .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .spendSummary(
                    BetaSpendSummary.builder()
                        .actor(
                            BetaSpendLimitUserActor.builder()
                                .deleted(true)
                                .emailAddress("email_address")
                                .name("name")
                                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                .build()
                        )
                        .amount("50000")
                        .currency("USD")
                        .period(BetaSpendLimitPeriod.DAILY)
                        .periodToDateSpend("12050.5")
                        .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .userSource("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .spendLimitId("spend_limit_id")
                        .build()
                )
                .status(BetaSpendLimitIncreaseRequestStatus.APPROVED)
                .build()

        assertThat(increaseRequestApproveResponse.id()).isEqualTo("id")
        assertThat(increaseRequestApproveResponse.actor())
            .isEqualTo(
                BetaSpendLimitIncreaseRequestActor.ofUser(
                    BetaSpendLimitUserActor.builder()
                        .deleted(true)
                        .emailAddress("email_address")
                        .name("name")
                        .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .build()
                )
            )
        assertThat(increaseRequestApproveResponse.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(increaseRequestApproveResponse.period()).isEqualTo(BetaSpendLimitPeriod.DAILY)
        assertThat(increaseRequestApproveResponse.resolvedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(increaseRequestApproveResponse.resolvedBy())
            .contains(
                BetaSpendLimitIncreaseRequestResolvedBy.ofUserActor(
                    BetaSpendLimitUserActor.builder()
                        .deleted(true)
                        .emailAddress("email_address")
                        .name("name")
                        .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .build()
                )
            )
        assertThat(increaseRequestApproveResponse.spendLimit())
            .isEqualTo(
                BetaSpendLimit.builder()
                    .id("id")
                    .amount("50000")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .currency("USD")
                    .isEnabled(true)
                    .period(BetaSpendLimitPeriod.DAILY)
                    .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(increaseRequestApproveResponse.spendSummary())
            .contains(
                BetaSpendSummary.builder()
                    .actor(
                        BetaSpendLimitUserActor.builder()
                            .deleted(true)
                            .emailAddress("email_address")
                            .name("name")
                            .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                            .build()
                    )
                    .amount("50000")
                    .currency("USD")
                    .period(BetaSpendLimitPeriod.DAILY)
                    .periodToDateSpend("12050.5")
                    .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                    .userSource("user_01WxYzAbCdEfGhJkMnPqRsTu")
                    .spendLimitId("spend_limit_id")
                    .build()
            )
        assertThat(increaseRequestApproveResponse.status())
            .isEqualTo(BetaSpendLimitIncreaseRequestStatus.APPROVED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val increaseRequestApproveResponse =
            IncreaseRequestApproveResponse.builder()
                .id("id")
                .actor(
                    BetaSpendLimitUserActor.builder()
                        .deleted(true)
                        .emailAddress("email_address")
                        .name("name")
                        .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
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
                        .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .build()
                )
                .spendLimit(
                    BetaSpendLimit.builder()
                        .id("id")
                        .amount("50000")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .currency("USD")
                        .isEnabled(true)
                        .period(BetaSpendLimitPeriod.DAILY)
                        .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .spendSummary(
                    BetaSpendSummary.builder()
                        .actor(
                            BetaSpendLimitUserActor.builder()
                                .deleted(true)
                                .emailAddress("email_address")
                                .name("name")
                                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                                .build()
                        )
                        .amount("50000")
                        .currency("USD")
                        .period(BetaSpendLimitPeriod.DAILY)
                        .periodToDateSpend("12050.5")
                        .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .userSource("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .spendLimitId("spend_limit_id")
                        .build()
                )
                .status(BetaSpendLimitIncreaseRequestStatus.APPROVED)
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
