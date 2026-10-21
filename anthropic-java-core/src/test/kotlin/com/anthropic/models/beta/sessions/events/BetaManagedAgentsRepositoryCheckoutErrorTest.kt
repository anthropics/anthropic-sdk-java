package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsRepositoryCheckoutErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsRepositoryCheckoutError =
            BetaManagedAgentsRepositoryCheckoutError.builder()
                .message("The requested branch or commit does not exist in the repository.")
                .repositoryUrl("https://github.com/example-org/example-repo")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        assertThat(betaManagedAgentsRepositoryCheckoutError.message())
            .isEqualTo("The requested branch or commit does not exist in the repository.")
        assertThat(betaManagedAgentsRepositoryCheckoutError.repositoryUrl())
            .contains("https://github.com/example-org/example-repo")
        assertThat(betaManagedAgentsRepositoryCheckoutError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsRepositoryCheckoutError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsRepositoryCheckoutError =
            BetaManagedAgentsRepositoryCheckoutError.builder()
                .message("The requested branch or commit does not exist in the repository.")
                .repositoryUrl("https://github.com/example-org/example-repo")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsRepositoryCheckoutError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsRepositoryCheckoutError),
                jacksonTypeRef<BetaManagedAgentsRepositoryCheckoutError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsRepositoryCheckoutError)
            .isEqualTo(betaManagedAgentsRepositoryCheckoutError)
    }
}
