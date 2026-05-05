// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsMultiagentParams
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AgentUpdateParamsTest {

    @Test
    fun create() {
        AgentUpdateParams.builder()
            .agentId("agent_011CZkYpogX7uDKUyvBTophP")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .version(1)
            .description("description")
            .addMcpServer(
                BetaManagedAgentsUrlMcpServerParams.builder()
                    .name("example-mcp")
                    .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
            .metadata(
                AgentUpdateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
            .model(
                BetaManagedAgentsModelConfigParams.builder()
                    .id(BetaManagedAgentsModel.CLAUDE_OPUS_4_6)
                    .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
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
            .name("name")
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
    fun pathParams() {
        val params =
            AgentUpdateParams.builder().agentId("agent_011CZkYpogX7uDKUyvBTophP").version(1).build()

        assertThat(params._pathParam(0)).isEqualTo("agent_011CZkYpogX7uDKUyvBTophP")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            AgentUpdateParams.builder()
                .agentId("agent_011CZkYpogX7uDKUyvBTophP")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .version(1)
                .description("description")
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .metadata(
                    AgentUpdateParams.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .model(
                    BetaManagedAgentsModelConfigParams.builder()
                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_4_6)
                        .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
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
                .name("name")
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
            AgentUpdateParams.builder().agentId("agent_011CZkYpogX7uDKUyvBTophP").version(1).build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            AgentUpdateParams.builder()
                .agentId("agent_011CZkYpogX7uDKUyvBTophP")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .version(1)
                .description("description")
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .metadata(
                    AgentUpdateParams.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .model(
                    BetaManagedAgentsModelConfigParams.builder()
                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_4_6)
                        .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
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
                .name("name")
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

        assertThat(body.version()).isEqualTo(1)
        assertThat(body.description()).contains("description")
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
                AgentUpdateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(body.model())
            .contains(
                AgentUpdateParams.Model.ofBetaManagedAgentsModelConfigParams(
                    BetaManagedAgentsModelConfigParams.builder()
                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_4_6)
                        .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
                        .build()
                )
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
        assertThat(body.name()).contains("name")
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
                AgentUpdateParams.Tool.ofAgentToolset20260401(
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
            AgentUpdateParams.builder().agentId("agent_011CZkYpogX7uDKUyvBTophP").version(1).build()

        val body = params._body()

        assertThat(body.version()).isEqualTo(1)
    }
}
