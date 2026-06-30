// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsInjectionLocationParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsInjectionLocationParams =
            BetaManagedAgentsInjectionLocationParams.builder().body(true).header(true).build()

        assertThat(betaManagedAgentsInjectionLocationParams.body()).contains(true)
        assertThat(betaManagedAgentsInjectionLocationParams.header()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsInjectionLocationParams =
            BetaManagedAgentsInjectionLocationParams.builder().body(true).header(true).build()

        val roundtrippedBetaManagedAgentsInjectionLocationParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsInjectionLocationParams),
                jacksonTypeRef<BetaManagedAgentsInjectionLocationParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsInjectionLocationParams)
            .isEqualTo(betaManagedAgentsInjectionLocationParams)
    }
}
