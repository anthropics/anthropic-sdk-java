package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaStorageAlreadyRegisteredErrorDetailsTest {

    @Test
    fun create() {
        val betaStorageAlreadyRegisteredErrorDetails =
            BetaStorageAlreadyRegisteredErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageAlreadyRegisteredErrorDetails =
            BetaStorageAlreadyRegisteredErrorDetails.builder().build()

        val roundtrippedBetaStorageAlreadyRegisteredErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageAlreadyRegisteredErrorDetails),
                jacksonTypeRef<BetaStorageAlreadyRegisteredErrorDetails>(),
            )

        assertThat(roundtrippedBetaStorageAlreadyRegisteredErrorDetails)
            .isEqualTo(betaStorageAlreadyRegisteredErrorDetails)
    }
}
