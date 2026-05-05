// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAskPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpServerUrlDefinition
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
import com.anthropic.models.beta.sessions.BetaManagedAgentsCacheCreationUsage
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThreadListPageResponseTest {

    @Test
    fun create() {
        val threadListPageResponse =
            ThreadListPageResponse.builder()
                .addData(
                    BetaManagedAgentsSessionThread.builder()
                        .id("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                        .agent(
                            BetaManagedAgentsSessionThreadAgent.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .description("A focused research subagent.")
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
                                .name("Researcher")
                                .addSkill(
                                    BetaManagedAgentsAnthropicSkill.builder()
                                        .skillId("xlsx")
                                        .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                                        .version("1")
                                        .build()
                                )
                                .system(
                                    "You are a research subagent that gathers and summarises sources for the coordinating agent."
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
                                .type(BetaManagedAgentsSessionThreadAgent.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .archivedAt(null)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .parentThreadId(null)
                        .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                        .stats(
                            BetaManagedAgentsSessionThreadStats.builder()
                                .activeSeconds(0.0)
                                .durationSeconds(0.0)
                                .startupSeconds(0.0)
                                .build()
                        )
                        .status(BetaManagedAgentsSessionThreadStatus.IDLE)
                        .type(BetaManagedAgentsSessionThread.Type.SESSION_THREAD)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .usage(
                            BetaManagedAgentsSessionThreadUsage.builder()
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
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(threadListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsSessionThread.builder()
                    .id("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                    .agent(
                        BetaManagedAgentsSessionThreadAgent.builder()
                            .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                            .description("A focused research subagent.")
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
                            .name("Researcher")
                            .addSkill(
                                BetaManagedAgentsAnthropicSkill.builder()
                                    .skillId("xlsx")
                                    .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                                    .version("1")
                                    .build()
                            )
                            .system(
                                "You are a research subagent that gathers and summarises sources for the coordinating agent."
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
                            .type(BetaManagedAgentsSessionThreadAgent.Type.AGENT)
                            .version(1)
                            .build()
                    )
                    .archivedAt(null)
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .parentThreadId(null)
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .stats(
                        BetaManagedAgentsSessionThreadStats.builder()
                            .activeSeconds(0.0)
                            .durationSeconds(0.0)
                            .startupSeconds(0.0)
                            .build()
                    )
                    .status(BetaManagedAgentsSessionThreadStatus.IDLE)
                    .type(BetaManagedAgentsSessionThread.Type.SESSION_THREAD)
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .usage(
                        BetaManagedAgentsSessionThreadUsage.builder()
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
                    .build()
            )
        assertThat(threadListPageResponse.nextPage()).contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val threadListPageResponse =
            ThreadListPageResponse.builder()
                .addData(
                    BetaManagedAgentsSessionThread.builder()
                        .id("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                        .agent(
                            BetaManagedAgentsSessionThreadAgent.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .description("A focused research subagent.")
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
                                .name("Researcher")
                                .addSkill(
                                    BetaManagedAgentsAnthropicSkill.builder()
                                        .skillId("xlsx")
                                        .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                                        .version("1")
                                        .build()
                                )
                                .system(
                                    "You are a research subagent that gathers and summarises sources for the coordinating agent."
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
                                .type(BetaManagedAgentsSessionThreadAgent.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .archivedAt(null)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .parentThreadId(null)
                        .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                        .stats(
                            BetaManagedAgentsSessionThreadStats.builder()
                                .activeSeconds(0.0)
                                .durationSeconds(0.0)
                                .startupSeconds(0.0)
                                .build()
                        )
                        .status(BetaManagedAgentsSessionThreadStatus.IDLE)
                        .type(BetaManagedAgentsSessionThread.Type.SESSION_THREAD)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .usage(
                            BetaManagedAgentsSessionThreadUsage.builder()
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
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedThreadListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(threadListPageResponse),
                jacksonTypeRef<ThreadListPageResponse>(),
            )

        assertThat(roundtrippedThreadListPageResponse).isEqualTo(threadListPageResponse)
    }
}
