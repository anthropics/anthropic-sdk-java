package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBillingPolicyTest {

    @Test
    fun create() {
        val betaBillingPolicy = BetaBillingPolicy.builder().billTo(BetaBillTo.SELF).build()

        assertThat(betaBillingPolicy.billTo()).contains(BetaBillTo.SELF)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBillingPolicy = BetaBillingPolicy.builder().billTo(BetaBillTo.SELF).build()

        val roundtrippedBetaBillingPolicy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBillingPolicy),
                jacksonTypeRef<BetaBillingPolicy>(),
            )

        assertThat(roundtrippedBetaBillingPolicy).isEqualTo(betaBillingPolicy)
    }
}
