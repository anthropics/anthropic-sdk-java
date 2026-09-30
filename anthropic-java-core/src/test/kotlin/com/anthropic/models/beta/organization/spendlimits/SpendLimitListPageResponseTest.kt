package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitListPageResponseTest {

    @Test
    fun create() {
        val spendLimitListPageResponse =
            SpendLimitListPageResponse.builder()
                .addData(
                    BetaSpendLimit.builder()
                        .id("id")
                        .amount("50000")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .currency("USD")
                        .isEnabled(true)
                        .period(BetaSpendLimitPeriod.DAILY)
                        .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(spendLimitListPageResponse.data())
            .containsExactly(
                BetaSpendLimit.builder()
                    .id("id")
                    .amount("50000")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .currency("USD")
                    .isEnabled(true)
                    .period(BetaSpendLimitPeriod.DAILY)
                    .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(spendLimitListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitListPageResponse =
            SpendLimitListPageResponse.builder()
                .addData(
                    BetaSpendLimit.builder()
                        .id("id")
                        .amount("50000")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .currency("USD")
                        .isEnabled(true)
                        .period(BetaSpendLimitPeriod.DAILY)
                        .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedSpendLimitListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitListPageResponse),
                jacksonTypeRef<SpendLimitListPageResponse>(),
            )

        assertThat(roundtrippedSpendLimitListPageResponse).isEqualTo(spendLimitListPageResponse)
    }
}
