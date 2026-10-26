package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSpendLimitUserActorTest {

    @Test
    fun create() {
        val betaSpendLimitUserActor =
            BetaSpendLimitUserActor.builder()
                .deleted(true)
                .emailAddress("email_address")
                .name("name")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        assertThat(betaSpendLimitUserActor.deleted()).isEqualTo(true)
        assertThat(betaSpendLimitUserActor.emailAddress()).contains("email_address")
        assertThat(betaSpendLimitUserActor.name()).contains("name")
        assertThat(betaSpendLimitUserActor.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitUserActor =
            BetaSpendLimitUserActor.builder()
                .deleted(true)
                .emailAddress("email_address")
                .name("name")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val roundtrippedBetaSpendLimitUserActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitUserActor),
                jacksonTypeRef<BetaSpendLimitUserActor>(),
            )

        assertThat(roundtrippedBetaSpendLimitUserActor).isEqualTo(betaSpendLimitUserActor)
    }
}
