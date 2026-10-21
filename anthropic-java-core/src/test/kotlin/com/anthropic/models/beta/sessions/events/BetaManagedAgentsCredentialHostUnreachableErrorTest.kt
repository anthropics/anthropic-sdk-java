package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCredentialHostUnreachableErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsCredentialHostUnreachableError =
            BetaManagedAgentsCredentialHostUnreachableError.builder()
                .credentialId("credential_id")
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .vaultId("vault_id")
                .build()

        assertThat(betaManagedAgentsCredentialHostUnreachableError.credentialId())
            .isEqualTo("credential_id")
        assertThat(betaManagedAgentsCredentialHostUnreachableError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsCredentialHostUnreachableError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsCredentialHostUnreachableError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder().build()
                )
            )
        assertThat(betaManagedAgentsCredentialHostUnreachableError.vaultId()).isEqualTo("vault_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCredentialHostUnreachableError =
            BetaManagedAgentsCredentialHostUnreachableError.builder()
                .credentialId("credential_id")
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .vaultId("vault_id")
                .build()

        val roundtrippedBetaManagedAgentsCredentialHostUnreachableError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCredentialHostUnreachableError),
                jacksonTypeRef<BetaManagedAgentsCredentialHostUnreachableError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCredentialHostUnreachableError)
            .isEqualTo(betaManagedAgentsCredentialHostUnreachableError)
    }
}
