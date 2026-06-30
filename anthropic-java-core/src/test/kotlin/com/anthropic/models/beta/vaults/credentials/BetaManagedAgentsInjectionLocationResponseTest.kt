// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsInjectionLocationResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsInjectionLocationResponse =
            BetaManagedAgentsInjectionLocationResponse.builder().body(true).header(true).build()

        assertThat(betaManagedAgentsInjectionLocationResponse.body()).isEqualTo(true)
        assertThat(betaManagedAgentsInjectionLocationResponse.header()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsInjectionLocationResponse =
            BetaManagedAgentsInjectionLocationResponse.builder().body(true).header(true).build()

        val roundtrippedBetaManagedAgentsInjectionLocationResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsInjectionLocationResponse),
                jacksonTypeRef<BetaManagedAgentsInjectionLocationResponse>(),
            )

        assertThat(roundtrippedBetaManagedAgentsInjectionLocationResponse)
            .isEqualTo(betaManagedAgentsInjectionLocationResponse)
    }
}
