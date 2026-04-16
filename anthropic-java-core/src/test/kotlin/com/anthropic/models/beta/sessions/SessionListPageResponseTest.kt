// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAskPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpServerUrlDefinition
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsFileResource
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsGitHubRepositoryResource
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SessionListPageResponseTest {

    @Test
    fun create() {
        val sessionListPageResponse =
            SessionListPageResponse.builder()
                .addData(
                    BetaManagedAgentsSession.builder()
                        .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                        .agent(
                            BetaManagedAgentsSessionAgent.builder()
                                .id("agent_011CZkYpogX7uDKUyvBTophP")
                                .description("A general-purpose starter agent.")
                                .addMcpServer(
                                    BetaManagedAgentsMcpServerUrlDefinition.builder()
                                        .name("example-mcp")
                                        .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                                        .url("https://example-server.modelcontextprotocol.io/sse")
                                        .build()
                                )
                                .model(
                                    BetaManagedAgentsModelConfig.builder()
                                        .id(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
                                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                        .build()
                                )
                                .name("My First Agent")
                                .addSkill(
                                    BetaManagedAgentsAnthropicSkill.builder()
                                        .skillId("xlsx")
                                        .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                                        .version("1")
                                        .build()
                                )
                                .addSkill(
                                    BetaManagedAgentsCustomSkill.builder()
                                        .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                                        .type(BetaManagedAgentsCustomSkill.Type.CUSTOM)
                                        .version("2")
                                        .build()
                                )
                                .system(
                                    "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                                )
                                .addTool(
                                    BetaManagedAgentsAgentToolset20260401.builder()
                                        .addConfig(
                                            BetaManagedAgentsAgentToolConfig.builder()
                                                .enabled(true)
                                                .name(BetaManagedAgentsAgentToolConfig.Name.BASH)
                                                .permissionPolicy(
                                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                                        .type(
                                                            BetaManagedAgentsAlwaysAllowPolicy.Type
                                                                .ALWAYS_ALLOW
                                                        )
                                                        .build()
                                                )
                                                .build()
                                        )
                                        .defaultConfig(
                                            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                                .enabled(true)
                                                .permissionPolicy(
                                                    BetaManagedAgentsAlwaysAskPolicy.builder()
                                                        .type(
                                                            BetaManagedAgentsAlwaysAskPolicy.Type
                                                                .ALWAYS_ASK
                                                        )
                                                        .build()
                                                )
                                                .build()
                                        )
                                        .type(
                                            BetaManagedAgentsAgentToolset20260401.Type
                                                .AGENT_TOOLSET_20260401
                                        )
                                        .build()
                                )
                                .type(BetaManagedAgentsSessionAgent.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .archivedAt(null)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                        .metadata(BetaManagedAgentsSession.Metadata.builder().build())
                        .addResource(
                            BetaManagedAgentsFileResource.builder()
                                .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                                .mountPath("/uploads/receipt.pdf")
                                .type(BetaManagedAgentsFileResource.Type.FILE)
                                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                                .build()
                        )
                        .addResource(
                            BetaManagedAgentsGitHubRepositoryResource.builder()
                                .id("sesrsc_011CZkZCKr6eXyl0gWMOdQiu")
                                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                                .mountPath("/workspace/example-repo")
                                .type(
                                    BetaManagedAgentsGitHubRepositoryResource.Type.GITHUB_REPOSITORY
                                )
                                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                                .url("https://github.com/example-org/example-repo")
                                .branchCheckout("main")
                                .build()
                        )
                        .stats(
                            BetaManagedAgentsSessionStats.builder()
                                .activeSeconds(0.0)
                                .durationSeconds(0.0)
                                .build()
                        )
                        .status(BetaManagedAgentsSession.Status.IDLE)
                        .title("Order #1234 inquiry")
                        .type(BetaManagedAgentsSession.Type.SESSION)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .usage(
                            BetaManagedAgentsSessionUsage.builder()
                                .cacheCreation(
                                    BetaManagedAgentsCacheCreationUsage.builder()
                                        .ephemeral1hInputTokens(0)
                                        .ephemeral5mInputTokens(0)
                                        .build()
                                )
                                .cacheReadInputTokens(0)
                                .inputTokens(0)
                                .outputTokens(0)
                                .build()
                        )
                        .addVaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(sessionListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsSession.builder()
                    .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .agent(
                        BetaManagedAgentsSessionAgent.builder()
                            .id("agent_011CZkYpogX7uDKUyvBTophP")
                            .description("A general-purpose starter agent.")
                            .addMcpServer(
                                BetaManagedAgentsMcpServerUrlDefinition.builder()
                                    .name("example-mcp")
                                    .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                                    .url("https://example-server.modelcontextprotocol.io/sse")
                                    .build()
                            )
                            .model(
                                BetaManagedAgentsModelConfig.builder()
                                    .id(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
                                    .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                    .build()
                            )
                            .name("My First Agent")
                            .addSkill(
                                BetaManagedAgentsAnthropicSkill.builder()
                                    .skillId("xlsx")
                                    .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                                    .version("1")
                                    .build()
                            )
                            .addSkill(
                                BetaManagedAgentsCustomSkill.builder()
                                    .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                                    .type(BetaManagedAgentsCustomSkill.Type.CUSTOM)
                                    .version("2")
                                    .build()
                            )
                            .system(
                                "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                            )
                            .addTool(
                                BetaManagedAgentsAgentToolset20260401.builder()
                                    .addConfig(
                                        BetaManagedAgentsAgentToolConfig.builder()
                                            .enabled(true)
                                            .name(BetaManagedAgentsAgentToolConfig.Name.BASH)
                                            .permissionPolicy(
                                                BetaManagedAgentsAlwaysAllowPolicy.builder()
                                                    .type(
                                                        BetaManagedAgentsAlwaysAllowPolicy.Type
                                                            .ALWAYS_ALLOW
                                                    )
                                                    .build()
                                            )
                                            .build()
                                    )
                                    .defaultConfig(
                                        BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                            .enabled(true)
                                            .permissionPolicy(
                                                BetaManagedAgentsAlwaysAskPolicy.builder()
                                                    .type(
                                                        BetaManagedAgentsAlwaysAskPolicy.Type
                                                            .ALWAYS_ASK
                                                    )
                                                    .build()
                                            )
                                            .build()
                                    )
                                    .type(
                                        BetaManagedAgentsAgentToolset20260401.Type
                                            .AGENT_TOOLSET_20260401
                                    )
                                    .build()
                            )
                            .type(BetaManagedAgentsSessionAgent.Type.AGENT)
                            .version(1)
                            .build()
                    )
                    .archivedAt(null)
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .metadata(BetaManagedAgentsSession.Metadata.builder().build())
                    .addResource(
                        BetaManagedAgentsFileResource.builder()
                            .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                            .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                            .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                            .mountPath("/uploads/receipt.pdf")
                            .type(BetaManagedAgentsFileResource.Type.FILE)
                            .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                            .build()
                    )
                    .addResource(
                        BetaManagedAgentsGitHubRepositoryResource.builder()
                            .id("sesrsc_011CZkZCKr6eXyl0gWMOdQiu")
                            .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                            .mountPath("/workspace/example-repo")
                            .type(BetaManagedAgentsGitHubRepositoryResource.Type.GITHUB_REPOSITORY)
                            .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                            .url("https://github.com/example-org/example-repo")
                            .branchCheckout("main")
                            .build()
                    )
                    .stats(
                        BetaManagedAgentsSessionStats.builder()
                            .activeSeconds(0.0)
                            .durationSeconds(0.0)
                            .build()
                    )
                    .status(BetaManagedAgentsSession.Status.IDLE)
                    .title("Order #1234 inquiry")
                    .type(BetaManagedAgentsSession.Type.SESSION)
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .usage(
                        BetaManagedAgentsSessionUsage.builder()
                            .cacheCreation(
                                BetaManagedAgentsCacheCreationUsage.builder()
                                    .ephemeral1hInputTokens(0)
                                    .ephemeral5mInputTokens(0)
                                    .build()
                            )
                            .cacheReadInputTokens(0)
                            .inputTokens(0)
                            .outputTokens(0)
                            .build()
                    )
                    .addVaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .build()
            )
        assertThat(sessionListPageResponse.nextPage()).contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val sessionListPageResponse =
            SessionListPageResponse.builder()
                .addData(
                    BetaManagedAgentsSession.builder()
                        .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                        .agent(
                            BetaManagedAgentsSessionAgent.builder()
                                .id("agent_011CZkYpogX7uDKUyvBTophP")
                                .description("A general-purpose starter agent.")
                                .addMcpServer(
                                    BetaManagedAgentsMcpServerUrlDefinition.builder()
                                        .name("example-mcp")
                                        .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                                        .url("https://example-server.modelcontextprotocol.io/sse")
                                        .build()
                                )
                                .model(
                                    BetaManagedAgentsModelConfig.builder()
                                        .id(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
                                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                        .build()
                                )
                                .name("My First Agent")
                                .addSkill(
                                    BetaManagedAgentsAnthropicSkill.builder()
                                        .skillId("xlsx")
                                        .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                                        .version("1")
                                        .build()
                                )
                                .addSkill(
                                    BetaManagedAgentsCustomSkill.builder()
                                        .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                                        .type(BetaManagedAgentsCustomSkill.Type.CUSTOM)
                                        .version("2")
                                        .build()
                                )
                                .system(
                                    "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                                )
                                .addTool(
                                    BetaManagedAgentsAgentToolset20260401.builder()
                                        .addConfig(
                                            BetaManagedAgentsAgentToolConfig.builder()
                                                .enabled(true)
                                                .name(BetaManagedAgentsAgentToolConfig.Name.BASH)
                                                .permissionPolicy(
                                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                                        .type(
                                                            BetaManagedAgentsAlwaysAllowPolicy.Type
                                                                .ALWAYS_ALLOW
                                                        )
                                                        .build()
                                                )
                                                .build()
                                        )
                                        .defaultConfig(
                                            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                                .enabled(true)
                                                .permissionPolicy(
                                                    BetaManagedAgentsAlwaysAskPolicy.builder()
                                                        .type(
                                                            BetaManagedAgentsAlwaysAskPolicy.Type
                                                                .ALWAYS_ASK
                                                        )
                                                        .build()
                                                )
                                                .build()
                                        )
                                        .type(
                                            BetaManagedAgentsAgentToolset20260401.Type
                                                .AGENT_TOOLSET_20260401
                                        )
                                        .build()
                                )
                                .type(BetaManagedAgentsSessionAgent.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .archivedAt(null)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                        .metadata(BetaManagedAgentsSession.Metadata.builder().build())
                        .addResource(
                            BetaManagedAgentsFileResource.builder()
                                .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                                .mountPath("/uploads/receipt.pdf")
                                .type(BetaManagedAgentsFileResource.Type.FILE)
                                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                                .build()
                        )
                        .addResource(
                            BetaManagedAgentsGitHubRepositoryResource.builder()
                                .id("sesrsc_011CZkZCKr6eXyl0gWMOdQiu")
                                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                                .mountPath("/workspace/example-repo")
                                .type(
                                    BetaManagedAgentsGitHubRepositoryResource.Type.GITHUB_REPOSITORY
                                )
                                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                                .url("https://github.com/example-org/example-repo")
                                .branchCheckout("main")
                                .build()
                        )
                        .stats(
                            BetaManagedAgentsSessionStats.builder()
                                .activeSeconds(0.0)
                                .durationSeconds(0.0)
                                .build()
                        )
                        .status(BetaManagedAgentsSession.Status.IDLE)
                        .title("Order #1234 inquiry")
                        .type(BetaManagedAgentsSession.Type.SESSION)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .usage(
                            BetaManagedAgentsSessionUsage.builder()
                                .cacheCreation(
                                    BetaManagedAgentsCacheCreationUsage.builder()
                                        .ephemeral1hInputTokens(0)
                                        .ephemeral5mInputTokens(0)
                                        .build()
                                )
                                .cacheReadInputTokens(0)
                                .inputTokens(0)
                                .outputTokens(0)
                                .build()
                        )
                        .addVaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedSessionListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(sessionListPageResponse),
                jacksonTypeRef<SessionListPageResponse>(),
            )

        assertThat(roundtrippedSessionListPageResponse).isEqualTo(sessionListPageResponse)
    }
}
