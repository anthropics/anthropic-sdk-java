// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTokenEndpointAuthNoneParamTest {

    @Test
    fun create() {
        val betaManagedAgentsTokenEndpointAuthNoneParam =
            BetaManagedAgentsTokenEndpointAuthNoneParam.builder()
                .type(BetaManagedAgentsTokenEndpointAuthNoneParam.Type.NONE)
                .build()

        assertThat(betaManagedAgentsTokenEndpointAuthNoneParam.type())
            .isEqualTo(BetaManagedAgentsTokenEndpointAuthNoneParam.Type.NONE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTokenEndpointAuthNoneParam =
            BetaManagedAgentsTokenEndpointAuthNoneParam.builder()
                .type(BetaManagedAgentsTokenEndpointAuthNoneParam.Type.NONE)
                .build()

        val roundtrippedBetaManagedAgentsTokenEndpointAuthNoneParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTokenEndpointAuthNoneParam),
                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthNoneParam>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTokenEndpointAuthNoneParam)
            .isEqualTo(betaManagedAgentsTokenEndpointAuthNoneParam)
    }
}
