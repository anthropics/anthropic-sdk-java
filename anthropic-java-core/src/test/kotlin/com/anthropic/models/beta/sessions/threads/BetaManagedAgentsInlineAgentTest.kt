package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsEffortLow
import com.anthropic.models.beta.agents.BetaManagedAgentsExecutionIdentity
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsServiceAccountExecutionIdentity
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsInlineAgentTest {

    @Test
    fun create() {
        val betaManagedAgentsInlineAgent =
            BetaManagedAgentsInlineAgent.builder()
                .description(null)
                .executionIdentity(
                    BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
                )
                .mcpServers(listOf())
                .model(
                    BetaManagedAgentsModelConfig.builder()
                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                        .effort(BetaManagedAgentsEffortLow.builder().build())
                        .inferenceGeo("inference_geo")
                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                        .build()
                )
                .name("pdf-reader-3")
                .skills(listOf())
                .system("You extract tables precisely. Output CSV only.")
                .addTool(
                    BetaManagedAgentsAgentToolset20260401.builder()
                        .configs(listOf())
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsInlineAgent.description()).isEmpty
        assertThat(betaManagedAgentsInlineAgent.executionIdentity())
            .isEqualTo(
                BetaManagedAgentsExecutionIdentity.ofServiceAccount(
                    BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
                )
            )
        assertThat(betaManagedAgentsInlineAgent.mcpServers()).containsExactly()
        assertThat(betaManagedAgentsInlineAgent.model())
            .isEqualTo(
                BetaManagedAgentsModelConfig.builder()
                    .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                    .effort(BetaManagedAgentsEffortLow.builder().build())
                    .inferenceGeo("inference_geo")
                    .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                    .build()
            )
        assertThat(betaManagedAgentsInlineAgent.name()).isEqualTo("pdf-reader-3")
        assertThat(betaManagedAgentsInlineAgent.skills()).containsExactly()
        assertThat(betaManagedAgentsInlineAgent.system())
            .contains("You extract tables precisely. Output CSV only.")
        assertThat(betaManagedAgentsInlineAgent.tools())
            .containsExactly(
                BetaManagedAgentsInlineAgent.Tool.ofAgentToolset20260401(
                    BetaManagedAgentsAgentToolset20260401.builder()
                        .configs(listOf())
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .build()
                        )
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsInlineAgent =
            BetaManagedAgentsInlineAgent.builder()
                .description(null)
                .executionIdentity(
                    BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
                )
                .mcpServers(listOf())
                .model(
                    BetaManagedAgentsModelConfig.builder()
                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                        .effort(BetaManagedAgentsEffortLow.builder().build())
                        .inferenceGeo("inference_geo")
                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                        .build()
                )
                .name("pdf-reader-3")
                .skills(listOf())
                .system("You extract tables precisely. Output CSV only.")
                .addTool(
                    BetaManagedAgentsAgentToolset20260401.builder()
                        .configs(listOf())
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsInlineAgent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsInlineAgent),
                jacksonTypeRef<BetaManagedAgentsInlineAgent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsInlineAgent).isEqualTo(betaManagedAgentsInlineAgent)
    }
}
