// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeletedCredentialTest {

    @Test
    fun create() {
        val betaManagedAgentsDeletedCredential =
            BetaManagedAgentsDeletedCredential.builder()
                .id("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .type(BetaManagedAgentsDeletedCredential.Type.VAULT_CREDENTIAL_DELETED)
                .build()

        assertThat(betaManagedAgentsDeletedCredential.id())
            .isEqualTo("vcrd_011CZkZEMt8gZan2iYOQfSkw")
        assertThat(betaManagedAgentsDeletedCredential.type())
            .isEqualTo(BetaManagedAgentsDeletedCredential.Type.VAULT_CREDENTIAL_DELETED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeletedCredential =
            BetaManagedAgentsDeletedCredential.builder()
                .id("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                .type(BetaManagedAgentsDeletedCredential.Type.VAULT_CREDENTIAL_DELETED)
                .build()

        val roundtrippedBetaManagedAgentsDeletedCredential =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeletedCredential),
                jacksonTypeRef<BetaManagedAgentsDeletedCredential>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeletedCredential)
            .isEqualTo(betaManagedAgentsDeletedCredential)
    }
}
