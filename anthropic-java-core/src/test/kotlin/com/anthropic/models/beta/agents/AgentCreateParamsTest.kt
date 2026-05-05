// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsMultiagentParams
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AgentCreateParamsTest {

    @Test
    fun create() {
        AgentCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .model(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
            .name("My First Agent")
            .description("A general-purpose starter agent.")
            .addMcpServer(
                BetaManagedAgentsUrlMcpServerParams.builder()
                    .name("example-mcp")
                    .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
            .metadata(
                AgentCreateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
            .multiagent(
                BetaManagedAgentsMultiagentParams.builder()
                    .addAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                    .addAgent(
                        BetaManagedAgentsMultiagentSelfParams.builder()
                            .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                            .build()
                    )
                    .type(BetaManagedAgentsMultiagentParams.Type.COORDINATOR)
                    .build()
            )
            .addSkill(
                BetaManagedAgentsAnthropicSkillParams.builder()
                    .skillId("xlsx")
                    .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                    .version("1")
                    .build()
            )
            .system(
                "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
            )
            .addTool(
                BetaManagedAgentsAgentToolset20260401Params.builder()
                    .type(BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401)
                    .addConfig(
                        BetaManagedAgentsAgentToolConfigParams.builder()
                            .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                            .enabled(true)
                            .permissionPolicy(
                                BetaManagedAgentsAlwaysAllowPolicy.builder()
                                    .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                    .build()
                            )
                            .build()
                    )
                    .defaultConfig(
                        BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                            .enabled(true)
                            .permissionPolicy(
                                BetaManagedAgentsAlwaysAllowPolicy.builder()
                                    .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                    .build()
                            )
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
                .model(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
                .name("My First Agent")
                .description("A general-purpose starter agent.")
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .metadata(
                    AgentCreateParams.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .multiagent(
                    BetaManagedAgentsMultiagentParams.builder()
                        .addAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .addAgent(
                            BetaManagedAgentsMultiagentSelfParams.builder()
                                .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                                .build()
                        )
                        .type(BetaManagedAgentsMultiagentParams.Type.COORDINATOR)
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                        .version("1")
                        .build()
                )
                .system(
                    "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                )
                .addTool(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .type(
                            BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401
                        )
                        .addConfig(
                            BetaManagedAgentsAgentToolConfigParams.builder()
                                .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            AgentCreateParams.builder()
                .model(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
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
                .model(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
                .name("My First Agent")
                .description("A general-purpose starter agent.")
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .metadata(
                    AgentCreateParams.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .multiagent(
                    BetaManagedAgentsMultiagentParams.builder()
                        .addAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .addAgent(
                            BetaManagedAgentsMultiagentSelfParams.builder()
                                .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                                .build()
                        )
                        .type(BetaManagedAgentsMultiagentParams.Type.COORDINATOR)
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                        .version("1")
                        .build()
                )
                .system(
                    "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                )
                .addTool(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .type(
                            BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401
                        )
                        .addConfig(
                            BetaManagedAgentsAgentToolConfigParams.builder()
                                .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.model())
            .isEqualTo(
                AgentCreateParams.Model.ofBetaManagedAgents(
                    BetaManagedAgentsModel.CLAUDE_SONNET_4_6
                )
            )
        assertThat(body.name()).isEqualTo("My First Agent")
        assertThat(body.description()).contains("A general-purpose starter agent.")
        assertThat(body.mcpServers().getOrNull())
            .containsExactly(
                BetaManagedAgentsUrlMcpServerParams.builder()
                    .name("example-mcp")
                    .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
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
                BetaManagedAgentsMultiagentParams.builder()
                    .addAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                    .addAgent(
                        BetaManagedAgentsMultiagentSelfParams.builder()
                            .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                            .build()
                    )
                    .type(BetaManagedAgentsMultiagentParams.Type.COORDINATOR)
                    .build()
            )
        assertThat(body.skills().getOrNull())
            .containsExactly(
                BetaManagedAgentsSkillParams.ofAnthropic(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
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
                        .type(
                            BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401
                        )
                        .addConfig(
                            BetaManagedAgentsAgentToolConfigParams.builder()
                                .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
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
                .model(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
                .name("My First Agent")
                .build()

        val body = params._body()

        assertThat(body.model())
            .isEqualTo(
                AgentCreateParams.Model.ofBetaManagedAgents(
                    BetaManagedAgentsModel.CLAUDE_SONNET_4_6
                )
            )
        assertThat(body.name()).isEqualTo("My First Agent")
    }
}
