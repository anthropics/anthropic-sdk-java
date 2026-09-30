package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitDeleteResponseTest {

    @Test
    fun create() {
        val spendLimitDeleteResponse = SpendLimitDeleteResponse.of("id")

        assertThat(spendLimitDeleteResponse.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitDeleteResponse = SpendLimitDeleteResponse.of("id")

        val roundtrippedSpendLimitDeleteResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitDeleteResponse),
                jacksonTypeRef<SpendLimitDeleteResponse>(),
            )

        assertThat(roundtrippedSpendLimitDeleteResponse).isEqualTo(spendLimitDeleteResponse)
    }
}
