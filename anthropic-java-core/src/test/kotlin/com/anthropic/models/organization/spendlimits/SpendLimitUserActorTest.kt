package com.anthropic.models.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitUserActorTest {

    @Test
    fun create() {
        val spendLimitUserActor =
            SpendLimitUserActor.builder()
                .deleted(true)
                .emailAddress("email_address")
                .name("name")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        assertThat(spendLimitUserActor.deleted()).isEqualTo(true)
        assertThat(spendLimitUserActor.emailAddress()).contains("email_address")
        assertThat(spendLimitUserActor.name()).contains("name")
        assertThat(spendLimitUserActor.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitUserActor =
            SpendLimitUserActor.builder()
                .deleted(true)
                .emailAddress("email_address")
                .name("name")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val roundtrippedSpendLimitUserActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitUserActor),
                jacksonTypeRef<SpendLimitUserActor>(),
            )

        assertThat(roundtrippedSpendLimitUserActor).isEqualTo(spendLimitUserActor)
    }
}
