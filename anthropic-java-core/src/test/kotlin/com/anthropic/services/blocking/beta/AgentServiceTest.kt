// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.agents.AgentArchiveParams
import com.anthropic.models.beta.agents.AgentCreateParams
import com.anthropic.models.beta.agents.AgentRetrieveParams
import com.anthropic.models.beta.agents.AgentUpdateParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolConfigParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401Params
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfigParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkillParams
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfigParams
import com.anthropic.models.beta.agents.BetaManagedAgentsUrlMcpServerParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class AgentServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val agentService = client.beta().agents()

        val betaManagedAgentsAgent =
            agentService.create(
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
                                BetaManagedAgentsAgentToolset20260401Params.Type
                                    .AGENT_TOOLSET_20260401
                            )
                            .addConfig(
                                BetaManagedAgentsAgentToolConfigParams.builder()
                                    .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                                    .enabled(true)
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
                                BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                    .enabled(true)
                                    .permissionPolicy(
                                        BetaManagedAgentsAlwaysAllowPolicy.builder()
                                            .type(
                                                BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                                            )
                                            .build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        betaManagedAgentsAgent.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val agentService = client.beta().agents()

        val betaManagedAgentsAgent =
            agentService.retrieve(
                AgentRetrieveParams.builder()
                    .agentId("agent_011CZkYpogX7uDKUyvBTophP")
                    .version(0)
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsAgent.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val agentService = client.beta().agents()

        val betaManagedAgentsAgent =
            agentService.update(
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
                                BetaManagedAgentsAgentToolset20260401Params.Type
                                    .AGENT_TOOLSET_20260401
                            )
                            .addConfig(
                                BetaManagedAgentsAgentToolConfigParams.builder()
                                    .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                                    .enabled(true)
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
                                BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                    .enabled(true)
                                    .permissionPolicy(
                                        BetaManagedAgentsAlwaysAllowPolicy.builder()
                                            .type(
                                                BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                                            )
                                            .build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        betaManagedAgentsAgent.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val agentService = client.beta().agents()

        val page = agentService.list()

        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val agentService = client.beta().agents()

        val betaManagedAgentsAgent =
            agentService.archive(
                AgentArchiveParams.builder()
                    .agentId("agent_011CZkYpogX7uDKUyvBTophP")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsAgent.validate()
    }
}
