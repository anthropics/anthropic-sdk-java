// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.BetaManagedAgentsBranchCheckout
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsGitHubRepositoryResourceTest {

    @Test
    fun create() {
        val betaManagedAgentsGitHubRepositoryResource =
            BetaManagedAgentsGitHubRepositoryResource.builder()
                .id("sesrsc_011CZkZCKr6eXyl0gWMOdQiu")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .mountPath("/workspace/example-repo")
                .type(BetaManagedAgentsGitHubRepositoryResource.Type.GITHUB_REPOSITORY)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .url("https://github.com/example-org/example-repo")
                .branchCheckout("main")
                .build()

        assertThat(betaManagedAgentsGitHubRepositoryResource.id())
            .isEqualTo("sesrsc_011CZkZCKr6eXyl0gWMOdQiu")
        assertThat(betaManagedAgentsGitHubRepositoryResource.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsGitHubRepositoryResource.mountPath())
            .isEqualTo("/workspace/example-repo")
        assertThat(betaManagedAgentsGitHubRepositoryResource.type())
            .isEqualTo(BetaManagedAgentsGitHubRepositoryResource.Type.GITHUB_REPOSITORY)
        assertThat(betaManagedAgentsGitHubRepositoryResource.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsGitHubRepositoryResource.url())
            .isEqualTo("https://github.com/example-org/example-repo")
        assertThat(betaManagedAgentsGitHubRepositoryResource.checkout())
            .contains(
                BetaManagedAgentsGitHubRepositoryResource.Checkout.ofBranch(
                    BetaManagedAgentsBranchCheckout.builder()
                        .name("main")
                        .type(BetaManagedAgentsBranchCheckout.Type.BRANCH)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsGitHubRepositoryResource =
            BetaManagedAgentsGitHubRepositoryResource.builder()
                .id("sesrsc_011CZkZCKr6eXyl0gWMOdQiu")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .mountPath("/workspace/example-repo")
                .type(BetaManagedAgentsGitHubRepositoryResource.Type.GITHUB_REPOSITORY)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .url("https://github.com/example-org/example-repo")
                .branchCheckout("main")
                .build()

        val roundtrippedBetaManagedAgentsGitHubRepositoryResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsGitHubRepositoryResource),
                jacksonTypeRef<BetaManagedAgentsGitHubRepositoryResource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsGitHubRepositoryResource)
            .isEqualTo(betaManagedAgentsGitHubRepositoryResource)
    }
}
