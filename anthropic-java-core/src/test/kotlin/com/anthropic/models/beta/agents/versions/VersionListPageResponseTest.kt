package com.anthropic.models.beta.agents.versions

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgent
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAskPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsEffortLow
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpServerUrlDefinition
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsServiceAccountExecutionIdentity
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionListPageResponseTest {

    @Test
    fun create() {
        val versionListPageResponse =
            VersionListPageResponse.builder()
                .addData(
                    BetaManagedAgentsAgent.builder()
                        .id("agent_011CZkYpogX7uDKUyvBTophP")
                        .archivedAt(null)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .description("A general-purpose starter agent.")
                        .executionIdentity(
                            BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
                        )
                        .addMcpServer(
                            BetaManagedAgentsMcpServerUrlDefinition.builder()
                                .name("example-mcp")
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
                                .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                .effort(BetaManagedAgentsEffortLow.builder().build())
                                .inferenceGeo("inference_geo")
                                .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                .build()
                        )
                        .multiagent(null)
                        .name("My First Agent")
                        .addSkill(
                            BetaManagedAgentsAnthropicSkill.builder()
                                .skillId("xlsx")
                                .version("1")
                                .build()
                        )
                        .addSkill(
                            BetaManagedAgentsCustomSkill.builder()
                                .skillId("skill_011CZkZFNu9hAbo3jZPRgTmx")
                                .version("2")
                                .build()
                        )
                        .system(
                            "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                        )
                        .addTool(
                            BetaManagedAgentsAgentToolset20260401.builder()
                                .configs(listOf())
                                .defaultConfig(
                                    BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                        .enabled(true)
                                        .permissionPolicy(
                                            BetaManagedAgentsAlwaysAskPolicy.builder().build()
                                        )
                                        .build()
                                )
                                .build()
                        )
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .version(1)
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(versionListPageResponse.data())
            .containsExactly(
                BetaManagedAgentsAgent.builder()
                    .id("agent_011CZkYpogX7uDKUyvBTophP")
                    .archivedAt(null)
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .description("A general-purpose starter agent.")
                    .executionIdentity(
                        BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
                    )
                    .addMcpServer(
                        BetaManagedAgentsMcpServerUrlDefinition.builder()
                            .name("example-mcp")
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
                            .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                            .effort(BetaManagedAgentsEffortLow.builder().build())
                            .inferenceGeo("inference_geo")
                            .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                            .build()
                    )
                    .multiagent(null)
                    .name("My First Agent")
                    .addSkill(
                        BetaManagedAgentsAnthropicSkill.builder()
                            .skillId("xlsx")
                            .version("1")
                            .build()
                    )
                    .addSkill(
                        BetaManagedAgentsCustomSkill.builder()
                            .skillId("skill_011CZkZFNu9hAbo3jZPRgTmx")
                            .version("2")
                            .build()
                    )
                    .system(
                        "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                    )
                    .addTool(
                        BetaManagedAgentsAgentToolset20260401.builder()
                            .configs(listOf())
                            .defaultConfig(
                                BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                    .enabled(true)
                                    .permissionPolicy(
                                        BetaManagedAgentsAlwaysAskPolicy.builder().build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .version(1)
                    .build()
            )
        assertThat(versionListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val versionListPageResponse =
            VersionListPageResponse.builder()
                .addData(
                    BetaManagedAgentsAgent.builder()
                        .id("agent_011CZkYpogX7uDKUyvBTophP")
                        .archivedAt(null)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .description("A general-purpose starter agent.")
                        .executionIdentity(
                            BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
                        )
                        .addMcpServer(
                            BetaManagedAgentsMcpServerUrlDefinition.builder()
                                .name("example-mcp")
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
                                .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                .effort(BetaManagedAgentsEffortLow.builder().build())
                                .inferenceGeo("inference_geo")
                                .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                .build()
                        )
                        .multiagent(null)
                        .name("My First Agent")
                        .addSkill(
                            BetaManagedAgentsAnthropicSkill.builder()
                                .skillId("xlsx")
                                .version("1")
                                .build()
                        )
                        .addSkill(
                            BetaManagedAgentsCustomSkill.builder()
                                .skillId("skill_011CZkZFNu9hAbo3jZPRgTmx")
                                .version("2")
                                .build()
                        )
                        .system(
                            "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                        )
                        .addTool(
                            BetaManagedAgentsAgentToolset20260401.builder()
                                .configs(listOf())
                                .defaultConfig(
                                    BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                        .enabled(true)
                                        .permissionPolicy(
                                            BetaManagedAgentsAlwaysAskPolicy.builder().build()
                                        )
                                        .build()
                                )
                                .build()
                        )
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .version(1)
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedVersionListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(versionListPageResponse),
                jacksonTypeRef<VersionListPageResponse>(),
            )

        assertThat(roundtrippedVersionListPageResponse).isEqualTo(versionListPageResponse)
    }
}
