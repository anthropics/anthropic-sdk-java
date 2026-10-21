package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsRepositoryCloneErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsRepositoryCloneError =
            BetaManagedAgentsRepositoryCloneError.builder()
                .message("The repository could not be cloned.")
                .repositoryUrl("https://github.com/example-org/example-repo")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        assertThat(betaManagedAgentsRepositoryCloneError.message())
            .isEqualTo("The repository could not be cloned.")
        assertThat(betaManagedAgentsRepositoryCloneError.repositoryUrl())
            .contains("https://github.com/example-org/example-repo")
        assertThat(betaManagedAgentsRepositoryCloneError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsRepositoryCloneError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsRepositoryCloneError =
            BetaManagedAgentsRepositoryCloneError.builder()
                .message("The repository could not be cloned.")
                .repositoryUrl("https://github.com/example-org/example-repo")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsRepositoryCloneError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsRepositoryCloneError),
                jacksonTypeRef<BetaManagedAgentsRepositoryCloneError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsRepositoryCloneError)
            .isEqualTo(betaManagedAgentsRepositoryCloneError)
    }
}
