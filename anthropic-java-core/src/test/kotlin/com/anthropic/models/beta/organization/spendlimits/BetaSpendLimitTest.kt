package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitTest {

    @Test
    fun create() {
        val betaSpendLimit =
            BetaSpendLimit.builder()
                .id("id")
                .amount("50000")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .currency("USD")
                .period(BetaSpendLimitPeriod.DAILY)
                .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaSpendLimit.id()).isEqualTo("id")
        assertThat(betaSpendLimit.amount()).contains("50000")
        assertThat(betaSpendLimit.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaSpendLimit.currency()).isEqualTo("USD")
        assertThat(betaSpendLimit.period()).isEqualTo(BetaSpendLimitPeriod.DAILY)
        assertThat(betaSpendLimit.scope())
            .isEqualTo(BetaSpendLimit.Scope.ofUser("user_01WCz1FkmYMm4gnmykNKUu3Q"))
        assertThat(betaSpendLimit.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimit =
            BetaSpendLimit.builder()
                .id("id")
                .amount("50000")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .currency("USD")
                .period(BetaSpendLimitPeriod.DAILY)
                .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaSpendLimit =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimit),
                jacksonTypeRef<BetaSpendLimit>(),
            )

        assertThat(roundtrippedBetaSpendLimit).isEqualTo(betaSpendLimit)
    }
}
