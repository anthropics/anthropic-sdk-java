package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsRepositoryAuthenticationErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsRepositoryAuthenticationError =
            BetaManagedAgentsRepositoryAuthenticationError.builder()
                .message(
                    "The repository host rejected the credentials for the repository, or required credentials and received none."
                )
                .repositoryUrl("https://github.com/example-org/example-repo")
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.of(
                        BetaManagedAgentsRetryStatusRetrying.Type.RETRYING
                    )
                )
                .build()

        assertThat(betaManagedAgentsRepositoryAuthenticationError.message())
            .isEqualTo(
                "The repository host rejected the credentials for the repository, or required credentials and received none."
            )
        assertThat(betaManagedAgentsRepositoryAuthenticationError.repositoryUrl())
            .contains("https://github.com/example-org/example-repo")
        assertThat(betaManagedAgentsRepositoryAuthenticationError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsRepositoryAuthenticationError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.Type.RETRYING
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsRepositoryAuthenticationError =
            BetaManagedAgentsRepositoryAuthenticationError.builder()
                .message(
                    "The repository host rejected the credentials for the repository, or required credentials and received none."
                )
                .repositoryUrl("https://github.com/example-org/example-repo")
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.of(
                        BetaManagedAgentsRetryStatusRetrying.Type.RETRYING
                    )
                )
                .build()

        val roundtrippedBetaManagedAgentsRepositoryAuthenticationError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsRepositoryAuthenticationError),
                jacksonTypeRef<BetaManagedAgentsRepositoryAuthenticationError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsRepositoryAuthenticationError)
            .isEqualTo(betaManagedAgentsRepositoryAuthenticationError)
    }
}
