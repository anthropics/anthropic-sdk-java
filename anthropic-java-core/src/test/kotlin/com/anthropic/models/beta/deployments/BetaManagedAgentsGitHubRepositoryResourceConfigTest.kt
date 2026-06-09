// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.BetaManagedAgentsBranchCheckout
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsGitHubRepositoryResourceConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsGitHubRepositoryResourceConfig =
            BetaManagedAgentsGitHubRepositoryResourceConfig.builder()
                .type(BetaManagedAgentsGitHubRepositoryResourceConfig.Type.GITHUB_REPOSITORY)
                .url("url")
                .branchCheckout("main")
                .mountPath("mount_path")
                .build()

        assertThat(betaManagedAgentsGitHubRepositoryResourceConfig.type())
            .isEqualTo(BetaManagedAgentsGitHubRepositoryResourceConfig.Type.GITHUB_REPOSITORY)
        assertThat(betaManagedAgentsGitHubRepositoryResourceConfig.url()).isEqualTo("url")
        assertThat(betaManagedAgentsGitHubRepositoryResourceConfig.checkout())
            .contains(
                BetaManagedAgentsGitHubRepositoryResourceConfig.Checkout.ofBranch(
                    BetaManagedAgentsBranchCheckout.builder()
                        .name("main")
                        .type(BetaManagedAgentsBranchCheckout.Type.BRANCH)
                        .build()
                )
            )
        assertThat(betaManagedAgentsGitHubRepositoryResourceConfig.mountPath())
            .contains("mount_path")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsGitHubRepositoryResourceConfig =
            BetaManagedAgentsGitHubRepositoryResourceConfig.builder()
                .type(BetaManagedAgentsGitHubRepositoryResourceConfig.Type.GITHUB_REPOSITORY)
                .url("url")
                .branchCheckout("main")
                .mountPath("mount_path")
                .build()

        val roundtrippedBetaManagedAgentsGitHubRepositoryResourceConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsGitHubRepositoryResourceConfig),
                jacksonTypeRef<BetaManagedAgentsGitHubRepositoryResourceConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsGitHubRepositoryResourceConfig)
            .isEqualTo(betaManagedAgentsGitHubRepositoryResourceConfig)
    }
}
