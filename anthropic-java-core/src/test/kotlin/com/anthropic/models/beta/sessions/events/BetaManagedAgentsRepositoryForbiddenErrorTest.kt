package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsRepositoryForbiddenErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsRepositoryForbiddenError =
            BetaManagedAgentsRepositoryForbiddenError.builder()
                .message("The repository host refused access to the repository.")
                .repositoryUrl("https://github.com/example-org/example-repo")
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.of(
                        BetaManagedAgentsRetryStatusRetrying.Type.RETRYING
                    )
                )
                .build()

        assertThat(betaManagedAgentsRepositoryForbiddenError.message())
            .isEqualTo("The repository host refused access to the repository.")
        assertThat(betaManagedAgentsRepositoryForbiddenError.repositoryUrl())
            .contains("https://github.com/example-org/example-repo")
        assertThat(betaManagedAgentsRepositoryForbiddenError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsRepositoryForbiddenError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.Type.RETRYING
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsRepositoryForbiddenError =
            BetaManagedAgentsRepositoryForbiddenError.builder()
                .message("The repository host refused access to the repository.")
                .repositoryUrl("https://github.com/example-org/example-repo")
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.of(
                        BetaManagedAgentsRetryStatusRetrying.Type.RETRYING
                    )
                )
                .build()

        val roundtrippedBetaManagedAgentsRepositoryForbiddenError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsRepositoryForbiddenError),
                jacksonTypeRef<BetaManagedAgentsRepositoryForbiddenError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsRepositoryForbiddenError)
            .isEqualTo(betaManagedAgentsRepositoryForbiddenError)
    }
}
