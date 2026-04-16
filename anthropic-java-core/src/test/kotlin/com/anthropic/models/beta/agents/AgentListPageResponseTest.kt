// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AgentListPageResponseTest {

    @Test
    fun create() {
        val agentListPageResponse =
            AgentListPageResponse.builder()
                .addData(
                    BetaManagedAgentsAgent.builder()
                        .id("agent_011CZkYpogX7uDKUyvBTophP")
                        .archivedAt(null)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .description("A general-purpose starter agent.")
                        .addMcpServer(
                            BetaManagedAgentsMcpServerUrlDefinition.builder()
                                .name("example-mcp")
                                .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                                .url("https://example-server.modelcontextprotocol.io/sse")
                                .build()
                        )
                        .metadata(
                            BetaManagedAgentsAgent.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
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
                                                    BetaManagedAgentsAlwaysAskPolicy.Type.ALWAYS_ASK
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
                        .type(BetaManagedAgentsAgent.Type.AGENT)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .version(1)
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(agentListPageResponse.data().getOrNull())
            .containsExactly(
                BetaManagedAgentsAgent.builder()
                    .id("agent_011CZkYpogX7uDKUyvBTophP")
                    .archivedAt(null)
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .description("A general-purpose starter agent.")
                    .addMcpServer(
                        BetaManagedAgentsMcpServerUrlDefinition.builder()
                            .name("example-mcp")
                            .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                            .url("https://example-server.modelcontextprotocol.io/sse")
                            .build()
                    )
                    .metadata(
                        BetaManagedAgentsAgent.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
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
                                                BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
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
                                            .type(BetaManagedAgentsAlwaysAskPolicy.Type.ALWAYS_ASK)
                                            .build()
                                    )
                                    .build()
                            )
                            .type(BetaManagedAgentsAgentToolset20260401.Type.AGENT_TOOLSET_20260401)
                            .build()
                    )
                    .type(BetaManagedAgentsAgent.Type.AGENT)
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .version(1)
                    .build()
            )
        assertThat(agentListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val agentListPageResponse =
            AgentListPageResponse.builder()
                .addData(
                    BetaManagedAgentsAgent.builder()
                        .id("agent_011CZkYpogX7uDKUyvBTophP")
                        .archivedAt(null)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .description("A general-purpose starter agent.")
                        .addMcpServer(
                            BetaManagedAgentsMcpServerUrlDefinition.builder()
                                .name("example-mcp")
                                .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                                .url("https://example-server.modelcontextprotocol.io/sse")
                                .build()
                        )
                        .metadata(
                            BetaManagedAgentsAgent.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
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
                                                    BetaManagedAgentsAlwaysAskPolicy.Type.ALWAYS_ASK
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
                        .type(BetaManagedAgentsAgent.Type.AGENT)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .version(1)
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedAgentListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(agentListPageResponse),
                jacksonTypeRef<AgentListPageResponse>(),
            )

        assertThat(roundtrippedAgentListPageResponse).isEqualTo(agentListPageResponse)
    }
}
