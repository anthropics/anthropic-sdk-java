package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CmekKeyNetworkBlockedErrorDetailsTest {

    @Test
    fun create() {
        val cmekKeyNetworkBlockedErrorDetails = CmekKeyNetworkBlockedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cmekKeyNetworkBlockedErrorDetails = CmekKeyNetworkBlockedErrorDetails.builder().build()

        val roundtrippedCmekKeyNetworkBlockedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cmekKeyNetworkBlockedErrorDetails),
                jacksonTypeRef<CmekKeyNetworkBlockedErrorDetails>(),
            )

        assertThat(roundtrippedCmekKeyNetworkBlockedErrorDetails)
            .isEqualTo(cmekKeyNetworkBlockedErrorDetails)
    }
}
