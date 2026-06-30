// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsInjectionLocationUpdateParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsInjectionLocationUpdateParams =
            BetaManagedAgentsInjectionLocationUpdateParams.builder().body(true).header(true).build()

        assertThat(betaManagedAgentsInjectionLocationUpdateParams.body()).contains(true)
        assertThat(betaManagedAgentsInjectionLocationUpdateParams.header()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsInjectionLocationUpdateParams =
            BetaManagedAgentsInjectionLocationUpdateParams.builder().body(true).header(true).build()

        val roundtrippedBetaManagedAgentsInjectionLocationUpdateParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsInjectionLocationUpdateParams),
                jacksonTypeRef<BetaManagedAgentsInjectionLocationUpdateParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsInjectionLocationUpdateParams)
            .isEqualTo(betaManagedAgentsInjectionLocationUpdateParams)
    }
}
