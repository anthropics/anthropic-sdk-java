package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitUserScopeTest {

    @Test
    fun create() {
        val betaSpendLimitUserScope = BetaSpendLimitUserScope.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        assertThat(betaSpendLimitUserScope.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitUserScope = BetaSpendLimitUserScope.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        val roundtrippedBetaSpendLimitUserScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitUserScope),
                jacksonTypeRef<BetaSpendLimitUserScope>(),
            )

        assertThat(roundtrippedBetaSpendLimitUserScope).isEqualTo(betaSpendLimitUserScope)
    }
}
