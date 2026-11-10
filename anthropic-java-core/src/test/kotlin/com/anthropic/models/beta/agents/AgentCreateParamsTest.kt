package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsMultiagentParams
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AgentCreateParamsTest {

    @Test
    fun create() {
        AgentCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .model(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
            .name("My First Agent")
            .description("A general-purpose starter agent.")
            .awsRoleExecutionIdentity("arn:aws:iam::228601553692:role/T")
            .addMcpServer(
                BetaManagedAgentsUrlMcpServerParams.builder()
                    .name("example-mcp")
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
            .metadata(
                AgentCreateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
            .multiagent(
                BetaManagedAgentsMultiagent20261001Params.builder()
                    .advisor(BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build())
                    .subagents(
                        BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                            .inlineAgents(
                                BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder()
                                    .build()
                            )
                            .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                            .build()
                    )
                    .workflows(
                        BetaManagedAgentsMultiagentWorkflowsEnabledParams.builder()
                            .inlineAgents(
                                BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder()
                                    .build()
                            )
                            .addPredefinedAgent(
                                BetaManagedAgentsAgentParams.builder()
                                    .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                    .version(1)
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
            .addSkill(
                BetaManagedAgentsAnthropicSkillParams.builder().skillId("xlsx").version("1").build()
            )
            .system(
                "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
            )
            .addTool(
                BetaManagedAgentsAgentToolset20260401Params.builder()
                    .addConfig(
                        BetaManagedAgentsBashToolConfigParams.builder()
                            .enabled(true)
                            .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                            .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                            .build()
                    )
                    .defaultConfig(
                        BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                            .enabled(true)
                            .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                            .build()
                    )
                    .build()
            )
            .build()
    }

    @Test
    fun headers() {
        val params =
            AgentCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .model(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                .name("My First Agent")
                .description("A general-purpose starter agent.")
                .awsRoleExecutionIdentity("arn:aws:iam::228601553692:role/T")
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .metadata(
                    AgentCreateParams.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .multiagent(
                    BetaManagedAgentsMultiagent20261001Params.builder()
                        .advisor(BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build())
                        .subagents(
                            BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                                .inlineAgents(
                                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder()
                                        .build()
                                )
                                .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .build()
                        )
                        .workflows(
                            BetaManagedAgentsMultiagentWorkflowsEnabledParams.builder()
                                .inlineAgents(
                                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder()
                                        .build()
                                )
                                .addPredefinedAgent(
                                    BetaManagedAgentsAgentParams.builder()
                                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                        .version(1)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .version("1")
                        .build()
                )
                .system(
                    "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                )
                .addTool(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .addConfig(
                            BetaManagedAgentsBashToolConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .build()
                        )
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "managed-agents-2026-04-01")
                            .joinToString(","),
                    )
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            AgentCreateParams.builder()
                .model(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                .name("My First Agent")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            AgentCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .model(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                .name("My First Agent")
                .description("A general-purpose starter agent.")
                .awsRoleExecutionIdentity("arn:aws:iam::228601553692:role/T")
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .metadata(
                    AgentCreateParams.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .multiagent(
                    BetaManagedAgentsMultiagent20261001Params.builder()
                        .advisor(BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build())
                        .subagents(
                            BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                                .inlineAgents(
                                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder()
                                        .build()
                                )
                                .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .build()
                        )
                        .workflows(
                            BetaManagedAgentsMultiagentWorkflowsEnabledParams.builder()
                                .inlineAgents(
                                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder()
                                        .build()
                                )
                                .addPredefinedAgent(
                                    BetaManagedAgentsAgentParams.builder()
                                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                        .version(1)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .version("1")
                        .build()
                )
                .system(
                    "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                )
                .addTool(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .addConfig(
                            BetaManagedAgentsBashToolConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .build()
                        )
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.model())
            .isEqualTo(
                AgentCreateParams.Model.ofBetaManagedAgents(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
            )
        assertThat(body.name()).isEqualTo("My First Agent")
        assertThat(body.description()).contains("A general-purpose starter agent.")
        assertThat(body.executionIdentity())
            .contains(
                BetaManagedAgentsExecutionIdentityParams.ofAwsRole(
                    "arn:aws:iam::228601553692:role/T"
                )
            )
        assertThat(body.mcpServers().getOrNull())
            .containsExactly(
                BetaManagedAgentsUrlMcpServerParams.builder()
                    .name("example-mcp")
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
        assertThat(body.metadata())
            .contains(
                AgentCreateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(body.multiagent())
            .contains(
                BetaManagedAgentsMultiagentParams.ofMultiagent20261001(
                    BetaManagedAgentsMultiagent20261001Params.builder()
                        .advisor(BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build())
                        .subagents(
                            BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                                .inlineAgents(
                                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder()
                                        .build()
                                )
                                .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .build()
                        )
                        .workflows(
                            BetaManagedAgentsMultiagentWorkflowsEnabledParams.builder()
                                .inlineAgents(
                                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder()
                                        .build()
                                )
                                .addPredefinedAgent(
                                    BetaManagedAgentsAgentParams.builder()
                                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                        .version(1)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
            )
        assertThat(body.skills().getOrNull())
            .containsExactly(
                BetaManagedAgentsSkillParams.ofAnthropic(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .version("1")
                        .build()
                )
            )
        assertThat(body.system())
            .contains(
                "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
            )
        assertThat(body.tools().getOrNull())
            .containsExactly(
                AgentCreateParams.Tool.ofAgentToolset20260401(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .addConfig(
                            BetaManagedAgentsBashToolConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
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
    fun bodyWithoutOptionalFields() {
        val params =
            AgentCreateParams.builder()
                .model(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                .name("My First Agent")
                .build()

        val body = params._body()

        assertThat(body.model())
            .isEqualTo(
                AgentCreateParams.Model.ofBetaManagedAgents(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
            )
        assertThat(body.name()).isEqualTo("My First Agent")
    }
}
