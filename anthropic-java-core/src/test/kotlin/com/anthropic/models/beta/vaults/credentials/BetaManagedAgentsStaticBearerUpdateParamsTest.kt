package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsStaticBearerUpdateParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsStaticBearerUpdateParams =
            BetaManagedAgentsStaticBearerUpdateParams.builder().token("token").build()

        assertThat(betaManagedAgentsStaticBearerUpdateParams.token()).contains("token")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStaticBearerUpdateParams =
            BetaManagedAgentsStaticBearerUpdateParams.builder().token("token").build()

        val roundtrippedBetaManagedAgentsStaticBearerUpdateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStaticBearerUpdateParams),
                jacksonTypeRef<BetaManagedAgentsStaticBearerUpdateParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStaticBearerUpdateParams)
            .isEqualTo(betaManagedAgentsStaticBearerUpdateParams)
    }
}
