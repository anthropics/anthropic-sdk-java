package com.anthropic.models.organization.spendlimits.effective

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.spendlimits.SpendLimitPeriod
import com.anthropic.models.organization.spendlimits.SpendLimitUserActor
import com.anthropic.models.organization.spendlimits.SpendSummary
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EffectiveListPageResponseTest {

    @Test
    fun create() {
        val effectiveListPageResponse =
            EffectiveListPageResponse.builder()
                .addData(
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
                .nextPage("next_page")
                .build()

        assertThat(effectiveListPageResponse.data())
            .containsExactly(
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
        assertThat(effectiveListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val effectiveListPageResponse =
            EffectiveListPageResponse.builder()
                .addData(
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
