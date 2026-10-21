package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTokenEndpointAuthNoneResponseTest {

    @Test
    fun create() {
        val betaManagedAgentsTokenEndpointAuthNoneResponse =
            BetaManagedAgentsTokenEndpointAuthNoneResponse.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTokenEndpointAuthNoneResponse =
            BetaManagedAgentsTokenEndpointAuthNoneResponse.builder().build()

        val roundtrippedBetaManagedAgentsTokenEndpointAuthNoneResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTokenEndpointAuthNoneResponse),
                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthNoneResponse>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTokenEndpointAuthNoneResponse)
            .isEqualTo(betaManagedAgentsTokenEndpointAuthNoneResponse)
    }
}
