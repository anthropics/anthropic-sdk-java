package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitUserScopeTest {

    @Test
    fun create() {
        val spendLimitUserScope = SpendLimitUserScope.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        assertThat(spendLimitUserScope.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitUserScope = SpendLimitUserScope.of("user_01WxYzAbCdEfGhJkMnPqRsTu")

        val roundtrippedSpendLimitUserScope =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitUserScope),
                jacksonTypeRef<SpendLimitUserScope>(),
            )

        assertThat(roundtrippedSpendLimitUserScope).isEqualTo(spendLimitUserScope)
    }
}
