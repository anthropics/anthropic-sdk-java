package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendSummaryTest {

    @Test
    fun create() {
        val spendSummary =
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

        assertThat(spendSummary.actor())
            .isEqualTo(
                SpendSummary.Actor.ofUser(
                    SpendLimitUserActor.builder()
                        .deleted(true)
                        .emailAddress("email_address")
                        .name("name")
                        .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                        .build()
                )
            )
        assertThat(spendSummary.amount()).contains("50000")
        assertThat(spendSummary.currency()).isEqualTo("USD")
        assertThat(spendSummary.period()).isEqualTo(SpendLimitPeriod.DAILY)
        assertThat(spendSummary.periodToDateSpend()).isEqualTo("12050.5")
        assertThat(spendSummary.scope())
            .isEqualTo(SpendSummary.Scope.ofUser("user_01WxYzAbCdEfGhJkMnPqRsTu"))
        assertThat(spendSummary.source())
            .isEqualTo(SpendSummary.Source.ofUser("user_01WxYzAbCdEfGhJkMnPqRsTu"))
        assertThat(spendSummary.spendLimitId()).isEqualTo("spend_limit_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendSummary =
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

        val roundtrippedSpendSummary =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendSummary),
                jacksonTypeRef<SpendSummary>(),
            )

        assertThat(roundtrippedSpendSummary).isEqualTo(spendSummary)
    }
}
