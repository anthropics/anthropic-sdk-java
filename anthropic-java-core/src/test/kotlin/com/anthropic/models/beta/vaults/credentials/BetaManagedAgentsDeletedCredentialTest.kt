package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeletedCredentialTest {

    @Test
    fun create() {
        val betaManagedAgentsDeletedCredential =
            BetaManagedAgentsDeletedCredential.of("vcrd_011CZkZEMt8gZan2iYPQfSkw")

        assertThat(betaManagedAgentsDeletedCredential.id())
            .isEqualTo("vcrd_011CZkZEMt8gZan2iYPQfSkw")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeletedCredential =
            BetaManagedAgentsDeletedCredential.of("vcrd_011CZkZEMt8gZan2iYPQfSkw")

        val roundtrippedBetaManagedAgentsDeletedCredential =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeletedCredential),
                jacksonTypeRef<BetaManagedAgentsDeletedCredential>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeletedCredential)
            .isEqualTo(betaManagedAgentsDeletedCredential)
    }
}
