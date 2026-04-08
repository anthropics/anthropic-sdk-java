// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsGitHubRepositoryResourceParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsGitHubRepositoryResourceParams =
            BetaManagedAgentsGitHubRepositoryResourceParams.builder()
                .authorizationToken("ghp_exampletoken")
                .type(BetaManagedAgentsGitHubRepositoryResourceParams.Type.GITHUB_REPOSITORY)
                .url("https://github.com/example-org/example-repo")
                .branchCheckout("main")
                .mountPath("x")
                .build()

        assertThat(betaManagedAgentsGitHubRepositoryResourceParams.authorizationToken())
            .isEqualTo("ghp_exampletoken")
        assertThat(betaManagedAgentsGitHubRepositoryResourceParams.type())
            .isEqualTo(BetaManagedAgentsGitHubRepositoryResourceParams.Type.GITHUB_REPOSITORY)
        assertThat(betaManagedAgentsGitHubRepositoryResourceParams.url())
            .isEqualTo("https://github.com/example-org/example-repo")
        assertThat(betaManagedAgentsGitHubRepositoryResourceParams.checkout())
            .contains(
                BetaManagedAgentsGitHubRepositoryResourceParams.Checkout.ofBranch(
                    BetaManagedAgentsBranchCheckout.builder()
                        .name("main")
                        .type(BetaManagedAgentsBranchCheckout.Type.BRANCH)
                        .build()
                )
            )
        assertThat(betaManagedAgentsGitHubRepositoryResourceParams.mountPath()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsGitHubRepositoryResourceParams =
            BetaManagedAgentsGitHubRepositoryResourceParams.builder()
                .authorizationToken("ghp_exampletoken")
                .type(BetaManagedAgentsGitHubRepositoryResourceParams.Type.GITHUB_REPOSITORY)
                .url("https://github.com/example-org/example-repo")
                .branchCheckout("main")
                .mountPath("x")
                .build()

        val roundtrippedBetaManagedAgentsGitHubRepositoryResourceParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsGitHubRepositoryResourceParams),
                jacksonTypeRef<BetaManagedAgentsGitHubRepositoryResourceParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsGitHubRepositoryResourceParams)
            .isEqualTo(betaManagedAgentsGitHubRepositoryResourceParams)
    }
}
