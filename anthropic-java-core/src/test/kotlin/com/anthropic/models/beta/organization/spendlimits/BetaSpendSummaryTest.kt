package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendSummaryTest {

    @Test
    fun create() {
        val betaSpendSummary =
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

        assertThat(betaSpendSummary.actor())
            .isEqualTo(
                BetaSpendSummary.Actor.ofUser(
                    BetaSpendLimitUserActor.builder()
                        .deleted(true)
                        .emailAddress("email_address")
                        .name("name")
                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .build()
                )
            )
        assertThat(betaSpendSummary.amount()).contains("50000")
        assertThat(betaSpendSummary.currency()).isEqualTo("USD")
        assertThat(betaSpendSummary.period()).isEqualTo(BetaSpendLimitPeriod.DAILY)
        assertThat(betaSpendSummary.periodToDateSpend()).isEqualTo("12050.5")
        assertThat(betaSpendSummary.scope())
            .isEqualTo(BetaSpendSummary.Scope.ofUser("user_01WCz1FkmYMm4gnmykNKUu3Q"))
        assertThat(betaSpendSummary.source())
            .isEqualTo(BetaSpendSummary.Source.ofUser("user_01WCz1FkmYMm4gnmykNKUu3Q"))
        assertThat(betaSpendSummary.spendLimitId()).isEqualTo("spend_limit_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendSummary =
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

        val roundtrippedBetaSpendSummary =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendSummary),
                jacksonTypeRef<BetaSpendSummary>(),
            )

        assertThat(roundtrippedBetaSpendSummary).isEqualTo(betaSpendSummary)
    }
}
