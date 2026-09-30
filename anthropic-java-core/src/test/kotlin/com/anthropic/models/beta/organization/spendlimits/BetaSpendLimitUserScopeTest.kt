package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitUserScopeTest {

    @Test
    fun create() {
        val betaSpendLimitUserScope = BetaSpendLimitUserScope.of("user_01WCz1FkmYMm4gnmykNKUu3Q")

        assertThat(betaSpendLimitUserScope.userId()).isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitUserScope = BetaSpendLimitUserScope.of("user_01WCz1FkmYMm4gnmykNKUu3Q")

        val roundtrippedBetaSpendLimitUserScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitUserScope),
                jacksonTypeRef<BetaSpendLimitUserScope>(),
            )

        assertThat(roundtrippedBetaSpendLimitUserScope).isEqualTo(betaSpendLimitUserScope)
    }
}
