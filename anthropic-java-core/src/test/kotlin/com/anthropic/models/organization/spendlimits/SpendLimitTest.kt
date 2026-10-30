package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitTest {

    @Test
    fun create() {
        val spendLimit =
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

        assertThat(spendLimit.id()).isEqualTo("id")
        assertThat(spendLimit.amount()).contains("50000")
        assertThat(spendLimit.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(spendLimit.currency()).isEqualTo("USD")
        assertThat(spendLimit.isEnabled()).isEqualTo(true)
        assertThat(spendLimit.period()).isEqualTo(SpendLimitPeriod.DAILY)
        assertThat(spendLimit.scope())
            .isEqualTo(SpendLimit.Scope.ofUser("user_01WxYzAbCdEfGhJkMnPqRsTu"))
        assertThat(spendLimit.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimit =
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

        val roundtrippedSpendLimit =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimit),
                jacksonTypeRef<SpendLimit>(),
            )

        assertThat(roundtrippedSpendLimit).isEqualTo(spendLimit)
    }
}
