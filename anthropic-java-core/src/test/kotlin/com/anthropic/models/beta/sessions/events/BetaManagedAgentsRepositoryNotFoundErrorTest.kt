package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsRepositoryNotFoundErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsRepositoryNotFoundError =
            BetaManagedAgentsRepositoryNotFoundError.builder()
                .message("The repository host reported the repository as not found.")
                .repositoryUrl("https://github.com/example-org/example-repo")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        assertThat(betaManagedAgentsRepositoryNotFoundError.message())
            .isEqualTo("The repository host reported the repository as not found.")
        assertThat(betaManagedAgentsRepositoryNotFoundError.repositoryUrl())
            .contains("https://github.com/example-org/example-repo")
        assertThat(betaManagedAgentsRepositoryNotFoundError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsRepositoryNotFoundError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsRepositoryNotFoundError =
            BetaManagedAgentsRepositoryNotFoundError.builder()
                .message("The repository host reported the repository as not found.")
                .repositoryUrl("https://github.com/example-org/example-repo")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsRepositoryNotFoundError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsRepositoryNotFoundError),
                jacksonTypeRef<BetaManagedAgentsRepositoryNotFoundError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsRepositoryNotFoundError)
            .isEqualTo(betaManagedAgentsRepositoryNotFoundError)
    }
}
