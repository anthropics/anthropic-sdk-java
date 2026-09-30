package com.anthropic.models.beta.organization.spendlimits.effective

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitPeriod
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitUserActor
import com.anthropic.models.beta.organization.spendlimits.BetaSpendSummary
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EffectiveListPageResponseTest {

    @Test
    fun create() {
        val effectiveListPageResponse =
            EffectiveListPageResponse.builder()
                .addData(
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
                .nextPage("next_page")
                .build()

        assertThat(effectiveListPageResponse.data())
            .containsExactly(
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
        assertThat(effectiveListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val effectiveListPageResponse =
            EffectiveListPageResponse.builder()
                .addData(
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
                .nextPage("next_page")
                .build()

        val roundtrippedEffectiveListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(effectiveListPageResponse),
                jacksonTypeRef<EffectiveListPageResponse>(),
            )

        assertThat(roundtrippedEffectiveListPageResponse).isEqualTo(effectiveListPageResponse)
    }
}
